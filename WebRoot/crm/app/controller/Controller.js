Ext.define('crm.controller.Controller', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			
		});
	},
	views : [
	    'MainLayout',
	    'Index'
	],
	stores : [
	    
	],
	models : [
	    
	]
});
//启用ajax全局响应
Ext.Ajax.on('requestcomplete',ww.common.checkUserSessionStatus, this);   
//加载默认的主题
var theme = localStorage.getItem('myTheme');
var topTheme = localStorage.getItem('topTheme');
if(theme != null && theme.length>0){
	Ext.util.CSS.swapStyleSheet('them', theme);
}
if(topTheme != null && topTheme.length>0){
	Ext.util.CSS.swapStyleSheet('topTheme', topTheme);
}
//分页，页码
var limit = parseInt((document.documentElement.clientHeight - 200) / 22);
/**
 * btn 按钮
 * clas  对应的对象
 * str  提示文字
 * url  具体url
 * title   展示文字
 * id  发送id
 * 
 */
var delFun = function(btn, clas, str, url, title, id){
	var store = btn.up(clas).store;
	var data = btn.up(clas).getSelectionModel().getSelection();
	if(data.length <= 0){
		MsgTip.msg('系统提示', '您没有选择要删除的'+str);
		return ;
	}
	var titles = [];
	var ids = [];
	Ext.Array.each(data, function(record, index, countriesItSelf) {
    	titles.push(record.get(title));
    	ids.push(record.get(id));
	});
	Ext.MessageBox.confirm('系统提示', '您是否要删一下'+str+'：<span style="color:red"><br>['+titles.join(']<br>[')+']</span><br>删除后不可恢复', function(optional){
		if(optional == 'yes' || optional == '是'){
			Ext.Ajax.request({
			    url: url,
			    params: {
			        ids: ids
			    },
			    success: function(response){
			    	var json = Ext.decode(response.responseText);
                    if (json.success) {
                    	if(btn.up(clas).model != null){
				    		store.load({
				    			params:{
				    				cid: btn.up(clas).model.get('id')
				    			}
				    		});
				    	}else{
				    		store.load();
				    	}
                    } else {
                        Ext.Msg.alert("提示","删除失败！");
                    }
			    	
			    },
			    failure: function(response){
			    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
			    }
			});
		}
	});
}

var updateFun = function(btn, clas, nullAlert, title, xtype, width, height, ch){
	if(!ch){
		ch = -1;
	}
	var store = btn.up(clas).store;
	var data = btn.up(clas).getSelectionModel().getSelection();
	if(data.length <= 0){
		MsgTip.msg('系统提示', nullAlert);
		return ;
	}
	var win = Ext.create('Ext.window.Window',{
		title: title,
		iconCls: btn.iconCls,
		width: width,
		height: height,
		modal: true,
		layout: 'fit',
		items:[{xtype: xtype, model: data[0], store:store, ch:ch, btn: btn}]
	});
	win.show();
	
}

var addFunWithData = function(btn, clas, nullAlert, title, width, height, xtype, ch){
	var store = btn.up(clas).store;
	var data = btn.up(clas).getSelectionModel().getSelection();
	if(nullAlert != null){
		if(data.length <= 0){
			MsgTip.msg('系统提示', nullAlert);
			return ;
		}
	}
	var win = Ext.create('Ext.window.Window',{
		title: title,
		iconCls: btn.iconCls,
		width: width,
		height: height,
		modal: true,
		layout: 'fit',
		items:[{xtype: xtype, model: data[0], store:store, ch:ch}]
	});
	win.show();
}

var addFun = function(btn, clas, title, width, height, xtype){
	var store = btn.up(clas).store;
	var win = Ext.create('Ext.window.Window',{
		title: title,
		width: width,
		height: height,
		iconCls: btn.iconCls,
		modal: true,
		layout: 'fit',
		items:[{xtype:xtype, store: store, btn: btn}]
	});
	win.show();
}

/**
 * 查找合作
 * @param {Object} btn
 * @param {Object} clas
 */
var searchCooperateFun = function(btn, clas){
	var store = btn.up(clas).store;
	var customerName = btn.previousSibling('#customer').getValue();
	var cooperatorType = btn.previousSibling('#cooperatorType').getValue();
	store.removeAll(true);
	store.load({
	    params:{
  			customerName:customerName
  		    ,cooperateType: cooperatorType
  	    }
	});
}

/**
 * 清除合作相关
 * @param {Object} btn
 * @param {Object} clas
 */
var clearCooperateFun = function(btn, clas){
	btn.previousSibling('#customer').setValue();
	btn.previousSibling('#cooperatorType').clearValue();
	btn.previousSibling('#startTime').setValue();
	btn.previousSibling('#endTime').setValue();
}

/**
 * 客户查询条件 
 * @param {Object} btn
 * @param {Object} clas
 * @param {Object} type 1:客户列表 2审核客户列表 3公海 4为空默认客户列表
 */
var searchCustomerFun = function(btn, clas,type){
	var store = btn.up(clas).store;
	var customerName = btn.previousSibling('#customer').getValue();
	var brandName = btn.previousSibling('#brandName').getValue();
	var startTime = btn.previousSibling('#startTime').getValue();
	var endTime = btn.previousSibling('#endTime').getValue();
	if(type==2){
		var audit = 0;
		var auditType = btn.previousSibling('#auditType').getValue();
		if(auditType){
			audit = auditType;
		}
	}
	store.removeAll(true);
	if(type==2){  //审核客户列表
		store.load({
		    params:{
	  			       'customerName':customerName,
	  			       'brandName':brandName,
	  			       'startTime':ww.common.formatDate(startTime),
	  			       'endTime':ww.common.formatDate(endTime),
	  			       'audit':audit
	  	    }
		});
	}else{  //客户列表
		store.load({
		    params:{
	  			       'customerName':customerName,
	  			       'brandName':brandName,
	  			       'startTime':ww.common.formatDate(startTime),
	  			       'endTime':ww.common.formatDate(endTime)
	  	    }
		});
	}
}

/**
 * 清除客户查询条件
 * @param {Object} btn
 * @param {Object} clas
 */
var clearCustomerFun = function(btn, clas){
	btn.previousSibling('#customer').setValue();
	btn.previousSibling('#brandName').setValue();
	btn.previousSibling('#startTime').setValue();
	btn.previousSibling('#endTime').setValue();
	if(btn.previousSibling('#auditType')){
		btn.previousSibling('#auditType').clearValue();
	}
}

var changeState = function(btn, clas, alert, conftitle, url, title, id){
	var store = btn.up(clas).store;
	var data = btn.up(clas).getSelectionModel().getSelection();
	if(data.length <= 0){
		MsgTip.msg('系统提示', alert);
		return ;
	}
	var titles = [];
	var ids = [];
	Ext.Array.each(data, function(record, index, countriesItSelf) {
    	titles.push(record.get(title));
    	ids.push(record.get(id));
	});
	Ext.MessageBox.confirm('系统提示', conftitle+'：<span style="color:red"><br>['+titles.join(']<br>[')+']</span>', function(optional){
		if(optional == 'yes' || optional == '是'){
			Ext.Ajax.request({
			    url: url,
			    params: {
			        ids: ids
			    },
			    success: function(response){
			    	var json = Ext.decode(response.responseText);
                    if (json.success) {
				    	store.load();
                    } else {
                        Ext.Msg.alert("提示","修改失败！");
                    }
			    },
			    failure: function(response){
			    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
			    }
			});
		}
	});
}
