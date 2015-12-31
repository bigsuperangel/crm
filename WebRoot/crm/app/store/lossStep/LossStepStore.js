//SalesOppStore.js
Ext.define('crm.store.lossStep.LossStepStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.lossStep.LossStepModel',
    pageSize: 14,
    proxy: {
        type: 'ajax',
        url: 'loss_getList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});