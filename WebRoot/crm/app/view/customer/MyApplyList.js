/**
 * 我的公海客户申请
 * @param {Object} obj
 * @param {Object} eOpts
 */
Ext.define('crm.view.customer.MyApplyList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.myapplylist',
	
    store: 'apply.MyApplyStore',
    columnLines: true,
    selModel: {
		selType: 'rowmodel'
	},
	multiSelect: true,
	forceFit:true,
	
    columns: [
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
//		{xtype: 'textfield', id:'keyword'},
//		{xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'apply.MyApplyStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});