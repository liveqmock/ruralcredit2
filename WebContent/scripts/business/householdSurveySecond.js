$(function() {
	// var passBorrowerServiceAppId = $("#borrowerServiceAppId").val();
// var dic = new DataDictionary();
// dic.addDic("familymembersGender0", "gender");
// dic.addDic("familymembersGender1", "gender");
// dic.addDic("familymembersGender2", "gender");
// dic.addDic("familymembersGender3", "gender");
// dic.addDic("familymembersGender4", "gender");
// dic.addDic("familymembersBorrowerreRationship0", "borrowerreRationship");
// dic.addDic("familymembersBorrowerreRationship1", "borrowerreRationship");
// dic.addDic("familymembersBorrowerreRationship2", "borrowerreRationship");
// dic.addDic("familymembersBorrowerreRationship3", "borrowerreRationship");
// dic.addDic("familymembersBorrowerreRationship4", "borrowerreRationship");
// dic.addDic("familymembersEducation0", "education");
// dic.addDic("familymembersEducation1", "education");
// dic.addDic("familymembersEducation2", "education");
// dic.addDic("familymembersEducation3", "education");
// dic.addDic("familymembersEducation4", "education");
// dic.dicAjax();

// getNameAndCode(passBorrowerServiceAppId);
// doSearchSurveySecondVo(passBorrowerServiceAppId);

	/*
	 * $("#p1").mouseenter(function() { //$("#p1").css("background-color", "#F5F5F5"); }); $("#p1").mouseleave(function() {
	 * //$("#p1").css("background-color", "#E9E9E4");
	 * 
	 * //alert("doSaveFamilymembers();"); //doSaveFamilymembers();
	 * 
	 * }); $("#p2").mouseenter(function() { //$("#p2").css("background-color", "#F5F5F5"); }); $("#p2").mouseleave(function() {
	 * //$("#p2").css("background-color", "#E9E9E4");
	 * 
	 * //doSaveFamilyotherincomes();
	 * 
	 * }); $("#p3").mouseenter(function() { //$("#p3").css("background-color", "#F5F5F5"); }); $("#p3").mouseleave(function() {
	 * //$("#p3").css("background-color", "#E9E9E4");
	 * 
	 * //doSavehSurveybusinessinfo();
	 * 
	 * }); $("#p4").mouseenter(function() { //$("#p4").css("background-color", "#F5F5F5"); }); $("#p4").mouseleave(function() {
	 * //$("#p4").css("background-color", "#E9E9E4");
	 * 
	 * //doSaveFamilytotalcosts();
	 * 
	 * }); $("#p5").mouseenter(function() { //$("#p5").css("background-color", "#F5F5F5"); }); $("#p5").mouseleave(function() {
	 * //$("#p5").css("background-color", "#E9E9E4");
	 * 
	 * //doSaveFamilydepositdebts();
	 * 
	 * }); $("#p6").mouseenter(function() { //$("#p6").css("background-color", "#F5F5F5"); }); $("#p6").mouseleave(function() {
	 * //$("#p6").css("background-color", "#E9E9E4");
	 * 
	 * //doSaveHouseholdassertss(); }); $("#p7").mouseenter(function() { //$("#p7").css("background-color", "#F5F5F5"); });
	 * $("#p7").mouseleave(function() { //$("#p7").css("background-color", "#E9E9E4");
	 * 
	 * //doSaveBorrowersurvey(); });
	 */

});
// 控制经营情况单选
function showBusinessPlaceType(value) {
	// if (value == 1) {
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('clear');
	// $("#surveybusinessinfoBusinessPlaceOther").attr("value", "");
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('disable');
	// $("#surveybusinessinfoBusinessPlaceOther").attr("disabled", true);
	// } else if (value == 2) {
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('enable');
	// $("#surveybusinessinfoBusinessPlaceOther").attr("value", "");
	// $("#surveybusinessinfoBusinessPlaceOther").attr("disabled", true);
	// } else if (value == 3) {
	// $("#surveybusinessinfoBusinessPlaceOther").attr("disabled", false);
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('clear');
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('disable');
	// } else {
	// //$.messager.alert('提示', 'showBusinessPlaceType()报错!', 'error');
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('clear');
	// $("#surveybusinessinfoBusinessPlaceOther").attr("value", "");
	// $("#surveybusinessinfoBusinessPlaceDate").datebox('disable');
	// $("#surveybusinessinfoBusinessPlaceOther").attr("disabled", true);
	// }
	if (value == 1) {
		$("#lease").html("");
		$("#other").html("");
	} else if (value == 2) {
		$("#lease").html("<input id='surveybusinessinfoBusinessPlaceDate' name='surveybusinessinfo.businessPlaceDate' type='text' ><font color='red'>请输入日期</font>");
		$("#other").html("");
		$("#surveybusinessinfoBusinessPlaceDate").datebox({
			required : true,
			editable : false
		});
	} else if (value == 3) {
		$("#lease").html("");
		$("#other").html("<input id='surveybusinessinfoBusinessPlaceOther' name='surveybusinessinfo.businessPlaceOther' type='text'/>");
	} else {
		$("#lease").html("");
		$("#other").html("");
	}

}

