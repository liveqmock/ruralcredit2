getCreditApplitionIdString = undefined;
$(function() {
	$("#overdueGrid").datagrid({
		url : serverName + "/receivablesList/overDueInfoList.do",
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
		},  {
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
			field : 'loanOfficerName',
			title : '信贷员姓名',
			width : 100,
			hidden : false
		},  {
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

		},{
			field : 'historyMaxOverDays',
			title : '历史逾期最大天数',
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
	departmentComboboxTree("companyId", false);
});

//同步逾期
function insertOverDueList(){
    //同步逾期
//    console.info("----------------------------insert---------------");
    $('#insertOverDueList').linkbutton('disable');
    ajaxSubmit(serverName + "/receivablesList/insertOverDueList.do", function(a) {
       // console.info("------同步逾期结束----------"+a);
        if (a.success) {
            $.messager.alert("消息", a.msg, "info");
            //刷新页面
            $('#insertOverDueList').linkbutton('enable');
            $("#overdueGrid").datagrid("reload");
        } else {
            $.messager.alert("消息", a.msg, "info");
            $("#overdueGrid").datagrid("reload");
        }
    });
}
function doSearch(param) {
    var queryParams = $('#overdueGrid').datagrid('options').queryParams;
    if (param == 0) {
        //模糊查询
        queryParams.fuzzyValue = $("#fuzzy").val();
	} else if (param == 1) {
        //高级查询
        var start = $("#startDate").datebox('getValue');
		var end = $("#endDate").datebox('getValue');
		var departmentNameValues = $("#companyId").combotree("getValues");
		var departmentNamestr = departmentNameValues.join(",");
            queryParams.businessNumber = $("#businessNumber").val();                                  // 业务单号
            queryParams.borrowerName = $("#borrowerName").val();                                      //  借款人姓名
            queryParams.customerName = $("#customerName").val();                                      //   客户经理
            queryParams.returnWayType = $("#defaultReturnmentWay").combobox('getValue');   // //  还款方式
            queryParams.companyId = departmentNamestr;                                                 //  分公司名称
            queryParams.startDate = start;                                                            //还款Start日期
            queryParams.endDate = end;                                                                //  还款end日期
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
    $('#overdueGrid').datagrid('options').queryParams =    queryParams;
    $('#overdueGrid').datagrid('load');
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
    var queryParams = $('#overdueGrid').datagrid('options').queryParams;
	var id = $("#repaymentSearchTab").tabs("getSelected")[0].children[0].id;
	var data = new Object();

        // 模糊搜索
        var fuzzyValue = $("#fuzzy").val();

		// 高级搜索
        var start = $("#startDate").datebox('getValue');
        var end = $("#endDate").datebox('getValue');
        var departmentNameValues = $("#companyId").combotree("getValues");
        var departmentNamestr = departmentNameValues.join(",");
        var businessNumber = $("#businessNumber").val();                                   // 业务单号
        var borrowerName = $("#borrowerName").val();                                       //  借款人姓名
        var customerName = $("#customerName").val();                                       //   客户经理
        var returnWayType = $("#defaultReturnmentWay").combobox('getValue');     //  还款方式
        var companyId = departmentNamestr;                                                  //  分公司名称
        var startDate = start;                                                             //还款Start日期
        var endDate = end;                                                                 //  还款end日期

	window.location.href = serverName + "/receivablesList/insertOverDueListToExcel.do?fuzzyValue="+fuzzyValue+"&businessNumber="+businessNumber+"&borrowerName="+borrowerName
        +"&customerName="+customerName+"&returnWayType="+returnWayType+"&companyId="+companyId+"&startDate="+startDate+"&endDate="+endDate;
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