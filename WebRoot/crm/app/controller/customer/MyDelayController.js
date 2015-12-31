/**
 * 我的延时申请列表
 */
Ext.define('crm.controller.customer.MyDelayController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'mydelaylist button[id=recall]':{
				click:function(btn){
					var store = btn.up('mydelaylist').store;
					var data = btn.up('mydelaylist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'delayApply_recall',
					    params: {
					        'delayApplyId': data[0].get('id')
					    },
					    success: function(response){
					    	MsgTip.msg('系统提示', '回收成功');
					    	store.load();
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			}
		});
	},
	views : [
	    'customer.MyDelayList'
	],
	stores : [
	    'delay.MyDelayStore'
	],
	models : [
	    'delay.DelayModel'
	]
});
