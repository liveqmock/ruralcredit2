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

		<title>My JSP 'rorecastHistoryDetail.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
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
			<div id="rorecastHistoryTabs" class="easyui-tabs" style="padding: 10px;">
				<div title="${show}">
					<table width="100%">
						<tr>
							<th class="myTH">
								预测月份
							</th>
							<th class="myTH" colspan="4">
								预测数据
							</th>
						</tr>
						<tr>
							<th width="15%" class="myTH" rowspan="${fn:length(rollingForecasts)+1}">
								${ym}
							</th>
							<th width="25%" class="myTH">
								营业部名称
							</th>
							<th width="20%" class="myTH">
								${first}
							</th>
							<th width="20%" class="myTH">
								${second}
							</th>
							<th width="20%" class="myTH">
								${third}
							</th>
						</tr>
						<c:forEach items="${rollingForecasts}" var="rf" varStatus="r">
							<tr>
								<th width="20%" class="myTH">
									${rf.areaDepartmentName}
								</th>
								<td width="20%" class="myTD">
									<input id="input0${r0.count-1}editsecond" type="text" name="input0${r0.count-1}editsecondname" value="${rf.firstMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="${rf.type*2}" />
								</td>
								<td width="20%" class="myTD">
									<input id="input0${r0.count-1}editthird" type="text" name="input0${r0.count-1}editthirdname" value="${rf.secondMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="${rf.type*2}" />
								</td>
								<td width="20%" class="myTD">
									<input id="input0${r0.count-1}editthird" type="text" name="input0${r0.count-1}editthirdname" value="${rf.thirdMonth}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="${rf.type*2}" />
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
