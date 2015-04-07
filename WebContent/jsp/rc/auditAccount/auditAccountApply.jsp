<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
String cmUrl = properties.getProperty("cm.url");
String isURL = request.getRequestURL().toString();
if(isURL.indexOf(".cn")>0){
	if(cmUrl.indexOf(".corp")>0){
		cmUrl=cmUrl.replaceAll(".corp",".cn");
	}
}else if(isURL.indexOf(".corp")>0){
	if(cmUrl.indexOf(".cn")>0){
		cmUrl=cmUrl.replaceAll(".cn",".corp");
	}
}
String cmIp = properties.getProperty("cm.hostip");
DESPlus desPlus = new DESPlus();
String DESNow = desPlus.encrypt(new Date().getTime()+"");
String DESIp = desPlus.encrypt(cmIp);
String role=request.getParameter("role");
StringBuffer conditions=new StringBuffer("");
String separator="?";
if(CommonUtil.isNotEmpty(role)){
	conditions.append(separator).append("role=").append(role);
	separator="&";
}
String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'newLoanList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
	<script type="text/javascript">
	var serverName = "<%=path%>";
	var cmUrl = "<%=cmUrl%>";
	var DESIp = "<%=DESIp%>";
	var DESNow = "<%=DESNow%>";
	var role = "<%=role%>";
	</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/auditAccount/auditAccountApply.js"></script>
		<script type="text/javascript">
	function operationApply(valueTemp, rowData, rowIndex){
		var value= rowData.status;
		var creditapplicationId = rowData.creditapplicationId;
		var clientid = rowData.creditapplicationDESId;
		
	   // 查看申请单的 加密id
	    var creditApplicationEscId = rowData.laonDESId;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newLoanList_searchLook}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=openDialog("+creditapplicationId+")><font color='#9932cc'>新增收款申请</font></a>";
		</sec:authorize>   
		return links;
	}
	function operationLookView(valueTemp, rowData, rowIndex){
		var balanceAccountApplyId=rowData.balanceAccountApplyId;
		var creditapplicationId = rowData.creditapplicationId;
		var auditResult=rowData.auditResult;
		var balanceDay=rowData.balanceDay;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.auditAccount_searchAccountLook}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=openSearchDialog("+creditapplicationId+","+balanceAccountApplyId+","+balanceDay+")><font color='#9932cc'>查看</font></a>";
		</sec:authorize>   
		<sec:authorize ifAnyGranted="${control.auditAccount_auditAccountButton}">
		if ("0" == auditResult) {
		links +="&nbsp;&nbsp;<a href='javascript:void(0)' onclick=openAuditDialog('"+creditapplicationId+"',"+balanceAccountApplyId+","+balanceDay+")><font color='#9932cc'>审批</font></a>";
		}
		</sec:authorize>  
		return links;
	}
	
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="hostTab" class="easyui-tabs" style="padding: 10px;">
				<div title="列表页面">
					<div class="easyui-tabs" style="padding: 10px;">
					<!-- 添加权限限制 -->
					<sec:authorize ifAnyGranted="${control.auditAccount_accountApply}">
						<div title="申请的数据查询">
							<table>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询条件：
									</td>
									<td>
										<input id="businessNumber" name="businessNumber" type="text" style="width: 150px;" />
									</td>
									<td>
										<a class="easyui-linkbutton" onclick="searchFussy()">搜索</a>
									</td>
									<td>
										<a class="easyui-linkbutton" onclick="searchCancelBus()">清空</a>
									</td>
									<td>
										<font color="red">（请输入正确的业务单号进行搜索）</font>
									</td>
								</tr>
							</table>
						</div>
						</sec:authorize>   
						<!-- 添加权限限制 -->
					<sec:authorize ifAnyGranted="${control.auditAccount_accountAudit}">
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
								<input id="companyId" type="text"  style="width: 260px;"/>
							</td>
							<td align="left" >
								&nbsp;&nbsp;&nbsp;&nbsp;申请状态:
								&nbsp;<input style="width: 120px;" id="auditAccountApplyState" type="text" />
							</td>
							<td colspan="0" align="left">
								<a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;&nbsp;
								<a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a>&nbsp;&nbsp;
								</td>
						</tr>
						
					</table>
				</div>
						</sec:authorize>
					</div>
					<!-- 添加权限限制 -->
					<sec:authorize ifAnyGranted="${control.auditAccount_accountApply}">
					<div id="applyAuditDateGrid" class="easyui-tabs" style="padding: 10px;">
						<div title="要申请的数据结果">
							<table id='applyAuditDateGriding'>
							 </table>
						</div>
					</div>
					</sec:authorize>
					<!-- 添加权限限制-->
					<sec:authorize ifAnyGranted="${control.auditAccount_accountAuditHistory}">
					<div id="listHistoryDateGrid" class="easyui-tabs" style="padding: 10px;">
						<div title="申请过的数据结果">
							<table id="historyDateGrid">
							</table>
						</div>
					</div>
					</sec:authorize> 
				</div>
			</div>
		</div>
			
        <div id="newApplyDialog" style="width: 950px; height: 500px;">
			<iframe  id="iframeTest" name="myIframe"
				frameborder="0" src="" style="width: 100%; height: 100%;"></iframe>
		</div>
		<div id="accountApplyView" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest2" name="myIframe2"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
		</div>
		<div id="accountApplyAudit" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest3" name="myIframe3"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
			</div>
	</body>
</html>
