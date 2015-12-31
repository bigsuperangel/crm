Ext.define('crm.store.type.TypeComboboxStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.type.TypeModel',
    proxy: {
    	type: 'ajax',
    	url: 'type_getCombobox',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});