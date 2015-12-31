Ext.define('crm.controller.role.RoleController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'rolelist button[id=add]':{
				click:function(btn){
					addFun(btn, 'rolelist', '添加角色', 300, 150, 'addrole');
				}
			},
			'rolelist button[id=update]':{
				click: function(btn){
					updateFun(btn, 'rolelist', '您没有选择要修改的角色，请先选择角色', '修改角色', 'updaterole', 300, 150);
				}
			},
			'rolelist button[id=delete]':{
				click: function(btn){
					delFun(btn, 'rolelist', '角色', 'role_delete', 'name', 'id');
				}
			},
		});
	},
	views : [
	    'role.RoleManage',
	    'role.RoleList',
	    'role.PrivTree',
	    'role.AddRole',
	    'role.UpdateRole'
	],
	stores : [
	    'role.RoleComboboxStore',
	    'role.RoleStore'
	],
	models : [
	    'role.RoleModel'
	]
});