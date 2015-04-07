<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>My JSP 'contactSurvey.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<!--<link rel="stylesheet" type="text/css" href="styles.css">
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript">
			var serverName = "<%=path%>";
			$(document).ready(function() {
				$("td").attr('style', 'font-size: 12; color: navy;');
			 	$(".panel-header").attr('style', "height:10px;");
			});
		</script>
		-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName = "<%=path%>";
			$(document).ready(function() {
				$("td").attr('style', 'font-size: 12; color: navy;');
			 	$(".panel-header").attr('style', "height:10px;");
			});
		</script>
		<script type="text/javascript" src="<%=basePath%>/scripts/business/contactSurvey.js"></script>
	</head>
	<body class="easyui-layout">
		<div region="north" style="height: 70px;">
			<table width=100%>
				<tr>
					<td align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center">
						<h2 style="color: #0099FF;">
							宜信平台借款服务联系人调查表
							<input id="borrowerServiceAppId" value="${borrowerServiceAppId}" type="hidden" />
							<input id="creditApplicationId" value="${creditApplicationId}" type="hidden" />
						</h2>
					</td>
				</tr>
			</table>
		</div>
		<div region="center">
			<div id="cc0" class="easyui-layout" fit="true">
				<div region="north" style="height: 50px;">
					<table width="100%" height="100%">
						<tr>
							<td width="20%px">
								&nbsp;
							</td>
							<td width="20%px">
								&nbsp;
							</td>
							<td width="20%px">
								借款人
							</td>
							<td width="20%px" align="left">
								<input id="borrowerServiceAppName" name="borrowerServiceAppName" type="text" disabled="disabled" />
							</td>
							<td width="20%px">
								&nbsp;
							</td>
						</tr>
					</table>
				</div>
				<div region="center">
					<div id="p1" class="easyui-panel" title="以下由宜信工作人员填写" style="width: 100% px; height: 360px;" iconCls="icon-save" collapsible="true">
						<form id="contactSurvey_form" class="easyui-form" method="post">
							<table width="100%px">
								<tr>
									<td height="30" colspan="4" align="center">
										对借款人的总体评价
									</td>
								</tr>
								<tr>
									<td height="50" width="30%px">
										&nbsp;
									</td>
									<td align="left">
										1、借款人还款能力
										<font color='red'>*</font>
									</td>
									<td align="right">
										<input id="borrowerrepaymentAbility" name="borrowerrepaymentAbility" type="text" class="easyui-combobox" required="true" />
									</td>
									<td height="50" width="30%px">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="50" width="30%px">
										&nbsp;
									</td>
									<td align="left">
										2、借款人还款意愿
										<font color='red'>*</font>
									</td>
									<td align="right">
										<input id="repaymentWill" name="repaymentWill" type="text" class="easyui-combobox" required="true" />
									</td>
									<td height="50" width="30%px">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="50" width="30%px">
										&nbsp;
									</td>
									<td align="left">
										3、有无不良记录或不良嗜好
										<font color='red'>*</font>
									</td>
									<td align="right">
										<input id="badrecordsAndHobbies" name="badrecordsAndHobbies" type="text" class="easyui-combobox" required="true" />
									</td>
									<td height="50" width="30%px">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="50" width="30%px">
										&nbsp;
									</td>
									<td align="left">
										4、其他补充
									</td>
									<td align="right">
										<input id="otherSupplementary" name="otherSupplementary" type="text" />
									</td>
									<td height="50" width="30%px">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="70" align="right" width="30%px">
										<input name="borrowerServiceAppId" type="hidden" />
									</td>
									<td height="70" align="center" colspan="2">
										<input id="borrowerSurveyId" name="borrowerSurveyId" type="hidden" />
									</td>
									<td height="70" align="left" width="30%px">
										<input name="familyWageincome" type="hidden" />
										<input name="otherIncome" type="hidden" />
										<input name="bankSave" type="hidden" />
										<input name="claims" type="hidden" />
										<input name="familyExpenditure" type="hidden" />
										<input name="bankLiabilitites" type="hidden" />
										<input name="liabilities" type="hidden" />
										<input name="totalMoney" type="hidden" />
									</td>
								</tr>


							</table>
						</form>
					</div>
					<table width="100%px">
						<tr>
							<td height="70" width="30%px">
								&nbsp;
							</td>
							<td height="70" align="center" colspan="2">
								<a class="easyui-linkbutton" href="javascript:void(0)" onclick="doSaveAndReturn()" iconCls="icon-save">保存&返回</a>
								<a class="easyui-linkbutton" href="javascript:void(0)" onclick="doClearContactSurvey()" iconCls="icon-reload">清空</a>
								<a class="easyui-linkbutton" href="javascript:void(0)" onclick="doCancel()" iconCls="icon-cancel">取消</a>
							</td>
							<td height="70" width="30%px">
								&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

	</body>
</html>
