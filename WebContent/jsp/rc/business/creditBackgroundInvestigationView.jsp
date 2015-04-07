<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <script type="text/javascript" src="<%=basePath%>scripts/business/creditBackgroundInvestigation.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/util.js"></script>
</head>
<body>
<script type="text/javascript">
    //记录是否已经计算过
    var isCaculate = true;
    ;
    function computLoanAmount() {
        if (isCaculate) {
            var amount = 0;
            for (var i = 0; i < 6; i++) {
                var theId = "creditHistoryList" + i + "LoanAmount";
                var temp = document.getElementById(theId).value;
                amount = accAdd(getFloat(amount), getFloat(temp));
            }
            document.getElementById('total').value = amount;
            isCaculate = false;
        }
    }
    function getFloat(param) {
        if (isNaN(parseFloat(param))) {
            param = 0;
        } else {
            param = parseFloat(param);
        }
        return param;
    }
</script>
<div region="center" onmousemove="computLoanAmount();">
<c:choose>
    <c:when test="${not empty showAttach}">
        <table fit="true" style="width: 100%;" align="center">
            <tr align="center" width="100%">
                <td align="right" width="60%">
                    <span style="font-size: 25px; font-weight: 700; color: #4B0082;"> 信用及背景调查报告</span>
                </td>
                <c:choose>
                    <c:when test="${showAttach eq 'self'}">
                        <td align="right" nowrap="nowrap" valign="top"></td>
                    </c:when>
                    <c:otherwise>
                        <td align="right" nowrap="nowrap" valign="top">
                            <a style="color: #0000ff;cursor: pointer;" onclick="viewApprovalBillAttach();">查看审批单附件</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr align="center" width="100%">
                <td align="center" width="100%" colspan="2">
                    <span style="font-size: 15px; font-weight: 700;">业务编号：${creditInvestigation.groupNumber}</span>
                </td>
            </tr>
            <tr align="center" width="100%">
                <td align="center" width="100%" colspan="2">
                    借款人姓名：
                    <input readonly="readonly" style="text-align:center;" type="text" id="creditInvestigationName"
                           name="name" value="${creditInvestigation.name}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp; 共借人姓名：
                    <input readonly="readonly" style="text-align:center;" id="spouseName" type="text" name="spouseName"
                           value="${creditInvestigation.spouseName}"/>
                </td>
            </tr>
            <tr align="center" width="100%">
                <td align="center" width="100%" colspan="2">
                    &nbsp;
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <table align="center">
            <tr align="center">
                <td align="center" width="900">
                    <span style="font-size: 25px; font-weight: 700; color: #4B0082;"> 信用及背景调查报告 </span>
                </td>
            </tr>
            <tr align="center">
                <td align="center" width="900">
                    <span style="font-size: 15px; font-weight: 700;">业务编号：${creditInvestigation.groupNumber}</span>
                </td>
            </tr>
            <tr align="center">
                <td align="center" width="900">
                    借款人姓名：
                    <input readonly="readonly" style="text-align:center;" type="text" id="creditInvestigationName"
                           name="name" value="${creditInvestigation.name}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp; 共借人姓名：
                    <input readonly="readonly" style="text-align:center;" id="spouseName" type="text" name="spouseName"
                           value="${creditInvestigation.spouseName}"/>
                </td>
            </tr>
            <tr align="center">
                <td align="center" width="800">
                    &nbsp;
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<table align="center" cellpadding="3" cellspacing="0">

<tr>
    <td align="center" colspan="9">
        <span style="font-size: 15px; font-weight: 700;">信用历史</span>
    </td>
</tr>
<tr>
    <td colspan="9" align="center"
        style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
</tr>

