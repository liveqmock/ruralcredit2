/**
 * ★━━━━━━━━━━━━━━━━━━是初始化━━━━━━━━━━━━━━━━━━━━★
 */
$(function() {

	passHistoryData = undefined;
	comboType = undefined;
	isItem1 = false;
	isItem2 = false;
	isItem3 = false;
	isItem4 = false;
	// 信贷申请对象
	creditAppObject = undefined;
	// 卡信息List
	cardObject = undefined;
	iDList = new Array();
	companyCard = new Array();
	personalCard = new Array();
	cardCount = 0;
	cardId = undefined;
	// tempPendingData = undefined;
	// 允许输入的最大金额！
	maxAmount = 0;
	// 正常还款OR提前还款
	normalOrEarly = $("#normalOrEarly").val();
	// 查询默认还款方式
	selectDefault();
	onWay = true;
	// 备注用——用来计算输入字符
	textCount();
	// 设置一下当前时间
	nowDate();
	// 显示还款类型
	showNormalOrEarly();
	/**
	 * ★━━━━━━━━━━━━━━━━━━━combobox始化部分━━━━━━━━━━━━━━━━━━━━★
	 */
	showCityCombo("provinceId1", "cityId1", "districtId1");
	var dic = new DataDictionary();
	dic.addDic("defaultBox_defaultBox_Y", "defaultRepaymentType");
	dic.addDic("cardFlag_cardFlag_Y", "cardFlag");
	dic.addDic("onUsed_onUsed_Y", "onUsed");
	dic.addDic("bankNum_bankNum_Y", "bankNum");
	dic.addDic("useType_useType_Y", "useType");
	dic.dicAjax();
	$("#iForm").form('validate');
	$("#useType").combobox({
		onSelect : function(record) {
			if (record.codeKey == "0") {
				// $.messager.alert("消息", "此处不能选付款！", "warning");
				alert("此处不能选付款！");
				$("#useType").combobox('clear');
				$("#useType").combobox('setValue', "");
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
				// $.messager.alert("消息", "此处不能选停用！", "warning");
				alert("此处不能选停用！");
				$("#onUsed").combobox('clear');
				$("#onUsed").combobox('setValue', "");
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

	$("#defaultBox").combobox({
		onSelect : function(record) {
			// 清空iCombobox
			// 清空地点
			$("#iCombobox").combobox('clear');
			$("#iCombobox").combobox('setValue', "");
			$("#locationSpan").html("");
			if (record.codeKey == "0") {
				// 选择个人卡划扣
				// 弹出dialog选择个人卡信息
				if (personalCard.length == 0) {
					// $.messager.alert("消息", "没有个人卡信息！请先填写个人卡信息！", "warning");
					alert("没有个人卡信息！请先填写个人卡信息！");
					// 弹出添加个人卡信息Dialog
					// 查询借款人信息表中的借款人身份证
					ajaxSubmit(serverName + "/receivablesRegistration/borrowServiceApp.do", {
						creditapplicationId : creditAppObject.creditapplicationId
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
			} else if (record.codeKey == "1") {
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
	});

	$("#iCombobox").combobox({
		required : true,
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "A",
			"text" : "个人卡划扣"
		}, {
			"id" : "C",
			"text" : "现金"
		}, {
			"id" : "R",
			"text" : "客户汇款"
		} ],
		onSelect : function(record) {
			var va = $("#defaultBox").combobox('getValue');
			if (va == null || va == "") {
				$.messager.alert("消息", "请先选择当期还款方式！", "warning", function() {
					$("#iCombobox").combobox('clear');
					$("#iCombobox").combobox('setValue', "");
				});
			} else {
				if (record.id == "A") {
					$("#locationSpan").html("");
					if (va == "0") {
					} else {
						$.messager.alert("消息", "资金来源不能选择个人卡划扣！", "warning", function() {
							$("#iCombobox").combobox('clear');
							$("#iCombobox").combobox('setValue', "");
						});
					}
				} else if (record.id == "C") {
					if (va == "0") {
						$.messager.alert("消息", "资金来源只能选择个人卡划扣！", "warning", function() {
							$("#iCombobox").combobox('clear');
							$("#iCombobox").combobox('setValue', "");
						});
					} else {
						$("#locationSpan").html("地点：<input id='place' name='place' type='text' style='width: 154px;'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						$("#place").combobox({
							required : true,
							valueField : 'id',
							textField : 'text',
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
					}
				} else if (record.id == "R") {
					$("#locationSpan").html("");
					if (va == "0") {
						$.messager.alert("消息", "资金来源只能选择个人卡划扣！", "warning", function() {
							$("#iCombobox").combobox('clear');
							$("#iCombobox").combobox('setValue', "");
						});
					} else {
					}
				}
			}
		}
	});

	/**
	 * ★━━━━━━━━━━━━━━━━━━combobox始化部分END━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * ★━━━━━━━━━━━━━━━━━━━dialog始化部分━━━━━━━━━━━━━━━━━━━━━━★
	 */

	$("#distributionDialog").dialog({
		onBeforeOpen : function() {
			// 每次打开前都要校验输入项
			// 校验form
			// 校验金额不能超过最大值
			// 检验金额不能是0
			// 校验备注是否保存了
			// 都成功了才会打开
			var isOpen = false;
			if ($("#iForm").form('validate')) {
				var value = $("#receivedBox").numberbox('getValue');
				var type = $("#normalOrEarly").val();
				if (type == "0") {
					if (value > 0 && value <= maxAmount) {
						isOpen = true;
					} else {
						// $.messager.alert("消息", "输入的金额不对！", "warning");
						alert("输入的金额不对！");
						isOpen = false;
					}
				} else if (type == "1") {
					if (value == maxAmount) {
						isOpen = true;
					} else {
						// $.messager.alert("消息", "输入的金额不对！", "warning");
						alert("输入的金额不对！");
						isOpen = false;
					}
				} else {
					isOpen = false;
				}
			}
			return isOpen;
		},
		onBeforeClose : function() {
			// 每次关闭前都销毁dialog中的datagrid
			var dRows = $("#distributionDataGird").datagrid('getRows');
			var lll = dRows.length;
			if (dRows.length == 0) {
			} else {
				for ( var i = 0; i < lll; i++) {
					$("#distributionDataGird").datagrid('deleteRow', 0);
				}
			}

		},
		onOpen : function() {
			// 打开后 取 输入金额 取 还款类型 取提前还款类型
			ajaxSubmit(serverName + "/receivablesRegistration/distributorController.do", $("#iForm").serialize(), function(r) {
				// console.info(r);
				appenddistributionDataGirdRow(r);
			});

		}

	});
	/**
	 * ★━━━━━━━━━━━━━━━━━━dialog始化部分END━━━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * ★━━━━━━━━━━━━━━━━━━━menu始化部分━━━━━━━━━━━━━━━━━━━━━━★
	 */
	$("#menu").menu({
		onClick : function(item) {
			var item1 = $(this).menu('findItem', "显示应收款项");
			var item2 = $(this).menu('findItem', "显示减免款项");
			var item3 = $(this).menu('findItem', "显示实收款项");
			var item4 = $(this).menu('findItem', "显示提前还款款项");
			var id = item.id;
			if (id == "item1") {
				isItem1 = !isItem1;
			} else if (id == "item2") {
				isItem2 = !isItem2;
			} else if (id == "item3") {
				isItem3 = !isItem3;
			} else if (id == "item4") {
				isItem4 = !isItem4;
			} else {
				alert("menu选择语句JS出错");
			}
			iconCls = item.iconCls;
			if (iconCls == "icon-empty") {
				$(this).menu('setIcon', {
					target : item.target,
					iconCls : "icon-ok"
				});
			} else {
				$(this).menu('setIcon', {
					target : item.target,
					iconCls : "icon-empty"
				});
			}
			if (isItem1) {
				// 显示应收款项receivablesDataGrid
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonPrincipal');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonInterest');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonManageFree');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonPenalty');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonLaterFree');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currAccountTotal');

				$("#receivablesDataGrid").datagrid('showColumn', 'currMonPrincipal');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonInterest');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonManageFree');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonPenalty');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonLaterFree');
				$("#receivablesDataGrid").datagrid('showColumn', 'currAccountTotal');

// $("#distributionDataGird").datagrid('showColumn', 'currMonPrincipal');
// $("#distributionDataGird").datagrid('showColumn', 'currMonInterest');
// $("#distributionDataGird").datagrid('showColumn', 'currMonManageFree');
// $("#distributionDataGird").datagrid('showColumn', 'currMonPenalty');
// $("#distributionDataGird").datagrid('showColumn', 'currMonLaterFree');
// $("#distributionDataGird").datagrid('showColumn', 'currAccountTotal');

			} else {
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonPrincipal');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonInterest');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonManageFree');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonPenalty');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonLaterFree');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currAccountTotal');

				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonPrincipal');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonInterest');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonManageFree');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonPenalty');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonLaterFree');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currAccountTotal');

// $("#distributionDataGird").datagrid('hideColumn', 'currMonPrincipal');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonInterest');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonManageFree');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonPenalty');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonLaterFree');
// $("#distributionDataGird").datagrid('hideColumn', 'currAccountTotal');
			}
			if (isItem2) {
				// 显示减免款项
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReducePrincipal');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReduceInterest');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReduceManageFree');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReducePenalty');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReduceLaterFree');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currReduceTotal');

				$("#receivablesDataGrid").datagrid('showColumn', 'currReducePrincipal');
				$("#receivablesDataGrid").datagrid('showColumn', 'currReduceInterest');
				$("#receivablesDataGrid").datagrid('showColumn', 'currReduceManageFree');
				$("#receivablesDataGrid").datagrid('showColumn', 'currReducePenalty');
				$("#receivablesDataGrid").datagrid('showColumn', 'currReduceLaterFree');
				$("#receivablesDataGrid").datagrid('showColumn', 'currReduceTotal');

