Ext.define('crm.store.agreement.AgreementDetailStore',{
	extend: 'Ext.data.Store',
	model: 'crm.model.agreement.AgreementDetailModel',
	pageSize: limit,
	proxy: {
		type: 'ajax',
		url: 'agreementDetail_getList',
		reader: {
			type: 'json',
			root: 'topics',
			totalProperty: 'total'
		}
	},
	autoLoad: false// {start: 0, limit: limit}
});