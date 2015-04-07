<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<link href="<%=basePath%>css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var serverName="<%=basePath%>";
		</script>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	</head>

	<body>
	<div class="easyui-panel">
		<h3>${alert }</h3>
		<c:if test="${alert==null }">
			<form id="receiveAgin" action="">
				<input type="hidden" id="receiveRecordIds" name="receiveRecordIds" value="${receiveRecordIds }"/>
				<input type="hidden" id="severDate" name="severDate" value="${severDate }"/>
				<table>
					<tr>
						<th>支付银行：</th>
						<td>${accountInfo }</td>
					</tr>
					<tr>
						<th>支付金额：</th>
						<td>${receivedRecordAmount }</td>
					</tr>
					<tr>
						<th>付款日期/预约划扣日期：</th>
						<td><input id="receivedTime" name="receivedTime" type="text" class="easyui-datetimebox" editable="false"/></td>
					</tr>
					<tr>
						<th>备注：</th>
						<td><textarea name="remark" class="ipttext" maxlength="70"></textarea></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	<c:if test="${alert==null }">
		<table>
			<tr>
				<th>借款人</th>
				<th>当期实收金额</th>
			</tr>
			<c:forEach items="${receivedRecordList }" var ="r">
				<tr>
					<td>${r.borrowerName }</td>
					<td>${r.receivedAmount }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</body>
</html>
