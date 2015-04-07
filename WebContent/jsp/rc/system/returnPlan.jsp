<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'returnPlan.jsp' starting page</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
		});
		function submitgroupNumber(){
			$("#submitgroupNumber").linkbutton("disable");
			var groupNumber = $("#groupNumber").val();
			if(groupNumber == null||
					groupNumber == ""){
				alert("申请业务单号null");
				$("#submitgroupNumber").linkbutton("enable");
				return;
			}
			$.ajax({url:"<%=basePath%>/returnPlanController/clientApply.do",
					data:{groupNumber:groupNumber},
					dataType:"json",
					success:function(message){
						if(message.success){
							$("#submitgroupNumber").linkbutton("enable");
							alert(message.success);
						}else{
							$("#submitgroupNumber").linkbutton("enable");
							alert(message.success);
						}
					}
			});
		}
		function searchreturnPlan(){
			var groupNumber = $("#groupNumber").val();
			if(groupNumber == null||
					groupNumber == ""){
				alert("申请业务单号null");
				return;
			}
			$("#returnPlan").datagrid({
				url:"<%=basePath%>/returnPlanController/returnScheme.do",
				queryParams:{groupNumber:groupNumber},
				striped : true,
				singleSelect : true,
				rownumbers : true,
				loadMsg : '正在加载....',
				columns:[[
				 	{title:"分期数",field:"period",width:150},
				 	{title:"是否逾期",field:"isOverdue",width:150},
				 	{title:"是否还清",field:"isReturned",width:150},
				 	{title:"还款日期",field:"repayDate",width:150},
				 	{title:"应收总金额",field:"receivableMoney",width:150},
				 	{title:"应收本金",field:"receivablePrincipal",width:150},
				 	{title:"应收利息",field:"receivableInterest",width:150}
				 ]]
			});
		}
	</script>
  </head>
  
  <body class="easyui-layout">
		<div region="center">
  		<div class="easyui-tabs" style="padding: 20px;">
  			<div title="查询" style="padding: 20px;">
  				申请单业务编号：<input id="groupNumber"/>
  				<a class="easyui-linkbutton" id="submitgroupNumber" href="javascript:submitgroupNumber()">生成还款计划</a>
  				<a class="easyui-linkbutton" id="searchreturnPlan" href="javascript:searchreturnPlan()">查询还款计划</a>
  			</div>
  		</div>
  		<div style="padding: 20px;">
  			<table id="returnPlan"></table>
  		</div>
  		 
  	</div>
  </body>
</html>