// 保存对储蓄/债权债务的操作
function doSaveFamilydepositdebts() {
	doComputeBankLiabilitites();
	doComputeLiabilities();
	doComputeClaims();
	ajaxSubmit(serverName + "/surveySecond/saveFamilydepositdebts.do", $("#householdsurveysecond_form5").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form5").form('clear');
			$("#householdsurveysecond_form5").form('load', result);
		}
	});
}
// 页面初始化的时候回显储蓄/债权债务数据
function doSearchFamilydepositdebts(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchFamilydepositdebts.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form5").form('clear');
			$("#householdsurveysecond_form5").form('load', result);
		}
		doComputeBankLiabilitites();
		doComputeLiabilities();
		doComputeClaims();
	});
}
// 保存对家庭资产的操作
function doSaveHouseholdassertss() {
	ajaxSubmit(serverName + "/surveySecond/saveHouseholdassertss.do", $("#householdsurveysecond_form6").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form6").form('clear');
			$("#householdsurveysecond_form6").form('load', result);
		}
	});
}
// 页面初始化的时候回显家庭资产数据
function doSearchHouseholdassertss(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchHouseholdassertss.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form6").form('clear');
			$("#householdsurveysecond_form6").form('load', result);
		}
	});
}
// 保存对日常总开支的操作
function doSaveFamilytotalcosts() {
	doComputeFamilyExpenditure();
	ajaxSubmit(serverName + "/surveySecond/saveFamilytotalcosts.do", $("#householdsurveysecond_form4").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form4").form('clear');
			$("#householdsurveysecond_form4").form('load', result);
		}
	});
}
// 页面初始化的时候回显日常总开支数据
function doSearchFamilytotalcosts(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchFamilytotalcosts.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form4").form('clear');
			$("#householdsurveysecond_form4").form('load', result);
		}
		doComputeFamilyExpenditure();
	});
}

// 保存对其他收入的操作
function doSaveFamilyotherincomes() {
	doComputeOtherIncome();
	ajaxSubmit(serverName + "/surveySecond/saveFamilyotherincomes.do", $("#householdsurveysecond_form2").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form2").form('clear');
			$("#householdsurveysecond_form2").form('load', result);
		}
	});
}
// 页面初始化的时候回显其他收入数据
function doSearchFamilyotherincomes(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchFamilyotherincomes.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form2").form('clear');
			$("#householdsurveysecond_form2").form('load', result);
		}
		doComputeOtherIncome();
	});
}
// 保存对上述合计的操作
function doSaveBorrowersurvey() {
	doComputeTotalMoney();
	ajaxSubmit(serverName + "/surveySecond/saveBorrowersurveyVo.do", $("#householdsurveysecond_form7").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form7").form('clear');
			$("#householdsurveysecond_form7").form('load', result);
		}
	});

}

// 页面初始化的时候回显上述合计数据
function doSearchBorrowersurvey(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchBorrowersurveyVo.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form7").form('clear');
			$("#householdsurveysecond_form7").form('load', result);
		}
		doComputeTotalMoney();
	});

}
// 保存对经营情况的操作
function doSavehSurveybusinessinfo() {
	ajaxSubmit(serverName + "/surveySecond/saveSurveybusinessinfoVo.do", $("#householdsurveysecond_form3").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form3").form('clear');
			showBusinessPlaceType(result.surveybusinessinfo.businessPlaceType);
			$("#householdsurveysecond_form3").form('load', result);
		}
	});

}

