// 申请类别的数据字典
$(function() {
	var dicReport2 = new DataDictionary();
	dicReport2.addDic("applyType_Y", "applyType");
	// $("#specialApplyViewContents").validatebox({required:true});
	dicReport2.dicAjax();
	$("#specialApplyForm").form("validate");
});
// 保存新增特殊申请信息
function save() {
	$("#submitSave").linkbutton("disable");
	alert("请不要重复点击提交按钮，耐心等待页面关闭并刷新,如果有错误会有提示，请不要重复操作！！");
		if ($("#specialApplyForm").form('validate')) {
			// 禁用提交按钮
			//window.parent.buttonSpecialdisDisable();
					/*
					 * if (r.imgAmount <= 0) { $.messager.alert("消息", "请先上传资料"); } else {
					 */
					ajaxSubmit(serverName + "/specialApplyController/saveSpecialApply.do", $("#specialApplyForm").serialize(), function(b) {
						if (b.success) {
							//启用保存按钮
							$("#submitSave").linkbutton("enable");
							//window.parent.buttonSpecialdisEnable();
							window.opener.messagerShow("消息:"+b.msg);
							//$.messager.alert("消息",b.msg, "message");
							//关闭特殊申请填写页面
							window.close();
							//parent.closeMyDialog("specialApplyDialog", "specialApplyDialog");
						} else {
							//禁用提交按钮
							$("#submitSave").linkbutton("enable");
							//window.parent.buttonSpecialdisDisable();
							$.messager.alert("消息",b.msg, "message");
							//parent.closeMyDialog("specialApplyDialog", "specialApplyDialog");
						}
					});
					// }
				
		} else {
			$.messager.alert("消息", "请填写完整信息！", "warning");
			$("#submitSave").linkbutton("enable");
		}
}
// 审批特殊申请
function audit(auditState) {
	//禁用保存按钮
	$("#tongGuo").linkbutton("disable");
	//window.parent.buttonSpecialdisDisable();
	ajaxSubmit(serverName + "/specialApplyController/auditSpecialApply.do", {
		specialApplyId : $("#specialApplyId").val(),
		specialApproveComment : $("#specialApproveComment").val(),
		auditSpecialApplyState : auditState

	}, function(r) {
		//启用保存按钮
		$("#tongGuo").linkbutton("enable");
		if (r.success) {
			window.opener.searchAdvanced();
			//关闭审批页面
			window.close();
			$.messager.show({
				title : "消息",
				msg : r.msg
			});
			//关闭审批页面
			//parent.messageSuccess(r.msg);
			//parent.closeMyDialog("specialApplyDialog", "specialApplyDialog");
		} else {
			$.messager.show({
				title : "消息",
				msg : r.msg
			});		
			//parent.messageFail(r.msg);
			//parent.closeMyDialog("specialApplyDialog", "specialApplyDialog");
		}
	});
	
}
// 禁用确定按钮
function buttonSpecialdisDisable() {
	$("#rdButton1").linkbutton("disable");
	$("#rdButton2").linkbutton("disable");
}

function buttonSpecialdisEnable() {
	$("#rdButton1").linkbutton("enable");
	$("#rdButton2").linkbutton("enable");
}
