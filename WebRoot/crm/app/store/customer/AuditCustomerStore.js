//SalesOppStore.js
Ext.define('crm.store.customer.AuditCustomerStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.customer.CustomerModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'customer_getAuditList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});