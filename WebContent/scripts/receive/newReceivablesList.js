$(function() {

	var dic = new DataDictionary();
	dic.addDic("businessType", "businessType");
	dic.addDic("defaultReturnmentWay", "defaultReturnmentWay");
// dic.addDic("cardFlag", "cardFlag");
// dic.addDic("onUsed", "onUsed");
// dic.addDic("bankNum", "bankNum");
// dic.addDic("useType", "useType");
	dic.dicAjax();

// $("#companyId").combobox({
// url : serverName + "/CustomerConsult/departmentList.do",
// valueField : "departmentId",
// textField : "departmentName",
// mote : "remote",
// panelHeight : 'auto',
// editable : false,
// multiple : false,
// onLoadSuccess : function() {
// $("#companyName").combobox('select', '');
// }
// });

	departmentComboboxTreeWithPanelWidth("companyId", false,250);
    /*增加产品类型搜索条件*/
    $("#productTypeName").combobox({
        url: serverName + "/creditgroup/selectProInfoAll.do",
        textField: 'productName',
        valueField:'productName',
        mode: 'remote',
        onSelect: function (data) {
            $("#productTypeName").val(data.productName);
        }
    });
    $("#receivablesList").datagrid({
		url : serverName + "/receivablesList/newReceivablesDataGrid.do"+ conditions,
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
			field : 'signagreementDate',
			title : '合同签订日期',
			width : 100,
			hidden : false,
			sortable : true
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
			hidden : true,
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
		},{
			field : 'repaymentDate',
			title : '还款日期',
			width : 100,
			hidden : false
		},{
			field : 'registrationOperation',
			title : '登记操作',
			width : 200,
			hidden : true,
			formatter : registrationOperation
		}, {
			field : 'changeOperation',
			title : '变更操作',
			width : 200,
			hidden : true,
			formatter : changeOperation
		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 150,
			hidden : false,
			formatter : returnPalnView
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
// deleteRowFromReceivablesDataGrid();
// passHistoryData = data;
// var dataRows = data.rows;
// processReceivablesDataGrid(dataRows, 0);
		}
	});

	$("#returnPlanDialig").dialog({
		modal : true,
		title : "还款计划查询",
		maximizable : false,
		closed : true,
		content : "<iframe scrolling='no' id='openGuaranor' frameborder='0'  src='/ruralcredit2/jsp/rc/receivables/earlyRepaymentRegistration.jsp' style='width:100%;height:100%;'></iframe>"
	});
});

function doSearch(param) {
	var data = new Object();
	if (param == 0) {
		var map = {
			fuzzyValue : $.trim($("#fuzzy").val())
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#receivablesList').datagrid('reload', data);
	} else if (param == 1) {
		var start = $("#startDate").datebox('getValue');
		var end = $("#endDate").datebox('getValue');

		var departmentNameValues = $("#companyId").combotree("getValues");
        var departmentNamestr;
        var productTypeName = $("#productTypeName").combobox("getValue");
        if(departmentNameValues == '请选择'){
            departmentNamestr = '';
        }else{
            departmentNamestr = departmentNameValues.join(",");
        }
        if(productTypeName == '请选择'){
            productTypeName = '';
        }

		var map = {
			businessNumber : $("#businessNumber").val(),
			borrowerName : $("#borrowerName").val(),
			businessType : $("#businessType").combobox('getValue'),
			defaultReturnmentWay : $("#defaultReturnmentWay").combobox('getValue'),
			companyId : departmentNamestr,
			startDate : start,
			endDate : end,
            repaymentPlanName : productTypeName
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#receivablesList').datagrid('reload', data);
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
function doClear(param) {
	if (param == 0) {
		$('#fuzzy').val("");
        doSearch(0);
	} else if (param == 1) {
		$("#searchform").form('clear');
		$("#businessType").combobox("setValue", "");
		$("#defaultReturnmentWay").combobox("setValue", "");
		$("#companyId").combotree("setValues", "");
        doSearch(1);
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

function openReturnPlan() {
	$("#returnPlanDialig").dialog('open');
}
function testUpdateClientApplyHistory() {
	ajaxSubmit(serverName + "/ruralBusyController/testUpdateClientApplyHistory.do", {}, function(r) {
		$.messager.alert("消息", r.msg, "info");
	});
}
