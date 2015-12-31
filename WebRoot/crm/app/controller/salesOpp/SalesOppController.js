Ext.define('crm.controller.salesOpp.SalesOppController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'salesopplist button[id=add]':{
				click:function(btn,e,opt){
					addFun(btn, 'salesopplist', '添加销售机会', 500, 350, 'addsalesopp');
				}
			},
			'salesopplist button[id=update]':{
				click:function(btn,e,opt){
					updateFun(btn, 'salesopplist', '您没有选择要修改的销售机会', '修改销售机会', 'updatesalesopp', 500, 350);
				}
			},
			'salesopplist button[id=deal]':{
				click:function(btn,e,opt){
					var store = btn.up('salesopplist').store;
					var data = btn.up('salesopplist').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要指派的销售机会');
						return ;
					}
					var tips = [];
					var ids = [];
					var warn = [];
					Ext.Array.each(data, function(record, index, countriesItSelf) {
					    if(record.get('state') == 1){
					    	warn.push(record.get('customer'));
					    }else{
					    	tips.push(record.get('customer'));
					    	ids.push(record.get('id'));
					    }
					});
					if(warn.length != 0){
						MsgTip.msg('系统提示', '销售机会：<br><span style="color:red">['+warn.join(']<br>[')+']</span><br>已经在处理中，不可以再次指派。');
					}
					if(ids.length == 0){
						return ;
					}
					var win = Ext.create('Ext.window.Window',{
						title: '指派销售机会',
						height: 180,
						width: 300,
						iconCls: btn.iconCls,
						modal: true,
						layout: 'fit',
						items:[{xtype:'appointsalesopp',tips: tips.join(','), ids: ids.join(','), store: store}]
					});
					win.show();
				}
			},
			'salesopplist button[id=dealed]':{
				click:function(btn,e,opt){
					Ext.getCmp('dealed').disable();
					Ext.getCmp('undeal').enable();
					Ext.getCmp('myWork').enable();
					Ext.getCmp('success').enable();
					Ext.getCmp('failure').enable();
					Ext.getCmp('control').disable();
					Ext.getCmp('astate').hideMenu();
					
					var store = btn.up('salesopplist').store;
					store.getProxy().url = 'salesOpp_salesOppDealList';
					store.load();
					url = 'salesOpp_salesOppDealList';
				}
			},
			'salesopplist button[id=undeal]':{
				click:function(btn,e,opt){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('undeal').disable();
					Ext.getCmp('myWork').enable();
					Ext.getCmp('success').enable();
					Ext.getCmp('failure').enable();
					Ext.getCmp('control').disable();
					Ext.getCmp('astate').hideMenu();
					
					var store = btn.up('salesopplist').store;
					store.getProxy().url = 'salesOpp_salesOppUnDealList';
					store.load();
					
					url = 'salesOpp_salesOppUnDealList';
				}
			},
			'salesopplist button[id=myWork]':{
				click:function(btn,e,opt){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('undeal').enable();
					Ext.getCmp('myWork').disable();
					Ext.getCmp('success').enable();
					Ext.getCmp('failure').enable();
					Ext.getCmp('control').enable();
					
					var store = btn.up('salesopplist').store;
					store.getProxy().url = 'salesOpp_getMyWork';
					store.load();
					
					url = 'salesOpp_getMyWork';
				}
			},
			'salesopplist button[id=success]':{
				click:function(btn,e,opt){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('undeal').enable();
					Ext.getCmp('myWork').enable();
					Ext.getCmp('success').disable();
					Ext.getCmp('failure').enable();
					Ext.getCmp('control').disable();
					Ext.getCmp('dstate').hideMenu();
					
					var store = btn.up('salesopplist').store;
					store.getProxy().url = 'salesOpp_getSuccessWork';
					store.load();
					
					url = 'salesOpp_getMyWork';
				}
			},
			'salesopplist button[id=failure]':{
				click:function(btn,e,opt){
					Ext.getCmp('dealed').enable();
					Ext.getCmp('undeal').enable();
					Ext.getCmp('myWork').enable();
					Ext.getCmp('success').enable();
					Ext.getCmp('failure').disable();
					Ext.getCmp('control').disable();
					Ext.getCmp('dstate').hideMenu();
					
					var store = btn.up('salesopplist').store;
					store.getProxy().url = 'salesOpp_getFailedMyWork';
					store.load();
					
					url = 'salesOpp_getMyWork';
				}
			},
			'salesopplist button[id=writePlan]':{
				click: function(btn,e,opt){
					Ext.getCmp('control').hideMenu();
					addFunWithData(btn, 'salesopplist', '您没有选择销售机会，无法进行定制开发计划', '定制开发计划', 340, 200, 'addplan', 0);
				}
			},
			'salesopplist button[id=devSuccess]':{
				click: function(btn,e,opt){
					Ext.getCmp('control').hideMenu();
					changeState(btn, 'salesopplist', '您没有选择销售机会，无法进行操作', '是否将一下，销售机会标志为开发成功', 'salesOpp_devSuccess', 'customer', 'id');
				}
			},
			'salesopplist button[id=devFaile]':{
				click: function(btn,e,opt){
					Ext.getCmp('control').hideMenu();
					changeState(btn, 'salesopplist', '您没有选择销售机会，无法进行操作', '是否将一下，销售机会标志为开发失败', 'salesOpp_devFaile', 'customer', 'id');
				}
			},
			'salesopplist button[id=delete]':{
				click:function(btn,e,opt){
					delFun(btn, 'salesopplist', '销售机会', 'salesOpp_delete', 'customer', 'id')
				}
			}
		});
	},
	views : [
	    'salesOpp.SalesOppList',
	    'salesOpp.AddSalesOpp',
	    'salesOpp.UpdateSalesOpp',
	    'salesOpp.AppointSalesOpp'
	],
	stores : [
	    'salesOpp.SalesOppStore'
	],
	models : [
	    'salesOpp.SalesOppModel'
	]
});
