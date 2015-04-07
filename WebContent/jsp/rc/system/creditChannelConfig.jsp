<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>"/>

    <title>申请单渠道配置</title>

    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <jsp:include page="../include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>/scripts/system/creditChannelConfig.js"></script>
    <style type="text/css">
        .myTH {
            border-right: 1px solid #ccc;
            font-size: 12px;
            font-weight: normal;
            background: #fafafa url('scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
            border-bottom: 1px dotted #ccc;
            border-top: 1px dotted #ccc;
            overflow: hidden;
            background: #fafafa url('scripts/uilib/themes/default/images/datagrid_header_bg.gif') repeat-x left bottom;
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
    </style>
</head>
<body class="easyui-layout">
<div region="west" title="营业部" style="width: 400px; padding: 10px;">
    <ul id="departmentTree"></ul>
</div>
<div region="center" title="信息" style="padding: 20px;">
    <input id="areaDepartmentId" type="hidden"/>
    <input id="areaDepartmentName" type="hidden"/>
    <span id="who_am_i" style="font-size: x-large; color: #4B0082;">全部</span>
    <table id="creditChannelList" toolbar="#toolbar"></table>
    <div id="configDialog" title="配置" class="easyui-dialog" style="width: 400px; height: 200px;" buttons="#buttons"
         closed="true" modal="true">
        <form id="configForm" novalidate>
            <table>
                <input type="hidden" id="configId" name="configId"/>
                <tr>
                    <th class="myTH" width="200px">
                        营业部名称：
                    </th>
                    <td class="myTD" width="200px">
                        <input id="departmentId" name="departmentId" type="hidden"/>
                        <input id="departmentName" name="departmentName" style="width: 200px;"/>
                    </td>
                </tr>
                <tr>
                    <th class="myTH" width="200px">
                        渠道：
                    </th>
                    <td class="myTD" width="200px">
                        <input id="channel" name="channel" style="width: 200px;" required="required"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="buttons">
    <a class="easyui-linkbutton" id="saveButton" href="javascript:void(0);" onclick="save_credit_channel_config();" plain="true"
       iconCls="icon-save">保存</a>
    <a class="easyui-linkbutton" id="cancelButton" href="javascript:void(0);" onclick="cancel_credit_channel_config();" plain="true"
       iconCls="icon-cancel">关闭</a>
</div>
<div id="toolbar">
    <a class="easyui-linkbutton" id="addOneButton" href="javascript:void(0);" onclick="addOne();" plain="true"
       iconCls="icon-addOne">新增</a>
    <a class="easyui-linkbutton" id="editButton" href="javascript:void(0);" onclick="edit();" plain="true"
       iconCls="icon-edit">编辑</a>
    <a class="easyui-linkbutton" id="removeButton" href="javascript:void(0);" onclick="removeOne();" plain="true"
       iconCls="icon-remove">删除</a>
</div>
</body>
</html>
