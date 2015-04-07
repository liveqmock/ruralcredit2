<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="../include/easyui.jsp"></jsp:include>
	<head>
		<%--<script type="text/javascript">var serverName="<%=path%>";</script>--%>
		<script type="text/javascript">var serverName="<%=path%>";</script>
		<script type="text/javascript">
		$(function() {
			paramValidateG = false;
			var dicGuaranor = new DataDictionary();
			dicGuaranor.addDic("borrowerServiceNationality_borrowerService.nationality_Y", "national");
			dicGuaranor.addDic("borrowerServiceMaritalStatus_borrowerService.maritalStatus_Y", "maritalStatus");
			dicGuaranor.addDic("borrowerServiceJobStatus_borrowerService.jobStatus_Y", "jobStatus");
			dicGuaranor.addDic("borrowerServiceBondsmanBorrower_borrowerService.bondsmanBorrower_Y", "bondsmanBorrower");
			dicGuaranor.addDic("familymembers0BorrowerreRationship_familymembers[0].borrowerreRationship_Y", "borrowerreRationship");
			dicGuaranor.addDic("familymembers1BorrowerreRationship_familymembers[1].borrowerreRationship", "borrowerreRationship");
			dicGuaranor.addDic("familymembers2BorrowerreRationship_familymembers[2].borrowerreRationship", "borrowerreRationship");
			dicGuaranor.addDic("familymembers3BorrowerreRationship_familymembers[3].borrowerreRationship", "borrowerreRationship");
			dicGuaranor.addDic("familymembers4BorrowerreRationship_familymembers[4].borrowerreRationship", "borrowerreRationship");
			dicGuaranor.addDic("familymembers0Profession_familymembers[0].profession_Y", "profession");
			dicGuaranor.addDic("familymembers1Profession_familymembers[1].profession", "profession");
			dicGuaranor.addDic("familymembers2Profession_familymembers[2].profession", "profession");
			dicGuaranor.addDic("familymembers3Profession_familymembers[3].profession", "profession");
			dicGuaranor.addDic("familymembers4Profession_familymembers[4].profession", "profession");
			dicGuaranor.addDic("surveybusinessinfos0State_surveybusinessinfos[0].state", "stateSurveybusinessinfoList");
			dicGuaranor.addDic("surveybusinessinfos1State_surveybusinessinfos[1].state", "stateSurveybusinessinfoList");
			dicGuaranor.addDic("surveybusinessinfos2State_surveybusinessinfos[2].state", "stateSurveybusinessinfoList");

			dicGuaranor.addDic("otherQuestionsGuarantee_otherQuestions.guarantee_Y", "yesOrNo");
			dicGuaranor.addDic("otherQuestionsHowLong_otherQuestions.howLong_Y", "howLong");
			dicGuaranor.addDic("otherQuestionsGambling_otherQuestions.gambling_Y", "answer");
			dicGuaranor.addDic("otherQuestionsHelp_otherQuestions.help_Y", "answer");
			dicGuaranor.addDic("otherQuestionsEnoughDeposits_otherQuestions.enoughDeposits_Y", "yesOrNo");
			dicGuaranor.addDic("otherQuestionsSpouseInformed_otherQuestions.spouseInformed_Y", "yesOrNo");

			dicGuaranor.dicAjax();

			
			$("#guaranorProfileForm input").attr("readonly", true);
			$("#guaranorProfileForm textarea").attr("readonly", true);
			$("#guaranorProfileForm").find("input[type=checkbox]").attr("disabled", "disabled");
			
			
			$("#borrowerServiceMaritalStatus").combobox({hasDownArrow:false});
			$("#borrowerServiceJobStatus").combobox({hasDownArrow:false});
			$("#borrowerServiceBondsmanBorrower").combobox({hasDownArrow:false});
			$("#borrowerServiceLivingDate").datebox('disable');
			$("#borrowerServiceBusinessDate").datebox('disable');
			$("#familymembers0BorrowerreRationship").combobox({hasDownArrow:false});
			$("#familymembers1BorrowerreRationship").combobox({hasDownArrow:false});
			$("#familymembers2BorrowerreRationship").combobox({hasDownArrow:false});
			$("#familymembers3BorrowerreRationship").combobox({hasDownArrow:false});
			$("#familymembers4BorrowerreRationship").combobox({hasDownArrow:false});
			$("#familymembers0Profession").combobox({hasDownArrow:false});
			$("#familymembers1Profession").combobox({hasDownArrow:false});
			$("#familymembers2Profession").combobox({hasDownArrow:false});
			$("#familymembers3Profession").combobox({hasDownArrow:false});
			$("#familymembers4Profession").combobox({hasDownArrow:false});


			$("#surveybusinessinfos0State").combobox({hasDownArrow:false});
			$("#surveybusinessinfos1State").combobox({hasDownArrow:false});
			$("#surveybusinessinfos2State").combobox({hasDownArrow:false});

			$("#otherQuestionsGuarantee").combobox({hasDownArrow:false});
			$("#otherQuestionsHowLong").combobox({hasDownArrow:false});
			$("#otherQuestionsGambling").combobox({hasDownArrow:false});
			$("#otherQuestionsHelp").combobox({hasDownArrow:false});
			$("#otherQuestionsEnoughDeposits").combobox({hasDownArrow:false});
			$("#otherQuestionsSpouseInformed").combobox({hasDownArrow:false});
			$("#borrowerServiceNationality").combobox({hasDownArrow:false});
			
		});
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
					<tr align="center" width="100%">
						<td align="center" width="100%">
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
							<input name="borrowerService.officePhone" type="text" value="${guaranorProfile.borrowerService.officePhone}" style="width: 150px;" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
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
							
								<textarea name="borrowerService.homeAddress" rows="3" cols="6" readonly="readonly" id="homeAddressG" style="width: 150px;">${guaranorProfile.borrowerService.homeAddress }</textarea>
							
						</td>
						<td align="right" valign="top">
							现经营场所地址：
						</td>
						<td valign="top">
							
								<textarea name="borrowerService.residenceAddress" rows="3" cols="6" readonly="readonly" id="businessAddressG" style="width: 150px;">${guaranorProfile.borrowerService.residenceAddress }</textarea>
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
											<input id="homeA2" type="checkbox" name="borrowerService.livingRent" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input id="homeA2" type="checkbox" name="borrowerService.livingRent" value="1" />
										</c:otherwise>
									</c:choose>
									租住（到期日
								</label>
								<span id="livingDateG"></span> ）
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
										<input id="businessA0" type="checkbox" name="borrowerService.businessCommercial" checked="checked" value="1" />
									</c:when>
									<c:otherwise>
										<input id="businessA0" type="checkbox" name="borrowerService.businessCommercial" value="1" />
									</c:otherwise>
								</c:choose>
								自有商品房
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessSelf==1}">
										<input id="businessA1" type="checkbox" name="borrowerService.businessSelf" checked="checked" value="1" />
									</c:when>
									<c:otherwise>
										<input id="businessA1" type="checkbox" name="borrowerService.businessSelf" value="1" />
									</c:otherwise>
								</c:choose>
								自有宅基地
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessRent==1}">
										<input id="businessA2" type="checkbox" name="borrowerService.businessRent" checked="checked" value="1" />
									</c:when>
									<c:otherwise>
										<input id="businessA2" type="checkbox" name="borrowerService.businessRent" value="1" />
									</c:otherwise>
								</c:choose>
								租住（到期日
							</label>
							<span id="businessDateG"></span> ）
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
							<input id="borrowerServiceLivingArea" type="text" name="borrowerService.livingArea" value="${guaranorProfile.borrowerService.livingArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="5" />
						</td>

						<td align="right">
							经营场所面积：
						</td>
						<td>
							<input id="borrowerServiceBusinessArea" type="text" name="borrowerService.businessArea" value="${guaranorProfile.borrowerService.businessArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="5" />
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
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
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
						<td align="center">
							姓名
						</td>
						<td align="center">
							与担保人关系
						</td>
						<td align="center">
							年龄
						</td>
						<td align="center">
							职业
						</td>
						<td align="center">
							职业明细
						</td>
						<td align="center">
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
						<td align="center">
							<input type="hidden" name="familymembers[0].borrowerServiceAppId" value="${guaranorProfile.familymembers[0].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[0].familyMemberId" value="${guaranorProfile.familymembers[0].familyMemberId}" />
							<input type="text" name="familymembers[0].name" value="${guaranorProfile.familymembers[0].name}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
						<td align="center">
							<input id="familymembers0BorrowerreRationship" type="text" name="familymembers[0].borrowerreRationship" value="${guaranorProfile.familymembers[0].borrowerreRationship}" required="true" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[0].age" value="${guaranorProfile.familymembers[0].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="3" />
						</td>
						<td align="center">
							<input id="familymembers0Profession" type="text" name="familymembers[0].profession" value="${guaranorProfile.familymembers[0].profession}" required="true" maxlength="10" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[0].professionDetail" value="${guaranorProfile.familymembers[0].professionDetail}" class="easyui-validatebox" required="true" maxlength="30" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[0].telphone" value="${guaranorProfile.familymembers[0].telphone}" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="familymembers[1].borrowerServiceAppId" value="${guaranorProfile.familymembers[1].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[1].familyMemberId" value="${guaranorProfile.familymembers[1].familyMemberId}" />
							<input type="text" name="familymembers[1].name" value="${guaranorProfile.familymembers[1].name}" maxlength="10" />
						</td>
						<td align="center">
							<input id="familymembers1BorrowerreRationship" type="text" name="familymembers[1].borrowerreRationship" value="${guaranorProfile.familymembers[1].borrowerreRationship}" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[1].age" value="${guaranorProfile.familymembers[1].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input id="familymembers1Profession" type="text" name="familymembers[1].profession" value="${guaranorProfile.familymembers[1].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[1].professionDetail" value="${guaranorProfile.familymembers[1].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[1].telphone" value="${guaranorProfile.familymembers[1].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="familymembers[2].borrowerServiceAppId" value="${guaranorProfile.familymembers[2].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[2].familyMemberId" value="${guaranorProfile.familymembers[2].familyMemberId}" />
							<input type="text" name="familymembers[2].name" value="${guaranorProfile.familymembers[2].name}" maxlength="10" />
						</td>
						<td align="center">
							<input id="familymembers2BorrowerreRationship" type="text" name="familymembers[2].borrowerreRationship" value="${guaranorProfile.familymembers[2].borrowerreRationship}" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[2].age" value="${guaranorProfile.familymembers[2].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input id="familymembers2Profession" type="text" name="familymembers[2].profession" value="${guaranorProfile.familymembers[2].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[2].professionDetail" value="${guaranorProfile.familymembers[2].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[2].telphone" value="${guaranorProfile.familymembers[2].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="familymembers[3].borrowerServiceAppId" value="${guaranorProfile.familymembers[3].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[3].familyMemberId" value="${guaranorProfile.familymembers[3].familyMemberId}" />
							<input type="text" name="familymembers[3].name" value="${guaranorProfile.familymembers[3].name}" maxlength="10" />
						</td>
						<td align="center">
							<input id="familymembers3BorrowerreRationship" type="text" name="familymembers[3].borrowerreRationship" value="${guaranorProfile.familymembers[3].borrowerreRationship}" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[3].age" value="${guaranorProfile.familymembers[3].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input id="familymembers3Profession" type="text" name="familymembers[3].profession" value="${guaranorProfile.familymembers[3].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[3].professionDetail" value="${guaranorProfile.familymembers[3].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[3].telphone" value="${guaranorProfile.familymembers[3].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="familymembers[4].borrowerServiceAppId" value="${guaranorProfile.familymembers[4].borrowerServiceAppId}" />
							<input type="hidden" name="familymembers[4].familyMemberId" value="${guaranorProfile.familymembers[4].familyMemberId}" />
							<input type="text" name="familymembers[4].name" value="${guaranorProfile.familymembers[4].name}" maxlength="10" />
						</td>
						<td align="center">
							<input id="familymembers4BorrowerreRationship" type="text" name="familymembers[4].borrowerreRationship" value="${guaranorProfile.familymembers[4].borrowerreRationship}" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[4].age" value="${guaranorProfile.familymembers[4].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input id="familymembers4Profession" type="text" name="familymembers[4].profession" value="${guaranorProfile.familymembers[4].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[4].professionDetail" value="${guaranorProfile.familymembers[4].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input type="text" name="familymembers[4].telphone" value="${guaranorProfile.familymembers[4].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
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

				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
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
							<input name="jobInfos[0].company" type="text" value="${guaranorProfile.jobInfos[0].company}" class="easyui-validatebox" maxlength="20" />
						</td>
						<td align="center">
							<input name="jobInfos[0].workunitAddress" value="${guaranorProfile.jobInfos[0].workunitAddress}" class="easyui-validatebox" maxlength="30" />
						</td>
						<td align="center">
							<input name="jobInfos[0].years" type="text" value="${guaranorProfile.jobInfos[0].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input name="jobInfos[0].post" type="text" value="${guaranorProfile.jobInfos[0].post}" class="easyui-validatebox" maxlength="10" />
						</td>
						<td align="center">
							<input name="jobInfos[0].income" type="text" value="${guaranorProfile.jobInfos[0].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="jobInfos[1].borrowerServiceAppId" value="${guaranorProfile.jobInfos[1].borrowerServiceAppId}" />
							<input type="hidden" name="jobInfos[1].jobInfoId" value="${guaranorProfile.jobInfos[0].jobInfoId}" />
							<input name="jobInfos[1].company" type="text" value="${guaranorProfile.jobInfos[1].company}" maxlength="20" />
						</td>
						<td align="center">
							<input name="jobInfos[1].workunitAddress" type="text" value="${guaranorProfile.jobInfos[1].workunitAddress}" maxlength="30" />
						</td>
						<td align="center">
							<input name="jobInfos[1].years" type="text" value="${guaranorProfile.jobInfos[1].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input name="jobInfos[1].post" type="text" value="${guaranorProfile.jobInfos[1].post}" maxlength="10" />
						</td>
						<td align="center">
							<input name="jobInfos[1].income" type="text" value="${guaranorProfile.jobInfos[1].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							&nbsp;
						</td>
					</tr>
				</table>

				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
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
							<input name="surveybusinessinfos[0].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[0].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[0].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[0].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input id="surveybusinessinfos0State" name="surveybusinessinfos[0].state" type="text" value="${guaranorProfile.surveybusinessinfos[0].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[0].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[0].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
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
							<input name="surveybusinessinfos[1].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[1].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[1].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[1].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input id="surveybusinessinfos1State" name="surveybusinessinfos[1].state" type="text" value="${guaranorProfile.surveybusinessinfos[1].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[1].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[1].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input id="surveybusinessinfos2OperatingItems" name="surveybusinessinfos[2].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[2].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input type="hidden" name="surveybusinessinfos[2].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[2].borrowerServiceAppId}" />
							<input type="hidden" name="surveybusinessinfos[2].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[2].surveyBusinessInfoId}" />
							<input name="surveybusinessinfos[2].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[2].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[2].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[2].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input id="surveybusinessinfos2State" name="surveybusinessinfos[2].state" type="text" value="${guaranorProfile.surveybusinessinfos[2].state}" />
						</td>
						<td align="center">
							<input name="surveybusinessinfos[2].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[2].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
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
									<span id="areaOne" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGuaranteeDetails' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGuaranteeDetails', 'areaOneS', 100);" name='otherQuestions.guaranteeDetails' rows='2' style='width: 757px;' maxlength='100'>${guaranorProfile.otherQuestions.guaranteeDetails }</textarea> </span>
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
									<span id="areaTwo" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsGamblingSpouse' name='otherQuestions.gamblingSpouse' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGamblingSpouse', 'areaTwoS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.gamblingSpouse }</textarea> </span>
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
									<span id="areaThree" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHelpDetails' name='otherQuestions.helpDetails' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHelpDetails', 'areaThreeS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.helpDetails}</textarea> </span>
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
									<span id="areaFour" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsHowEnough' name='otherQuestions.howEnough' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHowEnough', 'areaFourS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.howEnough}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaFourS"></span>
						</td>
					</tr>
					<tr>
						<td width="400" align="left">
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
									<span id="areaFive" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='otherQuestionsSpouseExplanation' name='otherQuestions.spouseExplanation' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsSpouseExplanation', 'areaFiveS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.spouseExplanation}</textarea> </span>
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

