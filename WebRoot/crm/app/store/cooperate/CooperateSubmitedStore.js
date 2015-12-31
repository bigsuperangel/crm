//SalesOppStore.js
Ext.define('crm.store.cooperate.CooperateSubmitedStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.cooperate.CooperateModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'cooperation_query_submited',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});