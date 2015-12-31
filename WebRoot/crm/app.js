Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.tip.QuickTipManager.init();
	//初始化load，自动加载
	Ext.Loader.setConfig({enabled: true});
	
	Ext.require('Ext.app.Application',function(){
		Ext.app.Application.addMembers({
		    //加载Controller后触发事件
		    newControllerAdded:function(idx,cntr){
		        cntr.init(this);
		    },
		    //判断是否已加载指定Controller，返回Boolean
		    hasController:function(name){
		        return !!this.controllers.get(name);
		    },
		    //加载并返回指定Controller
		    addController:function(name){
		        return this.getController(name);
		    }
		});
	});
	
	//初始化应用
	Ext.application({
		name: 'crm',//应用的名字
		appFolder: 'app',//应用所在的目录
		launch: function(){//当前页面加载完成后，执行的函数
			this.controllers.addListener('add',this.newControllerAdded,this);
			Ext.create("Ext.container.Viewport",{
				layout: 'fit',//自动布局
				items: [{
					xtype: 'mainlayout'
				}]
			});
		},
		controllers:[
			'Controller',
			'menu.MenuController',
//			'salesOpp.SalesOppController',
			'user.UserController',
//			'plan.PlanController',
			'customer.CustomerController',
			'customer.PublicCustomerController',
			'customer.AuditCustomerController',
			'customer.ApplyController',
			'customer.MyApplyController',
			'customer.DelayController',
			'customer.MyDelayController',
//			'delay.MyDelayController',
			'person.PersonController',
			'contact.ContactController',
//			'outflow.OutflowController',
//			'lossStep.LossStepController',
//			'service.ServiceController',
			'type.TypeController',
			'priv.PrivController',
//			'statistic.StatisticController',
//			'chart.ChartController',
			'dept.DeptController',
			'role.RoleController',
			'cooperate.CooperateAcceptController',
			'cooperate.CooperateSubmitedController',
			'cooperate.CooperateUnauditController',
//			'customer.PublicCustomerController',
			'agreement.AgreementController',
			'agreement.MyAgreementController',
			'payment.PaymentController'
//			'agreementDetail.AgreementDetailController'
		]
	});
});
