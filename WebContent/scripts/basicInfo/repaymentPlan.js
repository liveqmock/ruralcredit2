// var repaymentPlanForm;
// var addDataGridEditRow = undefined;// 判断添加明细的Datagrid可编辑行
// var editDataGridEditRow = undefined;// 判断修改明细的Datagrid可编辑行
// // 判断datagrid是新增还是编辑0是新增1是编辑
// var dategridAddOREdit;

/**
 * (｡-ω-｡)-----------------------------------页面初始化部分-------------------------------------(｡-ω-｡)
 */
$(function() {

	/**
	 * (｡-ω-｡)------------------------------写在初始化之前所有的combobox都在这里定义哦！----------------------------------(｡-ω-｡)
	 */
	var dic = new DataDictionary();
	dic.addDic("repaymentWayAdd", "repaymentWay");
	dic.addDic("useFlagAdd", "repaymentPlanUseFlag");
	dic.addDic("earlyTypeAdd", "earlyType");
	dic.addDic("repaymentWayEdit", "repaymentWay");
	dic.addDic("useFlagEdit", "repaymentPlanUseFlag");
	dic.addDic("earlyTypeEdit", "earlyType");
	dic.addDic("repaymentWaySearch", "repaymentWay");
	dic.addDic("useFlagSearch", "repaymentPlanUseFlag");
	dic.addDic("earlyTypeSearch", "earlyType");
	dic.dicAjax();
	/**
	 * (｡-ω-｡)------------------------------写在初始化之前所有的combobox都在这里定义哦！END----------------------------------(｡-ω-｡)
	 */

	/**
	 * (｡-ω-｡)------------------------------主要界面初始化部分----------------------------------(｡-ω-｡)
	 */
	// 还款方案搜索tabs
	$("#repaymentSearchTab").tabs({
		tools : [ {
			iconCls : "icon-addOne",
			text : "新增还款计划",
			handler : function() {
				openDialogForAdd();
			}
		} ]
	});
	// 模糊搜索input
	$('#fuzzyQueryRepayment').combobox({
		data : [ {
			"id" : "0",
			"text" : "启用"
		}, {
			"id" : "1",
			"text" : "停用"
		} ],
		valueField : 'id',
		textField : 'text'
	});
	// 初始化时添加北部面板
/*
 * $('#repayment_layout').layout('add', { region : 'north', height : 100, href : serverName + "/jsp/rc/basicInfo/repaymentPlan_layout_north_serch.jsp"
 * });
 */

	// 初始化时直接查询产品列表
	$('#repaymentDataGrid').datagrid({
		url : serverName + "/repaymentplan/repaymentPlanDataGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 20,
		pageList : [ 5, 10, 20 ],
		columns : [ [ {
			field : 'repaymentPlanId',
			title : 'repaymentPlanId',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanName',
			title : '方案名称',
			width : 100
		}, {
			field : 'loanPeriod',
			title : '贷款期限（月）',
			width : 100
		}, {
			field : 'heightLoanAmount',
			title : '最高借款金额',
			width : 100
		}, {
			field : 'nominalInterestRate',
			title : '名义年利率（%）',
			width : 100
		}, {
			field : 'firstServiceFree',
			title : '首期服务费（%）',
			width : 100
		}, {
			field : 'followupServiceFree',
			title : '后续服务费（%）',
			width : 100
		}, {
			field : 'repaymentWay',
			title : '还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'earlyType',
			title : '提前还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">利息与服务费之和的50%</font>';

				} else if (value == 1) {
					return '<font color="green">借款总金额的1.5%</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'useFlag',
			title : '是否启用',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">启用</font>';

				} else if (value == 1) {
					return '<font color="grey">停用</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : '操作',
			title : '操作',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return "&nbsp&nbsp&nbsp" + "<a id='' style='color: #9932cc;' onclick='doSearchRepaymentPlan(" + rowIndex + ")'>查询&修改</a>";
			}
		} ] ]
	});
	/**
	 * (｡-ω-｡)------------------------------主要界面初始化部分END------------------------------(｡-ω-｡)
	 */

	/**
	 * (｡-ω-｡)------------------------------新增还款方案dialog内容初始化部分------------------------------(｡-ω-｡)
	 */

	// 添加明细中部面板时生成的Datagrid
	$('#addRepaymentPlanDatagrid').datagrid({
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		singleSelect : true,
		showFooter : false,
		columns : [ [ {
			field : 'repaymentPlanId',
			title : 'repaymentPlanId',
			width : 100,
			hidden : true
		}, {
			field : 'months',
			title : '月数',
			width : 100
		}, {
			field : 'principalRepayment',
			title : '本金还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'principal',
			title : '本金（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		}, {
			field : 'intertestRepayment',
			title : '利息还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'interest',
			title : '利息（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		}, {
			field : 'chargeServiceMethod',
			title : '服务费还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'serviceFree',
			title : '服务费（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		} ] ],
		toolbar : [ '-', {
			id : 'repaymentPlan_edit',
			text : '编辑',
			disabled : true,
			iconCls : 'icon-edit',
			handler : function() {
				// 0代表新增datagrid
				var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
				for ( var i = 0; i < loanPeriodValue; i++) {
					$('#addRepaymentPlanDatagrid').datagrid('beginEdit', i);
				}
			}

		}, '-', {
			id : 'repaymentPlan_reload',
			text : '重新生成',
			disabled : true,
			iconCls : 'icon-reload',
			handler : function() {
				// 0代表新增datagrid
				var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
				$("#repaymentPlanFormAdd input").attr("readonly", false);
				$("#repaymentWayAdd").combobox('enable');
				$("#useFlagAdd").combobox('enable');
				$("#earlyTypeAdd").combobox('enable');
				doRecreatRepaymentItem(loanPeriodValue);
			}

		}, '-' ]
	});

	/**
	 * (｡-ω-｡)------------------------------新增还款方案dialog内容初始化部分END------------------------------(｡-ω-｡)
	 */

	/**
	 * (｡-ω-｡)------------------------------修改还款方案dialog内容初始化部分------------------------------(｡-ω-｡)
	 */

	// 搜索和修改用的datagrid
	$('#editRepaymentPlanDatagrid').datagrid({
		url : serverName + "/repaymentplan/repaymentPlanItemDataGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		singleSelect : true,
		showFooter : false,
		pagination : true,
		pageSize : 20,
		pageList : [ 5, 10, 20 ],
		columns : [ [ {
			field : 'repaymentPlanItemId',
			title : 'repaymentPlanItemId',
			width : 100,
			hidden : true
		}, {
			field : 'repaymentPlanId',
			title : 'repaymentPlanId',
			width : 100,
			hidden : true
		}, {
			field : 'months',
			title : '月数',
			width : 100
		}, {
			field : 'principalRepayment',
			title : '本金还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else if (value == "本金合计：") {
					return '<font color="red">本金合计：</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'principal',
			title : '本金（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		}, {
			field : 'intertestRepayment',
			title : '利息还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else if (value == "利息合计：") {
					return '<font color="red">利息合计：</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'interest',
			title : '利息（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		}, {
			field : 'chargeServiceMethod',
			title : '服务费还款方式',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return '<font color="green">按期平均</font>';

				} else if (value == 1) {
					return '<font color="blue">自定义</font>';
				} else if (value == "服务费合计：") {
					return '<font color="red">服务费合计：</font>';
				} else {
					return '<font color="red">Null</font>';
				}
			}
		}, {
			field : 'serviceFree',
			title : '服务费（%）',
			width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2
				}
			}
		} ] ],
		toolbar : [ '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var repaymentWayValue = $("#repaymentWayEdit").combobox('getValue');

				var loanPeriodValue = $('#repaymentPlanFormEdit input[name=loanPeriod]').val();
				$("#nominalInterestRateEdit").attr('readonly', false);
				$("#firstServiceFreeEdit").attr('readonly', false);
				$("#followupServiceFreeEdit").attr('readonly', false);
				$("#heightLoanAmountEdit").attr('readonly', false);
				$("#useFlagEdit").combobox('enable');
				$("#earlyTypeEdit").combobox('enable');
				if (repaymentWayValue == 0) {
					$("#updateRepaymentItemEdit").linkbutton('enable');
					$("#saveRepaymentForEidt").linkbutton('enable');
				} else if (repaymentWayValue == 1) {
					for ( var i = 0; i < loanPeriodValue; i++) {
						$('#editRepaymentPlanDatagrid').datagrid('beginEdit', i);
						$("#saveRepaymentForEidt").linkbutton('enable');
					}
				} else {
					$.messager.alert('提示', "编辑的JS出错了！", 'error');
				}
			}

		}, '-' ]

	});
	/**
	 * (｡-ω-｡)----------------------------------------------------修改还款方案dialog内容初始化部分END----------------------------------------------------(｡-ω-｡)
	 */
});

