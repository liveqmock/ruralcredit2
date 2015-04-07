<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'returnPlan.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/receive/returnPlan.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
	</head>

	<body class="easyui-layout">
		<input type="hidden" name="needRemindReceived" id="needRemindReceived"/>
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;" tools="#tools">
				<div title="基本信息">
					<table width="100%" align="center" cellpadding="3" cellspacing="0">
						<tr>
							<td width="100">
								<input id="creditapplicationId" type="hidden" value="${creditApplication.creditapplicationId}" />
							</td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
						</tr>
						<tr>
							<td width="100" align="right">
								<b style="font-weight: bold;">业务单号：</b>
							</td>
							<td width="100">
								<input id="groupNumber" type="hidden" value="${creditApplication.groupNumber}" />
								${creditApplication.groupNumber}
							</td>
							<td width="100" align="right">
								<b style="font-weight: bold;">借款人姓名：</b>
							</td>
							<td width="100">
								<input type="hidden" value="${creditApplication.groupName}" />
								${creditApplication.groupName}
							</td>
							<td width="100" align="right">
								<b style="font-weight: bold;">产品名称：</b>
							</td>
							<td width="100">
								<input type="hidden" value="${creditApplication.repaymentPlanName}" />
								${creditApplication.repaymentPlanName}
							</td>
							<td width="100"></td>
							<td width="100"></td>
						</tr>
						<tr>
							<td width="100" align="right">
								<b style="font-weight: bold;">协议签订日期：</b>
							</td>
							<td width="100">
								<input type="hidden" value="${creditApplication.signagreementDate}" />
								<fmt:formatDate value='${creditApplication.signagreementDate}' pattern='yyyy-MM-dd' />
							</td>
							<td width="100" align="right">
								<b style="font-weight: bold;">客户经理：</b>
							</td>
							<td width="100">
								<input type="hidden" value="${creditApplication.loanOfficerName}" />
								${creditApplication.loanOfficerName}
							</td>
							<td width="100" align="right">
								<b style="font-weight: bold;">分公司名称：</b>
							</td>
							<td width="100">
								<input type="hidden" value="${creditApplication.companyName}" />
								${creditApplication.companyName}
							</td>
							<td width="100"></td>
							<td width="100"></td>
						</tr>
						<tr>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
							<td width="100"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#toolsTest">
				<div title="还款计划">
					<table id="returnPlanGrid">
					</table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;display: none;">
				<div title="测试webservice接口">
					<%--<input type="button" onclick="testClientApply();" value="1.1testClientApply" />
					<input type="button" onclick="testReturnScheme();" value="1.2testReturnScheme" />
					<input type="button" onclick="testOverdueInfo();" value="1.3testOverdueInfo" />
					<input type="button" onclick="testReserveReturn();" value="testReserveReturn" />
					<input type="button" onclick="testQyReserveSearch();" value="testQyReserveSearch" />
					--%>
					<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="jumpTOGatheringAppointment();">跳转到新的收款页面</a>
					<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="jumpToQyClientApplyJsp();">跳转到查询借款人页面</a>
					<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="jumpToQyReserveSearch();">跳转到查询划扣结果页面</a>
				</div>
			</div>
		</div>
		<div id="tools">
		<sec:authorize ifAnyGranted="${control.returnPlan_registration}">
			<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="openRegistrationDialog(0)">正常还款登记</a>
			<a class="easyui-linkbutton" plain="true" id="returnAllOnceButton" iconCls="icon-ok" onclick="openRegistrationDialog(1)">一次性还款登记</a>
				</sec:authorize>
		</div>
		<div id="toolsTest">
				<a id="refresh" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="refresh();">刷新</a>
			<sec:authorize ifAnyGranted="${control.returnPlan_clientApply}">
				<a id="testClientApply" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="testClientApply();">发送借款人信息</a>
			</sec:authorize>
		</div>
		<div id="registrationDialog" style="width: 700px; height: 400px;"></div>
	</body>
</html>