<tr>
    <td align="center" width="10px;">
        贷款机构
    </td>
    <td align="center" width="10px;">
        发放日期
    </td>
    <td align="center" width="10px;">
        到期日期
    </td>
    <td align="center" width="10px;">
        授信额度
    </td>
    <td align="center" width="10px;">
        使用额度
    </td>
    <td align="center" width="10px;">
        账户状态
    </td>
    <td align="center" width="10px;">
        还款方式
    </td>
    <td align="center" width="10px;">
        贷款余额
    </td>
    <td align="center" width="10px;">
        每次还款金额
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[0].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[0].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[0].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[0].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0LoanOrganization" type="text"
               name="creditHistoryList[0].loanOrganization"
               value="${creditInvestigation.creditHistoryList[0].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0LoanDate" type="text"
               name="creditHistoryList[0].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0TermDate" type="text"
               name="creditHistoryList[0].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[0].creditAccount"
               value="${creditInvestigation.creditHistoryList[0].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[0].usedAccount"
               value="${creditInvestigation.creditHistoryList[0].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0AccountStatus"
               name="creditHistoryList[0].accountStatus"
               value="${creditInvestigation.creditHistoryList[0].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0RepaymentType"
               name="creditHistoryList[0].repaymentType"
               value="${creditInvestigation.creditHistoryList[0].repaymentType}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList0LoanAmount" type="text"
               name="creditHistoryList[0].loanAmount" value="${creditInvestigation.creditHistoryList[0].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[0].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[0].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[1].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[1].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[1].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[1].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1LoanOrganization" type="text"
               name="creditHistoryList[1].loanOrganization"
               value="${creditInvestigation.creditHistoryList[1].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1LoanDate" type="text"
               name="creditHistoryList[1].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1TermDate" type="text"
               name="creditHistoryList[1].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[1].creditAccount"
               value="${creditInvestigation.creditHistoryList[1].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[1].usedAccount"
               value="${creditInvestigation.creditHistoryList[1].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1AccountStatus"
               name="creditHistoryList[1].accountStatus"
               value="${creditInvestigation.creditHistoryList[1].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1RepaymentType"
               name="creditHistoryList[1].repaymentType"
               value="${creditInvestigation.creditHistoryList[1].repaymentType}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList1LoanAmount" type="text"
               name="creditHistoryList[1].loanAmount" value="${creditInvestigation.creditHistoryList[1].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"
               onblur="computLoanAmount()"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[1].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[1].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[2].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[2].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[2].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[2].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2LoanOrganization" type="text"
               name="creditHistoryList[2].loanOrganization"
               value="${creditInvestigation.creditHistoryList[2].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2LoanDate" type="text"
               name="creditHistoryList[2].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2TermDate" type="text"
               name="creditHistoryList[2].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[2].creditAccount"
               value="${creditInvestigation.creditHistoryList[2].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[2].usedAccount"
               value="${creditInvestigation.creditHistoryList[2].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2AccountStatus"
               name="creditHistoryList[2].accountStatus"
               value="${creditInvestigation.creditHistoryList[2].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2RepaymentType"
               name="creditHistoryList[2].repaymentType"
               value="${creditInvestigation.creditHistoryList[2].repaymentType}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList2LoanAmount" type="text"
               name="creditHistoryList[2].loanAmount" value="${creditInvestigation.creditHistoryList[2].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[2].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[2].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[3].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[3].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[3].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[3].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3LoanOrganization" type="text"
               name="creditHistoryList[3].loanOrganization"
               value="${creditInvestigation.creditHistoryList[3].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3LoanDate" type="text"
               name="creditHistoryList[3].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3TermDate" type="text"
               name="creditHistoryList[3].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[3].creditAccount"
               value="${creditInvestigation.creditHistoryList[3].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[3].usedAccount"
               value="${creditInvestigation.creditHistoryList[3].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3AccountStatus"
               name="creditHistoryList[3].accountStatus"
               value="${creditInvestigation.creditHistoryList[3].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3RepaymentType"
               name="creditHistoryList[3].repaymentType"
               value="${creditInvestigation.creditHistoryList[3].repaymentType}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList3LoanAmount" type="text"
               name="creditHistoryList[3].loanAmount" value="${creditInvestigation.creditHistoryList[3].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[3].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[3].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[4].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[4].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[4].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[4].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4LoanOrganization" type="text"
               name="creditHistoryList[4].loanOrganization"
               value="${creditInvestigation.creditHistoryList[4].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4LoanDate" type="text"
               name="creditHistoryList[4].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4TermDate" type="text"
               name="creditHistoryList[4].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[4].creditAccount"
               value="${creditInvestigation.creditHistoryList[4].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[4].usedAccount"
               value="${creditInvestigation.creditHistoryList[4].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4AccountStatus"
               name="creditHistoryList[4].accountStatus"
               value="${creditInvestigation.creditHistoryList[4].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4RepaymentType"
               name="creditHistoryList[4].repaymentType"
               value="${creditInvestigation.creditHistoryList[4].repaymentType}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList4LoanAmount" type="text"
               name="creditHistoryList[4].loanAmount" value="${creditInvestigation.creditHistoryList[4].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[4].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[4].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
