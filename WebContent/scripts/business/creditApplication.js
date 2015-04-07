// 判断申请 开始时间与结束时间 的先后
$(function() {
	window.onresize = function() {
		setTimeout(function() {
			var tabHeight2 = $("#creditApplicationListTab").height();
	    	var heightMa2 = 50+tabHeight2;
			$('#creditApplicationList').resizeDataGrid(heightMa2, 20, 0, 0);
		}, 500);
	};

	$("#advancedaquery").hide();
	// 新的搜索显示

	var dic = new DataDictionary();
	//dic.addDic("auditStatus", "auditStatus");
	dic.addDic("reasonType_e_Y", "revocationReason");
	//dic.addDic("businessType_wer_Y", "businessType");
	dic.addDic("cardFlag_cardFlag_Y", "cardFlag");
	dic.addDic("bankNum_bankNum_Y", "bankNum");
	dic.dicAjax();
	
	$("#auditStatus").combobox({
		url:serverName+"/dicRequest/getSpecifiedDic.do?section=auditStatus",
		textField:"codeVlue",
		valueField:"codeKey",
		editable: false,
		multiple:true,
		separator:","
	});
	
	$("#accountButtonSpan").hide();
	$("#accountInfoForm").form("validate");
	$("#businessType").combobox(
					{	
						data:[{"codeKey":"0","codeValue":"分公司"},{"codeKey":"1","codeValue":"个人"}],
						width : 153,
						valueField:'codeKey',
                        textField:'codeValue',
						required : true,
						onSelect : function(value) {
							if (value.codeKey == '0') {
								$("#accountButtonSpan").show();
								$.each($("#accountInfoForm input"),
												function(j, element) {
													if (element.id != undefined
															&& element.id != null
															&& element.id != "") {
														$("#" + element.id).attr("readonly",
																		"readonly");
													}
													var classNameOld = $("#" + element.id).attr("class");
													var elementvalue = $("#" + element.id).attr("value");
													if (classNameOld != undefined) {
														if ("easyui-combobox combobox-f combo-f" == classNameOld) {
															var comboboxValue = $("#"+ element.id).combobox("getValue");
															$("#" + element.id).combobox(
																			{
																				required : false,
																				value : comboboxValue,
																				validType : '',
																				hasDownArrow : false,
																				editable : false,
																				validType : '',
																				width : 153
																			});
															$("#" + element.id).combobox(
																			"validate");
														}
														if ("combobox-f combo-f" == classNameOld) {
															var comboboxValue = $(
																	"#"+ element.id)
																	.combobox("getValue");
															$("#" + element.id).combobox(
																			{
																				required : false,
																				value : comboboxValue,
																				validType : '',
																				hasDownArrow : false,
																				validType : '',
																				editable : false,
																				width : 153
																			});
															$("#" + element.id).combobox(
																			"validate");
														} else {
															var className = className = classNameOld
																	.substring(7,classNameOld
																					.indexOf(" v"));
															if ("validatebox" == className) {
																$("#"+ element.id).validatebox(
																				{
																					required : false
																				});
																$("#"+ element.id)
																		.validatebox(
																				"validate");
															}

															if ("numberbox" == className) {
																$("#"+ element.id).numberbox(
																				{
																					required : false,
																					value : elementvalue
																				});

																$("#"+ element.id).numberbox(
																				"validate");
															}
														}
													}
												});
								//change();
							}
							if (value.codeKey == '1' || value.codeKey == '') {
								
								$("#accountInfoId").val("");
								$("#mobilephone").val("");
								$("#bankPrefectureNum").val("");
								$("#bankNum").combobox("setValue", "");
								$("#bankName").val("");
								$("#cardFlag").combobox("setValue", "");
								$("#account").val("");
								$("#accountName").combobox("setValue", "");
								$("#payBranchno").val("");
								$("#credentialsNumber").val("");
								$("#onUsed").val("1");
								$("#accountType").val("1");
								$("#useType").val("2");
								provinceShowPublic("provinceId1", "cityId1","districtId1");

								$("#accountButtonSpan").hide();
								$.each(
												$("#accountInfoForm input"),
												function(j, element) {
													if (element.id != undefined
															&& element.id != null
															&& element.id != "") {
														$("#" + element.id)
																.removeAttr(
																		"readonly");
													}
													$("#credentialsNumber")
															.attr("readonly",
																	"readonly");
													$("#branchName").attr(
															"readonly",
															"readonly");
													var classNameOld = $(
															"#" + element.id)
															.attr("class");
													var elementvalue = $(
															"#" + element.id)
															.attr("value");
													if (classNameOld != undefined) {
														if ("easyui-combobox combobox-f combo-f" == classNameOld) {
															var comboboxValue = $(
																	"#"
																			+ element.id)
																	.combobox(
																			"getValue");
															$("#" + element.id)
																	.combobox(
																			{
																				required : true,
																				value : comboboxValue,
																				validType : '',
																				hasDownArrow : true,
																				editable : false,
																				validType : 'selectValueRequired["'
																						+ element.id
																						+ '","请选择"]',
																				width : 153
																			});
//															$("#" + element.id)
//																	.combobox(
//																			"validate");
														}
														if ("combobox-f combo-f" == classNameOld) {
															var comboboxValue = $("#"+ element.id)
																	.combobox(
																			"getValue");
															$("#" + element.id)
																	.combobox(
																			{
																				required : true,
																				value : comboboxValue,
																				validType : '',
																				hasDownArrow : true,
																				editable : false,
																				validType : 'selectValueRequired["'
																						+ element.id
																						+ '","请选择"]',
																				width : 153
																			});
//															$("#" + element.id)
//																	.combobox(
//																			"validate");
														} else {
															var className = className = classNameOld
																	.substring(
																			7,
																			classNameOld
																					.indexOf(" v"));
															if ("validatebox" == className) {
																$(
																		"#"
																				+ element.id)
																		.validatebox(
																				{
																					required : true
																				});
//																$(
//																		"#"
//																				+ element.id)
//																		.validatebox(
//																				"validate");
															}

															if ("numberbox" == className) {
																$("#"+ element.id).numberbox(
																				{
																					required : true,
																					value : elementvalue
																				});

//																$(
//																		"#"
//																				+ element.id)
//																		.numberbox(
//																				"validate");
															}
														}
													}
												});
								$("#accountInfoForm").form("validate");
							}
						}
					});
//	$("#companyName").combobox({
//		url : serverName + "/CustomerConsult/departmentList.do",
//		valueField : "departmentId",
//		textField : "departmentName",
//		mote : "remote",
//		panelHeight : 'auto',
//		editable : false,
//		multiple : false,
//		onLoadSuccess : function() {
//			$("#companyName").combobox('select', '');
//		}
//	});

	departmentComboboxTree("companyName", false);
	
	$("#beginSubmitDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginSubmitDate").datebox("getValue");
			var endSubmitDate = $("#endSubmitDate").datebox("getValue");
			if (endSubmitDate != null && endSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginSubmitDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#endSubmitDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginSubmitDate").datebox("getValue");
			var endSubmitDate = $("#endSubmitDate").datebox("getValue");
			if (beginSubmitDate != null && beginSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endSubmitDate").datebox("setValue", "");
					return;
				}
			}

		}
	});

	$("#loanTime").datebox(
			{
				onSelect : function(date) {
					var loanTime = $("#loanTime").datebox("getValue");
					if (loanTime != null && loanTime != "") {
						if (new Date(loanTime) - new Date(sysDate) < 0) {
							$.messager.alert("消息", "期望放款时间不能小于当前服务器时间,当前系统时间为-"
									+ sysDate + "-");
							$("#loanTime").datebox("setValue", "");
							return;
						}
					}
				}
			});
	/*
	 * $("#beginExpectLoanDate").datebox({ onSelect : function() { var
	 * beginExpectLoanDate = $("#beginExpectLoanDate").datebox("getValue"); var
	 * endExpectLoanDate = $("#endExpectLoanDate").datebox("getValue"); if
	 * (endExpectLoanDate != null && endExpectLoanDate != "") { if
	 * (beginExpectLoanDate > endExpectLoanDate) { $.messager.alert("消息",
	 * "时间选择有误！结束时间应该大于等于开始时间。"); $("#beginExpectLoanDate").datebox("setValue",
	 * ""); return; } } } }); $("#endExpectLoanDate").datebox({ onSelect :
	 * function() { var beginExpectLoanDate =
	 * $("#beginExpectLoanDate").datebox("getValue"); var endExpectLoanDate =
	 * $("#endExpectLoanDate").datebox("getValue"); if (beginExpectLoanDate !=
	 * null && beginExpectLoanDate != "") { if (beginExpectLoanDate >
	 * endExpectLoanDate) { $.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
	 * $("#endExpectLoanDate").datebox("setValue", ""); return; } } } });
	 */
	$("#beginLoanDate").datebox({
		onSelect : function() {
			var beginLoanDate = $("#beginLoanDate").datebox("getValue");
			var endLoanDate = $("#endLoanDate").datebox("getValue");
			if (endLoanDate != null && endLoanDate != "") {
				if (beginLoanDate > endLoanDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginLoanDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#endLoanDate").datebox({
		onSelect : function() {
			var beginLoanDate = $("#beginLoanDate").datebox("getValue");
			var endLoanDate = $("#endLoanDate").datebox("getValue");
			if (beginLoanDate != null && beginLoanDate != "") {
				if (beginLoanDate > endLoanDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endLoanDate").datebox("setValue", "");
					return;
				}
			}

		}
	});

	$("#creditApplicationList").datagrid(
					{	url : serverName
								+ "/creditgroup/selectCreditApplicationList.do"
								+ conditions,
						pagination : true,
						idfield : 'creditapplicationId',
						striped : true,
						singleSelect : true,
						sortName : 'groupNumber',
						sortOrder : 'desc',
						rownumbers : true,
						loadMsg : '正在加载....',
						frozenColumns : [ [{ title : '操作', field : 'operateFlag', width : '200', formatter : optionFormatter
										}, {title : '业务单号', field : 'groupNumber', width : '120'
										}] ],
						columns : [ [{ title : '业务状态', field : 'auditStatusShow', width : '95',formatter:function(value,param){
										if(param.validState == "0"){
											return "已作废或删除";
										}else{
											return value;
										}
									}
								},{ title : '借款人', field : 'groupName', width : '90'
								} ,{ title : "申请金额(元)", field : 'groupAppTotal', width : '85'
								}, { title : "审批金额(元)", field : 'amount', width : '85'
								}, { title : "最终放款金额(元)", field : 'loanAmount', width : '100'
								}, { title : '产品类型', field : 'repaymentPlanId', width : '100', hidden : true
								}, { title : '产品类型', field : 'repaymentPlanName', width : '90'
								}, { title : '产品额度上限', field : 'capitalUpperLimit', 	width : '100', hidden : true
								}, { title : '产品额度下限', field : 'capitalLowerLimit', width : '100', hidden : true
								}, { title : '提交日期', field : 'submitDate', width : '80'
								}/*
									 * , { title : '期望放款日期', field :
									 * 'expectLoanDate', width : '100' }
									 */,
								{ title : '放款日期', field : 'signagreementDate', width : '80'
								}, { title : '分公司名称', field : 'companyName', width : '100'
								}, { title : '客户经理', field : 'loanOfficerName', width : '90'
								},{ title : '风险经理id', field : 'fxid', width : '100', hidden : true
								}, { title : '放款方式', field : 'businessType', width : '70', formatter : function(value) {
										if ($.trim(value) == "1") {
											return "个人";
										}
										if ($.trim(value) == "0") {
											return "分公司";
										}
									}

								}/*  , { title : '组员数量', field :
									 * 'groupMemberCount', width : '100' }
								 */,
                            { title: '城市参审标记', field: 'isCityParticipate', width: '66', hidden: true
                            },
                            { title: '城市参审', field: 'cityParticipate', width: '66', formatter: function (v) {
                                if (v == '1') {
                                    return '是';
                                }
                                return '否';
                            }
                            },
                            { title : '背景调查报告', field : 'fx',colspan:'2', width : '90', formatter : function(value, param) {
												if (param.fxid == ""
														|| param.fxid == null) {
													return "未建立";
												}
												if (param.fxid > 0
														&& (param.fx == "" || param.fx == null)) {
													return "未提交";
												}
												if (param.fxid > 0 && param.fx == "1") {
													return "已提交";
												}
											} 
								}, {
									title : '申请状态', field : 'auditStatus', width : '100', hidden:true
								}, { title : '产品分类编号', field : 'producttypeid', width : '210' , hidden:true
								}, { title : '创建经理id', field : 'createLoanOfficerId', width : '210' , hidden:true
								}, { title : '创建经理', field : 'createLoanOfficerName', width : '260', hidden:true
								}, { title : '删除标记', field : 'validState', width : '210', hidden:true
								}, { title : '咨询id', field : 'customerConsultId', width : '210', hidden:true
								},{ title : '咨询池id', field : 'consultPoolId', width : '210', hidden:true
								}, { title : '配偶姓名', field : 'familyName', width : '210', hidden:true
								}, { title : '配偶的身份证号', field : 'familyIdnumber', width : '210', hidden:true
								}, { title : '借款人身份证号', field : 'credentialsNumber', width : '210', hidden:true
								}, { title : '拒贷原因', field : 'refuseReasons', width : '210',hidden:true
								} ] ]

					});
 	$("#creditApplicationList").datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"auditStatusShow",status:"27",showContant:"refuseReasons"});
	var tabHeight = $("#creditApplicationListTab").height();
	$("#creditApplicationListTab").tabs({
		onSelect :function(data){
			var tabHeight1 = $("#creditApplicationListTab").height();
			var heightMa1 = 50+tabHeight1;
			 $('#creditApplicationList').resizeDataGrid(heightMa1, 20, 0, 0);  
		}
	});
	var heightMa = 50+tabHeight;
	$('#creditApplicationList').resizeDataGrid(heightMa, 20, 0, 0);
	// 村镇联动菜单
	showComboboxTown("countyId", "townId", "villageId");

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
});
//查看咨询
function showCustomerConsult(){
	var rowdata = $("#creditApplicationList").datagrid("getSelected");
	//判断customerConsultId是否为空
	if(rowdata.customerConsultId!=null){
	var objtabs = window.parent;
	objtabs.addTabFun({
		src : serverName + "/jsp/rc/business/customerConsult.jsp?customerConsultId="+rowdata.customerConsultId,
		title : "客户咨询"
	});
	}
	//判断consultPoolId是否为空   hongjieluo
	if(rowdata.consultPoolId!=null){
		var objtabs = window.parent;
		objtabs.addTabFun({
			src : serverName + "/jsp/rc/CustomerConsultPool/inquire_pool_office_list.jsp?consultPoolId="+rowdata.consultPoolId,
			title : "受理咨询列表"
		});
	}
}

