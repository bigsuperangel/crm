package com.crm.business.service;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.Contact;
import com.crm.model.Customer;
import com.crm.model.Person;

/**
 * 
 * @author wukh
 * @2015-1-24
 */
public interface IContactService {
	/**
	 * 新增联系记录
	 * 
	 * @param Contact
	 * @return
	 * @throws CrmException 
	 */
	public void addContact(Contact Contact) throws CrmException;

	/**
	 * 删除联系记录
	 * 
	 * @param Contact
	 * @return
	 */
	public void deleteContact(Contact Contact);

	/**
	 * 批量删除联系记录
	 * 
	 * @param Contact
	 * @return
	 */
	public void deleteContacts(Integer[] ids);

	/**
	 * 更新联系记录资料
	 * 
	 * @param Contact
	 * @return
	 * @throws CrmException 
	 */
	public void updateContact(Contact Contact) throws CrmException;

	/**
	 * 分页查询联系记录
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageModel<Contact> queryContactList(int page, int limit ,int cid,String startDate, String endDate);

	/**
	 * 查询特定联系记录详细
	 * 
	 * @param Contact
	 * @return
	 */
	public Contact queryContact(int id);

	/**
	 * 获取id和namejson
	 * 
	 * @return
	 */
	public String getComboboxList();
}
