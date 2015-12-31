//SalesOppStore.js
Ext.define('crm.store.role.RoleStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.role.RoleModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'role_getList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});