function change() {
	// cardInfo(paramId, param);
	$("#cardDataGrid")
			.datagrid(
					{
						url : serverName
								+ "/accountInfo/selectCompanyAccounts.do?creditapplicationId="
								+ $("#creditapplicationIdComfirm").val(),
						method : 'post',
						loadMsg : "数据装载中....",
						fitColumns : true,
						nowrap : false,
						striped : true,
						singleSelect : true,
						columns : [ [ {
							field : 'bankPrefectureNum',
							title : '所在行地区号',
							width : 100,
							hidden : true
						}, {
							field : 'bankNum',
							title : '银行行别代码',
							width : 100,
							hidden : true
						}, {
							field : 'bankRank',
							title : '银行行别名称',
							width : 100,
							hidden : true
						}, {
							field : 'onUsed',
							title : '是否启用:0-停用,1-启用',
							width : 100,
							hidden : true
						}, {
							field : 'cardFlag',
							title : '卡折标志:0-卡,1-折',
							width : 100,
							hidden : true
						}, {
							field : 'mobilephone',
							title : '手机电话号码',
							width : 100,
							hidden : true
						}, {
							field : 'payWay',
							title : '支付方式:0:转帐，1:划扣',
							width : 100,
							hidden : true
						}, {
							field : 'payBranchno',
							title : '支付行号 ',
							width : 100,
							hidden : true
						}, {
							field : 'credentialsNumber',
							title : '身份证号码',
							width : 100,
							hidden : true
						}, {
							field : 'provinceId',
							title : '省ID',
							width : 100,
							hidden : true
						}, {
							field : 'cityId',
							title : '城市编号',
							width : 100,
							hidden : true
						}, {
							field : 'districtId',
							title : '区ID',
							width : 100,
							hidden : true
						}, {
							field : 'departmentId',
							title : '分公司ID',
							width : 100,
							hidden : true
						}, {
							field : 'borrowerName',
							title : '借款人姓名',
							width : 100,
							hidden : true
						}, {
							field : 'borrowerCredentialsNumber',
							title : '借款人身份证号',
							width : 100,
							hidden : true
						}, {
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
						}, {
							field : 'useType',
							title : '账号用途',
							width : 100,
							formatter : function(value) {
								if (value == "0") {
									return "付款";
								} else if (value == "1") {
									return "收款";
								} else if (value == "2") {
									return "收付款";
								} else {
									return "Null";
								}
							}
						} ] ],
						onSelect : function(rowIndex, rowData) {
							changeDRW(rowData.accountInfoId);
							$("#accountInfoForm").form("load", rowData);
							showCityCombopublic("provinceId1", "cityId1",
									"districtId1");
						}
					});
	$("#cardDialog").dialog({
		buttons : [ {
			text : "取消",
			handler : function() {
				$("#cardDialog").dialog('close');
			}
		} ],
		closed : false,
		onBeforeClose : function() {
			// 每次关闭前都销毁dialog中的datagrid
			// var dRows = $("#cardDataGrid").datagrid('getRows');
			// companyCard.length = 0;
			// personalCard.length = 0;
			// var lll = dRows.length;
			// if (dRows.length == 0) {
			// } else {
			// for ( var i = 0; i < lll; i++) {
			// $("#cardDataGrid").datagrid('deleteRow', 0);
			// }
			// }
		}
	});
}
// 选择公司与申请关联账户
function changeDRW(accInfoId) {

	ajaxSubmit(serverName + "/creditgroup/updateCreditApplication.do", {
		creditapplicationId : $("#creditapplicationIdComfirm").val(),
		accountInfoId : accInfoId,
		// returnAccountInfoId :accInfoId,
		businessType : "0",
		defaultReturnmentWay : "1"
	}, function(r) {
		if (r.success) {
			$("#cardDialog").dialog('close');
			$.messager.show({
				showType : "show",
				timeout : 5000,
				title : "消息",
				msg : "成功绑定付款账号"
			});

		} else {
			$.messager.show({
				showType : "show",
				timeout : 5000,
				title : "消息",
				msg : "绑定付款账号失败"
			});
		}
	});
}

