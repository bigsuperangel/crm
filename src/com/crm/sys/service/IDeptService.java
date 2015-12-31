package com.crm.sys.service;

import com.crm.model.Dept;
import com.crm.model.User;

public interface IDeptService {
	public String getDeptTree();
	public String getTreeCombobox();
	public void add(Dept dept);
	public void update(Dept dept );
	public void delete(Integer[] ids);
	public void move(int pid,int did);
	
	/**
	 * 获取部门列表
	 * @param flag			1:自己的部门			2: 跨区域部门      3:跨大区部门
	 * @return
	 */
	public String getDeptTree(int flag, User user);
	
}