// $("#distributionDataGird").datagrid('showColumn', 'currReducePrincipal');
// $("#distributionDataGird").datagrid('showColumn', 'currReduceInterest');
// $("#distributionDataGird").datagrid('showColumn', 'currReduceManageFree');
// $("#distributionDataGird").datagrid('showColumn', 'currReducePenalty');
// $("#distributionDataGird").datagrid('showColumn', 'currReduceLaterFree');
// $("#distributionDataGird").datagrid('showColumn', 'currReduceTotal');
			} else {
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReducePrincipal');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReduceInterest');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReduceManageFree');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReducePenalty');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReduceLaterFree');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currReduceTotal');

				$("#receivablesDataGrid").datagrid('hideColumn', 'currReducePrincipal');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currReduceInterest');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currReduceManageFree');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currReducePenalty');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currReduceLaterFree');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currReduceTotal');

// $("#distributionDataGird").datagrid('hideColumn', 'currReducePrincipal');
// $("#distributionDataGird").datagrid('hideColumn', 'currReduceInterest');
// $("#distributionDataGird").datagrid('hideColumn', 'currReduceManageFree');
// $("#distributionDataGird").datagrid('hideColumn', 'currReducePenalty');
// $("#distributionDataGird").datagrid('hideColumn', 'currReduceLaterFree');
// $("#distributionDataGird").datagrid('hideColumn', 'currReduceTotal');
			}
			if (isItem3) {
				// 显示实收款项
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currPaindinCapital');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currPaindinInterest');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currPaindinManageFree');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonPaidPenalty');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currLateFreePaid');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'currMonPayTotal');

				$("#receivablesDataGrid").datagrid('showColumn', 'currPaindinCapital');
				$("#receivablesDataGrid").datagrid('showColumn', 'currPaindinInterest');
				$("#receivablesDataGrid").datagrid('showColumn', 'currPaindinManageFree');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonPaidPenalty');
				$("#receivablesDataGrid").datagrid('showColumn', 'currLateFreePaid');
				$("#receivablesDataGrid").datagrid('showColumn', 'currMonPayTotal');

