<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.creditease.rc.util.PropertiesUtil" %>
<%@ page import="com.creditease.core.security.SpringSecurityUtils" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
	DESPlus desPlus = new DESPlus();
	String clientId = desPlus.encrypt(new Date().toString());
	String path2 = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>新增回访页面</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script  type="text/javascript">
		var serverName = "<%=path2%>";
		//附件上传
		var cmUrl = "<%=cmUrl%>";
		var clientid = "<%=clientId%>";
		var userId = "<%=userId%>";
		var creditapplicationId = ${customerReturnVisitVo.creditapplicationId };
	/*	console.info("clientId---"+clientid);
		console.info("cmUrl---"+cmUrl);
		console.info("userId---"+userId);
		console.info("creditapplicationId---"+creditapplicationId);*/

		function validPhone(value) {
			var a = /^[1][3-8]\d{9}$|^((\d{4}|\d{3})-(\d{7,8}))$/;
			if (a.test(value) || value == "") {

				return true;
			}
			else {

				return false;
			}}
		function stopBubble(e) {
			var e = e ? e : window.event;
			if (window.event) { // IE
				e.cancelBubble = true;
			} else { // FF
				//e.preventDefault();
				e.preventDefault();
				e.stopPropagation();
			}
		}

		function validatePhoneNumber(obj,event){
			console.info("phoneNumber:-----------------");
			console.info("obj.id:-----------------"+obj.id);
			var phoneNumber = $(obj).val();
			var tmp_id = '#'+obj.id+'_tmp';
			if(phoneNumber && validPhone(phoneNumber)){
				console.info("obj.id:-----------------"+obj.id);
				$(tmp_id).val(phoneNumber);
				console.info("temp:"+$(tmp_id).val());
				stopBubble(event);
				return true;
			}else{
				$(obj).focus();
				stopBubble(event);
				return false;
			}
		}
	</script>
	<script type="text/javascript" src="<%=basePath%>scripts/rtnvisits/editReturnVisitInfo_init.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/rtnvisits/editReturnVisitInfo_operate.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/rtnvisits/editReturnVisitInfo_validate.js"></script>
</head>

