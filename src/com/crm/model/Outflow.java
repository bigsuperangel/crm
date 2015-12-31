package com.crm.model;

public class Outflow extends BaseModel{
	private int id;
	private Customer customer;
	private String cdate;
	/**
	 * 0: 未处理
	 * 1： 暂缓处理
	 * 2：确定流失
	 */
	private int state;
	
	public Outflow(){
		
	}
	public Outflow(int state,long id){
		this.state = state;
		this.id = (int)id;
	}
	public String getJson() {
//		return null;
		return "{id:"+id+",userName:'"+customer.getHandler().getUsername()+"',state:"+state+",cdate:'"+cdate+"',customerName:'"+customer.getCustomerName()+"',customerId:"+customer.getId()+"}";
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

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
