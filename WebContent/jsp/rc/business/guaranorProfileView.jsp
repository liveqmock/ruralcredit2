<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<script type="text/javascript" src="<%=basePath%>scripts/business/guaranorProfile.js"></script>
		<style type="text/css">
			input {
				border-left: 0;
	   			border-right: 0;
	   			border-top: 0;
	   			border-bottom: 1px;
	   			background:white;
	   			text-align: inherit;
   			}
		</style>
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
				//provinceShowPublic("provinceHomeG", "cityHomeG", "countyHomeG");
				provinceShowG("provinceHomeG", "cityHomeG", "countyHomeG", "townHomeG", "villageHomeG", "homeAddressG", "detailHomeG");
				provinceShowG("provinceBusinessG", "cityBusinessG", "countyBusinessG", "townBusinessG", "villageBusinessG", "businessAddressG", "detailBusinessG");
	
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
			$("#guaranorProfileForm").form('validate');
		
			 $("#borrowerServiceName").focus();
			});
		</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">
			<form id="guaranorProfileForm" method="post" novalidate>
            <table id="focusTable" fit="true" style="width: 100%;" align="center">

                <c:choose>
                <c:when test="${not empty showAttach}">
                <tr align="center" width="100%">
                    <td align="right" width="56%">
                        <span style="font-size: 25px; font-weight: 700; color: #4B0082;">担保人信息表</span>
                    </td>
                    <td align="right" nowrap="nowrap" valign="top">
                        <a style="color: #0000ff;cursor: pointer;" onclick="viewguarantorAttach('${orderNum}');">查看担保人${orderNum}附件</a>
                    </td>
                </tr>
                <tr align="center" width="100%">
                    <td align="center" width="100%" colspan="2">
                        </c:when>
                        <c:otherwise>
                <tr align="center" width="100%">
                    <td align="center" width="56%">
                        <span style="font-size: 25px; font-weight: 700; color: #4B0082;">担保人信息表</span>
                    </td>
                <tr align="center" width="100%">
                    <td align="center" width="100%">
                        </c:otherwise>
                        </c:choose>
                        <span style="font-size: 15px; font-weight: 700;">业务编号：${guaranorProfile.borrowerService.businessNumber}</span>
                        <input readonly="readonly" id="borrowerServiceCreditapplicationId"
                               name="borrowerService.creditapplicationId"
                               value="${guaranorProfile.borrowerService.creditapplicationId}" type="hidden"
                               readonly="readonly"/>
                    </td>
                </tr>


            </table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="5" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td colspan="5" align="center">

							<input readonly="readonly" name="borrowerService.customerBasicId" type="hidden" value="${guaranorProfile.borrowerService.customerBasicId}" />
							<input readonly="readonly" id="borrowerServiceBorrowerServiceAppId" name="borrowerService.borrowerServiceAppId" type="hidden" value="${guaranorProfile.borrowerService.borrowerServiceAppId}" />
						</td>
					</tr>
					<tr>
						<td width="150" align="right">
							姓名：
						</td>
						<td width="250">
							<input readonly="readonly" id="borrowerServiceName" type="text" name="borrowerService.name" value="${guaranorProfile.borrowerService.name}" style="width: 150px;" class="easyui-validatebox" required="true" maxlength="10" />
						</td>

						<td width="150" align="right">
							民族：
						</td>
						<td width="250">
							<input readonly="readonly" type="text" value="${guaranorProfile.borrowerService.nationality}" style="width: 154px;" maxlength="10" />
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
							<input readonly="readonly" id="borrowerServiceCredentialsNumber" type="text" readonly="true" name="borrowerService.credentialsNumber" value="${guaranorProfile.borrowerService.credentialsNumber}" style="width: 150px;" class="easyui-validatebox" required="true" />
						</td>

						<td align="right">
							曾用名:
						</td>
						<td>
							<input readonly="readonly" type="text" name="borrowerService.former" value="${guaranorProfile.borrowerService.former}" style="width: 150px;" maxlength="10" />
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
							<input readonly="readonly" id="borrowerServiceBirthday" type="text" name="borrowerService.birthday" value="${guaranorProfile.borrowerService.birthday}" style="width: 150px;" readonly="true" />
						</td>

						<td align="right">
							性别：
						</td>
						<td>
							<c:if test="${guaranorProfile.borrowerService.gender=='1'}">
								<input readonly="readonly" value="女" type="text" readonly="readonly" style="width: 150px;" />
							</c:if>
							<c:if test="${guaranorProfile.borrowerService.gender=='0'}">
								<input readonly="readonly" value="男" type="text" readonly="readonly" style="width: 150px;" />
							</c:if>
							<input readonly="readonly" id="borrowerServiceGender" name="borrowerService.gender" value="${guaranorProfile.borrowerService.gender}" type="hidden" style="width: 150px;" required="true" />
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
							<input readonly="readonly" readonly="readonly" value="${guaranorProfile.borrowerService.maritalStatus}" type="text" style="width: 154px;" required="true" />
						</td>

						<td align="right">
							就业状况：
						</td>
						<td>
							<input readonly="readonly" type="text" value="${guaranorProfile.borrowerService.jobStatus}" style="width: 154px;" required="true" />
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
							<input readonly="readonly" name="borrowerService.mobilephone" type="text" value="${guaranorProfile.borrowerService.mobilephone}" style="width: 150px;" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
						</td>

						<td align="right">
							办公电话：
						</td>
						<td>
							<input readonly="readonly" name="borrowerService.officePhone" type="text" value="${guaranorProfile.borrowerService.officePhone}" style="width: 150px;" class="easyui-validatebox" validType="phoneNumber"  maxlength="11" />
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
							<input readonly="readonly" id="borrowerServiceBondsmanBorrower" name="borrowerService.bondsmanBorrower" type="text" value="${guaranorProfile.borrowerService.bondsmanBorrower}" style="width: 154px;" required="true" />
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
								<textarea readonly="readonly"  name="borrowerService.homeAddress" rows="3" cols="6" readonly="readonly" id="homeAddressG" style="width: 150px;">${guaranorProfile.borrowerService.homeAddress }</textarea>
							</p>
						</td>
						<td align="right" valign="top">
							现经营场所地址：
						</td>
						<td valign="top">
							<p>
								<textarea readonly="readonly"  name="borrowerService.residenceAddress" rows="3" cols="6" readonly="readonly" id="businessAddressG" style="width: 150px;">${guaranorProfile.borrowerService.residenceAddress }</textarea>
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
											<input readonly="readonly" id="homeA0" type="checkbox" disabled="disabled" name="borrowerService.livingCommercial" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input readonly="readonly" id="homeA0" type="checkbox" disabled="disabled" name="borrowerService.livingCommercial" value="1" />
										</c:otherwise>
									</c:choose>
									自有商品房
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingSelf==1}">
											<input readonly="readonly" id="homeA1" type="checkbox" disabled="disabled" name="borrowerService.livingSelf" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input readonly="readonly" id="homeA1" type="checkbox" disabled="disabled" name="borrowerService.livingSelf" value="1" />
										</c:otherwise>
									</c:choose>
									自有宅基地
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingRent==1}">
											<input readonly="readonly" id="homeA2" type="checkbox" disabled="disabled" name="borrowerService.livingRent" checked="checked" value="1" onclick="showLivingDate(this)" />
										</c:when>
										<c:otherwise>
											<input readonly="readonly" id="homeA2" type="checkbox" disabled="disabled" name="borrowerService.livingRent" value="1" onclick="showLivingDate(this)" />
										</c:otherwise>
									</c:choose>
									租住（到期日
								</label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.livingRent==1}">
										<span id="livingDateG"><input readonly="readonly" id="borrowerServiceLivingDate" type="text" name="borrowerService.livingDate" value="<fmt:formatDate value='${guaranorProfile.borrowerService.livingDate}' pattern='yyyy-MM-dd' />" editable="false" required="true" /> </span>
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
											<input readonly="readonly" id="homeA3" type="checkbox" disabled="disabled" disabled="disabled" name="borrowerService.livingRelative" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input readonly="readonly" id="homeA3" type="checkbox" disabled="disabled" name="borrowerService.livingRelative" value="1" />
										</c:otherwise>
									</c:choose>
									亲戚住房
								</label>
								<br />
								<label>
									<c:choose>
										<c:when test="${guaranorProfile.borrowerService.livingOther==1}">
											<input readonly="readonly" id="homeA4" type="checkbox" disabled="disabled" name="borrowerService.livingOther" checked="checked" value="1" />
										</c:when>
										<c:otherwise>
											<input readonly="readonly" id="homeA4" type="checkbox" disabled="disabled" name="borrowerService.livingOther" value="1" />
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
										<input readonly="readonly" id="businessA0" type="checkbox" disabled="disabled" name="borrowerService.businessCommercial" checked="checked" value="1" onclick="checkBusiness()"/>
									</c:when>
									<c:otherwise>
										<input readonly="readonly" id="businessA0" type="checkbox" disabled="disabled" name="borrowerService.businessCommercial" value="1" onclick="checkBusiness()"/>
									</c:otherwise>
								</c:choose>
								自有商品房
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessSelf==1}">
										<input readonly="readonly" id="businessA1" type="checkbox" disabled="disabled" name="borrowerService.businessSelf" checked="checked" value="1" onclick="checkBusiness()"/>
									</c:when>
									<c:otherwise>
										<input readonly="readonly" id="businessA1" type="checkbox" disabled="disabled" name="borrowerService.businessSelf" value="1" onclick="checkBusiness()"/>
									</c:otherwise>
								</c:choose>
								自有宅基地
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessRent==1}">
										<input readonly="readonly" id="businessA2" type="checkbox" disabled="disabled" name="borrowerService.businessRent" checked="checked" value="1" onclick="showBusinessDate(this)" />
									</c:when>
									<c:otherwise>
										<input readonly="readonly" id="businessA2" type="checkbox" disabled="disabled" name="borrowerService.businessRent" value="1" onclick="showBusinessDate(this)" />
									</c:otherwise>
								</c:choose>
								租住（到期日
							</label>
							<c:choose>
								<c:when test="${guaranorProfile.borrowerService.businessRent==1}">
									<span id="businessDateG"><input readonly="readonly" type="text" name="borrowerService.businessDate" value="<fmt:formatDate value='${guaranorProfile.borrowerService.businessDate}' pattern='yyyy-MM-dd' />" /> ）</span>
								</c:when>
								<c:otherwise>
									<span id="businessDateG"></span>
								</c:otherwise>
							</c:choose>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessRelative==1}">
										<input readonly="readonly" id="businessA3" type="checkbox" disabled="disabled" name="borrowerService.businessRelative" checked="checked" value="1" onclick="checkBusiness()" />
									</c:when>
									<c:otherwise>
										<input readonly="readonly" id="businessA3" type="checkbox" disabled="disabled" name="borrowerService.businessRelative" value="1" onclick="checkBusiness()" />
									</c:otherwise>
								</c:choose>
								亲戚住房
							</label>
							<br />
							<label>
								<c:choose>
									<c:when test="${guaranorProfile.borrowerService.businessOther==1}">
										<input readonly="readonly" id="businessA4" type="checkbox" disabled="disabled" name="borrowerService.businessOther" checked="checked" value="1" onclick="checkBusiness()" />
									</c:when>
									<c:otherwise>
										<input readonly="readonly" id="businessA4" type="checkbox" disabled="disabled" name="borrowerService.businessOther" value="1" onclick="checkBusiness()" />
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
							<input readonly="readonly" id="borrowerServiceLivingArea" type="text" name="borrowerService.livingArea" value="${guaranorProfile.borrowerService.livingArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="5" />
						</td>

						<td align="right">
							经营场所面积：
						</td>
						<td>
							<input readonly="readonly" id="borrowerServiceBusinessArea" type="text" name="borrowerService.businessArea" value="${guaranorProfile.borrowerService.businessArea}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="5" />
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
							<input readonly="readonly" id="borrowerServiceLivingYear" type="text" name="borrowerService.livingYear" value="${guaranorProfile.borrowerService.livingYear}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="5" />
						</td>

						<td align="right">
							经营场所年限：
						</td>
						<td>
							<input readonly="readonly" id="borrowerServiceBusinessYear" type="text" name="borrowerService.businessYear" value="${guaranorProfile.borrowerService.businessYear}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="5" />
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
      <td><input readonly  value="${guaranorProfile.familymembers[0].name }"   class="easyui-validatebox"   name="familymembers[0].name" maxlength="10" /></td>
      <td><input readonly name="familymembers[0].borrowerreRationship" id="familymembers0BorrowerreRationship" value="${guaranorProfile.familymembers[0].borrowerreRationship}"   size="15" hasDownArrow="false"/></td>
      <td><input readonly  value="${guaranorProfile.familymembers[0].idNumber }"   name="familymembers[0].idNumber" id="IdNumber" class="easyui-validatebox" validType="idnumberAll" size="25" onblur="familyIdNumber();"/></td>
      <td><input readonly  value="${guaranorProfile.familymembers[0].age }"   readonly="readonly" class="easyui-numberbox" id="familymemberListAge"  name="familymembers[0].age"   validType="number" maxlength="3" size="15"/></td>
      <td><input readonly  value="${guaranorProfile.familymembers[0].profession }"  required="true"    name="familymembers[0].profession" id="familymembers0Profession" size="15"/></td>
      <td><input readonly  value="${guaranorProfile.familymembers[0].professionDetail }"  required="true" class="easyui-validatebox"   name="familymembers[0].professionDetail" maxlength="20"/></td>
      <td><input readonly  value="${guaranorProfile.familymembers[0].telphone }"  required="true" class="easyui-validatebox"   name="familymembers[0].telphone" validType="phoneNumber" maxlength="11"/>
      		<input  value="1" name="familymembers[0].seq"   type="hidden"  />
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
						<td colspan="7" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							姓名
						</td>
						<td align="center">
							与担保人关系   
						</td>
						<td align="center">
							身份证号   
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
								<input readonly="readonly" type="hidden" name="familymembers[${status.index}].borrowerServiceAppId" value="${f.borrowerServiceAppId}" />
								<input readonly="readonly" type="text" name="familymembers[${status.index}].name" value="${f.name}" />
							</td>
							<td align="center">
								<input readonly="readonly" type="text" name="familymembers[${status.index}].borrowerreRationship" value="${f.borrowerreRationship}" />
							</td>
							<td align="center">
								<input readonly="readonly" type="text" name="familymembers[${status.index}].age" value="${f.age}" />
							</td>
							<td align="center">
								<input readonly="readonly" type="text" name="familymembers[${status.index}].profession" value="${f.profession}" />
							</td>
							<td align="center">
								<input readonly="readonly" type="text" name="familymembers[${status.index}].telphone" value="${f.telphone}" />
							</td>
						</tr>
					</c:forEach>--%>

					<%--<tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="familymembers[0].borrowerServiceAppId" value="${guaranorProfile.familymembers[0].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="familymembers[0].familyMemberId" value="${guaranorProfile.familymembers[0].familyMemberId}" />
							<input readonly="readonly" type="text" name="familymembers[0].name" value="${guaranorProfile.familymembers[0].name}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly"  type="text" name="familymembers[0].borrowerreRationship" value="${guaranorProfile.familymembers[0].borrowerreRationship}" required="true" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[0].age" value="${guaranorProfile.familymembers[0].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" required="true" maxlength="3" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers0Profession" type="text" name="familymembers[0].profession" value="${guaranorProfile.familymembers[0].profession}" required="true" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[0].professionDetail" value="${guaranorProfile.familymembers[0].professionDetail}" class="easyui-validatebox" required="true" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[0].telphone" value="${guaranorProfile.familymembers[0].telphone}" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
						</td>
					</tr>
					--%><tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="familymembers[1].borrowerServiceAppId" value="${guaranorProfile.familymembers[1].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="familymembers[1].familyMemberId" value="${guaranorProfile.familymembers[1].familyMemberId}" />
							<input readonly="readonly" type="text" name="familymembers[1].name" value="${guaranorProfile.familymembers[1].name}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers1BorrowerreRationship" type="text" name="familymembers[1].borrowerreRationship" value="${guaranorProfile.familymembers[1].borrowerreRationship}" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[1].idNumber" value="${guaranorProfile.familymembers[1].idNumber}" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[1].age" value="${guaranorProfile.familymembers[1].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers1Profession" type="text" name="familymembers[1].profession" value="${guaranorProfile.familymembers[1].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[1].professionDetail" value="${guaranorProfile.familymembers[1].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[1].telphone" value="${guaranorProfile.familymembers[1].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="familymembers[2].borrowerServiceAppId" value="${guaranorProfile.familymembers[2].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="familymembers[2].familyMemberId" value="${guaranorProfile.familymembers[2].familyMemberId}" />
							<input readonly="readonly" type="text" name="familymembers[2].name" value="${guaranorProfile.familymembers[2].name}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers2BorrowerreRationship" type="text" name="familymembers[2].borrowerreRationship" value="${guaranorProfile.familymembers[2].borrowerreRationship}" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[2].idNumber" value="${guaranorProfile.familymembers[2].idNumber}"  onkeypress="if(event.which==45){return false;}" maxlength="18" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[2].age" value="${guaranorProfile.familymembers[2].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers2Profession" type="text" name="familymembers[2].profession" value="${guaranorProfile.familymembers[2].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[2].professionDetail" value="${guaranorProfile.familymembers[2].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[2].telphone" value="${guaranorProfile.familymembers[2].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="familymembers[3].borrowerServiceAppId" value="${guaranorProfile.familymembers[3].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="familymembers[3].familyMemberId" value="${guaranorProfile.familymembers[3].familyMemberId}" />
							<input readonly="readonly" type="text" name="familymembers[3].name" value="${guaranorProfile.familymembers[3].name}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers3BorrowerreRationship" type="text" name="familymembers[3].borrowerreRationship" value="${guaranorProfile.familymembers[3].borrowerreRationship}" />
						</td>
						<td align="center">
							<input readonly="readonly"  type="text" name="familymembers[3].idNumber" value="${guaranorProfile.familymembers[3].idNumber}" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[3].age" value="${guaranorProfile.familymembers[3].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers3Profession" type="text" name="familymembers[3].profession" value="${guaranorProfile.familymembers[3].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[3].professionDetail" value="${guaranorProfile.familymembers[3].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[3].telphone" value="${guaranorProfile.familymembers[3].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="familymembers[4].borrowerServiceAppId" value="${guaranorProfile.familymembers[4].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="familymembers[4].familyMemberId" value="${guaranorProfile.familymembers[4].familyMemberId}" />
							<input readonly="readonly" type="text" name="familymembers[4].name" value="${guaranorProfile.familymembers[4].name}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers4BorrowerreRationship" type="text" name="familymembers[4].borrowerreRationship" value="${guaranorProfile.familymembers[4].borrowerreRationship}" />
						</td>
						<td align="center">
							<input readonly="readonly"  type="text" name="familymembers[4].idNumber" value="${guaranorProfile.familymembers[4].idNumber}" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[4].age" value="${guaranorProfile.familymembers[4].age}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="3" />
						</td>
						<td align="center">
							<input readonly="readonly" id="familymembers4Profession" type="text" name="familymembers[4].profession" value="${guaranorProfile.familymembers[4].profession}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[4].professionDetail" value="${guaranorProfile.familymembers[4].professionDetail}" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" type="text" name="familymembers[4].telphone" value="${guaranorProfile.familymembers[4].telphone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
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
							<input readonly="readonly" type="hidden" name="jobInfos[0].borrowerServiceAppId" value="${guaranorProfile.jobInfos[0].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="jobInfos[0].jobInfoId" value="${guaranorProfile.jobInfos[0].jobInfoId}" />
							<input readonly="readonly" name="jobInfos[0].company" type="text" value="${guaranorProfile.jobInfos[0].company}" class="easyui-validatebox" maxlength="20" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[0].workunitAddress" value="${guaranorProfile.jobInfos[0].workunitAddress}" class="easyui-validatebox" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[0].years" type="text" value="${guaranorProfile.jobInfos[0].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[0].post" type="text" value="${guaranorProfile.jobInfos[0].post}" class="easyui-validatebox" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[0].income" type="text" value="${guaranorProfile.jobInfos[0].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input readonly="readonly" type="hidden" name="jobInfos[1].borrowerServiceAppId" value="${guaranorProfile.jobInfos[1].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="jobInfos[1].jobInfoId" value="${guaranorProfile.jobInfos[1].jobInfoId}" />
							<input readonly="readonly" name="jobInfos[1].company" type="text" value="${guaranorProfile.jobInfos[1].company}" maxlength="20" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[1].workunitAddress" type="text" value="${guaranorProfile.jobInfos[1].workunitAddress}" maxlength="30" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[1].years" type="text" value="${guaranorProfile.jobInfos[1].years}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="4" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[1].post" type="text" value="${guaranorProfile.jobInfos[1].post}" maxlength="10" />
						</td>
						<td align="center">
							<input readonly="readonly" name="jobInfos[1].income" type="text" value="${guaranorProfile.jobInfos[1].income}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
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
	
							<input readonly="readonly" id="surveybusinessinfos0OperatingItems" name="surveybusinessinfos[0].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[0].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[0].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[0].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[0].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[0].surveyBusinessInfoId}" />
							<input readonly="readonly" name="surveybusinessinfos[0].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[0].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[0].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[0].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input readonly="readonly" id="surveybusinessinfos0State" name="surveybusinessinfos[0].state" type="text" value="${guaranorProfile.surveybusinessinfos[0].state}" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[0].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[0].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input readonly="readonly" id="surveybusinessinfos1OperatingItems" name="surveybusinessinfos[1].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[1].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[1].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[1].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[1].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[1].surveyBusinessInfoId}" />
							<input readonly="readonly" name="surveybusinessinfos[1].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[1].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[1].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[1].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input readonly="readonly" id="surveybusinessinfos1State" name="surveybusinessinfos[1].state" type="text" value="${guaranorProfile.surveybusinessinfos[1].state}" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[1].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[1].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
					</tr>
					<tr>
						<%--<td align="center">
	
							<input readonly="readonly" id="surveybusinessinfos2OperatingItems" name="surveybusinessinfos[2].operatingItems" type="text" value="${guaranorProfile.surveybusinessinfos[2].operatingItems}" />
						</td>
						--%>
						<td align="center">
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[2].borrowerServiceAppId" value="${guaranorProfile.surveybusinessinfos[2].borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="surveybusinessinfos[2].surveyBusinessInfoId" value="${guaranorProfile.surveybusinessinfos[2].surveyBusinessInfoId}" />
							<input readonly="readonly" name="surveybusinessinfos[2].operatingItemsDetail" type="text" value="${guaranorProfile.surveybusinessinfos[2].operatingItemsDetail}" maxlength="20" style="width: 300px;" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[2].startingDate" type="text" value="${guaranorProfile.surveybusinessinfos[2].startingDate}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4" />
						</td>
						<td align="center">
							<input readonly="readonly" id="surveybusinessinfos2State" name="surveybusinessinfos[2].state" type="text" value="${guaranorProfile.surveybusinessinfos[2].state}" />
						</td>
						<td align="center">
							<input readonly="readonly" name="surveybusinessinfos[2].monthGrossIncome" type="text" value="${guaranorProfile.surveybusinessinfos[2].monthGrossIncome}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
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
							<input readonly="readonly" type="hidden" name="otherQuestions.borrowerServiceAppId" value="${guaranorProfile.otherQuestions.borrowerServiceAppId}" />
							<input readonly="readonly" type="hidden" name="otherQuestions.otherQuestionsId" value="${guaranorProfile.otherQuestions.otherQuestionsId}" />
							<input readonly="readonly" id="otherQuestionsGuarantee" type="text" name="otherQuestions.guarantee" value="${guaranorProfile.otherQuestions.guarantee}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.guarantee=='是'}">
									<span id="areaOne"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsGuaranteeDetails' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGuaranteeDetails', 'areaOneS', 100);" name='otherQuestions.guaranteeDetails' rows='2' style='width: 757px;' maxlength='100'>${guaranorProfile.otherQuestions.guaranteeDetails }</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsGuaranteeDetails' onclick="textCount('otherQuestionsGuaranteeDetails', 'areaOneS', 100);" name='otherQuestions.guaranteeDetails' rows='2' style='width: 757px;' maxlength='100'>${guaranorProfile.otherQuestions.guaranteeDetails }</textarea> </span>
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
							<input readonly="readonly" id="otherQuestionsHowLong" type="text" name="otherQuestions.howLong" value="${guaranorProfile.otherQuestions.howLong}" style="width: 154px;" required="true" />
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
							<input readonly="readonly" id="otherQuestionsGambling" type="text" name="otherQuestions.gambling" value="${guaranorProfile.otherQuestions.gambling}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.gambling=='有'}">
									<span id="areaTwo"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsGamblingSpouse' name='otherQuestions.gamblingSpouse' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsGamblingSpouse', 'areaTwoS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.gamblingSpouse }</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsGamblingSpouse' name='otherQuestions.gamblingSpouse' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsGamblingSpouse', 'areaTwoS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.gamblingSpouse }</textarea> </span>
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
							<input readonly="readonly" id="otherQuestionsHelp" type="text" name="otherQuestions.help" value="${guaranorProfile.otherQuestions.help}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.help=='有'}">
									<span id="areaThree"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsHelpDetails' name='otherQuestions.helpDetails' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHelpDetails', 'areaThreeS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.helpDetails}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaThree" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsHelpDetails' name='otherQuestions.helpDetails' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsHelpDetails', 'areaThreeS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.helpDetails}</textarea> </span>
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
							<input readonly="readonly" id="otherQuestionsBorrowAmount" type="text" name="otherQuestions.borrowAmount" value="${guaranorProfile.otherQuestions.borrowAmount}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" required="true" maxlength="10" />
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
							<input readonly="readonly" id="otherQuestionsGuaranteeAmount" type="text" name="otherQuestions.guaranteeAmount" value="${guaranorProfile.otherQuestions.guaranteeAmount}" style="width: 150px;" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" required="true" maxlength="10" />
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
							<input readonly="readonly" readonly="readonly" id="otherQuestionsEnoughDeposits" type="text" name="otherQuestions.enoughDeposits" value="${guaranorProfile.otherQuestions.enoughDeposits}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.enoughDeposits=='否'}">
									<span id="areaFour"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsHowEnough' name='otherQuestions.howEnough' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsHowEnough', 'areaFourS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.howEnough}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaFour" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsHowEnough' name='otherQuestions.howEnough' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsHowEnough', 'areaFourS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.howEnough}</textarea> </span>
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
							<input readonly="readonly" id="otherQuestionsSpouseInformed" type="text" name="otherQuestions.spouseInformed" value="${guaranorProfile.otherQuestions.spouseInformed}" style="width: 154px;" required="true" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<c:choose>
								<c:when test="${guaranorProfile.otherQuestions.spouseInformed=='否'}">
									<span id="areaFive"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly" id='otherQuestionsSpouseExplanation' name='otherQuestions.spouseExplanation' rows='2' style='width: 757px;' class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('otherQuestionsSpouseExplanation', 'areaFiveS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.spouseExplanation}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaFive" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea readonly="readonly"  id='otherQuestionsSpouseExplanation' name='otherQuestions.spouseExplanation' rows='2' style='width: 757px;' onclick="textCount('otherQuestionsSpouseExplanation', 'areaFiveS', 100);" maxlength='100'>${guaranorProfile.otherQuestions.spouseExplanation}</textarea> </span>
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