</tr>
<tr>
    <td>
        <input readonly="readonly" style="text-align:center;" type="hidden" name="creditHistoryList[5].creditHistoryId"
               value="${creditInvestigation.creditHistoryList[5].creditHistoryId}"/>
        <input readonly="readonly" style="text-align:center;" type="hidden"
               name="creditHistoryList[5].creditInvestigatioId"
               value="${creditInvestigation.creditHistoryList[5].creditInvestigatioId}"/>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5LoanOrganization" type="text"
               name="creditHistoryList[5].loanOrganization"
               value="${creditInvestigation.creditHistoryList[5].loanOrganization}"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5LoanDate" type="text"
               name="creditHistoryList[5].loanDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].loanDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5TermDate" type="text"
               name="creditHistoryList[5].termDate"
               value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].termDate}' pattern='yyyy-MM-dd' />"
               style="width: 100px;" editable="false"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[5].creditAccount"
               value="${creditInvestigation.creditHistoryList[5].creditAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[5].usedAccount"
               value="${creditInvestigation.creditHistoryList[5].usedAccount}" size="15"
               onkeypress="if(event.which==45){return false;}" precision="2"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5AccountStatus"
               name="creditHistoryList[5].accountStatus"
               value="${creditInvestigation.creditHistoryList[5].accountStatus}" style="width: 90px;"/>

    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5RepaymentType"
               name="creditHistoryList[5].repaymentType"
               value="${creditInvestigation.creditHistoryList[5].repaymentType}" style="width: 90px;"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" id="creditHistoryList5LoanAmount" type="text"
               name="creditHistoryList[5].loanAmount" value="${creditInvestigation.creditHistoryList[5].loanAmount}"
               onblur="computLoanAmount();" onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
    </td>
    <td>
        <input readonly="readonly" style="text-align:center;" type="text" name="creditHistoryList[5].repaymentAmountPer"
               value="${creditInvestigation.creditHistoryList[5].repaymentAmountPer}"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15"/>
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
        <input readonly="readonly" style="text-align:center;" id="total" type="text"
               onkeypress="if(event.which==45){return false;}" precision="2" size="15" onblur="computLoanAmount();"/>
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
            <span style="font-size: 15px; font-weight: 700;">联系人调查 </span>
        </td>
    </tr>
</table>

