Ext.define('crm.store.statistic.SalesOppChartStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.statistic.SalesOppChartModel',
    proxy: {
        type: 'ajax',
        url: 'statistic_getSalesOppChart',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});