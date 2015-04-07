$(function() {
	var dataBestowallist = new Array();
	var company = $("#companyName").combobox("getValue");
	if (company == undefined || company == null || company == "") {

	}
	$("#dataBestowallist").datagrid({
		url : serverName + '/dataBestowalController/selectBestowalCreditApplicationByPagination.do',
		idfield : 'cod',
		singleSelect : false,
		rownumbers : true,
		striped : true,
		pagination : true,
		queryParams : {
			querayFlag : "T"
		},
		columns : [ [ {
			field : 'CREDITAPPLICATION_ID',
			checkbox : true
		}, {
			field : 'COMPANY_NAME',
			title : '分公司',
			width : 150
		}, {
			field : 'LOAN_OFFICER_NAME',
			title : '客户经理',
			width : 150
		}, {
			field : 'BUSINESS_NUMBER',
			title : '业务单号',
			width : 150
		}, {
			field : 'BORROWERNAME',
			title : '借款人姓名',
			width : 150
		}, {
			field : 'AUDITSTATUSSHOW',
			title : '业务状态',
			width : 150
		}, {
			field : 'ISOVERDUE',
			title : '是否逾期',
			width : 150,
			formatter : function(value) {
				if ("1" == value) {
					return "是";
				} else {
					return "否";
				}
			}
		}, {
			field : 'SIGNAGREEMENT_DATE',
			title : '放款日期',
			width : 150,
			formatter : function(value) {
				if ("" == value || value == null) {
					return "";
				} else {
					var date = new Date(value);
					var year = date.getFullYear();
					var month = date.getMonth() + 1;
					var day = date.getDate();

					return year + "年" + month + "月" + day + "日";
				}
			}
		} ] ]
	});

	$("#customerConsultTable").datagrid({
		url : serverName + '/dataBestowalController/selectCustomerConsultList.do',
		singleSelect : false,
		rownumbers : true,
		striped : true,
		pagination : true,
		queryParams : {
			querayFlag : "T"
		},
		columns : [ [ {
			field : 'customerConsultId',
			checkbox : true
		}, {
			field : 'departmentName',
			title : '部门',
			width : 150
		}, {
			field : 'receptionist',
			title : '客户经理',
			width : 150
		}, {
			field : 'telphone',
			title : '电话',
			width : 150
		}, {
			field : 'status',
			title : '状态',
			width : 150,
			formatter : function(value) {
				if ('1' == value) {
					return "已经申请";
				} else {
					return "未申请";
				}
			}
		} ] ]
	});
	$("#newLoanOfferNameDialog").show().dialog({
		title : "新客户经理",
		closed : true,
		modal : true,
		inline : false,
		width : 500,
		height : 500,
		border : false,
		dosize : false,
		draggable : false
	});
	// 初始化分公司
	/*$("#companyName").combobox({
		url : serverName + "/dataBestowalController/getDepartmentList.do",
		valueField : 'departmentId',
		textField : 'departmentName',
		onSelect : function(data) {
			$("#new_loanOfficerName").combobox("setValue", "");
			$("#old_loanOfficerName").combobox({
				url : serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId=" + data.departmentId,
				valueField : 'LOAN_OFFICER_ID',
				textField : 'LOAN_OFFICER_NAME',
				onSelect : function(data1) {
					$("#new_loanOfficerName").combobox({
						url : serverName + "/dataBestowalController/queryloanOfficerNameListByDepartmentId.do?departmentId=" + data1.AREADEPARTMENTID + "&loanOfficerId=" + data1.LOAN_OFFICER_ID,
						valueField : 'comEmpId',
						textField : 'name'
					});
				}
			});
		}
	});*/

    ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function(r) {
        $("#companyName").combotree({
            data : r,
            multiple: false,
            editable : false,
            required : false,
            panelWidth: 250,
            onSelect:function(node){
                var children = $("#companyName").tree("getChildren", node.target);
                if (children.length > 0) {
                    $.messager.alert('提示', '请选择中心下分公司', 'info');
                    $("#companyName").combotree('clear');
                    return;
                }
                $("#new_loanOfficerName").combobox("setValue", "");
                $("#old_loanOfficerName").combobox({
                    url : serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId=" + node.id,
                    valueField : 'LOAN_OFFICER_ID',
                    textField : 'LOAN_OFFICER_NAME',
                    onSelect : function(data1) {
                        $("#new_loanOfficerName").combobox({
                            url : serverName + "/dataBestowalController/queryloanOfficerNameListByDepartmentId.do?departmentId=" + data1.AREADEPARTMENTID + "&loanOfficerId=" + data1.LOAN_OFFICER_ID,
                            valueField : 'comEmpId',
                            textField : 'name'
                        });
                    }
                });
            }
        });
    });

});

