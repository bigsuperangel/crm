Ext.define('crm.controller.type.TypeController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'typelist button[id=add]':{
				click: function(btn){
					addFun(btn, 'typelist', '添加类型', 300, 250, 'addtype');
				}
			},
			'typelist button[id=update]':{
				click: function(btn){
					updateFun(btn, 'typelist', '您没有选择要修改的类型，请选择', '修改类型', 'updatetype', 300, 250);
				}
			},
			'typelist button[id=delete]':{
				click: function(btn){
					delFun(btn, 'typelist', '类型', 'type_delete', 'name', 'id');
				}
			}
		});
	},
	views : [
	    'type.TypeList',
	    'type.AddType',
	    'type.UpdateType'
	],
	stores : [
	    'type.TypeComboboxStore',
	    'type.TypeListStore',
	    'type.TypeBrandAwarenessComboboxStore',
	    'type.TypeCompanyAwarenessComboboxStore',
	    'type.TypeCompanyTypeComboboxStore',
	    'type.TypeSaleModeComboboxStore'
	],
	models : [
	    'type.TypeModel'
	]
});
