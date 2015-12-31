Ext.define('crm.view.customer.PublicCustomerList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.publiccustomerlist',
	
    store: 'customer.PublicCustomerStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	forceFit:true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 130,align:'center'},
        {text: '品牌名称', dataIndex: 'brand',width: 130 ,align:'center'},
        { text: '所在部门', dataIndex: 'deptName' ,width:80 ,align:'center'},
        {text: '代运营品牌数', dataIndex: 'operateBrand',width: 80 ,align:'center'},
        {text: '创建人', dataIndex: 'createrName',width: 60 ,align:'center'},
        {text: '跟进人', dataIndex: 'handlerName',width: 60 ,align:'center'},
//        {text: '是否合作过', dataIndex: 'isCooperate',width: 80 ,align:'center'},
        {text: '是否签约', dataIndex: 'isSign',width: 60 ,align:'center',
        	renderer: function(value) {
                	if(value == 0){
	        			return '<span style="color:red">未签约</span>';
	        		}else if(value == 1){
	        			return '<span style="color:green">已签约</span>';
	        		}
            }
        },
        {text: '是否审核', dataIndex: 'audit',width: 60 ,align:'center',
        	renderer: function(value) {
                	if(value == 0){
	        			return "未审核";
	        		}else if(value == 1){
	        			return '<span style="color:green">已审核</span>';
	        		}else if(value=-1){
	        			return '<span style="color:red">已驳回</span>';
	        		}
            }
        },
        {text: '合作状态', dataIndex: 'status',width: 60 ,align:'center',
        	renderer: function(value) {
                	if(value == 0){
	        			return '<span style="color:red">本地客户</span>';
	        		}else if(value == 1){
	        			return '<span style="color:green">合作客户</span>';
	        		}
            }
        },
        {text: '创建时间', dataIndex: 'cdate',width: 140 ,align:'center'},
        {text: '合作开始时间', dataIndex: 'auditTime',width: 140 ,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '捡起',id:'pickup', iconCls: 'icon419'},
        '-',
        {xtype: 'tbfill'},
        {xtype: 'textfield', id:'customer', width:150,emptyText:'请输入客户名称'},
        {xtype: 'textfield', id:'brandName', width:150,emptyText:'请输入品牌名称'},
        {emptyText:'起始日期',xtype:'datefield',id:'startTime',editable:false, width:150,format:'Y-m-d'},
        {emptyText:'结束日期',xtype:'datefield',id:'endTime',editable:false,width:150,format:'Y-m-d'},
        {xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'},
        {xtype:'button',text:'清空',iconCls:'icon214',id:'clearAll'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'customer.PublicCustomerStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});