<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'registrationDialog.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
			<script type="text/javascript">var serverName="<%=basePath%>";</script>
	-->

		<jsp:include page="../include/easyui.jsp"></jsp:include>
<script type="text/javascript">var serverName="<%=basePath%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/receive/registrationDialog.js"></script>
		<style type="text/css">
.myTABLE {
	width: 100%;
}

.myTD {
	font-size: 12px;
	border-right: 1px dotted #ccc;
	border-bottom: 1px dotted #ccc;
	overflow: hidden;
	padding: 0;
	margin: 0;
}

.myTH {
	border-right: 1px solid #ccc;
	font-size: 12px;
	font-weight: normal;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	border-top: 1px dotted #ccc;
	overflow: hidden;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	cursor: default;
	border-top: 1px dotted #ccc;
}

.myINPUT {
	border-style: none;
}
</style>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;" align="center">
				<div title="登记信息">
					<form id="registrationForm" method="post" novalidate>
						<table width="100%" align="center" cellpadding="3" cellspacing="0">
							<tr>
								<td width="100px;">
								</td>
								<td width="100px;">
								</td>
								<td width="100px;">
								</td>
								<td width="100px;">
								</td>
								<td width="100px;">
								</td>
								<td width="100px;">
								</td>
							</tr>
							<tr>
								<td width="100px;" align="right">
									收款金额：
								</td>
								<td width="100px;">
									<input id="receivedAmount" name="receivedAmount" type="text" class="easyui-numberbox" precision="2" onkeypress="if(event.which==45){return false;}" required="true" size="15" />
								</td>
								<td width="100px;" align="right">
									收款日期:
								</td>
								<td width="100px;">
									<input id="receivedTime" name="receivedTime" type="text" class="easyui-datebox" required="true" editable="false" value="<fmt:formatDate value='<%= new Date() %>' pattern='yyyy-MM-dd' />" style="width: 98px;" />
								</td>
								<td width="100px;" align="right">
									登记类型：
								</td>
								<td width="100px;">
									<c:if test="${receivedRecord.receivedType=='0'}">
										正常还款登记
									</c:if>
									<c:if test="${receivedRecord.receivedType=='1'}">
										提前还款登记
									</c:if>
								</td>
							</tr>
							<tr>
								<td width="100px;" align="right">
									资金来源：
								</td>
								<td width="100px;">
									<input id="capitalSource" name="capitalSource" type="text" class="easyui-combobox" editable="false" required="true" style="width: 98px;" />
								</td>
								<td width="100px;" align="right">
									<span id="site0" style="display: none;">地点：</span>
								</td>
								<td width="100px;">
									<span id="site1" style="display: none;"><input id="place" name="place" type="text" style="width: 98px;" /> </span>
								</td>
								<td width="100px;">
									<input id="receivedType" name="receivedType" type="hidden" value="${receivedRecord.receivedType}" />
									<input name="creditapplicationId" type="hidden" value="${receivedRecord.creditapplicationId}" />
									<input id="accountInfoId" name="accountInfoId" type="hidden" />
								</td>
								<td width="100px;">
									&nbsp;
								</td>
							</tr>
							<%--<tr>
								<td width="100px;" colspan="6">
									<textarea id="remark" name="remark" class="easyui-validatebox" validType="length[0,100]" maxlength="100" readonly="readonly" rows="1" cols="50" onclick="textCount('remark', 'remarkSpan', 100);" onblur="checkTextArea('remark','remarkSpan');" style="width: 100%; font-style: italic; color: gray;">点击添加备注……</textarea>
								</td>
							</tr>
							<tr>
								<td width="100px;" colspan="6">
									<span id="remarkSpan"></span>
								</td>
							</tr>
						--%>
						</table>
					</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" align="center">
				<c:choose>
					<c:when test="${receivedRecord.receivedType=='0'}">
						<%--<div title="预算还款金额">
							<form id="amountForm">
								<table class="myTABLE">
									<tr>
										<th class="myTH" align="right">
											应收本金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalReceivablePrincipal" />
										</td>
										<th class="myTH" align="right">
											应收利息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalReceivableInterest" />
										</td>
										<th class="myTH" align="right">
											应收分期服务费：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalFQFWFReceivableCharge" />
										</td>

									</tr>
									<tr>
										<th class="myTH" align="right">
											应收罚息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalFXReceivableCharge" />
										</td>
										<th class="myTH" align="right">
											应收滞纳金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalZNJReceivableCharge" />
										</td>
										<th class="myTH" align="right">
											应收总金额：
										</th>
										<td class="myTD">
											<input id="totalReceivableMoney" class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalReceivableMoney" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					--%></c:when>
					<c:when test="${receivedRecord.receivedType=='1'}">
						<div title="预算提前还款金额">
							<form id="amountForm">
								<table class="myTABLE">
									<tr>
										<th class="myTH" align="right">
											逾期本金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalPrincipal" />
										</td>
										<th class="myTH" align="right">
											逾期利息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalInterest" />
										</td>
										<th class="myTH" align="right">
											逾期分期服务费：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalOverdueFines" />
										</td>

									</tr>
									<tr>
										<th class="myTH" align="right">
											逾期罚息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalOverdueInterest" />
										</td>
										<th class="myTH" align="right">
											应收滞纳金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="totalPeriodCharge" />
										</td>
										<th class="myTH" align="right">
											当期本金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="currentPrincipal" />
										</td>
									</tr>
									<tr>
										<th class="myTH" align="right">
											当期利息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="currentInterest" />
										</td>
										<th class="myTH" align="right">
											当期分期服务费：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="currentPeriodCharge" />
										</td>
										<th class="myTH" align="right">
											剩余本金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="surplusPrincipal" />
										</td>
									</tr>
									<tr>
										<th class="myTH" align="right">
											未来利息：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="behindInterests" />
										</td>
										<th class="myTH" align="right">
											服务费：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="charge" />
										</td>
										<th class="myTH" align="right">
											提前还款违约金：
										</th>
										<td class="myTD">
											<input class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="penalbond" />
										</td>
									</tr>
									<tr>
										<th class="myTH" align="right">
											还款合计：
										</th>
										<td class="myTD">
											<input id="total" class="myINPUT easyui-numberbox" precision="2" size="17" onFocus="this.blur()" type="text" name="total" />
										</td>
										<td colspan="4">
											&nbsp;
										</td>
									</tr>
								</table>
							</form>
						</div>
					</c:when>
					<c:otherwise>
						<span style="font-style: italic; color: red; font-size: large;">没有获取到登记类型！</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="cardTabs" class="easyui-tabs" style="padding: 10px;">
				<div title="个人卡划扣">
					<table id="personalCardGrid"></table>
					<span id="noCard" style="font-style: italic; color: red; display: none; font-size: large;">没有个人卡信息！请到个人卡信息管理添加并授权！</span>
				</div>
				<div title="现金还款">
					<table id="companyCardGrid"></table>
					<span id="noCapCard" style="font-style: italic; color: red; display: none; font-size: large;">没有公司卡信息！请到公司卡信息管理添加并授权！</span>
				</div>
			</div>
		</div>

	</body>
</html>
