<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>图片查看</title>
<link rel="stylesheet" href="${ctx}/ext4.1/lightbox/css/img.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/ext4.1/lightbox/css/lightbox.css" type="text/css" />
<script type="text/javascript" src="${ctx}/ext4.1/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/ext4.1/ux/Lightbox.js"></script>
<script>
            Ext.ux.Lightbox.register('a[rel^=lightbox]');
            Ext.ux.Lightbox.register('a.lb-flower', true); // true to show them as a set
            
            function delPhoto(id){
	            Ext.Ajax.request({
				    url: 'delete',
				    params: {
				        attachmentId: id
				    },
				    success: function(response){
				    	var json = Ext.decode(response.responseText);
	                    if (json.success) {
	                    	MsgTip.msg('系统提示','删除成功！');
	                    	window.location.reload();
	                    } else {
	                        Ext.Msg.alert("提示","删除失败！");
	                    }
				    	
				    },
				    failure: function(response){
				    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
				    }
				});
            }
</script>

<style>
    .thumbnail {
    	padding: 4px;
    	background-color: #e6e6e0;
    	border: 1px solid #d6d6d0;
    	float: left;
    	margin-right: 10px;
    	margin-bottom: 10px;
	}
	.center{
		width:1000px;
		margin:0 auto;
	}
</style>


</head>
<body>
	<div id="content" class="center" >
	  <!-- <h2 style="clear: both;">图片列表</h2> -->
	  <c:forEach items="${photo}" var="a" >
		  <div class="thumbnail">
			<a href="${ctx}/${a.url}" class="lb-flower" title="${a.name}">
			<img src="${ctx}/${a.url }" width="120" height="92" /></a>
			<br><a href="javascript:void(0)" onclick="delPhoto(${a.id})">删除</a>
		 </div>
	</c:forEach>
	<h3 style="clear: both;">点击图片可查看大图</h3>
</div>
</body>
</html>
