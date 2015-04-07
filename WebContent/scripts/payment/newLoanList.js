var history_datagrid, customer_datagrid;
var operator;
/*客服检查：获取数据字典：错误文件、检查点*/
var sections = 'customerCheck';
var errorFile_compliance = [], checkpoint_loan = [];
var creditapplicationId;
$(function() {
//添加数据字典  放款渠道
	var dic = new DataDictionary();
	dic.addDic("lendingChannel", "lendingChannel");
	dic.dicAjax();
	$("#contractDateGrid").datagrid({
		url : serverName + "/contractAndLoanController/contractDateGrid.do?upContract="+role,
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		rownumbers : true,
		columns : [[{
			field : 'creditapplicationId',
			title : '信贷申请主键',
			width : 100,
			hidden : true
		}, {
			field : 'accountInfoId',
			title : '卡信息主键',
			width : 100,
			hidden : true
		}, {
			field : 'businessNumber',
			title : '业务单号',
			width : 125,
			hidden : false
		}, {
			field : 'name',
			title : '借款人姓名',
			width : 125,
			hidden : false
		}, {
			field : 'productTypeId',
			title : '产品类型',
			width : 125,
			hidden : true
		}, {
			field : 'productName',
			title : '产品名称',
			width : 125,
			hidden : false
		}, {
			field : 'loanAmount',
			title : '放款金额',
			width : 125,
			hidden : false
		}, {
			field : 'serviceCharge',
			title : '服务费',
			width : 125,
			hidden : false
		}, {
			field : 'rLoanAmount',
			title : '实发金额',
			width : 125,
			hidden : false
		}, {
			field : 'loanDate',
			title : '放款日期',
			width : 125,
			hidden : false
		}, {
			field : 'submitTime',
			title : '上传合同时间',
			width : 125,
			hidden : false,
			sortable : true
		}, {
			field : 'loanConfirmDate',
			title : '放款确认日期',
			width : 125,
			hidden : false
		}, {
			field : 'customerManager',
			title : '客户经理',
			width : 125,
			hidden : false
		},{
			title : '申请状态2',
			field : 'status',
			width : 100,
			hidden : true,
			formatter : function(value) {
				if (value == "04") {
					return "审批通过";
				}
				if (value == "34") {
					return "等待重新打印合同";
				}
				if (value == "33") {
					return "等待上传合同";
				}
				if (value == "35") {
					return "等待合同复核";
				}
			}
			}, {
			field : 'auditStatusShow',
			title : '申请状态',
			width : 125,
			hidden : false
		},{
			field : 'lendingConfiguration',
			title : '放款渠道1',
			width : 100,
			hidden : true
		},{
			field : 'lendingConfigurationShow',
			title : '放款渠道',
			width : 100,
			hidden : false
		}]],
		frozenColumns : [[{
			title : '操作',
			field : 'operateFlag',
			width : '170',
			formatter : operationFN
		}]]
	});

	var tabinners = $(".tabs-inner");
	$(tabinners[0]).bind("click", function() {
		return false;
	});

	// 选择放款日期 至begin
	$("#loanDateS").datebox({
		onSelect : function() {
			var loanDateS = $("#loanDateS").datebox("getValue");
			var loanDateE = $("#loanDateE").datebox("getValue");
			if (loanDateE != null && loanDateE != "") {
				if (loanDateS > loanDateE) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanDateS").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#loanDateE").datebox({
		onSelect : function() {
			var loanDateS = $("#loanDateS").datebox("getValue");
			var loanDateE = $("#loanDateE").datebox("getValue");
			if (loanDateS != null && loanDateS != "") {
				if (loanDateS > loanDateE) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanDateE").datebox("setValue", "");
					return;
				}
			}

		}
	});
	// end

	// 选择放款确认日期 至begin
	$("#loanConfirmDateS").datebox({
		onSelect : function() {
			var loanConfirmDateS = $("#loanConfirmDateS").datebox("getValue");
			var loanConfirmDateE = $("#loanConfirmDateE").datebox("getValue");
			if (loanConfirmDateE != null && loanConfirmDateE != "") {
				if (loanConfirmDateS > loanConfirmDateE) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanConfirmDateS").datebox("setValue", "");
					return;
				}
			}

		}
	});
	$("#loanConfirmDateE").datebox({
		onSelect : function() {
			var loanConfirmDateS = $("#loanConfirmDateS").datebox("getValue");
			var loanConfirmDateE = $("#loanConfirmDateE").datebox("getValue");
			if (loanConfirmDateS != null && loanConfirmDateS != "") {
				if (loanConfirmDateS > loanConfirmDateE) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#loanConfirmDateE").datebox("setValue", "");
					return;
				}
			}

		}
	});
	// end
	 departmentComboboxTreeWithPanelWidth("companyId",false,250);

});
// 模糊搜索方法
function searchFussy() {
	var data = new Object();
	var map = {
		fuzzy : $.trim($("#fuzzy").val())
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#contractDateGrid').datagrid('load', data);
	/*
	 * $("#").datagrid("load", { fuzzy : $.trim($("#fuzzy").val()) });
	 */
}

// 高级查询方法
function searchAdvanced() {
	var data = new Object();
	var map = {
		businessNumber : $.trim($("#businessNumber").val()),
		name : $.trim($("#name").val()),
		loanDateS : $("#loanDateS").datebox('getValue'),
		loanDateE : $("#loanDateE").datebox('getValue'),
		status : $("#auditStatus").combobox("getValue"),
		companyId : $("#companyId").combotree("getValues").join(","),
		loanConfirmDateS : $("#loanConfirmDateS").datebox('getValue'),
		loanConfirmDateE : $("#loanConfirmDateE").datebox('getValue'),
		lendingChannel:$("#lendingChannel").combobox("getValue")
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#contractDateGrid').datagrid('load', data);
}
// 清空方法
function searchCancel1() {
	$("#businessNumber").val("");
	$("#name").val("");
	$("#loanDateS").datebox('clear');
	$("#loanDateE").datebox('clear');
	$("#auditStatus").combobox("setValue", "");
	$("#companyId").combotree("setValues", "");
	$("#loanConfirmDateS").datebox('clear');
	$("#loanConfirmDateE").datebox('clear');
	// 放款渠道
	 $("#lendingChannel").combobox("setValue", "");
}
function searchCancel() {
	$("#fuzzy").val("");
}
/**
 * 打印合同页面
 * 
 * @param creditapplicationId
 */
function printContract(creditapplicationId,clientid,creditApplicationEscId,type) {
	//查询  放款渠道
	var flag="";
	//查询是否做过放款渠道配置
	ajaxSubmit(serverName+"/contractAndLoanController/searchConfigureChannel.do",{creditapplicationId:creditapplicationId},function(res){
		if(res){
			if(res.debentureTransfer=="0"&&res.trustLending=="0"){
				//信托,债权转让渠道都配置了
				flag = "1";
			}
			if(res.trustLending=="0" && res.debentureTransfer == '1'){
				//只配置了信托渠道
				flag = "2";
			}
			if(res.debentureTransfer=="0" && res.trustLending=="1"){
				//只配置了债权转让
				flag = "3";
			}
			
			//查询当前的业务状态  2.是重新打印合同
			ajaxSubmit(serverName+"/contractAndLoanController/searchForCreditApplicationStates.do",{creditapplicationId:creditapplicationId},function(result){
				if(result.auditStatus== "34"){
					type="2";
				}
			$("#hostTab").tabs("add", {
				title : "合同页面",
				content : "<iframe scrolling='no' id='iframeTest' name='iframeRegistration' frameborder='0'  src='" + serverName + "/contractAndLoanController/returnContractJSP.do?creditapplicationId=" + creditapplicationId + "&clientid="+clientid+"&creditApplicationEscId="+creditApplicationEscId+"&type="+type+"&flag="+flag+"' style='width:100%;height:450px;'></iframe>",
				onLoad : function(r) {
				}
			});
			});
		}else{
			alert("请先配置放款渠道！");
		}
	});
}

/** 初始化放款确认页面* */
function initComfirmPage(accountInfoId, creditApplicationId) {
	url = serverName + "/accountInfo/selectById.do";
	var accountInfoId = 0;
	if (accountInfoId != null && accountInfoId != "" && accountInfoId != undefined) {
		accountInfoId = accountInfoId;
	}
	ajaxSubmit(url, {
		accountInfoId : accountInfoId
	}, function(data) {
		// 基础信息
		$("#showAccountFormqr").form("load", data);
	});

	url = serverName + "/contractAndLoanController/searchForConfirm.do";
	var groupLoanRegistId;
	$("#remarkComment1").val("");
	ajaxSubmit(url, {
		creditapplicationId : creditApplicationId,
		registStatus : "0"
	}, function(result) {
		$("#loanConfirmForm").form("load", result);
		groupLoanRegistId = $("#groupLoanRegistId").val();
	  //是否显示信托计划
		//放款渠道
		if(result.amountConfirm != null && undefined != result.amountConfirm){
			if(result.amountConfirm.lendingChannel== "0"){
				//$("#amountConfirmLendingChannelDiv").hide();
				$("#lendingChannelEnable").val("债权转让");
				$('#xtjh1').hide();
				$('#xtjh2').hide();
			}else if(result.amountConfirm.lendingChannel == "1"){
				//$("#amountConfirmLendingChannelDiv").show();
				$("#lendingChannelEnable").val("信托");
				$('#xtjh1').show();
				$('#xtjh2').show();
			}else{
				$('#xtjh1').hide();
				$('#xtjh2').hide();
			}
		}
	});
	
	// 历史备注
	$("#historyRemark").datagrid({
		url : serverName + "/GroupLoanRegist/searchHistory.do?creditApplicationId=" + creditApplicationId,
		striped : true,
		singleSelect : true,
		fitColumns : true,
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
	/*// 放款登记详细
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
	});*/
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
                                panelHeight: 200,
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
                                panelHeight: 200,
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
//                customer_datagrid.datagrid('loadData', ret.customer);
                if (ret.remark_customer && ret.remark_customer.length > 0) {
                    $('#complianceCheckId').val(ret.remark_customer[0].complianceCheckId);
                }
            }
          });
    });

	specialApply('special_apply', 'applyContent_table', 'applyContent', creditApplicationId);
}
/*客服检查 start*/
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
/*客服检查 end*/
//放款确认
function LoanConfirm(accountInfoId,clientid,creditApplicationEscId,creditApplicationId,lendingConfiguration3) {
	//判断放款渠道是信托还是债权转让
	if(lendingConfiguration3=='1'){
		$('#openCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
		+ "&signIp=" + DESIp + "";
$('#applicationCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + creditApplicationEscId
		+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
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
            /*客服检查 start*/
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
            /*客服检查 end*/
			if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
				$("#loanReturnButton").linkbutton("enable");
				$("#loanConfirmButton").linkbutton("enable");
				$.messager.alert("消息", "请填写备注！");
				return;
			}

			$("#loanPerson").val($("#loanPerson1").val());
			$("#remarkComment").val($("#remarkComment1").val());
			$("#groupLoanRegistId").val($("#groupLoanRegistId1").val());
			url = serverName + "/contractAndLoanController/loanConfirm.do?registStatus=1";
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
					flushContractDateGrid();
				} else {
					$("#remarkComment1").val("");
					$("#LoanConfirm").dialog("close");
					$("#loanReturnButton").linkbutton("enable");
					$("#loanConfirmButton").linkbutton("enable");
					flushContractDateGrid();
					$.messager.alert("消息", result.msg);
				}
			});
		}
	}
	, {
		text : "补充资料",
		handler : function() {
			
			$("#loanReturnButton").linkbutton("disable");
			$("#loanConfirmButton").linkbutton("disable");
            /*客服检查 start*/
            /*备注和意见：增加合规检查项信息，如 1.错误文件 “某文件” ，检查点 “某检查点”*/
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
            /*客服检查 end*/
			if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
				$("#loanReturnButton").linkbutton("enable");
				$("#loanConfirmButton").linkbutton("enable");
				$.messager.alert("消息", "请填写备注！");
				return;
			}
			$("#loanPerson").val($("#loanPerson1").val());
			$("#remarkComment").val($("#remarkComment1").val());
			$("#groupLoanRegistId").val($("#groupLoanRegistId1").val());
            /*客服检查 start*/
            var inserted = customer_datagrid.datagrid('getChanges', "inserted");
            var deleted = customer_datagrid.datagrid('getChanges', "deleted");
            var effectRow = new Object();
            /*if (inserted.length) {
                *//*附加到备注和意见之前*//*
                var arrResult = {};
                for (var i = 0, n = inserted.length; i < n; i++) {
                    var item = inserted[i];
                    arrResult[ item.errorFile + " - " + item.checkPointLoan ] = item;
                }
                var i = 0, inserted_final = [];
                for (var item in arrResult) {
                    inserted_final[i++] = arrResult[item];
                }
                effectRow["inserted"] = JSON.stringify(inserted_final);
            }*/
            /*客户需求变更：同一次可添加重复检查点记录*/
            if (inserted.length) {
                effectRow["inserted"] = JSON.stringify(inserted);
            }
            if (deleted.length) {
                effectRow["deleted"] = JSON.stringify(deleted);
            }
            if (inserted.length) {
                var cks = '';
                for (var i = 0; i < inserted.length; i++) {
                    cks += (i + 1) + '.错误文件 "' + (errorFile_compliance[inserted[i].errorFile].codeVlue + '"，检查点 "' + checkpoint_loan[inserted[i].checkPointLoan].codeVlue + '"\n');
                }
                if (cks) {
                    $("#remarkComment").val(cks + $("#remarkComment").val());
                }
            }
            url = serverName + "/contractAndLoanController/loanConfirm.do?registStatus=3";
            ajaxSubmit(url, $("#loanConfirmForm").serializeObject(), function(result) {
                if (result.success) {
                    $("#LoanConfirm").dialog("close");
                    $("#loanReturnButton").linkbutton("enable");
                    $("#loanConfirmButton").linkbutton("enable");
                    $.messager.show({
                        title : "消息",
                        msg : "操作成功"
                    });
                    /*保存客服检查*/
                    if (inserted.length || deleted.length) {
                        ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $("#remarkComment1").val() + '&checkType=2&complianceCheckId=' + $('#complianceCheckId').val(), effectRow, function (ret) {
                            if (ret.success) {
                                $("#remarkComment1").val("");
                                console.info('--->>>保存客服检查成功!<<<---');
                            } else {
                                console.info('--->>>保存客服检查失败!<<<---');
                            }
                        });
                    }
                    customer_datagrid.datagrid('rejectChanges');
                    /*客服检查 end*/
                    flushContractDateGrid();
                } else {
                    $("#remarkComment1").val("");
                    $("#LoanConfirm").dialog("close");
                    $("#loanReturnButton").linkbutton("enable");
                    $("#loanConfirmButton").linkbutton("enable");
                    flushContractDateGrid();
                    $.messager.alert("消息", result.msg);
                }
            });
		
		}
	}
	, {
		text : "退回重签",
		id : "loanReturnButton",
		handler : function() {
			$("#loanReturnButton").linkbutton("disable");
			$("#loanConfirmButton").linkbutton("disable");
            /*客服检查 start*/
            /*备注和意见：增加合规检查项信息，如 1.错误文件 “某文件” ，检查点 “某检查点”*/
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
            /*客服检查 end*/
			if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
				$("#loanReturnButton").linkbutton("enable");
				$("#loanConfirmButton").linkbutton("enable");
				$.messager.alert("消息", "请填写备注！");
				return;
			}
			$("#loanPerson").val($("#loanPerson1").val());
			$("#remarkComment").val($("#remarkComment1").val());
			$("#groupLoanRegistId").val($("#groupLoanRegistId1").val());
            /*客服检查 start*/
            var inserted = customer_datagrid.datagrid('getChanges', "inserted");
            var deleted = customer_datagrid.datagrid('getChanges', "deleted");
            var effectRow = new Object();
            /*if (inserted.length) {
                *//*附加到备注和意见之前*//*
                var arrResult = {};
                for (var i = 0, n = inserted.length; i < n; i++) {
                    var item = inserted[i];
                    arrResult[ item.errorFile + " - " + item.checkPointLoan ] = item;
                }
                var i = 0, inserted_final = [];
                for (var item in arrResult) {
                    inserted_final[i++] = arrResult[item];
                }
                effectRow["inserted"] = JSON.stringify(inserted_final);
            }*/
            /*客户需求变更：同一次可添加重复检查点记录*/
            if (inserted.length) {
                effectRow["inserted"] = JSON.stringify(inserted);
            }
            if (deleted.length) {
                effectRow["deleted"] = JSON.stringify(deleted);
            }
            if (inserted.length) {
                var cks = '';
                for (var i = 0; i < inserted.length; i++) {
                    cks += (i + 1) + '.错误文件 "' + (errorFile_compliance[inserted[i].errorFile].codeVlue + '"，检查点 "' + checkpoint_loan[inserted[i].checkPointLoan].codeVlue + '"\n');
                }
                if (cks) {
                    $("#remarkComment").val(cks + $("#remarkComment").val());
                }
            }
            url = serverName + "/contractAndLoanController/loanConfirm.do?registStatus=4";
            ajaxSubmit(url, $("#loanConfirmForm").serializeObject(), function(result) {
                if (result.success) {
                    $("#LoanConfirm").dialog("close");
                    $("#loanReturnButton").linkbutton("enable");
                    $("#loanConfirmButton").linkbutton("enable");
                    $.messager.show({
                        title : "消息",
                        msg : "操作成功"
                    });
                    /*保存客服检查*/
                    if (inserted.length || deleted.length) {
                        ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $("#remarkComment1").val() + '&checkType=2&complianceCheckId=' + $('#complianceCheckId').val(), effectRow, function (ret) {
                            if (ret.success) {
                                $("#remarkComment1").val("");
                                console.info('--->>>保存客服检查成功!<<<---');
                            } else {
                                console.info('--->>>保存客服检查失败!<<<---');
                            }
                        });
                    }
                    customer_datagrid.datagrid('rejectChanges');
                    /*客服检查 end*/
                    flushContractDateGrid();
                } else {
                    $("#remarkComment1").val("");
                    $("#LoanConfirm").dialog("close");
                    $("#loanReturnButton").linkbutton("enable");
                    $("#loanConfirmButton").linkbutton("enable");
                    flushContractDateGrid();
                    $.messager.alert("消息", result.msg);
                }
            });
		}
	}, {
		text : "取消",
		handler : function() {
			$("#LoanConfirm").dialog("close");
		}
	}
	]
});
initComfirmPage(accountInfoId, creditApplicationId);
$("#LoanConfirm").dialog("maximize");
$("#layoutZ").layout("resize");
	}else{
	$('#openCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
			+ "&signIp=" + DESIp + "";
	$('#applicationCMView')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + creditApplicationEscId
			+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
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
                /*客服检查 start*/
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
                /*客服检查 end*/
				if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
					$("#loanReturnButton").linkbutton("enable");
					$("#loanConfirmButton").linkbutton("enable");
					$.messager.alert("消息", "请填写备注！");
					return;
				}

				$("#loanPerson").val($("#loanPerson1").val());
				$("#remarkComment").val($("#remarkComment1").val());
				$("#groupLoanRegistId").val($("#groupLoanRegistId1").val());
				url = serverName + "/contractAndLoanController/loanConfirm.do?registStatus=1";
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
						flushContractDateGrid();
					} else {
						$("#remarkComment1").val("");
						$("#LoanConfirm").dialog("close");
						$("#loanReturnButton").linkbutton("enable");
						$("#loanConfirmButton").linkbutton("enable");
						flushContractDateGrid();
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
                /*客服检查 start*/
                /*备注和意见：增加合规检查项信息，如 1.错误文件 “某文件” ，检查点 “某检查点”*/
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
                /*客服检查 end*/
				if ($("#remarkComment1").val() == null || "" == $("#remarkComment1").val()) {
					$("#loanReturnButton").linkbutton("enable");
					$("#loanConfirmButton").linkbutton("enable");
					$.messager.alert("消息", "请填写备注！");
					return;
				}
				$("#loanPerson").val($("#loanPerson1").val());
				$("#remarkComment").val($("#remarkComment1").val());
				$("#groupLoanRegistId").val($("#groupLoanRegistId1").val());
                /*客服检查 start*/
                var inserted = customer_datagrid.datagrid('getChanges', "inserted");
                var deleted = customer_datagrid.datagrid('getChanges', "deleted");
                var effectRow = new Object();
                /*if (inserted.length) {
                    *//*附加到备注和意见之前*//*
                    var arrResult = {};
                    for (var i = 0, n = inserted.length; i < n; i++) {
                        var item = inserted[i];
                        arrResult[ item.errorFile + " - " + item.checkPointLoan ] = item;
                    }
                    var i = 0, inserted_final = [];
                    for (var item in arrResult) {
                        inserted_final[i++] = arrResult[item];
                    }
                    effectRow["inserted"] = JSON.stringify(inserted_final);
                }*/
                /*客户需求变更：同一次可添加重复检查点记录*/
                if (inserted.length) {
                    effectRow["inserted"] = JSON.stringify(inserted);
                }
                if (deleted.length) {
                    effectRow["deleted"] = JSON.stringify(deleted);
                }
                if (inserted.length) {
                    var cks = '';
                    for (var i = 0; i < inserted.length; i++) {
                        cks += (i + 1) + '.错误文件 "' + (errorFile_compliance[inserted[i].errorFile].codeVlue + '"，检查点 "' + checkpoint_loan[inserted[i].checkPointLoan].codeVlue + '"\n');
                    }
                    if (cks) {
                        $("#remarkComment").val(cks + $("#remarkComment").val());
                    }
                }
                url = serverName + "/contractAndLoanController/loanConfirm.do?registStatus=2";
                ajaxSubmit(url, $("#loanConfirmForm").serializeObject(), function(result) {
                    if (result.success) {
                        $("#LoanConfirm").dialog("close");
                        $("#loanReturnButton").linkbutton("enable");
                        $("#loanConfirmButton").linkbutton("enable");
                        $.messager.show({
                            title : "消息",
                            msg : "操作成功"
                        });
                        /*保存客服检查*/
                        if (inserted.length || deleted.length) {
                            ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $("#remarkComment1").val() + '&checkType=2&complianceCheckId=' + $('#complianceCheckId').val(), effectRow, function (ret) {
                                if (ret.success) {
                                    $("#remarkComment1").val("");
                                    console.info('--->>>保存客服检查成功!<<<---');
                                } else {
                                    console.info('--->>>保存客服检查失败!<<<---');
                                }
                            });
                        }
                        customer_datagrid.datagrid('rejectChanges');
                        /*客服检查 end*/
                        flushContractDateGrid();
                    } else {
                        $("#remarkComment1").val("");
                        $("#LoanConfirm").dialog("close");
                        $("#loanReturnButton").linkbutton("enable");
                        $("#loanConfirmButton").linkbutton("enable");
                        flushContractDateGrid();
                        $.messager.alert("消息", result.msg);
                    }
                });
			}

		}, {
			text : "关闭",
			handler : function() {
				$("#LoanConfirm").dialog("close");
			}
		}
		]
	});
	initComfirmPage(accountInfoId, creditApplicationId);
	$("#LoanConfirm").dialog("maximize");
	$("#layoutZ").layout("resize");
}
}


