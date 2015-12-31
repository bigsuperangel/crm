Ext.define('crm.store.delay.MyDelayStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.delay.DelayModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'delayApply_queryMyAuditList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});