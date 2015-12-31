Ext.define("crm.view.menu.MenuTree",{
	extend: 'Ext.tree.Panel',
	alias: 'widget.menutree',//{xtype:'menutree'}
	rootVisible:false,//不展示ROOT
	displayField:'text',
	animate: true,
	store: 'menu.MenuTreeStore'
});