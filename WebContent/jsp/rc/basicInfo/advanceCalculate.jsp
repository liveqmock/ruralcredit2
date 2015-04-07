<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../include/easyui.jsp"></jsp:include>
<head>
	<title>逾期客户的逾期费用计算</title>
	<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/calculatedRate.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
	<script type="text/javascript">
	var serverName="<%=path%>";
	var departMentId = "<%=SpringSecurityUtils.getCurrentUser().getDepartmentId()%>";
		$(function(){
			$("#returnPlan").datagrid({
				columns:[[
				       {field:"period",title:"分期数",width:100},   
				       {field:"repaymentDate",title:"当期还款日期",width:100},
				       {field:"currMonPrincipal",title:"应还本金",width:100},
				       {field:"currMonInterest",title:"应还利息",width:100},
				       {field:"currMonManageFree",title:"应还服务费",width:100},
				       {field:"currAccountTotal",title:"应还总额",width:100},
				       {field:"allHeadMoney",title:"提前还款金额",width:100}
				       ]]
			});
			
			if(departMentId == undefined || departMentId == null || departMentId== ""){
				$("#company").attr("readonly",false);
				$("#tips").html("请手动输入部门id");
			}else{
				$("#productInfo").combobox({
					url:serverName+"/PdfController/getProductList.do?departMentId="+departMentId,
					textField : 'productName',
					valueField : 'productInfoId',
					mode : 'remote',
					width:"122",
					onSelect:function(data){
						$("#periodCount").val(data.instalments);
						$("#repaymentType").val(data.repaymentType);
						$("#productInfoId").val(data.productInfoId);
						$("#capitalLowerLimit").val(data.capitalLowerLimit);
						$("#capitalUpperLimit").val(data.capitalUpperLimit);
						$("#tips").html("该产品的上线金额：<font color='red'>"+data.capitalUpperLimit+"</font>,下线：<font color='red'>"+data.capitalLowerLimit+"</font>");
					}
				});
			}
					
		});
		
		function searchByParam(){
			var capitalUpperLimit = $("#capitalUpperLimit").val();
			var capitalLowerLimit = $("#capitalLowerLimit").val();
			var ContractMoney = $("#contractMoney").val();
			if(ContractMoney == undefined || ContractMoney == null || ContractMoney==""){
				alert("填写合同金额");
				return;
			}
			if(accSubtr(ContractMoney,capitalUpperLimit) > 0|| accSubtr(capitalLowerLimit,ContractMoney ) > 0){
				alert("合同金额超出上线或下线的限制");
				return;
			}
			var lenderDate = $("#calcDate").datebox("getValue");
			if(lenderDate == undefined || lenderDate == null ||lenderDate ==""){
				alert("请填写放款日期");
				return;
			}
			
			var departmentId = $("#company").val();
			if(departmentId == undefined || departmentId == null ||departmentId ==""||departmentId =="请选择"){
				alert("请选择分公司");
				return;
			}
			
			var productInfoId = $("#productInfoId").val();
			if(productInfoId == undefined || productInfoId == null ||productInfoId ==""){
				alert("请填写产品编号");
				return;
			}
			
			var repaymentType = $("#repaymentType").val();
			if(repaymentType == undefined || repaymentType == null ||repaymentType ==""){
				alert("请填写产品分类");
				return;
			}
			
			
			var periodCount = $("#periodCount").val();
			if(periodCount == undefined || periodCount == null ||periodCount ==""||periodCount =="请选择"){
				alert("请填写分期数");
				return;
			}
			ajaxSubmit(serverName+"/PdfController/advanceReturnPlanResult.do",
					{departmentId:departmentId,
					ContractMoney:ContractMoney,
					lenderDate:lenderDate,
					productInfoId:productInfoId,
					repaymentType:repaymentType,
					periodCount:periodCount},
					function(result){
					$("#serviceCharge").val(result.serviceCharge);
					$("#actualAmount").val(result.actualAmount);
					$("#returnPlan").datagrid("loadData",result.returnPlanList);
					
			});
		}
		
		function showProduct(){
			var departMentIdCompany = $("#company").val();
			$("#productInfo").combobox({
				url:serverName+"/PdfController/getProductList.do?departMentId="+departMentIdCompany,
				textField : 'productName',
				valueField : 'productInfoId',
				mode : 'remote',
				width:"122",
				onSelect:function(data){
					$("#periodCount").val(data.instalments);
					$("#repaymentType").val(data.repaymentType);
					$("#productInfoId").val(data.productInfoId);
					$("#capitalLowerLimit").val(data.capitalLowerLimit);
					$("#capitalUpperLimit").val(data.capitalUpperLimit);
					$("#tips").html("该产品的上线金额：<font color='red'>"+data.capitalUpperLimit+"</font>,下线：<font color='red'>"+data.capitalLowerLimit+"</font>");
				}
			});
		}
	</script>
</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow: hidden;">
			<div id="toolbar" class="datagrid-toolbar" style="height: auto;">
				<fieldset>
					<legend>查询条件</legend>
					<table id="conditionTable" class="tableForm" style="height:auto;padding-left: 20px" cellspacing="10px" cellpadding="0">
							 <tr>
					       <td colspan="6">若是没有产品信息，请填写正确"部门id"<a class="easyui-linkbutton" onclick="showProduct();" plain="true">点这里获取产品</a>
						   	 	<span id="tips"></span>
						   	 <input id="productInfoId" type="hidden"/>
						   	 <input id="repaymentType" type="hidden"/>	
						   	 <input id="periodCount"  type="hidden"/>
						   	 </td>
					       </tr>
						    <tr>
						   	 <td>部门id</td>
						   	 <td>
						   	 <input id="company"  value="<%=SpringSecurityUtils.getCurrentUser().getDepartmentId()%>"/>
						   	 </td>
						      <td>产品</td>
						      <td>
						      <input id="productInfo" class="easyui-combobox" editable="false"/>
						       <input id="capitalUpperLimit" type="hidden"/><input id="capitalLowerLimit" type="hidden"/> 
						      </td>
						   	 <td>
								  <a id="btn" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchByParam()">计算</a>
							 </td>
					       </tr>
					       <tr>  
						      <td>合同金额</td><td><input id="contractMoney" /></td>
						      <td>放款日期</td><td><input id="calcDate" class="easyui-datebox"/></td>
						      <td>
								  <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clearFun();">清空</a>
							  </td>
						    </tr>
							 
					</table>
				</fieldset>
			     <div style="padding:10px;"> 
				            前期服务费：<input id="serviceCharge"/>
					  实际到手金额:<input id="actualAmount"/>  
					  </br>
					  </div>
					<div  style="padding:10px;">
			         <table id="returnPlan" style="height: 280px;"></table>
			       </div>
			</div>
			
		</div>
		
	</body>
</html>


