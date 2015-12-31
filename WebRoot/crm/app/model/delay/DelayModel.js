/**
 * 延时申请列表
 */
Ext.define('crm.model.delay.DelayModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'customerId', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'applierId', type: 'int'},
        {name: 'applier', type: 'string'},  //延时申请人
        {name: 'delayDays',type:'int'},
        {name: 'audit', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'remark', type: 'string'}
    ]
});