/**
 * (｡-ω-｡)------------------------------------------------------------页面初始化部分END-----------------------------------------------------------------(｡-ω-｡)
 */

/**
 * (｡-ω-｡)------------------------------------------------------------查询还款方案相关方法------------------------------------------------------------(｡-ω-｡)
 */
// 查询还款方案列表
function doSearchRepayment(param) {
	var passFuzzy = undefined;
	if (param == 0) {
		var text = $('#fuzzyQueryRepayment').combobox('getText');
		if (text == "启用") {
			passFuzzy = 0;
		} else if (text == "停用") {
			passFuzzy = 1;
		} else {
			passFuzzy = text;
		}
		$('#repaymentDataGrid').datagrid('load', {
			fuzzyQueryValue : passFuzzy,
			searchFlag : 0
		});
	} else if (param == 1) {
		$('#repaymentDataGrid').datagrid('load', {
			repaymentPlanName : $('#repaymentPlan_searchform input[name=repaymentPlanName]').val(),
			loanPeriod : $('#repaymentPlan_searchform input[name=loanPeriod]').val(),
			nominalInterestRate : $('#repaymentPlan_searchform input[name=nominalInterestRate]').val(),
			firstServiceFree : $('#repaymentPlan_searchform input[name=firstServiceFree]').val(),
			followupServiceFree : $('#repaymentPlan_searchform input[name=followupServiceFree]').val(),
			repaymentWay : $('#repaymentPlan_searchform input[name=repaymentWay]').val(),
			useFlag : $('#repaymentPlan_searchform input[name=useFlag]').val(),
			heightLoanAmount : $('#repaymentPlan_searchform input[name=heightLoanAmount]').val(),
			searchFlag : 1
		});
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
// 清空查询条件
function doClearRepayment(param) {
	if (param == 0) {
		$('#fuzzyQueryRepayment').combobox('clear');
		doSearchRepayment(param == 0);
	} else if (param == 1) {
		$("#repaymentPlan_searchform").form('clear');
		doSearchRepayment(param == 1);
	} else {
		$.messager.alert('提示', "doClearRepayment的JS出错了！", 'error');
	}
}
// 打开新增还款方案对话框
function openDialogForAdd() {
	$("#dialogForAdd").dialog('open');
}

// 关闭新增还款方案对话框
function closeDialogForAdd() {
	var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
	doRecreatRepaymentItem(loanPeriodValue);
	$('#repaymentDataGrid').datagrid('reload');
	$("#repaymentPlanFormAdd input").attr("readonly", false);
	$("#repaymentWayAdd").combobox('enable');
	$("#useFlagAdd").combobox('enable');
	$("#earlyTypeAdd").combobox('enable');
	$("#dialogForAdd").dialog('close');
}
// 打开修改还款方案对话框
function openDialogForEdit() {
	$("#dialogForSearchAndEdit").dialog('open');
	$("#updateRepaymentItemEdit").linkbutton('disable');
	$("#saveRepaymentForEidt").linkbutton('disable');
}
// 关闭修改还款方案对话框
function closeDialogForEdit() {
	$("#dialogForSearchAndEdit").dialog('close');
}
// 查询还款方案及明细
function doSearchRepaymentPlan(rowIndex) {
	openDialogForEdit();
	$('#repaymentDataGrid').datagrid('selectRow', rowIndex);
	var row = $('#repaymentDataGrid').datagrid('getSelected');
	if (row != null) {
		var passRepaymentPlanId = row.repaymentPlanId;
		ajaxSubmit(serverName + "/repaymentplan/searchRepaymentPlanByRepaymentPlanId.do", {
			repaymentPlanId : passRepaymentPlanId
		}, function(result) {
			$("#repaymentPlanFormEdit").form('clear');
			$("#repaymentPlanFormEdit").form('load', result);
			$("#repaymentPlanFormEdit input").attr('readonly', true);
			$("#repaymentWayEdit").combobox('disable');
			$("#useFlagEdit").combobox('disable');
			$("#earlyTypeEdit").combobox('disable');
			$('#editRepaymentPlanDatagrid').datagrid('reload', {
				repaymentPlanId : passRepaymentPlanId
			});
		});
	} else {
		$.messager.alert('提示', '查询&修改还款方案JS有问题！', 'error');
	}
}
/**
 * (｡-ω-｡)------------------------------------------------------------查询还款方案相关方法END------------------------------------------------------------(｡-ω-｡)
 */

/**
 * (｡-ω-｡)------------------------------------------------------------新增还款方案相关方法------------------------------------------------------------(｡-ω-｡)
 */
// 保存还款方案及明细
function doSaveRepaymentPlanAndItem() {
	var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
	$("#repaymentWayAdd").combobox('enable');
	$("#useFlagAdd").combobox('enable');
	$("#earlyTypeAdd").combobox('enable');
	var datagridAddAllData = new Object();
	var isSaveSuccess = false;
	// var tempMSG = "错误";
	var repaymentWayValue = $("#repaymentWayAdd").combobox('getValue');
	$('#addRepaymentPlanDatagrid').datagrid('selectAll');
	var rows = $('#addRepaymentPlanDatagrid').datagrid('getSelections');
	datagridAddAllData["javaname"] = JSON.stringify(rows);
	datagridAddAllData["repaymentPlan"] = JSON.stringify($('#repaymentPlanFormAdd').serializeObject());
	ajaxSubmit(serverName + "/repaymentplan/insertRepaymentPlanAndItem.do", datagridAddAllData, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
			closeDialogForAdd();
		} else {
			$.messager.alert("提示信息", "还款方案保存成功！", 'info');
			// 关闭dialog并刷新查询列表
			closeDialogForAdd();
			$("#checkName1").html("");
			$("#checkName2").html("");
		}
	});
}

