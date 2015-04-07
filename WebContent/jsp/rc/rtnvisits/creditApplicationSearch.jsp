<%@ page language="java"  import="java.util.*,com.creditease.rc.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String telNumberString = request.getParameter("telNumber");
String signString = request.getParameter("sign");
String pageSizeString = request.getParameter("pageSize");
StringBuffer conditions=new StringBuffer("");
if(CommonUtil.isNotEmpty(telNumberString)){
	conditions.append("?telNumber="+telNumberString);
}
if(CommonUtil.isNotEmpty(signString)){
	conditions.append("&sign="+signString);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>通过姓名、身份证号查询与之关联的申请</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		var serverName="<%=path%>";
		var conditions = "";
		<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
			conditions="<%=conditions.toString()%>";
		<%}%>
		var pageSizeS = "10";
		var pagelist = "[10,20,30,40,50]";
		<% if(CommonUtil.isNotEmpty(pageSizeString)
			){%>
			pageSizeS = "<%=pageSizeString%>";
			if(pageSizeS >10 && pageSizeS < 20){
				pageSizeS = 20;
			}
			if(pageSizeS >20 && pageSizeS < 30){
				pageSizeS = 30;
			}
			if(pageSizeS >30 && pageSizeS < 40){
				pageSizeS = 40;
			}
			if(pageSizeS >40 && pageSizeS < 50){
				pageSizeS = 50;
			}
			if(pageSizeS >50){
				 pagelist = "[10,20,30,40,50,"+pageSizeS+"]"; 
			}
		<%}%>
		pagelist  = JSON.parse(pagelist);
		$(function(){
			falg = false;
			$("#creditApplications").datagrid({
				url:serverName+"/creditgroup/creditApplicationSearch.do"+conditions,
				rownumbers:true,
				pagination:true,
				pageList: pagelist,
				pageSize: pageSizeS,
				columns:[[
				          {field:"businessnumber",title:"业务编号",width:"130"},
				          {field:"borrower",	title:"借款人姓名",width:"100"},
				          {field:"auditstatusShow", title:"业务状态",width:"100"},
				          {field:"company",		title:"所在分公司",width:"150"},
				          {field:"createloanofficername",title:"创建客户经理",width:"100"},
				          {field:"loanofficername",title:"当前客户经理",width:"100"},
				          {field:"borrowmoney", title:"借款金额",width:"100"},
				          {field:"signagreementdate", title:"放款日期",width:"100"},
				          {field:"partner", title:"共借人姓名",width:"100"},
				          {field:"guarantor", title:"担保人姓名",width:"150",formatter:function(value,param,index){if(value!= null && value.indexOf(",") == value.length-1){return value.substring(0,value.length-1)}else{return value;}}},
				          ]],
	          	onLoadSuccess:function(data){
					if(falg){
						if(data.total == 0){
							alert("抱歉没有符合条件的记录");
						}
					}
				}
			});
			
		});
		
		function returnColor(value,param,rowindex){
			if(value != null  && value != undefined && value != "" && value.indexOf("red") > -1){
			//	return "color:red;";
			}
		}
		function searchBusiness(){
			falg = true;
			var name = $.trim($("#name").val());
			var identityNumber = $.trim($("#identityNumber").val());
			if(name == "" && identityNumber == ""){
				alert("请输入至少一个查询条件");
			}else{
				$("#creditApplications").datagrid("load",{name:name,identityNumber: identityNumber,telNumber:"",sign:""});
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout">
    	<div region="center">
    		<div class="easyui-tabs" style="padding: 5px;">
    			<div id="" title="搜索条件">
    				<div style="margin: 5px;">
		    			姓名：<input id="name"/>
		    			身份证号：<input  id="identityNumber" size="25"/>
		    			<font color="red">(保证号码正确才能查询到数据)</font>
		    			<a class="easyui-linkbutton"  onclick="searchBusiness();">查询</a>
		    		</div>
    			</div>
    		</div>
    		<div style="padding: 5px;">
	    		<table title="业务列表" id="creditApplications" toolbar="#tableToobar" >
	    		</table>
    		</div>
    	</div>
  </body>
</html>
