<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'showQyReserveSearch.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	var serverName="<%=path%>";
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		var serverName="<%=path%>";
	$(function() {
		$("#seachForm").form("validate");
		$("#myTable").datagrid({
			url : "",
			height: 300,
			method : 'post',
			loadMsg : "数据装载中....",
			fitColumns : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			pagination : false,
			pageSize : 20,
			pageList : [ 5, 10, 20 ],
			columns : [ [ {
				field : 'applyId',
				title : '申请编号',
				width : 250,
				hidden : false
			}, {
				field : 'reserveId',
				title : '预约编号',
				width : 100,
				hidden : false
			}, {
				field : 'bizid',
				title : '结算订单号',
				width : 100,
				hidden : false
			}, {
				field : 'clientName',
				title : '客户姓名',
				width : 100,
				hidden : false
			}, {
				field : 'returnName',
				title : '还款人姓名',
				width : 100,
				hidden : false
			}, {
				field : 'idNumber',
				title : '身份证号',
				width : 200,
				hidden : false
			}, {
				field : 'productId',
				title : '产品名称',
				width : 100,
				hidden : false
			}, {
				field : 'officeId',
				title : '分公司名称',
				width : 100,
				hidden : false
			}, {
				field : 'applyAmount',
				title : '借款金额',
				width : 100,
				hidden : false
			}, {
				field : 'amortisation',
				title : '分期数',
				width : 100,
				hidden : false
			}, {
				field : 'loanAmount',
				title : '放款金额',
				width : 100,
				hidden : false
			}, {
				field : 'startTime',
				title : '第一个还款日',
				width : 100,
				hidden : false
			}, {
				field : 'returnDate',
				title : '所属还款月份',
				width : 100,
				hidden : false
			}, {
				field : 'dAmount',
				title : '本期应还款',
				width : 100,
				hidden : false
			}, {
				field : 'keapAmount',
				title : '本期已还款',
				width : 100,
				hidden : false
			}, {
				field : 'keapDate',
				title : '还款时间',
				width : 100,
				hidden : false
			}, {
				field : 'returnType',
				title : '还款方式',
				width : 100,
				hidden : false
			}, {
				field : 'reserveResult',
				title : '状态',
				width : 100,
				hidden : false
			}, {
				field : 'reserveInfo',
				title : '状态描述',
				width : 300,
				hidden : false,
				formatter:function(value){
					if(value=="0"){
						return "贷后未预约";
					}else{
						return value;
					}
				}
			} ] ],
			onLoadSuccess : function(data) {
			},
			onLoadError : function() {
			},

			onHeaderContextMenu : function(e, field) {
			}

		});
	});
	function searchinfo(param) {
		$("#qybn").linkbutton("disable");
		$("#qybz").linkbutton("disable");
		if (param == "0") {
			if ($("#bussesNumber").val() == "") {
				alert("业务编号为空！");
			} else {
				ajaxSubmit(serverName + "/ruralBusyController/queryReserveSearchList.do", {
					businessNumber : $("#bussesNumber").val(),
					param : param
				}, function(r) {
					if(r.success!=undefined){
						$.messager.alert("消息", "查询划扣结果失败", "error");
						$("#qybn").linkbutton("enable");
						$("#qybz").linkbutton("enable");
					}else{
						
					$("#myTable").datagrid('loadData', r);
					$("#qybn").linkbutton("enable");
					$("#qybz").linkbutton("enable");
					}
				});
			}
		} else if (param == "1") {
			if ($("#bizIdString").val() == "") {
				alert("订单号为空！");
			} else {
				ajaxSubmit(serverName + "/ruralBusyController/queryReserveSearchList.do", {
					bizIdString : $("#bizIdString").val(),
					param : param
				}, function(r) {
					if(r.success!=undefined){
						$.messager.alert("消息", "查询划扣结果失败", "error");
						$("#qybn").linkbutton("enable");
						$("#qybz").linkbutton("enable");
					}else{
						
					$("#myTable").datagrid('loadData', r);
					$("#qybn").linkbutton("enable");
					$("#qybz").linkbutton("enable");
					}
				});
			}
		} else {
			alert("错了！");
		}
	}
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="查询信息">
					<form id="seachForm" novalidate>
						<table>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									请输入业务单号：
								</td>
								<td>
									<input size="180" id="bussesNumber" name="bussesNumber" value="${bussesNumber}" class="easyui-validatebox" required="true" />
								</td>
								<td>
									<a id="qybn" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchinfo(0);">查询</a>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									请输入订单号：
								</td>
								<td>
									<input size="180" id="bizIdString" name="bizIdString" class="easyui-validatebox" required="true" />
								</td>
								<td>
									<a id="qybz" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchinfo(1);">查询</a>（用逗号分隔）
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="预约贷后结果列表">
					<table id="myTable"></table>
				</div>
			</div>
		</div>
	</body>
</html>
