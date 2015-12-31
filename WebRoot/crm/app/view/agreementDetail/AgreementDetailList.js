Ext.define('crm.view.agreementDetail.AgreementDetailList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.agreementdetaillist',
	
    store: 'agreementDetail.AgreementDetailStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: 'value1', dataIndex: 'value1',width: 40,align:'center'},
        {text: 'value2', dataIndex: 'value2',width: 80 ,align:'center'},
        {text: 'value3', dataIndex: 'value3',width: 90,align:'center'},
        {text: 'value4', dataIndex: 'value4',width: 90,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'addAgreementDetail', iconCls: 'icon869'},
        {xtype: 'button', text: '修改',id:'updateAgreementDetail', iconCls: 'icon871'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'deleteAgreementDetail',cls:'color:red', iconCls: 'icon870'},
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'agreementDetail.AgreementDetailStore',
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
  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
				  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
  			       cid: me.up('agreementdetaillist').model.get('agreementId')
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
					cid: obj.model.get('agreementId')
				}
			});
		}
	}
});