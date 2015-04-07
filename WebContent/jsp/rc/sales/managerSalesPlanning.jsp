<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'salesPlanning.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>/scripts/sales/managerSalesPlanning.js">
		</script>
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
<script type="text/javascript">
	var serverName="<%=path%>";
</script>
	</head>
	<body class="easyui-layout">
		<div region="center">
			<div id="salesSearchTab" class="easyui-tabs" style="padding: 10px;">
				<div title="年份选择">
				<form id="searchForm">
				<table border="0" style="margin-left: 100px;" >
				<tr><td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr>
				<td>请选择年份：</td>
				<td ><input id="year" style="width:150px;"  class="easyui-combobox" />
				<input id="loanYear" style="width:150px;" type='hidden'/>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择营业部：&nbsp;&nbsp;</td>
				<td><input id=areaDepartmentId class="easyui-combobox" style="width: 400px;"  /></td>
				</tr>
				<tr>	
				<td colspan="4" align="right"><a style="margin-top: 20px;" class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="constructSalesPlanningLoanNumTable()">搜索放款量</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="constructSalesPlanningContractTable()">搜索合同金额量</a>
					</td>
				</tr>
				</table>
				</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
		        <div title="放款量（单位/笔）">
					<table id="loanNumSalesPlanning">
					</table>
				</div>
				<div title="合同金额（单位/万元）">
					<table id="contractMoneySalesPlanning">
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
