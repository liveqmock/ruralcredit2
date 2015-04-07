
function computLoanAmount() {
	var amount = 0;
	for ( var i = 0; i < 6; i++) {
		var temp = $("#creditHistoryList" + i + "LoanAmount").val();
		amount = accAdd(getFloat(amount), getFloat(temp));
	}
	$("#total").numberbox('setValue', amount);
}

function saveRiskMgr(param) {
	if ($("#creditInvForm").form('validate')) {
		//判断一共有几个table luohongjie
		if(contactsSurveySize==1){
				parent.$.messager.alert("消息","请在联系人调查中至少添加两条记录！");
		return;	
		}
		
// $("#saveButton").linkbutton('disable');
// $("#saveAndSubmitButton").linkbutton('disable');
		parent.buttonDisable("saveButton");
		parent.buttonDisable("saveAndSubmitButton");
		if ("0" == param) {
			ajaxSubmit(serverName + "/creditInvestigation/saveCreditInvestigation.do", $("#creditInvForm").serialize(), function(r) {
				if (r.success) {
					parent.closeMyDialog("riskMgrDialog", "openRiskMgr");
					parent.messageSuccess("保存成功！");
				} else {
					parent.messageFail("保存失败！");
				}
				parent.buttonEnable("saveButton");
				parent.buttonEnable("saveAndSubmitButton");
			});
		} else if ("1" == param) {
			ajaxSubmit(serverName + "/creditInvestigation/submit.do", $("#creditInvForm").serialize(), function(r) {
				if (r.success) {
					parent.closeMyDialog("riskMgrDialog", "openRiskMgr");
					parent.messageSuccess("提交成功！");
				} else {
					parent.messageFail("提交失败！");
				}
				parent.buttonEnable("saveButton");
				parent.buttonEnable("saveAndSubmitButton");
			});
		} else {
			alert("saveRiskMgr的param有问题====" + param);
		}
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
	}
}
function showRiskMgrDialog(creditapplicationId, groupNumber, creditInvestigatioId, show) {
	ajaxSubmit(
			serverName + "/creditInvestigation/getCreditInvestigation.do",
			{
				creditapplicationId : creditapplicationId,
				creditInvestigatioId : creditInvestigatioId,
				groupNumber : groupNumber
			},
			function(r) {
				// console.info(r);
				if ($("#creditInvForm").form('validate'))
					;
				if (r.isSubmit == "1") {
					$("#saveButton").linkbutton({
						disabled : true,
						plain : true,
						text : ""
					});
				}
				if (creditInvestigatioId == null || creditInvestigatioId == "") {
					// 新增
				} else {
					// 回显
					if (r.linkmanInvestigationList[0].discontentment == "0") {
						$("#areaOne0")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='linkmanInvestigationList0DiscontentmentDetail'name='linkmanInvestigationList[0].discontentmentDetail' rows='3' style='width:500px;'onclick='textCount(\"linkmanInvestigationList0DiscontentmentDetail\", \"areaOne0S\", 100);' maxlength='100'></textarea>");
						$("#linkmanInvestigationList0DiscontentmentDetail").validatebox({
							required : true,
							validType : 'length[0,100]'
						});
					}
					if (r.linkmanInvestigationList[1].discontentment == "0") {
						$("#areaOne1")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='linkmanInvestigationList1DiscontentmentDetail'name='linkmanInvestigationList[1].discontentmentDetail' rows='3' style='width:500px;'onclick='textCount(\"linkmanInvestigationList1DiscontentmentDetail\", \"areaOne1S\", 100);' maxlength='100'></textarea>");
						$("#linkmanInvestigationList1DiscontentmentDetail").validatebox({
							required : true,
							validType : 'length[0,100]'
						});
					}
					if (r.linkmanInvestigationList[0].gambling == "0") {
						$("#areaTwo0")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='linkmanInvestigationList0GamblingDetail'name='linkmanInvestigationList[0].gamblingDetail' rows='2' style='width:500px;'onclick='textCount(\"linkmanInvestigationList0GamblingDetail\", \"areaTwo0S\", 100);' maxlength='100'></textarea>");
						$("#linkmanInvestigationList0GamblingDetail").validatebox({
							required : true,
							validType : 'length[0,100]'
						});
					}
					if (r.linkmanInvestigationList[1].gambling == "0") {
						$("#areaTwo0")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='linkmanInvestigationList1GamblingDetail'name='linkmanInvestigationList[1].gamblingDetail' rows='2' style='width:500px;'onclick='textCount(\"linkmanInvestigationList1GamblingDetail\", \"areaTwo1S\", 100);' maxlength='100'></textarea>");
						$("#linkmanInvestigationList1GamblingDetail").validatebox({
							required : true,
							validType : 'length[0,100]'
						});
					}
					if (r.appelleeRecord.isAppelleeRecord == "0") {
// $("#appelleeRecordAccusalOne").attr('readonly', false);
// $("#appelleeRecordAccusalStatusOne").combobox('enable');
// $("#appelleeRecordAccusalTwo").attr('readonly', false);
// $("#appelleeRecordAccusalStatusTwo").combobox('enable');
						$("#appelleeRecordOne0")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='appelleeRecordAccusalOne' name='appelleeRecord.accusalOne' rows='3' style='width:500px;'onclick='textCount(\"appelleeRecordAccusalOne\", \"appelleeRecordOne1\", 100);' maxlength='100'></textarea>");
						$("#appelleeRecordOne2").html("<input id='appelleeRecordAccusalStatusOne' name='appelleeRecord.accusalStatusOne' type='text' style='width: 125px;' />");
						$("#appelleeRecordAccusalOne").validatebox({
							required : false,
							validType : 'length[0,100]'
						});
						$("#appelleeRecordAccusalStatusOne").combobox({
							valueField : "id",
							textField : "text",
							data : [ {
								id : "",
								text : "请选择"
							}, {
								id : "0",
								text : "在进行"
							}, {
								id : "1",
								text : "已判决"
							}, {
								id : "2",
								text : "撤销"
							} ],
							editable : false
						});

						$("#appelleeRecordTwo0")
								.html(
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='appelleeRecordAccusalTwo' name='appelleeRecord.accusalTwo' rows='3' style='width:500px;'onclick='textCount(\"appelleeRecordAccusalTwo\", \"appelleeRecordTwo1\", 100);' maxlength='100'></textarea>");
						$("#appelleeRecordTwo2").html("<input id='appelleeRecordAccusalStatusTwo' name='appelleeRecord.accusalStatusTwo' type='text' style='width: 125px;' />");
						$("#appelleeRecordAccusalTwo").validatebox({
							required : false,
							validType : 'length[0,100]'
						});
						$("#appelleeRecordAccusalStatusTwo").combobox({
							valueField : "id",
							textField : "text",
							data : [ {
								id : "",
								text : "请选择"
							}, {
								id : "0",
								text : "在进行"
							}, {
								id : "1",
								text : "已判决"
							}, {
								id : "2",
								text : "撤销"
							} ],
							editable : false
						});
					}
				}
				$("#creditInvForm").form('load', r);
				$("#creditInvestigationName").focus();
				if (show) {
					// 禁用一切组件
					readOnlyTrueC(r);
				}
			});
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
		} else {
			$("#" + htmlId).css({
				"color" : "#000"
			});
		}
		$("#" + htmlId).html(tishi);
	});
}
function readOnlyTrueC(r) {
	$("#creditInvForm input").attr("readonly", true);
	$("#creditInvForm textarea").attr("readonly", true);
	$("#creditHistoryList0LoanDate").datebox('disable');
	$("#creditHistoryList1LoanDate").datebox('disable');
	$("#creditHistoryList2LoanDate").datebox('disable');
	$("#creditHistoryList3LoanDate").datebox('disable');
	$("#creditHistoryList4LoanDate").datebox('disable');
	$("#creditHistoryList5LoanDate").datebox('disable');

	$("#creditHistoryList0TermDate").datebox('disable');
	$("#creditHistoryList1TermDate").datebox('disable');
	$("#creditHistoryList2TermDate").datebox('disable');
	$("#creditHistoryList3TermDate").datebox('disable');
	$("#creditHistoryList4TermDate").datebox('disable');
	$("#creditHistoryList5TermDate").datebox('disable');

	$("#creditHistoryList0LoanOrganization").combobox('disable');
	$("#creditHistoryList1LoanOrganization").combobox('disable');
	$("#creditHistoryList2LoanOrganization").combobox('disable');
	$("#creditHistoryList3LoanOrganization").combobox('disable');
	$("#creditHistoryList4LoanOrganization").combobox('disable');
	$("#creditHistoryList5LoanOrganization").combobox('disable');

	$("#creditHistoryList0AccountStatus").combobox('disable');
	$("#creditHistoryList1AccountStatus").combobox('disable');
	$("#creditHistoryList2AccountStatus").combobox('disable');
	$("#creditHistoryList3AccountStatus").combobox('disable');
	$("#creditHistoryList4AccountStatus").combobox('disable');
	$("#creditHistoryList5AccountStatus").combobox('disable');

	$("#creditHistoryList0RepaymentType").combobox('disable');
	$("#creditHistoryList1RepaymentType").combobox('disable');
	$("#creditHistoryList2RepaymentType").combobox('disable');
	$("#creditHistoryList3RepaymentType").combobox('disable');
	$("#creditHistoryList4RepaymentType").combobox('disable');
	$("#creditHistoryList5RepaymentType").combobox('disable');

	$("#linkmanInvestigationList0Relation").combobox('disable');
	$("#linkmanInvestigationList1Relation").combobox('disable');

	$("#linkmanInvestigationList0HowLong").combobox('disable');
	$("#linkmanInvestigationList1HowLong").combobox('disable');

	$("#linkmanInvestigationList0Discontentment").combobox('disable');
	$("#linkmanInvestigationList1Discontentment").combobox('disable');
	$("#linkmanInvestigationList0Gambling").combobox('disable');
	$("#linkmanInvestigationList1Gambling").combobox('disable');

	$("#appelleeRecordIsAppelleeRecord").combobox('disable');

	$("#result").combobox('disable');
	$("#investigationDate").datebox('disable');

	if (r.appelleeRecord.isAppelleeRecord == "0") {
		$("#appelleeRecordAccusalStatusOne").combobox('disable');
		$("#appelleeRecordAccusalStatusTwo").combobox('disable');
	}
}