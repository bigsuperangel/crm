Ext.define('crm.view.plan.PlanList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.planlist',
	
    store: 'plan.PlanStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customer',width: 80,align:'center'},
        {text: '成功几率', dataIndex: 'success',width: 60 ,align:'center',
        	renderer: function(value) {
        		return value+'%';
            }
        },
        {text: '概要', dataIndex: 'summery',width: 120},
        {text: '联系人', dataIndex: 'person',align:'center',width: 60},
        {text: '联系电话', dataIndex: 'tel',width: 85},
        {text: '计划时间', dataIndex: 'cdate',align:'center'},
        {text: '计划内容', dataIndex: 'content'},
        {text: '销售机会状态', dataIndex: 'state',align:'center',
        	renderer: function(value) {
        		if(value === 0){
        			return '<span style="font-weight:600;color:#5BBD2B">未指派</span>';
        		}else if(value == 1){
        			return '<span style="font-weight:600;color:#103667">已指派</span>';
        		}else if(value == 2){
        			return '<span style="font-weight:600;color:#00B2BF">开发成功</span>';
        		}else if(value == 3){
        			return '<span style="font-weight:600;color:#DF0029">开发失败</span>';
        		}
            }
        },
        {text: '创建者', dataIndex: 'userName',align:'center'},
        {text: '创建日期', dataIndex: 'createDate',align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'add', iconCls: 'icon1591'},
        {xtype: 'button', text: '修改',id:'update', iconCls: 'icon1596'},
        '-',
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
		store: 'plan.PlanStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
//			obj.store.getProxy().url = 'salesOpp_salesOppList';
			obj.store.load();
		}
	}
});