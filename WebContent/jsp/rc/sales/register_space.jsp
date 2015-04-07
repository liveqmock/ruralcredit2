<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils,com.creditease.rc.util.PropertiesUtil,
com.creditease.rc.util.DateUtil,com.creditease.rc.util.DESPlus" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
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
	String DESNow = desPlus.encrypt(new Date().getTime() + "");
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登记展业计划</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css">
    <jsp:include page="../include/easyui.jsp"></jsp:include>
    <script type="text/javascript">
        var serverName = "<%=path%>";
    $(function () {
		var dic = new DataDictionary();
		dic.addDic("spacestudit", "spacestudit");
		dic.dicAjax();
		//departmentComboboxTreeWithPanelWidth("branchName",false,250);
		//var ids=$("#branchName").combobox("getValues");
		 ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function (r) {
			 $("#areadepartmentid").combotree({
			 data : r,
			 	onSelect:function(data){
			 	var children = $("#areadepartmentid").tree("getChildren", data.target);
			 	$('#areadepartmentname').val(data.text);
			 	var citydepartmentnameNode = $("#areadepartmentid").tree("getParent",data.target);
			 	if(citydepartmentnameNode!=null && citydepartmentnameNode!=undefined){
			 		var regionnNode = $("#areadepartmentid").tree("getParent",citydepartmentnameNode.target);
			 		$('#citydepartmentname').val(citydepartmentnameNode.text);
			 		$('#citydepartmentid').val(citydepartmentnameNode.id);
			 		$('#regionname').val(regionnNode.text);
			 		//console.info($('#regionid').val(regionnNode.id));
			 		$('#regionid').val(regionnNode.id);
			 	}
			 	if (children.length) {
                    $.messager.alert('提示', '请选择营业部', 'info');
                    $("#areadepartmentid").combotree('clear');
                 }else{
                    $("#personid").combobox({
                            url: serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId=" + data.id,
                            valueField: 'LOAN_OFFICER_ID',
                            textField: 'LOAN_OFFICER_NAME'
                        });
                  }
	                           
			 	}
			 });
		 });
		 
		 $("#spacedate").datebox({
		 	onSelect : function(record){
		 			//var selectDate = record.toLocaleDateString();
		 			var selectYear = record.getFullYear();
		 			var selectMonth=record.getMonth()+1;
		 			var selectDay = record.getDate();
		 			//var nowDate = new Date().toLocaleDateString();
		 			if(selectYear==new Date().getFullYear()){
		 				if(selectMonth<(new Date().getMonth()+1)){
		 					alert("不能小于当前日期");
		 					$("#spacedate").datebox("setValue","");
		 				}else if(selectMonth==(new Date().getMonth()+1)){
		 					if(selectDay<new Date().getDate()){
		 						alert("不能小于当前日期");
		 						$("#spacedate").datebox("setValue","");
		 					}
		 				}
		 			}
		 			if(selectYear<new Date().getFullYear()){
							alert("不能小于当前日期");
		 					$("#spacedate").datebox("setValue","");		 			
		 			}
		 			
		 			/*console.info(nowDate);
		 			if(selectDate<nowDate){
		 				alert("不能小于当前日期");
		 				$("#spacedate").datebox("setValue","");
		 			}*/
		 	}
		 });
	});