// $("#distributionDataGird").datagrid('showColumn', 'currPaindinCapital');
// $("#distributionDataGird").datagrid('showColumn', 'currPaindinInterest');
// $("#distributionDataGird").datagrid('showColumn', 'currPaindinManageFree');
// $("#distributionDataGird").datagrid('showColumn', 'currMonPaidPenalty');
// $("#distributionDataGird").datagrid('showColumn', 'currLateFreePaid');
// $("#distributionDataGird").datagrid('showColumn', 'currMonPayTotal');
			} else {
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currPaindinCapital');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currPaindinInterest');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currPaindinManageFree');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonPaidPenalty');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currLateFreePaid');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'currMonPayTotal');

				$("#receivablesDataGrid").datagrid('hideColumn', 'currPaindinCapital');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currPaindinInterest');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currPaindinManageFree');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonPaidPenalty');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currLateFreePaid');
				$("#receivablesDataGrid").datagrid('hideColumn', 'currMonPayTotal');

// $("#distributionDataGird").datagrid('hideColumn', 'currPaindinCapital');
// $("#distributionDataGird").datagrid('hideColumn', 'currPaindinInterest');
// $("#distributionDataGird").datagrid('hideColumn', 'currPaindinManageFree');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonPaidPenalty');
// $("#distributionDataGird").datagrid('hideColumn', 'currLateFreePaid');
// $("#distributionDataGird").datagrid('hideColumn', 'currMonPayTotal');
			}
			if (isItem4) {
				// 显示实收一次性提前还款项
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'receEarlyDamages');
				$("#receivablesHistoryDataGrid").datagrid('showColumn', 'paidEarlyDamages');

				$("#receivablesDataGrid").datagrid('showColumn', 'receEarlyDamages');
				$("#receivablesDataGrid").datagrid('showColumn', 'paidEarlyDamages');

				$("#distributionDataGird").datagrid('showColumn', 'receEarlyDamages');
				$("#distributionDataGird").datagrid('showColumn', 'paidEarlyDamages');
			} else {
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'receEarlyDamages');
				$("#receivablesHistoryDataGrid").datagrid('hideColumn', 'paidEarlyDamages');

				$("#receivablesDataGrid").datagrid('hideColumn', 'receEarlyDamages');
				$("#receivablesDataGrid").datagrid('hideColumn', 'paidEarlyDamages');

				$("#distributionDataGird").datagrid('hideColumn', 'receEarlyDamages');
				$("#distributionDataGird").datagrid('hideColumn', 'paidEarlyDamages');
			}
		}
	});
	/**
	 * ★━━━━━━━━━━━━━━━━━━menu始化部分END━━━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * ★━━━━━━━━━━━━━━━━━━当期应还列表初始化部分━━━━━━━━━━━━━━━━━━━━★
	 */

	$("#receivablesDataGrid").datagrid({
		url : "",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : false,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		columns : [ [ {
			field : 'returnPlanId',
			title : '还款计划主键',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentDate',
			title : '应还款日期',
			width : 100
		}, {
			field : 'collectionStatus',
			title : '还款状态',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if (value == "0") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">未还款</font>';
					}
				} else if (value == "1") {
					return '<font color="green">已还款</font>';
				} else if (value == "2") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">部分还款</font>';
					}
				} else if (value == "3") {
					return '<font color="6633CC">不用还款</font>';
				} else {
					return '<font color="gray">Null</font>';
				}
			}
		}, {
			field : 'actualReceivablePrincipal',
			title : '实际应收本金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPrincipal = rowData.currMonPrincipal;
				var currReducePrincipal = rowData.currReducePrincipal;
				return accAdd(currMonPrincipal, currReducePrincipal).toFixed(2);
			}
		}, {
			field : 'actualReceivableInterest',
			title : '实际应收利息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonInterest = rowData.currMonInterest;
				var currReduceInterest = rowData.currReduceInterest;
				return accAdd(currMonInterest, currReduceInterest).toFixed(2);
			}
		}, {
			field : 'actualReceivableManageFree',
			title : '实际应收服务费',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonManageFree = rowData.currMonManageFree;
				var currReduceManageFree = rowData.currReduceManageFree;
				return accAdd(currMonManageFree, currReduceManageFree).toFixed(2);
			}
		}, {
			field : 'actualReceivablePenalty',
			title : '实际应收罚息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPenalty = rowData.currMonPenalty;
				var currReducePenalty = rowData.currReducePenalty;
				return accAdd(currMonPenalty, currReducePenalty).toFixed(2);
			}
		}, {
			field : 'actualReceivableLaterFree',
			title : '实际应收滞纳金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonLaterFree = rowData.currMonLaterFree;
				var currReduceLaterFree = rowData.currReduceLaterFree;
				return accAdd(currMonLaterFree, currReduceLaterFree).toFixed(2);
			}
		}, {
			field : 'actualReceivableTotal',
			title : '实际应收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				return accAdd(temp, receEarlyDamages).toFixed(2);
			}
		}, {
			field : 'currMonPrincipal',
			title : '当期应收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonInterest',
			title : '当期应收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonManageFree',
			title : '当期应收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPenalty',
			title : '当期应收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonLaterFree',
			title : '当期应收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currAccountTotal',
			title : '当期应收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePrincipal',
			title : '当期减免本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceInterest',
			title : '当期减免利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceManageFree',
			title : '当期减免服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePenalty',
			title : '当期减免罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceLaterFree',
			title : '当期减免滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceTotal',
			title : '当期减免总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinCapital',
			title : '当期实收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinInterest',
			title : '当期实收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinManageFree',
			title : '当期实收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPaidPenalty',
			title : '当期实收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currLateFreePaid',
			title : '当期实收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPayTotal',
			title : '当期实收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'realMonPayTotal',
			title : '当期实际实收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages).toFixed(2);
			},
			hidden : false
		}, {
			field : 'currNotTotal',
			title : '当期未还总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp1 = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				var temp2 = accAdd(temp1, receEarlyDamages);
				var currMonPayTotal = rowData.currMonPayTotal;
				var realMonPayTotal = accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages);
				return accAdd(temp2, -realMonPayTotal).toFixed(2);
			},
			hidden : false
		}, {
			field : 'overdueFlag',
			title : '逾期标识',
			width : 100,
			hidden : true
		}, {
			field : 'receEarlyDamages',
			title : '应收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'paidEarlyDamages',
			title : '实收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		} ] ],
		onHeaderContextMenu : function(e, field) {
			e.preventDefault();
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});

	/**
	 * ★━━━━━━━━━━━━━━━━━━当期应还列表初始化部分END━━━━━━━━━━━━━━━━━━━━★
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━还款历史列表初始化部分━━━━━━━━━━━━━━━━━━━━★
	 */

	$("#receivablesHistoryDataGrid").datagrid({
		url : serverName + "/receivablesRegistration/receivablesHistoryDataGrid.do",
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
			creditapplicationId : $("#caId").val()
		},
		columns : [ [ {
			field : 'returnPlanId',
			title : '还款计划主键',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentDate',
			title : '应还款日期',
			width : 100
		}, {
			field : 'collectionStatus',
			title : '还款状态',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if (value == "0") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">未还款</font>';
					}
				} else if (value == "1") {
					return '<font color="green">已还款</font>';
				} else if (value == "2") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">部分还款</font>';
					}
				} else if (value == "3") {
					return '<font color="6633CC">不用还款</font>';
				} else {
					return '<font color="gray">Null</font>';
				}
			}
		}, {
			field : 'actualReceivablePrincipal',
			title : '实际应收本金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPrincipal = rowData.currMonPrincipal;
				var currReducePrincipal = rowData.currReducePrincipal;
				return accAdd(currMonPrincipal, currReducePrincipal).toFixed(2);
			}
		}, {
			field : 'actualReceivableInterest',
			title : '实际应收利息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonInterest = rowData.currMonInterest;
				var currReduceInterest = rowData.currReduceInterest;
				return accAdd(currMonInterest, currReduceInterest).toFixed(2);
			}
		}, {
			field : 'actualReceivableManageFree',
			title : '实际应收服务费',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonManageFree = rowData.currMonManageFree;
				var currReduceManageFree = rowData.currReduceManageFree;
				return accAdd(currMonManageFree, currReduceManageFree).toFixed(2);
			}
		}, {
			field : 'actualReceivablePenalty',
			title : '实际应收罚息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPenalty = rowData.currMonPenalty;
				var currReducePenalty = rowData.currReducePenalty;
				return accAdd(currMonPenalty, currReducePenalty).toFixed(2);
			}
		}, {
			field : 'actualReceivableLaterFree',
			title : '实际应收滞纳金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonLaterFree = rowData.currMonLaterFree;
				var currReduceLaterFree = rowData.currReduceLaterFree;
				return accAdd(currMonLaterFree, currReduceLaterFree).toFixed(2);
			}
		}, {
			field : 'actualReceivableTotal',
			title : '实际应收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				return accAdd(temp, receEarlyDamages).toFixed(2);
			}
		}, {
			field : 'currMonPrincipal',
			title : '当期应收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonInterest',
			title : '当期应收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonManageFree',
			title : '当期应收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPenalty',
			title : '当期应收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonLaterFree',
			title : '当期应收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currAccountTotal',
			title : '当期应收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePrincipal',
			title : '当期减免本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceInterest',
			title : '当期减免利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceManageFree',
			title : '当期减免服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePenalty',
			title : '当期减免罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceLaterFree',
			title : '当期减免滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceTotal',
			title : '当期减免总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinCapital',
			title : '当期实收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinInterest',
			title : '当期实收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinManageFree',
			title : '当期实收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPaidPenalty',
			title : '当期实收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currLateFreePaid',
			title : '当期实收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPayTotal',
			title : '当期实收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'realMonPayTotal',
			title : '当期实际实收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages).toFixed(2);
			},
			hidden : false
		}, {
			field : 'currNotTotal',
			title : '当期未还总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp1 = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				var temp2 = accAdd(temp1, receEarlyDamages);
				var currMonPayTotal = rowData.currMonPayTotal;
				var realMonPayTotal = accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages);
				return accAdd(temp2, -realMonPayTotal).toFixed(2);
			},
			hidden : false
		}, {
			field : 'remark',
			title : '备注',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if (value == null || value == "") {
					return "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='addRemark(" + rowData.returnPlanId + ",\"" + rowData.remark + "\");'><font color='blue'>添加备注</font></a>";
				} else {
					// console.info(rowData);
					return "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='showRemark(" + rowData.returnPlanId + ",\"" + rowData.remark + "\");'><font color='green'>查看备注</font></a>";
					// return "<a onclick='showRemark(\"" + rowData.remark + "\");'>查看</a>";
					// return "<a href='javascript:void(0);' onclick='jump(" + paramId + ");'><font color='blue'>收款登记</font></a>";
				}
			},
			hidden : false
		}, {
			field : 'overdueFlag',
			title : '逾期标识',
			width : 100,
			hidden : true
		}, {
			field : 'receEarlyDamages',
			title : '应收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'paidEarlyDamages',
			title : '实收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		} ] ],
		onLoadSuccess : function(data) {
			deleteRowFromReceivablesDataGrid();
			passHistoryData = data;
			var dataRows = data.rows;
			processReceivablesDataGrid(dataRows, normalOrEarly);
		},
		onHeaderContextMenu : function(e, field) {
			e.preventDefault();
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}

	});
	/**
	 * ★━━━━━━━━━━━━━━━━━━还款历史列表初始化部分END━━━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * ★━━━━━━━━━━━━━━━━━━实收分配列表初始化部分━━━━━━━━━━━━━━━━━━━━★
	 */

	$("#distributionDataGird").datagrid({
		url : "",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		columns : [ [ {
			field : 'returnPlanId',
			title : '还款计划主键',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentDate',
			title : '应还款日期',
			width : 100
		}, {
			field : 'collectionStatus',
			title : '还款状态',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if (value == "0") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">未还款</font>';
					}
				} else if (value == "1") {
					return '<font color="green">已还款</font>';
				} else if (value == "2") {
					if (rowData.overdueFlag == "1") {
						return '<font color="red">逾期</font>';
					} else {
						return '<font color="0099FF">部分还款</font>';
					}
				} else if (value == "3") {
					return '<font color="6633CC">不用还款</font>';
				} else {
					return '<font color="gray">Null</font>';
				}
			}
		}, {
			field : 'actualReceivablePrincipal',
			title : '实际应收本金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPrincipal = rowData.currMonPrincipal;
				var currReducePrincipal = rowData.currReducePrincipal;
				return accAdd(currMonPrincipal, currReducePrincipal).toFixed(2);
			}
		}, {
			field : 'actualReceivableInterest',
			title : '实际应收利息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonInterest = rowData.currMonInterest;
				var currReduceInterest = rowData.currReduceInterest;
				return accAdd(currMonInterest, currReduceInterest).toFixed(2);
			}
		}, {
			field : 'actualReceivableManageFree',
			title : '实际应收服务费',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonManageFree = rowData.currMonManageFree;
				var currReduceManageFree = rowData.currReduceManageFree;
				return accAdd(currMonManageFree, currReduceManageFree).toFixed(2);
			}
		}, {
			field : 'actualReceivablePenalty',
			title : '实际应收罚息',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonPenalty = rowData.currMonPenalty;
				var currReducePenalty = rowData.currReducePenalty;
				return accAdd(currMonPenalty, currReducePenalty).toFixed(2);
			}
		}, {
			field : 'actualReceivableLaterFree',
			title : '实际应收滞纳金',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currMonLaterFree = rowData.currMonLaterFree;
				var currReduceLaterFree = rowData.currReduceLaterFree;
				return accAdd(currMonLaterFree, currReduceLaterFree).toFixed(2);
			}
		}, {
			field : 'actualReceivableTotal',
			title : '实际应收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				return accAdd(temp, receEarlyDamages).toFixed(2);
			}
		}, {
			field : 'currMonPrincipal',
			title : '当期应收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonInterest',
			title : '当期应收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonManageFree',
			title : '当期应收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPenalty',
			title : '当期应收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonLaterFree',
			title : '当期应收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currAccountTotal',
			title : '当期应收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePrincipal',
			title : '当期减免本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceInterest',
			title : '当期减免利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceManageFree',
			title : '当期减免服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReducePenalty',
			title : '当期减免罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceLaterFree',
			title : '当期减免滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currReduceTotal',
			title : '当期减免总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinCapital',
			title : '当期实收本金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinInterest',
			title : '当期实收利息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currPaindinManageFree',
			title : '当期实收服务费',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPaidPenalty',
			title : '当期实收罚息',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currLateFreePaid',
			title : '当期实收滞纳金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'currMonPayTotal',
			title : '当期实收总金额',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'realMonPayTotal',
			title : '当期实际实收总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages).toFixed(2);
			},
			hidden : false
		}, {
			field : 'currNotTotal',
			title : '当期未还总金额',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				var currAccountTotal = rowData.currAccountTotal;
				var currReduceTotal = rowData.currReduceTotal;
				var temp1 = accAdd(currAccountTotal, currReduceTotal);
				var receEarlyDamages = rowData.receEarlyDamages;
				var temp2 = accAdd(temp1, receEarlyDamages);
				var currMonPayTotal = rowData.currMonPayTotal;
				var realMonPayTotal = accAdd(rowData.currMonPayTotal, rowData.paidEarlyDamages);
				return accAdd(temp2, -realMonPayTotal).toFixed(2);
			},
			hidden : false
		}, {
			field : 'overdueFlag',
			title : '逾期标识',
			width : 100,
			hidden : true
		}, {
			field : 'receEarlyDamages',
			title : '应收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		}, {
			field : 'paidEarlyDamages',
			title : '实收提前还款违约金',
			width : 100,
			formatter : function(value) {
				return value.toFixed(2);
			},
			hidden : true
		} ] ],
		onHeaderContextMenu : function(e, field) {
			e.preventDefault();
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});

	/**
	 * ★━━━━━━━━━━━━━━━━━━实收分配列表初始化部分END━━━━━━━━━━━━━━━━━━━━★
	 */

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
			var cbb = rowData.accountInfoId;
			$("#cardId").val(cbb);
			$("#cardDialog").dialog('close');
			showCardArea(rowData);
		}
	});
	$("#cardDialog").dialog({
		buttons : [ {
			text : "取消",
			handler : function() {
				$("#cardDialog").dialog('close');
				$("#defaultBox").combobox('clear');
				$("#defaultBox").combobox('setValue', "");
			}
		} ],
		onBeforeClose : function() {
			// 每次关闭前都销毁dialog中的datagrid
			var dRows = $("#cardDataGrid").datagrid('getRows');
			var lll = dRows.length;
			if (dRows.length == 0) {
			} else {
				for ( var i = 0; i < lll; i++) {
					$("#cardDataGrid").datagrid('deleteRow', 0);
				}
			}
		}
	});
});

