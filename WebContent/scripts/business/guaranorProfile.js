var paramValidateG;

// 这里面写用于显示担保人信息的初始化赋值等等
isShowoOrUpdate = true;// false 表示回显不能更改
objectGuaranor = new Object();
function showGuaranorDialog(borrowerServiceAppId, param, show) {
	ajaxSubmit(serverName + "/guaranorProfile/show.do", {
		borrowerServiceAppId : borrowerServiceAppId,
		param : param
	}, function(r) {
		formLoad(r, param, show);
	});
}
/** 担保人提交保存 **/
function doSaveShowUpdate(param) {

	if ($("#guaranorProfileForm").form('validate') == true) {
		
		if (checkCheckBox() == true) {
// $("#guaranorButton1").linkbutton('disable');
			if ($("#guranorjobInfos0company").val() == "" && $("#guranorjobInfos1company").val() == "" && $("#guranorsurveybusinessinfos0operatingItemsDetail").val() == ""
					&& $("#guranorsurveybusinessinfos1operatingItemsDetail").val() == "" && $("#guranorsurveybusinessinfos2operatingItemsDetail").val() == "") {
				$.messager.alert("消息", "请在“工作情况”或“经营情况”中至少添加一条记录！”");
				return;
			}
			parent.disableButton("guaranorButton1");
			if ("0" == param) {
				// 做插入操作
				save();
			} else if ("1" == param) {
				// 做回显和修改操作
				update();
			}
		} else {
			$.messager.alert("消息", "现家庭住址至少选一项！", "warning");
		}
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
	}

}
/** 担保人提交暂存 **/
function tempDoSaveShowUpdate(param) {
	ajaxSubmit(serverName + "/guaranorProfile/tempSave.do", $("#guaranorProfileForm").serialize(), function(r) {
		if (r.success) {
			parent.closeGuarantor("guaranorDialog","操作成功,填完表单后请全部保存");
		} else {
			$.messager.alert("消息","系统错误,请重试或联系管理员");
		}
	});
}
function save() {
	ajaxSubmit(serverName + "/guaranorProfile/save.do", $("#guaranorProfileForm").serialize(), function(r) {
		if (r.success) {
			parent.enableButton("guaranorButton1");
			parent.closeGuarantor("guaranorDialog","保存成功");
		} else {
			parent.showMessage();
			parent.enableButton("guaranorButton1");
		}
	});
}
function update() {
	ajaxSubmit(serverName + "/guaranorProfile/update.do", $("#guaranorProfileForm").serialize(), function(r) {
		if (r.success) {
			parent.enableButton("guaranorButton1");
			parent.closeGuarantor("guaranorDialog","修改成功");
		} else {
			parent.showMessage();
			parent.enableButton("guaranorButton1");
		}
	});
}
function formLoad(r, param, show) {
	if ("1" == param) {
		if (show) {
			if ("1" == r.borrowerService.livingRent) {
				$("#livingDateG").html("<input id='borrowerServiceLivingDate'  type='text' name='borrowerService.livingDate' />");
				$("#borrowerServiceLivingDate").datebox({
					required : true,
					editable : false
				});
			}

			if ("1" == r.borrowerService.businessRent) {
				$("#businessDateG").html("<input id='borrowerServiceBusinessDate'  type='text' name='borrowerService.businessDate' />");
				$("#borrowerServiceBusinessDate").datebox({
					required : true,
					editable : false
				});
			}
			if ("0" == r.otherQuestions.guarantee) {
				$("#areaOne").show();
				$("#otherQuestionsGuaranteeDetails").validatebox({
					required : true,
					validType : 'length[0,100]'
				});
			}
			if ("0" == r.otherQuestions.gambling) {
				$("#areaTwo").show();
				$("#otherQuestionsGamblingSpouse").validatebox({
					required : true,
					validType : 'length[0,100]'
				});
			}
			if ("0" == r.otherQuestions.help) {
				$("#areaThree").show();
				$("#otherQuestionsHelpDetails").validatebox({
					required : true,
					validType : 'length[0,100]'
				});
			}
			if ("1" == r.otherQuestions.enoughDeposits) {
				$("#areaFour").show();
				$("#otherQuestionsHowEnough").validatebox({
					required : true,
					validType : 'length[0,100]'
				});
			}
			if ("1" == r.otherQuestions.spouseInformed) {
				$("#areaFive").show();
				$("#otherQuestionsSpouseExplanation").validatebox({
					required : true,
					validType : 'length[0,100]'
				});
			}
			$("#guaranorProfileForm").form("load", r);
// $("#borrowerServiceName").focus();
			readOnlyTrue();
		} else {

// $("#guaranorProfileForm").form("load", r);
			provinceShowG("provinceHomeG", "cityHomeG", "countyHomeG", "townHomeG", "villageHomeG", "homeAddressG", "detailHomeG");
			provinceShowG("provinceBusinessG", "cityBusinessG", "countyBusinessG", "townBusinessG", "villageBusinessG", "businessAddressG", "detailBusinessG");
// $("#borrowerServiceName").focus();
		}
	} else {
		$("#borrowerServiceMaritalStatus").combobox('setValue', "2");
		provinceShowG("provinceHomeG", "cityHomeG", "countyHomeG", "townHomeG", "villageHomeG", "homeAddressG", "detailHomeG");
		provinceShowG("provinceBusinessG", "cityBusinessG", "countyBusinessG", "townBusinessG", "villageBusinessG", "businessAddressG", "detailBusinessG");
	}
}
function readOnlyTrue() {
	$("#guaranorProfileForm input").attr("readonly", true);
	$("#guaranorProfileForm textarea").attr("readonly", true);
	$("#guaranorProfileForm").find("input[type=checkbox]").attr("disabled", "disabled");
	// $("#borrowerServiceBirthday").datebox('disable');
	// borrowerServiceNationality
	// $("#borrowerServiceGender").combobox('disable');
	$("#borrowerServiceMaritalStatus").combobox('disable');
	$("#borrowerServiceJobStatus").combobox('disable');
	$("#borrowerServiceBondsmanBorrower").combobox('disable');
	$("#borrowerServiceLivingDate").datebox('disable');
	$("#borrowerServiceBusinessDate").datebox('disable');
	$("#familymembers0BorrowerreRationship").combobox('disable');
	$("#familymembers1BorrowerreRationship").combobox('disable');
	$("#familymembers2BorrowerreRationship").combobox('disable');
	$("#familymembers3BorrowerreRationship").combobox('disable');
	$("#familymembers4BorrowerreRationship").combobox('disable');
	$("#familymembers0Profession").combobox('disable');
	$("#familymembers1Profession").combobox('disable');
	$("#familymembers2Profession").combobox('disable');
	$("#familymembers3Profession").combobox('disable');
	$("#familymembers4Profession").combobox('disable');

// $("#surveybusinessinfos0OperatingItems").combobox('disable');
// $("#surveybusinessinfos1OperatingItems").combobox('disable');
// $("#surveybusinessinfos2OperatingItems").combobox('disable');

	$("#surveybusinessinfos0State").combobox('disable');
	$("#surveybusinessinfos1State").combobox('disable');
	$("#surveybusinessinfos2State").combobox('disable');

	$("#otherQuestionsGuarantee").combobox('disable');
	$("#otherQuestionsHowLong").combobox('disable');
	$("#otherQuestionsGambling").combobox('disable');
	$("#otherQuestionsHelp").combobox('disable');
	$("#otherQuestionsEnoughDeposits").combobox('disable');
	$("#otherQuestionsSpouseInformed").combobox('disable');

// $("#provinceHomeG").combobox('disable');
// $("#cityHomeG").combobox('disable');
// $("#countyHomeG").combobox('disable');
// $("#townHomeG").combobox('disable');
// $("#villageHomeG").combobox('disable');
// $("#homeAddressG").combobox('disable');
// $("#detailHomeG").combobox('disable');
//
// $("#provinceBusinessG").combobox('disable');
// $("#cityBusinessG").combobox('disable');
// $("#countyBusinessG").combobox('disable');
// $("#townBusinessG").combobox('disable');
// $("#villageBusinessG").combobox('disable');
// $("#businessAddressG").combobox('disable');
// $("#detailBusinessG").combobox('disable');
//	
	$("#borrowerServiceNationality").combobox('disable');

}
function readOnlyFalse() {
	$("#guaranorProfileForm input").attr("readonly", false);
	$("#guaranorProfileForm").find("input[type=checkbox]").attr("disabled", false);
	$("#borrowerServiceBirthday").datebox('enable');
	// borrowerServiceNationality
	$("#borrowerServiceGender").combobox('enable');
	$("#borrowerServiceMaritalStatus").combobox('enable');
	$("#borrowerServiceJobStatus").combobox('enable');
	$("#borrowerServiceBondsmanBorrower").combobox('enable');
	$("#borrowerServiceLivingDate").datebox('enable');
	$("#borrowerServiceBusinessDate").datebox('enable');
	$("#familymembers0BorrowerreRationship").combobox('enable');
	$("#familymembers1BorrowerreRationship").combobox('enable');
	$("#familymembers2BorrowerreRationship").combobox('enable');
	$("#familymembers3BorrowerreRationship").combobox('enable');
	$("#familymembers4BorrowerreRationship").combobox('enable');
	$("#familymembers0Profession").combobox('enable');
	$("#familymembers1Profession").combobox('enable');
	$("#familymembers2Profession").combobox('enable');
	$("#familymembers3Profession").combobox('enable');
	$("#familymembers4Profession").combobox('enable');
// $("#surveybusinessinfos0OperatingItems").combobox('enable');
// $("#surveybusinessinfos1OperatingItems").combobox('enable');
// $("#surveybusinessinfos2OperatingItems").combobox('enable');

	$("#surveybusinessinfos0State").combobox('enable');
	$("#surveybusinessinfos1State").combobox('enable');
	$("#surveybusinessinfos2State").combobox('enable');

	$("#otherQuestionsGuarantee").combobox('enable');
	$("#otherQuestionsHowLong").combobox('enable');
	$("#otherQuestionsGambling").combobox('enable');
	$("#otherQuestionsHelp").combobox('enable');
	$("#otherQuestionsEnoughDeposits").combobox('enable');
	$("#otherQuestionsSpouseInformed").combobox('enable');
}
function showLivingDate(r) {
	// alert($("#" + r.id).checked);

	if ($("#" + r.id).attr("checked") == "checked") {
// $("#livingDateG").show();
// $("#borrowerServiceLivingDate").validatebox.required = true;
		$("#livingDateG").html("<input id='borrowerServiceLivingDate' type='text' name='borrowerService.livingDate' />");
		$("#borrowerServiceLivingDate").datebox({
			required : true,
			editable : false
		});

	} else {
// $("#borrowerServiceLivingDate").validatebox.required = false;
// $("#borrowerServiceLivingDate").datebox('clear');
// $("#livingDateG").hide();
		$("#livingDateG").html("");
	}
}
function showBusinessDate(r) {
	if ($("#" + r.id).attr("checked") == "checked") {
// $("#businessDateG").show();
// $("#borrowerServiceBusinessDate").validatebox.required = true;
		$("#businessDateG").html("<input id='borrowerServiceBusinessDate' class='easyui-datebox' type='text' name='borrowerService.businessDate' />");
		$("#borrowerServiceBusinessDate").datebox({
			required : true,
			editable : false
		});

	} else {
// $("#borrowerServiceBusinessDate").validatebox.required = false;
// $("#borrowerServiceBusinessDate").datebox('clear');
// $("#businessDateG").hide();
		$("#businessDateG").html("");

	}
	checkBusiness();
}
function checkCheckBox() {
	var checkFlag = false;
	for ( var i = 0; i < 5; i++) {
		if ($("#homeA" + i).attr("checked") == "checked") {
			checkFlag = true;
			break;
		}
	}
	return checkFlag;
}
function checkBusiness() {
	var checkFlag = false;
	for ( var i = 0; i < 5; i++) {
		if ($("#businessA" + i).attr("checked") == "checked") {
			checkFlag = true;
			break;
		}
	}
	if (checkFlag == true) {
		$("#borrowerServiceBusinessArea").numberbox({
			required : true
		});
		$("#borrowerServiceBusinessYear").numberbox({
			required : true
		});
		$("#borrowerServiceBusinessArea").numberbox('validate');
		$("#borrowerServiceBusinessYear").numberbox('validate');
	} else {
		$("#borrowerServiceBusinessArea").numberbox({
			required : false
		});
		$("#borrowerServiceBusinessYear").numberbox({
			required : false
		});
		$("#borrowerServiceBusinessArea").numberbox('clear');
		$("#borrowerServiceBusinessYear").numberbox('clear');
		$("#borrowerServiceBusinessArea").numberbox('validate');
		$("#borrowerServiceBusinessYear").numberbox('validate');
	}
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
			var s = $("#" + textId).val().substr(0, 100);
			$("#" + textId).val();
		} else {
			$("#" + htmlId).css({
				"color" : "#000"
			});
		}
		$("#" + htmlId).html(tishi);
	});
}

