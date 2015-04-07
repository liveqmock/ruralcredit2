$(document).ready(function() {

	// 支付方式
	$("#payway").val(($("#payway").val() == "0") ? "转账" : "划扣");
	// 显示tab内容
	$("#allPeopel").tabs({
		fit : true,
		onSelect : function(title) {
			if (title == "担保人") {
				showDanbao();
			} else if (title == "还款计划") {
				showReturnPlan();
			} else if (title == "信用及背景调查报告") {
				showFxInfo();
			} else if (title == "业务跟踪") {
				showOperateLog();
			} else if (title == "申请单附件") {
				showFile();
			} else if (title == "放款登记信息") {
				showGLRView();
			} else if (title == "放款登记附件") {
				showGLRFile();
			} else if (title == "现金流入流出表") {
				viewCashStreamInput();
			} else if (title == "现金流工作表") {
				showCashStreamWorkTableInput();
			} else if (title == "审批单附件") {
				showApproveFile();

			} else if (title == "附件下载") {
				downLoad();
			} else if (title == "附件查看及下载") {
				showFiles();
			}
		}
	});
	showBorrower();
	showCreditApp();
});

function showFiles() {

	$("#allPeopel").tabs("getTab", "附件查看及下载").panel({
		cache : true,
		href : serverName + "/borrowerServiceApp/returnFileViewAndLoadJSP.do?creditapplicationId=" + $("#creditapplicationId").val()

	});

}
/** 放款登记信息 * */
function showGLRView() {
	$("#allPeopel").tabs("getTab", "放款登记信息").panel({
		cache : true,
		href : serverName + "/GroupLoanRegist/searchGLR4View.do?creditapplicationId=" + $("#creditapplicationId").val()
	});
}
/** 显示放款登记附件 * */
function showGLRFile() {
	var loanRegistDESId = $("#loanRegistDESId").val();
	var href = cmUrl + "/operation/transferParameter.action?clientId=" + loanRegistDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
			+ "&signIp=" + DESIp + "";
	$("#allPeopel").tabs("getTab", "放款登记附件").panel({
		cache : true,
		content : "<iframe scrolling='auto' id='openCM1' frameborder='0' src='" + href + "' style='width: 100%; height: 800px;'>"
	});
}
/** 显示审批单附件 * */
function showApproveFile() {
	var approvalDESId = $("#approvalDESId").val();
	var href = cmUrl + "/operation/transferParameter.action?clientId=" + approvalDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
			+ "&signIp=" + DESIp + "";
	$("#allPeopel").tabs("getTab", "审批单附件").panel({
		cache : true,
		content : "<iframe scrolling='auto' id='openCM2' frameborder='0' src='" + href + "' style='width: 100%; height: 800px;'>"
	});
}
/** 显示申请单 * */
function showCreditApp() {
	$("#allPeopel").tabs("getTab", "申请单信息").panel({
		cache : true,
		href : serverName + "/application/showCreditApp.do?borrowerServiceAppId=" + $("#borrowerServiceAppId").val()
	});
}
/** 显示附件 * */
function showFile() {
	var creditapplicationDESId = $("#creditapplicationDESId").val();
	var href = cmUrl + "/operation/transferParameter.action?clientId=" + creditapplicationDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
	$("#allPeopel").tabs("getTab", "申请单附件").panel({
		cache : true,
		content : "<iframe scrolling='auto' id='openCM' frameborder='0' src='" + href + "' style='width: 100%; height: 800px;'>"
	});
}
/** 显示还款计划 * */
function showReturnPlan() {
	$("#returnPlanList").datagrid({
		url : serverName + "/returnPlanController/returnPlanDataGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : false,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
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
					return "已还清";
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
					return "正常";
				} else if ("1" == value) {
					return "逾期";
				} else {
					return "Null";
				}
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
			field : 'receivableMoney',
			title : '应收总金额',
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
			field : 'receivedMoney',
			title : '实收总金额',
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
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
		},
		onHeaderContextMenu : function(e, field) {
// e.preventDefault();
// $('#menu').menu('show', {
// left : e.pageX,
// top : e.pageY
// });
		}

	});
}
/** 显示担保人信息 * */
function showDanbao() {
	$("#danbao").datagrid({
		url : serverName + "/borrowerServiceApp/selectByBorrowerInfoVo.do?leader=0&creditapplicationId=" + $("#creditapplicationId").val(),
		columns : [ [ {
			title : "姓名",
			width : "150",
			field : "name"
		}, {
			title : "性别",
			width : "150",
			field : "gender",
			formatter : function(value, rowData, rowIndex) {
				var text;
				if (value == "0") {
					text = "男";
				} else {
					text = "女";
				}
				return text;
			}
		}, {
			title : "身份证",
			width : "150",
			field : "credentialsNumber"
		}, {
			title : "联系电话",
			width : "150",
			field : "mobilephone"
		}, {
			title : "操作",
			width : "150",
			field : "borrowerServiceAppId",
			formatter : function(value, rowData, rowIndex) {
				return "<a onclick='showGuaranorInfo(" + value + ");'>查看</a>";
			}
		}, {
			title : "附件",
			width : "150",
			field : "borrowerServiceAppDESId",
			formatter : function(value, rowData, rowIndex) {
				var borrowerServiceAppId = rowData.borrowerServiceAppId;
				return "<a onclick='showGuaranorUploadDig(\"" + value + "\");'>查看</a>&nbsp;|&nbsp; " + "<a onclick='downGuaranorUploadDig(\"" + value + "\"," + borrowerServiceAppId + ");'>下载</a>";

			}
		} ] ]
	});
}

