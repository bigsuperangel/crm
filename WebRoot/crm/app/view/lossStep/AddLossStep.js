Ext.define('crm.view.lossStep.AddLossStep',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addlossstep',
	
	url: 'loss_add',
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
		name: 'loss.outflow.id',
		id: 'id',
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
	    	var store = btn.up('addlossstep').store;
	    	var ch = btn.up('addlossstep').ch;
	        var form = this.up('form').getForm();
	        var cid = btn.up('addlossstep').model.get('oid');
	        if (form.isValid()) {
	            form.submit({
	                success: function(form, action) {
	                	win.close();
	                	if(ch == 1){//store != null && store != undefined
	                		store.load({
	                			params:{
	                				cid: cid
	                			}
	                		});
	                	}
	                	MsgTip.msg('系统提示','措施添加成功！');
	                },
	                failure: function(form, action) {
	                    Ext.Msg.alert('系统提示', "措施添加失败！");
	                }
	            });
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
			if(obj.ch == 0){
				Ext.getCmp('tips').html = '为流失预警：<span style="color:red">'+obj.model.get('customerName')+'</span>填写流失措施：';
				Ext.getCmp('id').setValue(obj.model.get('id'));
			}else if(obj.ch == 1){
				Ext.getCmp('tips').html = '为流失预警：<span style="color:red">'+obj.model.get('customerName')+'</span>填写流失措施：';
				Ext.getCmp('id').setValue(obj.model.get('oid'));
			}
		}
	}
});