<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'borrowerServiceApp.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		var serverNmae="<%=path%>";
	function tijiao() {
		ajaxSubmit(serverName + "/application/add.do", $("#form1")
				.serializeObject(), function(result) {
			alert(result);
		});
	}
</script>
	</head>

	<body>
		<form id="formUpdate" name="formUpdate" method="post" action="">
			<table width="892" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="5" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						个人申请
						<input id="borrowerServiceAppIdShow"
							name="borrowerService.borrowerServiceAppId"
							type="hidden">
						<input id="creditapplicationIdShow"
							name="borrowerService.creditapplicationId"
							type="hidden">
					</td>
				</tr>
				<tr>
					<td width="149" align="right">
						业务编号：
					</td>
					<td width="193">
						<input type="text" 
							id="businessNumberBorrowerShow" readonly="readonly"
							style="width: 150px;" />
					</td>
					<td width="132">
						&nbsp;
					</td>
					<td width="169" align="right">
						民族：
					</td>
					<td width="207">
						<input type="text" name="borrowerService.nationality"
							id="businessNumber4" style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td width="149" align="right">
						申请金额：
					</td>
					<td width="193">
						<input type="text" name="borrowerService.applyLimit"
							id="applyLimitBorrower" class="easyui-validatebox"
							validType="number" required="true" style="width: 150px;" />
					</td>
					<td width="132">
						&nbsp;
					</td>
					<td width="169" align="right">
						身份证：
					</td>
					<td width="207">
						<input type="text" name="borrowerService.credentialsNumber"
							id="credentialsNumberBorrrower" style="width: 150px;" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td>
						<input type="text" name="borrowerService.name"
							id="businessNumber2" class="easyui-validatebox"  required="true"  style="width: 150px;"/></td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						曾用名：
					</td>
					<td>
						<input type="text" name="borrowerService.former"
							id="businessNumber5" style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td align="right">
						出生日期：
					</td>
					<td>
						<input type="text" name="borrowerService.birthday" id="businessNumber3"
							style="width: 150px;" readonly="readonly"/>
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						性别：
					</td>
					<td>
						<input name="borrowerService.gender" id="genderBorrowerShow"
							style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td align="right">
						婚姻状况：
					</td>
					<td>
						<%--<select name="borrowerService.maritalStatus" id="maritalStatusShow"
							style="width: 150px;" >
							 <option value="1">单身</option>
							<option value="2">已婚</option>
							<option value="3">丧偶</option>
							<option value="4">分居</option> 
						</select>
						--%><input name="borrowerService.maritalStatus" id="maritalStatusShow"
							style="width: 150px;" />
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						就业状况：
					</td>
					<td>
						<input name="borrowerService.jobStatus" id="jobStatusShow"
							style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">
						家庭住址：
					</td>
					<td>
						<p>
							<label>
								<input type="checkbox" value="1"
									name="borrowerService.livingCommercial" id="CheckboxGroup1_0" />
								自有商品房
							</label>
							<br />
							<label>
								<input type="checkbox" value="1"
									name="borrowerService.livingSelf" id="CheckboxGroup1_1" />
								自有宅基地
							</label>
							<br />
							<label>
								<input type="checkbox" value="1" onclick="livingRentShowDate();"
									name="borrowerService.livingRent" id="livingRentShow" />
								租住
							</label>
							（到期日
							<input type="text" name="borrowerService.livingDate" 
								id="livingDateShow" class="easyui-datebox" editable="false"/>
							）
							<br />
							<label>
								<input type="checkbox" name="borrowerService.livingRelative"
									value="1" id="CheckboxGroup1_3" />
								亲戚住房
							</label>
							<br />
							<label>
								<input type="checkbox" name="borrowerService.livingOther"
									value="1" id="CheckboxGroup1_4" />
								其他
							</label>
							<br />
						</p>
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right" valign="top">
						现经营场所地址：
					</td>
					<td>
						<label>
							<input type="checkbox" name="borrowerService.businessCommercial"
								value="1" id="CheckboxGroup1_5" />
							自有商品房
						</label>
						<br />
						<label>
							<input type="checkbox" name="borrowerService.businessSelf"
								value="1" id="CheckboxGroup1_6" />
							自有宅基地
						</label>
						<br />
						<label>
							<input type="checkbox" name="borrowerService.businessRent"
								value="1" id="businessRentShow" onclick="businessRentShowDate();"/>
							租住
						</label>
						（到期日
						<input type="text" name="borrowerService.businessDate" id="businessDateShow"
							id="textfield"  class="easyui-datebox" editable="false"/>
						）
						<br />
						<label>
							<input type="checkbox" name="borrowerService.businessRelative"
								value="1" id="CheckboxGroup1_8" />
							亲戚住房
						</label>
						<br />
						<label>
							<input type="checkbox" name="borrowerService.businessOther"
								value="1" id="CheckboxGroup1_9" />
							其他
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						家庭住址面积：
					</td>
					<td>
						<input type="text" name="borrowerService.livingArea"
							id="businessNumber8"  class="easyui-validatebox" validType="number" style="width: 150px;" />
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						经营场所面积：
					</td>
					<td>
						<input type="text" name="borrowerService.businessArea"
							id="businessNumber9"  class="easyui-validatebox" validType="number" style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td align="right">
						家庭住址居住年限：
					</td>
					<td>
						<input type="text" name="borrowerService.livingYear"
							id="businessNumber6"  class="easyui-validatebox" validType="number" style="width: 150px;" />
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						经营场所已使用年限：
					</td>
					<td>
						<input type="text" name="borrowerService.businessYear"
							id="businessNumber10"  class="easyui-validatebox" validType="number" style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td align="right">
						手机号码：
					</td>
					<td>
						<input type="text" name="borrowerService.mobilephone"
							id="businessNumber7" class="easyui-validatebox" validType="phoneNumber"  style="width: 150px;" />
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						办公电话：
					</td>
					<td>
						<input type="text" name="borrowerService.officePhone"
							id="businessNumber10" class="easyui-validatebox" validType="phoneNumber"  style="width: 150px;" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
			<table width="898" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="5" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						家庭成员情况
					</td>
				</tr>
				<tr>
					<td width="149">
						姓名
					</td>
					<td width="199">
						与申请人关系
					</td>
					<td width="168">
						年龄
					</td>
					<td width="185">
						职业
					</td>
					<td width="155">
						联系电话
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="familymemberList[0].name"
							id="businessNumber11" />
					</td>
					<td>
						<input type="text" name="familymemberList[0].borrowerreRationship"
							id="borrowerreRationshipShow0" />
					</td>
					<td>
						<input type="text" name="familymemberList[0].age"
							id="businessNumber13" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input type="text" name="familymemberList[0].profession"
							id="businessNumber14" />
					</td>
					<td>
						<input type="text" name="familymemberList[0].telphone"
							id="businessNumber15" class="easyui-validatebox" validType="phoneNumber" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="familymemberList[1].name"
							id="businessNumber16" />
					</td>
					<td>
						<input type="text" name="familymemberList[1].borrowerreRationship"
							id="borrowerreRationshipShow1" />
					</td>
					<td>
						<input type="text" name="familymemberList[1].age"
							id="businessNumber18" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input type="text" name="familymemberList[1].profession"
							id="businessNumber19" />
					</td>
					<td>
						<input type="text" name="familymemberList[1].telphone"
							id="businessNumber20" class="easyui-validatebox" validType="phoneNumber" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="familymemberList[2].name"
							id="businessNumber21" />
					</td>
					<td>
						<input type="text" name="familymemberList[2].borrowerreRationship"
							id="borrowerreRationshipShow2" />
					</td>
					<td>
						<input type="text" name="familymemberList[2].age"
							id="businessNumber23" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input type="text" name="familymemberList[2].profession"
							id="businessNumber24" />
					</td>
					<td>
						<input type="text" name="familymemberList[2].telphone"
							id="businessNumber25" class="easyui-validatebox" validType="phoneNumber" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="familymemberList[3].name"
							id="businessNumber26" />
					</td>
					<td>
						<input type="text" name="familymemberList[3].borrowerreRationship"
							id="borrowerreRationshipShow3"/>
					</td>
					<td>
						<input type="text" name="familymemberList[3].age"
							id="businessNumber28" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input type="text" name="familymemberList[3].profession"
							id="businessNumber29" />
					</td>
					<td>
						<input type="text" name="familymemberList[3].telphone"
							id="businessNumber30" class="easyui-validatebox" validType="phoneNumber" />
					</td>
				</tr>
				<tr>
					<td width="168">
						<input type="text" name="familymemberList[4].name"
							id="businessNumber43" />
					</td>
					<td width="177">
						<input type="text" name="familymemberList[4].borrowerreRationship"
							id="borrowerreRationshipShow4" />
					</td>
					<td width="168">
						<input type="text" name="familymemberList[4].age"
							id="businessNumber45" class="easyui-validatebox" validType="number" />
					</td>
					<td width="175">
						<input type="text" name="familymemberList[4].profession"
							id="businessNumber46" />
					</td>
					<td width="168">
						<input type="text" name="familymemberList[4].telphone"
							id="businessNumber47" class="easyui-validatebox" validType="phoneNumber" />
					</td>
				</tr>
			</table>

			<p>
				&nbsp;
			</p>
			<table width="898" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="6" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						相关工作情况
					</td>
				</tr>
				<tr>
					<td width="185">
						就职公司
					</td>
					<td width="153">
						地址
					</td>
					<td width="139">
						工作年限
					</td>
					<td width="131">
						职位
					</td>
					<td width="126">
						联系人姓名
					</td>
					<td width="126">
						联系人电话
					</td>
				</tr>
				<tr>
					<td>
						<input name="jobInfoList[0].company" type="text"
							id="businessNumber31" size="18" />
					</td>
					<td>
						<input name="jobInfoList[0].workunitAddress" type="text"
							id="businessNumber32" size="18" />
					</td>
					<td>
						<input name="jobInfoList[0].years" type="text"
							id="businessNumber33" size="18" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input name="jobInfoList[0].post" type="text"
							id="businessNumber34" size="18" />
					</td>
					<td>
						<input name="jobInfoList[0].contantPerson" type="text"
							id="businessNumber35" size="18" />
					</td>
					<td>
						<input name="jobInfoList[0].contanTelephone" type="text"
							id="businessNumber36" size="18" class="easyui-validatebox" validType="phoneNumber"/>
					</td>
				</tr>
				<tr>
					<td>
						<input name="jobInfoList[1].company" type="text"
							id="businessNumber37" size="18" />
					</td>
					<td>
						<input name="jobInfoList[1].workunitAddress" type="text"
							id="businessNumber38" size="18" />
					</td>
					<td>
						<input name="jobInfoList[1].years" type="text"
							id="businessNumber39" size="18" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input name="jobInfoList[1].post" type="text"
							id="businessNumber40" size="18" />
					</td>
					<td>
						<input name="jobInfoList[1].contantPerson" type="text"
							id="businessNumber41" size="18" />
					</td>
					<td>
						<input name="jobInfoList[1].contanTelephone" type="text"
							id="businessNumber42" size="18" class="easyui-validatebox" validType="phoneNumber"/>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
			<table width="898" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="6" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						经营情况（包括当前和历史的经营情况）
					</td>
				</tr>
				<tr>
					<td width="188">
						经营项目
					</td>
					<td width="193">
						经营项目详细
					</td>
					<td width="154">
						起始年份(年)
					</td>
					<td width="146">
						状态
					</td>
					<td width="94">
						营业执照
					</td>
					<td width="85">
						税务登记证
					</td>
				</tr>
				<tr>
					<td>
						<input name="surveybusinessinfoList[0].operatingItems"
							id="operatingItemsShow0">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[0].operatingItemsDetail"
							type="text" id="businessNumber48" size="20" />
					</td>
					<td>
						<input name="surveybusinessinfoList[0].startingDate" type="text"
							id="businessNumber53" size="20" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<label for="input4"></label>
						<input name="surveybusinessinfoList[0].state" id="stateSurveybusinessinfoListShow0">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[0].businessLicense"
							id="businessLicenseShow0">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[0].taxRegistrationCertificate"
							id="taxRegistrationCertificateShow0">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="surveybusinessinfoList[1].operatingItems"
							id="operatingItemsShow1">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[1].operatingItemsDetail"
							type="text" id="businessNumber49" size="20" />
					</td>
					<td>
						<input name="surveybusinessinfoList[1].startingDate" type="text"
							size="20" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input name="surveybusinessinfoList[1].state" id="stateSurveybusinessinfoListShow1" ">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[1].businessLicense"
							id="businessLicenseShow1">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[1].taxRegistrationCertificate"
							id="taxRegistrationCertificateShow1">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="surveybusinessinfoList[2].operatingItems"
							id="operatingItemsShow2">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[2].operatingItemsDetail"
							type="text" id="businessNumber50" size="20" />
					</td>
					<td>
						<input name="surveybusinessinfoList[2].startingDate" type="text"
							id="businessNumber51" size="20" class="easyui-validatebox" validType="number" />
					</td>
					<td>
						<input name="surveybusinessinfoList[2].state" id="stateSurveybusinessinfoListShow2">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[2].businessLicense"
							id="businessLicenseShow2">
						</input>
					</td>
					<td>
						<input name="surveybusinessinfoList[2].taxRegistrationCertificate"
							id="taxRegistrationCertificateShow2">
						</input>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
			<table width="898" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="6" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						存款和应收现金
					</td>
				</tr>
				<tr>
					<td width="192">
						存款机构或应支付现金方
					</td>
					<td width="196">
						存款机构或应支付现金方
					</td>
					<td width="140">
						地址
					</td>
					<td width="152">
						电话
					</td>
					<td width="112">
						余额（元）
					</td>
					<td width="68">
						证明文档
					</td>
				</tr>
				<tr>
					<td>
						<input name="depositList[0].depositOrganization" id="depositOrganizationShow0">
						</input>
					</td>
					<td>
						<input name="depositList[0].depositOrganizationDetail" type="text"
							id="businessNumber66" size="20" />
					</td>
					<td>
						<input name="depositList[0].address" type="text"
							id="businessNumber67" size="20" />
					</td>
					<td>
						<input name="depositList[0].telephone" type="text"
							id="businessNumber68" size="20" class="easyui-validatebox" validType="phoneNumber"/>
					</td>
					<td>
						<input name="depositList[0].balance" type="text"
							id="balanceShow0" size="16" class="easyui-numberbox" min="0" precision="2"
							onblur="addBlanceShow();"/>
					</td>
					<td>
						<input name="depositList[0].proveDocument" id="proveDocumentShow0">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="depositList[1].depositOrganization" id="depositOrganizationShow1">
						</input>
					</td>
					<td>
						<input name="depositList[1].depositOrganizationDetail" type="text"
							id="businessNumber70" size="20" />
					</td>
					<td>
						<input name="depositList[1].address" type="text"
							id="businessNumber71" size="20" />
					</td>
					<td>
						<input name="depositList[1].telephone" type="text"
							id="businessNumber72" size="20" class="easyui-validatebox" validType="phoneNumber"/>
					</td>
					<td>
						<input name="depositList[1].balance" type="text"
							id="balanceShow1" size="16" class="easyui-numberbox" min="0" precision="2"
							onblur="addBlanceShow();"/>
					</td>
					<td>
						<input name="depositList[1].proveDocument" id="proveDocumentShow1">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="depositList[2].depositOrganization" id="depositOrganizationShow2">
						</input>
					</td>
					<td>
						<input name="depositList[2].depositOrganizationDetail" type="text"
							id="businessNumber87" size="20" />
					</td>
					<td>
						<input name="depositList[2].address" type="text"
							id="businessNumber88" size="20" />
					</td>
					<td>
						<input name="depositList[2].telephone" type="text"
							id="businessNumber89" size="20" class="easyui-validatebox" validType="phoneNumber"/>
					</td>
					<td>
						<input name="depositList[2].balance" type="text"
							id="balanceShow2" size="16" class="easyui-numberbox" min="0" precision="2"
							onblur="addBlanceShow();"/>
					</td>
					<td>
						<input name="depositList[2].proveDocument" id="proveDocumentShow2">
						</input>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						汇总：
					</td>
					<td>
						<input name="businessNumber66" type="text" id="totalBlanceShow"
							size="16" readonly="readonly"
							/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>

			<table width="899" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="6" align="center"
						style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
						借款申请
					</td>
				</tr>
				<tr>
					<td width="197">
						借款使用计划
					</td>
					<td width="163">
						借款使用计划详细
					</td>
					<td width="140">
						数量
					</td>
					<td width="140">
						单价(元)
					</td>
					<td width="145">
						总额(元)
					</td>
					<td width="76">
						证明文档
					</td>
				</tr>
				<tr>
					<td>
						<input name="applicationList[0].borrowUse" id="borrowUseShow0">
						</input>
					</td>
					<td>
						<input name="applicationList[0].borrowUseDetail" type="text"
							id="businessNumber891" size="20" />
					</td>
					<td>
						<input name="applicationList[0].quantity" type="text"
							id="quantityShow0" size="20" class="easyui-validatebox" validType="number"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[0].unitPrice" type="text"
							id="unitPriceShow0" size="20" class="easyui-numberbox" min="0" precision="2"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[0].totalAmount" type="text"
							id="totalAmountShow0" size="20" readonly="readonly"
							/>
					</td>
					<td>
						<input name="applicationList[0].proveDocument"
							id="proveDocumentApplicationShow0">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="applicationList[1].borrowUse"
							id="borrowUseShow1">
						</input>
					</td>
					<td>
						<input name="applicationList[1].borrowUseDetail" type="text"
							id="businessNumber89rr" size="20" />
					</td>
					<td>
						<input name="applicationList[1].quantity" type="text"
							id="quantityShow1" size="20" class="easyui-validatebox" validType="number"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[1].unitPrice" type="text"
							id="unitPriceShow1" size="20" class="easyui-numberbox" min="0" precision="2"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[1].totalAmount" type="text"
							id="totalAmountShow1" size="20" readonly="readonly"
							 />
					</td>
					<td>
						<input name="applicationList[1].proveDocument"
							id="proveDocumentApplicationShow1">
						</input>
					</td>
				</tr>
				<tr>
					<td>
						<input name="applicationList[2].borrowUse"
							id="borrowUseShow2">
						</input>
					</td>
					<td>
						<input name="applicationList[2].borrowUseDetail" type="text"
							id="busqNumber89sdgw34" size="20" />
					</td>
					<td>
						<input name="applicationList[2].quantity" type="text"
							id="quantityShow2" size="20" class="easyui-validatebox" validType="number"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[2].unitPrice" type="text"
							id="unitPriceShow2" size="20" class="easyui-numberbox" min="0" precision="2"
							onblur="addAmountShow();"/>
					</td>
					<td>
						<input name="applicationList[2].totalAmount" type="text"
							id="totalAmountShow2" size="20" readonly="readonly"
							 />
					</td>
					<td>
						<input name="applicationList[2].proveDocument" id="proveDocumentApplicationShow2">
						</input>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						汇总：
					</td>
					<td>
						<input name="businessNumber65" type="text" id="TTAmountShow"   size="20" readonly="readonly"/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<input name="familymemberList[0].familyMemberId" type="hidden" />
			<input name="familymemberList[1].familyMemberId" type="hidden" />
			<input name="familymemberList[2].familyMemberId" type="hidden" />
			<input name="familymemberList[3].familyMemberId" type="hidden" />
			<input name="familymemberList[4].familyMemberId" type="hidden" />
			<input name="jobInfoList[0].jobInfoId" type="hidden" />
			<input name="jobInfoList[1].jobInfoId" type="hidden" />
			<input name="surveybusinessinfoList[0].surveyBusinessInfoId"
				type="hidden" />
			<input name="surveybusinessinfoList[1].surveyBusinessInfoId"
				type="hidden" />
			<input name="surveybusinessinfoList[2].surveyBusinessInfoId"
				type="hidden" />
			<input name="depositList[0].depositid" type="hidden" />
			<input name="depositList[1].depositid" type="hidden" />
			<input name="depositList[2].depositid" type="hidden" />
			<input name="applicationList[0].applicationid" type="hidden" />
			<input name="applicationList[1].applicationid" type="hidden" />
			<input name="applicationList[2].applicationid" type="hidden" />
		</form>
	</body>
</html>
