package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Apply;
import com.crm.model.User;

public interface IApplyDao extends IBaseDao<Apply>{

	/**
	 * 查询用户对某个客户发起的申请列表
	 * @param user
	 * @param customerId		不针对某个客户查询过往记录, 传null
	 * @param status			审核状态. 查全部传null
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageModel<Apply> getUserCustomerApplyList(User user, Integer customerId, Integer status, int page, int limit);
	
	/**
	 * 审核通过一个公海客户申请
	 * @param applyId
	 */
	public void auditApply(int applyId,User user);
	
	
	/**
	 * 拒绝申请
	 * @param applyId
	 */
	public void refuseApply(int applyId,User user);
	
	/**
	 * 批量拒绝申请
	 * @param applyId
	 */
	public void batchRefuseApply(Integer applierId, Integer customerId,User user);
	
	/**
	 * 获取某个地区的所有公海客户申请
	 * @param page
	 * @param limit
	 * @param audit		查所有时传null
	 * @param user
	 * @return
	 */
	public PageModel<Apply> getAuditList(int page, int limit, Integer audit,String deptCode);
}
