<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String isURL = request.getRequestURL().toString();
	if (isURL.indexOf(".cn") > 0) {
		if (cmUrl.indexOf(".corp") > 0) {
			cmUrl = cmUrl.replaceAll(".corp", ".cn");
		}
	} else if (isURL.indexOf(".corp") > 0) {
		if (cmUrl.indexOf(".cn") > 0) {
			cmUrl = cmUrl.replaceAll(".cn", ".corp");
		}
	}
	DESPlus desPlus = new DESPlus();
	String DESNow = desPlus.encrypt(new Date().getTime() + "");
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'bankCardVeiw.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/bankCardVeiw.js"></script>
		
	</head>

	<body class="easyui-layout">
		<div region="center">
			<form id="accountInfo" novalidate>
				<table>
					<tr>
						<td>
							<input name="accountInfoId" id="accountInfoId" type="hidden" />
							<input id="creditapplicationId" name="creditapplicationId" type="hidden" value="${creditapplicationId}" />
							<input id="modifyCatdAppId" name="modifyCatdAppId" type="hidden" value="${modifyCatdAppId}" />
						</td>
					</tr>
				</table>
				<table align="center" width="100%" style="padding: 10px;">

					<tr align="center">
						<td>
							所在省:
						</td>
						<td align="left">
							<input name="provinceId" class="easyui-combobox" disabled="true" width=150 id="provinceId1" required="true" editable="false" />
						</td>
						<td>
							所在市:
						</td>
						<td align="left">
							<input name="cityId" class="easyui-combobox" disabled="true" width=150 id="cityId1" required="true" editable="false" />
						</td>
						<td>
							所在区县:
						</td>
						<td align="left">
							<input name="districtId" class="easyui-combobox" disabled="true" width=150 id="districtId1" required="true" editable="false" />
						</td>
					</tr>
				</table>
				<table align="center" width="100%" style="padding: 10px; border-top-color: olive; border-top-style: solid;">
					<tr align="center">
						<td align="right"">
							手机号码：
						</td>
						<td align="left">
							<input id="mobilephone" style="width: 150px;" name="mobilephone" class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage="输入11位手机号或加区号的固话号码" maxlength="11" readonly="readonly"/>
						</td>
						<td align="right"">
							分公司名称：
						</td>
						<td align="left">
							<input style="width: 150px;" id="departmentId" name="departmentId" class="easyui-combobox" disabled="true" required="true" editable="false" />
							<input style="width: 150px;" id="branchNameForm" name="branchName" type="hidden" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							所在行地区号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankPrefectureNum" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20" readonly="readonly"/>
						</td>
						<td align="right"">
							所在行别：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankNum" id="bankNum" class="easyui-combobox" disabled="true" required="true" />
							<input style="width: 150px;" name="bankRank" id="bankRank" type="hidden" width="130px" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							开&nbsp;户&nbsp;行：
						</td>
						<td align="left">
							<input style="width: 150px;" name="bankName" class="easyui-validatebox" required="true" maxlength="30" readonly="readonly"/>
						</td>
						<td align="right"">
							账号用途：
						</td>
						<td align="left">
							<input style="width: 150px;" name="useType" id="useType" class="easyui-combobox" disabled="true" required="true" />
						</td>

					</tr>
					<tr align="center">
						<td align="right"">
							账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="account" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20" readonly="readonly"/>
						</td>
						<td align="right"">
							卡折类型：
						</td>
						<td align="left">
							<input style="width: 150px;" name="cardFlag" id="cardFlag" class="easyui-combobox" disabled="true" required="true" />
						</td>
					</tr>
					<tr align="center">
						<td align="right"">
							支付行号：
						</td>
						<td align="left">
							<input style="width: 150px;" name="payBranchno" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20" readonly="readonly"/>
						</td>
						<td align="right"">
							是否启用：
						</td>
						<td align="left">
							<input style="width: 150px;" name="onUsed" id="onUsed" class="easyui-combobox" disabled="true" required="true" />
							<input name="accountType" type="hidden" id="accountType" value="0" />
						</td>

					</tr>
					<tr align="center">
						<td align="right"">
							账&nbsp;户&nbsp;名：
						</td>
						<td align="left">
							<input style="width: 150px;" id="accountName" name="accountName" class="easyui-combobox" disabled="true" maxlength="10" readonly="readonly"/>
						</td>
						<td align="right">
							账户身份证号：
						</td>
						<td align="left">
							<input name="credentialsNumber" style="width: 147px;" class="easyui-validatebox" readonly="readonly" validType="idnumberAll" id="credentialsNumber" />
							<input name="borrowerName" id="borrowerNameLook" type="hidden" />
							<input name="borrowerCredentialsNumber" id="borrowerCredentialsNumberLook" type="hidden" />
						</td>
					</tr>
					<tr>
					<td align="right">
							备注：
						</td>
						<td align="left" colspan="3">
						<textarea id='remark' name='remark' rows='2' style='width: 600px;' class="easyui-validatebox" required="true" readonly="readonly" validType="length[0,100]"  maxlength='100'></textarea> 
						</td>
					</tr>
					<tr><td align="right">
							&nbsp;
						</td>
						<td align="left" colspan="3">&nbsp;<span id="areaFiveS"></span></td>
						</tr>
					<tr><td align="right">
							文件上传：
						</td>
						<td align="center" colspan="3">&nbsp;</td>
						</tr>
					<tr>
						
						<td align="center" colspan="4">
							<iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/operation/transferParameter.action?clientId=${clientid}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529" style="width: 100%; height: 300px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