/** wyf担保人查看附件 * */
function showGuaranorUploadDig(borrowerServiceAppDESId) {
	var src = cmUrl + "/operation/transferParameter.action?clientId=" + borrowerServiceAppDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
	window.open(src);
}
/**
 * 担保人资料下载
 * 
 * @param {}
 *            borrowerServiceAppDESId
 */
function downGuaranorUploadDig(borrowerServiceAppDESId, borrowerServiceAppId) {
	ajaxSubmit(serverName + "/borrowerServiceApp/checkCM.do", {
		clientId : borrowerServiceAppId
	}, function(r) {
		if (r.success) {
			var href = downloadUrl + "/zipDownload.do?clientId=" + borrowerServiceAppDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
			window.location.href = href;
		} else {
			$.messager.alert("消息", r.msg, "info");
		}
	});

}
/** 显示借款人信息 * */
function showBorrower() {
	$("#borrower").datagrid({
		url : serverName + "/borrowerServiceApp/selectByBorrowerInfoVo.do?leader=1&creditapplicationId=" + $("#creditapplicationId").val(),
		columns : [ [ {
			title : "姓名",
			width : "150",
			field : "name"
		}, {
			title : "性别",
			width : "150",
			field : "gender",
			formatter : function(value) {
				var text;
				if (value == "0") {
					text = "男";
				}if(value == null){
					text="";
				}if(value == "1") {
					text = "女";
				}
				return text;
			}
		}, {
			title : "身份证",
			width : "150",
			field : "credentialsNumber"
		}, {
			title : "联系电话",
			width : "150",
			field : "mobilephone"
		}, {
			title : "申请金额",
			width : "145",
			field : "applyLimit"
		} ] ]
	});
}
/** 显示操作日志 * */
function showOperateLog() {
	$("#businesStrack").datagrid({
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		url : serverName + "/operateLogController/queryOperateLogByCreditId.do?creditAppId=" + $("#creditapplicationId").val(),
		columns : [ [ {
			title : "时间",
			width : fixWidth(0.15),
			field : "operateDate"
		}, {
			title : "操作人",
			width : fixWidth(0.1),
			field : "operatorName"
		}, {
			title : "结果",
			width : fixWidth(0.75),
			field : "functionBussiness"
		} ] ]
	});
}
function fixWidth(percent) {
	return document.body.clientWidth * percent; // 这里你可以自己做调整
}

/** 显示信用及背景调查信息 liuli 2013-05-03 * */
function showFxInfo() {
	$("#allPeopel").tabs("getTab", "信用及背景调查报告").panel({
		cache : true,
		href : serverName + "/creditInvestigation/toViewCreditInvestigation.do?creditInvestigationId=" + $("#copyCreditAd_creditInvestigationId").val()
	});
}

