Ext.define('crm.controller.agreementDetail.AgreementDetailController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'agreementdetaillist button[id=add]':{
				click:function(btn){
					addFun(btn, 'agreementdetaillist', '添加合同款项信息', 400, 220, 'addagreementdetail');
				}
			},
			'agreementdetaillist button[id=update]':{
				click:function(btn){
					updateFun(btn, 'agreementdetaillist', '您没有选择要修改的合同款项信息', '修改合同款项信息', 'updateagreementdetail', 400, 220);
				}
			},
			'agreementdetaillist button[id=delete]':{
				click:function(btn){
					delFun(btn, 'agreementdetaillist', '合同款项信息', 'agreementDetail_delete', 'detailName', 'detailId');
				}
			}
		});
	},
	views : [
	    'agreementDetail.AgreementDetailList',
	    'agreementDetail.AddAgreementDetail',
	    'agreementDetail.UpdateAgreementDetail'
	],
	stores : [
	    'agreementDetail.AgreementDetailStore'
	],
	models : [
	    'agreementDetail.AgreementDetailModel'
	]
});
