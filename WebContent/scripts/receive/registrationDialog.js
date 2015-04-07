$(function() {
	wpd = window.parent.document;
	wp = window.parent;
	creditapplicationId = $(wpd).find("input[id='creditapplicationId']").val();
	cardInfo();

	$("#companyCardGrid").datagrid({
		url : "",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : false,
		striped : true,
		idfield : "accountInfoId",
		singleSelect : true,
		columns : [ [ {
			field : 'accountInfoId',
			title : '卡信息ID',
			width : 100,
			hidden : false,
			checkbox : true
		}, {
			field : 'branchName',
			title : '分公司名称',
			width : 100,
			hidden : false
		}, {
			field : 'bankNum',// 这里借用了一下onUsed字段用来显示banknum的字典值
			title : '银行类别',
			width : 100,
			hidden : false
		}, {
			field : 'bankName',
			title : '银行名称',
			width : 100,
			hidden : false
		}, {
			field : 'accountName',
			title : '账户名称',
			width : 100,
			hidden : false
		}, {
			field : 'account',
			title : '账号',
			width : 100,
			hidden : false
		}, {
			field : 'accountType',
			title : '类型',
			width : 100,
			formatter : function(value) {
				if (value == "0") {
					return "分公司账户";
				} else if (value == "1") {
					return "个人账户";
				} else {
					return "Null";
				}
			},
			hidden : false
		} ] ],
		onSelect : function(rowIndex, rowData) {
			var aii = rowData.accountInfoId;
			$("#accountInfoId").val(aii);
		}
	});
	$("#personalCardGrid").datagrid({
		url : "",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : false,
		striped : true,
		idfield : "accountInfoId",
		singleSelect : true,
		columns : [ [ {
			field : 'accountInfoId',
			title : '卡信息ID',
			width : 100,
			hidden : false,
			checkbox : true
		}, {
			field : 'branchName',
			title : '分公司名称',
			width : 100,
			hidden : false
		}, {
			field : 'bankNum',// 这里借用了一下onUsed字段用来显示banknum的字典值
			title : '银行类别',
			width : 100,
			hidden : false
		}, {
			field : 'bankName',
			title : '银行名称',
			width : 100,
			hidden : false
		}, {
			field : 'accountName',
			title : '账户名称',
			width : 100,
			hidden : false
		}, {
			field : 'account',
			title : '账号',
			width : 100,
			hidden : false
		}, {
			field : 'accountType',
			title : '类型',
			width : 100,
			formatter : function(value) {
				if (value == "0") {
					return "分公司账户";
				} else if (value == "1") {
					return "个人账户";
				} else {
					return "Null";
				}
			},
			hidden : false
		} ] ],
		onSelect : function(rowIndex, rowData) {
			var aii = rowData.accountInfoId;
			$("#accountInfoId").val(aii);
		}
	});
	$("#capitalSource").combobox({
		required : true,
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "A",
			"text" : "个人卡划扣",
			"selected" : true
		}, {
			"id" : "C",
			"text" : "现金"
		}, {
			"id" : "R",
			"text" : "客户汇款"
		} ],
		onSelect : function(record) {
			// getSelected
			if (record.id == "A") {
				$("#cardTabs").tabs("select", "个人卡划扣");
			} else {
				$("#cardTabs").tabs("select", "现金还款");
			}

			if (record.id == "C") {
				$("#site0").show();
				$("#site1").show();
				$("#place").combobox({
					required : true,
					valueField : 'id',
					textField : 'text',
					editable : false,
					data : [ {
						"id" : "0",
						"text" : "营业部"
					}, {
						"id" : "1",
						"text" : "客户家"
					}, {
						"id" : "2",
						"text" : "约定地点"
					} ]
				});
			} else {
				$("#place").combobox({
					required : false
				});
				$("#site0").hide();
				$("#site1").hide();
			}
		}
	});
	$("#registrationForm").form('validate');

	$("#cardTabs").tabs({
		onSelect : function(title) {
			$("#accountInfoId").val("");
			if (title == "个人卡划扣") {
				$("#companyCardGrid").datagrid('unselectAll');
			} else if (title == "现金还款") {
				$("#personalCardGrid").datagrid('unselectAll');
			} else {
				alert("cardTabs,onSelect出错！");
			}
		}

	});
	$(".tabs-inner").bind("click", function() {
		return false;
	});
	$("#receivedTime").datebox({
		onSelect : function(record) {
			// if ($("#receivedType").val() == "1" || $("#receivedType").val() == null) {
			queryReturnAmount(record);
			// }
		}
	});
	queryReturnAmount(null);
});

