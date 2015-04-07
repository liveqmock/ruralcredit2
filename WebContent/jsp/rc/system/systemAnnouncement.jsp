<%@ page language="java" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.rc.util.DESPlus,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String isURL = request.getRequestURL().toString();
	if(isURL.indexOf(".cn")>0){
		if(cmUrl.indexOf(".corp")>0){
			cmUrl=cmUrl.replaceAll(".corp",".cn");
		}
	}else if(isURL.indexOf(".corp")>0){
		if(cmUrl.indexOf(".cn")>0){
			cmUrl=cmUrl.replaceAll(".cn",".corp");
		}
	}
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
	DESPlus des = new DESPlus();
	String cmIp = properties.getProperty("cm.hostip");
	DESPlus desPlus = new DESPlus();
	String DESNow = desPlus.encrypt(new Date().getTime()+"");
	String DESIp = desPlus.encrypt(cmIp);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
	<jsp:include page="../include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
		var serverName="<%=path%>";
		var cmUrl = "<%=cmUrl%>";
		var userId = "<%=userId%>";
		var DESNow =  "<%=DESNow%>";
		var DESIp =  "<%=DESIp%>";
		//查看
		function saView(){
			var rows = $("#systemAnnouncement_itemList").datagrid("getSelected");
			var saId = rows.systemAnnouncementId;
			var dd1 = $("#systemAnnouncementDailog").dialog({
				title : "查看",
				modal : true,
				closed : true,
				inline : false,
				width: 1000,
				height: 470,
				border : false,
				draggable:true,
				dosize : true,
				buttons : [ {
					text : "关闭",
					iconCls : "icon-cancel",
					handler : function() {
						$("#systemAnnouncementDailog").dialog('close');
					}
				} ],
				href : serverName+"/jsp/rc/system/systemAnnouncementView.jsp",
				onLoad:function(){
					var paraValue = saId;
					ajaxSubmit(serverName+"/systemInfo/viewSystemAnnouncement.do",{param:paraValue},function(result){
						$("#saViewForm").form("load",result);
						var attachmentId = $("#saViewAttachmentId").val();
						var imageNum = $("#saViewImageNum").val();
						if(imageNum>0){
							var href = cmUrl+"/operation/transferParameter.action?clientId="+attachmentId+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime="+DESNow+"&signIp="+DESIp+"";
							$('#saViewOpenCM')[0].src = href;
						}
					});
				}
			});
			dd1.dialog('open');
		}
		//编辑
		function saEdit(){
			//清除上一条的id
			$("#editSystemAnnouncementId").val("");
			$("#editAttachmentId").val();
			var rows = $("#systemAnnouncement_itemList").datagrid("getSelected");
			var saId = rows.systemAnnouncementId;
			var dd1 = $("#systemAnnouncementDailog").dialog({
				title : "编辑",
				modal : true,
				closed : true,
				inline : false,
				width: 1000,
				height: 470,
				border : false,
				draggable:true,
				dosize : true,
				buttons : [ {
					text : "关闭",
					iconCls : "icon-cancel",
					handler : function() {
						$.messager.confirm("消息", "确定关闭窗口?", function(result) {
							if(result){
								$("#systemAnnouncementDailog").dialog('close');
							}
						});
					}
				} ],
				href : serverName+"/jsp/rc/system/systemAnnouncementEdit.jsp",
				onLoad:function(){
					var paraValue = saId;
					ajaxSubmit(serverName+"/systemInfo/viewSystemAnnouncement.do",{param:paraValue},function(result){
						$("#saEditForm").form("load",result);
						//var attachmentId = $("#editAttachmentId").val();
						//var href = cmUrl+"/operation/transferParameter.action?clientId="+attachmentId+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime="+DESNow+"&signIp="+DESIp+"";
						//$('#saEditCm').dialog('open');
					});
				}
			});
			dd1.dialog('open');
		}
		//删除
		function saDelete(){
			var rows = $("#systemAnnouncement_itemList").datagrid("getSelected");
			var saId = rows.systemAnnouncementId;
			//var saId = "00000";
			var title = rows.title;
			$.messager.confirm("消息", "确定删除标题为: "+title+" 的数据?", function(result) {
				if(result){
					ajaxSubmit(serverName+"/systemInfo/deleteSystemAnnouncement.do",{saId:saId},function(result){
						if(result.success){
							alert("删除成功!");
							$("#systemAnnouncement_itemList").datagrid("reload",{});
						}else{
							alert("删除失败!");
						}
					});
				}
			});
		}
		//列表条目操作项
		function optionFormatter(value,param) {
			var view = "<a href='javascript:saView();'>查看</a>";
			var edit = "<a href='javascript:saEdit();'>编辑</a>";
			var del = "<a href='javascript:saDelete();'>删除</a>";
			var blank = " ";
			return view+blank+edit+blank+del;
		}
		function addItem(){
			 $("#systemAnnouncement_addItem_dialog").dialog({
	            title:"新增系统公告",
	            close:true,
	            modal:true,
	            draggable:true
	         });
			 $("#systemAnnouncementId").val("");
			 $("#attachmentId").val("");
			 $("#title").val("");
			 $("#content").val("");
			 $("#saSaveButton").attr("style", "display:;");
			 $("#saUploadFile").attr("style", "display:none;");
			 $("#saSaveButton").linkbutton("enable");
			 //$("#saSaveButton").attr("disabled",false);
			 $("#systemAnnouncement_addItem_dialog").dialog("open"); 
		}
		$(function() {
			$("#systemAnnouncement_itemList").datagrid({
				//url : serverName + "/CustomerConsult/searchPagnation.do",
				url : serverName + "/systemInfo/searchPagnation.do",
				pagination : true,
				rownumbers : true,
				idfield : "systemAnnouncementId",
				queryParams:{},
				height:370,
				singleSelect : true,
				frozenColumns:[[{
					title : "操作",
					field : "no",
					width: 125,
					formatter:optionFormatter
				}]],
				columns : 
				[ [  
					{
						field : "systemAnnouncementId",
						title : "id",
						width: 100,
						sortable:true
					},{
						field : "title",
						title : "标题",
						width: 100,
						sortable:true
					}, {
						title : "内容",
						field : "content",
						width: 150,
						formatter:function(value){
							if(null!=value && value.length>20){
								return value.substring(0,20);
							}else{
								return value;
							}
						}
					},{
						title : "创建时间",
						field : "createTime",
						width: 125,
						sortable:true
					},{
						title : "更新时间",
						field : "updateTime",
						width: 125,
						sortable:true
					}
			   ] ]
			});
		    // 第一次加载时自动变化大小  
		    $('#customerComsult').resizeDataGrid(140, 20, 0, 0);			
		});
		//提交表单
		function saSubmitForm(){
			var url;
			if($("#via").attr("checked")=="checked"){
				url = serverName+"/systemInfo/addSystemAnnouncement.do?via=1";
			}else{
				url = serverName+"/systemInfo/addSystemAnnouncement.do?via=0";
			}
			$.messager.confirm("消息", "确定保存数据？", function(result) {
				if(result){
					var title = $("#title").val();
					var content = $("#content").val();
					if(title==""){
						alert("标题不能为空!");
						return;
					}else if(content==""){
						alert("内容不能为空!");
						return;
					}
					$("#saSaveButton").linkbutton("disable");
					var formId = "#systemAnnouncement_addItemForm";
					ajaxSubmit(url,$(formId).serialize(),function(result){
						var systemAnnouncementId = result.systemAnnouncementId;
						var attachmentId = "";
						if(result != null && result != undefined){
							if(systemAnnouncementId!=null){
								$.messager.show({
									msg:"数据保存成功！",
									title:"消息"
								});
								$("#saSaveButton").attr("style", "display:none;");
								attachmentId = result.attachmentId;
								//$("#saveSystemAnnouncementId").val(systemAnnouncementId);
								$("#saveAttachmentId").val(attachmentId);
								$("#saUploadFile").attr("style", "display:;");
							}
						}else{
							 alert("保存失败！系统错误，请与管理员联系！");
						 }
					});
				}
			});
		}
		//关闭窗口
		function saCloseWindow(){
			$.messager.confirm("消息", "确定关闭窗口?", function(result) {
				if(result){
					$("#systemAnnouncement_addItem_dialog").dialog("close");
					$("#systemAnnouncement_itemList").datagrid("load",{});
					$("#saUploadFile").attr("style", "display:none;");
					//将附件id置空
					$("#attachmentId").val("");
				}
			});
		}
		//刷新列表
		function saReflashForm(){
			$("#systemAnnouncement_itemList").datagrid("load",{});
		}
		/** 内容管理平台页面 * */
		function showUploadDig(){
			var url1 = serverName + "/creditapplication2Approval/getDate.do";
			ajaxSubmit(url1,function(result) {
				if(result){
					var signTime = result[0];	
					var signIp = result[1];
					var attachmentId = $("#saveAttachmentId").val();
					var href = cmUrl+"/jsp/creditease/operation/operationControl.jsp?clientId="+attachmentId+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID="+userId+"&type=3&signTime="+signTime+"&signIp="+signIp+"";
					var content = '<iframe scrolling="auto" id="openCM" frameborder="0"  src="'+href+'" style="width:100%;height:100%;">';
					$("#cm").dialog({
						content:content,
						title:"公告资料",
						modal:false,
						maximizable:true,
						closed:true, 
						width:1000,
						height:350
					});
					//$('#openCM')[0].src = href;
					$('#cm').dialog('open');
				}else{
					$.messager.alert("提示信息","服务异常，请稍后重试");
				}
			});
		}
		//限制内容字符串长度
		function saLimitContent(obj){
			if(obj.value.length>200) {
				obj.value = obj.value.substr(0,200);
			}
		}
	</script>
	<style type="test/css">
	
	</style>
  </head>
  <body class="easyui-layout" id="cc" class="easyui-layout">
    <div region="center" >
    	
		<div style="padding: 10px;margin-top:20px;">
			<div id="toolbar1">
            	<a href="javascript:void(0)" id="updateCreditApplication" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addItem();">新增</a> 
	        </div>
	    	<table id="systemAnnouncement_itemList" toolbar="#toolbar1" ></table>
    	</div>
		
		<div class="easyui-dialog" id="systemAnnouncement_addItem_dialog"  closed="true" style="width: 800px;height:350px;padding: 10px;top:40px;">
			<div><font id="display"></font></div>
			<form id="systemAnnouncement_addItemForm">
				<input type="hidden" id="saveSystemAnnouncementId" name="systemAnnouncementId"  />
				<input type="hidden" id="saveAttachmentId" name="attachmentId" />
				<table>
					<tr style="height:40px;">
						<td width="50px">
							标题：
						</td>
						<td width="600px">
							<input maxlength="30" id="title" name="title" style="width:200px;" type="text" />
							&nbsp;&nbsp;&nbsp;&nbsp;<input id="via" type="checkbox" value="1" checked="checked">红色显示
							<font color="red">&nbsp;(提示：保存成功后才可以上传附件.)</font>
						</td>
					</tr>
					<tr style="height:80px;">
						<td>
							内容：
						</td>
						<td>
							<textarea onkeyup="saLimitContent(this);"  id="content" name="content" rows="7" cols="75" maxlength="100"></textarea>
							<br/><font color="red">(提示：允许最大输入内容为200字符，超过的部分会被自动删除!)</font>
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td width="600px" style="padding-left:270px;">
							<a id="saUploadFile" style="display:none;" class="easyui-linkbutton" onclick="showUploadDig();" >
								附件
							</a>
							<a id="saSaveButton" class="easyui-linkbutton" onclick="saSubmitForm();" >
								保 存
							</a>
							<a class="easyui-linkbutton" onclick="saCloseWindow();" >
								关 闭
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
    </div>
    <div id="systemAnnouncementDailog"></div>
    <!-- 内容管理平台弹出框 -->
    <div id="cm"></div>
  </body>
</html>
