Ext.define('crm.view.role.UpdateRole',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updaterole',
		
	url: 'role_update',
	border: 0,
	bodyPadding: 5,
	items: [{
		xtype: 'textfield',
		name: 'id',
		hidden:true
	},{
        fieldLabel: '角色名称',
        xtype: 'textfield',
        labelWidth: 60,
        name: 'name',
        allowBlank: false
    },{
        fieldLabel: '拥有最大客户数',
        xtype: 'numberfield',
        labelWidth: 60,
        name: 'count',
        minValue:0,
        allowBlank: false
    }],
    buttons: [{
        text: '清空',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: '提交修改',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        handler: function() {
            var form = this.up('form').getForm();
            var win = this.up('window');
            var store = this.up('updaterole').store;
            
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
//	                        form.owner.win.close();
//	                        form.owner.store.add(
//	                        	{username:form.findField('user.username').getValue(),password:form.findField('user.password').getValue(),id:action.result.id,realName:form.findField('user.realName').getValue()});
                    	store.load();
                    	win.close();
                    	MsgTip.msg('系统提示','角色修改成功！');
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('系统提示', "角色修改失败！");
                    }
                });
            }
        }
    }],
    listeners: {
		beforerender: function(obj, eOpts ){
			obj.getForm().findField('id').setValue(obj.model.get('id'));
			obj.getForm().findField('name').setValue(obj.model.get('name'));
			obj.getForm().findField('count').setValue(obj.model.get('count'));
		}
    }
});