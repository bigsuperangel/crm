package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Cooperation;
import com.crm.model.Customer;

/**
 * 合作DAO类
 * @author wukh
 * @2015-1-28
 */
public interface ICooperationDao  extends IBaseDao<Cooperation>{

	/**
	 * 根据条件查询合作列表
	 * @param page
	 * @param limit
	 * @param cooperateType    合作类型. 不查询则传-2
	 * @param submitorId		发起者ID. 不查询则传-2
	 * @param deptCode			发起地区Code
	 * @param targetAreaCode		接收地区Code
	 * @param receiverId		接受者ID
	 * @param audit		合作状态
	 * @param isValid 		是否可用
	 * @return
	 */
	public PageModel<Cooperation> queryListByArgs(int page, int limit, int cooperateType, int submitorId, String deptCode,String targetDeptCode, int receiverId, int audit, int isValid);
}
