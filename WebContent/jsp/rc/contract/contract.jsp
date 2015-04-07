<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	java.util.Date currentTime = new java.util.Date();
	String str_dataFormatter = formatter.format(currentTime);
	String str_dataString = currentTime.toString();
	
	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
String cmUrl = properties.getProperty("cm.url");
String isURL = request.getRequestURL().toString();
if(isURL.indexOf(".cn")>0){
	if(cmUrl.indexOf(".corp")>0){
		cmUrl=cmUrl.replaceAll(".corp",".cn");
	}
}else if(isURL.indexOf(".corp")>0){
	if(cmUrl.indexOf(".cn")>0){
		cmUrl=cmUrl.replaceAll(".cn",".corp");
	}
}
String userId = SpringSecurityUtils.getCurrentUser().getUserId();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'contract.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<script type="text/javascript">
	var serverName = "<%=path%>";
	var sysDate = "<%=str_dataFormatter%>";
</script>
	
	
	-->
	<script type="text/javascript">
	
	var serverName = "<%=path%>";
	var sysDate = "<%=str_dataFormatter%>";
	var cmUrl = "<%=cmUrl%>";
	var userId = "<%=userId%>";
	<%
		String flag = request.getParameter("flag");
	%>
	var flag ="<%=flag%>";
	//客户放弃
	function giveUp(){
	
		document.getElementById("customerQiutDiv").style.display='block';
		$('#signedFailReason').dialog('close');
		var dataCreditapplicationId = $("#creditapplicationId").val();
		$("#customerQiutCreditapplicationId").val(dataCreditapplicationId);
		$("#customerQiut").combobox({
			width:130,
			onSelect:function(data){
				$("#customerQiutSection").val(data.section);
			}
		});
		$("#customerQiutDiv").dialog({
			closed:false,
			width:400,
			closable:false,
			modal:true,
			height:150,
			title:"客户放弃原因",
			buttons:[
	    		        {
	    		        	id:"customerQiutButton",
	    		        	text:"确定",
	    		        	handler:function(){
	    		        		var  customerQiutReason= $("#customerQiut").combobox("getValue");
	    		        		if(customerQiutReason == "" || customerQiutReason == null){
	    		        			$.messager.alert("消息", "请选择客户放弃原因");
	    		        		}else{
	        		        		$("#customerQiutButton").linkbutton("disable");
	        		        		$("#customerQiutCancelButton").linkbutton("disable");
	    		    				var creditapplicationId =$("#customerQiutCreditapplicationId").val();
	    		    				var section = $("#customerQiutSection").val();
	    		    				//调用撤销接口请求接口
	    		    				//获取放款渠道的值判断是否是信托
									var lendingChannel= $("#lendingChannel").val();
									//判断是否是信托合同
									if(lendingChannel==1){
									//调用撤销撮合的接口
							$.ajax({
	    		        			url:"<%=basePath%>contractAndLoanController/revokeMatchmakingRefuseReason.do",
	    		        			datatype:"json",
									data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:refuseReasons,auditStatus:"27"},
									success:function(message){
										if(message.success){
											$.messager.alert("消息", "操作成功");
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$("#refuseReasonDialog").dialog("close");
											//重新加载放款列表页面
											parent.flushContractDateGrid();
			                				// 关闭 合同页面
			                				parent.closeTab();
										}else{
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$.messager.alert("消息", "操作失败");
										}
									}
	    		        		});
					}else{
						$.ajax({
	        		        url:"<%=basePath%>RefuseReasonController/refuse.do",
	        		        datatype:"json",
							data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:customerQiutReason,auditStatus:"28"},
							success:function(message){
							if(message.success){
								$.messager.alert("消息", "操作成功");
								$("#customerQiutButton").linkbutton("enable");
			            		$("#customerQiutCancelButton").linkbutton("enable");
								$("#customerQiutDiv").dialog("close");
								//重新加载放款列表页面
								parent.flushContractDateGrid();
				                // 关闭 合同页面
				                parent.closeTab();
								}else{
								$("#customerQiutButton").linkbutton("enable");
			            		$("#customerQiutCancelButton").linkbutton("enable");
								$.messager.alert("消息", "操作失败");
								}
							}
	        		        });
	        		       }
	        		    }
	        		      }
	    		        },
	    		        {
	    		        	id:"customerQiutCancelButton",
	    		        	text:"取消",
	    		        	handler:function(){
	    		        		$("#customerQiutDiv").dialog("close");
	    		        		$('#signedFailReason').dialog('open');
	    		        	}
	    		        }
			       
			        ]
		});
		var dic1 = new DataDictionary();
		dic1.addDic("customerQiut", "customerQiutReason");
		dic1.dicAjax();
		
	}
	
	//拒贷
	function refuse(){
		$('#signedFailReason').dialog('close');
		//清空数据
		$("#RefuseReason").val("");
		$("#showRefuseReason").val("");
		var dataCreditapplicationId = $("#creditapplicationId").val();
		$("#RefuseReasonCreditapplicationId").val(dataCreditapplicationId);
		$("#refuseReasonDialog").dialog({
			closed:false,
			width:500,
			closable:false,
			modal:true,
			height:200,
			title:"拒贷原因",
			buttons:[
			        {
			        	id:"okButton",
			        	text:"确定",
			        	handler:function(){
			        		var refuseReasons = $("#RefuseReason").val();
			        		if(refuseReasons =="" || refuseReasons ==null){
			        			$.messager.alert("消息", "请输入拒贷原因");
			        		}else{
	    		        		$("#okButton").linkbutton("disable");
	    		        		$("#noButton").linkbutton("disable");
			    				var creditapplicationId =$("#RefuseReasonCreditapplicationId").val();
			    				var section = $("#RefuseReasonSection").val();
			    				//调用撤销接口请求接口
	    		    			//获取放款渠道的值判断是否是信托
								var lendingChannel= $("#lendingChannel").val();
								//判断是否是信托合同
								if(lendingChannel==1){
								//调用撤销撮合的接口
	    		        		$.ajax({
	    		        			url:"<%=basePath%>contractAndLoanController/revokeMatchmakingRefuseReason.do",
	    		        			datatype:"json",
									data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:refuseReasons,auditStatus:"27"},
									success:function(message){
										if(message.success){
											$.messager.alert("消息", "操作成功");
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$("#refuseReasonDialog").dialog("close");
											//重新加载放款列表页面
											parent.flushContractDateGrid();
			                				// 关闭 合同页面
			                				parent.closeTab();
										}else{
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$.messager.alert("消息", "操作失败");
										}
									}
	    		        		});	
			        		}else{
			        		$.ajax({
	    		        			url:"<%=basePath%>RefuseReasonController/refuse.do",
	    		        			datatype:"json",
									data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:refuseReasons,auditStatus:"27"},
									success:function(message){
										if(message.success){
											$.messager.alert("消息", "操作成功");
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$("#refuseReasonDialog").dialog("close");
											//重新加载放款列表页面
											parent.flushContractDateGrid();
			                				// 关闭 合同页面
			                				parent.closeTab();
										}else{
											$("#okButton").linkbutton("enable");
		            		        		$("#noButton").linkbutton("enable");
											$.messager.alert("消息", "操作失败");
										}
									}
	    		        		});
	    		        		}
	    		        	}
			        	}
			        },{
			        	id:"noButton",
			        	text:"取消",
			        	handler:function(){
			        		$("#refuseReasonDialog").dialog("close");
			        		$('#signedFailReason').dialog('open');
			        	}
			        }
			     ]
		});
	}
