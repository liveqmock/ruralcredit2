var row;
$(function() {
	var dic = new DataDictionary();
	dic.addDic("cardFlag_cardFlag_Y", "cardFlag");
	dic.addDic("onUsed_onUsed_Y", "onUsed");
	dic.addDic("bankNum_bankNum_Y", "bankNum");
	dic.addDic("useType_useType_Y", "useType");
	dic.dicAjax();
	window.onresize = function(){
	    setTimeout(function(){
	  	  $('#accountInfoList').resizeDataGrid(155, 20, 0, 0);
	    }, 500);
	};
	// easyui-validatebox拓展验证
	$.extend($.fn.validatebox.defaults.rules, {
		// 验证数字
		number : {
			validator : function(value, param) {
				if (value) {
					return /^[1-9][0-9]*(\.[0-9]+)?$/.test(value);
				} else {
					return true;
				}
			},
			message : '只能输入非0开头的数字.'
		},
		date : {
			validator : function(value, param) {
				if (value) {
					return /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/.test(value);
				} else {
					return true;
				}
			},
			message : '请选择日期.'
		},
		select : {
			validator : function(value, param) {
				if (value == "请选择") {
					return false;
				} else {
					return true;
				}
			},
			message : '请选择'
		}
	});
	$("#departmentId").combobox({
		url:serverName+"/accountInfo/getDepartmentList.do",
		textField : 'departmentName',
		valueField: 'departmentId',
		mode : 'remote',
		delay: 500,
		width:150,
		value: '',
		onSelect:function(){
			$("#branchNameForm").val($("#departmentId").combobox("getText"));
		}
	});
	//县镇下拉框
//	showCityCombo("provinceId", "cityId", "districtId");
	
	$("#accountInfoList").datagrid({
		padding : '0',
		border : '0',
		title : '个人账户信息',
		pagination : true,
		idfield : 'accountInfoId',
		striped : true,
		singleSelect : true,
		sortName : 'branchName',
		sortOrder : 'desc',
		rownumbers: true,
		url : serverName + "/accountInfo/selectByPage.do?accountType=1",
		columns : [ [  {
			field : "mobilephone",
			title : "手机号码",
			width : 150
		}, {
			field : "branchName",
			title : "分公司名称",
			width : 150
		}, {
			field : "departmentId",
			title : "分公司id",
			width : 150,
			hidden : true
		} ,{
			field : "bankPrefectureNum",
			title : "所在行地区号",
			width : 150
		}, {
			title : "",
			field : "bankNum",
			width : 150,
			hidden : true
		}, {
			field : "borrowerName",
			title : "借款人姓名",
			width : 150
		}, {
			title : "借款人身份证号",
			field : "borrowerCredentialsNumber",
			width : 150
		}, {
			title : "所在行别",
			field : "bankRank",
			width : 150
		}, {
			title : "开户行",
			field : "bankName",
			width : 150
		}, {
			title : "账户名",
			field : "accountName",
			width : 150
		}, {
			title : "账号",
			field : "account",
			width : 150
		}, {
			field : "payBranchno",
			title : "支付行号",
			width : 150
		}, {
			field : "credentialsNumber",
			title : "账户身份证号",
			width : 150
		}, {
			field : "cardFlag",
			title : "卡折类型",
			formatter : function(value) {
				if (value == 0) {
					return "<font color='green'>卡</font>";
				} else {
					return "<font color='red'>存折</font>";
				}
			},
			width : 150
		}, {
			field : "useType",
			title : "账号用途",
			formatter : function(value) {
				if (value == 0) {
					return "<font color='green'>付款</font>";
				} else if (value == 1) {
					return "<font color='red'>收款</font>";
				} else {
					return "<font color='blue'>收付款</font>";
				}
			},
			width : 150
		}, {
			field : "accountType",
			title : "账号类型",
			/*formatter : function(value) {
				if (value == 0) {
					return "<font color='red'>公司账户</font>";
				} else {
					return "<font color='blue'>个人账户</font>";
				}
			},*/
			width : 150
		}, {
			field : "onUsed",
			title : "是否启用",
			formatter : function(value) {
				if (value == 0) {
					return "<font color='green'>停用</font>";
				} else {
					return "<font color='red'>启用</font>";
				}
			},
			width : 150
		}, {
			field : "operaterName",
			title : "最后一次操作人",
			width : 150
		}, {
			field : "operaterId",
			title : "最后一次操作人id",
			width : 150,
			hidden : true
		}, {
			field : "updateTime",
			title : "最后一次操作时间",
			width : 150
		}, {
			field : "createTime",
			title : "创建时间",
			width : 150
		}] ],
		toolbar : [/* {
			id : "addAcountInfo",
			text : "添加账户信息",
			iconCls:"icon-addOne",
			handler : function() {
				addAcountInfo();
			}
		}, */{
			id : "showUpdateAcountInfo",
			text : "修改账户信息",
			iconCls : "icon-edit",
			handler : function() {
				showUpdateAcountInfo();
			}

		}/*, {
			id : "deleteAcountInfo",
			text : "删除账户信息",
			iconCls : "icon-remove",
			handler : function() {
				deleteAcountInfo();
			}
		}*/]
	});
	 $('#accountInfoList').resizeDataGrid(155, 20, 0, 0);
//
//	$("#accountTab").tabs({
//		onSelect : function(title){
//			if(title == "模糊搜索"){
//				$("#provinceId").combobox("clear");
//				$("#cityId").combobox("clear");
//				$("#districtId").combobox("clear");
//				$("#branchName").attr("value", "");
//			}
//			if(title == "高级搜索"){
//				$("#fuzzyValue").val("");
//			}
//		}
//	});
    /*更改：分公司名称下拉选择框为两级树形菜单*/
    departmentComboboxTreeWithPanelWidth("branchName",false,250);
});

