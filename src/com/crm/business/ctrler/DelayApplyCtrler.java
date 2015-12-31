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
import com.crm.business.service.IDelayApplyService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.DelayApply;
import com.crm.model.User;

/**
 * 延时申请接口
 * 
 * @author wukh
 * @2015-3-3
 */
@Controller
@RequestMapping("/crm")
public class DelayApplyCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(DelayApplyCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("delayApplyService")
	private IDelayApplyService delayApplyService;

	/**
	 * 新增一个延时申请
	 * 
	 * @param customer
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delayApply_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(int customerId, int delayDays, HttpSession session) {
		try {
			User user = getSessionUser(session);
			delayApplyService.addDelayApply(customerId, delayDays, user);
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
	 * 查看我发起的延时申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param audit
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delayApply_queryMyAuditList", method = RequestMethod.GET)
	@ResponseBody
	public String queryMyAuditList(int page, int limit,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer audit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<DelayApply> list = delayApplyService.queryMyAuditList(
					page, limit, startTime, endTime, customerName, audit, user);
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
	 * 查看需要审批的申请列表
	 * 
	 * @param page
	 * @param limit
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delayApply_queryUnAuditList", method = RequestMethod.GET)
	@ResponseBody
	public String queryUnAuditList(int page, int limit,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String customerName,
			HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<DelayApply> list = delayApplyService.queryUnAuditList(
					page, limit, startTime, endTime, customerName, user);
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
	 * 审批申请
	 * 
	 * @param delayApply
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delayApply_audit", method = RequestMethod.POST)
	@ResponseBody
	public String auditApply(int delayApplyId, int audit, HttpSession session) {
		try {
			User user = getSessionUser(session);
			delayApplyService.audit(delayApplyId, audit, user);
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
	 * 主动撤回申请
	 * 
	 * @param delayApply
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delayApply_recall", method = RequestMethod.POST)
	@ResponseBody
	public String recallApply(int delayApplyId, HttpSession session) {
		try {
			User user = getSessionUser(session);
			delayApplyService.recall(delayApplyId, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

}
