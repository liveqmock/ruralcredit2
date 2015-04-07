<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String role=request.getParameter("role");
StringBuffer conditions=new StringBuffer("");
String separator="?";
if("rw".equals(role)){
	conditions.append(separator).append("rw=rw");
	separator="&";
}else if("rf".equals(role)){
	conditions.append(separator).append("rf=rf");
}
%>

<html>
	<head>
		<base href="<%=basePath%>"/>
		<link href="<%=basePath%>css/base.css" rel="stylesheet" type="text/css" />
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName="<%=path%>";
			var conditions="";
			<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
				conditions="<%=conditions.toString()%>";
			<%}%>
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/receive/recevieList.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/receive/base.js"></script>
		<script type="text/javascript">
			function optionFormatter(value) {
				var links ="<a href='javascript:look();'>查看</a>";
				if ($.trim(value) == "3"||$.trim(value) == "0") {
					<sec:authorize ifAnyGranted="${control.financeMoney_financeReceive}"> 
						links=links+"|<a href='javascript:receive();'>预约</a>";
					</sec:authorize>
					return links;
				} else if ($.trim(value) == "1") {
					<sec:authorize ifAnyGranted="${control.financeMoney_receiveUndo}"> 
						links+= "|<a href='javascript:undo();'>撤销</a>|";
					</sec:authorize>
					return links;
				} else{
					return links;
				}
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
			 			<td>财务状态：</td>
			 			<td>
			 				<select id="loansStatus" name="loansStatus"  style="width: 120px;">
							</select>
			 			</td>
						<td align="center">部门：</td>
						<td>
							<select id="companyName" name="companyName"  style="width: 120px;height:60px;">
							</select>
						</td>
						<td>订单号：</td>
						<td><input id="bizId" name="bizId" type="text" size="17" class="ipttext"/></td>
			 			<td>财务类型：</td>
			 			<td>
				 			<select id="receivedType" name="receivedType"  style="width: 100px;">
							</select>
						</td>
			 			<td>资金来源：</td>
			 			<td>
				 			<select id="capitalSource" name="capitalSource"  style="width: 100px;">
							</select>
						</td>
			 			<td>业务类型：</td>
			 			<td>
				 			<select id=businessType class="easyui-combobox" name="businessType"  style="width: 100px;">
							</select>
						</td>
			 		</tr>
			 		<tr>
						<td>业务单号：</td>
						<td>
							<input id="groupNumber" name="groupNumber" class="ipttext" style="width: 120px;"/>
						</td>
			 			<td>放款日期：</td>
			 			<td><input id="beginLoanDate" name="beginLoanDate" class="easyui-datebox" style="width: 120px;"/> </td>
			 			<td align="center">至</td>
			 			<td> <input id="endLoanDate" name="endLoanDate" class="easyui-datebox" style="width: 120px;"/></td>
			 			<td align="left" colspan="4"><a class="easyui-linkbutton" href="javascript:searchAdvanced(1);">搜索</a>
			 				&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="javascript:searchCancel1(1);">清除</a>
			 				<sec:authorize ifAnyGranted="${control.financeMoney_financeReceive}"> 
			 					&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="javascript:export();">导出excel</a>
			 					&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="javascript:batchReceive();">批量预约</a>
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
	<div id="receiveButton">
		<sec:authorize ifAnyGranted="${control.financeMoney_financeReceive}"> 
			<a class="easyui-linkbutton" id="toReceive" onclick="toReceive()">预约</a>  
		</sec:authorize>
		<a class="easyui-linkbutton" id="toClose" onclick="toClose()">关闭</a>  
	</div>
	<div id="look"  style="width: 500px; height: 300px;">
   	</div>
   	<div id="receive"  style="width: 500px; height: 300px;">
   	</div>
	</body>
</html>
