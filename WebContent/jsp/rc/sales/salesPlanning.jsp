<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>

    <title>My JSP 'salesPlanning.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
    <jsp:include page="../include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>/scripts/sales/salesPlanning.js"></script>
    <style type="text/css">
        .myTH {
            border-right: 1px solid #ccc;
            font-size: 12px;
            font-weight: normal;
            background: #fafafa url('/scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
            border-bottom: 1px dotted #ccc;
            border-top: 1px dotted #ccc;
            overflow: hidden;
            background: #fafafa url('/scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
            border-bottom: 1px dotted #ccc;
            cursor: default;
            border-top: 1px dotted #ccc;
        }

        .myTD {
            font-size: 12px;
            border-right: 1px dotted #ccc;
            border-bottom: 1px dotted #ccc;
            overflow: hidden;
            padding: 0;
            margin: 0;
        }

        .myInputBorder {
            border: 1px;
        }
    </style>
</head>

<body class="easyui-layout">
<div region="center">
    <div id="salesSearchTab" class="easyui-tabs" style="padding: 10px;">
        <div title="搜索" style="height: auto; width: auto">
            <form id="searchForm">
                <table style="height: 50px;padding-left: 50px;" cellspacing="20px">
                    <tr>
                        <td>
                            请选择年份：<input id="year" class="easyui-combobox"/>
                            <input id="_year" type="hidden"/>
                        </td>
                        <td>
                            <span id="box_name">请选择营业部</span>：<input id=areaDepartmentIds style="width: 400px;"/>
                            <a class="easyui-linkbutton" plain="false" iconCls="icon-search"
                               onclick="searchSalesPlan('', '', 'box')">搜索</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="tol">
        <a id="edit" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="edit()">编辑</a>
        <a id="save" class="easyui-linkbutton" plain="true" iconCls="icon-save" disabled="true"
           onclick="saveAll()">保存</a>
        <a id="redo" class="easyui-linkbutton" plain="true" iconCls="icon-redo" disabled="true"
           onclick="cancelEdit('cancelEdit')">取消</a>
    </div>
    <div id="sales_tab" class="easyui-tabs" style="padding: 10px; height: 500%;" tools="#tol">
        <jsp:include page="salesPlanningTable_multiple.jsp" flush="true"/>
    </div>
</div>
</body>
</html>
