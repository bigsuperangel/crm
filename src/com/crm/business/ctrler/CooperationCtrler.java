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

import com.crm.business.service.ICooperationService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Cooperation;
import com.crm.model.User;

/**
 * 合作管理相关
 * 
 * @author wukh`
 * @2015-1-28
 */
@Controller
@RequestMapping("/crm")
public class CooperationCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(CooperationCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("cooperationService")
	private ICooperationService cooperationService;

	/**
	 * 发起一个合作
	 * 
	 * @param customer
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cooperation_start", method = RequestMethod.POST)
	@ResponseBody
	public String start(Cooperation cooperation, HttpSession session) {
		try {
			User user = getSessionUser(session);
			cooperationService.startCooperation(cooperation, user);
			return toJson(JsonMsgFactory.successMsg());
		}catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 查询一个合作明细
	 * 
	 * @param cooperationId
	 *            合作ID
	 * @return
	 */
	@RequestMapping(value = "/cooperation_query", method = RequestMethod.GET)
	@ResponseBody
	public String query(int cooperationId) {
		try {
			Cooperation cooperation = cooperationService
					.queryCooperation(cooperationId);
			return cooperation.getJson();
		}catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 查询当前需要审批的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/cooperation_query_unaudit", method = RequestMethod.GET)
	@ResponseBody
	public String queryUnauditList(int page, int limit,
			@RequestParam(required = false) Integer cooperateType,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer status, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Cooperation> list = cooperationService
					.queryUnauditCooperationList(page, limit, cooperateType,
							customerName, status, user);
			return WriteGridUtil.writeGrid(list);
		}catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 查询自己发起的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/cooperation_query_submited", method = RequestMethod.GET)
	@ResponseBody
	public String querySubmitedList(int page, int limit,
			@RequestParam(required = false) Integer cooperateType,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer status, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Cooperation> list = cooperationService
					.querySubmitedCooperationList(page, limit, cooperateType,
							customerName, status, user);
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
	 * 查询等待自己接受的合作列表
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/cooperation_query_accept", method = RequestMethod.GET)
	@ResponseBody
	public String queryAcceptList(int page, int limit,
			@RequestParam(required = false) Integer cooperateType,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) Integer status, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Cooperation> list = cooperationService
					.queryAcceptCooperationList(page, limit, cooperateType,
							customerName, status, user);
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
	 * 审批通过.
	 * 
	 * @param cooperation
	 * @param receiverId
	 *            接收者ID. 如果是跨大区合作的第一步审批, 没有接受者的情况下, 这个值为0
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cooperation_audit", method = RequestMethod.POST)
	@ResponseBody
	public String audit(int cooperateId,
			@RequestParam(required = false) Integer receiverId,
			HttpSession session) {
		try {
			User user = getSessionUser(session);
			cooperationService.auditCooperation(cooperateId, receiverId, user);
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
	 * 审批拒绝合作
	 * 
	 * @param cooperation
	 * @param target
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cooperation_refuse", method = RequestMethod.POST)
	@ResponseBody
	public String refuse(int cooperateId, HttpSession session) {
		try {
			User user = getSessionUser(session);
			cooperationService.refuseCooperation(cooperateId, user);
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
	 * 接收者接收/拒绝合作
	 * 
	 * @param cooperation
	 * @param isAccept
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cooperation_accept", method = RequestMethod.POST)
	@ResponseBody
	public String acceptCooperation(int cooperateId, boolean isAccept,
			HttpSession session) {
		try {
			User user = getSessionUser(session);
			cooperationService.acceptCooperation(cooperateId, user, isAccept);
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
