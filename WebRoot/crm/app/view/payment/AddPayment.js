Ext.define('crm.view.payment.AddPayment',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addpayment',
	
	url: 'payment_add',
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
    	name: 'agreement.id',
    	hidden: true
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'datefield',
        	name: 'receiveDate',
        	fieldLabel:'收款日期',
        	editable:false,
        	format:'Y-m-d',
        	labelWidth: 60,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'numberfield',
        	name: 'receiveAmount',
        	fieldLabel:'收款金额',
        	labelWidth: 60,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
	    	xtype: 'combobox',
	    	store: 'payment.PayModeStore',
	    	name: 'payMode',
	    	fieldLabel: '支付方式',
	    	displayField: 'name',
	        valueField: 'val',
	        mode: 'local',
	        triggerAction: 'all',
	    	labelWidth: 60,
	    	allowBlank: false,
	    	blankText: '支付方式不能为空！',
	    	emptyText: '请选择支付方式...',
	        editable: false,
	        listeners:{
	        	beforerender: function(obj, eOpts){
	        		obj.store.load();
	        	}
	        }
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'receiver',
        	fieldLabel:'收款人',
        	labelWidth: 60,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '收款人不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'receiveAccount',
        	fieldLabel:'收款帐号',
        	labelWidth: 60,
        	maxLength: 50,
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'payer',
        	fieldLabel:'付款人',
        	labelWidth: 60,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '付款人不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'payAccount',
        	fieldLabel:'付款帐号',
        	labelWidth: 60,
        	maxLength: 50,
    	}]
    }
//    ,{
//    	layout:'column',
//    	border:false,
//    	items:[{
//    		columnWidth:.9,
//    		labelWidth: 60,
//            xtype: 'filefield',
//            id: 'form-file',
//            emptyText: 'Select an image',
//            fieldLabel: 'Photo',
//            name: 'photo-path',
//            buttonText: '',
//            buttonConfig: {
//                iconCls: 'icon1682'
//            }
//    	}]
//    }
    ],
	
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
	    	var store = btn.up('addpayment').store;
	    	var model = btn.up('addpayment').btn.up('paymentlist').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load({
	                		params:{
	           			       cid: model.get('agreementId')
	           			    }
	                	});
	                	MsgTip.msg('系统提示','合同款项添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "合同款项添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			var btn = obj.btn;
			var model = btn.up('paymentlist').model;
			console.info(model)
			obj.getForm().findField('agreement.id').setValue(model.get('id'));
			Ext.getCmp('tips').html = '为合同：【'+model.get('projectName')+'】添加款项：';
		}
	}
});