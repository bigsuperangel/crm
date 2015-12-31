Ext.define('crm.view.role.PrivTree',{
	alias: 'widget.privtree',
	
	constructor: function(config){
		var roleId = config.roleId;
		var store = Ext.create('Ext.data.TreeStore', {
			defaultRootId: 'root',
			nodeParam: 'roleId',
			defaultRootId: roleId,
			fields: ['text', 'done'],
			proxy: {
				type:'ajax',
				url: 'role_getPriv',
				reader:'json'
			}
		});
		
		var tree = Ext.create('Ext.tree.Panel', {
		    store: store,
		    rootVisible: false,
		    columns: [{
				xtype: 'treecolumn',
				text: '操作菜单',
				dataIndex: 'text',
				width: '40%'
			},{
				xtype: 'checkcolumn',
				text: '是否拥有',
				dataIndex: 'done',
				align: 'center',
				witdh: 100,
				listeners:{
					checkchange: function(obj, rowIndex, checked, eOpts ){
//						console.log(obj.get('text'));
//						var node = tree.store.getNodeById(1);
//						console.log(node.get('text'));
					}
				}
			}],
			bbar:[{xtype: 'tbfill'},{
				xtype:'button',
				text: '保存修改',
				iconCls: 'icon521',
				handler: function() {
				    var data = store.getUpdatedRecords();
				    var rid = [];//removeId
					var pid = [];//putId
					Ext.Array.each(data, function(model){
						if(model.get('done')){
							pid.push(model.get('id'));	
						}else{
							rid.push(model.get('id'));	
						}
					});
					Ext.Ajax.request({
					    url: 'role_changePriv',
					    params: {
					    	roleId: roleId,
					        remId: rid,
					        putId: pid
					    },
					    success: function(response){
//				    		store.sync();
					    	store.reload();
				    		MsgTip.msg('系统提示','操作成功！添加了'+pid.length+'个权限，取掉了'+rid.length+'个权限。');
					    },
					    failure: function(response){
					    	Ext.MessageBox.alert('系统提示','由于网络原因，删除操作失败！');
					    }
					});
				}
			}]
		});
		return tree;
	}
});