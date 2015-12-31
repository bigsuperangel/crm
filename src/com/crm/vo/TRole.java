package com.crm.vo;

import java.util.Set;

public class TRole {
	private int id;
	private String name;
	private Set<TUser> user;
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
	public Set<TUser> getUser() {
		return user;
	}
	public void setUser(Set<TUser> user) {
		this.user = user;
	}
}
