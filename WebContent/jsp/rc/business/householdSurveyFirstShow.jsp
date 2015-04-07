<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script type="text/javascript">
		 var serverName="<%=path%>";
		$(document).ready(function() {
		//	$("td").attr('style', 'font-size: 12; color: navy;');
		 	$(".panel-header").attr('style', "height:10px;");
		});

		</script>
		<script type="text/javascript" charset="utf-8"
			src="<%=basePath%>scripts/business/householdSurveyFirstShow.js"></script>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center" >
			<div id="borrowerServiceApp">
				<!-- 小组信息 -->
				<form id="borrowerServiceAppForm">
					<!-- 借款服务申请 -->
					<div class="easyui-panel" title="您的借款需求"
						style="border: 0px; margin: 0px; width: 100% px; padding: 10px;">
						<table width="98%">
							<tr>
								<td align="right">
									申请日期：
								</td>
								<td align="left">
									<input readonly="readonly"   
									 style="border: 0px;width: 100%;"
									id="applyDate" name="borrowerServiceApp.applyDate" />
								</td>
								<td align="right">
									借款用途：
								</td>
								<td align="left">
									<input readonly="readonly"  
										 style="border: 0px;width: 100%;"
									  id="detailsLoansuseType" name="borrowerServiceApp.detailsLoansuseType" />
								</td>

								<td align="right">
									咨询来源：
								</td>
								<td align="left">
									<input readonly="readonly"  
										 style="border: 0px;width: 100%;"
									  name="borrowerServiceApp.consulteSource" id="consulteSource" />
								</td>

							</tr>
							<tr>

								<td align="right">
									还款来源：
								</td>
								<td align="left">
									<input readonly="readonly"  
										 style="border: 0px;width: 100%;"
									  name="borrowerServiceApp.paymentSource" />
								</td>
								<td align="right">
									详细借款用途：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 id="detailUse" name="borrowerServiceApp.detailsLoansUse" />
								</td>
								<td align="right">
									详细咨询来源：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.detailsConsulteSource" id="detailsConsulteSource" />
								</td>
								<td>
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.creditapplicationId"
										id="creditapplicationIdForeign"
										value="${creditApplication.creditapplicationId}" type="hidden" />
								</td>
							</tr>
							<tr>
								<td align="right">
									申请借款额度：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 id="applyLimit" name="borrowerServiceApp.applyLimit"
										/>
								</td>
							</tr>
						</table>

					</div>
						<div class="easyui-panel"  title="您的个人资料""
						style="border: 0px; margin: 0px; width: 100% px; padding: 10px;">
						<table width="98%">
							<tr>
								<td align="right">
									姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.name" id="name" type="text"
									value="${borrowerServiceApp.name}"
										/>
								</td>
								<td align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;龄：
								</td>
								<td align="left">
									<input readonly="readonly"  
										 style="border: 0px;width: 100%;"
									name="borrowerServiceApp.age" id="age"  
										value="${borrowerServiceApp.age }" type="text"
										readonly="readonly"   />
								</td>

								<td align="right">
									联系电话：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.mobilephone" style="width: 100%"
										value="${borrowerServiceApp.mobilephone}"
										/>
								</td>

							</tr>
							<tr>

								<td align="right">
									身份证号码：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									  
										value="${borrowerServiceApp.credentialsNumber}"
										name="borrowerServiceApp.credentialsNumber" id="credentialsNumber"
										style="width: 100%;"/>
								</td>
								<td align="right">
									性&nbsp;&nbsp;&nbsp;&nbsp;别：
								</td>
								<td align="left">
									<input readonly="readonly"  
									 style="border: 0px;width: 100%;"
									name="borrowerServiceApp.gender" id="gender"
									value="${borrowerServiceApp.gender}" />
								</td>
								<td align="right">
										曾&nbsp;用&nbsp;名：
								</td>
								<td align="left">
									<input readonly="readonly" 
										 style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.former" validType="length[0,32]"
										value="${borrowerServiceApp.former}"
										  type="text" style="width: 100%;" />
								</td>
							</tr>
							<tr>
								<td align="right">
									户籍详细地址：
								</td>
								<td align="left">
								<input readonly="readonly" 
									style="border: 0px;width: 100%;"
								 name="borrowerServiceApp.residenceAddress" type="text" style="width: 100%"
										id="residenceAddress"
										value="${borrowerServiceApp.residenceAddress}" />
								</td>
								<td align="right">
									家庭年收入：
								</td>
								<td align="left">
								<input readonly="readonly" 
									style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.familyIncome" type="text"  
										   
										style="width: 80%; align: left" />
								</td>
								<td align="right">
								居住面积：
								</td>
								<td align="left">
									<input readonly="readonly" 
										style="border: 0px;width: 100%;"
									 name="borrowerServiceApp.livingArea" type="text"  
										  style="width: 80%" />
								</td>
							</tr>
							<tr>
								<td align="right">
									居住状况：
								</td>
								<td colspan="5" align="left">
									<input readonly="readonly" disabled="disabled" name="borrowerServiceApp.livingCommercial" id="livingCommercial"
										type="checkbox" value="0" />
									自有商品房
									<input readonly="readonly" disabled="disabled"  name="borrowerServiceApp.livingSelf" id="livingSelf" type="checkbox"
										value="1" />
									自有宅基地
									<input readonly="readonly" disabled="disabled"  name="borrowerServiceApp.livingRent" id="livingRent" type="checkbox"
										value="2" onclick="showLivigDate(this);" />
									租住（到期日：<input id="rentdate" readonly="readonly" name="borrowerServiceApp.livingDate" required="true">
									）
									<input readonly="readonly" disabled="disabled"  name="borrowerServiceApp.livingRelative" id="livingRelative"
										type="checkbox" value="3" />
									亲属住房
									<input readonly="readonly" disabled="disabled" name="borrowerServiceApp.livingOther" id="livingOther" type="checkbox"
										value="4" />
									其他
								</td>
							</tr>
						</table>

					</div>
			<div class="easyui-panel" title="您的借款情况"
				style="width: 100% px; padding: 10px; border: 0px; margin: 0px;">
				<form id="borrowInfosForm">
					<table>
						<tr>
						</tr>
						<tr>
							<td width="80px">
								贷款机构
							</td>
							<td width="80px">
								发放日期
							</td>
							<td width="80px">
								到期日期
							</td>
							<td width="80px">
								授信额度
							</td>
							<td width="80px">
								使用额度
							</td>
							<td width="180px">
								账户状态
							</td>
							<td width="230px">
								还款方式
							</td>
							<td width="100px">
								年利率%
							</td>
							<td width="90px">
								每次还款金额
							</td>
							<td width="20px">

							</td>
						</tr>
						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"	
								 name="borrowInfos[0].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].releaseDate" type="text" type="text"
									 style="width: 100%" editable="false" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].expirationDate" type="text"
									type="text"  style="width: 100%"
									editable="false" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].creditLine" type="text"
									     
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].useCredit" type="text"
									     
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
							<td>
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[0].amountStatus" type="radio" value="0" />
								正常
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[0].amountStatus" type="radio" value="1" />
								结清
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[0].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input readonly="readonly" disabled="disabled" name="borrowInfos[0].repayWay" type="radio" value="0" />
								按季还息
								<input readonly="readonly" disabled="disabled" name="borrowInfos[0].repayWay" type="radio" value="1" />
								利随本清
								<input readonly="readonly" disabled="disabled" name="borrowInfos[0].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].interestRate" type="text"
									 />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].eachRepayAmount" type="text"
									     
									style="width: 100%" />
							</td>
							<td width="20px">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[0].borrowInfoId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  id="borrowerServiceAppId0"
									name="borrowInfos[0].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].releaseDate" type="text"
									 style="width: 100%" editable="false" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].expirationDate" type="text"
									 style="width: 100%" editable="false" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].creditLine" type="text"
									     
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].useCredit" type="text"
									     
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
							<td>
								<input readonly="readonly" disabled="disabled" name="borrowInfos[1].amountStatus" type="radio" value="0" />
								正常
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[1].amountStatus" type="radio" value="1" />
								结清
								<input readonly="readonly" disabled="disabled" name="borrowInfos[1].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[1].repayWay" type="radio" value="0" />
								按季还息
								<input readonly="readonly" disabled="disabled" name="borrowInfos[1].repayWay" type="radio" value="1" />
								利随本清
								<input readonly="readonly" disabled="disabled" name="borrowInfos[1].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].interestRate" type="text"
									    max=100
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].eachRepayAmount" type="text"
									     
									style="width: 100%" />
							</td>
							<td width="20px">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[1].borrowInfoId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  id="borrowerServiceAppId1"
									name="borrowInfos[1].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr>
							<td>

								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].releaseDate" type="text" type="text"
									editable="false"  style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].expirationDate" type="text"
									type="text" editable="false"  
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].creditLine" type="text"
									     
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].useCredit" type="text"
									     
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
							<td>
								<input readonly="readonly" disabled="disabled" name="borrowInfos[2].amountStatus" type="radio" value="0" />
								正常
								<input readonly="readonly" disabled="disabled" name="borrowInfos[2].amountStatus" type="radio" value="1" />
								结清
								<input readonly="readonly" disabled="disabled" name="borrowInfos[2].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input readonly="readonly" disabled="disabled"  name="borrowInfos[2].repayWay" type="radio" value="0" />
								按季还息
								<input readonly="readonly"  disabled="disabled" name="borrowInfos[2].repayWay" type="radio" value="1" />
								利随本清
								<input readonly="readonly" disabled="disabled" name="borrowInfos[2].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].interestRate" type="text"
									    max=100
									style="width: 100%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].eachRepayAmount" type="text"
									style="width: 100%" />
							</td>
							<td width="20px">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="borrowInfos[2].borrowInfoId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  id="borrowerServiceAppId2"
									name="borrowInfos[2].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr style="height: 20px;">
							<td>
							</td>
						</tr>
					</table>
				</form>
			<form id="creditCardInfoForm">
					<table>
						<tr>
							<td>
								<table width="95%">
									<tr>
										<td colspan="4" align="left">
											<font color="black" style="font-weight: bold;">信用卡使用情况:
											</font> 
											<input readonly="readonly" disabled="disabled" type="radio" name="creditCardInfo.creditCondition"
												value="0" />
											无 
											<input readonly="readonly" disabled="disabled"  type="radio" name="creditCardInfo.creditCondition"
												value="1" />
											有，未透支 
											<input readonly="readonly" disabled="disabled" type="radio" name="creditCardInfo.creditCondition"
												value="2" />
											有，透支
											<%--<a class="easyui-linkbutton" onClick="addRow();">加一行</a><a
												class="easyui-linkbutton" onClick="removeRow();">减一行</a>
										--%>
										</td>
										<td>
											<input readonly="readonly"  name="creditCardInfo.creditCardInfoId" type="hidden" />
										</td>
									</tr>
									<tr>
										<td width="150">
											发卡机构名称
										</td>
										<td width="150">
											开户日期
										</td>
										<td width="150">
											信用额度
										</td>
										<td width="150">
											已使用额度
										</td>


									</tr>
									<tr>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[0].creditCardIssue"
												type="text" style="width: 90%" class="eastui-validatebox"
												validType="length[0,32]" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[0].openingDate" type="text"
												editable="false"  style="width: 90%" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[0].creditLimit" type="text"
												style="width: 90%" />
										</td>

										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[0].creditUsedAmount"
												onblur="sumCreditCardChargeTotal();" type="text"
												style="width: 90%" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[0].creditOrgId" type="hidden" />
										</td>

									</tr>
									<tr>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[1].creditCardIssue"
												type="text" style="width: 90%" class="eastui-validatebox"
												validType="length[0,32]" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[1].openingDate" type="text"
												editable="false"  style="width: 90%" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[1].creditLimit" type="text"
												     
												style="width: 90%" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[1].creditUsedAmount"
												onblur="sumCreditCardChargeTotal();" type="text"
												     
												style="width: 90%" />
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditOrganization[1].creditOrgId" type="hidden" />

										</td>
									</tr>
								</table>
							</td>
							<td align="right">
								<table width="100%px" align="right">
									<tr>
										<td></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<font color="black" style="font-weight: bold;">信贷情况负债情况合计：</font> 
										</td>
									</tr>
									<tr>
										<td width="150" align="right">
											贷款负债合计：
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditCardInfo.loanChargeTotal"
												    type="text"
												id="loanChargeTotal" style="width: 70%" readonly="readonly"   />
											万元
										</td>
									</tr>
									<tr>
										<td align="right">
											信用卡负债合计：
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditCardInfo.creditCardChargeTotal"
												id="creditCardChargeTotal"  
												  type="text" style="width: 70%"
												readonly="readonly"   />
											万元
										</td>
									</tr>
									<tr>
										<td align="right">
											为他人贷款担保合计：
										</td>
										<td>
											<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="creditCardInfo.loanGuaranteesOther" type="text"
												style="width: 70%" />
											万元
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="9" align="left">
								<font color="black" style="font-weight: bold;">宜信借款情况：</font> 
								<input readonly="readonly"  disabled="disabled"  type="radio" onclick="validateTime();" value="0"
									name="creditCardInfo.creditBorrowCondition" />
								从未在宜信申请借款 
								<input readonly="readonly" disabled="disabled"  type="radio" onclick="validateTime();" value="1"
									name="creditCardInfo.creditBorrowCondition" />
								申请过未通过审核 
								<input readonly="readonly" disabled="disabled"  type="radio" onclick="validateTime();" value="2"
									name="creditCardInfo.creditBorrowCondition" />
								已在宜信借款
								 <u><input id='BorrowTime' readonly="readonly"  type='text' name='creditCardInfo.creditBorrowConditionTime'/></u>次
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  id="creditCardInfoborrowerServiceAppId"
									name="creditCardInfo.borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
					</table>
				</form>
				</div>
			<!-- 工作信息 -->
		 <form id="jobContact">
			<div class="easyui-panel" title="您的工作信息"
				style="width: 100% px; padding: 10px; border: 0px; margin: 0px;">
				<form id="jobInfoForm">
					<table width="98%" align="left">
						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.jobInfoId" type="hidden" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.borrowerServiceAppId"
									id="jobInfoborrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr>
							<td>
								工作单位及地址：
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.workunitAddress" type="text" style="width: 100%"
									class="eastui-validatebox" validType="length[0,32]" />
							</td>
							<td align="right">
								担任职务：
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.post" type="text" style="width: 100%"
									class="eastui-validatebox" validType="length[0,32]" />
							</td>

							<td align="right">
								单位电话：
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.workunitTelephone" type="text" style="width: 100%"
									 />
							</td>
							<td align="right">
								进入单位时间：
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.enterTime"  
									id="jobInfo.enterTime" style="width: 100%" editable="false" />
							</td>
							<td align="right">
								所在部门：
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="jobInfo.department" type="text" style="width: 100%"
									class="eastui-validatebox" validType="length[0,32]" />
							</td>
						</tr>
					</table>
			 
			</div>
			<!--<a class="easyui-linkbutton" href="javascript:jobInfoFormSubmit();">保存
			</a>
			 联系人 -->
			<div class="easyui-panel" title="您的联系人信息"
				style="width: 100% px; padding: 10px; border: 0px; margin: 0px;">
				 
					<table width="98%">
						<tr>
							<td width="100px">
								姓名
							</td>
							<td colspan="2">
								与本人关系
							</td>
							<td colspan="2">
								工作单位
							</td>
							<td>
								家庭或工作单位详细地址
							</td>
							<td>
								职  务
							</td>
							<td colspan="2">
								联系电话
							</td>
						</tr>
						<tr>
							<td colspan="9" align="left">
								<font color="black" style="font-weight: bold;"> 工作证明人：</font> 

							</td>
						</tr>

						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].name" type="text" style="width: 90%"
									  validType="length[0,32]" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].borrowerreRationship" type="text"
									style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].workUnit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].addressfamilyOrWorkunit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].post" type="text" style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].telphone" type="text"
									style="width: 90%"  />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].contactId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[0].borrowerServiceAppId"
									id="contacts0borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr>
							<td colspan="9" align="left">
								<font color="black" style="font-weight: bold;">家庭联系人:</font> 
								（配偶）
							</td>
						</tr>
						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].name" type="text" style="width: 90%"
								 />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].borrowerreRationship" type="text"
									style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].workUnit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].addressfamilyOrWorkunit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].post" type="text" style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].telphone" type="text"
									style="width: 90%" />
							</td>

							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].contactId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[1].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" id="contacts1borrowerServiceAppId" />
							</td>

						</tr>
						<tr>
							<td colspan="9" align="left">
								<font color="black" style="font-weight: bold;">紧急联系人:</font> 
								（邻居、非配偶亲属、或其他有效联系人均可）
							</td>
						</tr>
						<tr>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].name" type="text" style="width: 90%"
									/>
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].borrowerreRationship" type="text"
									style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].workUnit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].addressfamilyOrWorkunit" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].post" type="text" style="width: 90%" />
							</td>
							<td colspan="2">
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].telphone" type="text"
									style="width: 90%" />
							</td>
							<td>
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].contactId" type="hidden" />
								<input readonly="readonly" 	style="border: 0px;width: 100%;"  name="contacts[2].borrowerServiceAppId"
									id="contacts2borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
					</table>
				 
				<%--<a class="easyui-linkbutton" href="javascript:contactsFormSubmit();">保存
				</a>
				--%><table>
					<tr>
						<td colspan="9" style="border-top-width: 2px">
							<strong>请您详细阅读以下条款</strong>
						</td>
					</tr>
					<tr>
						<td>
							<p align="right">
								<strong>● </strong>
							</p>
							<p>
								<strong>&nbsp;</strong>
							</p>
							<div align="right">
								<strong>●</strong>
							</div>
						</td>
						<td colspan="8">
							<p align="left" style="color: black;">
								本人（申请人）保证本表填写的所有内容及所提供的证明材料均真实无误，如有不实，愿承担一切法律责任。本人保证所提供的个人身份信息真实有效，且授权宜信惠民投资管理（北京）有限公司在任何第三方机构及银行查验信用信息，并在建立或更新本人信用档案时使用。
								<br />
								本人（申请人配偶）积极配合配偶偿还借款。
							</p>
							<font color="black">如申请人、申请人配偶未做特别说明，申请人的所有文件由宜信代为销毁。</font>
						</td>
					</tr>
				</table>
			</div>
		</form>
		</div>
	</body>
</html>