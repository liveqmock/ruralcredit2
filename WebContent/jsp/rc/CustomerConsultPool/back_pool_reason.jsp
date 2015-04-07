<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../include/easyui.jsp" />
<script type="text/javascript">var serverName = "<%=path%>";</script>
<script src="<%=basePath%>scripts/CustomerConsultPool/back_pool_reason.js"></script>
 </head>
  
<body class="easyui-layout">
	<c:choose>
			<c:when test="${pool==1}">
	<div region="center">
	<div style="background:#fafafa;padding:10px;width:auto;height:auto;">
	    <form novalidate=""  id="acceptConsultRollbackForm">
            <fieldset>
				<legend>退回原因</legend>
				<!-- <textarea class="easyui-validatebox" maxlength="300" validType="maxLength[300]" required="true"   id="rollbackReason" name="rollbackReason"   style="height:100px;width:500px;">${acceptConsultRollback==null?"":acceptConsultRollback.rollbackReason}</textarea> -->
				 <textarea class="easyui-validatebox" id="rollbackReason" maxlength="200" validType="length[0,200]" onclick="textCount('rollbackReason', 'areaFiveS', 200);"  required="true"    name="rollbackReason"   style="height:100px;width:500px;"></textarea> 
			</fieldset>
			<tr><td align="right">
							&nbsp;
						</td>
						<td align="left" colspan="3">&nbsp;<span id="areaFiveS"></span></td>
						</tr>
			<input type="hidden" id="consultPoolId" name="consultPoolId" value="${consultPoolId}">
    	</form>
	</div>
	</div>
	</c:when>
	
	<c:otherwise>
	<div region="center">
	<div style="background:#fafafa;padding:10px;width:auto;height:auto;">
	    <form novalidate=""  id="acceptConsultRollbackConfirmForm">
            <fieldset>
				<legend>确认退回</legend>
				<textarea disabled="disabled" class="easyui-validatebox" maxlength="300" validType="maxLength[300]"   id="rollbackReason"   style="height:100px;width:500px;">${acceptConsultRollback.rollbackReason}</textarea>
			</fieldset>
			<input type="hidden" id="consultPoolId" name="consultPoolId" value="${consultPoolId}">
    	</form>
	</div>
	</div>
	</c:otherwise>
		</c:choose>
</body>
	