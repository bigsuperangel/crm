package com.crm.model;

import com.crm.common.utils.JsonUtil;

public class Person extends BaseModel {
	private int id;
	private String name;
	private int sex;
	private String post;
	private String phone;
	private String tel;
	private String descr;
	private Customer customer;
	private String qq;
	private String wangWang;
	private String email;
	private int isValid;

	public String combobox() {
		return "{id:" + id + ",name:'" + name + "'}";
	}

	public String getJson() {
		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[] { "customer" });
		return ju.getJson(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWangWang() {
		return wangWang;
	}

	public void setWangWang(String wangWang) {
		this.wangWang = wangWang;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
