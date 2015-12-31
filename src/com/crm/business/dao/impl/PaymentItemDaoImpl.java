package com.crm.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.IPaymentItemDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.PaymentItem;

@Repository("paymentItemDao")
public class PaymentItemDaoImpl extends BaseDao<PaymentItem, Integer> implements
		IPaymentItemDao {

	@Override
	public PageModel<PaymentItem> query(int page, int limit,
			Integer agreementId, Integer status) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Payment p where p.isValid = 1");
		if(agreementId != null){
			hql.append(" and p.agreement.id = "+agreementId);
		}
		if(status != null){
			hql.append(" and p.status = "+status);
		}
		return getPageModel(hql.toString(), page, limit);
	}

}
