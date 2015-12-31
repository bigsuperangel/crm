Ext.define('crm.view.person.UpdatePerson',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateperson',
	
	url: 'person_update',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
		xtype: 'text',
		id: 'tips',
		height: 'auto'
	},{
    	xtype: 'textfield',
    	name: 'customer.id',
    	hidden: true
    },{
    	xtype: 'textfield',
    	name: 'id',
    	hidden: true
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'name',
        	fieldLabel:'姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
        	labelWidth: 60,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '姓名不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
			xtype: 'combobox',
		    fieldLabel: '性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别',
		    name: 'sex',
		    store: {
		    	xtype: 'store',
		        fields: ['val', 'name'],
		        data : [
					{"val":1, "name":"男"},
					{"val":0, "name":"女"}
		        ]
		    },
		    labelWidth: 60,
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'val',
		    allowBlank: false,
			blankText: '性别不能为空！',
			emptyText: '请选择联系人性别...',
		    editable: false
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'post',
        	fieldLabel:'职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务',
        	labelWidth: 60,
        	maxLength: 10
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'phone',
        	fieldLabel:'联系电话',
        	labelWidth: 60,
        	maxLength: 15
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'tel',
        	fieldLabel:'手机号码',
        	labelWidth: 60,
        	maxLength: 11,
        	allowBlank:false, 
        	blankText: '手机号码不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'qq',
        	fieldLabel:'QQ',
        	labelWidth: 60,
        	maxLength: 15
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'email',
        	fieldLabel:'EMAIL',
        	labelWidth: 60,
        	maxLength: 50
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'wangWang',
        	fieldLabel:'旺旺',
        	labelWidth: 60,
        	maxLength: 30
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'descr',
        	fieldLabel:'描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述',
        	labelWidth: 60,
        	maxLength: 50,
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
	    	var store = btn.up('updateperson').store;
	    	var model = btn.up('updateperson').btn.up('personlist').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load({
	         				params:{
	           			       cid: model.get('id')
	           			    }
	           			});
	                	MsgTip.msg('系统提示','客户联系人添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "客户联系人添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			var btn = obj.btn;
			var model = btn.up('personlist').model;
			var person = obj.model;
			obj.getForm().findField('customer.id').setValue(model.get('id'));
			Ext.getCmp('tips').html = '修改客户：【'+model.get('customerName')+'】的联系人：';
			obj.getForm().loadRecord(person);
			/*obj.getForm().findField('id').setValue(person.get('id'));
			obj.getForm().findField('name').setValue(person.get('name'));
			obj.getForm().findField('sex').setValue(person.get('sex'));
			obj.getForm().findField('post').setValue(person.get('post'));
			obj.getForm().findField('phone').setValue(person.get('phone'));
			obj.getForm().findField('tel').setValue(person.get('tel'));
			obj.getForm().findField('descr').setValue(person.get('descr'));
			obj.getForm().findField('qq').setValue(person.get('qq'));
			obj.getForm().findField('wangWang').setValue(person.get('wangWang'));
			obj.getForm().findField('email').setValue(person.get('email'));*/
			
		}
	}
});