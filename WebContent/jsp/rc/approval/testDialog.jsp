<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testDialog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
		});
		function openDialog(){
			$("#dialog1").dialog({
				loadingMessage:"加载中，请稍候",
				href:"<%=basePath%>creditapplication2ChgMount/selectById.do?creditapplicationId=2497"
			});
		}	
	</script>
  </head>
  
  <body>
    <input type="button" onclick="openDialog()" value="测试对话框">
    <div id="dialog1" style="width:900;height:500;"></div>
  </body>
</html>
