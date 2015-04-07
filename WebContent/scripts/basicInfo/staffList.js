$(function() {
	var dic = new DataDictionary();
	dic.addDic("isActive", "isActive");
	dic.dicAjax();
	//员工列表
	$("#staffList").datagrid({
		padding : '0',
		border : '0',
		title : '员工列表',
		pagination : true,
		idfield : 'employeeId',
		striped : true,
		singleSelect : true,
		rownumbers: true,
		fitColumns:true,
		url : serverName +"/employeeController/selectAllEmployee.do",
		frozenColumns :[[
		         {  title : '操作', 
			        field : 'operateFlag', 
			        width : '100', 
			        formatter :function (value, rowData, rowIndex){
			        	/*console.info(rowData);
			        	console.info(value);
			        	console.info(rowIndex);*/
			        	
			        	/*var row = $("#creditApplicationList").datagrid("getSelected");
			        	var centerj = window.parent;
			        	centerj.addTabFun({
			        				src : serverName
			        						+ "/creditgroup/searchLook.do?creditApplicationId="
			        						+ row.creditapplicationId + "&creditInvestigatioId="
			        						+ row.fxid,
			        				title : row.groupNumber + "申请单查看"
			        			});*/
			        	
			        	return "<a id='' style='color: #9932cc;' onclick='openHistory("+rowData.comEmpId+")'>变更历史</a>";
			        }
		         },
				{
					field : "comempno",
					title : "员工编号",
					width : 100
				}, {
					field : "account",
					title : "用户名",
					width : 100
				}, 
				 {
					field : "name",
					title : "姓名",
					width : 100
				}
		                 ]],
		columns : [ [ 
		{
			field : "activestatus",
			title : "在职状态",
			width : 150
		}, {
			title : "账户状态",
			field : "isactive",
			width : 150
		}, {
			field : "roledesc",
			title : "角色",
			width : 150
		}, {
			title : "部门名称",
			field : "departmentname",
			width : 150
		}, {
			title : "所属营业部",
			field : "areadepartmentname",
			width : 150
		}, {
			title : "所属分中心",
			field : "citydepartmentname",
			width : 150
		}, {
			title : "开通权限时间",
			field : "openauthoritydate",
			width : 150
		}, {
			title : "关闭权限时间",
			field : "closeauthoritydate",
			width : 150
		}, {
			field : "entrytime",
			title : "入职时间",
			width : 150
		}, {
			field : "emailaddr",
			title : "邮箱",
			width : 150
		}] ]
	});
	 $('#staffList').resizeDataGrid(155, 20, 0, 0);
    /*更改：分公司名称下拉选择框为两级树形菜单*/
    departmentComboboxTreeWithPanelWidth("branchName",false,250);
});

//条件搜索
function searchEmployee(){
	var areadepartmentid = $("#branchName").combotree("getValue");
	if(areadepartmentid==undefined){
		areadepartmentid = "";
	}
	$("#staffList").datagrid("load",{
		areadepartmentid : areadepartmentid,
		account : $.trim($("#account").val()),
		name : $.trim($("#name").val()),
		isactive : $("#isActive").combobox("getValue"),
		comempno : $.trim($("#comempno").val()),
		roledesc : $.trim($("#rolename").val())
	});
	
}
//清空
function clearAll(){
	 $("#branchName").combotree("setValues","");
	$("#account").val("");
	$("#name").val("");
	$("#isActive").combobox("setValue","");
	$("#comempno").val("");
	$("#rolename").val("");
}
//同步数据
function synchronizationData(){
	$("#synchronizationData").linkbutton("disable");
	$.ajax({
		type: "POST",
		url : serverName+'/employeeController/synchronizationData.do?flag=1',
		success:function (data){
			if(data.success){
				clearAll();
				searchEmployee();
				$.messager.alert("消息", "同步成功");
				$("#synchronizationData").linkbutton("enable");
			}else{
				$.messager.alert("消息", "同步失败");
				$("#synchronizationData").linkbutton("enable");
			}
		}
		
	});
}

//导出excel
function downloadExcel(){
	var areadepartmentid = $("#branchName").combotree("getValue");
	var account = $.trim($("#account").val());
	var name = $.trim($("#name").val());
	var isactive = $("#isActive").combobox("getValue");
	var comempno = $.trim($("#comempno").val());
	var rolename = $.trim($("#rolename").val());
	if(areadepartmentid==undefined){
		areadepartmentid = "";
	}
	window.location.href = serverName+'/employeeController/downloandExcel.do?areadepartmentid='+areadepartmentid+'&account='+account+
							'&name='+name+'&isactive='+isactive+'&comempno='+comempno+'&rolename='+rolename;
}

//打开变更历史
function openHistory(comEmpId){
	var centerj = window.parent;
	centerj.addTabFun({
				src : serverName+ "/employeeChangeController/toHistoryChange.do?comEmpId="+comEmpId,
				title : "变更历史"
			});
}


/*function showCodeTable() {
	var bankNum = $("#bankNum").combobox("getValue");
}*/

// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
/*function showCityCombo(province, city, district) {
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

}*/
/*function searchAcountInfo() {
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
}*/

/*function addAcountInfo() {

	$("#showForm").dialog('open').dialog('setTitle', '添加账号信息');
	provinceShowPublic("provinceId1", "cityId1", "districtId1");
	$("#accountInfo").form("clear");
	$("#accountType").val("1");
	showCodeTable();
	url = serverName + "/accountInfo/addAccountInfo.do";
	$("#accountInfo").form("validate");
}*/

/*function addOrUpdate() {
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
}*/
/*function showUpdateAcountInfo() {
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

}*/
/*function recfunc(result) {
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
}*/

/*function reset() {
	if (row == undefined || row == null) {
		$("#accountInfo").form("clear");
		$("#accountType").val("1");
	}
}*/

/*function cancel() {
	$("#accountInfo").form("clear");
	$("#accountType").val("1");
	$("#showForm").dialog("close");
}*/
/*function showAll() {
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
}*/

/*function searchFuzzy(){
	var fuzzyValue = $("#fuzzyValue").val();
	$("#accountInfoList").datagrid("load",{
		fuzzyValue: $.trim(fuzzyValue)
	}); 
}*/

/*function cancelSearch(){
	$("#fuzzyValue").val("");
    searchFuzzy();
}*/

/*function optionFormatter(){
	return "<a id='' style='color: #9932cc;' onclick='doSearchRepaymentPlan(" + rowIndex + ")'>变更历史</a>";
}*/