/**
 * 模糊搜索清空条件
 */
function cancelQuerySimpleCondition() {
	$("#simpleConditionInput").val('');
}

/**
 * 精确查询
 */
function queryAllCondition(customerConsult) {

// var data = $("#dataBestowallist").datagrid("getSelections");
	if ($("#companyName").combobox("getValue") == undefined || $("#companyName").combobox("getValue") == null || $("#companyName").combobox("getValue") == "") {
		alert("没有选择分公司");
		return;
	}
	if ($("#old_loanOfficerName").combobox("getValue") == undefined || $("#old_loanOfficerName").combobox("getValue") == null || $("#old_loanOfficerName").combobox("getValue") == "") {
		alert("没有选择原客户经理");
		return;
	}
	if (customerConsult == '1') {
		var loanOfficerId = $("#old_loanOfficerName").combobox("getValue");
		$("#listTab").tabs("select", "咨询列表");
		$("#customerConsultTable").datagrid("load", {
			receptionistId : loanOfficerId
		});
	} else {

		$("#listTab").tabs("select", "申请列表");
		$("#dataBestowallist").datagrid("load", {

			loanOfficerId : $("#old_loanOfficerName").combobox("getValue"),
			groupNumber : $("#businessNumber").val()
		});
	}
}

function clearAllCondition() {
	$("#companyName").combobox("clear");
	$("#old_loanOfficerName").combobox("clear");
	$("#new_loanOfficerName").combobox("clear");
	$("#businessNumber").val("");
	$("#note").val("");
}
// 提交数据赠与
function submitDataBestowal(flag) {

	if ($("#companyName").combobox("getValue") == undefined || $("#companyName").combobox("getValue") == null || $("#companyName").combobox("getValue") == "") {
		$("#submitDataBestowal").linkbutton("enable");
		alert("没有选择分公司");
		return;
	}
	if ($("#old_loanOfficerName").combobox("getValue") == undefined || $("#old_loanOfficerName").combobox("getValue") == null || $("#old_loanOfficerName").combobox("getValue") == "") {
		$("#submitDataBestowal").linkbutton("enable");
		alert("没有选择原客户经理");
		return;
	}

	if ($("#new_loanOfficerName").combobox("getValue") == undefined || $("#new_loanOfficerName").combobox("getValue") == null || $("#new_loanOfficerName").combobox("getValue") == "") {
		$("#submitDataBestowal").linkbutton("enable");
		alert("没有选择新客户经理");
		return;
	}

	if (flag != undefined && flag == "1") {
		var cuntomerConsultIds = new Array();
		var length = 0;
		var data = null;
		data = $("#customerConsultTable").datagrid("getSelections");
		// 选中的数据个数
		length = data.length;
		if (length == 0) {
			alert("没有选中咨询数据");
			return;
		} else {
			if (data[0].receptionistId != $("#old_loanOfficerName").combobox("getValue")) {
				alert("原客户经理与列表数据不匹配，请重新查询");
			} else {
				for ( var i = 0; i < length; i++) {
					cuntomerConsultIds.push(data[i].customerConsultId);
				}
				$.messager.confirm("消息", "确定要执行操作，已经选中了" + length + "条赠与数据", function(r) {
					if (r) {
						$("#dataBestowalConsultButton").linkbutton("disable");
						$.ajax({
							url : serverName + "/dataBestowalController/insertDataBestowalAndDataBestowalDetail.do?customerConsult=1",
							data : {
								oldLoanOfficerId : $("#old_loanOfficerName").combobox("getValue"),
								oldLoanOfficerName : $("#old_loanOfficerName").combobox("getText"),
								newLoanOfficerId : $("#new_loanOfficerName").combobox("getValue"),
								newLoanOfficerName : $("#new_loanOfficerName").combobox("getText"),
								note : "咨询赠与_" + $("#note").val(),
								creditApplicationIds : JSON.stringify(cuntomerConsultIds)
							},
							dataType : "json",
							success : function(message) {
								if (message.success) {
									$.messager.alert("消息", "操作成功");
									$("#dataBestowalConsultButton").linkbutton("enable");
									queryAllCondition('1');
									// oldCustomer();
								} else {
									$("#dataBestowalConsultButton").linkbutton("enable");
									$.messager.show({
										title : "消息",
										msg : message.msg
									});
								}
							}
						});
					}
				});
			}
		}
	} else {
		var creditApplicationIds = new Array();
		var data = $("#dataBestowallist").datagrid("getSelections");
		// 选中的数据个数
		var length = data.length;
		for ( var i = 0; i < length; i++) {
			creditApplicationIds.push(data[i].CREDITAPPLICATION_ID);
		}
		if (length == 0) {
			alert("没有选中申请数据");
			$("#submitDataBestowal").linkbutton("enable");
			return;
		} else {
			if (data[0].LOAN_OFFICER_ID != $("#old_loanOfficerName").combobox("getValue")) {
				$("#submitDataBestowal").linkbutton("enable");
				alert("原客户经理与列表数据不匹配，请重新查询");
			} else {
				$.messager.confirm("消息", "确定要执行操作，已经选中了" + length + "条赠与数据", function(r) {
					if (r) {
						$("#submitDataBestowal").linkbutton("disable");
						$.ajax({
							url : serverName + "/dataBestowalController/insertDataBestowalAndDataBestowalDetail.do",
							data : {
								oldLoanOfficerId : $("#old_loanOfficerName").combobox("getValue"),
								oldLoanOfficerName : $("#old_loanOfficerName").combobox("getText"),
								newLoanOfficerId : $("#new_loanOfficerName").combobox("getValue"),
								newLoanOfficerName : $("#new_loanOfficerName").combobox("getText"),
								note : "申请赠与_" + $("#note").val(),
								creditApplicationIds : JSON.stringify(creditApplicationIds),
								loanOfficerId : $("#companyName").combobox("getValue")
							},
							dataType : "json",
							success : function(message) {
								if (message.success) {
									$.messager.alert("消息", "操作成功");
									$("#submitDataBestowal").linkbutton("enable");
									queryAllCondition();
									// oldCustomer();
								} else {
									$("#submitDataBestowal").linkbutton("enable");
									$.messager.show({
										title : "消息",
										msg : message.msg
									});
								}
							}
						});
					} else {
						$("#submitDataBestowal").linkbutton("enable");
					}

				});
			}
		}
	}
}

function showNewOfficeNameDialog() {

	$("#newLoanOfferNameDialog").dialog('open');
	$("#newLoanOfferNameDataGrid").datagrid({
		title : " 信息",
		url : serverName + '/dataBestowalController/queryloanOfficerNameListByDepartmentId.do',
		idfield : 'cod',
		singleSelect : true,
		striped : true,
		pagination : true,
		columns : [ [ {
			field : 'areaDepartmentName',
			title : '分公司名称',
			width : 100
		}, {
			field : 'comEmpNo',
			title : '客户经理编号',
			width : 100
		}, {
			field : 'comEmpId',
			title : '客户经理ID',
			width : 100
		}, {
			field : 'name',
			title : '客户经理',
			width : 100
		} ] ]
	});

}
