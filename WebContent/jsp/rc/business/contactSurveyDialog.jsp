<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=basePath%>scripts/business/contactSurvey.js"></script>
<script type="text/javascript">
	
</script>
<div class="easyui-layout" fit="true">
	<div region="center">
		<div id="contactSurveyTabs" class="easyui-tabs" fit="true">
			<div title="以下由宜信工作人员填写" align="center">
				<form id="contactSurvey_form" class="easyui-form" method="post">
					<table width="100%px">
						<tr>
							<td height="50" width="30%px">
								&nbsp;
							</td>
							<td align="left">
								1、借款人还款能力
								<font color='red'>*</font>
							</td>
							<td align="right">
								<input id="borrowerrepaymentAbility" name="borrowerrepaymentAbility" type="text" class="easyui-combobox" required="true" style="width: 170px;" />
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
								<input id="repaymentWill" name="repaymentWill" type="text" class="easyui-combobox" required="true" style="width: 170px;"/>
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
								<input id="badrecordsAndHobbies" name="badrecordsAndHobbies" type="text" class="easyui-combobox" required="true" style="width: 170px;"/>
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
								<input id="otherSupplementary" name="otherSupplementary" type="text" style="width: 170px;"/>
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
		</div>
	</div>
</div>
