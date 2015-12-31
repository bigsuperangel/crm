Ext.define('crm.controller.customer.DelayController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'delaylist button[id=audit]':{
				click:function(btn){
					var store = btn.up('delaylist').store;
					var data = btn.up('delaylist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'delayApply_audit',
					    params: {
					        delayApplyId: data[0].get('id'),
					        audit:1
					    },
					    success: function(response){
					    	store.load();
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'delaylist button[id=refuse]':{
				click:function(btn){
					var store = btn.up('delaylist').store;
					var data = btn.up('delaylist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'delayApply_audit',
					    params: {
					        delayApplyId: data[0].get('id'),
					        audit:2
					    },
					    success: function(response){
					    	store.load();
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'delaylist button[id=search]':{
				click:function(btn){
					var customerName = btn.previousSibling('#customerName').getValue();
					var store = btn.up('delaylist').store;
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
			'delaylist button[id=clearAll]':{
				click:function(btn){
				    btn.previousSibling('#customerName').setValue();
					btn.previousSibling('#startTime').setValue();
					btn.previousSibling('#endTime').setValue();
				}
			}
		});
	},
	views : [
	    'customer.DelayList'
	],
	stores : [
	    'delay.DelayStore'
	],
	models : [
	    'delay.DelayModel'
	]
});
