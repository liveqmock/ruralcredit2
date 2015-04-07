<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'subjectManagement.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
	<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/weixin/subjectManagement.js"></script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<form id="uploadFileForm" action="" method="post"
				enctype="multipart/form-data">
				<table width="100%">
					<tr align="center">
						<td align="center">
							批量上传网点详情信息（请上传Excel文件）
						</td>
					</tr>
					<tr align="center">
						<td align="center">
							<input id="file" class="easyui-linkbutton" name="UpLoadFile"
								type="file" />
						</td>
					</tr>
					<tr align="center">
						<td align="center">
							<a id="uploadQuestionExcelButton" class="easyui-linkbutton"
								onclick="uploadQuestionExcel()">上传题目</a>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%">
					<tr align="center">
						<td align="center">
							<a id="prizeButton" class="easyui-linkbutton"
								onclick="prize()">生成奖品</a>
						</td>
					</tr>
			</table>
		</div>
	</body>
</html>
