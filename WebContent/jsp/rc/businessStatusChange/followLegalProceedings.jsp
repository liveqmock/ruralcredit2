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
        var creditapplication_id = <%=creditapplication_id%>;
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/businessStatusChange/followLegalProceedings.js"></script>
</head>

<body class="easyui-layout">
<div region="center" id="centerPanel">
    <div id="followText_history">
        <table>
            <tr>
                <td>
                    跟进历史记录：
                </td>
            </tr>
        </table>
    </div>
    <div id="dataGridDiv">
        <table id="inquire_pool_view"></table>
    </div>
    <br/>
    <br/>
    <br/>

    <div id="followText">
        <form id="followForm">
            <table>
                <tr>
                    <td>
                        <font color="red">*</font>
                        跟进记录：
                    </td>
                </tr>
                <tr>
                    <td>
                        <textarea id="follow_log" name="follow_log" cols="200" rows="10" border-color="#9ABBE8"
                                  style="height:120px;width:100%;"
                                  maxlength="200" validType="maxLength[200]"
                                  onkeydown="textCount('follow_log','counter',200);$('#counter').show()"
                                  onkeyup="textCount('follow_log','counter',200);$('#counter').show()"></textarea>
                        <span id="counter">还可以输入200个字</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
