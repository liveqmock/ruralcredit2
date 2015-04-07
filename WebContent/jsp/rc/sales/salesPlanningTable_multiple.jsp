<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
    function configWidthAndColspan(tabId, width, colspan) {
        $('#' + tabId + ' th').first().attr('width', width).attr('colspan', colspan);
    }
</script>

<c:set var="map_loan" value="${map_loan}" scope="request"/>
<c:set var="map_contract" value="${map_contract}" scope="request"/>

<script>
    var me_js = '${me}';
    var from = '${from}';
</script>
<c:if test="${me eq 'admin'}">
    <div id="tab1" title="大区销售计划">
        <div id="sales_tab_region" class="easyui-tabs" fit="true" style="padding: 1px;">
                <%--大区销售计划 apply for admin--%>
            <div id="sales_tab_region_loan" title="放款量（单位/笔）">
                <form>
                    <table style="width: 100%; height: 100%;" cellspacing="0px">
                        <tr style="width: 100%">
                            <th class="myTH">
                                <font style="font-weight: bold; color: red;">${year}年放款量</font>
                            </th>
                            <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                        </tr>
                        <c:forEach items="${regions}" var="region">
                            <tr>
                                <td class="myTH">
                                        ${region.departmentName}
                                </td>
                                <c:set var="departmentName" value="${region.departmentName}" scope="request"/>
                                <c:set var="departmentId" value="${region.departmentId}" scope="request"/>
                                <jsp:include page="salesPlanningTable_common_loan.jsp" flush="true"/>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
            <div id="sales_tab_region_contract" title="合同金额（单位/万元）">
                <form>
                    <table style="width: 100%; height: 100%;" cellspacing="0px">
                        <tr style="width: 100%">
                            <th class="myTH">
                                <font style="font-weight: bold; color: red;">${year}年合同金额</font>
                            </th>
                            <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                        </tr>
                        <c:forEach items="${regions}" var="region">
                            <tr>
                                <td class="myTH">
                                        ${region.departmentName}
                                </td>
                                <c:set var="departmentName" value="${region.departmentName}" scope="request"/>
                                <c:set var="departmentId" value="${region.departmentId}" scope="request"/>
                                <jsp:include page="salesPlanningTable_common.jsp" flush="true"/>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script>
        configWidthAndColspan('sales_tab_region_loan', '10%', 0);
        configWidthAndColspan('sales_tab_region_contract', '10%', 0);
    </script>
