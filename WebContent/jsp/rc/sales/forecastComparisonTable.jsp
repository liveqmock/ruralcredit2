<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="easyui-datagrid" fitColumns="true">
	<thead>
		<tr>
			<th width="100" field="loanRealAndPlannings.dateTime">
				日期
			</th>
			<th width="100" field="loanRealAndPlannings.rCount">
				实际放款量
			</th>
			<th width="100" field="loanRealAndPlannings.rAmount">
				实际合同金额
			</th>
			<th width="100" field="loanRealAndPlannings.pCount">
				计划放款量
			</th>
			<th width="100" field="loanRealAndPlannings.pAmount">
				计划合同金额
			</th>
			<th width="100" field="loanRealAndPlannings.countRatio">
				放款量比例
			</th>
			<th width="100" field="loanRealAndPlannings.amountRatio">
				合同金额比例
			</th>
			<th width="100" field="loanRealAndPlannings.countRatioTotal">
				累计放款量比例
			</th>
			<th width="100" field="loanRealAndPlannings.amountRatioTotal">
				累计合同金额
			</th>
		</tr>
	</thead>
	<tbody align="right">
		<c:forEach items="${loanRealAndPlannings}" var="lrp">
			<tr>
				<td>

					<fmt:formatDate value="${lrp.dateTime}" pattern="yyyy年MM月" />
				</td>
				<td>
					${lrp.rCount}
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lrp.rAmount}" pattern="#,#00.00#" />
				</td>
				<td>
					${lrp.pCount}
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lrp.pAmount}" pattern="#,#00.00#" />
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lrp.countRatio*100}" pattern="#0.00#" />
					%
				</td>
				<td>

					<fmt:formatNumber type="number" value="${lrp.amountRatio*100}" pattern="#0.00#" />
					%
				</td>
				<td>

					<fmt:formatNumber type="number" value="${lrp.countRatioTotal*100}" pattern="#0.00#" />
					%
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lrp.amountRatioTotal*100}" pattern="#0.00#" />
					%
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
