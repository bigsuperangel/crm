Ext.define('crm.store.payment.PaymentStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.payment.PaymentModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'payment_queryList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});