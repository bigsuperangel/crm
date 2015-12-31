package com.crm.sys.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crm.common.PageModel;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Dept;
import com.crm.model.Dic;
import com.crm.model.TypeDic;
import com.crm.model.User;
import com.crm.sys.dao.IDicDao;
import com.crm.sys.dao.TypeDicDao;
import com.crm.sys.dao.UserDao;
import com.crm.sys.service.IDicService;
import com.crm.sys.service.ITypeDicService;

@Service("dicService")
public class DicServiceImpl extends BaseService implements IDicService {
	@Autowired
	@Qualifier("dicDao")
	private IDicDao dicDao;

	@Override
	public void add(Dic dic, User user) {
		dic.setCreateBy(user);
		dic.setCdate(DateUtil.getFormatedCurrentDate());
		dicDao.add(dic);
	}

	@Override
	public void delete(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			StringBuilder hql = new StringBuilder();
			hql.append("update Dic d set d.isValid = 0 where d.id in (");

			for (int i = 0; i < ids.length; i++) {
				hql.append(ids[i] + ",");
			}
			hql.setLength(hql.length() - 1);
			hql.append(")");
			dicDao.update(hql.toString());
		}
	}

	@Override
	public String getComboBox(String type) {
		StringBuilder hql = new StringBuilder(
				"select d from Dic d where d.isValid = 1");
		if (!StringUtils.isEmpty(type)) {
			hql.append(" and d.type = '" + type + "'");
		}
		hql.append(" order by d.sort asc");
		List<Dic> list = dicDao.getList(hql.toString());
		StringBuffer sb = new StringBuffer("{total:100,topics:[");
		for (int i = 0; i < list.size(); i++) {
			Dic dic = list.get(i);
			sb.append(dic.combobox());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public PageModel<Dic> getList(int page, int limit) {
		StringBuilder hql = new StringBuilder("select d from Dic d");
		return dicDao.getPageModel(hql.toString(), page, limit);
	}

	@Override
	public void update(Dic dic, User user) {
		Dic d = dicDao.getObject(dic.getId());
		BeanUtils.copyProperties(dic, d, new String[]{"id","isValid"});
		d.setUpdateBy(user);
		d.setUdate(DateUtil.getFormatedCurrentDate());
		dicDao.update(d);
	}

	@Override
	public void updateDelayDays(int delayDays, User user) throws CrmException {
		if(!user.isUserMaster()){
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		String hql = "select d from Dic d where d.type = 'free_time' and d.sort = 10 and d.isValid = 1";
		Dic dic = dicDao.getObject(hql);
		dic.setValue(delayDays+"");
		dicDao.update(dic);
	}

}
