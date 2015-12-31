Ext.define('crm.store.apply.MyApplyStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.apply.ApplyModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'customer_getMyApplyList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});