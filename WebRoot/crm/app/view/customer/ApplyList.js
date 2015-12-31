/**
 * 审核公海客户
 * @param {Object} obj
 * @param {Object} eOpts
 */
Ext.define('crm.view.customer.ApplyList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.applylist',
	
    store: 'apply.ApplyStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	forceFit:true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 130,align:'center'},
        {text: '申请人', dataIndex: 'applierName',width: 130 ,align:'center'},
        {text: '审核状态', dataIndex: 'status',width: 60 ,align:'center',
        	renderer: function(value) {
        		if(value == 1){
        			return '<span style="color:green">审核通过</span>';
        		}else if(value==-1){
        			return '<span style="color:red">审核不通过</span>';
        		}else
        		return '未审核';
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
		store: 'apply.ApplyStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load({
				params:{
					'audit':0
				}
			});
		}
	}
});