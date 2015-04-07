<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<style>
	<!--
		.hover{
			background-color:#F0F031;
		}
	-->
	</style>
			<table id="lookTable" style="width: 100%">
				<tr>
					<th width="80px" align="right">
						汇入银行
					</th>
					<td>
						${financeMoney.bankId }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						汇入账户名称
					</th>
					<td>
						${financeMoney.accountName }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						汇入账号
					</th>
					<td>
						${financeMoney.accountNo }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						支付方式
					</th>
					<td>
						${financeMoney.payWay }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						付款金额
					</th>
					<td>
						${financeMoney.amount }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						付款预约时间
					</th>
					<td>
						${reserveTime }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						备注信息
					</th>
					<td>
						${financeMoney.remark }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						处理结果
					</th>
					<td>
						${financeMoney.returnMsg }
					</td>
				</tr>
				<tr>
					<th width="80px" align="right">
						财务打款时间
					</th>
					<td>
						${tradeTime }
					</td>
				</tr>
			</table>
