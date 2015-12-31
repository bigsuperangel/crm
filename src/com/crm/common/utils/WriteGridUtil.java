package com.crm.common.utils;

import java.util.List;

import com.crm.common.PageModel;
import com.crm.model.BaseModel;

public class WriteGridUtil {
	public static String writeGrid(PageModel<? extends BaseModel > pm){
		StringBuffer sb = new StringBuffer("{total:" + pm.getTotal()
				+ ",topics:[");
		for (int i = 0,count=pm.getList().size(); i < count; i++) {
			BaseModel t = pm.getList().get(i);
			sb.append(t.getJson());
			if (i < pm.getList().size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String writeGrid(List<? extends BaseModel> list) {
		StringBuffer sb = new StringBuffer("{topics:[");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getJson());
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