// 页面初始化的时候回显经营情况数据
function doSearchSurveybusinessinfo(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchSurveybusinessinfoVo.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form3").form('clear');
			showBusinessPlaceType(result.surveybusinessinfo.businessPlaceType);
			$("#householdsurveysecond_form3").form('load', result);

		}
	});

}
// 保存对家庭成员及收入的操作
function doSaveFamilymembers() {
	doComputeFamilyWageincome();
	ajaxSubmit(serverName + "/surveySecond/saveFamilymembers.do", $("#householdsurveysecond_form1").serialize(), function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form1").form('clear');
			$("#householdsurveysecond_form1").form('load', result);
		}
	});
}
// 页面初始化的时候回显家庭成员及收入数据
function doSearchFamilymembers(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/searchFamilymembers.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form1").form('clear');
			$("#householdsurveysecond_form1").form('load', result);
		}
		doComputeFamilyWageincome();
	});
}

// 计算年工资收入合计
function doComputeFamilyWageincome() {
	var yearIncome0 = $("#familymembersYearIncome0").numberbox('getValue');
	var yearIncome1 = $("#familymembersYearIncome1").numberbox('getValue');
	var yearIncome2 = $("#familymembersYearIncome2").numberbox('getValue');
	var yearIncome3 = $("#familymembersYearIncome3").numberbox('getValue');
	var yearIncome4 = $("#familymembersYearIncome4").numberbox('getValue');
	var familyWageincome = getFloat(yearIncome0) + getFloat(yearIncome1) + getFloat(yearIncome2) + getFloat(yearIncome3) + getFloat(yearIncome4);
	$("#familyWageincome").numberbox('setValue', familyWageincome);
	return familyWageincome;
}
// 计算开支合计第10个input
function doComputeCostTotal() {
	var amount0 = $("#familytotalcostsAmount0").numberbox('getValue');
	var amount1 = $("#familytotalcostsAmount1").numberbox('getValue');
	var amount2 = $("#familytotalcostsAmount2").numberbox('getValue');
	var amount3 = $("#familytotalcostsAmount3").numberbox('getValue');
	var amount4 = $("#familytotalcostsAmount4").numberbox('getValue');
	var amount5 = $("#familytotalcostsAmount5").numberbox('getValue');
	var amount6 = $("#familytotalcostsAmount6").numberbox('getValue');
	var amount7 = $("#familytotalcostsAmount7").numberbox('getValue');
	var amount8 = $("#familytotalcostsAmount8").numberbox('getValue');
	var costTotal = getFloat(amount0) + getFloat(amount1) + getFloat(amount2) + getFloat(amount3) + getFloat(amount4) + getFloat(amount5) + getFloat(amount6) + getFloat(amount7) + getFloat(amount8);
	$("#computeCostTotal").numberbox('setValue', costTotal);
	return costTotal;
}
// 计算日常总开支
function doComputeFamilyExpenditure() {
	var familyExpenditure = doComputeCostTotal();
	$("#familyExpenditure").numberbox('setValue', familyExpenditure);
	return familyExpenditure;
}
// 计算银行债务
function doComputeBankLiabilitites() {
	var amount2 = $("#familydepositdebtsAmount2").numberbox('getValue');
	var amount6 = $("#familydepositdebtsAmount6").numberbox('getValue');

	var bankLiabilitites = getFloat(amount2) + getFloat(amount6);
	$("#bankLiabilitites").numberbox('setValue', bankLiabilitites);

	return bankLiabilitites;

}

// 计算债务
function doComputeLiabilities() {
	var amount1 = $("#familydepositdebtsAmount1").numberbox('getValue');
	var amount5 = $("#familydepositdebtsAmount5").numberbox('getValue');

	var liabilities = getFloat(amount1) + getFloat(amount5);
	$("#liabilities").numberbox('setValue', liabilities);

	return liabilities;

}

// 计算债权
function doComputeClaims() {
	var amount0 = $("#familydepositdebtsAmount0").numberbox('getValue');
	var amount4 = $("#familydepositdebtsAmount4").numberbox('getValue');

	var claims = getFloat(amount0) + getFloat(amount4);
	$("#claims").numberbox('setValue', claims);

	return claims;

}

