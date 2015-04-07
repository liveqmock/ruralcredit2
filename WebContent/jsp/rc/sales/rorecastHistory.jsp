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

		<title>My JSP 'rorecastHistory.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
	<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/sales/rorecastHistory.js"></script>
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
				<div title="搜索">
				<form id="searchForm" >
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;预测年月范围：&nbsp;&nbsp;
					<input id="forecsatTimeBegin" class="easyui-datebox" value="${ipym}" editable="fasle" />
					&nbsp;至&nbsp;
					<input id="forecsatTimeEnd" class="easyui-datebox" value="${ipym}" editable="fasle" />
					&nbsp;&nbsp;&nbsp;&nbsp; 请选择营业部：&nbsp;&nbsp;
					<input id=areaDepartmentIds class="easyui-combobox"  style="width: 400px;"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="areaDepartmentIdsInput" type="hidden" />
					<input id="forecsatTimeBeginHidden" value="${ipym}" type="hidden" />
					<input id="forecsatTimeEndHidden" value="${ipym}" type="hidden" />
					<a class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="constructRorecastHistoryTable()">搜索</a>
					<br />
					<br />
					</form>
				</div>
			</div>
			<div id="rorecastHistoryTabs" class="easyui-tabs" style="padding: 10px;" tools="#tol">

			</div>
			<div id="tol">
				<sec:authorize ifAnyGranted="${control.rollingForecast_export}">
					<a id="save" class="easyui-linkbutton" plain="true" iconCls="icon-save"  onclick="exportExcel()">导出</a>
				</sec:authorize>
			</div>
			<div id="detailDialog" style="width: 700px; height: 400px;"></div>
		</div>
	</body>
</html>
