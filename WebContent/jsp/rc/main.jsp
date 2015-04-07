<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <jsp:include page="include/easyui.jsp"></jsp:include>
  <head>
    <title>农商贷2.0</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=basePath%>/scripts/index.js"></script>
    <script type="text/javascript">
		var serverName="<%=path%>";
	</script>
</head>
<body class="easyui-layout">
	<div region="north" split="false" border="false" style="overflow: hidden; height: 30px;background: url(<%=basePath%>images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 <%=SpringSecurityUtils.getCurrentUser().getName_zh()%> 农村商贷 
        <a href="javascript:openDialog()" id="editpass">修改密码</a> 
        <a href="<%=basePath%>j_spring_security_logout" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="<%=basePath%>images/blocks.gif" width="20" height="20" align="absmiddle" /> 农村商贷</span>
    </div>
	<div region="west" href="layout/west.jsp" title="导航" split="false" style="width:200px;overflow: hidden;"></div>
	<div region="center" href="layout/center.jsp" title="欢迎使用农村商贷系统v2.0" style="overflow: hidden;"></div>
    <div region="south" split="false" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By 宜信 网站：www.CreditEase.cn</div>
    </div>
    <div id="editDialog" style="display:none">
	    <form id="password_form">
		    	<table align="center">
		    		<tr>
		    			<td>
		    				密码:
		    			</td>
		    			<td>
		    				<input type="password" validType="length[6,32]" class="easyui-validatebox" id="newPassword" name="newPassword" required="true"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				重复密码:
		    			</td>
		    			<td>
		    				<input type="password" class="easyui-validatebox" id="repeatPassword" name="repeatPassword" required="true" validType="equalTo['#newPassword']" invalidMessage="两次输入密码不匹配"/>
		    			</td>
		    		</tr>
	    		</table>
	    </form>
    </div>
</body>
</html>
