package com.crm.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.core.base.BaseDao;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;


@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role, String> implements RoleDao{
	
}