function showCodeTable() {
	var bankNum = $("#bankNum").combobox("getValue");
}

// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
function showCityCombo(province, city, district) {
	var provinceid = $("#provinceId1").combobox("getValue");

	var province = $("#" + province).combobox(
					{
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
							$("#" + city).combobox(
											{
												editable : false,
												url : serverName
														+ '/NSC/listCity.do?parentId='
														+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value : '',
												onSelect : function(value) {
													$("#" + district).combobox(
																	{
																		editable : false,
																		url : serverName
																				+ '/NSC/listCity.do?parentId='
																				+ value.cityCode,
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
function searchAcountInfo() {
    var departmentIds = $("#branchName").combotree("getValues");
    if (departmentIds != null && departmentIds != "") {
        for (var i in departmentIds) {
            departmentIds[i] = "'" + departmentIds[i] + "'";
        }
    }
	$("#accountInfoList").datagrid("load", {
//		provinceId : $("#provinceId").combobox("getValue"),
//		cityId : $("#cityId").combobox("getValue"),
//		districtId : $("#districtId").combobox("getValue"),
//		branchName : $("#branchName").val(),
        departmentId : departmentIds.join(","),
		borrowerName : $.trim($("#borrowerName").val()),
		borrowerCredentialsNumber : $.trim($("#borrowerCredentialsNumber").val()),
		accountName : $("#accountNameSearch").val(),
		account : $("#accountSearch").val(),
		credentialsNumber : $("#credentialsNumberSearch").val()
		
	});
}

function addAcountInfo() {

	$("#showForm").dialog('open').dialog('setTitle', '添加账号信息');
	provinceShowPublic("provinceId1", "cityId1", "districtId1");
	$("#accountInfo").form("clear");
	$("#accountType").val("1");
	showCodeTable();
	url = serverName + "/accountInfo/addAccountInfo.do";
	$("#accountInfo").form("validate");
}

function addOrUpdate() {
	$("#bankCancelButton").linkbutton("disable");
	$("#bankAddButton").linkbutton("disable");
	$("#bankRank").val($("#bankNum").combobox("getText"));
	if ($("#accountInfo").form("validate")) {
		ajaxSubmit(url, $("#accountInfo").serializeObject(), recfunc);
	} else {
		$.messager.show({
			msg : "请将数据填写完整！",
			title : "消息"
		});
		$("#bankCancelButton").linkbutton("enable");
		$("#bankAddButton").linkbutton("enable");
	}
}
function showUpdateAcountInfo() {
	var rows = $("#accountInfoList").datagrid("getSelections");
	if (rows.length == 1) {
		row = $("#accountInfoList").datagrid("getSelected");
		$("#showForm").dialog('open').dialog('setTitle', '修改账号信息');

		$("#accountInfo").form("load", row);
		showCityCombo("provinceId1", "cityId1", "districtId1");
		showCodeTable();
		$("#departmentId").combobox("setValue",$("#departmentId").combobox("getValue"));
		url = serverName + "/accountInfo/addOrUpdate.do";
		var defultAcount = $("#accountName").combobox("getValue");
		$("#accountName").combobox({
			width:153,
			url:serverName+"/creditgroup/selectBorrowerFamilyByID.do?credentialsNumber="+$("#borrowerCredentialsNumberLook").val(),
			mode:'remote',
			editable:false,
			required:true,
			valueField : "name",
			textField : "name",
			value : defultAcount,
			onSelect:function(data){
				$("#credentialsNumber").val(data.credentialsNumber);
			} 
		});
		$("#accountInfo").form("validate");
	} else {
		$.messager.show({
			msg : "请选择一条记录进行编辑！您已经选择了" + rows.length + "条记录！",
			title : "消息"
		});
		row = undefined;
	}

}
function recfunc(result) {
	if (result.success) {
		$("#accountInfo").form("clear");
		$("#accountType").val("1");
		$("#showForm").dialog("close");
		$("#accountInfoList").datagrid("load");
		$.messager.show({
			msg : "保存成功!",
			title : "消息"
		});
		$("#bankCancelButton").linkbutton("enable");
		$("#bankAddButton").linkbutton("enable");
	} else {
		$.messager.show({
			msg : "保存失败!",
			title : "消息"
		});
		$("#bankCancelButton").linkbutton("enable");
		$("#bankAddButton").linkbutton("enable");
	}
}

function reset() {
	if (row == undefined || row == null) {
		$("#accountInfo").form("clear");
		$("#accountType").val("1");
	}
}

function cancel() {
	$("#accountInfo").form("clear");
	$("#accountType").val("1");
	$("#showForm").dialog("close");
}
function showAll() {
//	$("#provinceId").combobox("clear");
//	$("#cityId").combobox("clear");
//	$("#districtId").combobox("clear");
//	$("#branchName").attr("value", "");
    $("#branchName").combotree("setValues","");
	$("#borrowerName").val("");
	$("#borrowerCredentialsNumber").val("");
	 $("#accountNameSearch").val("");
	 $("#accountSearch").val("");
	 $("#credentialsNumberSearch").val("");
    searchAcountInfo();
}

function searchFuzzy(){
	var fuzzyValue = $("#fuzzyValue").val();
	$("#accountInfoList").datagrid("load",{
		fuzzyValue: $.trim(fuzzyValue)
	}); 
}

function cancelSearch(){
	$("#fuzzyValue").val("");
    searchFuzzy();
}

