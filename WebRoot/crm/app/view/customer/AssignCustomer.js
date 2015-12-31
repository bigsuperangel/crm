/**
 * 指派合作
 */
Ext.define('crm.view.customer.AssignCustomer',{
	extend: 'Ext.form.Panel',
	alias: 'widget.assigncustomer',
	
	url: 'customer_move',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
			xtype: 'combobox',
	    	store: 'user.UserComboboxStore',
	    	name: 'handlerId',
	    	fieldLabel: '维权顾问',
	    	displayField: 'realName',
	        valueField: 'id',
	        mode: 'local',
//	        multiSelect: true,
	    	labelWidth: ww.common.contactLabelWidth,
	    	allowBlank: false,
	    	blankText: '维权顾问不能为空！',
	    	emptyText: '请选择维权顾问...',
	        editable: false,
	    	listeners:{
	        	beforerender: function(obj, eOpts){
    				obj.store.getProxy().extraParams = {
				        deptId:user.deptId
				    };
    				obj.store.load();
	        	}
	        }
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
	    	var store = btn.up('assigncustomer').store;
	    	var model = btn.up('assigncustomer').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	            	params: {
				        'customerId': model.get('id'),
				    },
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','客户分配成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "客户分配失败！");
	                }
	            });
	        }
	    }
	}]
});