// 检查是否存在相同名称的还款方案
function doCheckName(value) {
	if (value == "") {
	} else {
		$.post(serverName + "/repaymentplan/checkRepaymentPlanName.do", {
			repaymentPlanName : value
		}, function(result) {
			if (result == false) {
				$("#repaymentPlanFormAdd input[name=repaymentPlanName]").val("");
				$.messager.alert("提示", "名称为“<font color='red'>" + value + "</font>”的产品已经存在！请输入其他产品名称。", "warning");
				$("#checkName1").html("“<font color='red'>" + value + "</font>”该产品名称");
				$("#checkName2").html("&nbsp;&nbsp;已经存在");
			} else {
				$("#checkName1").html("“<font color='green'>" + value + "</font>”该产品名称");
				$("#checkName2").html("&nbsp;&nbsp;可以使用");
			}
		});
	}
}
// 根据还款方案创建还款明细
function doAppendRow() {
	if ($('#repaymentPlanFormAdd').form('validate')) {
		var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
		// var repaymentWayValue = $('#repaymentPlanFormAdd input[name=repaymentWay]').val();
		var repaymentWayValue = $("#repaymentWayAdd").combobox('getValue');
		var repaymentWayText = $("#repaymentWayAdd").combobox('getText');
		var nominalInterestRateValue = $('#repaymentPlanFormAdd input[name=nominalInterestRate]').val();
		var followupServiceFreeValue = $('#repaymentPlanFormAdd input[name=followupServiceFree]').val();
		// 还款方式按期分配
		if (repaymentWayValue == 0) {
			// 计算本金
			tempLPV = 100 / loanPeriodValue;
			precision2LPV = tempLPV.toFixed(2);
			lastLPV = 100 - precision2LPV * (loanPeriodValue - 1);
			fanalLPV = lastLPV.toFixed(2);
			// 计算利息
			// var idealInterest = (nominalInterestRateValue / 12).toFixed(2) * loanPeriodValue;
			tempNIRV = nominalInterestRateValue / 12;
			precision2NIRV = tempNIRV.toFixed(2);
			lastNIRV = nominalInterestRateValue - precision2NIRV * 11;
			fanalNIRV = lastNIRV.toFixed(2);
			// 计算服务费
			tempFSFV = followupServiceFreeValue / loanPeriodValue;
			precision2FSFV = tempFSFV.toFixed(2);
			lastFSFV = followupServiceFreeValue - precision2FSFV * (loanPeriodValue - 1);
			fanalFSFV = lastFSFV.toFixed(2);
			for ( var i = 0; i < loanPeriodValue - 1; i++) {
				$('#addRepaymentPlanDatagrid').datagrid('appendRow', {
					months : i + 1,
					principalRepayment : repaymentWayValue,
					principal : precision2LPV,
					intertestRepayment : repaymentWayValue,
					interest : precision2NIRV,
					chargeServiceMethod : repaymentWayValue,
					serviceFree : precision2FSFV
				});
			}
			$('#addRepaymentPlanDatagrid').datagrid('appendRow', {
				months : loanPeriodValue,
				principalRepayment : repaymentWayValue,
				principal : fanalLPV,
				intertestRepayment : repaymentWayValue,
				interest : fanalNIRV,
				chargeServiceMethod : repaymentWayValue,
				serviceFree : fanalFSFV
			});
			$('#repaymentPlan_reload').linkbutton('enable');
			$('#addRepaymentItemButton').linkbutton('disable');
			$('#clearRepaymentItemButton').linkbutton('disable');
			// 还款方式自定义
		} else if (repaymentWayValue == 1) {
			for ( var i = 0; i < loanPeriodValue; i++) {
				$('#addRepaymentPlanDatagrid').datagrid('appendRow', {
					months : i + 1,
					principalRepayment : repaymentWayValue,
					intertestRepayment : repaymentWayValue,
					chargeServiceMethod : repaymentWayValue
				});
				$('#addRepaymentPlanDatagrid').datagrid('beginEdit', i);
			}
			$('#repaymentPlan_reload').linkbutton('enable');
			$('#repaymentPlan_edit').linkbutton('enable');
			$('#addRepaymentItemButton').linkbutton('disable');
			$('#clearRepaymentItemButton').linkbutton('disable');
		}
		$("#repaymentPlanFormAdd input").attr("readonly", true);
		$("#repaymentWayAdd").combobox('disable');
		$("#useFlagAdd").combobox('disable');
		$("#earlyTypeAdd").combobox('disable');
	} else {
		$.messager.alert('提示', "请填入完整信息！", 'error');
	}

}
// 校验还款方案合法与否add
function doCheckRowAndSaveAdd() {
	var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
	var isValidateRow = true;
	for ( var i = 0; i < loanPeriodValue; i++) {
		if ($('#addRepaymentPlanDatagrid').datagrid('validateRow', i)) {
			isValidateRow = true;
		} else {
			isValidateRow = false;
			break;
		}
	}
	if (isValidateRow) {
		for ( var i = 0; i < loanPeriodValue; i++) {
			$('#addRepaymentPlanDatagrid').datagrid('endEdit', i);
		}
		var isComputingAddPass = doComputingAdd();
		if (isComputingAddPass) {
			doSaveRepaymentPlanAndItem();
		} else {
			for ( var i = 0; i < loanPeriodValue; i++) {
				$('#addRepaymentPlanDatagrid').datagrid('beginEdit', i);
			}
		}
	} else {
		$.messager.alert('提示', "请填入完整信息！", 'error');
	}
}

