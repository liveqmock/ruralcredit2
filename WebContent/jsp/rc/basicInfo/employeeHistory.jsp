<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String comEmpId = request.getParameter("comEmpId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
       <base href="<%=basePath%>"></base>
    <title>My JSP 'bankSetup.jsp' starting page</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	 var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;
	 var comEmpId = <%=comEmpId%>
	</script>
	<script type="text/javascript">
		$(function(){
			$("#employeeChange").datagrid({
				padding : '0',
				border : '0',
				title : '变更历史',
				url : serverName+'/employeeChangeController/queryHistoryChange.do?comEmpId='+comEmpId,
				columns : [[
					{
						field : "account",
						title : "用户名",
						width : 150
					},
					{
						field : "name",
						title : "姓名",
						width : 150
					},
					{
						field : "citydepartmentname",
						title : "所属分中心",
						width : 150
					},
					{
						field : "areadepartmentname",
						title : "所属营业部",
						width : 150
					},
					{
						field : "rolename",
						title : "角色名称",
						width : 150
					},
					{
						field : "departmentname",
						title : "部门名称",
						width : 150
					},
					{
						field : "activestatus",
						title : "在职状态",
						width : 150
					},
					{
						field : "isactive",
						title : "账户状态",
						width : 150
					},
					{
						field : "changedate",
						title : "变更时间",
						width : 150
					}
				]]
			});
		});
	</script>
	
  </head>
  
  <body id="cc" class="easyui-layout">
		<div region="center">
			<div style="padding: 10px;">
			<table id="employeeChange"></table>
			</div>
		</div>
		
  </body>
</html>
