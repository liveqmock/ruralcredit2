<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
       <base href="<%=basePath%>"></base>
    <title>一期借款人信息</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	</script>
	
	<script type="text/javascript" src="<%=basePath %>/scripts/oldCustomerInfo/oldCustomerInfoList.js">
	</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		<div region="center">
			<div >
				<div class="easyui-tabs" id="oldCustomTab" style="padding: 10px;">
					<div  title="一期借款人产品列表" style="padding: 10px;" id="projectDIV">
						<table id="queryAllConditionTB">
							<tr>
								<td align="right">借款人身份证号码：</td>
								<td><input style="width: 150px;" name="project_idCard" id="project_idCard" /></td>
							</tr>
							<tr>
								<td colspan="7">
									<a class="easyui-linkbutton" href="javascript:queryProjuctList();">搜索</a> 
								<a class="easyui-linkbutton" href="javascript:clearProjuctListCondition();">清空</a>
								</td>
							</tr>
						</table>
					</div>
					<div  title="一期借款人审批列表" style="padding: 10px;" id="applyAuditDIV">
						<table id="queryAllConditionTB">
							<tr>
								<td align="right">借款人身份证号码：</td>
								<td><input style="width: 150px;" name="applyAudit_idCard" id="applyAudit_idCard" /></td>
							</tr>
							<tr>
								<td colspan="7">
									<a class="easyui-linkbutton" href="javascript:queryapplyAuditList();">搜索</a> 
								<a class="easyui-linkbutton" href="javascript:clearApplyAuditListCondition();">清空</a>
								</td>
							</tr>
						</table>
					</div>
					<div  title="一期借款人还款列表" style="padding: 10px;" id="repaymentDIV">
						<table id="queryAllConditionTB">
							<tr>
								<td align="right">借款人身份证号码：</td>
								<td><input style="width: 150px;" name="finance_idCard" id="finance_idCard" /></td>
							</tr>
							<tr>
								<td colspan="7">
									<a class="easyui-linkbutton" href="javascript:queryFinanceListByidCard();">搜索</a> 
								<a class="easyui-linkbutton" href="javascript:clearFinanceListCondition();">清空</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="padding: 10px;" id="projectListTabDiv">
					<table id="projectListDataGrid"></table>
				</div>
				<div style="padding: 10px;" id="applyAuditTableDiv" >
					<table id="applyAuditListDataGrid"></table>
				</div>
				<div style="padding: 10px;" id="repaymentTableDiv" >
					<table id="financeListDataGrid"></table>
				</div>
			</div>
			
		</div>
  </body>
</html>
