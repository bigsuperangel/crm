Ext.define('crm.controller.person.PersonController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'personlist button[id=addPerson]':{
				click: function(btn){
					addFun(btn, 'personlist', '添加客户联系人', 500, 350, 'addperson');
				}
			},
			'personlist button[id=updatePerson]': {
				click: function(btn){
					updateFun(btn, 'personlist', '您没有选择联系人信息，请选择', '修改联系人信息', 'updateperson', 500, 350);
				}
			},
			'personlist button[id=deletePerson]': {
				click: function(btn){
					delFun(btn, 'personlist', '联系人', 'person_delete', 'name', 'id');
				}
			}
		});
	},
	views : [
	    'person.PersonList',
	    'person.AddPerson',
	    'person.UpdatePerson'
	],
	stores : [
	    'person.PersonStore'
	],
	models : [
	    'person.PersonModel'
	]
});