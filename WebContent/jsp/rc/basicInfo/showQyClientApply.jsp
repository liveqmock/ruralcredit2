<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'showQyClientApply.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	var serverName="<%=path%>";
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		var serverName="<%=path%>";
	$(function() {
		$("#seachForm").form("validate");
	});
	function searchinfo() {
		if ($("#seachForm").form("validate")) {
			ajaxSubmit(serverName + "/ruralBusyController/testQyClientApply.do", {
				bussesNumber : $("#bussesNumber").val()
			}, function(r) {
				$("#myForm").form("load", r);
				$("#myAccordion").accordion("select", "信息");
			});
		} else {
			alert("请填写业务单号");
		}

	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" fit="true">
				<div title="借款人信息">
					<div id="myAccordion" class="easyui-accordion" fit="true">
						<div id="search" title="查询" iconCls="icon-search" style="overflow: auto; padding: 10px;">
							<form id="seachForm" novalidate>
								<table>
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											请输入业务单号：
										</td>
										<td>
											<input size="50" id="bussesNumber" name="bussesNumber" value="${bussesNumber}" class="easyui-validatebox" required="true" />
										</td>
										<td>
											<a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchinfo();">查询</a>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<div id="info" title="信息" iconCls="icon-save" style="overflow: auto; padding: 10px;">
							<form id="myForm">
								<table>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											申请编号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="applyId" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											还款方式：
										</td>
										<td>
											<input size="50" readonly="readonly" name="returnType" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款方式：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanType" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款银行账号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanBankAccount" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款人银行户名：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanBankClientName" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款银行所在省：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanBankProvince" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款银行所在市：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanBankCity" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款开户行名称：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanBankName" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											合同金额：
										</td>
										<td>
											<input size="50" readonly="readonly" name="applyAmount" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											产品编号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="productId" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											分期数：
										</td>
										<td>
											<input size="50" readonly="readonly" name="amortisation" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											借款用途：
										</td>
										<td>
											<input size="50" readonly="readonly" name="borrowPurpose" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											信贷员编号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="sellId" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											信贷员名字：
										</td>
										<td>
											<input size="50" readonly="readonly" name="sellName" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											分公司编号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="officeId" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											客户姓名：
										</td>
										<td>
											<input size="50" readonly="readonly" name="clientName" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											身份证号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="idNumber" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											手机号码：
										</td>
										<td>
											<input size="50" readonly="readonly" name="mobilePhone" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款金额：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanAmount" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											放款时间：
										</td>
										<td>
											<input size="50" readonly="readonly" name="loanTime" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											产品分类编号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="productTypeId" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											卡折标志：
										</td>
										<td>
											<input size="50" readonly="readonly" name="bankBook" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											还款银行账号：
										</td>
										<td>
											<input size="50" readonly="readonly" name="bankAccount" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											还款人银行户名：
										</td>
										<td>
											<input size="50" readonly="readonly" name="bankClientName" type="text" />
										</td>
									</tr>
									<tr>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											还款开户行名称：
										</td>
										<td>
											<input size="50" readonly="readonly" name="bankName" type="text" />
										</td>
										<td width="50px">
											&nbsp;
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
