package com.crm.business.dao;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Contact;

public interface ContactDao extends IBaseDao<Contact> {

	public PageModel<Contact> queryContactList(int page, int limit ,int cid,String startDate, String endDate);
}
