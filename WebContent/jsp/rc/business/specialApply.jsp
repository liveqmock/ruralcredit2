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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<title>特殊情况申请</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
	<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/specialApply.js"></script>
	</head>

	<body class="easyui-layout">
		<div region="center" align="center" style="background:#fafafa;padding:10px;width:auto;height:auto;border-bottom:1px solid red;" >
		<div region="north" style="height:60"><h2>特殊情况申请</h2></div>
		<div  region="center" >
		
			<!-- 特殊情况申请   罗红杰 -->
			<form id="specialApplyForm">
				<table border="0">
					<tr>
						<td style="width: 160px; line-height: 30px;">
							申请类别:
						</td>
						<td style="width: 690px;">
							<c:choose>
								<c:when test="${add==0}">
									<input type="text" id="applyType" name="specialApplyType" style="width: 150px;" />
								</c:when>
								<c:otherwise>
									<input id="applyType" name="specialApplyType" value="${specialApplyVo.specialApplyTypeShow}" disabled="disabled" style="width: 150px;"/>
									<input type="hidden" name="specialApplyType" value="${specialApplyVo.specialApplyType}" />
								</c:otherwise>
							</c:choose>
							<input id="creditapplicationId" name="creditapplicationId" value="${specialApplyVo.creditapplicationId}" type="hidden" />
							<input id="auditSpecialApplyState" name="auditSpecialApplyState" value="0" type="hidden" />
							&nbsp;&nbsp;&nbsp;&nbsp;<input id="specialApplyId" name="specialApplyId" value="${specialApplyVo.specialApplyId}" type="hidden" />
							客户经理电话:
							<c:choose>
								<c:when test="${add==0}">
									<input  name="customerManagerPhone" class="easyui-numberbox" invalidMessage="请输入11位电话号码" required="true" style="width: 150px;" validType="length[11,11]" maxlength="11" />
								</c:when>
								<c:otherwise>
									<input id="customerManagerPhone" name="customerManagerPhone" value="${specialApplyVo.customerManagerPhone}" disabled="disabled" style="width: 150px;"/>
								</c:otherwise>
							</c:choose>
							</td>
					</tr>
					<tr>
						<td style="width: 160px">
							详细内容：
						</td>
						<td align="left">
							<c:choose>
								<c:when test="${add==0}">
									<textarea cols="110" rows="4" class="easyui-validatebox" required="true" validType="length[0,200]" id="specialApplyViewContent" name="specialApplyViewContent" maxlength="200"></textarea>
								</c:when>
								<c:otherwise>
									<textarea readonly="readonly" cols="110" rows="4" id="specialApplyViewContent" name="specialApplyViewContent">${specialApplyVo.specialApplyViewContent}</textarea>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
							&nbsp;
						</td>
					</tr>

					<tr>
						<td height="300px">
							文件上传：
						</td>
						<td height="300px">
							<c:if test="${add==0}">
								<iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/jsp/creditease/operation/operationControl.jsp?clientId=${clientid}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=<%=userId%> 
				&type=3&signTime=${signTime}&signIp=${signIp}" style="width: 100%; height: 100%;" />
							</c:if>
							<iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/operation/transferParameter.action?clientId=${clientid}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529" style="width: 100%; height: 300px;"></iframe>
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;">
							&nbsp;
						</td>
					</tr>
					<c:if test="${add==0}">
					<tr>
					<td colspan="2" align="center"><a id="submitSave" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok"  onclick="save()">提交</a></td>
					</tr>
					</c:if>
							<c:if test="${audit!=1&&add!=0}">
							<tr>
									<td style="width: 160px">
										审批结果：
									</td>
									<td>
										<input readonly type="text" id="auditSpecialApplyState" name="auditSpecialApplyState" value="${specialApplyVo.auditSpecialApplyStateShow}" />
									</td>
								</tr>
							</c:if>
					
					<c:if test="${add!=0}">
						<tr>
							<td>
								备注:
							</td>
							<td>
								<c:choose>
									<c:when test="${audit==1}">
										<textarea cols="110" rows="4" class="easyui-validatebox" id="specialApproveComment" name="specialApproveComment" maxlength="200"></textarea>
									<tr>
										<td colspan="2" align="center"><input id="tongGuo" type="button" class="easyui-linkbutton" iconCls="icon-ok" value="通过" onclick="audit(1)"/>
										&nbsp;&nbsp;&nbsp;&nbsp;<input id="jujue" type="button" class="easyui-linkbutton" iconCls="icon-ok" value="拒绝" onclick="audit(2)"/></td>
									</tr>
									</c:when>
									<c:otherwise>
										<textarea readonly="readonly" cols="110" rows="4" class="easyui-validatebox" id="specialApproveComment" name="specialApproveComment" maxlength="200">${specialApplyVo.specialApproveComment}</textarea>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>

				</table>
			</form>
			</div>
		</div>
	</body>
</html>
