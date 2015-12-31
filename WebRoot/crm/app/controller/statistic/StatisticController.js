Ext.define('crm.controller.statistic.StatisticController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			
		});
	},
	views : [
	    'statistic.Statistic',
	    'statistic.SalesOppStatistic',
	    'statistic.ServiceStatistic',
	    'statistic.OutflowStatistic'
	],
	stores : [
	    'statistic.SalesOppChartStore',
	    'statistic.ServiceChartStore',
	    'statistic.OutflowChartStore'
	],
	models : [
	    'statistic.SalesOppChartModel',
	    'statistic.ServiceChartModel',
	    'statistic.OutflowChartModel'
	]
});