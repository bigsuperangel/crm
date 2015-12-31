package com.crm.business.service;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.Apply;
import com.crm.model.Customer;
import com.crm.model.User;

public interface ICustomerService {
	/**
	 * 新增用户
	 * 
	 * @param customer
	 * @return
	 * @throws CrmException
	 */
	public void addCustomer(Customer customer, User user) throws CrmException;

	/**
	 * 删除用户
	 * 
	 * @param customer
	 * @return
	 */
	public void deleteCustomer(Customer customer);

	/**
	 * 批量删除用户
	 * 
	 * @param customer
	 * @return
	 */
	public void deleteCustomers(Integer[] ids);

	/**
	 * 更新用户资料
	 * 
	 * @param customer
	 * @return
	 * @throws CrmException
	 */
	public void updateCustomer(Customer customer) throws CrmException;

	/**
	 * 分页查询用户
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageModel<Customer> queryCustomerList(int page, int limit);

	/**
	 * 分页查询公海客户
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageModel<Customer> queryPublicCustomerList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, int deptId);

	/**
	 * 查询特定用户详细
	 * 
	 * @param customer
	 * @return
	 */
	public Customer queryCustomer(int id);

	/**
	 * 获取id和namejson
	 * 
	 * @return
	 */
	public String getComboboxList();

	/**
	 * 获取审核列表
	 * 
	 * @param page
	 * @param limit
	 * @param auditType
	 * @return
	 * @throws CrmException
	 */
	public PageModel<Customer> getAuditList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, Integer auditType, User user) throws CrmException;

	/**
	 * 获取自己名下的用户
	 * 
	 * @param page
	 * @param limit
	 * @param audit
	 *            审核状态
	 * @return
	 */
	public PageModel<Customer> getMyCustomerList(int page, int limit,
			String customerName, String brandName, String startDate,
			String endDate, Integer audit, User user) throws CrmException;

	/**
	 * 审核用户通过/不通过
	 * 
	 * @param customer
	 * @param user
	 * @throws CrmException
	 */
	public void auditCustomer(int customerId, boolean isPass, User user)
			throws CrmException;

	/**
	 * 从公海捡起客户
	 * 
	 * @param customer
	 * @param user
	 * @throws CrmException
	 */
	public void pickCustomer(int customerId, User user) throws CrmException;

	/**
	 * 将客户分配给其他员工
	 * 
	 * @param customer
	 * @param target
	 *            目标员工
	 * @param user
	 *            当前操作员工
	 * @throws CrmException
	 */
	public void moveCustomer(int customerId, int targetId, User user)
			throws CrmException;

	/**
	 * 审核公海客户申请
	 * 
	 * @param applyId
	 * @param isPass
	 * @param user
	 * @throws CrmException
	 */
	public void auditPickup(int applyId, boolean isPass, User user)
			throws CrmException;

	/**
	 * 获取公海客户的申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 * @return
	 * @throws CrmException
	 */
	public PageModel<Apply> getApplyList(int page, int limit, String startTime,
			String endTime, String customerName, Integer audit, User user)
			throws CrmException;

	/**
	 * 获取公海客户的申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 * @return
	 * @throws CrmException
	 */
	public PageModel<Apply> getMyApplyList(int page, int limit,
			String startTime, String endTime, String customerName,
			Integer audit, User user) throws CrmException;

	/**
	 * 检测是否有冲突
	 * 
	 * @param customerName
	 * @param brandName
	 * @return
	 */
	public boolean checkConflict(String customerName, String brandName)
			throws CrmException;
}
