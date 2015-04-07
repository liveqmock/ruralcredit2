<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils,
	com.creditease.rc.util.PropertiesUtil,
com.creditease.rc.util.DateUtil,com.creditease.rc.util.DESPlus" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String cmIp = properties.getProperty("cm.hostip");
	String isURL = request.getRequestURL().toString();
	if (isURL.indexOf(".cn") > 0) {
		if (cmUrl.indexOf(".corp") > 0) {
			cmUrl = cmUrl.replaceAll(".corp", ".cn");
		}
	} else if (isURL.indexOf(".corp") > 0) {
		if (cmUrl.indexOf(".cn") > 0) {
			cmUrl = cmUrl.replaceAll(".cn", ".corp");
		}
	}
	DESPlus desPlus = new DESPlus();
	long nowTime = System.currentTimeMillis();
	String DESNow = desPlus.encrypt(nowTime + "");
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
	String DESIp = desPlus.encrypt(cmIp);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>执行展业计划</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css">
    <jsp:include page="../include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
    $(function () {
		 ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function (r) {
			 $("#areadepartmentid").combotree({
			 data : r,
			 	onSelect:function(data){
			 	var children = $("#areadepartmentid").tree("getChildren", data.target);
			 	$('#areadepartmentname').val(data.text);
			 	var citydepartmentnameNode = $("#areadepartmentid").tree("getParent",data.target);
			 	if (children.length) {
                    $.messager.alert('提示', '请选择营业部', 'info');
                    $("#areadepartmentid").combotree('clear');
                 }else{
                    $("#participantsidReality").combobox({
                            url: serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId=" + data.id,
                            valueField: 'LOAN_OFFICER_ID',
                            textField: 'LOAN_OFFICER_NAME'
                        });
                  }
	                           
			 	}
			 });
		 });
		 
		 
		 $("#spacedateReality").datebox({
		 	onSelect : function(record){
		 	
		 			var selectYear = record.getFullYear();
		 			var selectMonth=record.getMonth()+1;
		 			var selectDay = record.getDate();
		 			//var nowDate = new Date().toLocaleDateString();
		 			if(selectYear==new Date().getFullYear()){
		 				if(selectMonth<(new Date().getMonth()+1)){
		 					alert("不能小于当前日期");
		 					$("#spacedateReality").datebox("setValue","");
		 				}else if(selectMonth==(new Date().getMonth()+1)){
		 					if(selectDay<new Date().getDate()){
		 						alert("不能小于当前日期");
		 						$("#spacedateReality").datebox("setValue","");
		 					}
		 				}
		 			}
		 			if(selectYear<new Date().getFullYear()){
						alert("不能小于当前日期");
		 				$("#spacedateReality").datebox("setValue","");		 			
		 			}
		 	
		 			/*var selectDate = record.toLocaleDateString();
		 			var nowDate = new Date().toLocaleDateString();
		 			if(selectDate<nowDate){
		 				alert("不能小于当前日期");
		 				$("#spacedateReality").datebox("setValue","");
		 			}*/
		 	}
		 });
		 
	});
