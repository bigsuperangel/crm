//SalesOppStore.js
Ext.define('crm.store.cooperate.CooperateAcceptStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.cooperate.CooperateModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'cooperation_query_accept',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});