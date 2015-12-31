Ext.define('crm.model.lossStep.LossStepModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'oid', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'userId', type: 'int'},
        {name: 'userName', type: 'string'},
        {name: 'customerName', type: 'string'},
        {name: 'content', type: 'string'}
    ]
});