<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'website.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript"
			src="<%=basePath%>scripts/approval/website.js"></script>
	<script type="text/javascript">
	var serverName = "<%=path%>";
	 function importExcel() {
         $('#uploadFileForm').form('submit', {
             url: serverName + '/websiteController/uploadExcel.do',
             type: "post",
             onSubmit: function () {
                 var fileName = $('#File1').val();
                 if (fileName == '') {
                     $.messager.alert('提示', '请选择需要上传的文件', 'info');
                     return false;
                 }
                 if (fileName != '') {
                     if (fileName.indexOf('.xlsx') == -1 && fileName.indexOf('.xls') == -1) {
                         $.messager.alert('提示', '文件格式不正确，请选择正确的Excel文件', 'info');
                         return false;
                     }
                 }
                 return true;
             },
             success: function (data) {
            	 console.info(data);
            	 console.info(data);
                 if (data.indexOf("成功上传") > 0) {
                     var dataSuccessMes ="上传成功！";
                     $.messager.show({
                         showType: "show",
                         timeout: 2000,
                         title: '消息',
                         height: 150,
                         width: 300,
                         msg: dataSuccessMes
                     });
                 } else {
                     var dataMes ="上传失败！";
                     $.messager.show({
                         showType: "show",
                         timeout: 50000,
                         title: '消息',
                         height: 150,
                         width: 300,
                         msg: dataMes
                     });
                 }
                 $('#importExcelDiv').dialog('close');
             }
         });
         //清空上传文件信息显示
         $("#File1").val('');
     }
	
	</script>
  </head>
  
  <body class="easyui-layout">

     <%--导入Excel--%>
     <div region="center" id="centerPanel">
			<div id="toolbar1">
				<table border="0">
					<tr>
						<td align="left">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-addOne" plain="true" onclick="importExcelDialog()">导入Excel</a>
						</td>
					</tr>			
					<tr>
						<td align="left">
							<a id="aOldDataCalc" href="javascript:caclMoney()" class="easyui-linkbutton"
								 plain="true" >计算旧数据</a>
						</td>
					</tr>
					<tr>
						<td align="left">
							<a id="aOldDataCalc0509Before" href="javascript:caclMoney0509Before()" class="easyui-linkbutton"
								 plain="true" >计算旧数据(在05-09之前做的单子)</a>
						</td>
					</tr>					
				</table>
			</div>
			</div>
			
			<%--导入 Excel--%>
			<div id="importExcelDiv" style="width: 500px; height: 130px;">
				<form id="uploadFileForm" action="" method="post"
					enctype="multipart/form-data" runat="server">
					<table style="width: 100; height: 100%;">
						<tr style="height: 25px;" >
							<td colspan="2">
								批量上传网点详情信息（请上传Excel文件）
							</td>
						</tr>
						<tr style="height: 50px;">
							<td align="center">
								<input id="File1" runat="server" name="UpLoadFile" type="file" />
							</td>
							<td>
								<input id="button" type="button" value="上 传"
									onclick="importExcel();" />
							</td>
						</tr>
					</table>
				</form>
			</div>
  </body>
</html>