/**
 * ★━━━━━━━━━━━━━━━━━━初始化END━━━━━━━━━━━━━━━━━━━━★
 */

/**
 * ★━━━━━━━━━━━━━━━━━━这里都是Test方法━━━━━━━━━━━━━━━━━━━━★
 */

function closeDialog() {
	$("#distributionDialog").dialog('close');
}
// 打开前会校验很多规则
function openDialog() {
	$("#distributionDialog").dialog('open');
}
// 预处理还款状态
function processReceivablesDataGrid(dataRows, param) {

	if (param == "0") {
		// 正常还款登记
		// analyzeData(tempPendingData);
		ajaxSubmit(serverName + "/receivablesRegistration/appendNormalRow.do", {
			creditapplicationId : $("#caId").val()
		}, function(r) {
			//console.info(r);
			if (r.success != undefined) {
				$.messager.alert("消息", "appendNormalRow后台报错！", "error");
			} else {
				appendRows(r.returnPlanList);
				computNormalAmount(r.overFlag, r.lastAmout, r.currAmount, r.overAmount, r.biggestRepaymentAmount);
			}
		});

	} else if (param == "1") {
		// 提前还款登记
		// 从后台来处理数据
		ajaxSubmit(serverName + "/receivablesRegistration/appendEarlyRow.do", $("#iForm").serialize(), function(r) {
			//console.info(r);
			if (r.success != undefined) {
				$.messager.alert("消息", "appendEarlyRow后台报错！", "error");
			} else {
				appendRows(r.returnPlanList);
				computEarlyAmount(r.biggestRepaymentAmount, r.total, r.currAmount, r.penalbond);
			}
		});
	} else {
		alert("预处理还款状态JS出错");
	}

}

