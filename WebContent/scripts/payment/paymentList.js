$(function() {
    var paymentLoansStatus = new DataDictionary();
	paymentLoansStatus.addDic("loansStatus", "paymentLoansStatus");
	paymentLoansStatus.dicAjax();
	$("#loanList")
			.datagrid(
					{
						url : serverName + "/creditgroup/selectFinanceLoan.do"+conditions,
						pagination : true,
						rownumbers : true,
						idfield : 'creditapplicationId',
						striped : true,
						singleSelect : true,
						sortName : 'contactNumber',
						sortOrder : 'desc',
						frozenColumns:[[{
							title : '操作',
							field : 'operateFlag',
							width : '230',
							formatter : optionFormatter 
						}]],
						columns : [ [ 
						{
							title : '业务单号',
							field : 'groupNumber',
							width : '150'
						}, {
							title : '借款人姓名',
							field : 'groupName',
							width : '90'
						}, 
		                {
							title : '所在分公司',
							field : 'companyName',
							width : '200'
						}, {
							title : '借款金额',
							field : 'groupAppTotal',
							width : '100'
						}, {
							title : '服务费',
							field : 'serviceCharge',
							width : '100'
						}, {
							title : '实发金额',
							field : 'realAmount',
							width : '100'
						}, {
							title : '放款日期',
							field : 'signagreementDate',
							width : '100'
						}, {
							title : '客户经理',
							field : 'loanOfficerName',
							width : '100'
						}, {
							title : '订单号',
							field : 'bizId',
							width : '100'
						}, {
							title : '财务状态',
							field : 'loansStatus',
							width : '100',
							formatter : function(value) {
								if (value == "0") {
									return "未预约";
								} else if (value == "1") {
									return "已预约";
								} else if (value == "2") {
									return "付款成功";
								} else if (value == "3") {
									return "付款失败";
								} else if (value == "4") {
									return "撤销";
								} else if (value == "5") {
									return "作废";
								}
							}
						}]]
					});
    /*更改：分公司名称下拉选择框为两级树形菜单
	$('#companyName').combobox({
		url : serverName + "/financeMoneyController/getDepartmentList.do",
		valueField : 'departmentName',
		textField : 'departmentName'
	});*/
    departmentComboboxTreeWithPanelWidth('companyName',false,250);
});
//高级查询
function searchAdvanced(param) {
	if (param == 0) {
		$('#loanList').datagrid('load', {
			fuzzyValue : $.trim($("#fuzzyValue").val())
		});
	} else if (param == 1) {
        var companyIds = $("#companyName").combotree("getValues").join(',');
		$("#loanList").datagrid("load", {
			loansStatus : $("#loansStatus").combobox("getValue"),
            companyId : companyIds,
			groupNumber : $("#groupNumber").val(),
			bizId : $("#bizId").val(),
			groupName : $("#groupName").val(),
			beginLoanDate : $("#beginLoanDate").datebox("getValue"),
			endLoanDate : $("#endLoanDate").datebox("getValue")
		});
	}
}
//清空条件
function searchCancel1(param) {
	if (param == 0) {
		$('#fuzzyValue').val("");
        searchAdvanced(0);
	} else if (param == 1) {
		$("#loansStatus").combobox("clear");
//		$("#companyName").combobox("clear");
        $("#companyName").combotree("setValues","");
		$("#groupNumber").val("");
		$("#groupName").val("");
		$("#bizId").val("");
		$("#beginLoanDate").datebox("clear");
		$("#endLoanDate").datebox("clear");
		$("#loanList").datagrid("load", {});
	}
}
//财务付款查看
function look() {
	var rows = $("#loanList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	var url1 = serverName+"/financeMoneyController/selectPaymentFinanceMoney.do?creditapplicationId="
			+ creditapplicationId;
	constructLookDialog(url1);
	$("#look").dialog("open");
}
//财务付款撤销
function undo() {
	var rows = $("#loanList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	var url = serverName+"/financeMoneyController/paymentUndo.do";
	ajaxSubmit(url, {
		creditapplicationId : creditapplicationId
	}, function(result) {
		if (result.success) {
			$.messager.alert('提示','撤销成功!','info');
			$("#loanList").datagrid("reload");
		} else {
			$.messager.alert('提示',result.msg,'warning');
			$("#loanList").datagrid("reload");
		}
	});
}
//转到财务付款预约界面
function payment() {
	var rows = $("#loanList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	var url1 = serverName+"/financeMoneyController/paymentFinance.do?creditapplicationId="
			+ creditapplicationId;
	constructPaymentDialog(url1);
	$("#payment").dialog("open");
}
//构造财务付款预约弹出框
function constructPaymentDialog(url1) {
	$("#payment").dialog(
			{
				title : '付款预约',
				cache : false,
				closed : true,
				modal : true,
				buttons:"#paymentButton",
				onOpen : function() {
					$("#payment").dialog('refresh', url1);
				}
			});
}
//构造财务查看弹出框
function constructLookDialog(url1) {
	$("#look").dialog({
		title : '付款预约',
		cache : false,
		closed : true,
		modal : true,
		buttons : [ {
			text : "关闭",
			handler : function() {
				$("#look").dialog("close");
			}
		} ],
		onOpen : function() {
			$("#look").dialog('refresh', url1);
		},onLoad:function(){
			addBackgourdColor();
		}
	});
}
//财务付款预约操作
function toPayment(){
	var loanConfirmTime=$("#loanConfirmTime").datetimebox("getValue");
	var loan=new Date(loanConfirmTime.replace(/-/g,"/"));
	if(''==loanConfirmTime){
		$.messager.alert('提示','请选择预约时间！','warning');
		return;
	}else{
		var severDate=$("#severDate").val();
		var strSeverDate=new Date(severDate.replace(/-/g,"/"));
		if(loan<strSeverDate){
			$.messager.alert('提示','当前时间不能小于系统时间，系统时间：'+severDate,'warning');
		}else{
			$("#toPayment").linkbutton('disable');
			$("#payment").dialog("close");
			url = serverName+"/financeMoneyController/toPaymentFinance.do";
			ajaxSubmit(url, $("#paymentAgin")
					.serializeObject(), function(result) {
				$("#toPayment").linkbutton("enable");
				if (result.success) {
					$.messager.alert('提示','成功','info');
					$("#loanList").datagrid("reload");
				} else {
					$.messager.alert('提示',result.msg,'warning');
					$("#loanList").datagrid("reload");
				}
			});
		}
	}
}
//方法无用 用于验证是否可做预约
function isPayment(){
	var rows = $("#loanList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	var url=serverName+"/financeMoneyController/isPayment.do";
	ajaxSubmit(url, {creditapplicationId:creditapplicationId}, function(result) {
		if (result.success) {
			payment();
		} else {
			$.messager.alert('提示','请勿重复预约','warning');
			$("#loanList").datagrid("reload");
		}
	});
}
function toClose(){
	$("#payment").dialog("close");
}
