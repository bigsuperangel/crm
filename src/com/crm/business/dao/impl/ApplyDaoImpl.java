package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.IApplyDao;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseDao;
import com.crm.model.Apply;
import com.crm.model.User;

@Repository("applyDao")
public class ApplyDaoImpl extends BaseDao<Apply, Integer> implements IApplyDao {

	@Override
	public PageModel<Apply> getUserCustomerApplyList(User user,
			Integer customerId, Integer status,int page, int limit) {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from Apply a where a.applier.id = "+user.getId());
		if(customerId != null){
			hql.append(" and a.customer.id = "+customerId.intValue());
		}
		if(status != null){
			hql.append(" and a.status = "+status.intValue());
		}
		return getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void auditApply(int applyId,User user) {
		Apply apply = getObject(applyId);
		apply.setStatus(1);
		apply.setAuditor(user);
		apply.setOpTime(DateUtil.getFormatedCurrentDate());
		update(apply);
	}

	@Override
	public void batchRefuseApply(Integer applierId, Integer customerId,User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("update Apply a set a.status = -1, a.opTime = '"+DateUtil.getFormatedCurrentDate()
				+"', a.auditor.id="+user.getId()+" where a.status = 0");
		if(applierId != null){
			hql.append(" and a.applier.id = "+applierId.intValue());
		}
		if(customerId != null){
			hql.append(" and a.customer.id = "+customerId.intValue());
		}
		update(hql.toString());
	}

	@Override
	public void refuseApply(int applyId,User user) {
		Apply apply = getObject(applyId);
		apply.setStatus(-1);
		apply.setAuditor(user);
		apply.setCdate(DateUtil.getFormatedCurrentDate());
		update(apply);
	}

	@Override
	public PageModel<Apply> getAuditList(int page, int limit, Integer audit,
			String deptCode) {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from Apply a where a.applier.dept.code like '"+deptCode+"%'");
		if(audit != null){
			hql.append(" and a.status = "+audit.intValue());
		}
		
		return getPageModel(hql.toString(), page, limit);
	}


}
