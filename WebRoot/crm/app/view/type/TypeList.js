Ext.define('crm.view.type.TypeList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.typelist',
	
    store: 'type.TypeListStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '类型',  dataIndex: 'type',width: 120,align:'center'},
        {text: '标签名', dataIndex: 'name',width: 120 ,align:'center'},
        {text: '数据值',  dataIndex: 'value',width: 80,align:'center'},
        {text: '排序', dataIndex: 'sort',width: 60 ,align:'center'},
        {text: '创建者',  dataIndex: 'createBy',width: 80,align:'center'},
        {text: '创建时间', dataIndex: 'cdate',width: 140 ,align:'center'},
        {text: '更新者',  dataIndex: 'updateBy',width: 80,align:'center'},
        {text: '更新时间', dataIndex: 'udate',width: 140 ,align:'center'},
        {text: '描述',  dataIndex: 'description',width: 150,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'add', iconCls: 'icon1591'},
        {xtype: 'button', text: '修改',id:'update', iconCls: 'icon1596'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon682'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'type.TypeListStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});