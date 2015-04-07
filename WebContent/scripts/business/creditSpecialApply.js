$(function() {
	// 添加搜索框中分公司下拉框
	departmentComboboxTree("companyId", false);
	// 申请状态下拉框
	var dicReport2 = new DataDictionary();
	dicReport2.addDic("auditSpecialApplyState", "auditSpecialApplyState");
	dicReport2.dicAjax();

	// special列表
	$("#specialApplyList").datagrid({
		url : serverName + "/specialApplyController/querySpecialApplyList.do",
		pagination : true,
		idfield : 'specialApplyId',
		striped : true,
		fitColumns : false,
		singleSelect : true,
		sortName : 'applyTime',
		sortOrder : 'desc',
		rownumbers : true,
		columns : [ [ {
			title : '业务单号',
			field : 'businessNumber',
			width : '100'
		}, {
			title : '借款人姓名',
			field : 'name',
			width : '100'
		}, {
			title : 'creditapplicationId',
			field : 'creditapplicationId',
			hidden : true,
			width : '100'
		}, {
			title : '分公司名称',
			field : 'companyName',
			width : '100'
		}, {
			title : '特殊情况审批状态Code',
			field : 'auditSpecialApplyState',
			width : '100',
			hidden : true
		}, {
			title : '特殊情况审批状态',
			field : 'auditSpecialApplyStateShow',
			width : '100'
		}, {
			title : '申请类别code',
			field : 'specialApplyType',
			width : '100',
			hidden : true
		}, {
			title : '申请类别',
			field : 'specialApplyTypeShow',
			width : '100'
		}, {
			title : '申请时状态code',
			field : 'specialApplyState',
			width : '100',
			hidden : true
		}, {
			title : '申请时状态',
			field : 'specialApplyStateShow',
			width : '100'
		}, {
			title : '详细内容',
			field : 'specialApplyViewContent',
			hidden : true,
			width : '100'
		}, {
			title : '业务状态',
			field : 'auditStatus',
			hidden : true,
			width : '100'
		}, {
			title : '业务状态',
			field : 'auditStatusShow',
			width : '100'
		}, {
			title : '申请人',
			field : 'proposerName',
			width : '100'
		}, {
			title : '申请时间',
			field : 'applyTime',
			width : '100'
		}, {
			title : '审批人',
			field : 'auditorName',
			width : '100'
		}, {
			title : '审批时间',
			field : 'auditTime',
			width : '100'
		} ] ],
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
	var loanOfficerName = $("#loanOfficerName").val();
	// var companyId = $("#companyId").combobox("getValue");
	var departmentNameValues = $("#companyId").combotree("getValues");
// if (departmentNameValues != null && departmentNameValues != "") {
// for ( var i in departmentNameValues) {
// departmentNameValues[i] = "'" + departmentNameValues[i] + "'";
// }
// }
	var departmentNamestr = departmentNameValues.join(",");
	var auditSpecialApplyState = $("#auditSpecialApplyState").combobox("getValue");

	var map = {
		businessNumber : businessNumber,
		name : name,
		loanOfficerName : loanOfficerName,
		companyId : departmentNamestr,
		auditSpecialApplyState : auditSpecialApplyState
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#specialApplyList').datagrid('reload', data);
}

// 清空按钮
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#loanOfficerName").val("");
	$("#companyId").combotree("setValues", "");
	$("#auditSpecialApplyState").combobox("setValue", "");
}

// 打开dialog查看
function openDialog(paramId, specialApplyId) {
	window.open(serverName +"/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + paramId + "&specialApplyId=" + specialApplyId,"特殊情况申请");
	/*$("#iframeTest2").attr('src', serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + paramId + "&specialApplyId=" + specialApplyId);
	$("#specialApplyView").dialog({
		modal : true,
		title : "特殊情况申请",
		maximizable : false,
		cache : false,
		closed : false,
		onClose : function() {
			$("#iframeTest").attr('src', "");
		}
	});*/
}
// 打开dialog审批
function openDialogAudit(paramId, specialApplyId) {
	window.open(serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + paramId + "&specialApplyId=" + specialApplyId + "&audit=1","特殊情况申请");
	// var getRow =
	// $("#specialApplyList").datagrid("getRows")[creditapplicationId];
	// specialApplyId = getRow.specialApplyId; // 特殊申请ID
	/*$("#iframeTest").attr('src', serverName + "/specialApplyController/returnSpecialApplyJsp.do?creditapplicationId=" + paramId + "&specialApplyId=" + specialApplyId + "&audit=1");
	$("#specialApply").dialog({
		modal : true,
		title : "特殊情况申请",
		maximizable : false,
		cache : false,
		closed : false,
		buttons : [ {
			id : "rdButton1",
			text : "通过",
			iconCls : "icon-ok",
			handler : function() {
				// 在specialApply.js中调审批方法保存方法
				var auditState = 1;
				myIframe.window.audit(auditState);
			}
		}, {
			id : "rdButton2",
			text : "拒绝",
			iconCls : "icon-cancel",
			handler : function() {
				var auditState = 2;
				myIframe.window.audit(auditState);
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		}
	});*/
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
