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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'haveNoVisitPlanList.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>/scripts/system/haveNoVisitPlanList.js"></script>

	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;" tools="#too">
				<div title="查询列表">
				<table id="receivablesList"></table>
				</div>
			</div>
			<div id="too">
				<a id="test" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="visitPlan();">生成回访计划</a>
				<a id="testtest" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="creatUrgeHistory();">生成催收历史（与数据无关只点一次）</a>
			</div>
		</div>
	</body>
</html>
