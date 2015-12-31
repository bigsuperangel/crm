/**
 * 指派合作
 */
Ext.define('crm.view.cooperate.DispatchCooperate',{
	extend: 'Ext.form.Panel',
	alias: 'widget.dispatchcooperate',
	
	url: 'cooperation_audit',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.6,
			xtype: 'combobox',
	    	store: 'user.UserComboboxStore',
	    	name: 'receiverId',
	    	fieldLabel: '负&nbsp;责&nbsp;&nbsp;人',
	    	displayField: 'realName',
	        valueField: 'id',
	        mode: 'local',
//	        multiSelect: true,
	    	labelWidth: ww.common.contactLabelWidth,
	    	allowBlank: false,
	    	blankText: '负责人不能为空！',
	    	emptyText: '请选择负责人...',
	        editable: false,
	    	listeners:{
	        	beforerender: function(obj, eOpts){
    				var model = obj.up('dispatchcooperate').model;
    				console.info(model.raw.targetDeptId);
    				obj.store.getProxy().extraParams = {
				        deptId:model.raw.targetDeptId
				    };
    				obj.store.load();
//    				obj.store.removeAll();
//    				obj.store.getProxy().url = 'user_getUserCombobox';
//	        		obj.store.load({params:{deptId:model.raw.targetDeptId}});
	        	}
	        }
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
	    	var store = btn.up('dispatchcooperate').store;
	    	var model = btn.up('dispatchcooperate').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	            form.submit({
	            	params: {
				        'cooperateId': model.get('id'),
				    },
	                success: function(form, action) {
	                	win.close();
	                	store.load();
	                	MsgTip.msg('系统提示','客户信息添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "客户信息添加失败！");
	                }
	            });
	        }
	    }
	}]
});