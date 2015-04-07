<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>申请单查看</title>
</head>
<body>
<div>
    <table id="focusTable" fit="true" style="width: 100%;" align="center">
        <c:choose>
        <c:when test="${not empty showAttach}">
        <tr align="center" width="100%">
            <td align="right" width="56%">
                <span style="font-size: 25px; font-weight: 700; color: #4B0082;">申请人信息表</span>
            </td>
            <td align="right" nowrap="nowrap" valign="top">
                <a style="color: #0000ff;cursor: pointer;" onclick="viewApplyBillAttach();">查看申请单附件</a>
            </td>
        </tr>
        <tr align="center" width="100%">
            <td align="center" width="100%" colspan="2">
                </c:when>
                <c:otherwise>
        <tr align="center" width="800">
            <td align="center" width="800">
                <span style="font-size: 25px; font-weight: 700; color: #4B0082;">申请人信息表</span>
            </td>
        </tr>
        <tr align="center" width="100%">
            <td align="center" width="100%">
                </c:otherwise>
                </c:choose>
                <span style="font-size: 15px; font-weight: 700;">申请信息</span>

                <p>&nbsp;</p>
                <span style="font-size: 15px; font-weight: 700;">申请时间：</span>
                <input value="<fmt:formatDate value='${borrowerService.applyDate}' pattern='yyyy-MM-dd HH:mm:ss' />"
                       id="borrowerServicepplyDate" style="width:150px;" readonly="readonly"/>
                &nbsp;
                <span style="font-size: 15px; font-weight: 700;"></span>
            </td>
        </tr>
    </table>
		<table  fit="true" width="100%" align="center" cellpadding="4" cellspacing="4">
		  <tr>
		    <td colspan="5" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
		  </tr>
		  <tr>
		    <td align="right">业务编号：</td>
		    <td>${creditApplication.groupNumber }</td>
		    <td align="right">民族：</td>
		    <td>${ borrowerService.nationalityText}</td>
		    <td width="200px"></td>
		  </tr>
		   <tr>
		    <td align="right">申请金额(元)：</td>
		    <td>${borrowerService.applyLimit }</td>
		    <td align="right">身份证：</td>
		    <td>${borrowerService.credentialsNumber}</td>
		    <td></td>
		  </tr>
		  <tr>
		    <td align="right">姓名：</td>
		    <td>${borrowerService.name }</td>
		    <td align="right">曾用名：</td>
		    <td>${borrowerService.former }</td>
		    <td></td>
		  </tr>
		  <tr>
		    <td align="right">出生日期：</td>
		    <td>${borrowerService.birthday }</td>
		    <td align="right">性别：</td>
		    <td><c:choose>
		    		<c:when test="${borrowerService.gender==0 }">
		    			男
		    		</c:when>
		    		<c:when test="${borrowerService.gender==1 }">
		    			女
		    		</c:when>
		    		<c:otherwise>
		    			
		    		</c:otherwise>
		    	</c:choose>
		    </td>
		    <td></td>
		  </tr>
		   <tr>
		    <td align="right">婚姻状况：</td>
		    <td><c:choose>
		    		<c:when test="${borrowerService.maritalStatus==1 }">
		    			单身
		    		</c:when>
		    		<c:when test="${borrowerService.maritalStatus==2 }">
		    			已婚
		    		</c:when>
		    		<c:when test="${borrowerService.maritalStatus==3 }">
		    			丧偶
		    		</c:when>
		    		<c:when test="${borrowerService.maritalStatus==4 }">
		    			分居
		    		</c:when>
		    	</c:choose>
		    </td>
		    <td align="right">就业状况：</td>
		    <td><c:choose>
		    		<c:when test="${borrowerService.jobStatus==0 }">
		    			工作
		    		</c:when>
		    		<c:otherwise>
		    			失业
		    		</c:otherwise>
		    	</c:choose>
		    </td>
		  </tr>
		  <tr>
		    <td align="right" valign="top">家庭住址分类：</td>
		    <td><p>
		    		<c:if test="${borrowerService.livingCommercial==1}">
		    			<br/><label>自有商品房</label>
		    		</c:if>
		    		<c:if test="${borrowerService.livingSelf==1}">
		    			<br/>
		    			<label>自有宅基地</label>
		    		</c:if>
		    		<c:if test="${borrowerService.livingRent==1}">
		    			<br/><label>租住 <fmt:formatDate value="${borrowerService.livingDate}" type="date"  /></label>
		    		</c:if>
		    		<c:if test="${borrowerService.livingRelative==1}">
		    			<br/><label>亲戚住房</label>
		    		</c:if>
		    		<c:if test="${borrowerService.livingOther==1}">
		    			<br/><label>其他</label>
		    		</c:if>
		      <br />
		    </p></td>
		    <td align="right" valign="top">现经营场所地址分类：</td>
		    <td>
		    		<c:if test="${borrowerService.businessCommercial==1}">
		    			<br/><label>自有商品房</label>
		    		</c:if>
		    		<c:if test="${borrowerService.businessSelf==1}">
		    			<br/><label>自有宅基地</label>
		    		</c:if>
		    		<c:if test="${borrowerService.businessRent==1}">
		    			<br/><label>租住<fmt:formatDate value="${borrowerService.businessDate}" type="date"  /></label>
		    		</c:if>
		    		<c:if test="${borrowerService.businessRelative==1}">
		    			<br/><label>亲戚住房</label>
		    		</c:if>
		    		<c:if test="${borrowerService.businessOther==1}">
		    			<br/><label>其他</label>
		    		</c:if>
		      <br />
		    
		      </td>
		    </tr>
		    <tr>
		    <td align="right">户籍地址：</td>
		    <td>${borrowerService.homeAddress }</td>
		    <td align="right">现经营场所地址：</td>
		    <td>${ borrowerService.residenceAddress}</td>
		    </tr>
		    <tr>
		    <td align="right">家庭住址：</td>
		    <td>${borrowerService.hourseholdAddress }</td>
		    <td align="right"></td>
		    <td></td>
		    </tr>
		    <tr>
		    <td align="right">家庭住址面积(㎡)：</td>
		    <td>${borrowerService.livingArea }</td>
		    <td align="right">经营场所面积(㎡)：</td>
		    <td>${ borrowerService.businessArea}</td>
		    </tr>
		  <tr>
		    <td align="right">家庭住址居住年限(年)：</td>
		    <td><fmt:formatNumber   value="${borrowerService.livingYear }" type="number" pattern="0.00"></fmt:formatNumber></td>
		    <td align="right">经营场所已使用年限(年)：</td>
		    <td><fmt:formatNumber value="${borrowerService.businessYear }" type="number" pattern="0.00"></fmt:formatNumber></td>
		  </tr>
		    <tr>
		    <td align="right">手机号码：</td>
		    <td>${ borrowerService.mobilephone}</td>
		    <td align="right">办公电话：</td>
		    <td>${borrowerService.officePhone }</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>	
  <table width="100%" align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">共借人信息</span></td>
    </tr>
    <tr>
      <td align="center">姓名</td>
      <td align="center">与申请人关系</td>
      <td align="center">身份证号</td>
      <td align="center">年龄</td>
      <td align="center">职业</td>
      <td align="center">职业明细</td>
      <td align="center">联系电话</td>
    </tr>
   	<tr>
      <td align="center">${creditCoborrower.name}</td>
      <td align="center">${creditCoborrower.borrowerreRationshipView}</td>
      <td align="center">${creditCoborrower.idNumber}</td>
      <td align="center">${creditCoborrower.age}</td>
      <td align="center">${creditCoborrower.professionView}</td>
      <td align="center">${creditCoborrower.professionDetail}</td>
      <td align="center">${creditCoborrower.telphone}</td>
    </tr>
  </table>									
  <table width="100%" align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">家庭成员情况</span></td>
    </tr>
    <tr>
      <td align="center">姓名</td>
      <td align="center">与申请人关系</td>
      <td align="center">身份证号</td>
      <td align="center">年龄</td>
      <td align="center">职业</td>
       <td align="center">职业明细</td>
      <td align="center">联系电话</td>
    </tr>
     <c:forEach items="${familymemberList}" var="familymember" begin="0">
    <tr>
      <td align="center">${familymember.name}</td>
      <td align="center">${familymember.borrowerreRationshipView}</td>
      <td align="center">${familymember.idNumber}</td>
      <td align="center">${familymember.age}</td>
      <td align="center">${familymember.professionView}</td>
      <td align="center">${familymember.professionDetail}</td>
      <td align="center">${familymember.telphone}</td>
    </tr>
    </c:forEach>
  </table>
 
  <p>&nbsp;</p>
  <table width="100%" align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="5" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">相关工作情况</span></td>
    </tr>
    <tr>
      <td align="center">就职公司</td>
      <td align="center">地址</td>
      <td align="center">工作年限</td>
      <td align="center">职位</td>
      <td align="center">每月净收入</td>
    </tr>
    <c:forEach items="${jobInfoList}" var="jobInfo">
    	<tr>
	      <td align="center">${jobInfo.company }</td>
	      <td align="center">${jobInfo.workunitAddress }</td>
	      <td align="center">${jobInfo.years }</td>
	      <td align="center">${jobInfo.post }</td>
	      <td align="center">${jobInfo.income }</td>
	    </tr>
    </c:forEach>
  </table>
  <p>&nbsp;</p>
  <table width="100%"  align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">经营情况（包括当前和历史的经营情况）</span></td>
    </tr>
    <tr>
      <td align="center">经营项目</td>
      <td align="center">经营项目详细</td>
      <td align="center">起始年份</td>
      <td align="center">状态</td>
      <td align="center">营业执照</td>
      <td align="center">税务登记证</td>
    </tr>
    <c:forEach items="${surveybusinessinfoList}" var="surveyInfo">
    	<tr>
	      <td align="center">${surveyInfo.operatingItemsView }</td>
	      <td align="center">${surveyInfo.operatingItemsDetail }</td>
	      <td align="center">${surveyInfo.startingDate }</td>
	      <td align="center">
	      	<c:choose>
		    		<c:when test="${surveyInfo.state ==0 }">
		    			营业
		    		</c:when>
		    		<c:when test="${surveyInfo.state ==1 }">
		    			停业
		    		</c:when>
		    	</c:choose>
	      	</td>
	      <td align="center">
	      		<c:choose>
		    		<c:when test="${surveyInfo.businessLicense ==0 }">
		    			有
		    		</c:when>
		    		<c:when test="${surveyInfo.businessLicense ==1 }">
		    			无
		    		</c:when>
		    	</c:choose>
		    </td>
	      <td align="center">
	      		<c:choose>
		    		<c:when test="${surveyInfo.taxRegistrationCertificate ==0 }">
		    			有
		    		</c:when>
		    		<c:when test="${surveyInfo.taxRegistrationCertificate ==1 }">
		    			无
		    		</c:when>
		    	</c:choose>
	      </td>
	    </tr>
    </c:forEach>
  </table>
  <p>&nbsp;</p>
  <table width="100%" align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">存款和应收现金</span></td>
    </tr>
    <tr>
      <td align="center">存款机构或应支付现金方</td>
      <td align="center">存款机构或应支付现金方</td>
      <td align="center">地址</td>
      <td align="center">电话</td>
      <td align="center">余额(元)</td>
      <td align="center">证明文档</td>
    </tr>
    <c:set var="depositTotal" value="${0}"/>
    <c:forEach items="${depositList}" var="deposit">
    	<c:set var="depositTotal" value="${depositTotal + (deposit.balance)}"/>
    	<tr>
	      <td align="center">${deposit.depositOrganizationView }</td>
	      <td align="center">${deposit.depositOrganizationDetail }</td>
	      <td align="center">${deposit.address }</td>
	      <td align="center">${deposit.telephone }</td>
	      <td align="center">${deposit.balance }</td>
	      <td align="center">
	      		<c:choose>
		    		<c:when test="${deposit.proveDocument ==0 }">
		    			有
		    		</c:when>
		    		<c:when test="${deposit.proveDocument ==1 }">
		    			无
		    		</c:when>
		    	</c:choose>
		  </td>
	    </tr>
    </c:forEach>
    <tr>
      <td colspan="4" align="right">汇总(元)：</td>
      <td><c:out value="${depositTotal}"/></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  
  <table width="100%"  align="center" cellpadding="4" cellspacing="4">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      	<span style="font-size: 15px; font-weight: 700;">借款申请</span></td>
    </tr>
    <tr>
      <td align="center">借款使用计划</td>
      <td align="center">借款使用计划详细</td>
      <td align="center">数量</td>
      <td align="center">单价(元)</td>
      <td align="center">总额(元)</td>
      <td align="center">证明文档</td>
    </tr>
    <c:set var="appTotal" value="${0}"/>
    <c:forEach items="${applicationList}" var="application">
    	<c:set var="appTotal" value="${appTotal + (application.totalAmount)}"/>
    	<tr>
	      <td align="center">${application.borrowUseView }</td>
	      <td align="center">${application.borrowUseDetail }</td>
	      <td align="center">${application.quantity }</td>
	      <td align="center">${application.unitPrice }</td>
	      <td align="center">${application.totalAmount }</td>
	      <td align="center">
	      	<c:choose>
		    		<c:when test="${application.proveDocument ==0 }">
		    			有
		    		</c:when>
		    		<c:when test="${application.proveDocument ==1 }">
		    			无
		    		</c:when>
		    	</c:choose>
		   </td>
	    </tr>
    </c:forEach>
     <tr>
      <td colspan="4" align="right">汇总(元)：  <td><c:out value="${appTotal}"/></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</body>