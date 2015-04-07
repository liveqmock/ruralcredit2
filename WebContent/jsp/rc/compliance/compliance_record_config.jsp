<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:04
  合规检查-分数配置页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>分数配置页面</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_config.js"></script>
</head>
<body class="easyui-layout">
<div region="center" fit="true">
    <div id="div_panel_customer" class="easyui-panel" title="客服合规扣分配置" border="false" style="padding-top: 10px">
        <table>
            <tr>
                <td>检查点：</td>
                <td><select id="combobox_checkpoint_customer" name="combobox_checkpoint_customer"
                            class="easyui-combobox" style="width: 350px;"></select></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_search('customer');">搜索</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_add('customer');">新增</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_edit('customer');">编辑</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_delete('customer');">删除</a></td>
            </tr>
        </table>
        <table id="datagrid_customer"></table>
    </div>
    <div id="div_panel_commissioner" class="easyui-panel" title="合规检查扣分配置" border="false" style="padding-top: 10px">
        <table>
            <tr>
                <td>检查点：</td>
                <td><select id="combobox_checkpoint_commissioner" name="combobox_checkpoint_commissioner"
                            class="easyui-combobox" style="width: 350px;"></select></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_search('commissioner');">搜索</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_add('commissioner');">新增</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_edit('commissioner');">编辑</a></td>
                <td><a href="#" class="easyui-linkbutton" onclick="compliance_delete('commissioner');">删除</a></td>
            </tr>
        </table>
        <table id="datagrid_commissioner"></table>
    </div>
    <%--新增dialog--%>
    <div id="div_dialog" class="easyui-dialog"
         style="width:400px;height:150px;padding:10px" buttons="#dlg-buttons">
        <table>
            <tr>
                <td align="right" nowrap="nowrap">检查点：</td>
                <td><select id="commonDialog_checkpoint" name="commonDialog_checkpoint"
                            class="easyui-combobox" style="width: 300px;" required="true"></select></td>
            </tr>
            <tr>
                <td align="right" nowrap="nowrap">扣分：</td>
                <td><input id="commonDialog_score" name="commonDialog_score" type="text" style="width: 300px"
                           class="easyui-numberbox" precision="1" maxlength="4"/></td>
            </tr>
        </table>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:config_save();">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           onclick="javascript:config_cancel();">取消</a>
    </div>
    <%--编辑dialog--%>
    <div id="div_dialog_edit" class="easyui-dialog"
         style="width:400px;height:150px;padding:10px" buttons="#dlg-buttons_edit">
        <form id="editForm">
            <table>
                <input id="complianceCheckPointConfigId" name="complianceCheckPointConfigId" type="hidden"/>
                <tr>
                    <td align="right" nowrap="nowrap">检查点：</td>
                    <td><select id="commonDialog_checkpoint_edit" name="commonDialog_checkpoint_edit"
                                class="easyui-combobox" style="width: 300px;" required="true" disabled="disabled"></select></td>
                </tr>
                <tr>
                    <td align="right" nowrap="nowrap">扣分：</td>
                    <td><input id="commonDialog_score_edit" name="commonDialog_score_edit" type="text"
                               style="width: 300px" class="easyui-numberbox" precision="1" maxlength="4"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons_edit">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:config_save_edit();">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           onclick="javascript:config_cancel_edit();">取消</a>
    </div>
</div>
</body>
</html>
