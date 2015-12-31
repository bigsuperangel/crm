Ext.define('crm.model.customer.CustomerModel',{
	extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'customerName', type: 'string'},
        {name: 'creater', type: 'string'},
        {name: 'createrName', type: 'string'},
        {name: 'handlerName', type: 'string'},
        {name: 'credit', type: 'int'},
        {name: 'brand', type: 'string'},
        {name: 'companyAddress', type: 'string'},
        {name: 'operateBrand', type: 'int'},
        {name: 'status', type: 'int'},
//        {name: 'status', type: 'int',
//        	convert: function (value, record) {
//                if (Ext.isNumber(value)){
//                	if(value == 1){
//	        			return "本地客户";
//	        		}else if(value == 2){
//	        			return "合作客户";
//	        		}
//                }
//            }
//        },
        {name: 'handler', type: 'int'},
        {name: 'cdate', type: 'string'},
        {name: 'freeTime', type: 'string'},
         {name: 'isCooperate', type: 'int'},
//        {name: 'isCooperate', type: 'int',
//        	convert: function (value, record) {
//                if (Ext.isNumber(value)){
//                	if(value == 0){
//	        			return '<span style="color:red">未合作</span>';
//	        		}else if(value == 1){
//	        			return '<span style="color:green">已合作</span>';
//	        		}
//                }
//            }
//        },
        {name: 'auditTime', type: 'string'},
        {name: 'isSign', type: 'int'},
//        {name: 'isSign', type: 'int',
//        	convert: function (value, record) {
//                if (Ext.isNumber(value)){
//                	if(value == 0){
//	        			return '<span style="color:red">未签约</span>';
//	        		}else if(value == 1){
//	        			return '<span style="color:green">已签约</span>';
//	        		}
//                }
//            }
//        },
         {name: 'audit', type: 'int'},
//        {name: 'audit', type: 'int',
//        	convert: function (value, record) {
//                if (Ext.isNumber(value)){
//                	if(value == 0){
//	        			return "未审核";
//	        		}else if(value == 1){
//	        			return '<span style="color:green">已审核</span>';
//	        		}else if(value=-1){
//	        			return '<span style="color:red">已驳回</span>';
//	        		}
//                }
//            }
//        },
         {name: 'saleMode', type: 'string'},
//        {name: 'saleMode', type: 'string',
//        	convert: function (value, record) {
//            	if(value == 1){
//        			return "直营";
//        		}else if(value == 1){
//        			return "分销";
//        		}else if(value==2){
//        			return "直营和分销";
//        		}
//            }
//        },
          {name: 'companyType', type: 'int'},
//        {name: 'companyType', type: 'string',
//        	convert: function (value, record) {
//            	if(value == 1){
//        			return "品牌商";
//        		}else if(value == 2){
//        			return "代运营商";
//        		}
//            }
//        },
        {name: 'storePlatform', type: 'string'},
         {name: 'brandAwareness', type: 'string'},
//        {name: 'brandAwareness', type: 'string',
//        	convert: function (value, record) {
//            	if(value == 1){
//        			return "一线";
//        		}else if(value == 2){
//        			return "二线";
//        		}else if(value==3){
//        			return "三线";
//        		}
//            }
//        },
         {name: 'companyAwareness', type: 'string'},
//        {name: 'companyAwareness', type: 'string',
//        	convert: function (value, record) {
//            	if(value == 1){
//        			return "高";
//        		}else if(value == 2){
//        			return "中";
//        		}else if(value==3){
//        			return "低";
//        		}
//            }
//        },
        {name: 'customerSource', type: 'string'},
        {name: 'dept',type:'string'},
        {name: 'deptName',type:'string'}
    ]
});