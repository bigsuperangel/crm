package com.crm.core.base;

import java.lang.reflect.Type;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.common.PageModel;
import com.crm.model.User;
import com.crm.model.dto.UserDto;
import com.crm.sys.dao.UserDao;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public abstract class BaseCtrler {
	@Autowired
	@Qualifier("userDao")
	protected UserDao userDao;
	
	/**
	 * 默认处理异常. 子类需要另行处理的时候再覆盖
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	protected String handlerException(Exception e){
		e.printStackTrace();
		System.err.println("[handlerException:MemberController]异常类型："
				+ e.getClass().getSimpleName());
		System.err.println(e.getMessage());
		return "系统异常";
	}

	protected String toJson(Object obj) {
		GsonBuilder gb = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
		gb.registerTypeHierarchyAdapter(HibernateProxy.class,
				new JsonSerializer<HibernateProxy>() {
					public JsonElement serialize(HibernateProxy src,
							Type typeOfSrc, JsonSerializationContext context) {
						return null;
					}
				});
		gb.registerTypeHierarchyAdapter(PersistentList.class,
				new JsonSerializer<PersistentList>() {
					public JsonElement serialize(PersistentList src,
							Type typeOfSrc, JsonSerializationContext context) {
						return null;
					}
				});
		String json = gb.serializeNulls().create().toJson(obj);
		System.out.println("Json:" + json);
		return json;
	}

	/**
	 * 获取session中后台用户
	 * 
	 * @param session
	 *            session会话
	 * @return 登录用户
	 */
	protected UserDto sessionUser(HttpSession session) {
		return (UserDto) session.getAttribute("user");
	}
	
	/**
	 * 获取User对象
	 * @param session
	 * @return
	 */
	protected User getSessionUser(HttpSession session){
		User user = sessionUser(session).getUser();
		user = userDao.getObject(user.getId());
		return user;
	}
	
	/**
	 * 返回成功JSON
	 * 
	 * @return
	 */
	protected String createSuccessString() {
		return "{success:true}";
	}

	/**
	 * 返回失败JSON
	 * 
	 * @return
	 */
	protected String createFailString() {
		return "{success:false}";
	}
	
	/**
	 * 带信息的成功JSON
	 * @param msg
	 * @return
	 */
	protected String createSuccessString(String msg){
		return "{success:true,msg:\""+msg+"\"}";
	}
	
	/**
	 * 带信息的失败JSON
	 * @param msg
	 * @return
	 */
	protected String createFailString(String msg){
		return "{success:false,msg:\""+msg+"\"}";
	}
	
}
