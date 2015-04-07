$(function() {
	departmentComboboxTree("branchofficeIds", false);
	var dic = new DataDictionary();
	dic.addDic("isReceive", "isReceive");
	dic.addDic("prizeType", "prizeType");
	dic.dicAjax();
	$("#dialog").dialog({
		modal : true,
		title : "礼品领取",
		maximizable : false,
		closed : true,
		onClose : function() {
			$("#receiveForm").form("clear");
			$("#isRight").html("");
			$("#spanhh").html("");
		}
	});
	$("#upriRecordList").datagrid({
		url : serverName + "/prizeManagementController/upriRecordDateGrid.do",
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
			field : 'wxUpriRecordId',
			title : '兑奖记录主键',
			width : 100,
			hidden : true
		}, {
			field : 'wxUserId',
			title : '微信用户主键',
			width : 100,
			hidden : true
		}, {
			field : 'wxPrizeId',
			title : '奖品主键',
			width : 100,
			hidden : true
		}, {
			field : 'upriRecordCode',
			title : '兑奖编码',
			width : 100,
			hidden : true
		}, {
			field : 'recpriName',
			title : '兑奖人',
			width : 100,
			hidden : false,
			sortable : true
		}, {
			field : 'recpriPhone',
			title : '联系方式',
			width : 125,
			hidden : false,
			sortable : true
		}, {
			field : 'recpriDate',
			title : '兑奖日期',
			width : 100,
			hidden : false,
			sortable : true
		}, {
			field : 'prizeType',
			title : '奖品类型',
			width : 70,
			hidden : false,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == "0") {
					return "金靴奖";
				} else if (value == "1") {
					return "金球奖";
				} else if (value == "2") {
					return "普惠奖";
				}
			}
		}, {
			field : 'prizeName',
			title : '奖品名称',
			width : 100,
			hidden : true
		}, {
			field : 'prizeContent',
			title : '奖品内容',
			width : 150,
			hidden : false
		}, {
			field : 'realRecpriName',
			title : '实际兑奖人',
			width : 100,
			hidden : false,
			sortable : true
		}, {
			field : 'realRecpriPhone',
			title : '实际兑奖人联系方式',
			width : 125,
			hidden : false,
			sortable : true
		}, {
			field : 'isReceive',
			title : '状态',
			width : 70,
			hidden : false,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == "0") {
					return "未领取";
				} else if (value == "1") {
					return "已领取";
				}
			}
		}, {
			field : 'receiverId',
			title : '接待员工ID',
			width : 100,
			hidden : true
		}, {
			field : 'receiverName',
			title : '接待员工',
			width : 100,
			hidden : false,
			sortable : true
		}, {
			field : 'branchofficeId',
			title : '分公司ID',
			width : 100,
			hidden : true
		}, {
			field : 'branchofficeName',
			title : '分公司名称',
			width : 150,
			hidden : false,
			sortable : true
		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 60,
			hidden : false,
			formatter : operationFN
		} ] ],
		onLoadSuccess : function(data) {

		}
	});
});
function searchPrize() {
	var data = new Object();
	var recpriName = $("#recpriName").val();
	var branchofficeValues = $("#branchofficeIds").combotree("getValues");
	var branchofficeIds = branchofficeValues.join(",");
	var beginTime = $("#beginTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var receiverName = $("#receiverName").val();
	var isReceive = $("#isReceive").combobox("getValue");
	var prizeType = $("#prizeType").combobox("getValue");

	var map = {
		recpriName : recpriName,
		branchofficeIds : branchofficeIds,
		beginTime : beginTime,
		endTime : endTime,
		receiverName : receiverName,
		isReceive : isReceive,
		prizeType : prizeType
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#upriRecordList').datagrid('reload', data);
}
function clearPrize() {
	$("#recpriName").val("");
	$("#branchofficeIds").combotree("setValues", "");
	$("#beginTime").datebox("setValue", "");
	$("#endTime").datebox("setValue", "");
	$("#receiverName").val("");
	$("#isReceive").combobox("setValue", "");
	$("#prizeType").combobox("setValue", "");
}
function openDialog(wxUpriRecordId, upriRecordCode) {
	$("#upriRecordCodeH").val(upriRecordCode);
	$("#wxUpriRecordId").val(wxUpriRecordId);
	$("#receiveForm").form("validate");
	$("#dialog").dialog("open");
}
function closeDialg() {
	$("#dialog").dialog("close");
}
function checkCode() {
	var upriRecordCode = $("#upriRecordCode").val();
	var upriRecordCodeH = $("#upriRecordCodeH").val();
	var info = "请填写兑奖编号！";
	if (upriRecordCode == null || upriRecordCode == "") {
		info = "请填写兑奖编号！";
		$("#spanhh").html("<font style='font-style: italic; color: red;'>" + info + "</font>");
	} else if (upriRecordCode == upriRecordCodeH) {
		$("#isRight").html("<font style='font-style: italic; color: green;'>√</font>");
		$("#spanhh").html("");
	} else {
		info = "请输入正确的兑奖编号！";
		$("#isRight").html("<font style='font-style: italic; color: red;'>×</font>");
		$("#spanhh").html("<font style='font-style: italic; color: red;'>" + info + "</font>");
	}
}
function receive() {
	if ($("#receiveForm").form("validate")) {
		$("#receiveButton").linkbutton("disable");
		$("#cancelReceiveButton").linkbutton("disable");
		ajaxSubmit(serverName + "/prizeManagementController/receivePrize.do", $("#receiveForm").serialize(), function(r) {
			$("#receiveButton").linkbutton("enable");
			$("#cancelReceiveButton").linkbutton("enable");
			$.messager.show({
				showType : "show",
				timeout : 2000,
				title : '消息',
				height : 150,
				width : 300,
				msg : r.msg
			});
			if (r.success) {
				$('#upriRecordList').datagrid("reload");
				$("#dialog").dialog("close");
			}
		});
	}
}
function cancelReceive() {
	closeDialg();
}