Ext.define('crm.view.customer.AddCustomer',{
	extend: 'Ext.form.Panel',
	alias: 'widget.addcustomer',
	
	url: 'customer_add',
	border: false,
    bodyPadding: 5,
	layout: {
        type: 'form'
    },
    items:[{//第一行
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:1,
    		xtype: 'fieldcontainer',
	        layout: 'hbox',
		    items: [{
	        	xtype: 'textfield',
	        	id : 'customerName',
	        	flex: 4,
	        	name: 'customerName',
	        	fieldLabel:'客户名称',
	        	labelWidth: ww.common.customerLabelWidth,
	        	maxLength: 100,
	        	allowBlank:false, 
	        	blankText: '客户名称不能为空！',
	        	validateOnBlur : true,
                validateOnChange: false,
	        	validator : function(value){
	        		var error = true;
	        		if(value){
	        			Ext.Ajax.request({
	        				async: false,
	        				url: 'customer_checkConflict',
	        				params: {
	        					name:value
	        				},
	        				success: function(response){
	        					var json = Ext.decode(response.responseText);
	        					if(json.success){
	        					}else{
	        						error = '客户名称已被占用';
	        					}
	        				},
	        				failure: function(response){
	        					Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
	        				}
	        			});
	        		}
	        		return error;
	        	},
	        	listeners: {
                    focus: {
                        fn: function () { this.clearInvalid(); }
                    }
                }
		    },{
		        xtype: 'button',
            	text : '冲突检测',
//            	enableToggle: true,
            	id : 'check_customer',
		        flex: 1
//		        listeners: {
//			        click: function() {
//			            // this == the button, as we are in the local scope
////			            this.setText('I was clicked!');
//			            alert(Ext.getCmp('customerName').getValue());
//			        },
//			        mouseover: function() {
//			            // set a new config which says we moused over, if not already set
////			            if (!this.mousedOver) {
////			                this.mousedOver = true;
////			                alert('You moused over a button!\n\nI wont do this again.');
////			            }
//			        }
//			    }
		    }]

//        	xtype: 'textfield',
//        	name: 'customerName',
//        	fieldLabel:'客户名称',
//        	labelWidth: ww.common.customerLabelWidth,
//        	maxLength: 20,
//        	allowBlank:false, 
//        	blankText: '客户名称不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:1,
    		xtype: 'fieldcontainer',
	        layout: 'hbox',
		    items: [{
	        	xtype: 'textfield',
	        	flex: 4,
	        	xtype: 'textfield',
	        	id : 'brand',
	        	name: 'brand',
	        	fieldLabel:'品牌名称',
	        	labelWidth: ww.common.customerLabelWidth,
	        	maxLength: 100,
	        	allowBlank:false, 
	        	blankText: '品牌名称不能为空！',
	        	validateOnBlur : true,
                validateOnChange: false,
	        	validator : function(value){
	        		var error = true;
	        		if(value){
	        			Ext.Ajax.request({
	        				async: false,
	        				url: 'customer_checkConflict',
	        				params: {
	        					name:value
	        				},
	        				success: function(response){
	        					var json = Ext.decode(response.responseText);
	        					if(json.success){
	        					}else{
	        						error = '品牌名称已被占用';
	        					}
	        				},
	        				failure: function(response){
	        					Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
	        				}
	        			});
	        		}
	        		return error;
	        	},
	        	listeners: {
                    focus: {
                        fn: function () { this.clearInvalid(); }
                    }
                }
		    },{
		        xtype: 'button',
            	text : '冲突检测',
            	id : 'check_brand',
		        flex: 1
		    }]
//        	xtype: 'textfield',
//        	name: 'brand',
//        	fieldLabel:'品牌名称',
//        	labelWidth: ww.common.customerLabelWidth,
//        	maxLength: 100,
//        	allowBlank:false, 
//        	blankText: '品牌名称不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.8,
        	xtype: 'textfield',
        	name: 'companyAddress',
        	fieldLabel:'公司地址',
        	labelWidth: ww.common.customerLabelWidth,
        	maxLength: 100,
        	allowBlank:false, 
        	blankText: '公司地址不能为空！'
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
    		columnWidth:.3,
        	xtype: 'numberfield',
        	name: 'operateBrand',
        	maxValue: 99,
        	minValue: 0,
        	value: 0,
        	fieldLabel:'代运营品牌数',
        	labelWidth: ww.common.customerLabelWidth,
        	maxLength: 5
    	}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
	    	columnWidth:.6,
	    	xtype: 'comboxtree',
	    	storeUrl: 'dept_getMyDeptTree',
	    	fullPath: true,
	    	name: 'dept.id',
	    	fieldLabel:'所在地区',
	    	labelWidth: 60,
	    	labelWidth: ww.common.customerLabelWidth,
	    	allowBlank:false,
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
			xtype: 'combobox',
			store: 'type.TypeCompanyTypeComboboxStore',
			name: 'companyType',
			fieldLabel: '公司类型',
			displayField: 'name',
		    valueField: 'value',
		    mode: 'local',
//		    triggerAction:'query',
//		    queryParam : 'type',
			labelWidth: ww.common.customerLabelWidth,
			allowBlank: false,
			typeAhead : true,
			 selectOnFocus : true,
//			allQuery : 'company_type',
			blankText: '公司类型不能为空！',
			emptyText: '请选择公司类型...',
		    editable: false,
		    listeners:{
            	beforerender: function(obj, eOpts){
//    				obj.store.removeAll();
//    		    	obj.store.getProxy().extraParams = {
//				        type:'company_type'
//				    };
//            		obj.store.load({params:{type:'company_type'}});
            	},
//            	focus: function(obj, eOpts){
//            		obj.store.load({params:{type:'company_type'}});
//            		return false;
//            	}
            }
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:.6,
			xtype: 'combobox',
			store: 'type.TypeSaleModeComboboxStore',
			name: 'saleMode',
			fieldLabel: '销售模式',
			displayField: 'name',
		    valueField: 'value',
		    mode: 'local',
			labelWidth: ww.common.customerLabelWidth,
			allowBlank: false,
			blankText: '销售模式不能为空！',
			emptyText: '请选择销售模式...',
		    editable: false,
		    listeners:{
//            	beforerender: function(obj, eOpts){
//    				obj.store.removeAll();
//            		obj.store.getProxy().extraParams = {
//				        type:'sale_mode'
//				    };
//            		obj.store.load();
//            	},
//            	focus: function(obj, eOpts){
//            		obj.store.load({params:{type:'sale_mode'}});
//            	}
            }
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:1,
			xtype: 'checkboxgroup',
			id: 'storePlatform1',
	        fieldLabel: '开店平台',
	        allowBlank: false,
			blankText: '开店平台不能为空！',
	        columns: 5,
	        labelWidth: ww.common.customerLabelWidth,
//	        vertical: true,
	        items: [
	            { boxLabel: '淘宝系', name: 'storePlatform', inputValue: '1' },
	            { boxLabel: '拍拍系', name: 'storePlatform', inputValue: '2' },
	            { boxLabel: '京东商城', name: 'storePlatform', inputValue: '3' },
	            { boxLabel: '当当', name: 'storePlatform', inputValue: '4' },
	            { boxLabel: '卓越', name: 'storePlatform', inputValue: '5' },
	            { boxLabel: '１号店', name: 'storePlatform', inputValue: '6' },
	            { boxLabel: '马可波罗', name: 'storePlatform', inputValue: '7' },
	            { boxLabel: '慧聪', name: 'storePlatform', inputValue: '8' },
	            { boxLabel: '其他', name: 'storePlatform', inputValue: '9' }
	        ]
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:.6,
			xtype: 'combobox',
			store: 'type.TypeBrandAwarenessComboboxStore',
			name: 'brandAwareness',
			fieldLabel: '品牌知名度',
			displayField: 'name',
		    valueField: 'value',
		    mode: 'local',
			labelWidth: ww.common.customerLabelWidth,
			allowBlank: false,
			blankText: '品牌知名度不能为空！',
			emptyText: '请选择品牌知名度...',
		    editable: false,
		    listeners:{
//            	beforerender: function(obj, eOpts){
//    				obj.store.removeAll();
//    		       obj.store.getProxy().extraParams = {
//				        type:'brand_awareness'
//				    };
//            		obj.store.load();
//            	}
            }
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:.6,
			xtype: 'combobox',
			store: 'type.TypeCompanyAwarenessComboboxStore',
			name: 'companyAwareness',
			fieldLabel: '公司知名度',
			displayField: 'name',
		    valueField: 'value',
		    mode: 'local',
			labelWidth: ww.common.customerLabelWidth,
			allowBlank: false,
			blankText: '公司知名度不能为空！',
			emptyText: '请选择公司知名度...',
		    editable: false,
		    listeners:{
//            	beforerender: function(obj, eOpts){
//    				obj.store.removeAll();
//    		    	obj.store.getProxy().extraParams = {
//				        type:'company_awareness'
//				    };
//            		obj.store.load();
//            	}
            }
		}]
    },{
    	layout:'column',
    	border:false,
    	items:[{
			columnWidth:1,
			id : 'customerSource1',
			xtype: 'checkboxgroup',
	        fieldLabel: '客户来源',
	        columns: 6,
	        allowBlank: false,
			blankText: '客户来源不能为空！',
	        labelWidth: ww.common.customerLabelWidth,
	        vertical: true,
	        items: [
	            { boxLabel: '电话营销', name: 'customerSource', inputValue: '1' },
	            { boxLabel: '会议营销', name: 'customerSource', inputValue: '2'},
	            { boxLabel: '主动联系', name: 'customerSource', inputValue: '3' },
	            { boxLabel: '客户推荐', name: 'customerSource', inputValue: '4' },
	            { boxLabel: '淘拍档', name: 'customerSource', inputValue: '5' },
	            { boxLabel: '其他', name: 'customerSource', inputValue: '6' }
	        ]
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
	    	var store = btn.up('addcustomer').store;
	        var form = this.up('form').getForm();
//	        var storePlatform ;
//	        var storePlatformArr = Ext.getCmp('storePlatform').getValue().storePlatform;
//	        if(storePlatformArr.length>1){
//	        	storePlatform = storePlatformArr.join(',')
//	        }else {
//	        	storePlatform = storePlatformArr.toString();
//	        }
//	        var customerSource ;
//	        var customerSourceArr = Ext.getCmp('customerSource').getValue().customerSource;
//	        if(customerSourceArr.length>1){
//	        	customerSource = customerSourceArr.join(',')
//	        }else{
//	        	customerSource = customerSourceArr.toString();
//	        }
			var storePlatform = ww.common.getCheckboxgroup(Ext.getCmp('storePlatform1').getValue().storePlatform);
			var customerSource = ww.common.getCheckboxgroup(Ext.getCmp('customerSource1').getValue().customerSource);
			if (form.isValid()) {
				form.submit({
					params: {
						'storePlatform': storePlatform,
						'customerSource': customerSource,
						status:1
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