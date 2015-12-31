package com.crm.business.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.IAgreementDao;
import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseDao;
import com.crm.model.Agreement;
import com.crm.model.User;

@Repository("agreementDao")
public class AgreementDaoImpl extends BaseDao<Agreement, Integer> implements IAgreementDao {

	@Override
	public PageModel<Agreement> queryAgreementByArgs(int page, int limit,
			Integer customerId, Integer status, User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select a from Agreement a where a.isValid = 1");
		if(customerId != null){
			hql.append(" and a.customer.id = "+customerId.intValue());
		}
		if(status != null){
			hql.append(" and a.status = "+status.intValue());
		}
		if(user.isUserFinance()){
			//财务可以查看所有合同
		}else if(user.isUserSupervisor()){
			//查看自己区域内的客户的ID
			hql.append(" and a.customer.dept.code like '"+user.getDept().getCode()+"%'");
		}else{
			//只能查看自己客户的合同
			hql.append(" and a.customer.handler.id = "+user.getId());
		}
		
		return getPageModel(hql.toString(), page, limit);
	}

	@Override
	public String generateAgreementNo() {
		return UUID.randomUUID().toString().replace("-", "");
	}


}
