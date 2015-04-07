<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String isURL = request.getRequestURL().toString();
	if(isURL.indexOf(".cn")>0){
		if(cmUrl.indexOf(".corp")>0){
			cmUrl=cmUrl.replaceAll(".corp",".cn");
		}
	}else if(isURL.indexOf(".corp")>0){
		if(cmUrl.indexOf(".cn")>0){
			cmUrl=cmUrl.replaceAll(".cn",".corp");
		}
	}
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="../include/easyui.jsp"></jsp:include>
	<head>
		<script type="text/javascript">
		 var serverName="<%=path%>";
		 var cmUrl = "<%=cmUrl%>";
		 var userId = "<%=userId%>";
</script>
		<script type="text/javascript">var serverName="<%=path%>";var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/guaranorProfile.js"></script>
		<script type="text/javascript">
		$(function() {
			paramValidateG = false;
			var dicGuaranor = new DataDictionary();
			dicGuaranor.addDic("borrowerServiceNationality_borrowerService.nationality_Y", "national");
			dicGuaranor.addDic("borrowerServiceMaritalStatus_borrowerService.maritalStatus_Y", "maritalStatus");
			dicGuaranor.addDic("borrowerServiceJobStatus_borrowerService.jobStatus_Y", "jobStatus");
			dicGuaranor.addDic("borrowerServiceBondsmanBorrower_borrowerService.bondsmanBorrower_Y", "bondsmanBorrower");
			dicGuaranor.addDic("familymembers0BorrowerreRationship_familymembers[0].borrowerreRationship_Y", "borrowerreRationship");
			dicGuaranor.addDic("familymembers1BorrowerreRationship_familymembers[1].borrowerreRationship", "borrowerreRation");
			dicGuaranor.addDic("familymembers2BorrowerreRationship_familymembers[2].borrowerreRationship", "borrowerreRation");
			dicGuaranor.addDic("familymembers3BorrowerreRationship_familymembers[3].borrowerreRationship", "borrowerreRation");
			dicGuaranor.addDic("familymembers4BorrowerreRationship_familymembers[4].borrowerreRationship", "borrowerreRation");
			dicGuaranor.addDic("familymembers0Profession_familymembers[0].profession_Y", "profession");
			dicGuaranor.addDic("familymembers1Profession_familymembers[1].profession", "profession");
			dicGuaranor.addDic("familymembers2Profession_familymembers[2].profession", "profession");
			dicGuaranor.addDic("familymembers3Profession_familymembers[3].profession", "profession");
			dicGuaranor.addDic("familymembers4Profession_familymembers[4].profession", "profession");
			dicGuaranor.addDic("guranorsurveybusinessinfos0State_surveybusinessinfos[0].state", "stateSurveybusinessinfoList");
			dicGuaranor.addDic("guranorsurveybusinessinfos1State_surveybusinessinfos[1].state", "stateSurveybusinessinfoList");
			dicGuaranor.addDic("guranorsurveybusinessinfos2State_surveybusinessinfos[2].state", "stateSurveybusinessinfoList");

			dicGuaranor.addDic("otherQuestionsGuarantee_otherQuestions.guarantee_Y", "yesOrNo");
			dicGuaranor.addDic("otherQuestionsHowLong_otherQuestions.howLong_Y", "howLong");
			dicGuaranor.addDic("otherQuestionsGambling_otherQuestions.gambling_Y", "answer");
			dicGuaranor.addDic("otherQuestionsHelp_otherQuestions.help_Y", "answer");
			dicGuaranor.addDic("otherQuestionsEnoughDeposits_otherQuestions.enoughDeposits_Y", "yesOrNo");
			dicGuaranor.addDic("otherQuestionsSpouseInformed_otherQuestions.spouseInformed_Y", "yesOrNo");

			dicGuaranor.dicAjax();
			//provinceShowPublic("provinceHomeG", "cityHomeG", "countyHomeG");
			//cityComboboxPublic()
			provinceShowG("provinceHomeG", "cityHomeG", "countyHomeG", "townHomeG", "villageHomeG", "homeAddressG", "detailHomeG");
			provinceShowG("provinceBusinessG", "cityBusinessG", "countyBusinessG", "townBusinessG", "villageBusinessG", "businessAddressG", "detailBusinessG");

			
	$("#borrowerServiceLivingYear,#borrowerServiceBusinessYear").numberbox(
				{
					min : 0,
					precision : 2
				});

		$("#otherQuestionsGuarantee").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					$("#areaOne").show();
					$("#areaOneS").show();
					$("#otherQuestionsGuaranteeDetails").validatebox({
						required : true,
						validType : "length[0,100]"
					});

				} else {
					$("#otherQuestionsGuaranteeDetails").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#otherQuestionsGuaranteeDetails").val("");
					$("#areaOne").hide();
					$("#areaOneS").hide();

				}
			}
		});
		$("#otherQuestionsGambling").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					$("#areaTwo").show();
					$("#areaTwoS").show();
					$("#otherQuestionsGamblingSpouse").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#otherQuestionsGamblingSpouse").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#otherQuestionsGamblingSpouse").val("");
					$("#areaTwo").hide();
					$("#areaTwoS").hide();

				}
			}
		});
		$("#otherQuestionsHelp").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					$("#areaThree").show();
					$("#areaThreeS").show();
					$("#otherQuestionsHelpDetails").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#otherQuestionsHelpDetails").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#otherQuestionsHelpDetails").val("");
					$("#areaThree").hide();
					$("#areaThreeS").hide();

				}
			}
		});
		$("#otherQuestionsEnoughDeposits").combobox({
			onSelect : function(record) {
				if ("1" == record.codeKey) {
					$("#areaFour").show();
					$("#areaFourS").show();
					$("#otherQuestionsHowEnough").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#otherQuestionsHowEnough").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#otherQuestionsHowEnough").val("");
					$("#areaFour").hide();
					$("#areaFourS").hide();

				}
			}
		});
		$("#otherQuestionsSpouseInformed").combobox({
			onSelect : function(record) {
				if ("1" == record.codeKey) {
					$("#areaFive").show();
					$("#areaFiveS").show();
					$("#otherQuestionsSpouseExplanation").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#otherQuestionsSpouseExplanation").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#otherQuestionsSpouseExplanation").val("");
					$("#areaFive").hide();
					$("#areaFiveS").hide();

				}
			}
		});

		// showGuaranorDialog();
		guaranorProFlag="0";
		$("#guaranorProfileForm").form('validate');
		guaranorProFlag="1";
		$("#borrowerServiceName").focus();

		var length = $("#guranorjobInfosTable tbody tr td:nth-child(2)").length;
		for ( var i = 0; i < 2; i++) {
			$
					.each(
							$("#guranorjobInfosTable tbody tr td [id ^= 'guranorjobInfos"
									+ i + "']"), function(j, element) {
								$("#" + element.id).blur(
										function() {
											var elementName = element.id
													.substring(15, 16);
											guranorjobInfos(elementName);
										});
							});
		}

	//家庭成员当填写任何一项时：身份证号就为必填项 begin
	var length = $("#familyMemberSituationTable tbody tr td:nth-child(2)").length;
	for ( var i = 1; i < 5; i++) {
			$
					.each(
							$("#familyMemberSituationTable tbody tr td [id ^= 'familymembers"
									+ i + "']"), function(j, element) {
								$("#" + element.id).blur(
										function() {
											var elementName = element.id
													.substring(13, 14);
											familymembers(elementName);
										});
								if ("combobox-f combo-f" == $("#" + element.id)
										.attr("class")
										|| undefined == $("#" + element.id)
												.attr("class")) {
									var elementName = element.id.substring(13,
											14);
									$("#" + element.id).combobox(
													{
														width : 100,
														onSelect : function() {
															familymembers(elementName);
														}
													});
								}		
							});
		}
	//end
	
		var length = $("#guranorsurveybusinessTable tbody tr td:nth-child(2)").length;
		for ( var i = 0; i < 3; i++) {
			$
					.each(
							$("#guranorsurveybusinessTable tbody tr td [id ^= 'guranorsurveybusinessinfos"
									+ i + "']"),
							function(j, element) {
								$("#" + element.id)
										.blur(
												function() {
													var elementName = element.id
															.substring(26, 27);
													guranorsurveybusinessinfosHang(elementName);
												});
								if ("combobox-f combo-f" == $("#" + element.id)
										.attr("class")
										|| undefined == $("#" + element.id)
												.attr("class")) {
									var elementName = element.id.substring(26,
											27);
									$("#" + element.id)
											.combobox(
													{
														width : 100,
														onSelect : function() {
															guranorsurveybusinessinfosHang(elementName);
														}
													});
								}
							});
		}
	});

	function guranorsurveybusinessinfosHang(elementName) {
		var valueq = "";

		$
				.each(
						$("#guranorsurveybusinessTable tbody tr td [id ^= 'guranorsurveybusinessinfos"
								+ elementName + "']"),
						function(l, elementObject) {
							if ($("#" + elementObject.id).attr("value") != undefined
									&& $("#" + elementObject.id).attr("value") != null) {
								valueq = valueq
										+ $("#" + elementObject.id).attr(
												"value");
							}
							if ("combobox-f combo-f" == $(
									"#" + elementObject.id).attr("class")
									|| undefined == $("#" + elementObject.id)
											.attr("class")) {
								valueq = valueq
										+ $("#" + elementObject.id).combobox(
												"getValue");
							}
						});
		if (valueq != null && valueq != "") {
			$
					.each(
							$("#guranorsurveybusinessTable tbody tr td [id ^= 'guranorsurveybusinessinfos"
									+ elementName + "']"),
							function(k, elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								if ("combobox-f combo-f" == classNameOld
										|| classNameOld == undefined) {
									var comboboxValue = $(
											"#" + elementObject.id).combobox(
											"getValue");
									$("#" + elementObject.id)
											.combobox(
													{
														required : true,
														value : comboboxValue,
														validType : 'selectValueRequired["'
																+ elementObject.id
																+ '","请选择"]',
														width : 100
													});
									$("#" + elementObject.id).combobox(
											"validate");
								} else {
									var className = className = classNameOld
											.substring(7, classNameOld
													.indexOf(" v"));
									if ("validatebox" == className) {
										$("#" + elementObject.id).validatebox({
											required : true
										});
										$("#" + elementObject.id).validatebox(
												"validate");
									}

									if ("numberbox" == className) {
										$("#" + elementObject.id).numberbox({
											required : true,
											value : elementvalue
										});

										$("#" + elementObject.id).numberbox(
												"validate");
									}
								}
							});
		} else {
			$
					.each(
							$("#guranorsurveybusinessTable tbody tr td [id ^= 'guranorsurveybusinessinfos"
									+ elementName + "']"),
							function(k, elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								if ("combobox-f combo-f" == classNameOld) {
									var comboboxValue = $(
											"#" + elementObject.id).combobox(
											"getValue");
									$("#" + elementObject.id).combobox({
										required : false,
										value : comboboxValue,
										validType : "",
										width : 100
									});
									$("#" + elementObject.id).combobox(
											"validate");
								} else {
									if (classNameOld != undefined) {
										var className = className = classNameOld
												.substring(7, classNameOld
														.indexOf(" v"));
										if ("validatebox" == className) {
											$("#" + elementObject.id)
													.validatebox({
														required : false
													});
											$("#" + elementObject.id)
													.validatebox("validate");
										}

										if ("numberbox" == className) {
											$("#" + elementObject.id)
													.numberbox({
														required : false,
														value : elementvalue
													});

											$("#" + elementObject.id)
													.numberbox("validate");
										}
									}

								}

							});
		}
	}

	function guranorjobInfos(elementName) {
		var valueq = "";

		$.each($("#guranorjobInfosTable tbody tr td [id ^= 'guranorjobInfos"
				+ elementName + "']"), function(l, elementObject) {
			if ($("#" + elementObject.id).attr("value") != undefined
					&& $("#" + elementObject.id).attr("value") != null) {
				valueq = valueq + $("#" + elementObject.id).attr("value");
			}

		});
		if (valueq != null && valueq != "") {
			$
					.each(
							$("#guranorjobInfosTable tbody tr td [id ^= 'guranorjobInfos"
									+ elementName + "']"), function(k,
									elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var className = classNameOld.substring(7,
										classNameOld.indexOf(" v"));
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								if ("validatebox" == className) {
									$("#" + elementObject.id).validatebox({
										required : true
									});
									$("#" + elementObject.id).validatebox(
											"validate");
								}
								if ("numberbox" == className) {
									$("#" + elementObject.id).numberbox({
										required : true,
										value : elementvalue
									});

									$("#" + elementObject.id).numberbox(
											"validate");
								}
								if ("databox" == className) {
									$("#" + elementObject.id).databox({
										required : true,
										value : elementvalue
									});
									$("#" + elementObject.id).databox(
											"validate");
								}
								if ("combobox" == className) {
									$("#" + elementObject.id).combobox({
										required : true,
										value : elementvalue
									});
									$("#" + elementObject.id).combobox(
											"validate");
								}

							});
		} else {
			$.each(
							$("#guranorjobInfosTable tbody tr td [id ^= 'guranorjobInfos"
									+ elementName + "']"), function(k,
									elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var className = classNameOld.substring(7,
										classNameOld.indexOf(" v"));
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								if ("validatebox" == className) {
									$("#" + elementObject.id).validatebox({
										required : false
									});
									$("#" + elementObject.id).validatebox(
											"validate");
								}
								if ("numberbox" == className) {
									$("#" + elementObject.id).numberbox({
										required : false,
										value : elementvalue
									});
									$("#" + elementObject.id).numberbox(
											"validate");
								}
								if ("databox" == className) {
									$("#" + elementObject.id).databox({
										required : false,
										value : elementvalue
									});
									$("#" + elementObject.id).databox(
											"validate");
								}
								if ("combobox" == className) {
									$("#" + elementObject.id).combobox({
										required : false,
										value : elementvalue
									});
									$("#" + elementObject.id).combobox(
											"validate");
								}

							});
		}
	}
	//家庭成员情况
	function familymembers(elementName){
		var valueq = "";

		$.each($("#familyMemberSituationTable tbody tr td [id ^= 'familymembers"
				+ elementName + "']"), function(l, elementObject) {
			if ($("#" + elementObject.id).attr("value") != undefined
					&& $("#" + elementObject.id).attr("value") != null) {
				valueq = valueq + $("#" + elementObject.id).attr("value");
			}
			if ("combobox-f combo-f" == $(
									"#" + elementObject.id).attr("class")
									|| undefined == $("#" + elementObject.id)
											.attr("class")) {
								valueq = valueq
										+ $("#" + elementObject.id).combobox(
												"getValue");
							}

		});
		if (valueq != null && valueq != "") {
			$
					.each(
							$("#familyMemberSituationTable tbody tr td [id ^= 'IdNumber"
									+ elementName + "']"), function(k,
									elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var className = classNameOld.substring(7,
										classNameOld.indexOf(" v"));
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								var IdNumberId=(elementObject.id).substring(0, 8);
								if ("validatebox" == className&&"IdNumber"==IdNumberId) {
									$("#"+elementObject.id).validatebox({
										required : true
									});
									$("#"+elementObject.id).validatebox(
											"validate");
								}
							});
		} else {
			$.each(
							$("#familyMemberSituationTable tbody tr td [id ^= 'IdNumber"
									+ elementName + "']"), function(k,
									elementObject) {
								var classNameOld = $("#" + elementObject.id)
										.attr("class");
								var className = classNameOld.substring(7,
										classNameOld.indexOf(" v"));
								var elementvalue = $("#" + elementObject.id)
										.attr("value");
								var IdNumberId=(elementObject.id).substring(0, 8);
								if ("validatebox" == className&&"IdNumber"==IdNumberId) {
									$("#"+ elementObject.id).validatebox({
										required : false
									});
									$("#"+elementObject.id).validatebox(
											"validate");
								}
							});
		}
	}
	var guaranorProFlag="0";
	function familyIdNumber(){
		if(guaranorProFlag=="1"){
			var IdNumber = $.trim($("#IdNumber").val());   //获取刚填写IdNumber
			var IdNumberHidden=$.trim($("#IdNumberHidden").val());   //获取隐藏的IdNumber
			ajaxSubmit(serverName+"/customer/listForCombo.do?creditapplicationId="+parent.$("#creditapplicationId1").val(),{q:IdNumber},function(result){
				if(result != null && result != ""){
					$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
					if(result[0].blackFlag != null && result[0].blackFlag != ""){
						$.messager.alert("消息","该人已经加入黑名单","info",function(){
							$("#IdNumber").val("");
							return;
						});
					}
				}
				$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
			});
			if(IdNumberHidden!=null&&IdNumber!=null&&IdNumber!=IdNumberHidden){
			ajaxSubmit(serverName+"/customer/checkForCredentialsNumber.do",{credentialsNumber:IdNumber,creditapplicationId:parent.$("#creditapplicationId1").val()},function(res){
				
				//console.info(res);
				if(res != null && res != ""){
					$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
					if(res.success){
						
					}else{
						$.messager.alert("消息",res.msg,"info",function(){
							$("#IdNumber").val("");
							return;
						});
					}
				}
			});
			}
		}
	}
	//家庭成员身份证验证  1
	function familyIdNumber1(obj){
		if(guaranorProFlag=="1"){
			var IdNumber = obj.value;   //获取刚填写IdNumber1
			var IdNumberHidden=$.trim($("#IdNumberHidden1").val());   //获取隐藏的IdNumber
			
			
			ajaxSubmit(serverName+"/customer/listForCombo.do?creditapplicationId="+parent.$("#creditapplicationId1").val(),{q:IdNumber},function(result){
				if(result != null && result != ""){
					//$("#familymembers1age").numberbox("setValue",setAge(IdNumber));
					var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 		});
            			}
        			});
					if(result[0].blackFlag != null && result[0].blackFlag != ""){
						$.messager.alert("消息","该人已经加入黑名单","info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
							return;
						});
					}
				}
				//$("#familymembers1age").numberbox("setValue",setAge(IdNumber));
				var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 		});
            			}
        			});
			});
			if(IdNumberHidden!=null&&IdNumber!=null&&IdNumber!=IdNumberHidden){
			ajaxSubmit(serverName+"/customer/checkForCredentialsNumber.do",{credentialsNumber:IdNumber,creditapplicationId:parent.$("#creditapplicationId1").val()},function(res){
				
				//console.info(res);
				if(res != null && res != ""){
					//$("#familymembers1age").numberbox("setValue",setAge(IdNumber));
					var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 		});
            			}
        			});
					if(res.success){
						
					}else{
						$.messager.alert("消息",res.msg,"info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
							return;
						});
					}
				}
			});
			}
			var count=0;
			for(var i=1;i<5;i++){
			for(var j=1;j<5;j++){
				if(i==j){
					continue;
				}
				if(($("#IdNumber"+(i)).val()!="")&&($("#IdNumber"+(j)).val()!="")){
				if(($("#IdNumber"+(i)).val())==($("#IdNumber"+(j)).val())){
				count=count+1;
						break;
				}
				}
			}
	}
	if(count>0){
		$.messager.alert("消息","每个家庭成员的身份证号码不能相同！","info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() 
							{
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
            				return;
						});
	}
		}
		
	}
