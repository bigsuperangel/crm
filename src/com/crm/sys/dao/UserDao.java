package com.crm.sys.dao;

import com.crm.core.base.IBaseDao;
import com.crm.model.User;

public interface UserDao extends IBaseDao<User>{
	
	/**
	 * 判断用户是否是经理角色
	 * @param user
	 * @return
	 */
	public boolean isUserSupervisor(User user);

	/**
	 * 获取用户已有的客户数
	 * @param user
	 * @return
	 */
	public int getUserCustomerCount(User user);
}