// 查看现金流入和流出表 liuli2013-05-31该查看调用另一个接口，只是查看，不在更新借款人姓名和项目名称
function viewCashStreamInput() {

	// var rows = $("#creditApplicationList").datagrid("getSelected");
	var creditId = $("#creditapplicationId").val();
// alert($("#cashStream"));
	var dd1 = $("#cashStream").dialog({
		title : "现金流入流出表",
		modal : true,
		closed : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		draggable : true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#cashStream").dialog('close');
			}
		} ],
		href : serverName + "/jsp/rc/business/cashStreamInputView.jsp",
		onLoad : function() {
			$("#saveAllCashStreams").attr("disabled", true);
			var paraValue = creditId;
			ajaxSubmit(serverName + "/cashStream/toViewCashStream.do", {
				param : paraValue
			}, function(result) {
				if (result != null) {
					$("#cashStreamForm").form("load", result);
					$("#creatDate").val(result.csOperInTotal.createDate);
					$("#borrowerName").val(result.csOperInTotal.borrowerName);
					$("#saveAllCashStreams").attr("disabled", false);
					calculateEveryEq();
					// 删除加载中提示
					$(".datagrid-mask").remove();
					$(".datagrid-mask-msg").remove();
					// 加载完成后计算一遍 liuli 2013-05-30 因为从excel导入的数据是没有进行计算的
					var maxLoanSumValue = document.getElementById('maxLoanSum').value;
					// 当最大借款额度值为零时才进行计算
					if (maxLoanSumValue == "0") {
						calculateAllDown();
					}
				} else {
					$("#cashStream").dialog('close');
					alert("该用户没有现金流数据!");
				}

// 设置项目内容
				ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {
					section : "cashIncomeFamilyTotal",
					q : ""
				}, function(result) {
					var industryCategoryAll = result;
					for ( var k = 0; k < industryCategoryAll.cashIncomeFamilyTotal.length; k++) {
						var index = industryCategoryAll.cashIncomeFamilyTotal[k].codaTableId;
						var name = "cashIncomeFamilyTotal" + index;
						var objectName = "industryCategoryAll." + name;
						var object = (eval("(" + objectName + ")"));
						for ( var i = 0; i < object.length; i++) {
							if (object[i] != undefined) {
								if (object[i].codeKey == $("#csFamilyInList0projectCodeKey").val()) {
									$("#csFamilyInList0projectCodeKeyShow").val(object[i].codeVlue);
								}
								;
								if (object[i].codeKey == $("#csFamilyInList1projectCodeKey").val()) {
									$("#csFamilyInList1projectCodeKeyShow").val(object[i].codeVlue);
								}
								;
								if (object[i].codeKey == $("#csFamilyInList2projectCodeKey").val()) {
									$("#csFamilyInList2projectName").val(object[i].codeVlue);
								}
								;
								if (object[i].codeKey == $("#csFamilyInList3projectCodeKey").val()) {
									$("#csFamilyInList3projectName").val(object[i].codeVlue);
								}
								;
							}
						}
					}
				});
			});
			ajaxSubmit(serverName + "/cashStream/getProductInfo.do", {
				creditapplicationId : $("#creditapplicationId").val()
			}, function(a) {
				$("#repaymentPlanNamehq2").val(a.repaymentPlanName);
			});

		}

	});
	dd1.dialog('open');
}

// 现金流工作表 liuli
function showCashStreamWorkTableInput() {

	var creditId = $("#creditapplicationId").val();
	var dd1 = $("#cashStream").dialog({
		title : "现金流工作表",
		modal : true,
		closed : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		draggable : true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#cashStream").dialog('close');
			}
		} ],
		href : serverName + "/jsp/rc/business/cashStreamWorkTableInputView.jsp",
		onLoad : function() {
// $("#saveAllCashStreams").attr("disabled",true);
			// 禁用保存按钮
			$("#everyDayOperCsInFormBtn").attr("disabled", true);
			$("#everyWeekOperCsInFormBtn").attr("disabled", true);
			$("#everyMonthOperCsInFormBtn").attr("disabled", true);
			$("#everyCostOperCsInFormBtn").attr("disabled", true);
			$("#avgIncRateFormBtn").attr("disabled", true);
			$("#purchaceCostsFormBtn").attr("disabled", true);
			var paraValue = creditId;
			ajaxSubmit(serverName + "/cashStream/viewCashStreamWorkTable.do", {
				param : paraValue
			}, function(result) {
// $("<div class='datagrid-mask'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
// $("<div class='datagrid-mask-msg'></div>").html("加载中。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190)
// / 2, top: ($(window).height() - 45) / 2 });
				// 填充表单
				if (result != null) {
					$("#everyDayOperCsInForm").form("load", result);
					$("#everyWeekOperCsInForm").form("load", result);
					$("#everyMonthOperCsInForm").form("load", result);
					$("#everyCostOperCsInForm").form("load", result);
					$("#avgIncRateForm").form("load", result);
					$("#purchaceCostsForm").form("load", result);
					// 启用保存按钮
					$("#everyDayOperCsInFormBtn").attr("disabled", false);
					$("#everyWeekOperCsInFormBtn").attr("disabled", false);
					$("#everyMonthOperCsInFormBtn").attr("disabled", false);
					$("#everyCostOperCsInFormBtn").attr("disabled", false);
					$("#avgIncRateFormBtn").attr("disabled", false);
					$("#purchaceCostsFormBtn").attr("disabled", false);
					// 设置采购或进货成本名称
					setProjNameAndAddRate();
					// 删除加载效果
					$(".datagrid-mask").remove();
					$(".datagrid-mask-msg").remove();
				} else {
					$("#cashStream").dialog('close');
					alert("该用户没有现金流工作表数据!");
				}

			});
		}
	});
	dd1.dialog('open');
}

