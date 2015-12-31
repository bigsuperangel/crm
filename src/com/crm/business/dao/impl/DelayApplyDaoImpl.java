package com.crm.business.dao.impl;


import org.springframework.stereotype.Repository;

import com.crm.business.dao.IDelayApplyDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.DelayApply;
import com.crm.model.User;

@Repository("delayApplyDao")
public class DelayApplyDaoImpl extends BaseDao<DelayApply, Integer> implements IDelayApplyDao {

	@Override
	public PageModel<DelayApply> queryDelayApplyByArgs(int page, int limit,
			Integer customerId, Integer applierId, Integer status, User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select d from DelayApply d where ");
		if(user.isUserSupervisor()){
			hql.append("d.customer.dept.code like '"+user.getDept().getCode()+"%'");
			
		}else{
			hql.append("d.customer.handler.id = "+user.getId());
		}
		
		if(customerId != null){
			hql.append(" and d.customer.id = "+customerId);
		}
		if(applierId != null){
			hql.append(" and d.applier.id = "+applierId);
		}
		if(status != null){
			hql.append(" and d.status = "+status);
		}
		
		return getPageModel(hql.toString(), page, limit);
	}
}
