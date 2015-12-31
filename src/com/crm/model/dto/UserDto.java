package com.crm.model.dto;

import java.util.List;

import com.crm.model.Dept;
import com.crm.model.Privilege;
import com.crm.model.User;

public class UserDto {
	private int id;
	private String username;
	private String realName;
	private List<Privilege> list;
	private String roleName;
	private Privilege tree;
	private Dept dept;
	
	public User getUser(){
		User u = new User();
		u.setId(this.id);
		u.setRealName(this.realName);
		return u;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public List<Privilege> getList() {
		return list;
	}
	public void setList(List<Privilege> list) {
		this.list = list;
	}
	public Privilege getTree() {
		return tree;
	}
	public void setTree(Privilege tree) {
		this.tree = tree;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
}
