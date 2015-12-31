package com.crm.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.business.dao.CustomerDao;
import com.crm.business.dao.ICooperationDao;
import com.crm.business.service.ICooperationService;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Cooperation;
import com.crm.model.Customer;
import com.crm.model.User;
import com.crm.sys.dao.UserDao;

@Service("cooperationService")
public class CooperationServiceImpl extends BaseService implements
		ICooperationService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("cooperationDao")
	private ICooperationDao cooperationDao;

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;

	@Override
	public void acceptCooperation(int cooperateId, User user, boolean isAccept)
			throws CrmException {
		Cooperation cooperation = cooperationDao.getObject(cooperateId);

		Customer customer = cooperation.getCustomer();
		// 检验有效性
		if (cooperation.getCooperatorType() == Cooperation.TYPE_ACROSS_AREA
				&& cooperation.getStatus() == Cooperation.STATUS_SUBMIT_AUDIT) {
			// 跨区域合作
			handleAccept(cooperation, user, customer, isAccept);
		} else if (cooperation.getCooperatorType() == Cooperation.TYPE_ACROSS_BIG_AREA
				&& cooperation.getStatus() == Cooperation.STATUS_TARGET_AUDIT) {
			// 跨大区合作
			handleAccept(cooperation, user, customer, isAccept);
		} else {
			throw new CrmException("请先审核此合作");
		}
	}

	/**
	 * 处理接收/拒绝合作
	 * 
	 * @param cooperation
	 * @param user
	 * @param isAccept
	 */
	private void handleAccept(Cooperation cooperation, User user,
			Customer customer, boolean isAccept) {
		cooperation.setOpTime(DateUtil.getFormatedCurrentDate());
		if (isAccept) {
			// 接受合作
			cooperation.setStatus(Cooperation.STATUS_SUCCESS);
			customer.setStatus(1);
			customer.setHandler(user);
			customer.setAuditTime(DateUtil.getFormatedCurrentDate());

			cooperationDao.update(cooperation);
			customerDao.update(customer);
		} else {
			// 拒绝合作
			cooperation.setStatus(Cooperation.STATUS_FAIL);
			//拒绝合作时, 客户进入公海
			customer.setStatus(3);

			cooperationDao.update(cooperation);
			customerDao.update(customer);
		}
	}

	@Override
	public void refuseCooperation(int cooperateId, User self)
			throws CrmException {
		// 检查权限
		Cooperation cooperation = cooperationDao.getObject(cooperateId);
		if (cooperation.getCooperatorType() == Cooperation.TYPE_ACROSS_AREA) {
			// 跨区域合作
			if (cooperation.getStatus() != Cooperation.STATUS_NEW) { // 检查当前步骤
				throw new CrmException("合作状态校验失败");
			}
			// 审批不通过, 客户也视为无效
			Customer customer = cooperation.getCustomer();
			customer.setIsValid(0);
			customerDao.update(customer);

			cooperation.setStatus(Cooperation.STATUS_FAIL);
			cooperation.setOpTime(DateUtil.getFormatedCurrentDate());
			cooperationDao.update(cooperation);

		} else {
			// 跨大区
			if (cooperation.getStatus() == Cooperation.STATUS_NEW) {
				// 发起方区域经理审批
				if (cooperation.getSubmitDept().isChildOf(self.getDept())) {// 检查权限
					// 审批不通过, 客户也视为无效
					Customer customer = cooperation.getCustomer();
					customer.setIsValid(0);
					customerDao.update(customer);

					cooperation.setStatus(Cooperation.STATUS_FAIL);
					cooperation.setOpTime(DateUtil.getFormatedCurrentDate());
					cooperationDao.update(cooperation);
				} else {
					throw new CrmException(
							CrmException.MSG_NOT_ENOUGH_AUTHORITY);
				}

			} else if (cooperation.getStatus() == Cooperation.STATUS_SUBMIT_AUDIT) {
				// 接收方区域经理审批
				if (cooperation.getTargetDept().isChildOf(self.getDept())) {// 检查权限
					// 审批不通过, 客户处理成发起方的公海客户
					Customer customer = cooperation.getCustomer();
					customer.setDept(cooperation.getSubmitDept());
					customer.setStatus(1);
					customerDao.update(customer);

					cooperation.setStatus(Cooperation.STATUS_FAIL);
					cooperation.setOpTime(DateUtil.getFormatedCurrentDate());
					cooperationDao.update(cooperation);

				} else {
					throw new CrmException(
							CrmException.MSG_NOT_ENOUGH_AUTHORITY);
				}
			}
		}
	}

	@Override
	public void auditCooperation(int cooperateId, Integer receiverId, User self)
			throws CrmException {
		// 检查权限
		if (!userDao.isUserSupervisor(self)) {
			throw new CrmException(CrmException.MSG_USER_NOT_SUPERVISOR);
		}
		Cooperation cooperation = cooperationDao.getObject(cooperateId);
		if (cooperation.getCooperatorType() == Cooperation.TYPE_ACROSS_AREA) {
			// 跨区域合作
			if (cooperation.getStatus() != Cooperation.STATUS_NEW) { // 检查当前步骤
				throw new CrmException("合作状态校验失败");
			}
			// 检查接收者
			User receiver = userDao.getObject(receiverId);
			if (receiver == null) {
				throw new CrmException("未找到合作接收者");
			}
			cooperation.setReceiver(receiver);
			cooperation.setTargetDept(receiver.getDept());
			cooperation.setStatus(Cooperation.STATUS_SUBMIT_AUDIT);
			cooperation.setOpTime(DateUtil.getFormatedCurrentDate());

			cooperationDao.update(cooperation);
		} else {
			// 跨大区
			if (cooperation.getStatus() == Cooperation.STATUS_NEW) {
				// 发起方区域经理审批
				if (cooperation.getSubmitDept().isChildOf(self.getDept())) {// 检查权限
					cooperation.setStatus(Cooperation.STATUS_SUBMIT_AUDIT);
					cooperation.setOpTime(DateUtil.getFormatedCurrentDate());

					cooperationDao.update(cooperation);
				} else {
					throw new CrmException(
							CrmException.MSG_NOT_ENOUGH_AUTHORITY);
				}

			} else if (cooperation.getStatus() == Cooperation.STATUS_SUBMIT_AUDIT) {
				// 接收方区域经理审批
				if (cooperation.getTargetDept().isChildOf(self.getDept())) {// 检查权限
					// 检查接收者
					User receiver = userDao.getObject(receiverId);
					if (receiver == null) {
						throw new CrmException("未找到合作接收者");
					}
					cooperation.setReceiver(receiver);
					cooperation.setTargetDept(receiver.getDept());
					cooperation.setStatus(Cooperation.STATUS_TARGET_AUDIT);
					cooperation.setOpTime(DateUtil.getFormatedCurrentDate());

					cooperationDao.update(cooperation);

				} else {
					throw new CrmException(
							CrmException.MSG_NOT_ENOUGH_AUTHORITY);
				}
			}
		}
	}

	@Override
	public PageModel<Cooperation> queryAcceptCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException {
		StringBuilder hql = new StringBuilder();
		hql
				.append("select c from Cooperation c where c.isValid = 1 and c.receiver.id = "
						+ user.getId());
		if (cooperateType == null) {
			hql.append("  and ((c.cooperatorType = "
					+ Cooperation.TYPE_ACROSS_AREA + " and c.status = "
					+ Cooperation.STATUS_SUBMIT_AUDIT
					+ ") or (c.cooperatorType = "
					+ Cooperation.TYPE_ACROSS_BIG_AREA + " and c.status = "
					+ Cooperation.STATUS_TARGET_AUDIT + ")) ");
		} else {
			switch (cooperateType.intValue()) {
			case Cooperation.TYPE_ACROSS_AREA:
				// 跨区域合作
				hql.append(" and c.cooperatorType = "
						+ Cooperation.TYPE_ACROSS_AREA + " and c.status = "
						+ Cooperation.STATUS_SUBMIT_AUDIT);
				break;

			case Cooperation.TYPE_ACROSS_BIG_AREA:
				hql.append(" and c.cooperatorType = "
						+ Cooperation.TYPE_ACROSS_BIG_AREA + " and c.status = "
						+ Cooperation.STATUS_TARGET_AUDIT);
				break;

			default:
				break;
			}
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customer.customerName like '%" + customerName
					+ "%'");
		}
		if (status != null) {
			hql.append(" and c.status = " + status);
		}

		return cooperationDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public Cooperation queryCooperation(int cooperationId) throws CrmException {
		return cooperationDao.getObject(cooperationId);
	}

	@Override
	public PageModel<Cooperation> querySubmitedCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException {
		StringBuilder hql = new StringBuilder();
		hql
				.append("select c from Cooperation c where c.isValid = 1 and c.submitor.id = "
						+ user.getId());
		if (cooperateType != null) {
			hql.append(" and c.cooperatorType = " + cooperateType);
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customer.customerName like '%" + customerName
					+ "%'");
		}
		if (status != null) {
			hql.append(" and c.status = " + status);
		}

		return cooperationDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public PageModel<Cooperation> queryUnauditCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException {
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_USER_NOT_SUPERVISOR);
		}
		StringBuilder hql = new StringBuilder();
		hql.append("select c from Cooperation c where c.isValid = 1");
		if (cooperateType == null) {
			// 查询全部
			hql
					.append(" and ((c.cooperatorType = 0 and c.status = 0 and c.submitDept.code like '"
							+ user.getDept().getCode()
							+ "%') or (c.cooperatorType = 1 and c.status = 1 and c.targetDept.code like '"
							+ user.getDept().getCode() + "%')) ");
		} else {
			switch (cooperateType.intValue()) {
			case Cooperation.TYPE_ACROSS_AREA:
				// 跨区域合作
				hql
						.append(" and c.cooperatorType = 0 and c.status = 0 and c.submitDept.code like '"
								+ user.getDept().getCode() + "%'");
				break;

			case Cooperation.TYPE_ACROSS_BIG_AREA:
				// 跨大区合作
				hql
						.append(" and c.cooperatorType = 1 and c.status = 1 and c.targetDept.code like '"
								+ user.getDept().getCode() + "%'");

			default:
				break;
			}
		}

		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customer.customerName like '%" + customerName
					+ "%'");
		}
		if (status != null) {
			hql.append(" and c.status = " + status);
		}

		return cooperationDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void startCooperation(Cooperation cooperation, User user)
			throws CrmException {
		String currentDate = DateUtil.getFormatedCurrentDate();
		// 保存客户
		Customer customer = cooperation.getCustomer();
		customer.setStatus(2);
		customer.setCdate(currentDate);
		customer.setCreater(user);
		customer.setDept(cooperation.getTargetDept());
		customerDao.add(customer);
		// 保存合作
		cooperation.setStatus(Cooperation.STATUS_NEW);
		cooperation.setSubmitDept(user.getDept());
		cooperation.setSubmitor(user);
		cooperation.setCdate(currentDate);
		cooperation.setOpTime(currentDate);
		cooperation.setCustomer(customer);
		cooperationDao.add(cooperation);
	}

}
