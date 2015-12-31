Ext.define('crm.controller.agreement.AgreementController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'agreementlist button[id=update]':{
				click:function(btn){
					updateFun(btn, 'agreementlist', '您没有选择要修改的合同信息', '修改合同信息', 'updateagreement', 650, 400);
				}
			},
			'agreementlist button[id=delete]':{
				click:function(btn){
					delFun(btn, 'agreementlist', '合同信息', 'agreement_delete', 'agreementName', 'id');
				}
			},
			'agreementlist button[id=showPayment]':{
				click: function(btn){
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择合同');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '查看['+data[0].get('projectName')+']合同款项',
						iconCls: btn.iconCls,
						width: 1000,
						height: 500,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'paymentlist', model: data[0]}]
					});
					win.show();
				}
			},
			'agreementlist button[id=uploadagreement]':{
				click: function(btn){
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择合同');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '上传['+data[0].get('projectName')+']合同',
						iconCls: btn.iconCls,
						width: 300,
						height: 150,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'uploadagreement', model: data[0]}]
					});
					win.show();
				}
			},
			'agreementlist button[id=send]':{
				click:function(btn){
					var store = btn.up('agreementlist').store;
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'agreement_update_status',
					    params: {
					        'agreementId': data[0].get('id'),
					        'status':1
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
		                    if (json.success) {
						    	MsgTip.msg('系统提示', '合同已发出');
						    	store.load();
		                    } else {
		                    	if(json.msg){
		                    		Ext.Msg.alert("提示",json.msg);
		                    	}else{
		                    		Ext.Msg.alert("提示","操作失败！");
		                    	}
		                        
		                    }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'agreementlist button[id=sign]':{
				click:function(btn){
					var store = btn.up('agreementlist').store;
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'agreement_update_status',
					    params: {
					        'agreementId': data[0].get('id'),
					        'status':2
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
		                    if (json.success) {
						    	MsgTip.msg('系统提示', '合同已签约');
						    	store.load();
		                    } else {
		                    	if(json.msg){
		                    		Ext.Msg.alert("提示",json.msg);
		                    	}else{
		                    		Ext.Msg.alert("提示","操作失败！");
		                    	}
		                    }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'agreementlist button[id=receive]':{
				click:function(btn){
					var store = btn.up('agreementlist').store;
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'agreement_update_payment_status',
					    params: {
					        'agreementId': data[0].get('id'),
					        'status':3
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
		                    if (json.success) {
						    	MsgTip.msg('系统提示', '合同已到款');
						    	store.load();
		                    } else {
		                    	if(json.msg){
		                    		Ext.Msg.alert("提示",json.msg);
		                    	}else{
		                    		Ext.Msg.alert("提示","操作失败！");
		                    	}
		                    }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'agreementlist button[id=clear]':{
				click:function(btn){
					var store = btn.up('agreementlist').store;
					var data = btn.up('agreementlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'agreement_update_payment_status',
					    params: {
					        'agreementId': data[0].get('id'),
					        'status':4
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
		                    if (json.success) {
						    	MsgTip.msg('系统提示', '合同已结清');
						    	store.load();
		                    } else {
		                    	if(json.msg){
		                    		Ext.Msg.alert("提示",json.msg);
		                    	}else{
		                    		Ext.Msg.alert("提示","操作失败！");
		                    	}
		                    }
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'agreementlist button[id=search]':{
				click:function(btn){
					var agreementNo = Ext.getCmp('agreementNo').getValue();
					var agreementStatus = Ext.getCmp('agreementStatus').getValue();
					var startTime = Ext.getCmp('startTime').getValue();
					var endTime = Ext.getCmp('endTime').getValue();
					var store = btn.up('agreementlist').store;
					store.removeAll(true);
			    	store.load({
			    		params:{
		  			       'agreementNo':agreementNo,
		  			       'status':agreementStatus,
		  			       'startTime':ww.common.formatDate(startTime),
		  			       'endTime':ww.common.formatDate(endTime)
		  			    }
			    	});
				}
			},
			'agreementlist button[id=clearAll]':{
				click:function(btn){
				    Ext.getCmp('agreementNo').setValue();
					Ext.getCmp('agreementStatus').clearValue();
					Ext.getCmp('startTime').setValue();
					Ext.getCmp('endTime').setValue();
				}
			}
		});
	},
	views : [
	    'agreement.AgreementList',
	    'agreement.AddAgreement',
	    'agreement.UpdateAgreement',
	    'agreement.UploadAgreement'
	],
	stores : [
	    'agreement.AgreementStore'
	],
	models : [
	    'agreement.AgreementModel'
	]
});
