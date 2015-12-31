Ext.define('crm.model.salesOpp.SalesOppModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'createDate', type: 'string'},
        {name: 'customer', type: 'string'},
        {name: 'dealUserId', type: 'int'},
        {name: 'dealUserName', type: 'string'},
        {name: 'descr', type: 'string'},
        {name: 'id', type: 'int'},
        {name: 'person', type: 'string'},
        {name: 'resource', type: 'string'},
        {name: 'state', type: 'int'},
        {name: 'success', type: 'int'},
        {name: 'summery', type: 'string'},
        {name: 'tel', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'userId', type: 'int'}
    ]
});