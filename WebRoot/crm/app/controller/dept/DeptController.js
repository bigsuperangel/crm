Ext.define('crm.controller.dept.DeptController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'depttree button[id=typeOpen]':{
				click:function (btn){
					btn.up('depttree').expandAll();
				}
			},
			'depttree button[id=typeClose]':{
				click:function (btn){
					btn.up('depttree').collapseAll();
//					btn.up('depttree').expandNode({id:1, text:'所有部门'});
				}
			},
			'depttree button[id=add]':{
				click:function (btn){
					var data = btn.up('depttree').getSelectionModel().getSelection();
					if(data.length<=0){
						MsgTip.msg('系统提示','请先选择新部门所属的上级部门，顶级部门请选择“所有部门”');
						return ;
					}
					Ext.create('Ext.window.Window',{
						title: '添加部门',
						iconCls: btn.iconCls,
						width: 300,
						height: 150,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'adddept', model: data[0]}]
					}).show();
				}
			},
			'depttree button[id=update]':{
				click:function (btn){
					var data = btn.up('depttree').getSelectionModel().getSelection();
					if(data.length<=0){
						MsgTip.msg('系统提示','请先选择要修改的部门');
						return ;
					}
					Ext.create('Ext.window.Window',{
						title: '修改部门',
						iconCls: btn.iconCls,
						width: 300,
						height: 140,
						modal: true,
						layout: 'fit',
						items:[{xtype: 'updatedept', model: data[0], store: btn.up('depttree').store}]
					}).show();
				}
			},
			'depttree button[id=delete]':{
				click:function (btn){
					var store = btn.up('depttree').store;
					var data = btn.up('depttree').getSelectionModel().getSelection();
					if(data.length <= 0){
						MsgTip.msg('系统提示', '您没有选择要删除的部门');
						return ;
					}
					var titles = [];
					var ids = [];
					Ext.Array.each(data, function(record, index, countriesItSelf) {
				    	titles.push(record.get('text'));
				    	ids.push(record.get('id'));
					});
					Ext.MessageBox.confirm('系统提示', '您是否要删一下部门：<span style="color:red"><br>['+titles.join(']<br>[')+']</span><br>删除后不可恢复', function(optional){
						if(optional == 'yes' || optional == '是'){
							Ext.Ajax.request({
							    url: 'dept_delete',
							    params: {
							        ids: ids
							    },
							    success: function(response){
							    	var json = Ext.decode(response.responseText);
							    	if(json.success){
								    	Ext.Array.each(data, function(record, index, countriesItSelf) {
									    	record.remove();
										});
							    	}else{
							    		Ext.MessageBox.alert('系统提示','删除失败！');
							    	}
							    },
							    failure: function(response){
							    	Ext.MessageBox.alert('系统提示','由于网络原因，请求发送失败！');
							    }
							});
						}
					});
				}
			}
		});
	},
	views : [
	    'dept.DeptTree',
	    'dept.AddDept',
	    'dept.UpdateDept'
	],
	stores : [
	    'dept.DeptTreeStore'
	],
	models : [
	    
	]
});
