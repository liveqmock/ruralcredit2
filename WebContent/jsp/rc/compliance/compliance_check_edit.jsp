<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-7
  Time: 15:10
  合规检查-业务资料页面
--%>
<%@ page import="com.creditease.rc.framework.util.PropertiesUtil" %>
<%@ page import="java.util.Properties" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@ page import="com.creditease.rc.common.Constants" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    /*业务资料*/
    String creditApplicationId = request.getParameter("creditApplicationId");
    String creditInvestigatioId = request.getParameter("creditInvestigatioId");
	
	
 //合规检查搜索条件信息 关闭时需要回显 begin 
 //获取编辑合规检查 传入查询条件信息  关闭页面时再传回到主页面 供回显使用
 				String tab_index = request.getParameter("tab_index");
			    String fuzzy = request.getParameter("fuzzy");
			    String branch_name = request.getParameter("branch_name");
			    String business_number = request.getParameter("business_number");
			    String material_man = request.getParameter("material_man");
			    String loan_begin = request.getParameter("loan_begin");
			    String loan_end = request.getParameter("loan_end");
			    String borrower_man = request.getParameter("borrower_man");
			    String pageNumber = request.getParameter("pageNumber");
        //合规检查搜索条件信息 关闭时需要回显 end 

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
    String creditApplicationDESId = desPlus.encrypt(String.valueOf(creditApplicationId));

    String approvalDESId = desPlus.encrypt(String.valueOf(creditApplicationId) + Constants.CM_EXAM);

    String DESNow = desPlus.encrypt(new Date().getTime() + "");
    String cmIp = properties.getProperty("cm.hostip");
    String DESIp = desPlus.encrypt(cmIp);

    /*合规检查和营业部自查*/
    String checkType = request.getParameter("type");
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规检查/编辑</title>
    <style type="text/css">
        input {
            border-left: 0;
            border-right: 0;
            border-top: 0;
            border-bottom: 1px;
            background: white;
            text-align: inherit;
        }
    </style>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var creditApplicationId = "<%=creditApplicationId%>";
        var creditInvestigatioId = "<%=creditInvestigatioId%>";
        
         //
         //获取编辑合规检查 传入查询条件信息  关闭页面时再传回到主页面 供回显使用begin
          var tab_index = "<%=tab_index%>";
          var fuzzy = "<%=fuzzy%>";
          var branch_name = "<%=branch_name%>";
          var business_number = "<%=business_number%>";
          var material_man = "<%=material_man%>";
          var loan_begin = "<%=loan_begin%>";
          var loan_end = "<%=loan_end%>";
          var borrower_man = "<%=borrower_man%>";
          var pageNumber = "<%=pageNumber%>";
        //获取编辑合规检查 传入查询条件信息  关闭页面时再传回到主页面 供回显使用end
       //  $.messager.alert("提示", fuzzy+"branch_name"+branch_name+"business_number"+business_number+"material_man"+material_man+"loan_begin"+loan_begin+"loan_end"+loan_end+"borrower_man"+borrower_man);
        /*申请单附件ID*/
        var creditApplicationDESId = "<%=creditApplicationDESId%>";
        var cmUrl = "<%=cmUrl%>";
        /*审批单附件ID*/
        var approvalDESId = "<%=approvalDESId%>";
        var DESNow = "<%=DESNow%>";
        var DESIp = "<%=DESIp%>";
        /*检查类型*/
        var checkType = "<%=checkType%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_edit.js"></script>
    <c:set var="checkType" value="<%=checkType%>"></c:set>
    <c:choose>
        <c:when test="${checkType eq 'commissioner'}">
            <script type="text/javascript"
                    src="<%=basePath%>scripts/compliance/compliance_check_commissioner.js"></script>
        </c:when>
        <c:when test="${checkType eq 'self'}">
            <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_self.js"></script>
        </c:when>
    </c:choose>
</head>
<body class="easyui-layout">
<input id="borrowerServiceAppId" type="hidden"/>
<%--担保人IDs--%>
<input id="guarantorIds" type="hidden"/>
<%--担保人附件IDs--%>
<input id="borrowerServiceAppDESIds" type="hidden"/>

<div region="center">
    <div id="compliance_check_tab" class="easyui-tabs" style="padding: 10px;" fit="true">
        <div id="tab_div1" title="业务资料">
            <div id="allPeopel">
                <div id="tab0" title="申请单信息"></div>
            </div>
        </div>
        <c:choose>
            <c:when test="${checkType eq 'commissioner'}">
                <div id="tab_div2_commissioner" title="合规检查" fit="true">
                    <jsp:include page="compliance_check_commissioner.jsp" flush="true"></jsp:include>
                </div>
            </c:when>
            <c:when test="${checkType eq 'self'}">
                <div id="tab_div2_self" title="合规自查">
                    <jsp:include page="compliance_check_self.jsp" flush="true"></jsp:include>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
