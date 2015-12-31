Ext.define('crm.view.service.UpdateService',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateservice',
	
	//to_cxd@163.com
	url: 'service_update',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	xtype: 'textfield',
    	name: 'service.id',
    	hidden:true
    },{
    	xtype: 'textfield',
    	name: 'service.createUser.id',
    	hidden:true
    },{
    	xtype: 'textfield',
    	name: 'service.cdate',
    	hidden:true
    },{
    	xtype: 'textfield',
    	name: 'service.customer.id',
    	hidden:true
    },{//第一行
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
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'sc',
        	fieldLabel:'客户名称',
        	labelWidth: 60,
        	maxLength: 20,
        	disabled: true
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
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
	        editable: false,
	        listeners:{
            	beforerender: function(obj, eOpts){
            		obj.store.load();
            	}
            }
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.5,
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
	        editable: false,
	        listeners:{
            	beforerender: function(obj, eOpts){
            		obj.store.load();
            	}
            }
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'scdate',
        	fieldLabel:'创建日期',
        	labelWidth: 60,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '概要不能为空！',
        	disabled: true
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
	        var win = btn.up('window');
	        var store = btn.up('updateservice').store;
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	MsgTip.msg('系统提示','服务修改成功！');
	                	store.load();
	                	win.close();
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "服务修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners: {
		beforerender: function(obj, eOpts ){
			obj.getForm().findField('service.id').setValue(obj.model.get('id'));
			obj.getForm().findField('service.createUser.id').setValue(obj.model.get('userId'));
			obj.getForm().findField('service.customer.id').setValue(obj.model.get('customerId'));
			obj.getForm().findField('service.cdate').setValue(obj.model.get('cdate'));
			obj.getForm().findField('service.title').setValue(obj.model.get('title'));
			obj.getForm().findField('service.customer.id').setValue(obj.model.get('customerId'));
			obj.getForm().findField('service.type.id').setValue(obj.model.get('typeId'));
			obj.getForm().findField('service.dealUser.id').setValue(obj.model.get('dealUserId'));
			obj.getForm().findField('service.content').setValue(obj.model.get('content'));
			obj.getForm().findField('sc').setValue(obj.model.get('customerName'));
			obj.getForm().findField('scdate').setValue(obj.model.get('cdate'));
		}
	}
});