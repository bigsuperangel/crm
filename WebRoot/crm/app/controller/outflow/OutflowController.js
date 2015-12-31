Ext.define('crm.controller.outflow.OutflowController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'outflowlist button[id=stop]':{
				click: function(btn){
					changeState(btn, 'outflowlist', '您没有选择暂缓流失的客户，请选择', '您确定要暂缓一下客户流失告警吗？', 'outflow_stop', 'customerName', 'id');
				}
			},
			'outflowlist button[id=outflow]':{
				click: function(btn){
					changeState(btn, 'outflowlist', '您没有选择暂缓流失的客户，请选择', '您确定要暂缓一下客户流失告警吗？', 'outflow_outflow', 'customerName', 'id');
				}
			},
			'outflowlist button[id=writeLoss]':{
				click: function(btn){
					addFunWithData(btn, 'outflowlist', '您没有选择流失告警，无法填写厉流失措施', '填写流失措施', 340, 200, 'addlossstep', 0);
				}
			},
			'outflowlist button[id=showLoss]':{
				click: function(btn){
					var data = btn.up('outflowlist').getSelectionModel().getSelection();
					if(data.length <=0){
						MsgTip.msg('系统提示', '您没有选择流失预警');
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '查看['+data[0].get('customerName')+']暂缓措施',
						iconCls: btn.iconCls,
						width: 550,
						height: 405,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'losslist', model: data[0]}]
					});
					win.show();
				}
			},
			'outflowlist button[id=delete]':{
				click: function(btn){
					delFun(btn, 'outflowlist', '客户流失预警', 'outflow_delete', 'customerName', 'id')
				}
			}
		});
	},
	views : [
	    'outflow.OutflowList'
	],
	stores : [
	    'outflow.OutflowStore'
	],
	models : [
	    'outflow.OutflowModel'
	]
});
