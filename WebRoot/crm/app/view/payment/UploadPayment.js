/**
 * 上传款项
 */
Ext.define('crm.view.payment.UploadPayment',{
	extend: 'Ext.form.Panel',
	alias: 'widget.uploadpayment',
	
	url: 'upload',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{
    	xtype: 'textfield',
    	name: 'ownerId',
    	hidden: true
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'name',
        	fieldLabel:'文件名',
        	labelWidth: 60,
        	maxLength: 50
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
        	xtype: 'textfield',
        	name: 'description',
        	fieldLabel:'描述',
        	labelWidth: 60,
        	maxLength: 100
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.9,
    		labelWidth: 60,
            xtype: 'filefield',
            id: 'form-file',
            emptyText: '请选择文件...',
            fieldLabel: '文件',
            name: 'uploadFile',
            buttonText: '',
            buttonConfig: {
                iconCls: 'icon1682'
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
	    	var model = btn.up('uploadpayment').model;
	        var form = this.up('form').getForm();
	        if (form.isValid()) {
	        	Ext.MessageBox.confirm('系统提示', '<span style="color:red">是否确定上传</span>', function(optional){
					if(optional == 'yes' || optional == '是'){
			            form.submit({
			            	waitMsg:'正在上传中......',
			            	params: {
						        'ownerId': model.get('id'),
						        'type' :2
						    },
			                success: function(form, action) {
			                	MsgTip.msg('系统提示','上传成功！');
			                	win.close();
			                },
			                failure: function(form, action) {
			                    Ext.Msg.alert('系统提示', "上传失败！");
			                }
			            });
					}
				});
	        }
	    }
	}],
	listeners:{
		beforerender: function(obj, eOpts ){
    		obj.getForm().findField('ownerId').setValue(obj.model.get('id'));
		}
	}
});