<body class="easyui-layout">
<div region="center" align="center">
	<div align="center">
		<form id="customerReturnVisitForm">
			<h2 align="center">申请编号： ${customerReturnVisitVo.businessNumber }</h2>

			<input type="hidden" name="creditapplicationId" value="${customerReturnVisitVo.creditapplicationId }"/><!-- 申请id -->
			<input id="repaymentDateHide"  type="hidden"   value="<fmt:formatDate value='${customerReturnVisitVo.repaymentDate }' pattern='yyyy-MM-dd'/>"/>
			<input id="customerReturnVisitId" name="customerReturnVisitId"  type="hidden" value="${customerReturnVisitVo.customerReturnVisitId }"/>
			<input id="customerReturnVisitIdPresent"  type="hidden" value="${customerReturnVisitVo.customerReturnVisitId }"/>
			<input type="hidden" name=""/><!-- 客户回访id -->
			<table width="92%" class="tabfrom" align="center">
				<tr>
					<th width="20%">回访日期：</th><td width="30%"><input id="visitDate" name="visitDate" class="easyui-combobox"/></td>
					<th width="20%">还款日期：</th><td width="30%"><input id="repaymentDate" name="repaymentDate" readonly="readonly" value="<fmt:formatDate value='${customerReturnVisitVo.repaymentDate }' pattern='yyyy-MM-dd'/>"/></td>
				</tr>
				<tr>
					<th width="20%">回访方式：</th>
					<td width="30%">
						<%--<input name="visitWay" id="visitWay"/>--%>
						<input type="radio" name="visitWay" value="1001" checked/> 电话回访
						<span><input type="radio" name="visitWay" value="1002" /> 实地回访</span>


					</td>
					<th width="20%">回访时长：</th><td width="30%">
					<input id="hours" name="visitDurationHours" max="99" precision="0" class="easyui-numberbox" required="true" style="width:50px;">小时
					<input id="minutes" name="visitDurationMinutes" class="easyui-combobox" required="true" >分钟
					<input id="visitDurationTimes" name="visitDurationTimes" type="hidden"/></td>
				</tr>
				<tr>
					<th width="20%">借款用途变更：</th><td width="30%">

					<%--<input name="loanUse" id="loanUse"/>--%>
					<input type="radio" name="loanUse" value="0" /> 是
					<span><input type="radio" name="loanUse" value="1" checked/> 否</span>
				</td>
					<th width="20%">新借款用途：</th><td width="30%">
					<input id="newLoanUse" readonly="readonly"/>
					<input name="newLoanUse"  id="industryCategory" type="hidden"/>
					<a plain="true" class="easyui-linkbutton" onclick="$('#industryCategory').val('');$('#newLoanUse').val('');$('#newLoanUse').validatebox('validate');">清除</a>
				</td>
				</tr>

				<%--<tr>
					<th width="20%">新增家庭收入来源：</th><td width="30%"><input name="familyIncome" id="familyIncome"/></td>
					<th width="20%">新家庭收入来源：</th><td width="30%">
					<input  id="newFamilyIncome" readonly="readonly" class="easyui-validatebox" onfocus="selectProject('newFamilyIncome','newFamilyIncomeReal');"/>
					<input id="newFamilyIncomeReal" name="newFamilyIncome" type="hidden"/>
					<a plain="true" class="easyui-linkbutton" onclick="$('#newFamilyIncome').val('');$('#newFamilyIncome').validatebox('validate');$('#newFamilyIncomeReal').val('');">清除</a>
				</td>
				</tr>
				<tr>
					<th width="20%">新增经营收入来源：</th><td><input name="income" id="income"/></td>
					<th width="20%">新经营收入来源：</th><td><input readonly="readonly"  id="newIncome" class="easyui-validatebox" onfocus="selectNewIncome('newIncome','newIncomeReal');"/>
					<input id="newIncomeReal" name="newIncome" type="hidden"/>
					<a plain="true" class="easyui-linkbutton" onclick="$('#newIncome').val('');$('#newIncome').validatebox('validate');$('#newIncomeReal').val('');">清除</a>
				</td>
				</tr>--%>

				<tr>
					<th width="20%">新大项开支：</th><td width="30%">

					<%--<input name="spending" id="spending"/>--%>
					<input type="radio" name="spending" value="0" /> 是
					<span><input type="radio" name="spending" value="1" checked/> 否</span>
				</td>
					<th width="20%">大项开支：</th><td width="30%"><input  maxlength="64" readonly="readonly" name="newSpending" id="newSpending" onmouseover="showInputTip('新的大项开支（指除借款用途本身外，例如：红白事、大件家私、购房/车、孩子学费等）');" onmouseout="hideInputTip();"/></td>
				</tr>


				<tr>
					<th width="20%">新增债务：</th><td width="30%"><input name="newDebt" id="newDebt"/></td>
					<th width="20%">债权债务金额：</th><td width="30%"><input max="90000000" precision="0"  name="newDebtMoney" id="newDebtMoney" class="easyui-numberbox" /></td>
				</tr>
				<tr>
					<th align="right">客户还款态度：</th><td colspan="3"><input name="attitudeForRepayment" id="attitudeForRepayment"/>
						<span style="margin-left: 30px">对待客户经理态度：<input maxlength="32" name="attitudeForCutomermanager" id="attitudeForCutomermanager" /></span>
                           <span style="margin-left: 30px">是否高危标记：
							   <%--<input name="highDangered" id="highDangered"/>--%>
							   <input type="radio" name="highDangered" value="0" /> 是
								<span><input type="radio" name="highDangered" value="1" checked/> 否</span>
							   <input  id="higDangerCustomer" onfocus="selectHighDangerCustomer('higDangerCustomer','highDangerReason');" readonly="readonly"   />
							  <span> <input id="highDangerReason" name="highDangerReason" type="hidden"/></span>
							</span>	</td>
				</tr>
				<tr>
					<th width="20%">收入来源：</th><td colspan="3"><input name="sourceIncomeChangedType" id="sourceIncomeChangedType"/>


					<span  id="sourceIncomeChangedContent_div">
						<input id="sourceIncomeChangedContent" name="sourceIncomeChangedContent" type="hidden"/>
						<input id="sourceIncomeChangedContentStr" name="sourceIncomeChangedContentStr" type="hidden"/>
						收入来源变更内容：<input  id="sourceIncomeChangedContent_ipt" style="width:500px" onfocus="selectSourceIncomeChangedContent('sourceIncomeChangedContent_ipt','sourceIncomeChangedContent','yes');" readonly="readonly"   />

					</span>
				</td>
				</tr>
				<tr>
					<th width="20%">联系方式变更：</th>
					<td width="30%">
						<%--<input name="contactWay" id="contactWay"/>--%>
						<input name="contractChangeType" id="contractChangeType"/>
						<a plain="true" class="easyui-linkbutton" onclick="$('#contractChangeType').combotree('setValues','');">清除</a>

					</td>
					<td colspan="2">
						<span id="contractChangeType_div"> </span>
						<%--				借款人:<input maxlength="32" name="changeBorrowerPhone"/> &nbsp; &nbsp; 共借人:<input maxlength="32" name="changeCoborrowerPhone"/><br>
                                        担保1: <input maxlength="32" name="changeGuaranteeFirstPhone"/> &nbsp; &nbsp;担保2:<input maxlength="32" name="changeGuaranteeSecondPhone"/>
                --%>
						<input type="hidden" id="phoneValid" value="true">
						<input type="hidden" id="changeBorrowerPhone_tmp">
						<input type="hidden" id="changeCoborrowerPhone_tmp">
						<input type="hidden" id="changeGuaranteeFirstPhone_tmp">
						<input type="hidden" id="changeGuaranteeSecondPhone_tmp" >
					</td>
					<%--	<th width="20%">新联系方式：</th><td width="30%"><input maxlength="32" name="newContactWay" id="newContactWay" /></td>
                    --%></tr>
				<tr>
					<th width="20%">借款用途是否一致：</th>
					<td width="80%" colspan="3">
						<%--<input name="isPurposeConsistency" id="isPurposeConsistency"/>--%>
						<input type="radio" name="isPurposeConsistency" value="0" checked/> 是
						<span><input type="radio" name="isPurposeConsistency" value="1" /> 否</span>
						<span style="margin-left: 50px;display: none" id="reasonNotConsistencyDiv">借款用途不一致原因： <input   name="reasonNotConsistency" id="reasonNotConsistency" style="width:500px"/></span>
				</tr>


				<tr>
					<th width="20%">回访照片是否齐全：</th>
					<td width="80%" colspan="3">
						<%--<input name="isPurposeConsistency" id="isPurposeConsistency"/>--%>
						<input type="radio" name="isComplete" value="0" checked/> 齐全
						<span><input type="radio" name="isComplete" value="1" /> 不齐全</span>
						<span style="margin-left: 50px;display: none" id="reasonForIncompleteDiv">回访照片不齐全原因： <input   name="reasonForIncomplete" id="reasonForIncomplete" style="width:500px"/></span>
				</tr>

				<tr>
					<th width="20%">家庭成员变化：</th><td colspan=3" width="80%"><input id="familyNumberCondition" maxlength="100" name="familyNumberCondition" size="130" onmouseover="showInputTip('家庭成员变化（例如：夫妻关系、健康状况、成员是否在本地）');" onmouseout="hideInputTip();"/></td>
				</tr>
				<tr>
					<th width="20%">其他影响客户还款能力或意愿：</th><td colspan=3" width="80%"><input maxlength="100" name="otherFactor" size="130" onmouseover="showInputTip('其他影响客户还款能力或意愿的情况（例如：原来的经营规模变化、自然灾害、市场价格变动、资金回笼情况等）');" onmouseout="hideInputTip();"/></td>
				</tr>
				<tr>
					<th width="10%" align="right" nowrap="nowrap"> 附件：
					</td>
					<td colspan=3" height="250px">
						<iframe id="openCM" src="" style="width: 100%;height: 100%;border: 0"></iframe>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toobar" align="center">
		<a class="easyui-linkbutton" id="commitButton" href="javascript:editUpdateSubmit();">确定</a>
	</div>
</div>
<div id="windowBorrowUse">
	<table id="tableUse"></table>
</div>
<div id="windowProject">
	<table id="tableProject"></table>
</div>
<div id="windowNewIncome">
	<table id="tableNewIncome"></table>
</div>
<div id="windowHighDangerCustomer">
	<table id="tableHighDangerCustomer"></table>
</div>
<div id="windowSourceIncomeChangedContent">
	<table id="tableSourceIncomeChangedContent"></table>
</div>
</body>
</html>
