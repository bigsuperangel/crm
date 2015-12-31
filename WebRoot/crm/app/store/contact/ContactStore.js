//SalesOppStore.js
Ext.define('crm.store.contact.ContactStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.contact.ContactModel',
    pageSize: 14,
    proxy: {
        type: 'ajax',
        url: 'contact_getList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});