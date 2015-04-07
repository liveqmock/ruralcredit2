/**
 * ★━━━━━━━━━━━━━━━━━━━━页面初始化部分━━━━━━━━━━━━━━━━━━━━★
 */
$(function() {
});
/**
 * ★━━━━━━━━━━━━━━━━━━━━页面初始化部分END━━━━━━━━━━━━━━━━━━━━★
 */

/**
 * ★━━━━━━━━━━━━━━━━━━━━联系人调查表方法部分━━━━━━━━━━━━━━━━━━━━★
 */
// 回显联系人调查表
function doSearchContactSurvey(passBorrowerServiceAppId) {
	var dicContact = new DataDictionary();
	dicContact.addDic("borrowerrepaymentAbility", "borrowerrepaymentAbility");
	dicContact.addDic("repaymentWill", "repaymentWill");
	dicContact.addDic("badrecordsAndHobbies", "badrecordsAndHobbies");
	dicContact.dicAjax();
	$("#contactSurveyTabs").tabs({
		tools : [ {
			iconCls : 'icon-reload',
			text : "重置",
			handler : function() {
				doClearContactSurvey();
			}
		} ]
	});
	ajaxSubmit(serverName + "/contactSurvey/searchContactSurvey.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			// $("#contactSurvey_form").form('clear');
			$("#contactSurvey_form").form('load', result);
		}
	});
}
// 保存联系人调查表
function doSaveContactSurvey() {
	ajaxSubmit(serverName + "/contactSurvey/saveContactSurvey.do", $("#contactSurvey_form").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#contactSurvey_form").form('clear');
			$("#contactSurvey_form").form('load', result);
			$("#dd3").dialog('close');
		}
	});
}
// 清空联系人调查表
function doClearContactSurvey() {
	$("#borrowerrepaymentAbility").combobox('clear');
	$("#repaymentWill").combobox('clear');
	$("#badrecordsAndHobbies").combobox('clear');
	$("#otherSupplementary").val("");

}
// 保存后关闭Dialog
function doSaveAndCloseContactSurveyDialog() {
	if ($("#contactSurvey_form").form('validate')) {
		doSaveContactSurvey();
	} else {
		$.messager.alert('提示', '请输入必填项！', 'warning');
	}
}
/**
 * ★━━━━━━━━━━━━━━━━━━━━联系人调查表方法部分END━━━━━━━━━━━━━━━━━━━━★
 */
/*
 * // 保存后返回 function doSaveAndReturn() { if ($("#contactSurvey_form").form('validate')) { doSaveContactSurvey(); } else { $.messager.alert('提示',
 * '请输入必填项！', 'warning'); } }
 */
/*
 * // 保存后跳转 function doSaveAndNext() { if ($("#contactSurvey_form").form('validate')) { doSaveContactSurvey(); } else { $.messager.alert('提示',
 * '请输入必填项！', 'warning'); } }
 */

/*
 * // 取消 function doCancel() { window.location = serverName + "/creditgroup/selectCreditApplication.do?creditapplicationId=" +
 * $("#creditApplicationId").val(); }
 */

/*
 * // 查询小组信息 getNameAndCode function getNameAndCode(passBorrowerServiceAppId) { ajaxSubmit(serverName + "/surveySecond/getNameAndCode.do", {
 * borrowerServiceAppId : $("#borrowerServiceAppId").val() }, function(result) { if (result.success == false) { $.messager.alert('提示', result.msg,
 * 'error'); } else { $("#borrowerServiceAppName").val(result.name); } }); }
 */