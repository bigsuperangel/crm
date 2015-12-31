Ext.define('crm.store.type.TypeListStore',{
	extend: 'Ext.data.Store',
    model: 'crm.model.type.TypeModel',
    proxy: {
        type: 'ajax',
        url: 'type_getList',
        reader: {
            type: 'json',
            root: 'topics'
        }
    },
    autoLoad: false
});