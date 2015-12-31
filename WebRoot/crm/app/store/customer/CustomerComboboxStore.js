Ext.define('crm.store.customer.CustomerComboboxStore',{
	extend: 'Ext.data.Store',
	
	model: 'crm.model.customer.CustomerModel',
    proxy: {
    	type: 'ajax',
    	url: 'customer_getCustomerCombobox',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    },
    reader:{
    	type: 'json',
    	fields: [{
			name: 'id'
		},{
			name: 'realName'
		}]
    }
});