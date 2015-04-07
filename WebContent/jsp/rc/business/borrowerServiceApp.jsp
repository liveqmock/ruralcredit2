<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
java.util.Date currentTime = new java.util.Date();
String str_dataFormatter = formatter.format(currentTime);
%>
 <script type="text/javascript">
var serverName="<%=path%>";
var sysDate="<%=str_dataFormatter%>";
paramValidate=false;
$(function(){
	var dicBorrower = new DataDictionary();
	dicBorrower.addDic("maritalStatusBorrower_borrowerService.maritalStatus_Y", "maritalStatus");
	dicBorrower.addDic("jobStatusBorrower_borrowerService.jobStatus_Y", "jobStatus");
	dicBorrower.addDic("nationality_borrowerService.nationality_Y", "national");
	dicBorrower.addDic("borrowerreRationship0_familymemberList[0].borrowerreRationship_Y", "borrowerreRationship");
	dicBorrower.addDic("borrowerreRationship1", "borrowerreRation");
	dicBorrower.addDic("borrowerreRationship2", "borrowerreRation");
	dicBorrower.addDic("borrowerreRationship3", "borrowerreRation");
	dicBorrower.addDic("borrowerreRationship4", "borrowerreRation");
	dicBorrower.addDic("profession0_familymemberList[0].profession_Y", "profession");
	dicBorrower.addDic("profession1", "profession");
	dicBorrower.addDic("profession2", "profession");
	dicBorrower.addDic("profession3", "profession");
	dicBorrower.addDic("profession4", "profession");
	dicBorrower.addDic("surveybusinessinfoList0businessLicense", "yesno");
	dicBorrower.addDic("surveybusinessinfoList1businessLicense", "yesno");
	dicBorrower.addDic("surveybusinessinfoList2businessLicense", "yesno");
	dicBorrower.addDic("surveybusinessinfoList0taxRegistrationCertificate", "yesno");
	dicBorrower.addDic("surveybusinessinfoList1taxRegistrationCertificate", "yesno");
	dicBorrower.addDic("surveybusinessinfoList2taxRegistrationCertificate", "yesno");
	dicBorrower.addDic("proveDocument0", "yesno");
	dicBorrower.addDic("proveDocument1", "yesno");
	dicBorrower.addDic("proveDocument2", "yesno");
	dicBorrower.addDic("proveDocumentApplication0_applicationList[0].proveDocument_Y", "yesno");
	dicBorrower.addDic("proveDocumentApplication1", "yesno");
	dicBorrower.addDic("proveDocumentApplication2", "yesno");
	
	dicBorrower.addDic("surveybusinessinfoList0operatingItems", "industryCategory");
	dicBorrower.addDic("surveybusinessinfoList1operatingItems", "industryCategory");
	dicBorrower.addDic("surveybusinessinfoList2operatingItems", "industryCategory");
	dicBorrower.addDic("surveybusinessinfoList0state", "stateSurveybusinessinfoList");
	dicBorrower.addDic("surveybusinessinfoList1state", "stateSurveybusinessinfoList");
	dicBorrower.addDic("surveybusinessinfoList2state", "stateSurveybusinessinfoList");
	dicBorrower.addDic("depositOrganization0", "depositOrganization");
	dicBorrower.addDic("depositOrganization1", "depositOrganization");
	dicBorrower.addDic("depositOrganization2", "depositOrganization");
	dicBorrower.addDic("borrowUse0_applicationList[0].borrowUse_Y", "borroerUserConsult");
	dicBorrower.addDic("borrowUse1", "borroerUserConsult");
	dicBorrower.addDic("borrowUse2", "borroerUserConsult");
	dicBorrower.dicAjax();
	
	$("#borrowerServicepplyDate").datebox({
		onSelect:function(){
			var applydate = $("#borrowerServicepplyDate").datebox("getValue");
			if (applydate != null && applydate != "") {
				if (new Date(applydate) - new Date(sysDate) > 0) {
					$.messager.alert("消息", "申请时间不能大于当前服务器时间,当前系统时间为-"
							+ sysDate + "-");
					$("#borrowerServicepplyDate").datebox("setValue", "");
					return;
				}
			}
		}
	});
	$("#depositOrganization0").combobox({
		width:100,
		onSelect:function(value){
			if(value.codeKey == 4){
				$("#depositList0address").validatebox({
					required:true
				});
				$("#depositList0address").validatebox("validate");
				$("#depositList0telephone").validatebox({
					required:true
				});
				$("#depositList0telephone").validatebox("validate");
			}else{
				$("#depositList0address").validatebox({
					required:false
				});
				$("#depositList0address").validatebox("validate");
				$("#depositList0telephone").validatebox({
					required:false
				});
				$("#depositList0telephone").validatebox("validate");
			}
		}
	});
	$("#depositOrganization1").combobox({
		width:100,
		onSelect:function(value){
			if(value.codeKey == 4){
				$("#depositList1address").validatebox({
					required:true
				});
				$("#depositList1address").validatebox("validate");
				$("#depositList1telephone").validatebox({
					required:true
				});
				$("#depositList1telephone").validatebox("validate");
			}else{
				$("#depositList1address").validatebox({
					required:false
				});
				$("#depositList1address").validatebox("validate");
				$("#depositList1telephone").validatebox({
					required:false
				});
				$("#depositList1telephone").validatebox("validate");
			}
		}
	});
	$("#depositOrganization2").combobox({
		width:100,
		onSelect:function(value){
			if(value.codeKey == 4){
				$("#depositList2address").validatebox({
					required:true
				});
				$("#depositList2address").validatebox("validate");
				$("#depositList2telephone").validatebox({
					required:true
				});
				$("#depositList2telephone").validatebox("validate");
			}else{
				$("#depositList2address").validatebox({
					required:false
				});
				$("#depositList2address").validatebox("validate");
				$("#depositList2telephone").validatebox({
					required:false
				});
				$("#depositList2telephone").validatebox("validate");
			}
		}
	});
	//tableTrValidate("jobInfoList","jobTable");
	var length =$("#jobTable tbody tr td:nth-child(2)").length;
	for(var i = 0 ; i < length-1 ;i++){
		$.each($("#jobTable tbody tr td [id ^= 'jobInfoList"+i+"']"),function(j,element){
			 $("#"+element.id).blur(function(){
				var elementName = element.id.substring(11,12);
				hangValidate(elementName);
			});
		});
	}
	
	var length =$("#surveybusinessTable tbody tr td:nth-child(2)").length;
	for(var i = 0 ; i < length-1 ;i++){
		$.each($("#surveybusinessTable tbody tr td [id ^= 'surveybusinessinfoList"+i+"']"),function(j,element){
			 $("#"+element.id).blur(function(){
				var elementName = element.id.substring(22,23);
				surveybusinessinfoListHang(elementName);
			});
			 if("combobox-f combo-f" == $("#"+element.id).attr("class") || 
				undefined == $("#"+element.id).attr("class")){
				 var elementName = element.id.substring(22,23);
				 $("#"+element.id).combobox({
					 width:100,
					onSelect:function(){surveybusinessinfoListHang(elementName);}
				 });
			 }
		});
	}
	
});



