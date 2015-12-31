//SalesOppStore.js
Ext.define('crm.store.outflow.OutflowStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.outflow.OutflowModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'outflow_getList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});