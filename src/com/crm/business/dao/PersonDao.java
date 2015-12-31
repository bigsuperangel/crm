package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Person;

public interface PersonDao extends IBaseDao<Person>{

	public PageModel<Person> queryPersonList(int page, int limit,int cid) ;
}
