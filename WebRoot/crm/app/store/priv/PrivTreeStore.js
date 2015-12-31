Ext.define("crm.store.priv.PrivTreeStore",{
	extend: 'Ext.data.TreeStore',
	model: 'crm.model.priv.PrivModel',
	defaultRootId: 'root',
	proxy: {
		type:'ajax',
		url: 'privilege_tree',
//		url: 'treegrid.json',
		reader:'json'
	},
    folderSort: true
});