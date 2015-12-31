Ext.define('crm.view.chart.OutflowChart',{
	extend: 'Ext.chart.Chart',
	alias: 'widget.outflowchart',
	
	store: 'statistic.OutflowChartStore',
	legend: {position: 'right'},
    axes: [{
        type: 'Numeric',
        position: 'left',
        title:"数量",
        fields:['未处理','暂缓处理','确定流失'],
        minimum:0
    },{
        type: 'Category',
        position: 'bottom',
        fields: ['year'],
        title: "年月"
    }],
    series: [{
        type: 'column',
        axis: 'left',
        xField: 'year',
        yField: ['未处理','暂缓处理','确定流失'],
        label: {
            display: 'insideEnd',
            'text-anchor': 'middle',
            field: ['未处理','暂缓处理','确定流失'],
            orientation: 'horizontal',
            fill: '#fff',
            font: '14px Arial'
        },
        tips: {
            trackMouse: true,
            renderer: function(storeItem, item) {
                this.setTitle('<div align="center">'+String(item.value[1])+'</div>');
            }
        }
    }],
	listeners:{
		beforerender: function(obj, eOpts ){
			obj.store.load();
		}
	}
	
});