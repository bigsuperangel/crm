Ext.define('crm.view.statistic.Statistic',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.statistic',
	
	layout: 'form',
	items:[{
		xtype: 'panel',
//		title: '查询条件',
		height: 60,
		border: 0,
		layout: 'column',
		padding: 10,
		items: [{
			xtype: 'text',
			html: '查询月份：'
		},{
			xtype: 'datefield',
			allowBlank:false, 
        	format: 'Y-m-d',
        	altFormats: 'Y-m-d',
        	id: 'searchDate',
			value: new Date()
		},{
			xtype: 'button',
			text: '查询结果',
			handler: function() {
			    var val = Ext.getCmp('searchDate').getValue();
			    var store = Ext.getCmp('soChart').store;
			    store.load({
			    	params:{
			    		date:val
			    	}
			    });
			    
		    }
		}]
	},{
//		region: 'north',
		xtype: 'panel',
//		title: 'chart',
		border: 0,
		height: 260,
		margins: '0 20 0 50',
		items:[
		   {xtype: 'salesoppchart',id: 'soChart', height:250, width: 700}
	    ]
	},{
//		region: 'center',
		xtype: 'panel',
//		title: 'grid',
		border: 0,
		layout: 'fit',
		items:[
		   {xtype: 'salesoppstatistic'}
		]
	}]
	
});