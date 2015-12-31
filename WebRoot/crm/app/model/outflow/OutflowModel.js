Ext.define('crm.model.outflow.OutflowModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'state', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'customerId', type: 'int'}
    ]
});
