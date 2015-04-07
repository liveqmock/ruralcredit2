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

		<title>My JSP 'rollingForecastEditHistory.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="rorecastHistoryTabs" class="easyui-tabs" style="padding: 10px;">
				<div title="${show}">
					<table class="easyui-datagrid">
						<thead>
							<tr>
								<th width="50" field="queryRollingForecasts.rollingForecastId">
									序列
								</th>
								<th width="100" field="queryRollingForecasts.firstMonth">
									${first}
								</th>
								<th width="100" field="queryRollingForecasts.secondMonth">
									${second}
								</th>
								<th width="100" field="queryRollingForecasts.thirdMonth">
									${third}
								</th>
								<th width="120" field="queryRollingForecasts.operatorName">
									操作人姓名
								</th>
								<th width="160" field="queryRollingForecasts.operateTime">
									操作时间
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryRollingForecasts}" var="rf" varStatus="r">
								<tr>
									<td>
										${r.count}
									</td>
									<td>
										${rf.firstMonth}
									</td>
									<td>
										${rf.secondMonth}
									</td>
									<td>
										${rf.thirdMonth}
									</td>
									<td>
										${rf.operatorName}
									</td>
									<td>
										<fmt:formatDate value="${rf.operateTime}" pattern='yyyy-MM-dd HH:mm:ss' />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