function surveybusinessinfoListHang(elementName){
	var valueq ="";
	 
	$.each($("#surveybusinessTable tbody tr td [id ^= 'surveybusinessinfoList"+elementName+"']"),function(l,elementObject){
		if($("#"+elementObject.id).attr("value") != undefined && $("#"+elementObject.id).attr("value") != null ){
			valueq = valueq+$("#"+elementObject.id).attr("value");
		}
		 if("combobox-f combo-f" == $("#"+elementObject.id).attr("class") || 
			undefined == $("#"+elementObject.id).attr("class")){
				 valueq = valueq+ $("#"+elementObject.id).combobox("getValue");
		 }
	});
	if(valueq != null && valueq != "" ){
		$.each($("#surveybusinessTable tbody tr td [id ^= 'surveybusinessinfoList"+elementName+"']"),function(k,elementObject){
			var classNameOld = $("#"+elementObject.id).attr("class");
			var elementvalue = $("#"+elementObject.id).attr("value");
			if("combobox-f combo-f" == classNameOld || classNameOld == undefined){
					var comboboxValue= $("#"+elementObject.id).combobox("getValue");
					$("#"+elementObject.id).combobox({
						 	required:true,
						 	value:comboboxValue,
						 	validType:'selectValueRequired["'+elementObject.id+'","请选择"]',
						 	width:100
					 });
					$("#"+elementObject.id).combobox("validate");
			}else{
				var className = className = classNameOld.substring(7,classNameOld.indexOf(" v"));
				if("validatebox" == className){
					$("#"+elementObject.id).validatebox({
						 	required:true
					 });
					$("#"+elementObject.id).validatebox("validate");
				}
				
				if("numberbox" == className){
					$("#"+elementObject.id).numberbox({
						 	required:true,
						 	value:elementvalue
					 });
					
					$("#"+elementObject.id).numberbox("validate");
				}
			}
		});
	}else{
			$.each($("#surveybusinessTable tbody tr td [id ^= 'surveybusinessinfoList"+elementName+"']"),function(k,elementObject){
				var classNameOld = $("#"+elementObject.id).attr("class");
				var elementvalue = $("#"+elementObject.id).attr("value");
				if("combobox-f combo-f" == classNameOld ){
						var comboboxValue= $("#"+elementObject.id).combobox("getValue");
						$("#"+elementObject.id).combobox({
							 	required:false,
							 	value:comboboxValue,
							 	validType:"",
							 	width:100
						 });
						$("#"+elementObject.id).combobox("validate");
				}else{
					if(classNameOld != undefined){
						var className = className = classNameOld.substring(7,classNameOld.indexOf(" v"));	
						if("validatebox" == className){
							$("#"+elementObject.id).validatebox({
								 	required:false
							 });
							$("#"+elementObject.id).validatebox("validate");
						}
						
						if("numberbox" == className){
							$("#"+elementObject.id).numberbox({
								 	required:false,
								 	value:elementvalue
							 });
							
							$("#"+elementObject.id).numberbox("validate");
						}
					}
					
				}
				
			});
	 }
}