</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">
			<form id="guaranorProfileForm" method="post" novalidate>
				<table id="focusTable" fit="true" style="width: 100%;" align="center">
					<tr align="center" width="800">
						<td align="center" width="800">
							<span style="font-size: 25px; font-weight: 700; color: #4B0082;"> 担保人信息表 </span>
						</td>
					</tr>
					<tr align="center" width="800">
						<td align="center" width="800">
							<span style="font-size: 15px; font-weight: 700;">业务编号：${guaranorProfile.borrowerService.businessNumber}</span>
							<input id="borrowerServiceCreditapplicationId" name="borrowerService.creditapplicationId" value="${guaranorProfile.borrowerService.creditapplicationId}" type="hidden" readonly="readonly" />
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="5" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td colspan="5" align="center">

							<input name="borrowerService.customerBasicId" type="hidden" value="${guaranorProfile.borrowerService.customerBasicId}" />
							<input id="borrowerServiceBorrowerServiceAppId" name="borrowerService.borrowerServiceAppId" type="hidden" value="${guaranorProfile.borrowerService.borrowerServiceAppId}" />
							<input id="bsaDESId" name="borrowerService.borrowerServiceAppDESId" type="hidden" value="${guaranorProfile.borrowerService.borrowerServiceAppDESId}" />
						</td>
					</tr>
					<tr>
						<td width="150" align="right">
							姓名：
						</td>
						<td width="250">
							<input id="borrowerServiceName" type="text" name="borrowerService.name" value="${guaranorProfile.borrowerService.name}" style="width: 150px;" class="easyui-validatebox" required="true" maxlength="10" />
						</td>

						<td width="150" align="right">
							民族：
						</td>
						<td width="250">
							<input id="borrowerServiceNationality" type="text" name="borrowerService.nationality" value="${guaranorProfile.borrowerService.nationality}" style="width: 154px;" maxlength="10" />
						</td>
						<td width="80" align="right">
							&nbsp;
						</td>
					</tr>

					<tr>
						<td align="right">
							担保人身份证号：
						</td>
						<td>
							<c:if test="${guaranorProfile.borrowerService.credentialsNumber==''}">身份证号为空</c:if>
							<input id="borrowerServiceCredentialsNumber" type="text" readonly="true" name="borrowerService.credentialsNumber" value="${guaranorProfile.borrowerService.credentialsNumber}" style="width: 150px;" class="easyui-validatebox" required="true" />
						</td>

						<td align="right">
							曾用名:
						</td>
						<td>
							<input type="text" name="borrowerService.former" value="${guaranorProfile.borrowerService.former}" style="width: 150px;" maxlength="10" />
						</td>
						<td align="right">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							出生日期：
						</td>
						<td>
							<input id="borrowerServiceBirthday" type="text" name="borrowerService.birthday" value="${guaranorProfile.borrowerService.birthday}" style="width: 150px;" readonly="true" />
						</td>

						<td align="right">
							性别：
						</td>
						<td>
							<c:if test="${guaranorProfile.borrowerService.gender=='1'}">
								<input value="女" type="text" readonly="readonly" style="width: 150px;" />
							</c:if>
							<c:if test="${guaranorProfile.borrowerService.gender=='0'}">
								<input value="男" type="text" readonly="readonly" style="width: 150px;" />
							</c:if>
							<input id="borrowerServiceGender" name="borrowerService.gender" value="${guaranorProfile.borrowerService.gender}" type="hidden" style="width: 150px;" required="true" />
						</td>
						<td align="right">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							婚姻状况：
						</td>
						<td>
							<input id="borrowerServiceMaritalStatus" name="borrowerService.maritalStatus" value="${guaranorProfile.borrowerService.maritalStatus}" type="text" style="width: 154px;" required="true" />
						</td>

						<td align="right">
							就业状况：
						</td>
						<td>
							<input id="borrowerServiceJobStatus" name="borrowerService.jobStatus" type="text" value="${guaranorProfile.borrowerService.jobStatus}" style="width: 154px;" required="true" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号码：
						</td>
						<td>
							<input name="borrowerService.mobilephone" type="text" value="${guaranorProfile.borrowerService.mobilephone}" style="width: 150px;" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
						</td>

						<td align="right">
							办公电话：
						</td>
						<td>
							<input name="borrowerService.officePhone" type="text" value="${guaranorProfile.borrowerService.officePhone}" style="width: 150px;" class="easyui-validatebox" validType="phoneNumber"  maxlength="11" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							与借款人关系：
						</td>
						<td>
							<input id="borrowerServiceBondsmanBorrower" name="borrowerService.bondsmanBorrower" type="text" value="${guaranorProfile.borrowerService.bondsmanBorrower}" style="width: 154px;" required="true" />
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							家庭住址：
						</td>
						<td valign="top">
							<p>
								<input type="text" id="provinceHomeG" class="easyui-combobox" editable="false" required="true" style="width: 154px;" />
								省
							</p>
							<p>
								<input type="text" id="cityHomeG" class="easyui-combobox" editable="false" required="true" style="width: 154px;" />
								市
							</p>
							<p>
								<input type="text" id="countyHomeG" class="easyui-combobox" editable="false" required="true" style="width: 154px;" />
								区
							</p>
							<p>
								<input type="text" id="townHomeG" class="easyui-combobox" editable="false" required="true" style="width: 154px;" />
								镇
							</p>
							<p>
								<input type="text" name="borrowerService.villageId" value="${guaranorProfile.borrowerService.villageId}" id="villageHomeG" editable="false" required="true" class="easyui-combobox" style="width: 154px;" />
								村
							</p>
							<p style="width: 180px;">
								<input type="text" id="detailHomeG" class="easyui-validatebox" onkeydown="paramValidateG=true;" onblur="addAddressG(paramValidateG);" required="true" style="width: 150px;" maxlength="10" />
								号
							</p>
							<p>
								<textarea name="borrowerService.homeAddress" rows="3" cols="6" readonly="readonly" id="homeAddressG" style="width: 150px;">${guaranorProfile.borrowerService.homeAddress }</textarea>
							</p>
						</td>
						<td align="right" valign="top">
							现经营场所地址：
						</td>
						<td valign="top">
							<p>
								<input type="text" id="provinceBusinessG" class="easyui-combobox" editable="false" style="width: 154px;" />
								省
							</p>
							<p>
								<input type="text" id="cityBusinessG" class="easyui-combobox" editable="false" style="width: 154px;" />
								市
							</p>
							<p>
								<input type="text" id="countyBusinessG" class="easyui-combobox" editable="false" style="width: 154px;" />
								区
							</p>
							<p>
								<input type="text" id="townBusinessG" class="easyui-combobox" editable="false" style="width: 154px;" />
								镇
							</p>
							<p>
								<input type="text" name="borrowerService.townId" editable="false" value="${guaranorProfile.borrowerService.townId}" class="easyui-combobox" id="villageBusinessG" style="width: 154px;" />
								村
							</p>
							<p>
								<input type="text" id="detailBusinessG" onblur="addBusinessAddressG();" style="width: 150px;" maxlength="10" />
								号
							</p>
							<p>
								<textarea name="borrowerService.residenceAddress" rows="3" cols="6" readonly="readonly" id="businessAddressG" style="width: 150px;">${guaranorProfile.borrowerService.residenceAddress }</textarea>
							</p>
						</td>
					</tr>
					<tr>
						<td align="right">
							现家庭住址：
						</td>
						<td id="home">
							<p>
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingCommercial==1}">
											<input id="homeA0" type="checkbox" name="borrowerService.livingCommercial" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input id="homeA0" type="checkbox" name="borrowerService.livingCommercial" value="1" />
										</c:otherwise>
									</c:choose>
									自有商品房
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingSelf==1}">
											<input id="homeA1" type="checkbox" name="borrowerService.livingSelf" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input id="homeA1" type="checkbox" name="borrowerService.livingSelf" value="1" />
										</c:otherwise>
									</c:choose>
									自有宅基地
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingRent==1}">
											<input id="homeA2" type="checkbox" name="borrowerService.livingRent" checked="checked" value="1" onclick="showLivingDate(this)" />
										</c:when>
										<c:otherwise>
											<input id="homeA2" type="checkbox" name="borrowerService.livingRent" value="1" onclick="showLivingDate(this)" />
										</c:otherwise>
									</c:choose>
									租住（到期日
								</label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.livingRent==1}">
										<span id="livingDateG"><input id="borrowerServiceLivingDate" type="text" name="borrowerService.livingDate" value="<fmt:formatDate value='${guaranorProfile.borrowerService.livingDate}' pattern='yyyy-MM-dd' />" class="easyui-datebox" editable="false" required="true" /> </span>
									</c:when>
									<c:otherwise>
										<span id="livingDateG"></span>
									</c:otherwise>
								</c:choose>
								）
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingRelative==1}">
											<input id="homeA3" type="checkbox" name="borrowerService.livingRelative" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input id="homeA3" type="checkbox" name="borrowerService.livingRelative" value="1" />
										</c:otherwise>
									</c:choose>
									亲戚住房
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingOther==1}">
											<input id="homeA4" type="checkbox" name="borrowerService.livingOther" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input id="homeA4" type="checkbox" name="borrowerService.livingOther" value="1" />
										</c:otherwise>
									</c:choose>
									其他
								</label>
								<br />
							</p>
						</td>
						<td align="right">
							现经营场所地址：
						</td>
						<td>
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessCommercial==1}">
										<input id="businessA0" type="checkbox" name="borrowerService.businessCommercial" checked="checked" value="1" onclick="checkBusiness()"/>
									</c:when>
									<c:otherwise>
										<input id="businessA0" type="checkbox" name="borrowerService.businessCommercial" value="1" onclick="checkBusiness()"/>
									</c:otherwise>
								</c:choose>
								自有商品房
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessSelf==1}">
										<input id="businessA1" type="checkbox" name="borrowerService.businessSelf" checked="checked" value="1" onclick="checkBusiness()"/>
									</c:when>
									<c:otherwise>
										<input id="businessA1" type="checkbox" name="borrowerService.businessSelf" value="1" onclick="checkBusiness()"/>
									</c:otherwise>
								</c:choose>
								自有宅基地
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessRent==1}">
										<input id="businessA2" type="checkbox" name="borrowerService.businessRent" checked="checked" value="1" onclick="showBusinessDate(this)" />
									</c:when>
									<c:otherwise>
										<input id="businessA2" type="checkbox" name="borrowerService.businessRent" value="1" onclick="showBusinessDate(this)" />
									</c:otherwise>
								</c:choose>
								租住（到期日
							</label>
							<c:choose>
								<c:when test="${guaranorProfile.borrowerService.businessRent==1}">
									<span id="businessDateG"><input id="borrowerServiceBusinessDate" type="text" name="borrowerService.businessDate" value="<fmt:formatDate value='${guaranorProfile.borrowerService.businessDate}' pattern='yyyy-MM-dd' />" class="easyui-datebox" editable="false" required="true" /> </span>
								</c:when>
								<c:otherwise>
									<span id="businessDateG"></span>
								</c:otherwise>
							</c:choose>
							）
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessRelative==1}">
										<input id="businessA3" type="checkbox" name="borrowerService.businessRelative" checked="checked" value="1" onclick="checkBusiness()" />
									</c:when>
									<c:otherwise>
										<input id="businessA3" type="checkbox" name="borrowerService.businessRelative" value="1" onclick="checkBusiness()" />
									</c:otherwise>
								</c:choose>
								亲戚住房
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessOther==1}">
										<input id="businessA4" type="checkbox" name="borrowerService.businessOther" checked="checked" value="1" onclick="checkBusiness()" />
									</c:when>
									<c:otherwise>
										<input id="businessA4" type="checkbox" name="borrowerService.businessOther" value="1" onclick="checkBusiness()" />
									</c:otherwise>
								</c:choose>
								其他
							</label>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭住址面积：
						</td>
						<td>
							<input id="borrowerServiceLivingArea" type="text" name="borrowerService.livingArea" value="${guaranorProfile.borrowerService.livingArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true"  maxlength="10" />
						</td>

						<td align="right">
							经营场所面积：
						</td>
						<td>
							<input id="borrowerServiceBusinessArea" type="text" name="borrowerService.businessArea" value="${guaranorProfile.borrowerService.businessArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="10"/>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭住址年限：
						</td>
						<td>
							<input id="borrowerServiceLivingYear" type="text" name="borrowerService.livingYear" value="${guaranorProfile.borrowerService.livingYear}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="5" />
						</td>

						<td align="right">
							经营场所年限：
						</td>
						<td>
							<input id="borrowerServiceBusinessYear" type="text" name="borrowerService.businessYear" value="${guaranorProfile.borrowerService.businessYear}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="5" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>

				</table>
				 <p>&nbsp;	</p>
  <table id="familytable" width="898"   align="center" cellpadding="3" cellspacing="0">
  <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">配偶信息</span>
      </td>
    </tr>
    <tr>
      <td width="149">姓名</td>
      <td width="199">与担保人关系</td>
      <td width="155">身份证号</td>
      <td width="90">年龄</td>
      <td width="155">职业</td>
      <td width="185">职业明细</td>
      <td width="155">联系电话</td>
    </tr>
    <tr>
    <input type="hidden" name="familymembers[0].borrowerServiceAppId" value="${guaranorProfile.familymembers[0].borrowerServiceAppId}" />
	<input type="hidden" name="familymembers[0].familyMemberId" value="${guaranorProfile.familymembers[0].familyMemberId}" />
      <td><input  value="${guaranorProfile.familymembers[0].name }"  required="true" class="easyui-validatebox"   name="familymembers[0].name" maxlength="10" /></td>
      <td><input  name="familymembers[0].borrowerreRationship" id="familymembers0BorrowerreRationship" value="2"   size="15" hasDownArrow="false"/></td>
      <td><input  value="${guaranorProfile.familymembers[0].idNumber }"  required="true"  name="familymembers[0].idNumber" id="IdNumber" maxlength="18" class="easyui-validatebox" validType="idnumberAll" size="25"  onblur="familyIdNumber();"/><input  value="${guaranorProfile.familymembers[0].idNumber }"  type="hidden"  id="IdNumberHidden" class="easyui-validatebox" validType="idnumberAll" maxlength="18"/></td>
      <td><input  value="${guaranorProfile.familymembers[0].age }"   readonly="readonly" class="easyui-numberbox" id="familymemberListAge"  name="familymembers[0].age"   validType="number" maxlength="3" size="15"/></td>
      <td><input  value="${guaranorProfile.familymembers[0].profession }"  required="true"    name="familymembers[0].profession" id="familymembers0Profession" size="15"/></td>
      <td><input  value="${guaranorProfile.familymembers[0].professionDetail }"  required="true" class="easyui-validatebox"   name="familymembers[0].professionDetail" maxlength="20"/></td>
      <td><input  value="${guaranorProfile.familymembers[0].telphone }"  required="true" class="easyui-validatebox"   name="familymembers[0].telphone" validType="phoneNumber" maxlength="11"/>
      		<input  value="1" name="familymembers[0].seq"   type="hidden"  />
      </td>
    </tr>
  </table>
				<table  id="familyMemberSituationTable"  fit="true" width="100%"  align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="6" align="center">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<span style="font-size: 15px; font-weight: 700;">家庭成员情况</span>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td width="149">
							姓名
						</td>
						<td width="199">
							与担保人关系
						</td>
						<td width="155">
							身份证号
						</td>
						<td width="90">
							年龄
						</td>
						<td width="155">
							职业
						</td>
						<td width="185">
							职业明细
						</td>
						<td width="155">
							联系电话
						</td>
					</tr>
					<%--<c:forEach items="${guaranorProfile.familymembers}" begin="0" end="4" var="f" varStatus="status">
						<tr>
							<td align="center">
								<input type="hidden" name="familymembers[${status.index}].borrowerServiceAppId" value="${f.borrowerServiceAppId}" />
								<input type="text" name="familymembers[${status.index}].name" value="${f.name}" />
							</td>
							<td align="center">
								<input type="text" name="familymembers[${status.index}].borrowerreRationship" value="${f.borrowerreRationship}" />
							</td>
							<td align="center">
								<input type="text" name="familymembers[${status.index}].age" value="${f.age}" />
							</td>
							<td align="center">
								<input type="text" name="familymembers[${status.index}].profession" value="${f.profession}" />
							</td>
							<td align="center">
								<input type="text" name="familymembers[${status.index}].telphone" value="${f.telphone}" />
							</td>
						</tr>
					</c:forEach>--%>
				
					<tr>
						<td>
							<input type="hidden" name="familymembers[1].borrowerServiceAppId" value="${guaranorProfile.familymembers[1].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[1].familyMemberId" value="${guaranorProfile.familymembers[1].familyMemberId}" />
							<input type="text" id="familymembers1name"  class="easyui-validatebox" name="familymembers[1].name" value="${guaranorProfile.familymembers[1].name}" maxlength="10" />
						</td>
						<td>
							<input id="familymembers1BorrowerreRationship" type="text" name="familymembers[1].borrowerreRationship" value="${guaranorProfile.familymembers[1].borrowerreRationship}" />
						</td>
						<td><input  value="${guaranorProfile.familymembers[1].idNumber }" class="easyui-validatebox"  name="familymembers[1].idNumber" id="IdNumber1" maxlength="18" validType="idnumberAll" size="25"  onblur="familyIdNumber1(this);"/>
						<input  value="${guaranorProfile.familymembers[1].idNumber }"  type="hidden"  id="IdNumberHidden1" class="easyui-validatebox" validType="idnumberAll" maxlength="18"/>
						</td>
						<td>
							<input type="text" name="familymembers[1].age" id="familymembers1age" value="${guaranorProfile.familymembers[1].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" style="width:90px" />
						</td>
						<td>
							<input id="familymembers1Profession" type="text" name="familymembers[1].profession" value="${guaranorProfile.familymembers[1].profession}" maxlength="10" />
						</td>
						<td>
							<input id="familymembers1professionDetail" class="easyui-validatebox" type="text" name="familymembers[1].professionDetail" value="${guaranorProfile.familymembers[1].professionDetail}" maxlength="30" />
						</td>
						<td>
							<input id="familymembers1telphone" type="text" name="familymembers[1].telphone" value="${guaranorProfile.familymembers[1].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
							<input  value="2" name="familymembers[1].seq"   type="hidden"  />
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="hidden" name="familymembers[2].borrowerServiceAppId" value="${guaranorProfile.familymembers[2].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[2].familyMemberId" value="${guaranorProfile.familymembers[2].familyMemberId}" />
							<input type="text"  id="familymembers2name" name="familymembers[2].name" value="${guaranorProfile.familymembers[2].name}" maxlength="10" class="easyui-validatebox"/>
						</td>
						<td >
							<input id="familymembers2BorrowerreRationship" type="text" name="familymembers[2].borrowerreRationship" value="${guaranorProfile.familymembers[2].borrowerreRationship}" />
						</td>
						<td><input  value="${guaranorProfile.familymembers[2].idNumber }"    name="familymembers[2].idNumber" id="IdNumber2" maxlength="18" class="easyui-validatebox" validType="idnumberAll" size="25"  onblur="familyIdNumber1(this);"/>
						<input  value="${guaranorProfile.familymembers[2].idNumber }"  type="hidden"  id="IdNumberHidden2" class="easyui-validatebox" validType="idnumberAll" maxlength="18"/>
						</td>
						<td >
							<input type="text" name="familymembers[2].age" id="familymembers2age" value="${guaranorProfile.familymembers[2].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" style="width:90px"/>
						</td>
						<td >
							<input id="familymembers2Profession" type="text" name="familymembers[2].profession" value="${guaranorProfile.familymembers[2].profession}" maxlength="10" />
						</td>
						<td >
							<input id="familymembers2professionDetail" type="text" class="easyui-validatebox" name="familymembers[2].professionDetail" value="${guaranorProfile.familymembers[2].professionDetail}" maxlength="30" />
						</td>
						<td >
							<input id="familymembers2telphone" type="text" name="familymembers[2].telphone" value="${guaranorProfile.familymembers[2].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
							<input  value="3" name="familymembers[2].seq"   type="hidden"  />
						</td>
					</tr>
					<tr>
						<td >
							<input type="hidden" name="familymembers[3].borrowerServiceAppId" value="${guaranorProfile.familymembers[3].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[3].familyMemberId" value="${guaranorProfile.familymembers[3].familyMemberId}" />
							<input type="text" id="familymembers3name" name="familymembers[3].name" class="easyui-validatebox" value="${guaranorProfile.familymembers[3].name}" maxlength="10" />
						</td>
						<td >
							<input id="familymembers3BorrowerreRationship" type="text" name="familymembers[3].borrowerreRationship" value="${guaranorProfile.familymembers[3].borrowerreRationship}" />
						</td>
						<td><input  value="${guaranorProfile.familymembers[3].idNumber }"    name="familymembers[3].idNumber" id="IdNumber3" maxlength="18" class="easyui-validatebox" validType="idnumberAll" size="25"  onblur="familyIdNumber1(this);"/>
						<input  value="${guaranorProfile.familymembers[3].idNumber }"  type="hidden"  id="IdNumberHidden3" class="easyui-validatebox" validType="idnumberAll" maxlength="18"/>
						</td>
						<td >
							<input type="text" name="familymembers[3].age" id="familymembers3age" value="${guaranorProfile.familymembers[3].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" style="width:90px"/>
						</td>
						<td >
							<input id="familymembers3Profession" type="text" name="familymembers[3].profession" value="${guaranorProfile.familymembers[3].profession}" maxlength="10" />
						</td>
						<td >
							<input type="text" id="familymembers3professionDetail" name="familymembers[3].professionDetail" class="easyui-validatebox" value="${guaranorProfile.familymembers[3].professionDetail}" maxlength="30" />
						</td>
						<td >
							<input id="familymembers3telphone" type="text" name="familymembers[3].telphone" value="${guaranorProfile.familymembers[3].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
							<input  value="4" name="familymembers[3].seq"   type="hidden"  />
						</td>
					</tr>
					<tr>
						<td >
							<input type="hidden" name="familymembers[4].borrowerServiceAppId" value="${guaranorProfile.familymembers[4].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[4].familyMemberId" value="${guaranorProfile.familymembers[4].familyMemberId}" />
							<input type="text" id="familymembers4name" name="familymembers[4].name" value="${guaranorProfile.familymembers[4].name}" class="easyui-validatebox" maxlength="10" />
						</td>
						<td >
							<input id="familymembers4BorrowerreRationship" type="text" name="familymembers[4].borrowerreRationship" value="${guaranorProfile.familymembers[4].borrowerreRationship}" />
						</td>
						<td><input  value="${guaranorProfile.familymembers[4].idNumber }"    name="familymembers[4].idNumber" id="IdNumber4" maxlength="18" class="easyui-validatebox" validType="idnumberAll" size="25"  onblur="familyIdNumber1(this);"/>
						<input  value="${guaranorProfile.familymembers[4].idNumber }"  type="hidden"  id="IdNumberHidden4" class="easyui-validatebox" validType="idnumberAll" maxlength="18"/>
						</td>
						<td >
							<input type="text" name="familymembers[4].age" value="${guaranorProfile.familymembers[4].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" style="width:90px"/>
						</td>
						<td >
							<input id="familymembers4Profession" type="text" name="familymembers[4].profession" value="${guaranorProfile.familymembers[4].profession}" maxlength="10" />
						</td>
						<td >
							<input type="text" id="familymembers4professionDetail" name="familymembers[4].professionDetail" value="${guaranorProfile.familymembers[4].professionDetail}" class="easyui-validatebox" maxlength="30" />
						</td>
						<td >
							<input id="familymembers4telphone" type="text" name="familymembers[4].telphone" value="${guaranorProfile.familymembers[4].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
							<input  value="5" name="familymembers[4].seq"   type="hidden"  />
						</td>
					</tr>

					<tr>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
					</tr>
				</table>

				<table id="guranorjobInfosTable" fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="5" align="center">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<span style="font-size: 15px; font-weight: 700;">相关工作情况</span>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							就职公司
						</td>
						<td align="center">
							地址
						</td>
						<td align="center">
							工作年限
						</td>
						<td align="center">
							职位
						</td>
						<td align="center">
							每月净收入
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="jobInfos[0].borrowerServiceAppId" value="${guaranorProfile.jobInfos[0].borrowerServiceAppId}" />
							<input type="hidden" name="jobInfos[0].jobInfoId" value="${guaranorProfile.jobInfos[0].jobInfoId}" />
							<input name="jobInfos[0].company" id="guranorjobInfos0company" type="text" value="${guaranorProfile.jobInfos[0].company}" class="easyui-validatebox" maxlength="20" />
						</td>
						<td align="center">
							<input name="jobInfos[0].workunitAddress" id="guranorjobInfos0workunitAddress" value="${guaranorProfile.jobInfos[0].workunitAddress}" class="easyui-validatebox" maxlength="30" />
						</td>
						<td align="center">
							<input name="jobInfos[0].years" id="guranorjobInfos0years" type="text" value="${guaranorProfile.jobInfos[0].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input name="jobInfos[0].post" id="guranorjobInfos0post" type="text" value="${guaranorProfile.jobInfos[0].post}" class="easyui-validatebox" maxlength="10" />
						</td>
						<td align="center">
							<input name="jobInfos[0].income" id="guranorjobInfos0income" type="text" value="${guaranorProfile.jobInfos[0].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
							 <input   name="jobInfos[0].seq"   type="hidden""  value = "1"/>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="jobInfos[1].borrowerServiceAppId" value="${guaranorProfile.jobInfos[1].borrowerServiceAppId}" />
							<input type="hidden" name="jobInfos[1].jobInfoId" value="${guaranorProfile.jobInfos[1].jobInfoId}" />
							<input name="jobInfos[1].company" id="guranorjobInfos1company" type="text" value="${guaranorProfile.jobInfos[1].company}" maxlength="20" class="easyui-validatebox"/>
						</td>
						<td align="center">
							<input name="jobInfos[1].workunitAddress" id="guranorjobInfos1workunitAddress" type="text" value="${guaranorProfile.jobInfos[1].workunitAddress}" maxlength="30" class="easyui-validatebox"/>
						</td>
						<td align="center">
							<input name="jobInfos[1].years" id="guranorjobInfos1years"  type="text" value="${guaranorProfile.jobInfos[1].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input name="jobInfos[1].post" id="guranorjobInfos1post" type="text" value="${guaranorProfile.jobInfos[1].post}" maxlength="10" class="easyui-validatebox"/>
						</td>
						<td align="center">
							<input name="jobInfos[1].income"  id="guranorjobInfos1income" type="text" value="${guaranorProfile.jobInfos[1].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
							<input   name="jobInfos[1].seq"   type="hidden""  value = "2"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							&nbsp;
						</td>
					</tr>
				</table>

				<table id="guranorsurveybusinessTable" fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<span style="font-size: 15px; font-weight: 700;">经营情况（包括当前和历史的经营活动）</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<%--<td align="center">
							经营项目
						</td>
						--%>
						<td align="center">
							经营项目详细
						</td>
						<td align="center">
							起始年份
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							（仍在营业的业务）每月毛收入
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input id="surveybusinessinfos0OperatingItems" name="surveybusinessinfos[0].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[0].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input type="hidden" name="surveybusinessinfos[0].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[0].borrowerServiceAppId}" />
							<input type="hidden" name="surveybusinessinfos[0].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[0].surveyBusinessInfoId}" />
							<input class="easyui-validatebox" name="surveybusinessinfos[0].operatingItemsDetail" id="guranorsurveybusinessinfos0operatingItemsDetail"  type="text" value="${guaranorProfile.surveybusinessinfos[0].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[0].startingDate" id="guranorsurveybusinessinfos0startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[0].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input id="guranorsurveybusinessinfos0State" name="surveybusinessinfos[0].state" type="text" value="${guaranorProfile.surveybusinessinfos[0].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[0].monthGrossIncome" id="guranorsurveybusinessinfos0monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[0].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
							<input   name="surveybusinessinfos[0].seq"   type="hidden""  value = "1"/>
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input id="surveybusinessinfos1OperatingItems" name="surveybusinessinfos[1].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[1].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input type="hidden" name="surveybusinessinfos[1].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[1].borrowerServiceAppId}" />
							<input type="hidden" name="surveybusinessinfos[1].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[1].surveyBusinessInfoId}" />
							<input class="easyui-validatebox" name="surveybusinessinfos[1].operatingItemsDetail"  id="guranorsurveybusinessinfos1operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[1].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[1].startingDate" id="guranorsurveybusinessinfos1startingDate" type="text" 
							value="${guaranorProfile.surveybusinessinfos[1].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input id="guranorsurveybusinessinfos1State" name="surveybusinessinfos[1].state"
							type="text" value="${guaranorProfile.surveybusinessinfos[1].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[1].monthGrossIncome" id="guranorsurveybusinessinfos1monthGrossIncome" 
							type="text" value="${guaranorProfile.surveybusinessinfos[1].monthGrossIncome}" class="easyui-numberbox" 
							onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
							<input   name="surveybusinessinfos[1].seq"   type="hidden""  value = "2"/>
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input id="surveybusinessinfos2OperatingItems" name="surveybusinessinfos[2].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[2].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input type="hidden" name="surveybusinessinfos[2].borrowerServiceAppId" 
							value="${guaranorProfile.surveybusinessinfos[2].borrowerServiceAppId}" />
							<input type="hidden" name="surveybusinessinfos[2].surveyBusinessInfoId" 
							value="${guaranorProfile.surveybusinessinfos[2].surveyBusinessInfoId}" />
							<input class="easyui-validatebox" name="surveybusinessinfos[2].operatingItemsDetail" 
							id="guranorsurveybusinessinfos2operatingItemsDetail" type="text" 
							value="${guaranorProfile.surveybusinessinfos[2].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[2].startingDate" id="guranorsurveybusinessinfos2startingDate"
							 type="text" value="${guaranorProfile.surveybusinessinfos[2].startingDate}" 
							 class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input  id="guranorsurveybusinessinfos2State" name="surveybusinessinfos[2].state" 
							type="text" value="${guaranorProfile.surveybusinessinfos[2].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[2].monthGrossIncome" id="guranorsurveybusinessinfos2monthGrossIncome" 
							type="text" value="${guaranorProfile.surveybusinessinfos[2].monthGrossIncome}" class="easyui-numberbox" 
							onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
							<input   name="surveybusinessinfos[2].seq"   type="hidden""  value = "3"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							&nbsp;
						</td>
					</tr>
				</table>

				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="2" align="center">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<span style="font-size: 15px; font-weight: 700;">其他问题</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以前是否为别人做过担保
						</td>
						<td width="200" align="left">
							<input type="hidden" name="otherQuestions.borrowerServiceAppId" value="${guaranorProfile.otherQuestions.borrowerServiceAppId}" />
							<input type="hidden" name="otherQuestions.otherQuestionsId" value="${guaranorProfile.otherQuestions.otherQuestionsId}" />
							<input id="otherQuestionsGuarantee" type="text" name="otherQuestions.guarantee" value="${guaranorProfile.otherQuestions.guarantee}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.guarantee==0}">
									<span id="areaOne"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGuaranteeDetails' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGuaranteeDetails', 'areaOneS', 100);" name='otherQuestions.guaranteeDetails' rows='2' style='width: 757px;' maxlength='100'>${guaranorProfile.otherQuestions.guaranteeDetails }</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGuaranteeDetails' onclick="textCount('otherQuestionsGuaranteeDetails', 'areaOneS', 100);" name='otherQuestions.guaranteeDetails' rows='2' style='width: 757px;' maxlength='100'>${guaranorProfile.otherQuestions.guaranteeDetails }</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaOneS"></span>
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsHowLong" type="text" name="otherQuestions.howLong" value="${guaranorProfile.otherQuestions.howLong}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsGambling" type="text" name="otherQuestions.gambling" value="${guaranorProfile.otherQuestions.gambling}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.gambling==0}">
									<span id="areaTwo"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGamblingSpouse' name='otherQuestions.gamblingSpouse' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGamblingSpouse', 'areaTwoS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.gamblingSpouse }</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGamblingSpouse' name='otherQuestions.gamblingSpouse' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsGamblingSpouse', 'areaTwoS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.gamblingSpouse }</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaTwoS"></span>
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）以前有没有向你家请求过帮助？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsHelp" type="text" name="otherQuestions.help" value="${guaranorProfile.otherQuestions.help}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.help==0}">
									<span id="areaThree"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHelpDetails' name='otherQuestions.helpDetails' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHelpDetails', 'areaThreeS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.helpDetails}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaThree" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHelpDetails' name='otherQuestions.helpDetails' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsHelpDetails', 'areaThreeS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.helpDetails}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaThreeS"></span>
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这次你担保的借款是多少额度？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsBorrowAmount" type="text" name="otherQuestions.borrowAmount" value="${guaranorProfile.otherQuestions.borrowAmount}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" required="true" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果（申请人姓名）不能还款，你愿意替他/她还款多少？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsGuaranteeAmount" type="text" name="otherQuestions.guaranteeAmount" value="${guaranorProfile.otherQuestions.guaranteeAmount}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" required="true" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你有这么多存款替他/她还款吗？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsEnoughDeposits" type="text" name="otherQuestions.enoughDeposits" value="${guaranorProfile.otherQuestions.enoughDeposits}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.enoughDeposits==1}">
									<span id="areaFour"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHowEnough' name='otherQuestions.howEnough' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHowEnough', 'areaFourS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.howEnough}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaFour" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHowEnough' name='otherQuestions.howEnough' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsHowEnough', 'areaFourS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.howEnough}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaFourS"></span>
						</td>
					</tr>
					<tr>
						<td width="400" align="left" >
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你的配偶是否知道并同意你担保这笔借款呢？
						</td>
						<td width="200" align="left">
							<input id="otherQuestionsSpouseInformed" type="text" name="otherQuestions.spouseInformed" value="${guaranorProfile.otherQuestions.spouseInformed}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.spouseInformed==1}">
									<span id="areaFive"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsSpouseExplanation' name='otherQuestions.spouseExplanation' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsSpouseExplanation', 'areaFiveS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.spouseExplanation}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaFive" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsSpouseExplanation' name='otherQuestions.spouseExplanation' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsSpouseExplanation', 'areaFiveS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.spouseExplanation}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaFiveS"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>

