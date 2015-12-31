Ext.define('crm.store.user.UserStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.user.UserModel',
	pageSize: limit,
	proxy: {
		type: 'ajax',
		url: 'user_getList',
		reader: {
			type: 'json',
			root: 'topics',
			totalProperty: 'total'
		}
	},
	autoLoad: false// {start: 0, limit: limit}
});