</c:if>
<c:if test="${me eq 'admin' or me eq 'regionMgr'}">
    <div id="tab2" title="分中心销售计划">
        <div id="sales_tab_subs" class="easyui-tabs" fit="true" style="padding: 1px;">
            <c:choose>
                <c:when test="${me eq 'admin'}">
                    <div id="sales_tab_subs_loan" title="放款量（单位/笔）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年放款量</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--分中心销售计划 apply for admin--%>
                                <c:forEach items="${regions}" var="region">
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <tr>
                                            <c:if test="${status.count == 1}">
                                                <td class="myTH"
                                                    rowspan="${fn:length(departmentMap[region.departmentId].departmentIds)}"
                                                    width="7%">
                                                        ${region.departmentName}
                                                </td>
                                            </c:if>
                                            <td class="myTH" width="10%">
                                                    ${departmentMap[entity].departmentName}
                                            </td>
                                            <c:set var="departmentName" value="${departmentMap[entity].departmentName}"
                                                   scope="request"/>
                                            <c:set var="departmentId" value="${departmentMap[entity].departmentId}"
                                                   scope="request"/>
                                            <jsp:include page="salesPlanningTable_common_loan.jsp" flush="true"/>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                    <div id="sales_tab_subs_contract" title="合同金额（单位/万元）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年合同金额</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--分中心销售计划 apply for admin--%>
                                <c:forEach items="${regions}" var="region">
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <tr>
                                            <c:if test="${status.count == 1}">
                                                <td class="myTH"
                                                    rowspan="${fn:length(departmentMap[region.departmentId].departmentIds)}"
                                                    width="7%">
                                                        ${region.departmentName}
                                                </td>
                                            </c:if>
                                            <td class="myTH" width="10%">
                                                    ${departmentMap[entity].departmentName}
                                            </td>
                                            <c:set var="departmentName" value="${departmentMap[entity].departmentName}"
                                                   scope="request"/>
                                            <c:set var="departmentId" value="${departmentMap[entity].departmentId}"
                                                   scope="request"/>
                                            <jsp:include page="salesPlanningTable_common.jsp" flush="true"/>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                </c:when>
                <%--分中心销售计划 apply for counterparts--%>
                <c:when test="${me eq 'regionMgr'}">
                    <div id="sales_tab_subs_loan_counterparts" title="放款量（单位/笔）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年放款量</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                <c:forEach items="${subs}" var="sub">
                                    <tr>
                                        <td class="myTH" width="7%">
                                                ${sub.departmentName}
                                        </td>
                                        <c:set var="departmentName" value="${sub.departmentName}" scope="request"/>
                                        <c:set var="departmentId" value="${sub.departmentId}" scope="request"/>
                                        <jsp:include page="salesPlanningTable_common_loan.jsp" flush="true"/>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                    <div id="sales_tab_subs_contract_counterparts" title="合同金额（单位/万元）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年合同金额</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                <c:forEach items="${subs}" var="sub">
                                    <tr>
                                        <td class="myTH" width="7%">
                                                ${sub.departmentName}
                                        </td>
                                        <c:set var="departmentName" value="${sub.departmentName}" scope="request"/>
                                        <c:set var="departmentId" value="${sub.departmentId}" scope="request"/>
                                        <jsp:include page="salesPlanningTable_common.jsp" flush="true"/>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                </c:when>
            </c:choose>
            </table>
            </form>
        </div>
    </div>
    <script>
        configWidthAndColspan('sales_tab_subs_loan', '15%', 2);
        configWidthAndColspan('sales_tab_subs_contract', '15%', 2);
        configWidthAndColspan('sales_tab_subs_loan_counterparts', '10%', 1);
        configWidthAndColspan('sales_tab_subs_contract_counterparts', '10%', 1);
    </script>
