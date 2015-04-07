<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>回访信息列表</title>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<style type="text/css">
			.ipttext{
				border: 1px solid #A4BED4;
			}
		</style>
		<script type="text/javascript">var serverName="<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath %>/scripts/rtnvisits/rtnvisits.js"></script>
		
	</head>

	<body id="cc" class="easyui-layout" fit="true" >
	<div region="center">
		<div id="rtnTabs" class="easyui-tabs" style="padding: 10px;">
			<div title="查询条件" style="padding: 10px;">
				<table>
					<tr>
						<td>
							回访日期：
						</td>
						<td>
							<input type="text" id="returnTime" editable="false" class="easyui-datebox ipttext" />
						</td>
						<td>
							业务单号：
						</td>
						<td>
							<input type="text" id="groupNumber"  />
						</td>
						<td>
							客户经理：
						</td>
						<td>
							<input id="managerMan" name="" />
						</td>
						<td align="center">
							<a   class="easyui-linkbutton"  onclick="javascript:searchReturnVisit();">搜索</a>
						</td>
						<td align="center">
							<a   class="easyui-linkbutton" onclick="javascript:clearReturnVisit();">清空</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div style="padding: 10px;">
				<table id="rtnlist" class="easyui-datagrid"   >
					</thead>
				</table>
		</div>
	</div>
	</body>
</html>
