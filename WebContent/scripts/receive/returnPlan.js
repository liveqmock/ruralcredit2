$(function() {
	tempData = undefined;
	$("#registrationDialog").dialog({
		modal : true,
		title : "收款登记",
		maximizable : false,
		cache : false,
		closed : true,
		content : "<iframe scrolling='no' id='iframeTest' name='iframeRegistration' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
		buttons : [ {
			id : "rdButton1",
			text : "登记",
			iconCls : "icon-ok",
			handler : function() {
				iframeRegistration.window.save();
			}

		}, {
			id : "rdButton2",
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$("#registrationDialog").dialog('close');
			}
		} ],
		onClose : function() {
			$("#iframeTest").attr('src', "");
		}

	});

	$("#returnPlanGrid").datagrid({
		url : serverName + "/returnPlanController/returnPlanDataGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : false,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		pageSize : 20,
		pageList : [ 5, 10, 20 ],
		queryParams : {
			creditapplicationId : $("#creditapplicationId").val()
		},
		columns : [ [ {
			field : 'period',
			title : '期数',
			width : 100,
			hidden : true
		}, {
			field : 'repayDate',
			title : '还款日期',
			width : 100,
			hidden : false
		}, {
			field : 'isReturned',
			title : '还款状态',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "未还清";
				} else if ("1" == value) {
					return "<font style='font-weight:bolder;color: green;'>已还清</font>";
				} else {
					return value;
				}
			}
		}, {
			field : 'isOverdue',
			title : '逾期状态',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "<font>正常</font>";
				} else if ("1" == value) {
					return "<font color='red'>逾期</font>";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'receivableMoney',
			title : '应收总金额',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				var receivableMoney = rowData.receivableMoney;
				var receivedMoney = rowData.receivedMoney;
				if (receivableMoney == receivedMoney) {
					return "<font style='font-weight:bolder;color: green;'>" + value.toFixed(2) + "</font>";
				} else {
					return "<font color='blue'>" + value.toFixed(2) + "</font>";
				}

			}
		}, {
			field : 'receivedMoney',
			title : '实收总金额',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				var receivableMoney = rowData.receivableMoney;
				var receivedMoney = rowData.receivedMoney;
				if (receivableMoney == receivedMoney) {
					return "<font style='font-weight:bolder;color: green;'>" + value.toFixed(2) + "</font>";
				} else {
					return "<font color='red'>" + value.toFixed(2) + "</font>";
				}
				return value.toFixed(2);
			}
		}, {
			field : 'receivablePrincipal',
			title : '应收本金',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'receivableInterest',
			title : '应收利息',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'fQFWFReceivableCharge',
			title : '应收服务费',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'zNJReceivableCharge',
			title : '应收滞纳金',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'fXReceivableCharge',
			title : '应收罚息',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'receivedPrincipal',
			title : '实收本金',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'receivedInterest',
			title : '实收利息',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'fQFWFReceivedCharge',
			title : '实收服务费',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'zNJReceivedCharge',
			title : '实收滞纳金',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'fXReceivedCharge',
			title : '实收罚息',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'receivedlft',
			title : '当月应收滞纳金罚息合计',
			width : 100,
			hidden : false
		}, {
			field : 'receivablelft',
			title : '当月实收滞纳金罚息合计',
			width : 100,
			hidden : false
		}, {
			field : 'receivableMarrearage',
			title : '当月欠款',
			width : 100,
			hidden : false
		}, {
			field : 'receivableArrearage',
			title : '截至当月累计欠款',
			width : 100,
			hidden : false
		}, {
			field : 'overDays',
			title : '逾期天数',
			width : 100,
			hidden : false
		}, {
			field : 'dForfeit',
			title : '减免罚息',
			width : 100,
			hidden : false
		}, {
			field : 'dLatefee',
			title : '减免滞纳金',
			width : 100,
			hidden : false
		}, {
			field : 'fDate',
			title : '罚息截止时间',
			width : 100,
			hidden : false
		}, {
			field : 'lDate',
			title : '滞纳金截止时间',
			width : 100,
			hidden : false
		}, {
			field : 'dReason',
			title : '减免原因',
			width : 100,
			hidden : false
		}, {
			field : 'nDate',
			title : '减免时间',
			width : 100,
			hidden : false
		}, {
            field : 'canUseReturnButton',
            title : '是否可用一次性还款登记按钮',
            width : 100,
            hidden :   true,
            formatter:function(value,data,index){
                var canUseReturnButton = data.canUseReturnButton;
				var needRemindReceived = data.needRemindReceived;
               // console.info(canUseReturnButton+"-----------canUseReturnButton-------");
               // console.info($("#returnAllOnceButton").linkbutton+"-----------returnAllOnceButton.linkbutton-------");
                if((canUseReturnButton) && ($.trim(canUseReturnButton) == "false") && ($("#returnAllOnceButton").linkbutton)) {
                    $("#returnAllOnceButton").linkbutton("disable");
                }
				if((needRemindReceived) && ($.trim(needRemindReceived) == "true")) {
					$("#needRemindReceived").val("true");
				}
            }
        } ] ],
		onLoadSuccess : function(data) {
		},
		onHeaderContextMenu : function(e, field) {
		}
	});

});

