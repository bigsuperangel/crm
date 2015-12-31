package com.crm.business.service;

import com.crm.common.PageModel;
import com.crm.model.Customer;
import com.crm.model.Person;

/**
 * 
 * @author wukh
 * @2015-1-24
 */
public interface IPersonService {
	/**
	 * 新增联系人
	 * @param Person
	 * @return
	 */
	public void addPerson(Person Person);
	
	/**
	 * 删除联系人
	 * @param Person
	 * @return
	 */
	public void deletePerson(Person Person);
	
	
	/**
	 * 批量删除联系人
	 * @param Person
	 * @return
	 */
	public void deletePersons(Integer[] ids);
	
	/**
	 * 更新联系人资料
	 * @param Person
	 * @return
	 */
	public void updatePerson(Person Person);
	
	/**
	 * 分页查询联系人
	 * @param page
	 * @param limit
	 * @param cid 
	 * @return
	 */
	public PageModel<Person> queryPersonList(int page, int limit, int cid);
	
	/**
	 * 查询特定联系人详细
	 * @param Person
	 * @return
	 */
	public Person queryPerson(int id);
	/**
	 * 获取id和namejson
	 * @return
	 */
	public String getComboboxList();
}
