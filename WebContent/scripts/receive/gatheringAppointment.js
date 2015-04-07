$(function() {
	rowNum = 0;
	var combobox = new DataDictionary();
	combobox.addDic("loansStatus", "loansStatus");
	combobox.addDic("receivedType", "receivedType");
	combobox.addDic("capitalSource", "capitalSource");
	combobox.addDic("businessType", "businessType");
	combobox.dicAjax();
	$("#loanList").datagrid({
		url : serverName + "/ruralBusyController/selectRecevieList.do" + conditions,
		pagination : true,
		rownumbers : true,
		idfield : 'creditapplicationId',
		striped : true,
		singleSelect : false,
		sortName : 'contactNumber',
		sortOrder : 'desc',
		columns : [ [ {
			title : '业务单号',
			field : 'groupNumber',
			width : '150'
		}, {
			title : '借款人姓名',
			field : 'groupName',
			width : '90'
		}, {
			title : '借款金额',
			field : 'groupAppTotal',
			width : '100'
		}, {
			title : '放款日期',
			field : 'signagreementDate',
			width : '100'
		}, {
			title : '还款登记日期',
			field : 'operateDate',
			width : '100'
		}, {
			title : '收款金额',
			field : 'realAmount',
			width : '100'
		}, {
			title : '客户经理',
			field : 'loanOfficerName',
			width : '100'
		}, {
			title : '财务类型',
			field : 'receivedType',
			width : '100'
		}, {
			title : '订单号',
			field : 'bizId',
			width : '100'
		}, {
			title : '财务状态',
			field : 'loansStatus',
			width : '100'
		}, {
			title : '业务类型',
			field : 'businessType',
			width : '100'
		}, {
			title : '资金来源',
			field : 'capitalSource',
			width : '100'
		} ] ],
		frozenColumns : [ [ {
			field : 'creditapplicationId',
			checkbox : true
		}, {
			title : '操作',
			field : 'operateStatus',
			width : '100',
			formatter : optionFormatter
// formatter : function(value) {
// var links = "<a href='javascript:look();'>查看</a>";
// if ($.trim(value) == "3" || $.trim(value) == "0") {
// links = links + "|<a href='javascript:receive();'>预约</a>";
// return links;
// } else if ($.trim(value) == "1") {
// return links;
// } else {
// return links;
// }
// }
		} ] ],
		onLoadSuccess : function(data) {// 加载完毕后获取所有的checkbox遍历
			for ( var i = 0; i < data.rows.length; i++) {
				var oprtSts = data.rows[i].operateStatus;
				if (!($.trim(oprtSts) == "0" || $.trim(oprtSts) == "3" || $.trim(oprtSts) == "4")) {
					$("input[type='checkbox']")[i + 1].disabled = true;
				}
			}
		},
		onClickRow : function(rowIndex, rowData) {// 加载完毕后获取所有的checkbox遍历
			$("input[type='checkbox']").each(function(index, el) {
				if (el.disabled == true) {
					$('#loanList').datagrid('unselectRow', index - 1);
				}
			});
			rowNum = rowIndex;
		},
		onSelectAll : function(rowIndex, rowData) {
			$("input[type='checkbox']").each(function(index, el) {
				if (el.disabled == true) {
					$('#loanList').datagrid('unselectRow', index - 1);
				}
			});
		}
	});

    /*更改：分公司名称下拉选择框为两级树形菜单
     $('#companyName').combobox({
     url : serverName + "/financeMoneyController/getDepartmentList.do",
     valueField : 'departmentName',
     textField : 'departmentName'
     });*/
    departmentComboboxTreeWithPanelWidth('companyName',false,250);
// var options=$('#businessType').combobox("options");
// $('#businessType').combobox("select",options[0]);
// $("#businessType").combobox('setValue',"1");
});

