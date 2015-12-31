Ext.define("crm.view.dept.DeptTree",{
	extend: 'Ext.tree.Panel',
	alias: 'widget.depttree',//{xtype:'menutree'}
	rootVisible:false,//不展示ROOT
	displayField:'text',
	animate: true,
	store: 'dept.DeptTreeStore',
	bbar:[
	   {xtype:'button',text:'展开全部',id:'typeOpen',iconCls:'table_add'},
	   {xtype:'button',text:'收起全部',id:'typeClose',iconCls:'table_remove'}
	],
	tbar:[
	   {xtype:'button',text:'添加',id:'add',iconCls:'icon467'},  
	   {xtype:'button',text:'修改',id:'update',iconCls:'icon469'},
	   {xtype:'button',text:'删除',id:'delete',iconCls:'icon468'}
	],
	viewConfig:{
		plugins: {
			ptype: 'treeviewdragdrop'
//			,appendOnly: true //不能拖到叶子节点上放开
		},
		listeners: {
			drop: function(node,data,overModel,dropPosition,dragFunction,options){
				//可以使用ajax技术向后台操作
				Ext.Ajax.request({
					url: 'dept_move',
					params : 'did='+data.records[0].get('id')+'&pid='+overModel.get('id'),
					timeout: 3000,
					//method : 'POST',
					success: function(response,options){
						var json = Ext.decode(response.responseText);
	                    if (json.success) {
//					    	Ext.Msg.alert("提示","移动成功！");
	                    } else {
	                        Ext.Msg.alert("提示","移动失败！");
	                    }
					},
					failure: function(response,options){
						Ext.Msg.alert("系统提示","操作失败，无法发送远程连接！");
					}
				});
			},
			beforedrop: function(node,data,overModel,dropPosition,dragFunction,options){
				if(overModel.get('leaf')){
					overModel.set('leaf',false);
				}
//				if(data.records[0].parentNode.childNodes.length){
//					data.records[0].parentNode.set('leaf',true);
//				}
			}
		}
	}
});