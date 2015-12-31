Ext.define('crm.view.lossStep.UpdateLoss',{
	extend: 'Ext.form.Panel',
	alias: 'widget.updateloss',
	
	url: 'loss_update',
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
    	name: 'loss.id',
    	hidden: true
    },{
    	xtype: 'textfield',
    	name: 'loss.outflow.id',
    	hidden: true
    },{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.68,
        	xtype: 'datefield',
        	name: 'loss.cdate',
        	fieldLabel:'实施时间',
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
			xtype: 'combobox',
			store: 'user.UserComboboxStore',
			name: 'loss.user.id',
			fieldLabel: '负&nbsp;责&nbsp;&nbsp;人',
			displayField: 'realName',
		    valueField: 'id',
		    mode: 'local',
			labelWidth: 60,
			allowBlank: false,
			blankText: '负责人不能为空！',
			emptyText: '请选择负责人...',
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
    		columnWidth:.9,
        	xtype: 'textarea',
        	name: 'loss.content',
        	fieldLabel:'实施内容',
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
	    	var store = btn.up('updateloss').store;
	    	var model = btn.up('updateloss').btn.up('losslist').model;
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
			var model = btn.up('losslist').model;//流失预警
			var person = obj.model;//暂缓措施
			
			obj.getForm().findField('loss.outflow.id').setValue(model.get('id'));
			Ext.getCmp('tips').html = '修改流失预警：【'+model.get('customerName')+'】的暂缓措施：';
			obj.getForm().findField('loss.id').setValue(person.get('id'));
			obj.getForm().findField('loss.cdate').setValue(person.get('cdate'));
			obj.getForm().findField('loss.content').setValue(person.get('content'));
			obj.getForm().findField('loss.user.id').setValue(person.get('userId'));
		}
	}
});