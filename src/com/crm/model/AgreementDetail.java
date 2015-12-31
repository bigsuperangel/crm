package com.crm.model;

import java.util.List;

/**
 * 合同详情
 * 
 * @author wukh
 * @2015-2-26
 */
public class AgreementDetail extends BaseModel {

	private int detailId; // 详情ID
	private Agreement agreement; // 合同
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String cdate; // 创建时间
	private int isValid; // 是否可用

	private List<Attachment> attachmentList;

	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id:" + detailId);
		if (agreement!=null) {
			json.append(",agreementId:'" + agreement.getId() + "'");
		}
		json.append(",value1:'" + value1 + "'");
		json.append(",value2:'" + value2 + "'");
		json.append(",value3:'" + value3 + "'");
		json.append(",value4:'" + value4 + "'");
		json.append(",cdate:'" + cdate + "'");
		json.append(",isValid:" + isValid);
		if (attachmentList != null && attachmentList.size() > 0) {
			json.append(",attachments:[");
			for (Attachment attachment : attachmentList) {
				json.append(attachment.getJson());
			}
			json.append("]");
		}
		if (agreement != null) {
			json.append(",agreement:");
			json.append(agreement.getJson());
		}
		json.append("}");
		return json.toString();
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
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

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

}
