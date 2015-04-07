<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
String cmIp = properties.getProperty("cm.hostip");
DESPlus desPlus = new DESPlus();
String DESNow = desPlus.encrypt(new Date().getTime()+"");
String DESIp = desPlus.encrypt(cmIp);
String role=request.getParameter("role");
StringBuffer conditions=new StringBuffer("");
String separator="?";
if(CommonUtil.isNotEmpty(role)){
	conditions.append(separator).append("role=").append(role);
	separator="&";
}
String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'newLoanList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
        <style type="text/css">
            .myTH {
                border-right: 1px solid #ccc;
                font-size: 12px;
                font-weight: normal;
                background: #fafafa url('scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
                border-bottom: 1px dotted #ccc;
                border-top: 1px dotted #ccc;
                overflow: hidden;
                background: #fafafa url('scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
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

            .myInputBorder {
                border: 1px;
            }
        </style>
	<script type="text/javascript">
	var serverName = "<%=path%>";
	var cmUrl = "<%=cmUrl%>";
	var DESIp = "<%=DESIp%>";
	var DESNow = "<%=DESNow%>";
	var role = "<%=role%>";
	</script>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=basePath%>/scripts/payment/newLoanList.js"></script>
		<script type="text/javascript">
	function operationFN(valueTemp, rowData, rowIndex) {
		var value= rowData.status;
		//var wxUpriRecordId = rowData.wxUpriRecordId;
		//var upriRecordCode = rowData.upriRecordCode;
		//<font color='green'>查看</font>
		var accountInfoId=rowData.accountInfoId;
		var creditapplicationId = rowData.creditapplicationId;
		var clientid = rowData.creditapplicationDESId;
		var lendingConfiguration=rowData.lendingConfiguration;
		var lendingConfiguration2;
		var lendingConfiguration3;
		if(rowData.lendingConfigurationShow=="信托"){
			lendingConfiguration3="1";
		}
		if(rowData.lendingConfigurationShow=="债权转让"){
			lendingConfiguration3="0";
		}
		if((lendingConfiguration=="信托放款"&&rowData.lendingConfigurationShow=="信托") || (lendingConfiguration=="信托放款"&&rowData.lendingConfigurationShow==null)){
			lendingConfiguration2="1";
		}
		if(lendingConfiguration=="债权转让"&&rowData.lendingConfigurationShow=="债权转让"){
			lendingConfiguration2="0";
		}
	   // 查看申请单的 加密id
	    var creditApplicationEscId = rowData.laonDESId;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newLoanList_searchLook}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=viewDetail('"+accountInfoId+"','"+clientid+"','"+creditApplicationEscId+"',"+creditapplicationId+")><font color='#9932cc'>查看</font></a>";
		</sec:authorize>   
		//判断当前的业务状态
		if(($.trim(value) == "04"|| $.trim(value) == "34")){
		<sec:authorize ifAnyGranted="${control.newLoanList_printContract}">
		links = links + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=printContract('" + creditapplicationId + "','"+clientid+"','"+creditApplicationEscId+"','0')><font color='#9932cc'>打印合同</font></a>";
		</sec:authorize>
		}
		if(($.trim(value) == "33")){
		<sec:authorize ifAnyGranted="${control.newLoanList_signedContract}">
		links = links + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=printContract('" + creditapplicationId + "','"+clientid+"','"+creditApplicationEscId+"','1')><font color='#9932cc'>合同签订</font></a>";
		</sec:authorize>
		}
		if(($.trim(value) == "35")||($.trim(value) == "37")){
		<sec:authorize ifAnyGranted="${control.newLoanList_loanConfirm}">
		links = links + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=LoanConfirm('"+accountInfoId+"','"+clientid+"','"+creditApplicationEscId+"',"+creditapplicationId+","+lendingConfiguration3+")><font color='#9932cc'>放款确认</font></a>";
		</sec:authorize>
		}
		if((($.trim(value) == "04")||($.trim(value) == "34"))&&lendingConfiguration2=="1"){
		<sec:authorize ifAnyGranted="${control.newLoanList_changeLoanChannel}">
		links = links + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=changeLoanChannelXinTuo("+creditapplicationId+")><font color='#9932cc'>变更放款渠道</font></a>";
		</sec:authorize>
		}
		return links;
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id="hostTab" class="easyui-tabs" style="padding: 10px;">
				<div title="列表页面">
					<div class="easyui-tabs" style="padding: 10px;">
						<div title="模糊查询">
							<table>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模糊查询条件：
									</td>
									<td>
										<input id="fuzzy" name="fuzzy" type="text" style="width: 150px;" />
									</td>
									<td>
										<a class="easyui-linkbutton" onclick="searchFussy()">搜索</a>
									</td>
									<td>
										<a class="easyui-linkbutton" onclick="searchCancel()">清空</a>
									</td>
									<td>
										<font color="red">（可输入业务单号、或借款人姓名、或客户经理、分公司名称 不完整片段进行搜索）</font>
									</td>
								</tr>
							</table>
						</div>
						<div title="高级查询">
							<table border='0'>
								<tr>
									<td nowrap="nowrap" align="right" width="10%">
										业务单号：
									</td>
									<td nowrap="nowrap" align="left" width="10%">
										<input id="businessNumber" style="width: 105px;" type="text" />
									</td>
									<td nowrap="nowrap" align="right" width="10%">
										借款人姓名：
									</td>
									<td nowrap="nowrap" align="left" width="10%">
										<input id="name" style="width: 105px;" type="text" />
									</td>
									<td nowrap="nowrap" align="right" width="10%">
										放款日期：
									</td>
									<td nowrap="nowrap" align="left" width="20%" colspan="2">
										<input id="loanDateS" class="easyui-datebox" style="width: 105px;" type="text" />
										-
										<input id="loanDateE" class="easyui-datebox" style="width: 105px;" type="text" />
									</td>
									<td nowrap="nowrap" align="right" width="10%">
										状态：
									</td>
									<td nowrap="nowrap" align="left" width="10%">
										 <select id="auditStatus" class="easyui-combobox" style="width: 105px;">
                                        <option value="">请选择</option>
                                        <option value="04">审批通过</option>
                                        <option value="33">等待上传合同</option>
                                        <option value="34">等待重新打印合同</option>
                                        <option value="35">等待合同复核</option>
                                        <option value="36">等待信托复核</option>
                                        <option value="37">等待重新合同复核</option>
                                        <option value="38">等待放款</option>
                                    </select>
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" align="right" width="10%">
										分公司名称：
									</td>
									<td nowrap="nowrap" align="left" width="30%" colspan="3">
										<input id="companyId" style="width: 300px;" type="text" />
									</td>
									<td nowrap="nowrap" align="right" width="10%">
										放款确认：
									</td>
									<td nowrap="nowrap" align="left" width="20%" colspan="2">
										<input id="loanConfirmDateS" class="easyui-datebox" style="width: 105px;" type="text" />
										-
										<input id="loanConfirmDateE" class="easyui-datebox" style="width: 105px;" type="text" />
									</td>
									<td nowrap="nowrap" align="right" width="10%">
										放款渠道：
									</td>
									<td nowrap="nowrap" align="left" width="10%">
										<input id="lendingChannel" class="easyui-combobox"  style="width: 105px;" type="text" />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" align="right" width="10%" colspan="7">
										&nbsp;
									</td>
									<td nowrap="nowrap" align="right" width="20%" colspan="2">
										<a id="searchButton" class="easyui-linkbutton" onclick="searchAdvanced();">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="searchButton" class="easyui-linkbutton" onclick="searchCancel1();">清空</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div id="list" class="easyui-tabs" style="padding: 10px;">
						<div title="合同列表">
							<table id="contractDateGrid"></table>
						</div>
					</div>
				</div>
				<%--<div title="合同页面">
					aaaaaaa
				</div>
			--%>
			</div>
		</div>
		<%--放款确认 --%>
		<div id="LoanConfirm" >
		
					<div id="layoutZ" class="easyui-layout" style="width:100%;height:100%;">	
						<div region="west" split="true" title="合同基本信息" style="width:900px;height:500px;padding-top:10px;" >
							<form id="loanConfirmForm">
								<input name="groupLoanRegist.groupLoanRegistId" id="groupLoanRegistId1" type="hidden"/>		<!-- 放款登记编号 -->
								<input name="groupLoanRegistId" id="groupLoanRegistId" type="hidden"/>		<!-- 放款登记编号 -->
								<input name="loanAmount"  type="hidden"/>		 
								<input name="realAmount"  type="hidden"/>	
								<input name="loanPerson"  id="loanPerson" type="hidden"/>			<!-- 放款确认人 -->
								<input name="remarkComment" id="remarkComment" type="hidden"/>		<!-- 放款备注 -->	
								<input name="creditapplicationId" type="hidden"/> 								<!-- 信贷申请id -->
								<div class="easyui-panel" id="showAccountFormqr"  title="基本信息" style="padding-bottom: 10px;">
					 			<table align="center" width="100%" border='0' cellpadding="0" cellspacing="0">
									<tr >
										<th class="myTH" align="right">业务单号：</th>
										
										<td class="myTD" align="left" >
										<input class="myInputBorder"  name="businessNumber" readonly="readonly" style="border: 0" />
										</td>
										<th class="myTH"  align="right">所在营业部：</th>
										<td class="myTD" align="left" >
										<input class="myInputBorder" name="companyName" readonly="readonly" style="border: 0"/></td>
										<th class="myTH" align="right">客户经理：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"   name="loanOfficerName"  readonly="readonly" style="border: 0"/>
										</td>
										</tr>
										<tr >
										<th class="myTH" align="right">借款人：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"    name="borrowName" readonly="readonly" style="border: 0"/></td>
										<th class="myTH" align="right">借款人身份证号：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"    name="credentialsNumber" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right" >产品类型：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"    name="repaymentPlanName" readonly="readonly" style="border: 0"/></td>
										</tr>
										<tr >
										<th  class="myTH" align="right">共借人姓名：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"   name="famName" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right" >共借人身份证号：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"    name="idNumber" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right" >担保人：</th>
										<td class="myTD" align="left" ><input class="myInputBorder"    name="guarantorName" readonly="readonly" style="border: 0"/></td>
										</tr>
										<tr >
										<th  class="myTH" align="right">合同编号：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="contactNumber" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">放款金额：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="amountConfirm.amount" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">实发金额：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="amountConfirm.realAmount" readonly="readonly" style="border: 0"/></td>
										</tr>
										<tr >
										<th  class="myTH" align="right">放款账户名：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="accountInfo.accountName" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">放款卡号：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="accountInfo.account" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">开户行：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="accountInfo.bankName" readonly="readonly" style="border: 0"/></td>
										</tr>
										<tr >
										<th  class="myTH" align="right">所在行别：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="accountInfo.bankRank" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">借款用途：</th>
										<td class="myTD" align="left"><input class="myInputBorder"  style="width:230px"   name="contractRateForQYResult.borrowUseText" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">放款渠道：</th>
										<td class="myTD" align="left"><input class="myInputBorder" id="lendingChannelEnable"  name="" readonly="readonly" style="border: 0"/></td>
										</tr>
										<tr >
										<th  class="myTH" align="right">起息日期：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="amountConfirm.beginInterestTime" readonly="readonly" style="border: 0"/></td>
										<th  class="myTH" align="right">要求放款日期：</th>
										<td class="myTD" align="left"><input class="myInputBorder"    name="groupLoanRegist.loanTime" readonly="readonly" style="border: 0"/></td>
										<%--判断放款渠道是否是信托 start--%>
                                     
                                    <th class="myTH"  align="right" id="xtjh1">信托计划：</th>
                                    <td class="myTD" id="xtjh2">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="planName"/>
                                    </td>
                                    
										
										<!--<th align="right" width = "120">协议编号：</td>
										<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td>
									</tr>
										<tr height="30">
										<td align="right" width = "120">身份证号：</td>
										<td align="left" width = "150"><input size="25" name="credentialsNumber" readonly="readonly"/></td>
										<td align="right" width="120">银行账号:</td>
										<td align="left" width="150"><input name="bankAccountNumber"  readonly="readonly"/></td>
										
									--></tr>
								</table>
								</div>
							</form>
                            <%--客服检查 start--%>
                            <input id="complianceCheckId" type="hidden"/>
                            <div class="easyui-panel" title="合规检查">
                                <table id="check_customer" style="border: solid 1px #9ABBE8;"></table>
                                <table width="99%">
                                    <tr>
                                        <td align="right" colspan="2">
                                            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-addOne"
                                               plain="true"
                                               onclick="addRow();">添加</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <%--客服检查 end--%>

							<%--特殊情况申请 start--%>
							<div class="easyui-panel" title="特殊情况申请" style="margin-bottom: 30px;border: 0px;width: auto;height: auto">
								<table id="special_apply" style="border: solid 1px #9ABBE8;"></table>
								<table id="applyContent_table" style="width: 100%;height: 100%;display: none">
									<tr style="vertical-align: baseline">
										<td width=10%" align="left">
											申请内容：
										</td>
										<td id="applyContent" align="left"></td>
									</tr>
								</table>
							</div>
							<%--特殊情况申请 end--%>

								<div class="easyui-panel"  title="放款确认历史" style="margin-bottom: 30px;border: 0px;width: auto;height: auto">
							<table width="100%">	
								<tr>
									<td>
											<table id="historyRemark">
											</table>
									</td>
									</tr>
									</table>
									</div>
									<div class="easyui-panel" title="确认信息">
									<table  width="100%">
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
								</div>
						</div>
						<div region="center" title="上传的图片" style="background:#fafafa;width:710px;">
							<div id="editButton" class="easyui-tabs" style="height: 800px;width:800px">
								<div title="上传合同附件">
												<iframe height="100%" width="100%" scrolling="auto" id='openCMView' frameborder="0" src=""></iframe>
								</div>
								<div title="申请单附件">
												<iframe height="100%" width="100%" scrolling="auto" id='applicationCMView' frameborder="0" src=""></iframe>
								</div>
							</div>
						</div>
					</div>
			</div>

			
        <%--列表查看--%>
        <div id="LoanView">
            <div id="layoutZView" class="easyui-layout" style="width:100%;height:100%;">
               <div region="west" split="true" title="合同基本信息" style="width:900px;height:500px;padding-top:10px;" >
                    <form id="loanViewForm">
                        <div class="easyui-panel" id="showAccountFormqrView" title="合同信息" style="padding-bottom: 10px;">
                            <table width="100%">
                                <tr>
                                    <th class="myTH" align="right">合同编号：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contactNumber" />
                                    </td>
                                    <th class="myTH"  align="right">客户姓名：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="borrowName"/>
                                    </td>
                                    <th class="myTH"  align="right">借款总额：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="amountConfirm.amount"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">借款用途：</th>
                                    <td class="myTD">
                                        <input  class="myInputBorder" type="text" style="width:230px" readonly="readonly"   name="contractRateForQYResult.borrowUseText"/>
                                    </td>
                                    <th class="myTH"  align="right">产品名称：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="repaymentPlanName"/>
                                    </td>
                                    <th class="myTH"  align="right">分期数：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.periodCount"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">到手金额：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="amountConfirm.realAmount"/>
                                    </td>
                                    <th class="myTH"  align="right">服务费：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.serviceCharge"/>
                                    </td>
                                    <th class="myTH"  align="right">管理费：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.managementFee"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">年利率：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" style="width:50px" readonly="readonly" name="contractRate.iRR"/>%
                                    </td>
                                    <th class="myTH"  align="right">每月还款额：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.monthlyPayments"/>
                                    </td>
                                    <th class="myTH"  align="right">全部提前还款金额：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.prepayments"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">放款账户名：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.accountName"/>
                                    </td>
                                    <th class="myTH"  align="right">身份证号：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="credentialsNumber"/>
                                    </td>
                                    <th class="myTH"  align="right">放款卡号：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.account" />
                                    </td>
                                </tr>
                                  
                                <tr>
                                    <th class="myTH"  align="right">所在行别：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.bankRank"/>
                                    </td>
                                    <th class="myTH"  align="right">所在行地区号：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.bankPrefectureNum"/>
                                    </td>
                                     <th class="myTH"  align="right">所在地区：</th>
                                        <td width="20%" class="myTD" colspan="4">
									<input name="accountInfoId" id="accountInfoId" type="hidden" />
									<input id="provinceIdEnable" name="provinceId"  type="text" style="width: 80px;"  disabled="disabled" />
									<input id="districtIdEnable" name="cityId"  type="text" style="width: 80px;" disabled="disabled" />
									<input id="cityIdEnable" name="districtId"  type="text" style="width: 80px;"  disabled="disabled" />
								</td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">开户行：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.bankName" />
                                    </td>
                                    <th class="myTH"  align="right">支付行号：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="accountInfo.payBranchno"/>
                                    </td>
                                    <th class="myTH"  align="right">卡折类别：</th>
                                    <td class="myTD"><input class="myInputBorder" id="cardFlagEnable1"   name="accountInfo.cardFlag" readonly="readonly" style="border: 0" type="hidden"/>
										<input class="myInputBorder" id="cardFlagEnable"   readonly="readonly" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">放款渠道：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" id="lendingChannelEnableLook" type="text" readonly="readonly"/>
                                    </td>
                                    <th class="myTH"  align="right">起息日期：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="amountConfirm.beginInterestTime"/>
                                    </td>
                                    <th class="myTH"  align="right">最后一次下载合同时间：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="amountConfirm.lastDownloadContractTime"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="myTH"  align="right">要求放款时间：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" id="yLoanTime" type="text" readonly="readonly" name="groupLoanRegist.loanTime"/>
                                    </td>
                                    <th class="myTH"  align="right">合同签订日期：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" id="contractSignedTime" type="text" readonly="readonly" name="groupLoanRegist.contractSignedTime"/>
                                    </td>
                                     <th class="myTH"  align="right" id="xtjh3">信托计划：</th>
                                    <td class="myTD" id="xtjh4">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="planName"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </form>
                   <%--客服检查 start--%>
                   <div class="easyui-panel" title="合规检查" style="padding-bottom: 10px;">
                       <table id="check_customer_view"></table>
                   </div>
                   <%--客服检查 end--%>

				   <%--特殊情况申请 start--%>
				   <div class="easyui-panel" title="特殊情况申请" style="margin-bottom: 30px;border: 0px;width: auto;height: auto">
					   <table id="special_apply_view" style="border: solid 1px #9ABBE8;"></table>
					   <table id="applyContent_table_view" style="width: 100%;height: 100%;display: none">
						   <tr style="vertical-align: baseline">
							   <td width=10%" align="left">
								   申请内容：
							   </td>
							   <td id="applyContent_view" align="left"></td>
						   </tr>
					   </table>
				   </div>
				   <%--特殊情况申请 end--%>
                    <div class="easyui-panel" title="放款确认历史" style="padding-bottom: 50px;">
                        <table id="historyRemarkView"></table>
                    </div>
                </div>
                <div region="center" title="上传的图片" style="background:#fafafa;">
                    <div id="editButtonView" class="easyui-tabs" style="width:800px">
                        <div title="上传合同附件">
												<iframe height="450px" width="800px" scrolling="auto" id='openCMView2' frameborder="0" src=""></iframe>
								</div>
                    </div>
                </div>
            </div>
        </div>
	</body>
</html>
