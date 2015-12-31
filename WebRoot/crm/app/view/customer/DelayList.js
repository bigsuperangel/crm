Ext.define('crm.view.customer.DelayList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.delaylist',
	
    store: 'delay.DelayStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	forceFit:true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 130,align:'center'},
        {text: '申请人', dataIndex: 'applier',width: 130 ,align:'center'},
        {text: '延时天数', dataIndex: 'delayDays',width: 130 ,align:'center'},
        {text: '审核状态', dataIndex: 'audit',width: 60 ,align:'center',
        	renderer: function(value) {
        		if(value == 1){
        			return '<span style="color:green">审核通过</span>';
        		}else if(value==2){
        			return '<span style="color:red">审核不通过</span>';
        		}else if(value==0){
        			return '未审核';
        		} else if(value==4){
        			return '<span style="color:red">撤回</span>';
        		}
            }
        },
        {text: '创建时间', dataIndex: 'cdate',width: 140 ,align:'center'},
        {text: '备注', dataIndex: 'remarks',width: 140 ,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '审核',id:'audit', iconCls: 'icon4'},
        {xtype: 'button', text: '驳回',id:'refuse', iconCls: 'icon214'},
        '-',
        {xtype: 'tbfill'},
		{xtype: 'textfield', id:'customerName',emptyText:'请输入客户名称'},
		{xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'},
		{xtype:'button',text:'清空',iconCls:'icon214',id:'clearAll'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'delay.DelayStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});