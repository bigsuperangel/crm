package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.PaymentItem;

public interface IPaymentItemDao extends IBaseDao<PaymentItem> {

	/**
	 * 批量查询
	 * @param page
	 * @param limit
	 * @param agreementId		合同编号. 不查时为null
	 * @param status					状态. 不查时为null
	 * @return
	 */
	public PageModel<PaymentItem> query(int page, int limit, Integer agreementId, Integer status);
}
