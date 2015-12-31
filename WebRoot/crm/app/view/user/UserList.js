Ext.define('crm.view.user.UserList',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.userlist',
	
	store: 'user.UserStore',
	
	selModel: {
		selType: 'checkboxmodel'
	},
	columnLines: true,
	multiSelect: true,
	
    columns: [
        {xtype: 'rownumberer',align:'center'},
        { text: '真实姓名',  dataIndex: 'realName'},
        { text: '登录名称', dataIndex: 'username'},
        { text: '电子邮件', dataIndex: 'email',
        	renderer: function(value) {
        		return value.replace('null','');
            }
        },
        { text: '联系电话', dataIndex: 'tel',width:'20%',
        	renderer: function(value) {
        		return value.replace('null','');
            }
        },
        { text: '所在部门', dataIndex: 'deptName' },
        { text: '用户角色', dataIndex: 'roleName',flex: 1 ,
        	renderer: function(value) {
        		return value.replace('null','');
            }
        },
    ],
    tbar:[
        {xtype:'button',id:'add', text:'添加',iconCls:'icon443'},
        {xtype:'button',id:'update', text:'修改',iconCls:'icon445'},
        {xtype:'button',id:'delete', text:'删除',iconCls:'icon444'},
        {xtype:'button',id:'updatepwd', text:'修改密码',iconCls:'icon451'},
        '-',
        {xtype:'button',id:'ok', text:'启用列表',iconCls:'icon293',disabled: true},
        {xtype:'button',id:'forbit', text:'停用列表',iconCls:'icon466'}
    ],
    dockedItems:[{
		xtype:'pagingtoolbar',
		store:'user.UserStore',
		dock:'bottom',
		displayInfo:true
	}],
	listeners: {
		beforerender: function (obj, eOpts ){
			obj.store.getProxy().url = 'user_getList'; 
			obj.store.load();
//			obj.store.load({
//				callback: ww.common.callback
//			});
		}
    }
});