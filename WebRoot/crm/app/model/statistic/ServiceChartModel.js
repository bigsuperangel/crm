Ext.define('crm.model.statistic.ServiceChartModel',{
	extend: 'Ext.data.Model',
    fields: [
		{name: "year", type: "string"},
		{name: "未分配", type: "int"},
		{name: "已分配", type: "int"},
		{name: "已处理", type: "int"},
		{name: "已归档", type: "int"}
    ]
});
