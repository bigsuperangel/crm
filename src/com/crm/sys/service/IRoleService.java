package com.crm.sys.service;

import com.crm.common.PageModel;
import com.crm.model.Role;

public interface IRoleService {
	public void add(Role role);
	public void update(Role role);
	public void delete(Integer[] ids);
	public String getComboboxList();
	public PageModel<Role> getList(int page,int limit);
	public String getPriv(int roleId);
	public void changePriv(int roleId,Integer[] remId,Integer[] putId);
}
