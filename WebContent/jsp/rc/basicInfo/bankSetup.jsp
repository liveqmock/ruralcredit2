<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bankSetup.jsp' starting page</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	</script>
	<script type="text/javascript" src="<%=basePath %>scripts/basicInfo/bankSetup.js"></script>
  </head>
  <body id="cc" class="easyui-layout" >
	    <div region="center" title="" >
	    <form id="accountInfo" >
		    <table align="center" width="100%" style="padding: 10px;">
		    		<tr align="center">
		    			<td>所在省:</td>
		    			<td align="left"><input name="provinceId" width=150 id="provinceId" class="easyui-combobox" required="true" editable="false"/></td>
		    			<td>所在市:</td>
		    			<td align="left"><input name="cityId" class="easyui-combobox" width=150 id="cityId" required="true" editable="false"/></td>
		    			<td>所在区县:</td>
		    			<td align="left"><input name="districtId" class="easyui-combobox" width=150 id="districtId" required="true" editable="false"/></td>
		    		</tr>
	    	</table>
	    	<table  align="center" width="100%" style="padding: 10px;border-top-color:olive; border-top-style: solid;">
	    		<tr align="center">
	    			<td align="right"">手机号码：</td>
	    			<td align="left"><input name="mobilephone" class="easyui-validatebox" validType="number" required="true"/> </td>
	    			<td align="right"">分公司名称：</td>
	    			<td align="left"><input name="branchName" class="easyui-validatebox" required="true"/></td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">所在行地区号：</td>
	    			<td align="left"><input name="bankPrefectureNum" class="easyui-validatebox" validType="number" required="true"/></td>
	    			<td align="right"">所在行别：</td>
	    			<td align="left"><input name="bankNum" class="easyui-combobox" id="bankNum" editable="false" width="150px" required="true"/>
	    							<input name="bankRank" type="hidden" id="bankRank"/>
	    			</td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">开户行：</td>
	    			<td align="left"><input name="bankName" class="easyui-validatebox" required="true"/></td>
	    			<td align="right"">账户名：</td>
	    			<td align="left"><input name="accountName" class="easyui-validatebox" required="true"/></td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">账&nbsp;&nbsp;号：</td>
	    			<td align="left"><input name="account" class="easyui-validatebox" validType="number" required="true"/></td>
	    			<td align="right"">卡折类型：</td>
	    			<td align="left"><select name="cardFlag" class="easyui-combobox" editable="false"  required="true" validType="select">
	    								<option value="-1">请选择</option>
	    								<option value="0">卡</option>
	    								<option value="1">存折</option>
	    							</select></td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">账号用途：</td>
	    			<td align="left"><select name="useType" class="easyui-combobox" editable="false"  required="true" validType="select">
	    								<option value="-1">请选择</option>
	    								<option value="0">付款</option>
	    								<option value="1">收款</option>
	    								<option value="2">收付款</option>
	    							</select></td>
	    			<td align="right"">支付行号：</td>
	    			<td align="left"><input name="payBranchno" class="easyui-validatebox" validType="number" required="true"/></td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">是否启用：</td>
	    			<td align="left"><select name="onUsed" class="easyui-combobox"  editable="false"  required="true" validType="select">
	    								<option value="-1">请选择</option>
	    								<option value="0">是</option>
	    								<option value="1">否</option>
	    							</select></td>
	    			
	    		</tr>
	    	</table>
	    	<table align="center" width="100%" style="padding: 10px;">
	    		<tr><td align="center"><a class="easyui-linkbutton" href="javascript:addAccountInfo();">确认添加</a></td>
	    			<td align="left"><a class="easyui-linkbutton" href="javascript:cancel();">重置</a></td></tr>
	    	</table>
	    </form>	
	    </div>
  </body>
</html>
