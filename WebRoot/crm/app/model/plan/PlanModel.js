Ext.define('crm.model.plan.PlanModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'sid', type: 'int'},//销售机会id
        {name: 'pid', type: 'int'},
        {name: 'createDate', type: 'string'},
        {name: 'customer', type: 'string'},
        {name: 'person', type: 'string'},
        {name: 'state', type: 'int'},
        {name: 'success', type: 'int'},
        {name: 'summery', type: 'string'},
        {name: 'tel', type: 'string'},
        {name: 'userName', type: 'string'},
        {name: 'cdate', type: 'string'},
        {name: 'content', type: 'string'}
    ]
});