function searchAdvanced(param) {
	if (param == 0) {
		$('#loanList').datagrid('load', {
			fuzzyValue : $("#fuzzyValue").val()
		});
	} else if (param == 1) {
		var beginDate = $("#beginLoanDate").datebox("getValue");
		if ("" != beginDate) {
			beginDate += " 00:00:00";
		}
		var endDate = $("#endLoanDate").datebox("getValue");
		if ("" != endDate) {
			endDate += " 00:00:00";
		}
        var companyIds = $("#companyName").combotree("getValues").join(",");
		$("#loanList").datagrid("load", {
			loansStatus : $("#loansStatus").combobox("getValue"),
			receivedType : $("#receivedType").combobox("getValue"),
			businessType : $("#businessType").combobox("getValue"),
			capitalSource : $("#capitalSource").combobox("getValue"),
            companyId : companyIds,
			groupNumber : $("#groupNumber").val(),
			bizId : $("#bizId").val(),
			beginLoanDate : beginDate,
			endLoanDate : endDate
		});
	}
}

function searchCancel1(param) {
	if (param == 0) {
		$('#fuzzyValue').val("");
        searchAdvanced(0);
	} else if (param == 1) {
		$("#loansStatus").combobox("clear");
		$("#loansStatus").combobox("setValue","");
		$("#receivedType").combobox("clear");
		$("#capitalSource").combobox("clear");
		$("#businessType").combobox("clear"),
        $("#groupNumber").val("");
//		$("#companyName").combobox("clear");
        $("#companyName").combotree("setValues","");
		$("#bizId").val("");
		$("#beginLoanDate").datebox("clear");
		$("#endLoanDate").datebox("clear");
		$("#loanList").datagrid("load", {});
	}
}

function look() {
	$("#loanList").datagrid("clearSelections");
	$("#loanList").datagrid("selectRow", rowNum);
	var rows = $("#loanList").datagrid("getSelected");
	var associationId = rows.creditapplicationId;
	var url1 = serverName + "/financeMoneyController/selectReceiveFinanceMoney.do?associationId=" + associationId;
	$("#look").dialog({
		title : '收款预约',
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
		},
		onLoad : function() {
			addBackgourdColor();
		}
	});
	$("#look").dialog("open");
}

function undo() {
	$("#loanList").datagrid("clearSelections");
	$("#loanList").datagrid("selectRow", rowNum);
	var rows = $("#loanList").datagrid("getSelected");
	var receivedRecordId = rows.creditapplicationId;
	var url = serverName + "/financeMoneyController/receiveUndo.do";
	ajaxSubmit(url, {
		receivedRecordId : receivedRecordId
	}, function(result) {
		if (result.success) {
			$.messager.alert('提示', '成功', 'info');
			$("#loanList").datagrid("reload");
		} else {
			$.messager.alert('提示', result.msg, 'warning');
			$("#loanList").datagrid("reload");
		}
	});
}

function receive() {
	$("#loanList").datagrid("clearSelections");
	var obj = {};
	var flist = [];
	$("#loanList").datagrid("selectRow", rowNum);
	var rows = $("#loanList").datagrid("getSelected");
	var receivedRecordId = rows.creditapplicationId;
	flist.push(receivedRecordId);
	obj["datarow"] = JSON.stringify(flist);
	// 这一步有点儿意思
	// 看似只是跳转到一个新的页面 其实则是
	// 在后太经过了复杂的校验 是否能进行批量预约等
	// 全都通过了 才打开新的页面
	var url1 = serverName + "/financeMoneyController/selectRecevieFinanceATC.do?datarow=" + obj["datarow"];
	constructReceiveDialog(url1);
	$("#receive").dialog("open");
}

function batchReceive() {
	var obj = {};
	var flist = [];
	var rows = $("#loanList").datagrid("getSelections");
	var size = rows.length;
	if (eval(size) < eval(1)) {
		$.messager.alert('提示', '请选择数据', 'warning');
		return;
	}
	var flag = true;
	for ( var i = 0; i < rows.length; i++) {
		var operateFlag = rows[i].operateFlag;
		if ("1" != operateFlag && "2" != operateFlag) {
			var receivedRecordId = rows[i].creditapplicationId;
			flist.push(receivedRecordId);
		}
	}
	if (flag) {
		obj["datarow"] = JSON.stringify(flist);
		var url1 = serverName + "/financeMoneyController/selectRecevieFinanceATC.do?datarow=" + obj["datarow"];
		constructReceiveDialog(url1);
		$("#receive").dialog("open");
	}
}

