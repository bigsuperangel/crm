package com.crm.sys.service;

import javax.servlet.http.HttpSession;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.User;
import com.crm.model.dto.UserDto;

public interface IUserService {
	public void checkLogin(HttpSession session,String userName,String password)throws CrmException;
	public PageModel<User> getList(int page, int limit);
	public PageModel<User> getForbitList(int page, int limit);
	public void update(User user,Integer[] ids) ;
	public void delete(Integer[] ids);
	public void add(User user,Integer[] ids);
	public String getUserCombobox(Integer deptId);
	public String getMenu(UserDto currentUser);
	
	public void resetPwd(int currentUserId,String oldPwd, String newPwd, String repeatPwd, User user)throws CrmException;
	public int getCustomerLimit(Integer userId)throws CrmException;
}
