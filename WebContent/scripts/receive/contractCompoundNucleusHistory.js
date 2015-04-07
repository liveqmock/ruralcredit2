var history_datagrid, customer_datagrid;
$(function() {
	var dicReport = new DataDictionary();
	dicReport.addDic("lendingChannel", "lendingChannel");
	dicReport.addDic("compoundNucleusResults", "compoundNucleusResults");
	dicReport.dicAjax();
	// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
	//showCityCombopublic("provinceId1", "cityId1","districtId1");
	//合同复核历史
	$("#contractCompoundNucleusHistoryList").datagrid({
		url : serverName + "/contractHistory/selectContractCompoundNucleusHistory.do",
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
			title : '主键ID',
			field : 'creditapplicationId',
			width : 90,
			hidden : true
		},{
			title : '业务单号',
			field : 'groupNumber',
			width : 90
		}, {
			title : '借款人姓名',
			field : 'groupName',
			width : 90
		}, {
			title : '分公司名称',
			field : 'companyName',
			width : 90
		}, {
			title : '上传合同时间',
			field : 'submitTime',
			width : 90
		}, {
			title : '产品类型',
			field : 'repaymentPlanName',
			width : 90
		}, {
			title : '合同金额（元）',
			field : 'loanAmount',
			width : 90
		}, {
			title : '复核人',
			field : 'loanPerson',
			width : 100
		}, {
			title : '复核时间',
			field : 'loanConfirmDate',
			width : 100
		}, {
			title : '复核结果',
			field : 'compoundNucleusResultsShow',
			width : 110
		}, {
			title : '放款渠道',
			field : 'lendingConfigurationShow',
			width : 90,
			hidden : false
		} ] ],
		frozenColumns : [ [ {
			title : '操作',
			field : 'operateFlag',
			width : 100,
			formatter : returnPalnView
		} ] ]
	});
    /*更改：分公司名称下拉选择框为两级树形菜单
	// 获取分公司名称
	$("#companyId").combobox({
		url : serverName + "/urgeController/departMentCombo.do",
		valueField : "code",
		textField : "value",
		editable : false
	});*/
    departmentComboboxTreeWithPanelWidth("companyId",false,250);
    
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
	//选择催收日期至begin
	$("#loanConfirmDateBegin").datebox({
		onSelect : function() {
			var loanConfirmDateBegin = $("#loanConfirmDateBegin").datebox("getValue");
			var loanConfirmDateEnd = $("#loanConfirmDateEnd").datebox("getValue");
			if (loanConfirmDateEnd != null && loanConfirmDateEnd != "") {
				if (loanConfirmDateBegin > loanConfirmDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanConfirmDateBegin").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#loanConfirmDateEnd").datebox({
		onSelect : function() {
			var loanConfirmDateBegin = $("#loanConfirmDateBegin").datebox("getValue");
			var loanConfirmDateEnd = $("#loanConfirmDateEnd").datebox("getValue");
			if (loanConfirmDateBegin != null && loanConfirmDateBegin != "") {
				if (loanConfirmDateBegin > loanConfirmDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanConfirmDateEnd").datebox("setValue", "");
					return;
				}
			}

		}
	});

	//end
});

//打开dialog查看
function openDialog(paramId,urgeId) {
	$("#iframeTest").attr(
			'src',
			serverName + "/urgeController/toViewCreditUrge.do?creditapplicationId=" + paramId+"&urgeId=" + urgeId);
	$("#urgeView").dialog({
		title : "查看详情	",
		closed : false,
		modal : true,
		width : "900",
		draggable : false
		//onOpen : function(r) {
			//$("#iframeTest").form("load", paramId);
		//}
	});
}


// 搜索按钮
function searchAdvanced() {
	var data = new Object();
	var businessNumber = $("#businessNumber").val();
	var name = $("#name").val();
	var loanConfirmDateBegin = $("#loanConfirmDateBegin").datebox('getValue');
	var loanConfirmDateEnd = $("#loanConfirmDateEnd").datebox('getValue');
	var loanPerson = $("#loanPerson").val();
//新增产品类型搜索条件
	var pTypeName = $('#productTypeName').combobox('getValue');
	//var productTypeName=$("#productTypeName").combobox("getValue");
	var compoundNucleusResults=$("#compoundNucleusResults").combobox("getValue");
	var companyIds = $("#companyId").combotree("getValues").join(",");
	var lendingChannel=$("#lendingChannel").combobox("getValue");
	var map = {
		businessNumber : businessNumber,
		name : name,
		loanConfirmDateBegin : loanConfirmDateBegin,
		loanConfirmDateEnd : loanConfirmDateEnd,
		loanPerson : loanPerson,
		repaymentPlanName:pTypeName,
		compoundNucleusResults:compoundNucleusResults,
		companyId : companyIds,
		lendingChannel:lendingChannel
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#contractCompoundNucleusHistoryList').datagrid('load', data);
}
// 清空按钮
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#loanConfirmDateBegin").datebox("clear");
	$("#loanConfirmDateEnd").datebox("clear");
	$("#loanPerson").val("");
	$("#productTypeName").combobox("setValue", "");
	$("#compoundNucleusResults").combobox("setValue", "");
	$("#companyId").combotree("setValues","");
	$("#lendingChannel").combobox("setValue", "");
   // $('#contractCompoundNucleusHistoryList').datagrid('load',{});
}
//放款列表查看
/** 初始化放款确认页面* */
function initComfirmPageLook(accountInfoId, creditApplicationId) {
	url = serverName + "/accountInfo/selectById.do";
	var accountInfoId = 0;
	if (accountInfoId != null && accountInfoId != "" && accountInfoId != undefined) {
		accountInfoId = accountInfoId;
	}
	ajaxSubmit(url, {
		accountInfoId : accountInfoId
	}, function(data) {
		// 基础信息
		$("#showAccountFormqr").form("load", data);
		
		
	});
	
	url = serverName + "/contractAndLoanController/searchForConfirm.do";
	var groupLoanRegistId;
	$("#remarkComment1").val("");
	ajaxSubmit(url, {
		creditapplicationId : creditApplicationId,
		registStatus : "0"
	}, function(data) {
		$("#loanViewForm").form("load", data);
		if(data.accountInfo.cardFlag == "0"){
			$("#cardFlagEnable").val("卡");
		}else if(data.accountInfo.cardFlag == "1"){
			$("#cardFlagEnable").val("存折");
		}
		//$("#cardFlagEnable").val($("#cardFlagEnable1").val() == "0" ? "卡" : "存折");
		url = serverName + "/NSC/selectByCode.do";
		ajaxSubmit(url, {
			cityCode : data.accountInfo.provinceId
		}, function(result) {
			if (result != null) {
				$("#provinceIdEnable").val(result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : data.accountInfo.districtId
		}, function(result) {
			if (result != null) {
				$("#districtIdEnable").val(result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : data.accountInfo.cityId
		}, function(result) {
			if (result != null) {
				$("#cityIdEnable").val(result.cityName);
			}
		});
		groupLoanRegistId = $("#groupLoanRegistId").val();
	});
	// 历史备注
    history_datagrid = $("#historyRemarkView").datagrid({
		url : serverName + "/GroupLoanRegist/searchConfirm.do?creditApplicationId=" + creditApplicationId,
		striped : true,
		singleSelect : true,
		fitColumns : true,
        width : 699,
		columns : [ [ {
			title : '操作人',
			field : 'loanPerson',
			width : 200
		}, {
			title : '操作时间',
			field : 'loanConfirmTime',
			width : 200
		}, {
			title : '备注和意见',
			field : 'remarkComment',
			width : 200
		} ] ]
	});
    history_datagrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"remarkComment",showContant:"remarkComment"});
    /*客服检查 start*/
    ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
        customer_datagrid = $('#check_customer_view').datagrid({
            scrollbarSize: 0,
            fitColumns: true,
            singleSelect: true,
            columns: [
                [
                    {title: '错误文件', field: 'errorFile', width: 100, align: 'center'},
                    {title: '检查点', field: 'checkPointLoan', width: 200, align: 'center'},
                    {title: '操作人', field: 'operatorName', width: 100, align: 'center'},
                    {title: '操作时间', field: 'operateDate', width: 150, align: 'center'}
                ]
            ]
        });
        if (ret && ret.customer_loan && ret.customer_loan.length) {
            customer_datagrid.datagrid('loadData', ret.customer_loan);
        }
    });
    /*客服检查 end*/

	/*特殊情况申请 start*/
	$('#special_apply_view').datagrid({
		url: serverName + '/specialApplyController/searchApplyByCreditId.do?id=' + creditApplicationId,
		fitColumns: true,
		singleSelect: true,
		columns: [
			[
				{
					title: '申请人',
					field: 'APPLYER',
					width: 100,
					align: 'center'
				}, {
				title: '申请类别',
				field: 'APPLYTYPE',
				width: 100,
				align: 'center'
			}, {
				title: '审批人',
				field: 'APPROVER',
				width: 100,
				align: 'center'
			}, {
				title: '审批结果',
				field: 'APPLYRESULT',
				width: 100,
				align: 'center',
				formatter: function (v, r) {
					/*显示申请内容*/
					$('#applyContent_table_view').show();
					$('#applyContent_view').html(r.APPLYCONTENT);
					return v;
				}
			}
			]
		]
	});
	/*特殊情况申请 end*/
}
function viewDetail(accountInfoId,clientid,creditApplicationEscId,creditApplicationId){
	$('#openCMView2')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
	+ "&signIp=" + DESIp + "";
    $("#LoanView").dialog({
        closed : false,
        title : "查看",
        draggable : true,
        maximizable : true,
        modal : true
    });
    initComfirmPageLook(accountInfoId, creditApplicationId);
    $("#LoanView").dialog("maximize");
    $("#layoutZView").layout("resize");
}