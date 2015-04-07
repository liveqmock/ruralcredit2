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
				$("#productCategoryIdForm").combobox({
					url : serverName + "/discountConfigurationController/selectProInfo.do?areaDepartmentId=" + $("#areaDepartmentId").val(),
					textField : 'productName',
					valueField : 'producttypeid',
					required : true,
					onSelect : function(data) {
						// console.info(data);
						$("#productinfoIdForm").val(data.productInfoId);
						$("#productNameForm").val(data.productName);
					}
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
		url : serverName + "/discountConfigurationController/discountConfigurationDateGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		columns : [ [ {
			field : 'discountConfigurationId',
			title : '打折配置主键',
			width : 100,
			hidden : false
		}, {
			field : 'areaDepartmentId',
			title : '营业部ID',
			width : 100,
			hidden : false
		}, {
			field : 'areaDepartmentName',
			title : '营业部名称',
			width : 100,
			hidden : false
		}, {
			field : 'productinfoId',
			title : '产品版本编号',
			width : 100,
			hidden : false
		}, {
			field : 'productCategoryId',
			title : '产品分类编号',
			width : 100,
			hidden : false
		}, {
			field : 'productName',
			title : '产品名称',
			width : 100,
			hidden : false
		}, {
			field : 'discountRate',
			title : '折扣率%',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return (value * 100).toFixed(2);
			},
			hidden : false
		} ] ],
		onLoadSuccess : function(data) {
		}
	});
});
function addOne() {
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
		ajaxSubmit(serverName + "/discountConfigurationController/saveDiscountConfiguration.do", $("#configForm").serialize(), function(r) {
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
function cancel() {
	$("#configForm").form("clear");
	$("#configDialog").dialog("close");
}
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
			getSelected.discountRate = getSelected.discountRate * 100;
			$("#configForm").form("load", getSelected);
			$("#configForm").form("validate");
			$("#configDialog").dialog("open");
		}

	}
}
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
			ajaxSubmit(serverName + "/discountConfigurationController/removeDiscountConfigurationByDiscountConfigurationId.do", {
				discountConfigurationId : getSelected.discountConfigurationId
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