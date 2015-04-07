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
		<base href="<%=basePath%>"></base>

		<title>My JSP 'receivablesRegistration.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>scripts/business/receivablesRegistration.js"></script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="填写收款登记信息">
					<form id="iForm" method="post" novalidate>
						<table width="100%" align="center" cellpadding="3" cellspacing="0">
							<tr>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
								<td width="100px;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right">
									默认还款方式：
								</td>
								<td align="left">
									<span id="defaultArea"></span>
								</td>
								<td align="right">
									当期还款方式：
								</td>
								<td align="left">
									<input id="defaultBox" name="defaultRepaymentType" required="true" style="width: 154px;" />
								</td>
								<td align="right">
									资金来源：
								</td>
								<td align="left">
									<input id="iCombobox" name="capitalSource" style="width: 154px;" />
								</td>
								<td align=right colspan="2">
									<span id="locationSpan"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									收款登记类型：
								</td>
								<td align="left">
									<span id="receivedTypeSpan"></span>
								</td>
								<td align="right">
									收款登记的时间:
								</td>
								<td align="left">
									<input id="date" name="receivedTime" class="easyui-datebox" required="true" type="text" editable="false" style="width: 154px;" />
								</td>
								<td align="center" colspan="4">
									<input id="caId" name="creditapplicationId" value="${passCreditapplicationId}" type="hidden" />
									<input id="cardId" name="accountInfoId" type="hidden" />
									<input id="type" value="${type}" type="hidden" />
									<input id="normalOrEarly" name="receivedType" value="${normalOrEarly}" type="hidden" />
									<input id="principalTotal" type="hidden" />
									<input id="max" type="hidden" />
									<input id="businessN" value="${businessNumber}" type="hidden" />
								</td>
							</tr>
							<tr>
								<td align="right">
									收款金额：
								</td>
								<td align="left">
									<input id="receivedBox" name="receivedAmount" class="easyui-numberbox" precision="2" validType="numberNonNegative" required="true" type="text" style="width: 150px;" />
								</td>
								<td align="right">
									<a class="easyui-linkbutton" iconCls="icon-addOne" onclick="openDialog();">登记</a>
								</td>
								<td>
									&nbsp;
								</td>
								<td colspan="4">
									<span id="onWayArea"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									推荐金额：
								</td>
								<td align="left">
									<span id="showTotalArea"></span>
								</td>
								<td colspan="6">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right">
									其中包含：
								</td>
								<td align="left" colspan="7">
									<span id="showItemArea"></span>
								</td>
							</tr>
							<tr>
								<td colspan="8">
									<span id="cardArea"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="近期应还款项列表">
					<table id="receivablesDataGrid">

					</table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="还款历史列表">
					<table id="receivablesHistoryDataGrid">
					</table>
				</div>
			</div>
		</div>
		<div id="distributionDialog" class="easyui-dialog" title="收款登记分配" style="width: 1000px; height: 450px;" closed="true" modal="true" buttons="#yyom">
			<table id="distributionDataGird" class="easyui-datagrid">
			</table>
		</div>
		<div id="cardDialog" title="卡信息" style="width: 500px; height: 300px;" closed="true" modal="true" closable="false">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="请选择一张卡">
					<table id="cardDataGrid">
					</table>
				</div>
			</div>
		</div>
		<div id="menu" style="width: 100px; display: none;">
			<div id="item1" iconCls="icon-empty">
				显示应收款项
			</div>
			<div id="item2" iconCls="icon-empty">
				显示减免款项
			</div>
			<div id="item3" iconCls="icon-empty">
				显示实收款项
			</div>
			<div id="item4" iconCls="icon-empty">
				显示提前还款款项
			</div>
		</div>
		<div id="yyom">
			<a id="evas" class="easyui-linkbutton" onclick="doSave();">确定</a><a class="easyui-linkbutton" onclick="closeDialog();">取消</a>
		</div>
		<%--<div region="south" style="height: 40px;" align="center">
			<a class="easyui-linkbutton" iconCls="icon-back" onclick="back();">返回收款列表</a>
		</div>
		--%>
		<div id="accInfoDialog" class="easyui-dialog" title="添加卡信息" modal="true" align="center" buttons="#abutn" style="padding: 10px; border: 0px; margin: 0px; width: 700px; height: 350px" closed="true" resizable="true" inline="false" closable="false">
			<form id="accountInfo">
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table align="center" width="100%" style="padding: 10px;">

					<tr align="center">
						<td>
							所在省:
						</td>
						<td align="left">
							<input name="provinceId" class="easyui-combobox" width=150 id="provinceId1" required="true" editable="false" />
						</td>
						<td>
							所在市:
						</td>
						<td align="left">
							<input name="cityId" class="easyui-combobox" width=150 id="cityId1" required="true" editable="false" />
						</td>
						<td>
							所在区县:
						</td>
						<td align="left">
							<input name="districtId" class="easyui-combobox" width=150 id="districtId1" required="true" editable="false" />
						</td>
					</tr>
				</table>
				<table align="center" width="100%" style="padding: 10px; border-top-color: olive; border-top-style: solid;">
					<tr align="center">
						<td align="right"">
							手机号码：
						</td>
						<td align="left">
							<input style="width: 150px;" name="mobilephone" class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage="输入11位手机号或加区号的固话号码" />
						</td>
						<td align="right"">
							分公司名称：
						</td>
						<td align="left">
							<input style="width: 150px;" id="departmentId" name="departmentId" class="easyui-combobox" required="true" />
							<input style="width: 150px;" id="branchNameForm" name="branchName" type="hidden" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							所在行地区号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankPrefectureNum" class="easyui-validatebox" validType="number" required="true" />
						</td>
						<td align="right"">
							所在行别：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankNum" id="bankNum" />
							<input style="width: 150px;" name="bankRank" id="bankRank" type="hidden" width="130px" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							开&nbsp;户&nbsp;行：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankName" class="easyui-validatebox" required="true" />
						</td>
						<td align="right"">
							账&nbsp;户&nbsp;名：
						</td>
						<td align="left">
							<input style="width: 150px;" name="accountName" class="easyui-validatebox" required="true" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							账&nbsp;&nbsp;&nbsp;&nbsp;号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="account" class="easyui-validatebox" validType="number" required="true" />
						</td>
						<td align="right"">
							卡折类型：
						</td>
						<td align="left">
							<input style="width: 150px;" name="cardFlag" id="cardFlag" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							账号用途：
						</td>
						<td align="left">
							<input style="width: 150px;" name="useType" id="useType" />
						</td>
						<td align="right"">
							支付行号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="payBranchno" class="easyui-validatebox" validType="number" required="true" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							是否启用：
						</td>
						<td align="left">
							<input style="width: 150px;" name="onUsed" id="onUsed" />
							<input name="accountType" type="hidden" id="accountType" value="1" />
						</td>
						<td align="right"">
							账户身份证号：
						</td>
						<td align="left">
							<input name="credentialsNumber" style="width: 150px;" class="easyui-validatebox" required="true" validType="idnumberAll" id="credentialsNumber" />
						</td>
					</tr>
				</table>
				<input id="borrowerCredentialsNumber" name="borrowerCredentialsNumber" type="hidden" "/>
				<input id="borrowerName" name="borrowerName" type="hidden" "/>
			</form>
		</div>
		<div id="abutn">
			<a id="cardSaveButton" class="easyui-linkbutton" onclick="doSaveCard();">确定</a><a class="easyui-linkbutton" onclick="closeAccDialog();">取消</a>
		</div>
		<div id="remarkDialog" class="easyui-dialog" title="备注" modal="true" align="center" buttons="#rbutn" style="padding: 10px; border: 0px; margin: 0px; width: 300px; height: 210px" closed="true" resizable="true" inline="false" closable="false">
			<form id="remarkForm" method="post">
				<textarea id="remarkComment" cols="70" rows="2" name="remark" class="easyui-validatebox" validType="length[0,50]" style="width: 260px; height: 100px;" onclick="$('#remarkComment').attr('readonly',false);"></textarea>
				<input id="returnId" name="returnPlanId" type="hidden" />
			</form>
			<span id="remarkSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span id="countVachar"></span>
		</div>
		<div id="rbutn">
			<a id="remarkSaveButton" class="easyui-linkbutton" onclick="doSaveRemark();">确定</a><a class="easyui-linkbutton" onclick="closeRemarkDialog();">取消</a>
		</div>
	</body>
</html>
