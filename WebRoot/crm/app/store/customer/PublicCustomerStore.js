//SalesOppStore.js
Ext.define('crm.store.customer.PublicCustomerStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.customer.CustomerModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'customer_getPublicList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});