<%@ page language="java"  import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
java.util.Date currentTime = new java.util.Date();
String str_dataFormatter = formatter.format(currentTime);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<jsp:include page="../include/easyui.jsp"></jsp:include>
 <script type="text/javascript">
var serverName="<%=path%>";
var sysDate="<%=str_dataFormatter%>";
paramValidate=false;
	//经营请款 经营项目下拉 div选项
	var industryCategoryAll = new Object();
	var familySize = 0;
	var jobSize = 0 ;
	var surveybusinessSize = 0;
	
$(function(){
	var dicBorrower = new DataDictionary();
	dicBorrower.addDic("maritalStatusBorrower_borrowerService.maritalStatus_Y", "maritalStatus");
	dicBorrower.addDic("jobStatusBorrower_borrowerService.jobStatus_Y", "jobStatus");
	dicBorrower.addDic("nationality_borrowerService.nationality_Y", "national");
	dicBorrower.addDic("creditCoBorrowerBorrowerreRationship_creditCoBorrower.borrowerreRationship_Y", "borrowerreRationship");
	dicBorrower.addDic("creditCoBorrowerProfession_creditCoBorrower.profession_Y", "profession");
    var obj1 = $("input[id$='ProfessionX']");
	for(var i = 0; i < obj1.length;i++){
		dicBorrower.addDic("otherFamilyMemberList"+i+"ProfessionX_Y", "profession");
		dicBorrower.addDic("otherFamilyMemberList"+i+"BorrowerRationship_Y", "otherBorrowerreRationship");
		$("#otherFamilyMemberList"+i+"idNumber").validatebox({
			validType:"idnumberAll",
        	length:[18,18]
		});
	}
	
	var obj = $("input[id$='state']");
	for(var i = 0; i < obj.length;i++){
		dicBorrower.addDic("surveybusinessinfoList"+i+"businessLicense_Y", "yesno");
		dicBorrower.addDic("surveybusinessinfoList"+i+"taxRegistrationCertificate_Y", "yesno");
		dicBorrower.addDic("surveybusinessinfoList"+i+"state_Y", "stateSurveybusinessinfoList");
	}
	dicBorrower.addDic("proveDocument0", "yesno");
	dicBorrower.addDic("proveDocument1", "yesno");
	dicBorrower.addDic("proveDocument2", "yesno");
	dicBorrower.addDic("proveDocumentApplication0_applicationList[0].proveDocument_Y", "yesno");
	dicBorrower.addDic("proveDocumentApplication1", "yesno");
	dicBorrower.addDic("proveDocumentApplication2", "yesno");
	
	dicBorrower.addDic("depositOrganization0", "depositOrganization");
	dicBorrower.addDic("depositOrganization1", "depositOrganization");
	dicBorrower.addDic("depositOrganization2", "depositOrganization");
	dicBorrower.addDic("borrowUse0_applicationList[0].borrowUse_Y", "borroerUserConsult");
	dicBorrower.addDic("borrowUse1", "borroerUserConsult");
	dicBorrower.addDic("borrowUse2", "borroerUserConsult");
	dicBorrower.dicAjax();
    //是否试点营业部  通过是否试点营业部 控制借款人关系 共借人关系
    var specDep =  $.trim(${houseServeyVo.creditCoBorrowerSpe.specDep});
    if(specDep && specDep=="true") {
        $("#maritalStatusBorrower").combobox({
            hasDownArrow:true,
            onChange:function(old){
                var  maritalStatusBorrower= $.trim($("#maritalStatusBorrower").combobox("getValue"));
                if( maritalStatusBorrower == 2) {
                    $("#creditCoBorrowerBorrowerreRationship").combobox({
                        value:2,
                        readonly:true,
                        hasDownArrow:false
                    });
                } else {
                    $("#creditCoBorrowerBorrowerreRationship").combobox({
                        hasDownArrow:true
                    });
                 }
             }
        });

    } else{
        $("#maritalStatusBorrower").combobox({
             value:2,
             hasDownArrow:false
        });
    }

	//获取家庭、工作、经营的table行数
	var otherFamilyTable =document.getElementById("otherFamilyTable");
	familySize= otherFamilyTable.rows.length-2;
	var otherjobTable =document.getElementById("tbody");
	jobSize= otherjobTable.rows.length-2;
	
	var othersurveybusinessTable =document.getElementById("surveybusinessTable");
	surveybusinessSize= othersurveybusinessTable.rows.length-2;
	
	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"industryCategoryTotal",q:""},function(result){
		industryCategoryAll = result;
		for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
		 	var  index = industryCategoryAll.industryCategoryTotal[k].codaTableId;	
		 	var  name = "industryCategoryTotal"+index;
		 	var  objectName = "industryCategoryAll."+name;
		 	var object = (eval("("+objectName+")"));
			for(var i = 0 ; i < object.length;i++){
				var obj=$("input[id$='operatingItems']");
				for(var j=0;j<obj.length;j++){
					
				if($("#surveybusinessinfoList"+j+"operatingItems").val() == object[i].codeKey){
					$("#surveybusinessinfoList"+j+"operatingItemsShow").val(object[i].codeVlue);
					$("#surveybusinessinfoList"+j+"operatingItemsShow").validatebox("validate");
				}
				}
			}
		}
	});
	
	
	$("#businessNumber6,#businessYear").numberbox({  
		min:0,
		max:200,
		precision:2
	});  
	
	$("#borrowerServicepplyDate").datebox({
		onHidePanel:function(){
			var applydate = $("#borrowerServicepplyDate").datetimebox("getValue");
			var createDate=$("#createDate").val();
			$.ajax({ type:"post",  
			             url:"<%=basePath%>application/returnNowDate.do",  
			             data:{},  
			             dateType:"text", 
			             async: true,
			             success : function(resultDate){ 
								if(applydate != "" ){
									if(applydate > resultDate){
										alert("申请时间不能大于当前服务器时间,当前系统时间为- "+resultDate +"-");
					   					$("#borrowerServicepplyDate").datetimebox("setValue","");
									}else if(applydate>createDate){
										alert("申请时间不能大于小组编号创建时间,创建时间为- "+createDate +"-");
					   					$("#borrowerServicepplyDate").datetimebox("setValue","");
									}
								}
			             },
			             error : function(data){
			            	 alert(data);
			             } 
			});
			
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

		  	familyIdFlag = false;
			$("#formBorrrower").form('validate');
			$("#formOther").form('validate');
			familyIdFlag = true;
		  	provinceShow("provinceHome", "cityHome", "countyHome","townHome","villageHome","homeAddress","detailHome","hourseholdAddress");
			provinceShow("provinceBusiness", "cityBusiness", "countyBusiness","townBusiness","villageBusiness","businessAddress","detailBusiness","");
	// 地址
			var IdNumber = $.trim($("#IdNumber").val());
            if(IdNumber && IdNumber > 0)     {
                $("#familymemberListAge").numberbox("setValue",setAge(IdNumber));
            }
			var sex = maleOrFemalByIdCard($("#credentialsNumberBorrrower").val());
			$("#genderBorrower").val(sex);
			$("#showGender").val((sex == 0)?"男":"女");
});

//经营地址的验证  参数 所在的行数
function surveybusinessinfoListHang(elementName){
	var valueq ="";
	 
	$.each($("#surveybusinessTable tbody tr td [id ^= 'surveybusinessinfoList"+elementName+"']"),function(l,elementObject){
		if("combobox-f combo-f" == $("#"+elementObject.id).attr("class") || 
				undefined == $("#"+elementObject.id).attr("class")){
					 valueq = valueq+ $("#"+elementObject.id).combobox("getValue");
			 }else{
				 if($("#"+elementObject.id).attr("value") != undefined && $("#"+elementObject.id).attr("value") != null ){
						valueq = valueq+$("#"+elementObject.id).attr("value");
					}
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

// 工作 的验证
function hangValidate(elementName){
	var valueq ="";
	$.each($("#jobTable tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(l,elementObject){
		if($("#"+elementObject.id).attr("value") != undefined && $("#"+elementObject.id).attr("value") != null ){
			valueq = valueq+$("#"+elementObject.id).attr("value");
		}
		
	});
	if(valueq != null && valueq != "" ){
		$.each($("#tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(k,elementObject){
			var classNameOld = $("#"+elementObject.id).attr("class");
			var className = classNameOld.substring(7,classNameOld.indexOf(" v"));
			var elementvalue = $("#"+elementObject.id).attr("value");
			if(classNameOld.indexOf("validatebox") > 0) {
				$("#"+elementObject.id).validatebox({
					 	required:true
				 });
				$("#"+elementObject.id).validatebox("validate");
			}
			if(classNameOld.indexOf("numberbox") > 0){
				$("#"+elementObject.id).numberbox({
					 	required:true,
					 	value:elementvalue
				 });
				
				$("#"+elementObject.id).numberbox("validate");
			}
			if(classNameOld.indexOf("databox") > 0){
				$("#"+elementObject.id).databox({
					 	required:true,
					 	value:elementvalue
				 });
				$("#"+elementObject.id).databox("validate");
			}
			if(classNameOld.indexOf("combobox") > 0){
				$("#"+elementObject.id).combobox({
					 	required:true,
					 	value:elementvalue
				 });
				$("#"+elementObject.id).combobox("validate");
			}
			
		});
	}else{
			$.each($("#tbody tr td [id ^= 'jobInfoList"+elementName+"']"),function(k,elementObject){
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
//其他家庭成员
function familyIdNumber(obj){
//alert(familyIdFlag);
		//if(familyIdFlag){
		$("#"+obj.id).validatebox({
			validType:"idnumberAll",
        	length:[18,18]
		});
		var IdNumber = obj.value;   //获取刚填写IdNumber
		var IdNumberHidden=$.trim($("#IdNumberHidden").val());   //获取隐藏的IdNumber
			ajaxSubmit(serverName+"/customer/listForCombo.do?creditapplicationId="+parent.$("#creditapplicationId1").val(),{q:IdNumber},function(result){
				if(result != null && result != ""){
					var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 });
            		}
        		});
					if(result[0].blackFlag != null && result[0].blackFlag != ""){
						$.messager.alert("消息","该人已经加入黑名单","info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
							return;
						});
					}
				}
				var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 });
            		}
        		});
			});
			if(IdNumberHidden!=null&&IdNumber!=null&&IdNumber!=IdNumberHidden){
			ajaxSubmit(serverName+"/customer/checkForCredentialsNumber.do",{credentialsNumber:IdNumber,creditapplicationId:parent.$("#creditapplicationId1").val()},function(res){
				
				//console.info(res);
				if(res != null && res != ""){
					var tds = $(obj).parent().parent();
        			tds.find("td").each(function(x,y){
					if(x == 3){
					$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
               		 });
            		}
        		});
					if(res.success){
						
					}else{
						$.messager.alert("消息",res.msg,"info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
							return;
						});
					}
				}
			});
			}
			//判断添加的身份证号码是否存在
			var count=0;
			for(var i=0;i<=familySize;i++){
			for(var j=0;j<=familySize;j++){
				if(i==j){
					continue;
				}
				if(($("#otherFamilyMemberList"+(i)+"idNumber").val()!=undefined)&&($("#otherFamilyMemberList"+(j)+"idNumber").val()!=undefined)
				   &&($("#otherFamilyMemberList"+(i)+"idNumber").val()!="")&&($("#otherFamilyMemberList"+(j)+"idNumber").val()!="")){
				if(($("#otherFamilyMemberList"+(i)+"idNumber").val())==($("#otherFamilyMemberList"+(j)+"idNumber").val())){
				count=count+1;
						break;
				}
				}
			}
	}
	if(count>0){
		$.messager.alert("消息","每个家庭成员的身份证号码不能相同！","info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() 
							{
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
            				return;
						});
	}
		//}
	}

