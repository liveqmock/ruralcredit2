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
			src="<%=basePath%>scripts/business/customerGiveUpList.js"></script>

		<script type="text/javascript">
		var serverName="<%=path%>";
		/** wyf导出客户放弃Excel **/
		function exportExcelCustomerGiveUp(){
			var data = new Object();
			var businessNumber = $("#businessNumber").val();
			var name = $("#name").val();
			var departmentNameValues = $("#companyId").combotree("getValues");
			if(departmentNameValues!=null&&departmentNameValues!=""){
				for(var i in departmentNameValues){
					departmentNameValues[i]="'"+departmentNameValues[i]+"'";
				}
			}
			var departmentNamestr = departmentNameValues.join(",");
			var departmentId=departmentNamestr;
			var customerQiutReason = $("#customerQiutReason").combobox("getValue");
			window.location.href = "<%=basePath%>RefuseReasonController/exportExcelCustimerGiveUp.do?businessNumber="+
			businessNumber+"&name="+name+"&companyId="+departmentId+"&codeKey="+customerQiutReason+"";
		}
		
	function returnPalnView(value,rowData,rowIndex) {
		var paramId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var links = "";
		 links="&nbsp;&nbsp;<a href='javascript:searchLook();'>查看</a>";
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
							<td style="width:200px;" colspan="2">
								业务单号：
								<input id="businessNumber" name="businessNumber"  />
							</td>
							<td align="left"  colspan="2">
								借款人姓名：
								<input id="name" name="name"  style="width: 100px;" />
							</td>
							<td align="left">
							          客户放弃原因：
								<input id="customerQiutReason" name="customerQiutReason" style="width: 150px;" />
							</td>
							<td align="left" colspan="2"  >
								分公司名称：
								<input id="companyId" name="companyId" type="text"  style="width: 200px;"/>
							</td>
							</tr>
							<tr>
							<!-- <td align="left" >
								回滚状态：
								<input style="width: 120px;" id="auditSpecialApplyState" type="text" />
							</td> -->
							
							<td colspan="0" align="right">
								<a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;&nbsp;
								<a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a>&nbsp;&nbsp;
								<sec:authorize ifAnyGranted="${control.customerGiveUp_exportcustomerGiveUpList}">
								<a href='javascript:exportExcelCustomerGiveUp();' class="easyui-linkbutton">导出</a>
								</sec:authorize>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tr>
						
					</table>
				</div>
			</div>
			<!-- 客户放弃原因    -->
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
			<div title="客户放弃列表">
			<table  id="customerGiveUpList" toolbar="#toolbar1">
					</table>
			</div>
			</div>
		</div>
		<!--回滚填写和查看
			<div id="customerGiveUps" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest" name="myIframe"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
			</div>
			<div id="customerGiveUpView" style="width: 850px; height: 400px;" >
				<iframe scrolling="no" id="iframeTest2" name="myIframe2"
					frameborder="0" src="" style="width: 100%; height: 100%;">
					</iframe>
			</div>
			 -->
	</body>
</html>
