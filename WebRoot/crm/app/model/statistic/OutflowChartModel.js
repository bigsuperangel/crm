Ext.define('crm.model.statistic.OutflowChartModel',{
	extend: 'Ext.data.Model',
    fields: [
		{name: "year", type: "string"},
		{name: "未处理", type: "int"},
		{name: "暂缓处理", type: "int"},
		{name: "确定流失", type: "int"}
    ]
});
