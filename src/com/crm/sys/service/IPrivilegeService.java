package com.crm.sys.service;

import java.util.List;

import com.crm.model.Dept;
import com.crm.model.Privilege;

public interface IPrivilegeService {
	public String getPrivilegeTree();
	public void add(Privilege privilege);
	public void update(Privilege privilege);
	public void delete(Integer[] ids);
}