function retFun(data) {
}
function deleteById() {
	$.messager.confirm("消息", "确定要删除吗？", function(result) {
		if (result) {
			var rows = $("#creditApplicationList").datagrid("getSelected");
			url = serverName + "/creditgroup/delete.do";
			ajaxSubmit(url, {
				creditapplicationId : rows.creditapplicationId
			}, function(result) {
				if (result == true) {
					$.messager.alert("消息", "删除成功！");
					$("#creditApplicationList").datagrid("reload", "");
				} else {
					$.messager.alert("消息", "删除失败！");
					$("#creditApplicationList").datagrid("reload", "");
				}
			});
		}
	});
}
function update() {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	window.location = serverName
			+ "/creditgroup/selectCreditApplication.do?creditapplicationId="
			+ rows.creditapplicationId;
}

function showCashStreamInput() {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	var dd1 = $("#cashStream")
			.dialog(
					{
						title : "现金流入流出表",
						modal : true,
						closed : true,
						inline : false,
						width : 1000,
						height : 470,
						border : false,
						draggable : true,
						dosize : true,
						buttons : [
								{
									id : "saveAllCashStreams",
									text : "全部保存",
									iconCls : "icon-save",
									handler : function() {
										$("#saveAllCashStreams").attr(
												"disabled", true);
										// var controls =
										// document.getElementsByTagName('input');
										// //遍历所有的input对象
										// for(var i=0; i<controls.length; i++){
										// var valueT = controls[i].value;
										// if(valueT==""){
										// alert("有空值,不能提交。");
										// //controls[i].style.backgroundColor=red;
										// $("#saveAllCashStreams").attr("disabled",false);
										// return false;
										// }
										// }
										ajaxSubmit(
												serverName
														+ "/cashStream/update.do",
												$("#cashStreamForm")
														.serialize(),
												function(result) {
													if (result) {
														// $("#dd1").dialog("close");
														// $("#cashStreamForm").form("load",result);
														alert("保存成功");
														$("#cashStream")
																.dialog("close");
														// $.messager.alert(result);
													} else {
														$.messager
																.alert("操作失败,请检查是否有空值!");
														$("#saveAllCashStreams")
																.attr(
																		"disabled",
																		false);
													}
												});
									}
								}, {
									text : "关闭",
									iconCls : "icon-cancel",
									handler : function() {
										$("#cashStream").dialog('close');
									}
								} ],
						href : serverName
								+ "/jsp/rc/business/cashStreamInput.jsp",
						onLoad : function() {
							$("#saveAllCashStreams").attr("disabled", true);
							var paraValue = rows.creditapplicationId + "_"
									+ rows.groupName;
							ajaxSubmit(serverName
									+ "/cashStream/getCashStream.do", {
								param : paraValue
							}, function(result) {
								// $("<div style=\"z-index:1999\"
								// class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true)
								// - 190) /
								// 2,top:($(window).height() - 45) / 2});
								$("#cashStreamForm").form("load", result);
								$("#creatDate").val(
										result.csOperInTotal.createDate);
								$("#borrowerName").val(
										result.csOperInTotal.borrowerName);
								$("#saveAllCashStreams")
										.attr("disabled", false);
								// if(result!=null){
								// calculateCashStream(result);
								// }
							});
						}
					});
	dd1.dialog('open');
}
function searchAll() { 
	var auditStatusArray = $("#auditStatus").combobox("getValues");
	var auditStatusString = auditStatusArray.join(",");
	
	var departmentNameValues=$("#companyName").combotree("getValues");
	if(departmentNameValues!=null&&departmentNameValues!=""){
		for(var i in departmentNameValues){
			departmentNameValues[i]="'"+departmentNameValues[i]+"'";
		}
	}
	var 	departmentNamestr=departmentNameValues.join(",");
	
	//新增产品类型搜索条件
    var pTypeName = $('#productTypeName').combobox('getValue');

	$("#creditApplicationList").datagrid("load", {
			groupNumber : $.trim($("#groupNumber").val()),
			groupName : $.trim($("#groupName").val()),
			auditStatusArray : auditStatusString,
			// countyId:$("#countyId").combobox("getValue"),
			// townId:$("#townId").combobox("getValue"),
			// villageId:$("#villageId").combobox("getValue"),
			beginSubmitDate : $("#beginSubmitDate").datebox("getValue"),
			endSubmitDate : $("#endSubmitDate").datebox("getValue"),
			// beginExpectLoanDate : $("#beginExpectLoanDate").datebox("getValue"),
			// endExpectLoanDate : $("#endExpectLoanDate").datebox("getValue"),
			beginLoanDate : $("#beginLoanDate").datebox("getValue"),
			endLoanDate : $("#endLoanDate").datebox("getValue"),
			companyId : departmentNamestr,
            repaymentPlanName : pTypeName
	});
	
}
function clearSearchBox() {
	$("#groupNumber").val("");
	$("#groupName").val("");
	$("#companyName").combotree("setValues", "");
	$("#auditStatus").combobox("clear");
	$("#countyId").combobox("setValue", "");
	$("#townId").combobox("setValue", "");
	$("#villageId").combobox("setValue", "");
	$("#beginSubmitDate").datebox("setValue", "");
	$("#endSubmitDate").datebox("setValue", "");
	$("#beginLoanDate").datebox(
			"setValue", "");
	$("#endLoanDate").datebox("setValue", "");
    $("#productTypeName").combobox("clear");
}

