package com.crm.model;

public class LossStep extends BaseModel {
	private int id;
	private String content;
	private String cdate;
	private Outflow outflow;
	private User user;
	
	public String getJson() {
		return "{id:"+id+",oid:"+outflow.getId()+",content:'"+content+"',customerName:'"+outflow.getCustomer().getCustomerName()+"',cdate:'"+cdate+"',userName:'"+user.getRealName()+"',userId:"+user.getId()+"}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Outflow getOutflow() {
		return outflow;
	}

	public void setOutflow(Outflow outflow) {
		this.outflow = outflow;
	}

}
