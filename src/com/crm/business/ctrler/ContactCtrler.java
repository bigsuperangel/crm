package com.crm.business.ctrler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.business.service.IContactService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Contact;

/**
 * 联系记录管理相关
 * 
 * @author wukh
 * @2015-1-24
 */
@Controller
@RequestMapping("/crm")
public class ContactCtrler extends BaseCtrler {
	
	private static final Logger log = Logger.getLogger(ContactCtrler.class
			.getSimpleName());
	
	@Autowired
	@Qualifier("contactService")
	private IContactService contactService;

	/**
	 * 添加联系记录
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/contact_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Contact contact) {
		try {
			if (contact != null) {
				contactService.addContact(contact);
			}
			return toJson(JsonMsgFactory.successMsg());
		}  catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/contact_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Contact contact) {
		try {
			contactService.updateContact(contact);
			return toJson(JsonMsgFactory.successMsg());
		} catch (CrmException e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail(e.getMessage()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/contact_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			contactService.deleteContacts(ids);
			return toJson(JsonMsgFactory.successMsg());
		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/contact_getComboboxList")
	@ResponseBody
	public String getComboboxList() {
		try {
			return contactService.getComboboxList();
		} catch (Exception e) {
			e.printStackTrace();
			return toJson(JsonMsgFactory.failMsg());
		}
	}

	@RequestMapping(value = "/contact_getList")
	@ResponseBody
	public String getList(int page, int limit, int cid, String startDate,
			String endDate) {
		try {
			PageModel<Contact> list = contactService.queryContactList(page,
					limit, cid, startDate, endDate);
			return WriteGridUtil.writeGrid(list);
		}  catch (Exception e) {
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
