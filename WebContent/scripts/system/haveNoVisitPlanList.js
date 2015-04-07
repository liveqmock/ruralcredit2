$(function() {
	$("#receivablesList").datagrid({
		url : serverName + "/CustomerReturnVisitController/haveNoVisitPlanList.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
		columns : [ [ {
			field : 'creditapplicationId',
			title : '信贷申请单ID',
			width : 100,
			hidden : false
		}, {
			field : 'businessNumber',
			title : '业务单号',
			width : 100,
			hidden : false
		} ] ]
	});
});
function visitPlan() {
	$("#test").linkbutton("disable");
	ajaxSubmit(serverName + "/CustomerReturnVisitController/bulkGenerateVisitPlan.do", {}, function(r) {
		$.messager.show({
			showType : "show",
			timeout : 5000,
			title : "消息",
			msg : r.msg
		});
		$("#test").linkbutton("enable");
		$("#receivablesList").datagrid("reload");
	});
}
function creatUrgeHistory() {
	$("#testtest").linkbutton("disable");
	ajaxSubmit(serverName + "/urgeController/creatUrgeHistory.do", {}, function(r) {
		$.messager.show({
			showType : "show",
			timeout : 5000,
			title : "消息",
			msg : r.msg
		});
		$("#testtest").linkbutton("enable");
	});
}