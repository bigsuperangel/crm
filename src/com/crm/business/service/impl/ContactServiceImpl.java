package com.crm.business.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.business.dao.ContactDao;
import com.crm.business.dao.CustomerDao;
import com.crm.business.service.IContactService;
import com.crm.business.service.ICustomerService;
import com.crm.common.PageModel;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Contact;
import com.crm.model.Customer;

/**
 * 联系记录管理服务类
 * 
 * @author wukh
 * @2015-1-21
 */
@Service("contactService")
public class ContactServiceImpl extends BaseService implements IContactService {

	@Autowired
	@Qualifier("contactDao")
	private ContactDao contactDao;

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;
	
	@Override
	public void addContact(Contact contact) throws CrmException {
		Customer customer = customerDao.getObject(contact.getCustomer().getId());
		if(customer.getAudit() != 1 || customer.getStatus() != 1 || customer.getIsValid() != 1){
			throw new CrmException("无效的客户");
		}
		contactDao.add(contact);
	}

	@Override
	public void deleteContact(Contact contact){
		contactDao.delete(contact);
	}

	@Override
	public Contact queryContact(int id) {

		return contactDao.getObject(id);
	}

	@Override
	public PageModel<Contact> queryContactList(int page, int limit, int cid,
			String startDate, String endDate) {

		return contactDao
				.queryContactList(page, limit, cid, startDate, endDate);
	}

	@Override
	public void updateContact(Contact contact) throws CrmException {
		Customer customer = customerDao.getObject(contact.getCustomer().getId());
		if(customer.getAudit() != 1 || customer.getStatus() != 1 || customer.getIsValid() != 1){
			throw new CrmException("无效的客户");
		}
		Contact c = contactDao.getObject(contact.getId());
		BeanUtils.copyProperties(contact, c, new String[] { "id", "isValid" });
		contactDao.update(c);
	}

	@Override
	public void deleteContacts(Integer[] ids) {
		contactDao.delete(ids);
	}

	@Override
	public String getComboboxList() {
		String hql = "select c from Contact c";
		List<Contact> list = contactDao.getList(hql);
		StringBuffer sb = new StringBuffer("{topics:[");
		for (int i = 0; i < list.size(); i++) {
			Contact c = list.get(i);
			sb.append(c.combobox());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

}
