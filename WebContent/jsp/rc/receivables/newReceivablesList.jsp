<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ids = request.getParameter("ids");
	String seperator = "?";
	StringBuffer conditions=new StringBuffer();
	if(CommonUtil.isNotEmpty(ids)){
		conditions.append(seperator).append("ids=").append(ids);
		seperator="&";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'newReceivablesList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript">var serverName = "<%=path%>";</script>
	-->
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>

		<script type="text/javascript"
			src="<%=basePath%>/scripts/receive/newReceivablesList.js"></script>
		<script type="text/javascript">var serverName = "<%=path%>";</script>
		<%--<script type="text/javascript" src="<%=basePath%>/scripts/receive/receivablesList.js"></script>
		--%>
		<script type="text/javascript">
		var conditions="";
		<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
			conditions="<%=conditions.toString()%>";
		<%}%>
		
	function registrationOperation(value, rowData, rowIndex) {
		var paramId = rowData.creditapplicationId;
		var businessNumber = rowData.groupNumber;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.receivablesList_receiveAndPre}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='jump(" + paramId + ",\"" + businessNumber + "\"," + 0
				+ ");'><font color='blue'>还款登记</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:void(0);' onclick='jump(" + paramId + ",\"" + businessNumber + "\"," + 1
				+ ");'><font color='#9932cc'>一次性还款登记</font></a>";
		</sec:authorize>
		return links;
	}
	function changeOperation(value, rowData, rowIndex) {
		var paramId = rowData.creditapplicationId;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.receivablesList_change}">
		if (rowData.defaultReturnmentWay == "0") {
			links = "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='change(" + paramId + "," + rowData.defaultReturnmentWay + ");'><font color='green'>默认方式变为现金手动</font></a>";
		} else if (rowData.defaultReturnmentWay == "1") {
			links = "&nbsp;&nbsp;<a href='javascript:void(0);' onclick='change(" + paramId + "," + rowData.defaultReturnmentWay + ");'><font color='green'>默认方式变为个人卡自动</font></a>";
		} else {
			links = "Null";
		}
		</sec:authorize>
		return links;
	}
	function returnPalnView(value, rowData, rowIndex) {
		var paramId = rowData.creditapplicationId;
		var paramNumber = rowData.groupNumber;
		var links = "&nbsp;&nbsp;<font color='red'>无操作权限</font>";
		<sec:authorize ifAnyGranted="${control.newReceivablesList_returnPalnView}">
		links = "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='addReturnPlanTab(" + paramId + "," + "\"" + paramNumber + "\"" + ")'><font color='#9932cc'>查看还款计划</font></a>";
		</sec:authorize>
		<sec:authorize ifAnyGranted="${control.newReceivablesList_huifang}">
		links = links+"&nbsp;&nbsp;<a href='javascript:void(0)' onclick='returnVisit(" + paramId + ")'>回访</a>";
		</sec:authorize>
		return links;
	}
	
	function returnVisit(creditApplicationId){
		parent.addTabFun({
			src:"<%=basePath%>CustomerReturnVisitController/editReturnVisit.do?creditapplicationId="+creditApplicationId,
			title:"编辑回访"
		});
	}
