<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'loanBalanceData.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	<script type="text/javascript" src="<%=basePath%>/scripts/sales/loanBalanceData.js"></script>
		<style type="text/css">
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

.myTD {
	font-size: 12px;
	border-right: 1px dotted #ccc;
	border-bottom: 1px dotted #ccc;
	overflow: hidden;
	padding: 0;
	margin: 0;
}

.myInputBoder {
	border: 1px;
}
</style>
	</head>

	<body class="easyui-layout">
		<div region="center">
		<div id="salesSearchTab" class="easyui-tabs" style="padding: 10px;">
				<div title="营业部选择">
				<form id="searchForm">
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 请选择营业部：&nbsp;&nbsp;
					<input id=areaDepartmentIds class="easyui-combobox" style="width: 400px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="constructTable(0)">搜索</a>
					<br />
					<br />
				</form>
				</div>
			</div>
			<div id="loanTab" class="easyui-tabs" style="padding: 10px;" tools="#tol">
			</div>
			<div id="tab2" class="easyui-tabs" style="padding: 10px;">
				<div title="名词解释">
					<table width="100%">
						<tr>
							<th width="30%" class="myTH">
								名称
							</th>
							<th class="myTH">
								描述
							</th>
							<th width="30%" class="myTH">
								公式
							</th>
						</tr>
						<tr>
							<td class="myTD">
								1.客户数
							</td>
							<td class="myTD">
								在某个特定逾期区间的客户数
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务数之和
							</td>
						</tr>
						<tr>
							<td class="myTD">
								2.客户数%
							</td>
							<td class="myTD">
								在某个特定逾期区间的客户数占比
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务数之和/总客户数 *100%
							</td>
						</tr>
						<tr>
							<td class="myTD">
								3.违约金额
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的逾期总金额
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的逾期本金、利息、服务费之和
							</td>
						</tr>
						<tr>
							<td class="myTD">
								4.违约金额%
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的逾期总金额 占总贷款余额中比例
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的逾期总金额/总贷款余额*100%
							</td>
						</tr>
						<tr>
							<td class="myTD">
								5.贷款余额
							</td>
							<td class="myTD">
								在某个特定逾期区间的贷款余额
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的应还未还的本金、利息、服务费之和
							</td>
						</tr>
						<tr>
							<td class="myTD">
								6.贷款余额%
							</td>
							<td class="myTD">
								在某个特定逾期区间的贷款余额占总贷款余额中比例
							</td>
							<td class="myTD">
								在某个特定逾期区间的业务的贷款余额/总贷款余额*100%
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="tol">
				<sec:authorize ifAnyGranted="${control.loanBalanceData_synchronizationLoan}">
					<a id="test" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="test();">同步</a>
				</sec:authorize>
				<sec:authorize ifAnyGranted="${control.loanBalanceData_exportExcel}">
					<a id="exportExcel" class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="exportExcel()">导出</a>
				</sec:authorize>
			</div>
		</div>
	</body>
</html>
