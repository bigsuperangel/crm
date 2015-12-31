package com.crm.business.dao;

import com.crm.core.base.IBaseDao;
import com.crm.model.AgreementDetail;

public interface IAgreementDetailDao extends IBaseDao<AgreementDetail>{
	public AgreementDetail getDetailByAgreementId(int agreementId);
}
