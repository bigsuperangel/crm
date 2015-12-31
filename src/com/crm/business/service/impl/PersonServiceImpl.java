package com.crm.business.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.business.dao.PersonDao;
import com.crm.business.service.IPersonService;
import com.crm.common.PageModel;
import com.crm.core.base.BaseService;
import com.crm.model.Person;

/**
 * 联系人管理服务类
 * 
 * @author wukh
 * @2015-1-21
 */
@Service("personService")
public class PersonServiceImpl extends BaseService implements
		IPersonService {

	@Autowired
	@Qualifier("personDao")
	private PersonDao personDao;

	@Override
	public void addPerson(Person person) {
		personDao.add(person);
	}

	@Override
	public void deletePerson(Person person) {
		personDao.delete(person);
	}

	@Override
	public Person queryPerson(int id) {

		return personDao.getObject(id);
	}

	@Override
	public PageModel<Person> queryPersonList(int page, int limit,int cid) {

		return personDao.queryPersonList(page, limit,cid);
	}

	@Override
	public void updatePerson(Person person) {
		Person p = personDao.getObject(person.getId());
		BeanUtils.copyProperties(person, p, new String[]{"id","isValid"});
		personDao.update(p);
	}

	@Override
	public void deletePersons(Integer[] ids) {
		personDao.delete(ids);
	}

	@Override
	public String getComboboxList() {
		String hql = "select c from Person c";
		List<Person> list = personDao.getList(hql);
		StringBuffer sb = new StringBuffer("{topics:[");
		for (int i = 0; i < list.size(); i++) {
			Person c = list.get(i);
			sb.append(c.combobox());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

}
