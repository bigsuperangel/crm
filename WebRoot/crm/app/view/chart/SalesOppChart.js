Ext.define('crm.view.chart.SalesOppChart',{
	extend: 'Ext.chart.Chart',
	alias: 'widget.salesoppchart',
	
	store: 'statistic.SalesOppChartStore',
	legend: {position: 'right'},
    axes: [{
        type: 'Numeric',
        position: 'left',
        title:"数量",
        fields:['未分配','已分配','开发成功','开发失败'],
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
        yField: ['未分配','已分配','开发成功','开发失败'],
        label: {
            display: 'insideEnd',
            'text-anchor': 'middle',
            field: ['未分配','已分配','开发成功','开发失败'],
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