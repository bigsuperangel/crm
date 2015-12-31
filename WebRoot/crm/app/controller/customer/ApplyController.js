Ext.define('crm.controller.customer.ApplyController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'applylist button[id=audit]':{
				click:function(btn){
					var store = btn.up('applylist').store;
					var data = btn.up('applylist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'customer_auditPickup',
					    params: {
					        applyId: data[0].get('id'),
					        isPass:true
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
					    	if(json.success){
					    		MsgTip.msg('系统提示', '操作成功');
						    	store.load();
					    	}else{
					    		MsgTip.msg('系统提示', '操作失败');
					    	}
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'applylist button[id=refuse]':{
				click:function(btn){
					var store = btn.up('applylist').store;
					var data = btn.up('applylist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'customer_auditPickup',
					    params: {
					        applyId: data[0].get('id'),
					        isPass:false
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
					    	if(json.success){
					    		MsgTip.msg('系统提示', '操作成功');
						    	store.load();
					    	}else{
					    		MsgTip.msg('系统提示', '操作失败');
					    	}
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'applylist button[id=search]':{
				click:function(btn){
					var customerName = btn.previousSibling('#customerName').getValue();
					var store = btn.up('applylist').store;
					store.removeAll(true);
			    	store.load({
			    		params:{
		  			       'customerName':customerName,
		  			       'startTime':ww.common.formatDate(startTime),
		  			       'endTime':ww.common.formatDate(endTime)
		  			    }
			    	});
				}
			},
			'applylist button[id=clearAll]':{
				click:function(btn){
				    btn.previousSibling('#customerName').setValue();
					btn.previousSibling('#startTime').setValue();
					btn.previousSibling('#endTime').setValue();
				}
			}
		});
	},
	views : [
	    'customer.ApplyList'
	],
	stores : [
	    'apply.ApplyStore'
	],
	models : [
	    'apply.ApplyModel'
	]
});
