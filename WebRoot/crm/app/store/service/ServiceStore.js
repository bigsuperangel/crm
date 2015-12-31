//SalesOppStore.js
Ext.define('crm.store.service.ServiceStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.service.ServiceModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'service_getUnassignationList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});