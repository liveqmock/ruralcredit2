//申请退回方法
function updateAcceptConsultRollBack() {
	// 禁用保存按钮
	window.parent.buttonBackPooldisDisablRollbacke();
	var consultPoolId = $("#consultPoolId").val();
	if ($("#acceptConsultRollbackForm").form("validate")) {
		//查询当时的状态为什么状态
		ajaxSubmit(serverName+"/inquirePoolOfficeController/selectCustomerConsultPool.do",{consultPoolId:consultPoolId},function(result){
			if(result.acceptConsultState== "3"){
				window.parent.buttonBackPooldisEnableRollback();
				$.messager.alert("消息","该用户已申请退回了！");
				//$("#inquire_pool_office").datagrid("reload");
			}else{
		$.post(serverName + "/acceptAdviceController/updateRollbackReason.do", $("#acceptConsultRollbackForm").serialize(), function(o) {
			if (o.success) {
				window.parent.buttonBackPooldisEnableRollback();
				window.parent.showMessage("操作成功");
				parent.$('#rollbackDiv').dialog('close');
				parent.$("#inquire_pool_office").datagrid("reload");
				parent.$("#rollbackframe").attr("src", "");
			} else {
				window.parent.buttonBackPooldisEnableRollback();
				window.parent.showMessage("操作失败");
				parent.$('#rollbackDiv').dialog('close');
				parent.$("#rollbackframe").attr("src", "");
			}
		}, "json");
	}
});
	} else {
		window.parent.buttonBackPooldisEnableRollback();
		$.messager.alert('操作提示', '退回原因不能为空！', 'error');
	}
}
//确认退回操作
function updateAcceptConsultRollBackConfirm() {
	// 禁用保存按钮
	window.parent.buttonBackPooldisDisableRollbackConfirm();
	var consultPoolId = $("#consultPoolId").val();
	//查询当时的状态为什么状态
	ajaxSubmit(serverName+"/inquirePoolOfficeController/selectCustomerConsultPool.do",{consultPoolId:consultPoolId},function(result){
		if(result.marketConsultState== "1"){
			window.parent.buttonBackPooldisEnableRollbackConfirm();
			$.messager.alert("消息","该用户已确认退回了！");
			//$("#inquire_pool_office").datagrid("reload");
		}else if(result.acceptConsultState== "0"){
			window.parent.buttonBackPooldisEnableRollbackConfirm();
			$.messager.alert("消息","该用户已取消退回了！");
			//$("#inquire_pool_office").datagrid("reload");	
		}else{
	$.messager.confirm("消息","确认退回吗?",function(r){
		if(r){
		$.post(serverName + "/acceptAdviceController/updateRollbackReasonConfirm.do", $("#acceptConsultRollbackConfirmForm").serialize(), function(o) {
			if (o.success) {
				//启用
				window.parent.buttonBackPooldisEnableRollbackConfirm();
				window.parent.showMessage("操作成功");
				parent.$('#rollbackDivConfirm').dialog('close');
				parent.$("#inquire_pool_office").datagrid("reload");
				parent.$("#rollbackframeConfirm").attr("src", "");
			} else {
				window.parent.buttonBackPooldisEnableRollbackConfirm();
				window.parent.showMessage("操作失败");
				parent.$('#rollbackDivConfirm').dialog('close');
				parent.$("#rollbackframeConfirm").attr("src", "");
			}
		}, "json");
		}
		//启用
		window.parent.buttonBackPooldisEnableRollbackConfirm();
	}); 
		}
	});
}
//取消退回确认   状态改为 ：待分件
function updateAcceptConsultRollBackCancel(){
	// 禁用保存按钮
	window.parent.buttonBackPooldisDisableRollbackConfirm();
	var consultPoolId = $("#consultPoolId").val();
	//查询当时的状态为什么状态
	ajaxSubmit(serverName+"/inquirePoolOfficeController/selectCustomerConsultPool.do",{consultPoolId:consultPoolId},function(result){
		if(result.acceptConsultState== "0"){
			$.messager.alert("消息","该用户已取消退回了！");
		}else{
	$.post(serverName + "/acceptAdviceController/updateRollbackReasonCancel.do", $("#acceptConsultRollbackConfirmForm").serialize(), function(o) {
		if (o.success) {
			// 启用保存按钮
			window.parent.buttonBackPooldisEnableRollbackConfirm();
			//window.parent.showMessage("操作成功");
			parent.$('#rollbackDivConfirm').dialog('close');
			parent.$("#inquire_pool_office").datagrid("reload");
			parent.$("#rollbackframeConfirm").attr("src", "");
		} else {
			// 启用保存按钮
			window.parent.buttonBackPooldisEnableRollbackConfirm();
			//window.parent.showMessage("操作失败");
			parent.$('#rollbackDivConfirm').dialog('close');
			parent.$("#rollbackframeConfirm").attr("src", "");
		}
	}, "json");
		}
	}); 
}


//此处还可以输入多少个字
function textCount(textId, htmlId, max) {
	$("#" + textId).keyup(function() {
		var maxl = max;
		var tishi = "还可以输入" + maxl + "个字";
		$("#" + htmlId).html(tishi);
		var xianyou = $(this).val().length;
		var keyi = maxl - xianyou;
		var tishi = "还可以输入" + keyi + "个字";
		if (xianyou > (max - 1)) {
			var tishi = "还可以输0个字";
			$("#" + htmlId).css({
				"color" : "red"
			});
			var s = $("#" + textId).val().substr(0, 200);
			$("#" + textId).val();
		} else {
			$("#" + htmlId).css({
				"color" : "#000"
			});
		}
		$("#" + htmlId).html(tishi);
	});
}