// //////////-------------------------------

function provinceShowG(province, city, county, town, village, address, detail) {
	var homeAddressG = $("#" + address).val();
	var homeAddressList = homeAddressG.split("-");
	var villageHomeG = $("#" + village).combobox("getValue");
	var townHomeG;
	var countyHomeG;
	var cityHomeG;
	var provinceHomeG;
// $("#detailHomeG").validatebox();
	if (villageHomeG != null && villageHomeG != "") {
		var urlTown = serverName + "/town/searchNscByVillageId.do";
		ajaxSubmit(urlTown, {
			townshipinfoid : villageHomeG
		}, function(resultMap) {
			$("#" + province).combobox({
				editable : false,
				url : serverName + '/NSC/list.do',
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				value : resultMap.provinceId,
				onSelect : function(value) {
					$("#" + city).combobox("clear");
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
					$("#" + city).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						value : ""
					});
				}
			});
			$("#" + county).combobox({
				url : serverName + '/NSC/listCity.do?parentId=' + resultMap.cityId,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				value : resultMap.countyId,
				onSelect : function(countryNSC) {
					$("#" + town).combobox("setValue", "");
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
					$("#" + town).combobox({
						url : serverName + '/town/list.do?parentId=' + countryNSC.cityCode,
						textField : 'townshipinfName',
						valueField : 'townshipinfoid',
						mode : 'remote',
						panelHeight : 'auto',
						delay : 500,
						value : ""
					});
				}
			});
			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + resultMap.provinceId,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				value : resultMap.cityId,
				onSelect : function(cityNSC) {
					$("#" + county).combobox("setValue", "");
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
					$("#" + county).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						panelHeight : 'auto',
						delay : 500,
						value : ""
					});
				}

			});

			$("#" + town).combobox({
				url : serverName + '/town/list.do?parentId=' + resultMap.countyId,
				textField : 'townshipinfName',
				valueField : 'townshipinfoid',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				value : resultMap.townId,
				onSelect : function(townTown) {
					$("#" + village).combobox("setValue", "");
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
					$("#" + village).combobox({
						url : serverName + '/town/list.do?parentId=' + townTown.townshipinfoid,
						textField : 'townshipinfName',
						valueField : 'townshipinfoid',
						mode : 'remote',
						panelHeight : 'auto',
						delay : 500,
						value : ""
					});
				}
			});
			$("#" + village).combobox({
				url : serverName + '/town/list.do?parentId=' + resultMap.townId,
				textField : 'townshipinfName',
				valueField : 'townshipinfoid',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				onSelect : function() {
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
				}
			});
		});
		$("#" + detail).val(homeAddressList[5]);
