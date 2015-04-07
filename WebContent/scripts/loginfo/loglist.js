$(function(){
	$("#loglist").datagrid({
			title:" 信息",
			url : serverName+'/operateLogController/queryOperateLogList.do',
			idfield : 'cod',
			singleSelect : true,
			striped : true,
			pagination: true,
			columns : [ [{field:'BUSINESS_NUMBER',title:'业务单号',width:100},
      	    {field:'LOAN_OFFICER_NAME',title:'客户经理',width:100},
      	    {field:'COMPANY_NAME',title:'分公司',width:100},
      	    {field:'BORROWER_NAME',title:'借款人姓名',width:100},
      	    {field:'OPERATOR_NAME',title:'操作人',width:100},
      	    {field:'OPERATE_DATE',title:'操作日期',width:200} ,
      	    {field:'FUNCTION_CODE',title:'功能模块',width:100},
      	    {field:'CODE_VALUE',title:'操作方法',width:200},
      	    {field:'FUNCTION_BUSSINESS',title:'明细',width:900} ]]
	});
	//初始化分公司
	$("#companyName").combobox({
		url : serverName + "/operateLogController/getDepartmentList.do",
		valueField : 'departmentId',
		textField : 'departmentName'
	});
});

/**
 * 模糊搜索
 */
function querySimpleCondition() {
	$("#loglist").datagrid("load", {
		fuzzyLoanOfficerName : $("#simpleConditionInput").val(),
		fuzzyBorrowerName :  $("#simpleConditionInput").val(),
		fuzzyBusinessNumber :  $("#simpleConditionInput").val(),
		fuzzyOperatorName :  $("#simpleConditionInput").val()
	});
}
/**
 * 模糊搜索清空条件
 */	
function cancelQuerySimpleCondition(){
		$("#simpleConditionInput").val('');
}

/**
 * 精确查询
 */
function queryAllCondition(){
	$("#loglist").datagrid("load",
	{
		companyName 	: $("#companyName").combobox("getText"),
		functionCode 	: $("#functionCode").combobox("getValue"),
		businessNumber  : $("#businessNumber").val(),
		loanOfficerName : $("#loanOfficerName").val(),
		borrowerName 	: $("#borrowerName").val(),
		idCard 			: $("#idCard").val(),
		startDate 		: $("#startDate").datebox("getValue"),
		endDate 		: $("#endDate").datebox("getValue"),
		operatorName 	: $("#operatorName").val()
	});
}

function clearAllCondition(){
//	$("#companyName").val("");
	$("#businessNumber").val("");
	$("#loanOfficerName").val("");
	$("#borrowerName").val("");
	$("#idCard").val("");
	$("#functionCode").combobox("clear");
	$("#operatorName").val("");
	$("#startDate").datebox("clear");
	$("#endDate").datebox("clear");
	
	
}




