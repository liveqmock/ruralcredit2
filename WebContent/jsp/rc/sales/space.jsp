<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils,
	com.creditease.rc.util.PropertiesUtil,
com.creditease.rc.util.DateUtil,com.creditease.rc.util.DESPlus"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
	String downloadUrl =cmUrl.substring(0,cmUrl.lastIndexOf("/")+1)+"CreditCM";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>展业计划</title>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
       	var serverName = "<%=path%>";
		var downloadUrl="<%=downloadUrl%>"
        

    </script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/sales/space.js"></script>
	</head>

	<body class="easyui-layout">

		<div region="center" id="centerPanel">
			<div class="easyui-tabs" id="inquirePoolTab"
				data-options="plain:true" style="padding: 5px; width: auto; height: auto; margin-top: 5px; margin-bottom: 4px;">
				<div title="搜索条件" style="padding: 10px;" id="tabDiv1">
					<%--<legend>搜索</legend>--%>
					<table>
						<tbody>
							<tr>
								<td>
									分公司名称：
								</td>
								<td>
									<input name="areadepartmentid" id="areadepartmentid" style="width: 150px"/>
								</td>
								<td>
									计划展业日期：
								</td>
								<td>
									<input class="easyui-datebox" type="text" id="spacedate_begin"
										name="spacedate_begin" data-options="required:false"
										style="width: 150px;" editable="false" />
									-
									<input class="easyui-datebox" type="text" id="spacedate_end"
										name="spacedate_end" data-options="required:false"
										style="width: 150px;" editable="false" />
								</td>
								<td>
									展业状态：
								</td>
								<td>
									<select id="spacestudit" class="easyui-combobox"
										name="spacestudit" style="width: 150px;"
										data-options="required:false">
									</select>
								</td>
								<td>
									<a href="#" class="easyui-linkbutton"
										data-options="iconCls:'icon-search'"
										onclick="spaceSearch();">搜索</a>
									<a href="#" class="easyui-linkbutton"
										data-options="iconCls:'icon-reload'" onclick="clearAll()">清空</a>
								</td>
								
							</tr>
							<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								</tbody>
					</table>
				</div>
			</div>
			<div id="spaceList_Div" style="padding: 5px;" >
				<table id="spaceList" toolbar="#toolbar1"  class="easyui-datagrid">
				</table>
			</div>
		</div>
			<%--工具栏：登记客户咨询、导入Excel--%>
			<div id="toolbar1">
				<table >
					<tr>
						<td align="left">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-addOne" plain="true" onclick="registerSpace();">登记展业计划</a>
						</td>
						</tr>
				</table>
			</div>

			<%--新增展业计划--%>
			<div id="registerSpace"
				style="width: 600px; height: 400px; padding: 10px;"
				buttons="#opt_btn">
				<iframe id="registerSpaceFrame" name="registerSpaceFrame" src=""
					scrolling="yes" frameborder="0" style="width: 100%; height: 100%">
				</iframe>
				<div id="opt_btn" class="toolbar" style="text-align: right;">
					<a  id="registSave" class="easyui-linkbutton" iconCls="icon-ok"
						onclick="saveSpace();">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"
						onclick="unSpace();">取消</a>
				</div>
				
			</div>
			<%--执行展业计划--%>
			<div id="registerSpaceExecute"
				style="width: 800px; height: 500px; padding: 10px;"
				buttons="#edit_btn">
				<iframe id="registerSpaceExecuteFrame"
					name="registerSpaceExecuteFrame" src="" scrolling="yes"
					frameborder="0" style="width: 100%; height: 100%;">
				</iframe>
				<div id="edit_btn" class="toolbar">
					<a id="editCus" class="easyui-linkbutton" iconCls="icon-ok"
						onclick="saveExecuteSpace();">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"
						onclick="unExecuteSpace();">取消</a>
				</div>
			</div>
			
			<%--延期展业计划--%>
			<div id="delaySpace"
				style="width: 400px; height: 350px; padding: 10px;"
				buttons="#delaySpaceButtonDiv">
				<iframe id="delaySpaceFrame" name="delaySpaceFrame" src=""
					scrolling="yes" frameborder="0" style="width: 100%; height: 100%">
				</iframe>
				<div id="delaySpaceButtonDiv" class="toolbar" style="text-align: right;">
					<a  id="delaySpaceButton" class="easyui-linkbutton" iconCls="icon-ok"
						onclick="delaySpaceSave();">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"
						onclick="undelaySpaceSave();">取消</a>
				</div>
			</div>
			</form>
			</div>
		
	</body>
</html>





