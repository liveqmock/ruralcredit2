<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
 	  <title>My JSP 'townManege.jsp' starting page</title>
    <jsp:include page="../include/easyui_swf.jsp"></jsp:include>
    <script type="text/javascript">
    var serverName="<%=path%>";
    $(function(){
    	$("#actpic").uploadify({ 
			'swf':serverName+'/scripts/uilib/uploadify/uploadify.swf',
			'uploader':serverName+'/town/kkk.do',
			'cancel': serverName+'/scripts/uilib/uploadify/uploadify-cancel.png',
			'fileObjName': 'actpicFile',
			'auto': false,
			'height':20,
			'width':60,
			'buttonText': '选择文件', 
			'fileSizeLimit':'2048KB',
			'queueSizeLimit':'10',
			'formData':{ 'actid':'wwyyff'},
			'displayData': 'percentage',
			'fileType':'*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'fileTypeDesc': '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileTypeExt': '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'multi':true,
			'queueID':'file_queue'
		});
    });
    	function groupAllSave(){
			$('#uploadFile').form('submit', {
				url:serverName+ "/town/uploadfile.do",  
				type:"post",
				
				success:function(data){   
					$.messager.show({title:"提示信息",msg:"ALL SAVE"});
					$('#uploadFileList').datagrid('reload'); 
				}
			});
		}		
    </script>
	</head>
	<body>
	<form id="uploadFile" method="post" enctype="multipart/form-data">  
        <input type="file" name="file"/>  
        <input type="submit"/>  
    </form>  
	<input type="button" value ="提交" onclick="groupAllSave();"/>
	
	
	<div title="上传文件列表" id="file_queue" style="padding: 10px;">
			</div>
		<input type="file" id="actpic" name="actpicFile"/> 
		<p>
			<a href="javascript:$('#actpic').uploadify('upload','*')" class="easyui-linkbutton" >上传文件</a>
			<a href="javascript:$('#actpic').uploadify('cancel','*')" class="easyui-linkbutton"  >取消全部上传</a>
		</p> 
	</body>
</html>


