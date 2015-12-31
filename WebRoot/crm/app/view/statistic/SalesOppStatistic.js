Ext.define('crm.view.statistic.SalesOppStatistic',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.salesoppstatistic',
	
    store: 'statistic.SalesOppChartStore',
    columnLines: true,
	border: 0,
    columns: [
        {xtype: 'rownumberer',align:'center',width: 30},
        {text: '年月',  dataIndex: 'year',width: 80,align:'center'},
        {text: '已分配', dataIndex: '已分配',width: 80 ,align:'center'},
        {text: '未分配', dataIndex: '未分配',width: 80,align:'center'},
        {text: '开发成功', dataIndex: '开发成功',width: 80,align:'center'},
        {text: '开发失败', dataIndex: '开发失败',width: 80,align:'center'},
    ]
});