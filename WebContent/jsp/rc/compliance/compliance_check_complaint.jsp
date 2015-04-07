<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-16
  Time: 11:40
  合规检查-合规检查记录-申诉、申诉审批
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    String creditApplicationId = request.getParameter("creditApplicationId");
    /*操作类型：申诉、申诉审批*/
    String opType = request.getParameter("opType");
    /*申诉ID、申诉内容*/
    String complaintId = request.getParameter("complaintId");
    String complaintContent = request.getParameter("complaintContent");
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规检查-合规检查记录-申诉、申诉审批</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var creditApplicationId = "<%=creditApplicationId%>";
        var opType = "<%=opType%>";
        var complaintId = "<%=complaintId%>";
        var complaintContent = "<%=complaintContent%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_complaint.js"></script>
</head>
<body class="easyui-layout">
<div region="center" id="center_panel">
    <%--1.客服检查记录--%>
    <div id="div_customer_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: 250px;">
        <div id="div_customer" class="easyui-panel" title="客服检查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_customer" style="border: solid 1px #9ABBE8"></table>
            <table>
                <tr>
                    <td valign="top"> 备注和意见：${opType}</td>
                    <br/>
                    <td id="remark_customer" valign="top"></td>
                </tr>
            </table>
        </div>
    </div>
    <%--2.合规检查记录--%>
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
    <%--3.申诉--%>
    <c:set var="opType" value="<%=opType%>"></c:set>
    <c:set var="div_height" value="299"/>
    <c:if test="${opType eq 'approve'}">
        <c:set var="div_height" value="400"/>
    </c:if>
    <div id="div_complaint_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: ${div_height}px;">
        <div class="easyui-panel" title="申诉" style="padding: 10px;" fit="true" border="false">
            <table style="width: 100%;height: 100%;">
                <c:choose>
                    <%--申诉--%>
                    <c:when test="${opType eq 'complaining'}">
                        <tr>
                            <td nowrap="nowrap"><font color="red">*</font>申诉内容：</td>
                            <td id="complaint_content_complaning" align="left">
                                <textarea id="remark_self_complaining"
                                          style="border-color: #9ABBE8;resize: none"
                                          maxlength="200" validType="maxLength[200]"
                                          onkeydown="textCount('remark_self_complaining','counter',200);$('#counter').show()"
                                          onkeyup="textCount('remark_self_complaining','counter',200);$('#counter').show()"></textarea>
                                <br/>
                                <span id="counter">还可以输入200个字</span>
                                <script type="text/javascript">
                                    /*调整文本域宽度*/
                                    var w = $('#div_customer')[0].clientWidth, h = 170;
                                    $('#remark_self_complaining').width(w - 90).height(h);
                                </script>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" nowrap="nowrap" colspan="2">
                                <a id="submit_complaining" class="easyui-linkbutton abut"
                                   onclick="submit_complaining();">提交</a>
                                <a class="easyui-linkbutton abut" onclick="return_masterList_common();">取消</a>
                            </td>
                        </tr>
                    </c:when>
                    <%--申诉审批--%>
                    <c:when test="${opType eq 'approve'}">
                        <tr>
                            <td nowrap="nowrap" valign="top"><font color="red">&nbsp;&nbsp;</font>申诉内容：</td>
                            <td align="left" valign="top">
                                <div id="complaint_content_approve"></div>
                            </td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap"><font color="red">*</font>审批结果：</td>
                            <td align="left">
                                    <%--<select name="approve_result" id="approve_result_select">
                                        <option value="">请选择</option>
                                        <option value="0">通过</option>
                                        <option value="1">驳回</option>
                                    </select>--%>
                                <input id="approve_result_select" name="approve_result_select" value="">
                            </td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap"><font color="red">*</font>审批备注：</td>
                            <td id="approve_content" align="left">
                                <textarea id="remark_self_approve"
                                          style="border-color: #9ABBE8;resize: none;"
                                          maxlength="200" validType="maxLength[200]"
                                          onkeydown="textCount('remark_self_approve','_counter',200);$('#_counter').show()"
                                          onkeyup="textCount('remark_self_approve','_counter',200);$('#_counter').show()"></textarea>
                                <br/>
                                <span id="_counter">还可以输入200个字</span>
                                <script type="text/javascript">
                                    /*调整文本域宽度*/
                                    var w = $('#div_customer')[0].clientWidth, h = 180;
                                    $('#remark_self_approve').width(w - 90).height(h);
                                    $('#complaint_content_approve').width(w - 90);
                                </script>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" nowrap="nowrap" colspan="2">
                                <a id="submit_approve" class="easyui-linkbutton abut" onclick="submit_approve();">保存</a>
                                <a class="easyui-linkbutton abut" onclick="return_masterList_common();">取消</a>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </table>
        </div>
    </div>
</body>
