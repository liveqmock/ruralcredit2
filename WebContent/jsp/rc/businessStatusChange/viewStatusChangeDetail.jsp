<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@ page import="com.creditease.rc.util.PropertiesUtil" %>
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
    String cmIp = properties.getProperty("cm.hostip");
    DESPlus desPlus = new DESPlus();
    String DESNow = desPlus.encrypt(new Date().getTime() + "");
    String DESIp = desPlus.encrypt(cmIp);

    /*获取业务单号 number */
    String number = request.getParameter("number");
    String tabTitle = request.getParameter("tabTitle");
%>
<html>
<head>
    <base href="<%=basePath%>">

    <title>变更记录-查看详情</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var cmUrl = "<%=cmUrl%>";
        var DESNow = "<%=DESNow%>";
        var DESIp = "<%=DESIp%>";
        var poolViewDataGrid;
        $(function () {
            poolViewDataGrid = $('#inquire_pool_view').datagrid({
                url: serverName + '/businessStatusChangeController/selectChangeLogByNumber.do',
                queryParams: {number: '<%=number%>', tabTitle:'<%=tabTitle%>'},
                rownumbers: true,
                loadMsg: '正在加载....',
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                striped: true,
                singleSelect: true,
                scrollbarSize: 0,
                fitColumns: true,
                columns: [
                    [
                        {
                            title: '业务编码', field: 'BUSINESS_NUMBER', width: 200, align: 'left'
                        },
                        {
                            title: '操作人', field: 'OPERATOR', width: 200, align: 'left'
                        },
                        {
                            title: '操作时间', field: 'OPERATE_DATE', width: 200, align: 'left'
                        },
                        {
                            title: '操作前状态', field: 'BEFORE_STATUS', width: 200, align: 'left'
                        },
                        {
                            title: '操作后状态', field: 'AFTER_STATUS', width: 200, align: 'left'
                        },
                        {
                            title: '变更原因', field: 'CHANGE_REASON', width: 200, align: 'left'
                        },
                        {
                            title: '备注', field: 'CHANGE_REMARK', width: 200, align: 'left'
                        },
                        {
                            title: '上传文件', field: 'ATTATCHMENT_ID', width: 200, align: 'left', formatter: function (value, row, index) {
                            if (!value) {
                                return '';
                            }
                            return "<a style='color: blue' onclick='viewAttachment(\"" + value + "\")'>查看</a>";
                        }
                        }
                    ]
                ]
            });
            poolViewDataGrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"CHANGE_REMARK",showContant:"CHANGE_REMARK"});
        });

        //查看附件
        function viewAttachment(attatchmentId) {
            var href = cmUrl + "/operation/transferParameter.action?clientId=" + attatchmentId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
                    + "&signIp=" + DESIp + "";
            window.parent.open(href);
        }
        function resizeGrid() {
            poolViewDataGrid.datagrid('resize', {
                height: $('#centerPanel')[0].clientHeight
            });
        }
    </script>
</head>

<body class="easyui-layout" onload="resizeGrid();">
<div region="center" id="centerPanel">
    <%--查看变更记录详情--%>
    <div id="dataGridDiv">
        <table id="inquire_pool_view" width="100%"></table>
    </div>
</div>
</body>
</html>
