Ext.define('crm.controller.customer.AuditCustomerController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'auditcustomerlist button[id=audit]':{
				click:function(btn){
					var store = btn.up('auditcustomerlist').store;
					var data = btn.up('auditcustomerlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					Ext.Ajax.request({
					    url: 'customer_audit',
					    params: {
					        customerId: data[0].get('id'),
					        isAudit : true
					    },
					    success: function(response){
					    	 var json = Ext.decode(response.responseText);
                   			 if (json.success) {
                   				 MsgTip.msg('系统提示', '审核成功');
					    		store.load({params:{audit:0}});
                   			 }else{
                   				 MsgTip.msg('系统提示', '审核失败');
                   			 }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'auditcustomerlist button[id=refuse]':{
				click:function(btn){
					var store = btn.up('auditcustomerlist').store;
					var data = btn.up('auditcustomerlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					Ext.Ajax.request({
					    url: 'customer_audit',
					    params: {
					        customerId: data[0].get('id'),
					        isAudit : false
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
                   			 if (json.success) {
                   				MsgTip.msg('系统提示', '操作成功');
					    		store.load({params:{audit:0}});
                   			 }else{
                   				 MsgTip.msg('系统提示', '审核失败');
                   			 }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'auditcustomerlist button[id=search]':{
				click:function(btn){
					searchCustomerFun(btn,'auditcustomerlist',2);
				}
			},
			'auditcustomerlist button[id=clearAll]':{
				click:function(btn){
				    clearCustomerFun(btn,'auditcustomerlist');
				}
			}
		});
	},
	views : [
	    'customer.AuditCustomerList'
	],
	stores : [
	    'customer.AuditCustomerStore'
	],
	models : [
	    'customer.CustomerModel'
	]
});
