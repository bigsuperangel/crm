(function() {
    function getQueryParam(name, queryString) {
        var match = RegExp(name + '=([^&]*)').exec(queryString || location.search);
        return match && decodeURIComponent(match[1]);
    }

    function hasOption(opt) {
        var s = window.location.search;
        var re = new RegExp('(?:^|[&?])' + opt + '(?:[=]([^&]*))?(?:$|[&])', 'i');
        var m = re.exec(s);

        return m ? (m[1] === undefined ? true : m[1]) : false;
    }

    var scriptTags = document.getElementsByTagName('script'),
        defaultTheme = 'darkGreen',
        defaultRtl = false,
        i = scriptTags.length,
        requires = [
            'Ext.toolbar.Toolbar',
            'Ext.form.field.ComboBox',
            'Ext.form.FieldContainer',
            'Ext.form.field.Radio'

        ],
        defaultQueryString, src, theme='darkGreen', rtl;

    Ext.themeName = theme = getQueryParam('theme') || defaultTheme;
    
    rtl = getQueryParam('rtl') || defaultRtl;

    if (rtl.toString() === 'true') {
        requires.push('Ext.rtl.*');
        Ext.define('Ext.GlobalRtlComponent', {
            override: 'Ext.AbstractComponent',
            rtl: true
        });
    }

    Ext.require(requires);

    Ext.onReady(function() {
        Ext.getBody().addCls(Ext.baseCSSPrefix + 'theme-' + Ext.themeName);

        if (Ext.isIE6 && theme === 'neptune') {
            Ext.Msg.show({
                title: 'Browser Not Supported',
                msg: 'The Neptune theme is not supported in IE6.',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.WARNING
            });
        }
        
        if (hasOption('nocss3')) {
            Ext.supports.CSS3BorderRadius = false;
            Ext.getBody().addCls('x-nbr x-nlg');
        }
        function setParam(param) {
            var queryString = Ext.Object.toQueryString(
                Ext.apply(Ext.Object.fromQueryString(location.search), param)
            );
            location.search = queryString;
        }

        function removeParam(paramName) {
            var params = Ext.Object.fromQueryString(location.search);

            delete params[paramName];

            location.search = Ext.Object.toQueryString(params);
        }
	
        var toolbar;
		//html 读取cookies
        theme = localStorage.getItem('myTheme');
		console.log('get cookie: '+theme);
		if(theme == null || theme == 'undefined' || theme == ''){
			theme = 'darkGreen';
		}else{
			Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/'+theme+'/css/ext-all-'+theme+'.css'); 
		}

        setTimeout(function() {
            toolbar = Ext.widget({
                xtype: 'toolbar',
                border: true,
                rtl: false,
                id: 'options-toolbar',
                floating: true,
                fixed: true,
                
                items: [{
                    xtype: 'combo',
                    rtl: false,
                    width: 170,
                    labelWidth: 45,
                    fieldLabel: 'Theme',
                    displayField: 'name',
                    valueField: 'value',
					editable:false,
                    labelStyle: 'cursor:move;',
                    margin: '0 5 0 0',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['value', 'name'],
                        data : [

							{value:"blue", name:"blue"},
							{value:"darkGreen", name:"darkGreen"},
							{value:"green", name:"green"},
							{value:"pink", name:"pink"},
							{value:"purple", name:"purple"},
							{value:"yellow", name:"yellow"}
							
                        ]
                    }),
                    value: theme,
                    listeners: {
                        select: function(combo) {
                            var theme = combo.getValue();
							Ext.util.CSS.swapStyleSheet('them', '../ext4.1/resources/'+theme+'/css/ext-all-'+theme+'.css'); 
														
							localStorage.setItem('myTheme', theme);
							console.log('set cookie: '+theme);

                            //if (theme !== defaultTheme) {
                              //  setParam({ theme: theme });
//                            } else {
  //                              removeParam('theme');
    //                        }
                        }
                    }
                }, {
                    xtype: 'button',
                    rtl: false,
                    hidden: !(Ext.repoDevMode || location.href.indexOf('qa.sencha.com') !== -1),
                    enableToggle: true,
                    pressed: rtl,
                    text: 'RTL',
                    margin: '0 5 0 0',
                    listeners: {
                        toggle: function(btn, pressed) {
                            if (pressed) {
                                setParam({ rtl: true });
                            } else {
                                removeParam('rtl');
                            }
                        }
                    }
                }, {
                    xtype: 'tool',
                    type: 'close',
                    rtl: false,
                    handler: function() {
                        toolbar.destroy();
                    }
                }],

                // Extra constraint margins within default constrain region of parentNode
                constraintInsets: '0 -' + (Ext.getScrollbarSize().width + 4) + ' 0 0'
            });
            toolbar.show();
            toolbar.alignTo(
                document.body,
                Ext.optionsToolbarAlign || 'tr-tr',
                [
                    (Ext.getScrollbarSize().width - 20),
				     -(document.body.scrollTop || document.documentElement.scrollTop)
                ]
            );
            
            var constrainer = function() {
                toolbar.doConstrain();
            };
            
            Ext.EventManager.onWindowResize(constrainer);
            toolbar.on('destroy', function() { 
                Ext.EventManager.removeResizeListener(constrainer);
            });
        }, 100);

    });
})();