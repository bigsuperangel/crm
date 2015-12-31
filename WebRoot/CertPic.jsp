<%@page contentType="image/jpeg"%>
<%@page language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="image" scope="page" class="com.ibeifeng.crm.securityCode.MakeCertPic"/>
<%
	String str = image.getCertPic(response.getOutputStream());
	session.setAttribute("certCode",str);
	out.clear();  
	out = pageContext.pushBody();  
%>
