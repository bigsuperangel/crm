package com.crm.core.base;

import java.util.List;
import java.util.Map;

import com.crm.common.PageModel;
import com.crm.model.Customer;

public interface IBaseDao<J> {
	public List<J> getList(String hql);
	
	public List<J> getList(String hql, Object[] values);

	public PageModel<J> getPageModel(String hql, Object[] values,
			int page, int limit);

	public PageModel<J> getPageModel(String hql, int page, int limit);

	public List<J> getList(String hql, Map<String, Object> alias);
	
	public List<J> getList(String hql, Object[] values,
			Map<String, Object> alias);

	public PageModel<J> getPageModel(String hql,
			Map<String, Object> alias, int page, int limit);

	public PageModel<J> getPageModel(String hql, Object[] values,
			Map<String, Object> alias, int page, int limit);

	public J getObject(String hql);

	public J getObject( int id);

	public J getObject(String hql, Object[] values);

	public void add(J t);

	public void update(J t);

	public void update(String hql);

	public void update(String hql, Object[] values);

	public void update(String hql, Map<String, Object> alias);

	public void update(String hql, Map<String, Object> alias, Object[] values);

	public void merge(J t);
	
	public void delete(int id);
	
	public void delete(Integer[] ids);

	public void delete(J t);

	public void delete(String hql);

	public void delete(String hql, Object[] values);

	public void delete(String hql, Map<String, Object> alias);

	public void delete(String hql, Map<String, Object> alias, Object[] values);
	
	public Object queryUniqueValue(String hql);
}
