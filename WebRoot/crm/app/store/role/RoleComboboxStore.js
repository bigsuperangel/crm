Ext.define('crm.store.role.RoleComboboxStore',{
	extend: 'Ext.data.Store',
	
	model: 'crm.model.role.RoleModel',
    proxy: {
    	type: 'ajax',
    	url: 'role_getComboboxList',
    	reader: {
    		type: 'json',
    		root: 'topics'
    	}
    }
});