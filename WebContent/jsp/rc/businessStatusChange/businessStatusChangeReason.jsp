<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.creditease.rc.util.PropertiesUtil" %>
<%@ page import="com.creditease.core.security.SpringSecurityUtils" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

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
    String userId = SpringSecurityUtils.getCurrentUser().getUserId();
    DESPlus desPlus = new DESPlus();
    String clientId = "";
    /*String type = request.getParameter("type");
    String attachmentId = request.getParameter("attachmentId");
    if (attachmentId.equals("0")) {
        if (type.equals("不良贷款")) {
            clientId = desPlus.encrypt("00"+ new Date().toString());
        } else if (type.equals("法律诉讼")) {
            clientId = desPlus.encrypt("01" + new Date().toString());
        }
    } else {
        clientId = attachmentId;
    }*/
    clientId = desPlus.encrypt(new Date().toString());
%>
<html>
<head>
    <title>变更原因</title>
    <base href="<%=basePath%>"/>
    <jsp:include page="/jsp/rc/include/easyui.jsp"/>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var cmUrl = "<%=cmUrl%>";
        var clientid = "<%=clientId%>";
        var userId = "<%=userId%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/businessStatusChange/businessStatusChangeReason.js"></script>
</head>

<body class="easyui-layout" style="background:#fafafa; width:100%;height:100%;margin: 0px;padding: 0px;">
<div region="center" style="background:#fafafa; width:100%;height:100%;">
    <form action="" id="changeReasonForm">
        <input type="hidden" id="creditapplicationId" name="creditapplicationId" value="${vo.creditapplicationId}"/>
        <input type="hidden" id="type" name="type" value="${vo.type}"/>
        <input type="hidden" id="changeId" name="changeId" value="${vo.changeId}"/>
        <input type="hidden" id="beforeStatus" name="beforeStatus" value="${vo.beforeStatus}"/>
        <table align="center" width="100%">
            <c:choose>
                <c:when test="${(vo.type == '不良贷款' && (vo.beforeStatus == '还款中'||vo.beforeStatus == '法律诉讼'))}">
                <%--||(vo.type == '法律诉讼' && (vo.beforeStatus == '还款中'||vo.beforeStatus == '不良贷款'))}">--%>
                    <tr height="20px">
                        <td width="10%" align="right" nowrap="nowrap">
                            <font color="red">*</font>
                            变更原因：
                        </td>
                        <td>
                            <input id="changeReason" class="easyui-combobox" required="true" name="changeReason"
                                   style="width:150px;"> </input>
                        </td>
                    </tr>
                    <script type="text/javascript">
                        //加载数据字典
                        var dic = new DataDictionary();
                        dic.addDic("changeReason_Y", "changeReason");
                        dic.dicAjax();
                    </script>
                </c:when>
            </c:choose>
            <tr height="20px">
                <td width="10%" align="right" nowrap="nowrap">备注：</td>
                <td>
                    <textarea id="remark" name="remark"
                              style="height:100px;width:100%;"
                              maxlength="100" validType="maxLength[100]"
                              onkeydown="textCount('remark','counter',100);$('#counter').show()"
                              onkeyup="textCount('remark','counter',100);$('#counter').show()"></textarea>
                    <br><span id="counter">原因还可以输入100字</span><br>
                    <script type="text/javascript">
                        //字数验证
                        function textCount(textId, htmlId, max) {
                            $("#" + textId).keyup(function () {
                                var maxl = max;
                                var tishi = "原因还可以输入" + maxl + "个字";
                                $("#" + htmlId).html(tishi);
                                var xianyou = $(this).val().length;
                                var keyi = maxl - xianyou;
                                var tishi = "原因还可以输入" + keyi + "个字";
                                if (xianyou > (max - 1)) {
                                    var tishi = "原因还可以输0个字";
                                    $("#" + htmlId).css({
                                        "color": "red"
                                    });
                                    var s = $("#" + textId).val().substr(0, 100);
                                    $("#" + textId).val();
                                } else {
                                    $("#" + htmlId).css({
                                        "color": "#000"
                                    });
                                }
                                $("#" + htmlId).html(tishi);
                            });
                        }
                    </script>
                </td>
            </tr>
            <c:choose>
                <c:when test="${(vo.type == '不良贷款' && (vo.beforeStatus == '还款中'||vo.beforeStatus == '法律诉讼'))
                ||(vo.type == '法律诉讼' && (vo.beforeStatus == '还款中'||vo.beforeStatus == '不良贷款'))}">
                    <tr height="285px">
                        <td width="10%" align="right" nowrap="nowrap"> 文件上传：</td>
                        <td>
                            <iframe scrolling="auto" id="openCM" frameborder="0" src=""
                                    style="width:100%;height:100%;"/>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </table>
    </form>
</div>
</body>
</html>