// 计算第一个家庭其他收入的金额
//function doComputeAmount0(avoiCcount0) {
//	alert(avoiCcount0);
//	avoiCcount0++;
//	var qulitily0 = $("#familyotherincomesQulitily0").val();
//	var price0 = $("#familyotherincomesPrice0").numberbox('getValue');
//	if (isNaN(parseFloat(qulitily0))) {
//		$("#familyotherincomesAmount0").numberbox('setValue', "");
//	} else {
//		var amount0 = getFloat(qulitily0) * getFloat(price0);
//		$("#familyotherincomesAmount0").numberbox('setValue', amount0);
//	}
//
//}

//// 计算第二个家庭其他收入的金额
//function doComputeAmount1(avoiCcount1) {
//	var qulitily1 = $("#familyotherincomesQulitily1").val();
//	var price1 = $("#familyotherincomesPrice1").numberbox('getValue');
//	if (isNaN(parseFloat(qulitily1))) {
//		$("#familyotherincomesAmount1").numberbox('setValue', "");
//	} else {
//		var amount1 = getFloat(qulitily1) * getFloat(price1);
//		$("#familyotherincomesAmount1").numberbox('setValue', amount1);
//	}
//
//}
//
//// 计算第三个家庭其他收入的金额
//function doComputeAmount2(avoiCcount2) {
//	var qulitily2 = $("#familyotherincomesQulitily2").val();
//	var price2 = $("#familyotherincomesPrice2").numberbox('getValue');
//	if (isNaN(parseFloat(qulitily2))) {
//		$("#familyotherincomesAmount2").numberbox('setValue', "");
//	} else {
//		var amount2 = getFloat(qulitily2) * getFloat(price2);
//		$("#familyotherincomesAmount2").numberbox('setValue', amount2);
//	}
//
//}
//
//// 计算第四个家庭其他收入的金额
//function doComputeAmount3(avoiCcount3) {
//	var qulitily3 = $("#familyotherincomesQulitily3").val();
//	var price3 = $("#familyotherincomesPrice3").numberbox('getValue');
//	if (isNaN(parseFloat(qulitily3))) {
//		$("#familyotherincomesAmount3").numberbox('setValue', "");
//	} else {
//		var amount3 = getFloat(qulitily3) * getFloat(price3);
//		$("#familyotherincomesAmount3").numberbox('setValue', amount3);
//	}
//
//}
// 计算其他收入
function doComputeOtherIncome() {

	var amount0 = $("#familyotherincomesAmount0").numberbox('getValue');
	var amount1 = $("#familyotherincomesAmount1").numberbox('getValue');
	var amount2 = $("#familyotherincomesAmount2").numberbox('getValue');
	var amount3 = $("#familyotherincomesAmount3").numberbox('getValue');

	var otherIncome = getFloat(amount0) + getFloat(amount1) + getFloat(amount2) + getFloat(amount3);

	$("#otherIncome").numberbox('setValue', otherIncome);

	return otherIncome;
}

// 计算上述合计
function doComputeTotalMoney() {
	var familyWageincome = doComputeFamilyWageincome();
	var otherIncome = doComputeOtherIncome();
	var bankSave = $("#bankSave").numberbox('getValue');
	var claims = doComputeClaims();
	var familyExpenditure = doComputeFamilyExpenditure();
	var bankLiabilitites = doComputeBankLiabilitites();
	var liabilities = doComputeLiabilities();
	var totalMoney = getFloat(familyWageincome) + getFloat(otherIncome) + getFloat(bankSave) + getFloat(claims) - getFloat(familyExpenditure) - getFloat(bankLiabilitites) - getFloat(liabilities);
	$("#totalMoney").numberbox('setValue', totalMoney);
}
// getFolat方法
function getFloat(param) {
	if (isNaN(parseFloat(param))) {
		param = 0;
	} else {
		param = parseFloat(param);
	}
	return param;
}
// 查询小组信息 getNameAndCode

function getNameAndCode(passBorrowerServiceAppId) {
	ajaxSubmit(serverName + "/surveySecond/getNameAndCode.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#appName").val(result.name);
			ajaxSubmit(serverName + "/creditgroup/select.do", {
				creditapplicationId : $("#creditApplicationId").val()
			}, function(result) {
				$("#groupNumber").val(result.groupNumber);
			});
		}
	});
}

