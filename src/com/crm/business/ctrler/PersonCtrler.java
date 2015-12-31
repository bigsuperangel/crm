package com.crm.business.ctrler;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.business.service.IPersonService;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.utils.WriteGridUtil;
import com.crm.core.base.BaseCtrler;
import com.crm.model.Person;

/**
 * 联系人管理相关
 * 
 * @author wukh
 * @2015-1-24
 */
@Controller
@RequestMapping("/crm")
public class PersonCtrler extends BaseCtrler {
	private static final Logger log = Logger.getLogger(PersonCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("personService")
	private IPersonService personService;

	/**
	 * 添加联系人
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/person_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Person person) {
		try {
			if (person != null) {
				personService.addPerson(person);
			}
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/person_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Person person) {
		try {
			personService.updatePerson(person);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/person_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			personService.deletePersons(ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/person_getComboboxList")
	@ResponseBody
	public String getComboboxList() {
		try {
			return personService.getComboboxList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/person_showList")
	@ResponseBody
	public String showList(int page, int limit, int cid) {
		try {
			PageModel<Person> list = personService.queryPersonList(page, limit,
					cid);
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
}