function clearFuzzyBox() {
	$("#fuzzy").val("");
}
// 村镇联动菜单
function showComboboxTown(county, town, village) {
	$("#" + county)
			.combobox(
					{
						editable : false,
						url : serverName + '/NSC/listCity.do?data=' + 440200,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						width : '150',
						value : '',
						onSelect : function(value) {
							$("#" + town)
									.combobox(
											{
												url : serverName
														+ '/town/list.do?parentId='
														+ value.cityCode,
												textField : 'townshipinfName',
												valueField : 'townshipinfoid',
												mode : 'remote',
												delay : 500,
												width : '150',
												value : '',
												onSelect : function(value) {
													$("#" + village)
															.combobox(
																	{
																		url : serverName
																				+ '/town/list.do?parentId='
																				+ value.townshipinfoid,
																		textField : 'townshipinfName',
																		valueField : 'townshipinfoid',
																		mode : 'remote',
																		delay : 500,
																		width : '150',
																		value : '',
																		onSelect : function(
																				value) {
																			if (town == "town") {
																				showGroupAddress();
																			} else {
																				showBorrowerServiceAddress();
																			}
																		}
																	});
												}
											});
						}
					});

}

// 模糊查询
function fuzzySearch() {
	var fuzzyValue = $("#fuzzy").val();
	$("#creditApplicationList").datagrid("load", {
		fuzzyValue : $.trim(fuzzyValue)
	});
}
// 跳转到审核页面
function audit() {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	$("#auditPage")
			.dialog(
					{
						title : "信贷审查页面",
						closed : false,
						modal : true,
						inline : true,
						border : false,
						dosize : true,
						href : serverName
								+ "/creditApplication2Exam/selectById.do?creditapplicationId="
								+ creditapplicationId
					});

}

