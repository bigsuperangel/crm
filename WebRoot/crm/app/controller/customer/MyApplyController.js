Ext.define('crm.controller.customer.MyApplyController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			
		});
	},
	views : [
	    'customer.MyApplyList'
	],
	stores : [
	    'apply.MyApplyStore'
	],
	models : [
	    'apply.ApplyModel'
	]
});
