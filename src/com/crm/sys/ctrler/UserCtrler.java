package com.crm.sys.ctrler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.pojo.PageResult;
import com.crm.common.pojo.QueryCondition;
import com.crm.common.utils.Constant;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Role;
import com.crm.model.User;
import com.crm.model.dto.UserDto;
import com.crm.sys.service.IUserService;

@Controller
@RequestMapping("/crm")
public class UserCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(UserCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/login.jsp";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "redirect:/login.jsp";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(String username, String password, String certCode,
			Model model, HttpSession session) {
		try {
			// 暂时不作验证码验证
			// String sessionCertCode = (String)
			// session.getAttribute("certCode");
			// if (!sessionCertCode.equalsIgnoreCase(certCode)) {
			// throw new CrmException("验证码不正确");
			// }
			userService.checkLogin(session, username, password);
			return "redirect:/crm/index.html";
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			model.addAttribute("error", e.getMessage());
			return "redirect:/login.jsp";
		}
	}

	@RequestMapping(value = "/user_logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		try {
			session.removeAttribute("user");
			session.invalidate();
			return "redirect:../login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login.jsp";
		}
	}

	@RequestMapping(value = "/user_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(User user, Integer[] ids) {
		try {
			if (user != null) {
				userService.add(user, ids);
			}
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 修改用户数据(上级改下级)
	 * 
	 * @param user
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/user_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(User user, Integer[] ids) {
		try {
			userService.update(user, ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 修改用户信息(只能修改自己的信息)
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user_modify", method = RequestMethod.POST)
	@ResponseBody
	public String modify(User user, HttpSession session) {
		try {
			User oriuser = getSessionUser(session);
			if (user.getId() != oriuser.getId()) {
				throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
			}
			userService.update(user, null);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param repeatPwd
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user_resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(int currentUserId, String oldPwd, String newPwd,
			String repeatPwd, HttpSession session) {
		try {
			User user = getSessionUser(session);
			userService
					.resetPwd(currentUserId, oldPwd, newPwd, repeatPwd, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/user_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			userService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/user_getList")
	@ResponseBody
	public String getList(int page, int limit) {
		try {
			PageModel<User> list = userService.getList(page, limit);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/user_getForbitList")
	@ResponseBody
	public String getForbitList(int page, int limit) {
		try {
			PageModel<User> list = userService.getForbitList(page, limit);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 获取用户的客户数限制
	 * 
	 * @param userId
	 *            要查询的用户. 传null则查自己
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user_getCustomerLimit")
	@ResponseBody
	public String getCustomerLimit(
			@RequestParam(required = false) Integer userId, HttpSession session) {
		try {
			if (userId == null) {
				User user = getSessionUser(session);
				return user.getLimit() + "";
			} else {
				return userService.getCustomerLimit(userId) + "";
			}
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/user_getUserCombobox")
	@ResponseBody
	public String getUserCombobox(@RequestParam(required = false) Integer deptId) {
		try {
			return userService.getUserCombobox(deptId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/user_getMenu")
	@ResponseBody
	public String getMenu(HttpSession session, String node, String id) {
		try {
			UserDto currentUser = sessionUser(session);
			return userService.getMenu(currentUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	// @RequestMapping(value = "/user_loginInfo")
	// public String loginInfo() {
	// return "loginInfo";
	// }

	@RequestMapping(value = "/user_loginInfo")
	public ModelAndView userInfo(HttpSession session) {
		User user = getSessionUser(session);
		ModelAndView mav = new ModelAndView("loginInfo");
		mav.addObject("user", user);
		return mav;
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
