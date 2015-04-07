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
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/calculatedAnticipate.js"></script>
	<script type="text/javascript">
		var serverName="<%=path%>";
	</script>
</head>
	<body class="easyui-layout" style="overflow-x: hidden; overflow-y: auto;">
		<div region="center" border="false" style="overflow-x: hidden; overflow-y: auto;">
					<div id="queryOptions" class="easyui-layout" style="height:350px;" collapsible="false">  
				      <div region="center">  
				          <table id="dg" class="easyui-datagrid" title="各分期实际还款记录" toolbar="#toolbar1" idField="id" rownumbers="true" singleSelect="true">  
						     <thead>  
						         <tr>  
					                <th field="overdueFlag" width="60" formatter="formatOverdueFlag">是否逾期</th>  
					                <th field="period" width="50" editor="{type:'validatebox',options:{required:true}}">期数</th>
					                <th field="repaymentDate" width="80" editor="{type:'datebox',options:{required:true}}">实收日期</th>
					                <th field="currAccountTotal" width="80" editor="{type:'validatebox',options:{required:true}}">应收总额</th>
					                <th field="currMonPrincipal" width="80" editor="{type:'validatebox',options:{required:true}}">应收本金</th>
					                <th field="currMonInterest" width="80" editor="{type:'validatebox',options:{required:true}}">应收利息</th>
					                <th field="currMonPayTotal" width="80" editor="{type:'validatebox',options:{required:true}}">实收总额</th>
					                <th field="currPaindinCapital" width="80" editor="{type:'validatebox',options:{required:true}}">实收本金</th>
					                <th field="currPaindinInterest" width="80" editor="{type:'validatebox',options:{required:true}}">实收利息</th>
					                <th field="currMonPenalty" width="80" editor="{type:'validatebox'}">应收罚息</th>  
					                <th field="currMonPaidPenalty" width="80" editor="text">实收罚息</th>  
					                <th field="currMonLaterFree" width="80" editor="text">应收滞纳金</th>  
					                <th field="currLateFreePaid" width="80" editor="text">实收滞纳金</th>
					                <th field="currMonManageFree" width="80" editor="text">应收服务费</th>  
					                <th field="currPaindinManageFree" width="80" editor="text">实收服务费</th>
					            </tr>  
						     </thead>  
						 </table> 
				          <div id="toolbar1">
				          	业务编号：<input id="groupNum" />
				          	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="queryReturnPlan()">添加</a>
					        <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addLine()">增加</a>  
					        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>  
					        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLine()">删除</a>--%>  
					      </div>  
				      </div>  
				      <div region="south">
						  <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="calculatedRate()">计算</a>
						  <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a>
				      </div>
				      <div region="north" style="overflow: hidden;">
						<form id="rateOptions" method="post" novalidate>
				      	<table class="tableForm" style="height:auto;padding-left: 20px" cellspacing="10px" cellpadding="0">
						    <tr>
						      <td width="150px">全部提前还款<br />
						    	<input value="true" type="radio" name="aLLAhead" checked="checked"/>是
								<input value="false" type="radio" name="aLLAhead"/>否
							  </td>
						      <td width="100px">预约还款时间<input id="appointmentDate" required="true" name="appointmentDate" class="easyui-datetimebox"/></td>
						      <td width="100px">合同金额<input id="contractMoney" required="true" class="easyui-validatebox" name="contractMoney" value="20000"/></td>
						      <%--<td width="100px">审核日期<input id="auditDate" name="auditDate" class="easyui-datetimebox"/></td>--%>
						      <%--<td width="100px">产品分类编号<input id="catagoryId" name="catagoryId" value="302"/></td>--%>
						      <td width="100px">放款日期<input id="lenderDate" required="true" name="lenderDate" class="easyui-datetimebox"/></td>
						    </tr>
						    <tr> 
						      <td width="100px">营业部编号<input id="departmentId" required="true" class="easyui-validatebox" name="departmentId" value="11009488"/></td>
						      <%--<td width="150px">上一次计算逾期费用时间<input id="prevCalcDate" name="prevCalcDate" class="easyui-datetimebox"/></td>--%>
						      <td width="100px">请求系统日期<input id="reqSysDate" required="true" name="reqSysDate" class="easyui-datetimebox"/></td>
						      <td width="100px">分期数<input id="periodCount"  required="true" class="easyui-validatebox" name="periodCount" value="9"/></td>
						      <td width="100px">产品版本编号<input id="productInfoId" required="true" class="easyui-validatebox" name="productInfoId" value="486" /></td>
						      <td width="100px"></td>
						      <%--<td>首个还款日设置方式<input id="firstPayDateType" /></td>
						      <td>首期利息计算方式<input id="paramName" /></td>
						      <td>还款方式参数值<input id="paramValue" /></td>
						    --%>
						    </tr>
						</table>
						</form>
				      </div>
			 		</div>
			<table id="allAheadSchedule" title="预约全部提前还款计划" fitColumns="true" singleSelect="true">
		     	<thead>  
		         	<tr>  
	                <th field="totalPrincipal" width="50">逾期本金合计</th>  
	                <th field="totalInterest" width="50">逾期利息合计</th>  
	                <th field="totalOverdueFines" width="50">逾期分期费用合计</th>  
	                <th field="totalOverdueInterest" width="50">逾期滞纳金合计</th>  
	                <th field="currentPrincipal" width="50">当期本金</th>
	                <th field="currentInterest" width="50">当期利息</th>
	                <th field="currentPeriodCharge" width="50">当期分期费用</th>
	                <th field="surplusPrincipal" width="50">应收总金额</th>
	                <th field="behindInterests" width="50">剩余本金</th>
	                <th field="charge" width="50">以后各期未来利息</th>
	                <th field="penalbond" width="50">服务费</th>
	                <th field="total" width="50">全部提前还款合计</th>
	            	</tr>  
		     	</thead>  
		   </table> 
		   <%--<table id="apsList" title="预约还款分期还款计划" fitColumns="true" singleSelect="true">  
		     	<thead>  
		         	<tr>  
	                <th field="period" width="50">分期</th>  
	                <th field="receivablePrincipal" width="50">应收本金</th>  
	                <th field="receivableInterest" width="50">应收利息</th>  
	                <th field="periodReceivableChargeFQFWF" width="50">分期服务费应收费用</th>
	                <th field="overdueReceivableChargeFX" width="50">逾期应收罚息</th>
	                <th field="overdueReceivableChargeZNJ" width="50">逾期应收滞纳金</th>
	                <th field="receivableMoney" width="50">应收金额</th>
	            	</tr>  
		     	</thead>  
		   </table>
		   <div>
		     <th field="totalReceivablePrincipal" width="50">应收总本金</th>
             <th field="totalReceivableInterest" width="50">应收总利息</th>
             <th field="totalReceivableMoney" width="50">应收总金额</th>
             
             <th field="totalPeriodReceivableChargeFQFWF" width="50">汇总分期服务费</th>
             <th field="totalOverdueReceivableChargeFX" width="50">汇总罚息费用</th>
             <th field="totalOverdueReceivableChargeZNJ" width="50">汇总滞纳金费用</th>
		   </div>
		--%></div>
	</body>
</html>


