<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String role=request.getParameter("role");
StringBuffer conditions=new StringBuffer("");
String separator="?";
if(CommonUtil.isNotEmpty(role)){
	conditions.append(separator).append("role=").append(role);
	separator="&";
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<%--<link href="<%=basePath%>css/base.css" rel="stylesheet" type="text/css" />--%>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName="<%=path%>";
			var conditions="";
			<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
				conditions="<%=conditions.toString()%>";
			<%}%>
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/payment/paymentList.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/receive/base.js"></script>
		<script type="text/javascript">
			function optionFormatter(value) {
				var links="<a href='javascript:look();'>查看</a>";
			if ($.trim(value) == "2"||$.trim(value) == "0"|| $.trim(value) == "4") {
				return links;
			} else if ($.trim(value) == "1") {
				<sec:authorize ifAnyGranted="${control.financeMoney_paymentUndo}"> 
					links+="|<a href='javascript:undo();'>撤销预约</a>";
				</sec:authorize>
				return links;
			} else if ($.trim(value) == "3") {
				<sec:authorize ifAnyGranted="${control.financeMoney_financePayment}"> 
					links+="|<a href='javascript:isPayment();'>预约</a>";
					links+="|<a href='javascript:undo();'>撤销预约</a>";
				</sec:authorize>
				return links;
			}
		}
			
			function exportFinanceReport(){
				var loansStatus = $("#loansStatus").combobox("getValue");
                var companyIds = $("#companyName").combotree("getValues").join(',');
//				var companyName = $("#companyName").combobox("getValue");
				var groupNumber = $("#groupNumber").val();
				var bizId = $("#bizId").val();
				var groupName = $("#groupName").val();
				var beginLoanDate = $("#beginLoanDate").datebox("getValue");
				var endLoanDate = $("#endLoanDate").datebox("getValue");
				var params = "loansStatus="+loansStatus+"&companyId="
							+companyIds+"&groupNumber="+groupNumber+"&bizId="+bizId
							+"&groupName="+groupName
							+"&beginLoanDate="+beginLoanDate+"&endLoanDate="+endLoanDate;
				window.location.href="creditgroup/exportCreditApplicationForFinanceLoan.do?"+params;
			}
		</script>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center">
				 <div id="receiveListTab" class="easyui-tabs" style="padding: 10px;">
 				<div title="模糊查询">
					<br />
					<table>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模糊查询条件：
							</td>
							<td>
								<input id="fuzzyValue" name="fuzzyValue" type="text" style="width: 150px;" />
							</td>
							<td>
								<a class="easyui-linkbutton"  onclick="javascript:searchAdvanced(0);">搜索</a>
							</td>
							<td>
								<a class="easyui-linkbutton"  onclick="javascript:searchCancel1(0);">清空</a>
							</td>
							<td>
								<font color="red">（可输入业务单号 或借款人姓不完整片段进行搜索）</font>
							</td>
						</tr>

					</table>
					<br />
				</div>
				<div title="高级查询">
				 <table>
				 	<tr>
				 		<td>状态:</td>
				 		<td>
				 			<select id="loansStatus" name="loansStatus"  style="width: 120px;">
							</select>
						</td>
				 		<td>业务单号：</td>
				 		<td><input id="groupNumber" name="groupNumber" class="ipttext" style="width: 120px;"/></td>
				 		<td>订单号：</td>
				 		<td><input id="bizId" name="bizId" class="ipttext" style="width: 120px;"/></td>
				 		<td>组长姓名：</td>
				 		<td><input id="groupName" name="groupName" class="ipttext" style="width: 120px;"/></td>
				 	</tr>
				 	<tr>
				 		<td>放款日期：</td>
				 		<td><input id="beginLoanDate" name="beginLoanDate" class="easyui-datebox" style="width: 120px;"/> </td>
				 		<td>至</td>
				 		<td><input id="endLoanDate" name="endLoanDate" class="easyui-datebox" style="width: 120px;"/></td>
				 		<td>分公司名称：</td>
				 		<td>
				 			<select id="companyName" name="companyName" class="easyui-combobox"  style="width: 120px;">
						  </select>
					    </td>
				 		<td colspan="2" align="center">
				 			<a class="easyui-linkbutton" href="javascript:searchAdvanced(1);">搜索</a>
				 			<a class="easyui-linkbutton" href="javascript:searchCancel1(1);">清除</a>
				 			<sec:authorize ifAnyGranted="${control.financeMoney_exportFinanceReport }">
				 			<a class="easyui-linkbutton" onclick="javascript:exportFinanceReport();">导出Excel</a>
				 			</sec:authorize>
			 			</td>
				 	</tr>
				 </table>
			</div>
			</div>
			<div style="padding: 10px;">
				<table id="loanList">
				</table>
			</div>
		</div>
	<div id="paymentButton">
		<sec:authorize ifAnyGranted="${control.financeMoney_financePayment}"> 
			<a class="easyui-linkbutton" id="toPayment" onclick="toPayment()">预约</a>  
		</sec:authorize>
		<a class="easyui-linkbutton" id="toClose" onclick="toClose()">关闭</a>  
	</div>
	<div id="look"  style="width: 500px; height: 300px;">
   	</div>
   	<div id="payment"  style="width: 500px; height: 300px;">
   	</div>
	</body>
</html>
