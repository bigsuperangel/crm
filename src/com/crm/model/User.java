package com.crm.model;

import java.util.Set;

public class User extends BaseModel {
	private int id;
	private String username;
	private String password;
	private String realName;
	private String tel;
	private String email;
	private int enable; // 1：正常使用 2：停用 3：已删除
	private Dept dept;
	private Set<Role> role;
	private int limit = -1; // 客户人数

	public String getJson() {
		boolean isSuperVisor = isUserSupervisor();
		
		return "{id:" + id + ",password:'" + password + "',username:'"
				+ username + "',realName:'" + realName + "',tel:'" + tel
				+ "',email:'" + email + "',enable:" + enable + ",deptId:"
				+ dept.getId() + ",deptName:'" + dept.getName() + "',roleId:'"
				+ getRoleNameAndId()[0] + "',roleName:'"
				+ getRoleNameAndId()[1] + "',isSupervisor:"+(isSuperVisor?1:0)+"}";
	}

	public String combobox() {
		return "{id:" + id + ",realName:'" + realName + "'}";
	}

	public String[] getRoleNameAndId() {
		String[] str = new String[2];
		if (role == null) {
			return str;
		}
		int i = 0;
		for (Role r : role) {
			if (i == 0) {
				str[0] = "[" + r.getId();
				str[1] = r.getName();
			} else {
				str[0] += r.getId();
				str[1] += r.getName();
			}
			if (i < role.size() - 1) {
				str[0] += ",";
				str[1] += "，";
			}
			i++;
		}
		str[0] += "]";
		return str;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> roles) {
		this.role = roles;
	}
	
	private int getMaxLimit(Set<Role> roles){
		int limit = 0;
		if (roles == null || roles.isEmpty()) {
			
		} else {
			for (Role role : roles) {
				if (role.getCount() > limit) {
					limit = role.getCount();
				}
			}
		}
		return limit;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public int getLimit() {
		if(limit == -1){
			limit = getMaxLimit(getRole());
		}
		return limit;
	}
	
	public boolean isUserSupervisor(){
		Set<Role> roleSet = getRole();
		for (Role role : roleSet) {
			if (role.getId() == 4 || role.getId() == 2) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 用户是否有财务角色
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserFinance() {
		Set<Role> roleSet = getRole();
		if (roleSet != null && !roleSet.isEmpty()) {
			for (Role role : roleSet) {
				if (role.getId() == 6) { // 6是财务的角色ID
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 用户是否有总监角色
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserMaster() {
		Set<Role> roleSet = getRole();
		if (roleSet != null && !roleSet.isEmpty()) {
			for (Role role : roleSet) {
				if (role.getId() == 2) { // 总监角色ID2
					return true;
				}
			}
		}
		return false;
	}
}
