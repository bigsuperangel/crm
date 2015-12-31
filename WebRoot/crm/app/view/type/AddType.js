Ext.define('crm.view.type.AddType',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addtype',
	
	url: 'type_add',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'type',
        	fieldLabel:'类型',
        	labelWidth: 60,
        	allowBlank:false, 
        	blankText: '类型不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'name',
        	fieldLabel:'标签名',
        	labelWidth: 60,
        	maxLength: 80,
        	allowBlank:false, 
        	blankText: '标签名不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'value',
        	fieldLabel:'数据值',
        	labelWidth: 60,
        	maxLength: 80,
        	allowBlank:false, 
        	blankText: '数据值不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
        	xtype: 'numberfield',
        	name: 'sort',
        	fieldLabel:'排序',
        	labelWidth: 60,
        	maxLength: 4,
        	value:10,
        	step:10,
        	maxValue: 1000,
            minValue: 10,
        	allowBlank:false, 
        	editable:false,
        	blankText: '序号不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'description',
        	fieldLabel:'描述',
        	labelWidth: 60,
        	maxLength: 100
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
	        xtype : 'textareafield',
        	name: 'remarks',
        	fieldLabel:'备注',
        	labelWidth: 60,
	        grow      : true,
	        anchor    : '100%'
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
	    	var store = btn.up('addtype').store;
	    	var model = btn.up('addtype').btn.up('typelist').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','新类型添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "新类型添加失败！");
	                }
	            });
	        }
	    }
	}]
});