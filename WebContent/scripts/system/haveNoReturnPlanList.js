$(function() {
	$("#receivablesList").datagrid({
		url : serverName + "/returnPlanController/haveNoReturnPlanList.do",
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
		}, {
			field : 'sysGuid',
			title : '销售系统编号',
			width : 100,
			hidden : false
		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				var paramId = rowData.creditapplicationId;
				var paramNumber = rowData.businessNumber;
				return "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='addReturnPlanTab(" + paramId + "," + "\"" + paramNumber + "\"" + ")'><font color='#9932cc'>查看还款计划</font></a>";
			}
		} ] ],
		onLoadSuccess : function(data) {
		}
	});
});
// 同步还款计划
function returnPlan() {
	$("#test").linkbutton("disable");
	ajaxSubmit(serverName + "/returnPlanController/insertBatchReturnPlans.do", {}, function(r) {
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
// 打开还款计划
function addReturnPlanTab(creditapplicationId, businessNumber) {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/returnPlanController/returnPlanJSP.do?creditapplicationId=" + creditapplicationId,
		title : businessNumber + "的还款计划"
	});
}