<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.creditease.rc.domain.SystemAnnouncement"%>
<%@page import="com.creditease.rc.util.PropertiesUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
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


	List<SystemAnnouncement> sAnnouncements = (List<SystemAnnouncement>)request.getAttribute("sAnnouncements");
	int sASize = sAnnouncements.size();
	String cmIp = properties.getProperty("cm.hostip");
	DESPlus desPlus = new DESPlus();
	String DESNow = desPlus.encrypt(new Date().getTime()+"");
	String DESIp = desPlus.encrypt(cmIp);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<jsp:include page="../include/easyui.jsp"></jsp:include>
<jsp:include page="../include/easyui-portal.jsp"></jsp:include>
<title></title>
<meta content="text/html;" http-equiv=Content-Type />
<style type=text/css>
.shouye {
		background-image:url(<%=basePath%>images/background.jpg);
		background-repeat:no-repeat;
		background-attachment:fixed;
		background-position:center;
	}
#demo a {
	width:100%;
	overflow:hidden;
	font:12px/16px tahoma; /* 12px/16px代表 字体大小以及行高 */
	display:block;
	text-decoration:none;
	margin:2px;
	color:#4a551c;
	padding-left:2px;
	text-align:left;
}
#demo a:hover {
	color:#ff6600;
}
</style>
<script type="text/javascript">
	var serverName="<%=path%>";
	var cmUrl = "<%=cmUrl%>";
	var DESNow =  "<%=DESNow%>";
	var DESIp =  "<%=DESIp%>";
	var sAnnoucementSize = <%=sASize%>;
	function changeHeight(){
		if(sAnnoucementSize<10){
			var height = sAnnoucementSize*8;
			document.getElementById('demo').style.height = height+"px";
		}
	}
	function showMoreSystemAnnouncement(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName + "/jsp/rc/system/systemAnnouncementList.jsp",
			title : "公告"
		});
	}
	//查看公告
	function toViewSystemAnnouncement(id){
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
				var paraValue = id;
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
</script>
</head>
<body class="easyui-layout" fit="true" >
	<div id="shouye" class="shouye" region="center" border="false">
		<!-- 公告滚动显示 -->
		<div style="margin-left:700px;">
			<div style="margin-left:190px;margin-top:20px;">公告栏</div>
			<div style="margin-left:400px;"><a href="javascript:showMoreSystemAnnouncement();">更多</a></div>
			<% 	
				//超过一定条目的数据才滚动
				if(sASize>6){
			%>
				<div id="demo" style="overflow:hidden;height:110px;width:440px; border:1px solid #dde5bc; ">
					<div id="demo1">
						<% 
							for(int i=0;i<sASize;i++){
								SystemAnnouncement s = sAnnouncements.get(i);
								String id = s.getSystemAnnouncementId().toString();
								String title = s.getTitle();
								String createTime = s.getCreateTime().toLocaleString();
								String content = s.getContent();
								if(title.length()>5){
									title = title.substring(0,5);
								}
								if(content.length()>18){
									content = content.substring(0,18)+"...";
								}
						%>
							<div style="margin-top:10px;">
								<a href="javascript:toViewSystemAnnouncement(<%=id %>)" style="text-decoration:none;">
									<%= title %>
									&nbsp;&nbsp;
									<font><%= content %></font>
									&nbsp;&nbsp;
									<%= createTime %>
								</a>
							</div>
						<% 
							}
						%>
					</div>
					<div id=demo2></div>
				</div>
			<% 
				}else{
			%>
				<div style="border:solid 1px;height:165px;width:440px;">
					<% 
						for(int i=0;i<sASize;i++){
							SystemAnnouncement s = sAnnouncements.get(i);
							String id = s.getSystemAnnouncementId().toString();
							String title = s.getTitle();
							String createTime = s.getCreateTime().toLocaleString();
							String content = s.getContent();
							if(title.length()>5){
								title = title.substring(0,5);
							}
							if(content.length()>18){
								content = content.substring(0,18)+"...";
							}
					%>
						<div style="margin-top:10px;">
							<a href="javascript:toViewSystemAnnouncement(<%=id %>)" style="text-decoration:none;">
									<%= title %>
									&nbsp;&nbsp;
									<font><%= content %></font>
									&nbsp;&nbsp;
									<%= createTime %>
							</a>
						</div>
					<% 
						}
					%>
				</div>
			<% 
				}
			%>
		</div>
		<!-- 公告滚动显示 -->
		
		<!-- 查看公告信息 -->
		<div id="systemAnnouncementDailog"></div>
		
	</div>
	
<script>
	var speed = 40;
	var demo = document.getElementById("demo");
	var demo2 = document.getElementById("demo2");
	var demo1 = document.getElementById("demo1");
	demo2.innerHTML = demo1.innerHTML;
	function Marquee(){
		if(demo2.offsetTop-demo.scrollTop<=0){
			demo.scrollTop-=demo1.offsetHeight;
		}else{
			demo.scrollTop++;
		}
	}
	var MyMar = setInterval(Marquee,speed);
	demo.onmouseover=function() {clearInterval(MyMar);};
	demo.onmouseout=function() {MyMar=setInterval(Marquee,speed);};
</script>
</body>
</html>