// 校验新增dialog中的AddDataGrid
function doComputingAdd() {
	// 是否校验成功
	var isComputingSuccess = false;
	// 本金标识
	var isPrincipalFlag = false;
	// 利息标识
	var isInterestFlag = false;
	// 服务费标识
	var isServiceFreeFlag = false;

	var nominalInterestRateValue = $('#repaymentPlanFormAdd input[name=nominalInterestRate]').val();
	var loanPeriodValue = $('#repaymentPlanFormAdd input[name=loanPeriod]').val();
	var followupServiceFreeValue = $('#repaymentPlanFormAdd input[name=followupServiceFree]').val();
	var idealPrincipal = 100.00;
	var atcualPrincipal = doPrincipalComputingAdd(loanPeriodValue);
	if (atcualPrincipal == idealPrincipal) {
		isPrincipalFlag = true;
	} else {
		isPrincipalFlag = false;
		$.messager.alert('提示', '本金合计应为：' + idealPrincipal + '偏差值为：' + (atcualPrincipal - idealPrincipal).toFixed(2), 'error');
	}
	var tempNIRV = nominalInterestRateValue / 12;
	var precision2NIRV = tempNIRV.toFixed(2);
	var lastNIRV = nominalInterestRateValue - precision2NIRV * 11;
	var fanalNIRV = lastNIRV.toFixed(2);
	var idealInterestTemp = getFloat(precision2NIRV * (loanPeriodValue - 1)) + getFloat(fanalNIRV);
	var idealInterest = idealInterestTemp.toFixed(2);
	var atcualInterest = doInterestComputingAdd(loanPeriodValue);
	if (atcualInterest == idealInterest) {
		isInterestFlag = true;
	} else {
		isInterestFlag = false;
		$.messager.alert('提示', '利息合计应为：' + idealInterest + '偏差值为：' + (atcualInterest - idealInterest).toFixed(2), 'error');
	}
	var idealServiceFree = followupServiceFreeValue;
	var atcualServiceFree = doServiceFreeComputingAdd(loanPeriodValue);
	if (atcualServiceFree == idealServiceFree) {
		isServiceFreeFlag = true;
	} else {
		isServiceFreeFlag = false;
		$.messager.alert('提示', '服务费合计应为：' + idealServiceFree + '偏差值为：' + (atcualServiceFree - idealServiceFree).toFixed(2), 'error');
	}
	if (isPrincipalFlag && isInterestFlag && isServiceFreeFlag) {
		isComputingSuccess = true;
	} else {
		isComputingSuccess = false;
	}
	return isComputingSuccess;
}

