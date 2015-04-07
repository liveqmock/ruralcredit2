<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

        //操作列
        function operationsFunc(value,row,index){
            //信贷申请ID
            var consultPoolId = row.CREDITAPPLICATION_ID;
            //风险经理ID
            var fxId = row.CREDIT_INVESTIGATIO_ID;
            //业务单号
            var businessNumber = row.BUSINESS_NUMBER;
            var link;
            if (consultPoolId) {
                link = " <a href='#' onclick='viewCreditApplication(\"" + consultPoolId + "\",\"" + fxId + "\",\"" + businessNumber + "\");'>查看</a>";

                /*
                 角色限制：贷后服务/营业部经理
                 */
                <sec:authorize ifAnyGranted="${control.legalProceedings_followAndLog}">
                    link += " | <a href='#' onclick='followLegalProceedings(\"" + consultPoolId + "\")'>诉讼跟进</a>";
                    link += " | <a href='#' onclick='viewLegalProceedingsLog(\"" + consultPoolId + "\")'>跟进记录</a>";
                </sec:authorize>
            }
            return link;
        }

        /**
        * 查看业务详情信息
        * @param consultPoolId 信贷申请ID
        * @param fxId 风险经理ID
        * @param businessNumber 业务单号
        */
        function viewCreditApplication(consultPoolId,fxId,businessNumber){
            window.parent.addTabFun({
                src: serverName
                        + "/creditgroup/searchLook.do?creditApplicationId="
                        + consultPoolId + "&creditInvestigatioId="
                        + fxId,
                title: businessNumber + "申请单查看"
            });
        }

        //诉讼跟进
        function followLegalProceedings(creditApplicationId){
            $("#registerInquireViewIframe_follow")[0].src = "<%=basePath%>jsp/rc/businessStatusChange/followLegalProceedings.jsp?creditapplication_id=" + creditApplicationId;
            $("#registerInquireViewDiv_follow").dialog({
                title: "诉讼跟进",
                closed: true,
                draggable: true,
                width: $('#centerPanel')[0].clientWidth - 500,
                height: 500,
                onClose : function() {
                    $("#registerInquireViewIframe_follow").attr('src', "");
                }
            });
            $("#registerInquireViewDiv_follow").dialog("open");
        }

        //跟进记录
        function viewLegalProceedingsLog(creditApplicationId){
            $("#registerInquireViewIframe")[0].src = "<%=basePath%>jsp/rc/businessStatusChange/viewLegalProceedingsLog.jsp?creditapplication_id=" + creditApplicationId;
            $("#registerInquireViewDiv").dialog({
                title: "诉讼跟进历史记录",
                buttons: [
                    {id: "registerInquireViewButton", text: "关闭", handler: function () {
                        $("#registerInquireViewDiv").dialog("close");
                    }}
                ],
                closed: true,
                draggable: true,
                width: $('#centerPanel')[0].clientWidth - 500,
                height: 380,
                onClose : function() {
                    $("#registerInquireViewIframe").attr('src', "");
                }
            });
            $("#registerInquireViewDiv").dialog("open");
        }
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/businessStatusChange/legalProceedings.js"></script>
</head>
<body class="easyui-layout">

<div region="center" id="centerPanel">
    <%--easyui-tabs--%>
    <div class="easyui-tabs" id="bscTab"
         style="padding: 5px; width: auto; height: auto;">
        <div title="条件查询" style="padding: 5px;" id="tabDiv1">
            <table style="width: 100%">
                <tr>
                    <td style="width: 20%" align="left" nowrap="nowrap">
                        业务单号：<input type="text" style="width: 150px;" id="businessNumber" name="businessNumber"/>
                    </td>
                    <td style="width: 20%" align="left" nowrap="nowrap">
                        借款人姓名：<input type="text" style="width: 150px;" id="borrowerName" name="borrowerName"/>
                    </td>
                    <td style="width: 20%" align="left" nowrap="nowrap">
                        分公司名称：<input type="text" style="width: 155px;" id="branchName" name="branchName"/>
                    </td>
                    <td nowrap="nowrap">
                        <a href="#" class="easyui-linkbutton" onclick="searchLegalProceedings();">搜索</a>
                        <a href="#" class="easyui-linkbutton" onclick="clearLegalProceedings();">清空</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <%--easyui-datagrid--%>
    <div class="easyui-tabs" style="padding: 5px;">
        <div title="诉讼列表" id="bscDiv">
            <table id="bsc" style="border: 0px"></table>
        </div>
    </div>

</div>

<%--诉讼记录--%>
<div id="registerInquireViewDiv" style="padding: 0px;">
    <iframe id="registerInquireViewIframe" src="" scrolling="yes" frameborder="0"
            style="width: 100%; height: 100%;"></iframe>
</div>

<%--诉讼跟进--%>
<div id="registerInquireViewDiv_follow" style="padding: 0px;" buttons="#edit_btn">
    <iframe id="registerInquireViewIframe_follow" src="" scrolling="yes" frameborder="0"
            style="width: 100%; height: 100%;"></iframe>
    <div id="edit_btn" class="toolbar">
        <a id="saveBtn" class="easyui-linkbutton" iconCls="icon-ok"
           onclick="saveFollow();" disabled="true">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-undo"
           onclick="cancelFollow();">取消</a>
    </div>
</div>
</body>
</html>