<c:forEach items="${creditInvestigation.linkmanInvestigationList}" var="linkmans" varStatus="contacts" begin="0">
    <table align="center" cellpadding="3" cellspacing="0" border="0" style="width:1030px;">
        <tr>
            <td colspan="6" align="center"
                style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
        </tr>
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
                <input type="hidden" name="linkmanInvestigationList[${contacts.count}].linkmanInvestigationId"
                       value="${linkmans.linkmanInvestigationId}"/>
                <input id="creditInvestigatioId${contacts.count}" type="hidden"
                       name="linkmanInvestigationList[${contacts.count}].creditInvestigatioId"
                       value="${linkmans.creditInvestigatioId}"/>
                <input name="linkmanInvestigationList[${contacts.count}].seq" type="hidden" value="${contacts.count}"/>
                <input type="text" name="linkmanInvestigationList[${contacts.count}].name" value="${linkmans.name}"
                       class="easyui-validatebox" required="true" maxlength="10"/>
            </td>
            <td align="right">
                和申请人关系：
            </td>
            <td align="left">
                <input id="linkmanInvestigationList${contacts.count}Relation"
                       name="linkmanInvestigationList[${contacts.count}].newrelation" value="${linkmans.newrelation}"/>
            </td>
            <td align="right">
                联系电话：
            </td>
            <td align="left">
                <input type="text" name="linkmanInvestigationList[${contacts.count}].mobilephone"
                       value="${linkmans.mobilephone}" class="easyui-validatebox" validType="phoneNumber"
                       maxlength="11"/>
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
                <input id="linkmanInvestigationList${contacts.count}HowLong"
                       name="linkmanInvestigationList[${contacts.count}].howLong" value="${linkmans.howLong}"
                       style="width: 125px;"/>
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
                <input id="linkmanInvestigationList${contacts.count}Discontentment"
                       name="linkmanInvestigationList[${contacts.count}].discontentment"
                       value="${linkmans.discontentment}" style="width: 125px;"/>
            </td>
        </tr>
        <tr>
            <td align="left" colspan="5">
                <c:choose>
                    <c:when test="${linkmans.discontentment=='是'}">
                        <span id="areaOne${contacts.count}"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea
                                readonly="readonly" id="linkmanInvestigationList${contacts.count}DiscontentmentDetail"
                                name="linkmanInvestigationList[${contacts.count}].discontentmentDetail" rows="3"
                                style="width: 500px;"
                                onclick="textCount('linkmanInvestigationList${contacts.count}DiscontentmentDetail', 'areaOne${contacts.count}S', 100);"
                                maxlength="100" class="easyui-validatebox" required="true"
                                validType="length[0,100]">${linkmans.discontentmentDetail}</textarea> </span>
                    </c:when>
                    <c:otherwise>
                        <span id="areaOne${contacts.count}" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea
                                id="linkmanInvestigationList${contacts.count}DiscontentmentDetail"
                                name="linkmanInvestigationList[${contacts.count}].discontentmentDetail" rows="3"
                                style="width: 500px;"
                                onclick="textCount('linkmanInvestigationList${contacts.count}DiscontentmentDetail', 'areaOne${contacts.count}S', 100);"
                                maxlength="100" class="easyui-validatebox"
                                validType="length[0,100]">${linkmans.discontentmentDetail}</textarea> </span>
                    </c:otherwise>
                </c:choose>
                &nbsp;&nbsp;
                <span id="areaOne${contacts.count}S"></span>
            </td>
        </tr>
        <tr>
            <td align="left" colspan="5">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
            </td>
            <td align="left">
                <input id="linkmanInvestigationList${contacts.count}Gambling"
                       name="linkmanInvestigationList[${contacts.count}].gambling" value="${linkmans.gambling}"
                       style="width: 125px;"/>
            </td>
        </tr>
        <tr>
            <td align="left" colspan="6">
                <c:choose>
                    <c:when test="${linkmans.gambling=='是'}">
                        <span id="areaTwo${contacts.count}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea
                                readonly="readonly" id="linkmanInvestigationList${contacts.count}GamblingDetail"
                                name="linkmanInvestigationList[${contacts.count}].gamblingDetail" rows="2"
                                style="width: 500px;"
                                onclick="textCount('linkmanInvestigationList${contacts.count}GamblingDetail', 'areaTwo${contacts.count}S', 100);"
                                maxlength="100" class="easyui-validatebox" required="true"
                                validType="length[0,100]">${linkmans.gamblingDetail}</textarea> </span>
                    </c:when>
                    <c:otherwise>
                        <span id="areaTwo${contacts.count}" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea
                                id="linkmanInvestigationList${contacts.count}GamblingDetail"
                                name="linkmanInvestigationList[${contacts.count}].gamblingDetail" rows="2"
                                style="width: 500px;"
                                onclick="textCount('linkmanInvestigationList${contacts.count}GamblingDetail', 'areaTwo${contacts.count}S', 100);"
                                maxlength="100" class="easyui-validatebox"
                                validType="length[0,100]">${linkmans.gamblingDetail}</textarea> </span>
                    </c:otherwise>
                </c:choose>
                &nbsp;&nbsp;
                <span id="areaTwo${contacts.count}S"></span>
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
                <textarea readonly="readonly" id="linkmanInvestigationList${contacts.count}Remark"
                          name="linkmanInvestigationList[${contacts.count}].remark" rows="3" cols="150"
                          style="width: 700px;" class="easyui-validatebox" required="true" validType="length[0,100]"
                          onclick="textCount('linkmanInvestigationList${contacts.count}Remark', 'remarkTwo${contacts.count}', 100);"
                          maxlength="100">${linkmans.remark}</textarea>
                &nbsp;&nbsp;
                <span id="remarkTwo${contacts.count}"></span>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="6">
                &nbsp;
            </td>
        </tr>
    </table>
</c:forEach>