// 计算本金add
function doPrincipalComputingAdd(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#addRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#addRepaymentPlanDatagrid').datagrid('getSelected');
		var rowPrincipal = selectedRow.principal;
		temp = temp + getFloat(rowPrincipal);
	}
	return temp.toFixed(2);
}

// 计算名义年利率add
function doInterestComputingAdd(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#addRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#addRepaymentPlanDatagrid').datagrid('getSelected');
		var rowInterest = selectedRow.interest;
		temp = temp + getFloat(rowInterest);
	}
	return temp.toFixed(2);
}

// 计算后续服务费add
function doServiceFreeComputingAdd(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#addRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#addRepaymentPlanDatagrid').datagrid('getSelected');
		var rowServiceFree = selectedRow.serviceFree;
		temp = temp + getFloat(rowServiceFree);
	}
	return temp.toFixed(2);
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
// 清空新增还款方案form
function doClearAddRepayment() {
	$("#repaymentPlanFormAdd").form('clear');
}
// 重新生成还款明细
function doRecreatRepaymentItem(loanPeriodValue) {
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#addRepaymentPlanDatagrid').datagrid('deleteRow', 0);
	}
	$('#repaymentPlan_reload').linkbutton('disable');
	$('#repaymentPlan_edit').linkbutton('disable');
	$('#addRepaymentItemButton').linkbutton('enable');
	$('#clearRepaymentItemButton').linkbutton('enable');
	doClearAddRepayment();
}