</script>
<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/contract/contract.js"></script>
		<style type="text/css">
.myTH {
	border-right: 1px solid #ccc;
	font-size: 12px;
	font-weight: normal;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	border-top: 1px dotted #ccc;
	overflow: hidden;
	background: #fafafa
		url('scripts/uilib/themes/default/images/datagrid_header_bg.gif')
		repeat-x left bottom;
	border-bottom: 1px dotted #ccc;
	cursor: default;
	border-top: 1px dotted #ccc;
}

.myTD {
	font-size: 12px;
	border-right: 1px dotted #ccc;
	border-bottom: 1px dotted #ccc;
	overflow: hidden;
	padding: 0;
	margin: 0;
}

.myInputBoder {
	border: 1px;
}
</style>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="合同信息">
				<form id="form1">
					<table width="100%">
						<tr>
							<th align="right" width="12%" class="myTH">
								合同编号：
								<input style="width: 165px;" name="creditapplicationId" id="creditapplicationId" value="${creditapplicationId}" type="hidden" />
								<input style="width: 165px;" name="todayStamp" id="todayStamp" value="${todayStamp}" type="hidden" />
								<input style="width: 165px;" name="tomorrowStamp" id="tomorrowStamp" value="${tomorrowStamp}" type="hidden" />
								<input style="width: 165px;" name="tomorrowStamp" id="after30MStamp" value="${after30MStamp}" type="hidden" />
								<input style="width: 165px;" name="clientid" id="clientid" value="${clientid}" type="hidden" />
								<input style="width: 165px;" name="creditApplicationEscId" id="creditApplicationEscId" value="${creditApplicationEscId}" type="hidden" />
								
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" name="protocolNumber" id="protocolNumber" value="${protocolMapping.protocolNumber}" type="text"  readonly="readonly" />
							</td>
							<th align="right" width="12%" class="myTH">
								客户姓名：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" value="${contractRate.clientName}"/>
							</td>
							<th align="right" width="12%" class="myTH">
								担保人姓名：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" value="${loanConfirmMessageVo.guarantorName}"/>
							</td>
							</tr>
							<tr>
							<th align="right" width="12%" class="myTH">
								借款总额：
							</th>
							<td width="15%" class="myTD">
								￥
								<c:choose>
						<c:when test="${type==1||type==2}">
								<input style="border:0px" id="amount" name="amount"  class="easyui-numberbox"  precision="2"  type="text" value="${contractRate.contractMoney}" readonly="readonly"/>
								</c:when>
								</c:choose>
								<c:choose>
								<c:when test="${type==0}">
								<input class="myInputBoder" id="loanAmount" name="loanAmount" type="hidden" value="${contractRate.contractMoney}"/>
								<input style="border:0px"   id="amount" name="amount" type="text" value="${contractRate.contractMoney}" class="easyui-numberbox"  precision="2"  onblur="confirmAmount(this)" />
								<input id="editAmountButton" type="button" value="修改" onclick="editAmountAllMoney()" />
								</c:when>
								</c:choose>
							</td>
							<th align="right" width="12%" class="myTH">
								借款用途：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" style="width:230px;" type="text" readonly="readonly" value="${contractRate.borrowUse}"/>
							</td>
							<th align="right" width="12%" class="myTH">
								产品名称：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" value="${contractRate.productName}"/>
							</td>
							</tr>
							<tr>
							<th align="right" width="12%" class="myTH">
								分期数：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" value="${contractRate.periodCount}期"/>
							</td>
							<th align="right" width="12%" class="myTH">
								到手金额：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" id="toAmount" readonly="readonly" value="${contractRate.toAmount}"/>
							</td>
							<th align="right" width="12%" class="myTH">
								服务费：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" id="serviceCharge" readonly="readonly" value="${contractRate.serviceCharge}"/>
							</td>
							</tr>
						<tr>
							<th align="right" width="12%" class="myTH">
								管理费：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" id="managementFee" readonly="readonly" value="${contractRate.managementFee}"/>
							</td>
							<th align="right" width="12%" class="myTH">
								年利率：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" value="${contractRate.iRR}%"/>
							</td>
							<th align="right" width="12%" class="myTH">
								每月还款额：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" id="monthlyPayments" readonly="readonly" value="${contractRate.monthlyPayments}"/>
							</td>
							</tr>
						<tr>
							<th align="right" width="12%" class="myTH">
								提前一次性还款金额：
							</th>
							<td width="15%" class="myTD">
								<input class="myInputBoder" type="text" readonly="readonly" id="prepayments" value="${contractRate.prepayments}"/>
							</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
			<!-- 重新打印合同  begin-->
			<c:choose>
			<c:when test="${type==2}">
				<div class="easyui-tabs" style="padding: 10px;">
				<div title="放款账户信息">
					<form id="form2">
						<table width="100%">
							<tr>
								<th align="right" width="12%" class="myTH">
									放款帐户名：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="accountName" id="accountName" class="easyui-combobox" required="true" maxlength="10" value="${accountInfo.accountName}"  />
								</td>
								<th align="right" width="7%" class="myTH">
									身份证号：
								</th>
								<td width="20%" class="myTD">
									<input name="credentialsNumber" style="width: 99%;" readonly="readonly" class="easyui-validatebox myInputBoder" value="${accountInfo.credentialsNumber}" validType="idnumberAll"
										id="credentialsNumber" />
								</td>
								<th align="right" width="12%" class="myTH">
									放款卡号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 60%" name="account" class="easyui-validatebox" value="${accountInfo.account}" validType="numberOnlyLength[16,19]" required="true"  
										maxlength="19" />
								</td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									所在行别：
								</th>
								<c:choose>
								  <c:when test="${(type==0||type==2)&&(flag==1||flag==3)}">
								    <td width="15%" class="myTD">
									<input style="width: 165px;"  name="bankNum" id="bankNum" value="${accountInfo.bankNum}" />
									 <input style="width: 165px;" name="bankRank" id="bankRank" type="hidden" width="130px" /> 
								</td>
								  </c:when>
								  <c:otherwise>
								  <c:if  test="${type!=1}">
								<td width="15%" class="myTD">
									<input style="width: 165px;"  required="true" name="bankNum" id="bankNum" />
									 <input style="width: 165px;" name="bankRank" id="bankRank" type="hidden" width="130px" /> 
								</td>
								</c:if>
								  </c:otherwise>
								</c:choose>
								<th align="right" width="7%" class="myTH">
									所在地区：
								</th>
								<td width="20%" class="myTD">
									<input name="accountInfoId" id="accountInfoId" type="hidden" />
									<input id="provinceId1" name="provinceId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false" value="${accountInfo.provinceId}" />
									<input id="cityId1" name="cityId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false" value="${accountInfo.cityId}" />
									<input id="districtId1" name="districtId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false"  value="${accountInfo.districtId}"/>
								</td>
								<th align="right" width="12%" class="myTH">
									所在行地区号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 60%;" name="bankPrefectureNum" class="easyui-validatebox" value="${accountInfo.bankPrefectureNum}" validType="numberOnly" required="true"  
										maxlength="20" />
								</td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									开户行：
								</th>
								<td width="15%" class="myTD">
									<input type="text" style="width: 99%" name="bankName" value="${accountInfo.bankName}" class="easyui-validatebox" required="true"  
										maxlength="30" />
								</td>
								<th align="right" width="7%" class="myTH">
									支付行号：
								</th>
								<td width="20%" class="myTD">
									<input type="text" style="width: 99%" name="payBranchno" value="${accountInfo.payBranchno}" class="easyui-validatebox"
										validType="numberOnly" required="true" maxlength="20"  />
								</td>
								<th align="right" width="12%" class="myTH">
									卡折类别：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="cardFlag" id="cardFlag" value="${accountInfo.cardFlag}"  />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			</c:when>
			</c:choose>
		<!-- 重新打印合同  end -->	
			
