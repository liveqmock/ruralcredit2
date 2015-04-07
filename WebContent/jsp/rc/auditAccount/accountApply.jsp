<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String isURL = request.getRequestURL().toString();
	if (isURL.indexOf(".cn") > 0) {
		if (cmUrl.indexOf(".corp") > 0) {
			cmUrl = cmUrl.replaceAll(".corp", ".cn");
		}
	} else if (isURL.indexOf(".corp") > 0) {
		if (cmUrl.indexOf(".cn") > 0) {
			cmUrl = cmUrl.replaceAll(".cn", ".corp");
		}
	}
	DESPlus desPlus = new DESPlus();
	String DESNow = desPlus.encrypt(new Date().getTime() + "");
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
       <script type="text/javascript">var serverName = "<%=path%>";</script>
    <title>新增现金收款申请页面</title>
        <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
        <script type="text/javascript" src="<%=basePath%>scripts/auditAccount/accountApply.js"></script>
  </head>
  
  <body class="easyui-layout">
    	<div region="center" align="center">
    		<div align="center">
		    	<form id="auditAccountApplyForm"> 
		    	 <c:choose>
		    		  <c:when test="${add==0}">
		    		<table width="92%" class="tabfrom" align="center">
		    			<tr>
		    		     <th width="90px">存款时间：</th>
		    		     <td >
		    		     <input id="saveMoneyDateForm" name="saveMoneyTime"  style="width:150px"/>
		    				<input   id="creditapplicationId" name="creditapplicationId" value="${creditapplicationId}"  type="hidden"/>
		    				<input   id="clientid"  value="${creditapplicationId}"  type="hidden"/>
		    				<input   id="balanceDate"  name="balanceDay" value="${balanceDate}"  type="hidden"/><!-- 获取当前日期的 日 -->
		    				</td>
		    				<th width="90px">汇款人姓名：</th><td><input name="remitMoneyName" onkeydown="if(event.keyCode==32){return false;}" class="easyui-validatebox" required="true" validType="length[0,10]" maxlength="10"/></td>
		    			</tr>
		    			<tr>
		    				<th width="90px">存款金额：</th><td><input name="saveMoney" required="true" style="width: 147px;" class="easyui-numberbox" precision="2" invalidMessage="请输入数字!" maxlength="7" />(元)</td>
		    				<th width="90px">提前还款</th><td width="30%">
		    				<input id="ynearyRepayment"  name="earlyRepayment"  >
		    			</tr>
		    			<tr>
		    				<th width="90px">现金收款原因：</th><td colspan=3" width="80%"><textarea cols="110" rows="3" class="easyui-validatebox" required="true" validType="length[0,200]"  name="cashReceiptsReason" maxlength="200"></textarea></td>
		    			</tr>
                        <tr>
                            <th width="90px" align="right" nowrap="nowrap"> 存款单据照片：
                            <td colspan=3" height="250px">
                            <iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/jsp/creditease/operation/operationControl.jsp?clientId=${clientid}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=<%=userId%> 
							&type=3&signTime=${signTime}&signIp=${signIp}" style="width: 100%; height: 100%;" ></iframe>
                            </td>
                        </tr>
		    		</table>
		    		  </c:when>
		    		   <c:otherwise>
		    		   <table width="92%" class="tabfrom" align="center">
		    			<tr>
		    		     <th width="90px">存款时间：</th>
		    		     <td >
		    				<fmt:formatDate value="${balanceAccountApply.saveMoneyTime}" type="both"/>
		    				<input   id="creditapplicationId" name="creditapplicationId" value="${creditapplicationId}"  type="hidden"/>
		    				<input   id="balanceAccountApplyId" name="balanceAccountApplyId"  value="${balanceAccountApply.balanceAccountApplyId}"  type="hidden"/>
		    				<input   id="balanceDay" name="balanceDay"  value="${balanceAccountApply.balanceDay}"  type="hidden"/>
		    				</td>
		    				<th width="90px">汇款人姓名：</th><td><input name="remitMoneyName" readonly="readonly" value="${balanceAccountApply.remitMoneyName}"  onkeydown="if(event.keyCode==32){return false;}" class="easyui-validatebox" required="true" validType="length[0,10]" maxlength="10"/></td>
		    			</tr>
		    			<tr>
		    				<th width="90px">存款金额：</th><td><input name="saveMoney" readonly="readonly" required="true" value="${balanceAccountApply.saveMoney}" style="width: 147px;" class="easyui-numberbox" precision="2" invalidMessage="请输入数字!" maxlength="7" />(元)</td>
		    				<th width="90px">提前还款</th><td width="30%">
		    				<input id="ynearyRepayment" name="earlyRepayment" value="${balanceAccountApply.earlyRepaymentShow}" disabled="disabled" style="width: 150px;"/>
							<input type="hidden" name="earlyRepayment" value="${balanceAccountApply.earlyRepayment}" />
		    			</tr>
		    			<tr>
		    				<th width="90px">现金收款原因：</th><td colspan=3" width="80%"><textarea cols="110" rows="3" readonly="readonly"  class="easyui-validatebox"  value="${balanceAccountApply.cashReceiptsReason}" required="true" validType="length[0,200]"  name="cashReceiptsReason" maxlength="200">${balanceAccountApply.cashReceiptsReason}</textarea></td>
		    			</tr>
                        <tr>
                            <th width="90px" align="right" nowrap="nowrap"> 存款单据照片：
                            <td colspan=3" height="250px">
                               <iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/operation/transferParameter.action?clientId=${clientid}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529" style="width: 100%; height: 300px;"></iframe>
                            </td>
                        </tr>
		    		 <c:if test="${balanceAccountApply.auditResult==0&&add==2}">
                        <tr>
		    				<th width="90px">备注：</th>
		    				<td  colspan=3" width="80%">
		    				<textarea cols="110" rows="3"  validType="length[0,200]" id="borrRemark"  name="borrRemark" maxlength="200"></textarea>
		    				</td>
		    				</tr>
		    				</c:if>
		    				<c:if test="${balanceAccountApply.auditResult!=0&&add==1}">
		    				<tr>
		    				<th width="90px">备注：</th>
		    				<td  colspan=3" width="80%">
		    				<textarea cols="110" rows="3" readonly="readonly"  validType="length[0,200]" id="borrRemark"  name="borrRemark" maxlength="200">${balanceAccountApply.borrRemark}</textarea>
		    				</td>
		    			</tr>
		    				</c:if>
		    		</table>
		    		</c:otherwise>
		    	</c:choose>
		    	</form>
	    	</div>
    	</div>
  </body>
</html>
