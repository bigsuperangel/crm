Ext.define('crm.store.user.UserComboboxStore',{
	extend: 'Ext.data.Store',
//	fields: ['abbr', 'name'],
//    data : [
//        {"abbr":"AL", "name":"Alabama"},
//        {"abbr":"AK", "name":"Alaska"},
//        {"abbr":"AZ", "name":"Arizona"}
//    ]
//});
	
	model: 'crm.model.user.UserModel',
    proxy: {
    	type: 'ajax',
    	url: 'user_getUserCombobox',
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
    },
    autoLoad: false
});