Ext.define('crm.view.user.AddUser',{
	extend: 'Ext.form.Panel',
	alias: 'widget.adduser',
	
	url: 'user_add',
	border: 0,
	bodyPadding: 5,
	items: [{
        fieldLabel: '真实姓名',
        xtype: 'textfield',
        labelWidth: 60,
        name: 'realName',
        allowBlank: false,
        minLength:3,
        maxLength:16
    },{
        fieldLabel: '登录名称',
        xtype: 'textfield',
        labelWidth: 60,
        name: 'username',
        allowBlank: false,
        minLength:3,
        maxLength:16
    },{
        fieldLabel: '登录密码',
        xtype: 'textfield',
        inputType: 'password',
        labelWidth: 60,
        name: 'password',
        allowBlank: false,
        minLength:6,
        maxLength:16
    },{
        fieldLabel: '电子邮件',
        xtype: 'textfield',
        inputType:'email',
        labelWidth: 60,
        name: 'email',
        allowBlank: false
    },{
        fieldLabel: '联系电话',
        xtype: 'textfield',
        labelWidth: 60,
        name: 'tel',
        allowBlank: false,
        minLength:8,
        maxLength:16
    },{
    	columnWidth:.6,
    	xtype: 'comboxtree',
    	storeUrl: 'dept_getTreeCombobox',
    	fullPath: true,
    	name: 'dept.id',
    	fieldLabel:'所在部门',
    	labelWidth: 60,
    	allowBlank:false,
    	listeners:{
        	beforerender: function(obj, eOpts){
        		obj.store.load();
        	}
        }
    },{
    	xtype: 'combobox',
    	store: 'role.RoleComboboxStore',
    	name: 'ids',
    	fieldLabel: '用户角色',
    	displayField: 'name',
        valueField: 'id',
        mode: 'local',
        multiSelect: true,
        triggerAction: 'all',
    	labelWidth: 60,
    	allowBlank: false,
    	blankText: '用户角色不能为空！',
    	emptyText: '请选择用户角色...',
        editable: false,
        listeners:{
        	beforerender: function(obj, eOpts){
        		obj.store.load();
        	}
        }
    },{
		columnWidth:.6,
		xtype: 'combobox',
	    fieldLabel: '是否启用',
	    name: 'enable',
	    store: {
	    	xtype: 'store',
	        fields: ['val', 'name'],
	        data : [
				{"val":1, "name":"开启"},
				{"val":2, "name":"停用"}
	        ]
	    },
	    labelWidth: 60,
	    queryMode: 'local',
	    displayField: 'name',
	    valueField: 'val',
	    allowBlank: false,
		blankText: '是否启用不能为空！',
		emptyText: '请选择是否开启用户...',
	    editable: false
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
            var store = this.up('adduser').store;
            
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
//                        form.owner.win.close();
//                        form.owner.store.add(
//                        	{username:form.findField('user.username').getValue(),password:form.findField('user.password').getValue(),id:action.result.id,realName:form.findField('user.realName').getValue()});
                    	store.load();
                    	win.close();
                    	MsgTip.msg('系统提示','用户添加成功！');
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    }
                });
            }
        }
    }]
});