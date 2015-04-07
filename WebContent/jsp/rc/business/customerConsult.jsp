<%@ page language="java" import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
java.util.Date currentTime = new java.util.Date();
String str_dataFormatter = formatter.format(currentTime);
String customerConsultId = request.getParameter("customerConsultId");
StringBuffer conditionsConsult=new StringBuffer("");
String separator="?";
if(CommonUtil.isNotEmpty(customerConsultId)){
	conditionsConsult.append("?customerConsultId=").append(customerConsultId).append("&history=").append("");
	separator="&";
}
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>咨询列表</title>
    <base href="<%=basePath%>">
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	  <style type="text/css">
		  input.other::-webkit-input-placeholder {
			  font-size: 5pt;
		  }

		  input.other::-moz-placeholder {
			  font-size: 8pt;
		  }

		  input.other:-ms-input-placeholder {
			  font-size: 8pt;
		  }
	  </style>
	<script type="text/javascript">

		var serverName="<%=path%>";
		var sysDate="<%=str_dataFormatter%>";
		var conditons = "";
		var flag = false;
		<%if(CommonUtil.isNotEmpty(conditionsConsult.toString())){%>
		conditons = "<%=conditionsConsult.toString()%>";
		<%} %>
		function addCustomer(){
		    $("#customerComsultDialog").dialog({
	            title:"新增客户咨询",
	            close:true,
	            modal:true,
	            draggable:false,
                onClose: function(){
                    $('#isMsg').attr('checked', false);
                    $('#myInfo').hide();
                }
	        });
			flag = false;
		    $("#customerComsultForm").form("validate");
		    flag = true;
	        $("#customerComsultDialog").dialog("open");
			$("#counter").html("100");

            /*获取当前登录人最近一次联系方式*/
            ajaxSubmit(serverName + "/CustomerConsult/getManagerNameAndPhone.do", '', function (result) {
                if (result) {
                    $('#manager_name').val(result.myName);
                    if (result.myPhone)
                        $('#manager_phone').val(result.myPhone);
                }
            })
		}

		//与该联系电话相关的信息	luohongjie
		function subCustomer(){
		    $("#submitDialog").dialog({
	            title:"与该联系电话相关的信息	",
	            close:true,
	            modal:true,
	            draggable:false
	        });
			flag = false;
		    $("#subCustomerForm").form("validate");
		    flag = true;
	       // $("#submitDialog").dialog("open");
		}


		function optionFormatter(value,rowDate){
			 var linkList = "<a href='javascript:history();'>查看详情</a> ";
			 //
			if($.trim(value) == "1"){
				linkList = linkList + "|<a href='javascript:showApplication();'>查看申请 </a>";
			}
           /* if(rowDate.count > 1 && rowDate.history == "0"){
            	if(linkList == ""){
            		linkList = linkList + "";
				}else{
					linkList = linkList + "|<a href='javascript:history();'>查看详情</a>";
				}

            }*/
            if($.trim(value) == "0" && rowDate.processResult != 3&& rowDate.history == "0"){

				<sec:authorize ifAnyGranted="${control.customerConsult_loanApply}">
						 linkList = linkList + "|<a href='javascript:addNewApplication();'>申请借款</a>| <a href='javascript:editConsult();'>编辑</a>";
				</sec:authorize>
            }
                return linkList;

        }

		function countChar(textareaName,spanName)
		{
		 if(document.getElementById(textareaName).value.length > 100){
			 $("#"+textareaName).val(document.getElementById(textareaName).value.substr(0,100));
		 }
		 document.getElementById(spanName).innerHTML = 100 - document.getElementById(textareaName).value.length;
		}

		/** wyf导出客户咨询Excel **/
		function exportCustomer(){
			var departmentNameValues=$("#departmentName").combotree("getValues");
			if(departmentNameValues!=null&&departmentNameValues!=""){
				for(var i in departmentNameValues){
					departmentNameValues[i]="'"+departmentNameValues[i]+"'";
				}
			}
			var  departmentNamestr=departmentNameValues.join(",");


			var url = "<%=basePath%>CustomerConsult/exportConsultExcel.do";
			//var departmentId=$("#departmentName").combobox("getValue");
			var departmentId=departmentNamestr;
			var consultWay=$("#consultWay").combobox("getValue");

			var history=$("#history").combobox("getValue");

			var borrowUseSearch=$("#borrowUseSearch").combobox("getValue");
			//客户标签
			var processCausesSearch=$('#processCausesSearch').combobox("getValue");

			var customerName=$("#customerName").val();

			var address=$("#address").val();
			var telphone=$("#telphone").val();

			//信息来源
			var infomationSourceSearch=$('#infomationSourceSearch').combobox("getValue");
			//客户分类
			var processResultSearch=$('#processResultSearch').combobox("getValue");
			//咨询时间开始
			var consultDateBegin = $("#consultDateBegin").datebox("getValue");
			//咨询时间结束
			var consultDateEnd = $("#consultDateEnd").datebox("getValue");
			//操作时间开始
			var operateDateBegin = $("#operateDateBegin").datebox("getValue");
			//操作时间结束
			var operateDateEnd = $("#operateDateEnd").datebox("getValue");

			var fuzzyValue=$("#fuzzyValue").val();

			window.location.href = "<%=basePath%>CustomerConsult/exportConsultExcel.do?departmentId="+departmentId+"&consultWay="+consultWay+"&history="+history+"&borrowUse="+borrowUseSearch+"&processCauses="+processCausesSearch+"&customerName="+customerName+"&address="+address+"&telphone="+telphone+"&infomationSource="+infomationSourceSearch+"&processResult="+processResultSearch+"&consultDateBegin="+consultDateBegin+"&consultDateEnd="+consultDateEnd+"&operateDateBegin="+operateDateBegin+"&operateDateEnd="+operateDateEnd+"&fuzzyValue="+fuzzyValue+"";
		}
		//修改MD5加密的电话号码为空的数据
		function updateTelphoneMd5(){
			$.ajax({url:"<%=basePath%>CustomerConsult/updateTelphoneMd5.do",
					success:function(message){
						$("#customerComsult").datagrid("load");
						$.messager.alert("消息",message.msg);
					}
			});
		}

		//显示借款用途对话框
		function showborrowUse(valueField,textField){
			if(flag){
				/*ajaxSubmit(serverName+"/dicRequest/getSpecifiedDic.do",{section:"industryCategory",q:""},function(result){

						 var opanel = document.getElementById("tableWindow");
						 $("#tableWindow").html = "";

						 	for(var k = 0 ; k < 3;k++){

								 	var n = 0;
							     	var table = document.createElement("table");//创建table
							     	var length = 0;
							     	if(result.length%3 > 0){
							     		length = parseInt(result.length/3)+1;
							     	}else{
							     		length = parseInt(result.length/3);
							     	}
									for(var i = 0 ; i < length;i++){
										var tr=document.createElement("tr");
											if(i == 0){
												var cell1=document.createElement("td");
												cell1.innerHTML =
												"2";
												cell1.setAttribute("rowspan",length);
												tr.appendChild (cell1);
											}


										if(k % 2 == 0){
											tr.setAttribute('bgcolor','#DDE4EE');
										}
										for(var j = 0 ; j < 3 ;j++){
										n = n+1;
										if(result[n-1] != undefined){
											var key = result[n-1].codeKey;
											var value = result[n-1].codeVlue;
											var arrayObj = new Array(key,value);
											var obj = new Object();
											obj["codeKey"]=result[n-1].codeKey;
											obj["codeValue"]=result[n-1].codeVlue;
												var cell=document.createElement("td");
												cell.innerHTML =
												"<a href='javaScript:addBorrow(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+result[n-1].codeVlue+"</a>";
												tr.appendChild (cell);
											}
										}
									　	table.appendChild (tr);
									}
							 	opanel.appendChild(table);
						 	}
					$("#windowBorrowUse").window({
					    width:500,
					    height:30*(Math.ceil(result.length/3)),
					    title:"借款行业用途",
					    minimizable:false,
					    maximizable:false,
					    collapsible:false,
	    				modal:true
	    			});
				});*/
				ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"industryCategoryTotal",q:""},function(result){
						 var table = document.getElementById("tableUse");
						 var pchildren = table.childNodes;
						 //清空表中的行和列
						 for(var a=pchildren.length-1; a>=0; a--){
							 table.removeChild(pchildren[a]);
						 }
						 //判断颜色的
						 var m = 0;
					 	for(var k = 0 ; k < result.industryCategoryTotal.length;k++){
							 	var  index = result.industryCategoryTotal[k].codaTableId;
							 	var  name = "industryCategoryTotal"+index;
							 	var  objectName = "result."+name;
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
											cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ result.industryCategoryTotal[k].codeVlue+"</font>";
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
								table.appendChild (tr);
								}
					 	}
					$("#windowBorrowUse").window({
					    width:650,
					    height:30*(Math.ceil(result.length/3)),
					    title:"借款行业用途",
					    minimizable:false,
					    maximizable:false,
					    collapsible:false,
	   					modal:true
   					});
				});

			}
		}


		//信息来源
		function showinfomationSource(){
			if(flag){
				ajaxSubmit(serverName+"/dicRequest/getSpecifiedDic.do",{section:"infomationSource",q:""},function(result){
					 var opanel = document.getElementById("tableinfomationSource");
					 var pchildren = opanel.childNodes;
					 //清空表中的行和列
					 for(var a=pchildren.length-1; a>=0; a--){
					  opanel.removeChild(pchildren[a]);
					 }
					var n = 0;
					for(var i = 0 ; i < parseInt(result.length/3);i++){
						var tr=document.createElement("tr");
						if(i % 2 == 0){
							tr.setAttribute('bgcolor','#DDE4EE');
						}
						for(var j = 0 ; j < 3 ;j++){
						n = n+1;
							var cell=document.createElement("td");
							cell.innerHTML =
							"<a href='javaScript:addinfomationSource(\""+result[n-1].codeKey+"\",\""+result[n-1].codeVlue+"\");'>"+result[n-1].codeVlue+"</a>";
							tr.appendChild (cell);
						}
					document.getElementById("tableinfomationSource").appendChild (tr);
					document.getElementById("tableinfomationSource").appendChild (tr);
					}
					if(result.length%3 > 0){
						var tr=document.createElement("tr");
						if((parseInt(result.length/3))%2 == 0){
							tr.setAttribute('bgcolor','#DDE4EE');
						}
						for(var j = 0 ; j < result.length%3 ;j++){
							n = n+1;
							var obj = new Object();
							var cell=document.createElement ("td");
							var tag =document.createElement("tag");

							obj["codeKey"]=result[n-1].codeKey;
							obj["codeValue"]=result[n-1].codeVlue;
							tag.innerHTML = "<a href='javascript:;' onclick='javascript:addinfomationSource(\""+obj.codeKey+"\",\""+obj.codeValue+"\")'>"+result[n-1].codeVlue+"</a>";
							cell.appendChild(tag);
							tr.appendChild (cell);
						}
						document.getElementById("tableinfomationSource").appendChild (tr);
						document.getElementById("tableinfomationSource").appendChild (tr);
					}
				$("#windowinfomationSource").window({
					    width:500,
					    height:30*(Math.ceil(result.length/3)),
					    title:"信息来源",
					    minimizable:false,
					    maximizable:false,
					    collapsible:false,
	    				modal:true
	    			});
				});
			}

		}

		//bigin  ***********
		 //客户标签  新增客户咨询时
