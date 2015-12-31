Ext.define('crm.view.service.ServiceFileList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.servicefilelist',
	
    store: 'service.ServiceStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '服务概要',  dataIndex: 'title',flex:1},
        {text: '服务类型', dataIndex: 'typeName',align:'center'},
        {text: '客户名称', dataIndex: 'customerName',width: 60 },
        {text: '服务状态', dataIndex: 'state',width: 120, align:'center',
        	renderer: function(val){
        		if(val == 0){
        			return "未分配";
        		}else if(val == 1){
        			return "处理中";
        		}else if(val == 2){
        			return "已完成";
        		}else if(val == 3){
        			return "已归档";
        		}
        	}
        },
        {text: '处理人员', dataIndex: 'dealUserName',align:'center',width: 60},
        
        {text: '服务满意度', dataIndex: 'satisfied',align:'center',width: 85},
        {text: '创建者', dataIndex: 'userName',align:'center'},
        {text: '创建日期', dataIndex: 'cdate',align:'center'}
        
    ],
    tbar: [
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon682'},
        {xtype: 'tbfill'},
		{xtype: 'textfield', id:'keyword', enableKeyEvents: true, listeners: {
			change: function(obj, newValue, oldValue, eOpts ){
//				if(obj.getValue().length == 0){
//					var store = obj.up('salesopplist').store;
//					store.getProxy().url = url; 
//					store.load();
//				}
			},
			keypress: function(obj, e, eOpts ){
//				if(e.getKey() == 13){
//		            var store = obj.up('salesopplist').store;
//					var key = obj.getValue();
//					if(key.length == 0){
//						return ;	
//					}
//					store.removeAll(true);
//			    	store.getProxy().url = 'salesOpp_search'; 
//			    	store.load({
//			    		params:{
//		  			       keyword:key
//		  			       ,url: url
//		  			    }
//			    	});
//		        }  
			}
		}},
		{xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'service.ServiceStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.getProxy().url = 'service_getFileList';
			obj.store.load();
		},
		itemdblclick: function(obj, record, item, index, e, eOpts ){
			Ext.MessageBox.alert('查看服务内容',record.get('content'));
		}
	}
});