// $("#detailHomeG").validatebox('validate');
	} else {
		// cityComboboxG(province, city, county, town, village, provinceHomeG);
		cityComboboxPublic(province, city, county, town, village, provinceHomeG, detail, address, "");
	}

}

function cityComboboxG(province, city, county, town, village, provinceHomeG) {
	$("#" + province).combobox({
		editable : false,
		url : serverName + '/NSC/list.do',
		textField : 'cityName',
		valueField : 'cityCode',
		mode : 'remote',
		delay : 500,
		value : provinceHomeG,
		onSelect : function(value) {
			if (province == "provinceHomeG") {
				$("#" + city).combobox("clear");
				addAddressG(true);
			} else {
				addBusinessAddressG();
			}

			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				value : "",
				onSelect : function(value) {
					$("#" + county).combobox("setValue", "");
					if (province == "provinceHomeG") {
						addAddressG(true);
					} else {
						addBusinessAddressG();
					}
					$("#" + county).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						value : "",
						onSelect : function(value) {
							$("#" + town).combobox("clear");
							if (province == "provinceHomeG") {
								addAddressG(true);
							} else {
								addBusinessAddressG();
							}
							$("#" + town).combobox({
								url : serverName + '/town/list.do?parentId=' + value.cityCode,
								textField : 'townshipinfName',
								valueField : 'townshipinfoid',
								mode : 'remote',
								delay : 500,
								value : "",
								onSelect : function(value) {
									$("#" + village).combobox("clear");
									if (province == "provinceHomeG") {
										addAddressG(true);
									} else {
										addBusinessAddressG();
									}
									$("#" + village).combobox({
										url : serverName + '/town/list.do?parentId=' + value.townshipinfoid,
										textField : 'townshipinfName',
										valueField : 'townshipinfoid',
										mode : 'remote',
										delay : 500,
										value : "",
										onSelect : function(value) {
											if (province == "provinceHomeG") {
												addAddressG(true);
											} else {
												addBusinessAddressG();
											}
										}
									});
								}
							});
						}
					});
				}
			});
		}
	});
}

