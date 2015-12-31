Ext.define('crm.view.outflow.OutflowList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.outflowlist',
	
    store: 'outflow.OutflowStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 120},
        {text: '流失状态', dataIndex: 'state',width: 80,align:'center',
        	renderer: function(value) {
        		if(value == 0){
        			return '未处理';
        		}else if(value == 1){
        			return '暂缓中';
        		}else if(value == 2){
        			return '已流失';
        		}else if(value == 3){
        			return '流失消除';
        		}
            }
        },
        {text: '客户经理', dataIndex: 'userName',width: 80 ,align:'center'},
        {text: '告警时间', dataIndex: 'cdate',width: 120 ,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '暂缓流失',id:'stop', iconCls: 'icon681'},
        {xtype: 'button', text: '确定流失',id:'outflow', iconCls: 'icon683'},
        '-',
        {xtype: 'button', text: '填写流失措施',id:'writeLoss', iconCls: 'icon945'},
        {xtype: 'button', text: '查看流失措施',id:'showLoss', iconCls: 'icon1850'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon682'},
        
        {xtype: 'tbfill'},
		{xtype: 'textfield', id:'keyword', enableKeyEvents: true, listeners: {
			change: function(obj, newValue, oldValue, eOpts ){
				if(obj.getValue().length == 0){
					var store = obj.up('salesopplist').store;
					store.getProxy().url = url; 
					store.load();
				}
			},
			keypress: function(obj, e, eOpts ){
				if(e.getKey() == 13){
		            var store = obj.up('salesopplist').store;
					var key = obj.getValue();
					if(key.length == 0){
						return ;	
					}
					store.removeAll(true);
			    	store.getProxy().url = 'salesOpp_search'; 
			    	store.load({
			    		params:{
		  			       keyword:key
		  			       ,url: url
		  			    }
			    	});
		        }  
			}
		}},
		{xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'outflow.OutflowStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});