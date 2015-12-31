Ext.define('crm.view.statistic.ServiceStatistic',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.servicestatic',
	
	layout: 'form',
	items:[{
		xtype: 'panel',
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
		xtype: 'panel',
		border: 0,
		height: 260,
		margins: '0 20 0 50',
		items:[
		   {xtype: 'servicechart',id: 'soChart', height:250, width: 700}
	    ]
	},{
		xtype: 'panel',
		border: 0,
		layout: 'fit',
		items:[{
			xtype: 'grid',
			store: 'statistic.ServiceChartStore',
		    columnLines: true,
			border: 0,
		    columns: [
		        {xtype: 'rownumberer',align:'center',width: 30},
		        {text: '年月',  dataIndex: 'year',width: 80,align:'center'},
		        {text: '已分配', dataIndex: '已分配',width: 80 ,align:'center'},
		        {text: '未分配', dataIndex: '未分配',width: 80,align:'center'},
		        {text: '已处理', dataIndex: '已处理',width: 80,align:'center'},
		        {text: '已归档', dataIndex: '已归档',width: 80,align:'center'},
		    ]
		}]
	}]
	
});