function showGuaranorInfo(id) {
	var borrowerServiceAppId = id;
	// $('#openGuaranor')[0].src = serverName + "/guaranorProfile/guaranorProfileView.do?param=" + borrowerServiceAppId
	var dd1 = $("#guaranorInfo").dialog({
		title : "担保人信息",
		modal : true,
		closed : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		draggable : true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#guaranorInfo").dialog('close');
			}
		} ],
		href : serverName + "/guaranorProfile/guaranorProfileView.do?param=" + borrowerServiceAppId,
		onLoad : function() {
// var paraValue = creditId;
// ajaxSubmit(serverName + "/guaranorProfile/guaranorProfileView.do",{param:borrowerServiceAppId},function(result){
//				
// });
		},
		onClose : function() {
			$('#openGuaranor')[0].src = "";
		}
	});
	dd1.dialog('open');
}

/** 附件下载 * */
function downLoad() {
	var downLoadDIV = $("#downLoadDIV");
	$("#danbaoDiv").show().datagrid({
		title : "担保人资料下载",
		width : 410,
		url : serverName + "/borrowerServiceApp/selectByBorrowerInfoVo.do?leader=0&creditapplicationId=" + $("#creditapplicationId").val(),
		columns : [ [ {
			title : "姓名",
			width : "200",
			field : "name"
		}, {
			title : "资料",
			width : "200",
			field : "",
			formatter : function(value, rowData, rowIndex) {

				return "<a onclick='downGuaranorUploadDig(\"" + value + "\");'><font color='red'>下载</font></a> ";
			}
		} ] ]
	});

	downLoadDIV.show().dialog({

		title : '附件下载',
		width : 600,
		height : 400,
		closed : true,
		cache : false,
		resizable : true,
		modal : true,
		toolbar : [ {
			text : '申请单附件下载',
			iconCls : 'icon-downorange',
			handler : function() {
				var creditapplicationDESId = $("#creditapplicationDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + creditapplicationDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			}
		}, "-", {
			text : '审批单附件下载',
			iconCls : 'icon-downorange',
			handler : function() {
				var approvalDESId = $("#approvalDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + approvalDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			}
		}, "-", {
			text : '放款登记附件下载',
			iconCls : 'icon-downorange',
			handler : function() {
				var loanRegistDESId = $("#loanRegistDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + loanRegistDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			}
		} ]

	});
	downLoadDIV.dialog("open");
};

function openRegistrationDialog(param) {
	ajaxSubmit(serverName + "/accountInfo/checkApplicating.do", {
		creditApplicationId : param
	}, function(r) {
		if (r.success) {
			$("#registrationDialog").dialog({
				modal : true,
				title : "银行卡变更申请",
				maximizable : false,
				cache : false,
				closed : true,
				content : "<iframe scrolling='no' id='iframeTest' name='iframeRegistration' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
				buttons : [ {
					id : "rdButton1",
					text : "保存&提交",
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

			$("#iframeTest").attr('src', serverName + "/accountInfo/returnBankCardJSP.do?creditapplicationId=" + $("#creditapplicationId").val());

			$("#registrationDialog").dialog('open');
		} else {
			$.messager.alert("消息", r.msg, "info");
		}
	});

}
function closeRegistrationDialog() {
	$("#registrationDialog").dialog('close');
}

function buttonDisable() {
	$("#rdButton1").linkbutton('disable');
	$("#rdButton2").linkbutton('disable');
}

function buttonEnable() {
	$("#rdButton1").linkbutton('enable');
	$("#rdButton2").linkbutton('enable');
}

function messagerShow(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}