<c:choose>
			<c:when test="${type==1}">
			<!-- 合同签订 -->
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="放款账户信息">
					<form id="form2">
						<table width="100%">
							<tr>
								<th align="right" width="12%" class="myTH">
									放款帐户名：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="accountName" id="accountName" class="easyui-combobox" required="true" maxlength="10" value="${accountInfo.accountName}" disabled="disabled"/>
								</td>
								<th align="right" width="7%" class="myTH">
									身份证号：
								</th>
								<td width="20%" class="myTD">
									<input name="credentialsNumber" style="width: 99%;" readonly="readonly" class="easyui-validatebox myInputBoder" value="${accountInfo.credentialsNumber}" validType="idnumberAll"
										id="credentialsNumber" />
								</td>
								<th align="right" width="12%" class="myTH">
									放款卡号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 99%" name="account" class="easyui-validatebox" value="${accountInfo.account}" validType="numberOnlyLength[16,19]" required="true" readonly="readonly"
										maxlength="19" />
								</td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									所在行别：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" required="true" name="bankNum" id="bankNum" value="${accountInfo.bankNum}" disabled="disabled"/>
									 <input style="width: 165px;" name="bankRank" id="bankRank" type="hidden" width="130px" /> 
								</td>
								<th align="right" width="7%" class="myTH">
									所在地区：
								</th>
								<td width="20%" class="myTD">
									<input name="accountInfoId" id="accountInfoId" type="hidden" />
									<input id="provinceId1" name="provinceId" class="easyui-combobox" type="text" style="width: 80px;"  editable="false" disabled="disabled" value="${accountInfo.provinceId}"/>
									<input id="cityId1" name="cityId" class="easyui-combobox" type="text" style="width: 80px;" editable="false" disabled="disabled" value="${accountInfo.cityId}"/>
									<input id="districtId1" name="districtId" class="easyui-combobox" type="text" style="width: 80px;"  editable="false" disabled="disabled" value="${accountInfo.districtId}"/>
								</td>
								<th align="right" width="12%" class="myTH">
									所在行地区号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 99%;" name="bankPrefectureNum" class="easyui-validatebox" value="${accountInfo.bankPrefectureNum}" validType="numberOnly" required="true" readonly="readonly"
										maxlength="20" />
								</td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									开户行：
								</th>
								<td width="15%" class="myTD">
									<input type="text" style="width: 99%" name="bankName" value="${accountInfo.bankName}" class="easyui-validatebox" required="true" readonly="readonly"
										maxlength="30" />
								</td>
								<th align="right" width="7%" class="myTH">
									支付行号：
								</th>
								<td width="20%" class="myTD">
									<input type="text" style="width: 99%" name="payBranchno" value="${accountInfo.payBranchno}" class="easyui-validatebox"
										validType="numberOnly" required="true" maxlength="20" readonly="readonly"/>
								</td>
								<th align="right" width="12%" class="myTH">
									卡折类别：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="cardFlag" id="cardFlag" value="${accountInfo.cardFlag}" disabled="disabled"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
    </c:when>			