//家庭成员身份证
function familyIdNumber22(obj){
            if(familyIdFlag){          
		//var IdNumber = $.trim($("#familymemberList"+[familySize-1]+"idNumber").val());
		var IdNumber = obj.value;
		ajaxSubmit(serverName+"/customer/listForCombo.do?creditapplicationId="+parent.$("#creditapplicationId1").val(),{q:IdNumber},function(result){
			if(result != null && result != ""){
				///$("#familymemberList"+[familySize-1]+"age").numberbox("setValue",setAge(IdNumber));
				if(result[0].blackFlag != null && result[0].blackFlag != ""){
					$.messager.alert("消息","该人已经加入黑名单","info",function(){
						//$("#familymemberList"+[familySize-1]+"idNumber").val("");
						//$("#familymemberList"+[familySize-1]+"age").val("");
						var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
						return;
					});
				}else{
					if(result[0].customerType == "4"){
							$.messager.alert("消息","该人是本组的担保人","info",function(){
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
        		});
								//$("#familymemberList"+[familySize-1]+"idNumber").val("");
								//$("#familymemberList"+[familySize-1]+"age").val("");
								return;
							});
            		}
					// 其他担保人
					if(result[0].customerType == "1" 
							|| ((result[0].customerType == "" || result[0].customerType == null)&& result[0].guaranor == "0")){
	            		 var tds = $(obj).parent().parent();
        				 tds.find("td").each(function(x,y){
						 if(x == 3){
					     $(y).find("input").each(function() {
                   		 this.value=setAge(IdNumber);
                		});
            		}
       			 });
	            		return;
	            	}
	            	//借款人配偶 本组
					if(result[0].customerType == "3"
						&& result[0].creditApplicationId == parent.$("#creditapplicationId1").val()){
						var tds = $(obj).parent().parent();
        				 tds.find("td").each(function(x,y){
						 if(x == 3){
					     $(y).find("input").each(function() {
                   		 this.value=setAge(IdNumber);
                		});
            		}
       			 });
						return;
					}
					//状态可用
	            	if(result[0].flag == "" 
	            			|| result[0].flag == null
	            			||$.trim(result[0].flag) == "16"
	            			||$.trim(result[0].flag) == "20"
	            			||$.trim(result[0].flag) == "06"
	            			||$.trim(result[0].flag) == "27"
			            	||$.trim(result[0].flag) == "28"
	            			||$.trim(result[0].flag) == "26"){
	            		var tds = $(obj).parent().parent();
        				 tds.find("td").each(function(x,y){
						 if(x == 3){
					     $(y).find("input").each(function() {
                   		 this.value=setAge(IdNumber);
                		});
            		}
       			 });
	            		return;
	            	}
					// 其他担保人
	            	if(result[0].leader == "0"){
						var tds = $(obj).parent().parent();
        				 tds.find("td").each(function(x,y){
						 if(x == 3){
					     $(y).find("input").each(function() {
                   		 this.value=setAge(IdNumber);
                		});
            		}
       			 });
						return;
					}
		            	if(result[0].customerType == "3" 
		            	|| result[0].customerType == "0" 
		            	||result[0].customerType == "" 
		            	||result[0].customerType == null){
		            		if(result[0].creditApplicationId == parent.$("#creditapplicationId1").val() 
		            			&& result[0].customerType == "3"){
		            		}else{
			            		$.messager.alert("消息","该客户正在借款中！","info",function(){
									//$("#familymemberList"+[familySize-1]+"idNumber").val("");
									//$("#familymemberList"+[familySize-1]+"age").val("");
							var tds = $(obj).parent().parent();
        					tds.find("td").each(function(x,y){
							if(x == 3){
							$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}else if(x==2){
            				$(y).find("input").each(function() {
                    		this.value="";
                			});
            				}
            				});
									return;
						});
		            }
		          }
		            	
		            //补充作用	
		            	if(result[0].flag == "" 
		            			|| result[0].flag == null
		            			||$.trim(result[0].flag) == "16"
		            			||$.trim(result[0].flag) == "20"
		            			||$.trim(result[0].flag) == "06"
		            			||$.trim(result[0].flag) == "26"){
		            		 var tds = $(obj).parent().parent();
        				tds.find("td").each(function(x,y){
						if(x == 3){
						$(y).find("input").each(function() {
                   		 this.value=setAge(IdNumber);
                		});
           			 }
        		});
		            		return;
		            	}else {
		            		/*$.messager.alert("消息","该客户正在借款中！","info",function(){
								$("#IdNumber").val("");
								return;
							});*/
		            	}
						
				}
				
				/*if($.trim(result[0].flag) == "16"||$.trim(result[0].flag) == "20"||$.trim(result[0].flag) == "" || $.trim(result[0].flag) == null){
					 $("#familymemberListAge").val(setAge(IdNumber));
				}else{
					$.messager.alert("消息","拥有该身份证号的客户正在借款中！","info",function(){
						$("#IdNumber").val("");
						return;
					});
				}*/
			}
		
    });            
	 var tds = $(obj).parent().parent();
        tds.find("td").each(function(x,y){
			if(x == 3){
			$(y).find("input").each(function() {
                    this.value=setAge(IdNumber);
                });
            }
        });
	}
 }


//保存更新
function saveBorrowerService(){
	if($("#applyLimitBorrower").val() == "" ||$("#applyLimitBorrower").val() == undefined){
		parent.$.messager.alert("消息","请将数据填写正确完整！");
		return false;
	}
	var sex = maleOrFemalByIdCard($("#credentialsNumberBorrrower").val());
	$("#genderBorrower").val(sex);
	$("#showGender").val((sex == 0)?"男":"女");var obj = $("input[id^='profession']");
	
	//罗红杰 修改
	var company0=$("input[id$='company']");
	var operatingItemsDetail0=$("input[id$='operatingItemsDetail']");
		if(company0.length==0 && operatingItemsDetail0.length==0){
				parent.$.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
		return;		
	}
		
		if( $("#livingCommercialForm").attr("checked") == undefined &&
			$("#livingSelfForm").attr("checked") == undefined &&
			$("#livingRentForm").attr("checked") == undefined &&
			$("#livingRelativeForm").attr("checked") == undefined &&
			$("#livingOtherForm").attr("checked") == undefined)
		 {
			parent.$.messager.alert("消息","请至少选择一项 居住状况！");
			 return;
		 }
	
		familyIdFlag = false;
		if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
			parent.$("#addSave").linkbutton('disable');
			parent.$("#judaiButton").linkbutton('disable');
			parent.$("#fangqiButton").linkbutton('disable');
			parent.$("#tempAddSave").linkbutton('disable');
		}else{
			parent.$.messager.alert("消息","请将数据填写正确完整！");
			return;
		}
		familyIdFlag = true;
		ajaxSubmit(serverName+"/application/add.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
			if(result){
				parent.$("#borrowSerivce").dialog("close");
				parent.showBorrowerServiceApp();
				
				parent.$("#addSave").linkbutton('enable');
				parent.$("#judaiButton").linkbutton('enable');
				parent.$("#fangqiButton").linkbutton('enable');
				parent.$("#tempAddSave").linkbutton('enable');
			}else{
				parent.$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
				parent.$("#addSave").linkbutton('enable');
				parent.$("#judaiButton").linkbutton('enable');
				parent.$("#fangqiButton").linkbutton('enable');
				parent.$("#tempAddSave").linkbutton('enable');
			}
		});
}

function addAddress(paramValidate){
	if(paramValidate){
	var provinceHome=$("#provinceHome").combobox("getText");
	var cityHome=$("#cityHome").combobox("getText");
	var countyHome=$("#countyHome").combobox("getText");
	var townHome=$("#townHome").combobox("getText");
	var villageHome=$("#villageHome").combobox("getText");
	var detailHome=$("#detailHome").val();
	
	var homeAddress = provinceHome+"-"+cityHome+"-"+countyHome+"-"+townHome+"-"+villageHome+"-"+detailHome;
	$("#homeAddress").val(homeAddress);
	$("#hourseholdAddress").val(homeAddress);
	$("#hourseholdAddress").validatebox("validate");
	}
}
 
