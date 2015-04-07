<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

		<title>My JSP 'repaymentPlan.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/basicInfo/repaymentPlan.js"></script>


	</head>
	<body class="easyui-layout">
		<div region="center">
			<div id="repaymentSearchTab" class="easyui-tabs">
				<div title="模糊查询">
					<table>
						<tr>
							<td>
								模糊查询条件：
							</td>
							<td>
								<input id="fuzzyQueryRepayment" name="fuzzyQueryRepayment" type="text" style="width: 150px;" />
							</td>
							<td>
								<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearchRepayment(0)">搜索</a>
							</td>
							<td>
								<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doClearRepayment(0)">清空</a>
							</td>
							<td>
								<font color="red">（可输入不完整的产品名称 或选择是否启用进行搜索）</font>
							</td>
						</tr>
					</table>
				</div>
				<div title="高级查询">
					<form id="repaymentPlan_searchform" method="post">
						<table width="100%">
							<tr>
								<td align="right">
									方案名称：
								</td>
								<td>
									<input name="repaymentPlanName" type="text" />
								</td>
								<td align="right">
									贷款期限（月）：
								</td>
								<td>
									<input name="loanPeriod" type="text" class="easyui-numberbox" />
								</td>
								<td align="right">
									最高借款金额：
								</td>
								<td>
									<input name="heightLoanAmount" type="text" class="easyui-numberbox" precision="2" />
								</td>
								<td align="right">
									是否上线：
								</td>
								<td>
									<input id="useFlagSearch" name="useFlag" type="text" />
								</td>
							</tr>
							<tr>
								<td align="right">
									名义年利率（%）：
								</td>
								<td>
									<input name="nominalInterestRate" type="text" class="easyui-numberbox" precision="2" />
								</td>
								<td align="right">
									首期服务费（%）：
								</td>
								<td>
									<input name="firstServiceFree" type="text" class="easyui-numberbox" precision="2" />
								</td>
								<td align="right">
									后续服务费（%）：
								</td>
								<td>
									<input name="followupServiceFree" type="text" class="easyui-numberbox" precision="2" />
								</td>
								<td align="right">
									还款方式：
								</td>
								<td>
									<input id="repaymentWaySearch" name="repaymentWay" type="text" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearchRepayment(1)">搜索</a>
								</td>
								<td>

									<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doClearRepayment(1)">清空</a>
								</td>
								<td align="right">
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td align="right">
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td align="right">
									提前还款方案：
								</td>
								<td>
									<input id="earlyTypeSearch" name="earlyType" type="text" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<table id="repaymentDataGrid"></table>
			<div id="dialogForAdd" class="easyui-dialog" title="新增还款方案" style="width: 1000px; height: 550px;" modal="true" closed="true" buttons="#dialogForAddButtons">
				<form id="repaymentPlanFormAdd" method="post">
					<table width="100%">
						<tr>
							<td width="10%" align="right">
								<span id="checkName1"></span>
							</td>
							<td align="right">
								方案名称：
							</td>
							<td>
								<input id="repaymentPlanNameAdd" name="repaymentPlanName" type="text" class="easyui-validatebox" required="true" onblur="doCheckName(value);" />
							</td>
							<td align="right">
								贷款期限（月）：
							</td>
							<td>
								<input id="loanPeriodAdd" name="loanPeriod" type="text" class="easyui-numberbox" required="true" />
							</td>
							<td align="right">
								最高借款金额：
							</td>
							<td>
								<input id="heightLoanAmountAdd" name="heightLoanAmount" type="text" class="easyui-numberbox" precision="2" />
							</td>
						</tr>
						<tr>
							<td>
								<span id="checkName2"></span>
							</td>
							<td align="right">
								名义年利率（%）：
							</td>
							<td>
								<input id="nominalInterestRateAdd" name="nominalInterestRate" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
							<td align="right">
								首期服务费（%）：
							</td>
							<td>
								<input id="firstServiceFreeAdd" name="firstServiceFree" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
							<td align="right">
								后续服务费（%）：
							</td>
							<td>
								<input id="followupServiceFreeAdd" name="followupServiceFree" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right">
								是否启用：
							</td>
							<td>
								<input id="useFlagAdd" name="useFlag" type="text" required="true" />
							</td>
							<td align="right">
								还款方式：
							</td>
							<td>
								<input id="repaymentWayAdd" name="repaymentWay" type="text" required="true" />
							</td>
							<td align="right">
								提前还款方案：
							</td>
							<td>
								<input id="earlyTypeAdd" name="earlyType" type="text" required="true" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								<a id="addRepaymentItemButton" class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="doAppendRow()">创建还款计划详情</a>
								<a id="clearRepaymentItemButton" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doClearAddRepayment()">清空</a>
							</td>
						</tr>
					</table>
				</form>
				<table id="addRepaymentPlanDatagrid"></table>
			</div>
			<div id="dialogForAddButtons">
				<a class="easyui-linkbutton" iconCls="icon-save" onclick="doCheckRowAndSaveAdd()">保存还款方案</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialogForAdd()">取消关闭</a>
			</div>
			<div id="dialogForSearchAndEdit" class="easyui-dialog" title="查询和修改还款方案" style="width: 1000px; height: 550px;" modal="true" closed="true" buttons="#dialogForSearchAndEditButtons">
				<form id="repaymentPlanFormEdit" method="post">
					<table width="100%">
						<tr>
							<td width="10%" align="right">
								<input id="repaymentPlanId" name="repaymentPlanId" type="hidden" />
							</td>
							<td align="right">
								方案名称：
							</td>
							<td>
								<input id="repaymentPlanNameEdit" name="repaymentPlanName" type="text" />
							</td>
							<td align="right">
								贷款期限（月）：
							</td>
							<td>
								<input id="loanPeriodEdit" name="loanPeriod" type="text" />
							</td>
							<td align="right">
								最高借款金额：
							</td>
							<td>
								<input id="heightLoanAmountEdit" name="heightLoanAmount" type="text" class="easyui-numberbox" precision="2" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right">
								名义年利率（%）：
							</td>
							<td>
								<input id="nominalInterestRateEdit" name="nominalInterestRate" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
							<td align="right">
								首期服务费（%）：
							</td>
							<td>
								<input id="firstServiceFreeEdit" name="firstServiceFree" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
							<td align="right">
								后续服务费（%）：
							</td>
							<td>
								<input id="followupServiceFreeEdit" name="followupServiceFree" type="text" class="easyui-numberbox" precision="2" required="true" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right">
								是否启用：
							</td>
							<td>
								<input id="useFlagEdit" name="useFlag" type="text" required="true" />
							</td>
							<td align="right">
								还款方式：
							</td>
							<td>
								<input id="repaymentWayEdit" name="repaymentWay" type="text" required="true" />
							</td>
							<td align="right">
								提前还款方案：
							</td>
							<td>
								<input id="earlyTypeEdit" name="earlyType" type="text" required="true" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								<a id="updateRepaymentItemEdit" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="repickRepayment()" disabled="true"> 更新还款方案</a>
							</td>
						</tr>
					</table>
				</form>
				<table id="editRepaymentPlanDatagrid"></table>
			</div>
			<div id="dialogForSearchAndEditButtons">
				<a id="saveRepaymentForEidt" class="easyui-linkbutton" iconCls="icon-save" onclick="doCheckRowAndSaveEdit()" disabled="true">保存还款方案</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialogForEdit();">取消关闭</a>
			</div>
		</div>
		<input type="hidden" id="passrepaymentPlanId" value="-1" />
		<input type="hidden" id="searchORedit" value="-1" />
	</body>
</html>
