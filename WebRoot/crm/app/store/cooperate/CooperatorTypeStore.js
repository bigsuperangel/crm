//SalesOppStore.js
Ext.define('crm.store.cooperate.CooperatorTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['val', 'name'],
	data : [{
		"val" : 0,
		"name" : "跨区域合作"
	}, {
		"val" : 1,
		"name" : "跨大区合作"
	}]
});