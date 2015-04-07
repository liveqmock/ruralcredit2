<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>编辑咨询记录</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"/>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        function loadData(){
            //无效登记状态
            var status = '${customerConsultPool.invalidRegStatus}';
            if(status != '拒绝' && status != '无效客户' && status != '未联系上客户'){
                status = "";
            }
            $('#invalidStatus').combobox({
                valueField: 'id',
                textField: 'text',
                value: status,
                data: [
                    {
                        "id": "",
                        "text": "请选择"
                    },
                    {
                        "id": "4",
                        "text": "拒绝"
                    },
                    {
                        "id": "5",
                        "text": "无效客户"
                    },
                    {
                        "id": "6",
                        "text": "未联系上客户"
                    }
                ]
            });
        }
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/saleInquireEdit.js"></script>
</head>

<body class="easyui-layout" style="background:#fafafa; width:100%;height:100%;margin: 0px;padding: 0px;" onload="loadData();">
<div region="center" style="background:#fafafa; width:100%;height:100%;">
    <form action="" id="inquireFormEdit">
        <input type="hidden" name="consultPoolId" value="${customerConsultPool.consultPoolId}"/>
        <%--操作人--%>
        <input type="hidden" name="creater" value="${customerConsultPool.creater}"/>
        <table class="tabfrom" align="center" width="100%">
            <tr height="30">
                <td align="right" width="20%"><font color="red">*</font>咨询日期：</td>
                <td width="30%">
                    <input id="registDate" editable="false" class="easyui-datebox" type="text" name="registDate"
                           required="required" style="width:152px;"
                           value="<fmt:formatDate value='${customerConsultPool.registDate}' pattern='yyyy-MM-dd'/>"  readonly="readonly" disabled="disabled">
                </td>
                <td align="right" width="20%"><font color="red">*</font>客户姓名：</td>
                <td width="30%">
                    <input id="customerName" name="customerName" style="height:16px;border:1px solid #A4BED4 ;width: 147px;"
                           onkeydown="if(event.keyCode==32){return false;}"
                           class="easyui-validatebox" required="true"
                           validType="length[0,10]" maxlength="10" value="${customerConsultPool.customerName}"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>性别：</td>
                <td>
                    <input id="conSex" class="easyui-combobox" name="conSex" style="width:152px;"
                           value="${customerConsultPool.conSex}">
                    </input>
                </td>
                <td align="right">年龄：</td>
                <td>
                    <input id="conAge" class="easyui-numberbox" type="text" name="conAge" min="20" max="100" precision="0"
                           style="width:147px;" maxlength="3" value="${customerConsultPool.conAge}"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>手机号码：</td>
                <td>
                    <input id="mobilePhone" maxlength="11" class="easyui-validatebox" type="text" name="mobilePhone"
                           required="true" validType="phoneNumber" invalidmessage="输入11位手机号"
                           style="width: 147px;" value="${customerConsultPool.mobilePhone}" disabled="disabled"/>
                </td>
                <td align="right"><font color="red">*</font>咨询方式：</td>
                <td>
                    <input class="easyui-combobox" type="text" id="consultWay"
                           name="consultWay" style="width:150px;" required="true"
                           value="${customerConsultPool.consultWay}" disabled="disabled"/>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>借款用途行业：</td>
                <td>
                    <input type="text" id="borrowing" name="borrowing"
                           style="width: 150px;" required="true" value="${customerConsultPool.borrowing}"/>
                </td>
                <td align="right"><font color="red">*</font>咨询金额：</td>
                <td>
                    <input id="consultAmount" name="consultMoney" class="easyui-numberbox"
                           min='0' max='99' style="width: 147px;" required="true"
                           type="text" value="${customerConsultPool.consultMoney}"  disabled="disabled"/>（万元）
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>信息来源：</td>
                <td>
                    <input type="text" id="channel" name="channel" value="${customerConsultPool.channel}"
                           style="width: 150px;" required="true"/>
                    <%--<input class="easyui-validatebox" type="text" id="channelText"
                           name="channelText"
                           style="width: 147px;" required="true" editable="false" readonly="readonly"
                           onclick="showinfomationSource();" value="${customerConsultPool.channelText}" disabled="disabled"/>--%>
                </td>
            </tr>
            <tr height="30">
                <td align="right"><font color="red">*</font>户籍地址：</td>
                <td colspan="3">
                    <input id="province" class="easyui-combobox" name="province" style="width:120px;"
                           required="true" value="${customerConsultPool.province}">

                    </input>
                    <input id="city" class="easyui-combobox" name="city" style="width:120px;height:10px;"
                           required="true" value="${customerConsultPool.city}">
                    </input>
                    <input id="area" class="easyui-combobox" name="area" style="width:125px;height:10px;"
                           required="true" value="${customerConsultPool.area}">
                    </input>
                </td>
            </tr>
            <tr>
                <script type="text/javascript">
                    var l = '${customerConsultPool.marketConsultRemark}'.length;
                    var lable = 200 - l;
                    $('#counter').html('还可输入' + lable + '字！');
                </script>
                <td align="right">备注：</td>
                <td colspan="3">
                    <textarea id="marketConsultRemark" name="marketConsultRemark"
                              style="height:100px;width:600px;"
                              maxlength="200" validType="maxLength[200]"
                              onkeydown="textCount('marketConsultRemark','counter',200);$('#counter').show()"
                              onkeyup="textCount('marketConsultRemark','counter',200);$('#counter').show()">${customerConsultPool.marketConsultRemark}</textarea>
                    <br><span id="counter"></span><br>
                </td>
            </tr>
            <tr height="30">
                <td align="right">无效登记状态：</td>
                <td>
                    <input id="invalidStatus" class="easyui-combobox" name="invalidStatus" style="width:152px;"
                            value="${customerConsultPool.invalidRegStatus}"/>

                    </input>
                    <input type="hidden" id="marketConsultState" name="marketConsultState" value="${customerConsultPool.marketConsultState}"/>
                </td>
                <td align="right">客户标签：</td>
                <td>
                    <input id="customerTag" type="hidden" name="customerTag" value="${customerConsultPool.customerTag}"/>
                    <input id="processCausesSection" type="hidden"/>
                    <input type="text" id="customerTagName" name="customerTagName" style="width: 185px;"
                           onclick="showpprocessCauses('customerTag','customerTagName');"
                           value="${customerConsultPool.customerTagName}"/>
                </td>
            </tr>
        </table>
    </form>
</div>
    <%--弹出信息来源 DIV--%>
<div id="windowinfomationSource">
    <table id="tableinfomationSource" style="width: 400px;border:1px;  border-color: black;" align="center" fit="true">
    </table>
</div>
    <%--弹出客户标签 DIV--%>
<div id="windowprocessCauses">
    <table id="tableprocessCauses" style="width: 500px;border:1px;  border-color: black;" align="center" fit="true">
    </table>
</div>
</body>
</html>