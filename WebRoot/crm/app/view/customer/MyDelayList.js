/**
 * 我发起的延时申请(维权顾问)
 */
Ext.define('crm.view.customer.MyDelayList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.mydelaylist',
	
    store: 'delay.MyDelayStore',
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
        {xtype: 'button', text: '收回',id:'recall', iconCls: 'icon214'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'delay.MyDelayStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});