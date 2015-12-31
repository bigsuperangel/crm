Ext.define('crm.view.customer.PublicCustomerApply',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.publiccustomerapply',
	
    store: 'customer.PublicCustomerStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '姓名',  dataIndex: 'name',width: 80,align:'center'},
        {text: '性别', dataIndex: 'sex',width: 40,align:'center',
        	renderer: function(value) {
        		if(value == 1){
        			return '<span style="color:red">男</span>';
        		}
        		return '<span style="color:green">女</span>';
            }
        },
        {text: '职务', dataIndex: 'post',width: 80 ,align:'center'},
        {text: '联系电话', dataIndex: 'phone',width: 90,align:'center'},
        {text: '手机号码', dataIndex: 'tel',width: 90,align:'center'},
        {text: 'QQ', dataIndex: 'qq',width: 80 ,align:'center'},
        {text: '旺旺', dataIndex: 'wangWang',width: 90,align:'center'},
        {text: 'EMAIL', dataIndex: 'email',width: 90,align:'center'},
        {text: '描述', dataIndex: 'descr',flex:1,align:'center' },
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'addPublicCustomer', iconCls: 'icon869'},
        {xtype: 'button', text: '修改',id:'updatePublicCustomer', iconCls: 'icon871'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'deletePublicCustomer',cls:'color:red', iconCls: 'icon870'},
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'customer.PublicCustomerStore',
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
  			       cid: me.up('publiccustomerapply').model.get('id')
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
  			       cid: me.up('publiccustomerapply').model.get('id')
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
  			       cid: me.up('publiccustomerapply').model.get('id')
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
  			       cid: me.up('publiccustomerapply').model.get('id')
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
				  			       cid: me.up('customerlist').model.get('id')
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
  			       cid: me.up('publiccustomerapply').model.get('id')
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