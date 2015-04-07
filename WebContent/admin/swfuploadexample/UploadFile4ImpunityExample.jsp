<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	double perMaxSize = 2;//单个文件允许的max大小
	String sizeUnit = "MB";//perMaxSize数据对应的单位
	String ext = "*.*";//允许上传的文件类型
	
	//文件上传提交的目标页面
	StringBuffer uploadUrl = new StringBuffer("http://");
	uploadUrl.append(request.getHeader("Host"));
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/admin/swfuploadexample/UploadFileExampleSubmit.jsp");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<title>批量相片上传</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>admin/swfuploadexample/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>admin/swfuploadexample/js/swfupload.js"></script>
<script type="text/javascript" src="<%=basePath%>admin/swfuploadexample/js/swfupload.swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>admin/swfuploadexample/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>admin/swfuploadexample/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=basePath%>admin/swfuploadexample/js/handlers.js"></script>
<script type="text/javascript" src="<%=basePath%>jsp/script/jquery-1.4.2.js"></script>

<script type="text/javascript">
var swfu;

SWFUpload.onload = function () {
	var settings = {
		flash_url : "<%=basePath%>admin/swfuploadexample/js/swfupload.swf",
		upload_url: "<%=uploadUrl.toString()%>",
		post_params: {
			"user_id" : "stephen830",
			"pass_id" : "123456"
		},
		file_size_limit : "<%=perMaxSize%> <%=sizeUnit%>",
		file_types : "<%=ext%>",
		file_types_description : "<%=ext%>",
		file_upload_limit : 100,
		file_queue_limit : 0,
		custom_settings : {
			progressTarget : "fsUploadProgress",
			cancelButtonId : "btnCancel",
			uploadButtonId : "btnUpload",
			myFileListTarget : "idFileList"
		},
		debug: false,
		auto_upload:false,

		// Button Settings
		button_image_url : "<%=basePath%>admin/swfuploadexample/images/XPButtonUploadText_61x22.png",	// Relative to the SWF file
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 61,
		button_height: 22,

		// The event handler functions are defined in handlers.js
		swfupload_loaded_handler : swfUploadLoaded,
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete,	// Queue plugin event
		
		// SWFObject settings
		minimum_flash_version : "9.0.28",
		swfupload_pre_load_handler : swfUploadPreLoad,
		swfupload_load_failed_handler : swfUploadLoadFailed
	};

	swfu = new SWFUpload(settings);
};

$(document).ready(function(){
	
	// 全选所有行
	$("#selectAllFiles").click( function (){
		var s = $(".fileType");
		s.each(function(i){ 
			s[i].checked = $("#selectAllFiles").attr('checked');
		}); 
	});
	
	// 设置类型
	$("#uploadType").click( function (){
		var s = $(".fileType");
		s.each(function(i){ 
			if($("#uploadType").val() > 0 && s[i].checked == true ){
				s[i].parentNode.innerHTML = s[i].outerHTML + $("#uploadType option:selected").text() + "<input style=\"display:none\" type=\"text\" name=\"uploadFileTypeList\" class=\"uploadFileTypeListClass\" value=\"" + $("#uploadType").val() + "\" />";
			}
		}); 
		$("#uploadType").val("-1");
	});
	
});

</script>
</head>
<body bgcolor="#FCFCFC" topmargin="0px" leftmargin="10px" rightmargin="10px" scroll="yes">
		<table width="100%" cellspacing="4" cellpadding="4" border="0"
			bgcolor="#FCFCFC">
			<tr>
				<td class="DH1">
					<table width="100%" cellspacing="4" cellpadding="4" border="0"
						bgcolor="#FCFCFC">
						<tr>
							<td class="DH2">
								<STRONG>批量上传相片 （支持的相片类型：<%=ext%>；单个相片最大不能超过：<%=perMaxSize%>
									<%=sizeUnit%>）</STRONG>
							</td>
							<td class="DH2" align="right"></td>
						</tr>
					</table>
					<div id="content">
						<form id="form1" action="UploadFileExampleSubmit.jsp"
							method="post" enctype="multipart/form-data">
							<table width="90%" cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td>
										<span id="spanButtonPlaceholder"></span>
										<input id="btnUpload" type="button" value="上传文件" class="btn" />
										<input id="btnCancel" type="button" value="取消全部上传" disabled="disabled" class="btn" />
										设置文件类型
										<select id="uploadType" name="uploadType">
											<option value="-1">
												请选择
											</option>
											<option value="6">
												免罚申请单
											</option>
										</select>
									</td>
								</tr>
							</table>
							<table id="idFileList" class="uploadFileList">
								<tr class="uploadTitle">
									<td width="150px">
										<B><input type="checkbox" id="selectAllFiles" /> 类型</B>
									</td>
									<td>
										<B>文件名</B>
									</td>
									<td width=100px>
										<B>文件大小</B>
									</td>
									<td width=100px>
										<B>状态</B>
									</td>
									<td width=100px>
										<B>操作</B>
									</td>
								</tr>
							</table>
							等待上传
							<span id="idFileListCount">0</span> 个 ，成功上传
							<span id="idFileListSuccessUploadCount">0</span> 个
							<div id="divSWFUploadUI" style="visibility: hidden;"></div>
							<noscript
								style="display: block; margin: 10px 25px; padding: 10px 15px;">
								很抱歉，相片上传界面无法载入，请将浏览器设置成支持JavaScript。
							</noscript>
							<div id="divLoadingContent" class="content"
								style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
								相片上传界面正在载入，请稍后...
							</div>
							<div id="divLongLoading" class="content"
								style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
								相片上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。
							</div>
							<div id="divAlternateContent" class="content"
								style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
								很抱歉，相片上传界面无法载入，请安装或者升级您的Flash插件。 请访问：
								<a
									href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
									target="_blank">Adobe网站</a> 获取最新的Flash插件。
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>