//放款登记查看
function searchGroupRegist() {
	var rowdata = $("#contractDateGrid").datagrid("getSelected");
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
			if(result.cardFlag == "0"){
				$("#cardFlagLook").val("卡");
			}else if(result.cardFlag == "1"){
				$("#cardFlagLook").val("存折");
			}
			//$("#cardFlagLook").val((result.cardFlag == "0") ? "卡" : "存折");
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
}
//关闭合同页面
function closeTab(){
	$("#hostTab").tabs("close","合同页面");
}
//重新加载列表
function  flushContractDateGrid(){
	$('#contractDateGrid').datagrid('load');
}


//放款列表查看
/** 初始化放款确认页面* */
function initComfirmPageLook(accountInfoId, creditApplicationId) {
	url = serverName + "/accountInfo/selectById.do";
	var accountInfoId = 0;
	if (accountInfoId != null && accountInfoId != "" && accountInfoId != undefined) {
		accountInfoId = accountInfoId;
	}
	ajaxSubmit(url, {
		accountInfoId : accountInfoId
	}, function(data) {
		// 基础信息
		$("#showAccountFormqr").form("load", data);
	});
	
	url = serverName + "/contractAndLoanController/searchForConfirm.do";
	var groupLoanRegistId;
	$("#remarkComment1").val("");
	ajaxSubmit(url, {
		creditapplicationId : creditApplicationId,
		registStatus : "0"
	}, function(data) {
		$("#loanViewForm").form("load", data);
		if(data.accountInfo != null && undefined != data.accountInfo){
			
			if(data.accountInfo.cardFlag == "0"){
				$("#cardFlagEnable").val("卡");
			}else if(data.accountInfo.cardFlag == "1"){
				$("#cardFlagEnable").val("存折");
			}
		}
		//是否显示信托计划
		//放款渠道
		if(data.amountConfirm != null && undefined != data.amountConfirm){
			if(data.amountConfirm.lendingChannel== "0"){
				$("#lendingChannelEnableLook").val("债权转让");
				$('#xtjh3').hide();
				$('#xtjh4').hide();
			}else if(data.amountConfirm.lendingChannel == "1"){
				$("#lendingChannelEnableLook").val("信托");
				$('#xtjh3').show();
				$('#xtjh4').show();
			}else{
				$('#xtjh3').hide();
				$('#xtjh4').hide();
			}
		}
		//
		var aa = 0;
		var bb = 0;
		var cc = 0;
		if(data.accountInfo !=null && undefined != data.accountInfo){
			
			aa= data.accountInfo.provinceId;
			bb=data.accountInfo.districtId;
			cc=data.accountInfo.cityId;
		}else{
			aa = 0;
			bb=0;
			cc=0;
		}
		//$("#cardFlagEnable").val($("#cardFlagEnable1").val() == "0" ? "卡" : "存折");
		url = serverName + "/NSC/selectByCode.do";
		ajaxSubmit(url, {
			cityCode : aa
		}, function(result) {
			if (result != null) {
				$("#provinceIdEnable").val(result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : bb
		}, function(result) {
			if (result != null) {
				$("#districtIdEnable").val(result.cityName);
			}
		});
		ajaxSubmit(url, {
			cityCode : cc
		}, function(result) {
			if (result != null) {
				$("#cityIdEnable").val(result.cityName);
			}
		});
		groupLoanRegistId = $("#groupLoanRegistId").val();
	});
	// 历史备注
    history_datagrid = $("#historyRemarkView").datagrid({
		url : serverName + "/GroupLoanRegist/searchConfirm.do?creditApplicationId=" + creditApplicationId,
		striped : true,
		singleSelect : true,
		fitColumns : true,
		width : 699,
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
    history_datagrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"remarkComment",showContant:"remarkComment"});
    /*客服检查 start*/
    ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
        customer_datagrid = $('#check_customer_view').datagrid({
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
        if (ret && ret.customer_loan && ret.customer_loan.length) {
            customer_datagrid.datagrid('loadData', ret.customer_loan);
        }
    });
    /*客服检查 end*/

	/*特殊情况申请 start*/
	specialApply('special_apply_view', 'applyContent_table_view', 'applyContent_view', creditApplicationId);
	/*特殊情况申请 end*/
}
function viewDetail(accountInfoId,clientid,creditApplicationEscId,creditApplicationId){
	//判断当前的业务状态是   等待上传合同
	ajaxSubmit(serverName+"/contractAndLoanController/searchForCreditApplicationStates.do",{creditapplicationId:creditApplicationId},function(result){
		if(result.auditStatus== "33"){
			$("#yLoanTime").val(" ");
			$("#contractSignedTime").val(" ");
		}
	});
	$('#openCMView2')[0].src = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
	+ "&signIp=" + DESIp + "";
    $("#LoanView").dialog({
        closed : false,
        title : "查看",
        draggable : true,
        maximizable : true,
        modal : true
    });
    initComfirmPageLook(accountInfoId, creditApplicationId);
    $("#LoanView").dialog("maximize");
    $("#layoutZView").layout("resize");
}
//操作成功提示
function messageSuccess(){
	$.messager.show({
		title : "消息",
		msg : "操作成功！"
	});
}
//操作失败提示
function messageFail(){
	$.messager.show({
		title : "消息",
		msg : "操作失败！"
	});
}
//变更放款渠道
function changeLoanChannelXinTuo(creditapplicationId){
	//查询是否配置了债权转让
	ajaxSubmit(serverName+"/contractAndLoanController/searchConfigureChannel.do",{creditapplicationId:creditapplicationId},function(res){
		if(res){
			$.messager.confirm('提示框','确认变更放款渠道为债权转让吗？',function(b){
				if(b){
				//变更放款渠道为债权转让
				ajaxSubmit(serverName+"/contractAndLoanController/updateChangeLoanChannelXinTuo.do",{creditapplicationId:creditapplicationId},function(result){
					if(result.success){
						messageSuccess();
						flushContractDateGrid();
					}else{
						messageFail();
					}
			  });
				}
			});
		}else{
			alert("此营业部没有别的放款渠道！");
		}
	});
}

function specialApply(did, tbd, td, cid) {
	$('#' + did).datagrid({
		url: serverName + '/specialApplyController/searchApplyByCreditId.do?id=' + cid,
		fitColumns: true,
		singleSelect: true,
		columns: [
			[
				{
					title: '申请人',
					field: 'APPLYER',
					width: 100,
					align: 'center'
				}, {
				title: '申请类别',
				field: 'APPLYTYPE',
				width: 100,
				align: 'center'
			}, {
				title: '审批人',
				field: 'APPROVER',
				width: 100,
				align: 'center'
			}, {
				title: '审批结果',
				field: 'APPLYRESULT',
				width: 100,
				align: 'center',
				formatter: function (v, r) {
					/*显示申请内容*/
					$('#' + tbd).show();
					$('#' + td).html(r.APPLYCONTENT);
					return v;
				}
			}
			]
		]
	});
}