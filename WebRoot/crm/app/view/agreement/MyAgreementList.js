/**
 * 维权顾问查看自己增加的合同列表
 */
Ext.define('crm.view.agreement.MyAgreementList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.myagreementlist',
	
    store: 'agreement.AgreementStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '合同编号',  dataIndex: 'agreementNo',flex:1,align:'center'},
        {text: '项目名称', dataIndex: 'projectName',flex:1 ,align:'center'},
        {text: '签约日期', dataIndex: 'signDate',flex:1,align:'center'},
        {text: '完款日期', dataIndex: 'paymentClearDate',flex:1,align:'center'},
        {text: '完成日期', dataIndex: 'finishDate',flex:1,align:'center' },
        {text: '合同金额',  dataIndex: 'amount',flex:1,align:'center'},
        {text: '子合同数', dataIndex: 'subAgreementCount',flex:1 ,align:'center'},
//        {text: '收款状态', dataIndex: 'paymentStatus',flex:1,align:'center'},
        {text: '我方签约人', dataIndex: 'mySigner',flex:1,align:'center'},
        {text: '客户签约人', dataIndex: 'customerSigner',flex:1,align:'center' },
//        {text: '备注', dataIndex: 'remark',flex:1 ,align:'center'},
        {text: '合同状态', dataIndex: 'status',flex:1 ,align:'center',
        	renderer: function(value) {
        		if(value == 1){
        			return '<span style="color:yellow">已发出</span>';
        		}else if(value==2){
        			return '<span style="color:green">已签约</span>';
        		}else if(value==0){
        			return '新建合同';
        		} else if(value==3){
        			return '<span style="color:blue">已到款</span>';
        		}else if(value==4){
        			return '<span style="color:pink">已付清</span>';
        		} else {
        			return '<span style="color:purple">已完成</span>';
        		}
            }
        },
        {text: '是否开票', dataIndex: 'isReceipt',flex:1,align:'center',
        	renderer: function(value) {
        		if(value == 1){
        			return '<span style="color:green">是</span>';
        		}else if(value==0){
        			return '<span style="color:red">否</span>';
        		}
            }
        },
        {	text: '下载附件',
	        width: 55,
	        xtype: 'actioncolumn',
	        align: 'center',
	        icon: 'resources/icon/icon399.png',
	        handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
        		Ext.Ajax.request({
				    url: 'attachment_queryList',
				    params: {
				        ownerId: record.get('id'),
				        type:1
				    },
				    success: function(response){
				    	var json = Ext.decode(response.responseText);
				    	if(json){
				    		window.location = ww.common.hostUrl + json[0].url;
				    	}else{
				    		Ext.Msg.alert("提示信息", '无相关合同');
				    	}
				    },
				    failure: function(response){
				    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
				    }
				});
//        		window.location="http://localhost:7080/crm/upload/款项/20150302/1.doc"; 
//	            window.open('showPhoto?ownerId=1','_blank',' left=0,top=0,width='+ (screen.availWidth - 10) +',height='+ (screen.availHeight-50) +',resizable=yes,toolbar=no,location=no,directories=no,status=yrd,menubar=no,scrollbars=yes')
	        }
        }
    ],
    tbar: [
    	{xtype: 'button', text: '修改',id:'update', iconCls: 'icon871'},
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon870'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'agreement.AgreementStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
});