Ext.define('Ext.ux.MyButton', {
	extend : 'Ext.button.Button',
	alias : 'widget.mybutton',

	constructor : function(config) {
		var myText = '<span style="' + config.cls + '">' + config.text
				+ '</span>';

		Ext.apply(config, {
					text : myText
				});
		Ext.apply(this, config);
		this.callParent(config);
	}
});

/**
 * Extjs消息提示框
 * MsgTip.msg('消息标题', '消息内容');//不自动隐藏
 */
MsgTip = function() {
	var msgCt;
	function createBox(t, s) {
		return [
				'<div class="msg">',
				'<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
				'<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc" style="font-size=12px;"><div style="font-size:14px;font-weight:600">',
				t,
				'</div>',
				s,
				'</div></div></div>',
				'<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
				'</div>'].join('');
	}
	return {
		msg : function(title, message, autoHide, pauseTime) {
			if (!msgCt) {
				msgCt = Ext.DomHelper.insertFirst(document.body, {
					id : 'msg-div22',
					style : 'position:absolute;top:10px;width:300px;margin:0 auto;z-index:20000;'
				}, true);
			}
			msgCt.alignTo(document, 't-t');
			//给消息框右下角增加一个关闭按钮
			//message += '<div align="right" style="font-size:12px; width:100%;">'
			//		+ '<u style="cursor:hand;" onclick="MsgTip.hide(this);">关闭</u></div>'
			var m = Ext.DomHelper.append(msgCt, {
						html : createBox(title, message)
					}, true);
			m.slideIn('t');
			//if (!Ext.isEmpty(pauseTime) && autoHide == true) {
				if (Ext.isEmpty(pauseTime)) {
					pauseTime = 3;
				}
				m.pause(pauseTime * 1000).ghost("tr", {
							remove : true
						});
			//}
		},
		hide : function(v) {
			var msg = Ext.get(v.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement);
			msg.ghost("tr", {
						remove : true
					});
		}
	};
}();