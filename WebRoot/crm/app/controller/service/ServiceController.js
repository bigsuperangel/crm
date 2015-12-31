Ext.define('crm.controller.service.ServiceController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'servicelist button[id=undeal]':{
				click:function(btn){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('deal').enable()
					Ext.getCmp('undeal').disable();
					Ext.getCmp('writeReplay').disable();
					Ext.getCmp('appoint').enable();
					Ext.getCmp('update').enable();
					var store = btn.up('servicelist').store;
					store.getProxy().url = 'service_getUnassignationList';
					store.load();
				}
			},
			'servicelist button[id=deal]':{
				click:function(btn){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('deal').disable()
					Ext.getCmp('undeal').enable();
					Ext.getCmp('writeReplay').disable();
					Ext.getCmp('appoint').disable();
					Ext.getCmp('update').disable();
					var store = btn.up('servicelist').store;
					store.getProxy().url = 'service_getAssignationList';
					store.load();
				}
			},
			'servicelist button[id=dealed]':{
				click:function(btn){
					Ext.getCmp('dealed').disable();
					Ext.getCmp('deal').enable()
					Ext.getCmp('undeal').enable();
					Ext.getCmp('writeReplay').enable();
					Ext.getCmp('appoint').disable();
					Ext.getCmp('update').disable();
					var store = btn.up('servicelist').store;
					store.getProxy().url = 'service_getDealedList';
					store.load();
				}
			},
			'servicelist button[id=update]':{
				click: function(btn){//
					updateFun(btn, 'servicelist', '您没有选择服务，请选择要修改的服务', '修改服务', 'updateservice', 500, 380);
				}
			},
			'servicelist button[id=delete]':{
				click: function(btn){//
					delFun(btn, 'servicelist', '服务', 'service_delete', 'title', 'id');
				}
			},
			'servicefilelist button[id=delete]':{
				click: function(btn){//
					delFun(btn, 'servicefilelist', '归档服务', 'service_delete', 'title', 'id');
				}
			},
			'servicelist button[id=writeReplay]':{
				click: function(btn){
					updateFun(btn, 'servicelist', '您没有选择服务，请选择要填写反馈的服务', '填写服务反馈', 'addreplay', 400, 150);
				}
			},
			'servicelist button[id=appoint]':{
				click: function(btn){
					var store = btn.up('servicelist').store;
					var data = btn.up('servicelist').getSelectionModel().getSelection();
					if(data.length<=0){
						MsgTip.msg('系统提示','您没有选择要指派的服务');
						return ;
					}
					var tips = [];
					var ids = [];
					Ext.Array.each(data, function(record, index, countriesItSelf) {
				    	tips.push(record.get('title'));
				    	ids.push(record.get('id'));
					});
					var win = Ext.create('Ext.window.Window',{
						title: '指派销售机会',
						height: 180,
						width: 300,
						iconCls: btn.iconCls,
						modal: true,
						layout: 'fit',
						items:[{xtype:'appointservice',tips: tips.join(','), ids: ids.join(','), store: store}]
					});
					win.show();
				}
			},
			//''''''
			'myservicelist button[id=dealed]':{
				click:function(btn){
					Ext.getCmp('dealed').disable();
					Ext.getCmp('undeal').enable();
					Ext.getCmp('success').disable();
					var store = btn.up('myservicelist').store;
					store.getProxy().url = 'service_getMyDealedList';
					store.load();
				}
			},
			'myservicelist button[id=undeal]':{
				click:function(btn){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('undeal').disable();
					Ext.getCmp('success').enable();
					var store = btn.up('myservicelist').store;
					store.getProxy().url = 'service_getMyUndealList';
					store.load();
				}
			},
			'myservicelist button[id=success]':{
				click:function(btn){
					changeState(btn, 'myservicelist', '您没有选择任何要完成的服务，请选择', '是否讲一下服务，标记为完成', 'service_finish', 'title', 'id');
				}
			}
		});
	},
	views : [
	    'service.AddService',
	    'service.ServiceList',
	    'service.UpdateService',
	    'service.AddReplay',
	    'service.ServiceFileList',
	    'service.AppointService',
	    'service.MyServiceList'
	],
	stores : [
	    'service.ServiceStore'
	],
	models : [
	    'service.ServiceModel'
	]
});