<table fit="true" width="1030px" align="center" cellpadding="3" cellspacing="0">
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
        <td colspan="2" align="center"
            style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
    </tr>
    <tr>
        <td align="left" width="600">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人夫妇是否有过任何犯罪记录？如果有，请详细说明。
        </td>
        <td align="left" width="200">
            <input readonly="readonly" style="text-align:center;" type="hidden" name="appelleeRecord.appelleeRecordId"
                   value="${creditInvestigation.appelleeRecord.appelleeRecordId}"/>
            <input readonly="readonly" style="text-align:center;" type="hidden"
                   name="appelleeRecord.creditInvestigatioId"
                   value="${creditInvestigation.appelleeRecord.creditInvestigatioId}"/>
            <input readonly="readonly" style="text-align:center;" id="appelleeRecordIsAppelleeRecord"
                   name="appelleeRecord.isAppelleeRecord" value="${creditInvestigation.appelleeRecord.isAppelleeRecord}"
                   style="width: 125px;"/>
        </td>
    </tr>
    <tr>
        <td align="left">
            <c:choose>
                <c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord=='是'}">
                    <span id="appelleeRecordOne0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
                            readonly="readonly" id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3"
                            style="width: 500px;"
                            onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100"
                            validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
                </c:when>
                <c:otherwise>
                    <span id="appelleeRecordOne0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
                            readonly="readonly" id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3"
                            style="width: 500px;"
                            onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100"
                            validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
                </c:otherwise>
            </c:choose>

            <span id="appelleeRecordOne1"></span>
        </td>
        <td>
            <c:choose>
                <c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord=='是'}">
                    <span id="appelleeRecordOne2"><input readonly="readonly" style="text-align:center;"
                                                         id="appelleeRecordAccusalStatusOne"
                                                         name="appelleeRecord.accusalStatusOne"
                                                         value="${creditInvestigation.appelleeRecord.accusalStatusOne}"
                                                         type="text" style="width: 125px;"/></span>
                </c:when>
                <c:otherwise>
                    <span id="appelleeRecordOne2" style="display: none;"><input readonly="readonly"
                                                                                style="text-align:center;"
                                                                                id="appelleeRecordAccusalStatusOne"
                                                                                name="appelleeRecord.accusalStatusOne"
                                                                                value="${creditInvestigation.appelleeRecord.accusalStatusOne}"
                                                                                type="text"
                                                                                style="width: 125px;"/></span>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td align="left">
            <c:choose>
                <c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord=='是'}">
                    <span id="appelleeRecordTwo0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
                            readonly="readonly" id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3"
                            style="width: 500px;"
                            onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100"
                            validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
                </c:when>
                <c:otherwise>
                    <span id="appelleeRecordTwo0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
                            readonly="readonly" id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3"
                            style="width: 500px;"
                            onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100"
                            validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
                </c:otherwise>
            </c:choose>
            <span id="appelleeRecordTwo1"></span>
        </td>
        <td>
            <c:choose>
                <c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord=='是'}">
                    <span id="appelleeRecordTwo2"><input readonly="readonly" style="text-align:center;"
                                                         id="appelleeRecordAccusalStatusTwo"
                                                         name="appelleeRecord.accusalStatusTwo"
                                                         value="${creditInvestigation.appelleeRecord.accusalStatusTwo}"
                                                         type="text" style="width: 125px;"/> </span>
                </c:when>
                <c:otherwise>
                    <span id="appelleeRecordTwo2" style="display: none;"><input readonly="readonly"
                                                                                style="text-align:center;"
                                                                                id="appelleeRecordAccusalStatusTwo"
                                                                                name="appelleeRecord.accusalStatusTwo"
                                                                                value="${creditInvestigation.appelleeRecord.accusalStatusTwo}"
                                                                                type="text"
                                                                                style="width: 125px;"/> </span>
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
<table fit="true" width="1030px" align="center" cellpadding="3" cellspacing="0">
    <tr>
        <td align="center">
            <span style="font-size: 15px; font-weight: 700;">总结</span>
        </td>
    </tr>
    <tr>
        <td align="center"
            style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
    </tr>
    <tr>
        <td align="center">
            <input readonly="readonly" id="result" name="result" type="text" value="${creditInvestigation.result}"
                   style="width: 200px;"/>
        </td>
    </tr>
</table>
<table fit="true" width="1030px;" align="center" cellpadding="3" cellspacing="0">
    <tr>
        <td align="center"
            style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
    </tr>
    <tr>
        <td align="center">
            <input readonly="readonly" style="text-align:center;" type="hidden" name="riskManagerId"
                   value="${creditInvestigation.riskManagerId}"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查人姓名：&nbsp;&nbsp;
            <input readonly="readonly" style="text-align:center;" id="riskManagerName" name="riskManagerName"
                   value="${creditInvestigation.riskManagerName}" readonly="readonly"/>
            <c:choose>
                <c:when test="${creditInvestigation.investigationDate!=null||creditInvestigation.investigationDate!=''}">

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp; <input
                        readonly="readonly" style="text-align:center;" id="investigationDate" name="investigationDate"
                        value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />"
                        type="text" editable="false"/>
                </c:when>
                <c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp;
                    <input readonly="readonly" style="text-align:center;" id="investigationDate"
                           name="investigationDate"
                           value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />"
                           type="text" editable="false"/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</div>
<div style="height:50px;">

</div>
</body>
</html>