function save() {
	// 验证必填项
	// ajax请求
	// 保存成功关闭dialog
	if ($("#registrationForm").form('validate')) {
		if ($("#accountInfoId").val() != "" && $("#accountInfoId").val() != null) {
			// 验证是否是一次性提前还款
			if ($("#receivedType").val() == "1") {
				if ($("#total").val() == $("#receivedAmount").val()) {
					wp.buttonDisable();
					ajaxSubmit(serverName + "/receivablesRegistration/save.do", $("#registrationForm").serialize(), function(b) {
						wp.buttonEnable();
						if (b) {
							wp.messagerShow("登记成功！");
							wp.closeRegistrationDialog();
						} else {
							wp.messagerShow("登记失败！");
						}

					});
				} else {
					$.messager.alert("消息", "收款金额与应还金额不一致！", "warning");
				}
			} else {
				wp.buttonDisable();
				ajaxSubmit(serverName + "/receivablesRegistration/save.do", $("#registrationForm").serialize(), function(b) {
					wp.buttonEnable();
					if (b) {
						wp.messagerShow("登记成功！");
						wp.closeRegistrationDialog();
					} else {
						wp.messagerShow("登记失败！");
					}

				});
			}
		} else {
			$.messager.alert("消息", "请选择一张卡！", "warning");
		}
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
	}
}
function cardInfo() {
	ajaxSubmit(serverName + "/returnPlanController/cardInfoGrid.do", {
		creditapplicationId : creditapplicationId
	}, function(r) {
		// console.info(r);
		analysisCard(r);
	});

}
function analysisCard(cardList) {
	var length = cardList.length;
	var companyCard = new Array();
	var personalCard = new Array();
	for ( var i = 0; i < length; i++) {
		var accountType = cardList[i].accountType;
		if ("0" == accountType) {
			// 公司账户
			companyCard.push(cardList[i]);
		} else if ("1" == accountType) {
			// 个人账户
			personalCard.push(cardList[i]);
		}
	}
	// 生成公司账户和个人账户grid
	appendCardRows(companyCard, personalCard);
}
function appendCardRows(companyCard, personalCard) {
	for ( var i = 0; i < companyCard.length; i++) {
		$("#companyCardGrid").datagrid('appendRow', {
			accountInfoId : companyCard[i].accountInfoId,
			bankNum : companyCard[i].onUsed,
			branchName : companyCard[i].branchName,
			bankName : companyCard[i].bankName,
			accountName : companyCard[i].accountName,
			account : companyCard[i].account,
			accountType : companyCard[i].accountType
		});
	}
	for ( var i = 0; i < personalCard.length; i++) {
		$("#personalCardGrid").datagrid('appendRow', {
			accountInfoId : personalCard[i].accountInfoId,
			bankNum : personalCard[i].onUsed,
			branchName : personalCard[i].branchName,
			bankName : personalCard[i].bankName,
			accountName : personalCard[i].accountName,
			account : personalCard[i].account,
			accountType : personalCard[i].accountType

		});
	}
	if (personalCard.length == 0) {
		$("#noCard").show();
	}
	if (companyCard.length == 0) {
		$("#noCapCard").show();
	}
}
function textCount(textId, htmlId, max) {
	$("#" + textId).attr("readonly", false);
	$("#" + textId).attr("style", "width: 100%;");
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
		$("#" + textId).val("点击添加备注……");
		$("#" + textId).attr("style", "width: 100%; font-style: italic; color: gray;");
		$("#" + textId).attr("readonly", "readonly");
		$("#" + htmlId).html("");
	}
}
// 初始化时去调贷后接口查询还款金额
function queryReturnAmount(record) {
	var s = new Date();
	if (record == null || record == "") {
	} else {
		s = new Date(record);
	}
	var y = s.getFullYear();
	var m = s.getMonth() + 1;
	var d = s.getDate();
	var paramDate = y + "-" + m + "-" + d;
	ajaxSubmit(serverName + "/returnPlanController/queryReturnAmount.do", {
		creditapplicationId : creditapplicationId,
		ifPayAhead : $("#receivedType").val(),
		registReturnTime : paramDate
	}, function(r) {
		if (r.retCode != "0") {
			$.messager.alert("消息", r.retInfo + "！", "warning", function() {
				wp.closeRegistrationDialog();
			});

		}
		$("#amountForm").form("load", r);
	});
}