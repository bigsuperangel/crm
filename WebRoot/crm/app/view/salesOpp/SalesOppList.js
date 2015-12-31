var url = 'salesOpp_salesOppList';
Ext.define('crm.view.salesOpp.SalesOppList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.salesopplist',
	
    store: 'salesOpp.SalesOppStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customer',width: 80,align:'center'},
        {text: '机会来源', dataIndex: 'resource',width: 120},
        {text: '成功几率', dataIndex: 'success',width: 60 ,align:'center',
        	renderer: function(value) {
        		return value+'%';
            }
        },
        {text: '概要', dataIndex: 'summery',width: 120},
        {text: '联系人', dataIndex: 'person',align:'center',width: 60},
        {text: '联系电话', dataIndex: 'tel',width: 85},
        {text: '机会描述', dataIndex: 'descr',width: 80},
        {text: '负责人', dataIndex: 'dealUserName',align:'center'},
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
        {xtype: 'button', text: '添加',id:'add', iconCls: 'icon681'},
        {xtype: 'button', text: '修改',id:'update', iconCls: 'icon683'},
        {xtype: 'button', text: '指派',id:'deal', iconCls: 'icon387'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon682'},
        '-',
        {xtype: 'splitbutton',text: '机会状态',id:'astate', iconCls:'icon387', menu:{width:20, items:[
	        {xtype: 'button', text: '已指派列表',id:'dealed', iconCls: 'icon399', textAlign: 'center'},
	        {xtype: 'button', text: '未指派列表',id:'undeal', iconCls: 'icon411', textAlign: 'center'}
        ]}},
        '-',
        {xtype: 'splitbutton',text: '开发状态',id:'dstate', iconCls:'icon413', menu:{width:20, items:[
			{xtype: 'button', text: '开发成功列表',id:'success', iconCls: 'icon395', textAlign: 'center'},
			{xtype: 'button', text: '开发失败列表',id:'failure', iconCls: 'icon400', textAlign: 'center'},                     
        ]}},
        
        '-',
        {xtype: 'button', text: '我的任务',id:'myWork', iconCls: 'icon375'},
        {xtype: 'splitbutton',text: '操作',id:'control', iconCls:'icon413',disabled:true, menu:{width:20, items:[
            {xtype: 'button', text: '定制开发计划',id:'writePlan', iconCls: 'icon1596'},
            {xtype: 'button', text: '机会开发成功',id:'devSuccess', iconCls: 'icon395'},
            {xtype: 'button', text: '机会开发失败',id:'devFaile', iconCls: 'icon400'}
        ]}},
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
		store: 'salesOpp.SalesOppStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.getProxy().url = 'salesOpp_salesOppList';
			obj.store.load();
		}
	}
});