function hangValidate(elementName){
	var valueq ="";
	 
	$.each($("#jobTable tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(l,elementObject){
		if($("#"+elementObject.id).attr("value") != undefined && $("#"+elementObject.id).attr("value") != null ){
			valueq = valueq+$("#"+elementObject.id).attr("value");
		}
		
	});
	if(valueq != null && valueq != "" ){
		$.each($("#jobTable tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(k,elementObject){
			var classNameOld = $("#"+elementObject.id).attr("class");
			var className = classNameOld.substring(7,classNameOld.indexOf(" v"));
			var elementvalue = $("#"+elementObject.id).attr("value");
			if("validatebox" == className){
				$("#"+elementObject.id).validatebox({
					 	required:true
				 });
				$("#"+elementObject.id).validatebox("validate");
			}
			if("numberbox" == className){
				$("#"+elementObject.id).numberbox({
					 	required:true,
					 	value:elementvalue
				 });
				
				$("#"+elementObject.id).numberbox("validate");
			}
			if("databox" == className){
				$("#"+elementObject.id).databox({
					 	required:true,
					 	value:elementvalue
				 });
				$("#"+elementObject.id).databox("validate");
			}
			if("combobox" == className){
				$("#"+elementObject.id).combobox({
					 	required:true,
					 	value:elementvalue
				 });
				$("#"+elementObject.id).combobox("validate");
			}
			
		});
	}else{
			$.each($("#jobTable tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(k,elementObject){
				var classNameOld = $("#"+elementObject.id).attr("class");
				var className = classNameOld.substring(7,classNameOld.indexOf(" v"));
				var elementvalue = $("#"+elementObject.id).attr("value");
				if("validatebox" == className){
					$("#"+elementObject.id).validatebox({
						 	required:false
					 });
					$("#"+elementObject.id).validatebox("validate");
				}
				if("numberbox" == className){
					$("#"+elementObject.id).numberbox({
						 	required:false,
						 	value:elementvalue
					 });
					$("#"+elementObject.id).numberbox("validate");
				}
				if("databox" == className){
					$("#"+elementObject.id).databox({
						 	required:false,
						 	value:elementvalue
					 });
					$("#"+elementObject.id).databox("validate");
				}
				if("combobox" == className){
					$("#"+elementObject.id).combobox({
						 	required:false,
						 	value:elementvalue
					 });
					$("#"+elementObject.id).combobox("validate");
				}
				
			});
	 }
}
/**
 * 失去焦点时判断申请金额和产品是否匹配
 */
function validateCapitaLimit(){
	
	var applyLimitBorrower=$("#applyLimitBorrower");
	var capitalUpperLimit=$("#capitalUpperLimit").val();
	var capitalLowerLimit=$("#capitalLowerLimit").val();
	if(applyLimitBorrower.val()==""){
		return;
	}
	if(capitalUpperLimit==""){
		capitalUpperLimit=0;
	}
	if(capitalLowerLimit==""){
		capitalLowerLimit=0;
	}
	if(parseFloat(applyLimitBorrower.val())<=parseFloat(capitalUpperLimit) && parseFloat(applyLimitBorrower.val()) >=parseFloat(capitalLowerLimit) ){
 		
 	}else{
 		$.messager.alert("消息","申请额度不符合该产品额度,产品中额度上限="+capitalUpperLimit+";额度下限="+capitalLowerLimit);
 		applyLimitBorrower.numberbox("clear");
 		return ;
 	}
	
}

