<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>业务状态变更</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/businessStatusChange/businessStatusChange.js"></script>
</head>
<body class="easyui-layout">

<div region="center" id="centerPanel">
    <%--easyui-tabs--%>
    <div class="easyui-tabs" id="bscTab"
         style="padding: 5px; width: auto; height: auto;">
        <div title="不良贷款" style="padding: 5px;" id="tabDiv1">
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
        <table id="bsc"></table>
    </div>

</div>

<%--变更弹窗--%>
<div id="changeDiv"
     style="padding: 5px;"
     buttons="#edit_btn">
    <iframe id="changeFrame"
            name="changeFrame" src="" scrolling="yes"
            frameborder="0" style="width: 100%; height: 100%;">
    </iframe>
    <div id="edit_btn" class="toolbar">
        <a id="editCus" class="easyui-linkbutton" iconCls="icon-ok"
           onclick="saveChange();">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-undo"
           onclick="cancelChange();">取消</a>
    </div>
</div>
</body>
</html>





