//页面加载初始化
$(function(){
	$("#creditApplicationSearchTable").datagrid({
		url:serverName+"/CustomerReturnVisitController/selectPagination.do",
		title:"客户回访列表",
		pagination:true,
		columns:[[
			{field:"businessNumber",title:"业务单号",width:100},
			{field:"borrowerName",title:"借款人姓名",width:100},
			{field:"loanOfficerName",title:"客户经理",width:100},
			{field:"visitDate",title:"回访日期",width:100},
			{field:"repaymentDate",title:"还款日期",width:100},
			{field:"visitDurationTimes",title:"回访时长",width:100},
			{field:"visitWayShow",title:"回访方式",width:80},
			{field:"loanUseShow",title:"借款用途变更",width:100},
			{field:"newLoanUseShow",title:"新借款用途",width:100},
			{field:"familyIncomeShow",title:"家庭收入来源变更",width:100,hidden:true},
			{field:"newFamilyIncomeShow",title:"新家庭收入来源",width:100,hidden:true},
			{field:"incomeShow",title:"经营收入来源变更",width:100,hidden:true},
			{field:"sourceIncomeChangedType",title:"收入来源",width:100},
			{field:"sourceIncomeChangedContentStr",title:"收入来源变更内容",width:100},
			{field:"isComplete",title:"照片是否齐全",width:100},
			{field:"reasonForIncomplete",title:"照片不齐全原因",width:100},
			{field:"isPurposeConsistency",title:"借款用途是否一致",width:100},
			{field:"reasonNotConsistency",title:"借款用途不一致原因",width:100},

			/*{field:"newIncomeShow",title:"新经营收入来源",width:100},
			 {field:"spendingShow",title:"大项开支",width:100},
			 {field:"newSpending",title:"新大项开支",width:100},
			 {field:"contactWayShow",title:"联系方式变更",width:100},
			 {field:"newContactWay",title:"新联系方式",width:100},
			 {field:"newDebtShow",title:"新增债权债务",width:100},
			 {field:"newDebtMoney",title:"债权债额金额",width:100},
			 {field:"customerAttitude",title:"客户态度",width:100},
			 {field:"familyNumberCondition",title:"家庭成员变化",width:100},
			 {field:"otherFactor",title:"其他影响客户还款能力或意愿的情况",width:180},*/
			{field:"visitWay",title:"回访方式",width:100,hidden:true},
			{field:"loanUse",title:"借款用途变更",width:100,hidden:true},
			{field:"familyIncome",title:"家庭收入来源变更",width:100,hidden:true},
			{field:"income",title:"经营收入来源变更",width:100,hidden:true},
			{field:"spending",title:"大项开支",width:100,hidden:true},
			{field:"contactWay",title:"联系方式变更",width:100,hidden:true},
			{field:"newDebt",title:"新增债权债务",width:100,hidden:true},
			{field: "spendingShow", title: "新大项开支", width: 100},
			{field: "newSpending", title: "大项开支", width: 100},
		]],
		view: detailview,
		detailFormatter: function (index, row) {
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow: function (index, row) {
			var temp = [];
			temp.push(row);
			$('#ddv-' + index).datagrid({
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				loadMsg: '',
				height: 'auto',
				rowStyler: function (rowIndex, rowData) {
					return "background:#CCFFCC";
				},
				columns: [
					[
						{field: "newIncomeShow", title: "新经营收入来源", width: 100,hidden:true},
						{field: "changeBorrowerPhone", title: "借款人联系方式", width: 100},
						{field: "changeCoborrowerPhone", title: "共借人联系方式", width: 100},
						{field: "changeGuaranteeFirstPhone", title: "担保人1联系方式", width: 100},
						{field: "changeGuaranteeSecondPhone", title: "担保人2联系方式", width: 100},

						{field: "contactWayShow", title: "联系方式变更", width: 100,hidden:true},
						{field: "newContactWay", title: "新联系方式", width: 100,hidden:true},
						{field: "newDebtShow", title: "新增债权债务", width: 100},
						{field: "newDebtMoney", title: "债权债额金额", width: 100},
						{field: "customerAttitude", title: "客户态度", width: 100,hidden:true},
						{field:"attitudeForRepayment",title:"客户还款态度",width:100 },
						{field:"attitudeForCutomermanager",title:"对待客户经理态度",width:100},
						{field: "familyNumberCondition", title: "家庭成员变化", width: 100},
						{field: "otherFactor", title: "其他影响客户还款能力或意愿的情况", width: 180},
						{field: "highDangered", title: "是否高危客户", width: 80},
						{field: "highDangerReason", title: "高危客户类型", width: 180},                                {
						title: '上传文件', field: 'attatchmentId', width: 100, align: 'center', formatter: function (value, row, index) {
							console.info(row);
							if (!row.attachmentId) {
								return '';
							}
							return "<a href='javascript:void(0)' style='color: blue' onclick='viewAttachment(\"" + row.attachmentId + "\")'>查看</a>";
						}
					}
					]
				],
				onResize: function () {
					$('#creditApplicationSearchTable').datagrid('fixDetailRowHeight', index);
				},
				onLoadSuccess: function (r) {
					setTimeout(function () {
						$('#creditApplicationSearchTable').datagrid('fixDetailRowHeight', index);
					}, 0);
				}
			});
			$('#ddv-' + index).datagrid('loadData', temp);
			$('#creditApplicationSearchTable').datagrid('fixDetailRowHeight', index);
		}
	});



	$("#beginRepaymentDate").datebox({
		onSelect : function() {
			var beginRepaymentDate = $("#beginRepaymentDate").datebox("getValue");
			var endRepaymentDate = $("#endRepaymentDate").datebox("getValue");
			if (endRepaymentDate != null && endRepaymentDate != "") {
				if (beginRepaymentDate > endRepaymentDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginRepaymentDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#endRepaymentDate").datebox({
		onSelect : function() {
			var beginRepaymentDate = $("#beginRepaymentDate").datebox("getValue");
			var endRepaymentDate = $("#endRepaymentDate").datebox("getValue");
			if (beginRepaymentDate != null && beginRepaymentDate != "") {
				if (beginRepaymentDate > endRepaymentDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endRepaymentDate").datebox("setValue", "");
					return;
				}
			}

		}
	});



	$("#beginReturnVisitDate").datebox({
		onSelect : function() {
			var beginLoanDate = $("#beginReturnVisitDate").datebox("getValue");
			var endLoanDate = $("#endReturnVisitDate").datebox("getValue");
			if (endLoanDate != null && endLoanDate != "") {
				if (beginLoanDate > endLoanDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginReturnVisitDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#endReturnVisitDate").datebox({
		onSelect : function() {
			var beginLoanDate = $("#beginReturnVisitDate").datebox("getValue");
			var endLoanDate = $("#endReturnVisitDate").datebox("getValue");
			if (beginLoanDate != null && beginLoanDate != "") {
				if (beginLoanDate > endLoanDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endReturnVisitDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	//加载条件查询分中心
	//departmentComboboxTreeContainUsers("companyId1", false);
	departmentComboboxTree("companyId", false);
	//加载高级查询分中心
	console.info("加载分中心成功....");
	//departmentComboboxTreeContainUsers("companyId", false);
	//departmentComboboxTree("companyId1", false);
	//加载高级查询产品类型
	$("#productTypeName").combobox({
		url: serverName + "/creditgroup/selectProInfoAll.do",
		textField: 'productName',
		valueField:'productName',
		mode: 'remote',
		onSelect: function (data) {
			$("#productTypeName").val(data.productName);
		}
	});


});


function getDepartmentNameStr(companyId){
	var departmentNameValues=$("#"+companyId).combotree("getValues");
	//console.info("-----companyId---"+companyId+"-----departmentNameValues-----------"+departmentNameValues);
	if(departmentNameValues!=null&&departmentNameValues!=""){
		for(var i in departmentNameValues){
			departmentNameValues[i]="'"+departmentNameValues[i]+"'";
		}
	}
	var 	departmentNamestr=departmentNameValues.join(",");
	return departmentNamestr;
}

//条件查询页签 //高级查询页签
function searchPagination(flag){
	if(flag == 0){//条件查询
		//清空高级查询项
		clearQueryItemsForHigh();
		//获取查询项值
	}else{        //高级查询
		//清空条件查询项
		clearQueryItemsForSelection();
		//获取查询项值
	}
	$("#creditApplicationSearchTable").datagrid("load",
		{	selectflag:"",
			businessNumber:$("#businessBumber").val(),
			loanOfficerName:$("#loanOfficerName").val(),
			borrowerName:$("#borrowerName").val(),
			beginRepaymentDate:$("#beginRepaymentDate").datebox("getValue"),
			endRepaymentDate:$("#endRepaymentDate").datebox("getValue"),
			beginReturnVisitDate:$("#beginReturnVisitDate").datebox("getValue"),
			endReturnVisitDate:$("#endReturnVisitDate").datebox("getValue"),
			companyId:getDepartmentNameStr("companyId"),
			repaymentPlanName:$('#productTypeName').combobox('getValue')
		});
}


//清空
function cleanInput(){
	clearQueryItemsForSelection();
	clearQueryItemsForHigh();
}
function clearQueryItemsForSelection(){
	$("#borrowerName").val("");
	$("#businessBumber").val("");
	$("#beginReturnVisitDate").datebox("setValue","");
	$("#endReturnVisitDate").datebox("setValue","");
}
function clearQueryItemsForHigh(){
	$("#loanOfficerName").val("");
	$("#beginRepaymentDate").datebox("setValue","");
	$("#endRepaymentDate").datebox("setValue","");
	$("#productTypeName").combobox("clear");
	$("#companyId").combotree("setValues", "");
}
//导出列表
function exportExcel(flag){
	if(flag == 0){//条件查询
		//清空高级查询项
		clearQueryItemsForHigh();
		//获取查询项值
	}else{        //高级查询
		//清空条件查询项
		clearQueryItemsForSelection();
		//获取查询项值
	}
	var str ="businessNumber="+$("#businessBumber").val()+"&loanOfficerName="+$("#loanOfficerName").val()
		+"&borrowerName="+$("#borrowerName").val()+"&beginRepaymentDate="+$("#beginRepaymentDate").datebox("getValue")
		+"&endRepaymentDate="+$("#endRepaymentDate").datebox("getValue")+"&beginReturnVisitDate="+$("#beginReturnVisitDate").datebox("getValue")
		+"&endReturnVisitDate="+$("#endReturnVisitDate").datebox("getValue")+"&companyId="+getDepartmentNameStr("companyId")
		+"&repaymentPlanName="+$('#productTypeName').combobox('getValue');
	console.info("exportToExcel----------"+str);
	window.location.href=serverName+"/CustomerReturnVisitController/exportExcel.do?"+str;
}