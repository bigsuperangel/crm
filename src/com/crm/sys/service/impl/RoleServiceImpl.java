package com.crm.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.common.PageModel;
import com.crm.common.utils.JsonUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.IBaseDao;
import com.crm.model.Privilege;
import com.crm.model.Role;
import com.crm.sys.dao.PrivilegeDao;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;
import com.crm.sys.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseService implements IRoleService{
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Autowired
	@Qualifier("privilegeDao")
	private PrivilegeDao privilegeDao;
	
	@Override
	public void add(Role role) {
		// TODO Auto-generated method stub
		roleDao.add(role);
	}
	@Override
	public void changePriv(int roleId,Integer[] remId,Integer[] putId) {
		// TODO Auto-generated method stub
		String hql = "select r from Role r where r.id="+roleId;
		Role role = (Role) roleDao.getObject(hql);
		if(remId != null){
			List<Privilege> rList = new ArrayList<Privilege>();
			for(Privilege p:role.getPrivs()){
				for(int pid:remId){
					if(p.getId() == pid){
						rList.add(p);
					}
				}
			}
			role.getPrivs().removeAll(rList);
		}
		if(putId != null){
			hql = "select p from Privilege p where p.id in (:ids)";
			Map<String, Object> alias = new HashMap<String, Object>();
			alias.put("ids", putId);
			List<Privilege> pList = privilegeDao.getList(hql, alias);

			for(int i = 0;i<pList.size();i++){
				Privilege p = (Privilege)pList.get(i);
				role.getPrivs().add(p);
			}
		}
		this.update(role);
//		updateObj(role);
//		writeSuccess();
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		roleDao.delete(ids);
	}

	@Override
	public String getComboboxList() {
		// TODO Auto-generated method stub
		String hql = "select r from Role r";
		List<Role> list = roleDao.getList(hql);
		StringBuffer sb = new StringBuffer("{topics:[");
		for(int i = 0;i<list.size();i++){
			Role r = list.get(i);
			sb.append(r.getComboboJson());
			if(i<list.size() -1){
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public PageModel<Role> getList(int page,int limit) {
		// TODO Auto-generated method stub
		String hql = "select r from Role r";
		return roleDao.getPageModel(hql,page,limit);
	}

	@Override
	public String getPriv(int roleId) {
		// TODO Auto-generated method stub
		String hql = "select p from Privilege p where p.id!=1";
		List<Privilege> list1 = privilegeDao.getList(hql);
		hql = "select r from Role r where r.id="+roleId;
		Role role = (Role)roleDao.getObject(hql);
		List<Privilege> list = new ArrayList<Privilege>();
		for(int i = 0;i<list1.size();i++){
			Privilege pri = list1.get(i);
			for(Privilege p : role.getPrivs()){
				if(pri.getId() == p.getId()){
					pri.setDone(true);
					break;
				}
			}
			list.add(pri);
		}
		Privilege tree = Privilege.getTreeJson(list);
		tree.setName("所有权限");
		tree.setExpanded(true);
		
		StringBuffer json = new StringBuffer("{checked:false,children:[");
		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[]{"parent","index","config"});
		String[][] field = {{"name","text"},{"icon","iconCls"}};
		ju.setField(field);
		String treeJson = ju.getJson(tree);
		json.append(treeJson);
		json.append("]}");
		return json.toString();
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		Role r = roleDao.getObject(role.getId());
		r.setCount(role.getCount());
		r.setName(role.getName());
		roleDao.update(r);
	}
	
}
