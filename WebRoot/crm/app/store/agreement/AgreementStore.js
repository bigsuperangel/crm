Ext.define('crm.store.agreement.AgreementStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.agreement.AgreementModel',
	pageSize: limit,
	proxy: {
		type: 'ajax',
		url: 'agreement_queryList',
		reader: {
			type: 'json',
			root: 'topics',
			totalProperty: 'total'
		}
	},
	autoLoad: false// {start: 0, limit: limit}
});