Ext.define('crm.view.plan.UpdatePlan',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateplan',
	
	url: 'plan_update',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
		xtype: 'text',
		id: 'tips',
		height: 'auto'
	},{
		xtype: 'textfield',
		name: 'plan.salesOpp.id',
		id: 'id',
		hidden: true
	},{
		xtype: 'textfield',
		name: 'plan.id',
		id: 'pid',
		hidden: true
	},{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.68,
        	xtype: 'datefield',
        	name: 'plan.cdate',
        	fieldLabel:'计划时间',
        	labelWidth: 60,
        	allowBlank:false, 
        	format: 'Y-m-d H:i:s',
        	value: new Date()
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textarea',
        	name: 'plan.content',
        	fieldLabel:'计划内容',
        	labelWidth: 60,
        	maxLength: 100,
        	height: 65
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
	    	var store = btn.up('updateplan').store;
	    	var ch = btn.up('updateplan').ch;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','计划修改成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "计划修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			Ext.getCmp('tips').html = '修改销售机会：<span style="color:red">'+obj.model.get('customer')+'</span>的定制开发计划：';
			Ext.getCmp('id').setValue(obj.model.get('sid'));
			obj.getForm().findField('plan.cdate').setValue(obj.model.get('cdate'));
			obj.getForm().findField('plan.content').setValue(obj.model.get('content'));
			obj.getForm().findField('plan.id').setValue(obj.model.get('pid'));
		}
	}
});