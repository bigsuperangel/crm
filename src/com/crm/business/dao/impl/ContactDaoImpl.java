package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.ContactDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Contact;
import com.crm.model.Dept;
import com.crm.model.Contact;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;

@Repository("contactDao")
public class ContactDaoImpl extends BaseDao<Contact, String> implements
		ContactDao {

	@Override
	public PageModel<Contact> queryContactList(int page, int limit, int cid,
			String startDate, String endDate) {
		String hql = "select c from Contact c where c.customer.id=" + cid
				+ " and c.cdate between '" + startDate + "' and '" + endDate
				+ "'";
		return getPageModel(hql, page, limit);
	}
}
