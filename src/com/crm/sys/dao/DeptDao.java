package com.crm.sys.dao;

import com.crm.core.base.IBaseDao;
import com.crm.model.Dept;

public interface DeptDao extends IBaseDao<Dept>{
	
	/**
	 * 获取部门下的最大Code
	 * @param parentId
	 * @return
	 */
	public int getParentMaxCode(int parentId);
	
	/**
	 * 获取部门下人数
	 * @param dept
	 * @return
	 */
	public int getUserCount(String code);
}
