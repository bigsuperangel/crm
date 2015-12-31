package com.crm.model;

/**
 * 款项
 * 
 * @author wukh
 * @2015-2-26
 */
public class PaymentItem extends BaseModel {

	private int itemId; // 款项ID
	private Agreement agreement; // 合同
	private String agreementNo; // 合同编号
	private String receiveDate; // 收款日期
	private long receiveAmount; // 收款金额
	private int payMode; // 支付方式（1支付宝、2财付通、3转帐、4现金）
	private String receiver; // 收款人
	private String receiveAccount; // 收款账号
	private String payer; // 付款人
	private String payAccount; // 付款账号
	private int status; // 状态(0-未审核, 1-审核, -1- 审核不通过)
	private String receipt; // 发票
	private String cdate; //
	private int isValid;

	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id:" + itemId);
		json.append(",agreementId:" + agreement.getId());
		json.append(",projectName:'" + agreement.getProjectName() + "'");
		json.append(",agreementNo:'" + agreementNo + "'");
		json.append(",receiveDate:'" + receiveDate + "'");
		json.append(",receiveAmount:" + receiveAmount);
		json.append(",payMode:" + payMode);
		json.append(",receiver:'" + receiver + "'");
		json.append(",receiveAccount:'" + receiveAccount + "'");
		json.append(",payer:'" + payer + "'");
		json.append(",payAccount:'" + payAccount + "'");
		json.append(",status:" + status);
		json.append(",receipt:'" + receipt + "'");
		json.append(",cdate:'" + cdate + "'");
		json.append(",isValid:" + isValid);
		json.append("}");

		return json.toString();
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public long getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(long receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
