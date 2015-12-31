package com.crm.business.ctrler;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.crm.business.service.IAgreementService;
import com.crm.business.service.IAttachmentService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Agreement;
import com.crm.model.AgreementDetail;
import com.crm.model.Attachment;
import com.crm.model.User;

/**
 * 合同管理接口
 * 
 * @author wukh
 * @2015-1-28
 */
@Controller
@RequestMapping("/crm")
public class AgreementCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(AgreementCtrler.class
			.getSimpleName());

	@Autowired
	@Qualifier("agreementService")
	private IAgreementService agreementService;

	@Autowired
	@Qualifier("attachmentService")
	private IAttachmentService attachmentService;

	/**
	 * 新增一个合同
	 * 
	 * @param customer
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/agreement_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Agreement agreement, HttpSession session) {
		try {
			User user = getSessionUser(session);
			agreementService.addAgreement(agreement, user);
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
	 * 查询合同明细
	 * 
	 * @param agreementId
	 * @return
	 */
	@RequestMapping(value = "/agreement_query", method = RequestMethod.GET)
	@ResponseBody
	public String query(int agreementId) {
		try {
			AgreementDetail detail = agreementService
					.queryAgreement(agreementId);
			return detail.getJson();
		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/agreement_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids,
			HttpSession session) {
		try {
			User user = getSessionUser(session);
			agreementService.deleteAgreement(ids, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/agreement_queryList", method = RequestMethod.GET)
	@ResponseBody
	public String queryList(int page, int limit,
			@RequestParam(required = false) Integer customerId,
			@RequestParam(required = false) String agreementNo,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) Integer status, HttpSession session) {
		try {
			User user = getSessionUser(session);
			PageModel<Agreement> list = agreementService.queryAgreementList(
					page, limit, customerId, agreementNo, startTime, endTime,
					status, user);
			return WriteGridUtil.writeGrid(list);
		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/agreement_update_status", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatus(int agreementId, int status, HttpSession session) {
		try {
			User user = getSessionUser(session);
			agreementService.updateAgreementStatus(agreementId, status, user);
			return toJson(JsonMsgFactory.successMsg());
		}  catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/agreement_update_payment_status", method = RequestMethod.POST)
	@ResponseBody
	public String updatePaymentStatus(int agreementId,
			@RequestParam(required = false) String paymentStatus, int status,
			HttpSession session) {
		try {
			User user = getSessionUser(session);
			agreementService.updateAgreementPaymentStatus(agreementId, status,
					paymentStatus, user);
			return toJson(JsonMsgFactory.successMsg());
		}  catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/agreement_sign", method = RequestMethod.POST)
	@ResponseBody
	public String signAgreement(int agreementId, String mySigner,
			String customerSigner, HttpSession session) {
		try {
			User user = getSessionUser(session);
			agreementService.signAgreement(agreementId, mySigner,
					customerSigner, user);
			return toJson(JsonMsgFactory.successMsg());
		}  catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest request) {
		try {

			int agreementId = Integer.parseInt(request.getParameter("ownerId"));
			int type = Integer.parseInt(request.getParameter("type"));
			String name = request.getParameter("name");
			String des = request.getParameter("description");
			User user = getSessionUser(request.getSession());
			attachmentService.addAttachment(request, agreementId, type, name,
					des, user);
			return toJson(JsonMsgFactory.successMsg());

		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String filedelete(int attachmentId, HttpSession session) {
		try {

			int[] ids = new int[] { attachmentId };
			User user = getSessionUser(session);
			attachmentService.deleteAttachments(ids, user);
			return toJson(JsonMsgFactory.successMsg());

		}  catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/attachment_queryList", method = RequestMethod.POST)
	@ResponseBody
	public String queryAttachmentList(int ownerId, int type) {
		try {
			List<Attachment> attachmentList = attachmentService
					.queryAttachmentList(ownerId, type);
			StringBuilder json = new StringBuilder();
			json.append("[");
			if (attachmentList != null && attachmentList.size() > 0) {
				for (Attachment attachment : attachmentList) {
					json.append(attachment.getJson());
					json.append(",");
				}
				json.setLength(json.length() - 1);
			}
			json.append("]");
			return json.toString();
		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/showPhoto", method = RequestMethod.GET)
	public ModelAndView showPhoto(int ownerId, int type) {
		List<Attachment> attachmentList = attachmentService
				.queryAttachmentList(ownerId, type);

		// List<String> list = new ArrayList<String>();
		// for (Attachment attachment : attachmentList) {
		// list.add(attachment.getUrl());
		// }
		ModelAndView mav = new ModelAndView("showPhoto");
		mav.addObject("photo", attachmentList);
		return mav;
	}

	// /**
	// * 测试图片
	// *
	// * @param ownerId
	// * @return
	// */
	// @RequestMapping(value = "/showPhoto", method = RequestMethod.GET)
	// public ModelAndView showPhoto(
	// @RequestParam(required = false) Integer ownerId) {
	// List<String> list = new ArrayList<String>();
	// list.add("ext4.1/lightbox/images/1.jpg");
	// list.add("ext4.1/lightbox/images/2.jpg");
	// list.add("ext4.1/lightbox/images/3.jpg");
	// ModelAndView mav = new ModelAndView("showPhoto");
	// mav.addObject("photo", list);
	// return mav;
	// }
	
	@Override
	protected String handlerException(Exception e) {
		e.printStackTrace();
		System.err.println("[handlerException:MemberController]异常类型："
				+ e.getClass().getSimpleName());
		System.err.println(e.getMessage());
		return "系统异常";
	}
}
