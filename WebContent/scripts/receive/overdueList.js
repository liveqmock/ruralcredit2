getCreditApplitionIdString = undefined;
$(function() {
	$("#overdueGrid").datagrid({
		url : serverName + "/receivablesList/overdueGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
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
			hidden : true
		}, {
			field : 'accountInfoId',
			title : '公司财务账号表ID',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanId',
			title : '还款方案编码',
			width : 100,
			hidden : true
		}, {
			field : 'contactNumber',
			title : '合同编号',
			width : 100,
			hidden : true
		}, {
			field : 'groupNumber',
			title : '业务单号',
			width : 150,
			hidden : false
		}, {
			field : 'groupName',
			title : '借款人姓名',
			width : 100,
			hidden : false
		}, {
			field : 'address',
			title : '所在村镇',
			width : 200,
			hidden : true
		}, {
			field : 'loanOfficerId',
			title : '信贷员ID',
			width : 100,
			hidden : true
		}, {
			field : 'loanOfficerName',
			title : '信贷员姓名',
			width : 100,
			hidden : false
		}, {
			field : 'businessType',
			title : '业务类型',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "分公司";
				} else if ("1" == value) {
					return "个人";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'companyId',
			title : '分公司ID',
			width : 100,
			hidden : true
		}, {
			field : 'companyName',
			title : '分公司名称',
			width : 100,
			hidden : false
		}, {
			field : 'defaultReturnmentWay',
			title : '默认还款方式',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "自动划扣";
				} else if ("1" == value) {
					return "现金还款";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'customerConsultId',
			title : '客户咨询主键',
			width : 100,
			hidden : true
		}, {
			field : 'departmentId',
			title : '客户经理的部门id',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanName',
			title : '产品类型',
			width : 100,
			hidden : false
		}, {
			field : 'signagreementDate',
			title : '放款日期',
			width : 100,
			hidden : false
		}, {
			field : 'registrationOperation',
			title : '登记操作',
			width : 200,
			hidden : true
		}, {
			field : 'changeOperation',
			title : '变更操作',
			width : 200,
			hidden : true

		}, {
			field : 'aOverdueStart',
			title : '逾期开始时间',
			width : 100,
			hidden : false

		}, {
			field : 'aOverdueCount',
			title : '逾期期数',
			width : 100,
			hidden : true

		}, {
			field : 'overdueDayCount',
			title : '逾期天数',
			width : 100,
			hidden : false

		}, {
			field : 'aOverdueMoney',
			title : '逾期金额',
			width : 100,
			hidden : false

		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 125,
			hidden : false,
			formatter : returnPalnView
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
		}
	});
//	$("#companyId").combobox({
//		url : serverName + "/CustomerConsult/departmentList.do",
//		valueField : "departmentId",
//		textField : "departmentName",
//		mote : "remote",
//		panelHeight : 'auto',
//		editable : false,
//		multiple : false,
//		onLoadSuccess : function() {
//			$("#companyName").combobox('select', '');
//		}
//	});
	
	departmentComboboxTree("companyId", false);
});

function doSearch(param) {
	var data = new Object();
	if (param == 0) {
		var map = {
			fuzzyValue : $("#fuzzy").val()
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#overdueGrid').datagrid('load', data);
	} else if (param == 1) {
		var start = $("#startDate").datebox('getValue');
		var end = $("#endDate").datebox('getValue');
		
		var departmentNameValues = $("#companyId").combotree("getValues");
		var departmentNamestr = departmentNameValues.join(",");
		
		var map = {
			businessNumber : $("#businessNumber").val(),
			borrowerName : $("#borrowerName").val(),
			businessType : $("#businessType").combobox('getValue'),
			defaultReturnmentWay : $("#defaultReturnmentWay").combobox('getValue'),
			companyId : departmentNamestr,
			startDate : start,
			endDate : end
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#overdueGrid').datagrid('load', data);
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
function doClear(param) {
	if (param == 0) {
		$('#fuzzy').val("");
	} else if (param == 1) {
		$("#searchform").form('clear');
		$("#businessType").combobox("setValue", "");
		$("#defaultReturnmentWay").combobox("setValue", "");
		$("#companyId").combotree("setValues", "");
	} else {
		$.messager.alert('提示', "doClearRepayment的JS出错了！", 'error');
	}
}

function addReturnPlanTab(creditapplicationId, businessNumber) {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/returnPlanController/returnPlanJSP.do?creditapplicationId=" + creditapplicationId,
		title : businessNumber + "的还款计划"
	});
}
function exportExcel() {

	var id = $("#repaymentSearchTab").tabs("getSelected")[0].children[0].id;
	var data = new Object();
	if (id == "" || id == null) {
		// 模糊搜索
		var map = {
			fuzzyValue : $("#fuzzy").val()
		};
		data["paramJsonMap"] = JSON.stringify(map);
	} else {
		// 高级搜索
		var start = $("#startDate").datebox('getValue');
		var end = $("#endDate").datebox('getValue');
		var departmentNameValues = $("#companyId").combotree("getValues");
		var departmentNamestr = departmentNameValues.join(",");
		
		var map = {
			businessNumber : $("#businessNumber").val(),
			borrowerName : $("#borrowerName").val(),
			businessType : $("#businessType").combobox('getValue'),
			defaultReturnmentWay : $("#defaultReturnmentWay").combobox('getValue'),
			companyId : departmentNamestr,
			startDate : start,
			endDate : end
		};
		data["paramJsonMap"] = JSON.stringify(map);
	}
	window.location.href = serverName + "/receivablesList/exportExcel.do?paramJsonMap=" + data["paramJsonMap"] + "&getCreditApplitionIdString=" + getCreditApplitionIdString;

}

function closeMyDialog(dialogId, ifameId) {
	$("#" + dialogId).dialog('close');
	$("#" + ifameId)[0].src = "";
}
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


function buttonUrgedisDisable(){
	$("#rdButton1").linkbutton("disable");
} 

function buttonUrgedisEnable(){
	$("#rdButton1").linkbutton("enable");
} 

