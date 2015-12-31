/**
 * 树形结构下拉框
 */
Ext.define('Ext.ux.TreeComboBox',{
        extend : 'Ext.form.field.ComboBox',
        alias: 'widget.comboxtree',
        
        store : new Ext.data.ArrayStore({fields:[],data:[[]]}),
        editable : false,
//      listConfig : {resizable:true,minWidth:100,maxWidth:350},
        _idValue : null,
        _txtValue : null,
        _txt　: null,
        _val : null,
        storeUrl:null,
        height: null,
        width: null,
        fullPath: false,
        
        initComponent : function(){
        	Ext.apply(this, this.config);
            this.treeObj = new Ext.tree.Panel({
	            border : false,
	            height : this.height,
	            width : this.width,
	            autoScroll : true,
	            store : Ext.create('Ext.data.TreeStore', {
                    fields : ['id','text','leaf','urls','clazz'],
                    autoLoad : false,
                    proxy: {
	                    type: 'ajax',
	                    url : this.storeUrl//'js/tree.json'
	                },
	                root: {
	                    expanded: true,
	                    text : "所有部门"
	                }
	            })
	    	}),
                
	    	this.treeRenderId = Ext.id();
	        this.tpl = "<tpl><div id='"+this.treeRenderId+"'></div></tpl>";                
	        this.callParent(arguments);
	        this.on({
                'expand' : function(){
                    if(!this.treeObj.rendered && this.treeObj && !this.readOnly){
                        Ext.defer(function(){
                                this.treeObj.render(this.treeRenderId);
                        },300,this);
                    }
                }
	        });
	        this.treeObj.on('itemclick',function(view,rec){
	            if(rec){
            		if(this.fullPath){
            			this.setValue(this._txtValue = this.getParent(rec));
            		}else{
            			this.setValue(this._txtValue = rec.get('text'));
            		}
                    this._idValue = rec.get('id');
                    this.collapse();
	            }
	        },this);
	        
	        if(this._txt != null && this._val != null){
	        	this.setLocalValue(this._txt, this._val);
	        }
        },
        getValue : function(){//获取id值
            return this._idValue;
        },
        getTextValue : function(){//获取text值
            return this._txtValue;
        },
        setLocalValue : function(txt,id){//设默认值
            this._idValue = id;
            this.setValue(this._txtValue = txt);
        },
        getParent : function(node) {
        	if(node.parentNode == null){
        		return node.get('text');
        	}else{
        		return this.getParent(node.parentNode) + '->' + node.get('text');
        	}
        }
});