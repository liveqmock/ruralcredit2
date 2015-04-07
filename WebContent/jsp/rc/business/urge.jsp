<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Properties properties = PropertiesUtil
			.loadProperties("spring/cm/cm.properties");
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'specialApply.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
	<script type="text/javascript">var serverName = "<%=path%>";</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>scripts/business/urge.js"></script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>Insert title here</title>
	</head>
	<body  class="easyui-layout">
		<c:choose>
			<c:when test="${urgeAdd==1}">
				<!-- 新增催收  urgeAdd==1  luohongjie -->
				<div region="center" style="font-size: 40px;">
				<!-- 判断是否是需要数据字典的值的下拉框是必填项 所需的参数-->
	            <input id="urgeAdd" type="hidden" value="${urgeAdd}"/>
					<form id="subUrgeForm" name="myForm">
						<table align="left" cellpadding="5" style="width: 100%;"
							cellspacing="0" border="0" id="table">
							<tr>
								<td colspan="6" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="6" style="font-size: 14px;">
									&nbsp;催收概括
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;催收概括：
								</td>

								<td>
									<input style="width: 135px" id="urgeSummarize"
										name="urgeVo.urgeSummarize" value="" type="text"
										required="true"   />
								</td>
								<input style="width: 135px" id="urgeCreditapplicationId"
									name="urgeVo.creditapplicationId" type="hidden" value="${creditapplicationId}"/>

								<td>
									催收方式：
								</td>
								<td>
									<input id="urgeWay" name="urgeVo.urgeWay"  
										style="width: 135px" />
								</td>
								<td>
									催收状态：
								</td>
								<td>
									<input id="urgeState" name="urgeVo.urgeState" type="text"
										style="width: 135px" required="true" />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;收回金额：
								</td>
								<!-- 必填，大于等于0 -->
								<td>
									<input type="text" id="reginMoney" name="urgeVo.reginMoney"   
										class="easyui-numberbox" precision="1" min="0"
										  maxlength="8" onblur="moneyIsNot0()" 
										style="width: 135px" />
								</td>
								<td>
									金额来源：
								</td>
								<!-- 如收回金额不为0，则必填，下拉：同与借款人关系 -->
								<td>
									<input style="width: 135px" id="moneySource"
										name="urgeVo.moneySource"  />
								</td>
								<td>
									催收时长：
								</td>
								<td>
									<input id="hours" name="urgeVo.hour" precision="0"
										class="easyui-numberbox"  type="text" max="99" min="0"
										maxlength="2" size="2" />
									时
									<input id="minutes" name="urgeVo.minute"
										class="easyui-combobox" required="true"
										validType='selectValueRequired["minutes","请选择"]'
										style="width: 80px;" />
									分
								</td>
							</tr>
							<tr>

								<td>
									&nbsp;&nbsp;&nbsp;催收日期：
								</td>
								<!-- 格式：YYYY-MM-DD -->
								<td>
									<input type="text" id="urgeDate" name="urgeVo.urgeDate" class="easyui-combobox" value="${dateString}"
										style="width: 135px"  />
								</td>
								<td>
									违约开始日期：
								</td>
								<td>
									<input id="breakbegindate" readonly="readonly" class="easyui-validatebox" required="true"
										name="urgeVo.breakbegindate" value="${breakbegindateSave}" type="text" style="width: 135px" />
										<input id="repaymentCycle"  name="urgeVo.repaymentCycle" type="hidden" value="${repaymentCycle}"/>
								</td>
								<td>
									催收沟通评价：
								</td>
								<td>
									<input id="urgeRemark" name="urgeVo.urgeRemark" type="text"
										style="width: 135px" required="true" />
								</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;重大风险预警：
								</td>
								<td>
									<input id="bigWarning" name="urgeVo.bigWarning" type="text"
										style="width: 135px" />
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;催收描述：
								</td>
								<td colspan="3">
									<textarea rows="2" cols="60" id="urgeDescribe"
										name="urgeVo.urgeDescribe"  maxlength="200"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="6" style="font-size: 14px;">
									&nbsp;承诺情况
								</td>
							</tr>

							<tr>
								<td>
									上次承诺还款金额(元):
								</td>
								<td>
								<input readonly="readonly" type="text"
										id="previousPromiseMoney"  
										class="easyui-numberbox" precision="1" min="0"
										  maxlength="8" name="urgeVo.previousPromiseMoney" value="${urgeVo.previousPromiseMoney}"
										style="width: 135px" />
								</td>
								<td>
									上次承诺还款时间
								</td>
								<td>
									<input readonly="readonly" id="previousPromiseTime" 
										name="urgeVo.previousPromiseTime" type="text"  value="${previousPromiseTime}"
										style="width: 135px" />
								</td>
								<td>
									是否履诺：
								</td>
								<td>
									<input id="ynPromise" name="urgeVo.ynPromise" type="text"
										style="width: 135px" />
								</td>
							</tr>
							<tr>
								<td>
									本次承诺还款金额(元):
								</td>
								<td>
									<input type="text" id="currentPromiseMoney"
										name="urgeVo.currentPromiseMoney"
										 class="easyui-numberbox" precision="1" min="0"
										 maxlength="8" style="width: 135px" />
								</td>
								<td>
									本次承诺还款时间：
								</td>
								<td colspan="3">
									<input id="currentPromiseTime" class="easyui-datebox"  editable="false"
										name="urgeVo.curentPromiseTime" required="true" type="text"
										style="width: 135px" />
								</td>
							</tr>
						</table>
					</form>
					<form id="relationOne">
						<table id="urgeContacts" align="left" cellpadding="5"
							style="width: 100%;" cellspacing="0" border="0" id="table">
							<tr>
								<td colspan="7" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3" style="font-size: 14px;">
									&nbsp;催收联系人
								</td>
								<td colspan="4" align="right">
									<a iconCls="icon-addOne" class="easyui-linkbutton"
										onclick="javascript:addUrgeContacts()"></a>
								</td>
							</tr>
							<tr>
								<td style="width: 115px">
									催收联系人姓名：
								</td>
								<td>
									<input type="text" id="urgeListContact0Name"
										name="urgeLinkmans[0].urgeLinkName" class="easyui-validatebox"
										required="true" style="width: 135px" />
								</td>
								<td style="width: 120px">
									与借款人关系：
								</td>
								<td>
									<input id="relation0" name="urgeLinkmans[0].borrowerRelation"
										type="text" style="width: 135px" required="true" />
								</td>
								<td style="width: 120px;">
									联系电话：
								</td>
								<td>
									<input type="text" id="urgeList0telephone"
										name="urgeLinkmans[0].linkTelephone"
										class="easyui-validatebox" validType="phoneNumber" 
										maxlength="11" style="width: 123px" />
								</td>
								<td style="width: 70px">
									&nbsp;
								</td>
							</tr>
						</table>
					</form>
				</div>
			</c:when>
			
			<c:otherwise>
				<div region="center">
					<form id="subUrgeForm" name="myForm">
						<table align="left" cellpadding="5" style="width: 100%;"
							cellspacing="0" border="0" id="table">
							<tr>
								<td colspan="6" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="6" style="font-size: 14px;">
									&nbsp;催收概括
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;催收概括：
								</td>
								<td>
									<input disabled="disabled" style="width: 135px"
										id="urgeSummarize" name="urgeVo.urgeSummarize"
										value="${urgeVo.urgeSummarize}" type="text"   />
								</td>
								<input style="width: 135px" id="urgeCreditapplicationId"
									name="urgeVo.creditapplicationId" type="hidden"
									value="${creditapplicationId}" />

								<td>
									催收方式：
								</td>
								<td>
									<input id="urgeWay" disabled="disabled" name="urgeVo.urgeWay" type="text"
										value="${urgeVo.urgeWay}" style="width: 135px" />
								</td>
								<td>
									催收状态：
								</td>
								<td>
									<input id="urgeState" disabled="disabled" name="urgeVo.urgeState" type="text"
										value="${urgeVo.urgeState}" style="width: 135px"
										  />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;收回金额：
								</td>
								<!-- 必填，大于等于0 -->
								<td>
									<input  type="text" disabled="disabled" id="reginMoney" name="urgeVo.reginMoney"
										value="${urgeVo.reginMoney}" class="easyui-numberbox" precision="1" min="0"
										  maxlength="8"
										  style="width: 135px" />
								</td>
								<td>
									金额来源：
								</td>
								<!-- 如收回金额不为0，则必填，下拉：同与借款人关系 -->
								<td>
									<input disabled="disabled" style="width: 135px" id="moneySource"
										value="${urgeVo.moneySource}" name="urgeVo.moneySource"
										type="text" />
								</td>
								<td>
									催收时长：
								</td>
								<td>
									<input  disabled="disabled" id="hours" name="urgeVo.hour" onclick="HHNum()"
										class="easyui-validatebox"   type="text"
										maxlength="2" size="2" value="${hour}" />
									时
									<!-- <input id="MM" name="urgeVo.minute" onclick="MMnum()"  class="easyui-validatebox" required="true" type="text" size="2" />
							 -->
									<input disabled="disabled" id="minutes" name="urgeVo.minute"
										class="easyui-combobox"  
										validType='selectValueRequired["minutes","请选择"]'
										style="width: 80px;"  value="${minute}"  />
									分
								</td>
							</tr>
							<tr>

								<td>
									&nbsp;&nbsp;&nbsp;催收日期：
								</td>
								<!-- 格式：YYYY-MM-DD -->
								<td>
									<input disabled="disabled" type="text" name="urgeVo.urgeDate"
										style="width: 135px"  value="${urgeDate}" />
								</td>
								<td>
									违约开始日期：
								</td>
								<td>
									<input id="breakbegindate" disabled="disabled"
										name="urgeVo.breakbegindate" value="${breakBeginDate}"  type="text" style="width: 135px" />
										<input id="repaymentCycle"  name="urgeVo.repaymentCycle" type="hidden" value="${repaymentCycle}"/>
								</td> 
								<td>
									催收沟通评价：
								</td>
								<td>
									<input disabled="disabled" id="urgeRemark" name="urgeVo.urgeRemark" type="text"
										value="${urgeVo.urgeRemark}" style="width: 135px"
										  />
								</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;重大风险预警：
								</td>
								<td>
									<input disabled="disabled" id="bigWarning" name="urgeVo.bigWarning" type="text"
										value="${urgeVo.bigWarning}" style="width: 135px" />
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;催收描述：
								</td>
								<td colspan="3">
									<textarea  disabled="disabled" rows="2" cols="60" id="urgeDescribe"
										name="urgeVo.urgeDescribe" >${urgeVo.urgeDescribe}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="6" style="font-size: 14px;">
									&nbsp;承诺情况
								</td>
							</tr>

							<tr>
								<td>
									上次承诺还款金额(元):
								</td>
								<td>
									<input disabled="disabled" type="text"
										id="previousPromiseMoney"  
										class="easyui-numberbox" precision="1" min="0"
										  maxlength="8" value="${urgeVo.previousPromiseMoney}"
										name="urgeVo.previousPromiseMoney" style="width: 135px" />
								</td>
								<td>
									上次承诺还款时间
								</td>
								<td>
									<input disabled="disabled" id="previousPromiseTime"
										name="urgeVo.previousPromiseTime" type="text"
										value="${previousPromiseTime}" style="width: 135px" />
								</td>
								<td>
									是否履诺：
								</td>
								<td>
									<input disabled="disabled" id="ynPromise" name="urgeVo.ynPromise" type="text"
										value="${urgeVo.ynPromise}" style="width: 135px" />
								</td>
							</tr>
							<tr>
								<td>
									本次承诺还款金额(元):
								</td>
								<td>
									<input disabled="disabled" type="text" id="currentPromiseMoney"
										name="urgeVo.currentPromiseMoney"
										value="${urgeVo.currentPromiseMoney}"
										class="easyui-numberbox" precision="1" min="0"
										  maxlength="8" style="width: 135px" />
								</td>
								<td>
									本次承诺还款时间：
								</td>
								<td colspan="3">
									<input disabled="disabled" id="currentPromiseTime" class="easyui-datebox"
										name="urgeVo.curentPromiseTime"   type="text" value="${curentPromiseTime}"
										style="width: 135px" />
								</td>
							</tr>
						</table>
					</form>
					<form id="relationOne">
						<table id="urgeContacts" align="left" cellpadding="5"
							style="width: 100%;" cellspacing="0" border="0" id="table">
							<tr>
								<td colspan="7" align="center"
									style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="4" style="font-size: 14px;">
									&nbsp;催收联系人
								</td>
							</tr>
							<tr>
								<td style="width: 115px">
									催收联系人姓名：
								</td>
								<td>
									<input disabled="disabled" type="text" id="urgeListContact0Name"
										name="urgeLinkmans[0].urgeLinkName" class="easyui-validatebox"
										value="${urgelinkmanlist[0].urgeLinkName}"  
										style="width: 135px" />
								</td>
								<td style="width: 120px">
									与借款人关系：
								</td>
								<td>
									<input disabled="disabled" id="relation0" name="urgeLinkmans[0].borrowerRelation"
										value="${urgelinkmanlist[0].borrowerRelation}" type="text"
										style="width: 135px"  />
								</td>
								<td style="width: 120px;">
									联系电话：
								</td>
								<td>
									<input disabled="disabled" type="text" id="urgeList1telephone"
										name="urgeLinkmans[0].linkTelephone"
										class="easyui-validatebox"
										value="${urgelinkmanlist[0].linkTelephone}"
										validType="phoneNumber" maxlength="11" style="width: 123px" />
								</td>
								<td style="width: 70px">
									&nbsp;
								</td>
							</tr>
							<c:forEach items="${urgelinkmanlist}" var="urgelinkman"
								varStatus="linkmanU" begin="1">
								<tr>
									<td style="width: 115px">
										催收联系人姓名：
									</td>
									<td>
										<input disabled="disabled" type="text" id="urgeListContact${linkmanU.count}Name"
											name="urgeLinkmans[${linkmanU.count}].urgeLinkName"
											class="easyui-validatebox"
											value="${urgelinkman.urgeLinkName}"  
											style="width: 135px" />
									</td>
									<td style="width: 120px">
										与借款人关系：
									</td>
									<td>
										<input disabled="disabled" id="relation0"
											name="urgeLinkmans[${linkmanU.count}].borrowerRelation"
											value="${urgelinkman.borrowerRelation}" type="text"
											style="width: 135px" />
									</td>
									<td style="width: 120px;">
										联系电话：
									</td>
									<td>
										<input disabled="disabled" type="text" id="urgeList1telephone"
											name="urgeLinkmans[${linkmanU.count}].linkTelephone"
											class="easyui-validatebox"
											value="${urgelinkman.linkTelephone}" 
											maxlength="11"  style="width: 123px" />
									</td>
									<td style="width: 70px">
										&nbsp;
									</td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</body>
</html>