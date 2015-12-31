Ext.define('crm.view.salesOpp.AppointSalesOpp',{
	extend: 'Ext.form.Panel',
	alias: 'widget.appointsalesopp',
	
	url: 'salesOpp_appointSalesOpp',
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
		name: 'IDs',
		id: 'ids',
		hidden: true
	},{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
			xtype: 'combobox',
	    	store: 'user.UserComboboxStore',
	    	name: 'userId',
	    	fieldLabel: '销售经理',
	    	displayField: 'realName',
	        valueField: 'id',
	        mode: 'local',
//	        multiSelect: true,
	    	labelWidth: 60,
	    	allowBlank: false,
	    	blankText: '销售经理不能为空！',
	    	emptyText: '请选择销售经理...',
	        editable: false
    	}]
    }],
    
 // Reset and Submit buttons
	buttons: [{
	    text: '清空',
	    handler: function() {
	        this.up('form').getForm().reset();
	    }
	},{
	    text: '提交',
	    formBind: true, //only enabled once the form is valid
	    disabled: true,
	    handler: function(btn) {
	    	var win = btn.up('window');
	    	var store = btn.up('appointsalesopp').store;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示', '销售机会指派成功');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "销售机会添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners: {
		beforerender: function(obj, eOpts ){
			Ext.getCmp('tips').html = '指派：<span style="color:red">'+obj.tips+'</span>给：';
			Ext.getCmp('ids').setValue(obj.ids);
		}
	}
});