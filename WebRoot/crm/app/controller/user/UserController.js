Ext.define('crm.controller.user.UserController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'userlist button[id=add]':{
				click:function(btn){
					addFun(btn, 'userlist', '添加用户', 400, 280, 'adduser');
				}
			},
			'userlist button[id=update]':{
				click: function(btn){
					updateFun(btn, 'userlist', '您没有选择要修改的用户，请先选择用户', '修改用户', 'updateuser', 400, 280);
				}
			},
			'userlist button[id=delete]':{
				click: function(btn){
					delFun(btn, 'userlist', '用户', 'user_delete', 'realName', 'id');
				}
			},
			'userlist button[id=updatepwd]':{
				click: function(btn){
					updateFun(btn, 'userlist', '您没有选择要修改的用户，请先选择用户', '修改用户密码','updatepwduser', 300, 200);
				}
			},
			'userlist button[id=ok]':{
				click: function(btn){
					Ext.getCmp('ok').disable();
					Ext.getCmp('forbit').enable();
					
					var store = btn.up('userlist').store;
					store.getProxy().url = 'user_getList';
					store.load();
				}
			},
			'userlist button[id=forbit]':{
				click: function(btn){
					Ext.getCmp('forbit').disable();
					Ext.getCmp('ok').enable();

					var store = btn.up('userlist').store;
					store.getProxy().url = 'user_getForbitList';
					store.load();
				}
			}
		});
	},
	views : [
	    'user.UserList',
	    'user.AddUser',
	    'user.UpdateUser',
	    'user.UpdatePwdUser'
	],
	stores : [
	    'user.UserComboboxStore',
	    'user.UserStore'
	],
	models : [
	    'user.UserModel'
	]
});
