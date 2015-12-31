package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Agreement;
import com.crm.model.Contact;
import com.crm.model.DelayApply;
import com.crm.model.User;

/**
 *  
 * @author wukh
 * @2015-2-18
 */
public interface IDelayApplyDao extends IBaseDao<DelayApply> {

	/**
	 * 查询延时申请列表
	 * @param page
	 * @param limit
	 * @param customerId
	 * @param applierId
	 * @param status
	 * @param user
	 * @return
	 */
	public PageModel<DelayApply> queryDelayApplyByArgs(int page, int limit,
			Integer customerId, Integer applierId, Integer status, User user);
}
