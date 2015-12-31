/**
 * 公司知名度
 */
Ext.define('crm.store.type.TypeCompanyAwarenessComboboxStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.type.TypeModel',
    proxy: {
    	type: 'ajax',
    	url: 'type_getCombobox?type=company_awareness',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});