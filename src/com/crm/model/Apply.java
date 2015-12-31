package com.crm.model;

import org.springframework.util.StringUtils;

/**
 * 捡起公海客户的申请
 * 
 * @author wukh
 * @2015-2-6
 */
public class Apply extends BaseModel {

	private int id;
	private Customer customer; // 申请捡起的客户
	private User applier; // 发起申请的员工
	private String cdate; // 申请时间
	private int status; // 审核状态. 0-待审核. 1-审核通过. 2-审核拒绝
	private User auditor; // 审核人
	private String opTime; // 审核操作时间
	private String remarks; // 审核备注(预留字段)

	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id" + id);
		if (customer != null) {
			json.append(",customerId:" + customer.getId() + ",customerName:'"
					+ customer.getCustomerName() + "'");
		}
		if (applier != null) {
			json.append("',applierId:" + applier.getId() + ",applierName:'"
					+ applier.getRealName() + "'");
		}
		if (StringUtils.isEmpty(cdate)) {
			json.append(",cdate:'" + cdate + "'");
		}
		json.append(",status:" + status);
		if (StringUtils.isEmpty(remarks)) {
			json.append(",remarks:'" + remarks + "'");
		}
		json.append("}");
		
		return json.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getApplier() {
		return applier;
	}

	public void setApplier(User applier) {
		this.applier = applier;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