function saveSpace(){
	window.parent.regSpaceGray();
	if ($("#spaceForm").form("validate")) {
	$("#personplanning").val($("#personid").combobox("getText"));
	var spacetime = $("#spacetime").datetimebox("getValue");//日期
	var regEx = new RegExp("\\-","gi");
    spacetime=spacetime.replace(regEx,"/");
	var nowDate = new Date(Date.parse(spacetime));
	var selectYear = nowDate.getFullYear();
	var selectMonth=nowDate.getMonth()+1;
	var selectDay = nowDate.getDate();
	var date=new Date();
	if(selectYear==date.getFullYear()){
		if(selectMonth<(date.getMonth()+1)){
			alert("计划展业时间不能小于当前时间");
			$("#spacetime").datetimebox("setValue","");
			window.parent.unRegSpaceGray();
			return;
		}else if(selectMonth==(date.getMonth()+1)){
			if(selectDay<date.getDate()){
				alert("计划展业时间不能小于当前时间");
				$("#spacetime").datetimebox("setValue","");
				window.parent.unRegSpaceGray();
				return;
			}else if(selectDay==date.getDate()){
				if(nowDate.toLocaleTimeString()<date.toLocaleTimeString()){
					alert("计划展业时间不能小于当前时间");
					$("#spacetime").datetimebox("setValue","");
					 window.parent.unRegSpaceGray();
					return;
				}
			}
		}
	}
	if(selectYear<date.getFullYear()){
		alert("计划展业时间不能小于当前时间");
		$("#spacetime").datetimebox("setValue","");
		window.parent.unRegSpaceGray();
		return;		 			
	}
		 			
		 			
		 			
		 			
	/*if(nowDate.toLocaleDateString()<date.toLocaleDateString()){
		if(nowDate.toLocaleTimeString()<date.toLocaleTimeString()){
			alert("计划展业时间不能小于当前时间");
			$("#spacetime").datetimebox("setValue","");
			 window.parent.unRegSpaceGray();
			return;
		}
	}*/
	var personid = $("#personid").combobox("getValues");
	if(personid.length<4){
		alert("人员规划不能少于4人");
		$("#personid").combobox("clear");
		 window.parent.unRegSpaceGray();
		return;
	}
	
	$.post(serverName + "/spaceController/saveSpace.do", $("#spaceForm").serialize(), function (result) {
            if (result.success) {
                parent.$.messager.show({
                    title: '消息',
                    msg: '操作成功',
                    timeout: 5000,
                    showType: 'slide'
                });
                parent.unSpace();
                parent.clearAll();
                parent.spaceSearch();

            } else {
                parent.$.messager.show({
                    title: '消息',
                    msg: result.msg,
                    timeout: 5000,
                    showType: 'slide'
                });
               // parent.unSpace();
               window.parent.unRegSpaceGray();
            }
        }, "json");
    } else {
        alert("请检查！必输项未填写或填写格式错误！");
        window.parent.unRegSpaceGray();
        return;
    }
}
	
    </script>
    <!--<script type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/saleInquireRegister.js"></script>
    --><script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>
</head>
<body class="easyui-layout" style="background:#fafafa; width:100%;height:100%;margin: 0px;padding: 0px;">
<div region="center" style="background:#fafafa; width:100%;height:100%;">
    <form action="" id="spaceForm">
        <table class="tabfrom" align="center" width="100%">
            <tbody>
            <tr height="30">
                <td align="right" width="20%"><font color="red"></font>计划展业日期：</td>
                <td width="30%">
                    <input id="spacedate" editable="false" class="easyui-datebox" type="text" name="spacedate"
                           required="required" style="width:152px;"/>
                </td>
                <td align="right" width="20%"><font color="red"></font>计划展业时间：</td>
                <td width="30%">
                <input class="easyui-datetimebox" id="spacetime" name="spacetime" style="height:16px;border:1px solid #A4BED4 ;width: 147px;" required="true"/>
            </tr>
            <tr height="30">
                <td align="right"><font color="red"></font>计划展业地点：</td>
                <td>
                    <input class="easyui-validatebox"   id="spaceplace"  name="spaceplace" type="text" style="width: 147px;" required="true"/>
                </td>
                <td align="right"><font color="red"></font>展业状态：</td>
                <td>
                    <input class="easyui-combobox" id="spacestudit" type="text" name="spacestudit"
                           style="width: 150px;" required="true"/>
                           <input id="regionname"  name ="regionname" type="hidden"/>
                           <input id="regionid"  name ="regionid" type="hidden"/>
                           <input id="citydepartmentname"  name ="citydepartmentname" type="hidden"/>
                           <input id="citydepartmentid"  name ="citydepartmentid" type="hidden"/>
                           <input id="areadepartmentname"  name ="areadepartmentname" type="hidden"/>
                           <input id="personplanning"  name ="personplanning" type="hidden"/>
                </td>
            </tr>
            <tr>
            	<td>
					分公司名称：
				</td>
				<td>
					<input name="areadepartmentid" id="areadepartmentid" style="width: 150px"/>
				</td>
				<td align="right"><font color="red"></font>人员规划：</td>
                <td>
                    <input class="easyui-combobox" id="personid" name="personid" type="text" id="consultWay"
                           name="consultWay" style="width:150px;" required="true" multiple="true" separator=","/>
                </td>
            </tr>
            <tr>
            	<td>
					预计客户咨询量：
				</td>
				<td colspan="3">
					<input class="easyui-numberbox" name="customerconsultcount" id="customerconsultcount" type="text" style="width: 50px" required="true"/>人
				</td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>