function addBusinessAddress(){
	
	var provinceBusiness=$("#provinceBusiness").combobox("getText");
	var cityBusiness=$("#cityBusiness").combobox("getText");
	var countyBusiness=$("#countyBusiness").combobox("getText");
	var townBusiness=$("#townBusiness").combobox("getText");
	var villageBusiness=$("#villageBusiness").combobox("getText");
	var detailBusiness=$("#detailBusiness").val();
	var BusinessAddress = provinceBusiness+"-"+cityBusiness+"-"+countyBusiness+"-"+townBusiness+"-"+villageBusiness+"-"+detailBusiness;
	$("#businessAddress").val(BusinessAddress);
}

function hhAddress(){
	if($("#hourseholdAddress").val() == null || $("#hourseholdAddress").val() == "" ||$("#hourseholdAddress").val() == undefined){
		$("#hourseholdAddress").val($("#homeAddress").val());
		$("#hourseholdAddress").validatebox("validate");
	}
}



//计算添加里的余额合计
function addBlance(){
	var totalBlance;
	var balance2= $("#balance2").val();
	var balance1= $("#balance1").val();
	var balance0= $("#balance0").val();
	totalBlance = accAdd(accAdd(balance2,balance1),balance0);
	$("#totalBlance").val(totalBlance);
	
}


//计算总额
function addAmount(){
	var TTAmount;
	
	var totalAmount2;
	var totalAmount1;
	var totalAmount0;
	
	var unitPrice2=$("#unitPrice2").val();
	var unitPrice1=$("#unitPrice1").val();
	var unitPrice0=$("#unitPrice0").val();
	
	var quantity2 = $("#quantity2").val();
	var quantity1 = $("#quantity1").val();
	var quantity0 = $("#quantity0").val();
	
	totalAmount2 = accMul(unitPrice2,quantity2).toFixed(2);
	totalAmount1 = accMul(unitPrice1,quantity1).toFixed(2);
	totalAmount0 = accMul(unitPrice0,quantity0).toFixed(2);
	
	if(totalAmount2 != 0){
		$("#totalAmount2").val(totalAmount2);
	}else{
		$("#totalAmount2").val("");
	}
	if(totalAmount1 != 0){
		$("#totalAmount1").val(totalAmount1);
	}else{
		$("#totalAmount1").val("");
	}
	if(totalAmount0 != 0){
		$("#totalAmount0").val(totalAmount0);
	}else{
		$("#totalAmount0").val("");
	}
	TTAmount= accAdd(accAdd(totalAmount2,totalAmount1),totalAmount0).toFixed(2);
	if(TTAmount != 0){
		$("#TTAmount").val(TTAmount);
	}else{
		$("#TTAmount").val("");
	}
	
}

function livingRentDate(){
	if($("#livingRentForm").attr("checked") == "checked"){
		
		$("#livingdateDiv").html('<input type="text" name="borrowerService.livingDate" id="livingDate" style="width: 130px;" />');
		$("#livingDate").datebox({required : true,width :130,editable:false});
	}else{
		$("#livingdateDiv").html('');
	}
}

function businessRentDate(){
	if($("#businessRentForm").attr("checked") == "checked"){
		$("#businessDateDiv").html('<input type="text" name="borrowerService.businessDate"  id="businessDate" style="width: 130px;" />');
		$("#businessDate").datebox({required : true,width :130,editable:false});
	}else{
		$("#businessDateDiv").html('');
	}
}


function changsuo(){
	if( $("[name='borrowerService.businessCommercial']")[0].checked == false &&
			$("[name='borrowerService.businessSelf']")[0].checked == false&&
			$("[name='borrowerService.businessRent']")[0].checked ==false &&
			$("[name='borrowerService.businessRelative']")[0].checked ==false &&
			$("[name='borrowerService.businessOther']")[0].checked ==false)
		 {
			
			$("#businessArea").val("");
			$("#businessArea").validatebox({
				 required:false,
				 validType:"number" 
			 });
			$("#businessArea").attr("readonly","readonly");
			$("#businessYear").val("");
			 $("#businessYear").validatebox({
				 required:false,
				 validType:"number" 
			 });
			 $("#businessYear").attr("readonly","readonly");
			 $("#officePhone").val("");
			 $("#officePhone").validatebox({
				 required:false,
				 validType:"number" 
			 });
			 $("#officePhone").attr("readonly","readonly");
			
		 }else{
			 $("#businessArea").validatebox({
				 required:true,
				 validType:"number"
			 });
			 $("#businessArea").attr("readonly",false);
			 $("#businessYear").validatebox({
				 required:true,
				 validType:"number"
			 });
			 $("#businessYear").attr("readonly",false);
			 $("#officePhone").validatebox({
				 required:true,
				 validType:"phoneNumber"
			 });
			 $("#officePhone").attr("readonly",false);
		 }
}

function changsuobusinessRentDate(){
	changsuo();
	businessRentDate();
}

function provinceShow(province, city, county,town,village,address,detail,address2){
	var homeAddress = $("#"+address).val();
	if(homeAddress != undefined){
		var homeAddressList = homeAddress.split("-");
		$("#"+detail ).val(homeAddressList[5]);
	}
	var villageHome = $("#"+village).combobox("getValue");
	var townHome;
	var countyHome;
	var cityHome;
	var provinceHome;
	if(villageHome != null && villageHome != ""){
		ajaxSubmit(serverName+"/NSC/selectByVallageId.do",{VallageId:villageHome},function(resultMap){
						$("#" + province).combobox(
								{
									editable: false,
								//	url : serverName + '/NSC/list.do',
									data: resultMap.nationalStandardCodeSheng,
									textField : 'cityName',
									valueField : 'cityCode',
									mode :'remote',
									delay :500,
									width :'150',
									value:resultMap.nationalStandardCodeShi.parentId,
									onSelect: function(value) {
										$("#" + city).combobox("clear");
										$("#" + county).combobox("setValue","");
										$("#"+town).combobox("setValue","");
										$("#"+village).combobox("setValue","");
										if(province == "provinceHome"){
										addAddress(true);
										}else{addBusinessAddress();
										}$("#" + city).combobox(
										{
										editable : false,
										url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										delay : 500,
										width : '150',
										value:""
										});
									}
								});
						$("#" + county).combobox({
							//url : serverName + '/NSC/listCity.do?parentId=' + cityHome,
							data : resultMap.nationalStandardCodeQuList,
							textField : 'cityName',
							valueField : 'cityCode',
							mode : 'remote',
							delay : 500,
							width : '150',
							//value : countyHome,
							value : resultMap.nationalStandardCodeQu.cityCode,
							onSelect:function(countryNSC){
								$("#"+village).combobox("setValue","");
								$("#"+town).combobox("setValue","");
								if(province == "provinceHome"){
									addAddress(true);
								}else{
									addBusinessAddress();
								}
								$("#"+town).combobox({
									url : serverName+ '/town/list.do?parentId='+countryNSC.cityCode,
									textField : 'townshipinfName',
									valueField : 'townshipinfoid',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:""
								});
							}
						});
						$("#" + city).combobox({
							editable : false,
							//url : serverName + '/NSC/listCity.do?parentId=' + provinceHome,
							data :  resultMap.nationalStandardCodeShiList,
							textField : 'cityName',
							valueField : 'cityCode',
							mode : 'remote',
							delay : 500,
							width : '150',
							//value : cityHome,
							value : resultMap.nationalStandardCodeShi.cityCode,
							onSelect:function(cityNSC){
								$("#" + county).combobox("setValue","");
								$("#"+town).combobox("setValue","");
								$("#"+village).combobox("setValue","");
								if(province == "provinceHome"){
									addAddress(true);
								}else{
									addBusinessAddress();
								}
								$("#" + county).combobox({
									editable : false,
									url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:""
								});
							}
							
						});
						
						$("#"+town).combobox({
							//url : serverName+ '/town/list.do?parentId='+countyHome,
							data: resultMap.townTownList,
							textField : 'townshipinfName',
							valueField : 'townshipinfoid',
							mode : 'remote',
							delay : 500,
							width : '150',
							//value : townHome,
							value : resultMap.townTown.townshipinfoid,
							onSelect:function(townTown){
								$("#"+village).combobox("setValue","");
								if(province == "provinceHome"){
									addAddress(true);
								}else{
									addBusinessAddress();
								}
								$("#"+village).combobox({
									url : serverName+ '/town/list.do?parentId='+townTown.townshipinfoid,
									textField : 'townshipinfName',
									valueField : 'townshipinfoid',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:""
								});
							}
						});
						$("#"+village).combobox({
							//url : serverName+ '/town/list.do?parentId='+townHome,
							data : resultMap.townVallageList,
							textField : 'townshipinfName',
							valueField : 'townshipinfoid',
							mode : 'remote',
							delay : 500,
							width : '150',
							value : villageHome,
							onSelect:function(){
								if(province == "provinceHome"){
									addAddress(true);
								}else{
									addBusinessAddress();
								}
							}
						});
						// cityCombobox(province, city, county,town,village,provinceHome);
//					});
//				});
//			});
//		});
		});
		$("#detailHome").validatebox('validate');
	}else{
		cityComboboxPublic(province, city, county,town,village,provinceHome,detail,address,address2);
	}
	 
}


//

