package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Agreement;
import com.crm.model.Contact;
import com.crm.model.User;

/**
 * 合同DAO
 * 
 * @author wukh
 * @2015-2-18
 */
public interface IAgreementDao extends IBaseDao<Agreement> {

	/**
	 * 根据参数查询合同列表
	 * 
	 * @param page
	 * @param limit
	 * @param customerId			客户ID
	 * @param status					状态
	 * @return
	 */
	public PageModel<Agreement> queryAgreementByArgs(int page, int limit,
			Integer customerId, Integer status, User user);
	
	/**
	 * 生成合同编号
	 * @return
	 */
	public String generateAgreementNo();
}
