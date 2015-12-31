package com.crm.model;

public class Contact extends BaseModel {
	private int id;
	private String title;
	private String cdate;
	private String content;
	private String addr;
	private User user;
	private Customer customer;
	private String contactor;
	private int contactType;
	private String nextVisit;
	private String supervisor;
	private String customerStatus;
	private int isValid;

	public String combobox() {
		return "{id:" + id + ",title:'" + title + "'}";
	}

	public String getJson() {
		return "{id:" + id + ",title:'" + title + "',addr:'" + addr
				+ "',cdate:'" + cdate + "',content:'" + content
				+ "',userName:'" + user.getRealName() + "',userId:'"
				+ user.getId() + "',customerName:'"
				+ customer.getCustomerName() + "',customerId:"
				+ customer.getId() + "}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public int getContactType() {
		return contactType;
	}

	public void setContactType(int contactType) {
		this.contactType = contactType;
	}

	public String getNextVisit() {
		return nextVisit;
	}

	public void setNextVisit(String nextVisit) {
		this.nextVisit = nextVisit;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
