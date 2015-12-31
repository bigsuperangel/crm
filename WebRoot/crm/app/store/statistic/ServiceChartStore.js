Ext.define('crm.store.statistic.ServiceChartStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.statistic.ServiceChartModel',
    proxy: {
        type: 'ajax',
        url: 'statistic_getServiceChart',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});