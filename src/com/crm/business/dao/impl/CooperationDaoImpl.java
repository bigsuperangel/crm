package com.crm.business.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.crm.business.dao.ICooperationDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Cooperation;

@Repository("cooperationDao")
public class CooperationDaoImpl extends BaseDao<Cooperation, String> implements
		ICooperationDao {

	@Override
	public PageModel<Cooperation> queryListByArgs(int page, int limit,
			int cooperateType, int submitorId, String deptCode,
			String targetDeptCode, int receiverId, int audit, int isValid) {
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Cooperation c where 1=1");
		if (cooperateType != -2) {
			sb.append(" and c.cooperatorType = " + cooperateType);
		}
		if (submitorId != -2) {
			sb.append(" and c.submitor.id = " + submitorId);
		}
		if (!StringUtils.isEmpty(deptCode)) {
			sb.append(" and c.submitDept.code like '" + deptCode + "%'");
		}
		if (!StringUtils.isEmpty(targetDeptCode)) {
			sb.append(" and c.targetDept.code like '" + targetDeptCode + "%'");
		}
		if (receiverId != -2) {
			sb.append(" and c.receiver.id = " + receiverId);
		}
		if (audit != -2) {
			sb.append(" and c.customer.audit = " + audit);
		}
		if (isValid != -2) {
			sb.append(" and c.isValid = " + isValid);
		}

		return getPageModel(sb.toString(), page, limit);
	}

}
