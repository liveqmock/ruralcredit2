<%@ page language="java" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loanConfirm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <div id="LoanConfirm" 
		title="放款确认"
		class='easyui-dialog' 
		closed="true"
		draggable="false"
		style="width:900px;height:440px;padding: 10px;" maximizable="true">
			<div id="editButton" >
		  		<br/>
		  			<form id="loanConfirmForm">
						<input name="groupLoanRegistId" id="groupLoanRegistId" type="hidden"/>		<!-- 放款登记编号 -->
						<input name="loanAmount"  type="hidden"/>		 
						<input name="realAmount"  type="hidden"/>	
						<input name="loanPerson"  id="loanPerson" type="hidden"/>			<!-- 放款确认人 -->
						<input name="remarkComment" id="remarkComment" type="hidden"/>		<!-- 放款备注 -->	
						<input name="creditapplicationId" type="hidden"/> 								<!-- 信贷申请id -->
						
			 			<table width="100%">
							<tr height="40">
								<td align="right" width="10%">放款日期：</td>
								<td align="left" width = "150"><input name="loanTime" readonly="readonly"/></td>
							 
								<td align="right" width = "120">协议编号：</td>
								<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td>
								<td align="right" width="120">业务单号：</td>
								<td align="left" width="150"><input  name="groupNumber" readonly="readonly"/></td>
								<td align="right" width="120">客户经理：</td>
								<td align="left" width="150"><input  name="loanOfficerName"  readonly="readonly"/>
							</tr>
						</table>
				</form>
					<table width="100%">	
						<tr>
							<td valign="top" width="10%" align="right">放款明细：</td>
							<td width="90%">
								<table id="detailConfirm">
								</table>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								历史备注：
							</td>
							<td>
									<table id="historyRemark">
									</table>
							</td>
							</tr>
							<tr>
								<td align="right">确认人：
							</td>
							<td align="left">
								<input readonly="readonly" 
								id="loanPerson1"
								value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>"  />
							</td>
							</tr>
							<tr>
								<td align="right">备注和意见：
							</td>
							<td align="left">
								<textarea id="remarkComment1" 
								class="easyui-validatebox"
								required="true"
								rows="2" cols="50"></textarea>
							</td>
							</tr>	
						</table>
						<table style="width: 100%;height: 500px;">
							<tr>
								<td align="right" width="10%" valign="top">协议文件：</td>
								<td width="90%">
									<iframe height="500px" width="100%" scrolling="auto" id='openCMView' frameborder="0" src=""></iframe>
								</td>
							</tr>
						</table>
				</div>
		</div>
		
  </body>
</html>
