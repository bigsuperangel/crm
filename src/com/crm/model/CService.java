package com.crm.model;

public class CService extends BaseModel {
	private int id;
	private TypeDic type;
	private String content;
	private Customer customer;
	/**
	 * 0：未分配
	 * 1：已分配
	 * 2：已处理
	 * 3：已归档
	 */
	private int state;
	private String title;
	private User createUser;
	private String cdate;
	private int satisfied;
	private User dealUser;
	
	public CService(){
		
	}
	public CService(int state,long id){
		this.state = state;
		this.id = (int)id;
	}
	public String getJson() {
		String json = "{id:"+id+",typeId:"+type.getId()+",typeName:'"+type.getName() + "',"+
				"satisfied:"+satisfied+",state:"+state+",title:'"+title+"',content:'"+content+"',userName:'"+createUser.getRealName()+"',userId:"+createUser.getId()+",cdate:'"+cdate+"'";
		if(dealUser != null){
			json += ",dealUserName:'"+dealUser.getRealName()+"',dealUserId:"+dealUser.getId();
		}
		if(customer != null){
			json += ",customerId:"+customer.getId()+",customerName:'"+customer.getCustomerName()+"'";
		}
		return json+="}";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeDic getType() {
		return type;
	}
	public void setType(TypeDic type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public User getDealUser() {
		return dealUser;
	}

	public void setDealUser(User dealUser) {
		this.dealUser = dealUser;
	}
	public int getSatisfied() {
		return satisfied;
	}
	public void setSatisfied(int satisfied) {
		this.satisfied = satisfied;
	}

}
