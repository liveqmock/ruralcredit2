<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java"
	import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils"
	pageEncoding="utf-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<!-- var serverName="<%=path%>"; -->
		
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=basePath%>scripts/business/creditSpecialApply.js"></script>

		<script type="text/javascript">
		var serverName="<%=path%>";
	function returnPalnView(value,rowData,rowIndex) {
		var paramId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var auditSpecialApplyState = rowData.auditSpecialApplyState;
		var specialApplyType=rowData.specialApplyType;
		var specialApplyId=rowData.specialApplyId;
		var links = "";
		<sec:authorize ifAnyGranted="${control.specialApplyJsp_specialApplyView}">
		 links="&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openDialog("+paramId+","+specialApplyId+")'><font color='#9932cc'>查看&nbsp&nbsp</font></a>";
		</sec:authorize>
		<sec:authorize ifAnyGranted="${control.specialApplyJsp_specialApplyAudit}">
		if ("0" == auditSpecialApplyState) {
		links =links+ "<a href='javascript:void(0)' onclick='openDialogAudit("+paramId+","+specialApplyId+");'><font color='#9932cc'>审批&nbsp&nbsp</font></a>";
		}
		</sec:authorize>
		return links;
	}
</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">

			<div id="loanListTab" class="easyui-tabs" style="padding: 10px;">
				<div title="条件查询" style="padding: 10px;">
					<table width="100%" border="0">
						<tr>
							<td style="width:150px;" colspan="2">
								业务单号：
								<input id="businessNumber" name="businessNumber"  />
							</td>
							<td align="left" style="width: 150px;" colspan="2">
								借款人姓名：
							
								<input id="name" name="name" style="width: 100px;" />
							</td>
							<td align="left">
								客户经理：
								<input id="loanOfficerName" name="loanOfficerName" style="width: 100px;" />
							</td>
							</tr>
							<tr>
							<td align="left" colspan="2"  style="width: 150px;">
								分公司名称:
								<input id="companyId" type="text"  style="width: 200px;"/>
							</td>
							<td align="left" >
								申请状态:
								<input style="width: 120px;" id="auditSpecialApplyState" type="text" />
							</td>
							<td colspan="0" align="left">
								<a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;&nbsp;
								<a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a>&nbsp;&nbsp;
						</tr>
						
					</table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="特殊情况申请列表">
					<table id="specialApplyList">
					</table>
				</div>
			</div>
		</div>
			<!--<div id="specialApply" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest" name="myIframe"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
			</div>
			--><!--<div id="specialApplyView" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest2" name="myIframe2"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
			</div>
	--></body>
</html>
