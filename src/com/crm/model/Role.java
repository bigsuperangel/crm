package com.crm.model;

import java.util.Set;

public class Role extends BaseModel{
	private int id;
	private String name;
	private Set<Privilege> privs;
	private Set<User> users;
	private int count;			//客户人数限制
	
	public String getJson(){
		int count = 0;
		if(users != null){
			count = users.size();
		}
		return "{id:"+id+",name:'"+name+"',userCount:"+count+",count:"+this.count+"}";
	}
	public String getComboboJson(){
		return "{id:"+id+",name:'"+name+"'}";
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
	public Set<Privilege> getPrivs() {
		return privs;
	}
	/**
	 * @param privs
	 */
	public void setPrivs(Set<Privilege> privs) {
		this.privs = privs;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
