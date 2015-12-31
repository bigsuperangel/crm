Ext.define('crm.model.cooperate.CooperateModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'customerId', type: 'string'},
        {name: 'cooperatorType', type: 'int',
        	convert: function (value, record) {
                if (Ext.isNumber(value)){
                	if(value == 0){
	        			return "跨区域合作";
	        		}else if(value == 1){
	        			return "跨大区合作";
	        		}
                }
            }
        },
        {name: 'submitor', type: 'string'},
        {name: 'submitorId', type: 'string'},
        {name: 'receiver', type: 'string'},
        {name: 'receiverId', type: 'string'},
        {name: 'status', type: 'int',
        	convert: function (value, record) {
                if (Ext.isNumber(value)){
                	if(value == 0){
	        			return "新增合作";
	        		}else if(value == 1){
	        			return "大区经理审批";
	        		}else if(value==2){
	        			return "对方大区经理审批";
	        		}else if(value==9){
	        			return "合作成功";
	        		}else if(value==-1){
	        			return "合作失败";
	        		}
                }
            }
        },
        {name: 'submitDept', type: 'string'},
        {name: 'targetDept', type: 'string'},
        {name: 'cdate', type: 'string'},
        {name: 'opTime', type: 'string'}
    ]
});