function approval(role) {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
    /*增加判断：当前信贷申请单是否被做过自查，自查后方可进行审批。*/
    ajaxSubmit(serverName + '/ComplianceController/checkIfCheckByType.do?checkType=0&creditApplicationId=' + creditapplicationId, {}, function (ret) {
        if (ret) {
			/*20141128增加判断：当前信贷申请单是否配置营业部渠道关系，配置后方可进入审批流程。*/
			ajaxSubmit(serverName + '/discountConfigurationController/checkIfConfigureById.do?creditApplicationId=' + creditapplicationId, {}, function (ret) {
				if (ret) {
					$("#auditPage").dialog({
						title: "信贷审批页面",
						closed: false,
						modal: true,
						inline: true,
						border: false,
						dosize: true,
						href: serverName + "/creditapplication2Approval/selectById.do?creditapplicationId=" + creditapplicationId + "&role=" + role
					});
				} else {
					$.messager.alert('提示', '当前申请所属营业部未配置渠道，暂不可审批！', 'info');
					return;
				}
			});
        } else {
            $.messager.alert('提示', '当前申请尚未做过自查，暂不可审批！', 'info');
            return;
        }
    });
}

function changeLimit() {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;

	$("#auditPage")
			.dialog(
					{
						title : "额度变更页面",
						closed : false,
						modal : true,
						inline : true,
						border : false,
						dosize : true,
						href : serverName
								+ "/creditapplication2ChgMount/selectById.do?creditapplicationId="
								+ creditapplicationId
					});
}

