$(function() {
	$("#historyDialog").dialog({
		modal : true,
		title : "修改记录",
		maximizable : false,
		cache : false,
		closed : true,
		content : "<iframe scrolling='no' id='iframeTest' name='iframeTest' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
		buttons : [ {
			id : "button",
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#historyDialog").dialog("close");
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		},
		onOpen : function() {
		}
	});
});
function editAndSave(id) {
	var code = id.substr(1);
	if ($("#" + id).text() == "编辑") {
		$("#" + id).text("保存");
		$("input[id^=input" + code + "]").removeClass("myInputBoder");
		$("input[id^=input" + code + "]").removeAttr("readonly");
	} else if ($("#" + id).text() == "保存") {
		// 保存数据 ajaxSubmit
		var msgInfo = "";
		if ($("#role").val() == "1") {
			msgInfo = "保存后不可修改，确认保存预测信息吗？";
		} else {
			msgInfo = "确认保存预测信息吗？";
		}
		$.messager.confirm("提示", msgInfo, function(b) {
			if (b) {
				$("#" + id).removeAttr("onclick");
				ajaxSubmit(serverName + "/salesController/saveRollingForecast.do", {
					rollingForecastId : $("#input" + code + "rollingForecastId").val(),
					year : $("#input" + code + "year").val(),
					month : $("#input" + code + "month").val(),
					type : $("#input" + code + "type").val(),
					areaDepartmentId : $("#input" + code + "areaDepartmentId").val(),
					areaDepartmentName : $("#input" + code + "areaDepartmentName").val(),
					firstMonth : $("#input" + code + "editfirst").val(),
					secondMonth : $("#input" + code + "editsecond").val(),
					thirdMonth : $("#input" + code + "editthird").val(),
					operateTime : $("#input" + code + "operateTime").val(),
					operatorId : $("#input" + code + "operatorId").val(),
					operatorName : $("#input" + code + "operatorName").val(),
					historyFlag : $("#input" + code + "historyFlag").val()
				}, function(r) {
					$("#" + id).attr("onclick", "editAndSave(this.id)");
					if (r.success) {
						$.messager.show({
							showType : "show",
							timeout : 5000,
							title : "消息",
							msg : r.msg
						});
						$("#" + id).text("编辑");
						$("input[id^=input" + code + "]").addClass("myInputBoder");
						$("input[id^=input" + code + "]").attr("readonly", "readonly");
						// 角色代码role 1 代表营业部经理 只有一次编辑的权限
						if ($("#role").val() == "1") {
							$("#spanEdit" + code + "").html("");
						}
						// 如果节点没有下有查看修改记录按钮
						if ($("#spanShow" + code + "")[0].children[0] == undefined) {
							// 判断是不是第一次操作
							if ($("#input" + code + "rollingForecastId").val() != null && $("#input" + code + "rollingForecastId").val() != "") {
								// 如果不是是第一次操作
								// 则生成按钮
								$("#spanShow" + code + "").html("<a id='ahis" + code + "' href='javascript:void(0)' onclick='openDialog(this.id)'>查看修改记录</a>");
							}
						}
						$("#input" + code + "rollingForecastId").val(r.rollingForecastId);
					} else {
						$.messager.show({
							showType : "show",
							timeout : 5000,
							title : "消息",
							msg : r.msg
						});
					}
				});
			}
		});

	} else {
		alert("editAndSave方法报错！");
	}
}
// 查看历史记录
function showHistory() {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/salesController/rorecastHistoryJSP.do",
		title : "滚动预测历史查询"
	});
}
// 弹出Dialog
function openDialog(id) {
	var code = id.substr(4);
	var year = $("#input" + code + "year").val();
	var month = $("#input" + code + "month").val();
	var type = $("#input" + code + "type").val();
	var areaDepartmentId = $("#input" + code + "areaDepartmentId").val();
	$("#iframeTest").attr('src', serverName + "/salesController/rollingForecastEditHistoryJSP.do?year=" + year + "&month=" + month + "&type=" + type + "&areaDepartmentId=" + areaDepartmentId);
	$("#historyDialog").dialog('open');
}