</c:choose>
<!-- 打印合同 begin -->
<c:choose>
<c:when test="${type==0}">
<div class="easyui-tabs" style="padding: 10px;">
				<div title="放款账户信息">
					<form id="form2" novalidate>
						<table width="100%">
							<tr>
								<th align="right" width="12%" class="myTH">
									放款帐户名：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="accountName" id="accountName" class="easyui-combobox" required="true" maxlength="10" />
									<input   name="accountType" type="hidden" value="1"/><!-- 账户类型 -->	
									<input   name="onUsed" type="hidden" value="1"/><!-- 账户类型 -->	
									<input name="useType" id="useType" type="hidden"  value="2"/>		<!-- 付款账户 --></td>
									<input  id="branchName" name="branchName" type="hidden" /><!-- 营业部名称-->
									<input  id="companyId" name="departmentId" type="hidden" /><!-- 营业部ID-->
									<input  id="mobilephone" name="mobilephone" type="hidden" /><!--电话号码 --->
								</td>
								<th align="right" width="7%" class="myTH">
									身份证号：
								</th>
								<td width="20%" class="myTD">
									<input name="credentialsNumber" style="width: 99%;" readonly="readonly" class="easyui-validatebox myInputBoder"  validType="idnumberAll"
										id="credentialsNumber" />
								</td>
								<th align="right" width="12%" class="myTH">
									放款卡号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 60%" name="account" class="easyui-validatebox" validType="numberOnlyLength[16,19]" required="true" 
										maxlength="19" />
								
								<%--<input required="true" class="easyui-validatebox"/>
								--%></td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									所在行别：
								</th>
								<c:choose>
								  <c:when test="${(type==0||type==2)&&(flag==1||flag==2)}">
								    <td width="15%" class="myTD">
									<input style="width: 165px;"  name="bankNum" id="bankNum" />
									 <input style="width: 165px;" name="bankRank" id="bankRank" type="hidden" width="130px" /> 
								</td>
								  </c:when>
								  <c:otherwise>
								  <c:if  test="${type!=1}">
								<td width="15%" class="myTD">
									<input style="width: 165px;"  required="true" name="bankNum" id="bankNum" />
									 <input style="width: 165px;" name="bankRank" id="bankRank" type="hidden" width="130px" /> 
								</td>
								</c:if>
								  </c:otherwise>
								</c:choose>
								<th align="right" width="7%" class="myTH">
									所在地区：
								</th>
								<td width="20%" class="myTD">
									<input name="accountInfoId" id="accountInfoId" type="hidden" />
									<input id="provinceId1" name="provinceId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false" />
									<input id="cityId1" name="cityId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false" />
									<input id="districtId1" name="districtId" class="easyui-combobox" type="text" style="width: 80px;" required="true" editable="false" />
								</td>
								<th align="right" width="12%" class="myTH">
									所在行地区号：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 60%;" name="bankPrefectureNum"  class="easyui-validatebox" validType="numberOnly" required="true" 
										maxlength="20" />
								</td>
							</tr>
							<tr>
								<th align="right" width="12%" class="myTH">
									开户行：
								</th>
								<td width="15%" class="myTD">
									<input type="text" style="width: 99%" name="bankName" class="easyui-validatebox" required="true" 
										maxlength="30" />
								</td>
								<th align="right" width="7%" class="myTH">
									支付行号：
								</th>
								<td width="20%" class="myTD">
									<input type="text" style="width: 99%" name="payBranchno"  class="easyui-validatebox" 
										validType="numberOnly" required="true" maxlength="20" />
								</td>
								<th align="right" width="12%" class="myTH">
									卡折类别：
								</th>
								<td width="15%" class="myTD">
									<input style="width: 165px;" name="cardFlag" id="cardFlag"  />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
