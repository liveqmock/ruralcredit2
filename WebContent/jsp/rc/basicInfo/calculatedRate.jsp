<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>费率计算</title>
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/calculatedRate.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow: hidden;">
			<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
				<form id="rateOptions" method="post">
					<div id="queryOptions" class="easyui-layout" style="height:200px;" collapsible="false">  
					  <%--<div region="west" split="false" style="width:400px;overflow: hidden;" collapsible="false">  
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
				      --%><div region="south">
						  <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="calculatedRate()">计算</a>
						  <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a>
				      </div>
				      <div region="center" style="height:50px;">
				      	<table class="tableForm" style="width:100%;height: 100%;text-align: center;" cellspacing="10px" cellpadding="0">
						    <tr>
						      <td>还款方式<input class="easyui_combobox" id="paymentTypeCode"/></td>
						      <td>审核日期<input id="auditDate" name="auditDate" class="easyui-datetimebox"/></td>
						      <td>进件日期<input id="reqDate" name="reqDate" class="easyui-datetimebox"/></td>
						      <td>营业部编号<input id="departmentId" name="departmentId" value="11009485"/></td>
						      <td>产品分类编号<input id="catagoryId" name="catagoryId" readonly="readonly"/>
						      <input id="paymentTypeCode1" name="paymentTypeCode" type="hidden"/></td>
						      <%--<td>产品版本编号<input id="productInfoId" name="productInfoId" value="419"/></td>--%>
						      <td>分期数<input id="periodCount" name="periodCount" readonly="readonly"/></td>
						      <td>请求放款日期<input id="lenderDate" name="lenderDate" class="easyui-datetimebox"/></td>
						      <td>合同金额<input id="contractMoney" name="contractMoney" value="20000"/></td>
						      <%--<td>首个还款日设置方式<input id="firstPayDateType" /></td>
						      <td>首期利息计算方式<input id="paramName" /></td>
						      <td>还款方式参数值<input id="paramValue" /></td>
						    --%>
						    </tr>
						</table>
				      </div>
			 		</div>
				</form>
			</div>
			<table id="rateResult"></table>
		</div>
	</body>
</html>


