<%@ page language="java" import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>量化管理报表</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		var serverName="<%=path%>";
		$(function(){
			defaultHaveScroll("qm");
			/*$("#departmentName").combobox({
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
		    });*/
			departmentComboboxTree("departmentName", false);
		});
		/** 查询 **/
		function search(){
			var param = paramStr();
			$("#qm").datagrid("load",{param:param});
		}
		/** 清除查询条件 **/
		function clear(){
			$("#departmentName").combotree("setValues","");
			$("#groupNumber").val("");
			$("#beginLoanDate").datebox("clear");
			$("#endLoanDate").datebox("clear");
		}
		/** 导出QM报表 **/
		function erportQMExcel(){
			$.messager.confirm('提示信息','确认要导出报表吗?',function(r){
				if (r){
					var url = "<%=basePath%>excelExport/exportQMExcel.do";
					var param = paramStr();
					window.location.href = "<%=basePath%>excelExport/exportQMExcel.do?param="+param+"";
				}
			});
		}
		
		function paramStr(){
			var departmentNameValues=$("#departmentName").combotree("getValues");
			var 	departmentNamestr=departmentNameValues.join(",");
			var paramObj = {
					companyId:departmentNamestr,
				groupNumber:$("#groupNumber").val(),
				beginLoanDate:$("#beginLoanDate").datebox("getValue"),
				endLoanDate:$("#endLoanDate").datebox("getValue")	
			};
			var param = JSON.stringify(paramObj);
			return param;
		}
	</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" style="background: #fafafa;" fit="true">
		<table id="qm" class="easyui-datagrid" toolbar="#toolbar" url="../../../excelExport/loadQMData.do" title="量化管理报表"  
            pagination="true" rownumbers="true" 
            singleSelect="true">
			<thead align="center">
				<tr align="center">
					<th field="comName" width="150" align="center">分公司</th>
					<th field="busNum" width="100" align="center">业务编号</th>
					<th field="infoSor" width="100" align="center">信息来源</th>
					<th field="hTown" width="100" align="center">户籍乡镇</th>
					<th field="mTown" width="100" align="center">经营乡镇</th>
					<th field="planName" width="80" align="center">产品类型</th>
					<th field="applyLimit" width="100" align="center">申请额度</th>
					<th field="offerName" width="120" align="center">申请客户经理</th>
					<th field="maxCapital" width="120" align="center">最大借款额度</th>
					<th field="firAmount" width="120" align="center">首次审批额度</th>
					<th field="contract_amount" width="120" align="center">合同金额</th>
					<th field="loanDate" formatter="DateFormatter" width="100" align="center">放款日期</th>
					<th field="cofDate" formatter="DateTimeFormatter" width="120" align="center">放款确认日期</th>
					<th field="trade_time"  width="120" align="center">财务打款日期</th>
					<th field="loanUse" width="100" align="center">借款用途</th>
					<th field="loanUseIndStair" width="100" align="center">一级借款用途行业</th>
					<th field="loanUseInd" width="120" align="center">借款用途行业</th>
					<th field="revolving_credit" width="120" align="center">循环贷标记</th>
					<th field="loanbalance" width="120" align="center">贷款余额</th>
					<th field="industryName" width="120" align="center">主要收入来源行业</th>
					<th field="industryDetails" width="120" align="center">主要收入来源明细</th>
					<th field="incomeFrequency" width="120" align="center">主要收入频次</th>
					<th field="projectCT" width="120" align="center">收入项目数</th>
					<!-- 
					<th field="manageFre" width="200" align="center">经营现金流入项目频率</th>
					<th field="manageInd" width="200" align="center">经营现金流入项目行业</th>
					<th field="homePro" width="200" align="center">家庭现金流入项目</th>
					<th field="homeFre" width="200" align="center">家庭现金流如频率</th>
					 -->
				</tr>
			</thead>
		</table>
		<div id="toolbar"> 
			分公司名称： <input id="departmentName" style="width: 200px;"/> 
			业务单号：<input id="groupNumber" style="width: 120px;"/>
			放款日期：<input id="beginLoanDate" class="easyui-datebox" style="width: 120px;" editable="false"/> 至
				<input id="endLoanDate" class="easyui-datebox" style="width: 120px;"editable="false"/> 
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" href="javascript:search();">搜索</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" plain="true" href="javascript:clear();">清除</a> 
	        <sec:authorize ifAnyGranted="${control.exportQMExcel_erportQMExcel}">
            		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" href="javascript:erportQMExcel();">导出Excel</a>
		     </sec:authorize> 
	          
	    </div>
	</div>
</body>
</html>