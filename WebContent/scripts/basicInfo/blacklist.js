$(function() {
	$("#historyFlag").combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "F",
			"text" : "黑名单"
		}, {
			"id" : "T",
			"text" : "已剔除"
		} ],
		editable : false
	});

	/** 初始化黑名单对话框 郝强上传* */
	$("#blacklistDialog").dialog({
		modal : true,
		title : "黑名单",
		maximizable : false,
		cache : false,
		closed : true,
		buttons : [ {
			id : "rdButton1",
			text : "保存",
			iconCls : "icon-ok",
			handler : function() {
				saveBlacklist();
			}

		}, {
			id : "rdButton2",
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$("#blacklistDialog").dialog('close');
			}
		} ],
		onClose : function() {
			$("#blackForm").form('clear');
			$("#blacklistList").datagrid("reload");
			$("#blacklistedReasonSpan").html("");
		},
		onOpen : function() {
			$("#blackForm").form('validate');
		}

	});
	$("#blacklistDialogINSERT").dialog({
		modal : true,
		title : "黑名单",
		maximizable : false,
		cache : false,
		closed : true,
		buttons : [ {
			id : "rdButtonINSERT1",
			text : "保存",
			iconCls : "icon-ok",
			handler : function() {
				saveBlacklistINSERT();
			}

		}, {
			id : "rdButtonINSERT2",
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$("#blacklistDialogINSERT").dialog('close');
			}
		} ],
		onClose : function() {
			$("#show").html("");
			$("#blackFormINSERT").form('clear');
			$("#blacklistedReasonSpanINSERT").html("");
			$("#blacklistList").datagrid("reload");
		},
		onOpen : function() {
			$("#blackFormINSERT").form('validate');
		}

	});
	$("#blacklistList").datagrid({
		url : serverName + "/blacklistController/blacklistDateGrid.do",
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
			title : 'blacklistId',
			field : 'blacklistId',
			width : 130,
			hidden : true
		}, {
			title : '客户姓名',
			field : 'blackName',
			width : 130,
			hidden : false
		}, {
			title : '身份证号',
			field : 'blackCredentialsNumber',
			width : 130,
			hidden : false
		}, {
			title : '拉黑时间',
			field : 'blacklistedTime',
			width : 130,
			hidden : false
		}, {
			title : '拉黑操作人姓名',
			field : 'blacklistedOperatorName',
			width : 130,
			hidden : false
		}, {
			title : '拉黑原因',
			field : 'blacklistedReason',
			width : 130,
			hidden : false
		}, {
			title : '剔除的时间',
			field : 'removeTime',
			width : 130,
			hidden : false
		}, {
			title : '剔除的操作人姓名',
			field : 'removeOperatorName',
			width : 130,
			hidden : false
		}, {
			title : '剔除的原因',
			field : 'removeReason',
			width : 130,
			hidden : false
		}, {
			title : '历史标识',
			field : 'historyFlag',
			width : 130,
			hidden : false,
			formatter : function(value) {
				if ("F" == value) {
					return "黑名单";
				} else if ("T" == value) {
					return "已剔除";
				} else {
					return "Null";
				}
			}
		}, {
			title : '借款人姓名',
			field : 'name',
			width : 130,
			hidden : true
		}, {
			title : '证件类型',
			field : 'credentialsType',
			hidden : true,
			width : 150,
			formatter : function(value) {
				if (value == 1) {
					return '身份证';
				}
				if (value == 2) {
					return '驾照';
				}
				if (value == 3) {
					return '护照';
				}
				if (value == 4) {
					return '户口本';
				}
				if (value == 5) {
					return '临时身份证';
				}
			}
		}, {
			title : '身份证号',
			field : 'credentialsNumber',
			width : 200,
			hidden : true
		}, {
			title : '性别',
			field : 'gender',
			width : 80,
			formatter : function(value) {
				if (value == "0") {
					return "男";
				}
				if (value == "1") {
					return "女";
				}
			},
			hidden : false
		}, {
			title : '电话',
			field : 'mobilephone',
			width : 150,
			hidden : true
		}, {
			title : '操作人姓名',
			field : 'operateName',
			width : 170,
			hidden : true
		}, {
			title : '操作人id',
			field : 'operateId',
			width : 100,
			hidden : true
		}, {
			title : '固话',
			field : 'telephone',
			width : 150,
			hidden : true
		}, {
			title : '住址',
			field : 'presentAddress',
			width : 300,
			hidden : false
		}, {
			title : '借款人状态',
			field : 'auditStatus',
			width : 140,
			hidden : true,
			formatter : function(value) {
				if ($.trim(value) == "00") {
					return "未提交";
				} else if ($.trim(value) == "01") {
					return "<font>待审批</font>";
				} else if ($.trim(value) == "02") {
					return "审查通过";
				} else if ($.trim(value) == "03") {
					return "<font>审查拒绝</font>";
				} else if ($.trim(value) == "04") {
					return "审批通过";
				} else if ($.trim(value) == "05") {
					return "<font>审批补充资料</font>";
				} else if ($.trim(value) == "06") {
					return "放款失败登记";
				} else if ($.trim(value) == "07") {
					return "<font>撤回</font>";
				} else if ($.trim(value) == "08") {
					return "推迟放款";
				} else if ($.trim(value) == "09") {
					return "<font>已付款</font>";
				} else if ($.trim(value) == "9") {
					return "<font>已付款</font>";
				} else if ($.trim(value) == "10") {
					return "款项到位";
				} else if ($.trim(value) == "11") {
					return "<font>已放款登记</font>";
				} else if ($.trim(value) == "12") {
					return "放款失败-推迟";
				} else if ($.trim(value) == "13") {
					return "<font>放款确认</font>";
				} else if ($.trim(value) == "14") {
					return "放款确认退回";
				} else if ($.trim(value) == "15") {
					return "<font>还款中</font>";
				} else if ($.trim(value) == "16") {
					return "还款完成";
				} else if ($.trim(value) == "17") {
					return "<font>审批变更额度</font>";
				} else if ($.trim(value) == "18") {
					return "<font>审批拒绝</font>";
				} else if ($.trim(value) == "19") {
					return "提前还款登记";
				} else if ($.trim(value) == "20") {
					return "<font>提前还款完成</font>";
				} else if ($.trim(value) == "21") {
					return "<font>放款额度确认</font>";
				} else if ($.trim(value) == "22") {
					return "<font>客户经理提交</font>";
				} else if ($.trim(value) == "23") {
					return "<font>风险经理提交</font>";
				} else if ($.trim(value) == "24") {
					return "审批中";
				} else if ($.trim(value) == "25") {
					return "审批中";
				} else {
					return "<font >未申请</font>";
				}
			}
		}, {
			title : '借款人状态',
			field : 'auditStatusShow',
			width : 100,
			hidden : true
		}, {
			title : '民族',
			field : 'national',
			width : 100,
			hidden : true
		}, {
			title : '民族',
			field : 'nationalDetail',
			width : 100,
			hidden : false
		}, {
			title : '户籍地址',
			field : 'residenceAddress',
			width : 100,
			hidden : true
		}, {
			title : '个人收入',
			field : 'personIncome',
			width : 100,
			hidden : true
		}, {
			title : '最高学历',
			field : 'highestEducation',
			width : 100,
			hidden : true
		}, {
			title : '是借款人',
			field : 'customerType',
			width : 100,
			formatter : function(value) {
				if (value == '0') {
					return "是";
				} else {
					return "不是";
				}
			},
			hidden : false
		}, {
			title : '是担保人',
			field : 'guaranor',
			width : 100,
			formatter : function(value) {
				if (value == '0') {
					return "是";
				} else {
					return "不是";
				}
			},
			hidden : false
		}, {
			title : '最高学历',
			field : 'highestEducationDetail',
			width : 100,
			hidden : true
		}, {
			title : '邮寄地址',
			field : 'residenceAddress',
			width : 100,
			hidden : true
		}, {
			title : '备注',
			field : 'remark',
			width : 100,
			hidden : true
		}, {
			title : '婚姻状态',
			field : 'maritalStatus',
			width : 100,
			hidden : true
		}, {
			title : '婚姻状态',
			field : 'maritalStatusDetail',
			width : 100,
			hidden : false
		}, {
			field : 'vallageId',
			width : 100,
			title : "村id",
			hidden : true
		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				var historyFlag = rowData.historyFlag;
				var name = rowData.blackName;
				var credentialsNumber = rowData.blackCredentialsNumber;
				var blacklistId = rowData.blacklistId;
				if (historyFlag == "F") {
					return "<a href='javascript:void(0)' onclick='remove(\"" + name + "\",\"" + credentialsNumber + "\",\"" + blacklistId + "\");'>剔除黑名单<a>";
				} else if (historyFlag == "T") {
					return "无操作";
				} else {
					return "无操作";
				}
			}
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
		}
	});
});
function doSearch(param) {
	var data = new Object();
	if (param == 0) {
		var map = {
			fuzzyValue : $("#fuzzy").val()
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#blacklistList').datagrid('load', data);
	} else if (param == 1) {
		var historyFlag = $("#historyFlag").combobox('getValue');
		var map = {
			blackCredentialsNumber : $("#blackCredentialsNumber").val(),
			blackName : $("#blackName").val(),
			historyFlag : historyFlag
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#blacklistList').datagrid('load', data);
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
function doClear(param) {
	if (param == 0) {
		$('#fuzzy').val("");
	} else if (param == 1) {
		$("#searchform").form('clear');
	} else {
		$.messager.alert('提示', "doClearRepayment的JS出错了！", 'error');
	}
}
/** 郝强添加 加入黑名单功能* */
function remove(name, credentialsNumber, blacklistId) {
	if (name == "null" || name == null) {
		name = "";
	}
	$("#blackNameA").val(name);
	$("#blackCredentialsNumberA").val(credentialsNumber);
	$("#blacklistId").val(blacklistId);
	$("#blacklistDialog").dialog("open");
}

function textCount(textId, htmlId, max) {
	$("#" + textId).attr("readonly", false);
	if ($("#" + textId).val() == "点击添加备注……") {
		$("#" + textId).val("");
	}
	$("#" + textId).keyup(function() {
		var maxl = max;
		var tishi = "还可以输入" + maxl + "个字";
		$("#" + htmlId).html(tishi);
		var xianyou = $(this).val().length;
		var keyi = maxl - xianyou;
		var tishi = "还可以输入" + keyi + "个字";
		if (xianyou > (max - 1)) {
			var tishi = "还可以输0个字";
			$("#" + htmlId).css({
				"color" : "red"
			});
			var s = $("#" + textId).val().substr(0, 100);
			$("#" + textId).val();
		} else {
			$("#" + htmlId).css({
				"color" : "#000"
			});
		}
		$("#" + htmlId).html(tishi);
	});
}
function checkTextArea(textId, htmlId) {
	if ($("#" + textId).val() == "" || $("#" + textId).val() == null) {
		$("#" + textId).val("请添加加入黑名单原因……");
		$("#" + textId).attr("style", "width: 100%; font-style: italic; color: gray;");
		$("#" + textId).attr("readonly", "readonly");
		$("#" + htmlId).html("");
	}
}
function checkTextAreaINSERT(textId, htmlId) {
	if ($("#" + textId).val() == "" || $("#" + textId).val() == null) {
		$("#" + textId).val("请添加剔除黑名单原因……");
		$("#" + textId).attr("style", "width: 100%; font-style: italic; color: gray;");
		$("#" + textId).attr("readonly", "readonly");
		$("#" + htmlId).html("");
	}
}
// 保存黑名单
function saveBlacklist() {
	if ($("#blackForm").form('validate')) {
		$("#rdButton1").linkbutton("disable");
		$("#rdButton2").linkbutton("disable");
		ajaxSubmit(serverName + "/blacklistController/remove.do", $("#blackForm").serialize(), function(r) {
			$("#rdButton1").linkbutton("enable");
			$("#rdButton2").linkbutton("enable");
			if (r.success) {
				$("#blacklistDialog").dialog('close');
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : "剔除黑名单成功！"
				});
			} else {
				$.messager.alert("消息", r.msg, "error", function() {
					$.messager.show({
						showType : "show",
						timeout : 5000,
						title : "消息",
						msg : "剔除黑名单失败！"
					});
				});
			}
		});
	} else {
		$.messager.alert("消息", "请将数据填写完整！", "warning");
	}
}

function saveBlacklistINSERT() {
	if ($("#blackFormINSERT").form('validate')) {
		if ($("#check").val() == "0") {
			$("#rdButtonINSERT1").linkbutton("disable");
			$("#rdButtonINSERT2").linkbutton("disable");
			ajaxSubmit(serverName + "/blacklistController/blacklisted.do", $("#blackFormINSERT").serialize(), function(r) {
				$("#rdButtonINSERT1").linkbutton("enable");
				$("#rdButtonINSERT2").linkbutton("enable");
				if (r.success) {
					$("#blacklistDialogINSERT").dialog('close');
					$.messager.show({
						showType : "show",
						timeout : 5000,
						title : "消息",
						msg : "保存黑名单成功！"
					});
				} else {
					$.messager.alert("消息", r.msg, "error", function() {
						$.messager.show({
							showType : "show",
							timeout : 5000,
							title : "消息",
							msg : "保存黑名单失败！"
						});
					});
				}
			});
		} else {
			$.messager.alert("消息", "此身份证已经存在于黑名单中！", "warning");
		}
	} else {
		$.messager.alert("消息", "请将数据填写完整！", "warning");
	}
}

function checkIdNumber(param) {
	if (validNumberAll(param)) {
		$("#rdButtonINSERT1").linkbutton("disable");
		$("#rdButtonINSERT2").linkbutton("disable");
		ajaxSubmit(serverName + "/blacklistController/checkIdNumber.do", {
			credentialsNumber : param
		}, function(r) {
			$("#rdButtonINSERT1").linkbutton("enable");
			$("#rdButtonINSERT2").linkbutton("enable");
			if (r.success) {
				$("#show").html("<font style='font-style: italic; color: green;'>" + r.msg + "</font>");
				$("#check").val(0);
			} else {
				$("#show").html("<font style='font-style: italic; color: red;'>" + r.msg + "</font>");
				$("#check").val("");
			}
		});
	}
}