// 回显入户2信息
function doSearchSurveySecondVo(passBorrowerServiceAppId) {
	var dichss = new DataDictionary();
	dichss.addDic("familymembersGender0", "gender");
	dichss.addDic("familymembersGender1", "gender");
	dichss.addDic("familymembersGender2", "gender");
	dichss.addDic("familymembersGender3", "gender");
	dichss.addDic("familymembersGender4", "gender");
	dichss.addDic("familymembersBorrowerreRationship0", "borrowerreRationship");
	dichss.addDic("familymembersBorrowerreRationship1", "borrowerreRationship");
	dichss.addDic("familymembersBorrowerreRationship2", "borrowerreRationship");
	dichss.addDic("familymembersBorrowerreRationship3", "borrowerreRationship");
	dichss.addDic("familymembersBorrowerreRationship4", "borrowerreRationship");
	dichss.addDic("familymembersEducation0", "education");
	dichss.addDic("familymembersEducation1", "education");
	dichss.addDic("familymembersEducation2", "education");
	dichss.addDic("familymembersEducation3", "education");
	dichss.addDic("familymembersEducation4", "education");
	dichss.dicAjax();
	$("#familymembersGender0").combobox({
		width : 100
	});
	$("#familymembersGender1").combobox({
		width : 100
	});

	$("#familymembersGender2").combobox({
		width : 100
	});
	$("#familymembersGender3").combobox({
		width : 100
	});
	$("#familymembersGender4").combobox({
		width : 100
	});
	ajaxSubmit(serverName + "/surveySecond/doSearchSurveySecondVo.do", {
		borrowerServiceAppId : passBorrowerServiceAppId
	}, function(result) {
		if (result.success == false) {
			$.messager.alert('提示', result.msg, 'error');
		} else {
			$("#householdsurveysecond_form7").form('clear');
			$("#householdsurveysecond_form7").form('load', result);
			$("#householdsurveysecond_form6").form('clear');
			$("#householdsurveysecond_form6").form('load', result);
			$("#householdsurveysecond_form5").form('clear');
			$("#householdsurveysecond_form5").form('load', result);
			$("#householdsurveysecond_form4").form('clear');
			$("#householdsurveysecond_form4").form('load', result);
			$("#householdsurveysecond_form3").form('clear');
			showBusinessPlaceType(result.surveybusinessinfo.businessPlaceType);
			$("#householdsurveysecond_form3").form('load', result);
			$("#householdsurveysecond_form2").form('clear');
			$("#householdsurveysecond_form2").form('load', result);
			$("#householdsurveysecond_form1").form('clear');
			$("#householdsurveysecond_form1").form('load', result);
			doComputeFamilyWageincome();
			doComputeOtherIncome();
			doComputeFamilyExpenditure();
			doComputeBankLiabilitites();
			doComputeLiabilities();
			doComputeClaims();
			doComputeTotalMoney();
		}
	});
}

// 清空家庭成员form
function doClearFamilymembers() {
	$("input[id^='familymembersName']").val("");
	$("input[id^='familymembersGender']").combobox('clear');
	$("input[id^='familymembersAge']").numberbox('clear');
	$("input[id^='familymembersBorrowerreRationship']").combobox('clear');
	$("input[id^='familymembersEducation']").combobox('clear');
	$("input[id^='familymembersUorkUnit']").val("");
	$("input[id^='familymembersYearIncome']").numberbox('clear');
	$("input[id^='familymembersTelphone']").numberbox('clear');
}

// 清空家庭其他收入form
function doClearFamilyotherincomes() {
	$("input[id^='familyotherincomesOtherIncomeType']").val("");
	$("input[id^='familyotherincomesQulitily']").val("");
	$("input[id^='familyotherincomesPrice']").numberbox('clear');
	$("input[id^='familyotherincomesAmount']").numberbox('clear');
}

// 清空经营情况form
function doClearSurveybusinessinfo() {
	$("#surveybusinessinfoOrganizationName").val("");
	$("#surveybusinessinfoStartBusinessTime").datebox('clear');
	$("#surveybusinessinfoEmployeeNumber").numberbox('clear');
	$("#surveybusinessinfoBusinessScope").val("");
	$("#surveybusinessinfoSiteArea").val("");
	$("#surveybusinessinfoTotalIncome").numberbox('clear');
	$("#surveybusinessinfoBusinessAddress").val("");
	$("#surveybusinessinfoBusinessPlaceDate").datebox('clear');
	$("#surveybusinessinfoBusinessPlaceOther").val("");
	$("#surveybusinessinfoBusinessPermitNo").val("");
	$("#surveybusinessinfoTaxRegisterNo").val("");
	$("input[id^='surveybusinessinfoBusinessPlaceType']").attr("checked", false);
	showBusinessPlaceType(4);
}

