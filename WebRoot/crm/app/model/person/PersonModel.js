Ext.define('crm.model.person.PersonModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},//销售机会id
        {name: 'name', type: 'string'},
        {name: 'sex', type: 'int'},
        {name: 'post', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'tel', type: 'string'},
        {name: 'descr', type: 'string'},
        {name: 'qq', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'wangWang', type: 'string'}
        
    ]
});
