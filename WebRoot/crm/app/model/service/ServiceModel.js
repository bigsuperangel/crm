Ext.define('crm.model.service.ServiceModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'typeName', type: 'string'},
        {name: 'typeId', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'customerId', type: 'int'},
        {name: 'state', type: 'int'},
        {name: 'title', type: 'string'},
        {name: 'content', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'userId', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'dealUserName', type: 'string'},
        {name: 'satisfied', type: 'int'},
        {name: 'dealUserId', type: 'int'}
    ]
});