
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	/*放款登记查看*/
	function registView() {
		var loanRegistDESId = $("#loanRegistDESId").val();
		var src = cmUrl + "/operation/transferParameter.action?clientId=" + loanRegistDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
				+ "&signIp=" + DESIp + "";
		var t = top.window.open(src);
		t.document.title = "放款登记附件";
	}
	/*审批单查看*/
	function approvalView() {
		var approvalDESId = $("#approvalDESId").val();
		var src = cmUrl + "/operation/transferParameter.action?clientId=" + approvalDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
				+ "&signIp=" + DESIp + "";
		window.open(src);

	}

	/*申请单查看*/
	function creditApplicationView() {

		var creditapplicationDESId = $("#creditapplicationDESId").val();
		var src = cmUrl + "/operation/transferParameter.action?clientId=" + creditapplicationDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
		window.open(src);

	}
	/*担保人查看*/
	function guaranorView(borrowerServiceAppDESId) {
		var src = cmUrl + "/operation/transferParameter.action?clientId=" + borrowerServiceAppDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
		window.open(src);
	}

	/*放款登记下载*/
	function downloadRegist() {
		ajaxSubmit(serverName + "/borrowerServiceApp/checkCM.do", {
			clientId : $("#loanRegistUDESId").val()
		}, function(r) {
			if (r.success) {
				var loanRegistDESId = $("#loanRegistDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + loanRegistDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			} else {
				$.messager.alert("消息", r.msg, "info");
			}
		});
	}

	/*审批单下载*/
	function downloadApproval() {

		ajaxSubmit(serverName + "/borrowerServiceApp/checkCM.do", {
			clientId : $("#approvalUDESId").val()
		}, function(r) {
			if (r.success) {
				var approvalDESId = $("#approvalDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + approvalDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			} else {
				$.messager.alert("消息", r.msg, "info");
			}
		});
	}

	/*申请单下载*/
	function downloadCreditapplication() {
		ajaxSubmit(serverName + "/borrowerServiceApp/checkCM.do", {
			clientId : $("#creditapplicationUDESId").val()
		}, function(r) {
			if (r.success) {
				var creditapplicationDESId = $("#creditapplicationDESId").val();
				var href = downloadUrl + "/zipDownload.do?clientId=" + creditapplicationDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			} else {
				$.messager.alert("消息", r.msg, "info");
			}
		});
	}

	/*担保人下载*/
	function downGuaranor(borrowerServiceAppDESId, borrowerServiceAppId) {
		ajaxSubmit(serverName + "/borrowerServiceApp/checkCM.do", {
			clientId : borrowerServiceAppId
		}, function(r) {
			if (r.success) {
				var href = downloadUrl + "/zipDownload.do?clientId=" + borrowerServiceAppDESId + "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
				window.location.href = href;
			} else {
				$.messager.alert("消息", r.msg, "info");
			}
		});
	}
</script>

<div style="width: 805px">
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th width="400" field="name">
					&nbsp;&nbsp;名称
				</th>
				<th width="400" field="operation">
					&nbsp;&nbsp;操作
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					&nbsp;&nbsp;申请单附件
				</td>
				<td>
					&nbsp;&nbsp;
					<a onclick="javascript:creditApplicationView()">查看</a>&nbsp;&nbsp;|&nbsp;
					<a onclick="javascript:downloadCreditapplication();">下载</a>
				</td>
			</tr>
			<c:forEach items="${borrowerServiceApps}" var="b">
				<tr>
					<td>
						&nbsp;&nbsp;担保人&nbsp;&nbsp;:&nbsp;&nbsp;${b.name}
					</td>
					<td>
						&nbsp;&nbsp;
						<a onclick="javascript:guaranorView('${b.borrowerServiceAppDESId}')">查看</a>&nbsp;&nbsp;|&nbsp;
						<a onclick="javascript:downGuaranor('${b.borrowerServiceAppDESId}','${b.borrowerServiceAppId}');">下载</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
					&nbsp;&nbsp;审批单附件
				</td>
				<td>
					&nbsp;&nbsp;
					<a onclick="javascript:approvalView()">查看</a>&nbsp;&nbsp;|&nbsp;
					<a onclick="javascript:downloadApproval();">下载</a>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;&nbsp;放款登记附件
				</td>
				<td>
					&nbsp;&nbsp;
					<a onclick="javascript:registView();">查看</a>&nbsp;&nbsp;|&nbsp;
					<a onclick="javascript:downloadRegist();">下载</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>