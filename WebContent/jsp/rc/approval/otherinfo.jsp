<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	$(function() {
		$('#otherInfo').datagrid({
			nowrap:true,
			striped:true,
			url:'<%=basePath%>jsp/rc/approval/otherinfo.json',
			columns:[[
	      	    {field:'filename',title:'小组编号',width:100,align:'center'},
	      	    {field:'filetype',title:'还款方案',width:100,align:'center'},
	      	    {field:'operatetype',title:'组长姓名',width:100,align:'center'}
	        ]],
	        rownumbers:false,
	        singleSelect:true
		});
		$(".showImg").bind('mouseover',function(){
			$("img1").attr('src','<%=basePath%>jsp/rc/approval/aaa.jpg');
		});
	});
	function showImg(obj){
		var name=$(obj).text();
		$("#img1").attr('src','<%=basePath%>jsp/rc/approval/'+name);
		$("#img1").slideDown('slow');
	}
</script>
		<table id="otherInfo">
		</table>
		<div id="showImg"></div>
		<img id="img1" style="display:none;">
