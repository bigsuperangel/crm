Ext.define('crm.controller.customer.PublicCustomerController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'publiccustomerlist button[id=pickup]':{
				click:function(btn){
					var store = btn.up('publiccustomerlist').store;
					var data = btn.up('publiccustomerlist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '请选择一条记录');
						return ;
					}
					Ext.Ajax.request({
					    url: 'customer_pickup',
					    params: {
					        customerId: data[0].get('id')
					    },
					    success: function(response){
					    	var json = Ext.decode(response.responseText);
					    	if(json.success){
					    		MsgTip.msg('系统提示', '公海客户申请成功');
					    		store.load();
					    	}else{
					    		Ext.Msg.alert("错误信息", '加载失败');
					    	}
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
					    }
					});
				}
			},
			'publiccustomerlist button[id=search]':{
				click:function(btn){
					searchCustomerFun(btn,'publiccustomerlist');
				}
			},
			'publiccustomerlist button[id=clearAll]':{
				click:function(btn){
				    clearCustomerFun(btn,'publiccustomerlist');
				}
			}
		});
	},
	views : [
	    'customer.PublicCustomerList'
	],
	stores : [
	    'customer.PublicCustomerStore'
	],
	models : [
	    'customer.CustomerModel'
	]
});
