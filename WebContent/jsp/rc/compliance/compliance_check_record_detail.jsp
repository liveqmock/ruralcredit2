<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:03
  合规检查-合规检查记录-查看明细页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    String creditApplicationId = request.getParameter("creditApplicationId");
    /*申诉：申诉状态、申诉内容、审批内容*/
    String complaintStatus = request.getParameter("complaintStatus");
    String complaintContent = request.getParameter("complaintContent");
    String approveContent = request.getParameter("approveContent");
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规检查-合规检查记录-查看明细页面</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var creditApplicationId = "<%=creditApplicationId%>";
        var complaintStatus = "<%=complaintStatus%>";
        var complaintContent = "<%=complaintContent%>";
        var approveContent = "<%=approveContent%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_record_detail.js"></script>
</head>
<body class="easyui-layout">
<div region="center" id="center_panel">
    <%--1.营业部自查记录--%>
    <div id="div_self_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: 250px;">
        <div id="div_self" class="easyui-panel" title="营业部自查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_self" style="border: solid 1px #9ABBE8;"></table>
            <table>
                <tr>
                    <td> 备注：</td>
                    <br/>
                    <td id="remark_self"></td>
                </tr>
            </table>
        </div>
    </div>
    <%--2.客服检查记录--%>
    <div id="div_customer_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: 250px;">
        <div class="easyui-panel" title="客服检查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_customer" style="border: solid 1px #9ABBE8"></table>
            <table>
                <tr>
                    <td valign="top"> 备注和意见：</td>
                    <br/>
                    <td id="remark_customer" valign="top"></td>
                </tr>
            </table>
        </div>
    </div>
    <%--3.合规检查记录--%>
    <div id="div_commissioner_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: 250px;">
        <div class="easyui-panel" title="合规检查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_commissioner" style="border: solid 1px #9ABBE8"></table>
            <table>
                <tr>
                    <td> 备注：</td>
                    <br/>
                    <td id="remark_commissioner"></td>
                </tr>
            </table>
        </div>
    </div>
    <%--4.申诉记录--%>
    <div id="div_complaint_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: 250px;">
        <div class="easyui-panel" title="申诉记录" style="padding: 10px;" fit="true" border="false">
            <div id="div_complaint" style="display: none">
                <table style="width: 100%;height: 100%">
                    <tr>
                        <td nowrap="nowrap" valign="top" width="5%">申诉内容：</td>
                        <td align="left" valign="top" id="complaint_content"></td>
                    </tr>
                </table>
            </div>
            <div id="div_complaint_approve" style="display: none">
                <table style="width: 100%;height: 100%">
                    <tr style="height: 25%">
                        <td nowrap="nowrap" width="5%" valign="top">申诉内容：</td>
                        <td align="left" id="_complaint_content" valign="top"></td>
                    </tr>
                    <tr style="height: 25%">
                        <td nowrap="nowrap" width="5%" valign="top">审批结果：</td>
                        <td align="left" id="approve_result" valign="top"></td>
                    </tr>
                    <tr>
                        <td nowrap="nowrap" width="5%" valign="top">审批备注：</td>
                        <td align="left" id="approve_content" valign="top"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
