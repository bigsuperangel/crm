package com.crm.model;

public class SalesOpp extends BaseModel {
	private int id;//
	private String resource;//机会来源
	private String customer;//客户名称
	private int success;//成功几率
	private String summery;//机会概要
	private String person;//联系人
	private String tel;//联系电话
	private String descr;//机会描述
	private User user;//创建者
	private String createDate;//创建时间
	/*
	 * 0：未指派  1：已指派  2：开发成功   3：开发失败
	 */
	private int state; //是否指派
	private User dealUser;//负责人，销售机会负责人
	
	public SalesOpp(){
		
	}
	public SalesOpp(long id,int state){
		this.id = (int)id;
		this.state = state;
	}
	public String getJson() {
		String json = "{id:"+id+",resource:'"+resource+"',customer:'"+customer+"',success:"+success+",summery:'"+summery+"'," +
				"person:'"+person+"',tel:'"+tel+"',descr:'"+descr+"',createDate:'"+createDate+"'," +
						"state:"+state;
		if(user != null){
			json += ",userName:'"+user.getRealName()+"',userId:"+user.getId();
		}
		if(dealUser != null){
			json += ",dealUserId:"+dealUser.getId()+",dealUserName:'"+dealUser.getRealName()+"'}";
		}else{
			json += "}";
		}
		return json;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getSummery() {
		return summery;
	}
	public void setSummery(String summery) {
		this.summery = summery;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getDealUser() {
		return dealUser;
	}
	public void setDealUser(User dealUser) {
		this.dealUser = dealUser;
	}
}