</c:if>
<c:if test="${me eq 'admin' or me eq 'cityMgr'}">
    <div id="tab3" title="营业部销售计划">
        <div id="sales_tab_depart" class="easyui-tabs" fit="true" style="padding: 1px;">
            <c:choose>
                <c:when test="${me eq 'admin'}">
                    <div id="sales_tab_depart_loan" title="放款量（单位/笔）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年放款量</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--营业部销售计划 apply for admin--%>
                                <c:forEach items="${regions}" var="region">
                                    <c:set var="regionMerge" value="0"></c:set>
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <c:forEach items="${departmentMap[entity].departmentIds}" var="_entity"
                                                   varStatus="_status">
                                            <c:if test="${_status.last}">
                                                <c:set var="regionMerge"
                                                       value="${regionMerge + _status.count}"></c:set>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <c:forEach items="${departmentMap[entity].departmentIds}" var="_entity"
                                                   varStatus="_status">
                                            <tr>
                                                <c:if test="${status.count == 1 && _status.count == 1}">
                                                    <td class="myTH" width="10%" rowspan="${regionMerge}">
                                                            ${region.departmentName}
                                                    </td>
                                                </c:if>
                                                <c:if test="${_status.count == 1}">
                                                    <td class="myTH" width="10%"
                                                        rowspan="${fn:length(departmentMap[entity].departmentIds)}">
                                                            ${departmentMap[entity].departmentName}
                                                    </td>
                                                </c:if>
                                                <td class="myTH" width="13%">
                                                        ${departmentMap[_entity].departmentName}
                                                </td>
                                                <c:set var="departmentName"
                                                       value="${departmentMap[_entity].departmentName}"
                                                       scope="request"/>
                                                <c:set var="departmentId" value="${departmentMap[_entity].departmentId}"
                                                       scope="request"/>
                                                <jsp:include page="salesPlanningTable_common_loan.jsp" flush="true"/>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                    <div id="sales_tab_depart_contract" title="合同金额（单位/万元）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年合同金额</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--营业部销售计划 apply for admin--%>
                                <c:forEach items="${regions}" var="region">
                                    <c:set var="regionMerge" value="0"></c:set>
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <c:forEach items="${departmentMap[entity].departmentIds}" var="_entity"
                                                   varStatus="_status">
                                            <c:if test="${_status.last}">
                                                <c:set var="regionMerge"
                                                       value="${regionMerge + _status.count}"></c:set>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    <c:forEach items="${departmentMap[region.departmentId].departmentIds}"
                                               var="entity"
                                               varStatus="status">
                                        <c:forEach items="${departmentMap[entity].departmentIds}" var="_entity"
                                                   varStatus="_status">
                                            <tr>
                                                <c:if test="${status.count == 1 && _status.count == 1}">
                                                    <td class="myTH" width="10%" rowspan="${regionMerge}">
                                                            ${region.departmentName}
                                                    </td>
                                                </c:if>
                                                <c:if test="${_status.count == 1}">
                                                    <td class="myTH" width="10%"
                                                        rowspan="${fn:length(departmentMap[entity].departmentIds)}">
                                                            ${departmentMap[entity].departmentName}
                                                    </td>
                                                </c:if>
                                                <td class="myTH" width="13%">
                                                        ${departmentMap[_entity].departmentName}
                                                </td>
                                                <c:set var="departmentName"
                                                       value="${departmentMap[_entity].departmentName}"
                                                       scope="request"/>
                                                <c:set var="departmentId" value="${departmentMap[_entity].departmentId}"
                                                       scope="request"/>
                                                <jsp:include page="salesPlanningTable_common.jsp" flush="true"/>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                </c:when>
                <c:when test="${me eq 'cityMgr'}">
                    <div id="sales_tab_depart_loan_counterparts" title="放款量（单位/笔）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年放款量</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--营业部销售计划 apply for counterparts--%>
                                <c:forEach items="${departs}" var="depart">
                                    <tr>
                                        <td class="myTH" width="10%">
                                                ${depart.departmentName}
                                        </td>
                                        <c:set var="departmentName" value="${depart.departmentName}" scope="request"/>
                                        <c:set var="departmentId" value="${depart.departmentId}" scope="request"/>
                                        <jsp:include page="salesPlanningTable_common_loan.jsp" flush="true"/>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                    <div id="sales_tab_depart_contract_counterparts" title="合同金额（单位/万元）">
                        <form>
                            <table style="width: 100%; height: 100%;" cellspacing="0px">
                                <tr style="width: 100%">
                                    <th class="myTH">
                                        <font style="font-weight: bold; color: red;">${year}年合同金额</font>
                                    </th>
                                    <jsp:include page="salesPlanningTable_common_month.jsp" flush="true"/>
                                </tr>
                                    <%--营业部销售计划 apply for counterparts--%>
                                <c:forEach items="${departs}" var="depart">
                                    <tr>
                                        <td class="myTH" width="10%">
                                                ${depart.departmentName}
                                        </td>
                                        <c:set var="departmentName" value="${depart.departmentName}" scope="request"/>
                                        <c:set var="departmentId" value="${depart.departmentId}" scope="request"/>
                                        <jsp:include page="salesPlanningTable_common.jsp" flush="true"/>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
    <script>
        configWidthAndColspan('sales_tab_depart_loan', '15%', 3);
        configWidthAndColspan('sales_tab_depart_contract', '15%', 3);
        configWidthAndColspan('sales_tab_depart_loan_counterparts', '15%', 0);
        configWidthAndColspan('sales_tab_depart_contract_counterparts', '15%', 0);
    </script>
</c:if>