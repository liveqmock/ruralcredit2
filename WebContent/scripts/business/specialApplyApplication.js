/*$(function() {
 //dialog 初始化
	$("#specialApplyDialog").dialog({
		modal : true,
		title : "特殊情况申请",
		maximizable : false,
		cache : false,
		closed : true,
		buttons : [ {
			id : "rdButton1",
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				//在specialApply.js中调添加保存方法
				myIframe.window.save();
			}
		}, {
			id : "rdButton2",
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$("#specialApplyDialog").dialog('close');
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		}
	});
});*/
//在信贷申请中   打开特殊申请dialog
function openDialog(creditapplicationId,add) {
	var getRow = $("#creditApplicationList").datagrid("getRows");
	var one=getRow.auditStatusShow;
	var yewu=getRow.auditStatus;
	//调用 判断添加特殊申请的时候     特殊申请状态是否为：待审批
	/*ajaxSubmit(serverName + "/specialApplyController/getSpecialApplyAuditState.do", {creditapplicationId:creditapplicationId}, function(b) {
	if(b!=0){
	*//*$("#iframeTest").attr('src', serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + creditapplicationId+"&add="+add);
	$("#specialApplyDialog").dialog('open');*//*
		window.open(serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + creditapplicationId+"&add="+add,"特殊情况申请");
	}else{
		$.messager.alert("消息", "特殊申请状态为待审批！现不能添加。");
	}
	});*/

	var url = serverName + '/specialApplyController/getSpecialApplyAuditState.do';
	var xxx;
	$.ajax({
		type: 'POST',
		url: url,
		data: {creditapplicationId: creditapplicationId},
		success: function (r) {
			if (r != 0) {
				xxx = r;
			} else {
				$.messager.alert("消息", "特殊申请状态为待审批！现不能添加。");
			}
		},
		dataType: 'json',
		async: false
	});
	if (xxx && xxx == 1) {
		window.open(serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + creditapplicationId + "&add=" + add, "特殊情况申请");
	}
}
//禁用确定按钮
function buttonSpecialdisDisable() {
	$("#rdButton1").linkbutton("disable");
	$("#rdButton2").linkbutton("disable");
}

function buttonSpecialdisEnable() {
	$("#rdButton1").linkbutton("enable");
	$("#rdButton2").linkbutton("enable");
}


function messagerShow(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}


