package com.crm.model;

/**
 * 延时申请
 * @author wukh
 * @2015-3-3
 */
public class DelayApply extends BaseModel {

	private int id;			//id
	private User applier;			//申请人
	private Customer customer;				//申请的客户
	private int delayDays;				//延时天数
	private int audit;				//审核状态. 0-未审批, 1-审批通过, 2-审批不通过
	private String remarks;					//备注
	private String cdate;					//时间
	
	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("id:"+id);
		json.append(",applierId:"+applier.getId());
		json.append(",applier:'"+applier.getRealName()+"'");
		json.append(",customerId:"+customer.getId());
		json.append(",customerName:'"+customer.getCustomerName()+"'");
		json.append(",freeDate:'"+customer.getFreeTime()+"'");
		json.append(",delayDays:"+delayDays);
		json.append(",audit:"+audit);
		json.append(",cdate:'"+cdate+"'");
		json.append(",remarks:'"+remarks+"'");
		json.append("}");
		return json.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getApplier() {
		return applier;
	}

	public void setApplier(User applier) {
		this.applier = applier;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(int delayDays) {
		this.delayDays = delayDays;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

}
