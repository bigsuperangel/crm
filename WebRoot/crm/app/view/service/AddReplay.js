Ext.define('crm.view.service.AddReplay',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addreplay',
	
	url: 'service_addReplay',
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
    	name: 'service.id',
    	id: 'sid',
    	hidden: true
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
			xtype: 'combobox',
		    fieldLabel: '客户满意度',
		    name: 'service.satisfied',
		    store: {
		    	xtype: 'store',
		        fields: ['val', 'name'],
		        data : [
					{"val":1, "name":"01|★"},
					{"val":2, "name":"02|★★"},
					{"val":3, "name":"03|★★★"},
					{"val":4, "name":"04|★★★★"},
					{"val":5, "name":"05|★★★★★"}
		        ]
		    },
		    labelWidth: 70,
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'val',
		    allowBlank: false,
			blankText: '客户满意度不能为空！',
			emptyText: '请选择客户满意度...',
		    editable: false
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
	    	var store = btn.up('addreplay').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','客户满意度填写成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "客户满意度填写失败！");
	                }
	            });
	        }
	    }
	}],
	listeners: {
		beforerender: function(obj, eOpts ){
			Ext.getCmp('tips').html='为服务【'+obj.model.get('title')+'】填写反馈信息：';
			Ext.getCmp('sid').setValue(obj.model.get('id'));
		}
	}
});