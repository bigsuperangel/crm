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

import com.crm.business.ctrler.ContactCtrler;
import com.crm.common.PageModel;
import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.pojo.PageResult;
import com.crm.common.pojo.QueryCondition;
import com.crm.common.utils.Constant;
import com.crm.core.base.BaseCtrler;
import com.crm.core.base.CrmException;
import com.crm.model.Dept;
import com.crm.model.User;
import com.crm.sys.service.IDeptService;

@Controller
@RequestMapping("/crm")
public class DeptCtrler extends BaseCtrler {
	
	private static final Logger log = Logger.getLogger(DeptCtrler.class
			.getSimpleName());
	@Autowired
	@Qualifier("deptService")
	private IDeptService deptService;

	/**
	 * 添加公司
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/dept_add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Dept dept) {
		try {
			if (dept != null) {
				dept.setIsValid(1);
				deptService.add(dept);
			}
			return "{success:true,dept:"+dept.getJson()+"}";
//			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/dept_update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Dept dept) {
		try {
			deptService.update(dept);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/dept_delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "ids") Integer[] ids) {
		try {
			deptService.delete(ids);
			return toJson(JsonMsgFactory.successMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/dept_move", method = RequestMethod.POST)
	@ResponseBody
	public String move(int pid,int did) {
		try {
			deptService.move(pid, did);
			return toJson(JsonMsgFactory.successMsg());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/dept_getTreeCombobox")
	@ResponseBody
	public String getTreeCombobox() {
		try {
			return deptService.getTreeCombobox();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}

	@RequestMapping(value = "/dept_getDeptTree")
	@ResponseBody
	public String getDeptTree() {
		try {
			return deptService.getDeptTree();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/dept_getMyDeptTree")
	@ResponseBody
	public String getMyDeptTree(HttpSession session){
		try {
			User user =getSessionUser(session);
			return deptService.getDeptTree(1, user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/dept_getCrossAreaTree")
	@ResponseBody
	public String getCrossAreaTree(HttpSession session){
		try {
			User user =getSessionUser(session);
			return deptService.getDeptTree(2, user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return toJson(JsonMsgFactory.fail("网络连接错误"));
		}
	}
	
	@RequestMapping(value = "/dept_getCrossBigAreaTree")
	@ResponseBody
	public String getCrossBigAreaTree(HttpSession session){
		try {
			User user =getSessionUser(session);
			return deptService.getDeptTree(3, user);
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
