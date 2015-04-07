var cancleGlag = true;
var customer_datagrid;
var operator;
/*客服检查：获取数据字典：错误文件、检查点*/
var sections = 'customerCheck';
var errorFile_compliance = [], checkpoint_loan = [];
var creditapplicationId;
$(function() {
	window.onresize = function() {
		setTimeout(function() {
			var tabHeight2 = $("#loanListTab").height();
			var heightMa2 = 50 + tabHeight2;
			$('#loanList').resizeDataGrid(heightMa2, 20, 0, 0);
		}, 500);
	};

	creditAppId = undefined;
	paramType = undefined;
	companyCard = new Array();
	personalCard = new Array();
	var dic = new DataDictionary();
	dic.addDic("cardFlag", "cardFlag");
// dic.addDic("defaultReturnmentWay", "defaultReturnmentWay");
	dic.addDic("businessType", "businessType");
// dic.addDic("cardFlagAccount", "cardFlag");
// dic.addDic("onUsed", "onUsed");
// dic.addDic("bankNum", "bankNum");
// dic.addDic("useType", "useType");
	dic.dicAjax();
	// //////还款方式
// showCityCombo("provinceId1", "cityId1", "districtId1");

	$("#useType").combobox({
		onSelect : function(record) {
			if (record.codeKey == "0") {
				// $.messager.alert("消息", "此处不能选付款！", "warning");
				$.messager.alert("消息", "此处不能选付款！");
				$("#useType").combobox('clear');
			}
		}
	});
    /* 更改：分公司名称下拉选择框为两级树形菜单
    // 分公司名称查询
    $("#companyName").combobox({
        url: serverName + "/CustomerConsult/departmentList.do",
        valueField: "departmentId",
        textField: "departmentName",
        mote: "remote",
        panelHeight: 'auto',
        editable: false,
        multiple: false,
        onLoadSuccess: function () {
            $("#companyName").combobox('select', '');
        }
    });*/
    departmentComboboxTreeWithPanelWidth("companyName",false,235);

	// var defaultReturnWayOld = $("#defaultReturnmentWay").combobox("getValue");
	/*
	 * $("#defaultReturnmentWay").combobox({ onChange:function(r,a){ if(a == 1){ creditApplicationid=$("#creditapplicationIdDengji").val();
	 * change(creditApplicationid,a); } } });
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
	// /////还款方式

	$("#gaoji").hide();
	$("#loanList").datagrid({
		url : serverName + "/creditgroup/selectCreditApplicationForLoan.do" + conditions,
		pagination : true,
		idfield : 'creditapplicationId',
		striped : true,
		singleSelect : true,
		sortName : 'creditapplicationId',
		sortOrder : 'desc',
		rownumbers : true,
		columns : [ [ {
			title : '业务单号',
			field : 'groupNumber',
			width : '110',
			sortable : true
		}, {
			title : '借款人',
			field : 'groupName',
			width : '90'
		}, {
			title : '产品类型',
			field : 'repaymentPlanId',
			width : '150',
			hidden : true
		}, {
			title : '财务账号id',
			field : 'accountInfoId',
			width : '150',
			hidden : true
		}, {
			title : ' id',
			field : 'creditapplicationId',
			width : '150',
			hidden : true
		}, {
			title : '产品类型',
			field : 'repaymentPlanName',
			width : '110'
		}, {
			title : '乡村镇',
			field : 'address',
			width : '200',
			hidden : true
			
		}, {
			title : '放款金额(元)',
			field : 'loanAmount',
			width : '100'
		}, {
			title : '服务费(元)',
			field : 'serviceCharge',
			width : '90'
		}, {
			title : '实发金额(元)',
			field : 'realAmount',
			width : '100'
		}, {
			title : '放款日期',
			field : 'signagreementDate',
			width : '100',
			sortable : true
		}, {
			title : '放款确认日期',
			field : 'loanConfirmDate',
			width : '100',
			sortable : true
		}, {
			title : '客户经理',
			field : 'loanOfficerName',
			width : '100',
			sortable : true
		}, {
			title : '还款方式',
			field : 'defaultReturnmentWay',
			width : '100',
			formatter : function(value) {
				if (value == "0") {
					return "自动划扣";
				} else {
					return "现金";
				}
			},
			hidden : true
		}, {
			title : '申请状态',
			field : 'auditStatus',
			width : '100',
			hidden : true,
			formatter : function(value) {
				if (value == "21") {
					return "放款额度确认";
				}
				if (value == "06") {
					return "放款失败登记";
				}
				if (value == "09") {
					return "财务已付款";
				}
				if ($.trim(value) == "9") {
					return "财务已付款";
				}
				if (value == "11") {
					return "已放款登记";
				}
				if (value == "13") {
					return "放款确认";
				}

				if (value == "14") {
					return "放款确认退回";
				}
				if (value == "15") {
					return "还款中";
				}
				if (value == "19") {
					return "提前还款登记";
				}
				if (value == "20") {
					return "提前还款完成";
				}
				if (value == "10") {
					return "款项到位";
				}
			}
		}, {
			title : '申请状态',
			field : 'auditStatusShow',
			width : '100',
			sortable : true
		}, {
			title : '业务类型',
			field : 'businessType',
			width : '100',
			formatter : function(value) {
				if (value == 0) {
					return '分公司';
				} else {
					return '个人';
				}
			}
		} ] ],
		frozenColumns : [ [ {
			title : '操作',
			field : 'operateFlag',
			width : '170',
			formatter : loanListOperater
		} ] ]
	});
// datagrid 动态
	var tabHeight = $("#loanListTab").height();
	var heightMa = 50 + tabHeight;
	$('#loanList').resizeDataGrid(heightMa, 20, 0, 0);
	$("#loanListTab").tabs({
		onSelect : function(data) {
			var tabHeight2 = $("#loanListTab").height();
			var heightMa2 = 50 + tabHeight2;
			$('#loanList').resizeDataGrid(heightMa2, 20, 0, 0);
		}
	});
	// 合同打印初始化dialog
	$("#print_Dialog").show().dialog({
		title : "打印合同",
		closed : true,
		modal : true,
		inline : false,
		width : 500,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "cencleButton",
			text : "取消",
			handler : function() {
				$("#print_Dialog").dialog('close');
			}
		} ]

	});
});

// 放款确认
function LoanConfirm() {
	var rows = $("#loanList").datagrid("getSelected");
	console.info(rows);
	var clientid = rows.creditapplicationDESId;
	// 查看申请单的 加密id
	var creditApplicationEscId = rows.laonDESId;
	$('#openCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
			+ "&signIp=" + DESIp + "";
	$('#applicationCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + creditApplicationEscId
			+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
	var creditApplicationId = rows.creditapplicationId;
    creditapplicationId = rows.creditapplicationId;
	$("#LoanConfirm").dialog({
		closed : false,
		title : "放款确认",
		draggable : true,
		maximizable : true,
		modal : true,
		buttons : [ {
			text : "确定",
			id : "loanConfirmButton",
			handler : function() {

				$("#loanReturnButton").linkbutton("disable");
				$("#loanConfirmButton").linkbutton("disable");
				if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
					$("#loanReturnButton").linkbutton("enable");
					$("#loanConfirmButton").linkbutton("enable");
					$.messager.alert("消息", "请填写备注！");
					return;
				}

				$("#loanPerson").val($("#loanPerson1").val());
				$("#remarkComment").val($("#remarkComment1").val());
                var rows = customer_datagrid.datagrid('getRows');
                for (var i = 0; i < rows.length; i++) {
                    if (!customer_datagrid.datagrid('validateRow', i)) {
                        $.messager.alert("提示", "请将检查信息填写完成！");
                        $("#loanReturnButton").linkbutton("enable");
                        $("#loanConfirmButton").linkbutton("enable");
                        return;
                    } else {
                        customer_datagrid.datagrid('endEdit', i);
                    }
                }
                if (rows && rows.length > 0) {
                    $.messager.alert('提示', '合同发现不合规点，请确认！');
                    $("#loanReturnButton").linkbutton("enable");
                    $("#loanConfirmButton").linkbutton("enable");
                    return;
                }
				url = serverName + "/GroupLoanRegist/loanConfirm.do?registStatus=1";
				ajaxSubmit(url, $("#loanConfirmForm").serializeObject(), function(result) {
					if (result.success) {
						$("#remarkComment1").val("");
						$("#LoanConfirm").dialog("close");
						$("#loanReturnButton").linkbutton("enable");
						$("#loanConfirmButton").linkbutton("enable");
						$.messager.show({
							title : "消息",
							msg : "操作成功"
						});
						$("#loanList").datagrid("reload", {});
					} else {
						$("#remarkComment1").val("");
						$("#LoanConfirm").dialog("close");
						$("#loanReturnButton").linkbutton("enable");
						$("#loanConfirmButton").linkbutton("enable");
						$("#loanList").datagrid("reload", {});
						$.messager.alert("消息", result.msg);
					}
				});
			}
		}, {
			text : "回退",
			id : "loanReturnButton",
			handler : function() {

				$("#loanReturnButton").linkbutton("disable");
				$("#loanConfirmButton").linkbutton("disable");
				if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
					$("#loanReturnButton").linkbutton("enable");
					$("#loanConfirmButton").linkbutton("enable");
					$.messager.alert("消息", "请填写备注！");
					return;
				}
				$("#loanPerson").val($("#loanPerson1").val());
				$("#remarkComment").val($("#remarkComment1").val());
                /*备注和意见：增加合规检查项信息，如 1.错误文件 “某文件” ，检查点 “某检查点”*/
                var rows = customer_datagrid.datagrid('getRows');
                for (var i = 0; i < rows.length; i++) {
                    if (!customer_datagrid.datagrid('validateRow', i)) {
                        $.messager.alert("提示", "请将检查信息填写完成！");
                        return;
                    } else {
                        customer_datagrid.datagrid('endEdit', i);
                    }
                }
                var inserted = customer_datagrid.datagrid('getChanges', "inserted");
                var effectRow = new Object();
                if (inserted.length) {
                    effectRow["inserted"] = JSON.stringify(inserted);
                }
                if (inserted.length != 0) {
                    /*保存客服检查*/
                    ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $("#remarkComment").val() + '&checkType=2', effectRow, function (ret) {
                        if (ret.success) {
                            console.info('--->>>保存客服检查成功!<<<---');
                        } else {
                            console.info('--->>>保存客服检查失败!<<<---');
                        }
                    });
                    /*附加到备注和意见之前*/
                    var arrResult = {};
                    for (var i = 0, n = inserted.length; i < n; i++) {
                        var item = inserted[i];
                        arrResult[ item.errorFile + " - " + item.checkPoint ] = item;
                    }
                    var i = 0, inserted_final = [];
                    for (var item in arrResult) {
                        inserted_final[i++] = arrResult[item];
                    }
                    var cks = '';
                    for (var i = 0; i < inserted_final.length; i++) {
                        cks += (i + 1) + '.错误文件 "' + (errorFile_compliance[inserted_final[i].errorFile].codeVlue + '"，检查点 "' + checkpoint_loan[inserted_final[i].checkPointLoan].codeVlue + '"\n');
                    }
                    if (cks) {
                        $("#remarkComment").val(cks + $("#remarkComment").val());
                    }
                }
				url = serverName + "/GroupLoanRegist/loanConfirm.do?registStatus=2";
				ajaxSubmit(url, $("#loanConfirmForm").serializeObject(), function(result) {
					if (result.success) {
						$("#remarkComment1").val("");
						$("#LoanConfirm").dialog("close");
						$("#loanReturnButton").linkbutton("enable");
						$("#loanConfirmButton").linkbutton("enable");
						$.messager.show({
							title : "消息",
							msg : "操作成功"
						});
						$("#loanList").datagrid("reload", {});
					} else {
						$("#remarkComment1").val("");
						$("#LoanConfirm").dialog("close");
						$("#loanReturnButton").linkbutton("enable");
						$("#loanConfirmButton").linkbutton("enable");
						$("#loanList").datagrid("reload", {});
						$.messager.alert("消息", result.msg);
					}
				});
			}

		}, {
			text : "关闭",
			handler : function() {
				$("#LoanConfirm").dialog("close");
			}
		} ]
	});
	initComfirmPage(rows, creditApplicationId);
	$("#LoanConfirm").dialog("maximize");
	$("#layoutZ").layout("resize");
}

