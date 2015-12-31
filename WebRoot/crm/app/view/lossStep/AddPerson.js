Ext.define('crm.view.person.AddPerson',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addperson',
	
	url: 'person_add',
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
    	name: 'person.customer.id',
    	hidden: true
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.7,
        	xtype: 'textfield',
        	name: 'person.name',
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
		    name: 'person.sex',
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
        	name: 'person.post',
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
        	name: 'person.phone',
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
        	name: 'person.tel',
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
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'person.descr',
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
	    	var store = btn.up('addperson').store;
	    	var model = btn.up('addperson').btn.up('personlist').model;
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
			obj.getForm().findField('person.customer.id').setValue(model.get('id'));
			Ext.getCmp('tips').html = '为客户：【'+model.get('customerName')+'】添加联系人：';
		}
	}
});