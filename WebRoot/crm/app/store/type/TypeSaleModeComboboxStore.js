/**
 * 销售模式
 */
Ext.define('crm.store.type.TypeSaleModeComboboxStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.type.TypeModel',
    proxy: {
    	type: 'ajax',
    	url: 'type_getCombobox?type=sale_mode',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});