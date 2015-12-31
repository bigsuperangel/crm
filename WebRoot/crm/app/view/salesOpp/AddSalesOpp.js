Ext.define('crm.view.salesOpp.AddSalesOpp',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addsalesopp',
	
	url: 'salesOpp_add',
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
    	}]
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
	    	var store = btn.up('addsalesopp').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	
	                	win.close();
//	                	store.load();
//	                	store.add(action.result.msg);
	                	if(store.pageSize == store.data.length){
	                		//已经是满页，不添加当前页、
	                		MsgTip.msg('系统提示', '销售机会添加成功，追加至最后一页');
	                	}else{
	                		store.add(action.result.msg);
	                		MsgTip.msg('系统提示', '销售机会添加成功！');
	                	}
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "销售机会添加失败！");
	                }
	            });
	        }
	    }
	}]
});