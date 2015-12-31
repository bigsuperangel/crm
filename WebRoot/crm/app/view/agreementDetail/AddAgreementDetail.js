Ext.define('crm.view.agreementDetail.AddAgreementDetail',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addagreementdetail',
	
	url: 'agreementDetail_add',
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
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value1',
        	fieldLabel:'姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
        	labelWidth: ww.common.agreementDetailLabelWidth,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '姓名不能为空！'
    	}]
    },,{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value2',
        	fieldLabel:'职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务',
        	labelWidth: ww.common.agreementDetailLabelWidth,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value3',
        	fieldLabel:'联系电话',
        	labelWidth: ww.common.agreementDetailLabelWidth,
        	maxLength: 15
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'value4',
        	fieldLabel:'手机号码',
        	labelWidth: ww.common.agreementDetailLabelWidth,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '手机号码不能为空！'
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
	    	var store = btn.up('addagreementdetail').store;
	    	var model = btn.up('addagreementdetail').btn.up('agreementdetaillist').model;
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
			var model = btn.up('agreementdetaillist').model;
			obj.getForm().findField('agreement.id').setValue(model.get('agreementId'));
			Ext.getCmp('tips').html = '为合同：【'+model.get('projectName')+'】添加款项：';
		}
	}
});