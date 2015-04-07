<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName="<%=path%>";
			$(function() {
				bindSubmit("repairById",serverName + "/repairSysData/repairBusNum.do");
				bindSubmit("repairByNum",serverName + "/repairSysData/repairBusNum.do");
				
				$('#pp').portal({
					border:false,
					fit:true
				});
				var p = $('#repairByIdPanel').panel({
					title:"修改业务编号-申请表主键ID",
					height:150,  
				    closable: true,  
				    collapsible: true
				});
				var p1 = $('#repairByNumPanel').panel({
					title:"修改业务编号-业务编号",
					height:150,  
				    closable: true,  
				    collapsible: true
				});
				add(p,0);
				add(p1,1);
				
			});
			/** easyui form **/
			function bindSubmit(clickId,url){
				$("#"+clickId).form({
					url: url,
					onSubmit: function(){
						return $("#"+clickId).form('validate');  
					},
					success: function(data){
						if(data){
							var dataObj = JSON.parse(data);
							$.messager.alert("提示信息",dataObj.msg);
						}
					}
				});
			}
			function repairById(){
				$.messager.confirm('提示信息','确认要修改吗?',function(r){
					if (r){
						$("#repairById").submit();
					}
				});
			}
			function repairByNum(){
				$.messager.confirm('提示信息','确认要修改吗?',function(r){
					if (r){
						$("#repairByNum").submit();
					}
				});
			}
			//添加
			function add(p,i){
				$('#pp').portal('add', {
					panel:p,
					columnIndex:i
				});
				$('#pp').portal('resize');
			}
		</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">
			<div id="pp" style="position:relative">
				<div style="width:50%;">
					
				</div>
				<div style="width:50%;">
					
				</div>
			</div>
		</div>
		<div region="north" title="提示信息" style="height:80px;padding: 0px;overflow-y:hidden;">
			<p align="center" style="color:red;font-size:20px;font-family: Verdana, 微软雅黑,黑体;">错误数据修复，请谨慎操作</p>
		</div>
		<div id="repairByIdPanel" style="padding:10px;background:#fafafa;">
		  	<form id="repairById" method="post">
			  	<table>
			  		<tr>
			  			<td>申请表主键ID</td>
			  			<td><input type="text" id="caId1" name="caId" class="easyui-validatebox" required="true" missingMessage="请输入主键"/></td>
			  			<td>业务编号</td>
			  			<td><input type="text" id="busNum1" name="busNum" class="easyui-validatebox" required="true" missingMessage="请输入业务编号"/></td>
			  			<td><a href="#" class="easyui-linkbutton" onclick="repairById()" id="btn" iconCls="icon-ok">修改</a></td>
			  		</tr>
			  	</table>
			  	<input type="hidden" name="repairType" value="0"/>
		  	</form>
		</div>	
		<div id="repairByNumPanel" style="padding:10px;background:#fafafa;">
		  	<form id="repairByNum" method="post">
			  	<table>
			  		<tr>
			  			<td>原业务编号</td>
			  			<td><input type="text" id="caId2" name="caId" class="easyui-validatebox" required="true" missingMessage="请输入主键"/></td>
			  			<td>业务编号</td>
			  			<td><input type="text" id="busNum2" name="busNum" class="easyui-validatebox" validType="notEqualTo['caId2','不能与原业务编号相同']" required="true" missingMessage="请输入业务编号"/></td>
			  			<td><a href="#" class="easyui-linkbutton" onclick="repairByNum()" id="btn" iconCls="icon-ok">修改</a></td>
			  		</tr>
			  	</table>
			  	<input type="hidden" name="repairType" value="1"/>
		  	</form>
		</div>
	</body>
</html>
