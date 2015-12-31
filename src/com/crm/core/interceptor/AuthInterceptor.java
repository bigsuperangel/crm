package com.crm.core.interceptor;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.crm.common.factory.JsonMsgFactory;
import com.crm.common.tools.RequestTool;
import com.crm.common.tools.ResponseTool;
import com.crm.common.utils.Constant;
import com.crm.model.User;
import com.crm.model.dto.UserDto;
import com.google.gson.Gson;

/**
 * 权限拦截器
 * 
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
		logger.info("[afterCompletion:AuthInterceptor]");
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
		logger.info("[postHandle:AuthInterceptor]");
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestPath = RequestTool.getRequestPath(request);	// 用户访问的资源地址
		String requestServerPath = request.getServletPath();  //不带项目名的资源地址
		String webPath =  RequestTool.getWebPath(request) + "/login.html";
		logger.info("[preHandle:AuthInterceptor]requestPath:"+requestPath);
		String  reuqestAllPathStr = RequestTool.getReuqestAllPath(request);
		if (reuqestAllPathStr.length() < 2048*2) {//太长就不输出来
			logger.info("[###请求IP###]:"+request.getRemoteAddr()+"[#####请求#####]:"+ reuqestAllPathStr+"");
		}
		if ( !Constant.noAuthmap.containsKey(requestServerPath) ) {
			logger.info("[preHandle:AuthInterceptor]开始鉴权");
			HttpSession session = request.getSession(false);
			if ( session == null){ //没有登录
				logger.info("[preHandle:AuthInterceptor]登陆失败");
				checkSession(request, response);
                return false;  
			} else {
				//验证用户是否有权限
				logger.info("[preHandle:AuthInterceptor]开始验证");
				UserDto user = (UserDto) session.getAttribute("user");
				if (user==null || user.getId()<0) {
					logger.info("[preHandle:AuthInterceptor]登陆失败");
					checkSession(request, response);
					return false;
				}
			}
		}
		return true;
	}
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
//		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//			response.setHeader("sessionstatus", "timeout");
//		}
		writer.print("SESSION_TIMEOUT_ERROR");
		writer.print("<script>top.location = '../login.jsp';  </script>");
		IOUtils.closeQuietly(writer);
	}
	/**
	 * 鉴权失败跳转页面
	 */
	private void forward(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("error/authMsg.jsp").forward(request, response);
	}
}