</c:when>
</c:choose>
<!-- 打印合同end -->
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="放款信息确认">
				<form id="form3">
					<table width="100%">
						<tr>
							<th align="right" width="12%" class="myTH">
								放款渠道：
							</th>
							<td width="15%" class="myTD">
							<input id="lendingChannel" name="lendingChannel" value="${lendingConfiguration}" class="easyui-combobox"  />
								<input id="realAmount" name="realAmount" type="hidden"/>
								<input name="loanAmount" id="loanAmount" type="hidden"/>
								<input id="amount" name="amount" type="hidden"/>
							</td>
							<th align="right" width="12%" class="myTH">
								起息日期：
							</th>
							<c:choose>
							<c:when test="${type!=1}">
							<td width="15%" class="myTD" id="qixiDate2">
							<input style="width: 165px;" name="creditapplicationId" id="creditapplicationId" value="${creditapplicationId}" type="hidden" />
								<input name="beginInterestTime" class="easyui-combobox" value="${dateString}" style="width: 165px;" id="beginInterestTime" editable="false" required="true" />
							</td>
							</c:when>
							</c:choose>
							<c:choose>
							<c:when test="${type!=1}">
							    <td width="15%" class="myTD" id="qixiDate1">
							<input style="width: 165px;" name="creditapplicationId" id="creditapplicationId" value="${creditapplicationId}" type="hidden" />
								<input name="beginInterestTime" class="easyui-combobox" value="${dateString}" style="width: 165px;" id="beginInterestTimeXinTuo" editable="false" required="true" />
							</td>
							</c:when>
							</c:choose>
							<c:choose>
							<c:when test="${type==1}">
							<td width="15%" class="myTD">
							<input style="width: 165px;" name="creditapplicationId" id="creditapplicationId" value="${creditapplicationId}" type="hidden" />
								<input name="beginInterestTime" value="${beginInterestTime}" style="width: 165px;" readonly="readonly" />
							</td>
							</c:when>
							</c:choose>
							<th align="right" width="12%" class="myTH">
								最后一次下载合同时间：
							</th>
							<td width="15%" class="myTD">
								 <input   style="width: 165px;" name="lastDownloadContractTime" id="lastDownloadContractTime"  readonly="readonly" value="${lastDownloadContractTime}"
									/> 
								<!--<fmt:formatNumber type="number" value="100000" pattern="#,##0.00#" />
							--></td>
						</tr>
						<tr>
							<th align="right" width="12%" class="myTH">
								要求放款时间：
							</th>
							<td width="15%" class="myTD">
								<!--<fmt:formatNumber type="number" value="100000" pattern="#,##0.00#" />-->
								<c:choose>
				<c:when test="${type==1}">
								<input name="loanTime" class="easyui-combobox" value="${dateString}"    style="width: 165px;" id="loanTime" required="true"  editable="false" />
						   </c:when>
						   </c:choose>
						    </td>
							<th align="right" width="12%" class="myTH">
								合同签订时间：
							</th>
							<td width="15%" class="myTD">
							<c:choose>
				<c:when test="${type==1}">
								<input name="contractSignedTime" class="easyui-datebox" style="width: 165px;" id="contractSignedTime" editable="false" required="true" />
							</c:when>
							</c:choose>
							</td>
					<th class="myTH"  align="right" id="xtjh1" width="12%">信托计划：</th>
							<td class="myTD" id="xtjh2" width="15%" >
                                  <input class="myInputBorder" type="text" readonly="readonly" name="planName" value="${planName}"/>
                              </td>
							<!--<c:choose>
				<c:when test="${flag==1||flag==2}">
							<th align="right" width="12%" class="myTH">
								信托计划：
							</th>
							<td width="15%" class="myTD">
								<input name="trustPlan"  style="width: 165px;" id="trustPlan" readonly="readonly" value="${planName}"/>
							</td>
							</c:when>
							<c:otherwise>
							<th align="right" width="12%" class="myTH">
								&nbsp;
							</th>
							<td width="15%" class="myTD">
								&nbsp;
							</td>
							</c:otherwise>
							</c:choose>
						--></tr>
					</table>
					</form>
				</div>
				
			</div>
			<div id="cm1"  class="easyui-dialog"  closed="true" style="width:1000px;height:400px;z-index:1;">
   	</div>
   	<!-- 合同作废原因 -->
   	<div id="cancelContractReasonContent"  class="easyui-dialog"  closed="true" style="width:340px;height:250px;z-index:1;">
   		<div style="background:#fafafa;padding:10px;width:auto;height:auto;">
	    <form novalidate=""  id="cancelContractReasonForm">
            <fieldset>
				<legend>请填写作废合同原因</legend>
				<textarea class="easyui-validatebox" id="cancelContractReasonContent" maxlength="200"  required="true"    name="cancelContractReasonContent"   style="height:100px;width:250px;"></textarea> 
			</fieldset>
			<tr><td align="right">
							&nbsp;
						</td>
						<td align="left" colspan="3">&nbsp;<span id="areaFiveS"></span></td>
						</tr>
			<input type="hidden" id="creditapplicationId" name="creditapplicationId" value="${creditapplicationId}"/>
    	</form>
	</div>
   	</div>
   	<!-- 签约失败原因 -->
   	<div id="signedFailReason"  class="easyui-dialog"  closed="true" style="width:310px;height:170px;z-index:1;">
   		<div style="background:#fafafa;padding:10px;width:auto;height:auto;">
            <fieldset>
				<legend>请选择签约失败原因</legend>
				<div align="center">
				
				<input type="radio" id="giveUp" name="signedFail" value="客户放弃"  onclick="giveUp()" />客户放弃<br/>
				<br/>
           		<input type="radio" id="refuse" name="signedFail" value="拒贷" onclick="refuse()"/>拒贷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		</div>
			</fieldset>
			<tr><td align="right">
							&nbsp;
						</td>
						<td align="left" colspan="3">&nbsp;<span id="areaFiveS"></span></td>
						</tr>
			<input type="hidden" id="creditapplicationId" name="creditapplicationId" value="${creditapplicationId}">
	</div>
   	</div>
   	
   	<!-- 撮合情况 -->
   	<div id="contractMatchmaking"  class="easyui-dialog"  closed="true" style="width:340px;height:250px;z-index:1;">
   		<div style="background:#fafafa;padding:10px;width:auto;height:auto;">
	    <form novalidate=""  id="contractMatchmakingForm">
            <fieldset>
				<legend>撮合情况</legend>
				<textarea class="easyui-validatebox" id="contractMatchmakingMsg" maxlength="200"  required="true"    name="contractMatchmaking"   style="height:100px;width:250px;"></textarea> 
			</fieldset>
			<tr><td align="right">
							&nbsp;
						</td>
						<td align="left" colspan="3">&nbsp;<span id="areaFiveS"></span></td>
						</tr>
			<input type="hidden" id="creditapplicationId" name="creditapplicationId" value="${creditapplicationId}"/>
    	</form>
	</div>
   	</div>
   	
   	<div id="refuseReasonDialog" class="easyui-dialog"  style="width:500;height:150;" closed="true">
			<fieldset>
				<legend>拒贷原因</legend>
				<input id="RefuseReason" type="hidden"/>
				<input id="RefuseReasonSection" type="hidden"/>
				<input id="RefuseReasonCreditapplicationId" type="hidden"/>
				请输入拒贷原因：<input name="" id="showRefuseReason" size="60" readonly="readonly"
				onfocus="showRefuseReasonDiv('RefuseReason','showRefuseReason');"/>
			</fieldset>
		</div>
		<input id="section" type="hidden"/>
		 <div id="refuseReasonDiv">
    		<table id="refuseReasonTable" style="width: 870px;border:1px;  border-color: black;" align="center" fit="true" >
    		</table>
   		 </div>
   		 <div id="customerQiutDiv" style="display:none">
	   		 <fieldset>
					<legend>客户放弃原因</legend>
					<input id="customerQiutSection" type="hidden"/>
					<input id="customerQiutCreditapplicationId" type="hidden"/>
	   		 		请输入客户放弃原因:
	   		 		<input id="customerQiut" editable="false"/>
	   		 </fieldset>
   		 </div>
			<br />
			<br />
			<div style="float: right; padding-right: 20px;">
			<c:choose>
			<c:when test="${type==1}">
				<a class="easyui-linkbutton" onclick="cancelContract()"> 作废合同</a>
				<a class="easyui-linkbutton abut" onclick="showUploadDigContract()"> 上传合同资料</a>
				<a class="easyui-linkbutton" onclick="signedFail()"> 签约失败</a>
				</c:when>
				<c:otherwise>
				<input id="printContractBut" type="button"  class="easyui-linkbutton" onclick="printContract()" value="打印合同"/>
				<input id="printContractButXintuo" type="button" class="easyui-linkbutton" onclick="addTrustprintContract()" value="打印合同"/>
				</c:otherwise>
				</c:choose>
			</div>
			<br />
		</div>
	</body>
</html>
