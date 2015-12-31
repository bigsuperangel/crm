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

import com.crm.business.service.IPaymentItemService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.PaymentItem;
import com.crm.model.User;

/**
 * 款项接口
 * 
 * @author wukh
 * @2015-2-26
 */
@Controller
@RequestMapping("/crm")
public class PaymentItemCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(PaymentItemCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("paymentItemService")
	private IPaymentItemService paymentItemService;

	/**
	 * 新增一个款项
	 * 
	 * @param customer
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/payment_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(PaymentItem paymentItem) {
		try {
			paymentItemService.add(paymentItem);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	/**
	 * 查询款项明细
	 * 
	 * @param agreementId
	 * @return
	 */
	@RequestMapping(value = "/payment_query", method = RequestMethod.GET)
	@ResponseBody
	public String query(int paymentItemId) {
		try {
			PaymentItem paymentItem = paymentItemService.query(paymentItemId);
			return paymentItem.getJson();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/payment_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			paymentItemService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/payment_queryList", method = RequestMethod.GET)
	@ResponseBody
	public String queryList(int page, int limit,
			@RequestParam(required = false) Integer cid,
			@RequestParam(required = false) Integer status, HttpSession session) {
		try {
			PageModel<PaymentItem> list = paymentItemService.queryItemList(
					page, limit, cid, status);
			return WriteGridUtil.writeGrid(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/payment_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(PaymentItem paymentItem, HttpSession session) {
		try {
			System.out.println(toJson(paymentItem));
			User user = getSessionUser(session);
			paymentItemService.update(paymentItem, user);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/payment_audit", method = RequestMethod.POST)
	@ResponseBody
	public String audit(PaymentItem paymentItem, HttpSession session) {
		try {
			User user = getSessionUser(session);
			paymentItemService.audit(paymentItem, user);
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
