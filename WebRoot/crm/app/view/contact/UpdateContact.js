Ext.define('crm.view.contact.UpdateContact',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updatecontact',
	
	url: 'contact_update',
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
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'title',
        	fieldLabel:'概&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要',
        	labelWidth: 60,
        	maxLength: 20,
        	allowBlank:false, 
        	blankText: '概要不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'addr',
        	fieldLabel:'地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点',
        	labelWidth: 60,
        	maxLength: 50,
        	allowBlank:false, 
        	blankText: '地点不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'contactor',
        	fieldLabel:'联系人',
        	labelWidth: 60,
        	maxLength: 50,
        	allowBlank:false, 
        	blankText: '地点不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'supervisor',
        	fieldLabel:'负责人',
        	labelWidth: 60,
        	maxLength: 50,
        	allowBlank:false, 
        	blankText: '地点不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:.6,
			xtype: 'combobox',
			store: 'type.TypeComboboxStore',
			name: 'contactType',
			fieldLabel: '联系类型',
			displayField: 'name',
		    valueField: 'value',
		    mode: 'local',
			labelWidth: ww.common.contactLabelWidth,
			allowBlank: false,
			blankText: '联系类型不能为空！',
			emptyText: '请选择联系类型...',
		    editable: false,
		    listeners:{
            	beforerender: function(obj, eOpts){
            		obj.store.load();
            	}
            }
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
        	xtype: 'datefield',
        	name: 'cdate',
        	fieldLabel:'交流时间',
        	labelWidth: 60,
        	allowBlank:false, 
        	format: 'Y-m-d H:i:s',
        	value: new Date()
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
        	xtype: 'datefield',
        	name: 'nextVisit',
        	fieldLabel:'下次回访时间',
        	labelWidth: 60,
        	allowBlank:false, 
        	format: 'Y-m-d H:i:s',
        	value: new Date()
    	}]
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
			xtype: 'combobox',
	    	store: 'user.UserComboboxStore',
	    	name: 'user.id',
	    	fieldLabel: '负&nbsp;责&nbsp;&nbsp;人',
	    	displayField: 'realName',
	        valueField: 'id',
	        mode: 'local',
//	        multiSelect: true,
	    	labelWidth: 60,
	    	allowBlank: false,
	    	blankText: '负责人不能为空！',
	    	emptyText: '请选择负责人...',
	        editable: false
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textarea',
        	name: 'content',
        	fieldLabel:'交流详细',
        	labelWidth: 60,
        	maxLength: 100,
        	height: 65
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
	    	var store = btn.up('updatecontact').store;
	    	var model = btn.up('updatecontact').btn.up('contactlist').model;
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
			var model = btn.up('contactlist').model;
			var person = obj.model;
			obj.getForm().findField('customer.id').setValue(model.get('id'));
			Ext.getCmp('tips').html = '修改客户：【'+model.get('customerName')+'】的交往记录：';
			obj.getForm.loadRecord(person);
//			obj.getForm().findField('id').setValue(person.get('id'));
//			obj.getForm().findField('title').setValue(person.get('title'));
//			obj.getForm().findField('addr').setValue(person.get('addr'));
//			obj.getForm().findField('cdate').setValue(person.get('cdate'));
//			obj.getForm().findField('content').setValue(person.get('content'));
//			obj.getForm().findField('user.id').setValue(person.get('userId'));
		}
	}
});