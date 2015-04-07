<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<title>放款登记查看</title>
	</head>
	<body>
		<div>
			<table id="focusTable" fit="true" style="width: 100%;" align="center">
				<tr align="center" width="800">
					<td width="100">
						&nbsp;
					</td>
					<td align="center" width="600">
						<span style="font-size: 25px; font-weight: 700; color: #4B0082;">放款登记信息表</span>
					</td>
					<td width="100">
						<c:if test="${showButton==1}">
							<sec:authorize ifAnyGranted="${control.accountInformation_apply}">		
							<a id="save" class="easyui-linkbutton" plain="false" onclick="openRegistrationDialog(${applicationDENGJI})">变更银行卡信息</a>
							</sec:authorize>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
				</tr>
			</table>
			<table class="advisetable" width="70%" align="center">
				<tr>
					<td align="right" style="width: 120px;">
						放款日期：
					</td>
					<td>
						<fmt:formatDate value='${groupLoanRegist.loanTime}' pattern='yyyy-MM-dd' />
					</td>
					<td align="right" style="width: 100px;">
						协议编号：
					</td>
					<td>
						${groupLoanRegist.protocolID}
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 100px;">
						确认人：
					</td>
					<td>
						${groupLoanRegist.loanPerson }
					</td>
					<td align="right" style="width: 100px;">
						确认时间：
					</td>
					<td>
						<fmt:formatDate value='${ groupLoanRegist.loanConfirmTime}' pattern='yyyy-MM-dd' />
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 100px;">
						业务单号：
					</td>
					<td>
						${groupLoanRegist.groupNumber }
					</td>
					<td align="right" style="width: 100px;">
						客户经理：
					</td>
					<td>
						${groupLoanRegist.loanOfficerName }
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 100px;">
						放款明细：
					</td>
					<td colspan="3" style="padding: 0px;">
						<table class="advisetable" style="width: 100%; height: 100%;">
							<tr>
								<td>
									借款人
								</td>
								<td>
									放款金额
								</td>
								<td>
									实发金额
								</td>
							</tr>
							<tr>
								<td>
									${loanRegists.borrowerName }
								</td>
								<td>
									${loanRegists.loanAmount }
								</td>
								<td>
									${loanRegists.realAmount }
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 100px;">
						历史备注：
					</td>
					<td colspan="3" style="padding: 0px;">
						<table class="advisetable" style="width: 100%; height: 100%; border: thin;">
							<tr>
								<td>
									操作人
								</td>
								<td>
									操作时间
								</td>
								<td>
									备注和意见
								</td>
							</tr>
							<c:forEach items='${groupLoanRegists}' var="glr">
								<tr>
									<td>
										${glr.loanPerson}
									</td>
									<td>
										<fmt:formatDate value='${glr.loanConfirmTime}' pattern='yyyy-MM-dd' />
									</td>
									<td>
										${glr.remarkComment}
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 100px;">
						付款方式(
						<c:choose>
							<c:when test="${accountInfo.accountType==0}">
		    			公司
		    		</c:when>
							<c:when test="${accountInfo.accountType==1 }">
		    			个人
		    		</c:when>
						</c:choose>
						)：
					</td>
					<td colspan="3" style="padding: 0px;">
						<table class="advisetable" style="width: 100%; height: 100%; border: thin;">
							<tr>
								<td>
									省：
								</td>
								<td>
									${accountInfo.sheng}
								</td>
								<td>
									市：
								</td>
								<td>
									${accountInfo.shi}
								</td>
								<td>
									区：
								</td>
								<td>
									${accountInfo.qu}
								</td>
							</tr>
							<tr>
								<td>
									所在行地区号：
								</td>
								<td>
									${accountInfo.bankPrefectureNum}
								</td>
								<td>
									账户名：
								</td>
								<td>
									${accountInfo.accountName}
								</td>
								<td>
									支付行号：
								</td>
								<td>
									${accountInfo.payBranchno}
								</td>
							</tr>
							<tr>
								<td>
									开户行：
								</td>
								<td>
									${accountInfo.bankName}
								</td>
								<td>
									账号：
								</td>
								<td>
									${accountInfo.account}
								</td>
								<td>
									卡折类型：
								</td>
								<td>
									<c:choose>
										<c:when test="${accountInfo.cardFlag==0}">
		    			卡
		    		</c:when>
										<c:when test="${accountInfo.cardFlag==1 }">
		    			折
		    		</c:when>
									</c:choose>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div id="registrationDialog" style="width: 900px; height: 400px;"></div>
		</div>
	</body>
</html>