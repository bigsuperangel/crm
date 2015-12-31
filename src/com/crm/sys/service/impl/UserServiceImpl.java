package com.crm.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.business.dao.ContactDao;
import com.crm.common.PageModel;
import com.crm.common.utils.JsonUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Privilege;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.model.dto.UserDto;
import com.crm.sys.dao.RoleDao;
import com.crm.sys.dao.UserDao;
import com.crm.sys.service.IUserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	public void checkLogin(HttpSession session, String userName, String password)
			throws CrmException {
		String hql = "select u from User u where u.username=? and u.password=? and u.enable=1";
		Object[] values = { userName, password };
		User user = (User) userDao.getObject(hql, values);
		if (user == null) {
			throw new CrmException("用户名或者密码不正确！请重新输入");
		}
		Set<Role> role = user.getRole();
		List<Privilege> list = new ArrayList<Privilege>();
		String roleName = "";
		int i = 0;
		for (Role r : role) {
			Set<Privilege> pri = r.getPrivs();
			for (Privilege p : pri) {
				boolean isPut = false;
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getId() == p.getId()) {
						isPut = true;
						break;
					}
				}
				if (!isPut) {
					list.add(p);
				}
			}
			roleName += r.getName();
			if (i < role.size() - 1) {
				roleName += "，";
			}
			i++;
		}
		Collections.sort(list);
		Privilege tree = Privilege.getTreeJson(list);
		UserDto ud = new UserDto();
		ud.setId(user.getId());
		ud.setUsername(user.getUsername());
		ud.setRealName(user.getRealName());
		// ud.setList(list);
		ud.setTree(tree);
		ud.setRoleName(roleName);
		ud.setDept(user.getDept());
//		session.setMaxInactiveInterval(3);
		session.setAttribute("user", ud);
	}

	@Override
	public PageModel<User> getForbitList(int page, int limit) {
		// TODO Auto-generated method stub
		String hql = "select u from User u where u.enable=2";
		return userDao.getPageModel(hql, page, limit);
	}

	@Override
	public PageModel<User> getList(int page, int limit) {
		// TODO Auto-generated method stub
		String hql = "select u from User u where u.enable=1";
		return userDao.getPageModel(hql, page, limit);
	}

	@Override
	public void add(User user, Integer[] ids) {
		// TODO Auto-generated method stub
		String hql = "select r from Role r where r.id in(:ids)";
		List<Role> list = roleDao.getList(hql, this.getAlias(ids));
		Set<Role> set = new HashSet<Role>();
		for (int i = 0; i < list.size(); i++) {
			Role role = (Role) list.get(i);
			set.add(role);
		}
		user.setRole(set);
		userDao.add(user);
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		String hql = "update User u set u.enable=3 where u.id in(:ids)";
		userDao.update(hql, this.getAlias(ids));
	}

	@Override
	public void update(User user, Integer[] ids) {
		User oriUser = userDao.getObject(user.getId());
//		user.setPassword(oriUser.getPassword());
		oriUser.setDept(user.getDept());
		oriUser.setEmail(user.getEmail());
		oriUser.setEnable(user.getEnable());
		oriUser.setRealName(user.getRealName());
		oriUser.setTel(user.getTel());
		oriUser.setUsername(user.getUsername());
		
		if(ids != null && ids.length != 0){
			String hql = "select r from Role r where r.id in(:ids)";
			List<Role> list = roleDao.getList(hql, this.getAlias(ids));
			Set<Role> set = new HashSet<Role>();
			for (int i = 0; i < list.size(); i++) {
				Role role = (Role) list.get(i);
				set.add(role);
			}
			oriUser.setRole(set);
		}
		
		userDao.update(oriUser);
	}

	public Map<String, Object> getAlias(Integer[] ids) {
		Map<String, Object> alias = new HashMap<String, Object>();
		alias.put("ids", ids);
		return alias;
	}

	@Override
	public String getUserCombobox(Integer deptId) {
		// TODO Auto-generated method stub
		String hql = "select u from User u where u.enable = 1";
		if (deptId != null) {
			hql = hql + " and u.dept.id = " + deptId;
		}
		List<User> list = userDao.getList(hql);
		StringBuffer sb = new StringBuffer("{total:100,topics:[");
		for (int i = 0; i < list.size(); i++) {
			sb.append((list.get(i)).combobox());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public String getMenu(UserDto currentUser) {
		// TODO Auto-generated method stub
		StringBuffer json = new StringBuffer("{checked:false,children:");
		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[] { "parent", "index", "done" });
		String[][] field = { { "name", "text" }, { "icon", "iconCls" } };
		ju.setField(field);
		String tree = ju.getJson(currentUser.getTree().getChildren());
		json.append(tree);
		json.append("}");
		return json.toString();
	}

	@Override
	public void resetPwd(int currentUserId,String oldPwd, String newPwd, String repeatPwd,
			User user) throws CrmException {
		System.out.println(oldPwd + "-" + newPwd + "-" + repeatPwd + "-" + user.getPassword());
		if(StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(repeatPwd) ){
			throw new CrmException("两次密码输入不一致");
		}
		if(newPwd == null){
			newPwd = "";
		}
		User currentUser = userDao.getObject(currentUserId);
		if (currentUser==null) {
			throw new CrmException("要修改的用户不存在");
		}
		if(!String.valueOf(oldPwd).equals(String.valueOf(currentUser.getPassword()))){
			throw new CrmException("旧密码输入错误");
		}
		
		String hql = "update User u set u.password = '"+newPwd+"' where u.id = "+currentUserId;
		userDao.update(hql);
		return;
	}

	@Override
	public int getCustomerLimit(Integer userId) throws CrmException {
		User user = userDao.getObject(userId);
		if(user == null){
			throw new CrmException("无效的用户ID");
		}
		return user.getLimit();
	}
}
