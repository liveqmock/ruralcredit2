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
	<title>业务详情报表</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		var serverName="<%=path%>";
		$(function(){
		//var dicReport2 = new DataDictionary();
		//dicReport2.addDic("auditStatus", "auditStatus");
		//dicReport2.dicAjax();
		$("#auditStatus").combobox({
		url:serverName+"/dicRequest/getSpecifiedDic.do?section=auditStatus",
		textField:"codeVlue",
		valueField:"codeKey",
		editable: false,
		multiple:true,
		separator:","
	});
			defaultHaveScroll("bd");
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
			$("#bd").datagrid("load",{param:param});
		}
		/** 清除查询条件 **/
		function clear(){
			$("#departmentName").combotree("setValues","");
			$("#groupNumber").val("");
			$("#auditStatus").combobox("clear");
			$("#beginLoanDate").datebox("clear");
			$("#endLoanDate").datebox("clear");
			$("#createDateStart").datebox("clear");
			$("#createDateEnd").datebox("clear");
		}
		/** 导出BD报表 **/
		function erportBDExcel(){
			$.messager.confirm('提示信息','确认要导出报表吗?',function(r){
				if (r){
					var url = "<%=basePath%>excelExport/exportBDExcel.do";
					var param = paramStr();
					window.location.href = "<%=basePath%>excelExport/exportBDExcel.do?param="+param+"";
				}
			});
		}
		
		function paramStr(){
			
			var departmentNameValues=$("#departmentName").combotree("getValues");
			var 	departmentNamestr=departmentNameValues.join(",");
			var auditStatus =$("#auditStatus").combobox("getValues");
			var auditStatusStr = auditStatus.join(",");
			var paramObj = {
				companyId:departmentNamestr,
				groupNumber:$("#groupNumber").val(),
				auditStatus:auditStatusStr,
				beginLoanDate:$("#beginLoanDate").datebox("getValue"),
				endLoanDate:$("#endLoanDate").datebox("getValue"),
				createDateStart : $("#createDateStart").datebox("getValue"),
				createDateEnd : $("#createDateEnd").datebox("getValue")
			};
			var param = JSON.stringify(paramObj);
			return param;
		}
	</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" style="background: #fafafa;" fit="true" >
		<table id="bd" class="easyui-datagrid" toolbar="#toolbar" url="../../../excelExport/loadBDData.do" title="业务详情报表"  
            pagination="true" rownumbers="true" height="100%"
            singleSelect="true">
			<thead align="center">
				<tr align="center">
					<th field="comName" width="150" align="center">分公司</th>
					<th field="busNum" width="100" align="center">业务编号</th>
					<th field="identityNum" width="100" align="center">身份证号</th>
					<th field="infoSor" width="100" align="center">信息来源</th>
					<th field="mTown" width="100" align="center">经营乡镇</th>
					<th field="planName" width="80" align="center">产品类型</th>
					<th field="auditStatusShow" width="80" align="center">业务状态</th>
					<th field="applyLimit" width="100" align="center">申请额度</th>
					<th field="applyTime" formatter="DateFormatter" width="100" align="center">申请时间</th>
					<th field="offerName" width="120" align="center">申请客户经理</th>
					<th field="maxCapital" width="120" align="center">最大借款额度</th>
					<th field="create_date" width="120" align="center">创建时间</th>
					<th field="submitTime" formatter="DateFormatter" width="120" align="center">提交时间</th>
					<th field="firLoanTime" formatter="DateFormatter" width="120" align="center">首次放款登记日期</th>
					<th field="endLoanTime" formatter="DateFormatter" width="120" align="center">最终放款登记日期</th>
					<th field="loanDate" formatter="DateFormatter" width="100" align="center">放款日期</th>
					<th field="loanOfficer" width="120" align="center">放款登记客户经理</th>
					<th field="cofDate" formatter="DateTimeFormatter" width="120" align="center">放款确认日期</th>
					<th field="tradeDate" formatter="DateTimeFormatter" width="120" align="center">财务转账日期</th>
					<th field="loanUse" width="100" align="center">借款用途</th>
					<th field="loanUseIndStair" width="100" align="center">一级借款用途行业</th>
					<th field="loanUseInd" width="120" align="center">借款用途行业</th>
					<th field="minExamDate" formatter="DateFormatter" width="120" align="center">首次审批日期</th>
					<th field="maxExamDate" formatter="DateFormatter" width="120" align="center">最终审批日期</th>
					<th field="firAmount" width="120" align="center">首次审批额度</th>
					<th field="endAmount" width="120" align="center">最终审批额度</th>
					<th field="contract_amount" width="120" align="center">合同金额</th>
					<th field="revolving_credit" width="120" align="center">循环贷标记</th>
					<th field="loanbalance" width="120" align="center">贷款余额</th>
					<th field="industryName" width="120" align="center">主要收入来源行业</th>
					<th field="industryDetails" width="120" align="center">主要收入来源明细</th>
					<th field="incomeFrequency" width="120" align="center">主要收入频次</th>
					<th field="projectCT" width="120" align="center">收入项目数</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="height: 60px;"> 
		<table width="100%" border="0">
		<tr>
		 <td style="width: 260px;">
			分公司名称： <input id="departmentName" style="width: 180px;"/> 
			</td>
			<td style="width:180px;">
			业务单号：<input id="groupNumber" style="width:120px;"/>
			</td>
			<td style="width:180px;">
			业务状态：<input id="auditStatus"  style="width:120px;"/>
			</td>
			<td style="width:150px;" colspan="2">
			放款日期：<input id="beginLoanDate" class="easyui-datebox" editable="false"/> 至
				<input id="endLoanDate" class="easyui-datebox" editable="false"/> 
				</td>
			
				</tr>
				<tr>
				<td colspan="2">创建时间&nbsp;&nbsp;&nbsp;：&nbsp;<input id="createDateStart"  class="easyui-datebox" editable="false"/>
				至
				<input id="createDateEnd" class="easyui-datebox" editable="false"/>
				</td>
				<td style="width:150px;" colspan="2" align="right">
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" href="javascript:search();">搜索</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" plain="true" href="javascript:clear();">清除</a> 
			 <sec:authorize ifAnyGranted="${control.exportBusinessExcel_erportBDExcel}">
            		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" href="javascript:erportBDExcel();">导出报表</a>
		     </sec:authorize> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	          </td>
	          </tr>
	    </table>
	    </div>
	</div>
</body>
</html>