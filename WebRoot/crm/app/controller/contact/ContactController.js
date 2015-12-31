Ext.define('crm.controller.contact.ContactController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'contactlist button[id=addContact]':{
				click: function(btn){
					addFun(btn, 'contactlist', '添加客户交往记录', 500, 350, 'addcontact');
				}
			},
			'contactlist button[id=updateContact]':{
				click: function(btn){
					updateFun(btn, 'contactlist', '您没有选择客户交往记录，请选择', '修改客户交往记录', 'updatecontact', 500, 350);
				}
			},
			'contactlist button[id=deleteContact]':{
				click: function(btn){
					delFun(btn, 'contactlist', '客户交往记录', 'person_delete', 'name', 'id');
				}
			}
		});
	},
	views : [
	    'contact.ContactList',
	    'contact.AddContact',
	    'contact.UpdateContact'
	],
	stores : [
	    'contact.ContactStore'
	],
	models : [
	    'contact.ContactModel'
	]
});