function closeDialog() {
	$("#auditPage").dialog("close");
	$("#creditApplicationList").datagrid("reload", "");
}

// 收款登记
function shoukuan() {
	var row = $("#creditApplicationList").datagrid("getSelected");
	window.location = serverName
			+ "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid="
			+ row.creditapplicationId;
}
// 撤回功能
function chehui() {
	var rowData = $("#creditApplicationList").datagrid("getSelected");
	$("#chehui")
			.dialog(
					{
						title : "撤回操作",
						closed : true,
						modal : true,
						width : 450,
						heigth : 400,
						buttons : [
								{
									text : "确认",
									handler : function() {
										if (!$("#chehuiForm").form("validate")) {
											$.messager.alert("消息", "请将表单填写完整！");
											return;
										}
										$.messager
												.confirm(
														"消息",
														"确定要撤回吗？",
														function(r) {
															if (r) {
																var row = $(
																		"#creditApplicationList")
																		.datagrid(
																				"getSelected");
																var url = serverName
																		+ "/creditgroup/update.do";
																ajaxSubmit(
																		url,
																		$("#chehuiForm").serializeObject(),
																		function(
																				result) {
																			if (result.success) {
																				$.messager.show({
																							title : "消息",
																							msg : "撤回成功！"
																						});
																				$("#chehui").dialog(
																								"close");
																				$("#creditApplicationList").datagrid(
																								"reload");

																			} else {
																				$("#chehui").dialog("close");
																				$("#creditApplicationList").datagrid(
																								"reload");
																				
																				$.messager.show({
																							title : "消息",
																							msg : result.msg
																						});
																			}
																		});
															}
															;
														});
									}
								}, {
									text : "关闭",
									handler : function() {
										$("#chehui").dialog("close");
									}
								} ]
					});
	$("#chehui").dialog("open");
	$("#chehuiForm").form("load", rowData);
	$("#reasonType").combobox("setValue","");

}
// 查看详细
function searchLook() {
	var row = $("#creditApplicationList").datagrid("getSelected");
	var centerj = window.parent;
	centerj.addTabFun({
				src : serverName
						+ "/creditgroup/searchLook.do?creditApplicationId="
						+ row.creditapplicationId + "&creditInvestigatioId="
						+ row.fxid,
				title : row.groupNumber + "申请单查看"
			});
}

//作废申请
function updateCancel(){
	$.messager.confirm('消息','确定要作废吗？作废的单子将从申请列表中删除，不能继续业务',function(b){
		if(b){
			var row = $("#creditApplicationList").datagrid("getSelected");
			var url = serverName + "/creditgroup/updateCancel.do";
			ajaxSubmit(url,{creditapplicationId:row.creditapplicationId },function(message){
				$("#creditApplicationList").datagrid("reload");
				if(message.success){
					$.messager.show({
						title:"消息",
						msg:"操作成功",
						showType:"slide"
					});
				}else{
					$.messager.show({
						title:"消息",
						msg:"操作失败",
						showType:"slide"
					});
				}
			});
		}
	});
}

