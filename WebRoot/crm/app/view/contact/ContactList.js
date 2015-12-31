Ext.define('crm.view.contact.ContactList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.contactlist',
	
    store: 'contact.ContactStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '客户名称',  dataIndex: 'customerName',width: 80,align:'center'},
        {text: '概要', dataIndex: 'title',flex:1,align:'center'},
//        {text: '详情', dataIndex: 'content',width: 80 ,align:'center'},
        {text: '地点', dataIndex: 'addr',width: 90,align:'center'},
        {text: '负责人', dataIndex: 'contactor',width: 80 ,align:'center'},
        {text: '联系人', dataIndex: 'supervisor',width: 80 ,align:'center'},
        {text: '下次回访时间', dataIndex: 'nextVisit',width: 90,align:'center'},
        {text: '时间', dataIndex: 'cdate',width: 90,align:'center'},
        {text: '创建人员', dataIndex: 'userName',width:60,align:'center' },
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'addContact', iconCls: 'icon869'},
        {xtype: 'button', text: '修改',id:'updateContact', iconCls: 'icon871'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'deleteContact',cls:'color:red', iconCls: 'icon870'},
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'contact.ContactStore',
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
  			       cid: me.up('contactlist').model.get('id')
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
  			       cid: me.up('contactlist').model.get('id')
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
  			       cid: me.up('contactlist').model.get('id')
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
  			       cid: me.up('contactlist').model.get('id')
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
				  			       cid: me.up('contactlist').model.get('id')
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
  			       cid: me.up('contactlist').model.get('id')
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
		},
		itemdblclick: function(obj, record, item, index, e, eOpts ){
			Ext.MessageBox.alert('交往详情',record.get('content'));
		}
	}
});