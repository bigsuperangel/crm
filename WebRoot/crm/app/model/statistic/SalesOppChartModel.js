Ext.define('crm.model.statistic.SalesOppChartModel',{
	extend: 'Ext.data.Model',
    fields: [
		{name: "year", type: "string"},
		{name: "已分配", type: "int"},
		{name: "未分配", type: "int"},
		{name: "开发成功", type: "int"},
		{name: "开发失败", type: "int"}
    ]
});