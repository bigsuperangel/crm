package com.crm.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.common.PageModel;
import com.crm.core.base.BaseService;
import com.crm.model.TypeDic;
import com.crm.sys.dao.TypeDicDao;
import com.crm.sys.dao.UserDao;
import com.crm.sys.service.ITypeDicService;

@Service("typeDicService")
public class TypeDicServiceImpl extends BaseService implements ITypeDicService{
	@Autowired
	@Qualifier("typeDicDao")
	private TypeDicDao typeDicDao;
	
	@Override
	public void add(TypeDic typeDic) {
		// TODO Auto-generated method stub
		typeDicDao.add(typeDic);
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		typeDicDao.delete(ids);
	}

	@Override
	public PageModel<TypeDic> getList(int page, int limit) {
		// TODO Auto-generated method stub
		String hql = "select t from TypeDic t";
		return typeDicDao.getPageModel(hql, page, limit);
	}

	@Override
	public void update(TypeDic typeDic) {
		// TODO Auto-generated method stub
		typeDicDao.update(typeDic);
	}

	@Override
	public String getCombobox() {
		// TODO Auto-generated method stub
		String hql = "select t from TypeDic t ";
		List<TypeDic> list = typeDicDao.getList(hql);
		StringBuffer sb = new StringBuffer("{total:100,topics:[");
		for(int i = 0;i<list.size();i++){
			sb.append(((TypeDic)list.get(i)).combobox());
			if(i<list.size()-1){
				sb.append(",");
			}
		}
		sb.append("]}");
		return sb.toString();
	}
	
}
