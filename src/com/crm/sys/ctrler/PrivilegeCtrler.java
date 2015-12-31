package com.crm.sys.ctrler;

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
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Dic;
import com.crm.model.Privilege;
import com.crm.model.User;
import com.crm.sys.service.IDicService;
import com.crm.sys.service.IPrivilegeService;

@Controller
@RequestMapping("/crm")
public class PrivilegeCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(PrivilegeCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("privilegeService")
	private IPrivilegeService privilegeService;

	@RequestMapping(value = "/privilege_tree")
	@ResponseBody
	public String tree() {
		try {
			return privilegeService.getPrivilegeTree();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/priv_add")
	@ResponseBody
	public String priv_add(Privilege privilege) {
		try {
			privilegeService.add(privilege);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/priv_update")
	@ResponseBody
	public String priv_update(Privilege privilege) {
		try {
			privilegeService.update(privilege);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/priv_delete")
	@ResponseBody
	public String priv_delete(Integer[] ids) {
		try {
			privilegeService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@Override
	protected String handlerException(Exception e) {
		e.printStackTrace();
		System.err.println("[handlerException:MemberController]异常类型："
				+ e.getClass().getSimpleName());
		System.err.println(e.getMessage());
		return "系统异常";
	}
}