function judaiFun(paramcaId){
	if( $("#jobInfoList0company").val() == "" &&
			$("#jobInfoList1company").val() == "" &&
			$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
			$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
			$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
		 {
			parent.$.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
			 return;
		 }
		if( $("#livingCommercialForm").attr("checked") == undefined &&
			$("#livingSelfForm").attr("checked") == undefined &&
			$("#livingRentForm").attr("checked") == undefined &&
			$("#livingRelativeForm").attr("checked") == undefined &&
			$("#livingOtherForm").attr("checked") == undefined)
		 {
			parent.$.messager.alert("消息","请至少选择一项 居住状况！");
			 return;
		 }
	
		familyIdFlag = false;
		if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
			
		}else{
			parent.$.messager.alert("消息","请将数据填写正确完整！");
			return;
		}
		familyIdFlag = true;
		ajaxSubmit(serverName+"/application/add.do","post",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
			if(result){
			}else{
				parent.$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
				
			}
		},false);
		ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:paramcaId,flag:"27"},function(result){
			if(result){
				parent.$.messager.alert("消息",result.msg,"info",function(){
					if(result.success){
						parent.$("#borrowSerivce").dialog("close");
						parent.document.location=serverName+'/jsp/rc/business/creditApplicationList.jsp';
					}
				});
			}else{
				parent.$.messager.alert("消息","系统错误,请重试或联系管理员！");
			}
		});
}
function addIndustry(valueField,textField){
			 var table = document.getElementById("tableUse");
			 var pchildren = table.childNodes;
			 //清空表中的行和列
			 for(var a=pchildren.length-1; a>=0; a--){
				 table.removeChild(pchildren[a]);
			 }
			 //判断颜色的 
			 var m = 0;
		 	for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
		 		//	不显示 家庭消费
		 			if(industryCategoryAll.industryCategoryTotal[k].codeVlue == "家庭消费"){
		 			}else{
					 	var  index = industryCategoryAll.industryCategoryTotal[k].codaTableId;	
					 	var  name = "industryCategoryTotal"+index;
					 	var  objectName = "industryCategoryAll."+name;
					 	var object = (eval("("+objectName+")"));
					 	var n = 0;
				     	var length = 0;
				     	if(object.length%3 > 0){
				     		length = parseInt(object.length/3)+1;
				     	}else{
				     		length = parseInt(object.length/3);
				     	}
						for(var i = 0 ; i < length;i++){
							m = m+1;
							var tr=document.createElement("tr");
							if(k % 2 == 0){
								//tr.setAttribute('bgcolor','#DDE4EE');
							}
								if(i == 0){
									var cell1=document.createElement("td"); 
									cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ industryCategoryAll.industryCategoryTotal[k].codeVlue+"</font>";
									cell1.setAttribute("rowspan",length);
									cell1.setAttribute("align","center");
									cell1.setAttribute("width","150");
									cell1.setAttribute("bgcolor","#DDE4EE");
									tr.appendChild (cell1);
								}
								
							
							for(var j = 0 ; j < 3 ;j++){
								var cell=document.createElement("td"); 
								cell.setAttribute("width","160");
								n = n+1;
								if(m % 2 == 0){
									cell.setAttribute("bgcolor","#DDE4EE");
								}
								if(object[n-1] != undefined){
									var key = object[n-1].codeKey;
									var value = object[n-1].codeVlue;
									var arrayObj = new Array(key,value);
									var obj = new Object();
									obj["codeKey"]=object[n-1].codeKey;
									obj["codeValue"]=object[n-1].codeVlue;
										cell.innerHTML = 
										"<a href='javaScript:addBorrow(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
									}
								tr.appendChild (cell); 
							}
					 	table.appendChild (tr); 
						}
		 			}
		 	}
		$("#windowBorrowUse").window({   
		    width:650,  
		    height:30*(Math.ceil(industryCategoryAll.length/3)), 
		    title:"经营项目",
		    minimizable:false,
		    maximizable:false,
		    collapsible:false,
				modal:true   
			});   
	}
	
	function addBorrow(){
		$("#"+arguments[2]).val(arguments[1]);
		$("#"+arguments[2]).validatebox("validate");
		$("#"+arguments[3]).val(arguments[0]);
		var i =  arguments[3].substring(22,23);
		//surveybusinessinfoListHang(i);
		$("#windowBorrowUse").window("close");
	}
	

	//家庭成员情况  加一行
	function addFamilyTable(){
		var otherFamilyTable =document.getElementById("otherFamilyTable");
		var tr=document.createElement("tr");
		tr.width="90%";
		var size= familySize;
   //console.info("--------------familySize---"+size);
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		var td7 = document.createElement("td");
		var td8 = document.createElement("td");
		var td9 = document.createElement("td");
		td1.innerHTML ='<input type="text" name="familymemberList['+size+'].name" id="businessNumber43" maxlength="10" style="width:100px"/>';
		td2.innerHTML ='<input  type="text" name="familymemberList['+size+'].borrowerreRationship" id="otherFamilyMemberList'+size+'BorrowerRationship" style="width:100px"/>';
		td3.innerHTML ='<input  type="text" name="familymemberList['+size+'].idNumber"  id="otherFamilyMemberList'+size+'idNumber" maxlength="18"  class="easyui-validatebox" validType="idnumberAll"  onblur="familyIdNumber(this);" />';
		td4.innerHTML ='<input  type="text" name="familymemberList['+size+'].age"  id="familymemberList'+size+'age" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3" style="width:80px"/>';
		td5.innerHTML ='<input  type="text" name="familymemberList['+size+'].profession" id="otherFamilyMemberList'+size+'ProfessionX" style="width:100px"/>';
		td6.innerHTML ='<input  name="familymemberList['+size+'].professionDetail" maxlength="20" style="width:100px" />';
		td7.innerHTML ='<input  type="text" name="familymemberList['+size+'].telphone" id="familymemberList'+size+'telephone" class="easyui-validatebox" validType="phoneNumber" maxlength="11" style="width:100px" />';
		td8.innerHTML ='<a  iconCls="icon-remove" id="familymemberList'+size+'but"  class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a>';
		td9.innerHTML='<input  value="'+(size)+'"  name="familymemberList['+size+'].seq" type="hidden"/>';
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tr.appendChild(td8);
		tr.appendChild(td9);
		otherFamilyTable.appendChild(tr);
		//var obj = $("input[id^='profession']");
		var dicBorrower1 = new DataDictionary();
		//for(var i = 1; i < obj.length;i++){
			dicBorrower1.addDic("otherFamilyMemberList"+size+"ProfessionX", "profession");
			dicBorrower1.addDic("otherFamilyMemberList"+size+"BorrowerRationship", "otherBorrowerreRationship");
		//}
		dicBorrower1.dicAjax();
		$("#familymemberList"+size+"age").numberbox();
		$("#familymemberList"+size+"telephone").validatebox();
		$("#familymemberList"+size+"but").linkbutton({iconCls:"icon-remove"});
		familySize=familySize+1;
	}

	//相关工作情况  加一行
	function addjobTable(){
		var otherjobTable =document.getElementById("tbody");
		var tr=document.createElement("tr");
		tr.width="100%";
		var size = jobSize;
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		td1.innerHTML ='<input type="text" name="jobInfoList['+size+'].company" required="true" id="jobInfoList'+size+'company"   class="easyui-validatebox" size="18" maxlength="20" />'
		 +'<input  name="jobInfoList['+size+'].seq" type="hidden"    value="'+(size+1)+'"/>';
		td2.innerHTML ='<input  type="text" name="jobInfoList['+size+'].workunitAddress"  required="true" id="jobInfoList'+size+'workunitAddress"  class="easyui-validatebox" size="18" maxlength="30"/>';
		td3.innerHTML ='<input  type="text" name="jobInfoList['+size+'].years" id="jobInfoList'+size+'years"  required="true"  class="easyui-numberbox"  onkeypress="if(event.which==45){return false;}" size="18" validType="numberDivided" maxlength="4" precision="1"/>';
		td4.innerHTML ='<input  type="text" name="jobInfoList['+size+'].post" id="jobInfoList'+size+'post" required="true" class="easyui-validatebox" size="18" maxlength="20"/>';
		td5.innerHTML ='<input  type="text" name="jobInfoList['+size+'].income" class="easyui-numberbox" required="true"  id="jobInfoList'+size+'income"  onkeypress="if(event.which==45){return false;}" precision="2" size="18" maxlength="10"/>';
		td6.innerHTML ='<a  iconCls="icon-remove"  id="jobInfoList'+size+'but" class="easyui-linkbutton" onclick="$(this).parent().parent().remove()" ></a>';
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		otherjobTable.appendChild(tr);
		$("#jobInfoList"+size+"company").validatebox({required:true});
		$("#jobInfoList"+size+"workunitAddress").validatebox({required:true});
		$("#jobInfoList"+size+"years").numberbox({required:true});
		$("#jobInfoList"+size+"post").validatebox({required:true});
		$("#jobInfoList"+size+"income").numberbox({required:true});
		
		$("#jobInfoList"+size+"company").validatebox("validate");
		$("#jobInfoList"+size+"workunitAddress").validatebox("validate");
		$("#jobInfoList"+size+"years").numberbox("validate");
		$("#jobInfoList"+size+"post").validatebox("validate");
		$("#jobInfoList"+size+"income").numberbox("validate");
		
		$("#jobInfoList"+size+"company").validatebox("moveTips");
		$("#jobInfoList"+size+"workunitAddress").validatebox("moveTips");
		$("#jobInfoList"+size+"years").numberbox("moveTips");
		$("#jobInfoList"+size+"post").validatebox("moveTips");
		$("#jobInfoList"+size+"income").numberbox("moveTips");
		$("#jobInfoList"+size+"but").linkbutton({iconCls:"icon-remove"});
		jobSize =jobSize+1;
		
	}

	//经营情况 加一行
	function addsurveybusinessTable(){
		var othersurveybusinessTable =document.getElementById("surveybusinessTable");
		var tr=document.createElement("tr");
		tr.width="100%";
		var size= surveybusinessSize;
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		var td7 = document.createElement("td");
	    td1.innerHTML ='<input name="surveybusinessinfoList['+size+'].operatingItems" id="surveybusinessinfoList'+size+'operatingItems" style="width:130px;display:none;"/>'
		+'<input type="text" required="true"  class="easyui-validatebox"  readonly id="surveybusinessinfoList'+size+'operatingItemsShow" maxlength="10"  onclick="addIndustry(\'surveybusinessinfoList'+size+'operatingItemsShow\',\'surveybusinessinfoList'+size+'operatingItems\')" style="width:130px;"/>';
		td2.innerHTML ='<input  type="text" name="surveybusinessinfoList['+size+'].operatingItemsDetail" required="true" class="easyui-validatebox"  id="surveybusinessinfoList'+size+'operatingItemsDetail" style="width:160px"  size="20" maxlength="20"/>';
		td3.innerHTML ='<input  type="text" name="surveybusinessinfoList['+size+'].startingDate" required="true" id="surveybusinessinfoList'+size+'startingDate" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate"  size="20" maxlength="4" style="width:154px;"/>';
		td4.innerHTML ='<input  type="text" name="surveybusinessinfoList['+size+'].state"  id="surveybusinessinfoList'+size+'state" style="width:100px;"/>';
		td5.innerHTML ='<input  type="text" name="surveybusinessinfoList['+size+'].businessLicense" id="surveybusinessinfoList'+size+'businessLicense" style="width:100px;"/>';
		td6.innerHTML ='<input  type="text" name="surveybusinessinfoList['+size+'].taxRegistrationCertificate" id="surveybusinessinfoList'+size+'taxRegistrationCertificate"  style="width:100px;" />';
		td7.innerHTML ='<a id="surveybusinessinfoList'+size+'but" iconCls="icon-remove" class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a>'+'<input  name="surveybusinessinfoList['+size+'].seq" type="hidden"    value="'+(size+1)+'"/>';
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		othersurveybusinessTable.appendChild(tr);
		//var obj = $("input[id$='state']");
		var dicBorrower2 = new DataDictionary();
		//for(var i = 0; i < obj.length;i++){
			dicBorrower2.addDic("surveybusinessinfoList"+size+"state_Y", "stateSurveybusinessinfoList");
			dicBorrower2.addDic("surveybusinessinfoList"+size+"businessLicense_Y", "yesno");
			dicBorrower2.addDic("surveybusinessinfoList"+size+"taxRegistrationCertificate_Y","yesno");
		dicBorrower2.dicAjax();
		//}
		$("#surveybusinessinfoList"+size+"operatingItemsShow").validatebox({required:true});
		$("#surveybusinessinfoList"+size+"operatingItemsDetail").validatebox({required:true});
		$("#surveybusinessinfoList"+size+"startingDate").numberbox({required:true});
		$("#remove").linkbutton();
		
		$("#surveybusinessinfoList"+size+"operatingItemsShow").validatebox("validate");
		$("#surveybusinessinfoList"+size+"operatingItemsDetail").validatebox("validate");
		$("#surveybusinessinfoList"+size+"startingDate").numberbox("validate");
		
		$("#surveybusinessinfoList"+size+"operatingItemsShow").validatebox("moveTips");
		$("#surveybusinessinfoList"+size+"operatingItemsDetail").validatebox("moveTips");
		$("#surveybusinessinfoList"+size+"startingDate").numberbox("moveTips");
		$("#surveybusinessinfoList"+size+"but").linkbutton({iconCls:"icon-remove"});
		surveybusinessSize =surveybusinessSize+1;
	}
