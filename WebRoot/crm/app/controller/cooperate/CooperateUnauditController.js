Ext.define('crm.controller.cooperate.CooperateUnauditController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'cooperateunauditlist button[id=accept]':{
				click:function(btn){
					var data = btn.up('cooperateunauditlist').getSelectionModel().getSelection();
					var store = btn.up('cooperateunauditlist').store;
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要提交的合作信息');
						return ;
					}
					if(data[0].raw.cooperatorType==1 && data[0].raw.status==0){  //跨大区并且是本大区经理审批,不弹窗
						Ext.Ajax.request({
						    url: 'cooperation_audit',
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
					}else{
						addFunWithData(btn, 'cooperateunauditlist', '您没有选择合作，无法指派维权顾问', '指派合作', 340, 200, 'dispatchcooperate', 1);
//						addFun(btn, 'cooperateunauditlist', '指派合作', 400, 220, 'dispatchcooperate');
					}
				}
			},
			'cooperateunauditlist button[id=refuse]':{
				click:function(btn){
					var store = btn.up('cooperateunauditlist').store;
					var data = btn.up('cooperateunauditlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要提交的合作信息');
						return ;
					}
					Ext.Ajax.request({
					    url: 'cooperation_refuse',
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
			'cooperateunauditlist button[id=search]':{
				click:function(btn){
					searchCooperateFun(btn,'cooperateunauditlist');
				}
			},
			'cooperateunauditlist button[id=clearAll]':{
				click:function(btn){
				    clearCooperateFun(btn,'cooperateunauditlist');
				}
			}
		});
	},
	views : [
	    'cooperate.CooperateUnauditList',
	    'cooperate.DispatchCooperate'
	],
	stores : [
	    'cooperate.CooperateUnauditStore'
	],
	models : [
	    'cooperate.CooperateModel'
	]
});
