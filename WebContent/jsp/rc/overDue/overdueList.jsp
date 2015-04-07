<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'overdueList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=basePath%>scripts/overDue/overdueList.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/overDue/urgeOverdueList.js"></script>
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript">
	$(function() {
		var dic = new DataDictionary();
		dic.addDic("businessType", "businessType");
		dic.addDic("defaultReturnmentWay", "defaultReturnmentWay");
		dic.dicAjax();
	});

	function returnPalnView(value, rowData, rowIndex) {
		var paramId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newReceivablesList_returnPalnView}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='addReturnPlanTab(" + paramId + "," + "\"" + paramNumber + "\""
				+ ")'><font color='#9932cc'>查看还款计划</font></a>&nbsp&nbsp";
		</sec:authorize>
		<sec:authorize ifAnyGranted="${control.newReceivablesList_urgeButton}">
			links+= "&nbsp;&nbsp;<a href='javascript:openDialog(" + paramId +","+1+")'><font color='#9932cc'>催收</font></a>";
		</sec:authorize>
		<sec:authorize ifAnyGranted="${control.newReceivablesList_urgeButton}">
		links+= "&nbsp;&nbsp;<a href='javascript:openOverDueRemindDialog(" + paramId + ")'><font color='#9932cc'>发送短信</font></a>";
		</sec:authorize>
		return links;
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="repaymentSearchTab" class="easyui-tabs"
				style="padding: 10px;">
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
								<font color="red">（可输入业务单号 、客户经理姓名或借款人姓不完整片段进行搜索）</font>
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
									<input id="businessNumber" name="groupNumber" type="text" />
								</td>
								<td align="right">
									借款人姓名：
								</td>
								<td>
									<input id="borrowerName" name="groupName" type="text" />
								</td>
								<td align="right">
                                    客户经理：
								</td>
								<td>
									<input id="customerName" name="businessType" type="text" />
								</td>



								<td align="right">

                                    还款日期:
								</td>
								<td>
                                    <input id="startDate" name="startDate" type="text"
                                           class="easyui-datebox" editable="false" style="width: 122px;" />       至
                                    <input id="endDate" name="endDate" type="text"
                                           class="easyui-datebox" editable="false" style="width: 122px;" />
                                </td>

							</tr>
							<tr>
                                <td align="right">
                                    还款方式：
                                </td>
                                <td>
                                    <input id="defaultReturnmentWay" name="defaultReturnmentWay"
                                           type="text" />
                                </td>
								<td align="right">
                                    分公司名称：
								</td>
								<td colspan="2">
									<input id="companyId" name="companyId" type="text"
										style="width: 200px;" />
								</td>

								<td colspan="3">
									<a class="easyui-linkbutton" onclick="doSearch(1)">搜索</a>

									<a class="easyui-linkbutton" onclick="doClear(1)">清空</a>
                                 <sec:authorize ifAnyGranted="${control.overDueListPage_export}">
                                    <a id="export" class="easyui-linkbutton" plain="true"
                                       iconCls="icon-save" onclick="exportExcel()">导出</a>
                                </sec:authorize>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="逾期列表">
					<table id="overdueGrid">
					</table>
				</div>
			</div>
			<div id="tol">
				<sec:authorize ifAnyGranted="${control.overDueListPage_synOverDue}">
					<a id="insertOverDueList" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" onclick="insertOverDueList()">同步逾期</a>
				</sec:authorize>
			</div>
		</div>
		<div id="urgeDialog" style="width: 900px; height: 410px;">
			<iframe  id="iframeTest" name="myIframe"
				frameborder="0" src="" style="width: 100%; height: 100%;"></iframe>
		</div>
		<div id="overDueRemindMegDiv" style="width: 410px; height: 280px;background-color: #ffffff;" title="逾期短信提醒" >
			<iframe  id="overDueRemindMegFrame" name="overDueRemindMegFrame"
				 frameborder="0" src="" style="width: 100%; height: 100%;"></iframe>
		</div>
	</body>
</html>
