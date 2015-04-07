<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'lockList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>

		<script type="text/javascript">
		var serverName = "<%=path%>";
	$(function() {
		$("#lockList").datagrid({
			url : serverName + "/LockListController/lockStateQuery.do",
			method : 'post',
			loadMsg : "数据装载中....",
			fitColumns : true,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'account',
				title : '银行卡号',
				width : 250,
				hidden : false
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 250,
				hidden : true
			}, {
				field : 'updateTime',
				title : '更新时间',
				width : 250,
				hidden : true
			}, {
				field : 'oprate',
				title : '操作',
				width : 250,
				hidden : false,
				formatter : function(value, rowDate, rowINdex) {
					var account = rowDate.account;
					return "<a href='javascript:void(0)' onclick='unlock(" + "\"" + account + "\"" + ")'><font color='#9932cc'>解锁</font></a>";
				}
			} ] ]
		});
	});
	function unlock(account) {
		ajaxSubmit(serverName + "/LockListController/unLock.do", {
			account : account
		}, function(r) {
			if (r.success) {
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : "解锁成功"
				});
			} else {
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : "解锁失败"
				});

			}
			$("#lockList").datagrid("load");
		});
	}
	function unLockAll() {
		ajaxSubmit(serverName + "/LockListController/unLockAll.do", {
		}, function(r) {
			if (r.success) {
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : "解锁成功"
				});
			} else {
				$.messager.show({
					showType : "show",
					timeout : 5000,
					title : "消息",
					msg : "解锁失败"
				});

			}
			$("#lockList").datagrid("load");
		});
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;" tools="#tools">
				<div title="锁列表">
					<table id="lockList">
					</table>
				</div>
			</div>
			<div id="tools">
				<a class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="unLockAll();">清除所有锁</a>
			</div>
		</div>
	</body>
</html>
