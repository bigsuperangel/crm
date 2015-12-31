Ext.define('crm.controller.customer.CustomerController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
		  'addcustomer button[id=check_customer]':{  //检测客户名称是否冲突
				click:function(btn){
					var customer = Ext.getCmp('customerName').getValue();
					if (!customer){
						MsgTip.msg('系统提示', '您没有填写客户名称');
						return;
					}
					Ext.Ajax.request({
					    url: 'customer_checkConflict',
					    params: {
					        name:customer
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
					    	if(json.success){
					    		MsgTip.msg('系统提示', '客户名称没有被占用');
					    	}else{
					    		MsgTip.msg('系统提示', '客户名称已被占用');
					    	}
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
		  'addcustomer button[id=check_brand]':{ //检测品牌是否冲突
				click:function(btn){
					var brand = Ext.getCmp('brand').getValue();
					if(!brand){
						MsgTip.msg('系统提示', '您没有填写品牌名称');
						return;
					}
					Ext.Ajax.request({
					    url: 'customer_checkConflict',
					    params: {
					        brandName : brand
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
					    	if(json.success){
					    		MsgTip.msg('系统提示', '品牌名称没有被占用');
					    	}else{
					    		MsgTip.msg('系统提示', '品牌名称已被占用');
					    	}
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'customerlist button[id=add]':{
				click:function(btn){
					addFun(btn, 'customerlist', '添加客户信息', 600, 450, 'addcustomer');
				}
			},
			'customerlist button[id=update]':{
				click:function(btn){
					updateFun(btn, 'customerlist', '您没有选择要修改的客户信息', '修改客户信息', 'updatecustomer',  600, 450);
				}
			},
			'customerlist button[id=delete]':{
				click:function(btn){
					delFun(btn, 'customerlist', '客户信息', 'customer_delete', 'customerName', 'id');
				}
			},
			'customerlist button[id=showPerson]':{
				click: function(btn){
					var data = btn.up('customerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '查看['+data[0].get('customerName')+']联系人',
						iconCls: btn.iconCls,
						width: 1000,
						height: 600,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'personlist', model: data[0]}]
					});
					win.show();
				}
			},
			'customerlist button[id=showContact]':{
				click: function(btn){
					var data = btn.up('customerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '查看['+data[0].get('customerName')+']联系人',
						iconCls: btn.iconCls,
						width: 1000,
						height: 600,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'contactlist', model: data[0]}]
					});
					win.show();
				}
			},
			'customerlist button[id=assign]':{
				click: function(btn){
					var data = btn.up('customerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '分配客户',
						iconCls: btn.iconCls,
						width: 300,
						height: 150,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'assigncustomer', model: data[0]}]
					});
					win.show();
				}
			},
			'customerlist button[id=addAgreement]':{
				click: function(btn){
					var data = btn.up('customerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '新增合同',
						iconCls: btn.iconCls,
						width: 650,
						height: 400,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'addagreement', model: data[0]}]
					});
					win.show();
				}
			},
			'customerlist button[id=delayApply]':{
				click: function(btn){
					var data = btn.up('customerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '延时申请',
						iconCls: btn.iconCls,
						width: 300,
						height: 150,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'delaycustomer', model: data[0]}]
					});
					win.show();
				}
			},
			'customerlist button[id=search]':{
				click:function(btn){
					searchCustomerFun(btn,'customerlist');
				}
			},
			'customerlist button[id=clearAll]':{
				click:function(btn){
				    clearCustomerFun(btn,'customerlist');
				}
			}
//			'customerlist button[id=search]':{
//				click:function(btn){
//					var customerName = Ext.getCmp('customerName').getValue();
//					var brandName = Ext.getCmp('brandName').getValue();
//					var startTime = Ext.getCmp('startTime').getValue();
//					var endTime = Ext.getCmp('endTime').getValue();
//					var store = btn.up('customerlist').store;
//					store.removeAll(true);
//			    	store.load({
//			    		params:{
//		  			       'customerName':customerName,
//		  			       'brandName':brandName,
//		  			       'startTime':ww.common.formatDate(startTime),
//		  			       'endTime':ww.common.formatDate(endTime)
//		  			    }
//			    	});
//				}
//			},
//			'customerlist button[id=clear]':{
//				click:function(btn){
//				    Ext.getCmp('customerName').setValue();
//					Ext.getCmp('brandName').setValue();
//					Ext.getCmp('startTime').setValue();
//					Ext.getCmp('endTime').setValue();
//				}
//			}
		});
	},
	views : [
	    'customer.CustomerList',
	    'customer.AddCustomer',
	    'customer.UpdateCustomer',
	    'customer.DelayCustomer',
	    'customer.AssignCustomer'
	],
	stores : [
	    'customer.CustomerStore',
	    'customer.CustomerComboboxStore'
	],
	models : [
	    'customer.CustomerModel'
	]
});
