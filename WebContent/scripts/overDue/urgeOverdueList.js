$(function() {
 //dialog 初始化
	$("#urgeDialog").dialog({
		modal : true,
		title : "新增催收",
		maximizable : false,
		cache : false,
		closed : true,
		buttons : [ {
			id : "rdButton1",
			text : "保存",
			iconCls : "icon-ok",
			handler : function() {
				//在urge.js中调添加保存方法
				myIframe.window.save();
			}
		}, {
			id : "rdButton2",
			text : "清空",
			iconCls : "icon-cancel",
			handler : function() {
				//在urge.js中清空所有文本
				myIframe.window.clearText();
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		}
	});
});

//在逾期列表中   打开催收列表dialog
function openDialog(creditapplicationId,urgeAdd) {
	$("#iframeTest").attr('src', serverName + "/urgeController/returnUrgeJSP.do?creditapplicationId=" + creditapplicationId+"&urgeAdd="+urgeAdd);
	$("#urgeDialog").dialog('open');
}

//在逾期列表中   打开短信逾期对话框
function openOverDueRemindDialog(creditapplicationId) {
	$("#overDueRemindMegFrame").attr('src', serverName + "/urgeController/returnOverDueMsgRemindJSP.do?creditapplicationId=" + creditapplicationId);
	$("#overDueRemindMegDiv").dialog({
		title:"逾期短信提醒",
		width:410,
		height:320,
		buttons : [ {
			id: "addSave",
			text : "发送短信",

			iconCls : "icon-save",
			handler : function() {
				var overDueRemindMegFrame = window.frames["overDueRemindMegFrame"];
				if (overDueRemindMegFrame.window) {
					overDueRemindMegFrame.window.sendOverDueRemindMsg();
				} else {
					overDueRemindMegFrame.contentWindow.sendOverDueRemindMsg();
				}
			}
		  } , {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#overDueRemindMegDiv").dialog('close');
			}
		}
		]  ,
		onLoad : function() {
			console.info("------init-------------");
		}
	});
	$("#overDueRemindMegDiv").dialog('open');
}