package com.crm.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	/** 重复捡起公海客户申请 */
	public static final String EXCEPTION_CODE_DUPPLICATE_APPLY = "1001";
	
	//不需要鉴权的地址放在MAP里面
	public static Map<String, String> noAuthmap= new HashMap<String, String>();
	static{
		noAuthmap.put("/crm/login", "/crm/login");
		noAuthmap.put("/crm/userLogin", "/crm/userLogin");
	}
}
