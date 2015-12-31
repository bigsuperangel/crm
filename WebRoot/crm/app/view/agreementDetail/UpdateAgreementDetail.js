Ext.define('crm.view.agreementDetail.UpdateAgreementDetail',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateagreementdetail',
	
	url: 'agreementDetail_update',
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
    	xtype: 'textfield',
    	name: 'detailId',
    	hidden: true
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value1',
        	fieldLabel:'姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
        	labelWidth: 60,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '姓名不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value2',
        	fieldLabel:'手机号码',
        	labelWidth: 60,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '手机号码不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value3',
        	fieldLabel:'QQ',
        	labelWidth: 60,
        	maxLength: 15
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value4',
        	fieldLabel:'EMAIL',
        	labelWidth: 60,
        	maxLength: 50
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
	    	var store = btn.up('updateagreementdetail').store;
	    	var model = btn.up('updateagreementdetail').btn.up('agreementdetaillist').model;
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
	                	MsgTip.msg('系统提示','合同款项修改成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "合同款项修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			var btn = obj.btn;
			var model = btn.up('agreementdetaillist').model;
			var agreementDetail = obj.model;
			obj.getForm().findField('agreement.id').setValue(model.get('agreementId'));
			Ext.getCmp('tips').html = '修改合同：【'+model.get('projectName')+'】的款项：';
			obj.getForm().loadRecord(agreementDetail);
			
		}
	}
});