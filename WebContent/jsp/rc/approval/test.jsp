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

		<title>My JSP 'test.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<style type="text/css">
		td{
			text-align:left;
		}
		#operate{
			width:200px;
			background-color:#ccc;
			visibility:hidden;
		}
		ul li{
			padding-left:5px;
			list-style:none;
		}
		td{
			text-align:center;
		}
		.ipttext{
			border: 1px solid #A4BED4;
		}
		.abut{
			margin-bottom:4px;
		}
	</style>
		<script type="text/javascript">
		$(function(){
			$(".uname").bind('click',function(){
				$("#groupInfo").dialog({
					title:"操作",
					modal:false
				});
				var offset=$(this).offset();
				var left=offset.left+5;
				var top=offset.top;
				var height=$(this).height();
				$("#groupInfo").dialog('move',{
					left:left+50,
					top:top+height-20
				});
			});
		});
		
		function ruhu1(){
			$("#ruhu1").dialog({
				title:"宜信平台入户调查表一",
				modal:false,
				href:"<%=basePath%>jsp/rc/business/1_householdSurveyFirst.jsp"
			});
		}
		function ruhu2(){
			$("#ruhu1").dialog({
				title:"宜信平台入户调查表二",
				modal:false,
				href:"<%=basePath%>jsp/rc/business/householdSurveySecond(test1).jsp"
			});
		}
		function contact(){
			$("#ruhu1").dialog({
				title:"宜信平台联系人调查表",
				modal:false,
				href:"<%=basePath%>jsp/rc/business/contactSurvey(test1).jsp"
			});
		}
		function others(){
			$("#others").dialog({
				title:"其他资料",
				modal:false,
				href:"<%=basePath%>jsp/rc/approval/otherinfo.jsp"
			});
		}
		function common(){
			
		}
</script>
	</head>

	<body id="cc" class="easyui-layout" fit="true">
	<div region="center">
  	<h4>所在村镇：崇信县-金萍镇-南关村&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				小组编码：3333&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				客户经理：静宁测试&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>
    <div class="easyui-panel" title="组员信息">
    	<table>
					   		<tr><thead>
							   	<th>组员</th>
					   			<th>审批</th>
					   			<th>金额</th>
					   			<th>操作</th>
					   		</thead></tr>
					   		<tr>
					   			<td><a class="uname">赵某某</a></td>
			   					<td>通过<input name="aa" type="radio" value="">&nbsp;&nbsp;
			   						拒绝<input name="aa" type="radio" value=""></td>
			   					<td><input type="text" value="" class="easyui-numberbox  ipttext"></td>
			   					<td rowspan="3">
					   				<a class="easyui-linkbutton abut">接受</a><br>
					   				<a class="easyui-linkbutton abut">复议</a><br>
					   				<a class="easyui-linkbutton abut">撤销</a><br>
					   				<a class="easyui-linkbutton abut">返回</a><br>
					   			</td>
					   		</tr>
					   		<tr>
					   			<td><img src="<%=basePath %>/scripts/uilib/themes/icons/firstnew.gif"><a class="uname">赵某某</a></td>
			   					<td>通过<input name="aa" type="radio" value="">&nbsp;&nbsp;
			   						拒绝<input name="aa" type="radio" value=""></td>
			   					<td><input type="text" value="" class="easyui-numberbox ipttext"></td>
					   		</tr>
					   		<tr>
					   			<td><img src="<%=basePath %>/scripts/uilib/themes/icons/firstnew.gif"><a class="uname">赵某某</a></td>
			   					<td>通过<input name="aa" type="radio" value="">&nbsp;&nbsp;
			   						拒绝<input name="aa" type="radio" value=""></td>
			   					<td><input type="text" value="" class="easyui-numberbox ipttext"></td>
					   		</tr>
				   	</table>
    </div>
    <div class="easyui-panel" title="意见">
    	<table>
    		<tr>
    			<td>审查意见</td>
    			<td><textarea cols="120" rows="5" style="resize:none;" class="ipttext">同意</textarea></td>
    		</tr>
    		<tr>
    			<td>审批意见</td>
    			<td><textarea cols="120" rows="5" style="resize:none;" class="ipttext">同意</textarea></td>
    		</tr>
    	</table>
    </div>
    <div class="easyui-panel" title="信贷资料">
    	<table>
			<tr>
				<td>产品类型</td>
	 			<td>宜农贷</td>
	 			<td>放款日期</td>
	 			<td>
	 				<input type="text" class="easyui-datebox">
				</td>
			</tr>
			<tr>
					<td>支付类型</td>
	 			<td>
	 				<select class="easyui-combobox">
	 					<option value="">
	 						银行支付
	 					</option>
	 				</select>
 				</td>
	 			<td>分公司账号</td>
	 			<td><input type="text" class="ipttext">
					</td>
			</tr>
		</table>
    </div>
  </div>
   	</div>
   		<div id="groupInfo">
   		<a class="easyui-linkbutton abut" onclick="ruhu1()">入户调查表1</a><br>
		<a class="easyui-linkbutton abut" onclick="ruhu2()">入户调查表2</a><br>
		<a class="easyui-linkbutton abut" onclick="contact()">联系人调查表</a><br>
		<a class="easyui-linkbutton abut" onclick="others()">其他资料</a><br>
		<a class="easyui-linkbutton abut" onclick="common()">小组共同资料</a><br>
   	</div>
   	
   	<div id="ruhu1" style="width:1000px;height:400px;">
   	</div>
   	<div id="ruhu2" style="width:1000px;height:400px;">
   	</div>
   	<div id="contact" style="width:1000px;height:400px;">
   	</div>
   	<div id="others" style="width:1000px;height:400px;">
   	</div>
   	<div id="common" style="width:1000px;height:400px;">
	</body>
</html>