function computNormalAmount(overdueFlag, totalAmount, currAmount, overdueTotalAmount, biggestRepaymentAmount) {
	maxAmount = biggestRepaymentAmount;
	showComputedAmount(overdueFlag, totalAmount, currAmount, overdueTotalAmount);
}
// 显示还款总金额
function showComputedAmount(overdueFlag, totalAmount, currAmount, overdueTotalAmount) {
	if (overdueFlag) {// showItemArea
		$("#showTotalArea").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>" + totalAmount.toFixed(2) + "&nbsp;RMB</font>");
		$("#showItemArea").html(
				"逾期总应收：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + overdueTotalAmount.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;和&nbsp;&nbsp;当月应收：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + currAmount.toFixed(2) + "&nbsp;RMB</font>&nbsp;&nbsp;");
	} else {
		$("#showTotalArea").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>" + totalAmount.toFixed(2) + "&nbsp;RMB</font>");
		$("#showItemArea").html("当月应收：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + currAmount.toFixed(2) + "&nbsp;RMB</font>&nbsp;&nbsp;");
	}
}

// 防止NaN的出现
function getFloat(param) {
	if (isNaN(parseFloat(param))) {
		param = 0;
	} else {
		param = parseFloat(param);
	}
	return param;
}

// 删除应还datagrid方法
function deleteRowFromReceivablesDataGrid() {
	var dRows = $("#receivablesDataGrid").datagrid('getRows');
	var lll = dRows.length;
	if (dRows.length == 0) {
	} else {
		for ( var i = 0; i < lll; i++) {
			$("#receivablesDataGrid").datagrid('deleteRow', 0);
		}
	}
}

/** 重写这个方法appendRows(analyzedData, normalData, earlyData, overdueFlag) */
function appendRows(analyzedData) {
	for ( var i = 0; i < analyzedData.length; i++) {
		$("#receivablesDataGrid").datagrid('appendRow', {
			repaymentDate : analyzedData[i].repaymentDate,
			collectionStatus : analyzedData[i].collectionStatus,
			currMonPrincipal : analyzedData[i].currMonPrincipal,
			currMonInterest : analyzedData[i].currMonInterest,
			currMonManageFree : analyzedData[i].currMonManageFree,
			currMonPenalty : analyzedData[i].currMonPenalty,
			currMonLaterFree : analyzedData[i].currMonLaterFree,
			currAccountTotal : analyzedData[i].currAccountTotal,
			currPaindinCapital : analyzedData[i].currPaindinCapital,
			currPaindinInterest : analyzedData[i].currPaindinInterest,
			currPaindinManageFree : analyzedData[i].currPaindinManageFree,
			currMonPaidPenalty : analyzedData[i].currMonPaidPenalty,
			currLateFreePaid : analyzedData[i].currLateFreePaid,
			currMonPayTotal : analyzedData[i].currMonPayTotal,
			currReducePrincipal : analyzedData[i].currReducePrincipal,
			currReduceInterest : analyzedData[i].currReduceInterest,
			currReduceManageFree : analyzedData[i].currReduceManageFree,
			currReducePenalty : analyzedData[i].currReducePenalty,
			currReduceLaterFree : analyzedData[i].currReduceLaterFree,
			currReduceTotal : analyzedData[i].currReduceTotal,
			overdueFlag : analyzedData[i].overdueFlag,
			receEarlyDamages : analyzedData[i].receEarlyDamages,
			paidEarlyDamages : analyzedData[i].paidEarlyDamages
		});
	}
}

/** 重写这个方法function computEarlyAmount(analyzedData, normalData, earlyData, overdueFlag) */
/** total-总应收 currAmount-当月应收 penalbond-一次性提前还款产生的违约金 */
function computEarlyAmount(biggestRepaymentAmount, total, currAmount, penalbond) {
	// overdueFlag, all, saveMoney, overdueTotalAmount, currAmount, earlyTotalMoney
	overdueFlag = false;
	overdueTotalAmount = 0;
	maxAmount = biggestRepaymentAmount;
	var earlyTotalMoney = accAdd(total, -currAmount);
	var saveMoney = accAdd(biggestRepaymentAmount, -total);
	showComputedEarlyAmount(overdueFlag, biggestRepaymentAmount, saveMoney, overdueTotalAmount, currAmount, earlyTotalMoney);

}

/**
 * 显示还款总金额 overdueFlag-逾期标识 all-全部应收金额 saveMoney-为您节省了的金额 overdueTotalAmount-违约总金额 currAmount-当期应还总金额 earlyTotalMoney-一次性提前还款涉及金额
 */
function showComputedEarlyAmount(overdueFlag, all, saveMoney, overdueTotalAmount, currAmount, earlyTotalMoney) {
	if (overdueFlag) {// showItemArea
		$("#showTotalArea").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>" + all.toFixed(2) + "&nbsp;RMB</font>");
		$("#showItemArea").html(
				"逾期总应收金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + overdueTotalAmount.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;、&nbsp;&nbsp;当月应收金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + currAmount.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;、&nbsp;&nbsp;一次性还款涉及的应收金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + earlyTotalMoney.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;、&nbsp;&nbsp;为您节省的金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + saveMoney.toFixed(2) + "&nbsp;RMB</font>&nbsp;&nbsp;");
	} else {
		$("#showTotalArea").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>" + all.toFixed(2) + "&nbsp;RMB</font>");
		$("#showItemArea").html(
				"当月应收金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + currAmount.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;、&nbsp;&nbsp;一次性还款涉及的应收金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + earlyTotalMoney.toFixed(2)
						+ "&nbsp;RMB</font>&nbsp;&nbsp;、&nbsp;&nbsp;为您节省的金额：<font style=' font-size:12px;color:#4b0082;font-weight:bold;'>" + saveMoney.toFixed(2) + "&nbsp;RMB</font>&nbsp;&nbsp;");
	}
}
function appenddistributionDataGirdRow(r) {
	for ( var i = 0; i < r.length; i++) {
		$("#distributionDataGird").datagrid('appendRow', {
			repaymentDate : r[i].repaymentDate,
			collectionStatus : r[i].collectionStatus,
			currMonPrincipal : r[i].currMonPrincipal,
			currMonInterest : r[i].currMonInterest,
			currMonManageFree : r[i].currMonManageFree,
			currMonPenalty : r[i].currMonPenalty,
			currMonLaterFree : r[i].currMonLaterFree,
			currAccountTotal : r[i].currAccountTotal,
			currPaindinCapital : r[i].currPaindinCapital,
			currPaindinInterest : r[i].currPaindinInterest,
			currPaindinManageFree : r[i].currPaindinManageFree,
			currMonPaidPenalty : r[i].currMonPaidPenalty,
			currLateFreePaid : r[i].currLateFreePaid,
			currMonPayTotal : r[i].currMonPayTotal,
			currReducePrincipal : r[i].currReducePrincipal,
			currReduceInterest : r[i].currReduceInterest,
			currReduceManageFree : r[i].currReduceManageFree,
			currReducePenalty : r[i].currReducePenalty,
			currReduceLaterFree : r[i].currReduceLaterFree,
			currReduceTotal : r[i].currReduceTotal,
			overdueFlag : r[i].overdueFlag,
			receEarlyDamages : r[i].receEarlyDamages,
			paidEarlyDamages : r[i].paidEarlyDamages
		});
	}
}
/**
 * ★━━━━━━━━━━━━━━━━━━这里都是Test方法END━━━━━━━━━━━━━━━━━━━★
 */
// 插入测试
function insert() {
	ajaxSubmit(serverName + "/receivablesRegistration/insert.do", function() {
	});
}
function cloneAll(fromObj, toObj) {
	for ( var i in fromObj) {
		if (typeof fromObj[i] == "object") {
			toObj[i] = {};
			cloneAll(fromObj[i], toObj[i]);
			continue;
		}
		toObj[i] = fromObj[i];
	}
}

function doSave() {
	$("#evas").linkbutton('disable');
	ajaxSubmit(serverName + "/receivablesRegistration/save.do", $("#iForm").serialize(), function(b) {
		if (b) {
			// $.messager.alert("消息", "登记成功！", "info");
			alert("登记成功！");
			$("#evas").linkbutton('enable');
			back();
		} else {
			// $.messager.alert("消息", "登记失败！", "error");
			alert("登记失败！");
			$("#evas").linkbutton('enable');
		}

	});
}
// 查询默认还款方式
function selectDefault() {
	ajaxSubmit(serverName + "/receivablesRegistration/selectAppliaction.do", {
		creditapplicationId : $("#caId").val()
	}, function(r) {
		creditAppObject = r;
		// 检测在途资金
		checkOnWay(r.creditapplicationId);
		// 查询卡信息
		cardInfo(r.creditapplicationId);
		if (r.defaultReturnmentWay == "0") {
			$("#defaultArea").html("<font color='blue'>个人卡</font>");
		} else if (r.defaultReturnmentWay == "1") {
			$("#defaultArea").html("<font color='blue'>现金</font>");
		} else {
			alert("selectDefault js 出错");
		}
	});
}
function checkOnWay(id) {
	ajaxSubmit(serverName + "/receivablesRegistration/checkOnWay.do", {
		creditapplicationId : id
	}, function(r) {
		var rKey = undefined;
		var rValue = new Array();
		$.each(r, function(key, value) {
			rKey = key;
			rValue = rValue.concat(value);
		});
		if ("0" == rKey) {
			// 没有在途登记
			onWay = false;
		} else if ("1" == rKey) {
			// 有收款登记但是没有进行预约
			// console.info(rValue);
			onWayAmount("1", rValue);
		} else if ("2" == rKey) {
			// 有预约的收款登记但是可以进行预约撤回
			// console.info(rValue);
			onWayAmount("2", rValue);
		} else if ("3" == rKey) {
			// 在途登记不能撤回
			// console.info(rValue);
			onWayAmount("3", rValue);
		} else {
		}
	});

}
function onWayAmount(key, value) {
	var length = value.length;
	// 共有资金receivedAmountT
	var receivedAmountT = 0;
	for ( var i = 0; i < length; i++) {
		iDList.push(value[i].receivedRecordId);
		receivedAmountT = accAdd(receivedAmountT, value[i].receivedAmount);
	}
	showOnWayArea(key, length, receivedAmountT, iDList);
}
function showOnWayArea(param, count, amount, iDList) {
	if ("0" == param) {
		// 没有在途登记
	} else if ("1" == param) {
		// 有收款登记但是没有进行预约
		$("#onWayArea").html("<font color='red'>您有" + count + "笔在途资金,共" + amount + "RMB,可以撤回</font>&nbsp;&nbsp;&nbsp;&nbsp;<a id='bu' iconCls='icon-cancel' onclick='doCancelR(" + 1 + ");'>撤销登记</a>");
		$("#bu").linkbutton();
	} else if ("2" == param) {
		// 有预约的收款登记但是可以进行预约撤回
		$("#onWayArea").html("<font color='red'>您有" + count + "笔在途资金,共" + amount + "RMB,收款登记已预约但可以撤回</font>");
	} else if ("3" == param) {
		// 在途登记不能撤回
		$("#onWayArea").html("<font color='red'>您有" + count + "笔在途资金,共" + amount + "RMB,不可撤回</font>");
	} else {
	}
}
function doCancelR(param) {
	var data = new Object;
	// console.info(iDList);
	data["iDs"] = JSON.stringify(iDList);
	data["param"] = param;
	ajaxSubmit(serverName + "/receivablesRegistration/appointmentRevoked.do", data, function(r) {
		if (r) {
			// $.messager.alert("消息", "撤销成功！", "info");
			alert("撤销成功！");
			window.location = serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + $("#caId").val();
		} else {
			// $.messager.alert("消息", "撤销失败！", "error");
			alert("撤销失败！");
		}

	});
}
function back() {
	// window.location.href = serverName + "/jsp/rc/receivables/receivablesList.jsp";
	// window.close();
	// $("#distributionDialog").dialog('close');
	// window.location.href = serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + $("#caId").val() +
	// ",early=" + $("#normalOrEarly").val();
	var centerj = window.parent;
	var ty = $("#normalOrEarly").val();
	var chuan = "业务单号为";
	if (ty == "0") {
		chuan = chuan + $("#businessN").val() + "的还款登记";
	} else if (ty == "1") {
		chuan = chuan + $("#businessN").val() + "的一次性提前还款登记";
	}
	centerj.closeTab(chuan);
}
function cardInfo(id) {
	ajaxSubmit(serverName + "/receivablesRegistration/selectCardInfo.do", {
		creditapplicationId : id
	}, function(r) {
		// console.info(r);
		cardObject = r;
		analysisCardType(r);
	});
}
function analysisCardType(cardList) {
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
}
function tempCard(companyCard, personalCard) {
	if (companyCard.length == 1) {

	} else {
		// alert("有多张公司卡");
	}
	if (personalCard.length == 0) {
		cardCount = 0;
	} else if (personalCard.length == 1) {
	}
}
function showCardArea(rowData) {
	var type = rowData.accountType;

	if ("0" == type) {
		// 0代表个人卡
		$("#cardArea").html(
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分公司名称：" + rowData.branchName + "&nbsp;&nbsp;银行名称：" + rowData.bankName + "&nbsp;&nbsp;账号名称："
						+ rowData.accountName + "&nbsp;&nbsp;账号：" + rowData.account + "&nbsp;&nbsp;类型：公司账户");
	} else if ("1" == type) {
		// 1代表现金
		$("#cardArea").html(
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分公司名称：" + rowData.branchName + "&nbsp;&nbsp;银行名称：" + rowData.bankName + "&nbsp;&nbsp;账号名称："
						+ rowData.accountName + "&nbsp;&nbsp;账号：" + rowData.account + "&nbsp;&nbsp;类型：个人账户");

	}
}
function time(param) {
	var temp = [];
	cloneAll(param, temp);
	var timeArray = [];
	var length = temp.length;
	for ( var i = 0; i < length; i++) {
		timeArray.push(temp[i].repaymentDate);
	}
	return timeArray;
}
function openAccDialog() {
	$("#accInfoDialog").dialog('open');
}

// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
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
function doSaveCard() {
	$("#cardSaveButton").linkbutton('disable');
	if ($("#accountInfo").form('validate')) {
		ajaxSubmit(serverName + "/receivablesRegistration/addPersonalCard.do", $("#accountInfo").serialize(), function(r) {
			if (r != undefined) {
				// console.info(r);
				// $.messager.alert("消息", "个人卡信息添加成功！", "info");
				alert("个人卡信息添加成功！");
				// 关闭dialog
				$("#accInfoDialog").dialog('close');
				// 个人卡id插入页面
				$("#cardId").val(r.accountInfoId);
				// 个人卡对象加入个人卡数组
				personalCard.push(r);
				// 显示个人卡信息
				showCardArea(r);
				$("#cardSaveButton").linkbutton('enable');
			} else {
				// $.messager.alert("消息", "个人卡信息添加失败！", "error");
				alert("个人卡信息添加失败！");
				$("#cardSaveButton").linkbutton('enable');
			}
		});
	} else {
		// $.messager.alert("消息", "请填写完整信息！", "warning");
		alert("请填写完整信息！");
	}
}
function closeAccDialog() {
	$("#accountInfo").form('clear');
	$("#accInfoDialog").dialog('close');
	$("#defaultBox").combobox('clear');
	$("#defaultBox").combobox('setValue', "");
}
function showRemark(returnPlanId, remark) {
	// alert(rowData[0].remark);
	// console.info(rowIndex);
	$("#remarkComment").val(remark);
	$("#returnId").val(returnPlanId);
	$("#remarkComment").attr("readonly", true);
	$("#remarkDialog").dialog('open');
	$("#remarkSpan").html("点击灰色区域可进行编辑");
}
function doSaveRemark() {
	$("#remarkSaveButton").linkbutton('disable');
	if ($("#remarkForm").form('validate')) {
		ajaxSubmit(serverName + "/receivablesRegistration/saveRemark.do", {
			returnPlanId : $("#returnId").val(),
			remark : $("#remarkComment").val()
		}, function(r) {
			if (r == true) {
				alert("备注保存成功！");
				$("#remarkSaveButton").linkbutton('enable');
			} else {
				alert("备注保存失败！");
				$("#remarkSaveButton").linkbutton('enable');
			}
			$("#remarkDialog").dialog('close');
			$("#remarkForm").form('clear');
			$("#receivablesHistoryDataGrid").datagrid('reload');
		});
	}
}
function closeRemarkDialog() {
	$("#remarkForm").form('clear');
	$("#remarkDialog").dialog('close');
	$("#countVachar").html("");
}
function addRemark(returnPlanId, remark) {
	$("#returnId").val(returnPlanId);
	$("#remarkDialog").dialog('open');
	$("#remarkSpan").html("请输入内容");
}
function textCount() {
	$("#remarkComment").keyup(function() {
		var maxl = 50;
		var tishi = "还可以输入" + maxl + "个字";
		$("#countVachar").html(tishi);
		var xianyou = $(this).val().length;
		var keyi = maxl - xianyou;
		var tishi = "还可以输入" + keyi + "个字";
		if (xianyou > 49) {
			var tishi = "还可以输0个字";
			$("#countVachar").css({
				"color" : "red"
			});
		} else {
			$("#countVachar").css({
				"color" : "#000"
			});
		}
		$("#countVachar").html(tishi);
	});
}
function nowDate() {
	var newday = new Date();
	var m = newday.getMonth() + 1;
	if (m < 10) {
		m = "0" + m;
	}
	var d = newday.getDate();
	if (d < 10) {
		d = "0" + d;
	}
	var formatterDate = newday.getFullYear() + "-" + m + "-" + d;
	$("#date").datebox('setValue', formatterDate);
}
function showNormalOrEarly() {
	var ty = $("#normalOrEarly").val();
	if (ty == "0") {
		$("#receivedTypeSpan").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>正常还款登记</font>");
	} else if (ty == "1") {
		$("#receivedTypeSpan").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>一次性还款登记</font>");
	} else {
		$("#receivedTypeSpan").html("<font style=' font-size:12px;color:#fa8072;font-weight:bold;'>未取到还款登记类型</font>");
	}
}