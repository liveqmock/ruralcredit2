<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript">
			var serverName="<%=basePath%>";
		</script>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	</head>

	<body>
	<div class="easyui-panel">
		<form id="paymentAgin" action="" method="post">
			<input type="hidden" name="groupLoanRegistId" value="${financeMoney.associationId }"/>
			<input type="hidden" id="severDate" name="severDate" value="${severDate }"/>
			<input type="hidden" name="creditapplicationId" value="${financeMoney.creditapplicationId }"/>
			<table>
				<tr>
					<th width="80px">汇入银行</th>
					<td>${financeMoney.bankId }</td>
				</tr>
				<tr>
					<th width="80px">汇入账户名</th>
					<td>${financeMoney.accountName }</td>
				</tr>
				<tr>
					<th width="80px">汇入账号</th>
					<td>${financeMoney.accountNo }</td>
				</tr>
				<tr>
					<th width="80px">支付方式</th>
					<td>${financeMoney.payWay }</td>
				</tr>
				<tr>
					<th width="80px">付款金额</th>
					<td>${financeMoney.amount }</td>
				</tr>
				<tr>
					<th width="80px">备注</th>
					<td>${financeMoney.remark }</td>
				</tr>
				<tr>
					<th width="80px">处理结果</th>
					<td>${financeMoney.returnMsg }</td>
				</tr>
				<tr>
					<th width="80px">付款预约时间</th>
					<td><input id="loanConfirmTime" name="loanConfirmTime" type="text" class="easyui-datebox" editable="false"/></td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>
