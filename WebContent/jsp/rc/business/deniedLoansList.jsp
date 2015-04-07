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
			src="<%=basePath%>scripts/business/deniedLoansList.js"></script>

		<script type="text/javascript">
		var serverName="<%=path%>";
		/** wyf导出拒贷Excel **/
		function exportExcelRefuseList(){
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
			window.location.href = "<%=basePath%>RefuseReasonController/exportExcelRefuseList.do?businessNumber="+
			businessNumber+"&name="+name+"&companyId="+departmentId+"";
		}

		/*导出修改*/
		function exportModify() {
			var businessNumber = $("#businessNumber").val();
			var name = $("#name").val();
			var departmentNameValues = $("#companyId").combotree("getValues");
			if (departmentNameValues) {
				for (var i in departmentNameValues) {
					departmentNameValues[i] = "'" + departmentNameValues[i] + "'";
				}
			}
			var departmentId = departmentNameValues.join(",");
			window.location.href = "<%=basePath%>RefuseReasonController/exportModify.do?businessNumber=" +
			$.trim(businessNumber) + "&name=" + $.trim(name) + "&companyId=" + $.trim(departmentId);
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
							<td style="width:150px;" colspan="2">
								业务单号：
								<input id="businessNumber" name="businessNumber"  />
							</td>
							<td align="left" style="width: 150px;" colspan="2">
								借款人姓名：
								<input id="name" name="borrowName" style="width: 100px;" />
							</td>
							<td align="left" colspan="2"  style="width: 150px;">
								分公司名称：
								<input id="companyId" name="companyId" type="text"  style="width: 200px;"/>
							</td>
							<td colspan="0" align="left">
								<a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;&nbsp;
								<a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a>&nbsp;&nbsp;
								<sec:authorize ifAnyGranted="${control.deniedLoans_exportDeniedLoansList}">
								<a href='javascript:exportExcelRefuseList();' class="easyui-linkbutton" >导出</a>
								</sec:authorize>
								<sec:authorize ifAnyGranted="${control.deniedLoans_exportModify}">
								<a href='javascript:exportModify();' class="easyui-linkbutton" >导出_modify</a>
								</sec:authorize>
						</tr>
						
					</table>
				</div>
			</div>
			<!-- 拒贷列表  -->
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
			<div title="拒贷列表" >
			<table  id="deniedLoansList" toolbar="#toolbar1">
					</table>
			</div>
			</div>
		</div>
	</body>
</html>
