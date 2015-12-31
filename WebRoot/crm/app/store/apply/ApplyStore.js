Ext.define('crm.store.apply.ApplyStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.apply.ApplyModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'customer_getApplyList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});