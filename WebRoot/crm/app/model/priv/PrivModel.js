Ext.define('crm.model.priv.PrivModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'iconCls', type: 'string'},
        {name: 'expanded', type: 'boolean'},
        {name: 'indx', type: 'int'},
        {name: 'config', type: 'string'}
    ]
});