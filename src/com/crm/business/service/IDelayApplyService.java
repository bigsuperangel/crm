package com.crm.business.service;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.DelayApply;
import com.crm.model.User;

/**
 * 
 * @author wukh
 * @2015-2-18
 */
public interface IDelayApplyService {

	/**
	 * 新增/发起一个延时申请
	 * 
	 * @param delayApply
	 * @param user
	 * @throws CrmException
	 */
	public void addDelayApply(int customerId, int delayDays, User user)
			throws CrmException;

	/**
	 * 查询待审批的延时申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 * @return
	 */
	public PageModel<DelayApply> queryUnAuditList(int page, int limit,
			String startTime, String endTime, String customerName, User user)
			throws CrmException;

	/**
	 * 查询自己发起的延时申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 * @return
	 */
	public PageModel<DelayApply> queryMyAuditList(int page, int limit,
			String startTime, String endTime, String customerName,
			Integer audit, User user) throws CrmException;

	/**
	 * 审批延时申请
	 * 
	 * @param delayApply
	 */
	public void audit(int delayApplyId, int audit, User user)
			throws CrmException;

	/**
	 * 撤回延时申请
	 * 
	 * @param delayApply
	 */
	public void recall(int delayApplyId, User user) throws CrmException;
}