function updateClose(){
	$.messager.confirm('消息','确定要关闭吗？关闭的单子将变为注销状态，不能继续业务',function(b){
		if(b){
			var row = $("#creditApplicationList").datagrid("getSelected");
			var url = serverName + "/creditgroup/updateClose.do";
			ajaxSubmit(url,{creditapplicationId:row.creditapplicationId },function(message){
				$("#creditApplicationList").datagrid("reload");
				if(message.success){
					$.messager.show({
						title:"消息",
						msg:"操作成功",
						showType:"slide"
					});
				}else{
					$.messager.show({
						title:"消息",
						msg:"操作失败",
						showType:"slide"
					});
				}
			});
		}
	});
}

// 额度确认
function confirmLimit() {

	$("#accountInfoId").val("");
	$("#mobilephone").val("");
	$("#bankPrefectureNum").val("");
	$("#bankNum").combobox("setValue", "");
	$("#bankName").val("");
	$("#cardFlag").combobox("setValue", "");
	$("#account").val("");
	$("#accountName").combobox("setValue", "");
	$("#payBranchno").val("");
	$("#credentialsNumber").val("");
	$("#onUsed").val("1");
	$("#accountType").val("1");
	$("#useType").val("2");
	
	$("#accountButtonSpan").hide();
	$("#confirmAmountForm").form("clear");
	var row = $("#creditApplicationList").datagrid("getSelected");
	url = serverName + "/creditgroup/selectAmount.do";
	ajaxSubmit(url, {
		creditapplicationId : row.creditapplicationId
	}, function(result) {
		$("#confirmAmount").form("load", result);
		$("#businessType").combobox("setValue","1");
		if (result.amount == null || result.amount == "") {
			$("#amountLoan").attr("value", result.loanAmount);
		}
		provinceShowPublic("provinceId1", "cityId1","districtId1");

		$("#accountName").combobox(
				{
					width : 153,
					url : serverName
							+ "/creditgroup/selectBorrowerFamily.do?creditapplicationId="
							+ $("#creditapplicationIdComfirm").val(),
					mode : 'remote',
					editable : false,
					required : true,
					valueField : "name",
					textField : "name",
					onSelect : function(data) {
						$("#credentialsNumber")
								.val(data.credentialsNumber);
					}
				});
	});
	$("#confirmAmount").dialog(
					{
						title : "借款金额确认",
						closed : false,
						modal : true,
						inline : true,
						border : false,
						dosize : false,
						width : 900,
						heigth : 470,
						buttons : [
								{	id:"confirmButton",
									text : "确认",
									handler : function() {
										$("#confirmButton").linkbutton("disable");
										if ($("#businessType").combobox("getValue") == "1"
												&& !$("#accountInfoForm").form("validate")) {
											$("#confirmButton").linkbutton("enable");
											$.messager.alert("消息",
													"完成个人账户信息的填写");
											return;
										}
										if ($("#businessType").combobox("getValue") == "0"
												&& ($("#accountInfoId").val() == ""|| $("#accountInfoId").val() == null || $("#accountInfoId").val() == undefined)) {
											$("#confirmButton").linkbutton("enable");
											$.messager.alert("消息", "请选择公司账户");
											return;
										}

										if ($("#businessType").combobox("getValue") == "") {
											$("#confirmButton").linkbutton("enable");
											$.messager.alert("消息", "请选择收付款方式");
											return;
										}
										var amount = $("#amountLoan").val();
										if (amount == null || amount == ""
												|| amount == undefined
												|| amount == 0.00) {
											$("#confirmButton").linkbutton("enable");
											$.messager.alert("消息", "请填写放款金额！");
											$("#amountLoan").focus();
											return;
										}

										if ($("#loanTime").datebox("getValue") == null
												|| $("#loanTime").datebox(
														"getValue") == "") {
											$("#confirmButton").linkbutton("enable");
											$.messager.alert("消息", "请填写期望放款时间");
											return;
										}

											$.messager.confirm("消息",
															"确认放款额度为"+ $("#amountLoan").val()+ "元,该金额将作为最终放款金额进行放款预约，是否确定！",
															function(r) {
																if (!r) {
																	$("#confirmButton").linkbutton("enable");
																	return;
																} else {
																	var amount = $("#amountLoan").val();
																	if (amount == null
																			|| amount == ""
																			|| amount == undefined
																			|| amount == 0.00) {
																		$("#confirmButton").linkbutton("enable");
																		$.messager.alert(
																						"消息",
																						"请填写放款金额！");
																		$("#amountLoan").focus();
																		return;
																	} else {
																				url = serverName+ "/creditgroup/confirmAmount.do",
																				ajaxSubmit(
																						url,
																						$("#confirmAmountForm").serialize()
																								+ "&"+ $("#accountInfoForm").serialize(),
																						function(message) {
																							if (message.success) {
																								$("#confirmButton").linkbutton("enable");
																								$.messager.show({title:"消息",
																												msg:message.msg});
																								$("#confirmAmount").dialog("close");
																								$("#creditApplicationList").datagrid("reload");
																							} else {
																								$("#confirmButton").linkbutton("enable");
																								$.messager.show({title:"消息",msg:message.msg});
																							}
																						});
																	}
																}
															});
										}
								}, {
									text : "关闭",
									handler : function() {
										$("#confirmAmountForm").form("clear");
										$("#confirmAmount").dialog("close");

									}
								} ]
					});
}