function constructReceiveDialog(url1) {
	$("#receive").dialog({
		title : '收款预约',
		closed : true,
		modal : true,
		cache : false,
		buttons : "#receiveButton",
		onOpen : function() {
			$("#receive").dialog('refresh', url1);
			/*var curHour = new Date().getHours();
			var curMin = new Date().getMinutes();
			if ((curHour == 22 && curMin > 30) || curHour == 23) {
				$("#toReceive").linkbutton('disable');
			} else {
				$("#toReceive").linkbutton('enable');
			}
			console.info('当前整点时间：' + curHour + '(手动修改本地时间会导致按钮无效的情况)');*/
		}
	});
}

function toReceive() {
	var receiveRecordIds = $("#receiveRecordIds").val();
	if (undefined == receiveRecordIds) {
		$.messager.alert('提示', '没有数据可以预约', 'warning');
		return;
	} else {
		/*var url = serverName + "/financeMoneyController/toRecevieFinanceATC.do";
		var receivedTime = $("#receivedTime").datetimebox("getValue");
		var loan = new Date(receivedTime.replace(/-/g, "/"));
		var now = new Date();
		if ('' == receivedTime) {
			$.messager.alert('提示', '请选择预约时间', 'warning');
			return;
		} else {
			if (loan < now) {
				$.messager.alert('提示', '预约时间不能早于当前时间', 'warning');
				return;
			} else {
				var severDate = $("#severDate").val();
				var strSeverDate = new Date(severDate.replace(/-/g, "/"));
				if (loan < strSeverDate) {
					$.messager.alert('提示', '客户端时间不正确！请调整到正确时间', 'warning');
				} else {
					$("#toReceive").linkbutton('disable');
					//$("#receive").dialog("close");
					ajaxSubmit(url, $("#receiveAgin").serializeObject(), function(result) {
						$("#toReceive").linkbutton("enable");
						if (result.success) {
							$.messager.alert('提示', '成功', 'info',function(){
								$("#receive").dialog("close");
							});
							$("#loanList").datagrid("reload");
						} else {
							$.messager.alert('提示', result.msg, 'warning',function(){
								$("#receive").dialog("close");
							});
							$("#loanList").datagrid("reload");
						}
					});
				}
			}
		}*/
		/*2015-01-15 预约划扣修改为实时划扣*/
		(function () {
			var url = serverName + "/financeMoneyController/toRecevieFinanceATC.do";
			$("#toReceive").linkbutton('disable');
			ajaxSubmit(url, $("#receiveAgin").serializeObject(), function (result) {
				$("#toReceive").linkbutton("enable");
				if (result.success) {
					$.messager.alert('提示', '成功', 'info', function () {
						$("#receive").dialog("close");
					});
					$("#loanList").datagrid("reload");
				} else {
					$.messager.alert('提示', result.msg, 'warning', function () {
						$("#receive").dialog("close");
					});
					$("#loanList").datagrid("reload");
				}
			});
		})();
	}
}

function toClose() {
	$("#receive").dialog("close");
}
// 手动查询预约划扣结果
function manualQueries() {
	$("#manualQueriesButton").linkbutton("disable");
	ajaxSubmit(serverName + "/financeMoneyController/manualQueries.do", {}, function(r) {
		if (r.success) {
			$("#manualQueriesButton").linkbutton("enable");
			$.messager.alert('提示', r.msg, 'warning');
			$("#loanList").datagrid("reload");
		} else {
			$("#manualQueriesButton").linkbutton("enable");
			$.messager.alert('提示', r.msg, 'error');
		}
	});
}
