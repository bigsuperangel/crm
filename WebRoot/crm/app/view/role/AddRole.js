Ext.define('crm.view.role.AddRole',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addrole',
	
	url: 'role_add',
	border: 0,
	bodyPadding: 5,
	items: [{
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
        value:0,
        minValue:0,
        allowBlank: false
    }],
    buttons: [{
        text: '清空',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: '添加',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        handler: function() {
            var form = this.up('form').getForm();
            var win = this.up('window');
            var store = this.up('addrole').store;
            
            if (form.isValid()) {
                form.submit({
//                	params:{
//                		'limit':0
//                	},
                    success: function(form, action) {
//                        form.owner.win.close();
//                        form.owner.store.add(
//                        	{username:form.findField('user.username').getValue(),password:form.findField('user.password').getValue(),id:action.result.id,realName:form.findField('user.realName').getValue()});
                    	MsgTip.msg('系统提示','角色添加成功！');
                    	store.load();
                    	win.close();
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    }
                });
            }
        }
    }]
});