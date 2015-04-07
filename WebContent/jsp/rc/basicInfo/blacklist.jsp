<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'blacklist.jsp' starting page</title>

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
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/blacklist.js"></script>

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
								<font color="red">（可输入借款人姓名或身份证号不完整片段进行搜索）</font>
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
									身份证号：
								</td>
								<td>
									<input id="blackCredentialsNumber" name="blackCredentialsNumber" type="text" />
								</td>
								<td align="right">
									客户姓名：
								</td>
								<td>
									<input id="blackName" name="blackName" type="text" />
								</td>
								<td align="right">
									记录状态：
								</td>
								<td>
									<input id="historyFlag" name="historyFlag" type="text"  />
								</td>
								<td>
									<a class="easyui-linkbutton" onclick="doSearch(1)">搜索</a>
								</td>
								<td>
									<a class="easyui-linkbutton" onclick="doClear(1)">清空</a>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#blackTools">
				<div title="黑名单列表">
					<table id="blacklistList">
					</table>
				</div>
			</div>
		</div>
		<div id="blackTools">
			<a class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="$('#blacklistDialogINSERT').dialog('open');">添加黑名单</a>
		</div>
		<div id="blacklistDialog" style="width: 700px; height: 250px;">
			<form id="blackForm" novalidate>
				<table width="100%">
					<tr>
						<td align="right">
							客户姓名：
						</td>
						<td>
							<input id="blackNameA" name="name" class="easyui-validatebox" required="true" size="30" />
							<input id="blacklistId" name="blacklistId" type="hidden"/>
						</td>
						<td align="right">
							身份证号：
						</td>
						<td>
							<input id="blackCredentialsNumberA" name="credentialsNumber" readonly="readonly" size="30" />
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td>
							&nbsp;
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4" align="center">
							<textarea id="removeReason" name="removeReason" class="easyui-validatebox" validType="length[0,250]" maxlength="250" rows="3" cols="45" onclick="textCount('removeReason', 'removeReasonSpan', 250);" required="true" style="width: 98%; font-style: italic; color: black;"></textarea>
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="removeReasonSpan"></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="blacklistDialogINSERT" style="width: 700px; height: 250px;">
			<form id="blackFormINSERT" novalidate>
				<table width="100%">
					<tr>
						<td align="right">
							客户姓名：
						</td>
						<td>
							<input id="blackNameINSERT" name="name" class="easyui-validatebox" required="true" size="30"/>
						</td>
						<td align="right">
							身份证号：
						</td>
						<td>
							<input id="blackCredentialsNumberINSERT" name="credentialsNumber" onclick="$('#show').html('')" class="easyui-validatebox" onblur="checkIdNumber(this.value)" onkeypress="if(event.which==32){return false;}" validType="idnumberAll"  required="true" size="30"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td>
							&nbsp;
						</td>
						<td align="right">
							<input id="check" type="hidden" />
						</td>
						<td>
							&nbsp;<span id="show">
							</span>
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4" align="center">
							<textarea id="blacklistedReasonINSERT" name="blacklistedReason" class="easyui-validatebox" validType="length[0,250]" maxlength="250"  rows="3" cols="45" onclick="textCount('blacklistedReasonINSERT', 'blacklistedReasonSpanINSERT', 250);" required="true" style="width: 98%; font-style: italic; color: black;"></textarea>
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;<span id="blacklistedReasonSpanINSERT"></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
