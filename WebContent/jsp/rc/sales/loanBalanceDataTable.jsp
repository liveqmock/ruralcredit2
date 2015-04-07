<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="myForm">
	<table width="100%">
		<tr>
			<th width="12%" class="myTH">
				类型
			</th>
			<th class="myTH">
				客户数
			</th>
			<th class="myTH">
				客户数%
			</th>
			<th class="myTH">
				违约金额
			</th>
			<th class="myTH">
				违约金额%
			</th>
			<th class="myTH">
				贷款余额
			</th>
			<th class="myTH">
				贷款余额%
			</th>
			<th width="12%" class="myTH">
				描述
			</th>
		</tr>
		<tr>
			<th class="myTH">
				不违约
				<input name="loanTableDatas[0].type" value="不违约" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[0].customerCount" value="${loanTableDatas[0].customerCount}" type="hidden" />
				${loanTableDatas[0].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[0].customerCountRate" value="${loanTableDatas[0].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[0].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[0].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[0].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[0].defaultValue" value="${loanTableDatas[0].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[0].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[0].defaultValueRate" value="${loanTableDatas[0].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[0].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[0].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[0].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[0].loanBalance" value="${loanTableDatas[0].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[0].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[0].loanBalanceRate" value="${loanTableDatas[0].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[0].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[0].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[0].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				不违约
				<input name="loanTableDatas[0].description" value="不违约" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				1-7DPD
				<input name="loanTableDatas[1].type" value="1-7DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[1].customerCount" value="${loanTableDatas[1].customerCount}" type="hidden" />
				${loanTableDatas[1].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[1].customerCountRate" value="${loanTableDatas[1].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[1].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[1].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[1].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[1].defaultValue" value="${loanTableDatas[1].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[1].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[1].defaultValueRate" value="${loanTableDatas[1].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[1].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[1].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[1].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[1].loanBalance" value="${loanTableDatas[1].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[1].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[1].loanBalanceRate" value="${loanTableDatas[1].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[1].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[1].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[1].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约1~7天
				<input name="loanTableDatas[1].description" value="违约1~7天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				8-15DPD
				<input name="loanTableDatas[2].type" value="8-15DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[2].customerCount" value="${loanTableDatas[2].customerCount}" type="hidden" />
				${loanTableDatas[2].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[2].customerCountRate" value="${loanTableDatas[2].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[2].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[2].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[2].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[2].defaultValue" value="${loanTableDatas[2].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[2].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[2].defaultValueRate" value="${loanTableDatas[2].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[2].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[2].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[2].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[2].loanBalance" value="${loanTableDatas[2].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[2].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[2].loanBalanceRate" value="${loanTableDatas[2].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[2].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[2].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[2].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约8~15天
				<input name="loanTableDatas[2].description" value="违约8~15天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				16-30DPD
				<input name="loanTableDatas[3].type" value="16-30DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[3].customerCount" value="${loanTableDatas[3].customerCount}" type="hidden" />
				${loanTableDatas[3].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[3].customerCountRate" value="${loanTableDatas[3].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[3].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[3].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[3].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[3].defaultValue" value="${loanTableDatas[3].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[3].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[3].defaultValueRate" value="${loanTableDatas[3].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[3].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[3].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[3].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[3].loanBalance" value="${loanTableDatas[3].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[3].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[3].loanBalanceRate" value="${loanTableDatas[3].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[3].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[3].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[3].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约16~30天
				<input name="loanTableDatas[3].description" value="违约16~30天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				31-60DPD
				<input name="loanTableDatas[4].type" value="31-60DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[4].customerCount" value="${loanTableDatas[4].customerCount}" type="hidden" />
				${loanTableDatas[4].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[4].customerCountRate" value="${loanTableDatas[4].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[4].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[4].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[4].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[4].defaultValue" value="${loanTableDatas[4].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[4].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[4].defaultValueRate" value="${loanTableDatas[4].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[4].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[4].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[4].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[4].loanBalance" value="${loanTableDatas[4].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[4].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[4].loanBalanceRate" value="${loanTableDatas[4].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[4].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[4].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[4].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约31~60天
				<input name="loanTableDatas[4].description" value="违约31~60天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				61-90DPD
				<input name="loanTableDatas[5].type" value="61-90DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[5].customerCount" value="${loanTableDatas[5].customerCount}" type="hidden" />
				${loanTableDatas[5].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[5].customerCountRate" value="${loanTableDatas[5].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[5].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[5].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[5].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[5].defaultValue" value="${loanTableDatas[5].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[5].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[5].defaultValueRate" value="${loanTableDatas[5].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[5].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[5].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[5].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[5].loanBalance" value="${loanTableDatas[5].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[5].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[5].loanBalanceRate" value="${loanTableDatas[5].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[5].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[5].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[5].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约61~90天
				<input name="loanTableDatas[5].description" value="违约61~90天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				91-180DPD
				<input name="loanTableDatas[6].type" value="91-180DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[6].customerCount" value="${loanTableDatas[6].customerCount}" type="hidden" />
				${loanTableDatas[6].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[6].customerCountRate" value="${loanTableDatas[6].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[6].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[6].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[6].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[6].defaultValue" value="${loanTableDatas[6].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[6].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[6].defaultValueRate" value="${loanTableDatas[6].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[6].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[6].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[6].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[6].loanBalance" value="${loanTableDatas[6].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[6].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[6].loanBalanceRate" value="${loanTableDatas[6].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[6].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[6].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[6].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约91~180天
				<input name="loanTableDatas[6].description" value="违约91~180天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				181-365DPD
				<input name="loanTableDatas[7].type" value="181-365DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[7].customerCount" value="${loanTableDatas[7].customerCount}" type="hidden" />
				${loanTableDatas[7].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[7].customerCountRate" value="${loanTableDatas[7].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[7].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[7].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[7].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[7].defaultValue" value="${loanTableDatas[7].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[7].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[7].defaultValueRate" value="${loanTableDatas[7].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[7].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[7].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[7].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[7].loanBalance" value="${loanTableDatas[7].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[7].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[7].loanBalanceRate" value="${loanTableDatas[7].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[7].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[7].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[7].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约181~365天
				<input name="loanTableDatas[7].description" value="违约181~365天" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				366+DPD
				<input name="loanTableDatas[8].type" value="366+DPD" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[8].customerCount" value="${loanTableDatas[8].customerCount}" type="hidden" />
				${loanTableDatas[8].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[8].customerCountRate" value="${loanTableDatas[8].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[8].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[8].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[8].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[8].defaultValue" value="${loanTableDatas[8].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[8].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[8].defaultValueRate" value="${loanTableDatas[8].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[8].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[8].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[8].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[8].loanBalance" value="${loanTableDatas[8].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[8].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[8].loanBalanceRate" value="${loanTableDatas[8].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[8].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[8].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[8].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				违约366天以上
				<input name="loanTableDatas[8].description" value="违约366天以上" type="hidden" />
			</th>
		</tr>
		<tr>
			<th class="myTH">
				汇总
				<input name="loanTableDatas[9].type" value="汇总" type="hidden" />
			</th>
			<td class="myTD">
				<input name="loanTableDatas[9].customerCount" value="${loanTableDatas[9].customerCount}" type="hidden" />
				${loanTableDatas[9].customerCount}
			</td>
			<td class="myTD">
				<input name="loanTableDatas[9].customerCountRate" value="${loanTableDatas[9].customerCountRate*100}" type="hidden" />
				<input name="loanTableDatas[9].customerCountRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[9].customerCountRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[9].customerCountRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[9].defaultValue" value="${loanTableDatas[9].defaultValue*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[9].defaultValue*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[9].defaultValueRate" value="${loanTableDatas[9].defaultValueRate*100}" type="hidden" />
				<input name="loanTableDatas[9].defaultValueRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[9].defaultValueRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[9].defaultValueRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<td class="myTD">
				<input name="loanTableDatas[9].loanBalance" value="${loanTableDatas[9].loanBalance*1}" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[9].loanBalance*1}" pattern="#,##0.00#" />
			</td>
			<td class="myTD">
				<input name="loanTableDatas[9].loanBalanceRate" value="${loanTableDatas[9].loanBalanceRate*100}" type="hidden" />
				<input name="loanTableDatas[9].loanBalanceRateShow" value="<fmt:formatNumber type="number" value="${loanTableDatas[9].loanBalanceRate*100}" pattern="#,##0.00#" />%" type="hidden" />
				<fmt:formatNumber type="number" value="${loanTableDatas[9].loanBalanceRate*100}" pattern="#,##0.00#" />
				%
			</td>
			<th class="myTH">
				汇总
				<input name="loanTableDatas[9].description" value="汇总" type="hidden" />
			</th>
		</tr>
	</table>
</form>