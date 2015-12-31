Ext.define('crm.model.user.UserModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'username', type: 'string'},
        {name: 'realName', type: 'string'},
        {name: 'id', type: 'int'},
        {name: 'tel', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'enable', type: 'int'}, // 1：正常使用    2：停用    3：已删除
        {name: 'roleId', type: 'string'},//[1,23]
        {name: 'roleName', type: 'string'},
        {name: 'deptName', type: 'string'},
        {name: 'deptId', type: 'string'},
        {name: 'password', type: 'string'}
    ]
});
