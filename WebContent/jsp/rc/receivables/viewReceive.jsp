	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<div>
		<table id="lookTable" style="width: 100%">
			<tr>
				<th width="80px" align="right">支付方式</th>
				<td>${financeMoney.payWay }</td>
			</tr>
			<tr>
				<th width="80px" align="right">支付银行</th>
				<td>${financeMoney.openBankName }</td>
			</tr>
			<tr>
				<th width="80px" align="right">支付账户名称</th>
				<td>${financeMoney.accountName }</td>
			</tr>
			<tr>
				<th width="80px" align="right">支付账号</th>
				<td>${financeMoney.accountNo }</td>
			</tr>
			<tr>
				<th width="80px" align="right">支付金额</th>
				<td>${financeMoney.amount }</td>
			</tr>
			<tr>
				<th width="80px" align="right">预约日期</th>
				<td>${reserveTime }</td>
			</tr>
			<tr>
				<th width="80px" align="right">备注信息</th>
				<td>${financeMoney.remark }</td>
				
			</tr>
			<tr>
				<th width="80px" align="right">登记人员</th>
				<td>${financeMoney.operatorName }</td>
			</tr>
			<tr>
				<th width="80px" align="right">操作时间</th>
				<td>${operateDate }</td>
			</tr>
			<tr>
				<th width="80px" align="right">确认时间</th>
				<td>${tradeTime }</td>
			</tr>
			<tr>
				<th width="80px" align="right">处理结果</th>
				<td>${financeMoney.returnMsg }</td>
			</tr>
		</table>
	</div>
