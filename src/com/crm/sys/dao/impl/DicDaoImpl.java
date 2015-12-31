package com.crm.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.core.base.BaseDao;
import com.crm.model.Dic;
import com.crm.model.TypeDic;
import com.crm.model.User;
import com.crm.sys.dao.IDicDao;
import com.crm.sys.dao.TypeDicDao;
import com.crm.sys.dao.UserDao;


@Repository("dicDao")
public class DicDaoImpl extends BaseDao<Dic, Integer> implements IDicDao{

	@Override
	public int getInt(String type) {
		String value = getValue(type);
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getValue(String type) {
		String hql = "select max(d.value) from  Dic d where d.type = '"+type+"'";
		Object value = queryUniqueValue(hql);
		return value == null? "":value.toString();
	}
	
}
