package com.crm.sys.dao;

import com.crm.core.base.IBaseDao;
import com.crm.model.Dic;

public interface IDicDao extends IBaseDao<Dic> {

	public String getValue(String type);
	
	public int getInt(String type);
}
