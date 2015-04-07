<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登记客户咨询</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css">
    <jsp:include page="../include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/saleInquireRegister.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>
</head>
<body class="easyui-layout" style="background:#fafafa; width:100%;height:100%;margin: 0px;padding: 0px;">
<div region="center" style="background:#fafafa; width:100%;height:100%;">
    <form action="" id="inquireForm">
        <table class="tabfrom" align="center" width="100%">
            <tbody>
            <tr height="30">
                <td align="right" width="20%"><font color="red">*</font>咨询日期：</td>
                <td width="30%">
                    <input id="registDate" editable="false" class="easyui-datebox" type="text" name="registDate"
                           required="required" style="width:152px;"/>
                </td>
                <td align="right" width="20%"><font color="red">*</font>客户姓名：</td>
                <td width="30%">
                    <input id="customerName" name="customerName" style="height:16px;border:1px solid #A4BED4 ;width: 147px;"
                           onkeydown="if(event.keyCode==32){return false;}"
                           class="easyui-validatebox" required="true"
                           validType="length[0,10]" maxlength="10"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>性别：</td>
                <td>
                    <input id="conSex" class="easyui-combobox" required="true" name="conSex" style="width:152px;">
                    </input>
                </td>
                <td align="right">年龄：</td>
                <td>
                    <input class="easyui-numberbox" type="text" name="conAge" min="20" max="100" precision="0"
                           style="width:147px;" maxlength="3"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>手机号码：</td>
                <td>
                    <input class="easyui-validatebox" validType="phoneNumber"  id="mobilePhone" maxlength="11" name="mobilePhone" type="text" style="width: 147px;" required="true"/>
                </td>
                <td align="right"><font color="red">*</font>咨询方式：</td>
                <td>
                    <input class="easyui-combobox" type="text" id="consultWay"
                           name="consultWay" style="width:150px;" required="true" value="2"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>借款用途行业：</td>
                <td>
                    <input id="borrowing" type="text" name="borrowing"
                           style="width: 150px;" required="true"/>
                </td>
                <td align="right"><font color="red">*</font>咨询金额：</td>
                <td>
                    <input id="consultAmount" name="consultAmount" class="easyui-numberbox"
                           min="0" max="99" precision="0" style="width: 147px;" required="true"
                           maxlength="2" type="text"/>（万元）
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>信息来源：</td>
                <td>
                    <input type="text" id="channel" name="channel" editable="false" required="true"
                           style="width: 150px;"/>
                    <%--<input class="easyui-validatebox" type="text" id="channelText" name="channelText"
                           style="width: 147px;" required="true" editable="false"
                           readonly="readonly"
                           onclick="showinfomationSource();"/>--%>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>户籍地址：</td>
                <td colspan="3">
                    <input id="provinceName" name="provinceName" type="hidden"/>
                    <input id="province" class="easyui-combobox" name="province" style="width:120px;"
                           required="true" editable="false">

                    </input>
                    <input id="cityName" name="cityName" type="hidden"/>
                    <input id="city" class="easyui-combobox" name="city" style="width:120px;height:10px;"
                           required="true" editable="false">
                    </input>
                    <input id="areaName" name="areaName" type="hidden"/>
                    <input id="area" class="easyui-combobox" name="area" style="width:125px;height:10px;"
                           required="true" editable="false">
                    </input>
                </td>
            </tr>
            <tr>
                <td align="right">备注：</td>
                <td colspan="3">
                    <textarea id="marketConsultRemark" name="marketConsultRemark"
                              style="height:100px;width:600px;" maxlength="200" validType="maxLength[200]"
                              onkeydown="textCount('marketConsultRemark','counter',200);$('#counter').show()"
                              onkeyup="textCount('marketConsultRemark','counter',200);$('#counter').show()"></textarea>
                    <br><span id="counter">还可输入200字！</span><br>
                </td>
            </tr>

            <%--<tr height="30">
                <td align="right"><font color="red">*</font>手机：</td>
                <td>
                    <input maxlength="11" class="easyui-validatebox" type="text" name="mobilePhone" data-options="required:true,validType:'phoneNumber',invalidmessage:'输入11位手机号'" style="width: 147px;"/>
                </td>
                <td align="right">固定电话：</td>
                <td>
                    <input class="easyui-validatebox" type="text" name="fixedTelephone" data-options="validType:'fixPhoneNumber'" style="width: 147px;"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right">经营年限：</td>
                <td>
                    <select id="businessPeriod" class="easyui-combobox" name="businessPeriod" style="width:152px;">
                    </select>
                </td>
                <td align="right">是否有营业执照：</td>
                <td>
                    <select id="isBusinessLicense" class="easyui-combobox" name="isBusinessLicense" style="width:152px;">
                    </select>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>推荐渠道：</td>
                <td>
                    <select id="channel" class="easyui-combobox" name="channel" style="width:152px;" data-options="required:true">
                        <option>请选择</option>
                    </select>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </form>
</div>

<div id="windowinfomationSource">
    <table id="tableinfomationSource" style="width: 400px;border:1px;  border-color: black;" align="center" fit="true">
    </table>
</div>

</body>
</html>