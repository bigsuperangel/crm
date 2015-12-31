package com.crm.core.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.common.PageModel;

@SuppressWarnings("unchecked")
public abstract class BaseDao<T extends Object, PK extends Serializable> {
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> entityClass;

	public BaseDao() {
		this.entityClass = null;
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	public Session getSession() {
		// return this.sessionFactory.openSession();
		return this.sessionFactory.getCurrentSession();
	}

	private Query getQuery(String hql) {
		return this.getSession().createQuery(hql);
	}

	public List<T> getList(String hql) {
		return this.getQuery(hql).list();
	}

	public void setParams(Query query, Object[] values) {
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
	}

	/**
	 * select * from user;
	 * 
	 * @param hql
	 * @return
	 */
	public String getCountHql(String hql) {
		int pos = hql.indexOf("from");
		return "select count(*) " + hql.substring(pos);
	}

	public void setPage(Query query, int page, int limit) {
		if (page == -1 || limit == -1) {
			return;
		}
		query.setFirstResult((page - 1) * limit);
		query.setMaxResults(limit);
	}

	/**
	 * select u from User u where i.id in(:uid)
	 * 
	 * @param query
	 * @param alias
	 */
	@SuppressWarnings("rawtypes")
	public void setAlias(Query query, Map<String, Object> alias) {
		if (alias != null && alias.size() > 0) {
			Set<String> key = alias.keySet();
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String k = it.next();
				Object val = alias.get(k);
				if (val instanceof Collection) {
					query.setParameterList(k, (Collection) val);
				} else if (val.getClass().isArray()) {
					query.setParameterList(k, (Object[]) val);
					System.out.println("array");
				} else {
					query.setParameter(k, val);
				}
			}
		}
	}

	// //////-------------------
	public List<T> getList(String hql, Object[] values) {
		Query query = this.getQuery(hql);
		setParams(query, values);
		return query.list();
	}

	public PageModel<T> getPageModel(String hql, Object[] values, int page,
			int limit) {
		PageModel<T> pm = new PageModel<T>();
		Query squery = this.getQuery(hql);
		Query cquery = this.getQuery(this.getCountHql(hql));
		this.setParams(squery, values);
		this.setParams(cquery, values);
		this.setPage(squery, page, limit);
		int total = ((Long) cquery.uniqueResult()).intValue();
		pm.setTotal(total);
		pm.setList(squery.list());
		return pm;
	}

	public PageModel<T> getPageModel(String hql, int page, int limit) {
		return this.getPageModel(hql, new Object[] {}, page, limit);
	}

	public List<T> getList(String hql, Map<String, Object> alias) {
		return this.getList(hql, new Object[] {}, alias);
	}

	/**
	 * select u from User u where u.id in(:uid) and u.role=?
	 */
	public List<T> getList(String hql, Object[] values,
			Map<String, Object> alias) {
		Query query = this.getQuery(hql);
		this.setAlias(query, alias);
		this.setParams(query, values);
		return query.list();
	}

	public PageModel<T> getPageModel(String hql, Map<String, Object> alias,
			int page, int limit) {
		return this.getPageModel(hql, new Object[] {}, alias, page, limit);
	}

	public PageModel<T> getPageModel(String hql, Object[] values,
			Map<String, Object> alias, int page, int limit) {
		PageModel<T> pm = new PageModel<T>();
		Query cquery = this.getQuery(this.getCountHql(hql));
		Query squery = this.getQuery(hql);
		this.setParams(squery, values);
		this.setAlias(squery, alias);
		this.setPage(squery, page, limit);

		this.setParams(cquery, values);
		this.setAlias(cquery, alias);
		int total = (Integer) cquery.uniqueResult();
		pm.setTotal(total);
		pm.setList(squery.list());
		return pm;
	}

	public T getObject(String hql) {
		return this.getObject(hql, new Object[] {});
	}

	public T getObject(int id) {
		return (T) this.getSession().get(entityClass, id);
	}

	public T getObject(String hql, Object[] values) {
		Query query = this.getQuery(hql);
		this.setParams(query, values);
		return (T) query.uniqueResult();
	}

	public void add(T t) {
		this.getSession().save(t);
		System.out.println("add success");
	}

	public void update(T t) {
		this.getSession().update(t);
	}

	public void update(String hql) {
		this.update(hql, null, new Object[] {});
	}

	public void update(String hql, Object[] values) {
		this.update(hql, null, values);
	}

	public void update(String hql, Map<String, Object> alias) {
		this.update(hql, alias, new Object[] {});
	}

	public void update(String hql, Map<String, Object> alias, Object[] values) {
		Query query = this.getQuery(hql);
		this.setParams(query, values);
		this.setAlias(query, alias);
		query.executeUpdate();
	}

	public void merge(T t) {
		this.getSession().merge(t);
	}

	public void delete(int id) {
		T t = (T) this.getSession().get(entityClass, id);
		this.delete(t);
	}

	public void delete(Integer[] ids) {
		if (ids != null && ids.length != 0) {
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}

	public void delete(T t) {
		this.getSession().delete(t);
	}

	public void delete(String hql) {
		this.delete(hql, null, new Object[] {});
	}

	public void delete(String hql, Object[] values) {
		this.delete(hql, null, values);
	}

	public void delete(String hql, Map<String, Object> alias) {
		this.delete(hql, alias, new Object[] {});
	}

	public void delete(String hql, Map<String, Object> alias, Object[] values) {
		Query query = this.getQuery(hql);
		this.setParams(query, values);
		this.setAlias(query, alias);
		query.executeUpdate();
	}

	public Iterator<?> getIterator(String hql) {
		return this.getQuery(hql).iterate();
	}
	
	public Object queryUniqueValue(String hql){
		Query query = this.getSession().createQuery(hql);
		Object result = query.uniqueResult();
		
		return result;
	}
	
	
	public Object queryUniqueValue(String hql,Object[] params){
		Query query = this.getSession().createQuery(hql);
		this.setParams(query, params);
		Object result = query.uniqueResult();
		
		return result;
	}
}
