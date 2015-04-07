$(function() {
	$("#forecsatTimeBegin").datebox({
		formatter : function(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			return year + "年" + month + "月";
		},
		onSelect : function(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			$("#forecsatTimeBeginHidden").val(year + "年" + month + "月");
		}
	});
	$("#forecsatTimeEnd").datebox({
		formatter : function(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			return year + "年" + month + "月";
		},
		onSelect : function(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			$("#forecsatTimeEndHidden").val(year + "年" + month + "月");
		}
	});
// $("#areaDepartmentIds").combobox({
// valueField : "code",
// textField : "value",
// width : 400,
// multiple : true,
// editable : false,
// url : serverName + "/salesController/getComboAreaDepartments.do",
// onSelect : function(record) {
// var getValuesArray = $("#areaDepartmentIds").combobox("getValues");
// if (getValuesArray.length >= 2 && getValuesArray[0] == "") {
// getValuesArray.shift();
// $("#areaDepartmentIds").combobox("setValues", getValuesArray);
// }
// if (record.code == "") {
// $("#areaDepartmentIds").combobox("setValue", "");
// }
// },
// onUnselect : function(record) {
// var getValuesArray = $("#areaDepartmentIds").combobox("getValues");
// if (getValuesArray == null || getValuesArray == "") {
// $("#areaDepartmentIds").combobox("setValue", "");
// }
//
// }
// });
	departmentComboboxTree("areaDepartmentIds", true);
	$("#detailDialog").dialog({
		modal : true,
		title : "历史详情",
		maximizable : false,
		cache : false,
		closed : true,
		content : "<iframe scrolling='no' id='iframeTest' name='iframeTest' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
		buttons : [ {
			id : "button",
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#detailDialog").dialog("close");
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		},
		onOpen : function() {
		}
	});
});
function constructRorecastHistoryTable() {
	if ($("#searchForm").form("validate")) {
		var forecsatTimeBegin = $("#forecsatTimeBegin").datebox("getValue");
		var forecsatTimeEnd = $("#forecsatTimeEnd").datebox("getValue");

		var areaDepartmentIds = $("#areaDepartmentIds").combobox("getValues");
		if ($("#rorecastHistoryTabs").tabs("exists", "历史记录")) {
			$("#rorecastHistoryTabs").tabs("close", "历史记录");
		}
		var departmentNameValues = $("#areaDepartmentIds").combotree("getValues");
		var departmentNamestr = departmentNameValues.join(",");
		var areaDepartmentIds = departmentNamestr;
		$("#areaDepartmentIdsInput").val(areaDepartmentIds);
		$("#rorecastHistoryTabs").tabs(
				"add",
				{
					title : "历史记录",
					href : serverName + "/salesController/constructRorecastHistoryTable.do?forecsatTimeBegin=" + forecsatTimeBegin + "&forecsatTimeEnd=" + forecsatTimeEnd + "&areaDepartmentIds="
							+ areaDepartmentIds,
					onLoad : function(r) {

					}
				});
	} else {
		$.messager.alert("提示", '请选择营业部', "warning");
	}
}
function showDetail(id) {

	var code = id.substr(2);
	var year = $("#input" + code + "year").val();
	var month = $("#input" + code + "month").val();
	var type = $("#input" + code + "type").val();
	var areaDepartmentIds = $("#areaDepartmentIdsInput").val();
	if (areaDepartmentIds == null) {
		areaDepartmentIds = "";
	}
	$("#iframeTest").attr('src', serverName + "/salesController/rorecastHistoryDetailJSP.do?year=" + year + "&month=" + month + "&type=" + type + "&areaDepartmentIds=" + areaDepartmentIds);
	$("#detailDialog").dialog('open');
}
function exportExcel() {
	var forecsatTimeBegin = $("#forecsatTimeBeginHidden").val();
	var forecsatTimeEnd = $("#forecsatTimeEndHidden").val();
	var areaDepartmentIds = $("#areaDepartmentIdsInput").val();
	if (areaDepartmentIds == null) {
		areaDepartmentIds = "";
	}
	window.location.href = serverName + "/salesController/exportExcel.do?forecsatTimeBegin=" + forecsatTimeBegin + "&forecsatTimeEnd=" + forecsatTimeEnd + "&areaDepartmentIds=" + areaDepartmentIds;
}
function exportExcelDetail(id) {
	var code = id.substr(3);
	var year = $("#input" + code + "year").val();
	var month = $("#input" + code + "month").val();
	var areaDepartmentIds = $("#areaDepartmentIdsInput").val();
	if (areaDepartmentIds == null) {
		areaDepartmentIds = "";
	}
	window.location.href = serverName + "/salesController/exportExcelDetail.do?year=" + year + "&month=" + month + "&areaDepartmentIds=" + areaDepartmentIds;
}