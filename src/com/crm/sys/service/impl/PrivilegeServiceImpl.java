package com.crm.sys.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.common.utils.DateUtil;
import com.crm.common.utils.TreeUtil;
import com.crm.core.base.BaseService;
import com.crm.model.Customer;
import com.crm.model.Dept;
import com.crm.model.Privilege;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.dao.PrivilegeDao;
import com.crm.sys.service.IDeptService;
import com.crm.sys.service.IPrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseService implements IPrivilegeService {
	@Autowired
	@Qualifier("privilegeDao")
	private PrivilegeDao privilegeDao;

	@Override
	public String getPrivilegeTree() {
		String hql = "from Privilege";
		List<Privilege> list = privilegeDao.getList(hql);
		return TreeUtil.toTree(list);
	}

	@Override
	public void add(Privilege privilege) {
		// TODO Auto-generated method stub
		privilegeDao.add(privilege);
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		privilegeDao.delete(ids);
	}

	@Override
	public void update(Privilege privilege) {
		// TODO Auto-generated method stub
		Privilege p = privilegeDao.getObject(privilege.getId());
		p.setName(privilege.getName());
		p.setConfig(privilege.getConfig());
		p.setIcon(privilege.getIcon());
		p.setIndex(privilege.getIndex());
		privilegeDao.update(p);
	}

}
