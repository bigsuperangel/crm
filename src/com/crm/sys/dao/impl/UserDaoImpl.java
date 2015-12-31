package com.crm.sys.dao.impl;

import java.util.Set;

import org.hibernate.dialect.InterbaseDialect;
import org.springframework.stereotype.Repository;

import com.crm.core.base.BaseDao;
import com.crm.model.Dept;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User, String> implements UserDao {

	@Override
	public boolean isUserSupervisor(User user) {
		return user.isUserSupervisor();
	}

	@Override
	public int getUserCustomerCount(User user) {
		String hql = "select count(c) from Customer c where c.handler.id = ? and c.isValid = 1";
		Object result = queryUniqueValue(hql, new Integer[]{user.getId()});
		try {
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
