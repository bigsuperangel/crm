package com.crm.sys.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.crm.core.base.BaseDao;
import com.crm.model.Dept;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.sys.dao.DeptDao;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;

@Repository("deptDao")
public class DeptDaoImpl extends BaseDao<Dept, String> implements DeptDao {

	@Override
	public int getParentMaxCode(int parentId) {
		String hql = "select max(d.code) from Dept d where d.isValid = 1 and d.parent.id = "
				+ parentId;
		try {
			Query query = this.getSession().createQuery(hql);
			Object result = query.uniqueResult();
			if (result == null) {
				// 父节点id*100
				Dept dept = getObject(parentId);
				String code = dept.getCode();
				return Integer.parseInt(code) * 100;

			} else {
				String code = result.toString();
				return Integer.parseInt(code) + 1;
			}
		} catch (Exception e) {
			Dept dept = getObject(parentId);
			String code = dept.getCode();
			return Integer.parseInt(code) * 100;
		}
	}

	@Override
	public int getUserCount(String deptCode) {
		String hql = "select count(u) from User u where u.isValid = 1 and u.dept.code like '"
				+ deptCode + "%'";
		try {
			Query query = this.getSession().createQuery(hql);
			Object result = query.uniqueResult();
			if (result == null) {
				return 0;

			} else {
				String code = result.toString();
				return Integer.parseInt(code);
			}
		} catch (Exception e) {
			return 0;
		}
	}

}
