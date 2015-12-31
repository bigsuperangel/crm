package com.crm.model;

import java.util.List;

import org.springframework.util.StringUtils;


/**
 * 合同
 * 
 * @author wukh
 * @2015-2-18
 */
public class Agreement extends BaseModel {

	private int id;// ID
	private String agreementNo; // 合同编号
	private int status; // 合同状态
	private String projectName; // 项目名称
	private String cdate; // 创建时间
	private Customer customer; // 客户
	private String signDate; // 签约时间
	private String paymentClearDate; // 完款时间
	private String finishDate; // 完成日期
	private long amount; // 合同金额(单位为分)
	private int subAgreementCount; // 子合同数
	private String paymentStatus; // 收款状态
	private String mySigner; // 我方签约人
	private String customerSigner; // 客户签约人
	private String remark; // 备注
	private int isReceipt; // 是否开票
	private int isValid;		//删除标识. 0删除, 1正常
	private AgreementDetail detail;
	private List<Attachment> attachmentList;

	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id:" + id);
		json.append(",agreementNo:'" + agreementNo + "'");
		json.append(",status:" + status);
		json.append(",projectName:'" + projectName + "'");
		json.append(",cdate:'" + cdate + "'");
		if (customer!=null) {
			json.append(",customerId:" + customer.getId());
			json.append(",customerName:'" + customer.getCustomerName() + "'");
		}
		json.append(",signDate:'" + signDate + "'");
		if(!StringUtils.isEmpty(paymentClearDate)){
			json.append(",paymentClearDate:'" + paymentClearDate + "'");
		}
		if(!StringUtils.isEmpty(finishDate)){
			json.append(",finishDate:'" + finishDate + "'");
		}
		json.append(",amount:" + amount);
		json.append(",subAgreementCount:" + subAgreementCount);
		if(!StringUtils.isEmpty(paymentStatus)){
			json.append(",paymentStatus:'" + paymentStatus + "'");
		}
		if(!StringUtils.isEmpty(mySigner)){
			json.append(",mySigner:'" + mySigner + "'");
		}
		if(!StringUtils.isEmpty(customerSigner)){
			json.append(",customerSigner:'" + customerSigner + "'");
		}
		if(!StringUtils.isEmpty(remark)){
			json.append(",remark:'" + remark + "'");
		}
		json.append(",isReceipt:" + isReceipt + "}");

		return json.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getPaymentClearDate() {
		return paymentClearDate;
	}

	public void setPaymentClearDate(String paymentClearDate) {
		this.paymentClearDate = paymentClearDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getSubAgreementCount() {
		return subAgreementCount;
	}

	public void setSubAgreementCount(int subAgreementCount) {
		this.subAgreementCount = subAgreementCount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getMySigner() {
		return mySigner;
	}

	public void setMySigner(String mySigner) {
		this.mySigner = mySigner;
	}

	public String getCustomerSigner() {
		return customerSigner;
	}

	public void setCustomerSigner(String customerSigner) {
		this.customerSigner = customerSigner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(int isReceipt) {
		this.isReceipt = isReceipt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public AgreementDetail getDetail() {
		return detail;
	}

	public void setDetail(AgreementDetail detail) {
		this.detail = detail;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
}
