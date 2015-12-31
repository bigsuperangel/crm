package com.crm.core.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.common.PageModel;
import com.crm.model.BaseModel;
import com.crm.model.Dept;

@Service("baseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public abstract class BaseService {
	
	public String getComboboxs(PageModel<BaseModel> modelList){
//		StringBuilder string = new StringBuilder();
//		"" + Dept.getComboboxJson(list)
//		+ "}";
//		string.append("{checked:false,children:")
		return "";
	}
	
}