// 清空日常总开支
function doClearFamilytotalcosts() {
	$("input[id^='familytotalcostsAmount']").numberbox('clear');
	$("#computeCostTotal").numberbox('clear');
}

// 清空储蓄/债权债务
function doClearFamilydepositdebts() {
	$("input[id^='familydepositdebtsBank']").val("");
	$("input[id^='familydepositdebtsAmount']").numberbox('clear');
	$("input[id^='amountFamilydepositdebts']").attr("checked", false);
}

// 清空家庭资产
function doClearHouseholdassertss() {
	$("input[id^='householdassertssDescription']").val("");
	$("input[id^='householdassertssValue']").numberbox('clear');
}

// 清空上述合计
function doClearBorrowersurvey() {
	$("#familyWageincome").numberbox('clear');
	$("#otherIncome").numberbox('clear');
	$("#bankSave").numberbox('clear');
	$("#claims").numberbox('clear');
	$("#familyExpenditure").numberbox('clear');
	$("#bankLiabilitites").numberbox('clear');
	$("#liabilities").numberbox('clear');
	$("#totalMoney").numberbox('clear');
}
// 保存后返回
function doSaveAndReturn() {
	if ($("#householdsurveysecond_form1").form('validate') && $("#householdsurveysecond_form2").form('validate') && $("#householdsurveysecond_form3").form('validate')
			&& $("#householdsurveysecond_form4").form('validate') && $("#householdsurveysecond_form5").form('validate') && $("#householdsurveysecond_form6").form('validate')
			&& $("#householdsurveysecond_form7").form('validate')) {
		data1 = $('#householdsurveysecond_form1').serialize();
		data2 = $('#householdsurveysecond_form2').serialize();
		data3 = $('#householdsurveysecond_form3').serialize();
		data4 = $('#householdsurveysecond_form4').serialize();
		data5 = $('#householdsurveysecond_form5').serialize();
		data6 = $('#householdsurveysecond_form6').serialize();
		data7 = $('#householdsurveysecond_form7').serialize();
		data = data1 + "&" + data2 + "&" + data3 + "&" + data4 + "&" + data5 + "&" + data6 + "&" + data7;
		ajaxSubmit(serverName + "/surveySecond/saveSurveySecondVo.do", data, function(result) {
			if (result.success == false) {
				$.messager.alert('提示', result.msg, 'error');
			} else {
				window.location = serverName + "/creditgroup/selectCreditApplication.do?creditapplicationId=" + $("#creditApplicationId").val();
			}
		});
	} else {
		$.messager.alert('提示', '请输入必填项和合法数据！', 'warning');
	}
}
// 保存后跳转
function doSaveAndNext() {
	if ($("#householdsurveysecond_form1").form('validate') && $("#householdsurveysecond_form2").form('validate') && $("#householdsurveysecond_form3").form('validate')
			&& $("#householdsurveysecond_form4").form('validate') && $("#householdsurveysecond_form5").form('validate') && $("#householdsurveysecond_form6").form('validate')
			&& $("#householdsurveysecond_form7").form('validate')) {
		data1 = $('#householdsurveysecond_form1').serialize();
		data2 = $('#householdsurveysecond_form2').serialize();
		data3 = $('#householdsurveysecond_form3').serialize();
		data4 = $('#householdsurveysecond_form4').serialize();
		data5 = $('#householdsurveysecond_form5').serialize();
		data6 = $('#householdsurveysecond_form6').serialize();
		data7 = $('#householdsurveysecond_form7').serialize();
		data = data1 + "&" + data2 + "&" + data3 + "&" + data4 + "&" + data5 + "&" + data6 + "&" + data7;
		ajaxSubmit(serverName + "/surveySecond/saveSurveySecondVo.do", data, function(result) {
			if (result.success == false) {
				$.messager.alert('提示', result.msg, 'error');
			} else {
				window.location = serverName + "/surveySecond/returnContactSurveyJSP.do?borrowerServiceAppId=" + $("#borrowerServiceAppId").val() + "&creditApplicationId="
						+ $("#creditApplicationId").val();
			}
		});

	} else {
		$.messager.alert('提示', '请输入必填项和合法数据！', 'warning');
	}
}
// 取消
function doCancel() {
	window.location = serverName + "/creditgroup/selectCreditApplication.do?creditapplicationId=" + $("#creditApplicationId").val();
}

