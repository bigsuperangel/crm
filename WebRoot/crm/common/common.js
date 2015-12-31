/**
 * 公共js
 */
// 模块名称注册
if (ww == null)
	var ww = {};
if (ww.common == null)
	ww.common = {};

var url = document.location.pathname;
var itmp = url.indexOf("/", 1);
var webpath = itmp < 0 ? url : url.substr(0, itmp);
if (webpath.indexOf('/') == -1) {
	webpath = '/' + webpath;
}
ww.common = {
	hostUrl:window.location.protocol + '//' + window.location.host + webpath, 
	pagesize:10,
	//客户模块设置
	customerLabelWidth:80,
	personLabelWidth:60,
	contactLabelWidth:80,
	//转换MODEL空值
	nvlModel : function(obj){
		return obj=='null' ? '':obj;
	},
	formatDate : function(value) {
		if (null != value) {
			return Ext.Date.format(new Date(value), 'Y-m-d');
		} else {
			return '';
		}
	},
	//设置checkboxgroup值
	setCheckboxgroup:function(str){
		var arr = [];
		var temp = ww.common.nvlModel(str);
		if(temp.indexOf(',')>0){
			return temp.split(',');
		}else{
			arr.push(temp);
		}
		return arr;
	},
	//获取checkboxgroup值上传表单
	getCheckboxgroup:function(arr){
	    return arr.length>1 ? arr.join(','):arr.toString();
	},
	callback: function(records, operation, success) {
        if(!success){
        	MsgTip.msg('系统提示','用户登陆超时！');
        	window.location = '../../login.jsp';
        	window.location.reload();
        }
    },
    failure: function(form, action) {
		if (action.failureType === "connect") {
			Ext.Msg.alert('错误信息', '状态:' + action.response.status + ': '
					+ action.response.statusText);
			return;
		}
		if (action.result) {
			if (action.result.msg)
				Ext.Msg.alert('错误信息', action.result.msg);
		}else{
			Ext.Msg.alert('错误信息', '操作失败');
		}
	},
	ajaxSuccess: function(response,store){
    	var json = Ext.decode(response.responseText);
    	if(json.success){
    		MsgTip.msg('系统提示', '操作成功');
    		if(store){
    			store.load();
    		}
    	}else{
    		MsgTip.msg('系统提示', '操作失败');
    	}
    },
    ProxyException : function(proxy, response, opts) {
    	var responseText = response.responseText;  
        if (responseText  
                && responseText.indexOf("SESSION_TIMEOUT_ERROR") > -1) {  
        	Ext.Msg.alert("错误信息", '用户登陆超时');
            top.location = '../login.jsp';  
            return;
        } else if(response.status=='404'){
				Ext.Msg.alert("错误信息", '加载失败');
        }else{ //请求异常
        	    var json = Ext.decode(response.responseText);
		    	if(json.success==false){
		    		if(json.msg){
		    			Ext.Msg.alert("错误信息", json.msg);
		    		}else{
		    			Ext.Msg.alert("错误信息", '加载失败');
		    		}
		    	}
        }
	},
	checkUserSessionStatus: function checkUserSessionStatus(conn,response,options){   
	    //Ext重新封装了response对象   
//	    if(typeof response.getResponseHeader.sessionstatus != 'undefined'){   
//	        //发现请求超时，退出处理代码...   
//	     }   
		console.info(response);
		var responseText = response.responseText;  
		//登陆超时
        if (responseText  && responseText.indexOf("SESSION_TIMEOUT_ERROR") > -1) {  
        	Ext.Msg.alert("错误信息", '用户登陆超时');
            top.location = '../login.jsp';  
            return;
        } else if(response.status=='404'){ //请求失败
				Ext.Msg.alert("错误信息", '加载失败');
        } else{ //请求异常
//        	    var json = Ext.decode(response.responseText);
//		    	if(json.success==false){
//		    		if(json.msg){
//		    			Ext.Msg.alert("错误信息", json.msg);
//		    		}else{
//		    			Ext.Msg.alert("错误信息", '加载失败');
//		    		}
//		    	}
        }
	}   
};

