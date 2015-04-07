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
		<script type="text/javascript">
		$(function(){
			var dicReport = new DataDictionary();
			dicReport.addDic("creditHistoryList0LoanOrganization_creditHistoryList[0].loanOrganization", "depositOrganization");
			dicReport.addDic("creditHistoryList1LoanOrganization_creditHistoryList[1].loanOrganization", "depositOrganization");
			dicReport.addDic("creditHistoryList2LoanOrganization_creditHistoryList[2].loanOrganization", "depositOrganization");
			dicReport.addDic("creditHistoryList3LoanOrganization_creditHistoryList[3].loanOrganization", "depositOrganization");
			dicReport.addDic("creditHistoryList4LoanOrganization_creditHistoryList[4].loanOrganization", "depositOrganization");
			dicReport.addDic("creditHistoryList5LoanOrganization_creditHistoryList[5].loanOrganization", "depositOrganization");
	
			dicReport.addDic("creditHistoryList0AccountStatus_creditHistoryList[0].accountStatus", "accountStatus");
			dicReport.addDic("creditHistoryList1AccountStatus_creditHistoryList[1].accountStatus", "accountStatus");
			dicReport.addDic("creditHistoryList2AccountStatus_creditHistoryList[2].accountStatus", "accountStatus");
			dicReport.addDic("creditHistoryList3AccountStatus_creditHistoryList[3].accountStatus", "accountStatus");
			dicReport.addDic("creditHistoryList4AccountStatus_creditHistoryList[4].accountStatus", "accountStatus");
			dicReport.addDic("creditHistoryList5AccountStatus_creditHistoryList[5].accountStatus", "accountStatus");
	
			dicReport.addDic("creditHistoryList0RepaymentType_creditHistoryList[0].repaymentType", "repaymentType");
			dicReport.addDic("creditHistoryList1RepaymentType_creditHistoryList[1].repaymentType", "repaymentType");
			dicReport.addDic("creditHistoryList2RepaymentType_creditHistoryList[2].repaymentType", "repaymentType");
			dicReport.addDic("creditHistoryList3RepaymentType_creditHistoryList[3].repaymentType", "repaymentType");
			dicReport.addDic("creditHistoryList4RepaymentType_creditHistoryList[4].repaymentType", "repaymentType");
			dicReport.addDic("creditHistoryList5RepaymentType_creditHistoryList[5].repaymentType", "repaymentType");
	
			dicReport.addDic("linkmanInvestigationList0Relation_linkmanInvestigationList[0].relation_Y", "borrowerreRationship");
			dicReport.addDic("linkmanInvestigationList1Relation_linkmanInvestigationList[1].relation", "borrowerreRationship");
	
			dicReport.addDic("linkmanInvestigationList0HowLong_linkmanInvestigationList[0].howLong_Y", "howLong");
			dicReport.addDic("linkmanInvestigationList1HowLong_linkmanInvestigationList[1].howLong", "howLong");
	
			dicReport.addDic("linkmanInvestigationList0Discontentment_linkmanInvestigationList[0].discontentment_Y", "answer");
			dicReport.addDic("linkmanInvestigationList1Discontentment_linkmanInvestigationList[1].discontentment", "answer");
	
			dicReport.addDic("linkmanInvestigationList0Gambling_linkmanInvestigationList[0].gambling_Y", "answer");
			dicReport.addDic("linkmanInvestigationList1Gambling_linkmanInvestigationList[1].gambling", "answer");
	
			dicReport.addDic("appelleeRecordIsAppelleeRecord_appelleeRecord.isAppelleeRecord_Y", "answer");
	
			// dicReport.addDic("appelleeRecordAccusalStatusOne", "accusalStatus");
			// dicReport.addDic("appelleeRecordAccusalStatusTwo", "accusalStatus");//result
	
			dicReport.addDic("result_result_Y", "result");
			dicReport.dicAjax();
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
	
			$("#creditHistoryList0LoanOrganization").combobox({hasDownArrow:false});
			$("#creditHistoryList1LoanOrganization").combobox({hasDownArrow:false});
			$("#creditHistoryList2LoanOrganization").combobox({hasDownArrow:false});
			$("#creditHistoryList3LoanOrganization").combobox({hasDownArrow:false});
			$("#creditHistoryList4LoanOrganization").combobox({hasDownArrow:false});
			$("#creditHistoryList5LoanOrganization").combobox({hasDownArrow:false});
	
			$("#creditHistoryList0AccountStatus").combobox({hasDownArrow:false});
			$("#creditHistoryList1AccountStatus").combobox({hasDownArrow:false});
			$("#creditHistoryList2AccountStatus").combobox({hasDownArrow:false});
			$("#creditHistoryList3AccountStatus").combobox({hasDownArrow:false});
			$("#creditHistoryList4AccountStatus").combobox({hasDownArrow:false});
			$("#creditHistoryList5AccountStatus").combobox({hasDownArrow:false});
	
			$("#creditHistoryList0RepaymentType").combobox({hasDownArrow:false});
			$("#creditHistoryList1RepaymentType").combobox({hasDownArrow:false});
			$("#creditHistoryList2RepaymentType").combobox({hasDownArrow:false});
			$("#creditHistoryList3RepaymentType").combobox({hasDownArrow:false});
			$("#creditHistoryList4RepaymentType").combobox({hasDownArrow:false});
			$("#creditHistoryList5RepaymentType").combobox({hasDownArrow:false});
	
			$("#linkmanInvestigationList0Relation").combobox({hasDownArrow:false});
			$("#linkmanInvestigationList1Relation").combobox({hasDownArrow:false});
	
			$("#linkmanInvestigationList0HowLong").combobox({hasDownArrow:false});
			$("#linkmanInvestigationList1HowLong").combobox({hasDownArrow:false});
	
			$("#linkmanInvestigationList0Discontentment").combobox({hasDownArrow:false});
			$("#linkmanInvestigationList1Discontentment").combobox({hasDownArrow:false});
			$("#linkmanInvestigationList0Gambling").combobox({hasDownArrow:false});
			$("#linkmanInvestigationList1Gambling").combobox({hasDownArrow:false});
	
			$("#appelleeRecordIsAppelleeRecord").combobox({hasDownArrow:false});
	
			$("#result").combobox({hasDownArrow:false});
			$("#investigationDate").datebox('disable');
	
			$("#appelleeRecordAccusalStatusOne").combobox({hasDownArrow:false});
			$("#appelleeRecordAccusalStatusTwo").combobox({hasDownArrow:false});
		});
		</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">
			<form id="creditInvForm" method="post" novalidate>
				<table fit="true" style="width: 100%;" align="center">
					<tr align="center">
						<td align="center" width="800">
							<span style="font-size: 25px; font-weight: 700; color: #4B0082;"> 信用及背景调查报告 </span>
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							<input type="hidden" name="creditapplicationId" value="${creditInvestigation.creditapplicationId}" />
							<input type="hidden" name="creditInvestigatioId" value="${creditInvestigation.creditInvestigatioId}" />
							<input type="hidden" name="isSubmit" value="${creditInvestigation.isSubmit}" />
							<span style="font-size: 15px; font-weight: 700;">业务编号：${creditInvestigation.groupNumber}</span>
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							借款人姓名：
							<input type="text" id="creditInvestigationName" name="name" value="${creditInvestigation.name}" class="easyui-validatebox" required="true" maxlength="10" />
							&nbsp;&nbsp;&nbsp;&nbsp; 共借人姓名：
							<input id="spouseName" type="text" name="spouseName" value="${creditInvestigation.spouseName}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							&nbsp;
						</td>
					</tr>
					
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" colspan="9">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="9">
							<span style="font-size: 15px; font-weight: 700;">信用历史</span>
						</td>
					</tr>
					<tr>
						<td colspan="9" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>

					<tr>
						<td align="center" width="100">
							贷款机构
						</td>
						<td align="center" width="10">
							发放日期
						</td>
						<td align="center" width="100">
							到期日期
						</td>
						<td align="center" width="100">
							授信额度
						</td>
						<td align="center" width="100">
							使用额度
						</td>
						<td align="center" width="100">
							账户状态
						</td>
						<td align="center" width="100">
							还款方式
						</td>
						<td align="center" width="100">
							贷款余额
						</td>
						<td align="center" width="100">
							每次还款金额
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[0].creditHistoryId" value="${creditInvestigation.creditHistoryList[0].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[0].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[0].creditInvestigatioId}" />
							<input id="creditHistoryList0LoanOrganization" type="text" name="creditHistoryList[0].loanOrganization" value="${creditInvestigation.creditHistoryList[0].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList0LoanDate" type="text" name="creditHistoryList[0].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList0TermDate" type="text" name="creditHistoryList[0].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].creditAccount" value="${creditInvestigation.creditHistoryList[0].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].usedAccount" value="${creditInvestigation.creditHistoryList[0].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList0AccountStatus" name="creditHistoryList[0].accountStatus" value="${creditInvestigation.creditHistoryList[0].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList0RepaymentType" name="creditHistoryList[0].repaymentType" value="${creditInvestigation.creditHistoryList[0].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList0LoanAmount" type="text" name="creditHistoryList[0].loanAmount" value="${creditInvestigation.creditHistoryList[0].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[0].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[1].creditHistoryId" value="${creditInvestigation.creditHistoryList[1].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[1].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[1].creditInvestigatioId}" />
							<input id="creditHistoryList1LoanOrganization" type="text" name="creditHistoryList[1].loanOrganization" value="${creditInvestigation.creditHistoryList[1].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList1LoanDate" type="text" name="creditHistoryList[1].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList1TermDate" type="text" name="creditHistoryList[1].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].creditAccount" value="${creditInvestigation.creditHistoryList[1].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].usedAccount" value="${creditInvestigation.creditHistoryList[1].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList1AccountStatus" name="creditHistoryList[1].accountStatus" value="${creditInvestigation.creditHistoryList[1].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList1RepaymentType" name="creditHistoryList[1].repaymentType" value="${creditInvestigation.creditHistoryList[1].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList1LoanAmount" type="text" name="creditHistoryList[1].loanAmount" value="${creditInvestigation.creditHistoryList[1].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" onblur="computLoanAmount()" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[1].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[2].creditHistoryId" value="${creditInvestigation.creditHistoryList[2].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[2].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[2].creditInvestigatioId}" />
							<input id="creditHistoryList2LoanOrganization" type="text" name="creditHistoryList[2].loanOrganization" value="${creditInvestigation.creditHistoryList[2].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList2LoanDate" type="text" name="creditHistoryList[2].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList2TermDate" type="text" name="creditHistoryList[2].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].creditAccount" value="${creditInvestigation.creditHistoryList[2].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].usedAccount" value="${creditInvestigation.creditHistoryList[2].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList2AccountStatus" name="creditHistoryList[2].accountStatus" value="${creditInvestigation.creditHistoryList[2].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList2RepaymentType" name="creditHistoryList[2].repaymentType" value="${creditInvestigation.creditHistoryList[2].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList2LoanAmount" type="text" name="creditHistoryList[2].loanAmount" value="${creditInvestigation.creditHistoryList[2].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[2].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[3].creditHistoryId" value="${creditInvestigation.creditHistoryList[3].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[3].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[3].creditInvestigatioId}" />
							<input id="creditHistoryList3LoanOrganization" type="text" name="creditHistoryList[3].loanOrganization" value="${creditInvestigation.creditHistoryList[3].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList3LoanDate" type="text" name="creditHistoryList[3].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList3TermDate" type="text" name="creditHistoryList[3].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].creditAccount" value="${creditInvestigation.creditHistoryList[3].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].usedAccount" value="${creditInvestigation.creditHistoryList[3].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList3AccountStatus" name="creditHistoryList[3].accountStatus" value="${creditInvestigation.creditHistoryList[3].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList3RepaymentType" name="creditHistoryList[3].repaymentType" value="${creditInvestigation.creditHistoryList[3].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList3LoanAmount" type="text" name="creditHistoryList[3].loanAmount" value="${creditInvestigation.creditHistoryList[3].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[3].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[4].creditHistoryId" value="${creditInvestigation.creditHistoryList[4].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[4].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[4].creditInvestigatioId}" />
							<input id="creditHistoryList4LoanOrganization" type="text" name="creditHistoryList[4].loanOrganization" value="${creditInvestigation.creditHistoryList[4].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList4LoanDate" type="text" name="creditHistoryList[4].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList4TermDate" type="text" name="creditHistoryList[4].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].creditAccount" value="${creditInvestigation.creditHistoryList[4].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].usedAccount" value="${creditInvestigation.creditHistoryList[4].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList4AccountStatus" name="creditHistoryList[4].accountStatus" value="${creditInvestigation.creditHistoryList[4].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList4RepaymentType" name="creditHistoryList[4].repaymentType" value="${creditInvestigation.creditHistoryList[4].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList4LoanAmount" type="text" name="creditHistoryList[4].loanAmount" value="${creditInvestigation.creditHistoryList[4].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[4].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[5].creditHistoryId" value="${creditInvestigation.creditHistoryList[5].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[5].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[5].creditInvestigatioId}" />
							<input id="creditHistoryList5LoanOrganization" type="text" name="creditHistoryList[5].loanOrganization" value="${creditInvestigation.creditHistoryList[5].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList5LoanDate" type="text" name="creditHistoryList[5].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList5TermDate" type="text" name="creditHistoryList[5].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].creditAccount" value="${creditInvestigation.creditHistoryList[5].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].usedAccount" value="${creditInvestigation.creditHistoryList[5].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList5AccountStatus" name="creditHistoryList[5].accountStatus" value="${creditInvestigation.creditHistoryList[5].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList5RepaymentType" name="creditHistoryList[5].repaymentType" value="${creditInvestigation.creditHistoryList[5].repaymentType}" style="width: 90px;" />
						</td>
						<td>
							<input id="creditHistoryList5LoanAmount" type="text" name="creditHistoryList[5].loanAmount" value="${creditInvestigation.creditHistoryList[5].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[5].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
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
						<td>
							&nbsp;
						</td>
						<td align="right">
							总余额:
						</td>
						<td>
							<input id="total" type="text" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" onblur="computLoanAmount();" maxlength="10" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="9">
							&nbsp;
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							<span style="font-size: 15px; font-weight: 700;">联系人调查</span>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="right">
							联系人姓名：
						</td>
						<td align="left">
							<input type="hidden" name="linkmanInvestigationList[0].linkmanInvestigationId" value="${creditInvestigation.linkmanInvestigationList[0].linkmanInvestigationId}" />
							<input type="hidden" name="linkmanInvestigationList[0].creditInvestigatioId" value="${creditInvestigation.linkmanInvestigationList[0].creditInvestigatioId}" />
							<input type="text" name="linkmanInvestigationList[0].name" value="${creditInvestigation.linkmanInvestigationList[0].name}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
						<td align="right">
							和申请人关系：
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Relation" name="linkmanInvestigationList[0].relation" value="${creditInvestigation.linkmanInvestigationList[0].relation}" required="true" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<input type="text" name="linkmanInvestigationList[0].mobilephone" value="${creditInvestigation.linkmanInvestigationList[0].mobilephone}" class="easyui-validatebox" validType="phoneNumber" required="true" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0HowLong" name="linkmanInvestigationList[0].howLong" value="${creditInvestigation.linkmanInvestigationList[0].howLong}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你知道有人对（申请人夫妻姓名）有任何不满吗？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Discontentment" name="linkmanInvestigationList[0].discontentment" value="${creditInvestigation.linkmanInvestigationList[0].discontentment}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[0].discontentment==0}">
									<span id="areaOne0"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0DiscontentmentDetail" name="linkmanInvestigationList[0].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList0DiscontentmentDetail', 'areaOne0S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].discontentmentDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne0" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0DiscontentmentDetail" name="linkmanInvestigationList[0].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList0DiscontentmentDetail', 'areaOne0S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].discontentmentDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
						</td>
						<td align="left">
							<span id="areaOne0S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Gambling" name="linkmanInvestigationList[0].gambling" value="${creditInvestigation.linkmanInvestigationList[0].gambling}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[0].gambling==0}">
									<span id="areaTwo0"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0GamblingDetail" name="linkmanInvestigationList[0].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList0GamblingDetail', 'areaTwo0S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].gamblingDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo0" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0GamblingDetail" name="linkmanInvestigationList[0].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList0GamblingDetail', 'areaTwo0S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].gamblingDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaTwo0S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点评
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea id="linkmanInvestigationList0Remark" name="linkmanInvestigationList[0].remark" rows="3" cols="150" style="width: 700px;" class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('linkmanInvestigationList0Remark', 'remarkOne', 100);" maxlength="100">${creditInvestigation.linkmanInvestigationList[0].remark}</textarea>
							&nbsp;&nbsp;
							<span id="remarkOne"></span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							&nbsp;
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							联系人姓名：
						</td>
						<td align="left">
							<input type="hidden" name="linkmanInvestigationList[1].linkmanInvestigationId" value="${creditInvestigation.linkmanInvestigationList[1].linkmanInvestigationId}" />
							<input type="hidden" name="linkmanInvestigationList[1].creditInvestigatioId" value="${creditInvestigation.linkmanInvestigationList[1].creditInvestigatioId}" />
							<input type="text" name="linkmanInvestigationList[1].name" value="${creditInvestigation.linkmanInvestigationList[1].name}" maxlength="10" />
						</td>
						<td align="right">
							和申请人关系：
						</td>
						<td align="left">
							<input id="linkmanInvestigationList1Relation" name="linkmanInvestigationList[1].relation" value="${creditInvestigation.linkmanInvestigationList[1].relation}" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<input type="text" name="linkmanInvestigationList[1].mobilephone" value="${creditInvestigation.linkmanInvestigationList[1].mobilephone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList1HowLong" name="linkmanInvestigationList[1].howLong" value="${creditInvestigation.linkmanInvestigationList[1].howLong}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你知道有人对（申请人夫妻姓名）有任何不满吗？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList1Discontentment" name="linkmanInvestigationList[1].discontentment" value="${creditInvestigation.linkmanInvestigationList[1].discontentment}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[1].discontentment==0}">
									<span id="areaOne1"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList1DiscontentmentDetail" name="linkmanInvestigationList[1].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList1DiscontentmentDetail', 'areaOne1S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[1].discontentmentDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne1" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList1DiscontentmentDetail" name="linkmanInvestigationList[1].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList1DiscontentmentDetail', 'areaOne1S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[1].discontentmentDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaOne1S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList1Gambling" name="linkmanInvestigationList[1].gambling" value="${creditInvestigation.linkmanInvestigationList[1].gambling}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[1].gambling==0}">
									<span id="areaTwo1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList1GamblingDetail" name="linkmanInvestigationList[1].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList1GamblingDetail', 'areaTwo1S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[1].gamblingDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList1GamblingDetail" name="linkmanInvestigationList[1].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList1GamblingDetail', 'areaTwo1S', 100);" maxlength="100" required="true" ,validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[1].gamblingDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaTwo1S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点评
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea id="linkmanInvestigationList1Remark" name="linkmanInvestigationList[1].remark" rows="3" cols="150" style="width: 700px;" class="easyui-validatebox"  validType="length[0,100]" onclick="textCount('linkmanInvestigationList1Remark', 'remarkTwo', 100);" maxlength="100">${creditInvestigation.linkmanInvestigationList[1].remark}</textarea>
							&nbsp;&nbsp;
							<span id="remarkTwo"></span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							&nbsp;
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<span style="font-size: 15px; font-weight: 700;">被执行记录</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="left" width="600">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人夫妇是否有过任何犯罪记录？如果有，请详细说明。
						</td>
						<td align="left" width="200">
							<input type="hidden" name="appelleeRecord.appelleeRecordId" value="${creditInvestigation.appelleeRecord.appelleeRecordId}" />
							<input type="hidden" name="appelleeRecord.creditInvestigatioId" value="${creditInvestigation.appelleeRecord.creditInvestigatioId}" />
							<input id="appelleeRecordIsAppelleeRecord" name="appelleeRecord.isAppelleeRecord" value="${creditInvestigation.appelleeRecord.isAppelleeRecord}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left">
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordOne0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100" required="false" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordOne0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100" required="false" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
								</c:otherwise>
							</c:choose>

							<span id="appelleeRecordOne1"></span>
						</td>
						<td>
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordOne2"><input id="appelleeRecordAccusalStatusOne" name="appelleeRecord.accusalStatusOne" type="text" style="width: 125px;" /> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordOne2" style="display: none;"><input id="appelleeRecordAccusalStatusOne" name="appelleeRecord.accusalStatusOne" type="text" style="width: 125px;" /> </span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="left">
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordTwo0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100" required="false" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordTwo0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100" required="false" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
								</c:otherwise>
							</c:choose>
							<span id="appelleeRecordTwo1"></span>
						</td>
						<td>
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordTwo2"><input id="appelleeRecordAccusalStatusTwo" name="appelleeRecord.accusalStatusTwo" type="text" style="width: 125px;" /> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordTwo2" style="display: none;"><input id="appelleeRecordAccusalStatusTwo" name="appelleeRecord.accusalStatusTwo" type="text" style="width: 125px;" /> </span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							&nbsp;
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center">
							<span style="font-size: 15px; font-weight: 700;">总结</span>
						</td>
					</tr>
					<tr>
						<td align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							<input id="result" name="result" type="text" required="true" value="${creditInvestigation.result}" style="width: 200px;" />
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="riskManagerId" value="${creditInvestigation.riskManagerId}" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查人姓名：&nbsp;&nbsp;
							<input id="riskManagerName" name="riskManagerName" class="easyui-validatebox" required="true" value="${creditInvestigation.riskManagerName}" readonly="readonly" />
							<c:choose>
								<c:when test="${creditInvestigation.investigationDate!=null||creditInvestigation.investigationDate!=''}">
									
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp;	<input id="investigationDate" name="investigationDate" value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />" type="text" class="easyui-datebox" required="true" editable="false" />
								</c:when>
								<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp;
							<input id="investigationDate" name="investigationDate" value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />" type="text" class="easyui-datebox" required="true" editable="false" />
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>