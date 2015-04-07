<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'LoanPortfolioList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>scripts/payment/LoanPortfolioList.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="repaymentSearchTab" class="easyui-tabs" style="padding: 10px;">
				<div title="模糊查询">
					<br />
					<table>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模糊查询条件：
							</td>
							<td>
								<input id="fuzzy" name="fuzzy" type="text" style="width: 150px;" />
							</td>
							<td>
								<a class="easyui-linkbutton" onclick="doSearch(0)">搜索</a>
							</td>
							<td>
								<a class="easyui-linkbutton" onclick="doClear(0)">清空</a>
							</td>
							<td>
								<font color="red">（可输入业务单号 或借款人姓不完整片段进行搜索）</font>
							</td>
						</tr>

					</table>
					<br />
				</div>
				<div title="高级查询">
					<form id="searchform" method="post">
						<br />
						<table width="100%">
							<tr>
								<td align="right">
									业务单号：
								</td>
								<td>
									<input id="businessNumber" name="businessNumber" type="text" />
								</td>
								<td align="right">
									借款人姓名：
								</td>
								<td>
									<input id="borrowerName" name="borrowerName" type="text" />
								</td>
								<td nowrap="nowrap" align="right">
										分公司名称：
									</td>
									<td nowrap="nowrap" align="left" width="30%" colspan="3">
										<input id="companyId" style="width: 300px;" type="text" />
									</td>
									</tr>
									<tr>
									<td colspan="3"></td>
									</tr>
								<tr>
								<td align="right">
									起始日期：
								</td>
								<td>
									<input id="startDate" name="startDate" type="text" class="easyui-datebox" editable="false"/>
								</td>
								<td align="right">
									结束日期：
								</td>
								<td>
									<input id="endDate" name="endDate" type="text" class="easyui-datebox" editable="false"/>
								</td>
								<td align="right">
									<a class="easyui-linkbutton" onclick="doSearch(1)">搜索放款详情统计</a>
								</td>
								<sec:authorize ifAnyGranted="${control.LoanPortfolioList_LoanMonthStatistics}"> 
								<td align="right">
									<a class="easyui-linkbutton" onclick="doSearchMonth()">搜索放款月度统计</a>
								</td>
								</sec:authorize>
								<td >
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" onclick="doClear(1)">清空</a>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>
			</div>
			<div id="i" class="easyui-tabs" style="padding: 10px;">
				<div title="放款详情统计列表">
					<div style="padding: 10px;">
						<table id="loanPortfolioList"></table>
					</div>
				</div>
				<div title="放款月度统计列表">
					<div style="padding: 10px;">
						<table id="loanCurrentList"></table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
