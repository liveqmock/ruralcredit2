<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/jquery-easyui-portal/portal.css">
<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery-easyui-portal/jquery.portal.js"></script>