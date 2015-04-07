<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
       <base href="<%=basePath%>"></base>
    <title>My JSP 'bankSetup.jsp' starting page</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	</script>
	
	<script type="text/javascript" src="<%=basePath %>/scripts/loginfo/loglist.js">
		</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		
		<div region="center">
				<div class="easyui-tabs" id="accountTab" style="padding: 10px;">
					<div title="模糊搜索" style="padding: 10px;">
						查询条件：<input id="simpleConditionInput"/>
						<a class="easyui-linkbutton" href="javascript:querySimpleCondition();">搜索</a>
						<a class="easyui-linkbutton" href="javascript:cancelQuerySimpleCondition();">清空</a><font color="red">（搜索条件可以是：业务单号，客户经理、借款人姓名，操作人）</font>
					</div>
					<div  title="高级搜索" style="padding: 10px;">
						<table id="queryAllConditionTB">
							<tr>
								<td align="right">分公司名称：</td>
								<td><input style="width: 150px;" name="companyName" id="companyName" class="easyui-combobox"/></td>
								<td align="right">模块名称</td>
								<td>
									<select name="functionCode" class="easyui-combobox" width=80 id="functionCode" editable="false"/>
										<option value="" selected="selected">---请选择---</option>  
										<option value="1" >申请模块</option>  
								        <option value="2">审批模块</option>  
								        <option value="3">放款模块</option>
								        <option value="4">收款模块</option>
								        <option value="5">财务模块</option>
								        <option value="6">免罚模块</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">业务单号：</td>
								<td><input style="width: 150px;" name="businessNumber" id="businessNumber"/></td>
								<td align="right">客户经理：</td>
								<td><input style="width: 150px;" name="loanOfficerName" id="loanOfficerName"/></td>
							</tr>
							<tr>
								<td align="right">借款人姓名：</td>
								<td><input style="width: 150px;" name="borrowerName" id="borrowerName"/></td>
								<td align="right">借款人身份证号：</td>
								<td><input style="width: 150px;" name="idCard" id="idCard"/></td>
							</tr>
							<tr>
								
								<td align="right">操作日期：</td>
								<td><input style="width: 150px;" name="startDate" id="startDate" type="text" class="easyui-datebox"/></td>
								<td align="center">至</td>
								<td><input style="width: 150px;" name="endDate" id="endDate" type="text" class="easyui-datebox"/></td>
							</tr>
							<tr>
							<td align="right">操作人：</td>
								<td><input style="width: 150px;" name="operatorName" id="operatorName"/></td>
							</tr>
							<tr>
								<td><a class="easyui-linkbutton" href="javascript:queryAllCondition();">搜索</a></td>
								<td><a class="easyui-linkbutton" href="javascript:clearAllCondition();">清空</a></td>
							</tr>
							
							
							
						</table>
					</div>
				</div>
			<div style="padding: 10px;">
			<table id="loglist"></table>
			</div>
		</div>
  </body>
</html>