function saveExecuteSpace(){
	window.parent.exeSpaceGray();
	if ($("#executeSpaceForm").form("validate")) {
	var spacetimeReality = $("#spacetimeReality").datetimebox("getValue");//日期
	var regEx = new RegExp("\\-","gi");
    spacetimeReality=spacetimeReality.replace(regEx,"/");
	var nowDate = new Date(Date.parse(spacetimeReality));
	var selectYear = nowDate.getFullYear();
	var selectMonth=nowDate.getMonth()+1;
	var selectDay = nowDate.getDate();
	var date=new Date();
	if(selectYear==date.getFullYear()){
		if(selectMonth<(date.getMonth()+1)){
			alert("计划展业时间不能小于当前时间");
			$("#spacetimeReality").datetimebox("setValue","");
			window.parent.unExeSpaceGray();
			return;
		}else if(selectMonth==(date.getMonth()+1)){
			if(selectDay<date.getDate()){
				alert("计划展业时间不能小于当前时间");
				$("#spacetimeReality").datetimebox("setValue","");
				window.parent.unExeSpaceGray();
				return;
			}else if(selectDay==date.getDate()){
				if(nowDate.toLocaleTimeString()<date.toLocaleTimeString()){
					alert("计划展业时间不能小于当前时间");
					$("#spacetimeReality").datetimebox("setValue","");
					window.parent.unExeSpaceGray();
					return;
				}
			}
		}
	}
	if(selectYear<date.getFullYear()){
		alert("计划展业时间不能小于当前时间");
		$("#spacetimeReality").datetimebox("setValue","");
		window.parent.unExeSpaceGray();
		return;	 			
	}
	/*if(nowDate.toLocaleDateString()<date.toLocaleDateString()){ 
		if(nowDate.toLocaleTimeString()<date.toLocaleTimeString()){
			alert("计划展业时间不能小于当前时间");
			$("#spacetimeReality").datetimebox("setValue","");
			window.parent.unExeSpaceGray();
			return;
		}
	}*/
	var personid = $("#participantsidReality").combobox("getValues");
	if(personid.length<4){
		alert("实际人员不能少于4人");
		$("#participantsidReality").combobox("clear");
		 window.parent.unExeSpaceGray();
		return;
	}
	//添加验证上传照片
	ajaxSubmit(serverName+"/creditgroup/getImgAmount.do","post",{creditAppId:$("#spaceid").val()},function(r){
			if(r>=3&&r<=5){
				$("#participantsReality").val($("#participantsidReality").combobox("getText"));
				$("#photocount").val(r);
				$.post(serverName + "/spaceController/saveExecuteSpace.do", $("#executeSpaceForm").serialize(), function (result) {
		            if (result.success) {
		                parent.$.messager.show({
		                    title: '消息',
		                    msg: result.msg,
		                    timeout: 5000,
		                    showType: 'slide'
		                });
		                parent.unExecuteSpace();
		                parent.clearAll();
		                parent.spaceSearch();
						window.location.href=serverName + "/spaceController/downloadExcel.do";	
		            } else {
		                parent.$.messager.show({
		                    title: '消息',
		                    msg: result.msg,
		                    timeout: 5000,
		                    showType: 'slide'
		                });
		                window.parent.unExeSpaceGray();
		                //parent.unSpace();
		            }
		        }, "json");
				
			}else{
				alert("照片数量为不低于3张，不多于5张");
				window.parent.unExeSpaceGray();
				return;
			}
		},false);
	
    } else {
        alert("请检查！必输项未填写或填写格式错误！");
        window.parent.unExeSpaceGray();
        return;
    }
}
	
    </script>
    <!--<script type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/saleInquireRegister.js"></script>
    --><script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>
</head>
<body class="easyui-layout" style="background:#fafafa; width:100%;height:100%;margin: 0px;padding: 0px;">
<div region="center" style="background:#fafafa; width:100%;height:100%;">
    <form action="" id="executeSpaceForm">
        <table class="tabfrom" align="center" width="100%">
            <tbody>
            <tr height="30">
                <td align="right" width="20%"><font color="red">*</font>实际展业日期：</td>
                <td width="30%">
                    <input id="spacedateReality" editable="false" class="easyui-datebox" type="text" name="spacedateReality"
                           required="required" style="width:152px;"/>
                </td>
                <td align="right" width="20%"><font color="red">*</font>实际展业时间：</td>
                <td width="30%">
                <input class="easyui-datetimebox" id="spacetimeReality" name="spacetimeReality" style="height:16px;border:1px solid #A4BED4 ;width: 147px;" required="true"/>
            </tr>
            	<td>
					分公司名称：
				</td>
				<td>
					<input name="areadepartmentid" id="areadepartmentid" style="width: 150px"/>
				</td>
				<td align="right"><font color="red">*</font>实际参与人员：</td>
                <td>
                    <input class="easyui-combobox" id="participantsidReality" name="participantsidReality" type="text"
                            style="width:150px;" required="true" multiple="true" separator=","/>
                            <input id="participantsReality"  name ="participantsReality" type="hidden"/>
                            <input id="photocount" name="photocount" type="hidden" />
                            <input type="hidden" id="spaceid" name="spaceid" value="${value}"/>
                </td>
            </tr>
             <tr height="30">
                <td align="right"><font color="red"></font>实际展业地点：</td>
                <td >
                    <input class="easyui-validatebox"   id="spaceplaceReality"  name="spaceplaceReality" type="text" style="width: 147px;" required="true"/>
                </td>
                <td align="right"><font color="red"></font>实际客户量：</td>
                <td >
                    <input class="easyui-numberbox"   id="customerconsultcountReality"  name="customerconsultcountReality" type="text" style="width: 50px;" required="true"/>人
                </td>
               </tr>
            <tr>
            <tr>
                <td align="right" >文件上传：</td>
                <td height="300px" colspan="3">
								<iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/jsp/creditease/operation/operationControl.jsp?clientId=${value2}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=<%=userId%> 
				&type=3&signTime=<%=DESNow%>&signIp=<%=DESIp%>" style="width: 100%; height: 100%;" />
							<c:if test="${add==0}"> </c:if>
							<iframe scrolling="auto" id="openCM" frameborder="0" src="<%=cmUrl%>/operation/transferParameter.action?clientId=${value2}&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529" style="width: 100%; height: 300px;"></iframe>
						</td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>