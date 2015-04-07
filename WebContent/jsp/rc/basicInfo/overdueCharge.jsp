<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>逾期客户的逾期费用计算</title>
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
				<fieldset>
					<legend>查询条件</legend>
					<table class="tableForm" style="height:auto;padding-left: 20px" cellspacing="10px" cellpadding="0">
					    <tr>
					      <td>产品版本编号<input id="productInfoId"/></td>
					      <td>合同金额<input id="contractMoney" /></td>
					      <td>分期数<input id="periodCount" /></td>
					      <td>上次逾期费用计算日期<input id="prevCalcDate" class="easyui-datetimebox"/></td>
					      <td>计算日期<input id="calcDate" class="easyui-datetimebox"/></td>
					    </tr>
						<tr>
							<td>
							  <a id="btn" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchByParam()">计算</a>
							  <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a>
							</td>
							<td></td>
						</tr>
					</table>
				</fieldset>
				<div id="queryOptions" class="easyui-accordion">
				  <div title="各分期实际还款记录" style="overflow:auto;padding:10px;">
			          <h3 style="color:#0099FF;">Accordion for jQuery</h3>
			          <p>content1</p>
			      </div>
			      <div title="分期服务费列表" style="padding:10px;">
			          content2
			      </div>
			      <div title="逾期费用服务费列表">
			         content3
			      </div>
		 		</div>

				
			</div>
			<table id="rateResult"></table>
		</div>
		
	</body>
</html>


