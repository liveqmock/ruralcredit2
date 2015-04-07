<%--
  Created by IntelliJ IDEA.
  User: v-weizhang
  Date: 2015-1-27
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<td class="myTD">
    <input type="text" name="jan" value="${map_loan[departmentId].jan}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].jan}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
    <input type="hidden" name="departmentId" value="${departmentId}"/>
    <input type="hidden" name="departmentName" value="${departmentName}"/>
</td>
<td class="myTD">
    <input type="text" name="feb" value="${map_loan[departmentId].feb}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].feb}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="mar" value="${map_loan[departmentId].mar}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].mar}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="apr" value="${map_loan[departmentId].apr}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].apr}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/></td>
<td class="myTD">
    <input type="text" name="may" value="${map_loan[departmentId].may}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].may}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="jun" value="${map_loan[departmentId].jun}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].jun}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="jul" value="${map_loan[departmentId].jul}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].jul}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="aug" value="${map_loan[departmentId].aug}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].aug}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="sep" value="${map_loan[departmentId].sep}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].sep}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="oct" value="${map_loan[departmentId].oct}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].oct}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="nov" value="${map_loan[departmentId].nov}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].nov}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>
<td class="myTD">
    <input type="text" name="dec" value="${map_loan[departmentId].dec}" style="width: 100%;"
           class="easyui-numberbox myInputBorder" readonly="readonly"
            <c:if test="${not empty map_loan[departmentId].dec}">
                <c:if test="${! (me eq 'admin')}">
                    disabled
                </c:if>
            </c:if>/>
</td>



