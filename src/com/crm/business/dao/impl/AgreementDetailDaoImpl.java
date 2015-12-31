package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.IAgreementDao;
import com.crm.business.dao.IAgreementDetailDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Agreement;
import com.crm.model.AgreementDetail;

@Repository("detailDao")
public class AgreementDetailDaoImpl extends BaseDao<AgreementDetail, Integer>
		implements IAgreementDetailDao {

	@Override
	public AgreementDetail getDetailByAgreementId(int agreementId) {
		String hql = "select d from AgreementDetail d where d.agreement.id = "+agreementId+" and d.isValid = 1";
		return getObject(hql);
	}

}