/**
 * (｡-ω-｡)------------------------------------------------------------新增还款方案相关方法END------------------------------------------------------------(｡-ω-｡)
 */

/**
 * (｡-ω-｡)------------------------------------------------------------修改还款方案相关方法------------------------------------------------------------(｡-ω-｡)
 */

// 更新还款方案及明细
function doUpdateRepaymentPlanAndItem() {
	var loanPeriodValue = $('#repaymentPlanFormEdit input[name=loanPeriod]').val();
	$("#repaymentWayEdit").combobox('enable');
	$("#useFlagEdit").combobox('enable');
	$("#earlyTypeEdit").combobox('enable');
	var datagridEditAllData = new Object();
	var isSaveSuccess = false;
	var repaymentWayValue = $("#repaymentWayEdit").combobox('getValue');
	$('#editRepaymentPlanDatagrid').datagrid('selectAll');
	var rows = $('#editRepaymentPlanDatagrid').datagrid('getRows');
	datagridEditAllData["javaname"] = JSON.stringify(rows);
	datagridEditAllData["repaymentPlan"] = JSON.stringify($('#repaymentPlanFormEdit').serializeObject());
	ajaxSubmit(serverName + "/repaymentplan/updateRepaymentPlanAndItem.do", datagridEditAllData, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
			closeDialogForEdit();
			$('#repaymentDataGrid').datagrid('reload');
		} else {
			$.messager.alert("提示信息", "还款方案保存成功！", 'info');
			// 关闭dialog并刷新查询列表
			closeDialogForEdit();
			$('#repaymentDataGrid').datagrid('reload');
		}
	});
}
// 校验还款方案合法与否edit
function doCheckRowAndSaveEdit() {
	var loanPeriodValue = $('#repaymentPlanFormEdit input[name=loanPeriod]').val();
	var repaymentWayValue = $("#repaymentWayEdit").combobox('getValue');
	if (repaymentWayValue == 0) {
		repickRepayment();
	}
	var isValidateRow = true;
	for ( var i = 0; i < loanPeriodValue; i++) {
		if ($('#editRepaymentPlanDatagrid').datagrid('validateRow', i)) {
			isValidateRow = true;
		} else {
			isValidateRow = false;
			break;
		}
	}
	if (isValidateRow) {
		for ( var i = 0; i < loanPeriodValue; i++) {
			$('#editRepaymentPlanDatagrid').datagrid('endEdit', i);
		}
		var isComputingEditPass = doComputingEdit();
		if (isComputingEditPass) {
			doUpdateRepaymentPlanAndItem();
		} else {
			for ( var i = 0; i < loanPeriodValue; i++) {
				$('#editRepaymentPlanDatagrid').datagrid('beginEdit', i);
			}
		}
	} else {
		$.messager.alert('提示', "请填入完整信息！", 'error');
	}
}
// 校验EditDataGrid
function doComputingEdit() {
	// 是否校验成功
	var isComputingSuccess = false;
	// 本金标识
	var isPrincipalFlag = false;
	// 利息标识
	var isInterestFlag = false;
	// 服务费标识
	var isServiceFreeFlag = false;

	var nominalInterestRateValue = $('#repaymentPlanFormEdit input[name=nominalInterestRate]').val();
	var loanPeriodValue = $('#repaymentPlanFormEdit input[name=loanPeriod]').val();
	var followupServiceFreeValue = $('#repaymentPlanFormEdit input[name=followupServiceFree]').val();
	var idealPrincipal = 100.000;
	var atcualPrincipal = doPrincipalComputingEdit(loanPeriodValue);
	if (atcualPrincipal == idealPrincipal) {
		isPrincipalFlag = true;
	} else {
		isPrincipalFlag = false;
		$.messager.alert('提示', '本金合计应为：' + idealPrincipal + '偏差值为：' + (atcualPrincipal - idealPrincipal).toFixed(2), 'error');
	}
	var tempNIRV = nominalInterestRateValue / 12;
	var precision2NIRV = tempNIRV.toFixed(2);
	var lastNIRV = nominalInterestRateValue - precision2NIRV * 11;
	var fanalNIRV = lastNIRV.toFixed(2);
	var idealInterestTemp = getFloat(precision2NIRV * (loanPeriodValue - 1)) + getFloat(fanalNIRV);
	var idealInterest = idealInterestTemp.toFixed(2);
	var atcualInterest = doInterestComputingEdit(loanPeriodValue);
	if (atcualInterest == idealInterest) {
		isInterestFlag = true;
	} else {
		isInterestFlag = false;
		$.messager.alert('提示', '利息合计应为：' + idealInterest + '偏差值为：' + (atcualInterest - idealInterest).toFixed(2), 'error');
	}
	var idealServiceFree = followupServiceFreeValue;
	var atcualServiceFree = doServiceFreeComputingEdit(loanPeriodValue);
	if (atcualServiceFree == idealServiceFree) {
		isServiceFreeFlag = true;
	} else {
		isServiceFreeFlag = false;
		$.messager.alert('提示', '服务费合计应为：' + idealServiceFree + '偏差值为：' + (atcualServiceFree - idealServiceFree).toFixed(2), 'error');
	}
	if (isPrincipalFlag && isInterestFlag && isServiceFreeFlag) {
		isComputingSuccess = true;
	} else {
		isComputingSuccess = false;
	}
	return isComputingSuccess;
}
// 计算本金edit
function doPrincipalComputingEdit(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#editRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#editRepaymentPlanDatagrid').datagrid('getSelected');
		var rowPrincipal = selectedRow.principal;
		temp = temp + getFloat(rowPrincipal);
	}
	return temp.toFixed(2);
}

