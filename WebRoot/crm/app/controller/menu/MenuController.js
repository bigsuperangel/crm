Ext.define('crm.controller.menu.MenuController',{
	extend: 'Ext.app.Controller',
	init:function(){
		this.control({
			'menutree':{
				itemclick: function(view, record, item, index, e, eOpts ){
					if(record.get('leaf')){
						if(record.raw.config.length<=0){
							MsgTip.msg('系统提示', '该功能暂未开放')
							return ;
						}
//						var config = Ext.decode(record.raw.config);
						var config = record.raw.config;
						var mainPanel = Ext.getCmp('mainPanel');
						mainPanel.removeAll(true);
						mainPanel.setTitle(record.get('text'));
						mainPanel.setIconCls(record.get('iconCls'));
						mainPanel.add({xtype: config});
					}
				}
			}
		});
	},
	views: [
		'menu.MenuTree'
	],
	stores: [
		'menu.MenuTreeStore'
	],
	models: [
		
	]
});
