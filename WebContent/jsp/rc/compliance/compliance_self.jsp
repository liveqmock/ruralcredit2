<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:04
  合规检查-营业部自查列表页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规自查</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_self.js"></script>
</head>
<body class="easyui-layout" fit="true">
<div region="center" id="center_panel">
    <div id="compliance_check_tab" class="easyui-tabs" style="padding: 10px">
        <div id="tab_div1" title="模糊查询" style="padding: 10px;">
            <table>
                <tr>
                    <td>
                        搜索条件：
                    </td>
                    <td>
                        <input id="fuzzy" type="text" style="width: 150px"/>
                    </td>
                    <td>
                        <a class="easyui-linkbutton" href="javascript:fuzzySearch()">搜索</a>
                        <a class="easyui-linkbutton" href="javascript:clearFuzzyBox();">清空</a>
                    </td>
                    <td>
                        <font color="red">
                            (可输入业务单号、或借款人、或客户经理、分公司名称 不完整片段进行搜索)
                        </font>
                    </td>
                </tr>
            </table>
        </div>
        <div id="tab_div2" title="高级查询" style="padding: 10px;">
            <form id="searchForm">
                <table width="100%">
                    <tr>
                        <td align="right" nowrap="nowrap">
                            分公司名称：
                        </td>
                        <td>
                            <input id="branch_name" name="branch_name" type="text" style="width: 150px"/>
                        </td>
                        <td align="right" nowrap="nowrap">
                            业务单号：
                        </td>
                        <td>
                            <input id="business_number" name="business_number" type="text" style="width: 150px"/>
                        </td>
                        <td align="right" nowrap="nowrap">
                            客户经理：
                        </td>
                        <td>
                            <input id="account_manager" name="account_manager" type="text" style="width: 150px"/>
                        </td>
                        <td align="right" nowrap="nowrap">
                            借款人姓名：
                        </td>
                        <td>
                            <input id="borrower_man" name="borrower_man" type="text" style="width: 150px"/>
                        </td>
                        <td nowrap="nowrap">
                            <a class="easyui-linkbutton" href="javascript:search_advanced()">搜索</a>
                            <a class="easyui-linkbutton" href="javascript:clear_advanced();">清空</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="easyui-tabs" style="padding: 10px;">
        <div title="合规自查列表">
            <table id="ca_list"></table>
        </div>
    </div>
</div>
</body>
</html>
