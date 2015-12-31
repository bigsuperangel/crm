Ext.define('crm.view.dept.AddDept',{
	extend: 'Ext.form.Panel',
	alias: 'widget.adddept',
	
	url: 'dept_add',
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
	    	var model = btn.up('adddept').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	            		console.info(action)
	                	win.close();
	                	console.log(">>"+action.result.dept);
	                	if(model.get('leaf') == true){
	                		model.set('leaf', false);
	                		model.appendChild(action.result.dept);
	                	}else{
	                		model.appendChild(action.result.dept);
	                	}
//	                	btn.up('adddept').getView().refresh();
	                	MsgTip.msg('系统提示','新部门添加成功！');
	                },
	                failure: function(form, action) {
	                	console.info(action);
	                    Ext.Msg.alert('系统提示', "新部门添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			Ext.getCmp('tips').html = '您要在['+obj.model.get('text')+']下添加一个新部门';
			obj.getForm().findField('parent.id').setValue(obj.model.get('id'));
		}
		
	}
});