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

import sun.rmi.runtime.Log;

import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Dic;
import com.crm.model.User;
import com.crm.sys.service.IDicService;

@Controller
@RequestMapping("/crm")
public class DicCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(DicCtrler.class.getSimpleName());
	@Autowired
	@Qualifier("dicService")
	private IDicService dicService;

	@RequestMapping(value = "/type_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Dic dic, HttpSession session) {
		
		try {
			if (dic != null) {
				User user = getSessionUser(session);
				dicService.add(dic, user);
			}
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/type_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Dic dic, HttpSession session) {
		try {
			User user = getSessionUser(session);
			dicService.update(dic, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/type_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			dicService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/type_getCombobox")
	@ResponseBody
	public String getCombobox(String type) {
		try {
			return dicService.getComboBox(type);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/type_getList")
	@ResponseBody
	public String getList(int page, int limit) {
		try {
			PageModel<Dic> list = dicService.getList(page, limit);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/type_updateDelayDays")
	@ResponseBody
	public String updateDelayDays(int delayDays, HttpSession session) {
		try {
			User user = getSessionUser(session);
			dicService.updateDelayDays(delayDays, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
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
