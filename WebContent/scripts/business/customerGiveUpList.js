$(function() {
	// 添加搜索框中分公司下拉框
	departmentComboboxTree("companyId", false);
	// 客户放弃原因下拉框
	var dicReport2 = new DataDictionary();
	dicReport2.addDic("customerQiutReason", "customerQiutReason");
	dicReport2.dicAjax();

	// special列表
	$("#customerGiveUpList").datagrid({
		url : serverName + "/RefuseReasonController/customerGiveUpList.do",
		pagination : true,
		idfield : 'refuseReasonId',
		striped : true,
		fitColumns : false,
		singleSelect : true,
		sortName : 'operateDate',
		sortOrder : 'desc',
		rownumbers : true,
		columns : [ [ {
			title : '业务单号',
			field : 'businessNumber',
			width : '150'
		}, {
			title : '借款人姓名',
			field : 'name',
			width : '100'
		}, {
			title : '业务状态',
			field : 'auditStatus',
			hidden:true,
			width : '100'
		}, {
			title : '业务状态',
			field : 'auditStatusShow',
			width : '100',
			hidden:true
		}, {
			title : 'creditapplicationId',
			field : 'creditapplicationId',
			hidden : true,
			width : '100'
		}, {
			title : '分公司名称',
			field : 'companyName',
			width : '180'
		}, {
			title : '操作人',
			field : 'operatorName',
			width : '100'
		},{
			title : '操作时间',      
			field : 'operateDate',
			width : '100'
		},{
			title : '客户放弃时业务状态',
			field : 'state',
			width : '100',
			hidden:true
		}, {
			title : '回滚状态',
			field : 'specialApplyTypeShow',
			width : '100',
			hidden:true
		}, {
			title : '客户放弃原因',
			field : 'refuseReasons',
			width : '150'
		},{ title : '风险经理id', 
			field : 'fxid', 
			width : '100', 
			hidden : true
		}] ],
		frozenColumns : [ [ {
			title : '操作',
			field : 'operateFlag',
			width : '130',
			formatter : returnPalnView
		} ] ]
	});
});

// 搜索按钮
function searchAdvanced() {
	var data = new Object();
	var businessNumber = $("#businessNumber").val();
	var name = $("#name").val();
	var departmentNameValues = $("#companyId").combotree("getValues");
	var departmentNamestr = departmentNameValues.join(",");
	var customerQiutReason = $("#customerQiutReason").combobox("getValue");
	var map = {
		businessNumber : businessNumber,
		name : name,
		companyId : departmentNamestr,
		customerQiutReason : customerQiutReason
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#customerGiveUpList').datagrid('load', data);
}

// 清空按钮
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#companyId").combotree("setValues", "");
	$("#customerQiutReason").combobox("setValue", "");
}

//查看详细
function searchLook() {
	var row = $("#customerGiveUpList").datagrid("getSelected");
	var centerj = window.parent;
	centerj.addTabFun({
				src : serverName
						+ "/creditgroup/searchLook.do?creditApplicationId="
						+ row.creditapplicationId + "&creditInvestigatioId="
						+ row.fxid,
				title : row.businessNumber + "申请单查看"
			});
}

// 关闭dialog
function closeMyDialog() {
	$("#specialApply").dialog('close');
}
// 提示消息
function messageSuccess(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}
function messageFail(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}
// 禁用确定按钮
function buttonSpecialdisDisable() {
	$("#rdButton1").linkbutton("disable");
	$("#rdButton2").linkbutton("disable");
}

function buttonSpecialdisEnable() {
	$("#rdButton1").linkbutton("enable");
	$("#rdButton2").linkbutton("enable");
}
