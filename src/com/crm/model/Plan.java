package com.crm.model;


public class Plan extends BaseModel{
	private int id;
	private String cdate;
	private String content;
	private SalesOpp salesOpp;
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SalesOpp getSalesOpp() {
		return salesOpp;
	}
	public void setSalesOpp(SalesOpp salesOpp) {
		this.salesOpp = salesOpp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getJson() {
		String json = "{pid:"+id+",sid:"+salesOpp.getId()+",createDate:'"+salesOpp.getCreateDate()+"',customer:'"+salesOpp.getCustomer()+"',person:'"+salesOpp.getPerson()+"',state:"+salesOpp.getState()+",success:"+salesOpp.getSuccess()+",summery:'"+salesOpp.getSummery()+"',tel:'"+salesOpp.getTel()+"'";
		if(salesOpp.getUser() != null){
			json += ",userName:'"+salesOpp.getUser().getRealName()+"'";
		}
		json += ",cdate:'"+cdate+"',content:'"+content+"'}";
		return json;
	}
}
