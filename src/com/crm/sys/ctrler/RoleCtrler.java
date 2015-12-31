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
import com.crm.core.base.CrmException;
import com.crm.model.Role;
import com.crm.sys.service.IRoleService;

@Controller
@RequestMapping("/crm")
public class RoleCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(RoleCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;

	/**
	 * 添加公司
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/role_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Role role) {
		try {
			if (role != null) {
				roleService.add(role);
			}
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Role role) {
		try {
			roleService.update(role);
			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			roleService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_getComboboxList")
	@ResponseBody
	public String getComboboxList() {
		try {
			return roleService.getComboboxList();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_getList")
	@ResponseBody
	public String getList(int page, int limit) {
		try {
			PageModel<Role> list = roleService.getList(page, limit);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_getPriv")
	@ResponseBody
	public String getPriv(int roleId) {
		try {
			return roleService.getPriv(roleId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/role_changePriv")
	@ResponseBody
	public String changePriv(int roleId, Integer[] remId, Integer[] putId) {
		try {
			roleService.changePriv(roleId, remId, putId);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@Override
	protected String handlerException(Exception e) {
		e.printStackTrace();
		System.err.println("[handlerException:MemberController]异常类型：" + e.getClass().getSimpleName());
		System.err.println(e.getMessage());
		return "系统异常";
	}
}
