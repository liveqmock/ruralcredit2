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

		<title>My JSP 'discountConfiguration.jsp' starting page</title>

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
		<script type="text/javascript" src="<%=basePath%>/scripts/system/lendingconfiguration.js"></script>
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
	border: 2px;
}
</style>
<script type="text/javascript">

	$(function(){
	
		var dic = new DataDictionary();
		dic.addDic("trustLending_Y", "trustLending");
		dic.addDic("debentureTransfer_Y", "debentureTransfer");
		// dic.addDic("lendingChannel_Y", "lendingChannel");
		dic.dicAjax();
	})
	
</script>
	</head>
	<body class="easyui-layout">
		<div region="west" title="营业部" style="width: 400px; padding: 10px;">
			<ul id="departmentTree"></ul>
		</div>
		<div region="center" title="信息" style="padding: 20px;">
			<input id="areaDepartmentId" type="hidden" />
			<input id="areaDepartmentName" type="hidden" />
			<span id="name" style="font-size: x-large; color: #4B0082;">全部</span>
			<table id="discountConfigurationList" toolbar="#toolbar"></table>
			<div id="configDialog" title="配置" class="easyui-dialog" style="width: 400px; height: 200px;" buttons="#buttons" closed="true" modal="true">
				<form id="configForm" >
					<table>
						<tr>
							<th class="myTH" width="200px">
								营业部名称：
							</th>
							<td class="myTD" width="200px">
								<input id="discountConfigurationIdForm" type="hidden" name="discountConfigurationId" />
								<input id="areaDepartmentIdForm" type="hidden" name="areaDepartmentId" />
								<input id="areaDepartmentNameForm" name="areaDepartmentName" type="text" style="width: 200px;" class="easyui-validatebox" required="true" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th class="myTH" width="200px">
								信托放款：
							</th>
							<td class="myTD" width="200px">
								<input id="trustLending" name="trustLending" style="width: 204px;" type="text" class="easyui-combobox" />
							</td>
						</tr>
						<tr>
							<th class="myTH" width="200px">
								债权转让放款：
							</th>
							<td class="myTD" width="200px">
								<input id="debentureTransfer" name="debentureTransfer" style="width: 204px;" type="text" class="easyui-combobox" />
								<input id="lendingConfigurationId" type="hidden" name="lendingConfigurationId"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="buttons">
			<a class="easyui-linkbutton" id="saveButton" href="javascript:void(0);" onclick="save();" plain="true" iconCls="icon-save">保存</a>
			<a class="easyui-linkbutton" id="cancelButton" href="javascript:void(0);" onclick="cancel();" plain="true" iconCls="icon-cancel">关闭</a>
		</div>
		<div id="toolbar">
			<a class="easyui-linkbutton" id="addOneButton" href="javascript:void(0);" onclick="addOne();" plain="true" iconCls="icon-addOne">新增</a>
			<a class="easyui-linkbutton" id="editButton" href="javascript:void(0);" onclick="edit();" plain="true" iconCls="icon-edit">编辑</a>
			<a class="easyui-linkbutton" id="removeButton" href="javascript:void(0);" onclick="removeOne();" plain="true" iconCls="icon-remove">删除</a>
		</div>
	</body>
</html>
