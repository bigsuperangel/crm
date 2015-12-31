Ext.define("crm.store.menu.MenuTreeStore",{
	extend: 'Ext.data.TreeStore',
	defaultRootId: 'root',
	proxy: {
		type:'ajax',
		url: 'user_getMenu',
		reader:'json'
	}
});