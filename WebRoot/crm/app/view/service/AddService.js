Ext.define('crm.view.service.AddService',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addservice',
	
	//to_cxd@163.com
	url: 'service_add',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'service.title',
        	fieldLabel:'服务概要',
        	labelWidth: 60,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '概要不能为空！'
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.3,
			xtype: 'combobox',
	    	store: 'customer.CustomerComboboxStore',
	    	name: 'service.customer.id',
	    	fieldLabel: '服务客户',
	    	displayField: 'customerName',
	        valueField: 'id',
	        mode: 'local',
	    	labelWidth: 60,
	    	allowBlank: false,
	    	blankText: '服务客户不能为空！',
	    	emptyText: '请选择服务客户...',
	        editable: false
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.3,
			xtype: 'combobox',
	    	store: 'type.TypeComboboxStore',
	    	name: 'service.type.id',
	    	fieldLabel: '服务类型',
	    	displayField: 'name',
	        valueField: 'id',
	        mode: 'local',
	    	labelWidth: 60,
	    	allowBlank: false,
	    	blankText: '服务客户不能为空！',
	    	emptyText: '请选择服务客户...',
	        editable: false
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.3,
			xtype: 'combobox',
	    	store: 'user.UserComboboxStore',
	    	name: 'service.dealUser.id',
	    	fieldLabel: '处理人员',
	    	displayField: 'realName',
	        valueField: 'id',
	        mode: 'local',
	    	labelWidth: 60,
	    	blankText: '处理人员不能为空！',
	    	emptyText: '请选择处理人员...',
	        editable: false
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'htmleditor',
        	name: 'service.content',
        	fieldLabel:'服务内容',
        	labelWidth: 60,
        	frame: true,
        	border: 1,
        	height: 200
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
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	MsgTip.msg('系统提示','客户联系人添加成功！');
	                	form.reset();
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "客户联系人添加失败！");
	                }
	            });
	        }
	    }
	}]
});