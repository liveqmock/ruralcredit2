<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    /*获取 poolId、phoneNumber */
    String poolId = request.getParameter("poolId");
    String phoneNumber = request.getParameter("phoneNumber");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">

    <title>受理咨询-查看详情</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var poolViewDataGrid;
        $(function () {
            poolViewDataGrid = $('#inquire_pool_view').datagrid({
                url: serverName + '/saleInquireController/registerInquireView.do',
                fitColumns: false,
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                queryParams: {consultPoolId:<%= poolId %>, mobilePhone: <%= phoneNumber %>},
                rownumbers: true,
                columns: [
                    [
                        {
                            title: '咨询日期', field: 'REGIST_DATE', width: 100, align: 'left', sortable: true
                        },
                        {
                            title: '客户姓名', field: 'CUSTOMER_NAME', width: 100, align: 'left'
                        },
                        {
                            title: '状态', field: 'ACCEPT_CONSULT_STATE', width: 100, align: 'left'
                        },
                        {
                            title: '性别', field: 'CON_SEX', width: 100, align: 'left'
                        },
                        {
                            title: '年龄', field: 'CON_AGE', width: 100, align: 'left'
                        },
                        {
                            title: '户籍地址', field: 'RESIDENCE_ADDRESS', width: 150, align: 'left', sortable: true
                        },
                        {
                            title: '咨询金额（万元）', field: 'CONSULTMONEY', width: 100, align: 'left', sortable: true
                        },
                        {
                            title: '借款用途', field: 'BORROWING', width: 150, align: 'left', sortable: true
                        },
                        {
                            title: '信息来源', field: 'INFOSRC', width: 100, align: 'left', sortable: true
                        },
                        {
                            title: '咨询方式', field: 'CONSULTWAY', width: 100, align: 'left'
                        },
                        {
                            title: '备注', field: 'MARKET_CONSULT_REMARK', width: 100, align: 'left'
                        },
                        {
                            title: '跟踪状态', field: 'TRACESTATUS', width: 100, align: 'left'
                        },
                        {
                            title: '沟通记录', field: 'COMMUNICATION_RECORD', width: 100, align: 'left'
                        },
                        {
                            title: '退回人', field: 'OPERATOR', width: 100, align: 'left'
                        },
                        {
                            title: '退回原因', field: 'ROLLBACK_REASON', width: 100, align: 'left'
                        },
                        {
                         title: '无效登记状态', field: 'MARKET_CONSULT_STATE', width: 100, align: 'left', formatter: function (value) {
                            	 /*当状态为拒绝、无效名单、未联系上客户时，显示无效登记状态，此处查询数据字典表 rl_codetable-code_key 判断。*/
                                if (value == '拒绝' || value == '无效客户' || value == '未联系上客户') {
                                    return value;
                          }
                        }
                        },
                        {
                            title: '客户标签', field: 'CUSTOMER_TAG_NAME', width: 100, align: 'left'
                        },
                        {
                            title: '咨询次数', field: 'CONSULTTIMES', width: 100, align: 'left'
                        }
                    ]
                ],
                onLoadSuccess: function(){
                    var row = poolViewDataGrid.datagrid('getRows')[0];
                    $('#pName').html(row.CUSTOMER_NAME);
                    $('#pPhone').html(row.MOBILE_PHONE);
                }

            });
        });
        function resizeGrid() {
            poolViewDataGrid.datagrid('resize', {
                height: $('#centerPanel')[0].clientHeight - $('#headerDiv')[0].clientHeight
            });
        }
    </script>
</head>

<body class="easyui-layout" onload="resizeGrid();">
<div region="center" id="centerPanel">
    <%--更改
    <form novalidate="" action="/CustomerConsult/addUpdate.do" id="customerComsultForm">
        <table class="tabfrom" align="center" width="100%">
            <tbody>
            <tr height="30">
                <td align="right" width="20%">登记日期：</td>
                <td width="30%">
                    <fmt:formatDate value="${customerConsultPoolVo.registDate }" pattern="yyyy-MM-dd"/>
                </td>
                <td align="right" width="20%">客户姓名：</td>
                <td width="30%">
                    ${customerConsultPoolVo.customerName }
                </td>
            </tr>
            <tr height="30">
                <td align="right">性别：</td>
                <td>${customerConsultPoolVo.conSexShow }</td>
                <td align="right">年龄：</td>
                <td>${customerConsultPoolVo.conAge }</td>
            </tr>
            <tr height="30">
                <td align="right">城市：</td>
                <td colspan="3">${customerConsultPoolVo.cityAllShow }</td>
            </tr>
            <tr height="30">
                <td align="right">手机：</td>
                <td>${customerConsultPoolVo.mobilePhone }</td>
                <td align="right">固定电话：</td>
                <td>${customerConsultPoolVo.fixedTelephone }</td>
            </tr>
            <tr height="30">
                <td align="right">经营年限：</td>
                <td>${customerConsultPoolVo.businessPeriodShow }</td>
                <td align="right">是否有营业执照：</td>
                <td>${customerConsultPoolVo.isBusinessLicenseShow }</td>
            </tr>
            <tr height="30">
                <td align="right">借款额度：</td>
                <td>${customerConsultPoolVo.borrowAmount }万元</td>
                <td align="right"> 推荐渠道：</td>
                <td>${customerConsultPoolVo.channelShow }</td>
            </tr>
            <tr height="30">
                <td align="right">备注：</td>
                <td colspan="3">${customerConsultPoolVo.marketConsultRemark }</td>
            </tr>
            <c:if test="${customerConsultPoolVo.marketConsultState == '1'}">
                <tr height="30">
                    <td align="right">退回人：</td>
                    <td colspan="3">${customerConsultPoolVo.operatorReturn }</td>
                </tr>
                <tr height="30">
                    <td align="right">退回原因：</td>
                    <td colspan="3">${customerConsultPoolVo.rollbackReason }</td>
                </tr>
            </c:if>
            <c:if test="${customerConsultPoolVo.marketConsultState == '4' || customerConsultPoolVo.marketConsultState == '5'||customerConsultPoolVo.marketConsultState == '6'||customerConsultPoolVo.marketConsultState == '7'}">
                <tr height="30">
                    <td align="right">无效登记状态：</td>
                    <td colspan="3">${customerConsultPoolVo.saleConsultStatusShowUnless }</td>
                </tr>

                <tr height="30">
                    <td align="right">具体反馈结果：</td>
                    <td colspan="3">${customerConsultPoolVo.feedbackResultShow }</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </form>--%>
    <div id="headerDiv">
        <table width="100%">
            <tr>
                <td>
                    当前咨询客户姓名：
                    <span id="pName"></span>
                    &nbsp; &nbsp; &nbsp;
                    联系方式：
                    <span id="pPhone"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--查看受理咨询池详情--%>
    <div id="dataGridDiv">
        <table id="inquire_pool_view"></table>
    </div>
</div>
</body>
</html>
