Ext.define('crm.controller.cooperate.CooperateAcceptController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'cooperateacceptlist button[id=accept]':{
				click:function(btn){
					var store = btn.up('cooperateacceptlist').store;
					var data = btn.up('cooperateacceptlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要提交的合作信息');
						return ;
					}
					Ext.Ajax.request({
					    url: 'cooperation_accept',
					    params: {
					        cooperateId: data[0].get('id'),
					        isAccept: true
					    },
					    success: function(response){
					    	MsgTip.msg('系统提示', '已审核');
					    	store.load();
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'cooperateacceptlist button[id=refuse]':{
				click:function(btn){
					var store = btn.up('cooperateacceptlist').store;
					var data = btn.up('cooperateacceptlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要提交的合作信息');
						return ;
					}
					Ext.Ajax.request({
					    url: 'cooperation_accept',
					    params: {
					        cooperateId: data[0].get('id'),
					        isAccept: false
					    },
					    success: function(response){
					    	MsgTip.msg('系统提示', '已驳回');
					    	store.load();
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'cooperateacceptlist button[id=search]':{
				click:function(btn){
					searchCooperateFun(btn,'cooperateacceptlist');
				}
			},
			'cooperateacceptlist button[id=clearAll]':{
				click:function(btn){
				    clearCooperateFun(btn,'cooperateacceptlist');
				}
			}
		});
	},
	views : [
	    'cooperate.CooperateAcceptList'
	],
	stores : [
	    'cooperate.CooperateAcceptStore'
	],
	models : [
	    'cooperate.CooperateModel'
	]
});
