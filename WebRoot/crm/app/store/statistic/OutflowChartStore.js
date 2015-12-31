Ext.define('crm.store.statistic.OutflowChartStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.statistic.OutflowChartModel',
    proxy: {
        type: 'ajax',
        url: 'statistic_getOutflowChart',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});