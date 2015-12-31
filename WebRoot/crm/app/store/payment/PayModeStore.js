//SalesOppStore.js
Ext.define('crm.store.payment.PayModeStore', {
	extend : 'Ext.data.Store',
	fields : ['val', 'name'],
	data : [{
		"val" : 1,
		"name" : "支付宝"
	}, {
		"val" : 2,
		"name" : "财付通"
	}, {
		"val" : 3,
		"name" : "转帐"
	}, {
		"val" : 4,
		"name" : "现金"
	}]
});