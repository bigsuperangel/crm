Ext.define('crm.controller.lossStep.LossStepController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'losslist button[id=addLoss]':{
				click:function(btn){
					addFunWithData(btn, 'losslist', '您没有选择流失告警，无法填写流失措施', '填写流失措施', 340, 200, 'addlossstep', 1);
				}
			},
			'losslist button[id=updateLoss]':{
				click:function(btn){
					updateFun(btn, 'losslist', '您没有选择暂缓措施，请选择', '修改暂缓措施', 'updateloss', 400, 248);
				}
			},
			'losslist button[id=deleteLoss]':{
				click:function(btn){
					delFun(btn, 'losslist', '暂缓措施', 'loss_delete', 'content', 'id');
				}
			}
		});
	},
	views : [
	    'lossStep.AddLossStep',
	    'lossStep.LossList',
	    'lossStep.UpdateLoss'
	],
	stores : [
	    'lossStep.LossStepStore'
	],
	models : [
	    'lossStep.LossStepModel'
	]
});
