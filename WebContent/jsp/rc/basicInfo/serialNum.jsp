<%@page import="com.creditease.core.security.SpringSecurityUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sessionid = session.getId();
Integer departmentId = SpringSecurityUtils.getCurrentUser().getDepartmentId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>营业部/产品编码设置</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css">
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/serialNum.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>
    <script type="text/javascript">
	    var serverName="<%=path%>";
	    var departmentId="<%=departmentId%>";
	    $(function(){
	    });
    </script>
</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow: hidden;">
			<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="showDepartmentSN()">添加营业部编码</a>
					<%--<a href="#" class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="showProductSN()">添加产品编码</a>--%>
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRow()">删除编码</a>
			      	<%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="saveEdit()">保存修改</a>
			      	
				--%></div>
			</div>
			<table id="codetables"></table>
		</div>
		<!-- 新增弹出框 -->
		<div id="dlg" modal="true" 
		 class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px;" closed="true" buttons="#dlg-buttons">
		      <div class="ftitle">字典信息</div>
		      <form id="insertForm" method="post" novalidate>
		      <table class="fitem">
		      	<tr>
		      		<td><label>字典分类:</label></td>
		      		<td><input name="codaTableId" type="hidden">
		      			<input class="easyui-validatebox" type="text" id="mysection" name="section" readonly="readonly"></input>
		      	</tr>
		      	<tr>
		      		<td><label>字典名称:</label></td>
		      		<td>
		      		<input id="departmentName" name="codeVlue"></td>
		      	</tr>
		      	<tr>
		      		<td><label>字典代码:</label></td>
		      		<td><input class="easyui-validatebox" id="codeKey" name="codeKey" type="text"  missingMessage="请输入字典代码" required="true"
		      		validType="remoteValidate['mysection','请先填写字典分类','codeKey','已存在,请重新输入','../../../dicRequest/validateCodeKey.do']"></td>
		      	</tr>
		      	<tr>
		      		<td><label>说明:</label></td>
		      		<td><input name="codeNote"></td>
		      	</tr>
		      	<tr>
		      		<td><label>是否启用:</label></td>
		      		<td>
		      			<select id="available" class="easyui-combobox" name="available" panelHeight="auto"
							style="width:124px;" required="true" validType="selectValueRequired['available','请选择是否启用']">
						   <option value="">请选择</option>  
						   <option value="0">启用</option>  
						   <option value="-1">停用</option>
						</select>
		      		</td>
		      	</tr>
		      </table>
		      </form>
		</div>
		<div id="dlg1" modal="true" 
		 class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px;" closed="true" buttons="#dlg-buttons1">
		      <div class="ftitle">字典信息</div>
		      <form id="insertForm1" method="post" novalidate>
		      <table class="fitem">
		      	<tr>
		      		<td><label>字典分类:</label></td>
		      		<td><input name="codaTableId" type="hidden">
		      			<input class="easyui-validatebox" type="text" id="mysection1" name="section" readonly="readonly"></input>
		      	</tr>
		      	<tr>
		      		<td><label>字典名称:</label></td>
		      		<td>
		      		<input id="productName" name="codeVlue"></td>
		      	</tr>
		      	<tr>
		      		<td><label>字典代码:</label></td>
		      		<td><input class="easyui-validatebox" id="codeKey1" name="codeKey" type="text"  missingMessage="请输入字典代码" required="true"
		      		validType="remoteValidate['mysection1','请先填写字典分类','codeKey','已存在,请重新输入','../../../dicRequest/validateCodeKey.do']"></td>
		      	</tr>
		      	<tr>
		      		<td><label>说明:</label></td>
		      		<td><input id="codeNote1" name="codeNote"></td>
		      	</tr>
		      	<tr>
		      		<td><label>是否启用:</label></td>
		      		<td>
		      			<select id="available1" class="easyui-combobox" name="available" panelHeight="auto"
							style="width:124px;" required="true" validType="selectValueRequired['available1','请选择是否启用']">
						   <option value="">请选择</option>  
						   <option value="0">启用</option>  
						   <option value="-1">停用</option>
						</select>
		      		</td>
		      	</tr>
		      </table>
		      </form>
		</div>
		<div id="dlg-buttons">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInsert()">保存</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div id="dlg-buttons1">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInsert1()">保存</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
		</div>
	</body>
</html>