Ext.define('crm.controller.customer.AuditPublicCustomerController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'auditpubliccustomerlist button[id=show]':{
				click: function(btn){
					var data = btn.up('auditpubliccustomerlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '请选择一条客户信息');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '查看['+data[0].get('customerName')+']公海客户申请',
						iconCls: btn.iconCls,
						width: 1000,
						height: 600,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'publiccustomerapply', model: data[0]}]
					});
					win.show();
				}
			}
		});
	},
	views : [
	    'customer.AuditPublicCustomerList'
	],
	stores : [
	    'customer.PublicCustomerStore'
	],
	models : [
	    'customer.CustomerModel'
	]
});
