<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

		
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery.json-2.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/json2.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery.easyui.min3.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery.edatagrid.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery.blockUI.js"></script>	
	<script type="text/javascript" src="<%=basePath%>scripts/util1.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jzloding.js"></script>
