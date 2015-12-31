package com.crm.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.model.Dept;
import com.crm.model.User;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.service.IDeptService;

@Service("deptService")
public class DeptServiceImpl extends BaseService implements IDeptService {

	// 关于最大code运算的同步锁
	private static byte[] getMaxCodeLock = new byte[0];

	@Autowired
	@Qualifier("deptDao")
	private DeptDao deptDao;

	@Override
	public void delete(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Dept dept = deptDao.getObject(ids[i]);
				String hql = "update Dept d set d.isValid = 0 where d.code like '"
						+ dept.getCode() + "%'";
				deptDao.update(hql);
			}
		}
	}

	@Override
	public String getDeptTree() {
		String hql = "select d from Dept d where d.id!=1 and d.isValid = 1";
		List<Dept> list = deptDao.getList(hql);
		String json = "{checked:false,children:[" + Dept.getJson(list) + "]}";
		return json;
	}

	@Override
	public String getTreeCombobox() {
		String hql = "select d from Dept d where d.id!=1 and d.isValid = 1";
		List<Dept> list = deptDao.getList(hql);// []
		String json = "{checked:false,children:" + Dept.getComboboxJson(list)
				+ "}";
		return json;
	}

	@Override
	public void move(int pid, int did) {
		synchronized (getMaxCodeLock) {
			int code = deptDao.getParentMaxCode(pid);
			String hql = "update Dept d set d.parent.id=" + pid + ",d.code = '"
					+ code + "' where d.id=" + did;
			deptDao.update(hql);
		}
	}

	@Override
	public void update(Dept dept) {
		Dept d = deptDao.getObject(dept.getId());
		d.setName(dept.getName());
		deptDao.update(d);
	}

	@Override
	public void add(Dept dept) {
		synchronized (getMaxCodeLock) {
			int code = deptDao.getParentMaxCode(dept.getParent().getId());
			dept.setCdate(DateUtil.getFormatedCurrentDate());
			dept.setCode(String.valueOf(code));
			deptDao.add(dept);
		}
	}

	@Override
	public String getDeptTree(int flag, User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select d from Dept d where d.isValid = 1 and d.code != 1");
		String bigAreaCode = user.getDept().getBigAreaCode();
		String selfDeptCode = user.getDept().getCode();
		switch (flag) {
		case 1: // 自己的部门
			hql.append(" and (d.code = "+bigAreaCode+" or d.code like '"+selfDeptCode+"%')");
			break;

		case 2: // 跨区域部门
			if(user.isUserSupervisor()){
				return "";
			}
			hql.append(" and d.code like '"+bigAreaCode+"%' and d.code not like '"+user.getDept().getCode()+"%'");
			break;

		case 3: // 跨大区部门
			hql.append(" and d.code not like '"+bigAreaCode+"%'");
			break;
		}
		List<Dept> deptList = deptDao.getList(hql.toString());
		if(deptList == null || deptList.size() == 0){
			return "";
		}
		return Dept.getComboboxJson(deptList);
	}
}
