Ext.define('crm.view.user.UpdatePwdUser',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updatepwduser',
		
	url: 'user_resetPwd',
	border: 0,
	bodyPadding: 5,
	items: [{
		xtype: 'textfield',
		name: 'currentUserId',
		hidden:true
	},{
        fieldLabel: '旧密码',
        xtype: 'textfield',
        inputType: 'password',
        labelWidth: 60,
        name: 'oldPwd',
        allowBlank: false
    },{
        fieldLabel: '新密码',
        xtype: 'textfield',
        inputType: 'password',
        labelWidth: 60,
        name: 'newPwd',
        allowBlank: false
    },{
        fieldLabel: '确认密码',
        xtype: 'textfield',
        inputType: 'password',
        labelWidth: 60,
        name: 'repeatPwd',
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
            var store = this.up('updatepwduser').store;
            
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
                    	store.load();
                    	win.close();
                    	MsgTip.msg('系统提示','用户修改成功！');
                    },
                    failure: ww.common.failure
                });
            }
        }
    }],
    listeners: {
		beforerender: function(obj, eOpts ){
			obj.getForm().findField('currentUserId').setValue(obj.model.get('id'));
		}
    }
});