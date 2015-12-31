/**
 * 指派合作
 */
Ext.define('crm.view.customer.DelayCustomer',{
	extend: 'Ext.form.Panel',
	alias: 'widget.delaycustomer',
	
	url: 'delayApply_add',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'numberfield',
        	name: 'delayDays',
        	fieldLabel:'延时天数',
        	labelWidth: 60,
        	maxLength: 3,
        	allowBlank:false, 
        	value:10
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
	    	var store = btn.up('delaycustomer').store;
	    	var model = btn.up('delaycustomer').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	            	params: {
				        'customerId': model.get('id'),
				    },
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','延时客户成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "延时客户失败！");
	                }
	            });
	        }
	    }
	}]
});