$(function() {
	// 催收列表
	var promiseRefundDateBegin = $("#promiseRefundDateBegins").val();
	var promiseRefundDateEnd = $("#promiseRefundDateEnds").val();
	$("#urgeList").datagrid({
		url : serverName + "/urgeController/queryUrgeList.do",
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
		queryParams:{promiseRefundDateBegin:promiseRefundDateBegin,promiseRefundDateEnd:promiseRefundDateEnd},
		columns : [ [ {
			title : '主键ID',
			field : 'urgeId',
			width : 90,
			hidden : true
		},{
			title : 'creditapplicationId外键',
			field : 'creditapplicationId',
			width : 90,
			hidden : true
		}, {
			title : '业务单号',
			field : 'businessNumber',
			width : 90
		}, {
			title : '借款人姓名',
			field : 'name',
			width : 90
		}, {
			title : '分公司名称',
			field : 'companyName',
			width : 90
		}, {
			title : '客户经理',
			field : 'loanOfficerName',
			width : 90
		}, {
			title : '催收日期',
			field : 'urgeDate',
			width : 90
		}, {
			title : '还款日期',
			field : 'refundDate',
			width : 90
		}, {
			title : '催收概括',
			field : 'urgeSummarize',
			width : 100
		}, {
			title : '催收方式',
			field : 'urgeWay',
			width : 100
		}, {
			title : '催收状态',
			field : 'urgeState',
			width : 110
		}, {
			title : '收回金额',
			field : 'reginMoney',
			width : 90
		}, {
			title : '金额来源',
			field : 'moneySource',
			width : 100

		}, {
			title : '催收时长',
			field : 'urgeLongTime',
			hidden : true,
			width : 100

		}, {
			title : '本次承诺还款金额',
			field : 'currentPromiseMoney',
			width : 100
		}, {
			title : '重大风险预警',
			field : 'bigWarning',
			hidden : true,
			width : 90
		}, {
			title : '催收描述',
			field : 'urgeDescribe',
			hidden : true,
			width : 90
		}, {
			title : '是否履诺',
			field : 'ynPromise',
			hidden : true,
			width : 90
		}, {
			title : '上次承诺还款金额',
			field : 'previousPromiseMoney',
			hidden : true,
			width : 100
		}, {
			title : '本次承诺还款时间',
			field : 'curentPromiseTime',
			hidden : false,
			width : 110
		}, {
			title : '上次承诺还款时间',
			field : 'previousPromiseTime',
			hidden : true,
			width : 110
		}, {
			title : '小时',
			field : 'hour',
			hidden : true,
			width : 110
		}, {
			title : '分钟',
			field : 'minute',
			hidden : true,
			width : 110
		}, {
			title : '催收沟通评价',
			field : 'urgeRemark',
			hidden : true,
			width : 90
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
	//选择催收日期至begin
	$("#urgeDateBegin").datebox({
		onSelect : function() {
			var urgeDateBegin = $("#urgeDateBegin").datebox("getValue");
			var urgeDateEnd = $("#urgeDateEnd").datebox("getValue");
			if (urgeDateEnd != null && urgeDateEnd != "") {
				if (urgeDateBegin > urgeDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#urgeDateBegin").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#urgeDateEnd").datebox({
		onSelect : function() {
			var urgeDateBegin = $("#urgeDateBegin").datebox("getValue");
			var urgeDateEnd = $("#urgeDateEnd").datebox("getValue");
			if (urgeDateBegin != null && urgeDateBegin != "") {
				if (urgeDateBegin > urgeDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#urgeDateEnd").datebox("setValue", "");
					return;
				}
			}

		}
	});

	//end
	
	
	//选择承诺还款日期至       begin
	$("#promiseRefundDateBegin").datebox({
		onSelect : function() {
			var promiseRefundDateBegin = $("#promiseRefundDateBegin").datebox("getValue");
			var promiseRefundDateEnd = $("#promiseRefundDateEnd").datebox("getValue");
			if (promiseRefundDateEnd != null && promiseRefundDateEnd != "") {
				if (promiseRefundDateBegin > promiseRefundDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#promiseRefundDateBegin").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#promiseRefundDateEnd").datebox({
		onSelect : function() {
			var promiseRefundDateBegin = $("#promiseRefundDateBegin").datebox("getValue");
			var promiseRefundDateEnd = $("#promiseRefundDateEnd").datebox("getValue");
			if (promiseRefundDateBegin != null && promiseRefundDateBegin != "") {
				if (promiseRefundDateBegin > promiseRefundDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#promiseRefundDateEnd").datebox("setValue", "");
					return;
				}
			}

		}
	});
//end
});

//导出催收列表Execl表格
function exportFinanceReport(){
	var businessNumber = $("#businessNumber").val();
	var name = $("#name").val();
	var loanOfficerName = $("#loanOfficerName").val();
	var companyId = $("#companyId").combobox("getValue");
	var urgeDateBegin = $("#urgeDateBegin").datebox('getValue');
	var urgeDateEnd = $("#urgeDateEnd").datebox('getValue');
	var promiseRefundDateBegin = $("#promiseRefundDateBegin").datebox(
			'getValue');
	var promiseRefundDateEnd = $("#promiseRefundDateEnd").datebox('getValue');
	var params = "businessNumber="+businessNumber+"&name="
				+name+"&loanOfficerName="+loanOfficerName+"&companyId="+companyId
				+"&urgeDateBegin="+urgeDateBegin
				+"&urgeDateEnd="+urgeDateEnd+"&promiseRefundDateBegin="+promiseRefundDateBegin+"&promiseRefundDateEnd="+promiseRefundDateEnd;
	window.location.href="urgeController/exportCreditApplicationForFinanceLoan.do?"+params;
}

//打开dialog查看
function openDialog(paramId,urgeId) {
	$("#iframeTest").attr(
			'src',
			serverName + "/urgeController/toViewCreditUrge.do?creditapplicationId=" + paramId+"&urgeId=" + urgeId);
	$("#urgeView").dialog({
		title : "查看催收详情	",
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
	var loanOfficerName = $("#loanOfficerName").val();
//	var companyId = $("#companyId").combobox("getValue");
    var companyIds = $("#companyId").combotree("getValues").join(",");
	var urgeDateBegin = $("#urgeDateBegin").datebox('getValue');
	var urgeDateEnd = $("#urgeDateEnd").datebox('getValue');
	var promiseRefundDateBegin = $("#promiseRefundDateBegin").datebox(
			'getValue');
	var promiseRefundDateEnd = $("#promiseRefundDateEnd").datebox('getValue');

	var map = {
		businessNumber : businessNumber,
		name : name,
		loanOfficerName : loanOfficerName,
		companyId : companyIds,
		urgeDateBegin : urgeDateBegin,
		urgeDateEnd : urgeDateEnd,
		promiseRefundDateBegin : promiseRefundDateBegin,
		promiseRefundDateEnd : promiseRefundDateEnd
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#urgeList').datagrid('reload', data);
}
// 清空按钮
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#loanOfficerName").val("");
//	$("#companyId").combobox("setValue", "");
    $("#companyId").combotree("setValues","");
	$("#urgeDateBegin").datebox("clear");
	$("#urgeDateEnd").datebox("clear");
	$("#promiseRefundDateBegin").datebox("clear");
	$("#promiseRefundDateEnd").datebox("clear");
    $('#urgeList').datagrid('load',{});
}



/*//动态添加催收联系人信息一行

function addUrgeContacts(i) {
	var urgeContactsTable = document.getElementById("urgeContacts");
	var tr = document.createElement("tr");
	tr.width = "100%";
	var size = i;
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	var td5 = document.createElement("td");
	var td6 = document.createElement("td");
	td1.innerHTML = '催收联系人姓名：';
	td2.innerHTML = '<input type="text" id="urgeLinkName'+size+'" name="urgeLinkName" style="width:135px" />';
	td3.innerHTML = '和申请人关系：';
	td4.innerHTML = '<input id="borrowerRelation' + size + '"  name="borrowerRelation" style="width:135px"/>';
	td5.innerHTML = '联系电话：';
	td6.innerHTML = '<input type="text" id="linkTelephone' + size + '" name="linkTelephone"   style="width:123px" />';
	td1.width = "115px";

	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	tr.appendChild(td6);
	// tr.appendChild(td8);
	urgeContactsTable.appendChild(tr);
}*/