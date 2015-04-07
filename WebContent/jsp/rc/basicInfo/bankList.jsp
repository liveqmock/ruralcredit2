<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
       <base href="<%=basePath%>"></base>
    <title>My JSP 'bankSetup.jsp' starting page</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	 var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;
	</script>
	<script type="text/javascript" src="<%=basePath %>scripts/basicInfo/bankList.js">
	</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		
		<div region="center">
				<div class="easyui-tabs" id="accountTab" style="padding: 10px;">
					<div title="模糊搜索" style="padding: 10px;">
						查询条件：<input id="fuzzyValue"/>
						<a class="easyui-linkbutton" href="javascript:searchFuzzy();">搜索</a>
						<a class="easyui-linkbutton" href="javascript:cancelSearch();">清空</a><font color="red">（搜索条件可以是：分公司的名称，账户名，账号）</font>
					</div>
					<div  title="高级搜索" style="padding: 10px;">
						 
								<%--<td>省：</td>
								<td><input name="provinceId" width=150 id="provinceId"/></td>
								<td>市</td>
								<td><input name="cityId" class="easyui-combobox" width=150 id="cityId" editable="false"/></td>
								<td>区县：</td>
								<td><input name="districtId" class="easyui-combobox" width=150 id="districtId" editable="false"/></td>
								--%> 分公司名称： 
								 <input  id="branchNameSearch"/> 
								账户名： 
								 <input   id="accountNameSearch"/> 
								账号：
								 <input id="accountSearch"/> 
								 账户身份证号：
								 <input  id="credentialsNumberSearch"/> 
								 <a class="easyui-linkbutton" href="javascript:searchAcountInfo();">搜索</a> 
								 <a class="easyui-linkbutton" href="javascript:showAll();">清空</a> 
					</div>
				</div>
			<div style="padding: 10px;">
			<table id="accountInfoList"></table>
			</div>
		</div>
		<div id="showForm" class="easyui-dialog" modal="true" align="center"  buttons="#buttons"
			style="padding: 10px; border: 0px; margin: 0px; width:700px;height : 350px"
			closed="true" resizable="true" inline="false">
			<form id="accountInfo" novalidate>
			<table>
					<tr>
		    			<td><input name="accountInfoId" id="accountInfoId" type="hidden"/></td>
		    		</tr>
			</table>
		    <table align="center" width="100%" style="padding: 10px;">
		    		
		    		<tr align="center">
		    			<td>所在省:</td>
		    			<td align="left">
		    			<input name="provinceId" class="easyui-combobox" 
		    			width=150 id="provinceId1" required="true" 
		    			editable="false"/></td>
		    			<td>所在市:</td>
		    			<td align="left">
		    			<input name="cityId" class="easyui-combobox" 
		    			width=150 id="cityId1" required="true" 
		    			editable="false"/></td>
		    			<td>所在区县:</td>
		    			<td align="left">
		    			<input name="districtId" class="easyui-combobox" 
		    			width=150 id="districtId1" required="true"
		    			 editable="false"/></td>
		    		</tr>
	    	</table>
	    	<table  align="center" width="100%" style="padding: 10px;border-top-color:olive; border-top-style: solid;">
	    		<tr align="center">
	    			<td align="right"">手机号码：</td>
	    			<td align="left">
	    				<input style="width:150px;" name="mobilephone" class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage
	    				="输入11位手机号或加区号的固话号码" maxlength="11"/> </td>
	    			<td align="right"">分公司名称：</td>
	    			<td align="left"><input style="width:150px;" 
	    									id="departmentId"
	    									name="departmentId" 
	    									class="easyui-combobox"  
	    									required="true" editable="false"/>
	    								<input style="width:150px;" 
	    									id="branchNameForm"
	    									name="branchName" 
	    									type="hidden"/>
	    			</td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">所在行地区号：</td>
	    			<td align="left"><input style="width:150px;" name="bankPrefectureNum" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20"/></td>
	    			<td align="right"">所在行别：</td>
	    			<td align="left"><input style="width:150px;" name="bankNum" id="bankNum" required="true"   />
	    							<input style="width:150px;" name="bankRank" id="bankRank" 
	    							type="hidden" width="130px" />
	    			</td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">开&nbsp;户&nbsp;行：</td>
	    			<td align="left"><input style="width:150px;" name="bankName" class="easyui-validatebox"  required="true" maxlength="30"/></td>
	    				<td align="right"">账号用途：</td>
	    			<td align="left"><input style="width:150px;" name="useType" id="useType" required="true"   />
	    			</td>
	    			
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
	    			<td align="left"><input style="width:150px;" name="account" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20"/></td>
	    			<td align="right"">卡折类型：</td>
	    			<td align="left"><input style="width:150px;" name="cardFlag" required="true"   
	    						id="cardFlag"/>
	    			</td>
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">账&nbsp;户&nbsp;名：</td>
	    			<td align="left"><input style="width:150px;" name="accountName" class="easyui-validatebox"  required="true" maxlength="10"/></td>
	    			<td align="right"">是否启用：</td>
	    			<td align="left">
	    			<input style="width:150px;" name="onUsed"  id="onUsed" required="true"   />
	    			
	    		</tr>
	    		<tr align="center">
	    			<td align="right"">支付行号：</td>
	    			<td align="left"><input style="width:150px;" name="payBranchno" class="easyui-validatebox" validType="numberOnly" required="true" maxlength="20"/></td>
	    			<input  name="accountType" type="hidden"  id="accountType" value="0"/>
	    			</td>
	    			<td align="right"">账户身份证号：</td>
	    			<td align="left">
	    			<input  name="credentialsNumber"  
	    				style="width:147px;"
	    				class="easyui-validatebox" 
	    				required="true"  
	    			 	validType="idnumberAll"  
	    			 	id="credentialsNumber"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>	
		</div>
		<div id="buttons">
			 <a id="bankAddButton" class="easyui-linkbutton" href="javascript:addOrUpdate();">确认</a> 
   			 <%--<a class="easyui-linkbutton" href="javascript:reset();">重置</a> 
   			 --%><a id="bankCancelButton" class="easyui-linkbutton" href="javascript:cancel();">取消</a> 
		</div>
  </body>
</html>
