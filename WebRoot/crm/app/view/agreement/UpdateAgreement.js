Ext.define('crm.view.agreement.UpdateAgreement',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateagreement',
	
	url: 'agreement_update',
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
    	name: 'id',
    	hidden: true
    },{
    	xtype: 'textfield',
    	name: 'customer.id',
    	hidden: true
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'projectName',
        	fieldLabel:'项目名称',
        	labelWidth: 70,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '项目名称不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'datefield',
        	name: 'signDate',
        	fieldLabel:'签约日期',
        	format: 'Y-m-d',
        	labelWidth: 70,
        	maxLength: 10
    	}]
    },
//    {
//    	layout:'column',
//    	border:false,
//    	items:[{
//    		columnWidth:.9,
//        	xtype: 'datefield',
//        	name: 'paymentClearDate',
//        	fieldLabel:'完款日期',
//        	labelWidth: 70,
//        	maxLength: 10
//    	}]
//    },
    {
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'datefield',
        	name: 'finishDate',
        	fieldLabel:'完成日期',
        	format: 'Y-m-d',
        	labelWidth: 70,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'numberfield',
        	name: 'amount',
        	fieldLabel:'合同金额',
        	labelWidth: 70,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'numberfield',
        	name: 'subAgreementCount',
        	fieldLabel:'子合同数',
        	labelWidth: 70,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'paymentStatus',
        	fieldLabel:'收款状态',
        	labelWidth: 70,
        	maxLength: 15
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'textfield',
        	name: 'mySigner',
        	fieldLabel:'我方签约人',
        	labelWidth: 70,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '我方签约人不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'textfield',
        	name: 'customerSigner',
        	fieldLabel:'客户签约人',
        	labelWidth: 70,
        	maxLength: 50,
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
			xtype: 'fieldcontainer',
            fieldLabel: '是否开票',
            labelWidth: 70,
            defaultType: 'checkboxfield',
            items: [
                {
                    boxLabel  : '开票',
                    name      : 'isReceipt',
                    inputValue: '1'
                }
            ]
    	}]
	},{
	    layout:'column',
    	border:false,
	    items: [
	        {
	        	columnWidth:1,
	            xtype: 'fieldcontainer',
	            fieldLabel: '合同参数',
//	            combineErrors: true,
//	            msgTarget: 'under',
	            layout: 'hbox',
	            labelWidth: 70,
	            defaults: {
	                hideLabel: true
	            },
	            items: [
	                {xtype: 'displayfield', value: '侵权链接检索及处理'},
	                {xtype: 'numberfield',    fieldLabel: 'detail.value1', name: 'detail.value1', width: 48, allowBlank: false, margins: '0 5 0 0'},
	                {xtype: 'displayfield', value: '条,'},
	                {xtype: 'numberfield',    fieldLabel: 'detail.value2', name: 'detail.value2', width: 48, allowBlank: false, margins: '0 5 0 0'},
	                {xtype: 'displayfield', value: '元/单价; 假货购买鉴定'},
	                {xtype: 'numberfield',    fieldLabel: 'detail.value3', name: 'detail.value3', width: 48, allowBlank: false, margins: '0 5 0 0'},
	                {xtype: 'displayfield', value: '件,'},
	                {xtype: 'numberfield',    fieldLabel: 'detail.value4', name: 'detail.value4', width: 48, allowBlank: false, margins: '0 5 0 0'},
	                {xtype: 'displayfield', value: '元/单价'}
	            ]
	        }
	    ]
	},{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'remark',
        	fieldLabel:'备注',
        	labelWidth: 70,
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
	    	var store = btn.up('updateagreement').store;
	    	var model = btn.up('updateagreement').btn.up('agreementlist').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load({
	         				params:{
	           			       cid: model.get('id')
	           			    }
	           			});
	                	MsgTip.msg('系统提示','合同修改成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "合同修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
    		obj.getForm().loadRecord(obj.model);
			obj.getForm().findField('customer.id').setValue(obj.model.get('cId'));
			Ext.Ajax.request({
			    url: 'agreement_query',
			    method:'get',
			    params: {
			        agreementId: obj.model.get('id')
			    },
			    success: function(response){
			    	 var json = Ext.decode(response.responseText);
			    	 console.log(json);
			    	 if(json){
				    	obj.getForm().findField('detail.value1').setValue(json.value1);
				    	obj.getForm().findField('detail.value2').setValue(json.value2);
				    	obj.getForm().findField('detail.value3').setValue(json.value3);
				    	obj.getForm().findField('detail.value4').setValue(json.value4);
			    	 }
			    },
			    failure: function(response){
			    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
			    }
			});
		}
	}
});