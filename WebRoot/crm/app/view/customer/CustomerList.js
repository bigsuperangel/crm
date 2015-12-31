Ext.define('crm.view.customer.CustomerList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.customerlist',
	
    store: 'customer.CustomerStore',
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
        {text: '合作开始时间', dataIndex: 'auditTime',width: 140 ,align:'center'},
        {text: '释放时间', dataIndex: 'freeTime',width: 140 ,align:'center'}
    ],
    tbar: {
    	xtype:"container",
        border:false,
        items:[{
              //tbar第二行工具栏
              xtype:"toolbar",
              items:[
		        {xtype: 'textfield', id:'customer', width:150,emptyText:'请输入客户名称'},
		        {xtype: 'textfield', id:'brandName', width:150,emptyText:'请输入品牌名称'},
		        {emptyText:'起始日期',xtype:'datefield',id:'startTime',editable:false, width:150,format:'Y-m-d'},
		        {emptyText:'结束日期',xtype:'datefield',id:'endTime',editable:false,width:150,format:'Y-m-d'},
		        {xtype: 'button', text: '搜索', iconCls: 'icon1527', id: 'search'},
		        {xtype:'button',text:'清空',iconCls:'icon214',id:'clearAll'}
        	]
        },{
              //tbar第一行工具栏
              xtype:"toolbar",
              items:[
				{xtype: 'button', text: '添加',id:'add', iconCls: 'icon681'},
		        {xtype: 'button', text: '修改',id:'update', iconCls: 'icon683'},
		        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon682'},
		        '-',
		        {xtype: 'button', text: '查看联系人',id:'showPerson', iconCls: 'icon945'},
		        {xtype: 'button', text: '查看交往记录',id:'showContact', iconCls: 'icon1850'},
		        '-',
		        {xtype: 'button', text: '分配客户',id:'assign', iconCls: 'icon1003'},
		        {xtype: 'button', text: '建立合同',id:'addAgreement', iconCls: 'icon681'},
		        {xtype: 'button', text: '延时',id:'delayApply', iconCls: 'icon818'}        
        	]
        }]
        

//		{xtype: 'textfield', id:'keyword', emptyText:'请输入客户名称或品牌',enableKeyEvents: true, listeners: {
//			change: function(obj, newValue, oldValue, eOpts ){
//				if(obj.getValue().length == 0){
//					var store = obj.up('customerlist').store;
//					store.getProxy().url = 'customer_getMyCustomerList'; 
//					store.load();
//				}
//			},
//			keypress: function(obj, e, eOpts ){
//				if(e.getKey() == 13){
//					var store = obj.up('customerlist').store;
//					var key = obj.getValue();
//					if(key.length == 0){
//						return ;	
//					}
//					store.removeAll(true);
//			    	store.getProxy().url = 'customer_getMyCustomerList'; 
//			    	store.load({
//			    		params:{
//		  			       keyword:key
//		  			       ,url: url
//		  			    }
//			    	});
//		        }  
//			}
//		}},
		
    },
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'customer.CustomerStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
//			obj.store.getProxy().url = 'salesOpp_salesOppList';
			if(user.isSupervisor<1){ //经理 ，总监
				Ext.getCmp('assign').disable();
			}
			obj.store.load();
		},
		itemclick: function(obj, record, item, index, e, eOpts){
			var auditType = record.raw.audit;
			var freeTime = record.raw.freeTime ;
			var today = Ext.Date.format(new Date(), 'Y-m-d').substr(0,10);
			if(freeTime){
				freeTime = freeTime.substr(0,10);
			}
			if(auditType!=1 || !freeTime || today==freeTime){//合同必须审核，开始跟进才能新建
				Ext.getCmp('addAgreement').disable();
			}else{
				Ext.getCmp('addAgreement').enable();
			}
			if(auditType==1){  //联系跟进必须审核才能跟进
				Ext.getCmp('showContact').enable();
			}else{
				Ext.getCmp('showContact').disable();
			}
		}
	}
});