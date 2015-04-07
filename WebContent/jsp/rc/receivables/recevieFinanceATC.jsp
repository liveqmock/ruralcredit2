<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="easyui-panel">
		<h3>${alert }</h3>
		<c:if test="${alert==null }">
			<form id="receiveAgin" action="">
				<input type="hidden" id="receiveRecordIds" name="receiveRecordIds" value="${receiveRecordIds }"/>
				<input type="hidden" id="severDate" name="severDate" value="${severDate }"/>
				<table>
					<tr style="line-height: 5px">
						<td colspan="2" align="left">
							<font style="color: red; font-size: 11;">
								(注：每日22:30-24:00不能进行预约)
							</font>
						</td>
					</tr>
					<tr>
						<th align="right" nowrap="nowrap">支付银行：</th>
						<td align="left">${accountInfo }</td>
					</tr>
					<tr>
						<th align="right" nowrap="nowrap">支付金额：</th>
						<td align="left">${receivedRecordAmount }</td>
					</tr>
					<%--2015-01-15 预约划扣修改为实时划扣
					<tr>
						<th align="right">预约划扣日期：</th>
						<td align="left"><input id="receivedTime" name="receivedTime" type="text" style="width: 150px;" class="easyui-datetimebox" editable="false"/></td>
					</tr>--%>
					<tr>
						<th align="right" nowrap="nowrap">备注：</th>
						<td align="left"><textarea name="remark" class="ipttext" maxlength="70" cols="70" rows="5"></textarea></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	<c:if test="${alert==null }">
		<table>
			<tr>
				<th>借款人</th>
				<th>当期实收金额</th>
			</tr>
			<c:forEach items="${receivedRecordList }" var ="r">
				<tr>
					<td>${r.borrowerName }</td>
					<td>${r.receivedAmount }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
