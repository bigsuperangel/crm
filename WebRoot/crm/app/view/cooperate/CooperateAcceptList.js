Ext.define('crm.view.cooperate.CooperateAcceptList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.cooperateacceptlist',
	
    store: 'cooperate.CooperateAcceptStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	forceFit:true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 150,align:'center'},
        {text: '合作类型', dataIndex: 'cooperatorType',width: 130 ,align:'center'},        
        { text: '发起者', dataIndex: 'submitor' ,width:100 ,align:'center'},
        {text: '接收者', dataIndex: 'receiver',width: 100 ,align:'center'},
        {text: '合作状态', dataIndex: 'status',width: 180 ,align:'center'},
        {text: '发起区域', dataIndex: 'submitDept',width: 100 ,align:'center'},
        {text: '目标区域', dataIndex: 'targetDept',width: 100 ,align:'center'},
        {text: '创建时间', dataIndex: 'cdate',width: 140 ,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '接受合作',id:'accept', iconCls: 'icon4'},
        {xtype: 'button', text: '拒绝合作',id:'refuse', iconCls: 'icon214'},
        {xtype: 'tbfill'},
        {
			xtype: 'combobox',
		    name: 'cooperatorType',
		    id: 'cooperatorType',
		    store: 'cooperate.CooperatorTypeStore',
		    labelWidth: 40,
		    queryMode: 'local',
		    displayField: 'name',
		    emptyText: '请选择合作类型...',
		    valueField: 'val',
		    editable: false
	    },
		{xtype: 'textfield', id:'customer', width:150,emptyText:'请输入客户名称'},
		{xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'},
		{xtype: 'button',text:'清空',iconCls:'icon214',id:'clearAll'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'cooperate.CooperateAcceptStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});