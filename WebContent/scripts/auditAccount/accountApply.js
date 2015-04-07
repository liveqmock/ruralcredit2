$(function() {
	var dicReport = new DataDictionary();
	dicReport.addDic("ynearyRepayment_Y", "ynearyRepayment");
	dicReport.dicAjax();
	$("#auditAccountApplyForm").form("validate");
	$("#saveMoneyDateForm").datetimebox(
			{
				required:true,
				editable:false,
				onChange : function(newValue, oldValue) {
					var loanTime = $("#saveMoneyDateForm").datetimebox("getValue");
				}
			});
});
// 保存
function saveAuditAccountApply() {
	// 禁用保存按钮 getImgAmountByBalanceAccountApply
	window.parent.buttonAccountdisDisable();
	var cid=$("#clientid").val();
	// 验证
	if ($("#auditAccountApplyForm").form('validate')) {
		ajaxSubmit(serverName + "/balanceAccountApplyController/getImgAmountByBalanceAccountApply.do", 
				{creditAppId : $("#clientid").val(),balanceDate:$("#balanceDate").val()}, function(r) {
					if (r == 0){
						$.messager.alert("消息", "请先上传合同资料");
						window.parent.buttonAccountdisEnable();
					}else if (r < 0){
						$.messager.alert("消息", "上传服务异常,请稍后重试");
						window.parent.buttonAccountdisEnable();
					}else{
						ajaxSubmit(serverName + "/balanceAccountApplyController/saveBalanceAccountApply.do", $("#auditAccountApplyForm").serialize(), function(r) {
							if (r.success) {
								//提示信息
								parent.messageSuccess("保存成功！");
								//刷新申请过的数据的列表
								parent.searchAdvanced2();
								parent.searchAdvanced3();
								window.parent.buttonAccountdisEnable();
								window.parent.closeMyDialogNewApplyDialog();
							} else {
								//提示信息
								parent.messageSuccess("保存失败！");
								window.parent.buttonAccountdisEnable();
								parent.closeMyDialogNewApplyDialog();
							}

						});
					}
			
		});
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		//启用保存按钮
		window.parent.buttonAccountdisEnable();
	}
}
//审批对账申请数据
function auditAccout(auditState){
	//判断是通过还是拒绝 1通过，2拒绝
	if (auditState == 2) {
		$("#borrRemark").validatebox({
			required : true
		});
	}else{
		$("#borrRemark").validatebox({
			required : false
		});
	}
	//禁用审批按钮
	window.parent.buttonAccountdisDisable();
	//验证信息是否填写完整
	if ($("#auditAccountApplyForm").form('validate')) {
	ajaxSubmit(serverName + "/balanceAccountApplyController/auditAccountApply.do", {
		balanceAccountApplyId : $("#balanceAccountApplyId").val(),
		borrRemark : $("#borrRemark").val(),
		auditResult : auditState
	}, function(r) {
		if (r.success) {
			//启用保存按钮
			window.parent.buttonAccountdisEnable();
			//刷新申请过的数据的列表
			parent.searchAdvanced2();
			//关闭审批页面
			parent.closeMyDialogAccountApplyAuditDialog();
			//提示信息
			parent.messageSuccess("保存成功！");
		} else {
			//启用保存按钮
			window.parent.buttonAccountdisEnable();
			//提示信息
			parent.messageSuccess("保存失败！");	
			parent.closeMyDialogAccountApplyAuditDialog();
		}
	});
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		window.parent.buttonAccountdisEnable();
	}
}
