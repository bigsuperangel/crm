Ext.define('crm.model.payment.PaymentModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'customerId', type: 'int'},
        {name: 'agreementId', type: 'int'},
        {name: 'agreementNo', type: 'string'},
        {name: 'receiveDate', type: 'string'},
        {name: 'receiveAmount', type: 'int'},
        {name: 'payMode', type: 'int'},
        {name: 'receiver', type: 'string'},
        {name: 'receiveAccount', type: 'string'},
        {name: 'payer', type: 'string'},
        {name: 'payAccount', type: 'string'},
        {name: 'status', type: 'string'},
        {name: 'receipt', type: 'string'},
        {name: 'cdate', type: 'string'}
    ]
});