function familyIdNumber(){
	if(familyIdFlag){
			
		var IdNumber = $.trim($("#IdNumber").val());
		
		ajaxSubmit(serverName+"/customer/listForCombo.do?creditapplicationId="+$("#creditapplicationId1").val(),{q:IdNumber},function(result){
			if(result != null && result != ""){
				$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
				if(result[0].blackFlag != null && result[0].blackFlag != ""){
					$.messager.alert("消息","该人已经加入黑名单","info",function(){
						$("#IdNumber").val("");
						return;
					});
				}else{
						
						if(result[0].customerType == "4"){
							$.messager.alert("消息","该人是本组的担保人","info",function(){
								$("#IdNumber").val("");
								return;
							});
		            	}
		            	if(result[0].customerType == "3" || result[0].customerType == "0" 
		            			||result[0].customerType == "" ||result[0].customerType == null){
		            		if(result[0].creditApplicationId == $("#creditapplicationId1").val() && result[0].customerType == "3"){
		            		}else{
			            		$.messager.alert("消息","该客户正在借款中！","info",function(){
									$("#IdNumber").val("");
									return;
								});
		            		}
		            	}
		            	if(result[0].customerType == "1"){
		            		$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
		            		return;
		            	}
				}
				
			} 
			$("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
		});
	}
}


function saveBorrowerService(){
	if( $("#jobInfoList0company").val() == "" &&
			$("#jobInfoList1company").val() == "" &&
			$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
			$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
			$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
		 {
			 $.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
			 return;
		 }
		if( $("#livingCommercialForm").attr("checked") == undefined &&
			$("#livingSelfForm").attr("checked") == undefined &&
			$("#livingRentForm").attr("checked") == undefined &&
			$("#livingRelativeForm").attr("checked") == undefined &&
			$("#livingOtherForm").attr("checked") == undefined)
		 {
			 $.messager.alert("消息","请至少选择一项 居住状况！");
			 return;
		 }
	
		familyIdFlag = false;
		if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
			$("#addSave").linkbutton('disable');
		}else{
			$.messager.alert("消息","请将数据填写正确完整！");
			return;
		}
		familyIdFlag = true;
		ajaxSubmit(serverName+"/application/add.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
			if(result){
				$("#borrowSerivce").dialog("close");
				showBorrowerServiceApp();
				$("#addSave").linkbutton('enable');
			}else{
				$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
				$("#addSave").linkbutton('enable');
			}
		});
}
</script>
<div>
	<form id="formBorrrower" name="form1" method="post" novalidate >
		<table width="892"   align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="5" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
				
					<span style="font-size: 25px; font-weight: 700; color: #4B0082;">
					业务申请表 </span>
					<p>&nbsp;</p>
					申请时间：<input name="borrowerService.applyDate" id="borrowerServicepplyDate" required="true"  editable="false" class="easyui-datebox"/>
					 &nbsp; 
					<span style="font-size: 15px; font-weight: 700;"></span>
					<input id="borrowerServiceAppId" name="borrowerService.borrowerServiceAppId" type="hidden"> 
					<input id="creditapplicationIdBorrower" name="borrowerService.creditapplicationId" type="hidden">
					<!-- 产品上限 -->
					<input id="capitalUpperLimit" name="creditApplication.capitalUpperLimit" type="hidden">
					<!-- 产品下限 -->
					<input id="capitalLowerLimit" name="creditApplication.capitalLowerLimit" type="hidden">
					<input type="hidden" name="borrowerService.firstFlag" id="firstFlag"/>
				</td>
			</tr>
		  <tr>
		    <td width="156" align="right">业务编号：</td>
		    <td width="193"><input type="text"   id="businessNumberBorrower" readonly="readonly"   style="width: 147px;"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">身份证：</td>
		    <td width="207"><input type="text" name="borrowerService.credentialsNumber" id="credentialsNumberBorrrower" readonly="readonly"  style="width: 147px;"/></td>
		  </tr>
		   <tr>
		    <td width="156" align="right">申请金额(元)：</td>
		    <td width="193"><input type="text" name="borrowerService.applyLimit" id="applyLimitBorrower" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" onblur="validateCapitaLimit()"  style="width: 147px;" maxlength="8"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">民族：</td>
		    <td width="207"><input type="text" name="borrowerService.nationality"   id="nationality" required="true"  style="width: 150px;"/></td>
		  </tr>
		  <tr>
		    <td align="right">姓名：</td>
		    <td width="193"><input type="text" name="borrowerService.name" id="nameBorrow" class="easyui-validatebox" required="true"  style="width: 147px;" maxlength="10"/></td>
		    <td>&nbsp;</td>
		    <td align="right">曾用名：</td>
		    <td><input type="text" name="borrowerService.former" id="businessNumber5"  style="width: 147px;" maxlength="10"/></td>
		  </tr>
		  <tr>
		    <td align="right">出生日期：</td>
		    <td><input type="text" name="borrowerService.birthday" id="businessNumber3" readonly="readonly"  style="width: 147px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">性别：</td>
		    <td><input name="borrowerService.gender" id="genderBorrower" type="hidden"  style="width: 147px;"/>
		    <input id="showGender" readonly="readonly" style="width: 147px;"/></td>
		  </tr>
		   <tr>
		    <td align="right">婚姻状况：</td>
		    <td> 
		      <input name="borrowerService.maritalStatus"  id="maritalStatusBorrower" required="true" style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">就业状况：</td>
		    <td> 
		      <input name="borrowerService.jobStatus"  id="jobStatusBorrower" required="true" style="width: 150px;"/></td>
		  </tr>
		
		  <tr>
		    <td align="right" valign="top">家庭住址分类：</td>
		    <td><p>
		      <label>
		        <input type="checkbox" value="1" id="livingCommercialForm" name="borrowerService.livingCommercial" />
		        自有商品房</label>
		      <br />
		      <label>
		        <input type="checkbox" value="1" name="borrowerService.livingSelf"   id="livingSelfForm" />
		        自有宅基地</label>
		      <br />
		      <label>
		        <input type="checkbox" value="1" name="borrowerService.livingRent" onclick="livingRentDate();"  id="livingRentForm" />
		        租住</label>
		      （到期日<span id="livingdateDiv" >
		      </span>
		      ）<br />
		      <label>
		        <input type="checkbox" name="borrowerService.livingRelative" value="1" id="livingRelativeForm" />
		        亲戚住房</label>
		      <br />
		      <label>
		        <input type="checkbox" name="borrowerService.livingOther" value="1" id="livingOtherForm"/>
		        其他</label>
		      <br />
		    </p></td>
		    <td>&nbsp;</td>
		    <td align="right" valign="top">现经营场所地址分类：</td>
		    <td><label>
		      <input type="checkbox"   name="borrowerService.businessCommercial" value="1"  />
		      自有商品房</label>
		      <br />
		      <label>
		        <input type="checkbox"   name="borrowerService.businessSelf" value="1" id="CheckboxGroup1_6" />
		        自有宅基地</label>
		      <br />
		      <label>
		        <input type="checkbox" onclick="businessRentDate();" name="borrowerService.businessRent"   id="businessRentForm" value="1" />
		        租住</label>
		      （到期日<span id="businessDateDiv" >
			</span>
		）<br />
		       <label>
		        <input type="checkbox"   name="borrowerService.businessRelative" value="1" id="CheckboxGroup1_8" />
		        亲戚住房</label>
		      <br />
		      <label>
		        <input type="checkbox"   name="borrowerService.businessOther" value="1" id="CheckboxGroup1_9" />
		        其他</label></td>
		    </tr>
		    <tr>
		    <td align="right">家庭住址面积(㎡)：</td>
		    <td><input type="text" name="borrowerService.livingArea" id="businessNumber8" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"  style="width: 150px;" maxlength="4"/></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所面积(㎡)：</td>
		    <td><input type="text" name="borrowerService.businessArea" id="businessArea"  class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"  style="width: 150px;" maxlength="4"/></td>
		    </tr>
		  <tr>
		    <td align="right">家庭住址居住年限(年)：</td>
		    <td><input type="text" maxlength="4" name="borrowerService.livingYear" id="businessNumber6" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所已使用年限(年)：</td>
		    <td><input type="text" maxlength="4" name="borrowerService.businessYear" id="businessYear" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"    style="width: 150px;"/></td>
		  </tr>
		    <tr>
		    <td align="right">手机号码：</td>
		    <td><input type="text" maxlength="11" name="borrowerService.mobilephone" id="mobilephoneBorrow" required="true" class="easyui-validatebox" validType="phoneNumber"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">办公电话：</td>
		    <td><input type="text" maxlength="11" name="borrowerService.officePhone" id="officePhone" class="easyui-validatebox" validType="phoneNumber"   style="width: 150px;"/></td>
		  </tr>
		    <tr>
     <td align="right" valign="top">户籍地址：</td>
     <td valign="top" width="193"><p>
       <input type="text"  id="provinceHome" class="easyui-combobox" editable="false" required="true" style="width: 150px;"/>省
     </p>
      <p>
        <input type="text"  id="cityHome" class="easyui-combobox" editable="false" required="true" style="width: 150px;"/>市
      </p>
      <p>
        <input type="text"  id="countyHome" class="easyui-combobox" editable="false" required="true" style="width: 150px;"/>区
      </p>
      <p>
        <input type="text"  id="townHome"  class="easyui-combobox"  editable="false" required="true" style="width: 150px;"/>镇
      </p>
      <p>
        <input type="text" name="borrowerService.villageId"  id="villageHome" editable="false" required="true" class="easyui-combobox"  style="width: 150px;"/>村
      </p>
      <p style="width: 180px;">
        <input type="text"  id="detailHome" class="easyui-validatebox"  onkeydown="paramValidate=true;" onblur="addAddress(paramValidate);" required="true" style="width: 147px;" maxlength="50"/>号
      </p>
       <p>
        <textarea name="borrowerService.homeAddress" rows="3" cols="6" readonly="readonly" id="homeAddress" style="width: 147px;" class="easyui-validatebox"  validType="length[0,100]">
        </textarea>
      </p>
      </td>
     <td>&nbsp;</td>
     <td align="right" valign="top">现经营场所地址：</td>
     <td valign="top"><p>
       <input type="text"   id="provinceBusiness" class="easyui-combobox" editable="false" style="width: 150px;"/>省
     </p>
      <p>
        <input type="text"   id="cityBusiness" class="easyui-combobox" editable="false" style="width: 150px;"/>市
      </p>
      <p>
        <input type="text" id="countyBusiness" class="easyui-combobox" editable="false" style="width: 150px;"/>区
      </p>
      <p>
        <input type="text"  id="townBusiness" class="easyui-combobox" editable="false" style="width: 150px;"/>镇
      </p>
      <p>
        <input type="text"  name="borrowerService.townId" class="easyui-combobox" editable="false"   id="villageBusiness" style="width: 150px;" />村
      </p>
      <p>
        <input type="text"   id="detailBusiness" onblur="addBusinessAddress();" style="width: 147px;" maxlength="50"/>号
      </p>
         <p>
        <textarea name="borrowerService.residenceAddress" rows="3" cols="6" readonly="readonly" id="businessAddress" style="width: 147px;" class="easyui-validatebox"  validType="length[0,100]">
        
        </textarea>
      </p>
      </td>
   </tr>
   <tr>
   	 		<td align="right" valign="top">家庭住址:</td>
		    <td>
		    	<textarea name="borrowerService.hourseholdAddress" onblur="hhAddress();" 
		    	maxlength= "60" class="easyui-validatebox"  validType="length[0,80]"
		    	rows="3" cols="6" class="easyui-validatebox" required="true"
		    	 id="hourseholdAddress" style="width: 147px;">
        		</textarea>
		    </td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
   </tr>  
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>										
	</form>
