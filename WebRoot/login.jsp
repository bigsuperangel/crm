<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<base href="<%=basePath%>">
<TITLE>欢迎登陆crm客户关系管理系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="<%=path%>/images/login.css" type=text/css rel=stylesheet>
<script>
	window.onload = function(){
		var error=	getParameter('error');
		if(error!=-1){
			alert(decodeURI(error));
		}
	}
	function getParameter(name){
	   var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href); 
	   if (!results) { return "-1"; }
	   return results[1]||"-1";
	}
	
	function changeCode() { 
		var myimg = document.getElementById("code"); 
		now = new Date(); 
		myimg.src="CertPic.jsp?code="+now.getTime();
	}
	
	function checkForm(){
		if(document.getElementById("username").value == ""){
			alert("用户名不能为空！");
			return false;
		} 
		if(document.getElementById("password").value == ""){
			alert("密码不能为空！");
			return false;
		}
		if(document.getElementById("cretCode").value == ""){
			alert("验证码不能为空！");
			return false;
		}
		return true;
	}
	function focuseOn(){
		document.getElementById("username").focus();
	}
	
	//出iframe的页面设置
	if(window !=top){
		top.location.href=location.href;
	}
</script>
</HEAD>
<BODY onload="focuseOn();">
<form action="crm/login" method="post" onsubmit="return checkForm();">
  <table width="800" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:-12px">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="496" id="main"><table width="639" height="264" border="0" align="center">
        <tr>
          <td width="234" height="96">&nbsp;</td>
          <td width="389">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center">
          <div id="dv"><span>用户名：</span><INPUT class="textbox" type="text" id="username" name="username" value="admin"></div>
          <div id="dv"><span>密　码：</span><INPUT class="textbox" type="password" id="password" name="password" value="admin"></div>
          <div id="dvs" align="left"><span>验证码：</span><input class="textbox" id="cretCode" name="certCode" type="text" id="chknumber" maxlength="4" value="暂不输入" style="width:55px; height:20px"/>
				<img id="code" src="CertPic.jsp" width="60px" height="20px" align="top" alt="看不清，点我" onclick="javascript:changeCode()"/>
          </div>
          <div align="right" id="dv"><input type="submit" id="btLogin" value="登录"></div>
          </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td height="104" id="root">&nbsp;</td>
    </tr>
  </table>
</form>
</BODY>
</HTML>