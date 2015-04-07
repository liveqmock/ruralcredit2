<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sessionid = session.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>数据字典管理</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css">
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/dictionary.js"></script>
    <script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>
    <script type="text/javascript">
	    var serverName="<%=path%>";
	    var jsessionid="<%=sessionid%>";
	    $(function(){
	    	$("#actpic").uploadify({
	    		'debug': true,
				'swf':serverName+'/scripts/uilib/uploadify/uploadify.swf',
				'uploader':serverName+'/upDownload/transferFile.do;jsessionid='+jsessionid,
				'cancel': serverName+'/scripts/uilib/uploadify/uploadify-cancel.png',
				'fileObjName': 'actpicFile',
				'auto': false,
				'height':20,
				'width':60,
				'buttonText': '选择文件', 
				'fileSizeLimit':0,
				'queueSizeLimit':1,
				//'formData':{ 'actid':'wwyyff'},
				'displayData': 'percentage',
				'multi':false,
				'queueID':'file_queue',
				'onUploadSuccess': function (file,data,response){
					var msg=$.parseJSON(data);
					if(msg.success){
						$.messager.alert("提示信息",file.name+"导入成功");
					}else{
						$.messager.alert("提示信息",file.name+"上传错误:"+msg.msg);
					}
				},
				'onUploadError': function (file,errorCode,errorMsg,errorString) {//错误提示 
                    $.messager.alert("提示信息",file.name+"上传错误:"+errorString+errorMsg+errorCode);
                }
			});
	    });
    </script>
</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow: hidden;">
			<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
				<fieldset>
					<legend>查询条件</legend>
					<table class="tableForm">
						<tr>
							<td>字典分类:<input type="text" id="section" /></td>
							<td><a id="btn" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchByParam()">查询</a></td>
							<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a></td>
							<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="reloadInitGetAllDic();">重新加载</a></td>
						</tr>
					</table>
				</fieldset>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="showInsertView()">添加字典信息</a>
			      	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRowByButton()">修改字典信息</a>
			      	<%--<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRow()">删除字典信息</a>--%>
				</div>
			</div>
			<table id="codetables"></table>
			<div>
				<fieldset>
				    <legend>批量导入</legend>
				    <div title="上传文件列表" id="file_queue" style="padding: 10px;"></div>
					<input type="file" id="actpic" name="actpicFile"/> 
					<p>
						<a href="javascript:$('#actpic').uploadify('upload','*')" class="easyui-linkbutton" >上传文件</a>
						<a href="javascript:$('#actpic').uploadify('cancel','*')" class="easyui-linkbutton"  >取消全部上传</a>
					</p>
				</fieldset>
			</div>
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
		      			<input class="easyui-validatebox" type="text" id="mysection" name="section" required="required" missingMessage="请输入字典分类"></input>
		      	</tr>
		      	<tr>
		      		<td><label>字典代码:</label></td>
		      		<td><input class="easyui-validatebox" name="codeKey" type="text"  missingMessage="请输入字典代码" required="true"
		      		validType="remoteValidate['mysection','请先填写字典分类','codeKey','已存在,请重新输入','../../../dicRequest/validateCodeKey.do']"></td>
		      	</tr>
		      	<tr>
		      		<td><label>字典名称:</label></td>
		      		<td><input name="codeVlue" class="easyui-validatebox" missingMessage="请输入字典名称" required="true"></td>
		      	</tr>
		      	<tr>
		      		<td><label>说明:</label></td>
		      		<td><input name="codeNote"></td>
		      	</tr>
		      	<tr>
		      		<td><label>序列:</label></td>
		      		<td><input name="sequence" class="easyui-validatebox" missingMessage="请输入序列" required="true" validType="number"></td>
		      	</tr>
		      	<tr>
		      		<td><label>是否启用:</label></td>
		      		<td><select id="available" class="easyui-combobox" name="available" panelHeight="auto"
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
		<div id="dlg-buttons">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInsert()">保存</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		
		<!-- 修改弹出框 -->
		<div id="dlg1" modal="true" 
		 class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px;" closed="true" buttons="#dlg-buttons1">
		      <div class="ftitle">字典信息</div>
		      <form id="insertForm1" method="post" novalidate>
		      <table class="fitem">
		      	<tr>
		      		<td><label>字典分类:</label></td>
		      		<td><input name="codaTableId" type="hidden">
		      			<input class="easyui-validatebox" type="text" id="mysection1" name="section" required="required" missingMessage="请输入字典分类"></input>
		      	</tr>
		      	<tr>
		      		<td><label>字典代码:</label></td>
		      		<td><input  name="codeKey" type="text" disabled="disabled"></td>
		      	</tr>
		      	<tr>
		      		<td><label>字典名称:</label></td>
		      		<td><input name="codeVlue" class="easyui-validatebox" missingMessage="请输入字典名称" required="true"></td>
		      	</tr>
		      	<tr>
		      		<td><label>说明:</label></td>
		      		<td><input name="codeNote"></td>
		      	</tr>
		      	<tr>
		      		<td><label>序列:</label></td>
		      		<td><input name="sequence" class="easyui-validatebox" missingMessage="请输入序列" required="true" validType="number"></td>
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
		<div id="dlg-buttons1">
		    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateRow()">保存</a>
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
		</div>
	</body>
</html>


