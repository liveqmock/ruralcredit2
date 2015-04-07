<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'forecastComparison.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>/scripts/sales/forecastComparison.js"></script>
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
					<a class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="constructSalesPlanningTable(0)">搜索</a>
					<br />
					<br />
				</form>
				</div>
			</div>
			<div id="salesInfo" class="easyui-tabs" style="padding: 10px;">
			
			</div>
		</div>
	</body>
</html>
