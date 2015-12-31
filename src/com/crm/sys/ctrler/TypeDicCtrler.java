package com.crm.sys.ctrler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.pojo.PageResult;
import com.crm.common.pojo.QueryCondition;
import com.crm.common.utils.Constant;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.model.TypeDic;
import com.crm.sys.service.ITypeDicService;
import com.crm.sys.service.ITypeDicService;

//@Controller
//@RequestMapping("/crm")
public class TypeDicCtrler extends BaseCtrler {
//	@Autowired
//	@Qualifier("typeDicService")
//	private ITypeDicService typeDicService;
//
//	/**
//	 * 添加公司
//	 * 
//	 * @param company
//	 * @return
//	 */
////	@RequestMapping(value = "/type_add", method = RequestMethod.POST)
//	@ResponseBody
//	public String add(TypeDic typeDic) {
//		try {
//			if (typeDic != null) {
//				typeDicService.add(typeDic);
//			}
//			return toJson(JsonMsgFactory.successMsg());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return toJson(JsonMsgFactory.failMsg());
//		}
//	}
//
//	@RequestMapping(value = "/type_update", method = RequestMethod.POST)
//	@ResponseBody
//	public String update(TypeDic typeDic) {
//		try {
//			typeDicService.update(typeDic);
//			return toJson(JsonMsgFactory.successMsg());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return toJson(JsonMsgFactory.failMsg());
//		}
//	}
//
//	@RequestMapping(value = "/type_delete", method = RequestMethod.POST)
//	@ResponseBody
//	public String delete(@RequestParam(value = "ids") Integer[] ids) {
//		try {
//			typeDicService.delete(ids);
//			return toJson(JsonMsgFactory.successMsg());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return toJson(JsonMsgFactory.failMsg());
//		}
//	}
//
//	@RequestMapping(value = "/type_getCombobox")
//	@ResponseBody
//	public String getCombobox() {
//		try {
//			return typeDicService.getCombobox();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return toJson(JsonMsgFactory.failMsg());
//		}
//	}
//
//	@RequestMapping(value = "/type_getList")
//	@ResponseBody
//	public String getList(int page,int limit) {
//		try {
//			PageModel<TypeDic> list =typeDicService.getList(page,limit);
//			return WriteGridUtil.writeGrid(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return toJson(JsonMsgFactory.failMsg());
//		}
//	}

	@Override
	protected String handlerException(Exception e) {
		e.printStackTrace();
		System.err.println("[handlerException:MemberController]异常类型：" + e.getClass().getSimpleName());
		System.err.println(e.getMessage());
		return "系统异常";
	}
}
