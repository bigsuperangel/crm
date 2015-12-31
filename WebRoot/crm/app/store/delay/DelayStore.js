Ext.define('crm.store.delay.DelayStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.delay.DelayModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'delayApply_queryUnAuditList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});