</script>
</head>
<body class="easyui-layout">
<div region="center">
	<form id="formBorrrower" name="form1" method="post" novalidate >
		<table width="892"   align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="5" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
					<span style="font-size: 25px; font-weight: 700; color: #4B0082;">
					业务申请表</span>
					<p>&nbsp;</p>
					 <span style="font-size: 15px; font-weight: 700;">申请时间：</span>
				<input name="borrowerService.applyDate" value="<fmt:formatDate value='${houseServeyVo.borrowerService.applyDate}' pattern='yyyy-MM-dd HH:mm:ss' />" id="borrowerServicepplyDate" required="true" style="width:150px;"  editable="false" class="easyui-datetimebox"/>
					 <input type="hidden" value="<fmt:formatDate value='${houseServeyVo.creditApplication.createDate}' pattern='yyyy-MM-dd HH:mm:ss' />" id="createDate" />&nbsp; 
					 <span style="font-size: 15px; font-weight: 700;"></span>
					<input id="borrowerServiceAppId" value="${houseServeyVo.borrowerService.borrowerServiceAppId}" name="borrowerService.borrowerServiceAppId" type="hidden"> 
					<input id="creditapplicationIdBorrower" value="${houseServeyVo.borrowerService.creditapplicationId}" name="borrowerService.creditapplicationId" type="hidden">
					<!-- 产品上限 -->
					<input id="capitalUpperLimit" value="${houseServeyVo.creditApplication.capitalUpperLimit }" name="creditApplication.capitalUpperLimit" type="hidden">
					<!-- 产品下限 -->
					<input id="capitalLowerLimit" value="${houseServeyVo.creditApplication.capitalLowerLimit}" name="creditApplication.capitalLowerLimit" type="hidden">
					
				</td>
			</tr>
		  <tr>
		    <td width="156" align="right">业务编号：</td>
		    <td width="193"><input type="text"  value="${houseServeyVo.borrowerService.businessNumber}"  id="businessNumberBorrower" readonly="readonly"   style="width: 147px;"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">民族：</td>
		    <td width="207"><input value="${houseServeyVo.borrowerService.nationality}" type="text" name="borrowerService.nationality"   id="nationality" required="true"  style="width: 147px;"/></td>
		  </tr>
		   <tr>
		    <td width="156" align="right">申请金额(元)：</td>
		    <td width="193"><input value="${houseServeyVo.borrowerService.applyLimit}" type="text" name="borrowerService.applyLimit" id="applyLimitBorrower" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" onblur="validateCapitaLimit()"  style="width: 147px;" maxlength="8"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">身份证：</td>
		    <td width="207"><input value="${houseServeyVo.borrowerService.credentialsNumber}" type="text" name="borrowerService.credentialsNumber" id="credentialsNumberBorrrower" readonly="readonly"  style="width: 147px;"/></td>
		  </tr>
		  <tr>
		    <td align="right">姓名：</td>
		    <td width="193"><input value="${houseServeyVo.borrowerService.name}" type="text" name="borrowerService.name" id="nameBorrow" class="easyui-validatebox" required="true"  style="width: 147px;" maxlength="10"/></td>
		    <td>&nbsp;</td>
		    <td align="right">曾用名：</td>
		    <td><input type="text" value="${houseServeyVo.borrowerService.former}" name="borrowerService.former" id="businessNumber5"  style="width: 147px;" maxlength="10"/></td>
		  </tr>
		  <tr>
		    <td align="right">出生日期：</td>
		    <td><input value="${houseServeyVo.borrowerService.birthday}" type="text" name="borrowerService.birthday" id="businessNumber3" readonly="readonly"  style="width: 147px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">性别：</td>
		    <td><input value="${houseServeyVo.borrowerService.gender}" name="borrowerService.gender" id="genderBorrower" type="hidden" style="width: 147px;"/>
			  <c:choose>
			    <c:when test="${houseServeyVo.borrowerService.gender == 0}">
			    <input  id="showGender" readonly="readonly" style="width: 147px;" value = "男"/>
			    </c:when>
			    <c:when test="${houseServeyVo.borrowerService.gender == 1}">
			    <input  id="showGender" readonly="readonly" style="width: 147px;" value = "女"/>
			    </c:when>
			    <c:otherwise>
			    <input  id="showGender" readonly="readonly" style="width: 147px;" value = ""/>
			    </c:otherwise>
			   </c:choose>
		    </td>
		  </tr>
		   <tr>
		    <td align="right">婚姻状况：</td>
		    <td>
                <%-- 通过是否试点营业部 控制借款人婚姻状况 --%>
               <c:if test="${houseServeyVo.creditCoBorrowerSpe.specDep eq 'true'}">
                   <input value="${houseServeyVo.borrowerService.maritalStatus}" name="borrowerService.maritalStatus"   onchange="changeMarryStatus()" id="maritalStatusBorrower" required="true" style="width: 150px;"/></td>
               </c:if>
               <c:if test="${houseServeyVo.creditCoBorrowerSpe.specDep eq 'false'}">
                   <input value="${houseServeyVo.borrowerService.maritalStatus}" name="borrowerService.maritalStatus" hasDownArrow="false" id="maritalStatusBorrower" required="true" style="width: 150px;"/></td>
               </c:if>
               <td>&nbsp;</td>
		    <td align="right">就业状况：</td>
		    <td> 
		      <input value="${houseServeyVo.borrowerService.jobStatus}" name="borrowerService.jobStatus"  id="jobStatusBorrower" required="true" style="width: 150px;"/></td>
		  </tr>
		    <tr>
		    <td align="right" valign="top">家庭住址分类：</td>
		    <td><p>
		      <label>
		      	<c:choose>
			      	<c:when test="${houseServeyVo.borrowerService.livingCommercial==1}">
			        	<input  checked="checked"  type="checkbox" value="1" id="livingCommercialForm" name="borrowerService.livingCommercial" />
			        </c:when>
			        <c:otherwise>
						<input   type="checkbox" value="1" id="livingCommercialForm" name="borrowerService.livingCommercial" />
					</c:otherwise>
				</c:choose>
		        自有商品房</label>
		      <br />
		      <label>
		     	<c:choose>
			      	<c:when test="${houseServeyVo.borrowerService.livingSelf==1}">
			        	 <input  type="checkbox" value="1" name="borrowerService.livingSelf"  checked="checked"  id="livingSelfForm" />
			        </c:when>
			        <c:otherwise>
						 <input type="checkbox" value="1" name="borrowerService.livingSelf"   id="livingSelfForm" />
					</c:otherwise>
		       </c:choose>
		        自有宅基地</label>
		      <br />
		      <label>
		      <c:choose>
		       	<c:when test="${houseServeyVo.borrowerService.livingRent==1}">
		        	 <input  checked="checked"   type="checkbox" value="1" name="borrowerService.livingRent" onclick="livingRentDate();"  id="livingRentForm" />
		        </c:when>
		        <c:otherwise>
					 <input  type="checkbox" value="1" name="borrowerService.livingRent" onclick="livingRentDate();"  id="livingRentForm" />
				</c:otherwise>
		       </c:choose>
		        租住</label>
		      （到期日:
		      <c:choose>
		      		<c:when test="${houseServeyVo.borrowerService.livingRent==1}">
				        <span id="livingdateDiv" >
				        		<input type="text" value="<fmt:formatDate value='${houseServeyVo.borrowerService.livingDate}' pattern='yyyy-MM-dd' />"  class="easyui-datebox" required="true"  editable="false" name="borrowerService.livingDate" id="livingDate" style="width: 130px;" />
					    </span>
			        </c:when>
			        <c:otherwise>
						 <span id="livingdateDiv" >
			      		</span>
					</c:otherwise>
			   </c:choose>
		      ）<br />
		      <label>
		      <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.livingRelative==1}">
		        	  <input   checked="checked"  type="checkbox" name="borrowerService.livingRelative" value="1" id="livingRelativeForm" />
		        </c:when>
		        <c:otherwise>
					  <input    type="checkbox" name="borrowerService.livingRelative" value="1" id="livingRelativeForm" />
				</c:otherwise>
		       </c:choose>
		        亲戚住房</label>
		      <br />
		      <label>
		      <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.livingOther==1}">
		        	  <input checked="checked"  type="checkbox" name="borrowerService.livingOther" value="1" id="livingOtherForm"/>
		        </c:when>
		        <c:otherwise>
					  <input  type="checkbox" name="borrowerService.livingOther" value="1" id="livingOtherForm"/>
				</c:otherwise>
		      </c:choose>  
		        其他</label>
		      <br />
		    </p></td>
		    <td>&nbsp;</td>
		    <td align="right" valign="top">现经营场所地址分类：</td>
		    <td><label>
		    <c:choose>
		    	<c:when test="${houseServeyVo.borrowerService.businessCommercial==1}">
		        	  <input checked="checked"  type="checkbox"   name="borrowerService.businessCommercial" value="1"  />
		        </c:when>
		        <c:otherwise>
					   <input   type="checkbox"   name="borrowerService.businessCommercial" value="1"  />
				</c:otherwise>
		     </c:choose>
		      自有商品房</label>
		      <br />
		      <label>
		      <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.businessSelf==1}">
		        	   <input  checked="checked"  type="checkbox"   name="borrowerService.businessSelf" value="1" id="CheckboxGroup1_6" />
		        </c:when>
		        <c:otherwise>
					  <input type="checkbox"   name="borrowerService.businessSelf" value="1" id="CheckboxGroup1_6" />
				</c:otherwise>
		      </c:choose>
		        自有宅基地</label>
		      <br />
		      <label>
		      <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.businessRent==1}">
		        	   <input checked="checked"  type="checkbox" onclick="businessRentDate();" name="borrowerService.businessRent"   id="businessRentForm" value="1" />
		        </c:when>
		        <c:otherwise>
					  <input type="checkbox" onclick="businessRentDate();" name="borrowerService.businessRent"   id="businessRentForm" value="1" />
				</c:otherwise>
		       </c:choose> 
		        租住</label>
			（到期日: <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.businessRent==1}">
		        	   	<span id="businessDateDiv" >
		        	   		<input type="text" value="<fmt:formatDate value='${houseServeyVo.borrowerService.businessDate}' pattern='yyyy-MM-dd' />" class="easyui-datebox" editable="false" required="true" name="borrowerService.businessDate"  id="businessDate" style="width: 130px;" />
						</span>
		        </c:when>
		        <c:otherwise>
					  	<span id="businessDateDiv" >
						</span>
				</c:otherwise>
		       </c:choose> 
		）<br />
		       <label>
		        <input type="checkbox"   name="borrowerService.businessRelative" value="1" id="CheckboxGroup1_8" />
		        亲戚住房</label>
		      <br />
		      <label>
		      <c:choose>
		      	<c:when test="${houseServeyVo.borrowerService.businessOther==1}">
		        	    <input checked="checked"  type="checkbox"   name="borrowerService.businessOther" value="1" id="CheckboxGroup1_9" />
		        </c:when>
		        <c:otherwise>
					   <input type="checkbox"   name="borrowerService.businessOther" value="1" id="CheckboxGroup1_9" />
				</c:otherwise>
		      </c:choose>
		        其他</label></td>
		    </tr>
		     <tr>
		    <td align="right">家庭住址面积(㎡)：</td>
		    <td><input value="${houseServeyVo.borrowerService.livingArea }" type="text" name="borrowerService.livingArea" id="businessNumber8" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"  style="width: 150px;" maxlength="10" /></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所面积(㎡)：</td>
		    <td><input value="${houseServeyVo.borrowerService.businessArea }" type="text" name="borrowerService.businessArea" id="businessArea"  class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number"  style="width: 150px;"  maxlength="10"/></td>
		    </tr>
		  <tr>
		    <td align="right">家庭住址居住年限(年)：</td>
		    <td><input  value="${houseServeyVo.borrowerService.livingYear }"  type="text"   name="borrowerService.livingYear" id="businessNumber6" required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所已使用年限(年)：</td>
		    <td><input  value="${houseServeyVo.borrowerService.businessYear }"  type="text" name="borrowerService.businessYear" id="businessYear" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}"   style="width: 150px;"/></td>
		  </tr>
		    <tr>
		    <td align="right">手机号码：</td>
		    <td><input  value="${houseServeyVo.borrowerService.mobilephone }"  type="text" maxlength="11" name="borrowerService.mobilephone" id="mobilephoneBorrow" required="true" class="easyui-validatebox" validType="phoneNumber"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">办公电话：</td>
		    <td><input  value="${houseServeyVo.borrowerService.officePhone }"  type="text" maxlength="11" name="borrowerService.officePhone" id="officePhone" class="easyui-validatebox" validType="phoneNumber"   style="width: 150px;"/></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
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
        <input  value="${houseServeyVo.borrowerService.villageId}" type="text" name="borrowerService.villageId"  id="villageHome" editable="false" required="true" class="easyui-combobox"  style="width: 150px;"/>村
      </p>
      <p style="width: 180px;">
        <input type="text"  id="detailHome" class="easyui-validatebox"  onkeydown="paramValidate=true;" onblur="addAddress(paramValidate);" required="true" style="width: 147px;" maxlength="10"/>号
      </p>
       <p>
        <textarea   name="borrowerService.homeAddress" rows="3" cols="6" readonly="readonly" id="homeAddress" style="width:  147px;" class="easyui-validatebox"  validType="length[0,100]">${houseServeyVo.borrowerService.homeAddress}</textarea>
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
        <input value="${houseServeyVo.borrowerService.townId}" type="text" editable="false"  name="borrowerService.townId" class="easyui-combobox"  id="villageBusiness" style="width: 150px;" />村
      </p>
      <p>
        <input type="text"   id="detailBusiness" onblur="addBusinessAddress();" style="width: 147px;" maxlength="10"/>号
      </p>
       <p>
        <textarea name="borrowerService.residenceAddress" rows="3" cols="6" readonly="readonly" id="businessAddress" style="width: 147px;" class="easyui-validatebox"  validType="length[0,100]">${houseServeyVo.borrowerService.residenceAddress}</textarea>
      </p>
      </td>
   </tr>
      <tr>
   	 		<td align="right" valign="top">家庭住址:</td>
		    <td>
		    	<textarea name="borrowerService.hourseholdAddress" onblur="hhAddress();" 
		    	maxlength= "60" class="easyui-validatebox"  validType="length[0,80]"
		    	rows="3" cols="6"  required="true"
		    	 id="hourseholdAddress" style="width: 147px;">${houseServeyVo.borrowerService.hourseholdAddress}</textarea>
		    </td>
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
      <span style="font-size: 15px; font-weight: 700;">共借人信息</span>
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
      <td><input  value="${houseServeyVo.creditCoBorrower.name }"  required="true" class="easyui-validatebox"   name="creditCoBorrower.name" maxlength="10" /></td>
      <td><input  name="creditCoBorrower.borrowerreRationship" id="creditCoBorrowerBorrowerreRationship"     value="${houseServeyVo.creditCoBorrower.borrowerreRationship }"   size="15" hasDownArrow="false"/></td>
      <td><input  value="${houseServeyVo.creditCoBorrower.idNumber }" readonly="readonly"  name="creditCoBorrower.idNumber" id="idNumber"  size="25" /></td>
      <td><input  value="${houseServeyVo.creditCoBorrower.age }"   readonly="readonly" id="familymemberListAge"  name="creditCoBorrower.age"    size="15"/></td>
      <td><input  value="${houseServeyVo.creditCoBorrower.profession }"  required="true"    name="creditCoBorrower.profession" id="creditCoBorrowerProfession" size="15"/></td>
      <td><input  value="${houseServeyVo.creditCoBorrower.professionDetail }"  required="true" class="easyui-validatebox"   name="creditCoBorrower.professionDetail" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.creditCoBorrower.telphone }"  required="true" class="easyui-validatebox"   name="creditCoBorrower.telphone" validType="phoneNumber" maxlength="11"/>
      		<input  value="1" name=".creditCoBorrower.seq"   type="hidden"  />
      </td>
    </tr>
  </table>
  <p>&nbsp;	</p>
  <table width="90%"  id="otherFamilyTable"  align="center" >
     <tr>
       <td colspan="9"  align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">   
  		 <span style="font-size: 15px; font-weight: 700;">其他家庭成员情况</span>
  		 <a iconCls="icon-addOne" class="easyui-linkbutton" onclick="javascript:addFamilyTable()"></a> 
  		  
  		<!-- <span style="font-size: 15px; font-weight: 700;">其他家庭成员情况</span><a iconCls="icon-add" class="easyui-linkbutton" onclick="javascript:addFamilyTable()">加</a> 
  		  <a iconCls="icon-add" class="easyui-linkbutton" onclick="javascript:subtractFamilyTable();">减</a> -->
	</td>
    </tr>
    <tr width="100%">
     <td width="198px">姓名</td>
      <td width="180px">与申请人关系</td>
      <td width="155">身份证号</td>
      <td width="198px">年龄</td>
      <td width="180px">职业</td>
      <td width="198px">职业明细</td>
      <td  width="196px">联系电话</td>
       <td width="60px" clospan='2'>操作</td>
     </tr>

   <c:forEach items="${houseServeyVo.familymemberList}" var="family" varStatus="familyC" begin="0">
    		<tr width="100%">
		      <td width="198px"><input  value="${family.name }"  type="text" name="familymemberList[${familyC.count-1}].name"  maxlength="10" style="width:100px" /></td>
		       <td width="180px"><input  value="${family.borrowerreRationship }"  type="text" name="familymemberList[${familyC.count-1}].borrowerreRationship" id="otherFamilyMemberList${familyC.count-1}BorrowerRationship" style="width:100px" /></td>
		     <td><input  value="${family.idNumber }" type="text"  name="familymemberList[${familyC.count-1}].idNumber" id="otherFamilyMemberList${familyC.count-1}idNumber"   onblur="familyIdNumber(this);" maxlength="18" />
		      <input  value="${houseServeyVo.creditCoBorrower.idNumber }" readonly="readonly"   id="IdNumberHidden"  size="25" type="hidden" />
		     </td>
		      <td width="198px"><input  value="${family.age }"  type="text" name="familymemberList[${familyC.count-1}].age"  class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3" style="width:80px" /></td>
		      <td width="180px"><input  value="${family.profession }"  type="text" name="familymemberList[${familyC.count-1}].profession" id="otherFamilyMemberList${familyC.count-1}ProfessionX" style="width:100px" /></td>
		      <td width="198px"><input  value="${family.professionDetail }"   name="familymemberList[${familyC.count-1}].professionDetail" maxlength="20" style="width:100px" /></td>
		      <td width="196px"><input  value="${family.telphone }"  type="text" name="familymemberList[${familyC.count-1}].telphone"  class="easyui-validatebox" validType="phoneNumber" maxlength="11" style="width:100px" />
		      <td width="60px" clospan='2'><a iconCls="icon-remove"  class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a></td>
		      	<input  value="${familyC.count}" name="familymemberList[${familyC.count-1}].seq" type="hidden"  />
		      </td>
		    </tr>
    </c:forEach>  
  </table>
 
  <p>&nbsp;</p>
  <table width="90%" id="jobTable" align="center">
  <tbody id="tbody">
    <tr >
      <td colspan="6" align="center"   style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
	   <span style="font-size: 15px; font-weight: 700;"> 相关工作情况</span> 
	   <a iconCls="icon-addOne" class="easyui-linkbutton" onclick="javascript:addjobTable()"></a> 
  		  
	   </td>
    </tr>
    <tr width="100%">
      <td width="200px">就职公司</td>
      <td width="198px">地址</td>
      <td width="196px">工作年限</td>
      <td width="190px">职位</td>
      <td width="196px">每月净收入(元)</td>
      <td width="54px">操作</td>
    </tr>
    <c:forEach items="${houseServeyVo.jobInfoList}" var="job" varStatus="jobC" begin="0">
    <tr width="100%">
      <td><input  value="${job.company }"   class="easyui-validatebox"s required="true" name="jobInfoList[${jobC.count-1}].company" type="text" id="jobInfoList${jobC.count-1}company" size="18" maxlength="20"/></td>
      <td><input  value="${job.workunitAddress }"   class="easyui-validatebox" required="true"  name="jobInfoList[${jobC.count-1}].workunitAddress" type="text" id="jobInfoList${jobC.count-1}workunitAddress" size="18" maxlength="30"/></td>
      <td><input  value="${job.years }" required="true"   class="easyui-numberbox"  
      onkeypress="if(event.which==45){return false;}"  name="jobInfoList[${jobC.count-1}].years" type="text" 
      id="jobInfoList${jobC.count-1}years" size="18" validType="numberDivided" maxlength="4" precision="1"/></td>
      <td><input  value="${job.post }"  required="true"  class="easyui-validatebox"  name="jobInfoList[${jobC.count-1}].post" type="text" id="jobInfoList${jobC.count-1}post" size="18" maxlength="20"/></td>
      <td><input  value="${job.income }"  required="true"  class="easyui-numberbox"  name="jobInfoList[${jobC.count-1}].income" type="text" onkeypress="if(event.which==45){return false;}" id="jobInfoList${jobC.count-1}ncome"  precision="2" size="18" maxlength="10"/> 
      <td width="60px" clospan='2'><a iconCls="icon-remove" class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a></td>
      <input  name="jobInfoList[${jobC.count-1}].seq" type="hidden"    value="${jobC.count}"/>
      </td>
    </tr>
    </c:forEach>
