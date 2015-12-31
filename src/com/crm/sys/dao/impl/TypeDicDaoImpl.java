package com.crm.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.core.base.BaseDao;
import com.crm.model.TypeDic;
import com.crm.model.User;
import com.crm.sys.dao.TypeDicDao;
import com.crm.sys.dao.UserDao;


@Repository("typeDicDao")
public class TypeDicDaoImpl extends BaseDao<TypeDic, String> implements TypeDicDao{
	
}
