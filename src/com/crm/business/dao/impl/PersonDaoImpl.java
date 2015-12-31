package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.PersonDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Person;

@Repository("personDao")
public class PersonDaoImpl extends BaseDao<Person, String> implements PersonDao {

	@Override
	public PageModel<Person> queryPersonList(int page, int limit,int cid) {
		String hql = "select p from Person p where p.customer.id="+cid;
		return getPageModel(hql, page, limit);
	}

}
