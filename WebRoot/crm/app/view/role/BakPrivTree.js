Ext.define('crm.view.role.PrivTreeBrk',{
	alias: 'widget.privtreebrk',
	
	constructor: function(config){
		var roleId = config.roleId;
		var store = Ext.create('Ext.data.TreeStore', {
//			defaultRootId: 'root',
			nodeParam: 'roleId',
			defaultRootId: roleId,
			fields: ['text', 'done','leaf'],
			proxy: {
				type:'ajax',
				url: 'role_getPriv',
				reader:'json'
			}
		});
		var eachNode = function(node,checked){
			console.info('leaf:'+node.get('leaf') + "---"+checked)
//			if(!node.get('leaf')){
//				tree.store.suspendEvents(); // avoid view update after each row
				node.eachChild(function(rec){ 
					console.info(rec);
					rec.set('done', checked);
					eachNode(rec,checked);
				});
//				tree.store.resumeEvents();
//			}
//			tree.getView().refresh();
		}
		var checkParent = function(node){
			var flag = false;  //判断父节点是否要取消 ，只有字节点全部都没勾的时候取消
			if(node){
				node.eachChild(function(rec){ 
					if(rec.get('done')){
						flag=true;
						return flag;
					}else{
						checkParent(rec);
					}
				});
			}
			console.info(flag);
			return flag;
		}
		var eachParentNode = function(node,checked){
			var parentNode = node.parentNode;
			if(checkParent(parentNode)){
				parentNode.set('done',true);
			}else{
				parentNode.set('done',false);
			}
//			eachParentNode(parentNode,checked);
		}
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
						var node = tree.store.getNodeById(rowIndex+1);
						console.info(node);
						var flag = checked;
						if(!node.get('leaf')){
							tree.store.suspendEvents(); // avoid view update after each row
							eachNode(node,flag);
							tree.store.resumeEvents();
							tree.getView().refresh();
						}else{
							eachParentNode(node,checked);
						}
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