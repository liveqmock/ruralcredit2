$(function() {
	creditAppId = undefined;
	paramType = undefined;
	param0 = undefined;
	var dic = new DataDictionary();
	dic.addDic("businessType", "businessType");
	dic.addDic("defaultReturnmentWay", "defaultReturnmentWay");
	dic.addDic("cardFlag", "cardFlag");
	dic.addDic("onUsed", "onUsed");
	dic.addDic("bankNum", "bankNum");
	dic.addDic("useType", "useType");
	dic.dicAjax();
	showCityCombo("provinceId1", "cityId1", "districtId1");
	companyCard = new Array();
	personalCard = new Array();

	$("#useType").combobox({
		onSelect : function(record) {
			if (record.codeKey == "0") {
				$.messager.alert("消息", "此处不能选付款！", "warning", function() {
					$("#useType").combobox('clear');
				});
				// alert("此处不能选付款！");

			}
		}
	});
	$("#bankNum").combobox({
		onSelect : function(record) {
			$("#bankRank").val(record.codeVlue);
		}
	});
	$("#onUsed").combobox({
		onSelect : function(record) {
			if (record.codeKey == "0") {
				$.messager.alert("消息", "此处不能选停用！", "warning", function() {
					$("#onUsed").combobox('clear');
				});
				// alert("此处不能选停用！");

			}
		}
	});
	$("#departmentId").combobox({
		url : serverName + "/accountInfo/getDepartmentList.do",
		textField : 'departmentName',
		valueField : 'departmentId',
		mode : 'remote',
		delay : 500,
		width : 150,
		value : '',
		onSelect : function() {
			$("#branchNameForm").val($("#departmentId").combobox("getText"));
		}
	});
	$("#receivablesList").datagrid({
		url : serverName + "/receivablesList/receivablesDataGrid.do" + conditions,
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
		frozenColumns:[[{
			field : 'changeOperation',
			title : '变更操作',
			width : 200,
			hidden : false,
			formatter : changeOperation

		}]],
		columns : [ [ {
			field : 'creditapplicationId',
			title : '信贷申请单ID',
			width : 100,
			hidden : true
		}, {
			field : 'accountInfoId',
			title : '公司财务账号表ID',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanId',
			title : '还款方案编码',
			width : 100,
			hidden : true
		}, {
			field : 'contactNumber',
			title : '合同编号',
			width : 100,
			hidden : true
		}, {
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
			field : 'address',
			title : '所在村镇',
			width : 200,
			hidden : true
		}, {
			field : 'loanOfficerId',
			title : '信贷员ID',
			width : 100,
			hidden : true
		}, {
			field : 'loanOfficerName',
			title : '信贷员姓名',
			width : 100,
			hidden : false
		}, {
			field : 'businessType',
			title : '业务类型',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "分公司";
				} else if ("1" == value) {
					return "个人";
				} else {
					return "Null";
				}
			}
		}, {
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
			field : 'customerConsultId',
			title : '客户咨询主键',
			width : 100,
			hidden : true
		}, {
			field : 'departmentId',
			title : '客户经理的部门id',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanName',
			title : '产品类型',
			width : 100,
			hidden : false
		}, {
			field : 'registrationOperation',
			title : '登记操作',
			width : 200,
			hidden : false,
			formatter : registrationOperation
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
// deleteRowFromReceivablesDataGrid();
// passHistoryData = data;
// var dataRows = data.rows;
// processReceivablesDataGrid(dataRows, 0);
		}
	});
});
function jump(param, businessNumber, early) {
	// window.location = serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + param;
	var centerj = window.parent;
	if ("0" == early) {
		centerj.addTabFun({
			src : serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + param + "&early=" + early + "&businessNumber=" + businessNumber,
			title : "业务单号为" + businessNumber + "的还款登记"
		});
	} else if ("1" == early) {
		// 当前为一次性还款登记去检测三种情况1.有无在途资金2.有无逾期3.有无半期
		ajaxSubmit(serverName + "/receivablesList/checkForEarlyReturn.do", {
			creditapplicationId : param
		}, function(r) {
			if (r.success) {
				centerj.addTabFun({
					src : serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + param + "&early=" + early + "&businessNumber=" + businessNumber,
					title : "业务单号为" + businessNumber + "的一次性提前还款登记"
				});
			} else {
				$.messager.alert("消息", r.msg, "warning");
			}

		});
	} else {
		alert("early未取到");
	}

}

