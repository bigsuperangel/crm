package com.crm.common.tools;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;


/**
 * request工具
 * 
 * 
 */
public class RequestTool {
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return 请求路径
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		if(request.getQueryString()!=null)
			requestPath = requestPath + "?"	+ request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}
	
	/**
	 * 获取WEB路径 
	 * @param request
	 * @return eg:http://localhost:8080/crm
	 */
	public static String getWebPath(HttpServletRequest request) {
		String requestPath = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		return requestPath.substring(0,requestPath.indexOf(contextPath)+contextPath.length());
	}
	
	public static void main(String[] args) {
		String path = "http://localhost:8080/crm/crm/user_loginInfo";
		String url = "/crm";
		System.out.println(path.substring(0,path.indexOf(url)+url.length()));
	}
	
	/**
	 * 获得请求IP
	 * 
	 * @param request
	 * @return IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 获得请求的全路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getReuqestAllPath(HttpServletRequest request){
		Map properties = request.getParameterMap();
		return request.getRequestURI()+new Gson().toJson(properties);
	}
}

