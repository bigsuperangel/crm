Ext.define('crm.controller.cooperate.CooperateSubmitedController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
//		  'addcooperate button[id=check_customer]':{  //检测客户名称是否冲突
		  'startcrossareacooperate button[id=check_customer]':{  //检测客户名称是否冲突
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
		  'startcrosslocalcooperate button[id=check_brand]':{ //检测品牌是否冲突
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
			'startcrosslocalcooperate button[id=check_customer]':{  //检测客户名称是否冲突
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
		  'startcrossareacooperate button[id=check_brand]':{ //检测品牌是否冲突
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
			'cooperatesubmitedlist button[id=startcrosslocal]':{
				click:function(btn){
					addFun(btn, 'cooperatesubmitedlist', '发起跨区域合作', 600, 450, 'startcrosslocalcooperate');
				}
			},
			'cooperatesubmitedlist button[id=startcrossarea]':{
				click:function(btn){
					addFun(btn, 'cooperatesubmitedlist', '发起跨大区合作', 600, 450, 'startcrossareacooperate');
				}
			},
			'cooperatesubmitedlist button[id=search]':{
				click:function(btn){
					searchCooperateFun(btn,'cooperatesubmitedlist');
				}
			},
			'cooperatesubmitedlist button[id=search]':{
				click:function(btn){
					searchCooperateFun(btn,'cooperatesubmitedlist');
				}
			},
			'cooperatesubmitedlist button[id=clearAll]':{
				click:function(btn){
				    clearCooperateFun(btn,'cooperatesubmitedlist');
				}
			}
			
		});
	},
	views : [
	    'cooperate.CooperateSubmitedList',
	    'cooperate.StartCrossLocalCooperate',
	    'cooperate.StartCrossAreaCooperate'
	],
	stores : [
	    'cooperate.CooperateSubmitedStore',
	    'cooperate.CooperatorTypeStore'
	],
	models : [
	    'cooperate.CooperateModel'
	]
});
