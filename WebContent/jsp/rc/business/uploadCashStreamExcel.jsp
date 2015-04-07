<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <title>My JSP 'townManege.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
    <script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/jquery.easyui.min3.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/locale/easyui-lang-zh_CN.js"></script>
	<style>
	*{margin:0;padding:0;}
	a{text-decoration:none;}
	</style>
	<script type="text/javascript">
		var serverName="<%=path%>";
		var extArray = new Array(".xlsx", ".xls");
		function uploadFile(){
			var file = $("#fileTown").val();
			 allowSubmit = false;
			 if (!file){
				 $.messager.alert("消息","请选择要上传的文件");
				 return;
			 }
			 while (file.indexOf("\\") != -1)
			 file = file.slice(file.indexOf("\\") + 1);
			 ext = file.slice(file.indexOf(".")).toLowerCase();
			 for (var i = 0; i < extArray.length; i++) {
			 	if (extArray[i] == ext) { allowSubmit = true; break; }
			 }
			 if (allowSubmit){
				 $('#uploadFile').form('submit', {
						url:serverName+ "/cashStream/uploadCashStreamExcel.do?parentId=1",  
						type:"post",
						success:function(data){ 
							var data = JSON.parse(data);
							if(data.success){
								$.messager.alert("消息","上传成功","info",function(){
										
								});
							}else{
								 $.messager.alert("消息",data.msg);
							}
							$("#uploadFile").form("clear");
							$("#fileName").text("");
						}
					});
			 }else{
				 $.messager.alert("消息","对不起，只能上传以下格式的文件:  "  + (extArray.join("  ")) + "\n请重新选择符合条件的文件" + "再上传.");
				 $("#uploadFile").form("clear");
				 $("#fileName").text("");
			 }
		}
		function getFileName(){
			 $("#fileName").text("上传的文件为："+$("#fileTown").val());
		}
	</script>

  </head>
  
 	<body>
	   <div region="center"  style="padding: 0px;">
		   <div style="padding: 7px;border-top-color:olive; border-top-style: solid;">
			   		<form id="uploadFile" method="post" enctype="multipart/form-data">  
			   			<table cellspacing="20">
			   				<tr style="padding: 10px;">
			   					<td>
			   						<a class="btn_addPic" href="javascript:void(0);"><span><em>+</em>选择excel文件</span> 
					        		<input class="filePrew" id="fileTown" onchange="getFileName()" tabindex="3" size="3" type="file" name="file"/>
					        		</a>
			   					</td>
			   					<td>
						    		<a class="easyui-linkbutton" onclick="uploadFile();">提交</a>
			   					</td>
			   				</tr>
			   			</table>
				   		
		    		</form>
		   </div>
		   <div style="padding: 7px;border-top-color:olive; border-top-style: solid;">
			    	<span id="fileName"></span>
	    		<br />
		   </div>
	   </div>
 	</body>
  <%--<form>
  	<a class="btn_addPic" href="javascript:void(0);">
  	<span><em>+</em>添加图片</span> 
  	<input class="filePrew" title="支持jpg、jpeg、gif、png格式，文件小于5M" tabindex="3" type="file" name="pic" size="3" /></a>
  	</form> 
--%></html>
