<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户经理业绩报表</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		var serverName="<%=path%>";
		$(function(){
			defaultHaveScroll("cmp");
			$("#departmentName").combobox({
		    	url:serverName+"/CustomerConsult/departmentList.do",
		    	valueField:"departmentId",
		    	textField:"departmentName",
		    	mote:"remote",
		    	panelHeight:'auto',
		    	editable:false,
				multiple:false,
				onLoadSuccess:function(){
					$("#departmentName").combobox('select','');
				}
		    });
		});
		/** 查询 **/
		function search(){
			var param = paramStr();
			$("#cmp").datagrid("load",{param:param});
		}
		/** 清除查询条件 **/
		function clear(){
			$("#departmentName").combobox("setValue","");
			$("#beginDate").datebox("clear");
			$("#endDate").datebox("clear");
		}
		/** 导出CMP报表 **/
		function erportCMPExcel(){
			$.messager.confirm('提示信息','确认要导出报表吗?',function(r){
				if (r){
					var url = "<%=basePath%>excelExport/exportCMPExcel.do";
					var param = paramStr();
					window.location.href = "<%=basePath%>excelExport/exportCMPExcel.do?param="+param+"";
				}
			});
		}
		
		function paramStr(){
			var paramObj = {
				companyId:$("#departmentName").combobox("getValue"),
				beginLoanDate:$("#beginDate").datebox("getValue"),
				endLoanDate:$("#endDate").datebox("getValue")	
			};
			var param = JSON.stringify(paramObj);
			return param;
		}
	</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" style="background: #fafafa; overflow: hidden;" fit="true">
		<table id="cmp" class="easyui-datagrid" toolbar="#toolbar" url="../../../excelExport/loadCMPData.do" title="客户经理业绩报表"  
            pagination="true" rownumbers="true" style="height:380px" singleSelect="true">
			<thead align="center">
				<tr align="center">
					<%--<th field="comName" width="150"  align="center">时间</th>--%>
					<th field="loanOfficerName" width="150"  align="center">客户经理</th>
					<th field="companyName" width="150"  align="center">分公司</th>
					<th field="lendNum" width="100"  align="center">放款组数</th>
					<th field="lendAmount" width="100"  align="center">放款金额</th>
					<th field="returnedNum" width="100" align="center">回款组数</th>
					<th field="returnedAmount" width="100" align="center">回款金额</th>
					<th field="businessCycle" width="120" align="center">业务周期</th>
					<th field="maxBusinessCycle" width="120" align="center">最长业务周期</th>
					<th field="minBusinessCycle" width="120" align="center">最短业务周期</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			时间：<input id="beginDate" class="easyui-datebox" style="width: 120px;" editable="false"/> 至
				<input id="endDate" class="easyui-datebox" style="width: 120px;"editable="false"/> 
			分公司： <input id="departmentName" style="width: 100px;"/> 
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" href="javascript:search();">搜索</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" plain="true" href="javascript:clear();">清除</a> 
	        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" href="javascript:erportCMPExcel();">导出报表</a>  
	    </div>
	</div>
</body>
</html>