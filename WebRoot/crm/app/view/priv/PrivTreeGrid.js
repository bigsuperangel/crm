Ext.define('crm.view.priv.PrivTreeGrid', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.privtreegrid',
    
    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.tree.*',
        'crm.model.priv.PrivModel'
    ],    
//    xtype: 'tree-grid',
    
//    title: '菜单列表',
    height: 300,
    useArrows: true,
    rootVisible: false,
    multiSelect: true,
    singleExpand: true,
    
    initComponent: function() {
        this.width = 600;
        
        Ext.apply(this, {
            store: 'priv.PrivTreeStore',
//        	store: new Ext.data.TreeStore({
//                model: 'crm.model.priv.PrivModel',
//                proxy: {
//                    type: 'ajax',
//                    url: 'treegrid.json'
//                },
//                folderSort: true
//            }),
            columns: [{
                xtype: 'treecolumn', //this is so we know which column will show the tree
                text: '名称',
                flex: 1,
                sortable: true,
                dataIndex: 'name'
            }, {
                text: '配置',
                flex: 1,
                dataIndex: 'config',
                sortable: true
	        },{
                text: '样式',
                flex: 1,
                dataIndex: 'iconCls',
                sortable: true
	        },{
                text: '排序',
                flex: 1,
                dataIndex: 'indx',
                sortable: true
	        },{
                text: 'Add',
                width: 55,
//                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '新增菜单',
                align: 'center',
                icon: 'resources/icon/icon611.gif',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
            		console.info(record);
            		if(record.get('leaf') && record.parentNode.data.id){
            			MsgTip.msg('系统提示', '不能在子类别新增菜单');
						return ;
            		}
					var win = Ext.create('Ext.window.Window',{
						title: ' 新增菜单',
						iconCls: this.icon,
						width: 300,
						height: 200,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'addpriv', model: record, store:grid.store, ch:-1}]
					});
					win.show();
                },
                // Only leaf level tasks may be edited
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                	console.info(record);
                	return false;
//                    return !record.data.leaf;
                }
            }, {
                text: 'Edit',
                width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '修改菜单',
                align: 'center',
                icon: 'resources/icon/icon612.gif',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
            		console.info(record);
            		var win = Ext.create('Ext.window.Window',{
						title: ' 修改菜单',
						iconCls: this.icon,
						width: 300,
						height: 200,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'updatepriv', model: record, store:grid.store, ch:-1}]
					});
					win.show();
                },
                // Only leaf level tasks may be edited
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                	console.info(record);
                    return !record.data.leaf;
                }
            }, {
                text: 'Del',
                width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: '删除菜单',
                align: 'center',
                icon: 'resources/icon/icon613.gif',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
            		console.info(record);
					var ids = [];
            		if(record.get('leaf')){
            			ids.push(record.get('id'));
            		}else{
            			record.eachChild(function(rec){ 
							ids.push(rec.get('id'));
						});
            		}
					Ext.MessageBox.confirm('系统提示', '您是否要删一下：<span style="color:red"><br>['+record.get('name')+']</span><br>删除后不可恢复', function(optional){
						if(optional == 'yes' || optional == '是'){
							Ext.Ajax.request({
							    url: 'priv_delete',
							    params: {
							        ids: ids
							    },
							    success: function(response){
							    	var json = Ext.decode(response.responseText);
				                    if (json.success) {
								    	grid.store.load();
								    	Ext.Msg.alert("提示","删除成功！");
				                    } else {
				                        Ext.Msg.alert("提示","删除失败！");
				                    }
							    },
							    failure: function(response){
							    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
							    }
							});
						}
					});
                },
                // Only leaf level tasks may be edited
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                	console.info(record);
                    return !record.data.leaf;
                }
            }],
            tbar:[
			   {xtype:'button',text:'添加类别',id:'add',iconCls:'icon467'}
			]
        });
        this.callParent();
    }
});
