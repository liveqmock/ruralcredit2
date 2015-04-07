<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java" import="java.util.*" import="com.creditease.rc.util.PropertiesUtil" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
String downloadUrl =cmUrl.substring(0,cmUrl.lastIndexOf("/")+1)+"CreditCM";
String cmIp = properties.getProperty("cm.hostip");
DESPlus desPlus = new DESPlus();
String DESNow = desPlus.encrypt(new Date().getTime()+"");
String DESIp = desPlus.encrypt(cmIp);
//获取信用背景调查id liuli 2013-05-03
String creditInvestigationId = "";
if(request.getAttribute("creditInvestigationId") != null){
	if(!request.getAttribute("creditInvestigationId").equals("null")){
		creditInvestigationId = (String)request.getAttribute("creditInvestigationId");
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
		<script type="text/javascript" src="<%=basePath%>scripts/business/copycreditApplicationDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
		<script type="text/javascript">
			var serverName = "<%=path%>";
			var cmUrl = "<%=cmUrl%>";
			var DESIp = "<%=DESIp%>";
			var DESNow = "<%=DESNow%>";
			var downloadUrl="<%=downloadUrl%>";
		</script>
		<style type="text/css">
			input {
			border-left: 0;
   			border-right: 0;
   			border-top: 0;
   			border-bottom: 1px;
   			background:white;
   			text-align: inherit;}
   			.tabTitle {
				background-color: #E9E5E1;
				text-align:center;
				width: 100px;
			}			
			.advisetable {
				border:thin;
				border-color:#558DD5;
				border-collapse:collapse;
				width:95%;
				align:center;
			}
			.advisetable td{
				border: 1px solid #558DD5;
			    padding: 4px;
				text-align:left;
			}
		</style>
  </head>
  <body class="easyui-layout">
  	<div id="cashStream"></div>
  	<div id="guaranorInfo">
  		<iframe scrolling="no" id='openGuaranor' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
  	</div>
  	<div region="north" split="true" border="false" style="overflow: hidden; height: 100px;line-height: 20px;font-family: Verdana, 微软雅黑,黑体">
		<table border="0" align="center" class="advisetable" style="width: 100%;height: 100%;">
			<tr>
				<td class="tabTitle">业务编号:</td><td width="200px"><c:out value="${creditApplication.groupNumber}"></c:out></td>
				<td class="tabTitle">产品类型:</td><td width="200px"><c:out value="${creditApplication.repaymentPlanName}"></c:out></td>
				<td class="tabTitle">审批结果:</td><td width="200px"><c:out value="${applyAuditTable.examResult}"></c:out></td>
				<td class="tabTitle">审批金额:</td><td width="200px"><c:out value="${applyAuditTable.examAmount}"></c:out></td>
			</tr>
			<tr>
				<td class="tabTitle">借款人:</td><td><c:out value="${borrowerServiceApp.name}"></c:out></td>
				<td class="tabTitle">放款金额:</td><td><c:out value="${amountConfirm.amount}"></c:out></td>
				<td class="tabTitle">放款时间:</td><td><fmt:formatDate value="${creditApplication.signagreementDate}" pattern="yyyy-MM-dd"/></td>
				<td class="tabTitle">当前状态:</td><td><input readonly="readonly" id="auditStatus" value="${creditApplication.auditStatusShow}"/></td>
				<%--<td class="tabTitle">审批意见:</td><td width="100px;"><c:out value="${applyAuditTable.borrRemark}"></c:out></td>--%>
				
			</tr>
			<tr>
				<td class="tabTitle">支付方式:</td>
				<td>
					<c:choose>
		    		<c:when test="${creditApplication.businessType==0}">
		    			分公司
		    		</c:when>
		    		<c:when test="${creditApplication.businessType==1}">
		    			个人
		    		</c:when>
		    	</c:choose></td>
				<td class="tabTitle">支付账号:</td><td><c:out value="${accountInfo.account}"></c:out></td>
				<td class="tabTitle">账号名:</td><td><c:out value="${accountInfo.accountName}"></c:out></td>
				<td class="tabTitle">开户行:</td><td><c:out value="${accountInfo.bankName}"></c:out></td>
			</tr>
		</table>
  	</div>
  	<div region="center" style="height: 800px;">
    	<input id="creditapplicationId"  value="${creditApplication.creditapplicationId}" type="hidden"/>
    	<input id="creditapplicationUDESId"  value="${creditapplicationUDESId}" type="hidden"/>
    	<input id="loanRegistDESId"  value="${loanRegistDESId}" type="hidden"/>
    	<input id="loanRegistUDESId"  value="${loanRegistUDESId}" type="hidden"/>
    	<input id="approvalDESId"  value="${approvalDESId}" type="hidden"/>
    	<input id="approvalUDESId"  value="${approvalUDESId}" type="hidden"/>
    	<input id="creditapplicationDESId"  value="${creditApplication.creditapplicationDESId }" type="hidden"/>
		<input id="borrowerServiceAppId" value="${borrowerServiceApp.borrowerServiceAppId}" type="hidden"/>
		<!-- liuli 增加获取信用背景id -->
	  	<input id="copyCreditAd_creditInvestigationId" value="<%= creditInvestigationId %>" type="hidden" />
	  	<div id="allPeopel" plain="true">
	  		<div title="借款人">
			    <table id="borrower"></table>
		    </div>
		   	<div title="担保人">
			    <table id="danbao"></table>
			    <div id="guaranorUploadDig"></div>
		    </div>
		    <div title="还款计划">
	    		<div id="returnPlanList"></div>
	    	</div>
	    	<div title="申请单信息"></div>
	    	<div title="信用及背景调查报告">
	    	</div>
	    	<div title="业务跟踪">
	    		<div id="businesStrack"></div>
	    	</div>
	    	<div title="放款登记信息"></div>
	    	<div title="现金流入流出表"></div>	
	    	<div title="现金流工作表"></div>
	    	
	    	
			<div title="附件查看及下载"  >
	    	</div>
		</div>
	</div>
  </body>
</html>