function showpprocessCauses(valueField, textField) {
	//加/*------------------------begin-----------------*/
	var reasons = $("#processCauses").val();
	console.info(reasons);
	var reasonsArray = reasons.split(",");
	/*-------------------------end-----------------*/
    ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {section: "processCausesTotal", q: ""}, function (result) {
        var opanel = document.getElementById("tableprocessCauses");
        var pchildren = opanel.childNodes;
        //清空表中的行和列
        for (var a = pchildren.length - 1; a >= 0; a--) {
            opanel.removeChild(pchildren[a]);
        }
        var m = 0;
        for (var k = 0; k < result.processCausesTotal.length; k++) {
            var index = result.processCausesTotal[k].codaTableId;
            var name = "processCausesTotal" + index;
            var objectName = "result." + name;
            var object = (eval("(" + objectName + ")"));
            var n = 0;
            var length = 0;
            if (object.length % 3 > 0) {
                length = parseInt(object.length / 3) + 1;
            } else {
                length = parseInt(object.length / 3);
            }
            for (var i = 0; i < length; i++) {
                m = m + 1;
                var tr = document.createElement("tr");
                if (i == 0) {

                    var cell1 = document.createElement("td");
                    cell1.innerHTML = "<font style='width: 150px;font-size: 13;'>" + result.processCausesTotal[k].codeVlue + "</font>";
                    cell1.setAttribute("rowspan", length);
                    cell1.setAttribute("align", "center");
                    cell1.setAttribute("width", "130");
                    cell1.setAttribute('bgcolor', '#DDE4EE');
                    tr.appendChild(cell1);
                }
                for (var j = 0; j < 3; j++) {
                    n = n + 1;
                    var cell = document.createElement("td");
                    cell.setAttribute("width", "130");
                    if (m % 2 == 0) {
                        cell.setAttribute('bgcolor', '#DDE4EE');
                    }



                    //加/*-------------------------begin-----------------*/
                    if(object[n-1] != undefined){
						$("#processCausesSection").val(object[n-1].section);
						var key = object[n-1].codeKey;
						var value = object[n-1].codeVlue;
						var arrayObj = new Array(key,value);
						var obj = new Object();
						obj["codeKey"]=object[n-1].codeKey;
						obj["codeValue"]=object[n-1].codeVlue;
							for(var l =0;l <reasonsArray.length;l++){
								if(obj.codeKey == reasonsArray[l]){
									cell.innerHTML =
										"<input type='checkbox' checked='checked' name='processCauses' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
									break;
								}else{
									cell.innerHTML =
										"<input type='checkbox' name='processCauses' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
								}
							}

						}

                    /*-------------------------end-----------------*/
                    tr.appendChild(cell);
                }
                document.getElementById("tableprocessCauses").appendChild(tr);
            }
        }
        $("#windowprocessCauses").dialog({
            width: 600,
            height: 30 * (Math.ceil(result.length / 3)),
            title: "客户标签",
            minimizable: false,
            maximizable: false,
            collapsible: false,
            modal: true,
            buttons:[{
                id:"CustomerTagButton",
                text:'确定',
                iconCls:'icon-ok',
                handler:function(){
                    addCustomerTag();
                }
            }
            ]
        });



    });
}
		//end **********


		//bigin  ***********
		 //客户标签   编辑客户咨询时
