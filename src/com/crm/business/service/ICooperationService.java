package com.crm.business.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.Cooperation;
import com.crm.model.User;

/**
 * 合作相关逻辑服务类
 * 
 * @author wukh
 * @2015-1-28
 */
public interface ICooperationService {

	/**
	 * 发起合作
	 * 
	 * @param cooperation
	 * @param user
	 */
	public void startCooperation(Cooperation cooperation, User user)
			throws CrmException;

	/**
	 * 查询合作明细
	 * 
	 * @param cooperationId
	 *            合作ID
	 * @return
	 */
	public Cooperation queryCooperation(int cooperationId) throws CrmException;

	/**
	 * 查询待审批的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @param userDto
	 *            当前用户, 应为大区经理
	 * @return
	 */
	public PageModel<Cooperation> queryUnauditCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException;

	/**
	 * 查询自己发起的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 *            当前用户, 应为合作发起方
	 * @return
	 */
	public PageModel<Cooperation> querySubmitedCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException;

	/**
	 * 查询等待自己接收的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @param user
	 *            当前用户, 应为合作的接受者
	 * @return
	 */
	public PageModel<Cooperation> queryAcceptCooperationList(int page,
			int limit, Integer cooperateType, String customerName,
			Integer status, User user) throws CrmException;

	/**
	 * 审批合作(通过和拒绝)
	 * 
	 * @param cooperateId
	 * @param audit
	 *            审核状态
	 */
	public void auditCooperation(int cooperateId, Integer receiverId, User self)
			throws CrmException;

	/**
	 * 审批通过并分配接收者
	 * 
	 * @param cooperation
	 * @param target
	 *            要分配的合作的目标客户
	 */
	public void refuseCooperation(int cooperateId, User user)
			throws CrmException;

	/**
	 * 接受合作/拒绝合作
	 * 
	 * @param cooperation
	 * @param user
	 *            当前用户
	 * @param isAccept
	 *            是否接受合作
	 */
	public void acceptCooperation(int cooperateId, User user, boolean isAccept)
			throws CrmException;

}
