<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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

		<title>OverDueMsgRemindJSP</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<script type="text/javascript">
			function sendOverDueRemindMsg(){
				//console.info("---in--------n");
				var remindOverdueCount = $("#remindOverdueCount").val();
				var newDay = $("#newDay").val();
				console.info("---in--------remindOverdueCount-------"+remindOverdueCount);
				console.info("---in--------newDay-------"+newDay);
				var remindStr = "";
				if("T"==newDay){
					remindStr = "确认要发送逾期短信提醒吗?";
				}else{
					remindStr = "你已经发送过短信提醒"+remindOverdueCount+"次，请不要重复发送！";
					if(remindOverdueCount > 1){
						$.messager.alert('Warning',remindStr);
						return;
					}
				}
				$.messager.confirm("消息",remindStr,function(r){
					if(r){
						var url = serverName + '/urgeController/sendOverDueRemindMsgByCreditId.do?';
						console.info("----serizlize-----"+JSON.stringify($("#group").serializeObject()));
						ajaxSubmit(url, $("#group").serialize() , function(r) {
							// 关闭dialog
							// 提示
							if (r.success) {
								parent.messageSuccess("发送短信成功！");
								parent.$("#overDueRemindMegDiv").dialog("close");
							} else {
								$.messager.alert("错误","发送短信失败！系统错误，请与管理员联系！");
							}
						});
					}
				});
			}
		</script>
	</head>

	<body  class="easyui-layout" fit="true" >
	<form id ="group" style="background-color: #ffffff;">
	   <table width="" style="font-size:14px">
		   <tr><td><pr/></td></tr>
		   <tr><td><pr/></td></tr>
		   <tr><td><pr/></td></tr>
		   <tr><td><pr/></td></tr>
		   <tr><td><pr/></td></tr>
		   <tr><td><pr/></td></tr>
		   <tr><td>


			  &nbsp;&nbsp; 【宜信公司】逾期提醒：尊敬的客户您好！受出借人委托提醒，您本
		   </td></tr>
		   <tr><td>
			   期还款已逾期${messageInfoVo.overdueDayCount }天，应还款金额${messageInfoVo.currAccountTotal}元， 应还日期${messageInfoVo.month }月  ${messageInfoVo.day }日，
		   </td></tr>
		   <tr><td>
			请您尽快还款。谢谢您的合作（农商贷400-818-9199）
		   </td></tr>
		   <tr><td>

		   </td></tr>

		   <tr><td>

		   </td></tr>
		   <tr><td>

		   </td></tr>
		   <%--<tr><td>
			   <input type="button" id="sendOverDueRemindMsgBtn" onclick="sendOverDueRemindMsg();" value="发送短信提醒">
		   </td></tr>--%>
	   </table>
		<input type="hidden" value="${messageInfoVo.currAccountTotal }" name="currAccountTotal">
		<input type="hidden" value="${messageInfoVo.month }" name="month">
		<input type="hidden" value="${messageInfoVo.day }" name="day">
		<input type="hidden" value="${messageInfoVo.overdueDayCount }" name="overdueDayCount">
		<input type="hidden" value="${messageInfoVo.creditapplicationId}" name="creditapplicationId">
		<input type="hidden" value="${messageInfoVo.newDay}" name="newDay" id="newDay">
		<input type="hidden" id="remindOverdueCount" value="${messageInfoVo.remindOverdueCount}" name="remindOverdueCount">
	</form>
	</body>
</html>
