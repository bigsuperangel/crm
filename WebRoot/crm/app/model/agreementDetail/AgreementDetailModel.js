Ext.define('crm.model.agreement.AgreementDetailModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'agreementId', type: 'int'},
        {name: 'detailId', type: 'int'},
        {name: 'detailName',type:'string'},
        {name: 'value1', type: 'string'},
        {name: 'value2', type: 'string'},
        {name: 'value3', type: 'string'},
        {name: 'value4', type: 'string'}
    ]
});
