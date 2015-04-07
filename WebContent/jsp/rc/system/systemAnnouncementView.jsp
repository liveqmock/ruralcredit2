<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<script type="text/javascript">
		var serverName="<%=path%>";
	</script>
    <div class="easyui-tabs">
    <div title="内容">
	    <form id="saViewForm" >
	    	<input type="hidden" name="attachmentId" id="saViewAttachmentId" />
	    	<input type="hidden" name="imageNum" id="saViewImageNum" />
	    	<table>
	    		<tr style="height:40px;">
					<td width="70px" align="right">
						发布时间：
					</td>
					<td width="600px">
						<input style="border:none;" readonly="readonly" name="createTime" style="width:200px;" type="text" />
					</td>
				</tr>
	    		<tr style="height:40px;">
					<td width="70px" align="right">
						标题：
					</td>
					<td width="600px">
						<input readonly="readonly" name="title" style="width:300px;border:none;" type="text" />
					</td>
				</tr>
				<tr style="height:80px;">
					<td width="70px" align="right">
						内容：
					</td>
					<td>
						<textarea readonly="readonly" name="content" rows="7" cols="75" maxlength="100"></textarea>
					</td>
				</tr>
	    	</table>
	    </form>
	    </div>
				<div title="附件">
	    <iframe scrolling="auto" id='saViewOpenCM' frameborder="0" src="" style="width: 98%; height: 365px;"></iframe>
	    </div>
	    </div>