function pprocessCauses(valueField, textField) {
	//加/*------------------------begin-----------------*/
	var reasons = $("#processCausesEdit").val();
	console.info(reasons);
	var reasonsArray = reasons.split(",");
	/*-------------------------end-----------------*/
    ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {section: "processCausesTotal", q: ""}, function (result) {
        var opanel = document.getElementById("tableprocessCauses");
        var pchildren = opanel.childNodes;
        //清空表中的行和列
        for (var a = pchildren.length - 1; a >= 0; a--) {
            opanel.removeChild(pchildren[a]);
        }
        var m = 0;
        for (var k = 0; k < result.processCausesTotal.length; k++) {
            var index = result.processCausesTotal[k].codaTableId;
            var name = "processCausesTotal" + index;
            var objectName = "result." + name;
            var object = (eval("(" + objectName + ")"));
            var n = 0;
            var length = 0;
            if (object.length % 3 > 0) {
                length = parseInt(object.length / 3) + 1;
            } else {
                length = parseInt(object.length / 3);
            }
            for (var i = 0; i < length; i++) {
                m = m + 1;
                var tr = document.createElement("tr");
                if (i == 0) {

                    var cell1 = document.createElement("td");
                    cell1.innerHTML = "<font style='width: 150px;font-size: 13;'>" + result.processCausesTotal[k].codeVlue + "</font>";
                    cell1.setAttribute("rowspan", length);
                    cell1.setAttribute("align", "center");
                    cell1.setAttribute("width", "130");
                    cell1.setAttribute('bgcolor', '#DDE4EE');
                    tr.appendChild(cell1);
                }
                for (var j = 0; j < 3; j++) {
                    n = n + 1;
                    var cell = document.createElement("td");
                    cell.setAttribute("width", "130");
                    if (m % 2 == 0) {
                        cell.setAttribute('bgcolor', '#DDE4EE');
                    }



                    //加/*-------------------------begin-----------------*/
                    if(object[n-1] != undefined){
						$("#processCausesSection").val(object[n-1].section);
						var key = object[n-1].codeKey;
						var value = object[n-1].codeVlue;
						var arrayObj = new Array(key,value);
						var obj = new Object();
						obj["codeKey"]=object[n-1].codeKey;
						obj["codeValue"]=object[n-1].codeVlue;
							for(var l =0;l <reasonsArray.length;l++){
								if(obj.codeKey == reasonsArray[l]){
									cell.innerHTML =
										"<input type='checkbox' checked='checked' name='processCauses' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
									break;
								}else{
									cell.innerHTML =
										"<input type='checkbox' name='processCauses' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
								}
							}

						}

                    /*-------------------------end-----------------*/
                    tr.appendChild(cell);
                }
                document.getElementById("tableprocessCauses").appendChild(tr);
            }
        }
        $("#windowprocessCauses").dialog({
            width: 600,
            height: 30 * (Math.ceil(result.length / 3)),
            title: "客户标签",
            minimizable: false,
            maximizable: false,
            collapsible: false,
            modal: true,
            buttons:[{
                id:"CustomerTagButton",
                text:'确定',
                iconCls:'icon-ok',
                handler:function(){
                    addCustomerTagEdit();
                }
            }
            ]
        });



    });
}
		//end **********



		//客户咨询编辑    点击保存
		function addCustomerTagEdit(){
    var datas = $("[name='processCauses']");
    var customerTagKey= "";
    var customerTagValue= "";
    for(var i =0;i<datas.length;i++){
        if(datas[i].checked == true){
            customerTagKey = customerTagKey +","+datas[i].value;
            customerTagValue = customerTagValue+ "," + datas[i].nextSibling.nodeValue;
        }
    }
    if(customerTagKey !=""){
        customerTagKey = customerTagKey.substr(1,customerTagKey.length);
        customerTagValue = customerTagValue.substr(1,customerTagValue.length);
        //判断选择了多少个复选框
        if(customerTagKey.split(',').length>5){
        	$.messager.alert("提示","选择的项不能超过5个");
        	return ;
        }
    }
    $("#processCausesDetailEdit").val(customerTagValue);
    $("#processCausesEdit").val(customerTagKey);
    $("#processCausesEdit").focus();
    $("#windowprocessCauses").window("close");
}

		//新增客户咨询    点击保存
		function addCustomerTag(){
    var datas = $("[name='processCauses']");
    var customerTagKey= "";
    var customerTagValue= "";
    for(var i =0;i<datas.length;i++){
        if(datas[i].checked == true){
            customerTagKey = customerTagKey +","+datas[i].value;
            customerTagValue = customerTagValue+ "," + datas[i].nextSibling.nodeValue;
        }
    }
    if(customerTagKey !=""){
        customerTagKey = customerTagKey.substr(1,customerTagKey.length);
        customerTagValue = customerTagValue.substr(1,customerTagValue.length);
       	//判断选择了多少个复选框
        if(customerTagKey.split(',').length>5){
        	$.messager.alert("提示","选择的项不能超过5个");
        	return ;
        }
    }
    $("#processCausesDetailAdd").val(customerTagValue);
    $("#processCauses").val(customerTagKey);
    $("#processCauses").focus();
    $("#windowprocessCauses").window("close");
}

		//客户标签
		function showpprocessCauses1(valueField,textField){
			if(flag){
				/*ajaxSubmit(serverName+"/dicRequest/getSpecifiedDic.do",{section:"processCauses",q:""},function(result){
					 var opanel = document.getElementById("tableprocessCauses");
					 var pchildren = opanel.childNodes;
					 //清空表中的行和列
					 for(var a=pchildren.length-1; a>=0; a--){
					  opanel.removeChild(pchildren[a]);
					 }
					var n = 0;
					for(var i = 0 ; i < parseInt(result.length/3);i++){
						　var tr=document.createElement("tr");
						if(i % 2 == 0){
							tr.setAttribute('bgcolor','#DDE4EE');
						}
						for(var j = 0 ; j < 3 ;j++){
						n = n+1;
							var cell=document.createElement("td");
							cell.innerHTML =
							"<a href='javaScript:addprocessCauses(\""+result[n-1].codeKey+"\",\""+result[n-1].codeVlue+"\",\""+valueField+"\",\""+textField+"\");'>"+result[n-1].codeVlue+"</a>";
							tr.appendChild (cell);
						}
					　document.getElementById("tableprocessCauses").appendChild (tr);
					}
					if(result.length%3 > 0){
						var tr=document.createElement("tr");
						if((parseInt(result.length/3))%2 == 0){
							tr.setAttribute('bgcolor','#DDE4EE');
						}
						for(var j = 0 ; j < result.length%3 ;j++){
							n = n+1;
							var obj = new Object();
							var cell=document.createElement ("td");
							var tag =document.createElement("tag");

							obj["codeKey"]=result[n-1].codeKey;
							obj["codeValue"]=result[n-1].codeVlue;
							tag.innerHTML = "<a href='javascript:;' onclick='javascript:addprocessCauses(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\")'>"+result[n-1].codeVlue+"</a>";
							cell.appendChild(tag);
							tr.appendChild (cell);
						}
						　document.getElementById("tableprocessCauses").appendChild (tr);
					}
				$("#windowprocessCauses").window({
					    width:500,
					    height:30*(Math.ceil(result.length/3)),
					    title:"客户标签",
					    minimizable:false,
					    maximizable:false,
					    collapsible:false,
	    				modal:true
	    			});
				});*/

				ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"processCausesTotal",q:""},function(result){
					 var opanel = document.getElementById("tableprocessCauses");
					 var pchildren = opanel.childNodes;
					 //清空表中的行和列
					 for(var a=pchildren.length-1; a>=0; a--){
					  opanel.removeChild(pchildren[a]);
					 }
					 var m = 0;
					 for(var k = 0 ; k < result.processCausesTotal.length;k++){
					 	var  index = result.processCausesTotal[k].codaTableId;
					 	var  name = "processCausesTotal"+index;
					 	var  objectName = "result."+name;
					 	var object = (eval("("+objectName+")"));
						var n = 0;
						var length = 0;
				     	if(object.length%3 > 0){
				     		length = parseInt(object.length/3)+1;
				     	}else{
				     		length = parseInt(object.length/3);
				     	}
						for(var i = 0 ; i < length;i++){
							m = m +1;
							var tr=document.createElement("tr");
							var tr=document.createElement("tr");
							if(i == 0){

								var cell1=document.createElement("td");
								cell1.innerHTML = "<font style='width: 150px;font-size: 13;'>"+ result.processCausesTotal[k].codeVlue+"</font>";
								cell1.setAttribute("rowspan",length);
								cell1.setAttribute("align","center");
								cell1.setAttribute("width","120");
								cell1.setAttribute('bgcolor','#DDE4EE');
								tr.appendChild (cell1);
							}
							for(var j = 0 ; j < 3 ;j++){
								n = n+1;
									var cell=document.createElement("td");
									cell.setAttribute("width","110");
									if(m%2 == 0){
										cell.setAttribute('bgcolor','#DDE4EE');
									}
									if(object[n-1] != undefined){
										cell.innerHTML =
										"<a href='javaScript:addprocessCauses(\""+object[n-1].codeKey+"\",\""+object[n-1].codeVlue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
									}
									tr.appendChild (cell);
							}
						document.getElementById("tableprocessCauses").appendChild (tr);
						document.getElementById("tableprocessCauses").appendChild (tr);
						}
					 }
				$("#windowprocessCauses").window({
					    width:550,
					    height:30*(Math.ceil(result.length/3)),
					    title:"客户标签",
					    minimizable:false,
					    maximizable:false,
					    collapsible:false,
	    				modal:true
	    			});
				});
			}

		}

		//客户标签
		function addprocessCauses(){
			$("#"+arguments[3]).val(arguments[1]);
			$("#"+arguments[3]).validatebox("validate");
			$("#"+arguments[2]).val(arguments[0]);
			$("#"+arguments[2]).focus();
			$("#windowprocessCauses").window("close");
		}
		//信息来源
        function addinfomationSource() {
            /*大类*/
            $('#baseInfSrcKey').val(arguments[4]);
            $('#baseInfSrcValue').val(arguments[5]);
            /*其他*/
            $("#infomationSourceDetail").val(arguments[1]);
            $("#infomationSourceDetail").validatebox("validate");
            $("#infomationSourceForm").val(arguments[0]);
            $("#infomationSourceForm").focus();
            $("#windowinfomationSource").window("close");
        }
		//借款用途行业
		function addBorrow(){
			$("#"+arguments[3]).val(arguments[1]);
			$("#"+arguments[3]).validatebox("validate");
			$("#"+arguments[2]).val(arguments[0]);
			$("#"+arguments[2]).focus();
			$("#windowBorrowUse").window("close");
		}

		//编辑
		function updateForm(){

			flag = false;
			if(!($("#customerComsultFormEdit").form('validate'))){
				$.messager.alert("消息","请把表单数据填写正确！");
				flag = true;
				return;
			}else{
				ajaxSubmit(serverName+"/CustomerConsult/addUpdate.do",$("#customerComsultFormEdit").serialize(),function(result){
					if(result.success){
						$.messager.show({
							title:"消息",
							msg:result.msg
							});

						$("#telNumber").val($("#customerComsultFormEdit input[name=telphone]").val());
						$("#telNumberName").val($("#customerComsultFormEdit input[name='customerName']").val());
						//点击提交后弹窗（显示咨询数量）  luohongjie
						ajaxSubmit(serverName+"/CustomerConsult/selectCustomerConsultByTel.do","telphone="+$("#customerComsultFormEdit input[name=telphone]").val(),function(result){
							$("#submitButtonCustomer").linkbutton('enable');
									if (!(typeof (result) == "undefined")) {
										if(result.customerNumber !="0"){
											$("#num_a").html(result.customerNumber+"条咨询记录");
											$("#numtd_a").html("");
										}else{
											$("#num_a").html("");
											$("#numtd_a").html(result.customerNumber+"条咨询记录");
										}
										if(result.borrowerNum !="0"){
											$("#num_b").html(result.borrowerNum+"次借款记录");
											$("#numtd_b").html("");
											$("#numborrower").val(result.borrowerNum);
										}else{
											$("#num_b").html("");
											$("#numtd_b").html(result.borrowerNum+"次借款记录");
										}
										if(result.guantorNum != "0"){
											$("#num_c").html(result.guantorNum+"次担保记录");
											$("#numtd_c").html("");
											$("#numguantor").val(result.guantorNum);
										}else{
											$("#num_c").html("");
											$("#numtd_c").html(result.guantorNum+"次担保记录");
										}


							}
						});
						//点击提交后弹窗
						$("#submitDialog").dialog("open");
						$("#customerComsultDialogEdit").dialog("close");
						$("#customerComsultFormEdit").form("clear");
						$("#customerComsult").datagrid({sortName:'',sortOrder:''});
						$("#customerComsult").datagrid("load",{});
					}else{
						$.messager.show({
							title:"消息",
							msg:result.msg
						});

					}
				});
			}
		}

		function numFromBorrower(){
			parent.parent.addTabFun({
					src : serverName+ "/jsp/rc/rtnvisits/creditApplicationSearch.jsp?sign=0"
						+ "&telNumber="
						+ $("#telNumber").val()
						+ "&pageSize="
						+ $("#numborrower").val(),
				title : "客户业务查询"
			});
		}

		function numFromguantor(){
			parent.parent.addTabFun({
					src : serverName+"/jsp/rc/rtnvisits/creditApplicationSearch.jsp?sign=1"
						+ "&telNumber="
						+ $("#telNumber").val()
						+ "&pageSize="
						+ $("#numguantor").val(),
				title : "客户业务查询"
			});
		}

		function numFromConsult(){
			history($("#telNumber").val(),$("#telNumberName").val());
		}

        /*信息来源：增加大类、子类*/
        function showInfSrc(valueField,textField) {
            if (flag) {
                ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {
                    section: "base_info_src",
                    q: ""
                }, function (result) {
                    var table = document.getElementById("tableinfomationSource");
                    var pchildren = table.childNodes;
                    //清空表中的行和列
                    for (var a = pchildren.length - 1; a >= 0; a--) {
                        table.removeChild(pchildren[a]);
                    }
                    //判断颜色的
                    var m = 0;
                    for (var k = 0; k < result.base_info_src.length; k++) {
                        var index = result.base_info_src[k].codaTableId;
                        var name = "base_info_src" + index;
                        var objectName = "result." + name;
                        var object = (eval("(" + objectName + ")"));
                        var n = 0;
                        var length = 0;
                        if (object.length % 3 > 0) {
                            length = parseInt(object.length / 3) + 1;
                        } else {
                            length = parseInt(object.length / 3);
                        }
                        for (var i = 0; i < length; i++) {
                            m = m + 1;
                            var tr = document.createElement("tr");
                            if (k % 2 == 0) {
                                //tr.setAttribute('bgcolor','#DDE4EE');
                            }
                            if (i == 0) {
                                var cell1 = document.createElement("td");
                                cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>" + result.base_info_src[k].codeVlue + "</font>";
                                cell1.setAttribute("rowspan", length);
                                cell1.setAttribute("align", "center");
                                cell1.setAttribute("width", "150");
                                cell1.setAttribute("bgcolor", "#DDE4EE");
                                tr.appendChild(cell1);
                            }


                            for (var j = 0; j < 3; j++) {
                                var cell = document.createElement("td");
                                cell.setAttribute("width", "160");
                                n = n + 1;
                                if (m % 2 == 0) {
                                    cell.setAttribute("bgcolor", "#DDE4EE");
                                }
                                if (object[n - 1] != undefined) {
                                    var key = object[n - 1].codeKey;
                                    var value = object[n - 1].codeVlue;
                                    var arrayObj = new Array(key, value);
                                    var obj = new Object();
                                    obj["codeKey"] = object[n - 1].codeKey;
                                    obj["codeValue"] = object[n - 1].codeVlue;

									var otherChannel = $('#baseInfSrcKey').val() && $('#baseInfSrcKey').val() == 4 ? $('#infomationSourceDetail').val() : '';

									cell.innerHTML = ' ' == obj.codeValue ?
											'<input type="text" ' +
												'name="write_by_self" ' +
												'id="write_by_self" ' +
												'style="width: 100%; border: 1px #dde4ee;" ' +
												'placeholder="您可以在这里输入其他来源" ' +
												'class="other" onblur="post_func(this)" ' +
												'basekey=\"' + result.base_info_src[k].codeKey + '\" ' +
												'basevalue=\"' + result.base_info_src[k].codeVlue + '\" ' +
												'value=\"' + otherChannel + '\" />' :
									        "<a href='javaScript:addinfomationSource(\"" +
												obj.codeKey + "\",\"" +
												obj.codeValue + "\",\"" +
												valueField + "\",\"" +
												textField + "\", \"" +
												result.base_info_src[k].codeKey + "\",\"" +
												result.base_info_src[k].codeVlue + "\");'>" +
												object[n - 1].codeVlue + "</a>";
								}
								tr.appendChild(cell);
                            }
                            table.appendChild(tr);
                        }
                    }
                    $("#windowinfomationSource").window({
                        width: 650,
                        height: 30 * (Math.ceil(result.length / 3)),
                        title: "信息来源",
                        minimizable: false,
                        maximizable: false,
                        collapsible: false,
                        modal: true
                    });
                });
            }
        }

		function post_func(e) {
			if ($.trim(e.value)) {
				/*大类*/
				$('#baseInfSrcKey').val(e.getAttribute('basekey'));
				$('#baseInfSrcValue').val(e.getAttribute('basevalue'));
				/*其他*/
				$("#infomationSourceDetail").val(e.value);
				$("#windowinfomationSource").dialog("close");
			} else {
				e.value = '';
			}
		}
    </script>
	<script type="text/javascript" src="<%=basePath%>scripts/business/customerConsult.js"></script>
  </head>
  <body class="easyui-layout" id="cc">
    <div region="center" >

    	<div style="height: auto;">
    		<div id="customerConsultTab"  class="easyui-tabs" style="padding: 10px;">
	    		<div title="模糊搜索"  style="padding: 10px;">

				<input name="loanOfficerId" class="easyui-validatebox" type="hidden"
				value="<%=SpringSecurityUtils.getCurrentUser().getUserId()%>" />
				<input name="companyId" class="easyui-validatebox" type="hidden"
				value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentId()%>"/>
				<input name="customerConsultId" id="customerConsultId" type="hidden" value="${creditApplicationReturn.customerConsultId }"/>
	    			搜索条件：<input id="fuzzyValue" name="fuzzyValue"/>
	    			<a id="searchFuzzy" class="easyui-linkbutton" onclick="searchFuzzy();">搜索</a>
	    			<a class="easyui-linkbutton" onclick="clearFuzzy()">清空</a><font color="red">(可以输入分公司名称，客户姓名，客户住址，联系电话)</font>
	    		</div>
	    		<div title="高级搜索"  style="padding: 10px;">
	    				<table>
	    					<tr>
	    						<td> 分公司名称： </td><td><input id="departmentName" style="width: 153px;"/> </td>
	    						<td> 咨询方式： </td><td><input id="consultWay" style="width: 153px;"/> </td>
	    						<td>  历史：</td><td> <select id="history" style="width: 120px;" class="easyui-combobox">
							    				    	<option value="0">显示当前</option>
							    				    	<option value=""> 显示所有</option>
							    				    </select> </td>

							    <td>借款用途：</td>  <td><input id="borrowUseSearch" style="width: 120px;"/></td>
							    <td>客户标签：</td>  <td><input id="processCausesSearch" style="width: 120px;"/></td>

	    					</tr>
	    					<tr>
	    						<td> 客户姓名： </td><td> <input id="customerName" style="width: 150px;"/> </td>
	    						<td> 客户住址：</td><td> <input id="address" style="width: 150px;"/> </td>
	    						<td> 联系电话：</td><td><input id="telphone" style="width: 116px;" /></td>
	    						<td> 信息来源：</td>  <td><input id="infomationSourceSearch" style="width: 120px;"/></td>
	    						<td> 客户分类：</td>  <td><input id="processResultSearch" style="width: 120px;"/></td>
	    						<td></td>
	    					</tr>
                            <tr>
                                <td>首次创建时间：</td>
                                <td><input id="consultDateBegin" class="easyui-datetimebox" editable="false"
                                           style="width: 150px;"/>
                                </td>
                                <td align="center"> 至</td>
                                <td>
                                    <input id="consultDateEnd" class="easyui-datetimebox" editable="false"
                                           style="width: 150px;"/>
                                </td>
                                <td>最后操作时间：</td>
                                <td><input id="operateDateBegin" class="easyui-datetimebox" editable="false"
                                           style="width: 150px;"/>
                                </td>
                                <td align="center"> 至</td>
                                <td>
                                    <input id="operateDateEnd" class="easyui-datetimebox" editable="false"
                                           style="width: 150px;"/>
                                </td>

                                <td></td>
                                <td><a id="searchFuzzy" class="easyui-linkbutton" onclick="searchGaoji();">搜索</a>
                                    <a class="easyui-linkbutton" onclick="clearGaoji()">清空</a>
                                </td>
                            </tr>
	    				</table>
	    		</div>
	    	</div>
		</div>
		<div id="toolbar1">
		    <sec:authorize ifAnyGranted="${control.customerConsult_add}">
	          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-addOne" plain="true" onclick="addCustomer()">新增客户咨询</a>
		    </sec:authorize>
		    <sec:authorize ifAnyGranted="${control.customerConsult_excel}">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="exportCustomer()">导出Excel</a>
		    </sec:authorize>
	         <sec:authorize ifAnyGranted="${control.customerConsult_updateTelphoneMd5}">
	           <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateTelphoneMd5()">修正加密号码为空的数据</a>
	         </sec:authorize>
	          <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="showHistory()">显示历史</a>
	          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="hideHistory()">隐藏历史</a>
		--%></div>
		<div style="padding: 10px;">
	    	<table id="customerComsult" toolbar="#toolbar1" ></table>
    	</div>

    	<div id="customerComsultDialog" class="easyui-dialog" closed="true" style="width: 800px;padding: 10px;" >
    		<form id="customerComsultForm" action="/CustomerConsult/addUpdate.do" novalidate>
	    		<table >
	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">咨询时间：</td>
	    				<td><input name="consultDate" style="width: 150px;"  id="consultDateForm"/>  </td>
	    				<td width="150"></td>
	    				<td align="right" nowrap="nowrap">咨询方式：</td>
	    				<td><input name="consultWay" style="width: 150px;" id="consultWayForm" required="true"/>
	    					<input name="consultWayDetail" style="width: 150px;" id="consultWayDetail" required="true" type="hidden"/>
	    				</td>
	    			</tr>

	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">客户姓名：</td>
	    				<td><input name="customerName" style="height:16px;border:1px solid #A4BED4 ;width: 147px;" onkeydown="if(event.keyCode==32){return false;}" class="easyui-validatebox" required="true" validType="length[0,10]" maxlength="10"/> </td>
	    				<td width="150"></td>
	    				<td align="right" nowrap="nowrap">联系电话：</td>
	    				<td><input id="customer_phone" name="telphone" style="height:16px;border:1px solid #A4BED4 ;width: 147px;" class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage
	    				="输入11位手机号或加区号的固话号码" maxlength="11"/> </td>
	    			</tr>

	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">性别：</td>
	    				<td>
	    					<input name="gender" id="gender" style="width: 150px;" required="false"/>
	    					<input name="genderDetail" id="genderDetail" style="width: 147px;" required="false" type="hidden"/>
	    				</td>
	    				<td width="150"></td>
	    				<td align="right" nowrap="nowrap">信息来源：</td>
	    				<td><input name="infomationSource" style="width: 150px;" required="true"
						 id="infomationSourceForm" type="hidden"/>

                            <input type="hidden" id="baseInfSrcKey" name="baseInfSrcKey" />
                            <input type="hidden" id="baseInfSrcValue" name="baseInfSrcValue"/>

						 <input name="infomationSourceDetail" style="width: 147px;height:16px;border:1px solid #A4BED4;"
						 class="easyui-validatebox" required="true" editable="false"
						  onKeydown="return false;" onKeyup="return false;" readonly="readonly"
						 id="infomationSourceDetail"
                         <%--原始弹窗显示：onFocus="showinfomationSource();"/>--%>
                         onFocus="showInfSrc('infomationSource', 'infomationSourceDetail');"/>
	    				</td>

	    			</tr>
	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">借款用途：</td>
	    				<td><input style="width: 150px;" name="borrowUse" required="true"  id="borrowUseForm"/>
	    					<input style="width: 150px;" name="borrowUseDetail" required="true"  id="borrowUseDetail" type="hidden"/>
	    				</td>
	    				<td></td>
	    				<td align="right" nowrap="nowrap">借款用途行业：</td>
	    				<td><input name="industryCategory" style="width: 150px;"
							id="industryCategory"  required="true" type="hidden"/>
							<input name="industryCategoryDetail"  class="easyui-validatebox" style="width: 147px;height:16px;border:1px solid #A4BED4" editable="false"  onKeydown="return false;" onKeyup="return false;" readonly="readonly"
							id="industryCategoryDetail" required="true" onFocus="showborrowUse('industryCategory','industryCategoryDetail');"/>
	    				</td>

	    			</tr>
	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">咨询金额：</td>
	    				<td><input name="borrowAmount" style="width: 147px;" class="easyui-numberbox"
	    				 precision="2" invalidMessage
	    				="请输入数字!" maxlength="7"/>(元)</td>

	    				<td width="150"></td>
	    				<td align="right" nowrap="nowrap">客户住址：</td>
	    				<td><input name="address" style="width: 145px;" class="easyui-validatebox" validType="length[0,100]" maxlength="99"/></td>

	    			</tr>
	    			<tr height="30">
	    				<td align="right" nowrap="nowrap">客户分类：</td>
	    				<td><input name="processResult" style="width: 150px;" id="processResultForm" required="true" />
	    					<input name="processResultDetail" style="width: 150px;" id="processResultDetail" type="hidden"/>
	    				</td>
	    				<td width="150"></td>
	    				<td align="right" nowrap="nowrap">客户标签：</td>
	    				<td><input id="processCauses"  name="processCauses" type="hidden"/>
                    <input id="processCausesSection" type="hidden"/>
                    <input type="text" id="processCausesDetailAdd" name="processCausesDetail" style="width: 145px;" editable="false" onKeydown="return false;" onKeyup="return false;" readonly="readonly"
                           onclick="showpprocessCauses('processCauses','processCausesDetail');"
                           />
	    				</td>
	    			</tr>
	    			<tr>
	    					<td align="right" nowrap="nowrap">备注：</td>
	    				<td colspan="5">
	    				
	    				<textarea maxlength="100" id="remarkId"  name="remark" cols="25" rows="5" style="width: 610px;"
	    				class="easyui-validatebox" validType="length[0,100];" onkeydown="countChar('remarkId','counter');"
	    				onkeyup="countChar('remarkId','counter');"></textarea><br />
	    				备注还可以输入<span id="counter">100</span>字<br/>
	    				</td>
	    			</tr>
	    			<tr>
	    				<td align="right"></td>
	    				<td colspan="3" align="center">
	    				<input name="customerConsultId" type="hidden"/>
	    				<input name="receptionist" id="loanOfficerName" style="width: 150px;"
						class="easyui-validatebox"   type="hidden"
						value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>" readonly="readonly" />
						<input name="departmentName" id="company" class="easyui-validatebox" type="hidden"
						value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentName()%>"/>
	    				</td>
	    			</tr>
                    <tr>
                        <td align="right"></td>
                        <td align="right" colspan="5" nowrap="nowrap"><a class="easyui-linkbutton"
                                                                         id="submitButtonCustomer"
                                                                         href="javascript:submitForm();"
                                                                         onclick="subCustomer()">提交</a>
                            <a class="easyui-linkbutton" href="javascript:closeDialog();">取消</a></td>
                    </tr>
	    		</table>
    		</form>
    		</div>


    		<%--!与该联系电话相关的信息luohongjie--%>
    		<div id="submitDialog" class="easyui-dialog" closed="true" style="width: 300px;padding: 10px;"   modal="true">
    		  <form id="subCustomerForm" action="">
    		   <table align="center">
				<tr>
					<td><input id="telNumber" type="hidden"/>
						<input id="telNumberName" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td><a id="num_a" href="javascript:numFromConsult();"></a></td>
				</tr>
    		     <tr>
    		       <td id="numtd_a"></td>
    		     </tr>
    		     <tr>
    		     	<td><a id="num_b" href="javascript:numFromBorrower();"></a><input id="numborrower" type="hidden"/></td>
    		     </tr>
    		     <tr>
    		       <td id="numtd_b"></td>
    		     </tr>
    		     <tr>
    		     	<td><a id="num_c" href="javascript:numFromguantor();"></a><input id="numguantor" type="hidden"/></td>
    		     </tr>
    		      <tr>
    		       <td id="numtd_c"></td>
    		     </tr>
    		   </table>
    		   </form>
    		</div>


	<div id="detail" class="easyui-dialog" closed="true">
		<font style="color: blue;font-size: larger;">当前咨询客户姓名： </font><label id="customerConsultName" value="34535"></label>
		&nbsp; &nbsp; &nbsp;<font style="color: blue;font-size: larger;">电话号码：</font><label id="customerConsultTepephone"></label>
		<div id="detailList"></div>
	</div>
    </div>
    <div id="windowBorrowUse">
    	<div id="tableWindow" align="center"></div>
    	<table id="tableUse" style="width: 590px;border:1px;  border-color: black;" align="center" fit="true" >
    	</table>
    </div>

    <%--<div id="windowinfomationSource">
    	<table id="tableinfomationSource" style="width: 400px;border:1px;  border-color: black;" align="center" fit="true" >
    	</table>
    </div>--%>

    <div id="windowinfomationSource">
    	<table id="tableinfomationSource" style="width: 590px;border:1px;  border-color: black;" align="center" fit="true" >
    	</table>
    </div>

     <div id="windowprocessCauses">
    	<table id="tableprocessCauses" style="width: 500px;border:1px;  border-color: black;" align="center" fit="true" >
    	</table>
    </div>


    <div id="customerComsultDialogEdit" class="easyui-dialog" closed="true" style="width: 800px;padding: 10px;" >
    		<form id="customerComsultFormEdit" action="/CustomerConsult/addUpdate.do" novalidate>
	    		<table >
	    			<tr height="30">
	    				<td align="right">咨询时间：</td>
	    				<td><input name="consultDate" style="width: 150px;height:16px;border:1px solid #A4BED4 ;  background-color: #F0F0F0;"  id="consultDateForm" readonly="readonly" />  </td>
	    				<td width="150"></td>
	    				<td align="right">咨询方式：</td>
	    				<td><input name="consultWay" style="width: 150px;" id="consultWayFormEdit" required="true" type="hidden"/>
	    					<input name="consultWayDetail" style="width: 147px;height:16px;border:1px solid #A4BED4 ; background-color: #F0F0F0;" id="consultWayDetailEdit" required="true" readonly="readonly"/>
	    				</td>
	    			</tr>
	    			<tr height="30">
	    				<td align="right">客户姓名：</td>
	    				<td><input name="customerName" style="width: 147px;" onkeydown="if(event.keyCode==32){return false;}"
	    				class="easyui-validatebox" required="true" validType="length[0,10]" maxlength="10"/> </td>
	    				<td width="150"></td>
	    				<td align="right">联系电话：</td>
	    				<td><input name="telphone" style="height:16px;border:1px solid #A4BED4 ;width: 147px; background-color: #F0F0F0;" class="easyui-validatebox" validType="phoneNumber"
	    				required="true" readonly="readonly"
	    				invalidMessage ="输入11位手机号或加区号的固话号码" maxlength="11"/> </td>
	    			</tr>
		    		<tr height="30">
		    				<td align="right">性别：</td>
		    				<td>
		    					<input name="gender" id="genderEdit" style="width: 147px; background-color: #F0F0F0;" required="false" type="hidden"/>
		    					<input name="genderDetail" id="genderDetailEdit" readonly="readonly" style="width: 147px;" required="false" />
		    				</td>
		    				<td width="150"></td>
		    				<td align="right">信息来源：</td>
		    				<td><input name="infomationSource" style="width: 147px; background-color: #F0F0F0;" required="true"
							 id="_infomationSourceForm" type="hidden"/>
							 <%--<input name="infomationSourceDetail" style="width: 147px;" class="easyui-validatebox" required="true" editable="false"  onKeydown="return false;" onKeyup="return false;" readonly="readonly"
							 id="_infomationSourceDetail" />--%>
                                <input type="text" id="_infomationSourceDetail" readonly="readonly"/>
		    				</td>

		    		</tr>
		    		<tr height="30">
	    				<td align="right" >借款用途：</td>
	    				<td><input style="width: 150px;" name="borrowUse" required="true"  id="borrowUseFormEdit"/>
	    					<input style="width: 150px;" name="borrowUseDetail" required="true"  id="borrowUseDetailEdit" type="hidden"/>
	    				</td>
	    				<td></td>
	    				<td align="right">借款用途行业：</td>
	    				<td><input name="industryCategory" style="width: 150px;"
							id="industryCategoryEdit"  required="true" type="hidden"/>
							<input name="industryCategoryDetail"  class="easyui-validatebox"  id="industryCategoryDetailEdit"
							style="width: 147px;height:16px;border:1px solid #A4BED4"
							 onKeydown="return false;" onKeyup="return false;" readonly="readonly"
							required="true" onFocus="showborrowUse('industryCategoryEdit','industryCategoryDetailEdit');"/>
	    				</td>

	    			</tr>
	    			<tr height="30">
	    				<td align="right">咨询金额：</td>
	    				<td><input name="borrowAmount" style="width: 147px;height:16px;border:1px solid #A4BED4 ;background-color: #F0F0F0;" readonly="readonly" class="easyui-numberbox" precision="2" invalidMessage
	    				="请输入数字!" maxlength="7"/>(元)</td>

	    				<td width="150"></td>
	    				<td align="right">客户住址：</td>
	    				<td><input name="address" style="height:16px;border:1px solid #A4BED4 ;width: 147px; background-color: #F0F0F0;   background-color: #F0F0F0"
	    				class="easyui-validatebox" readonly="readonly"
	    				validType="length[0,100]" maxlength="99"/></td>

	    			</tr>
	    			<tr height="30">
	    				<td align="right">客户分类：</td>
	    				<td><input name="processResult" style="width: 150px;" id="processResultFormEdit" required="true" />
	    					<input name="processResultDetail" style="width: 150px;" id="processResultDetailEdit" type="hidden"/>
	    				</td>
	    				<td width="150"></td>
	    				<td align="right">客户标签：</td>
	    				<td><input  name="processCauses" style="width: 150px;" id="processCausesEdit"  type="hidden" />
	    					<input  name="processCausesDetail"    id="processCausesDetailEdit"
	    					style="width: 147px;height:16px;border:1px solid #A4BED4 ;"
	    					 onKeydown="return false;" onKeyup="return false;" readonly="readonly"
	    					   onclick="pprocessCauses('processCausesEdit','processCausesDetailEdit');" />
	    				</td>
	    			</tr>

	    			<tr>
	    				<td align="right">备注：</td>
	    				<td colspan="5">
	    				<textarea maxlength="100" id="remarkIdEdit"  name="remark" cols="23" rows="2" style="width: 527px;"
	    				class="easyui-validatebox" validType="length[0,100];" onkeydown="countChar('remarkIdEdit','counterEdit');"
	    				onkeyup="countChar('remarkIdEdit','counterEdit');"></textarea><br />
	    				备注还可以输入<span id="counterEdit">100</span>字<br/>
	    				</td>
	    			</tr>
	    			<tr height="50" valign="bottom">
		    				<td align="right"></td>
		    				<td colspan="3" align="center">
		    				<input name="customerConsultId" type="hidden" />
		    				</td>
		    				<td align="right"><a class="easyui-linkbutton" id="submitButtonCustomerEdit"   href="javascript:updateForm();" onclick="subCustomer()">提交</a>
	    			</tr>
	    		</table>
	    	</form>
	 </div>
    <!-- 2014-09-16 add by ygx 身份证验证 -->
    <div id="validateIndentityNumberDialog"
         style="width: 800px; height: 500px; padding: 10px;"
         buttons="#opt_btn">
        <iframe id="validateIndentityNumberFrame" name="validateIndentityNumberFrame" src=""
                scrolling="yes" frameborder="0" style="width: 100%; height: 100%">
        </iframe>
    </div>

  <%--发送短信-弹窗提示--%>
	<div id="send_sms_dialog">
		<table style="padding: 5px;" align="center">
			<tr style="height: 20px;">
				<td colspan="2" style="vertical-align: 50%;" nowrap="nowrap">
					<b>发送您的联系方式到当前咨询客户</b>
				</td>
			</tr>
			<tr>
				<td align="left" nowrap="nowrap">
					您的姓名：
				</td>
				<td nowrap="nowrap">
					<input id="manager_name" type="text"
						   value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>"/>
					<span id="tip_name" style="display: none;">
						<font color="red">*</font>
					</span>
				</td>
			</tr>
			<tr>
				<td align="left" nowrap="nowrap">
					您的电话：
				</td>
				<td nowrap="nowrap">
					<input id="manager_phone" type="text" maxlength="11"/>
					<span id="tip_phone" style="display: none;">
						<font color="red">*</font>
					</span>
				</td>
			</tr>
			<tr>

			</tr>
		</table>
	</div>
  </body>
      <%
      out=pageContext.pushBody();
		 %>
</html>
