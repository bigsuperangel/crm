package com.crm.common.tools;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



public class ResponseTool {
	
	private final static Logger log = Logger.getLogger(ResponseTool.class);
	
	public static String toJson(Object obj){
		GsonBuilder gb = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
		gb.registerTypeHierarchyAdapter(HibernateProxy.class,new JsonSerializer<HibernateProxy>(){
			public JsonElement serialize(HibernateProxy src,Type typeOfSrc, JsonSerializationContext context) {
				return null;
			}
		});
		gb.registerTypeHierarchyAdapter(PersistentList.class,new JsonSerializer<PersistentList>(){
			public JsonElement serialize(PersistentList src,Type typeOfSrc, JsonSerializationContext context) {
				return null;
			}
		});
		String json = gb.serializeNulls().create().toJson(obj);
		System.out.println("Json:"+json);
		return json;
	}
	
	public static void responseJsonFormStr(String str,HttpServletRequest request, HttpServletResponse response){
		String dataType = "text/json;charset=UTF-8";
		responseJsonFormStr(str, dataType, request, response);
	}
	
	public static void responseJson(Object obj,HttpServletRequest request, HttpServletResponse response){
		String jsonArray =toJson(obj);
		String json = "";
		json = jsonArray.toString();
		if (json.indexOf("[")==0) {
			json = json.substring(1,json.length()-1);
		}
		responseJsonFormStr(json, request, response);
	}
	
	
	public static void print(String message, OutputStream os) throws Exception {
		OutputStreamWriter out = new OutputStreamWriter(os, "utf-8");
		out.write(message);
		out.flush();
		out.close();
	}
	
	/**
	 * 将json数据传到前台
	 */
	 private static void responseJsonFormStr(String str,String dataType,HttpServletRequest request, HttpServletResponse response){
			if (dataType.length()<=0) {
				dataType = "text/json;charset=UTF-8";
			}
			String cb = request.getParameter("callback");
			if (cb!=null) {
				StringBuffer sb = new StringBuffer(cb);
				sb.append("(");
				sb.append(str);
				sb.append(")");
				str = sb.toString();				
			}
			log.info("手机客户端请求返回值　："+str);
			response.setContentType(dataType);
			try {
				response.addHeader("Access-Control-Allow-Origin", "*");
				response.getWriter().write(str);
			} catch (IOException e) {
				log.error("responseJson failed:",e);
			}
	 }
	
	public static String handlerExceptionString(Exception e ,Logger logger){
		logger.info("[handlerException:MemberController]异常类型："+e.getClass().getSimpleName());
		logger.error(e.getMessage(), e);
		return "系统异常";
	}
	
}

