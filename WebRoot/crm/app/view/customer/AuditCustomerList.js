/**
 * 审核客户
 */
Ext.define('crm.view.customer.AuditCustomerList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.auditcustomerlist',
	
    store: 'customer.AuditCustomerStore',
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
        {text: '公司类型', dataIndex: 'companyType',width: 60 ,align:'center',
        	renderer: function(value) {
            	if(value == 1){
        			return "品牌商";
        		}else if(value == 2){
        			return "代运营商";
        		}
            }
        },
        {text: '销售模式', dataIndex: 'saleMode',width: 60 ,align:'center',
        	renderer: function(value) {
            	if(value == 1){
        			return "直营";
        		}else if(value == 1){
        			return "分销";
        		}else if(value==2){
        			return "直营和分销";
        		}
            }
        },
//        {text: '公司知名度', dataIndex: 'companyAwareness',width: 60 ,align:'center',
//        	renderer: function(value) {
//            	if(value == 1){
//        			return "高";
//        		}else if(value == 2){
//        			return "中";
//        		}else if(value==3){
//        			return "低";
//        		}
//            }
//        },
//        {text: '品牌知名度', dataIndex: 'brandAwareness',width: 60 ,align:'center',
//        	renderer: function(value) {
//            	if(value == 1){
//        			return "一线";
//        		}else if(value == 2){
//        			return "二线";
//        		}else if(value==3){
//        			return "三线";
//        		}
//            }
//        },
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
        {text: '创建人', dataIndex: 'createrName',width: 60 ,align:'center'},
        {text: '跟进人', dataIndex: 'handlerName',width: 60 ,align:'center'},
        {text: '创建时间', dataIndex: 'cdate',width: 140 ,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '审核',id:'audit', iconCls: 'icon4'},
        {xtype: 'button', text: '驳回',id:'refuse', iconCls: 'icon214'},
        '-',
        {xtype: 'tbfill'},
         {	xtype: 'combobox',
				    id:'auditType',
				    store: {
				    	xtype: 'store',
				        fields: ['val', 'name'],
				        data : [
							{"val":0, "name":"未审核"},
							{"val":1, "name":"已审核"},
							{"val":-1, "name":"已驳回"}
				        ]
				    },
				    labelWidth: 60,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'val',
					emptyText: '请选择审核状态...',
				    editable: false
		        },
        {xtype: 'textfield', id:'customer', width:150,emptyText:'请输入客户名称'},
        {xtype: 'textfield', id:'brandName', width:150,emptyText:'请输入品牌名称'},
        {emptyText:'起始日期',xtype:'datefield',id:'startTime',editable:false, width:150,format:'Y-m-d'},
        {emptyText:'结束日期',xtype:'datefield',id:'endTime',editable:false,width:150,format:'Y-m-d'},
        {xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'},
        {xtype:'button',text:'清空',iconCls:'icon214',id:'clearAll'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'customer.AuditCustomerStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
//			obj.store.getProxy().url = 'salesOpp_salesOppList';
			obj.store.load({
				params:{
					audit:0
				}
			});
		}
	}
});