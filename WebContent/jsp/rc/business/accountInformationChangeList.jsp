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

		<title>My JSP 'accountInformationChangeList.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>/scripts/business/accountInformationChangeList.js"></script>
		<script type="text/javascript">
	function returnPalnView(value, rowData, rowIndex) {
		var creditapplicationId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var status = rowData.status;
		var modifyCatdAppId = rowData.modifyCatdAppId;
		var links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openRegistrationDialog(" + modifyCatdAppId + "," + creditapplicationId + ",1)'><font color='green'>查看</font></a>";
		<sec:authorize ifAnyGranted="${control.accountInformation_approval}">
		if ("0" == status) {
			links =links+ "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openRegistrationDialog(" + modifyCatdAppId + "," + creditapplicationId + ",0)'><font color='#9932cc'>审批</font></a>";
		}
		</sec:authorize>
		return links;
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="查询条件">
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 业务单号：&nbsp;&nbsp;
					<input id="businessNumber" type="text" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 借款人姓名：&nbsp;&nbsp;
					<input id="name" type="text" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" plain="false" iconCls="icon-search" onclick="mySearch()">搜索</a>
					<a class="easyui-linkbutton" plain="false" iconCls="icon-reload" onclick="myClear()">清空</a>
					<br />
					<br />
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="查询列表">
					<table id="receivablesList"></table>
				</div>
			</div>
			<div id="registrationDialog" style="width: 900px; height: 400px;"></div>
			<div id="registrationDialog2" style="width: 900px; height: 400px;"></div>
		</div>
	</body>
</html>