// 对登记页面的操作
function editAccount() {
	$("#accountEdit").show();
	$("#showAcount").show();
	$("#accountShow").hide();
	$("#buttonEdit").hide();
	$("#buttonCongirm").show();
	$("#zhanghu").val("0");

	var defultAcount = $("#accountName").combobox("getValue");
	$("#accountName").combobox({
		width : 120,
		url : serverName + "/creditgroup/selectBorrowerFamily.do?creditapplicationId=" + $("#creditapplicationIdDengji").val(),
		mode : 'remote',
		editable : false,
		required : true,
		valueField : "name",
		textField : "name",
		value : defultAcount,
		onSelect : function(data) {
			$("#credentialsNumber").val(data.credentialsNumber);
		}
	});
}
// 修改账户信息确定按钮
function showAcount() {
	if ($("#accountEditForm").form("validate")) {
		url = serverName + "/accountInfo/addOrUpdateReturn.do";
		ajaxSubmit(url, $("#accountEditForm").serializeObject(), function(result) {
			// 不可以修改的账户
			$("#showAccountForm").form("load", result);
			// 可以修改的账户
			$("#accountEditForm").form("load", result);

			// 卡折类型
			$("#cardFlagEnable").val($("#cardFlag").combobox("getText"));
			url = serverName + "/NSC/selectByCode.do";
			ajaxSubmit(url, {
				cityCode : $("#provinceId").combobox("getValue"),
				dfg : "qwe"
			}, function(result) {
				$("#provinceIdEnable").attr("value", result.cityName);
			});
			ajaxSubmit(url, {
				cityCode : $("#districtId").combobox("getValue")
			}, function(result) {
				$("#districtIdEnable").attr("value", result.cityName);
			});
			ajaxSubmit(url, {
				cityCode : $("#cityId").combobox("getValue")
			}, function(result) {
				$("#cityIdEnable").attr("value", result.cityName);
			});
			$("#accountShow").show();
			$("#accountEdit").hide();
			$("#showAcount").hide();
			$("#buttonEdit").show();
			$("#buttonCongirm").hide();
			// 判断账户是否在修改
			$("#zhanghu").val("1");
		});
	} else {
		$.messager.alert("消息", "请填写完整！");
	}
}
function dengji() {
	var rows = $("#loanList").datagrid("getSelected");
	var clientid = rows.creditapplicationDESId;
	var cid = rows.creditapplicationId + "CM_LOAN";
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1, function(result) {
		if (result) {
			var signTime = result[0];
			var signIp = result[1];
			$('#openCM')[0].src = cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + clientid
					+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=" + userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
			$('#cm').dialog('open');
		} else {
			$.messager.alert("提示信息", "服务异常，请稍后重试");
		}
	});
	var loanRegistration = $("#loanRegistration").dialog({
		// closed:false,
		modal : true,
		buttons : [ {
			text : "确定",
			id : "loanButton",
			handler : function() {
				$("#loanButton").linkbutton("disable");
				var url = serverName + "/creditgroup/getImgAmount.do";
				var imgAmount;
				$.ajax({
					type : "post",
					async : false,
					url : url,
					data : {
						creditAppId : cid
					},
					success : function(result) {
						imgAmount = result;
					}
				});
				if (imgAmount == 0) {
					$("#loanButton").linkbutton("enable");
					$.messager.alert("消息", "请先上传业务资料");
					return;
				} else if (imgAmount < 0) {
					$("#loanButton").linkbutton("enable");
					$.messager.alert("消息", "上传服务异常,请稍后重试");
					return;
				}
				if ($("#zhanghu").val() == "0") {
					$("#loanButton").linkbutton("enable");
					$.messager.alert("消息", "请先保存账户信息");
					return;
				}
				if ($("#loanRefistration").form("validate")) {
					var data = $("#loanRefistration").serialize();
					var obj = new Object();
					obj["loanRegistList"] = JSON.stringify($("#detailRegist").datagrid("getRows"));
					obj["groupLoanRegist"] = JSON.stringify($("#loanRefistration").serializeObject());
					obj["time"] = $("#loanTime").datebox('getValue');
					url = serverName + "/GroupLoanRegist/loanRegist.do?";
					ajaxSubmit(url, obj, function(result) {
						if (result.success != undefined) {
							$("#loanButton").linkbutton("enable");
							$.messager.show({
								title : "消息",
								msg : "操作失败！"
							});
							$("#loanList").datagrid("reload", {});
							return;
						}

						if (result) {
							$("#loanRefistration").form("clear");
							$("#loanRegistration").dialog("close");
							$.messager.show({
								title : "消息",
								msg : "操作成功！"
							});
							$("#loanButton").linkbutton("enable");
							$("#loanList").datagrid("reload", {});
						}
						$("#loanButton").linkbutton("enable");
					});
				} else {
					$("#loanButton").linkbutton("enable");
				}
			}
		}, {
			text : "关闭",
			handler : function() {
				$("#loanRefistration").form("clear");
				$("#loanRegistration").dialog("close");
			}
		} ]
	});

	// 放款登记页面 放款确认历史
	$("#historyLoan").datagrid({
		url : serverName + "/GroupLoanRegist/searchConfirm.do?creditApplicationId=" + rows.creditapplicationId,
		striped : true,
		singleSelect : true,
		width : 600,
		columns : [ [ {
			title : '操作人',
			field : 'loanPerson',
			width : '170'
		}, {
			title : '操作时间',
			field : 'loanConfirmTime',
			width : '170'
		}, {
			title : '备注 和意见',
			field : 'remarkComment',
			width : '250'
		} ] ]
	});
	url = serverName + "/accountInfo/selectById.do";
	var accountInfoId = 0;
	if (rows.accountInfoId != null && rows.accountInfoId != "" && rows.accountInfoId != undefined) {
		accountInfoId = rows.accountInfoId;
	}
	ajaxSubmit(url, {
		accountInfoId : accountInfoId
	}, function(data) {
		$("#accountEdit").hide();
		$("#showAcount").hide();
		var payway = $("#paywayRegristraion").val();
		if (payway == "1") {
			$("#buttonEdit").hide();
		} else {
			$("#buttonEdit").show();
			$("#buttonCongirm").hide();
		}
		// 基础信息
		$("#loanRefistration").form("load", rows);
		// 账户信息
		if ($("#businessTypeRegristration").val() == 0) {
			$("#showAccountForm").hide();
			$("#buttonEdit").hide();
			$("#buttonCongirm").hide();
		} else {
			$("#showAccountForm").show();
		}
		$("#showAccountForm").form("load", data);
		$("#accountEditForm").form("load", data);
		// 修改省市区
		showArea("provinceId", "cityId", "districtId");
		url = serverName + "/NSC/selectByCode.do";
		ajaxSubmit(url, {
			cityCode : $("#provinceId").combobox("getValue")
		}, function(result) {
			if (result != null) {
				$("#provinceIdEnable").attr("value", result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : $("#districtId").combobox("getValue")
		}, function(result) {
			if (result != null) {
				$("#districtIdEnable").attr("value", result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : $("#cityId").combobox("getValue")
		}, function(result) {
			if (result != null) {
				$("#cityIdEnable").attr("value", result.cityName);
			}
		});
		$("#cardFlagEnable").val(($("#cardFlagEnable").val() == "0") ? "卡" : "存折");
		$("#paywayRegristraion").val(($("#businessTypeRegristration").val() == "1") ? "个人" : "公司");
// $("#groupNumberRegistration").val(rows.groupNumber);
// $("#loanOfficerName").val(rows.loanOfficerName);
		// 个人借款详细信息
		$("#detailRegist").datagrid({
			url : serverName + "/GroupLoanRegist/searchDetail.do?creditApplicationId=" + rows.creditapplicationId,
			striped : true,
			singleSelect : true,
			fitColumns : true,
			width : 600,
			columns : [ [ {
				title : '借款人',
				field : 'borrowerName',
				width : '200'
			}, {
				title : '放款金额',
				field : 'loanAmount',
				width : '200'
			}, {
				title : '实发金额',
				field : 'realAmount',
				width : '200'/*
								 * , editor:{ type:"numberbox", option:{ request:true } }, onblur:function(value){ alert(value); },
								 * formatter:function(value,rowData,rowIndex){ return "<input name='realAmount' class='easyui-numberbox'
								 * onkeyup='validateNumber(this);' onblur='validate(this);' value="+ value +">"; }
								 */

			} ] ]
		/*
		 * , onLoadSuccess:function(){ var length = $("#detailRegist").datagrid("getRows").length; for(var i = 0;i < length;i++){
		 * $("#detailRegist").datagrid("beginEdit",i); var ed = $("#detailRegist").datagrid("getEditors",i); for (var j = 0; j < ed.length; j++) { var
		 * e = ed[j]; $(e.target).bind('focus', function() {}); $(e.target).bind('blur', function() { }); } } }
		 */
		});
	});
	loanRegistration.dialog("open");
}

function validate(text) {
	var row = $("#detailRegist").datagrid("getSelected");
	if (text.value == "" || text.value == null) {
		$.messager.alert("消息", "实际放款不能为空！");
		text.value = row.loanAmount;
	}
	if (!((/^[1-9][0-9]*(\.[0-9]+)?$/).test(text.value))) {
		$.messager.alert("消息", "只能输入非零数字！");
		text.value = row.loanAmount;
	}
	if (text.value > row.loanAmount) {
		$.messager.alert("消息", "实际放款不能大于放款金额！");
		text.value = row.loanAmount;
	}

	row.realAmount = text.value;
}
// 对登记页面的操作 结束

// 失败登记
function LoanRegistrationFail() {
	var dataGrid = $("#loanList").datagrid("getSelected");
	$("#loanFail").form("load", dataGrid);
// $("#auditStatus1").combobox("select","06");;
	$("#LoanRegistrationFail").dialog({
		closed : false,
		modal : true,
		buttons : [ {
			text : "确定",
			id : "loanFailButton",
			handler : function() {
				$("#loanFailButton").linkbutton("disable");
				if ($("#loanFail").form("validate")) {
					url = serverName + "/creditgroup/loanregistRevocation.do";
					ajaxSubmit(url, $("#loanFail").serialize(), function(result) {
						if (result.success) {
							$.messager.show({
								title : "消息",
								msg : "操作成功!"
							});
							$("#loanFailButton").linkbutton("enable");
							$("#LoanRegistrationFail").dialog("close");
							$("#loanList").datagrid("reload", {});
						} else {
							$("#loanFailButton").linkbutton("enable");
							$.messager.show({
								title : "消息",
								msg : "操作失败!"
							});
						}
					});
				} else {
					alert("请输入必输项！");
					$("#loanFailButton").linkbutton("enable");
				}
			}
		}, {
			text : "关闭",
			handler : function() {
				$("#loanFail").form("clear");
				$("#LoanRegistrationFail").dialog("close");
			}
		} ]
	});
}
/**
 * 合同打开dialog
 */
function contract() {
	$("#print_Dialog").dialog('open');
	$("#addButton").linkbutton('enable');
	var rows = $("#loanList").datagrid("getSelected");
	var creditapplicationId = rows.creditapplicationId;
	$("#print_creditapplicationId").val(creditapplicationId);
    /*弹窗打开时检查缓存距当前时间*/
    checkTimeLimit(creditapplicationId);
}
/**
 * 打印合同
 */
function print_contract() {
	if ($("#print_form").form("validate")) {
		$("#addButton").linkbutton('disable');
		// 加入打折判断
		ajaxSubmit(serverName + "/discountConfigurationController/discountRule.do", {
			creditapplicationId : $("#print_creditapplicationId").val()
		}, function(a) {
			if (a.success) {
				// 加入打折的校验
				ajaxSubmit(serverName + "/discountConfigurationController/checkDiscountConfiguration.do", {
					creditapplicationId : $("#print_creditapplicationId").val()
				}, function(r) {
					if (r.success || r.success == "true") {
						// 调用没有出问题
						if (r.alert == "alert") {
							$.messager.confirm("提示", r.msg, "info");
//							$.messager.confirm("提示", r.msg, function(b) {
//								if (b) {
//									$("#print_Dialog").dialog('close');
//									var print_loan_date = $("#print_loan_date").datebox("getValue");
//									var creditapplicationId = $("#print_creditapplicationId").val();
//									var url = serverName + "/protocolMappingController/saveAndDownProtocol.do";
//									ajaxSubmit(url, {
//										"creditapplicationId" : creditapplicationId,
//										"loanDate" : print_loan_date
//									}, function(data) {
//										if (data && data.success) {
//											window.location.href = data.msg;
//											$("#print_Dialog").dialog('close');
//										} else {
//											$("#addButton").linkbutton('enable');
//											$("#print_Dialog").dialog('open');
//										}
//									});
//								} else {
//									$("#addButton").linkbutton('enable');
//								}
//							});
							$("#addButton").linkbutton('enable');
						} else {
//							$("#print_Dialog").dialog('close');
							var print_loan_date = $("#print_loan_date").datebox("getValue");
							var creditapplicationId = $("#print_creditapplicationId").val();
							var url = serverName + "/protocolMappingController/saveAndDownProtocol.do";
							ajaxSubmit(url, {
								"creditapplicationId" : creditapplicationId,
								"loanDate" : print_loan_date
							}, function(data) {
								if (data && data.success) {
									window.location.href = data.msg;
                                    setCookie(creditapplicationId, new Date().getTime());
									$("#print_Dialog").dialog('close');
                                    $("#btn").linkbutton('disable');
								} else {
									$("#addButton").linkbutton('enable');
									$("#print_Dialog").dialog('open');
								}
							});
						}
					} else {
						// 调用出问题了
						$("#addButton").linkbutton('enable');
						$.messager.alert("消息", r.msg, "info");
					}

				});
			} else {
				$("#addButton").linkbutton('enable');
				$.messager.alert("消息", a.msg, "info");
			}

		});

	}

}
function showgaoji() {
	$("#sousuo").hide();
	$("#gaoji").show();
}
function showsousuo() {
	$("#sousuo").show();
	$("#gaoji").hide();
}

function concel() {
	$("#LoanRegistrationFail").dialog("close");
}

function searchAdvanced() {
    var companyNames = $("#companyName").combotree("getValues").join(",");
	$("#loanList").datagrid("load", {
		companyId : companyNames,
		businessType : $("#businessType").combobox("getValue"),
		auditStatus : $("#auditStatus").combobox("getValue"),
		groupNumber : $.trim($("#groupNumber").val()),
		groupName : $.trim($("#groupName").val()),
		beginLoanDate : $("#beginLoanDate").datebox("getValue"),
		endLoanDate : $("#endLoanDate").datebox("getValue"),
		loanConfirmDateBegin : $("#loanConfirmDateBegin").datebox("getValue"),
		loanConfirmDateEnd : $("#loanConfirmDateEnd").datebox("getValue")
	});
}

function searchFussy() {
	$("#loanList").datagrid("load", {
		fuzzyValue : $.trim($("#fuzzyValue").val())
	});
}

function searchCancel1() {
	$("#businessType").combobox("setValue", "");
//	$("#companyName").combobox("setValue", "");
    $("#companyName").combotree("setValues","");
	$("#auditStatus").combobox("setValue", "");
	$("#groupNumber").val("");
	$("#groupName").val("");
	$("#beginLoanDate").datebox("clear");
	$("#endLoanDate").datebox("clear");
	$("#loanConfirmDateBegin").datebox("clear");
	$("#loanConfirmDateEnd").datebox("clear");
    searchAdvanced();
}

function searchCancel() {
	$("#fuzzyValue").val("");
}

/** 初始化放款确认页面* */
function initComfirmPage(rows, creditApplicationId) {
	url = serverName + "/accountInfo/selectById.do";
	var accountInfoId = 0;
	if (rows.accountInfoId != null && rows.accountInfoId != "" && rows.accountInfoId != undefined) {
		accountInfoId = rows.accountInfoId;
	}
	ajaxSubmit(url, {
		accountInfoId : accountInfoId
	}, function(data) {
		console.info(data);
		// 基础信息
		$("#showAccountFormqr").form("load", data);
		$("#cardFlagEnable1").val(($("#cardFlagEnable1").val() == "0") ? "卡" : "存折");
		$("#paywayRegristraion1").val(($("#businessTypeRegristration").val() == "1") ? "个人" : "公司");
		url = serverName + "/NSC/selectByCode.do";
		ajaxSubmit(url, {
			cityCode : data.provinceId
		}, function(result) {
			if (result != null) {
				$("#provinceIdEnable1").attr("value", result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : data.districtId
		}, function(result) {
			if (result != null) {
				$("#districtIdEnable1").attr("value", result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : data.cityId
		}, function(result) {
			if (result != null) {
				$("#cityIdEnable1").attr("value", result.cityName);
			}
		});
	});

	url = serverName + "/GroupLoanRegist/searchForConfirm.do";
	var groupLoanRegistId;
	$("#remarkComment1").val("");
	ajaxSubmit(url, {
		creditapplicationId : creditApplicationId,
		registStatus : "0"
	}, function(result) {
		$("#loanConfirmForm").form("load", result);
		groupLoanRegistId = $("#groupLoanRegistId").val();
	});
	// 历史备注
	$("#historyRemark").datagrid({
		url : serverName + "/GroupLoanRegist/searchHistory.do?creditApplicationId=" + creditApplicationId,
		striped : true,
		singleSelect : true,
		fitColumns : true,
		width : 600,
		columns : [ [ {
			title : '操作人',
			field : 'loanPerson',
			width : 200
		}, {
			title : '操作时间',
			field : 'loanConfirmTime',
			width : 200
		}, {
			title : '备注和意见',
			field : 'remarkComment',
			width : 200
		} ] ]
	});
	// 放款登记详细
	$("#detailConfirm").datagrid({
		url : serverName + "/GroupLoanRegist/searchDetail.do?creditApplicationId=" + creditApplicationId,
		striped : true,
		singleSelect : true,
		fitColumns : true,
		width : 600,
		columns : [ [ {
			title : '借款人',
			field : 'borrowerName',
			width : 200
		}, {
			title : '放款金额',
			field : 'loanAmount',
			width : 200
		}, {
			title : '实发金额',
			field : 'realAmount',
			width : 200
		} ] ]
	});
    /*客服检查*/
    ajaxSubmit(serverName + '/ComplianceController/selectDataDictionaryBySections.do?sections=' + sections, {}, function (ret) {
        if (ret) {
            if (ret.errorFile_compliance.length > 0) {
                errorFile_compliance = JSON.parse(ret.errorFile_compliance);
            }
            if (ret.checkpoint_loan.length > 0) {
                checkpoint_loan = JSON.parse(ret.checkpoint_loan);
            }
            if (ret.operator) {
                operator = ret.operator;
            }
        }
        ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
            customer_datagrid = $('#check_customer').datagrid({
                scrollbarSize: 0,
                fitColumns: true,
                singleSelect: true,
                columns: [
                    [
                        {title: '错误文件', field: 'errorFile', width: 180, align: 'center', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeKey',
                                textField: 'codeVlue',
                                data: errorFile_compliance,
                                required: true,
                                panelHeight: 'auto',
                                editable: false
                                /*onSelect: function (rowData) {
                                    var target = customer_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'checkPointLoan' }).target;
                                    target.combobox('clear');
                                    var url = '/ComplianceController/getDataDictionaryBySectionAndParentId.do?section=checkpoint_loan&parentId=' + rowData.codaTableId;
                                    target.combobox('reload', url);
                                }*/
                            }
                        }, formatter: function (value) {
                            for (var i = 0; i < errorFile_compliance.length; i++) {
                                if (errorFile_compliance[i].codeKey == value) {
                                    return errorFile_compliance[i].codeVlue;
                                }
                            }
                            return value;
                        }
                        },
                        {title: '检查点', field: 'checkPointLoan', width: 220, align: 'center', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeKey',
                                textField: 'codeVlue',
                                data: checkpoint_loan,
                                required: true,
                                panelHeight: 'auto',
                                editable: false
                            }
                        }, formatter: function (value) {
                            for (var i = 0; i < checkpoint_loan.length; i++) {
                                if (checkpoint_loan[i].codeKey == value) {
                                    return checkpoint_loan[i].codeVlue;
                                }
                            }
                            return value;
                        }},
                        {title: '操作人', field: 'operatorName', width: 80, align: 'center', formatter: function (value) {
                            if (!value) {
                                return operator;
                            }
                            return value;
                        }},
                        {title: '操作', field: 'auditStatus', width: 100, align: 'center', formatter: function (value, row, index) {
                            return '<a style="color: red" onclick="deleteRow(this);">删除</a>';
                        }},
                        {title: '检查项主键ID', field: 'complianceCheckItemId', hidden: true}
                    ]
                ]
            });
            if (ret) {
                customer_datagrid.datagrid('loadData', ret.customer);
                if (ret.remark_customer && ret.remark_customer.checkRemark) {
                    $('#complianceCheckId').val(ret.remark_customer.complianceCheckId);
                }
            }
        });
    });
}
/*获取行索引*/
function getRowIndex(target) {
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
/*添加行*/
function addRow() {
    var rows = customer_datagrid.datagrid('getRows');
    customer_datagrid.datagrid('appendRow', {});
    customer_datagrid.datagrid('beginEdit', rows.length - 1);
    customer_datagrid.datagrid('selectRow', rows.length - 1);
}
/*删除行*/
function deleteRow(target) {
    customer_datagrid.datagrid('deleteRow', getRowIndex(target));
}
// 放款登记查看
function searchGroupRegist() {
	var rowdata = $("#loanList").datagrid("getSelected");
	var clientid = rowdata.creditapplicationDESId;
	$('#openCMDJView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime="
			+ DESNow + "&signIp=" + DESIp + "";
	$("#LoanLook").dialog({
		closed : false,
		modal : true,
		buttons : [ {
			text : "关闭",
			handler : function() {
				$("#registLook").form("clear");
				$("#LoanLook").dialog("close");
			}
		} ],
		onClose : function() {
			$("#registLook").form("clear");
		}
	});
	// 设置付款方式
	$("#paywayLook").val((rowdata.businessType == 0) ? "公司" : "个人");

	var registStatus = 0;
	if ((rowdata.auditStatus == "10" && rowdata.businessType == '0') || rowdata.auditStatus == "21" || rowdata.auditStatus == "06") {
		$("#addtr").html("");
	} else if (rowdata.auditStatus == "11") {
		registStatus = 0;
		$("#addtr").html(
				'<tr height="40"><td align="right" width = "90">放款日期：</td>' + '<td align="left" width = "150">' + '<input name="loanTime" readonly="readonly"/>' + '</td><td width="200"></td>'
						+ '<td align="right" width = "90">协议编号：</td>' + '<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td></tr>');
// $().appendTo($("#registDetail"));

	} else if (rowdata.auditStatus == "14") {
		registStatus = 2;
		$("#addtr").html(
				'<tr height="30"><td align="right" width = "91">  放款日期：</td>' + '<td align="left" width = "151">' + '<input name="loanTime" readonly="readonly"/>' + '</td><td width="201"></td>'
						+ '<td align="right" width = "90">  协议编号：</td>' + '<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td></tr>'
						+ '<tr height="30"><td align="right" width = "90">   确认人：</td><td align="left" width = "150">' + '<input name="loanPerson" readonly="readonly"/>'
						+ '</td><td width="200"></td><td align="right" width = "90">回退时间：</td>' + '<td align="left" width = "150"><input readonly="readonly" name="loanRegistDate"/></td></tr>');
// $().appendTo($("#registDetail"));
	} else {
		registStatus = 1;
		$("#addtr").html(
				'<tr height="30"><td align="right" width = "91">  放款日期：</td>' + '<td align="left" width = "151">' + '<input name="loanTime" readonly="readonly"/>' + '</td><td width="201"></td>'
						+ '<td align="right" width = "90">   协议编号：</td>' + '<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td></tr>'
						+ '<tr height="30"><td align="right" width = "90">   确认人：</td><td align="left" width = "150">' + '<input name="loanPerson" readonly="readonly"/>'
						+ '</td><td width="200"></td><td align="right" width = "90">确认时间：</td>' + '<td align="left" width = "150"><input readonly="readonly" name="loanRegistDate"/></td></tr>');
// $().appendTo($("#registDetail"));

	}

	url = serverName + "/GroupLoanRegist/searchForLook.do?creditApplicationId=" + rowdata.creditapplicationId + "&registStatus=" + registStatus;
	ajaxSubmit(url, {}, function(result) {
// if(result.registStatus == '0'){
// alert(result.registStatus );
// $('<tr height="40"><td align="right" width = "90">放款日期1：</td> <td align="left" width = "150"><input name="loanTime" readonly="readonly" /></td>'
// +'<td width="200"></td><td align="right" width = "90">协议编号1：</td>'
// +'<td align="left" width = "150"> <input name="protocolID" readonly="readonly" /> </td></tr>'
// ).appendTo($("#registLookTable"));
// // $("#registLookTable").appendChild( $("#dengjiLater"));
// }
		$("#registLook").form("load", result);
	});

	// 设置账户信息
	if (rowdata.businessType == "0") {
		$("#showAccountFormLook").hide();
	} else {
		$("#showAccountFormLook").show();
		url = serverName + "/accountInfo/selectById.do", ajaxSubmit(url, {
			accountInfoId : rowdata.accountInfoId
		}, function(result) {
			$("#showAccountFormLook").form("load", result);
			$("#cardFlagLook").val((result.cardFlag == "0") ? "卡" : "存折");
			url = serverName + "/NSC/selectByCode.do";
			ajaxSubmit(url, {
				cityCode : $("#cityIdLook").val()
			}, function(result) {
				$("#cityIdLook").attr("value", result.cityName);
			});
			ajaxSubmit(url, {
				cityCode : $("#provinceIdLook").val()
			}, function(result) {
				$("#provinceIdLook").attr("value", result.cityName);
			});
			ajaxSubmit(url, {
				cityCode : $("#districtIdLook").val()
			}, function(result) {
				$("#districtIdLook").attr("value", result.cityName);
			});
		});

	}
	// 借款详细信息
	$("#detailLook").datagrid({
		url : serverName + "/GroupLoanRegist/searchDetail.do?creditApplicationId=" + rowdata.creditapplicationId,
		striped : true,
		singleSelect : true,
		width : 600,
		columns : [ [ {
			title : '借款人',
			field : 'borrowerName',
			width : '170'
		}, {
			title : '放款金额',
			field : 'loanAmount',
			width : '170'
		}, {
			title : '实发金额',
			field : 'realAmount',
			width : '250'
		} ] ]
	});
	// 历史备注
	$("#historyRemarkLook").datagrid({
		url : serverName + "/GroupLoanRegist/searchConfirm.do?creditApplicationId=" + rowdata.creditapplicationId,
		striped : true,
		singleSelect : true,
		width : 600,
		columns : [ [ {
			title : '操作人',
			field : 'loanPerson',
			width : '170'
		}, {
			title : '操作时间',
			field : 'loanConfirmTime',
			width : '170'
		}, {
			title : '备注 和意见',
			field : 'remarkComment',
			width : '250'
		} ] ]
	});
    /*客服检查*/
    ajaxSubmit(serverName + '/ComplianceController/selectDataDictionaryBySections.do?sections=' + sections, {}, function (ret) {
        if (ret) {
            if (ret.errorFile_compliance.length > 0) {
                errorFile_compliance = JSON.parse(ret.errorFile_compliance);
            }
            if (ret.checkpoint_loan.length > 0) {
                checkpoint_loan = JSON.parse(ret.checkpoint_loan);
            }
        }
        ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + rowdata.creditapplicationId, {}, function (ret) {
            $('#check_customer_view').datagrid({
                scrollbarSize: 0,
                fitColumns: true,
                singleSelect: true,
                columns: [
                    [
                        {title: '错误文件', field: 'errorFile', width: 100, align: 'center'},
                        {title: '检查点', field: 'checkPointLoan', width: 200, align: 'center'},
                        {title: '操作人', field: 'operatorName', width: 100, align: 'center'},
                        {title: '操作时间', field: 'operateDate', width: 150, align: 'center'}
                    ]
                ]
            });
            if (ret) {
                $('#check_customer_view').datagrid('loadData', ret.customer);
            }
        });
    });
}

function showArea(province, city, district) {
	var provinceid = $("#" + province).combobox("getValue");

	var province = $("#" + province).combobox({
		// required : true,
		editable : false,
		url : serverName + '/NSC/list.do',
		textField : 'cityName',
		valueField : 'cityCode',
		mode : 'remote',
		delay : 500,
		width : '120',
		value : provinceid,
		onSelect : function(value) {
			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				width : '120',
				value : '',
				onSelect : function(value) {
					$("#" + district).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						width : '120',
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
			panelHeight : '200',
			delay : 500,
			width : '120',
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
			panelHeight : '200',
			delay : 500,
			width : '120',
			value : districtId
		});
	}
}

/*打印合同添加时间限制*/
function checkTimeLimit(specificCondition) {
    var clickTime = getCookie(specificCondition);
    if (clickTime) {
        var timeSpace = new Date().getTime() - clickTime;
        var _timeSpace = timeSpace / 1000;
        if (_timeSpace < 30) {
            $("#btn").linkbutton('disable');

            /*调用定时器，显示倒计时*/
            destroyAllTimer();
            $('#timer').html('');

            var count = ((30 - _timeSpace) | 0);
            var counter = setInterval(function () {
                if (count <= 1) {
                    clearInterval(counter);
                    enableAndEliminate();
                    return;
                }
                count = count - 1;
                $('#timer').html("(" + count + "秒后可再次下载)");
            }, 1000);

        } else {
            destroyAllTimer();
            enableAndEliminate();
        }
    } else {
        destroyAllTimer();
        enableAndEliminate();
    }
}

/*按钮启用并清除显示信息*/
function enableAndEliminate(){
    $("#btn").linkbutton('enable');
    $('#timer').html('');
}

/*销毁所有定时器*/
function destroyAllTimer(){
    for (var i = 1; i < 99999; i++) {
        window.clearInterval(i);
    }
}

/*缓存存取*/

//setCookie
function setCookie(cookieName, cookieValue) {
    document.cookie = cookieName + "=" + escape(cookieValue);
}

//get cookie
function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) {
        return parts.pop().split(";").shift();
    }
}

/*
 * function changeDRW(accInfoId) { ajaxSubmit(serverName + "/receivablesList/changeDefaultReturnmentWay.do", { creditapplicationId : creditAppId,
 * param : paramType, p : 0, accountInfoId : accInfoId }, function(r) { if (r == true) { $("#receivablesList").datagrid('reload'); $.messager.show({
 * showType : "show", timeout : 5000, title : "消息", msg : "更改默认还款方式成功" }); } else { $("#receivablesList").datagrid('reload'); $.messager.show({
 * showType : "show", timeout : 5000, title : "消息", msg : "更改默认还款方式失败" }); } }); }
 * 
 * function change(paramId, param) { cardInfo(paramId, param); $("#cardDataGrid").datagrid({ url : "", method : 'post', loadMsg : "数据装载中....",
 * fitColumns : true, nowrap : false, striped : true, singleSelect : true, columns : [ [ { field : 'accountInfoId', title : '卡信息ID', width : 100,
 * hidden : true }, { field : 'branchName', title : '分公司名称', width : 100, hidden : false }, { field : 'bankName', title : '银行名称', width : 100, hidden :
 * false }, { field : 'accountName', title : '账户名称', width : 100, hidden : false }, { field : 'account', title : '账号', width : 100, hidden : false }, {
 * field : 'accountType', title : '类型', width : 100, formatter : function(value) { if (value == "0") { return "分公司账户"; } else if (value == "1") {
 * return "个人账户"; } else { return "Null"; } }, hidden : false } ] ], onSelect : function(rowIndex, rowData) { changeDRW(rowData.accountInfoId);
 * $("#cardDialog").dialog('close'); // 把个人卡传过去 } }); $("#cardDialog").dialog({ buttons : [ { text : "取消", handler : function() {
 * $("#cardDialog").dialog('close'); cancleGlag = false;
 * $("#defaultReturnmentWay").combobox("setValue",(1-$("#defaultReturnmentWay").combobox("getValue"))); cancleGlag = true; } } ], onBeforeClose :
 * function() { // 每次关闭前都销毁dialog中的datagrid var dRows = $("#cardDataGrid").datagrid('getRows'); companyCard.length = 0; personalCard.length = 0; var
 * lll = dRows.length; if (dRows.length == 0) { } else { for ( var i = 0; i < lll; i++) { $("#cardDataGrid").datagrid('deleteRow', 0); } } } }); }
 * 
 * function cardInfo(id, param) { creditAppId = id; paramType = param;
 * 
 * ajaxSubmit(serverName + "/receivablesRegistration/selectCardInfo.do", { creditapplicationId : id }, function(r) {
 * 
 * cardObject = r; analysisCardType(id, r, param); }); } function analysisCardType(id, cardList, param) { var length = cardList.length; for ( var i =
 * 0; i < length; i++) { var accountType = cardList[i].accountType; if ("0" == accountType) { // 公司账户 companyCard.push(cardList[i]); } else if ("1" ==
 * accountType) { // 个人账户 personalCard.push(cardList[i]); } } // tempCard(companyCard, personalCard); selectCard(id, param); } function selectCard(id,
 * param) { if (param == "1") { // 要变为个人卡划扣 // 弹出dialog选择个人卡信息 if (personalCard.length == 0) { // $.messager.alert("消息", "没有个人卡信息！请先填写个人卡信息！",
 * "warning"); $.messager.confirm("消息","没有个人卡信息！请先填写个人卡信息！",function(flag){ if(flag){ // 弹出添加个人卡信息Dialog // 查询借款人信息表中的借款人身份证 ajaxSubmit(serverName +
 * "/receivablesRegistration/borrowServiceApp.do", { creditapplicationId : id }, function(r) { if (r != undefined) {
 * $("#accInfoDialog").dialog('open'); $("#borrowerCredentialsNumber").val(r.credentialsNumber); $("#borrowerName").val(r.name); } else { //
 * $.messager.alert("消息", "借款人信息为空！", "error"); $.messager.alert("消息","借款人信息为空！"); } }); }else{ cancleGlag = false;
 * $("#defaultReturnmentWay").combobox("setValue",(1-$("#defaultReturnmentWay").combobox("getValue"))); cancleGlag = true; } }); } else {
 * $("#cardDialog").dialog('open'); for ( var i = 0; i < personalCard.length; i++) { $("#cardDataGrid").datagrid('appendRow', { accountInfoId :
 * personalCard[i].accountInfoId, branchName : personalCard[i].branchName, bankName : personalCard[i].bankName, accountName :
 * personalCard[i].accountName, account : personalCard[i].account, accountType : personalCard[i].accountType }); } } } else if (param == "0") { //
 * 选择公司卡 // 弹出dialog选择公司卡信息 $("#cardDialog").dialog('open'); for ( var i = 0; i < companyCard.length; i++) { $("#cardDataGrid").datagrid('appendRow', {
 * accountInfoId : companyCard[i].accountInfoId, branchName : companyCard[i].branchName, bankName : companyCard[i].bankName, accountName :
 * companyCard[i].accountName, account : companyCard[i].account, accountType : companyCard[i].accountType }); } } else {
 * $.messager.alert("消息","defaultBox onSelect JS 出错了"); } } function doSaveCard() { if ($("#accountInfo").form('validate')) { ajaxSubmit(serverName +
 * "/receivablesRegistration/addPersonalCard.do", $("#accountInfo").serialize(), function(r) { if (r != undefined) { // $.messager.alert("消息",
 * "个人卡信息添加成功！", "info"); $.messager.show("消息","个人卡信息添加成功！"); // 关闭dialog $("#accInfoDialog").dialog('close'); // 个人卡id插入页面 personalCard.push(r); //
 * 显示个人卡信息 $("#cardDialog").dialog('open'); for ( var i = 0; i < personalCard.length; i++) { $("#cardDataGrid").datagrid('appendRow', { accountInfoId :
 * personalCard[i].accountInfoId, branchName : personalCard[i].branchName, bankName : personalCard[i].bankName, accountName :
 * personalCard[i].accountName, account : personalCard[i].account, accountType : personalCard[i].accountType }); } } else { // $.messager.alert("消息",
 * "个人卡信息添加失败！", "error"); $.messager.show("消息","个人卡信息添加失败！"); } }); } else { // $.messager.alert("消息", "请填写完整信息！", "warning");
 * $.messager.alert("消息","请填写完整信息！"); } } function closeAccDialog() { $("#accountInfo").form('clear'); $("#accInfoDialog").dialog('close'); cancleGlag =
 * false; $("#defaultReturnmentWay").combobox("setValue",(1-$("#defaultReturnmentWay").combobox("getValue"))); cancleGlag = true; } //
 * ////////////////////////////////////// function showCityCombo(province, city, district) {
 * 
 * var provinceid = $("#provinceId1").combobox("getValue"); var province = $("#" + province).combobox({ // required : true, editable : false, url :
 * serverName + '/NSC/list.do', textField : 'cityName', valueField : 'cityCode', mode : 'remote', delay : 500, width : '150', value : provinceid,
 * onSelect : function(value) { $("#" + city).combobox({ editable : false, url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode, textField :
 * 'cityName', valueField : 'cityCode', mode : 'remote', delay : 500, width : '150', value : '', onSelect : function(value) { $("#" +
 * district).combobox({ editable : false, url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode, textField : 'cityName', valueField :
 * 'cityCode', mode : 'remote', width : '150', delay : 500, value : '' }); } }); } });
 * 
 * var ciryId = $("#" + city).combobox("getValue"); var districtId = $("#" + district).combobox("getValue");
 * 
 * if (provinceid != null && provinceid != undefined) { $("#" + city).combobox({ editable : false, url : serverName + '/NSC/listCity.do?parentId=' +
 * provinceid, textField : 'cityName', valueField : 'cityCode', mode : 'remote', panelHeight : 'auto', delay : 500, width : '150', value : ciryId }); }
 * if (ciryId != null && ciryId != undefined) { $("#" + district).combobox({ editable : false, url : serverName + '/NSC/listCity.do?parentId=' +
 * ciryId, textField : 'cityName', valueField : 'cityCode', mode : 'remote', panelHeight : 'auto', delay : 500, width : '150', value : districtId }); } }
 */