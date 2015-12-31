Ext.define('crm.view.priv.AddPriv',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addpriv',
	
	url: 'priv_add',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	xtype: 'textfield',
    	hidden:true,
    	name: 'parent.id'
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textfield',
        	name: 'name',
        	fieldLabel:'权限名称',
        	labelWidth: 80,
        	maxLength: 100,
        	allowBlank:false, 
        	blankText: '权限名称不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'numberfield',
        	name: 'indx',
        	maxValue: 99,
        	minValue: 0,
        	value:0,
        	fieldLabel:'排序',
        	labelWidth: 80,
        	maxLength: 5
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textfield',
        	name: 'config',
        	fieldLabel:'菜单配置',
        	labelWidth: 80,
        	maxLength: 100
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textfield',
        	name: 'iconCls',
        	fieldLabel:'菜单样式',
        	labelWidth: 80,
        	maxLength: 100
    	}]
    }],
	
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
	    	var store = btn.up('addpriv').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','菜单添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "菜单添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners: {
		beforerender: function(obj, eOpts ){
    		var model = obj.model;
    		var id;
    		if(model){
    			id = obj.model.get('id');
    		}else{
    			id = 1;
    		}

			obj.getForm().findField('parent.id').setValue(id);
		}
	}
});