<form id="formOther" novalidate>
 <p>&nbsp;	</p>
  <table id="familytable" width="898"   align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">配偶信息</span>
      </td>
    </tr>
    <tr>
      <td width="149">姓名</td>
      <td width="199">与申请人关系</td>
      <td width="155">身份证号</td>
      <td width="90">年龄</td>
      <td width="155">职业</td>
      <td width="185">职业明细</td>
      <td width="155">联系电话</td>
    </tr>
   
	<tr>
      <td><input required="true" class="easyui-validatebox"   name="familymemberList[0].name" maxlength="10" /></td>
      <td><input name="familymemberList[0].borrowerreRationship" id="borrowerreRationship0" value="2"   size="15" hasDownArrow="false"/></td>
      <td><input required="true"  name="familymemberList[0].idNumber" id="IdNumber" class="easyui-validatebox" validType="idnumberAll" size="25" onblur="familyIdNumber();"/></td>
     <td><input  readonly="readonly" class="easyui-numberbox" id="familymemberListAge"  name="familymemberList[0].age" onkeypress="if(event.which==45){return false;}"  validType="number" maxlength="3" size="15"/></td>
       <td><input required="true"    name="familymemberList[0].profession" id="profession0" size="15"/></td>
      <td><input required="true" class="easyui-validatebox"   name="familymemberList[0].professionDetail" maxlength="20"/></td>
      <td><input required="true" class="easyui-validatebox"   name="familymemberList[0].telphone" validType="phoneNumber" maxlength="11"/>
      		<input  value="1" name="familymemberList[0].seq"  />
      </td>
    </tr>
  </table>
  <p>&nbsp;	</p>
  <table width="898"   align="center" cellpadding="3" cellspacing="0">
     <tr>
      <td colspan="6"  align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
  		<span style="font-size: 15px; font-weight: 700;">其他家庭成员情况</span>    
	</td>
    </tr>
    <tr>
     <td width="149">姓名</td>
      <td width="199">与申请人关系</td>
      <td width="168">年龄</td>
      <td width="185">职业</td>
      <td width="185">职业明细</td>
      <td width="155">联系电话</td>
     </tr>
   	<tr>
      <td><input type="text" name="familymemberList[1].name" id="businessNumber16" maxlength="10"/></td>
      <td><input type="text" name="familymemberList[1].borrowerreRationship" id="borrowerreRationship1" /></td>
      <td><input type="text" name="familymemberList[1].age" id="businessNumber18" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input type="text" name="familymemberList[1].profession" id="profession1"/></td>
      <td><input  name="familymemberList[1].professionDetail" maxlength="20"/></td>
      <td><input type="text" name="familymemberList[1].telphone" id="businessNumber20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/>
      		<input  value="2" name="familymemberList[1].seq"  />
      </td>
    </tr>
    <tr>
      <td><input type="text" name="familymemberList[2].name" id="businessNumber21" maxlength="10"/></td>
      <td><input type="text" name="familymemberList[2].borrowerreRationship" id="borrowerreRationship2" /></td>
      <td><input type="text" name="familymemberList[2].age" id="businessNumber23" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input type="text" name="familymemberList[2].profession" id="profession2"/></td>
      <td><input  name="familymemberList[2].professionDetail" maxlength="20"/></td>
      <td><input type="text" name="familymemberList[2].telphone" id="businessNumber25" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/>
      		<input  value="3" name="familymemberList[2].seq"  />
      </td>
    </tr>
    <tr>
      <td><input type="text" name="familymemberList[3].name" id="businessNumber26" maxlength="10"/></td>
      <td><input type="text" name="familymemberList[3].borrowerreRationship" id="borrowerreRationship3" /></td>
      <td><input type="text" name="familymemberList[3].age" id="businessNumber28" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input type="text" name="familymemberList[3].profession" id="profession3"/></td>
      <td><input  name="familymemberList[3].professionDetail" maxlength="20"/></td>
      <td><input type="text" name="familymemberList[3].telphone" id="businessNumber30" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/>
      	<input  value="4" name="familymemberList[3].seq"  />
      </td>
    </tr>
    <tr>
      <td width="168"><input type="text" name="familymemberList[4].name" id="businessNumber43" maxlength="10"/></td>
      <td width="177"><input type="text" name="familymemberList[4].borrowerreRationship" id="borrowerreRationship4" /></td>
      <td width="168"><input type="text" name="familymemberList[4].age" id="businessNumber45" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td width="175"><input type="text" name="familymemberList[4].profession" id="profession4"/></td>
      <td><input  name="familymemberList[4].professionDetail" maxlength="20"/></td>
      <td width="168"><input type="text" name="familymemberList[4].telphone" id="businessNumber47" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/>
      	<input  value="5" name="familymemberList[4].seq"  />
      </td>
    </tr>
  </table>
 
  <p>&nbsp;</p>
  <table width="898" id="jobTable" align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="5" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">相关工作情况</span>
      </td>
    </tr>
    <tr>
      <td width="185">就职公司</td>
      <td width="153">地址</td>
      <td width="139">工作年限</td>
      <td width="131">职位</td>
      <td width="126">每月净收入(元)</td>
    </tr>
    <tr>
      <td><input  class="easyui-validatebox"  name="jobInfoList[0].company" type="text" id="jobInfoList0company" size="18" maxlength="20"/></td>
      <td><input  class="easyui-validatebox"  name="jobInfoList[0].workunitAddress" type="text" id="jobInfoList0workunitAddress" size="18" maxlength="30"/></td>
      <td><input  class="easyui-numberbox"  onkeypress="if(event.which==45){return false;}"  name="jobInfoList[0].years" type="text" id="jobInfoList0years" size="18" validType="number" maxlength="4"/></td>
      <td><input  class="easyui-validatebox"  name="jobInfoList[0].post" type="text" id="jobInfoList0post" size="18" maxlength="20"/></td>
      <td><input  class="easyui-numberbox"  name="jobInfoList[0].income" type="text" onkeypress="if(event.which==45){return false;}" id="jobInfoList0ncome"  precision="2" size="18" maxlength="10"/>
          <input  class="easyui-numberbox"  name="jobInfoList[0].seq" type="text"  value = "1"/>
      </td>
    </tr>
    <tr>
      <td><input class="easyui-validatebox" name="jobInfoList[1].company" type="text" id="jobInfoList1company" size="18" maxlength="20"/></td>
      <td><input class="easyui-validatebox"  name="jobInfoList[1].workunitAddress" type="text" id="jobInfoList1workunitAddress" size="18" maxlength="30"/></td>
      <td><input class="easyui-numberbox"   name="jobInfoList[1].years" type="text" id="jobInfoList1years" size="18" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="4"/></td>
      <td><input class="easyui-validatebox" name="jobInfoList[1].post" type="text" id="jobInfoList1post" size="18" maxlength="20"/></td>
      <td><input class="easyui-numberbox"   name="jobInfoList[1].income" id="jobInfoList1income" type="text"  onkeypress="if(event.which==45){return false;}" precision="2" size="18" maxlength="10"/>
      	  <input  class="easyui-numberbox"  name="jobInfoList[1].seq" type="text"  value = "2"/>
      </td>
    </tr>
    
     
    <!-- <tr>
      <td><input class="easyui-validatebox" name="jobInfoList[2].company" type="text" id="jobInfoList2company" size="18" maxlength="20"/></td>
      <td><input class="easyui-validatebox"  name="jobInfoList[2].workunitAddress" type="text" id="jobInfoList2workunitAddress" size="18" maxlength="30"/></td>
      <td><input class="easyui-numberbox"   name="jobInfoList[2].years" type="text" id="jobInfoList2years" size="18" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="4"/></td>
      <td><input class="easyui-validatebox" name="jobInfoList[2].post" type="text" id="jobInfoList2post" size="18" maxlength="20"/></td>
      <td>
	      <input class="easyui-numberbox"   name="jobInfoList[2].income" id="jobInfoList2income" type="text"  onkeypress="if(event.which==45){return false;}" precision="2" size="18" maxlength="10"/>
	      <input  class="easyui-numberbox"  name="jobInfoList[2].seq" type="text"  value = "3"/>
      </td>
    </tr>-->
  </table>
  <p>&nbsp;</p>
  <table width="898"  id="surveybusinessTable" align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">经营情况（包括当前和历史的经营情况）</span>
      </td>
    </tr>
    <tr>
     <td width="188">经营项目所在行业</td>
      <td width="193">经营项目详细</td>
      <td width="154">起始年份</td>
      <td width="146">状态</td>
      <td width="94">营业执照</td>
      <td width="85">税务登记证</td>
    </tr>
    <tr>
     <td><input     name="surveybusinessinfoList[0].operatingItems" id="surveybusinessinfoList0operatingItems"></input></td>
      <td><input name="surveybusinessinfoList[0].operatingItemsDetail" type="text" id="surveybusinessinfoList0operatingItemsDetail" size="20" maxlength="20" class="easyui-validatebox"/></td>
      <td><input class="easyui-numberbox"onkeypress="if(event.which==45){return false;}" validType="yearValidate"  name="surveybusinessinfoList[0].startingDate" type="text" id="surveybusinessinfoList0startingDate" size="20" maxlength="4"/></td>
      <td><input  name="surveybusinessinfoList[0].state" id="surveybusinessinfoList0state"></input></td>
      <td><input  name="surveybusinessinfoList[0].businessLicense" id="surveybusinessinfoList0businessLicense" > </input></td>
      <td><input name="surveybusinessinfoList[0].taxRegistrationCertificate" id="surveybusinessinfoList0taxRegistrationCertificate"> </input>
      	  <input name="surveybusinessinfoList[0].seq"  value="1"> </input>
      </td>
    </tr>
    <tr>
      <td><input     name="surveybusinessinfoList[1].operatingItems" id="surveybusinessinfoList1operatingItems"></input></td>
      <td><input name="surveybusinessinfoList[1].operatingItemsDetail" type="text" id="surveybusinessinfoList1operatingItemsDetail" size="20" maxlength="20" class="easyui-validatebox"/></td>
      <td><input name="surveybusinessinfoList[1].startingDate" type="text" id="surveybusinessinfoList1startingDate" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4"/></td>
      <td><input name="surveybusinessinfoList[1].state" id="surveybusinessinfoList1state">
      </input>
      <input name="surveybusinessinfoList[1].seq"  value="2"> </input>
      </td>
      <td><input name="surveybusinessinfoList[1].businessLicense" id="surveybusinessinfoList1businessLicense">
      </input></td>
      <td><input name="surveybusinessinfoList[1].taxRegistrationCertificate" id="surveybusinessinfoList1taxRegistrationCertificate">
      </input> <input name="surveybusinessinfoList[1].seq"  value="2"> </input>
      </td>
    </tr>
    <tr>
      <td><input    name="surveybusinessinfoList[2].operatingItems" id="surveybusinessinfoList2operatingItems"></input></td>
      <td><input name="surveybusinessinfoList[2].operatingItemsDetail" type="text" id="surveybusinessinfoList2operatingItemsDetail" size="20" maxlength="20" class="easyui-validatebox"/></td>
      <td><input name="surveybusinessinfoList[2].startingDate" type="text" id="surveybusinessinfoList2startingDate" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate" maxlength="4"/></td>
      <td><input name="surveybusinessinfoList[2].state" id="surveybusinessinfoList2state">
      </input></td>
      <td><input name="surveybusinessinfoList[2].businessLicense" id="surveybusinessinfoList2businessLicense">
      </input></td>
      <td><input name="surveybusinessinfoList[2].taxRegistrationCertificate" id="surveybusinessinfoList2taxRegistrationCertificate">
      </input>
      <input name="surveybusinessinfoList[2].seq"  value="3"> </input>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="898" align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">存款和应收现金</span>
      </td>
    </tr>
    <tr>
      <td width="192">存款机构或应支付现金方</td>
      <td width="196">存款机构或应支付现金方详细</td>
      <td width="140">地址</td>
      <td width="152">电话</td>
      <td width="112">余额(元)</td>
      <td width="68">证明文档</td>
    </tr>
    <tr>
      <td><input  name="depositList[0].depositOrganization" id="depositOrganization0"></input></td>
      <td><input  class="easyui-validatebox"  name="depositList[0].depositOrganizationDetail" type="text" id="businessNumber66" size="20" maxlength="20"/></td>
      <td><input  class="easyui-validatebox"  name="depositList[0].address" type="text" id="depositList0address" size="20" maxlength="30"/></td>
      <td><input  class="easyui-validatebox"  name="depositList[0].telephone" type="text" id="depositList0telephone" size="20"  validType="phoneNumber" maxlength="11"/> </td>
      <td><input   class="easyui-numberbox"   name="depositList[0].balance" type="text" onkeypress="if(event.which==45){return false;}" id="balance0" onblur="addBlance();" size="16"   precision="2" maxlength="7"/></td>
      <td><input   name="depositList[0].proveDocument" id="proveDocument0">  </input>
     	 <input   name="depositList[0].seq" value="1">  </input>
      </td>
    </tr>
    <tr>
      <td><input name="depositList[1].depositOrganization" id="depositOrganization1">
      </input></td>
      <td><input name="depositList[1].depositOrganizationDetail" type="text" id="businessNumber70" size="20" maxlength="20"/></td>
      <td><input name="depositList[1].address" type="text" id="depositList1address" size="20" maxlength="30"/></td>
      <td><input name="depositList[1].telephone" type="text" id="depositList1telephone" size="20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
      <td><input name="depositList[1].balance" type="text" id="balance1" onblur="addBlance();" size="16" onkeypress="if(event.which==45){return false;}" class="easyui-numberbox"   precision="2" maxlength="7"/></td>
      <td><input name="depositList[1].proveDocument" id="proveDocument1">
      </input>
      <input   name="depositList[1].seq" value="2">  </input>
      </td>
    </tr>
    <tr>
      <td><input name="depositList[2].depositOrganization" id="depositOrganization2">
      </input></td>
      <td><input name="depositList[2].depositOrganizationDetail" type="text" id="depositList2depositOrganizationDetail" size="20" maxlength="20"/></td>
      <td><input name="depositList[2].address" type="text" id="depositList2address" size="20" maxlength="30"/></td>
      <td><input name="depositList[2].telephone" type="text" id="depositList2telephone" size="20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
      <td><input name="depositList[2].balance" type="text" id="balance2" onblur="addBlance();" size="16" onkeypress="if(event.which==45){return false;}" class="easyui-numberbox"   precision="2" maxlength="7"/></td>
      <td><input name="depositList[2].proveDocument" id="proveDocument2">
      </input>
      <input   name="depositList[2].seq" value="3">  </input>
      </td>
    </tr>
    <tr>
      <td colspan="4" align="right">汇总(元)：</td>
      <td><input name="businessNumber66" type="text" id="totalBlance" size="16" readonly="readonly"/></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  
  <table width="899"  align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">借款申请</span>
      </td>
    </tr>
    <tr>
      <td width="197">借款使用计划</td>
      <td width="163">借款使用计划详细</td>
      <td width="140">数量</td>
      <td width="140">单价(元)</td>
      <td width="145">总额(元)</td>
      <td width="76">证明文档</td>
    </tr>
    <tr>
      <td><input required="true"  name="applicationList[0].borrowUse" id="borrowUse0"></input></td>
      <td><input required="true" class="easyui-validatebox"   name="applicationList[0].borrowUseDetail" type="text" id="businessNumber891" size="20" maxlength="20"/></td>
      <td><input required="true" class="easyui-numberbox"   name="applicationList[0].quantity"  onkeypress="if(event.which==45){return false;}" type="text" id="quantity0" onblur="addAmount();" size="20"   maxlength="7"/></td>
      <td><input required="true" class="easyui-numberbox"     name="applicationList[0].unitPrice" type="text" id="unitPrice0" onblur="addAmount();" size="20" onkeypress="if(event.which==45){return false;}"  precision="2" maxlength="7"/></td>
      <td><input   name="applicationList[0].totalAmount" type="text" id="totalAmount0" size="20" readonly="readonly" /></td>
      <td><input required="true"   name="applicationList[0].proveDocument" id="proveDocumentApplication0">  </input>
         <input    name="applicationList[0].seq" value="1">  </input>
      </td>
    </tr>
    <tr>
      <td><input name="applicationList[1].borrowUse" id="borrowUse1">
      </input></td>
      <td><input name="applicationList[1].borrowUseDetail" type="text" id="businessNumber89rr" size="20" maxlength="20"/></td>
      <td><input name="applicationList[1].quantity" type="text" id="quantity1" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="7"/></td>
      <td> 
        <input name="applicationList[1].unitPrice" type="text" id="unitPrice1" onblur="addAmount();" size="20" class="easyui-numberbox"   onkeypress="if(event.which==45){return false;}" precision="2" maxlength="7"/>
       </td>
      <td><input name="applicationList[1].totalAmount" type="text" id="totalAmount1" size="20" readonly="readonly"/></td>
      <td><input name="applicationList[1].proveDocument" id="proveDocumentApplication1">  </input>
      	 <input    name="applicationList[1].seq" value="2">  </input>
      </td>
    </tr> 
    <tr>
      <td><input name="applicationList[2].borrowUse" id="borrowUse2">
      </input></td>
      <td><input name="applicationList[2].borrowUseDetail" type="text" id="busqNumber89sdgw34" size="20" maxlength="20"/></td>
      <td><input name="applicationList[2].quantity" type="text" id="quantity2" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="7"/></td>
      <td> 
        <input name="applicationList[2].unitPrice" type="text" id="unitPrice2" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}"   precision="2" maxlength="7"/>
       </td>
      <td><input name="applicationList[2].totalAmount" type="text" id="totalAmount2" size="20" readonly="readonly" /></td>
      <td><input name="applicationList[2].proveDocument" id="proveDocumentApplication2">  </input>
       	<input    name="applicationList[2].seq" value="3">  </input>
      </td>
    </tr>
     <tr>
      <td colspan="4" align="right">汇总(元)：  <td><input name="businessNumber65" type="text" id="TTAmount" size="20" readonly="readonly" /></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  		<table>
  		<tr>
  		<td>
 			<input name="familymemberList[0].familyMemberId" type="hidden" />
			<input name="familymemberList[1].familyMemberId" type="hidden" />
			<input name="familymemberList[2].familyMemberId" type="hidden" />
			<input name="familymemberList[3].familyMemberId" type="hidden" />
			<input name="familymemberList[4].familyMemberId" type="hidden" />
			<input name="jobInfoList[0].jobInfoId" type="hidden" />
			<input name="jobInfoList[1].jobInfoId" type="hidden" />
			<input name="surveybusinessinfoList[0].surveyBusinessInfoId"
				type="hidden" />
			<input name="surveybusinessinfoList[1].surveyBusinessInfoId"
				type="hidden" />
			<input name="surveybusinessinfoList[2].surveyBusinessInfoId"
				type="hidden" />
			<input name="depositList[0].depositid" type="hidden" />
			<input name="depositList[1].depositid" type="hidden" />
			<input name="depositList[2].depositid" type="hidden" />
			<input name="applicationList[0].applicationid" type="hidden" />
			<input name="applicationList[1].applicationid" type="hidden" />
			<input name="applicationList[2].applicationid" type="hidden" />
			</td>
			</tr>
		</table>
</form>
</div>