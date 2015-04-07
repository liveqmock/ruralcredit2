<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    /*获取 creditapplication_id */
    String creditapplication_id = request.getParameter("creditapplication_id");
%>
<html>
<head>
    <base href="<%=basePath%>">

    <title>查看诉讼跟进历史记录</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var poolViewDataGrid;
        $(function () {
            poolViewDataGrid = $('#inquire_pool_view').datagrid({
                url: serverName + '/businessStatusChangeController/viewLegalProceedingsDetail.do',
                fitColumns: true,
                queryParams: {creditapplication_id:<%= creditapplication_id %>},
                rownumbers: true,
                scrollbarSize: 0,
                height: $('#centerPanel')[0].clientHeight,
                singleSelect:true,
                pagination:true,
                columns: [
                    [
                        {
                            title: '操作人', field: 'OPERATOR', width: 50, align: 'left'
                        },
                        {
                            title: '操作时间', field: 'OPERATE_DATE', width: 50, align: 'left'
                        },
                        {
                            title: '跟进记录', field: 'FOLLOW_LOG', width: 200, align: 'left'
                        }
                    ]
                ]
            });
            poolViewDataGrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"FOLLOW_LOG",showContant:"FOLLOW_LOG"});
        });

    </script>
</head>

<body id="fBody" class="easyui-layout">
<div region="center" id="centerPanel">
    <%--查看诉讼跟进历史记录--%>
    <div id="dataGridDiv">
        <table id="inquire_pool_view"></table>
    </div>
</div>
</body>
</html>
