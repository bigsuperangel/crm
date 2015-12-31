Ext.define('crm.model.role.RoleModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'userCount', type: 'int'},
        {name:'count',type:'int'}, //角色拥有的最大客户数
        {name: 'name', type: 'string'}
    ]
});
