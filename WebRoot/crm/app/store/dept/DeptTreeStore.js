Ext.define("crm.store.dept.DeptTreeStore",{
	extend: 'Ext.data.TreeStore',
	defaultRootId: 'root',
	proxy: {
		type:'ajax',
		url: 'dept_getDeptTree',
		reader:'json'
	}
});