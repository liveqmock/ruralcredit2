<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>产品计算-提前还款</title>
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/calculatedRepaymentPlan.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow: hidden;">
			<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
				<fieldset>
				<form id="rateOptions" method="post">
					<legend>产品计算(提前还款)选项</legend>
					<div id="queryOptions" class="easyui-layout" style="height:200px;" collapsible="false">  
					  <div region="west" split="false" style="width:400px;overflow: hidden;" collapsible="false">  
				         <div class="easyui-panel" title="前期服务费打折信息列表" fit="true" border="false">
				          <h3 style="color:#0099FF;">frontChargeDiscountList</h3>  
				          <p></p>  
				         </div>
				      </div>  
				      <div region="center" title="分期费用打折信息列表" split="false">  
				          <h3 style="color:#0099FF;">periodChargeDiscountList</h3>    
				      </div>  
				      <div region="east" split="fasle" style="width:400px;overflow: hidden;" collapsible="false">  
				         <div class="easyui-panel" title="还款方式配置列表" fit="true" border="false">
				           <table class="tableForm" style="height:auto;text-align:right;">
						    <tr>
						      <td>还款方式</td>
						      <td><input id="paymentTypeCode" name="paymentTypeCode"/></td>
						    </tr>
						    <tr>
						      <td>参数名称</td>
						      <td><input id="paramName" name="paramName" style="width: 145px;" value="r"/></td>
						    </tr>
						    <tr>
						      <td>参数值</td>
						      <td><input id="paramValue" name="paramValue" style="width: 145px;" value="0.035"/></td>
						    </tr>
						</table>
				         </div>    
				      </div>  
				      <div region="south">
						  <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="calculatedRate()">计算</a>
						  <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a>
				      </div>
				      <div region="north">
				      	<table class="tableForm" style="height:auto;padding-left: 20px" cellspacing="10px" cellpadding="0">
						    <tr>
						      <td>营业部编号<input id="departmentId" name="departmentId" value="11009485"/></td>
						      <td>产品分类编号<input id="catagoryId" name="catagoryId" value="249"/></td>
						      <td>审核日期<input id="auditDate" name="auditDate" class="easyui-datetimebox"/></td>
						      <td>产品版本编号<input id="productInfoId" name="productInfoId" value="419" /></td>
						      <td>合同金额<input id="contractMoney" name="contractMoney" value="20000"/></td>
						      <td>分期数<input id="periodCount" name="periodCount" value="9"/></td>
						      <td>请求放款日期<input id="lenderDate" name="lenderDate" class="easyui-datetimebox"/></td>
						      <%--<td>首个还款日设置方式<input id="firstPayDateType" /></td>
						      <td>首期利息计算方式<input id="paramName" /></td>
						      <td>还款方式参数值<input id="paramValue" /></td>
						    --%>
						      
						    </tr>
						</table>
				      </div>
			 		</div>
				</form>
				</fieldset>
			</div>
			<table id="rateResult"></table>
			<div class="">
				综合信息
				前期费用列表信息
				费用编号<span id="eChargeType"></span>
费用名称<span id="eChargeName"></span>
费用公式描述<span id="eChargeFormula"></span>
费用金额<span id="eCharge"></span>
				
			</div>
		</div>
	</body>
</html>


