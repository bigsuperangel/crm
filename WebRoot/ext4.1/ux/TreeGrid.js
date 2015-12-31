
Ext.define('Ext.ux.TreeGrid', {
    extend: 'Ext.tree.Panel',
    
    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.tree.*',
        'Ext.ux.CheckColumn'
    ],    
    xtype: 'tree-grid',
    
    
    title: 'Core Team Projects',
    height: 300,
    useArrows: true,
    rootVisible: false,
    multiSelect: true,
    singleExpand: true,
    store: null,
    
    initComponent: function() {
        this.width = 600;
        
        Ext.apply(this, {
            store: store,
            columns: [{
                xtype: 'treecolumn', //this is so we know which column will show the tree
                text: 'Task',
                flex: 2,
                sortable: true,
                dataIndex: 'task'
            },{
                //we must use the templateheader component so we can use a custom tpl
                xtype: 'templatecolumn',
                text: 'Duration',
                flex: 1,
                sortable: true,
                dataIndex: 'duration',
                align: 'center',
                //add in the custom tpl for the rows
                tpl: Ext.create('Ext.XTemplate', '{duration:this.formatHours}', {
                    formatHours: function(v) {
                        if (v < 1) {
                            return Math.round(v * 60) + ' mins';
                        } else if (Math.floor(v) !== v) {
                            var min = v - Math.floor(v);
                            return Math.floor(v) + 'h ' + Math.round(min * 60) + 'm';
                        } else {
                            return v + ' hour' + (v === 1 ? '' : 's');
                        }
                    }
                })
            },{
                text: 'Assigned To',
                flex: 1,
                dataIndex: 'user',
                sortable: true
            }, {
                xtype: 'checkcolumn',
                header: 'Done',
                dataIndex: 'done',
                width: 55,
                stopSelection: false,
                menuDisabled: true
            }, {
                text: 'Edit',
                width: 55,
                menuDisabled: true,
                xtype: 'actioncolumn',
                tooltip: 'Edit task',
                align: 'center',
                icon: 'resources/images/edit_task.png',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    Ext.Msg.alert('Editing' + (record.get('done') ? ' completed task' : '') , record.get('task'));
                },
                // Only leaf level tasks may be edited
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return !record.data.leaf;
                }
            }]
        });
        this.callParent();
    }
});
