Ext.define('crm.view.dept.UpdateDept',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updatedept',
	
	url: 'dept_update',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	xtype: 'text',
    	html: '',
    	id: 'tips'
    },{
    	xtype: 'textfield',
    	name: 'id',
    	hidden: true
    },{
    	xtype: 'textfield',
    	name: 'parent.id',
    	hidden: true
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'name',
        	fieldLabel:'部门名称',
        	labelWidth: 60,
        	maxLength: 20
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
	    	var model = btn.up('updatedept').model;
	    	var store = btn.up('updatedept').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	model.set('text', form.findField('name').getValue());
	                	store.sync();
	                	MsgTip.msg('系统提示','部门修改成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "部门修改失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			Ext.getCmp('tips').html = '您要修改['+obj.model.get('text')+']部门';
			obj.getForm().findField('id').setValue(obj.model.get('id'));
			obj.getForm().findField('name').setValue(obj.model.get('text'));
			obj.getForm().findField('parent.id').setValue(obj.model.parentNode.get('id'));
			
		}
		
	}
});