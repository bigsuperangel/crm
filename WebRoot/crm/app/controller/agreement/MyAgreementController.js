Ext.define('crm.controller.agreement.MyAgreementController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'myagreementlist button[id=delete]':{
				click:function(btn){
					delFun(btn, 'myagreementlist', '合同信息', 'agreement_delete', 'agreementName', 'id');
				}
			},
			'myagreementlist button[id=update]':{
				click:function(btn){
					updateFun(btn, 'myagreementlist', '您没有选择要修改的合同信息', '修改合同信息', 'updateagreement', 650, 400);
				}
			}
		});
	},
	views : [
	    'agreement.MyAgreementList'
	],
	stores : [
	    'agreement.AgreementStore'
	],
	models : [
	    'agreement.AgreementModel'
	]
});
