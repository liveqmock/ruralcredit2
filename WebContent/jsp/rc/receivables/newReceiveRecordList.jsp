<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'newReceiveRecordList.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>scripts/receive/newReceiveRecordList.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
		<script type="text/javascript">
	function operate(value, rowData, rowIndex) {
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newReceiveRecordList_revocation}">
		var h = rowData.receivedStatus;
		if (rowData.historyFlag == "F") {
			if ("4" == h || "5" == h||"2"==h) {
				links = "&nbsp;&nbsp;<a href='javascript:void (0);' onclick='cancel(" + rowData.receivedRecordId + "," + h + ")'><font color='red'>撤销登记</font></a>";
			} else {
				links = "&nbsp;&nbsp;<font color='red'>无操作</font>";
			}
		} else {
			links = "&nbsp;&nbsp;<font color='red'>无操作</font>";
		}
		</sec:authorize>
		return links;
	}
</script>
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
								<a class="easyui-linkbutton"  onclick="doSearch(0)">搜索</a>
							</td>
							<td>
								<a class="easyui-linkbutton"  onclick="doClear(0)">清空</a>
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
									<input name="businessNumber" type="text" style="width: 146px;" />
								</td>
								<td align="right">
									借款人姓名：
								</td>
								<td>
									<input name="borrowerName" type="text" style="width: 146px;" />
								</td>
								<td align="right">
									实际收款日期：
								</td>
								<td>
									<input id="receivedTime" name="receivedTime" class="easyui-datebox" type="text" style="width: 146px;" />
								</td>
								<td align="right">
									收款登记类型：
								</td>
								<td>
									<input id="receivedType" name="receivedType" type="text" style="width: 146px;" />
								</td>

							</tr>
							<tr>
								<td align="right">
									收款状态：
								</td>
								<td>
									<input id="receivedStatus" name="receivedStatus" type="text" style="width: 146px;" />
								</td>
								<td align="right">
									是否撤销：
								</td>
								<td>
									<input id="historyFlag" name="historyFlag" type="text" style="width: 146px;" />
								</td>
								<td align="right">
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td align="right">
									<a class="easyui-linkbutton"  onclick="doSearch(1)">搜索</a>
								</td>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="easyui-linkbutton"  onclick="doClear(1)">清空</a>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>
			</div>
			<div style="padding: 10px;">
				<table id="receivedRecordDataGrid"></table>
			</div>
			<div style="padding: 10px; display: none;" >
				<input type="button" onclick="fen();" value="收款自动分配" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="yuyue();" value="个人卡自动预约" />
			</div>
			<div id="menu" style="width: 100px; display: none;" >
				<div id="item1" iconCls="icon-ok">
					进行收款分配(入口)
				</div>
				<div id="item2" iconCls="icon-ok">
					个人卡自动预约(入口)
				</div>
			</div>
		</div>
	</body>
</html>
