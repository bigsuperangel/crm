Ext.define('crm.view.role.RoleList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.rolelist',
	
    store: 'role.RoleStore',
    columnLines: true,
    selModel: {
		selType: 'checkboxmodel'
	},
	multiSelect: true,
	border: 0,
	
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '角色名称',  dataIndex: 'name',flex: 1,align:'center'},
        {text: '用户数量',  dataIndex: 'userCount',flex: 1,align:'center'},
        {text: '拥有最大客户数',  dataIndex: 'count',flex: 2,align:'center'}
    ],
    tbar: [
        {xtype: 'button', text: '添加',id:'add', iconCls: 'icon835'},
        {xtype: 'button', text: '修改',id:'update', iconCls: 'icon837'},
        '-',
        {xtype: 'mybutton', text: '删除',id:'delete',cls:'color:red', iconCls: 'icon836'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store: 'role.RoleStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		},
		itemdblclick: function(obj, record, item, index, e, eOpts ){
			
			var priv = Ext.getCmp('role_priv');
			priv.removeAll();
			priv.add({xtype:'privtree', roleId: record.get('id')});
		}
	}
});