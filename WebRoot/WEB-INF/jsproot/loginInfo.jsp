
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div align="right" style="color: black; font-size: 14px; color: white">
	您好，<span id="userName"></span>欢迎登录！您是&nbsp;&nbsp;<span id="roleName"></span>[<a
		href="user_logout" target="_parent"
		style="color: white; text-decoration: none">退出</a>]
</div>

<script type="text/javascript">
var u = ${user.getJson()};
if(!u){
	Ext.Msg.alert("错误信息", '用户登陆超时');
    top.location = '../login.jsp';  
}
parent.init(u); //调用父类方法index.html
document.getElementById("userName").innerHTML=u.realName;
document.getElementById("roleName").innerHTML=u.roleName;
</script>

