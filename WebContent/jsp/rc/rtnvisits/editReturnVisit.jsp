<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.creditease.rc.util.PropertiesUtil" %>
<%@ page import="com.creditease.core.security.SpringSecurityUtils" %>
<%@ page import="com.creditease.rc.util.DESPlus" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    String userId = SpringSecurityUtils.getCurrentUser().getUserId();
    DESPlus desPlus = new DESPlus();
    String clientId = desPlus.encrypt(new Date().toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增回访页面</title>
        <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
        <script type="text/javascript">
        
        	var serverName = "<%=path%>";
        	var industryCategoryAll;
        	var cashIncomeFamilyTotal;
			var highDangerCustomer;
        	var flag1 = false;
        	var flag2 = false;
        	var flag3 = false;
            //附件上传
            var cmUrl = "<%=cmUrl%>";
            var clientid = "<%=clientId%>";
            var userId = "<%=userId%>";

        	$(function(){
        		var dic = new DataDictionary();
        		dic.addDic("visitWay_Y","visit_way");
        		dic.addDic("newDebt_Y","new_debt");
        		dic.addDic("loanUse_Y","yesOrNo");
        		dic.addDic("familyIncome_Y","yesOrNo");
        		dic.addDic("income_Y","yesOrNo");
        		dic.addDic("spending_Y","yesOrNo");
				dic.addDic("isPurposeConsistency_Y","yesOrNo");
        		dic.addDic("contactWay_Y","yesOrNo");
				dic.addDic("attitudeForRepayment_Y","att_for_repayment");
				dic.addDic("attitudeForCutomermanager_Y","att_for_cutomermanager");
				dic.addDic("highDangered_Y","yesOrNo");
        		dic.dicAjax();
        		$("#higDangerCustomer").hide();
        		flag1 = false;
        		flag2 = false;
        		flag3 = false;
        		$("#customerReturnVisitForm").form("validate");
        		flag1 = true;
        		flag2 = true;
        		flag3 = true;
        		var creditapplicationid = "${customerReturnVisitVo.businessNumber }";
        		if(creditapplicationid ==undefined ||creditapplicationid == null || creditapplicationid == ""){
        			$("#commitButton").linkbutton("disable");
        			alert("还没有生成回访计划");
        		};
        		
        		ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"industryCategoryTotal",q:""},function(result){
        			industryCategoryAll = result;
        		});
        		ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"cashIncomeFamilyTotal",q:""},function(result){
        			cashIncomeFamilyTotal = result;
        		});
				ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"highDangerCustomer",q:""},function(result){
					highDangerCustomer = result;
				});
        		//新借款用途：
        		$("#loanUse").combobox({width:"119",
        			onSelect:function(date){
        			if(date.codeKey =='0'){
        				flag1 = true;
	        			$("#newLoanUse").validatebox({required:true});
	        			$("#newLoanUse").validatebox("validate");
        			}else{
        				flag1 = false;
        				$("#newLoanUse").val("");
        				$("#industryCategory").val("");
	        			$("#newLoanUse").validatebox({required:false});
	        			$("#newLoanUse").validatebox("validate");
        			}
        			
        		}});
        		//新家庭收入来源：
				$("#familyIncome").combobox({width:"119",
	        		onSelect:function(date){
					if(date.codeKey =='0'){
						flag2 = true;
	        			$("#newFamilyIncome").validatebox({required:true});
	        			$("#newFamilyIncome").validatebox("validate");
        			}else{
        				flag2 = false;
        				$("#newFamilyIncome").val("");
        				$("#newFamilyIncomeReal").val("");
	        			$("#newFamilyIncome").validatebox({required:false});
	        			$("#newFamilyIncome").validatebox("validate");
        			}			
				}});
        		//新经营收入来源：
				$("#income").combobox({width:"119",
	        		onSelect:function(date){
					if(date.codeKey =='0'){
						flag3 = true;
	        			$("#newIncome").validatebox({required:true});
	        			$("#newIncome").validatebox("validate");
        			}else{
        				flag3 = false;
        				$("#newIncome").val("");
        				$("#newIncomeReal").val("");
	        			$("#newIncome").validatebox({required:false});
	        			$("#newIncome").validatebox("validate");
        			}
				}});
        		//大项开支：
				$("#spending").combobox({width:"119",
	        		onSelect:function(date){
					if(date.codeKey =='0'){
						$("#newSpending").attr("readonly",false);
	        			$("#newSpending").validatebox({required:true});
	        			$("#newSpending").validatebox("validate");
        			}else{
        				$("#newSpending").val("");
        				$("#newSpending").attr("readonly","readonly");
	        			$("#newSpending").validatebox({required:false});
	        			$("#newSpending").validatebox("validate");
        			}
				}});
        		
        		//新联系方式：
				$("#contactWay").combobox({width:"119",
        			onSelect:function(date){
					if(date.codeKey =='0'){
						$("#newContactWay").attr("readonly",false);
	        			$("#newContactWay").validatebox({required:true});
	        			$("#newContactWay").validatebox("validate");
        			}else{
        				$("#newContactWay").val("");
        				$("#newContactWay").attr("readonly","readonly");
	        			$("#newContactWay").validatebox({required:false});
	        			$("#newContactWay").validatebox("validate");
        			}
				}});
				//债权债务金额：
				$("#newDebt").combobox({width:"119",
        			onSelect:function(date){
					if(date.codeVlue =='请选择' || date.codeVlue =='无' ){
        				$("#newDebtMoney").numberbox("setValue","");
        				$("#newDebtMoney").attr("readonly","readonly");
	        			$("#newDebtMoney").numberbox({required:false,disabled:true});
	        			$("#newDebtMoney").numberbox("validate");
        			}else{
        				$("#newDebtMoney").attr("readonly",false);
	        			$("#newDebtMoney").numberbox({required:true,disabled:false});
	        			$("#newDebtMoney").numberbox("validate");
        			}
				}});

				//借款用途是否一致：
				$("#isPurposeConsistency").combobox({width:"119",
					onSelect:function(date){
						if(date.codeKey =='1'){
							$("#reasonNotConsistencyDiv").show();
							$("#reasonNotConsistency").attr("readonly",false);
							$("#reasonNotConsistency").validatebox({required:true});
							$("#reasonNotConsistency").validatebox("validate");
						}else{
							$("#reasonNotConsistencyDiv").hide();
							$("#reasonNotConsistency").val("");
							$("#reasonNotConsistency").attr("readonly","readonly");
							$("#reasonNotConsistency").validatebox({required:false});
							$("#reasonNotConsistency").validatebox("validate");
						}
					}});
				//是否高危标记
				$("#highDangered").combobox({width:"119",
					onSelect:function(date){
						if(date.codeKey =='0'){
							console.info("---是高危标记客户--");
							selectHighDangerCustomer("higDangerCustomer","highDangerReason");
						}else{
							console.info("--不是高危标记客户---");
							$("#higDangerCustomer").val("");
							$("#highDangerReason").val("");
							$("#higDangerCustomer").hide();
						}

					}});
				
				$("#minutes").combobox({data:[{id:"00",text:"00"},{id:"15",text:"15"},{id:"30",text:"30"},{id:"45",text:"45"}],
										valueField:"id",
										textField:"text",
										height:"auto",
										editable:false,
										width:50});
				

				
				$.ajax({
					url:"<%=basePath%>PresentDateController/returnDateListSelectChange.do?creditapplicationId="+"${customerReturnVisitVo.creditapplicationId }",
					datatype:"json",
					success:function(data){
						$("#visitDate").combobox({
							data:data.list,
							width:119,
							required:true,
							editable:false,
							panelHeight:"auto",
							value:data.todayNow,
							valueField:"dateString",
							textField:"dateString",
							onSelect:function(value){
								if(data.flag){   //如果今天是还款日
									if(value.dateString == data.lastRepayDate){
										$("#repaymentDate").val($("#repaymentDateHide").val()); //给还款日期赋值
										$("#customerReturnVisitId").val($("#customerReturnVisitIdPresent").val());		//给回访id 赋值
									}else{
										$("#repaymentDate").val(data.lastRepayDate);
										$("#customerReturnVisitId").val(data.lastcustomerReturnVisitId);		//给回访id 赋值
									}
								}
							}
						});
					},
					error:function(){
						
					}
				});

                //上传文件
                var url1 = serverName + "/creditapplication2Approval/getDate.do";
                ajaxSubmit(url1, function (result) {
                    if (result) {
                        var signTime = result[0];
                        var signIp = result[1];
                        var href = cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=" + userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
                        $('#openCM')[0].src = href;
                    } else {
                        $.messager.alert("提示信息", "服务异常，请稍后重试");
                    }
                })
        	});
            function editUpdateSubmit() {
                flag1 = false;
                flag2 = false;
                flag3 = false;
                if ($("#customerReturnVisitForm").form("validate")) {
                    //检查是否上传附件
                    var clientId = clientid;
                    var url = serverName + "/businessStatusChangeController/checkAttachment.do";
                    ajaxSubmit(url, "post", {clientId: clientId}, function (attachmentCount) {
                        if (!attachmentCount.count > 0) {
                            clientId = '';
                            if ($('#visitWay').combobox('getText') == '实地回访') {
                                $.messager.alert("提示", "实地回访请上传附件！");
                                return;
                            }
                        }
                        $.messager.confirm('提示', '确定保存吗？', function (r) {
                            if (r) {
                                var dataParam = 'attachmentId=' + clientId + '&' + $('#customerReturnVisitForm').serialize();
                                $("#commitButton").linkbutton("disable");
                                editUpdate();
                                ajaxSubmit(serverName + "/CustomerReturnVisitController/update.do", dataParam, function (result) {
                                    if (result.success) {
                                        $("#commitButton").linkbutton("enable");
                                        parent.$.messager.show({
                                            title: "消息",
                                            msg: "保存成功"
                                        });
                                        parent.closeTab("编辑回访");
                                    }
                                    $("#commitButton").linkbutton("enable");
                                });
                            }
                        });
                    }, false);
                } else {
                    alert("请将红色提示框填写正确");
                }
                flag1 = true;
                flag2 = true;
                flag3 = true;
            }
	        
	        
	      //显示借款用途对话框
			function showborrowUse(valueField,textField){
				if(flag1 && isDisplay($('#loanUse'))){
							 var table = document.getElementById("tableUse");
							 var pchildren = table.childNodes;
							 //清空表中的行和列
							 for(var a=pchildren.length-1; a>=0; a--){
								 table.removeChild(pchildren[a]);
							 }
							 //判断颜色的 
							 var m = 0;
						 	for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
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
						$("#windowBorrowUse").window({   
						    width:650,  
						    height:30*(Math.ceil(industryCategoryAll.length/3)), 
						    title:"借款行业用途",
						    minimizable:false,
						    maximizable:false,
						    collapsible:false,
		   					modal:true   
	   					});   
				}
			}
	      
			//借款用途行业
			function addBorrow(){
				$("#"+arguments[3]).val(arguments[1]);
				$("#"+arguments[3]).validatebox("validate");
				$("#"+arguments[2]).val(arguments[0]);
				$("#"+arguments[2]).focus();
				$("#windowBorrowUse").window("close");
			}
			
			
			
			//选择家庭收入
			function selectProject(projectName,projectCodeKey){
				if(flag2 && isDisplay($('#familyIncome'))){
							 var table = document.getElementById("tableProject");
							 var pchildren = table.childNodes;
							 //清空表中的行和列
							 for(var a=pchildren.length-1; a>=0; a--){
								 table.removeChild(pchildren[a]);
							 }
							var n = 0;
							//判断颜色的 
							 var m = 0;
							for(var k = 0 ; k < cashIncomeFamilyTotal.cashIncomeFamilyTotal.length;k++){
							 	var  index = cashIncomeFamilyTotal.cashIncomeFamilyTotal[k].codaTableId;	
							 	var  name = "cashIncomeFamilyTotal"+index;
							 	var  objectName = "cashIncomeFamilyTotal."+name;
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
											cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ cashIncomeFamilyTotal.cashIncomeFamilyTotal[k].codeVlue+"</font>";
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
													"<a href='javascript:;' onclick='javascript:addProject(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+projectName+"\",\""+projectCodeKey+"\")'>"+obj.codeValue+"</a>";
											}
										tr.appendChild (cell); 
									}
								table.appendChild (tr); 
								}
						 	}
							$("#windowProject").window({
							    width:550,  
							    height:25*(Math.ceil(cashIncomeFamilyTotal.length/3)), 
							    title:"新家庭收入来源",
							    minimizable:false,
							    maximizable:false,
							    collapsible:false,
							    closed:false,
			    				modal:true   
			    			});  
				}

			}
			
			function addProject(){
				$("#"+arguments[2]).val(arguments[1]);
				$("#"+arguments[2]).validatebox("validate");
				$("#"+arguments[3]).val(arguments[0]);
				$("#"+arguments[3]).focus();
				$("#windowProject").window("close");
			}


			
			
			//选择收入
			function selectNewIncome(projectName,projectCodeKey){
				if(flag3 && isDisplay($('#income'))){
					
							 var table = document.getElementById("tableNewIncome");
							 var pchildren = table.childNodes;
							 //清空表中的行和列
							 for(var a=pchildren.length-1; a>=0; a--){
								 table.removeChild(pchildren[a]);
							 }
							var n = 0;
							//判断颜色的 
							 var m = 0;
							for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
//								不显示 家庭消费
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
														"<a href='javascript:;' onclick='javascript:addNewIncome(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+projectName+"\",\""+projectCodeKey+"\")'>"+obj.codeValue+"</a>";
												}
											tr.appendChild (cell); 
										}
									table.appendChild (tr); 
									}
					 			}
						 	}
							$("#windowNewIncome").window({
							    width:550,  
							    height:25*(Math.ceil(industryCategoryAll.length/3)), 
							    title:"新经营收入来源",
							    minimizable:false,
							    maximizable:false,
							    collapsible:false,
							    closed:false,
			    				modal:true   
			    			});  
				}
			}
			
			function addNewIncome(){
				$("#"+arguments[2]).val(arguments[1]);
				$("#"+arguments[2]).validatebox("validate");
				$("#"+arguments[3]).val(arguments[0]);
				$("#"+arguments[3]).focus();
				$("#windowNewIncome").window("close");
			}

			//选择高危客户标记原因
			function selectHighDangerCustomer(higDangerCustomer,highDangerReason){
				console.info(higDangerCustomer+"-----"+highDangerReason);

				if(isDisplay($('#highDangered'))){
					console.info("ss");
					var table = document.getElementById("tableHighDangerCustomer");
					var pchildren = table.childNodes;
					//清空表中的行和列
					for(var a=pchildren.length-1; a>=0; a--){
						table.removeChild(pchildren[a]);
					}
					var n = 0;
					//判断颜色的
					var m = 0;
					for(var k = 0 ; k < highDangerCustomer.highDangerCustomer.length;k++){
						var  index = highDangerCustomer.highDangerCustomer[k].codaTableId;
						var  name = "highDangerCustomer"+index;
						var  objectName = "highDangerCustomer."+name;
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
								cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ highDangerCustomer.highDangerCustomer[k].codeVlue+"</font>";
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
											"<a href='javascript:;' onclick='javascript:addHighDangerCustomer(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+higDangerCustomer+"\",\""+highDangerReason+"\")'>"+obj.codeValue+"</a>";
								}
								tr.appendChild (cell);
							}
							table.appendChild (tr);
						}
					}
					$("#windowHighDangerCustomer").window({
						width:550,
						height:25*(Math.ceil(cashIncomeFamilyTotal.length/3)),
						title:"高危标记客户原因选择",
						minimizable:false,
						maximizable:false,
						collapsible:false,
						closed:false,
						modal:true,
						closable: false
					});
				}

			}

			function addHighDangerCustomer(){
				console.info("args[2]----"+arguments[2]+"----arg[3]---"+arguments[3]);
				$("#"+arguments[2]).val(arguments[1]);
				//$("#"+arguments[2]).bind("click",selectHighDangerCustomer('higDangerCustomer','highDangerReason'));
				//$("#"+arguments[2]).validatebox("validate");
				$("#"+arguments[2]).show();
				$("#"+arguments[3]).val(arguments[0]);
				$("#"+arguments[3]).focus();
				$("#windowHighDangerCustomer").window("close");
			}
			
			
			function editUpdate(){
				$("#visitDurationTimes").val($("#hours").val()+"-"+$("#minutes").combobox("getValue"));
			}

            /*验证当选择为否时，控制是否可显*/
            function isDisplay($obj) {
                return $obj.combobox('getText') == '否' ? false : true;
            }

        </script>
  </head>
  
  <body class="easyui-layout">
    	<div region="center" align="center">
    		<div align="center">
		    	<form id="customerReturnVisitForm"> 
		    		<h2 align="center">申请编号： ${customerReturnVisitVo.businessNumber }</h2>
		    		<input type="hidden" name="creditapplicationId" value="${customerReturnVisitVo.creditapplicationId }"/><!-- 申请id -->
		    		<input id="repaymentDateHide"  type="hidden"   value="<fmt:formatDate value='${customerReturnVisitVo.repaymentDate }' pattern='yyyy-MM-dd'/>"/>
		    		<input id="customerReturnVisitId" name="customerReturnVisitId"  type="hidden" value="${customerReturnVisitVo.customerReturnVisitId }"/>
		    		<input id="customerReturnVisitIdPresent"  type="hidden" value="${customerReturnVisitVo.customerReturnVisitId }"/>
		    		<input type="hidden" name=""/><!-- 客户回访id -->
		    		<table width="92%" class="tabfrom" align="center">
		    			<tr>
		    				<th width="20%">回访日期：</th><td width="30%"><input id="visitDate" name="visitDate" class="easyui-combobox"/></td>
		    				<th width="20%">还款日期：</th><td width="30%"><input id="repaymentDate" name="repaymentDate" readonly="readonly" value="<fmt:formatDate value='${customerReturnVisitVo.repaymentDate }' pattern='yyyy-MM-dd'/>"/></td>
		    			</tr>
		    			<tr>
		    				<th width="20%">回访方式：</th><td width="30%"><input name="visitWay" id="visitWay"/></td>
		    				<th width="20%">回访时长：</th><td width="30%">
		    				<input id="hours" name="visitDurationHours" max="99" precision="0" class="easyui-numberbox" required="true" style="width:50">小时
		    				<input id="minutes" name="visitDurationMinutes" class="easyui-combobox" required="true" >分钟
		    				<input id="visitDurationTimes" name="visitDurationTimes" type="hidden"/></td>
		    			</tr>
		    			<tr>
		    				<th width="20%">借款用途变更：</th><td width="30%"><input name="loanUse" id="loanUse"/></td>
		    				<th width="20%">新借款用途：</th><td width="30%">
		    				<input id="newLoanUse" class="easyui-validatebox" readonly="readonly" class="easyui-validatebox" onFocus="showborrowUse('industryCategory','newLoanUse');"/>
		    				<input name="newLoanUse"  id="industryCategory" type="hidden"/>
		    				<a plain="true" class="easyui-linkbutton" onclick="$('#industryCategory').val('');$('#newLoanUse').val('');$('#newLoanUse').validatebox('validate');">清除</a>
		    				</td>
		    			</tr>
		    			
		    			<tr>
		    				<th width="20%">新增家庭收入来源：</th><td width="30%"><input name="familyIncome" id="familyIncome"/></td>
		    				<th width="20%">新家庭收入来源：</th><td width="30%">
		    				<input  id="newFamilyIncome" readonly="readonly" class="easyui-validatebox" onfocus="selectProject('newFamilyIncome','newFamilyIncomeReal');"/>
		    				<input id="newFamilyIncomeReal" name="newFamilyIncome" type="hidden"/>
		    				<a plain="true" class="easyui-linkbutton" onclick="$('#newFamilyIncome').val('');$('#newFamilyIncome').validatebox('validate');$('#newFamilyIncomeReal').val('');">清除</a>
		    				</td>
		    			</tr>
		    			<tr>
		    				<th width="20%">新增经营收入来源：</th><td><input name="income" id="income"/></td>
		    				<th width="20%">新经营收入来源：</th><td><input readonly="readonly"  id="newIncome" class="easyui-validatebox" onfocus="selectNewIncome('newIncome','newIncomeReal');"/>
		    				<input id="newIncomeReal" name="newIncome" type="hidden"/>
		    				<a plain="true" class="easyui-linkbutton" onclick="$('#newIncome').val('');$('#newIncome').validatebox('validate');$('#newIncomeReal').val('');">清除</a>
		    				</td>
		    			</tr>
		    			<tr>
		    				<th width="20%">新大项开支：</th><td width="30%"><input name="spending" id="spending"/></td>
		    				<th width="20%">大项开支：</th><td width="30%"><input  maxlength="64" name="newSpending" id="newSpending" onmouseover="showInputTip('新的大项开支（指除借款用途本身外，例如：红白事、大件家私、购房/车、孩子学费等）');" onmouseout="hideInputTip();"/></td>
		    			</tr>
		    			<tr>
		    				<th width="20%">联系方式变更：</th><td width="30%"><input name="contactWay" id="contactWay"/></td>
		    				<th width="20%">新联系方式：</th><td width="30%"><input maxlength="32" name="newContactWay" id="newContactWay" /></td>
		    			</tr>
		    			
		    			<tr>
		    				<th width="20%">新增债务：</th><td width="30%"><input name="newDebt" id="newDebt"/></td>
		    				<th width="20%">债权债务金额：</th><td width="30%"><input max="90000000" precision="0"  name="newDebtMoney" id="newDebtMoney" class="easyui-numberbox" /></td>
		    			</tr>
						<tr>
							<td colspan="4">客户还款态度：<input name="attitudeForRepayment" id="attitudeForRepayment"/>
							<span style="margin-left: 30px">对待客户经理态度：<input maxlength="32" name="attitudeForCutomermanager" id="attitudeForCutomermanager" /></span>
                           <span style="margin-left: 30px">是否高危标记：<input name="highDangered" id="highDangered"/>
							   <input  id="higDangerCustomer" onfocus="selectHighDangerCustomer('higDangerCustomer','highDangerReason');" readonly="readonly"   />
							  <span> <input id="highDangerReason" name="highDangerReason" type="hidden"/></span>
							</span>	</td>
						</tr>

						<tr>
							<th width="20%">借款用途是否一致：</th>
							<td width="80%" colspan="3">
							<input name="isPurposeConsistency" id="isPurposeConsistency"/>
							<span style="margin-left: 50px;display: none" id="reasonNotConsistencyDiv">借款用途不一致原因： <input   name="reasonNotConsistency" id="reasonNotConsistency" style="width:500px"/></span>
						</tr>


		    			<tr>
		    				<th width="20%">家庭成员变化：</th><td colspan=3" width="80%"><input id="familyNumberCondition" maxlength="100" name="familyNumberCondition" size="130" onmouseover="showInputTip('家庭成员变化（例如：夫妻关系、健康状况、成员是否在本地）');" onmouseout="hideInputTip();"/></td>
		    			</tr>
		    			<tr>
		    				<th width="20%">其他影响客户还款能力或意愿：</th><td colspan=3" width="80%"><input maxlength="100" name="otherFactor" size="130" onmouseover="showInputTip('其他影响客户还款能力或意愿的情况（例如：原来的经营规模变化、自然灾害、市场价格变动、资金回笼情况等）');" onmouseout="hideInputTip();"/></td>
		    			</tr>
                        <tr>
                            <th width="10%" align="right" nowrap="nowrap"> 附件：
                            </td>
                            <td colspan=3" height="250px">
                                <iframe id="openCM" src="" style="width: 100%;height: 100%;border: 0"></iframe>
                            </td>
                        </tr>
		    		</table>
		    	</form>
	    	</div>
	    	<div id="toobar" align="center">
	    		<a class="easyui-linkbutton" id="commitButton" href="javascript:editUpdateSubmit();">确定</a>
	    	</div>
    	</div>
    	<div id="windowBorrowUse">
    		<table id="tableUse"></table>
    	</div>
    	<div id="windowProject">
    		<table id="tableProject"></table>
    	</div>
    	<div id="windowNewIncome">
    		<table id="tableNewIncome"></table>
    	</div>
		<div id="windowHighDangerCustomer">
			<table id="tableHighDangerCustomer"></table>
		</div>
  </body>
</html>
