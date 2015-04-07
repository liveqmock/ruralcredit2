<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<jsp:include page="../include/easyui.jsp"></jsp:include>

		<!--<link rel="stylesheet" type="text/css" href="styles.css">
	    <script type="text/javascript">var serverName = "<%=path%>";</script>
	    -->
		<script type="text/javascript">var serverName = "<%=path%>";
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/business/urgeList.js"></script>

		<script type="text/javascript">
	function returnPalnView(value, rowData, rowIndex) {
		var paramId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var urgeId = rowData.urgeId;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newReceivablesList_returnPalnView}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openDialog(" + paramId + "," + urgeId + ")'><font color='#9932cc'>查看&nbsp&nbsp</font></a>";
		</sec:authorize>
		return links;
	}
</script>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center">
			<div id="loanListTab" class="easyui-tabs" style="padding: 10px;">
				<div title="条件查询" style="padding: 10px;">
                    <table width="100%">
                        <tr>
                            <td nowrap="nowrap" align="right">
                                业务单号：
                            </td>
                            <td>
                                <input id="businessNumber" style="width: 150px;"/>
                            </td>
                            <td nowrap="nowrap" align="right">
                                借款人姓名：
                            </td>
                            <td>
                                <input id="name" style="width: 150px;"/>
                            </td>
                            <%--<td align="right" style="width: 100px;">
                                客户经理：
                            </td>
                            <td>
                                <input id="loanOfficerName" style="width: 100px;" />
                            </td>
                            <td align="right">
                                分公司名称:
                            </td>
                            <td>
                                <input style="width: 120px;" id="companyId" type="text" />
                            </td>--%>
                            <td nowrap="nowrap" align="right">
                                催收日期：
                            </td>
                            <td nowrap="nowrap">
                                <input id="urgeDateBegin" type="text" class="easyui-datebox" editable="false"
                                       style="width: 150px;"/>
                                -
                                <input id="urgeDateEnd" class="easyui-datebox" type="text" editable="false"
                                       style="width: 150px;"/>
                            </td>
                            <td style="display:none">
                                <input id="promiseRefundDateBegins" type="text" class="easyui-datebox"
                                       style="width: 104px;" editable="false"
                                       value="${promiseRefundDateBegin}"/>
                                &nbsp;&nbsp;至&nbsp;&nbsp;
                                <input id="promiseRefundDateEnds" class="easyui-datebox" type="text"
                                       style="width: 104px;" editable="false"
                                       value="${promiseRefundDateEnd}"/>
                            </td>
                            <td nowrap="nowrap">
                                <a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;
                                <a class="easyui-linkbutton" href="javascript:searchCancel1();">清空</a>&nbsp;
                                <a class="easyui-linkbutton" href="javascript:exportFinanceReport();">导出</a>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap" align="right">
                                分公司名称：
                            </td>
                            <td>
                                <input style="width: 155px;" id="companyId" type="text" name="companyId"/>
                            </td>
                            <td nowrap="nowrap" align="right">
                                客户经理：
                            </td>
                            <td>
                                <input id="loanOfficerName" style="width: 150px;"/>
                            </td>
                            <td nowrap="nowrap" align="right">
                                承诺还款日期：
                            </td>
                            <td nowrap="nowrap">
                                <input id="promiseRefundDateBegin" type="text" editable="false"
                                       class="easyui-datebox" style="width: 150px;"/>
                                -
                                <input id="promiseRefundDateEnd" class="easyui-datebox" editable="false"
                                       type="text" style="width: 150px;"/>
                            </td>
                        </tr>
                    </table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="催收列表">
					<table id="urgeList">
					</table>
				</div>
			</div>
		</div>
		<div id="urgeView" style="width: 800px; height: 400px;">
			<iframe scrolling="no" id="iframeTest" name="myIframe"
				frameborder="0" src="" style="width: 100%; height: 100%;">
			</iframe>
		</div>
	</body>
</html>
