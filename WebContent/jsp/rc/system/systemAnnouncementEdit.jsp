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
	
	<style type="test/css">
	
	</style>
  </head>
  <body class="easyui-layout" id="cc" class="easyui-layout">
  	<script type="text/javascript">
		var serverName="<%=path%>";
		var cmUrl = "<%=cmUrl%>";
		var userId = "<%=userId%>";
		var DESNow =  "<%=DESNow%>";
		var DESIp =  "<%=DESIp%>";
		//提交编辑
		function saEditSubmitForm(){
			var url;
			if($("#vie").attr("checked")=="checked"){
				url = serverName+"/systemInfo/updateSystemAnnouncement.do?via=1";
			}else{
				url = serverName+"/systemInfo/updateSystemAnnouncement.do?via=0";
			}
			$.messager.confirm("消息", "确定更新数据？", function(result) {
				if(result){
					var title = $("#saTitle").val();
					var content = $("#saContent").val();
					if(title==""){
						alert("标题不能为空!");
						return;
					}else if(content==""){
						alert("内容不能为空!");
						return;
					}
					var formId = "#saEditForm";
					ajaxSubmit(url,$(formId).serialize(),function(result){
						//alert(result);
						if(result.success){
							alert("更新成功");
							$("#systemAnnouncementDailog").dialog("close");
							$("#systemAnnouncement_itemList").datagrid("reload",{});
						}else{
							$.messager.alert("操作失败,请检查是否有空值!");
						}
					});
				}
			});
		}
		//关闭窗口
		function saEditCloseWindow(){
			$.messager.confirm("消息", "确定关闭窗口?", function(result) {
				if(result){
					$("#systemAnnouncementDailog").dialog("close");
					$("#editSystemAnnouncementId").val("");
					$("#editAttachmentId").val();
				}
			});
		}
		
		/** 内容管理平台页面 * */
		function showUploadDigEdit(){
			var url1 = serverName + "/creditapplication2Approval/getDate.do";
			ajaxSubmit(url1,function(result) {
				if(result){
					var signTime = result[0];	
					var signIp = result[1];
					var editAttachmentId = $("#editAttachmentId").val();
					var href = cmUrl+"/jsp/creditease/operation/operationControl.jsp?clientId="+editAttachmentId+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID="+userId+"&type=3&signTime="+signTime+"&signIp="+signIp+"";
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
    <div region="center" style="margin-left:20px;">
	    <form id="saEditForm" action="">
	    	<input type="hidden" name="systemAnnouncementId" id="editSystemAnnouncementId"/>
	    	<input type="hidden" name="attachmentId" id="editAttachmentId" />
	    	<table>
	    		<tr style="height:40px;">
					<td>
						标题：
					</td>
					<td width="600px">
						<input maxlength="30" id="saTitle" name="title" style="width:200px;" type="text" />
						&nbsp;&nbsp;&nbsp;&nbsp;<input id="vie" type="checkbox" value="1" checked="checked">红色显示
					</td>
				</tr>
				<tr style="height:80px;">
					<td>
						内容：
					</td>
					<td>
						<textarea id="saContent" name="content" onkeyup="saLimitContent(this);" rows="7" cols="75" maxlength="100"></textarea>
						<br/><font color="red">(提示：允许最大输入内容为200字符，超过的部分会被自动删除!)</font>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td width="600px" style="padding-left:290px;">
						<a id="saEidtUploadFile" class="easyui-linkbutton" onclick="showUploadDigEdit();">
							附 件
						</a>
						<a class="easyui-linkbutton" onclick="saEditSubmitForm();" >
							更 新
						</a>
						<a class="easyui-linkbutton" onclick="saEditCloseWindow();" >
							关 闭
						</a>
					</td>
				</tr>
	    	</table>
	    </form>
    </div>
    <!-- 内容管理平台弹出框 -->
    <div id="saEditCm"></div>
  </body>
</html>
