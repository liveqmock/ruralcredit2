<%@ page import="com.creditease.rc.util.PropertiesUtil" %>
<%@ page import="java.util.Properties" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
    String cmIp = properties.getProperty("cm.hostip");
    DESPlus desPlus = new DESPlus();
    String DESNow = desPlus.encrypt(new Date().getTime() + "");
    String DESIp = desPlus.encrypt(cmIp);
%>
<html>
<head>
    <title>业务状态变更</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var cmUrl = "<%=cmUrl%>";
        var DESNow = "<%=DESNow%>";
        var DESIp = "<%=DESIp%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/businessStatusChange/businessStatusChangeHistory.js"></script>
</head>
<body class="easyui-layout">

<div region="center" id="centerPanel">
    <%--easyui-tabs--%>
    <div class="easyui-tabs" id="bscTab"
         style="padding: 5px; width: auto; height: auto;">
        <div title="不良贷款" style="padding: 10px;" id="tabDiv1">
            <table style="width: 100%">
                <tr>
                    <td style="width: 20%">
                        业务单号：<input type="text" style="width: 150px;" id="businessNum_first" name="businessNum_first">
                    </td>
                    <td >
                        <a href="#" class="easyui-linkbutton" onclick="searchBusStaChange('nonPerformingLoans');">查询</a>
                        <a href="#" class="easyui-linkbutton" onclick="clearBusinessNum('nonPerformingLoans');">清空</a>
                    </td>
                </tr>
            </table>
        </div>
        <div title="法律诉讼" style="padding: 10px;" id="tabDiv2">
            <table width="100%">
                <tr>
                    <td style="width: 20%">
                        业务单号：<input type="text" style="width: 150px;" id="businessNum_second" name="businessNum_second">
                    </td>
                    <td>
                        <a href="#" class="easyui-linkbutton" onclick="searchBusStaChange('legalProceedings');">查询</a>
                        <a href="#" class="easyui-linkbutton" onclick="clearBusinessNum('legalProceedings');">清空</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <%--easyui-datagrid--%>
    <div id="bscDiv" style="padding: 5px;">
        <table id="bsc" class="easyui-datagrid"></table>
    </div>

</div>
<div id="registerInquireViewDiv" class="easyui-dialog" closed="true"
     style="padding: 0px;">
    <iframe id="registerInquireViewIframe" src="" scrolling="no"
            frameborder="0" style="width: 100%; height: 100%;"></iframe>
</div>
</body>
</html>





