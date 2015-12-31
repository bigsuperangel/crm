Ext.define('crm.controller.plan.PlanController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'planlist button[id=add]':{
				click: function(btn){
					addFunWithData(btn, 'planlist', '您没有选择销售机会，无法进行定制开发计划', '定制开发计划', 340, 200, 'addplan', 1);
				}
			},
			'planlist button[id=update]':{
				click: function(btn){
					updateFun(btn, 'planlist', '您没有选择销售机会，无法进行定制开发计划', '修改开发计划', 'updateplan', 340, 200, 1);
				}
			},
			'planlist button[id=delete]':{
				click:function(btn,e,opt){
					delFun(btn, 'planlist', '客户开发计划', 'plan_delete', 'content', 'pid')
				}
			}
		});
	},
	views : [
	    'plan.AddPlan',
	    'plan.PlanList',
	    'plan.UpdatePlan'
	],
	stores : [
	    'plan.PlanStore'
	],
	models : [
	    'plan.PlanModel'
	]
});
