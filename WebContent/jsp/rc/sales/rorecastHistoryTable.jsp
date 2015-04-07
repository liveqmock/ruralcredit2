<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="easyui-tabs" style="padding: 10px;">
	<div title="放款量（单位/笔）">
		<table width="100%">
			<tr>
				<th width="20%" class="myTH">
					预测月份
				</th>
				<th class="myTH" colspan="3">
					预测数据
				</th>
				<th width="20%" class="myTH">
					操作
				</th>
			</tr>
			<c:forEach items="${rollingForecastHistories0}" var="rfh0" varStatus="r0">
				<tr>
					<th width="20%" class="myTH" rowspan="2">
						${rfh0.thisYearAndMonth}
						<input id="input0${r0.count-1}year" value="${rfh0.year}" type="hidden" />
						<input id="input0${r0.count-1}month" value="${rfh0.month}" type="hidden" />
						<input id="input0${r0.count-1}type" value="${rfh0.type}" type="hidden" />
					</th>
					<th width="20%" class="myTH">
						${rfh0.firstMonths}
					</th>
					<th width="20%" class="myTH">
						${rfh0.secondMonths}
					</th>
					<th width="20%" class="myTH">
						${rfh0.thirdMonths}
					</th>
					<td rowspan="2" width="20%" class="myTD" align="center">
						<a id="ad0${r0.count-1}" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="showDetail(this.id);">查看明细</a>
						<sec:authorize ifAnyGranted="${control.rollingForecast_export}">
						&nbsp;&nbsp;
						<a id="aed0${r0.count-1}" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="exportExcelDetail(this.id);">导出</a>
						</sec:authorize>
					</td>
				</tr>
				<tr>
					<td width="20%" class="myTD">
						<input id="input0${r0.count-1}editfirst" type="text" name="input0${r0.count-1}editfirstname" value="${rfh0.firstMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
					</td>
					<td width="20%" class="myTD">
						<input id="input0${r0.count-1}editfirst" type="text" name="input0${r0.count-1}editfirstname" value="${rfh0.secondMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
					</td>
					<td width="20%" class="myTD">
						<input id="input0${r0.count-1}editfirst" type="text" name="input0${r0.count-1}editfirstname" value="${rfh0.thirdMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<sec:authorize ifAnyGranted="${control.rollingForecast_amountShow}">
		<div title="合同金额（单位/万元）">
			<table width="100%">
				<tr>
					<th width="20%" class="myTH">
						预测月份
					</th>
					<th class="myTH" colspan="3">
						预测数据
					</th>
					<th width="20%" class="myTH">
						操作
					</th>
				</tr>
				<c:forEach items="${rollingForecastHistories1}" var="rfh1" varStatus="r1">
					<tr>
						<th width="20%" class="myTH" rowspan="2">
							${rfh1.thisYearAndMonth}
							<input id="input1${r1.count-1}year" value="${rfh1.year}" type="hidden" />
							<input id="input1${r1.count-1}month" value="${rfh1.month}" type="hidden" />
							<input id="input1${r1.count-1}type" value="${rfh1.type}" type="hidden" />
						</th>
						<th width="20%" class="myTH">
							${rfh1.firstMonths}
						</th>
						<th width="20%" class="myTH">
							${rfh1.secondMonths}
						</th>
						<th width="20%" class="myTH">
							${rfh1.thirdMonths}
						</th>
						<td rowspan="2" width="20%" class="myTD" align="center">
							<a id="ad1${r1.count-1}" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="showDetail(this.id);">查看明细</a>
							<sec:authorize ifAnyGranted="${control.rollingForecast_export}">
							&nbsp;&nbsp;
							<a id="aed1${r1.count-1}" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="exportExcelDetail(this.id);">导出</a>
							</sec:authorize>
						</td>
					</tr>
					<tr>
						<td width="20%" class="myTD">
							<input id="input1${r1.count-1}editfirst" type="text" name="input1${r1.count-1}editfirstname" value="${rfh1.firstMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
						</td>
						<td width="20%" class="myTD">
							<input id="input1${r1.count-1}editfirst" type="text" name="input1${r1.count-1}editfirstname" value="${rfh1.secondMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
						</td>
						<td width="20%" class="myTD">
							<input id="input1${r1.count-1}editfirst" type="text" name="input1${r1.count-1}editfirstname" value="${rfh1.thirdMonthTolal}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</sec:authorize>
</div>
