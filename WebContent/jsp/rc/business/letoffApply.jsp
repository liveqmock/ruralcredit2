<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <jsp:include page="/jsp/rc/include/easyui_swf.jsp"></jsp:include>
    <script type="text/javascript">
    $(function(){
    	$("#fileDiv").hide();    	
    });
    	
    	function showDiv(){
    		$("#fileDiv").show();
    	}
    	function hideDiv(){
    		$("#fileDiv").hide();
    	}
    </script>
  </head>
  <body class="easyui-layout" fit="true">
  	<div region="center" align="center">
	  	<div class="easyui-panel" align="left" title="免罚申请" fit="true"  style="padding: 15px;">
		  	<div style="border-bottom-color: #eeeeee;border-bottom-style: solid;" >
		  		<h3>小组信息</h3>
		  		<table>
		  			<tr><td align="right" width="100">小组编号:</td><td align="left" width="100"><input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value="0830"/></td>
		  			<td align="right" width="100">借款人姓名:</td><td align="left" width="100"><input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value="李莫愁  "/> </td> 
		  			<td align="right" width="100">放款日期: </td><td align="left" width="100"><input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value="2012-08-30"/></td>
		  			</tr>
		  			<tr><td align="right" width="100">借款人身份证号:</td><td align="left" width="100"><input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value=" 620823198001010033"/></td>
		  			<td align="right" width="100">客户经理 :</td><td align="left" width="100"><input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value="测试崇县"/>  </td>
		  			<td align="right" width="100">收款人:</td><td align="left" width="100">  <input class="easyui-validatabox"  readonly="readonly" style="background-color: #eeeeee " value="测试崇县"/></td>
		  			</tr>
		  			   
		  		</table>
		  	</div>
		  	<div style="border-bottom-color: #eeeeee;border-bottom-style: solid;">
				<h3>还款信息</h3>
		  		<table>
		  			<tr><td align="right" width="100">实收金额:</td><td align="left" width="100"><input /> </td>
		  			<td align="right" width="100">收款日期:</td><td align="left" width="100"><input class="easyui-datebox"/> </td>
		  			</tr>
		  			<tr><td align="right" width="100">备注: </td><td align="left"  colspan="3"><textarea rows="2" cols="60"></textarea> </td></tr>
		  			   
		  		</table>
		  	</div>
		  	<div  style="border-bottom-color: #eeeedd;border-bottom-style: solid;" fit="true">
		  		<h3>逾期列表</h3>
		  		<table style="padding: 0px;margin: 0px; border-spacing: 0px;width: 100%"  fit="true">
		  			<tr bgcolor="#E0ECFF" >
		  						<td align="center"></td>
		  						<td align="center">违约信息</td>
		  						<td align="center">滞纳金</td>
		  						<td align="center">罚息</td>
		  						<td align="center">管理费</td>
		  						<td align="center">利息</td>
		  						<td align="center">本金</td>
		  						<td align="center">合计</td></tr>
		  			<tr>
			  			<td></td>
			  			<td>
			  				<table    style="height: 100%;width: 100%;border-spacing: 0px;border: 0.5px;">
			  				<tr><td>开始日期</td><td>开始日期</td><td>天数</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;" /></td></tr>
			  				</table>
			  			
			  			</td>
			  			<td>
			  				<table  fit="true" style="border-spacing: 0px;border: 0px;" >
			  					<tr><td>逾期</td><td>已还</td><td>实收分配</td><td>免罚</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;" /></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;" /></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
			  			<td>
			  				<table  fit="true" style="border-spacing: 0px;border: 0px;">
			  					<tr><td>逾期</td><td>已还</td><td>实收分配</td><td>免罚</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
			  			<td>
			  				<table   fit="true" style="border-spacing: 0px;border: 0px;">
			  					<tr><td>管理费</td><td>已还</td><td>实收分配</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
			  			<td>
			  			<table   fit="true" style="border-spacing: 0px;border: 0px;">
			  					<tr><td>利息</td><td>已还</td><td>实收分配</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
			  			<td>
			  				<table  fit="true" style="border-spacing: 0px;border: 0px;">
			  					<tr><td>本金</td><td>已还</td><td>实收分配</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
			  			<td>
			  				<table  fit="true" style="border-spacing: 0px;border: 0px;">
			  					<tr><td>合计</td><td>已还</td><td>免罚</td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  					<tr><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td><td><input style="width: 40px;"/></td></tr>
			  				</table>
			  			</td>
		  			</tr>
		  			<tr><td>合计</td><td>55</td><td>585</td><td>656</td><td>54</td><td>452</td><td>8</td><td>78</td></tr>
		  			
		  		</table>
		  	</div>
		  	<div >
		  		<h3>是否申请免罚</h3>
		  		<input id="shi" name="letoff" type="radio" onclick="showDiv();"/> 是<input id="fou"  name="letoff" onclick="hideDiv();" type="radio" /> 否
		  	</div>
		  	<div id="fileDiv">
							<jsp:include page="../../../admin/swfuploadexample/UploadFileExample.jsp"></jsp:include>
		  	</div>
	  		<a class="easyui-linkbutton" href="javascript:history.go(-1);">取消</a>
  	</div>
  </body>
</html>
