Ext.define('crm.view.salesOpp.UpdateSalesOpp',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updatesalesopp',
	
	url: 'salesOpp_update',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textfield',
        	name: 'salesOpp.customer',
        	fieldLabel:'客户名称',
        	labelWidth: 60,
        	maxLength: 12,
        	allowBlank:false
    	},{
    		xtype: 'textfield',
    		name: 'salesOpp.id',
    		hidden : true
    	}
//    	,{
//    		xtype: 'textfield',
//    		name: 'salesOpp.user.id',
//    		hidden : true
//    	},{
//    		xtype: 'textfield',
//    		name: 'salesOpp.dealUser.id',
//    		hidden : true
//    	},{
//    		xtype: 'textfield',
//    		name: 'salesOpp.createDate',
//    		hidden : true
//    	}
    	]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textarea',
        	name: 'salesOpp.resource',
        	fieldLabel:'机会来源',
        	labelWidth: 60,
        	maxLength: 50,
        	height: 55
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.4,
        	xtype: 'numberfield',
        	name: 'salesOpp.success',
        	id: 'realName',
        	fieldLabel:'成功几率',
        	labelWidth: 60,
        	maxValue: 100,
        	minValue: 1,
        	allowBlank:false,
        	blankText: '成功几率不能为空！',
        	value: 1
    	},{
    		xtype: 'text',
    		labelWidth: 60,
    		height: 20,
    		margin: '2 0 0 5',
    		text: '%'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textarea',
        	name: 'salesOpp.summery',
        	fieldLabel:'概要说明',
        	labelWidth: 60,
        	maxLength: 120,
        	height: 55
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
        	fieldLabel:'联系人',
        	columnWidth:.5,
        	xtype: 'textfield',
        	name: 'salesOpp.person',
        	labelWidth: 60,
        	maxLength: 12,
        	allowBlank:false
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
        	fieldLabel:'联系电话',
        	columnWidth:.8,
        	xtype: 'textfield',
        	name: 'salesOpp.tel',
        	labelWidth: 60,
        	maxLength: 15,
        	allowBlank:false
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textarea',
        	name: 'salesOpp.descr',
        	fieldLabel:'机会描述',
        	labelWidth: 60,
        	maxLength: 50,
        	height: 55
    	}]
    }],
	
	// Reset and Submit buttons
	buttons: [{
	    text: '清空',
	    handler: function() {
	        this.up('form').getForm().reset();
	    }
	}, {
	    text: '提交',
	    formBind: true, //only enabled once the form is valid
	    disabled: true,
	    handler: function(btn) {
	    	var win = btn.up('window');
	    	var store = btn.up('updatesalesopp').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
//	                	store.add(action.result.msg);
                		//已经是满页，不添加当前页、
                		MsgTip.msg('系统提示', '销售机会修改成功');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "销售机会修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners: {
		beforerender: function(obj, eOpts ){
			obj.getForm().findField('salesOpp.customer').setValue(obj.model.get('customer'));
			obj.getForm().findField('salesOpp.resource').setValue(obj.model.get('resource'));
			obj.getForm().findField('salesOpp.success').setValue(obj.model.get('success'));
			obj.getForm().findField('salesOpp.summery').setValue(obj.model.get('summery'));
			obj.getForm().findField('salesOpp.person').setValue(obj.model.get('person'));
			obj.getForm().findField('salesOpp.tel').setValue(obj.model.get('tel'));
			obj.getForm().findField('salesOpp.descr').setValue(obj.model.get('descr'));
			obj.getForm().findField('salesOpp.id').setValue(obj.model.get('id'));
			
//			obj.getForm().findField('salesOpp.user.id').setValue(obj.model.get('userId'));
//			obj.getForm().findField('salesOpp.dealUser.id').setValue(obj.model.get('dealUserId'));
//			obj.getForm().findField('salesOpp.createDate').setValue(obj.model.get('createDate'));
		}
	}
});