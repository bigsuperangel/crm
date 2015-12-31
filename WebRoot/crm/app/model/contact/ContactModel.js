Ext.define('crm.model.contact.ContactModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'content', type: 'string'},
        {name: 'customerId', type: 'id'},
        {name: 'customerName', type: 'string'},
        {name: 'title', type: 'string'},
        {name: 'userId', type: 'int'},
        {name: 'userName', type: 'string'},
        {name: 'addr', type: 'string'},
        {name: 'contactor', type: 'string'},
        {name: 'contactType', type: 'int'},
        {name: 'nextVisit', type: 'string'},
        {name: 'supervisor', type: 'string'},
    ]
});