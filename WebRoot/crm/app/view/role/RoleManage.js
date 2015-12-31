Ext.define('crm.view.role.RoleManage',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.rolemanage',
	
	layout: 'border',
	items:[{
		region: 'west',
		xtype: 'panel',
		title: '角色列表',
		width: '50%',
		margins: '0 2 0 1',
		layout: 'fit',
		items:[{xtype: 'rolelist'}]
	},{
		region: 'center',
		xtype: 'panel',
		title: '权限详情',
		id: 'role_priv',
		layout: 'fit',
		html: '<br><br><div align="center" style="color:red">双击角色，查看具体的权限</div>'
	}]
});