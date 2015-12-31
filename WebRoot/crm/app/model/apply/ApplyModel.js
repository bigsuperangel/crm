/**
 * 公海客户申请列表
 */
Ext.define('crm.model.apply.ApplyModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'customerId', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'applierId', type: 'int'},
        {name: 'applierName', type: 'string'},
        {name: 'status', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'remarks', type: 'string'}
    ]
});