<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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
DESPlus desPlus = new DESPlus();
String DESNow = desPlus.encrypt(new Date().getTime()+"");
String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <base href="<%=basePath%>">
    
    <title>审批页面</title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/examine.css" />
	<script type="text/javascript">
		var server="<%=basePath%>";
        var serverName = "<%=path%>";
		var cmUrl = "<%=cmUrl%>";
		var userId = "<%=userId%>";
	</script>
	<script type="text/javascript" src="<%=basePath %>scripts/business/creditBackgroundInvestigation.js">
	</script>
	<script type="text/javascript" src="<%=basePath %>scripts/approval/examine.js">
	</script>
    <style type="text/css">
        .originInput{
            border-left: 0;
            border-right: 0;
            border-top: 0;
            border-bottom: 1px;
            background:white;
            text-align: inherit;
        }
    </style>
  <div id="cc" class="easyui-layout" fit="true" >
  <div region="center">
  <form id="aduit">
  	<div class="easyui-panel" title="申请信息">
  		<input id="creditapplicationId" name="creditapplicationId" type='hidden' value="${creditApplication.creditapplicationId }"/>
  		<input id="creditInvestigatioId" name="creditInvestigatioId" type='hidden' value="${creditApplication.creditInvestigatioId }"/>
  		<input id="groupNumber1" name="groupNumber" type='hidden' value="${creditApplication.groupNumber }"/>
  		<input id="guaRanor" name="guaRanor" type='hidden' value="${guaRanor.borrowerServiceAppId }"/>
  		<input id="capitalUpperLimit" name="capitalUpperLimit" type='hidden' value="${creditApplication.capitalUpperLimit }"/>
  		<input id="capitalLowerLimit" name="capitalLowerLimit" type='hidden' value="${creditApplication.capitalLowerLimit }"/>
  		<input id="role" name="role" type='hidden' value="${role }"/>
  		<input id="caDESId" type='hidden' value="${caDESId }"/>
  		<input id="caDESId1" type='hidden' value="${caDESId1 }"/>
  		<input id="cmexam" type='hidden' value="${cmexam }"/>
  		<input name="businessType" type='hidden' value="${creditApplication.businessType }"/>
    	<table class="ctable" id="table1">
    		<tr>
	    		<th>业务单号：</th><td>${creditApplication.groupNumber }</td>
	    		<th align="right">客户经理：</th><td>${creditApplication.loanOfficerName }</td>
    		</tr>
    		<tr>
    			<th align="right">产品类型：</th><td>${creditApplication.repaymentPlanName }</td>
	    		<th align="right">申请资料:</th>
	    		<td>
	    			<a id="showInfo">显示资料</a>
					   	<div id="groupInfo" style="width:200px;background-color:#E0ECFF;">  
			        		 <div><a class="abut bac" onclick="ruhu1(event)">申请人信息表</a></div>
					   		<c:forEach items="${guaRanorList }" var = "guaRanor" varStatus="status">
								<div><a class="abut bac" onclick="ruhu2(event,'${guaRanor.borrowerServiceAppId}')">担保人${status.index+1 }信息表</a></div>
					   		</c:forEach>
							<div><a class="abut bac" onclick="contact(event)">信用及背景调查报告</a></div>
							<div><a class="abut bac" onclick="others(event,null)">借款人资料</a></div>
							<c:forEach items="${guaRanorList }" var = "guaRanor" varStatus="status">
								<div><a class="abut bac" onclick="others(event,'${guaRanor.validState}')">担保人${status.index+1 }资料</a></div>
					   		</c:forEach>
							<%--<div><a class="abut bac" onclick="showCashFlowStreamInput(event)">现金流入流出表</a></div>
							<div><a class="abut bac" onclick="showCashFlowWorkTable(event)">现金流工作表</a></div>
				       --%></div>  
	    		</td>
    		</tr>
    	</table>
    </div>
      <%--营业部经理、风险经理审批--%>
      <c:set var="r1" value="${control.loanList_approve}"></c:set>
      <c:set var="r2" value="${control.loanList_fxapprove}"></c:set>
      <c:set var="r12" value="${r1},${r2}"></c:set>
      <%--
      区域经理、区域风险经理
      2014.12.02 参审添加为统一入口 start
      <c:set var="r3" value="${control.loanList_qyapprove}"></c:set>
      <c:set var="r4" value="${control.loanList_qyfxapprove}"></c:set>
      <c:set var="r34" value="${r3},${r4}"></c:set>
      --%>
	  <%--城市参审（包括区域经理、区域风险经理等）--%>
	  <c:set var="r34" value="${control.loanList_participate}"></c:set>
	  <%-- 2014.12.02 参审添加为统一入口 end --%>
     <div class="easyui-panel" title="借款信息">
    	<table class="ctable">
	   		<tr>
			   	<th width="80px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借款人</th>
	   			<th width="80px">申请金额</th>
	   			<th width="80px">审批金额</th>
	   		</tr>
	   		<c:forEach var="rlBorrowerServiceApp" items="${list }" varStatus="status">
	   			<tr>
	   			<td width="80">
	   				<input id="appBorrowerAppId" type="hidden" name="list[${status.index }].borrowerAppId" value="${rlBorrowerServiceApp.borrowerServiceAppId}">
	   				<input type="hidden" name="list[${status.index }].borrowerName" value="${rlBorrowerServiceApp.name}">
	   				<a class="uname" style="padding-left:30px;">${rlBorrowerServiceApp.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	   			</td>
				<td width="80">
					<input class="originInput" id="amount"  type="text" value="${rlBorrowerServiceApp.applyLimit }"
  					class="easyui-numberbox ipttext" readonly>
				</td>
				<td width="80">
                    <sec:authorize ifAnyGranted="${r12}">
                        <input id="chgamount" name="list[${status.index }].amount" type="text" value="${rlBorrowerServiceApp.applyLimit }"
                               class="easyui-numberbox ipttext" onblur="validateAmount(this)" >
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${r34}">
                        <input class="originInput" id="chgamount" name="list[${status.index }].amount" type="text" value="${finalAmount}"
                               class="easyui-numberbox ipttext" readonly>
                    </sec:authorize>
				</td>
	   		</tr>
	   		</c:forEach>
	   	</table>
    </div>
    <%--<div class="easyui-panel" title="付款账户">
    	<table class="ctable">
			<tr>
	 			<th>&nbsp;&nbsp;&nbsp;&nbsp;${accountType }</th>
	 			<td><select id="accountInfoId" name="accountInfoId">
	 				<c:forEach items="${rlAccountInfoList }" var="ail" varStatus="status">
	 					<option value="${ail.accountInfoId }" <c:if test="${status.index==1 }">selected</c:if>>${ail.branchName }||${ail.account }</option>
	 				</c:forEach>
	 			</select><span class="font-color:red;">${alert}</span>
				</td>
			</tr>
		</table>
    </div>--%>
    <div class="easyui-panel" title="意见">
    	<table style="margin:auto auto;table-layout: fixed;width: 100%">
    		<c:forEach items="${listRlAAT }" var="rlApplyAuditTable" varStatus="status">
    		<tr>
    			<th width="20%" nowrap="nowrap" valign="baseline">历史意见${status.index+1 }</th>
    			<td style="word-wrap:break-word;width: 68%" valign="baseline">
	    			${rlApplyAuditTable.examPaerson }&nbsp;&nbsp;于
	    			${rlApplyAuditTable.examDate }&nbsp;&nbsp;
	    			${rlApplyAuditTable.examResult}&nbsp;&nbsp;
	    			${rlApplyAuditTable.borrRemark}
    			<td>
    		</tr>
    		</c:forEach>
    		<tr>
                <sec:authorize ifAnyGranted="${r12}">
                    <th width="20%" nowrap="nowrap">审批意见<font color="red">*</font></th>
                </sec:authorize>
                <sec:authorize ifAnyGranted="${r34}">
                    <th width="20%" nowrap="nowrap">备注<font color="red">*</font></th>
                </sec:authorize>
    			<td><textarea name="borrRemark" id="borrRemark" cols="100" rows="5" style="resize:none;" onkeyup="this.value = this.value.substring(0, 200)" maxlength="200" class="ipttext"></textarea> </td>
            </tr>
    		<tr>
				<td width="100%" align="center" colspan="2">
					<sec:authorize ifAnyGranted="${control.loanList_approve}">
						<a class="easyui-linkbutton abut" onclick="showUploadDig2Approval()">上传材料清单与审批表</a>
					</sec:authorize>
                    <sec:authorize ifAnyGranted="${r12}">
                        <a id="doButton" class="easyui-linkbutton abut" onclick="javascript:aduit();">同意</a>
                        <a id="unDoButton" class="easyui-linkbutton abut" onclick="javascript:unaduit();">拒绝</a>
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${r34}">
                        <a id="doButton" class="easyui-linkbutton abut" onclick="javascript:aduit();">解冻</a>
                        <a id="unDoButton" class="easyui-linkbutton abut" onclick="javascript:unaduit();">拒绝</a>
                    </sec:authorize>
				</td>
			</tr>
    	</table>
    </div>
  </form>
  </div>
   	<div id="ruhu1" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<div id="ruhu2" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<div id="contact" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<div id="others">
   		 <iframe scrolling="auto" id='openCM' frameborder="0"  src="" style="width:100%;height:600px;"></iframe>
   	</div>
   	<div id="cashFlow" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<div id="cm1" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<div id="cashFlowWorkTable" style="width:1000px;height:400px;z-index:1;">
   	</div>
 </div>
<%--拒贷原因--%>
<div id="sub_refuseReasonDialog" class="easyui-dialog"  style="width:500;height:150;" closed="true">
    <fieldset>
        <legend>拒贷原因</legend>
        <input id="sub_RefuseReason" type="hidden"/>
        <input id="sub_RefuseReasonSection" type="hidden"/>
        <input id="sub_RefuseReasonCreditapplicationId" type="hidden" value="${creditApplication.creditapplicationId}"/>
        请输入拒贷原因：<input name="" id="sub_showRefuseReason" size="60" readonly="readonly"
                       onfocus="sub_showRefuseReasonDiv('sub_RefuseReason','sub_showRefuseReason');"/>
    </fieldset>
</div>
<input id="sub_section"/>
<div id="sub_refuseReasonDiv">
    <table id="sub_refuseReasonTable" style="width: 870px;border:1px;  border-color: black;" align="center" fit="true" >
    </table>
</div>