</script>
	</head>

	<body id="ooos" class="easyui-layout">
		<div region="center">
			<div id="repaymentSearchTab" class="easyui-tabs"
				style="padding: 10px;" tools="#toolss">
				<div title="模糊查询">
					<br />
					<table>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模糊查询条件：
							</td>
							<td>
								<input id="fuzzy" name="fuzzy" type="text" style="width: 150px;" />
							</td>
							<td>
								<a class="easyui-linkbutton" onclick="doSearch(0)">搜索</a>
							</td>
							<td>
								<a class="easyui-linkbutton" onclick="doClear(0)">清空</a>
							</td>
							<td>
								<font color="red">（可输入业务单号、客户经理姓名或借款人姓不完整片段进行搜索）</font>
							</td>
						</tr>

					</table>
					<br />
				</div>
				<div title="高级查询">
					<form id="searchform" method="post">
						<br />
						<table width="100%">
							<tr>
								<td align="right">
									业务单号：
								</td>
								<td>
									<input id="businessNumber" name="businessNumber" type="text" size="20" style="width:122px"/>
								</td>
								<td align="right">
									借款人姓名：
								</td>
								<td>
									<input id="borrowerName" name="borrowerName" type="text" size="20" style="width:122px"/>
								</td>
								<td align="right">
									业务类型：
								</td>
								<td>
									<input id="businessType" name="businessType" type="text"
										style="width: 122px;" />
								</td>
								<td align="right">
									还款方式：
								</td>
								<td>
									<input id="defaultReturnmentWay" name="defaultReturnmentWay"
										type="text" style="width: 122px;" />
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>

							<tr>
								<td align="right">
									起始日期：
								</td>
								<td>
									<input id="startDate" name="startDate" type="text"
										class="easyui-datebox" editable="false" style="width: 126px;" />
								</td>
								<td align="right">
									结束日期：
								</td>
								<td>
									<input id="endDate" name="endDate" type="text"
										class="easyui-datebox" editable="false" style="width: 126px;" />
								</td>
								<td align="right">
								    分公司名称：
								</td>
								<td>
									<input id="companyId" name="companyId" type="text"
										style="width: 122px;" />
								</td>
								<td align="right">
									产品类型：
								</td>
								<td>
                                    <input id="productTypeName" name="productTypeName" style="width: 122px;"/>
								</td>
								<td>
									<a class="easyui-linkbutton" onclick="doSearch(1)">搜索</a>
								</td>
								<td>
									<a class="easyui-linkbutton" onclick="doClear(1)">清空</a>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>
			</div>
			<div id="returnPlanDialig"
				style="width: 1000px; height: 470px; overflow: hidden;"></div>
			<div style="padding: 10px;">
				<table id="receivablesList"></table>
			</div>

			<%--<div id="accInfoDialog" class="easyui-dialog" title="添加卡信息" modal="true" align="center" buttons="#abutn" style="padding: 10px; border: 0px; margin: 0px; width: 700px; height: 350px" closed="true" resizable="true" inline="false" closable="false">
				<form id="accountInfo">
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table align="center" width="100%" style="padding: 10px;">

						<tr align="center">
							<td>
								所在省:
							</td>
							<td align="left">
								<input name="provinceId" class="easyui-combobox" width=150 id="provinceId1" required="true" editable="false" />
							</td>
							<td>
								所在市:
							</td>
							<td align="left">
								<input name="cityId" class="easyui-combobox" width=150 id="cityId1" required="true" editable="false" />
							</td>
							<td>
								所在区县:
							</td>
							<td align="left">
								<input name="districtId" class="easyui-combobox" width=150 id="districtId1" required="true" editable="false" />
							</td>
						</tr>
					</table>
					<table align="center" width="100%" style="padding: 10px; border-top-color: olive; border-top-style: solid;">
						<tr align="center">
							<td align="right"">
								手机号码：
							</td>
							<td align="left">
								<input style="width: 150px;" name="mobilephone" class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage="输入11位手机号或加区号的固话号码" />
							</td>
							<td align="right"">
								分公司名称：
							</td>
							<td align="left">
								<input style="width: 150px;" id="departmentId" name="departmentId" class="easyui-combobox" required="true" />
								<input style="width: 150px;" id="branchNameForm" name="branchName" type="hidden" />
							</td>
						</tr>
						<tr align="center">
							<td align="right"">
								所在行地区号：
							</td>
							<td align="left">
								<input style="width: 150px;" name="bankPrefectureNum" class="easyui-validatebox" validType="number" required="true" />
							</td>
							<td align="right"">
								所在行别：
							</td>
							<td align="left">
								<input style="width: 150px;" name="bankNum" id="bankNum" />
								<input style="width: 150px;" name="bankRank" id="bankRank" type="hidden" width="130px" />
							</td>
						</tr>
						<tr align="center">
							<td align="right"">
								开&nbsp;户&nbsp;行：
							</td>
							<td align="left">
								<input style="width: 150px;" name="bankName" class="easyui-validatebox" required="true" />
							</td>
							<td align="right"">
								账&nbsp;户&nbsp;名：
							</td>
							<td align="left">
								<input style="width: 150px;" name="accountName" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr align="center">
							<td align="right"">
								账&nbsp;&nbsp;&nbsp;&nbsp;号：
							</td>
							<td align="left">
								<input style="width: 150px;" name="account" class="easyui-validatebox" validType="number" required="true" />
							</td>
							<td align="right"">
								卡折类型：
							</td>
							<td align="left">
								<input style="width: 150px;" name="cardFlag" id="cardFlag" />
							</td>
						</tr>
						<tr align="center">
							<td align="right"">
								账号用途：
							</td>
							<td align="left">
								<input style="width: 150px;" name="useType" id="useType" />
							</td>
							<td align="right"">
								支付行号：
							</td>
							<td align="left">
								<input style="width: 150px;" name="payBranchno" class="easyui-validatebox" validType="number" required="true" />
							</td>
						</tr>
						<tr align="center">
							<td align="right"">
								是否启用：
							</td>
							<td align="left">
								<input style="width: 150px;" name="onUsed" id="onUsed" />
								<input name="accountType" type="hidden" id="accountType" value="1" />
							</td>
							<td align="right"">
								账户身份证号：
							</td>
							<td align="left">
								<input name="credentialsNumber" style="width: 150px;" class="easyui-validatebox" required="true" validType="idnumberAll" id="credentialsNumber" />
							</td>
						</tr>
					</table>
					<input id="borrowerCredentialsNumber" name="borrowerCredentialsNumber" type="hidden" "/>
					<input id="borrowerName" name="borrowerName" type="hidden" "/>
				</form>
			</div>
			<div id="abutn">
				<a class="easyui-linkbutton" onclick="doSaveCard();">确定</a><a class="easyui-linkbutton" onclick="closeAccDialog();">取消</a>
			</div>

		--%>
		</div>
		<%--<div id="cardDialog" title="卡信息" style="width: 500px; height: 300px;" closed="true" modal="true" closable="false">
			<table id="cardDataGrid">
			</table>
		</div>
	--%>
		<div id="toolss">
			<sec:authorize
				ifAnyGranted="${control.newReceivablesList_clientApplyHistory}">
				<a id="testUpdateClientApplyHistory" class="easyui-linkbutton"
					plain="true" iconCls="icon-reload"
					onclick="testUpdateClientApplyHistory();">重发所有失败借款人信息</a>
			</sec:authorize>
		</div>
	</body>
</html>
