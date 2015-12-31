Ext.define('crm.controller.payment.PaymentController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'paymentlist button[id=addPayment]':{
				click:function(btn){
					addFun(btn, 'paymentlist', '添加合同款项信息', 400, 300, 'addpayment');
				}
			},
			'paymentlist button[id=updatePayment]':{
				click:function(btn){
					updateFun(btn, 'paymentlist', '您没有选择要修改的合同款项信息', '修改合同款项信息', 'updatepayment', 400, 300);
				}
			},
			'paymentlist button[id=deletePayment]':{
				click:function(btn){
					delFun(btn, 'paymentlist', '合同款项信息', 'payment_delete', 'id', 'id');
				}
			},
			'paymentlist button[id=uploadPayment]':{
				click: function(btn){
					var data = btn.up('paymentlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择款项');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '上传款项',
						iconCls: btn.iconCls,
						width: 300,
						height: 150,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'uploadpayment', model: data[0]}]
					});
					win.show();
				}
			},
			'paymentlist button[id=auditPayment]':{  //审核款项
				click:function(btn){
					var store = btn.up('paymentlist').store;
					var data = btn.up('paymentlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					console.info(data[0].get('itemId'))
					Ext.Ajax.request({
					    url: 'payment_audit',
					    params: {
					        itemId: data[0].get('itemId'),
					        status:1  //待定
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
			'paymentlist button[id=refusePayment]':{  //审核款项
				click:function(btn){
					var store = btn.up('paymentlist').store;
					var data = btn.up('paymentlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					console.info(data[0].get('itemId'))
					Ext.Ajax.request({
					    url: 'payment_audit',
					    params: {
					        itemId: data[0].get('itemId'),
					        status:-1  //待定
					    },
					    success: function(response){
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
	    'payment.PaymentList',
	    'payment.AddPayment',
	    'payment.UpdatePayment',
	    'payment.UploadPayment'
	],
	stores : [
	    'payment.PaymentStore',
	    'payment.PayModeStore'
	],
	models : [
	    'payment.PaymentModel'
	]
});
