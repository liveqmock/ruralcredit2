<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	$(function() {
		$('#commonInfo').datagrid({
			nowrap:true,
			striped:true,
			url:'<%=basePath%>jsp/rc/approval/commoninfo.json',
			columns:[[
	      	    {field:'filename',title:'小组编号',width:100,align:'center'},
	      	    {field:'filetype',title:'还款方案',width:100,align:'center'},
	      	    {field:'operatetype',title:'组长姓名',width:100,align:'center'}
	        ]],
	        rownumbers:false,
	        singleSelect:true
		});
	});
</script>
		<table id="commonInfo">
		</table>