function doSearch(param) {
	if (param == 0) {
		$('#receivablesList').datagrid('load', {
			fuzzyValue : $("#fuzzy").val()
		});
	} else if (param == 1) {
		var s = $('#defaultReturnmentWay').combobox('getValue');
		$('#receivablesList').datagrid('load', {
			groupNumber : $('#searchform input[name=groupNumber]').val(),
			groupName : $('#searchform input[name=groupName]').val(),
			businessType : $('#businessType').combobox('getValue'),
			defaultReturnmentWay : $('#defaultReturnmentWay').combobox('getValue')
		});
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
function sss() {
// $("#ooos").parent();
// $("#PopContainer", parent.document);
	var ct = $(window.parent.document).find("#centerTabs");

	var sc = ct.tabs({});
	// console.info(sc);
	alert(typeof (sc));
	alert(sc);

}
function change(paramId, param) {
	ajaxSubmit(serverName + "/receivablesRegistration/checkOnWay.do", {
		creditapplicationId : paramId
	}, function(r) {
		var rKey = undefined;
		var rValue = new Array();
		$.each(r, function(key, value) {
			rKey = key;
			rValue = rValue.concat(value);
		});
		if ("0" == rKey) {
			// 没有在途登记
			// 提示更改默认还款方式
			$.messager.confirm("提示", "确定要更改默认还款方式吗？", function(b) {
				if (b) {
// console.info(paramId);
// alert(paramId);
					cardInfo(paramId, param);
					// alert("aaaaaa");
// ajaxSubmit(serverName + "/receivablesList/changeDefaultReturnmentWay.do", {
// creditapplicationId : paramId,
// param : param,
// p : 0
// }, function(r) {
// if (r == true) {
// $("#receivablesList").datagrid('reload');
// $.messager.show({
// showType : "show",
// timeout : 5000,
// title : "消息",
// msg : "更改默认还款方式成功"
// });
// } else {
// $("#receivablesList").datagrid('reload');
// $.messager.show({
// showType : "show",
// timeout : 5000,
// title : "消息",
// msg : "更改默认还款方式失败"
// });
// }
// });
				}
			});
		} else if ("1" == rKey) {
			// 有收款登记但是没有进行预约
			// console.info(rValue);
			// $.messager.confirm("提示", "有在途的收款登记，撤销并更改默认还款方式吗？", function(b) {
			// if (b) {
			// ajaxSubmit("", {
			// creditapplicationId : paramId,
			// param : param,
			// p : 1
			// }, function(r) {
			// });
			// } else {
			// }
			// });
			$.messager.alert("提示", "有在途的收款登记不能更改默认还款方式！", "info", function(b) {
				if (b) {
				}
			});
		} else if ("2" == rKey) {
			// 有预约的收款登记但是可以进行预约撤回
			// console.info(rValue);
			$.messager.alert("提示", "有在途的收款登记不能更改默认还款方式！", "info", function(b) {
				if (b) {
				}
			});
		} else if ("3" == rKey) {
			// 在途登记不能撤回
			// console.info(rValue);
			$.messager.alert("提示", "有在途的收款登记不能更改默认还款方式！", "info", function(b) {
				if (b) {
				}
			});
		} else {
			alert("判断撤销登记出错");
		}
	});
	$("#cardDataGrid").datagrid({
		url : "",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		singleSelect : true,
		columns : [ [ {
			field : 'accountInfoId',
			title : '卡信息ID',
			width : 100,
			hidden : true
		}, {
			field : 'branchName',
			title : '分公司名称',
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
			changeDRW(rowData.accountInfoId);
			$("#cardDialog").dialog('close');
			// 把个人卡传过去
		}
	});
	$("#cardDialog").dialog({
		buttons : [ {
			text : "取消",
			handler : function() {
				$("#cardDialog").dialog('close');
				// $("#defaultBox").combobox('clear');
			}
		} ],
		onBeforeClose : function() {
			// 每次关闭前都销毁dialog中的datagrid
			var dRows = $("#cardDataGrid").datagrid('getRows');
			companyCard.length = 0;
			personalCard.length = 0;
			var lll = dRows.length;
			if (dRows.length == 0) {
			} else {
				for ( var i = 0; i < lll; i++) {
					$("#cardDataGrid").datagrid('deleteRow', 0);
				}
			}
		}
	});
}

function cardInfo(id, param) {
	creditAppId = id;
	paramType = param;
	ajaxSubmit(serverName + "/receivablesRegistration/selectCardInfo.do", {
		creditapplicationId : id
	}, function(r) {
		// console.info(r);
		cardObject = r;
		analysisCardType(id, r, param);
	});
}
function analysisCardType(id, cardList, param) {
	var length = cardList.length;
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
	// tempCard(companyCard, personalCard);
	selectCard(id, param);
}
function selectCard(id, param) {
	if (param == "1") {
		// 要变为个人卡划扣
		// 弹出dialog选择个人卡信息
		if (personalCard.length == 0) {
			$.messager.alert("消息", "没有个人卡信息！请先填写个人卡信息！", "warning", function() {
				// alert("没有个人卡信息！请先填写个人卡信息！");
				// 弹出添加个人卡信息Dialog
				// 查询借款人信息表中的借款人身份证
				ajaxSubmit(serverName + "/receivablesRegistration/borrowServiceApp.do", {
					creditapplicationId : id
				}, function(r) {
					// console.info(r);
					if (r != undefined) {
						$("#accInfoDialog").dialog('open');
						$("#borrowerCredentialsNumber").val(r.credentialsNumber);
						$("#borrowerName").val(r.name);

					} else {
						// $.messager.alert("消息", "借款人信息为空！", "error");
						alert("借款人信息为空！");
					}
				});
			});
		} else {
			$("#cardDialog").dialog('open');
			for ( var i = 0; i < personalCard.length; i++) {
				$("#cardDataGrid").datagrid('appendRow', {
					accountInfoId : personalCard[i].accountInfoId,
					branchName : personalCard[i].branchName,
					bankName : personalCard[i].bankName,
					accountName : personalCard[i].accountName,
					account : personalCard[i].account,
					accountType : personalCard[i].accountType
				});
			}
		}
	} else if (param == "0") {
		// 选择公司卡
		// 弹出dialog选择公司卡信息
		$("#cardDialog").dialog('open');
		for ( var i = 0; i < companyCard.length; i++) {
			$("#cardDataGrid").datagrid('appendRow', {
				accountInfoId : companyCard[i].accountInfoId,
				branchName : companyCard[i].branchName,
				bankName : companyCard[i].bankName,
				accountName : companyCard[i].accountName,
				account : companyCard[i].account,
				accountType : companyCard[i].accountType
			});
		}
	} else {
		// alert("defaultBox onSelect JS 出错了");
	}

}

function doSaveCard() {
	if ($("#accountInfo").form('validate')) {
		ajaxSubmit(serverName + "/receivablesRegistration/addPersonalCard.do", $("#accountInfo").serialize(), function(r) {
			if (r != undefined) {
				// console.info(r);
				// $.messager.alert("消息", "个人卡信息添加成功！", "info");
				alert("个人卡信息添加成功！");
				// 关闭dialog
				$("#accInfoDialog").dialog('close');
				// 个人卡id插入页面
				personalCard.push(r);
				// 显示个人卡信息
				$("#cardDialog").dialog('open');
				for ( var i = 0; i < personalCard.length; i++) {
					$("#cardDataGrid").datagrid('appendRow', {
						accountInfoId : personalCard[i].accountInfoId,
						branchName : personalCard[i].branchName,
						bankName : personalCard[i].bankName,
						accountName : personalCard[i].accountName,
						account : personalCard[i].account,
						accountType : personalCard[i].accountType
					});
				}
			} else {
				$.messager.alert("消息", "个人卡信息添加失败！", "error");
				// alert("个人卡信息添加失败！");
			}
		});
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		// alert("请填写完整信息！");
	}
}
function closeAccDialog() {
	$("#accountInfo").form('clear');
	$("#accInfoDialog").dialog('close');
	$("#defaultBox").combobox('clear');
}

function changeDRW(accInfoId) {
	ajaxSubmit(serverName + "/receivablesList/changeDefaultReturnmentWay.do", {
		creditapplicationId : creditAppId,
		param : paramType,
		p : 0,
		accountInfoId : accInfoId
	}, function(r) {
		if (r == true) {
			$("#receivablesList").datagrid('reload');
			$.messager.show({
				showType : "show",
				timeout : 5000,
				title : "消息",
				msg : "更改默认还款方式成功"
			});
		} else {
			$("#receivablesList").datagrid('reload');
			$.messager.show({
				showType : "show",
				timeout : 5000,
				title : "消息",
				msg : "更改默认还款方式失败"
			});
		}
	});
}

// //////////////////////////////////////
function showCityCombo(province, city, district) {

	var provinceid = $("#provinceId1").combobox("getValue");
	var province = $("#" + province).combobox({
		// required : true,
		editable : false,
		url : serverName + '/NSC/list.do',
		textField : 'cityName',
		valueField : 'cityCode',
		mode : 'remote',
		delay : 500,
		width : '150',
		value : provinceid,
		onSelect : function(value) {
			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				width : '150',
				value : '',
				onSelect : function(value) {
					$("#" + district).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						width : '150',
						delay : 500,
						value : ''
					});
				}
			});
		}
	});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId = $("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : ciryId
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : districtId
		});
	}

}

function tiaozhuanxinyemian() {
	// /ruralcredit2/WebContent/jsp/rc/receivables/newReceivablesList.jsp
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/jsp/rc/receivables/newReceivablesList.jsp",
		title : "（新）收款列表"
	});
}
// {
// var paramId = rowData.creditapplicationId;
// return "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='jump(" + paramId + ");'><font color='blue'>收款登记</font>"
// + "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='pre(" + paramId + ");'><font color='red'>一次性还款登记</font>";
// }

// function addTabFun(opts) {
// var options = $.extend({
// title : '',
// content : 'asdad',
// closable : true,
// iconCls : '',
// fit : true
// }, opts);
// if (!ct.tabs('exists', options.title)) {
// ct.tabs('add', options);
// } else {
// ct.tabs('select', options.title);
// }
// // tabClose();
// };
// function addTabFun2(opts) {
// var options = $.extend({
// title : '',
// content : '<iframe marginwidth="0" marginheight="0" src="' + opts.src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
// closable : true,
// iconCls : '',
// fit : true
// }, opts);
// if (!centerTabs.tabs('exists', options.title)) {
// centerTabs.tabs('add', options);
// } else {
// centerTabs.tabs('select', options.title);
// }
// tabClose();
// };
