$(function() {
	// 添加搜索框中分公司下拉框
	departmentComboboxTree("companyId", false);
	// 申请状态下拉框
	var dicReport2 = new DataDictionary();
	dicReport2.addDic("auditAccountApplyState", "auditAccountApplyState");
	dicReport2.dicAjax();
	//dialog 初始化
		$("#newApplyDialog").dialog({
			modal : true,
			title : "新增收款申请",
			maximizable : false,
			cache : false,
			closed : true,
			buttons : [ {
				id : "rdButton1",
				text : "保存",
				iconCls : "icon-ok",
				handler : function() {
					//在urge.js中调添加保存方法
					myIframe.window.saveAuditAccountApply();
				}
			}],
			onClose : function() {
				$("#iframeTest").attr('src', "");
			}
		});
		//要申请的数据
		$("#applyAuditDateGriding").datagrid({
			url : serverName + "/balanceAccountApplyController/accountApplyDateGrid.do",
			method : 'post',
			loadMsg : "数据装载中....",
			fitColumns : true,
			nowrap : true,
			striped : true,
			singleSelect : true,
			columns : [[{
				field : 'creditapplicationId',
				title : '信贷申请主键',
				width : 100,
				hidden : true
			}, {
				field : 'businessNumber',
				title : '业务单号',
				width : 100,
				hidden : true
			}, {
				field : 'name',
				title : '客户姓名',
				width : 100,
				hidden : false
			},{
				field : 'credentialsNumber',
				title : '身份证号',
				width : 125,
				hidden : false
			}]],
			frozenColumns : [[{
				title : '操作',
				field : 'operateFlag',
				width : '170',
				formatter : operationApply
			}]]
		});
		//申请历史数据
		$("#historyDateGrid").datagrid({
			url : serverName + "/balanceAccountApplyController/accountApplyHistoryDateGrid.do",
			method : 'post',
			loadMsg : "数据装载中....",
			fitColumns : true,
			nowrap : true,
			striped : true,
			singleSelect : true,
			pagination : true,
			pageSize : 10,
			pageList : [10, 20, 30, 40, 50],
			rownumbers : true,
			columns : [[{
				field : 'balanceAccountApplyId',
				title : '对账申请表主键',
				width : 100,
				hidden : true
			}, {
				field : 'creditapplicationId',
				title : '信贷申请主键',
				width : 100,
				hidden : true
			}, {
				field : 'businessNumber',
				title : '业务单号',
				width : 125,
				hidden : false
			}, {
				field : 'remitMoneyName',
				title : '汇款人姓名',
				width : 125,
				hidden : true
			}, {
				field : 'name',
				title : '借款人姓名',
				width : 100,
				hidden : false
			},{
				field : 'credentialsNumber',
				title : '身份证号',
				width : 125,
				hidden : false
			}, {
				field : 'saveMoney',
				title : '存款金额',
				width : 125,
				hidden : false
			}, {
				field : 'auditResultShow',
				title : '审批结果',
				width : 125,
				hidden : false
			}, {
				field : 'auditResult',
				title : '审批结果Code',
				width : 125,
				hidden : true
			}, {
				field : 'balanceDay',
				title : '记录上传资料的日期的天',
				width : 125,
				hidden : true
			},{
				field : 'applyName',
				title : '客户经理',
				width : 125,
				hidden : false
			}, {
				field : 'applyTime',
				title : '申请时间',
				width : 125,
				hidden : false
			}, {
				field : 'earlyRepaymentShow',
				title : ' 是否是提前还款',
				width : 125,
				hidden : false
			}]],
			frozenColumns : [[{
				title : '操作',
				field : 'operateFlag',
				width : '170',
				formatter : operationLookView
			}]]
		});
});
//根据筛选条件搜索按钮
function searchAdvanced() {
	var data = new Object();
	var businessNumber = $.trim($("#businessNumber").val());
	var name = $.trim($("#name").val());
	var loanOfficerName = $.trim($("#loanOfficerName").val());
	var departmentNameValues = $("#companyId").combotree("getValues");
	var departmentNamestr = departmentNameValues.join(",");
	var auditAccountApplyState = $("#auditAccountApplyState").combobox("getValue");
	var map = {
		businessNumber : businessNumber,
		name : name,
		loanOfficerName : loanOfficerName,
		companyId : departmentNamestr,
		auditAccountApplyState : auditAccountApplyState
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#historyDateGrid').datagrid('load', data);
}
//根据筛选条件搜索按钮
function searchAdvanced2() {
	var data = new Object();
	var map = {
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#historyDateGrid').datagrid('load', data);
}
//保存后刷新列表条件搜索按钮
function searchAdvanced3() {
	$("#businessNumber").val("");
	var data = new Object();
	var map = {
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#applyAuditDateGriding').datagrid('load', data);
}
//清空按钮
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#loanOfficerName").val("");
	$("#companyId").combotree("setValues", "");
	$("#auditAccountApplyState").combobox("setValue", "");
}
//点击根据正确的业务单号搜索按钮
function searchFussy(){
	var data = new Object();
	var businessNumber = $.trim($("#businessNumber").val());
	
	var map = {
		businessNumber : businessNumber
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#applyAuditDateGriding').datagrid('load', data);
}
//清空业务单号的文本框
function searchCancelBus(){
	$("#businessNumber").val("");
}
//在申请收款列表中   打开新增申请收款dialog
function openDialog(creditapplicationId) {
	$("#iframeTest").attr('src', serverName + "/balanceAccountApplyController/returnAccountApplyJSP.do?creditapplicationId=" + creditapplicationId+"&add="+0);
	$("#newApplyDialog").dialog('open');
}
//点击查看按钮
function openSearchDialog(creditapplicationId,balanceAccountApplyId,balanceDay){
	$("#iframeTest2").attr('src', serverName + "/balanceAccountApplyController/returnAccountApplyJSP.do?creditapplicationId="+creditapplicationId+"&balanceAccountApplyId=" +balanceAccountApplyId+"&balanceDay="+balanceDay+"&add="+1);
	$("#accountApplyView").dialog({
		modal : true,
		title : "现金收款",
		maximizable : false,
		cache : false,
		closed : false,
		onClose : function() {
			$("#iframeTest2").attr('src', "");
		}
	});
}
//点击审批按钮
function openAuditDialog(creditapplicationId,balanceAccountApplyId,balanceDay){
	$("#iframeTest3").attr('src', serverName + "/balanceAccountApplyController/returnAccountApplyJSP.do?creditapplicationId="+creditapplicationId+"&balanceAccountApplyId=" +balanceAccountApplyId+"&balanceDay="+balanceDay+"&add="+2);
	$("#accountApplyAudit").dialog({
		modal : true,
		title : "现金收款",
		maximizable : false,
		cache : false,
		closed : false,
		buttons : [ {
			id : "rdButton1",
			text : "通过",
			iconCls : "icon-ok",
			handler : function() {
				// 在accountApply.js中调审批方法保存方法
				var auditState = 1;
				myIframe3.window.auditAccout(auditState);
			}
		}, {
			id : "rdButton2",
			text : "拒绝",
			iconCls : "icon-cancel",
			handler : function() {
				var auditState = 2;
				myIframe3.window.auditAccout(auditState);
			}
		} ],
		onClose : function() {
			$("#iframeTest3").attr('src', "");
		}
	});
}
//关闭NewApplydialog
function closeMyDialogNewApplyDialog() {
	$("#newApplyDialog").dialog('close');
}
//关闭accountApplyAuditdialog
function closeMyDialogAccountApplyAuditDialog() {
	$("#accountApplyAudit").dialog('close');
}
//禁用审批按钮
function buttonAccountdisDisable() {
	$("#rdButton1").linkbutton("disable");
	$("#rdButton2").linkbutton("disable");
}
//启用审批按钮
function buttonAccountdisEnable() {
	$("#rdButton1").linkbutton("enable");
	$("#rdButton2").linkbutton("enable");
}
//提示消息
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