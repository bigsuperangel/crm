//SalesOppStore.js
Ext.define('crm.store.customer.CustomerStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.customer.CustomerModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'customer_getMyCustomerList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});