//SalesOppStore.js
Ext.define('crm.store.salesOpp.SalesOppStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.salesOpp.SalesOppModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'salesOpp_salesOppList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});