<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:04
  合规检查-记录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规检查记录</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        function someOperation(value, row, index) {
            var canDo = "<a href='javascript:viewDetail();'>查看明细</a>";
            /*限制：仅做合规自查时，限制申诉、申诉审批以及申诉状态列的显示*/
            if (row.CUSTOMER_COMMISSIONER) {
                if (!row.COMPLAINT_STATUS) {
                    <sec:authorize ifAnyGranted="${control.compliancerecord_complaint}">
                    canDo += " | <a href='javascript:complianceComplaint(\"complaining\");'>申诉</a>";
                    </sec:authorize>
                } else if (row.COMPLAINT_STATUS == '待申诉审批') {
                    <sec:authorize ifAnyGranted="${control.compliancerecord_approve}">
                    canDo += " | <a href='javascript:complianceComplaint(\"approve\");'>申诉审批</a>";
                    </sec:authorize>
                }
            }
            return canDo;
        }
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check_record.js"></script>
</head>
<body class="easyui-layout" fit="true">
<div region="center" id="center_panel">
    <div id="compliance_check_record_tab" class="easyui-tabs" style="padding: 10px">
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
                <table>
                    <tr>
                        <td align="right">
                            分公司名称：
                        </td>
                        <td>
                            <input id="branch_name" name="branch_name" type="text" style="width: 312px"/>
                        </td>
                        <td align="right">
                            业务单号：
                        </td>
                        <td>
                            <input id="business_number" name="business_number" type="text" style="width: 166px"/>
                        </td>
                        <td align="right">
                            资料提交人：
                        </td>
                        <td>
                            <input id="material_man" name="material_man" type="text" style="width: 166px"/>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" href="javascript:search_advanced()">搜索</a>
                            <a class="easyui-linkbutton" href="javascript:clear_advanced();">清空</a>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            放款日期：
                        </td>
                        <td>
                            <input id="loan_begin" name="loan_begin" class="easyui-datebox" style="width: 150px"
                                   editable="false"/>
                            -
                            <input id="loan_end" name="loan_end" class="easyui-datebox" style="width: 150px"
                                   editable="false"/>
                        </td>
                        <td>申诉状态</td>
                        <td>
                            <input id="complaintStatus" name="complaintStatus" value="" style="width: 165px">
                        </td>
                        <td colspan="3">
                            <sec:authorize ifAnyGranted="${control.compliancerecord_commissioner}">
                                <a class="easyui-linkbutton" href="javascript:exportData('commissioner');">导出合规明细数据</a>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="${control.compliancerecord_customer}">
                                <a class="easyui-linkbutton" href="javascript:exportData('customer');">导出客服明细数据</a>
                            </sec:authorize>
                            <%--<sec:authorize ifAnyGranted="${control.compliancerecord_statistics}">--%>
                                <a class="easyui-linkbutton" href="javascript:exportData('statistics')">导出统计数据</a>
                            <%--</sec:authorize>--%>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="easyui-tabs" style="padding: 10px;">
        <div title="合规检查记录列表">
            <table id="ca_list"></table>
        </div>
    </div>
</div>
</body>
</html>
