//SalesOppStore.js
Ext.define('crm.store.plan.PlanStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.plan.PlanModel',
    pageSize: limit,
    proxy: {
        type: 'ajax',
        url: 'plan_showMyPlan',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});