/**
 * ★━━━━━━━━━━━━━━━━━━━━这里都是Test方法━━━━━━━━━━━━━━━━━━━━★
 */

//// 离开tab时对其进行保存
//function doSaveTabs(tempTitle, countTabs) {
//	if (countTabs > 1) {
//		// alert(tempTitle + "和" + countTabs);
//		if (tempTitle == "家庭成员及工资收入") {
//			doSaveFamilymembers();
//		} else if (tempTitle == "其他收入") {
//			doSaveFamilyotherincomes();
//		} else if (tempTitle == "经营情况") {
//			doSavehSurveybusinessinfo();
//			avoiCcount0 = 0;
//		} else if (tempTitle == "日常总开支") {
//			doSaveFamilytotalcosts();
//		} else if (tempTitle == "储蓄/债权债务") {
//			doSaveFamilydepositdebts();
//		} else if (tempTitle == "家庭资产") {
//			doSaveHouseholdassertss();
//		} else if (tempTitle == "上述合计") {
//			doSaveBorrowersurvey();
//		} else {
//			alert("doSaveTabs的JS出错啦！");
//		}
//	}
//}

function doSaveHouseholdSurveySecondDialog() {

// $("#householdsurveysecond_form1").form('validate') && $("#householdsurveysecond_form2").form('validate') &&
// $("#householdsurveysecond_form3").form('validate')
// && $("#householdsurveysecond_form4").form('validate') && $("#householdsurveysecond_form5").form('validate') &&
// $("#householdsurveysecond_form6").form('validate')
// && $("#householdsurveysecond_form7").form('validate');
	if ($("#householdsurveysecond_form1").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "家庭成员及工资收入");
	} else if ($("#householdsurveysecond_form2").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "其他收入");
	} else if ($("#householdsurveysecond_form3").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "经营情况");
	} else if ($("#householdsurveysecond_form4").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "日常总开支");
	} else if ($("#householdsurveysecond_form5").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "储蓄/债权债务");
	} else if ($("#householdsurveysecond_form6").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "家庭资产");
	} else if ($("#householdsurveysecond_form7").form('validate') == false) {
		$("#householdSurveySecondTabs").tabs('select', "上述合计");
	} else {
		data1 = $('#householdsurveysecond_form1').serialize();
		data2 = $('#householdsurveysecond_form2').serialize();
		data3 = $('#householdsurveysecond_form3').serialize();
		data4 = $('#householdsurveysecond_form4').serialize();
		data5 = $('#householdsurveysecond_form5').serialize();
		data6 = $('#householdsurveysecond_form6').serialize();
		data7 = $('#householdsurveysecond_form7').serialize();
		data = data1 + "&" + data2 + "&" + data3 + "&" + data4 + "&" + data5 + "&" + data6 + "&" + data7;
		ajaxSubmit(serverName + "/surveySecond/saveSurveySecondVo.do", data, function(result) {
			if (result.success == false) {
				$.messager.alert('提示', result.msg, 'error');
			} else {
				$("#dd2").dialog('close');
			}
		});
	}
}
function doCancelHouseholdSurveysecond() {
	if ($("#householdsurveysecond_form1").form('validate') && $("#householdsurveysecond_form2").form('validate') && $("#householdsurveysecond_form3").form('validate')
			&& $("#householdsurveysecond_form4").form('validate') && $("#householdsurveysecond_form5").form('validate') && $("#householdsurveysecond_form6").form('validate')
			&& $("#householdsurveysecond_form7").form('validate')) {
		$("#dd2").dialog('close');
	} else {
		ajaxSubmit(serverName + "/surveySecond/saveSurveySecondVo.do", data, function(result) {
			if (result.success == false) {
				$.messager.alert('提示', result.msg, 'error');
			} else {
				$("#dd2").dialog('close');
			}
		});
	}
}
/**
 * ★━━━━━━━━━━━━━━━━━━━━这里都是Test方法━━━━━━━━━━━━━━━━━━━━★
 */
