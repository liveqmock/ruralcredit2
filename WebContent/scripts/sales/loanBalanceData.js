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
	constructTable("initialization");
});

function constructTable(type) {
	// 初始化时为了避免渲染时差问题
	if (type == "initialization") {
		var areaDepartmentIds = null;
		if ($("#loanTab").tabs("exists", "业务质量报表")) {
			$("#loanTab").tabs("close", "业务质量报表");
		}
		$("#loanTab").tabs("add", {
			title : "业务质量报表",
			href : serverName + "/loanBalanceDataController/returnLoanBalanceDataTable.do?areaDepartmentIds=" + areaDepartmentIds,
			onLoad : function(r) {
			}
		});
	} else {// 搜索按钮执行重新组建table
		if ($("#searchForm").form("validate")) {

			var departmentNameValues = $("#areaDepartmentIds").combotree("getValues");
			var departmentNamestr = departmentNameValues.join(",");

			var areaDepartmentIds = departmentNamestr;

			if ($("#loanTab").tabs("exists", "业务质量报表")) {
				$("#loanTab").tabs("close", "业务质量报表");
			}
			$("#loanTab").tabs("add", {
				title : "业务质量报表",
				href : serverName + "/loanBalanceDataController/returnLoanBalanceDataTable.do?areaDepartmentIds=" + areaDepartmentIds,
				onLoad : function(r) {
				}
			});
		} else {
			$.messager.alert("提示", '请选择营业部',"warning");
		}
	}

}

function exportExcel() {
	$("#test").linkbutton("disable");
	$("#exportExcel").linkbutton("disable");
	ajaxSubmit(serverName + "/loanBalanceDataController/putDateInSession.do", $("#myForm").serialize(), function(r) {
		if (r) {
			window.location.href = serverName + "/loanBalanceDataController/exportExcel.do";
		}
		$("#test").linkbutton("enable");
		$("#exportExcel").linkbutton("enable");
	});
}
function test() {
	$("#test").linkbutton("disable");
	$("#exportExcel").linkbutton("disable");
	ajaxSubmit(serverName + "/salesController/synchronizationLoan.do", {}, function(r) {
		$.messager.alert("消息", r.msg, "info", function(s) {
			$("#test").linkbutton("enable");
			$("#exportExcel").linkbutton("enable");
			window.location.href = serverName + "/loanBalanceDataController/returnLoanBalanceDataJSP.do";
		});
	});
}
function returnPlan() {
	ajaxSubmit(serverName + "/returnPlanController/insertBatchReturnPlans.do", {}, function(r) {
		console.info(r);
	});
}