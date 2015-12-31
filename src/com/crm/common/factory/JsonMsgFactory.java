package com.crm.common.factory;

import org.apache.log4j.Logger;

import com.crm.common.pojo.JsonMsg;
import com.crm.core.base.CrmException;

/**
 * JsonMsg工厂类 
 * @author wengx
 */
public class JsonMsgFactory {

	private static final boolean SUCCESS = true;
	private static final boolean FAIL = false;
	private static final String MSG_CTRL_SUCCESS = "操作成功！";
	private static final String MSG_CTRL_FAIL = "操作失败！";
	private static final String MSG_TIME_OUT = "服务器繁忙或超时,请尝试重新登录!";
	private static final String MSG_NO_PERMISSIONS = "无操作权限!";
	private static final String MSG_NO_LOGIN = "请先登录,再进行操作!";
	private static final String MSG_ERROR_LOGIN ="用户名或密码错误,请重新输入!";

	private JsonMsgFactory() {

	}
	
	/**
	 * 返回登陆失败的JsonMsg
	 * @return
	 */
	public static JsonMsg loginErrorMsg(){
		return new JsonMsg(FAIL, MSG_ERROR_LOGIN);
	}
	
	/**
	 * 返回没有登陆JsonMsg
	 * @return
	 */
	public static JsonMsg loginFailMsg(){
		return new JsonMsg(FAIL, MSG_NO_LOGIN);
	}
	
	/**
	 * 返回无权限JsonMsg
	 * @return
	 */
	public static JsonMsg noPermissionsMsg(){
		return new JsonMsg(FAIL, MSG_NO_PERMISSIONS);
	}

	/**
	 * 返回操作成功JsonMsg
	 * @return
	 */
	public static JsonMsg successMsg() {
		return new JsonMsg(SUCCESS, MSG_CTRL_SUCCESS);
	}

	/**
	 * 返回操作失败JsonMsg
	 * @return
	 */
	public static JsonMsg failMsg() {
		return new JsonMsg(FAIL, MSG_CTRL_FAIL);
	}
	
	public static JsonMsg failMsg(Logger log,CrmException e) {
		log.error(e.getMessage(),e);
		return new JsonMsg(FAIL, MSG_CTRL_FAIL);
	}

	/**
	 * 返回超时JsonMsg
	 * @return
	 */
	public static JsonMsg timeoutMsg() {
		return new JsonMsg(FAIL, MSG_TIME_OUT);
	}

	/**
	 * 返回成功自定义信息JsonMsg
	 * @param msg 自定义信息
	 * @return
	 */
	public static JsonMsg success(String msg) {
		return new JsonMsg(SUCCESS, msg);
	}

	/**
	 * 返回失败自定义信息JsonMsg
	 * @param msg 自定义信息
	 * @return
	 */
	public static JsonMsg fail(String msg) {
		return new JsonMsg(FAIL, msg);
	}
}
