package com.crm.sys.service;

import com.crm.common.PageModel;
import com.crm.core.base.CrmException;
import com.crm.model.Dic;
import com.crm.model.User;

public interface IDicService {
	/**
	 * 查询所有列表(编辑列表页面)
	 * @param page
	 * @param limit
	 * @return
	 */
	public PageModel<Dic> getList(int page,int limit);
	/**
	 * 新增
	 * @param dic
	 */
	public void add(Dic dic,User user);
	/**
	 * 更新
	 * @param dic
	 */
	public void update(Dic dic, User user);
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(Integer[] ids);
	/**
	 * 获取同一类型的items
	 * @param type		类型
	 * @return
	 */
	public String getComboBox(String type);

	/**
	 * 修改延时天数
	 * @param delayDays
	 * @param user
	 * @throws CrmException 
	 */
	public void updateDelayDays(int delayDays, User user) throws CrmException;
}
