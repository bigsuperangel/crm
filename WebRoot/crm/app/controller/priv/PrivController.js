Ext.define('crm.controller.priv.PrivController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'privtreegrid button[id=add]':{
				click: function(btn){
					addFun(btn, 'privtreegrid', '添加类别', 300, 250, 'addpriv');
				}
			}
		});
	},
	views : [
	    'priv.PrivTreeGrid',
	    'priv.UpdatePriv',
	    'priv.AddPriv'
	],
	stores : [
		'priv.PrivTreeStore'
	],
	models : [
		'priv.PrivModel'
	]
});
