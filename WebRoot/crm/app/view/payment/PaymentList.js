Ext.define('crm.view.payment.PaymentList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.paymentlist',
	
    store: 'payment.PaymentStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '合同编号',  dataIndex: 'agreementNo',flex:1,align:'center'},
        {text: '收款日期', dataIndex: 'receiveDate',flex:2,align:'center'},
        {text: '收款金额', dataIndex: 'receiveAmount',flex:1,align:'center'},
        {text: '支付方式', dataIndex: 'payMode',flex:1,align:'center' },
        {text: '收款人',  dataIndex: 'receiver',flex:1,align:'center'},
        {text: '收款帐号', dataIndex: 'receiveAccount',flex:2,align:'center'},
        {text: '付款人', dataIndex: 'payer',flex:1,align:'center'},
        {text: '付款帐号', dataIndex: 'payAccount',flex:2,align:'center'},
        {text: '状态', dataIndex: 'status',flex:1,align:'center' },
        {text: '创建时间', dataIndex: 'cdate',flex:2,align:'center'},
        {	text: '查看图片',
	        width: 55,
	        xtype: 'actioncolumn',
	        align: 'center',
	        icon: 'resources/icon/icon627.gif',
	        handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
        		console.info(record);
        		var ownerId = record.get('id');
	            window.open('showPhoto?ownerId='+ownerId+'&type=2','_blank',' left=0,top=0,width='+ (screen.availWidth - 10) +',height='+ (screen.availHeight-50) +',resizable=yes,toolbar=no,location=no,directories=no,status=yrd,menubar=no,scrollbars=yes')
	        }
        }
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'addPayment', iconCls: 'icon597'},
        {xtype: 'button', text: '修改',id:'updatePayment', iconCls: 'icon599'},
        {xtype: 'mybutton', text: '删除',id:'deletePayment',cls:'color:red', iconCls: 'icon598'},
        '-',
        {xtype: 'button', text: '上传款项',id:'uploadPayment', iconCls: 'icon1117'}
//        '-',
//        {xtype: 'button', text: '审核',id:'auditPayment', iconCls: 'icon4'},
//        {xtype: 'button', text: '驳回',id:'refusePayment', iconCls: 'icon214'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'payment.PaymentStore',
		dock:'bottom',
		displayInfo:true,
		
		moveLast: function(){
			var me = this;
 			var store = this.store;
 			var limit = store.lastOptions.limit;
 			var totalCount = parseInt((store.totalCount+limit-1)/limit);
 			store.currentPage = totalCount;
 			store.load({
 				params:{
  			       page: totalCount,
  			       cid: me.up('paymentlist').model.get('id')
  			    }
  			});
 		},
 		
 		movePrevious: function(){
 			var me = this;
 			var store = this.store;
 			store.currentPage = store.currentPage - 1;
 			store.load({
 				params:{
  			       page: store.currentPage,
  			       cid: me.up('paymentlist').model.get('id')
  			    }
  			});
 		},
 		
 		moveNext: function(){
 			var me = this;
 			var store = this.store;
 			store.currentPage = store.currentPage + 1;
 			store.load({
 				params:{
  			       page: store.currentPage,
  			       cid: me.up('paymentlist').model.get('id')
  			    }
  			});
 		},
 		
 		moveFirst: function(){
 			var me = this;
 			var store = this.store;
 			store.currentPage = 1;
 			store.load({
 				params:{
  			       page: store.currentPage,
  			       cid: me.up('paymentlist').model.get('id')
  			    }
  			});
 		},
 		onPagingKeyDown : function(field, e){
 	        var me = this,
 	            k = e.getKey(),
 	            pageData = me.getPageData(),
 	            increment = e.shiftKey ? 10 : 1,
 	            pageNum;

 	        if (k == e.RETURN) {
 	            e.stopEvent();
 	            pageNum = me.readPageFromInput(pageData);
 	            if (pageNum !== false) {
 	                pageNum = Math.min(Math.max(1, pageNum), pageData.pageCount);
 	                if(me.fireEvent('beforechange', me, pageNum) !== false){
 	                	me.store.currentPage = pageNum;
 	                	me.store.load({
	                    		params:{
				  			       page: pageNum,
				  			       cid: me.up('paymentlist').model.get('id')
				  			    }
 	                    });
 	                }
 	            }
 	        } else if (k == e.HOME || k == e.END) {
 	            e.stopEvent();
 	            pageNum = k == e.HOME ? 1 : pageData.pageCount;
 	            field.setValue(pageNum);
 	        } else if (k == e.UP || k == e.PAGE_UP || k == e.DOWN || k == e.PAGE_DOWN) {
 	            e.stopEvent();
 	            pageNum = me.readPageFromInput(pageData);
 	            if (pageNum) {
 	                if (k == e.DOWN || k == e.PAGE_DOWN) {
 	                    increment *= -1;
 	                }
 	                pageNum += increment;
 	                if (pageNum >= 1 && pageNum <= pageData.pageCount) {
 	                    field.setValue(pageNum);
 	                }
 	            }
 	        }
 	    },
 		doRefresh: function(){
 			var me = this;
 			var store = this.store;
 			store.load({
 				params:{
  			       page: store.currentPage,
  			       cid: me.up('paymentlist').model.get('id')
  			    }
  			});
 		}
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.currentPage = 1;
			obj.store.load({
				params:{
					page: 1,
					cid: obj.model.get('id')
				}
			});
		}
	}
});