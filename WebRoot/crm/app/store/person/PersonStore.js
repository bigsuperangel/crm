//SalesOppStore.js
Ext.define('crm.store.person.PersonStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.person.PersonModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'person_showList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});