function confirmAmount(obj) {
	var limitAmount = $("#loanAmount").val();
	if (accSubtr(obj.value, limitAmount) > 0) {
		$.messager.alert("消息", "放款金额不应大于审批金额！");
		$("#amountLoan").val(limitAmount);
	}
}

function addfx() {
	var rows = $("#creditApplicationList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	var groupNumber = rows.groupNumber;
	var creditInvestigatioId = rows.fxid;
	var groupName = rows.groupName;
	if (creditInvestigatioId == null) {
		creditInvestigatioId = "";
	}
	if (groupName == null) {
		groupName = "";
	}
	$('#openRiskMgr')[0].src = serverName
			+ "/creditInvestigation/toAddAndEditPageCreditInvestigation.do?creditapplicationId="
			+ creditapplicationId + "&groupNumber=" + groupNumber
			+ "&creditInvestigatioId=" + creditInvestigatioId;
	$("#riskMgrDialog").dialog({
		title : "信用与背景调查",
		closed : true,
		modal : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "saveButton",
			text : "保存",
			handler : function() {
				var riskMgr = window.frames["openRiskMgr"];
				if (riskMgr.window) {
					riskMgr.window.saveRiskMgr(0);
				} else {
					riskMgr.contentWindow.saveRiskMgr(0);
				}
			}
		}, {
			id : "saveAndSubmitButton",
			text : "保存&提交",
			handler : function() {
				var riskMgr = window.frames["openRiskMgr"];
				if (riskMgr.window) {
					riskMgr.window.saveRiskMgr(1);
				} else {
					riskMgr.contentWindow.saveRiskMgr(1);

				}
			}
		}, {
			text : "取消",
			handler : function() {
				$("#riskMgrDialog").dialog("close");
				$('#openRiskMgr')[0].src = "";
			}
		} ],
		// href : serverName +
		// "/creditInvestigation/toAddAndEditPageCreditInvestigation.do?creditapplicationId="
		// + creditapplicationId + "&groupNumber=" +
		// groupNumber + "&creditInvestigatioId="
		// + creditInvestigatioId,
		onLoad : function() {
			// 回显操作
			// showRiskMgrDialog(creditapplicationId, groupNumber,
			// creditInvestigatioId);
		},
		onClose : function() {
			$("#creditApplicationList").datagrid('reload');
		}
	});
	$("#riskMgrDialog").dialog('open');

}

function buttonEnable(buttonId) {
	$("#" + buttonId).linkbutton('enable');
}
function buttonDisable(buttonId) {
	$("#" + buttonId).linkbutton('disable');
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
//业务回退功能
function rollback(){
		$.messager.confirm("提示","确认要回退吗?",function(flag){
			if(flag){
				var rows = $("#creditApplicationList").datagrid("getSelected");
				var url=serverName+"/rollbackController/rollback.do";
				var data="creditapplicationId="+rows.creditapplicationId;
				ajaxSubmit(url,data,function(result){
					if(result.success){
						$.messager.alert("消息","回退成功",'info',function(){$("#creditApplicationList").datagrid("load");});
					}else{
						$.messager.alert("消息",result.msg);
					}
				});
			}
		});
	}

/*
参审：2014.12.02 参审添加为统一入口
function p_approve(p_Type, p_creditapplicationId, p_already) { var tabspace = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; if (p_already == '1') { $.messager.alert('消息', tabspace + '&nbsp;&nbsp;&nbsp;您已参审', 'info'); return; } $.messager.confirm("消息", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "确认参加审贷会吗？", function (r) { if (r) { ajaxSubmit(serverName + "/creditapplication2Approval/participateApprove.do", {role: p_Type, creditapplicationId: p_creditapplicationId, isCityParticipate:p_already}, function (r) { $.messager.show({ title: '消息', msg: r.msg, timeout: 5000, showType: 'slide' }); if (r.success) { $("#creditApplicationList").datagrid('reload'); } }); } }) }
*/

/*
参审验证与保存
*/
function participate(cId, already) {
	console.info('rrrr');
    var tabspace = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    if (already >= '1') {
        $.messager.alert('消息', tabspace + '&nbsp;&nbsp;&nbsp;您已参审', 'info');
        return;
    }
    $.messager.confirm("消息", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "确认参加审贷会吗？", function (r) {
        if (r) {
            ajaxSubmit(serverName + "/creditapplication2Approval/participateApprove.do", {creditapplicationId: cId, isCityParticipate:already}, function (r) {
                $.messager.show({
                    title: '消息',
                    msg: r.msg,
                    timeout: 5000,
                    showType: 'slide'
                });
                if (r.success) {
                    $("#creditApplicationList").datagrid('reload');
                }
            });
        }
    })
}