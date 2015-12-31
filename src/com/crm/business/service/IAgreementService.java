package com.crm.business.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.Agreement;
import com.crm.model.AgreementDetail;
import com.crm.model.Attachment;
import com.crm.model.User;

/**
 * 合同服务接口
 * 
 * @author wukh
 * @2015-2-18
 */
public interface IAgreementService {

	/**
	 * 新增合同
	 * 
	 * @param agreement
	 * @throws CrmException
	 */
	public void addAgreement(Agreement agreement, User user)
			throws CrmException;

	/**
	 * 查询列表
	 * 
	 * @param page
	 * @param limit
	 * @param customerId
	 * @param status
	 */
	public PageModel<Agreement> queryAgreementList(
			int page, int limit, Integer customerId, String agreementNo,
			String startTime, String endTime, Integer status, User user);

	/**
	 * 查询合同明细
	 * 
	 * @param agreementId
	 */
	public AgreementDetail queryAgreement(int agreementId);

	/**
	 * 批量删除合同
	 * 
	 * @param agreementId
	 */
	public void deleteAgreement(Integer[] agreementId, User user)
			throws CrmException;

	/**
	 * 更新合同状态(发出,完成)
	 * 
	 * @param agreementId
	 * @param status
	 */
	public void updateAgreementStatus(Integer agreementId, int status, User user)
			throws CrmException;

	/**
	 * 更新合同收款状态
	 * 
	 * @param agreementId
	 * @param status
	 */
	public void updateAgreementPaymentStatus(Integer agreementId, int status,
			String paymentStatus, User user) throws CrmException;

	/**
	 * 签约合同
	 * 
	 * @param agreementId
	 * @throws CrmException
	 */
	public void signAgreement(int agreementId, String mySigner,
			String customerSigner, User user) throws CrmException;

	/**
	 * 修改合同
	 * 
	 * @param agreementId
	 * @param subAgreementCount
	 * @param amount
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @throws CrmException
	 */
	public void updateAgreement(int agreementId, Integer subAgreementCount,
			Long amount, String value1, String value2, String value3,
			String value4, User user) throws CrmException;
}
