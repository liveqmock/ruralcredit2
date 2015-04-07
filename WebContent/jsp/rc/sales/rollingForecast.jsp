<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'rollingForecast.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/sales/rollingForecast.js"></script>
		<style type="text/css">
.myTH {
	border-right: 1px solid #ccc;
	font-size: 12px;
	font-weight: normal;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	border-top: 1px dotted #ccc;
	overflow: hidden;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	cursor: default;
	border-top: 1px dotted #ccc;
}

.myTD {
	font-size: 12px;
	border-right: 1px dotted #ccc;
	border-bottom: 1px dotted #ccc;
	overflow: hidden;
	padding: 0;
	margin: 0;
}

.myInputBoder {
	border: 1px;
}
</style>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<input id="role" type="hidden" value="${role}" />
			<div id="rollingForecastTabs" class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="滚动预测">
					<div id="" class="easyui-tabs" style="padding: 10px;">
						<div title="放款量（单位/笔）">
							<table width="100%">
								<tr>
									<th width="20%" class="myTH" colspan="2">
										<font style="font-weight: bold; color: red;">${thisYearAndMonth}放款量</font>
									</th>
									<th width="20%" class="myTH">
										${firstYearAndMonth}
									</th>
									<th width="20%" class="myTH">
										${secondYearAndMonth}
									</th>
									<th width="20%" class="myTH">
										${thirdYearAndMonth}
									</th>
									<th width="20%" class="myTH">
										操作
									</th>
								</tr>
								<c:set var="fangKuanLiangIndex" value="-1"></c:set>
								<c:forEach items="${rollingForecastVos0List}" var="firstList">
									<c:forEach items="${firstList}" var="secMap">
										<tr>
											<td width="10%" class="myTH" rowspan="${fn:length(secMap.value)+1}">${secMap.key}</td>
										</tr>
										<c:forEach items="${secMap.value}" var="rList0" varStatus="r0">
											<c:set var="fangKuanLiangIndex" value="${fangKuanLiangIndex +1}"></c:set>
											<tr>
										<th width="20%" class="myTH">
											${rList0.areaDepartmentName}
											<input id="input0${fangKuanLiangIndex}rollingForecastId" name="" value="${rList0.rollingForecastId}" type="hidden" />
											<input id="input0${fangKuanLiangIndex}year" name="" value="${rList0.year}" type="hidden" />
											<input id="input0${fangKuanLiangIndex}month" name="" value="${rList0.month}" type="hidden" />
											<input id="input0${fangKuanLiangIndex}type" name="" value="${rList0.type}" type="hidden" />
											<input id="input0${fangKuanLiangIndex}areaDepartmentId" name="" value="${rList0.areaDepartmentId}" type="hidden" />
											<input id="input0${fangKuanLiangIndex}areaDepartmentName" name="" value="${rList0.areaDepartmentName}" type="hidden" />
										</th>
										<td width="20%" class="myTD">
											<input id="input0${fangKuanLiangIndex}editfirst" type="text" name="input0${fangKuanLiangIndex}editfirstname" value="${rList0.firstMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											<input id="input0${fangKuanLiangIndex}editsecond" type="text" name="input0${fangKuanLiangIndex}editsecondname" value="${rList0.secondMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											<input id="input0${fangKuanLiangIndex}editthird" type="text" name="input0${fangKuanLiangIndex}editthirdname" value="${rList0.thirdMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${rList0.editFlag==1}">
												<span id="spanEdit0${fangKuanLiangIndex}"><a id="a0${fangKuanLiangIndex}" href="javascript:void(0);" onclick="editAndSave(this.id)">编辑</a> </span>
											</c:if>
											<span id="span0${fangKuanLiangIndex}"></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="spanShow0${fangKuanLiangIndex}"> <c:if test="${rList0.histroyShowFlag==1}">
													<a id="ahis0${fangKuanLiangIndex}" href="javascript:void(0)" onclick="openDialog(this.id)">查看修改记录</a>
												</c:if> </span>
										</td>
									</tr>
										</c:forEach>
									</c:forEach>
								</c:forEach>
								<!--<c:forEach items="${rollingForecastVos0}" var="rList0" varStatus="r0">
									<tr>
										<th width="20%" class="myTH">
											${rList0.areaDepartmentName}
											<input id="input0${r0.count-1}rollingForecastId" name="" value="${rList0.rollingForecastId}" type="hidden" />
											<input id="input0${r0.count-1}year" name="" value="${rList0.year}" type="hidden" />
											<input id="input0${r0.count-1}month" name="" value="${rList0.month}" type="hidden" />
											<input id="input0${r0.count-1}type" name="" value="${rList0.type}" type="hidden" />
											<input id="input0${r0.count-1}areaDepartmentId" name="" value="${rList0.areaDepartmentId}" type="hidden" />
											<input id="input0${r0.count-1}areaDepartmentName" name="" value="${rList0.areaDepartmentName}" type="hidden" />
										</th>
										<td width="20%" class="myTD">
											<input id="input0${r0.count-1}editfirst" type="text" name="input0${r0.count-1}editfirstname" value="${rList0.firstMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											<input id="input0${r0.count-1}editsecond" type="text" name="input0${r0.count-1}editsecondname" value="${rList0.secondMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											<input id="input0${r0.count-1}editthird" type="text" name="input0${r0.count-1}editthirdname" value="${rList0.thirdMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
										</td>
										<td width="20%" class="myTD">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${rList0.editFlag==1}">
												<span id="spanEdit0${r0.count-1}"><a id="a0${r0.count-1}" href="javascript:void(0);" onclick="editAndSave(this.id)">编辑</a> </span>
											</c:if>
											<span id="span0${r0.count-1}"></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="spanShow0${r0.count-1}"> <c:if test="${rList0.histroyShowFlag==1}">
													<a id="ahis0${r0.count-1}" href="javascript:void(0)" onclick="openDialog(this.id)">查看修改记录</a>
												</c:if> </span>
										</td>
									</tr>
								</c:forEach>
							--></table>
						</div>
						<sec:authorize ifAnyGranted="${control.rollingForecast_amountShow}">
							<div title="合同金额（单位/万元）">
								<table width="100%">
									<tr>
										<th width="20%" class="myTH" colspan="2">
											<font style="font-weight: bold; color: red;">${thisYearAndMonth}合同金额</font>
										</th>
										<th width="20%" class="myTH">
											${firstYearAndMonth}
										</th>
										<th width="20%" class="myTH">
											${secondYearAndMonth}
										</th>
										<th width="20%" class="myTH">
											${thirdYearAndMonth}
										</th>
										<th width="20%" class="myTH">
											操作
										</th>
									</tr>
									<c:set var="contractInedx" value="-1"></c:set>
									<c:forEach items="${rollingForecastVos1List}" var="firstList">
									<c:forEach items="${firstList}" var="secMap">
										<tr>
											<td width="10%" class="myTH" rowspan="${fn:length(secMap.value)+1}">${secMap.key}</td>
										</tr>
										<c:forEach items="${secMap.value}" var="rList1" varStatus="r1">
											<c:set var="contractInedx" value="${contractInedx +1}"></c:set>
											<tr>
											<th width="20%" class="myTH">
												${rList1.areaDepartmentName}
												<input id="input1${contractInedx}rollingForecastId" name="" value="${rList1.rollingForecastId}" type="hidden" />
												<input id="input1${contractInedx}year" name="" value="${rList1.year}" type="hidden" />
												<input id="input1${contractInedx}month" name="" value="${rList1.month}" type="hidden" />
												<input id="input1${contractInedx}type" name="" value="${rList1.type}" type="hidden" />
												<input id="input1${contractInedx}areaDepartmentId" name="" value="${rList1.areaDepartmentId}" type="hidden" />
												<input id="input1${contractInedx}areaDepartmentName" name="" value="${rList1.areaDepartmentName}" type="hidden" />
											</th>
											<td width="20%" class="myTD">
												<input id="input1${contractInedx}editfirst" type="text" name="input1${contractInedx}editfirstname" value="${rList1.firstMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												<input id="input1${contractInedx}editsecond" type="text" name="input1${contractInedx}editsecondname" value="${rList1.secondMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												<input id="input1${contractInedx}editthird" type="text" name="input1${contractInedx}editthirdname" value="${rList1.thirdMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:if test="${rList1.editFlag==1}">
													<span id="spanEdit1${contractInedx}"><a id="a1${contractInedx}" href="javascript:void(0);" onclick="editAndSave(this.id)">编辑</a> </span>
												</c:if>
												<span id="span1${contractInedx}"></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="spanShow1${contractInedx}"> <c:if test="${rList1.histroyShowFlag==1}">
														<a id="ahis1${contractInedx}" href="javascript:void(0)" onclick="openDialog(this.id)">查看修改记录</a>
													</c:if> </span>
											</td>
										</tr>
										</c:forEach>
									</c:forEach>
									</c:forEach>
									
									<!--<c:forEach items="${rollingForecastVos1}" var="rList1" varStatus="r1">
										<tr>
											<th width="20%" class="myTH">
												${rList1.areaDepartmentName}
												<input id="input1${r1.count-1}rollingForecastId" name="" value="${rList1.rollingForecastId}" type="hidden" />
												<input id="input1${r1.count-1}year" name="" value="${rList1.year}" type="hidden" />
												<input id="input1${r1.count-1}month" name="" value="${rList1.month}" type="hidden" />
												<input id="input1${r1.count-1}type" name="" value="${rList1.type}" type="hidden" />
												<input id="input1${r1.count-1}areaDepartmentId" name="" value="${rList1.areaDepartmentId}" type="hidden" />
												<input id="input1${r1.count-1}areaDepartmentName" name="" value="${rList1.areaDepartmentName}" type="hidden" />
											</th>
											<td width="20%" class="myTD">
												<input id="input1${r1.count-1}editfirst" type="text" name="input1${r1.count-1}editfirstname" value="${rList1.firstMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												<input id="input1${r1.count-1}editsecond" type="text" name="input1${r1.count-1}editsecondname" value="${rList1.secondMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												<input id="input1${r1.count-1}editthird" type="text" name="input1${r1.count-1}editthirdname" value="${rList1.thirdMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
											</td>
											<td width="20%" class="myTD">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:if test="${rList1.editFlag==1}">
													<span id="spanEdit1${r1.count-1}"><a id="a1${r1.count-1}" href="javascript:void(0);" onclick="editAndSave(this.id)">编辑</a> </span>
												</c:if>
												<span id="span1${r1.count-1}"></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="spanShow1${r1.count-1}"> <c:if test="${rList1.histroyShowFlag==1}">
														<a id="ahis1${r1.count-1}" href="javascript:void(0)" onclick="openDialog(this.id)">查看修改记录</a>
													</c:if> </span>
											</td>
										</tr>
									</c:forEach>
								--></table>
							</div>
						</sec:authorize>
					</div>
				</div>
			</div>
			<div id="tol">
					<a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="showHistory();">查看历史</a>
			</div>
			<div id="historyDialog" style="width: 700px; height: 400px;"></div>
		</div>
	</body>
</html>
