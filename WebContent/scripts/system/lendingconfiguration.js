$(function() {
	
	
	$("#departmentTree").tree({
		url : serverName + "/easyUIController/getDepartmentComboboxTree.do",
		onSelect : function(note) {
			var children = $("#departmentTree").tree("getChildren", note.target);
			if (children == null || children == "") {
				$("#areaDepartmentId").val(note.id);
				$("#areaDepartmentName").val(note.text);
				$("#name").html(note.text);
				$("#discountConfigurationList").datagrid("load", {
					areaDepartmentId : note.id
				});
			} else if (note.id == 0) {
				$("#areaDepartmentId").val(note.id);
				$("#areaDepartmentName").val(note.text);
				$("#name").html(note.text);
				$("#discountConfigurationList").datagrid("load", {});
			} else {

			}
		}
	});
	$("#discountConfigurationList").datagrid({
		url : serverName+"/lendingConfigurationController/selectAll.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		columns : [ [  {
			field : 'areaDepartmentId',
			title : '营业部ID',
			width : 100,
			hidden : false
		}, {
			field : 'areaDepartmentName',
			title : '营业部名称',
			width : 100,
			hidden : false
		},  {
			field : 'trustLending',
			title : '信托放款',
			width : 100,
			hidden : false
		}, {
			field : 'debentureTransfer',
			title : '债权转让',
			width : 100,
			hidden : false
		}] ],
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
		onLoadSuccess : function(data) {
		}
	});
});
function addOne() {var dic = new DataDictionary();
	var dic = new DataDictionary();
	dic.addDic("trustLending_Y", "trustLending");
	dic.addDic("debentureTransfer_Y", "debentureTransfer");
	dic.dicAjax();
	var areaDepartmentId = $("#areaDepartmentId").val();
	var areaDepartmentName = $("#areaDepartmentName").val();
	if (areaDepartmentId == 0 || areaDepartmentId == null || areaDepartmentId == "") {
		$.messager.alert("消息", "请在树形菜单选择营业部！", "info");
	} else {
		$("#areaDepartmentIdForm").val(areaDepartmentId);
		$("#areaDepartmentNameForm").val(areaDepartmentName);
		$("#configForm").form("validate");
		$("#configDialog").dialog("open");
	}
}
function save() {
	if ($("#configForm").form("validate")) {
		$("#saveButton").linkbutton("disable");
		$("#cancelButton").linkbutton("disable");
		var trus = $("#trustLending").combobox("getValue");
		var deben = $("#debentureTransfer").combobox("getValue");
		if(("1"==trus && "1"==deben) || ("否"==trus && "否"== deben) || ("1"==trus && "否"==deben) || ("否"==trus && "1"==deben)){
			$.messager.alert("消息", "不能同时选择否", "info");
			$("#saveButton").linkbutton("enable");
			$("#cancelButton").linkbutton("enable");
			return;
		}
		ajaxSubmit(serverName + "/lendingConfigurationController/saveLendingConfiguration.do", $("#configForm").serialize(), function(r) {
			$("#saveButton").linkbutton("enable");
			$("#cancelButton").linkbutton("enable");
			if (r.success) {
				$("#configForm").form("clear");
				$("#configDialog").dialog("close");
				$("#discountConfigurationList").datagrid("reload");
			}
			$.messager.show({
				showType : "show",
				timeout : 5000,
				title : "消息",
				msg : r.msg
			});
		});
	} else {
		$.messager.alert("消息", "请填写完整信息！", "info");
	}
}
//关闭
function cancel() {
	$("#configForm").form("clear");
	$("#configDialog").dialog("close");
}
//编辑
function edit() {
	var areaDepartmentId = $("#areaDepartmentId").val();
	var areaDepartmentName = $("#areaDepartmentName").val();
	if (areaDepartmentId == 0 || areaDepartmentId == null || areaDepartmentId == "") {
		$.messager.alert("消息", "请在树形菜单选择营业部！", "info");
	} else {
		var getSelected = $("#discountConfigurationList").datagrid("getSelected");
		if (getSelected == "" || getSelected == null) {
			$.messager.alert("消息", "请先选择一条数据！", "info");
		} else {
			//getSelected.discountRate = getSelected.discountRate * 100;
			$("#configForm").form("load", getSelected);
			$("#configForm").form("validate");
			$("#configDialog").dialog("open");
		}

	}
}
//删除
function removeOne() {
	var areaDepartmentId = $("#areaDepartmentId").val();
	var areaDepartmentName = $("#areaDepartmentName").val();
	if (areaDepartmentId == 0 || areaDepartmentId == null || areaDepartmentId == "") {
		$.messager.alert("消息", "请在树形菜单选择营业部！", "info");
	} else {
		var getSelected = $("#discountConfigurationList").datagrid("getSelected");
		if (getSelected == "" || getSelected == null) {
			$.messager.alert("消息", "请先选择一条数据！", "info");
		} else {
			$("#addOneButton").linkbutton("disable");
			$("#editButton").linkbutton("disable");
			$("#removeButton").linkbutton("disable");
			ajaxSubmit(serverName + "/lendingConfigurationController/removeLendingConfiguration.do", {
				lendingConfigurationId : getSelected.lendingConfigurationId
			}, function(r) {
				$("#addOneButton").linkbutton("enable");
				$("#editButton").linkbutton("enable");
				$("#removeButton").linkbutton("enable");
				$("#discountConfigurationList").datagrid("reload");
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : r.msg
				});
			});
		}

	}
}