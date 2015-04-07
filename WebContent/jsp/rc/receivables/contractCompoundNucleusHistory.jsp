<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
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
		<jsp:include page="../include/easyui.jsp"></jsp:include>

		<!--<link rel="stylesheet" type="text/css" href="styles.css">
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
	</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/receive/contractCompoundNucleusHistory.js"></script>

		<script type="text/javascript">
	function returnPalnView(value, rowData, rowIndex) {
		var accountInfoId=rowData.accountInfoId;
		var creditapplicationId = rowData.creditapplicationId;
		var clientid = rowData.creditapplicationDESId;
		 // 查看申请单的 加密id
	    var creditApplicationEscId = rowData.laonDESId;
		var links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=viewDetail('"+accountInfoId+"','"+clientid+"','"+creditApplicationEscId+"',"+creditapplicationId+")><font color='#9932cc'>查看&nbsp&nbsp</font></a>";
		return links;
	}
</script>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center">
			<div id="loanListTab" class="easyui-tabs" style="padding: 10px;">
				<div title="条件查询" style="padding: 10px;">
					<table border='0'>
						<tr>
							<td nowrap="nowrap" align="right">
								业务单号：
							</td>
							<td>
								<input id="businessNumber" style="width: 140px;" />
							</td>
							<td nowrap="nowrap" align="right">
								借款人姓名：
							</td>
							<td>
								<input id="name" style="width: 140px;" />
							</td>
							<td nowrap="nowrap" align="right">
								复核日期：
							</td>
							<td nowrap="nowrap">
								<input id="loanConfirmDateBegin" type="text" class="easyui-datebox"
									editable="false" style="width: 140px;" />
								-
								<input id="loanConfirmDateEnd" class="easyui-datebox" type="text"
									editable="false" style="width: 140px;" />
							</td>
							<td nowrap="nowrap" align="right">
								复核人：
							</td>
							<td>
								<input id="loanPerson" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">
								产品类型：
							</td>
							<td>
								<input id="productTypeName" name="productTypeName"
									style="width: 150px;" />
							</td>
							<td nowrap="nowrap" align="right">
								复核结果：
							</td>
							<td>
								<input id="compoundNucleusResults" name="compoundNucleusResults"
									style="width: 140px;" />
							</td>
							<td nowrap="nowrap" align="right">
								分公司名称：
							</td>
							<td>
								<input style="width: 180px;" id="companyId" type="text"
									name="companyId" />
							</td>

							<td nowrap="nowrap" align="right">
								放款渠道：
							</td>
							<td nowrap="nowrap">
							<input id="lendingChannel" name="lendingChannel" type="text"
									style="width: 130px;" />
								<%--<input id="lendingChannel" name="lendingChannel" type="text"
									style="width: 130px;" value="债权转让" />
							--%></td>
						</tr>
						<tr>
							<td nowrap="nowrap" colspan="8" align='right'>
								<a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>&nbsp;
								<a class="easyui-linkbutton" href="javascript:searchCancel1();">清空</a>&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="合同复核历史">
					<table id="contractCompoundNucleusHistoryList">
					</table>
				</div>
			</div>
		</div>
		<div id="urgeView" style="width: 800px; height: 400px;">
			<iframe scrolling="no" id="iframeTest" name="myIframe"
				frameborder="0" src="" style="width: 100%; height: 100%;">
			</iframe>
		</div>
		<%--列表查看--%>
        <div id="LoanView">
            <div id="layoutZView" class="easyui-layout" style="width:100%;height:100%;">
               <div region="west" split="true" title="合同基本信息" style="width:710px;height:500px;padding-top:10px;" >
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
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRateForQYResult.borrowUseText"/>
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
                                        <input class="myInputBorder" type="text" readonly="readonly" name="contractRate.iRR"/>
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
                                        <input class="myInputBorder" type="text" readonly="readonly" value="债权转让"/>
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
                                        <input class="myInputBorder" type="text" readonly="readonly" name="groupLoanRegist.loanTime"/>
                                    </td>
                                    <th class="myTH"  align="right">合同签订日期：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" name="groupLoanRegist.contractSignedTime"/>
                                    </td>
                                    <%--<th class="myTH"  align="right">信托计划：</th>
                                    <td class="myTD">
                                        <input class="myInputBorder" type="text" readonly="readonly" value=""/>
                                    </td>
                                --%></tr>
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
                        <table width="100%">
                            <tr>
                                <td class="myTD">
                                    <table id="historyRemarkView">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div region="center" title="上传的图片" style="background:#fafafa;">
                    <div id="editButtonView" class="easyui-tabs">
                        <div title="上传合同附件">
												<iframe height="450px" width="100%" scrolling="auto" id='openCMView2' frameborder="0" src=""></iframe>
								</div>
                    </div>
                </div>
            </div>
	</body>
</html>
