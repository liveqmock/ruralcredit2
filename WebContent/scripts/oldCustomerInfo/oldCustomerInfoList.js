$(function(){
	projectdispalyBlock();
	//去掉输入框空格
	$("input").blur(function(){
		TrimAll(this);
	});
	
	$("#oldCustomTab").tabs({
		border:false, 
		onSelect:function(title){  
			if(title=="一期借款人产品列表"){
				projectdispalyBlock();
			}else if(title=="一期借款人审批列表"){
				applyAuditdispalyBlock();
			}else if(title=="一期借款人还款列表"){
				repaymentdispalyBlock();
			}

		}

	});
	//产品列表
	$("#projectListDataGrid").datagrid({
			title:" 借款人产品列表",
			url : serverName+'/oldCustomerInfoController/queryProductListByidCard.do',
			queryParams:{
				defaultQuery:'F'
			},
			rownumbers:true,
			idfield : 'cod',
			singleSelect : false,
			striped : true,
			pagination: true,
			columns : [[
      	    {field:'REPAYMENT_PLAN_NAME',title:'产品名称',width:100}
      	    ]]
	});
	//审批记录
	$("#applyAuditListDataGrid").datagrid({
			title:" 借款人审批列表",
			url : serverName+'/oldCustomerInfoController/queryApplyAuditTableListByidCard.do',
			queryParams:{
				defaultQuery:'F'
			},
			rownumbers:true,
			idfield : 'cod',
			singleSelect : false,
			striped : true,
			pagination: true,
			columns : [[
      	    {field:'GROUP_NUMBER',title:'小组编号',width:100},
      	    {field:'CREDITAPPLICATION_ID',title:'申请id',width:100,hidden:true},
      	    {field:'GROUP_NAME',title:'借款人姓名',width:100},
      	    {field:'APPLYAUDITTABLE_ID',title:'审批id',width:100,hidden:true},
      	    {field:'EXAM_RESULT',title:'审查结果',width:100},
      	    {field:'EXAM_AMOUNT',title:'审查金额',width:100},
      	    {field:'EXAM_PAERSON',title:'审查人',width:100,hidden:true},
      	    {field:'EXAM_DATE',title:'审查日期',width:100},
      	    {field:'EXAM_NOTE',title:'审查备注',width:100},
      	    {field:'AUDIT_RESULT',title:'审批结果',width:100},
      	    {field:'AUDIT_AMOUNT',title:'审批金额',width:100},
      	    {field:'AUDITOR',title:'审批人',width:100,hidden:true},
      	    {field:'AUDIT_DATE',title:'审批日期',width:100},
      	    {field:'BORR_REMARK',title:'审批备注',width:100}
      	   
      	    ]]
	});
	//还款详情
	$("#financeListDataGrid").datagrid({
			title:" 借款人还款列表",
			url : serverName+'/oldCustomerInfoController/queryFinanceListByidCard.do',
			queryParams:{
				defaultQuery:'F'
			},
			rownumbers:true,
			idfield : 'cod',
			singleSelect : false,
			striped : true,
			pagination: true,
			columns : [[
      	    {field:'GROUP_NUMBER',title:'小组编号',width:100},
      	    {field:'BORROWER_NAME',title:'借款人姓名',width:100},
      	    {field:'GROUP_APP_TOTAL',title:'小组借款金额',width:100},
      	    {field:'SIGNAGREEMENT_DATE',title:'放款日期',width:100},
      	    {field:'REPAYMENT_DATE',title:'本期还款日期',width:100},
      	    {field:'RECIEVE_DATE',title:'收款日期',width:100},
      	    {field:'FS_MONEY',title:'收款金额',width:100},
      	    {field:'LOAN_OFFICER_NAME',title:'客户经理',width:100},
      	    {field:'FS_TYPE',title:'财务类型',width:100,
  	    	 formatter : function(value) {
							if (value == "2") {
								return "放款失败回款";
							} else if (value == "3") {
								return "正常收款";
							} else if (value == "4") {
								return "违约收款";
							} else if (value == "6") {
								return "提前还款收款";
							} else{
									return value;
							}
			 } },
      	    {field:'FS_GROUPFLAG',title:'订单号',width:100},
      	    {field:'FS_STATUS',title:'回收状态',width:100,
	  	     formatter : function(value) {
								if (value == "0") {
									return "未预约";
								} else if (value == "1") {
									return "已预约";
								} else if (value == "2") {
									return "收款确认";
								} else if (value == "10") {
									return "线下废弃";
								} else if (value == "9") {
									return "线下收款";
								} else{
									return value;
								}
			 }}
      	    ]]
	});
	
});

/**
 * 产品列表查询搜索清空条件
 */	
function clearProjuctListCondition(){
	$("#project_idCard").val("");
}

/**
 * 精确查询
 */
function queryProjuctList(){
	console.info($("#project_idCard").val());
	if($("#project_idCard").val()==""){
		$.messager.alert("提示信息","请输入证件号码");
		return ;
	}
	$("#projectListDataGrid").datagrid("load",
	{
		idCard:$("#project_idCard").val()
 	});

}
/**
 * 审批记录查询搜索清空条件
 */	
function clearApplyAuditListCondition(){
	$("#applyAudit_idCard").val("");
}

/**
 * 审批记录精确查询
 */
function queryapplyAuditList(){
	
	if($("#applyAudit_idCard").val()==""){
		$.messager.alert("提示信息","请输入证件号码");
		return ;
	}
	$("#applyAuditListDataGrid").datagrid("load",
	{
		idCard:$("#applyAudit_idCard").val()
 	});

}
/**
 * 根据借款人省份证号查询还款情况搜索清空条件
 */	
function clearFinanceListCondition(){
	$("#finance_idCard").val("");
}

/**
 * 根据借款人省份证号查询还款情况
 */
function queryFinanceListByidCard (){
	
	if($("#finance_idCard").val()==""){
		$.messager.alert("提示信息","请输入证件号码");
		return ;
	}
	$("#financeListDataGrid").datagrid("load",
	{
		idCard:$("#finance_idCard").val()
 	});

}
//产品类表展示
function projectdispalyBlock(){
	$("#projectListTabDiv").show();
	$("#applyAuditTableDiv").hide();
	$("#repaymentTableDiv").hide();
	
	
}
//审批列表展示
function applyAuditdispalyBlock(){
	
	$("#projectListTabDiv").hide();
	$("#applyAuditTableDiv").show();
	$("#repaymentTableDiv").hide();
	
}
//还款详情显示
function repaymentdispalyBlock(){
	$("#projectListTabDiv").hide();
	$("#applyAuditTableDiv").hide();
	$("#repaymentTableDiv").show();
}