function refresh() {
	$("#returnPlanGrid").datagrid("reload");
}

function openRegistrationDialog(param) {
	var needRemindReceived = $("#needRemindReceived").val();
	console.info("needRemindReceived:"+needRemindReceived);
	if(param == 0){
		if(needRemindReceived && "true"==needRemindReceived){
			$.messager.confirm('预约确认', '本期还款已还清，是否确定预约?', function(r){
				if (r){
					$("#iframeTest").attr('src', serverName + "/returnPlanController/registrationDialog.do?creditapplicationId=" + $("#creditapplicationId").val() + "&receivedType=" + param);
					$("#registrationDialog").dialog('open');
				}else{
					return;
				}
			});
		}else{
			$("#iframeTest").attr('src', serverName + "/returnPlanController/registrationDialog.do?creditapplicationId=" + $("#creditapplicationId").val() + "&receivedType=" + param);
			$("#registrationDialog").dialog('open');
		}

	}else{
		$("#iframeTest").attr('src', serverName + "/returnPlanController/registrationDialog.do?creditapplicationId=" + $("#creditapplicationId").val() + "&receivedType=" + param);
		$("#registrationDialog").dialog('open');
	}
}
function closeRegistrationDialog() {
	$("#registrationDialog").dialog('close');
}
function buttonEnable() {
	$("#rdButton1").linkbutton('enable');
	$("#rdButton2").linkbutton('enable');
}
function buttonDisable() {
	$("#rdButton1").linkbutton('disable');
	$("#rdButton2").linkbutton('disable');
}
function messagerShow(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}
function jumpTOGatheringAppointment() {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/jsp/rc/receivables/gatheringAppointment.jsp",
		title : "收款预约列表"
	});
}
// 1.1
function testClientApply() {
	$("#testClientApply").linkbutton("disable");
	ajaxSubmit(serverName + "/ruralBusyController/testClientApply.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function(r) {
		$.messager.alert("消息", r.msg, "info");
		$("#returnPlanGrid").datagrid("reload");
		$("#testClientApply").linkbutton("enable");
	});
}
// 1.2
function testReturnScheme() {
	console.info($("#creditapplicationId").val());
	alert($("#creditapplicationId").val());
	ajaxSubmit(serverName + "/ruralBusyController/testReturnScheme.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function() {
	});
}
// 1.3
function testOverdueInfo() {
	console.info($("#creditapplicationId").val());
	alert($("#creditapplicationId").val());
	ajaxSubmit(serverName + "/ruralBusyController/testOverdueInfo.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function() {
	});
}
function testReturnAmount() {
	console.info($("#creditapplicationId").val());
	alert($("#creditapplicationId").val());
	ajaxSubmit(serverName + "/ruralBusyController/testReturnAmount.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function() {
	});
}
// 1.3
function testReserveReturn() {
	console.info(33372);
	alert("receivedRecordId为：33374");
	ajaxSubmit(serverName + "/ruralBusyController/testReserveReturn.do", {
		receivedRecordId : 33374
	}, function(r) {
	});
}
// 1.8
function testQyReserveSearch() {
	console.info(33372);
	alert("receivedRecordId为：33374");
	ajaxSubmit(serverName + "/ruralBusyController/testQyReserveSearch.do", {
		receivedRecordId : 33374
	}, function(r) {
	});
}

function jumpToQyClientApplyJsp() {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/ruralBusyController/jumpToQyClientApplyJsp.do?bussesNumber=" + $("#groupNumber").val(),
		title : "查询借款人信息"
	});
}

function jumpToQyReserveSearch() {
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/jsp/rc/receivables/showQyReserveSearch.jsp",
		title : "查询预约划扣结果"
	});
}
