Ext.define('crm.model.type.TypeModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'value',type:'string'},
        {name: 'type', type: 'string'},
        {name: 'sort', type: 'int'},
        {name: 'description',type:'string'},
        {name: 'createBy',type:'string'},
        {name: 'cdate', type: 'string'},
        {name: 'updateBy', type: 'string'},
        {name: 'udate',type:'string'},
        {name: 'remark',type:'string'},
    ]
});