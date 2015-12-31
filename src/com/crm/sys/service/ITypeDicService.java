package com.crm.sys.service;

import com.crm.common.PageModel;
import com.crm.model.TypeDic;

public interface ITypeDicService {
	public PageModel<TypeDic> getList(int page,int limit);
	public void add(TypeDic typeDic);
	public void update(TypeDic typeDic);
	public void delete(Integer[] ids);
	public String getCombobox();
}
