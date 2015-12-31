/**
 * 公司类型
 */
Ext.define('crm.store.type.TypeCompanyTypeComboboxStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.type.TypeModel',
    proxy: {
    	type: 'ajax',
    	url: 'type_getCombobox?type=company_type',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});