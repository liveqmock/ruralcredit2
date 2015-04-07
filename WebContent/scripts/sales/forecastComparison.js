$(function() {
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
	constructSalesPlanningTable("initialization");
});

function constructSalesPlanningTable(type) {
	// 初始化时为了避免渲染时差问题
	if (type == "initialization") {
		var areaDepartmentIds = null;
		if ($("#salesInfo").tabs("exists", "业绩达成报表")) {
			$("#salesInfo").tabs("close", "业绩达成报表");
		}
		$("#salesInfo").tabs("add", {
			title : "业绩达成报表",
			href : serverName + "/salesController/showComparisonTable.do?areaDepartmentIds=" + areaDepartmentIds,
			onLoad : function(r) {
			}
		});
	} else {// 搜索按钮执行重新组建table
		if ($("#searchForm").form("validate")) {
			var departmentNameValues = $("#areaDepartmentIds").combotree("getValues");
			var departmentNamestr = departmentNameValues.join(",");
			var areaDepartmentIds = departmentNamestr;
			if ($("#salesInfo").tabs("exists", "业绩达成报表")) {
				$("#salesInfo").tabs("close", "业绩达成报表");
			}
			$("#salesInfo").tabs("add", {
				title : "业绩达成报表",
				href : serverName + "/salesController/showComparisonTable.do?areaDepartmentIds=" + areaDepartmentIds,
				onLoad : function(r) {
				}
			});
		} else {
			$.messager.alert("提示", '请选择营业部', "warning");
		}
	}

}