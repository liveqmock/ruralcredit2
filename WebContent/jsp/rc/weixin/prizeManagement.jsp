<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

		<title>My JSP 'prizeManagement.jsp' starting page</title>

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
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/weixin/prizeManagement.js"></script>
		<script type="text/javascript">
	function operationFN(value, rowData, rowIndex) {
		var wxUpriRecordId = rowData.wxUpriRecordId;
		var upriRecordCode = rowData.upriRecordCode;
		var isReceive = rowData.isReceive;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.prizeManagement_openReceiveDialog}">
		if (isReceive == "0") {
			links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openDialog(" + wxUpriRecordId + "," + "\"" + upriRecordCode + "\""
					+ ")'><font color='#9932cc'>领取</font></a>";
		} else if (isReceive == "1") {
			links = "&nbsp;&nbsp;<font color='green'>无操作</font>";
		}
		</sec:authorize>
		return links;
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="salesSearchTab" class="easyui-tabs" style="padding: 10px;">
				<div title="礼品兑换">
					<form id="searchForm">
						<table>
							<tr>
								<td width="11%">
									兑奖人：
								</td>
								<td width="11%">
									<input id="recpriName" name="recpriName" type="text" />
								</td>
								<td width="11%">
									分公司名称：
								</td>
								<td colspan="3"">
									<input id="branchofficeIds" name="branchofficeIds" type="text" style="width: 345px;" />
								</td>
								<td width="11%">
									&nbsp;&nbsp;&nbsp;兑奖日期：
								</td>
								<td width="11%" colspan="2">
									<input class="easyui-datebox" id="beginTime" name="beginTime" type="text" style="width: 108px;" />
									-
									<input class="easyui-datebox" id="endTime" name="endTime" type="text" style="width: 108px;" />
								</td>
								<td width="1%">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="11%">
									接待员工：
								</td>
								<td width="11%">
									<input id="receiverName" name="receiverName" type="text" />
								</td>
								<td width="11%">
									状态：
								</td>
								<td width="11%">
									<input id="isReceive" name="isReceive" type="text" class="easyui-combobox" style="width: 90px;" />
								</td>
								<td width="11%">
									奖品类型：
								</td>
								<td width="11%">
									<input id="prizeType" name="prizeType" type="text" class="easyui-combobox" style="width: 90px;" />
								</td>
								<td width="11%">
									&nbsp;
								</td>
								<td width="11%" align="right">
									<a id="searchButton" class="easyui-linkbutton" onclick="searchPrize();">搜索</a>
								</td>
								<td width="11%" align="right">
									<a id="clearButton" class="easyui-linkbutton" onclick="clearPrize();">清空</a>
								</td>
								<td width="1%">
									&nbsp;
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="list" class="easyui-tabs" style="padding: 10px;">
				<div title="兑奖记录列表">
					<table id="upriRecordList"></table>
				</div>
			</div>
			<div id="dialog" style="width: 260px; height: 210px; overflow: hidden;" buttons="#dialogButtons">
				<form id="receiveForm" novalidate>
					<table>
						<tr>
							<td width="30%" align="right">
								兑奖编号
							</td>
							<td width="60%">
								<input id="upriRecordCode" name="upriRecordCode" class="easyui-validatebox" required="true" maxlength="8" type="text" onblur="checkCode();" />
							</td>
							<td width="10%">
								<span id="isRight"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="spanhh"></span>

							</td>
							<td width="10%">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								实际兑奖人
							</td>
							<td width="60%">
								<input id="realRecpriName" name="realRecpriName" class="easyui-validatebox" validType="nameHQ" maxlength="8" required="true" type="text" />
							</td>
							<td width="10%">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								&nbsp;
							</td>
							<td width="60%">
								<input id="upriRecordCodeH" type="hidden" />
								<input id="wxUpriRecordId" name="wxUpriRecordId" type="hidden" />
							</td>
							<td width="10%">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								联系方式
							</td>
							<td width="60%">
								<input id="realRecpriPhone" name="realRecpriPhone" class="easyui-validatebox" validType="phoneNumberHQ" maxlength="11" required="true"
									type="text" />
							</td>
							<td width="10%">
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="dialogButtons">
				<a class="easyui-linkbutton" id="receiveButton" href="javascript:void(0);" onclick="receive()">领取</a>
				<a class="easyui-linkbutton" id="cancelReceiveButton" href="javascript:void(0);" onclick="cancelReceive()">取消</a>
			</div>
		</div>
	</body>
</html>

