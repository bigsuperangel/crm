package com.crm.business.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.crm.common.PageModel;
import com.crm.core.base.IBaseDao;
import com.crm.model.Customer;

public interface CustomerDao extends IBaseDao<Customer> {
	
	public PageModel<Customer> queryCustomerList(int page, int limit) ;
	
	public PageModel<Customer> queryPublicCustomerList(int page, int limit,String deptCode);
}
