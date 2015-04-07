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
		<script type="text/javascript" src="<%=basePath%>scripts/payment/virtualFinance.js"></script>
		<script type="text/javascript">
			var serverName="<%=path%>";
			$(function() {
			    $("#financeResult").combobox({
			    	data:[{"id":"","text":"请选择"},{"id":"0","text":"成功"},{"id":"-1","text":"失败"}],
			    	valueField:"id",
			    	textField:"text",
			    	panelHeight:'auto',
			    	editable:false,
					multiple:false,
					validType:"selectValueRequired['financeResult','请选择付款结果']"
			    });
			    $("#virFinance").form({
					url: serverName + "/financeMsgController/manualFinance.do",
					onSubmit: function(){
						return $("#virFinance").form('validate');  
					},
					success: function(data){
						if(data=="SUCCESS"){
							$.messager.alert("提示信息",data);
						}else{
							$.messager.alert("提示信息","订单不存在或者类型不匹配");
						}
					}
				});
			});
			function confirm(){
				$("#virFinance").submit(); 
			}
		</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="north" border="false" style="overflow: hidden; height: 150px;padding: 5px;">
			<div class="easyui-panel" title="手动付款收款" fit="true" style="padding:10px;background:#fafafa;" closable="false" collapsible="true">
			  	<form id="virFinance" method="post">
				  	<table>
				  		<tr>
				  			<td>订单号</td>
				  			<td><input type="text" id="orderNum" name="orderNum" class="easyui-validatebox" required="true" missingMessage="请输入订单号"/></td>
				  			<td>结果</td>
				  			<td>
				  				<%--<select id="financeResult"  class="easyui-combobox" name="financeResult" panelHeight="auto"
				  					style="width:100px;" required="true" validType="selectValueRequired['#financeResult','请选择结果']">
								   <option value="">请选择</option>  
								   <option value="0">成功</option>  
								   <option value="-1">失败</option>
								</select>
				  			--%>
				  				<input id="financeResult" name="financeResult" />
				  			</td>
				  			<td>类型</td>
				  			<td>
								<select id="financeType" class="easyui-combobox" name="financeType" panelHeight="auto"
									style="width:100px;" required="true" validType="selectValueRequired['financeType','请选择结算类型']">
								   <option value="">请选择</option>  
								   <option value="F">放款</option>  
								   <option value="S">收款</option>
								</select>
				  			</td>
				  			<td>
				  				<a href="#" class="easyui-linkbutton" onclick="confirm()" id="btn" iconCls="icon-ok">确定</a>
				  			</td>
				  		</tr>
				  	</table>
			  	</form>
			</div>	
		</div>
		<div region="center" border="false">
		</div>
	</body>
</html>
