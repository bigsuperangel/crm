/**
 * 品牌知名度
 */
Ext.define('crm.store.type.TypeBrandAwarenessComboboxStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.type.TypeModel',
    proxy: {
    	type: 'ajax',
    	url: 'type_getCombobox?type=brand_awareness',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});