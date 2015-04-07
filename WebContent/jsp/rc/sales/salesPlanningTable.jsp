<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="repaymentSearchTab" class="easyui-tabs" style="padding: 10px;">
	<div title="放款量（单位/笔）">
		<form id="form1">
			<table width="100%">
				<tr>
					<th width="12%" class="myTH" colspan="2">
						<font style="font-weight: bold; color: red;">${selectYear}放款量</font>
					</th>
					<th class="myTH">
						1月
					</th>
					<th class="myTH">
						2月
					</th>
					<th class="myTH">
						3月
					</th>
					<th class="myTH">
						4月
					</th>
					<th class="myTH">
						5月
					</th>
					<th class="myTH">
						6月
					</th>
					<th class="myTH">
						7月
					</th>
					<th class="myTH">
						8月
					</th>
					<th class="myTH">
						9月
					</th>
					<th class="myTH">
						10月
					</th>
					<th class="myTH">
						11月
					</th>
					<th class="myTH">
						12月
					</th>
				</tr>
				<c:set var="indexStart" value="-1"></c:set><!--定义全球变量 用于后台获取vo的list的索引 -->
				<c:forEach var="first_map" items="${total}">

                    <c:forEach var="sec_map" items="${first_map}">
                        <tr>
                        <td class="myTH" rowspan="${fn:length(sec_map.value)/2+1}">${sec_map.key}</td>
                        <c:forEach items="${sec_map.value}" var="spt" varStatus="sptp" begin="0"
                                   end="${fn:length(sec_map.value)/2-1}">
                              <c:set var="indexStart" value="${indexStart +1}"></c:set>

                            <tr>
                                <th  class="myTH">
							${spt.areaDepartmentName}
							<input name="salesPlanningTables[${indexStart}].areaDepartmentId" type="hidden" value="${spt.areaDepartmentId}" />
							<input name="salesPlanningTables[${indexStart}].areaDepartmentName" type="hidden" value="${spt.areaDepartmentName}" />
							<input name="salesPlanningTables[${indexStart}].type" type="hidden" value="0" />
							<input name="salesPlanningTables[${indexStart}].year" type="hidden" type="hidden" value="${spt.year}" />
							<input name="salesPlanningTables[${indexStart}].value" type="hidden" />
						</th>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].janId" type="hidden" value="${spt.janId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].jan" value="${spt.jan}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].febId" type="hidden" value="${spt.febId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].feb" value="${spt.feb}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].marId" type="hidden" value="${spt.marId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].mar" value="${spt.mar}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].aprId" type="hidden" value="${spt.aprId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].apr" value="${spt.apr}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].mayId" type="hidden" value="${spt.mayId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].may" value="${spt.may}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].junId" type="hidden" value="${spt.junId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].jun" value="${spt.jun}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].julId" type="hidden" value="${spt.julId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].jul" value="${spt.jul}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].augId" type="hidden" value="${spt.augId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].aug" value="${spt.aug}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].sepId" type="hidden" value="${spt.sepId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].sep" value="${spt.sep}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].octId" type="hidden" value="${spt.octId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].oct" value="${spt.oct}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].novId" type="hidden" value="${spt.novId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].nov" value="${spt.nov}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
						<td class="myTD">
							<input name="salesPlanningTables[${indexStart}].decId" type="hidden" value="${spt.decId}" />
							<input type="text" name="salesPlanningTables[${indexStart}].dec" value="${spt.dec}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="0" />
						</td>
                            </tr>

                        </c:forEach>
                        </tr>
                    </c:forEach>
                </c:forEach>
				</table>
		</form>
	</div>
	<sec:authorize ifAnyGranted="${control.salesPlanning_save}">
		<div title="合同金额（单位/万元）">
			<form id="form2">
				<table width="100%">
					<tr>
						<th width="12%" class="myTH" colspan="2">
							<font style="font-weight: bold; color: red;" >${selectYear}合同金额</font>
						</th>
						<th class="myTH">
							1月
						</th>
						<th class="myTH">
							2月
						</th>
						<th class="myTH">
							3月
						</th>
						<th class="myTH">
							4月
						</th>
						<th class="myTH">
							5月
						</th>
						<th class="myTH">
							6月
						</th>
						<th class="myTH">
							7月
						</th>
						<th class="myTH">
							8月
						</th>
						<th class="myTH">
							9月
						</th>
						<th class="myTH">
							10月
						</th>
						<th class="myTH">
							11月
						</th>
						<th class="myTH">
							12月
						</th>
					</tr>
					<c:forEach var="first_map" items="${total}">

                    <c:forEach var="sec_map" items="${first_map}">
                        <tr>
                        <td class="myTH" rowspan="${fn:length(sec_map.value)/2+1}">${sec_map.key}</td>
                        <c:forEach items="${sec_map.value}" var="spt" varStatus="sptp" begin="${fn:length(sec_map.value)/2}"
                                   end="${fn:length(sec_map.value)-1}">
							<c:set var="indexStart" value="${indexStart +1}"></c:set>
                            <tr>
                            	<th class="myTH">
								${spt.areaDepartmentName}
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].areaDepartmentId" type="hidden" value="${spt.areaDepartmentId}" />
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].areaDepartmentName" type="hidden" value="${spt.areaDepartmentName}" />
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].type" type="hidden" value="1" />
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].year" type="hidden" type="hidden" value="${spt.year}" />
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].value" type="hidden" />
							</th>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].janId" value="${spt.janId}" type="hidden" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].jan" value="${spt.jan}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].febId" type="hidden" value="${spt.febId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].feb" value="${spt.feb}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].marId" type="hidden" value="${spt.marId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].mar" value="${spt.mar}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].aprId" type="hidden" value="${spt.aprId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].apr" value="${spt.apr}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].mayId" type="hidden" value="${spt.mayId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].may" value="${spt.may}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].junId" type="hidden" value="${spt.junId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].jun" value="${spt.jun}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].julId" type="hidden" value="${spt.julId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].jul" value="${spt.jul}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].augId" type="hidden" value="${spt.augId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].aug" value="${spt.aug}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].sepId" type="hidden" value="${spt.sepId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].sep" value="${spt.sep}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].octId" type="hidden" value="${spt.octId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].oct" value="${spt.oct}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].novId" type="hidden" value="${spt.novId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].nov" value="${spt.nov}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
							<td class="myTD">
								<input name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].decId" type="hidden" value="${spt.decId}" />
								<input type="text" name="salesPlanningTables[<fmt:formatNumber type="number" value="${indexStart}" pattern="#"/>].dec" value="${spt.dec}" style="width: 95%;" readonly="readonly" class="easyui-numberbox myInputBoder" precision="2" />
							</td>
                      		</tr>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                </c:forEach>
					</table>
			</form>
		</div>
	</sec:authorize>
</div>

