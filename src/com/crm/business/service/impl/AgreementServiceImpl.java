package com.crm.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.business.dao.CustomerDao;
import com.crm.business.dao.IAgreementDao;
import com.crm.business.dao.IAgreementDetailDao;
import com.crm.business.service.IAgreementService;
import com.crm.business.service.IAttachmentService;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Agreement;
import com.crm.model.AgreementDetail;
import com.crm.model.Attachment;
import com.crm.model.Customer;
import com.crm.model.User;

/**
 * 合同服务类
 * 
 * @author wukh
 * @2015-2-18
 */
@Service("agreementService")
public class AgreementServiceImpl extends BaseService implements
		IAgreementService {

	@Autowired
	@Qualifier("agreementDao")
	private IAgreementDao agreementDao;

	@Autowired
	@Qualifier("detailDao")
	private IAgreementDetailDao detailDao;

	@Autowired
	@Qualifier("attachmentService")
	private IAttachmentService attachmentService;

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;

	@Override
	public void addAgreement(Agreement agreement, User user)
			throws CrmException {

		Customer customer = customerDao.getObject(agreement.getCustomer().getId());
		if(customer.getAudit() != 1 || customer.getStatus() != 1 || customer.getIsValid() != 1){
			throw new CrmException("该用户无法发起合同");
		}
		
		if (isCustomerAgreementExist(customer.getId())) {
			throw new CrmException("该用户已存在合同");
		}

		String cdate = DateUtil.getFormatedCurrentDate();
		agreement.setAgreementNo(agreementDao.generateAgreementNo());
		agreement.setStatus(0);
		agreement.setCdate(cdate);
		agreement.setIsValid(1);

		agreementDao.add(agreement);

		AgreementDetail detail = agreement.getDetail();
		detail.setAgreement(agreement);
		detail.setIsValid(1);
		detail.setCdate(cdate);

		detailDao.add(detail);
	}
	
	/**
	 * 用户是否已存在合同
	 * @param userId
	 * @return
	 */
	private boolean isCustomerAgreementExist(int userId){
		StringBuilder hql = new StringBuilder();
		hql.append("select count(1) from Agreement a where a.isValid = 1");
		hql.append(" and a.customer.id = "+userId);
		hql.append(" and (a.status != 0 and a.status != 5)");	//排除掉新建和已完成的合同
		int value = 0;
		try {
			value = Integer.parseInt(agreementDao.queryUniqueValue(hql.toString()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value != 0;
	}

	@Override
	public void deleteAgreement(Integer[] agreementId, User user)
			throws CrmException {
		if (agreementId != null && agreementId.length != 0) {
			for (int i = 0; i < agreementId.length; i++) {
				int id = agreementId[i];
				Agreement agreement = agreementDao.getObject(id);
				if (agreement.getStatus() != 0) { // 合同已发出, 只有财务可以删除
					if (!isUserFinance(user)) {
						throw new CrmException(
								CrmException.MSG_NOT_ENOUGH_AUTHORITY);
					}
				}
				agreement.setIsValid(0);
				agreementDao.update(agreement);

				AgreementDetail detail = detailDao
						.getDetailByAgreementId(agreement.getId());
				detail.setIsValid(0);
				detailDao.update(detail);
				// 删除附件
				List<Attachment> attachments = attachmentService
						.queryAttachmentList(id, 1);
				if (attachments != null && attachments.size() > 0) {
					int[] ids = new int[attachments.size()];
					for (int j = 0; j < ids.length; j++) {
						ids[j] = attachments.get(j).getId();
					}
					attachmentService.deleteAttachments(ids, user);
				}
			}
		}
	}

	@Override
	public void signAgreement(int agreementId, String mySigner,
			String customerSigner, User user) throws CrmException {
		if (!isUserFinance(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		Agreement agreement = agreementDao.getObject(agreementId);
		agreement.setSignDate(DateUtil.getFormatedCurrentDate());
		agreement.setMySigner(mySigner);
		agreement.setCustomerSigner(customerSigner);
		agreement.setStatus(2);

		agreementDao.update(agreement);
	}

	@Override
	public void updateAgreementStatus(Integer agreementId, int status, User user)
			throws CrmException {
		if (!isUserFinance(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		Agreement agreement = agreementDao.getObject(agreementId);
		agreement.setStatus(status);

		agreementDao.update(agreement);

		if (status == 2) {
			// 签约时, 客户改为已签约, 而且不再超时
			Customer customer = agreement.getCustomer();
			customer.setIsSign(1);
			customer.setFreeTime("");
			customerDao.update(customer);
		}
	}

	@Override
	public void updateAgreementPaymentStatus(Integer agreementId, int status,
			String paymentStatus, User user) throws CrmException {
		if (!isUserFinance(user)) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}

		Agreement agreement = agreementDao.getObject(agreementId);
		if (paymentStatus != null) {
			agreement.setPaymentStatus(paymentStatus);
		}
		agreement.setStatus(status);
		if (status == 4) {
			agreement.setPaymentClearDate(DateUtil.getFormatedCurrentDate());
		}

		agreementDao.update(agreement);
	}

	/**
	 * 用户是否有财务角色
	 * 
	 * @param user
	 * @return
	 */
	private boolean isUserFinance(User user) {
		return user.isUserFinance();
	}

	@Override
	public AgreementDetail queryAgreement(int agreementId) {
		Agreement agreement = agreementDao.getObject(agreementId);
		AgreementDetail detail = detailDao.getDetailByAgreementId(agreementId);
		List<Attachment> attachmentList = attachmentService
				.queryAttachmentList(agreementId, 1);

		detail.setAgreement(agreement);
		detail.setAttachmentList(attachmentList);
		return detail;
	}

	@Override
	public PageModel<Agreement> queryAgreementList(int page, int limit,
			Integer customerId, String agreementNo, String startTime,
			String endTime, Integer status, User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from Agreement a where a.isValid = 1");
		if(customerId != null){
			hql.append(" and a.customer.id = "+customerId.intValue());
		}
		if(status != null){
			hql.append(" and a.status = "+status.intValue());
		}
		if(user.isUserFinance()){
			//财务可以查看所有合同
		}else if(user.isUserSupervisor()){
			//查看自己区域内的客户的ID
			hql.append(" and a.customer.dept.code like '"+user.getDept().getCode()+"%'");
		}else{
			//只能查看自己客户的合同
			hql.append(" and a.customer.handler.id = "+user.getId());
		}
		if(!StringUtils.isEmpty(agreementNo)){
			hql.append(" and a.agreementNo like '%"+agreementNo+"%'");
		}
		if (!StringUtils.isEmpty(startTime)) {
			hql.append(" and a.cdate >= '" + startTime+"'");
		}
		if (!StringUtils.isEmpty(endTime)) {
			hql.append(" and a.cdate <= '" + endTime+"'");
		}
		return agreementDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void updateAgreement(int agreementId, Integer subAgreementCount,
			Long amount, String value1, String value2, String value3,
			String value4, User user) throws CrmException {
		Agreement agreement = agreementDao.getObject(agreementId);
		if (agreement.getStatus() != 0) { // 合同已发出, 只有财务可以修改
			if (!isUserFinance(user)) {
				throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
			}
		}
		if (subAgreementCount != null) {
			agreement.setSubAgreementCount(subAgreementCount);
		}
		if (amount != null) {
			agreement.setAmount(amount);
		}
		agreementDao.update(agreement);

		AgreementDetail detail = agreement.getDetail();

		if (value1 != null) {
			detail.setValue1(value1);
		}
		if (value2 != null) {
			detail.setValue1(value2);
		}
		if (value3 != null) {
			detail.setValue1(value3);
		}
		if (value4 != null) {
			detail.setValue1(value4);
		}

		detailDao.update(detail);
	}
}
