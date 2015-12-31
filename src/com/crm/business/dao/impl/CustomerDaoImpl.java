package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.CustomerDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Customer;

/**
 * 用户DAO
 * 
 * @author wukh
 * @2015-1-21
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDao<Customer, String> implements
		CustomerDao {

	@Override
	public PageModel<Customer> queryCustomerList(int page, int limit) {
		String hql = "select c from Customer c";
		return getPageModel(hql, page, limit);
	}

	@Override
	public PageModel<Customer> queryPublicCustomerList(int page, int limit,
			String deptCode) {

		String hql = "select c from Customer c where c.dept.code like '" + deptCode
				+ "%' and c.handler is null and c.isValid = 1 and c.audit = 1 and c.status = 1";
		return getPageModel(hql, page, limit);
	}
}
