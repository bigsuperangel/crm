package com.crm.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.business.dao.IAgreementDao;
import com.crm.business.dao.IPaymentItemDao;
import com.crm.business.service.IPaymentItemService;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Agreement;
import com.crm.model.PaymentItem;
import com.crm.model.User;

/**
 * 款项服务类
 * @author wukh
 * @2015-2-26
 */
@Service("paymentItemService")
public class PaymentItemServiceImpl extends BaseService implements IPaymentItemService {

	@Autowired
	@Qualifier("paymentItemDao")
	private IPaymentItemDao paymentItemDao;


	@Autowired
	@Qualifier("agreementDao")
	private IAgreementDao agreementDao;
	
	@Override
	public void add(PaymentItem paymentItem) {
		Agreement agreement = agreementDao.getObject(paymentItem.getAgreement().getId());
		paymentItem.setAgreementNo(agreement.getAgreementNo());
		
		paymentItem.setStatus(0);
		paymentItem.setIsValid(1);
		paymentItem.setCdate(DateUtil.getFormatedCurrentDate());
		paymentItemDao.add(paymentItem);
	}

	@Override
	public void audit(PaymentItem item, User user) throws CrmException {
		if(!user.isUserFinance()){
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		PaymentItem data = paymentItemDao.getObject(item.getItemId());
		data.setStatus(item.getStatus());
		
		paymentItemDao.update(data);
	}

	@Override
	public void delete(Integer[] ids) {
		for(int id: ids){
			PaymentItem data = paymentItemDao.getObject(id);
			data.setIsValid(0);
			paymentItemDao.update(data);
		}
	}

	@Override
	public PageModel<PaymentItem> queryItemList(int page, int limit,
			Integer areementId, Integer status) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PaymentItem p where p.isValid = 1");
		if(areementId != null){
			hql.append(" and p.agreement.id = "+areementId);
		}
		if(status != null){
			hql.append(" and p.status = "+status);
		}
		return paymentItemDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void update(PaymentItem item, User user) {
		PaymentItem data = paymentItemDao.getObject(item.getItemId());
		data.setReceiveDate(item.getReceiveDate());
		data.setReceipt(item.getReceipt());
		data.setReceiveAmount(item.getReceiveAmount());
		data.setReceiver(item.getReceiver());
		data.setReceiveAccount(item.getReceiveAccount());
		data.setPayAccount(item.getPayAccount());
		data.setPayer(item.getPayer());
		data.setPayMode(item.getPayMode());
		
		paymentItemDao.update(data);
	}

	@Override
	public PaymentItem query(int paymentItemId) {
		return paymentItemDao.getObject(paymentItemId);
	}

}
