package com.crm.business.ctrler;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.business.service.ICustomerService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Apply;
import com.crm.model.Customer;
import com.crm.model.User;

/**
 * 客户管理相关
 * 
 * @author wukh
 * @2015-1-24
 */
@Controller
@RequestMapping("/crm")
public class CustomerCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(CustomerCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("customerService")
	private ICustomerService customerService;

	/**
	 * 添加客户
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/customer_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Customer customer, HttpSession session) {
		try {
			if (customer != null) {
				User user = getSessionUser(session);
				customerService.addCustomer(customer, user);
			}
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/customer_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Customer customer) {
		try {
			customerService.updateCustomer(customer);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/customer_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			customerService.deleteCustomers(ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/customer_getComboboxList")
	@ResponseBody
	public String getComboboxList() {
		try {
			return customerService.getComboboxList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/customer_getList")
	@ResponseBody
	public String getList(int page, int limit) {
		try {
			PageModel<Customer> list = customerService.queryCustomerList(page,
					limit);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 查看公海客户
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/customer_getPublicList")
	@ResponseBody
	public String getPublicList(int page, int limit,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime, HttpSession session) {
		try {
			User user = getSessionUser(session);
			int deptId = user.getDept().getId();
			PageModel<Customer> list = customerService.queryPublicCustomerList(
					page, limit, customerName, brandName, startTime, endTime,
					deptId);
			return WriteGridUtil.writeGrid(list);
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

	/**
	 * 获取审核列表
	 * 
	 * @param page
	 * @param limit
	 * @param audit
	 *            审核状态. 0未审核 1已审核 －1驳回 null全部
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_getAuditList")
	@ResponseBody
	protected String getAuditList(int page, int limit,
			@RequestParam(required = false) Integer audit,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Customer> list = customerService.getAuditList(page,
					limit, customerName, brandName, startTime, endTime, audit,
					user);
			return WriteGridUtil.writeGrid(list);
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 获取自己的当前客户
	 * 
	 * @param page
	 * @param limit
	 * @param customerName
	 *            客户名称
	 * @param brandName
	 *            品牌名称
	 * @param startTime
	 *            创建时间查询
	 * @param endTime
	 * @param audit
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_getMyCustomerList")
	@ResponseBody
	protected String getMyCustomerList(int page, int limit,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) Integer audit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Customer> list = customerService.getMyCustomerList(page,
					limit, customerName, brandName, startTime, endTime, audit,
					user);
			return WriteGridUtil.writeGrid(list);
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 审核用户
	 * 
	 * @param customer
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_audit")
	@ResponseBody
	public String audit(int customerId, boolean isAudit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			customerService.auditCustomer(customerId, isAudit, user);
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
	 * 分配用户到其他业务员
	 * 
	 * @param customerId
	 * @param handlerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_move")
	@ResponseBody
	public String move(int customerId, int handlerId, HttpSession session) {
		try {
			User user = getSessionUser(session);
			customerService.moveCustomer(customerId, handlerId, user);
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
	 * 发起获取公海客户的申请
	 * 
	 * @param customerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_pickup")
	@ResponseBody
	public String pickup(int customerId, HttpSession session) {
		try {
			User user = getSessionUser(session);
			customerService.pickCustomer(customerId, user);
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
	 * 审核公海客户的申请
	 * 
	 * @param applyId
	 * @param isPass
	 *            是否通过
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_auditPickup")
	@ResponseBody
	public String auditPickup(int applyId, boolean isPass, HttpSession session) {
		try {
			User user = getSessionUser(session);
			customerService.auditPickup(applyId, isPass, user);
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
	 * 查看公海客户申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_getApplyList")
	@ResponseBody
	public String getApplyList(int page, int limit,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer audit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Apply> list = customerService.getApplyList(page, limit,
					startTime, endTime, customerName, audit, user);
			return WriteGridUtil.writeGrid(list);
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 获取自己提交过的公海客户申请
	 * 
	 * @param page
	 * @param limit
	 * @param audit
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/customer_getMyApplyList")
	@ResponseBody
	public String getMyApplyList(int page, int limit,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer audit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Apply> list = customerService.getMyApplyList(page, limit,
					startTime, endTime, customerName, audit, user);
			return WriteGridUtil.writeGrid(list);
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/customer_checkConflict")
	@ResponseBody
	public String checkConflict(@RequestParam(required = false) String name,
			@RequestParam(required = false) String brandName) {
		try {
			if (customerService.checkConflict(name, brandName)) {
				return toJson(JsonMsgFactory.successMsg());
			} else {
				return toJson(JsonMsgFactory.failMsg());
			}
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
}
