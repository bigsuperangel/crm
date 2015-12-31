package com.crm.business.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.business.dao.CustomerDao;
import com.crm.business.dao.IDelayApplyDao;
import com.crm.business.service.IDelayApplyService;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Customer;
import com.crm.model.DelayApply;
import com.crm.model.User;

/**
 * 延时申请服务
 * 
 * @author wukh
 * @2015-3-3
 */
@Service("delayApplyService")
public class DelayApplyServiceImpl extends BaseService implements
		IDelayApplyService {

	@Autowired
	@Qualifier("delayApplyDao")
	private IDelayApplyDao delayApplyDao;

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;

	@Override
	public void addDelayApply(int customerId, int delayDays, User user)
			throws CrmException {

		Customer customer = customerDao.getObject(customerId);
		if (customer.getHandler() == null) {
			throw new CrmException("用户未分配");
		}
		if (customer.getHandler().getId() != user.getId()) {
			throw new CrmException("不能对不归属于自己的用户发起延时申请");
		}
		if (delayDays <= 0) {
			throw new CrmException("无效的延时时长");
		}
		int applyCount = getCustomerApplyCount(customer.getId());
		if (applyCount > 0) {
			throw new CrmException("不能对相同用户发起延时申请");
		}
		DelayApply delayApply = new DelayApply();
		delayApply.setCustomer(customer);
		delayApply.setDelayDays(delayDays);
		delayApply.setApplier(user);
		delayApply.setAudit(0);
		delayApply.setCdate(DateUtil.getFormatedCurrentDate());

		delayApplyDao.add(delayApply);
	}

	@Override
	public void audit(int delayApplyId, int audit, User user)
			throws CrmException {
		if (!user.isUserSupervisor()) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}

		if (audit != 1 && audit != 2) {
			throw new CrmException("无效的审核状态");
		}

		DelayApply oldApply = delayApplyDao.getObject(delayApplyId);
		if (oldApply.getAudit() != 0) {
			throw new CrmException("不能重复审核");
		}

		Customer customer = oldApply.getCustomer();
		if (!customer.getDept().isChildOf(user.getDept())) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}

		oldApply.setAudit(audit);
		delayApplyDao.update(oldApply);

		// 对客户执行延时
		if (audit == 1) {
			Date date = DateUtil.getDateFromStr(customer.getFreeTime());
			String newStr = DateUtil.getFormatedFixedDate(date, oldApply
					.getDelayDays());
			customer.setAuditTime(newStr);
			customerDao.update(customer);
		}
	}

	@Override
	public PageModel<DelayApply> queryMyAuditList(int page, int limit,
			String startTime, String endTime, String customerName,
			Integer audit, User user) throws CrmException {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from DelayApply a where a.applier.id = "
				+ user.getId());
		if (audit != null) {
			hql.append(" and a.audit = " + audit);
		}

		if (!StringUtils.isEmpty(startTime)) {
			hql.append(" and a.cdate >= '" + startTime + "'");
		}
		if (!StringUtils.isEmpty(endTime)) {
			hql.append(" and a.cdate <= '" + endTime + "'");
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and a.customer.customerName like '%" + customerName
					+ "%'");
		}

		return delayApplyDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public PageModel<DelayApply> queryUnAuditList(int page, int limit,
			String startTime, String endTime, String customerName, User user)
			throws CrmException {
		if (!user.isUserSupervisor()) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		StringBuilder hql = new StringBuilder();
		hql
				.append("select a from DelayApply a where a.customer.dept.code like '"
						+ user.getDept().getCode() + "%' and a.audit = 0");
		if (!StringUtils.isEmpty(startTime)) {
			hql.append(" and a.cdate >= '" + startTime + "'");
		}
		if (!StringUtils.isEmpty(endTime)) {
			hql.append(" and a.cdate <= '" + endTime + "'");
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and a.customer.customerName like '%" + customerName
					+ "%'");
		}
		return delayApplyDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void recall(int delayApplyId, User user) throws CrmException {
		DelayApply delayApply = delayApplyDao.getObject(delayApplyId);

		int audit = delayApply.getAudit();
		if (audit != 0) {
			throw new CrmException("请求已被审核, 无法撤销");
		}
		if (delayApply.getApplier().getId() != user.getId()) {
			throw new CrmException("请求发起人不是当前用户,无法撤销");
		}
		delayApply.setAudit(4);

		delayApplyDao.update(delayApply);
	}

	private int getCustomerApplyCount(int customerId) {
		try {
			StringBuilder hql = new StringBuilder();
			hql
					.append("select count(1) from DelayApply d where d.audit = 0 and d.customer.id = "
							+ customerId);
			Object value = delayApplyDao.queryUniqueValue(hql.toString());
			if (value != null) {
				return Integer.parseInt(value.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