function addAddressG(paramValidateG) {
	if (paramValidateG) {
		var provinceHomeG = $("#provinceHomeG").combobox("getText");
		var cityHomeG = $("#cityHomeG").combobox("getText");
		var countyHomeG = $("#countyHomeG").combobox("getText");
		var townHomeG = $("#townHomeG").combobox("getText");
		var villageHomeG = $("#villageHomeG").combobox("getText");
		var detailHomeG = $("#detailHomeG").val();

		var homeAddressG = provinceHomeG + "-" + cityHomeG + "-" + countyHomeG + "-" + townHomeG + "-" + villageHomeG + "-" + detailHomeG;
		$("#homeAddressG").val(homeAddressG);
	}
}

function addBusinessAddressG() {

	var provinceBusinessG = $("#provinceBusinessG").combobox("getText");
	var cityBusinessG = $("#cityBusinessG").combobox("getText");
	var countyBusinessG = $("#countyBusinessG").combobox("getText");
	var townBusinessG = $("#townBusinessG").combobox("getText");
	var villageBusinessG = $("#villageBusinessG").combobox("getText");
	var detailBusinessG = $("#detailBusinessG").val();
	var businessAddressG = provinceBusinessG + "-" + cityBusinessG + "-" + countyBusinessG + "-" + townBusinessG + "-" + villageBusinessG + "-" + detailBusinessG;
	$("#businessAddressG").val(businessAddressG);
}