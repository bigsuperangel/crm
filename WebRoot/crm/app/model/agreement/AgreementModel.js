Ext.define('crm.model.agreement.AgreementModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'agreementNo', type: 'string'},
        {name: 'status', type: 'int'},
        {name: 'projectName', type: 'string'}, 
        {name: 'cId', type: 'int'},
        {name: 'signDate', type: 'string'},
        {name: 'paymentClearDate', type: 'string'},
        {name: 'finishDate', type: 'string'},
        {name: 'amount', type: 'int'},
        {name: 'subAgreementCount', type: 'int'},
        {name: 'paymentStatus', type: 'string'},
        {name: 'mySigner', type: 'string'},
        {name: 'customerSigner', type: 'string'},
        {name: 'remark', type: 'string'},
        {name: 'isReceipt', type: 'int'},
        {name: 'customerName',type:'string'}
    ]
});
