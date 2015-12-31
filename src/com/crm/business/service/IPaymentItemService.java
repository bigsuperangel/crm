package com.crm.business.service;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.PaymentItem;
import com.crm.model.User;

/**
 * 款项处理
 * @author wukh
 * @2015-2-26
 */
public interface IPaymentItemService {

	/**
	 * 新增款项
	 * @param paymentItem
	 */
	public void add(PaymentItem paymentItem);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(Integer[] ids);
	
	/**
	 * 审核款项
	 * @param item
	 * @throws CrmException 
	 */
	public void audit(PaymentItem item, User user) throws CrmException;
	
	/**
	 * 修改款项
	 * @param item
	 */
	public void update(PaymentItem item, User user);
	
	/**
	 * 查询款项
	 * @param paymentItemId
	 * @return
	 */
	public PaymentItem query(int paymentItemId);
	
	/**
	 * 查询款项
	 * @param page
	 * @param limit
	 * @param areementId			合同ID
	 * @param status			状态
	 * @return
	 */
	public PageModel<PaymentItem> queryItemList(int page, int limit, Integer areementId,Integer status);
}