</tbody>
  </table>
  <p>&nbsp;</p>
  <table width="898px"  id="surveybusinessTable" align="center">
   <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
      <span style="font-size: 15px; font-weight: 700;">经营情况（包括当前和历史的经营情况）</span>
      <a iconCls="icon-addOne" class="easyui-linkbutton" onclick="javascript:addsurveybusinessTable()"></a> 
      </td>
    </tr>
    <tr width="890px"> 
      <td width="130px">经营项目所在行业</td>
      <td width="173px">经营项目详细</td>
      <td width="154px">起始年份</td>
      <td width="116px">状态</td>
      <td width="94px">营业执照</td>
      <td width="95px">税务登记证</td>
      <td width="54px">操作</td>
    </tr>
   <c:forEach items="${houseServeyVo.surveybusinessinfoList}" var="surveybusiness" varStatus="surveybusinessC" begin="0">  
 <tr>
      <td>
      <input id="surveybusinessinfoList${surveybusinessC.count-1}operatingItems" type="hidden"  class="easyui-validatebox"  name="surveybusinessinfoList[${surveybusinessC.count-1}].operatingItems"   value="${surveybusiness.operatingItems }" style="width:130px"/>
      <input  class="easyui-validatebox"  readonly="readonly" required="true"  id="surveybusinessinfoList${surveybusinessC.count-1}operatingItemsShow" onClick="addIndustry('surveybusinessinfoList${surveybusinessC.count-1}operatingItemsShow','surveybusinessinfoList${surveybusinessC.count-1}operatingItems');" style="width:130px"/><%--
     <a class="easyui-linkbutton"  onclick="$('#surveybusinessinfoList${surveybusinessC.count}operatingItemsShow').val('');$('#surveybusinessinfoList${surveybusinessC.count}operatingItems').val('');surveybusinessinfoListHang(${surveybusinessC.count);" plain="true">清除</a>--%>
      </td>
      <td><input  value="${surveybusiness.operatingItemsDetail }" required="true"   class="easyui-validatebox"  name="surveybusinessinfoList[${surveybusinessC.count-1}].operatingItemsDetail" type="text" id="surveybusinessinfoList${surveybusinessC.count-1}operatingItemsDetail" size="20" maxlength="20" style="width:160px"/></td>
      <td><input  value="${surveybusiness.startingDate }"  required="true" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="yearValidate"  name="surveybusinessinfoList[${surveybusinessC.count-1}].startingDate" type="text" id="surveybusinessinfoList${surveybusinessC.count-1}startingDate" size="20" maxlength="4" style="width:154px"/></td>
      <td><input  value="${surveybusiness.state }"  name="surveybusinessinfoList[${surveybusinessC.count-1}].state" id="surveybusinessinfoList${surveybusinessC.count-1}state" style="width:100px" /></td>
      <td><input  value="${surveybusiness.businessLicense }"  name="surveybusinessinfoList[${surveybusinessC.count-1}].businessLicense" id="surveybusinessinfoList${surveybusinessC.count-1}businessLicense" style="width:100px" /></td>
      <td><input  value="${surveybusiness.taxRegistrationCertificate }"  name="surveybusinessinfoList[${surveybusinessC.count-1}].taxRegistrationCertificate" id="surveybusinessinfoList${surveybusinessC.count-1}taxRegistrationCertificate" style="width:100px"/>
          <input name="surveybusinessinfoList[${surveybusinessC.count-1}].seq" type="hidden"  value="${surveybusinessC.count}"/> 
      </td>
       <td width="60px" clospan='2'><a iconCls="icon-remove" class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a></td>
    </tr>
    </c:forEach>
     
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
      <td><input  value="${houseServeyVo.depositList[0].depositOrganization }"   name="depositList[0].depositOrganization" id="depositOrganization0"></input></td>
      <td><input  value="${houseServeyVo.depositList[0].depositOrganizationDetail }"   class="easyui-validatebox"  name="depositList[0].depositOrganizationDetail" type="text" id="businessNumber66" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.depositList[0].address }"   class="easyui-validatebox"  name="depositList[0].address" type="text" id="depositList0address" size="20" maxlength="30"/></td>
      <td><input  value="${houseServeyVo.depositList[0].telephone }"   class="easyui-validatebox"  name="depositList[0].telephone" type="text" id="depositList0telephone" size="20"  validType="phoneNumber" maxlength="11"/> </td>
      <td><input  value="${houseServeyVo.depositList[0].balance }"    class="easyui-numberbox"   name="depositList[0].balance" type="text" onkeypress="if(event.which==45){return false;}" id="balance0" onblur="addBlance();" size="16"   precision="2" maxlength="7"/></td>
      <td><input  value="${houseServeyVo.depositList[0].proveDocument }"    name="depositList[0].proveDocument" id="proveDocument0">
      </input> <input   name="depositList[0].seq" type="hidden" value="1">  </input>
      </td>
    </tr>
    <tr>
      <td><input  value="${houseServeyVo.depositList[1].depositOrganization }"  name="depositList[1].depositOrganization" id="depositOrganization1">
      </input></td>
      <td><input  value="${houseServeyVo.depositList[1].depositOrganizationDetail }"  name="depositList[1].depositOrganizationDetail" type="text" id="businessNumber70" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.depositList[1].address }"  name="depositList[1].address" type="text" id="depositList1address" size="20" maxlength="30"/></td>
      <td><input  value="${houseServeyVo.depositList[1].telephone }"  name="depositList[1].telephone" type="text" id="depositList1telephone" size="20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
      <td><input  value="${houseServeyVo.depositList[1].balance }"  name="depositList[1].balance" type="text" id="balance1" onblur="addBlance();" size="16" onkeypress="if(event.which==45){return false;}" class="easyui-numberbox"   precision="2" maxlength="7"/></td>
      <td><input  value="${houseServeyVo.depositList[1].proveDocument }"  name="depositList[1].proveDocument" id="proveDocument1">
      </input> <input   name="depositList[1].seq" type="hidden" value="2">  </input>
      </td>
    </tr>
    <tr>
      <td><input  value="${houseServeyVo.depositList[2].depositOrganization }"  name="depositList[2].depositOrganization" id="depositOrganization2">
      </input></td>
      <td><input  value="${houseServeyVo.depositList[2].depositOrganizationDetail }"  name="depositList[2].depositOrganizationDetail" type="text" id="depositList2depositOrganizationDetail" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.depositList[2].address }"  name="depositList[2].address" type="text" id="depositList2address" size="20" maxlength="30"/></td>
      <td><input  value="${houseServeyVo.depositList[2].telephone }"  name="depositList[2].telephone" type="text" id="depositList2telephone"  size="20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
      <td><input  value="${houseServeyVo.depositList[2].balance }"  name="depositList[2].balance" type="text" id="balance2" onblur="addBlance();" size="16" onkeypress="if(event.which==45){return false;}" class="easyui-numberbox"   precision="2" maxlength="7"/></td>
      <td><input  value="${houseServeyVo.depositList[2].proveDocument }"  name="depositList[2].proveDocument" id="proveDocument2">
      </input><input   name="depositList[2].seq" type="hidden" value="3">  </input>
      </td>
    </tr>
    <tr>
      <td colspan="4" align="right">汇总(元)：</td>
      <td><input   type="text" id="totalBlance" size="16" readonly="readonly"/></td>
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
      <td><input  value="${houseServeyVo.applicationList[0].borrowUse }"  required="true"  name="applicationList[0].borrowUse" id="borrowUse0"></input></td>
      <td><input  value="${houseServeyVo.applicationList[0].borrowUseDetail }"  required="true" class="easyui-validatebox"   name="applicationList[0].borrowUseDetail" type="text" id="businessNumber891" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.applicationList[0].quantity }"  required="true" class="easyui-numberbox"   name="applicationList[0].quantity"  onkeypress="if(event.which==45){return false;}" type="text" id="quantity0" onblur="addAmount();" size="20"   maxlength="7"/></td>
      <td><input  value="${houseServeyVo.applicationList[0].unitPrice }"  required="true" class="easyui-numberbox"     name="applicationList[0].unitPrice" type="text" id="unitPrice0" onblur="addAmount();" size="20" onkeypress="if(event.which==45){return false;}"  precision="2" maxlength="7"/></td>
      <td><input  value="${houseServeyVo.applicationList[0].totalAmount }"    name="applicationList[0].totalAmount" type="text" id="totalAmount0" size="20" readonly="readonly" /></td>
      <td><input  value="${houseServeyVo.applicationList[0].proveDocument }"  required="true"   name="applicationList[0].proveDocument" id="proveDocumentApplication0">
      </input><input    name="applicationList[0].seq" type="hidden" value="1">  </input>
      </td>
    </tr>
    <tr>
      <td><input  value="${houseServeyVo.applicationList[1].borrowUse }"  name="applicationList[1].borrowUse" id="borrowUse1">
      </input></td>
      <td><input  value="${houseServeyVo.applicationList[1].borrowUseDetail }"  name="applicationList[1].borrowUseDetail" type="text" id="businessNumber89rr" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.applicationList[1].quantity }"  name="applicationList[1].quantity" type="text" id="quantity1" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="7"/></td>
      <td> 
        <input  value="${houseServeyVo.applicationList[1].unitPrice }"  name="applicationList[1].unitPrice" type="text" id="unitPrice1" onblur="addAmount();" size="20" class="easyui-numberbox"   onkeypress="if(event.which==45){return false;}" precision="2" maxlength="7"/>
       </td>
      <td><input  value="${houseServeyVo.applicationList[1].totalAmount }"  name="applicationList[1].totalAmount" type="text" id="totalAmount1" size="20" readonly="readonly"/></td>
      <td><input  value="${houseServeyVo.applicationList[1].proveDocument }"  name="applicationList[1].proveDocument" id="proveDocumentApplication1">
      </input> <input    name="applicationList[1].seq" type="hidden" value="2">  </input>
      </td>
    </tr> 
    <tr>
      <td><input  value="${houseServeyVo.applicationList[2].borrowUse }"  name="applicationList[2].borrowUse" id="borrowUse2">
      </input></td>
      <td><input  value="${houseServeyVo.applicationList[2].borrowUseDetail }"  name="applicationList[2].borrowUseDetail" type="text" id="busqNumber89sdgw34" size="20" maxlength="20"/></td>
      <td><input  value="${houseServeyVo.applicationList[2].quantity }"  name="applicationList[2].quantity" type="text" id="quantity2" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" maxlength="7"/></td>
      <td> 
        <input  value="${houseServeyVo.applicationList[2].unitPrice }"  name="applicationList[2].unitPrice" type="text" id="unitPrice2" onblur="addAmount();" size="20" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}"   precision="2" maxlength="7"/>
       </td>
      <td><input  value="${houseServeyVo.applicationList[2].totalAmount }"  name="applicationList[2].totalAmount" type="text" id="totalAmount2" size="20" readonly="readonly" /></td>
      <td><input  value="${houseServeyVo.applicationList[2].proveDocument }"  name="applicationList[2].proveDocument" id="proveDocumentApplication2">
      </input>	<input    name="applicationList[2].seq" type="hidden" value="3">  </input>
      </td>
    </tr>
     <tr>
      <td colspan="4" align="right">汇总(元)：  <td><input   type="text" id="TTAmount" size="20" readonly="readonly" /></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  		<table>
  		<tr>
  		<td>
 			<input  value="${houseServeyVo.familymemberList[0].familyMemberId }"  name="familymemberList[0].familyMemberId" type="hidden" />
			<input  value="${houseServeyVo.familymemberList[1].familyMemberId }"  name="familymemberList[1].familyMemberId" type="hidden" />
			<input  value="${houseServeyVo.familymemberList[2].familyMemberId }"  name="familymemberList[2].familyMemberId" type="hidden" />
			<input  value="${houseServeyVo.familymemberList[3].familyMemberId }"  name="familymemberList[3].familyMemberId" type="hidden" />
			<input  value="${houseServeyVo.familymemberList[4].familyMemberId }"  name="familymemberList[4].familyMemberId" type="hidden" />
			<input  value="${houseServeyVo.jobInfoList[0].jobInfoId }"  name="jobInfoList[0].jobInfoId" type="hidden" />
			<input  value="${houseServeyVo.jobInfoList[1].jobInfoId }"  name="jobInfoList[1].jobInfoId" type="hidden" />
			<input  value="${houseServeyVo.surveybusinessinfoList[0].surveyBusinessInfoId }"  name="surveybusinessinfoList[0].surveyBusinessInfoId"
				type="hidden" />
			<input  value="${houseServeyVo.surveybusinessinfoList[1].surveyBusinessInfoId }"  name="surveybusinessinfoList[1].surveyBusinessInfoId"
				type="hidden" />
			<input  value="${houseServeyVo.surveybusinessinfoList[2].surveyBusinessInfoId }"  name="surveybusinessinfoList[2].surveyBusinessInfoId"
				type="hidden" />
			<input  value="${houseServeyVo.depositList[0].depositid }"  name="depositList[0].depositid" type="hidden" />
			<input  value="${houseServeyVo.depositList[1].depositid }"  name="depositList[1].depositid" type="hidden" />
			<input  value="${houseServeyVo.depositList[2].depositid }"  name="depositList[2].depositid" type="hidden" />
			<input  value="${houseServeyVo.applicationList[0].applicationid }"  name="applicationList[0].applicationid" type="hidden" />
			<input  value="${houseServeyVo.applicationList[1].applicationid }"  name="applicationList[1].applicationid" type="hidden" />
			<input  value="${houseServeyVo.applicationList[2].applicationid }"  name="applicationList[2].applicationid" type="hidden" />
			</td>
			</tr>
		</table>
</form>
</div>
<div id="windowBorrowUse">
	<table id="tableUse"></table>
</div>
</body>
    <script type="text/javascript">
        //处理键盘事件
//        function doKey(e){
//            console.info("---------------------key------------");
//            var ev = e || window.event;//获取event对象
//            var obj = ev.target || ev.srcElement;//获取事件源
//            var t = obj.type || obj.getAttribute('type');//获取事件源类型
//            if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){
//                ev.preventDefault();
//                return false;
//            }
//        }
//        //禁止后退键 作用于Firefox、Opera
//        document.onkeypress=doKey;
//        //禁止后退键  作用于IE、Chrome
//        document.onkeydown=doKey;

        $(document).on("keydown", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });
        $(document).on("keypress", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });

    </script>
</html>
