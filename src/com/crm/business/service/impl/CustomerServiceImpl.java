package com.crm.business.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.business.dao.CustomerDao;
import com.crm.business.dao.IApplyDao;
import com.crm.business.service.ICustomerService;
import com.crm.common.PageModel;
import com.crm.common.utils.Constant;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Apply;
import com.crm.model.Customer;
import com.crm.model.Dept;
import com.crm.model.User;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.dao.IDicDao;
import com.crm.sys.dao.UserDao;

/**
 * 用户管理服务类
 * 
 * @author wukh
 * @2015-1-21
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseService implements
		ICustomerService {

	private static final Logger log = Logger
			.getLogger(CustomerServiceImpl.class.getSimpleName());

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("dicDao")
	private IDicDao dicDao;

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;

	@Autowired
	@Qualifier("deptDao")
	private DeptDao deptDao;

	@Autowired
	@Qualifier("applyDao")
	private IApplyDao applyDao;

	@Override
	public void deleteCustomer(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public Customer queryCustomer(int id) {

		return customerDao.getObject(id);
	}

	@Override
	public PageModel<Customer> queryCustomerList(int page, int limit) {

		return customerDao.queryCustomerList(page, limit);
	}

	@Override
	public void updateCustomer(Customer customer) throws CrmException {
		try {
			Customer c = customerDao.getObject(customer.getId());
			BeanUtils.copyProperties(customer, c, new String[] { "id",
					"isValid","status" });
			customerDao.update(c);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CrmException("操作失败");
		}
	}

	public static void main(String[] args) throws Exception {
		Customer c = new Customer();
		c.setBrand("222");
		c.setCompanyAddress("33");
		c.setCustomerName("444");
		Customer dest = new Customer();
		dest.setCustomerName("555");
		dest.setCdate("53534");
		dest.setId(8);
		BeanUtils.copyProperties(dest, c);
		System.out.println(dest.getJson());
		System.out.println(c.getJson());
	}

	@Override
	public void deleteCustomers(Integer[] ids) {
		customerDao.delete(ids);
	}

	@Override
	public String getComboboxList() {
		String hql = "select c from Customer c";
		List<Customer> list = customerDao.getList(hql);
		StringBuffer sb = new StringBuffer("{topics:[");
		for (int i = 0; i < list.size(); i++) {
			Customer c = list.get(i);
			sb.append(c.combobox());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public PageModel<Customer> queryPublicCustomerList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, int deptId) {
		Dept dept = deptDao.getObject(deptId);

		StringBuilder hql = new StringBuilder();
		hql
				.append("select c from Customer c where c.dept.code like '"
						+ dept.getCode()
						+ "%' and c.handler is null and c.isValid = 1 and c.audit = 1 and c.status = 3");

		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customerName like '%" + customerName + "%'");
		}
		if (!StringUtils.isEmpty(brandName)) {
			hql.append(" and c.brand like '%" + brandName + "%'");
		}
		if (!StringUtils.isEmpty(startDate)) {
			hql.append(" and c.cdate >= '" + startDate + "'");
		}
		if (!StringUtils.isEmpty(endDate)) {
			hql.append(" and c.cdate <= '" + endDate + "'");
		}

		return customerDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void addCustomer(Customer customer, User user) throws CrmException {
		int status = customer.getStatus();
		if (status == 1) {
			customer.setHandler(user);
		}
		User handler = customer.getHandler();
		if (handler != null) {
			if (userDao.getUserCustomerCount(handler) >= handler.getLimit()) {
				throw new CrmException("已超出客户人数限制");
			}
		}

		customer.setCreater(user);
		customer.setCdate(DateUtil.getFormatedCurrentDate());
		customerDao.add(customer);
	}

	@Override
	public PageModel<Customer> getAuditList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, Integer auditType, User user) throws CrmException {
		// 检查权限
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		StringBuilder hql = new StringBuilder();
		hql
				.append("select c from Customer c where c.isValid = 1 and c.dept.code like '"
						+ user.getDept().getCode() + "%'");
		if (auditType != null) {
			int audit = auditType.intValue();
			hql.append(" and c.audit = " + audit);
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customerName like '%" + customerName + "%'");
		}
		if (!StringUtils.isEmpty(brandName)) {
			hql.append(" and c.brand like '%" + brandName + "%'");
		}
		if (!StringUtils.isEmpty(startDate)) {
			hql.append(" and c.cdate >= '" + startDate + "'");
		}
		if (!StringUtils.isEmpty(endDate)) {
			hql.append(" and c.cdate <= '" + endDate + "'");
		}
		return customerDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public PageModel<Customer> getMyCustomerList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, Integer audit, User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select c from Customer c where 1=1");
		if (user.isUserSupervisor()) {// 经理就查他部门下的所有客户
			hql.append(" and c.dept.code like'" + user.getDept().getCode()
					+ "%' ");
			hql.append(" and c.status != 3");
		} else {
			hql.append(" and c.handler.id = " + user.getId());
		}

		if (audit != null) {
			switch (audit) {
			case -1:
				hql.append(" and c.audit = "+audit+" and  c.isValid = 0");
				break;
				
			default:
				hql.append(" and c.audit = "+audit+" and  c.isValid = 1");
				break;
			}
		}
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customerName like '%" + customerName + "%'");
		}
		if (!StringUtils.isEmpty(brandName)) {
			hql.append(" and c.brand like '%" + brandName + "%'");
		}
		if (!StringUtils.isEmpty(startDate)) {
			hql.append(" and c.cdate >= '" + startDate + "'");
		}
		if (!StringUtils.isEmpty(endDate)) {
			hql.append(" and c.cdate <= '" + endDate + "'");
		}

		return customerDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void auditCustomer(int customerId, boolean isPass, User user)
			throws CrmException {
		// 检查权限
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		Customer customer = customerDao.getObject(customerId);
		if (!customer.getDept().isChildOf(user.getDept())) {
			throw new CrmException("当前客户不归属于你的地区");
		}
		if (isPass) {
			customer.setAudit(1);
			customer.setAuditTime(DateUtil.getFormatedCurrentDate());
			int days = dicDao.getInt("free_time");
			String freeTime = DateUtil.getFreeTime(days);
			customer.setFreeTime(freeTime);

		} else {
			customer.setAudit(-1);
			customer.setIsValid(0);
		}
		customerDao.update(customer);
	}

	@Override
	public void moveCustomer(int customerId, int targetId, User user)
			throws CrmException {
		Customer customer = customerDao.getObject(customerId);
		User target = userDao.getObject(targetId);

		// 检查权限
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		if (!customer.getDept().isChildOf(user.getDept())) {
			throw new CrmException("当前客户不归属于你的地区");
		}
		if (!target.getDept().isChildOf(user.getDept())) {
			throw new CrmException("当前用户不归属于你的地区");
		}
		if (userDao.getUserCustomerCount(target) >= target.getLimit()) {
			throw new CrmException("已超出客户人数限制");
		}
		if (customer.getAudit() != 1) {
			throw new CrmException("客户未审核");
		}
		if (customer.getStatus() != 1) {
			throw new CrmException("非本地客户");
		}

		if (customer.getHandler() != null) {
			if (customer.getHandler().getId() == target.getId()) {
				return;
			}
		}

		customer.setHandler(target);
		if (customer.getIsSign() != 1) { // 未签约客户重新计算释放时间
			int days = dicDao.getInt("free_time");
			String freeTime = DateUtil.getFormatedFixedDate(days);
			customer.setFreeTime(freeTime);
		}

		customerDao.update(customer);
	}

	@Override
	public void pickCustomer(int customerId, User user) throws CrmException {
		Customer customer = customerDao.getObject(customerId);
		if (customer.getHandler() != null) {
			throw new CrmException("客户已有跟进人");
		}
		if (!customer.getDept().isChildOf(user.getDept())) { // 非当前部门
			if (deptDao.getUserCount(customer.getDept().getBigAreaCode()) != 0) {
				throw new CrmException("不能捡起其他地区的客户");
			}
		}
		if (customer.getAudit() != 1 || customer.getStatus() != 3
				|| customer.getIsValid() != 1) {
			throw new CrmException("无效的客户状态");
		}
		if (userDao.getUserCustomerCount(user) >= user.getLimit()) {
			throw new CrmException("已超出客户人数限制");
		}
		// 避免重复申请
		PageModel<Apply> applyList = applyDao.getUserCustomerApplyList(user,
				customer.getId(), 0, 0, 100);
		if (applyList != null && applyList.getTotal() != 0) {
			throw new CrmException("不能发起重复申请");
		}
		Apply apply = new Apply();
		apply.setApplier(user);
		apply.setCustomer(customer);
		apply.setCdate(DateUtil.getFormatedCurrentDate());
		apply.setStatus(0);

		applyDao.add(apply);
	}

	@Override
	public void auditPickup(int applyId, boolean isPass, User user)
			throws CrmException {
		// 检查权限
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		Apply apply = applyDao.getObject(applyId);
		if (apply == null) {
			throw new CrmException("wrong apply id");
		}
		if (!apply.getApplier().getDept().isChildOf(user.getDept())) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		Customer customer = apply.getCustomer();
		if (customer.getHandler() != null) {
			throw new CrmException("客户已有跟进人");
		}
		if (isPass) {// 同意当前并拒绝其他客户
			applyDao.auditApply(applyId, user);
			applyDao.batchRefuseApply(null, customer.getId(), user);
			// 更新客户
			customer.setHandler(apply.getApplier());
			customer.setAuditTime(DateUtil.getFormatedCurrentDate());
			customer.setStatus(1);			//从公海转为本地客户

			int days = dicDao.getInt("free_time");
			String freeTime = DateUtil.getFormatedFixedDate(days);
			customer.setFreeTime(freeTime);

			customerDao.update(customer);

		} else {
			applyDao.refuseApply(applyId, user);
		}

	}

	@Override
	public PageModel<Apply> getApplyList(int page, int limit, String startTime,
			String endTime, String customerName, Integer audit, User user)
			throws CrmException {
		// 检查权限
		if (!userDao.isUserSupervisor(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}

		StringBuilder hql = new StringBuilder();
		hql.append("select a from Apply a where a.applier.dept.code like '"
				+ user.getDept().getCode() + "%'");
		if (audit != null) {
			hql.append(" and a.status = " + audit.intValue());
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

		return applyDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public PageModel<Apply> getMyApplyList(int page, int limit,
			String startTime, String endTime, String customerName,
			Integer audit, User user) throws CrmException {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from Apply a where a.applier.id = "+user.getId());
		if(audit != null){
			hql.append(" and a.status = "+audit.intValue());
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
		return applyDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public boolean checkConflict(String customerName, String brandName)
			throws CrmException {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(c) from Customer c where c.isValid = 1 ");
		if (!StringUtils.isEmpty(customerName)) {
			hql.append(" and c.customerName = '" + customerName + "'");
		}
		if (!StringUtils.isEmpty(brandName)) {
			hql.append(" and c.brand = '" + brandName + "'");
		}

		try {
			Object result = customerDao.queryUniqueValue(hql.toString());
			Integer count = Integer.parseInt(result.toString());
			return count == 0;

		} catch (Exception e) {
			return false;
		}
	}
}