// 计算名义年利率edit
function doInterestComputingEdit(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#editRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#editRepaymentPlanDatagrid').datagrid('getSelected');
		var rowInterest = selectedRow.interest;
		temp = temp + getFloat(rowInterest);
	}
	return temp.toFixed(2);
}

// 计算后续服务费edit
function doServiceFreeComputingEdit(loanPeriodValue) {
	var temp = 0;
	for ( var i = 0; i < loanPeriodValue; i++) {
		$('#editRepaymentPlanDatagrid').datagrid('selectRow', i);
		var selectedRow = $('#editRepaymentPlanDatagrid').datagrid('getSelected');
		var rowServiceFree = selectedRow.serviceFree;
		temp = temp + getFloat(rowServiceFree);
	}
	return temp.toFixed(2);
}
// 重新更新还款方案明细
function repickRepayment() {
	if ($('#repaymentPlanFormEdit').form('validate')) {
		var loanPeriodValue = $('#repaymentPlanFormEdit input[name=loanPeriod]').val();
		var repaymentWayValue = $("#repaymentWayEdit").combobox('getValue');
		var repaymentWayText = $("#repaymentWayEdit").combobox('getText');
		var nominalInterestRateValue = $('#repaymentPlanFormEdit input[name=nominalInterestRate]').val();
		var followupServiceFreeValue = $('#repaymentPlanFormEdit input[name=followupServiceFree]').val();

		// 计算本金
		tempLPV = 100 / loanPeriodValue;
		precision2LPV = tempLPV.toFixed(2);
		lastLPV = 100 - precision2LPV * (loanPeriodValue - 1);
		fanalLPV = lastLPV.toFixed(2);
		// 计算利息
		tempNIRV = nominalInterestRateValue / 12;
		precision2NIRV = tempNIRV.toFixed(2);
		lastNIRV = nominalInterestRateValue - precision2NIRV * 11;
		fanalNIRV = lastNIRV.toFixed(2);
		// 计算服务费
		tempFSFV = followupServiceFreeValue / loanPeriodValue;
		precision2FSFV = tempFSFV.toFixed(2);
		lastFSFV = followupServiceFreeValue - precision2FSFV * (loanPeriodValue - 1);
		fanalFSFV = lastFSFV.toFixed(2);
		for ( var i = 0; i < loanPeriodValue - 1; i++) {
			$('#editRepaymentPlanDatagrid').datagrid('updateRow', {
				index : i,
				row : {
					principal : precision2LPV,
					interest : precision2NIRV,
					serviceFree : precision2FSFV
				}
			});
		}
		$('#editRepaymentPlanDatagrid').datagrid('updateRow', {
			index : loanPeriodValue - 1,
			row : {
				principal : fanalLPV,
				interest : fanalNIRV,
				serviceFree : fanalFSFV
			}
		});
	} else {
		$.messager.alert('提示', "请填入完整信息", 'error');
	}
}
/**
 * (｡-ω-｡)------------------------------------------------------------修改还款方案相关方法END------------------------------------------------------------(｡-ω-｡)
 */

/**
 * (｡-ω-｡)------------------------------------------------------------这里都是临时的Test方法------------------------------------------------------------(｡-ω-｡)
 */

/**
 * (｡-ω-｡)------------------------------------------------------------这里都是临时的Test方法END------------------------------------------------------------(｡-ω-｡)
 */
