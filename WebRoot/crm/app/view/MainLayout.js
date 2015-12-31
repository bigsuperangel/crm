Ext.define("crm.view.MainLayout",{
	extend: 'Ext.panel.Panel',
	alias: 'widget.mainlayout',
	defaults: {
		split: true,
		bodyStyle: 'padding:1px'
	},
	layout: 'border',
	items: [{//左侧
		title: '操作菜单',
		iconCls: 'dept_tree',
		region: 'west',
		xtype: 'panel',
		margins: '0 0 0 3',
		width: 200,
		collapsible: true,//面板可以折叠
		id: 'west-tree',
		layout: 'fit',
		items: [{
			id: 'dept-tree',
			xtype:'menutree'
		}]
	},{//右侧中间主界面
		title: '首页',
		iconCls: 'icon170',
		region: 'center',
		xtype: 'panel',
		margins: '0 3 0 0',
		id: 'mainPanel',
		layout: 'fit',
		items: [{
			xtype: 'index'
		}],
		tools: [{
            text: '更换皮肤',
            id: 'mybtn',
            tooltip: '更换皮肤',
            handler: function(){
            	var win = Ext.create('Ext.window.Window',{
            		title: '皮肤设置',
            		height: 90,
            		width: 240,
            		layout: 'form',
            		items:[{
            			xtype: 'panel',
            			border: 0,
            			items:[{
            				xtype: 'button',
            				text: 'Blue',
            				margin: '2 2 2 2',
            				handler:function(){//topTheme
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/blue/css/ext-all-blue.css');
//            					Ext.util.CSS.swapStyleSheet('topTheme', '../ext4.1/resources/blue/css/ext-all-blue.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/blue/css/ext-all-blue.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'Custom',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/custom/css/my-ext-custom.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/custom/css/my-ext-custom.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'DarkGreen',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/darkGreen/css/ext-all-darkGreen.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/darkGreen/css/ext-all-darkGreen.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'Green',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/green/css/ext-all-green.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/green/css/ext-all-green.css');
            					Ext.util.CSS.swapStyleSheet('topTheme', 'resources/css/top-green.css');
            					localStorage.setItem('topTheme','resources/css/top-green.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'Pink',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/pink/css/ext-all-pink.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/pink/css/ext-all-pink.css');
            					Ext.util.CSS.swapStyleSheet('topTheme', 'resources/css/top-pink.css');
            					localStorage.setItem('topTheme','resources/css/top-pink.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'Purple',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/purple/css/ext-all-purple.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/purple/css/ext-all-purple.css');
            					Ext.util.CSS.swapStyleSheet('topTheme', 'resources/css/top-purple.css');
            					localStorage.setItem('topTheme','resources/css/top-purple.css');
            				}
            			},{
            				xtype: 'button',
            				text: 'Yellow',
            				margin: '2 2 2 2',
            				handler:function(){
            					Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/yellow/css/ext-all-yellow.css');
            					localStorage.setItem('myTheme','../ext4.1/resources/yellow/css/ext-all-yellow.css');
            				}
            			}]
            		}]
            	});
            	win.show();
            }
        }]
//		tools: [
//	            {type:'toggle'},
//	            {type:'close'},
//	            {type:'minimize'},
//	            {type:'maximize'},
//	            {type:'restore'},
//	            {type:'gear'},
//	            {type:'pin'},
//	            {type:'unpin'},
//	            {type:'right'},
//	            {type:'left'},
//	            {type:'down'},
//	            {type:'refresh'},
//	            {type:'minus'},
//	            {type:'plus'},
//	            {type:'help'},
//	            {type:'search'},
//	            {type:'save'},
//	            {type:'print'}
//	        ]
	},{//顶部
		region: 'north',
		xtype: 'panel',
		height: 80,
		id: 'north-panel',
		border: 0,
		margins: '0 3 0 3',
		html: "<table width='100%' height='80' border='0' cellpadding='0' cellspacing='0'>"+
		  "<tr>"+
			"<td width='500' id='bkt'>&nbsp;</td>"+
			"<td align='right' valign='top' id='bke'>"+
			"<iframe src='user_loginInfo' width='500' align='middle' marginwidth='1' marginheight='1' frameborder='0'"+ 
			"scrolling='No'> </iframe></td>"+
		  "</tr>"+
		"</table>"
	},{//底部
		region: 'south',
		title: "<div align='center'>CRM管理系统</div>",
		xtype: 'panel',
		height: 26,
		id: 'south-panel',
		margins: '0 3 0 3'
	}]
});
