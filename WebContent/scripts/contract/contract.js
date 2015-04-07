$(function() {
	var dic = new DataDictionary();
	dic.addDic("cardFlag_cardFlag_Y", "cardFlag");
	dic.addDic("lendingChannel_Y", "lendingChannel");
	//if(flag == "2" || flag=="3"){
		$("#lendingChannel").combobox({
			disabled : true
		});
	/*}else{
		$("#lendingChannel").combobox({
			disabled : false
		});
	}*/
	
	//选择放款渠道  判断显示打印合同按钮
	$("#lendingChannel").combobox({
		onSelect : function() {
			var lendingChannel = $("#lendingChannel").combobox("getValue");
			if (lendingChannel == 0) {
				//债权转让
				$("#printContractButXintuo").hide();
				$("#printContractBut").show();
			}else{
				//信托
				$("#printContractBut").hide();
				$("#printContractButXintuo").show();
			}
		}
	});
	var lendingChannel = $("#lendingChannel").val();
	if(lendingChannel =="0"){//债权转让
		$("#qixiDate1").hide();
		$("#qixiDate2").show();
		$("#xtjh1").hide();
		$("#xtjh2").hide();
		$("#printContractButXintuo").hide();
		$("#printContractBut").show();
		dic.addDic("bankNum_bankNum_Y", "bankNum");
		dic.addDic("lendingChannel_Y", "lendingChannel");
		
	}else if(lendingChannel=="1"){//信托
		$("#qixiDate1").show();
		$("#qixiDate2").hide();
		$("#xtjh1").show();
		$("#xtjh2").show();
		$("#printContractBut").hide();
		$("#printContractButXintuo").show();
		dic.addDic("bankNum_bankNum_Y", "bankNumxt");	
		dic.addDic("lendingChannel_Y", "lendingChannel");
		
	}
	dic.dicAjax();
	
	
	// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
	//判断省市区是否为空
	var provinceId1=$("#provinceId1").val();
	if(provinceId1==""){
		provinceShowPublicWidth("provinceId1", "cityId1","districtId1");
	}else{
		showCityCombopublicWidth("provinceId1", "cityId1","districtId1");
	}
    //showCityCombo("provinceId1", "cityId1", "districtId1");
	// 初始化账户名
	$("#accountName").combobox({
		url : serverName + "/creditgroup/selectBorrowerFamily.do?creditapplicationId=" + $("#creditapplicationId").val(),
		mode : 'remote',
		editable : false,
		required : true,
		valueField : "name",
		textField : "name",
		onSelect : function(data) {
			$("#credentialsNumber").val(data.credentialsNumber);
		}
	});
	//初始化营业部名称，ID，电话号码
	var creditapplicationId=$("#creditapplicationId").val();
	ajaxSubmit(serverName + "/contractAndLoanController/searchForCompanyNameAndMobilePhone.do?creditapplicationId="+creditapplicationId, function(r) {
		   $("#companyId").val(r.companyId);
		   $("#branchName").val(r.branchName);
		   $("#mobilephone").val(r.mobilephone);
	});
	
	
	// 初始化起息日
	var todayStamp = $("#todayStamp").val();
	var today = new Date(parseInt(todayStamp, 10));
	var tomorrowStamp = $("#tomorrowStamp").val();
	var tomorrow = new Date(parseInt(tomorrowStamp, 10));

	var dD = today.getDate();
	var dM = today.getMonth();
	dM = dM + 1;
	var dY = today.getFullYear();

	var mD = tomorrow.getDate();
	var mM = tomorrow.getMonth();
	mM = mM + 1;
	var mY = tomorrow.getFullYear();

	var todayValue = dY + "-" + dM + "-" + dD;
	var todayText = dY + "年" + dM + "月" + dD + "日";

	var tomorrowValue = mY + "-" + mM + "-" + mD;
	var tomorrowText = mY + "年" + mM + "月" + mD + "日";
	$("#beginInterestTime").combobox("setValue", "1");
	$("#beginInterestTime").combobox({
		editable : false,
		required : true,
		valueField : "value",
		textField : "text",
		data : [{
			value : todayValue,
			text : todayValue
		}, {
			value : tomorrowValue,
			text : tomorrowValue
		}],
		onSelect : function(data) {
		}
	});
	
	
	//放款渠道为信托时   起息日期取当天
	$("#beginInterestTimeXinTuo").combobox("setValue", "1");
	$("#beginInterestTimeXinTuo").combobox({
		editable : false,
		required : true,
		valueField : "value",
		textField : "text",
		data : [{
			value : todayValue,
			text : todayValue
		}],
		onSelect : function(data) {
		}
	});
	
	
	$("#loanTime").combobox("setValue", "1");
	$("#loanTime").combobox({
		editable : false,
		required : true,
		valueField : "value",
		textField : "text",
		data : [{
			value : todayValue,
			text : todayValue
		}, {
			value : tomorrowValue,
			text : tomorrowValue
		}],
		onSelect : function(data) {
		}
	});
	
	// 初始化合同签订时间规则
	$("#contractSignedTime").datetimebox({
		editable : false,
		required : true,
		onHidePanel : function() {
			var selectTimeStr = $("#contractSignedTime").datetimebox("getValue");
			selectTimeStr = selectTimeStr.replace(/-/g, "/");
			var selectTime = new Date(selectTimeStr);
			var after30MStamp = $("#after30MStamp").val();
			//alert(after30MStamp);
			var after30Time = new Date(parseInt(after30MStamp));
			var s = selectTime.getTime();
			var a = after30Time.getTime();
		}
	});
	// 初始化要求放款时间规则
	/*$("#loanTime").datetimebox({
		editable : false,
		required : true,
		onHidePanel : function() {
			var selectTimeStr = $("#loanTime").datetimebox("getValue");
			selectTimeStr = selectTimeStr.replace(/-/g, "/");
			var selectTime = new Date(selectTimeStr);
			var after30MStamp = $("#after30MStamp").val();
			//alert(after30MStamp);
			var after30Time = new Date(parseInt(after30MStamp));
			var s = selectTime.getTime();
			var a = after30Time.getTime();
			if (s >= a) {
			} else if (s < a) {
				alert("要求放款时间至少在30分钟之后");
				$("#loanTime").datetimebox("clear");
			} else {
				alert(s + "-" + a);
				$("#loanTime").datetimebox("clear");
			}

		}
	});*/
	$("#form2").form("validate");
});


/*function showCityCombo(province, city, district) {
	var provinceid = $("#provinceId1").combobox("getValue");

	var province = $("#" + province).combobox({
		// required : true,
		editable : false,
		url : serverName + '/NSC/list.do',
		textField : 'cityName',
		valueField : 'cityCode',
		mode : 'remote',
		delay : 500,
		width : '80',
		value : provinceid,
		onSelect : function(value) {
			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				width : '80',
				value : '',
				onSelect : function(value) {
					$("#" + district).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						width : '80',
						delay : 500,
						value : ''
					});
				}
			});
		}
	});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId = $("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '80',
			value : ciryId
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '80',
			value : districtId
		});
	}

}*/
function editAmount() {
	var value = $("#editAmountButton").val();
	if (value == "修改") {
		$("#editAmountButton").val("确定");
		$(".easyui-numberbox").removeClass("myInputBoder");
		$(".easyui-numberbox").removeAttr("readonly");
	} else if (value = "确定") {
		$(".easyui-numberbox").addClass("myInputBoder");
		$(".easyui-numberbox").attr("readonly", "readonly");
		$("#editAmountButton").val("修改");
		ajaxSubmit("", {}, function(r) {
			//formLoad
		});
	}
}

//点击打印合同按钮
function printContract(){
	// 验证
	if ($("#form2").form('validate')) {
		// 禁用打印合同按钮
		$("#printContractBut").linkbutton('disable');
		// 加入打折判断
		ajaxSubmit(serverName + "/discountConfigurationController/discountRule.do", {
			creditapplicationId : $("#creditapplicationId").val()
		}, function(a) {
			if (a.success) {
				// 加入打折的校验
				ajaxSubmit(serverName + "/discountConfigurationController/checkDiscountConfiguration.do", {
					creditapplicationId : $("#creditapplicationId").val()
				}, function(r) {
					if (r.success || r.success == "true") {
						// 调用没有出问题
						if (r.alert == "alert") {
							$.messager.confirm("提示", r.msg, "info");
							$("#addButton").linkbutton('enable');
						} else {
							//打印合同方法  begin
							$("#bankRank").val($("#bankNum").combobox("getText"));
								var data1 = $("#form1").serialize();
								var data2 = $("#form2").serialize();
								var data3 = $("#form3").serialize();
								var creditapplicationId=$("#creditapplicationId").val();
								ajaxSubmit(serverName + "/contractAndLoanController/printContract.do",data1 + "&" + data2+"&" + data3, function(data) {
									// 提示
									if (data && data.success) {
										//启用打印合同按钮
										window.location.href = data.msg;
										$.messager.alert("", "请耐心等待合同下载完成后再关闭此窗口！", "ok", function(){
											//提示操作成功的方法
											parent.messageSuccess();
					        				//重新加载放款列表页面
											parent.flushContractDateGrid();
					        				// 关闭 合同页面
					        				parent.closeTab();
										});
										
									} else {
										$("#printContractBut").linkbutton('enable');
										//操作失败提示
		                				parent.messageFail();
									}

								});
								//end
						}
					} else {
						// 调用出问题了
						$("#addButton").linkbutton('enable');
						$.messager.alert("消息", r.msg, "info");
					}

				});
			} else {
				$("#addButton").linkbutton('enable');
				$.messager.alert("消息", a.msg, "info");
			}

		});
		} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		//启用打印合同按钮
		$("#printContractBut").linkbutton('enable');
	}
		
}
	
//修改借款总额
function confirmAmount(obj){
	var limitAmount = $("#loanAmount").val();
	if (accSubtr(obj.value, limitAmount) > 0) {
		$.messager.alert("消息", "放款金额不应大于审批金额！审批金额为："+limitAmount);
		$("#amount").val(limitAmount);
	}
}
//修改借款总额
function editAmountAllMoney(){
	var amount=$("#amount").val();
	var creditapplicationId=$("#creditapplicationId").val();
	ajaxSubmit(serverName + "/contractAndLoanController/CalculateByCreditapplicationIdAndAmont.do?creditapplicationId="+creditapplicationId + "&amount=" +amount , function(r) {
		   $("#toAmount").val(r.toAmount);
		   $("#serviceCharge").val(r.serviceCharge);
		   $("#serviceCharge").val(r.serviceCharge);
		   $("#managementFee").val(r.managementFee);
		   $("#monthlyPayments").val(r.monthlyPayments);
		   $("#prepayments").val(r.prepayments);
	});
}
//上传合同资料
function showUploadDigContract(){
	if ($("#form3").form("validate")) {
	var creditapplicationId= $("#creditapplicationId").val();
	//alert(creditapplicationId);
	var clientid = $("#clientid").val();
	var creditApplicationEscId=$("#creditApplicationEscId").val();
	var cid = creditapplicationId + "CM_LOAN";
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1, function(result) {
		if (result) {
			var signTime = result[0];
			var signIp = result[1];
			var href =  cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + clientid
			+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=" + userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
			var content = '<iframe scrolling="auto" id="openCM1" frameborder="0"  src="' + href + '" style="width:100%;height:100%;">';
			$("#cm1").dialog({
				content : content,
				title : "上传合同资料",
				modal : false,
				maximizable : true,
				closed : true,
				width : 1000,
				height : 350,
				buttons : [ {
					id : "rdButton1",
					text : "提交",
					iconCls : "icon-ok",
					handler : function() {
						var url = serverName + "/creditgroup/getImgAmount.do";
						var imgAmount;
						$.ajax({
							type : "post",
							async : false,
							url : url,
							data : {
								creditAppId : cid
							},
							success : function(result) {
								imgAmount = result;
							}
						});
						if (imgAmount == 0) {
							$("#loanButton").linkbutton("enable");
							$.messager.alert("消息", "请先上传合同资料");
							return;
						} else if (imgAmount < 0) {
							$("#loanButton").linkbutton("enable");
							$.messager.alert("消息", "上传服务异常,请稍后重试");
							return;
						}
							//禁用提交按钮
							$("#rdButton1").linkbutton("disable");
							//将实发金额（放款金额-服务费，向财务预约的金额）
							$("#realAmount").val($("#toAmount").val());
							//合同金额（业务详情列表中显示的）
							$("#loanAmount").val($("#amount").val());
							var data = $("#form3").serialize();
							ajaxSubmit(serverName + "/contractAndLoanController/contractSigned.do",data, function(result) {
								if (result) {
			        						//关闭上传合同资料框
											$("#cm1").dialog("close");
											//提示操作成功的方法
											parent.messageSuccess();
											//重新加载放款列表页面
											parent.flushContractDateGrid();
			                				// 关闭 合同页面
			                				parent.closeTab();
											//$("#loanButton").linkbutton("enable");
								}else{
									$("#cm1").dialog("close");
								//启用提交按钮
								$("#rdButton1").linkbutton("enable");
								//操作失败提示
                				parent.messageFail();
								}
							});
						
					}
				}, {
					id : "rdButton2",
					text : "取消",
					iconCls : "icon-cancel",
					handler : function() {
						$('#cm1').dialog('close');
					}
				} ]
			});
			// $('#openCM')[0].src = href;
			$('#cm1').dialog('open');
		} else {
			$.messager.alert("提示信息", "服务异常，请稍后重试");
		}
	});
	} else {
	$.messager.alert("消息","请将信息填写完整！","info");
	$("#loanButton").linkbutton("enable");
}
}
function cancelContract(){
	var creditapplicationId= $("#creditapplicationId").val();
	//获取放款渠道的值判断是否是信托
	var lendingChannel= $("#lendingChannel").val();
	
	$.messager.confirm('消息','确认作废合同吗？',function(b){
			if(b){
				if(lendingChannel==1){
						$("#cancelContractReasonContent").dialog({
							title : "作废合同原因",
							modal : false,
							maximizable : true,
							closed : true,
							width : 340,
							height : 250,
							buttons : [ {
								id : "rdButton1",
								text : "保存",
								iconCls : "icon-ok",
								handler : function() {
									if ($("#cancelContractReasonForm").form('validate')) {
										// 禁用保存按钮
									$("#rdButton1").linkbutton("disable");
									var data1 = $("#cancelContractReasonForm").serialize();
									ajaxSubmit(serverName + "/contractAndLoanController/saveCancelReasonRepeal.do",data1, function(r) {
										// 提示
										if (r.success) {
					        						//启用保存按钮
					        						$("#rdButton1").linkbutton("enable");
													//清空
													$("#cancelContractReasonContent").form("clear");
													//关闭原因对话框
													$("#cancelContractReasonContent").dialog('close');
													//提示操作成功的方法
													parent.messageSuccess();
													//重新加载放款列表页面
													parent.flushContractDateGrid();
					                				// 关闭 合同页面
					                				parent.closeTab();
					                			} else {
					                				//操作失败提示
					                				parent.messageFail();
					                			}
											$("#cancelContractReasonContent").dialog('close');
											// 调用清空from表单方法
											$("#cancelContractReasonForm").dialog("clearSelections");
									});
								}else {
									$.messager.alert("消息", "请填写完整信息！", "warning");
									
								}
							}
					}]
				});
						//启用保存按钮的方法
						$('#cancelContractReasonContent').dialog('open');
				}else{
					//放款渠道为债权转让时
					$("#cancelContractReasonContent").dialog({
						title : "作废合同原因",
						modal : false,
						maximizable : true,
						closed : true,
						width : 340,
						height : 250,
						buttons : [ {
							id : "rdButton1",
							text : "保存",
							iconCls : "icon-ok",
							handler : function() {
								if ($("#cancelContractReasonForm").form('validate')) {
									// 禁用保存按钮
								$("#rdButton1").linkbutton("disable");
								var data1 = $("#cancelContractReasonForm").serialize();
								ajaxSubmit(serverName + "/contractAndLoanController/saveCancelReason.do",data1, function(r) {
									// 提示
									if (r.success) {
				        						//启用保存按钮
				        						$("#rdButton1").linkbutton("enable");
												//清空
												$("#cancelContractReasonContent").form("clear");
												//关闭原因对话框
												$("#cancelContractReasonContent").dialog('close');
												//提示操作成功的方法
												parent.messageSuccess();
												//重新加载放款列表页面
												parent.flushContractDateGrid();
				                				// 关闭 合同页面
				                				parent.closeTab();
				                			} else {
				                				//操作失败提示
				                				parent.messageFail();
				                			}
										$("#cancelContractReasonContent").dialog('close');
										// 调用清空from表单方法
										$("#cancelContractReasonForm").dialog("clearSelections");
								});
							}else {
								$.messager.alert("消息", "请填写完整信息！", "warning");
								
							}
						}
				}]
			});
					//启用保存按钮的方法
					$('#cancelContractReasonContent').dialog('open');
				}
				
			}
	});
}
//签约失败
function signedFail(){
	//获取放款渠道的值判断是否是信托
	//var lendingChannel= $("#lendingChannel").val();
	var creditapplicationId= $("#creditapplicationId").val();
	$.messager.confirm('消息','确认做签约失败操作吗？',function(b){
		if(b){
		//判断是否是信托合同
		/*if(lendingChannel==1){
			//调用撤销撮合的接口
			ajaxSubmit(serverName + "/contractAndLoanController/revokeMatchmaking.do?creditapplicationId="+creditapplicationId, function(r) {
				if(r.success){
						$("#signedFailReason").dialog({
							title : "签约失败原因",
							modal : false,
							maximizable : true,
							closed : true,
							width : 310,
							height : 170
						});
						$('#signedFailReason').dialog('open');
					}
				
			});
		}else{*/
			$("#signedFailReason").dialog({
				title : "签约失败原因",
				modal : false,
				maximizable : true,
				closed : true,
				width : 310,
				height : 170
			});
			$('#signedFailReason').dialog('open');
		}
		//}	
	});

}

//显示拒贷原因
function showRefuseReasonDiv(valueField,textField){
	//原因
	var reasons = $("#RefuseReason").val();
	var reasonsArray = reasons.split(",");
	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"refuseReasonTotal",q:""},function(result){
			 var table = document.getElementById("refuseReasonTable");
			 var pchildren = table.childNodes;
			 //清空表中的行和列
			 for(var a=pchildren.length-1; a>=0; a--){
				 table.removeChild(pchildren[a]);
			 }
			 //判断颜色的 
			 var m = 0;
		 	for(var k = 0 ; k < result.refuseReasonTotal.length;k++){
				 	var  index = result.refuseReasonTotal[k].codaTableId;	
				 	var  name = "refuseReasonTotal"+index;
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
								cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ result.refuseReasonTotal[k].codeVlue+"</font>";
								cell1.setAttribute("rowspan",length);
								cell1.setAttribute("align","center");
								cell1.setAttribute("width","150");
								cell1.setAttribute("bgcolor","#DDE4EE");
								tr.appendChild (cell1);
							}
							
						
						for(var j = 0 ; j < 3 ;j++){
							var cell=document.createElement("td"); 
							cell.setAttribute("width","360");
							n = n+1;
							if(m % 2 == 0){
								cell.setAttribute("bgcolor","#DDE4EE");
							}
							if(object[n-1] != undefined){
								$("#RefuseReasonSection").val(object[n-1].section);
								var key = object[n-1].codeKey;
								var value = object[n-1].codeVlue;
								var arrayObj = new Array(key,value);
								var obj = new Object();
								obj["codeKey"]=object[n-1].codeKey;
								obj["codeValue"]=object[n-1].codeVlue;
									for(var l =0;l <reasonsArray.length;l++){
										if(obj.codeKey == reasonsArray[l]){
											cell.innerHTML = 
												"<input type='checkbox' checked='checked' name='refuseReason' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
											break;
										}else{
											cell.innerHTML = 
												"<input type='checkbox' name='refuseReason' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
										}
									}
									
									//"<a href='javaScript:addRefuseReason(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
								}
							tr.appendChild (cell); 
						}
				  	table.appendChild (tr); 
					}
		 	}
		$("#refuseReasonDiv").dialog({   
		    width:950,  
		    height:30*(Math.ceil(result.length/3)), 
		    title:"拒贷原因",
		    minimizable:false,
		    maximizable:false,
		    collapsible:false,
				modal:true,
				buttons:[{
						id:"refuseButton",
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
							addRefuseReason();
						}
					}
				   ]
			});   
	});
}
//添加拒贷原因
function addRefuseReason(){
	var datas = $("[name='refuseReason']");
	var refuseResionKey= "";
	var refuseResionValue= "";
	for(var i =0;i<datas.length;i++){
		if(datas[i].checked == true){
		refuseResionKey = refuseResionKey +","+datas[i].value;
		refuseResionValue = refuseResionValue+ "," + datas[i].nextSibling.nodeValue;
		}
	}
	if(refuseResionKey !=""){
		refuseResionKey = refuseResionKey.substr(1,refuseResionKey.length);
		refuseResionValue = refuseResionValue.substr(1,refuseResionValue.length);
	}
	$("#showRefuseReason").val(refuseResionValue);
	$("#RefuseReason").val(refuseResionKey);
	$("#RefuseReason").focus();
	$("#refuseReasonDiv").dialog("close");
}
/**************信托打印合同***************/
function addTrustprintContract(){
	// 验证
	if ($("#form2").form('validate')) {
		// 禁用打印合同按钮
		$("#printContractBut").linkbutton('disable');
		// 加入打折判断
		ajaxSubmit(serverName + "/discountConfigurationController/discountRule.do", {
			creditapplicationId : $("#creditapplicationId").val()
		}, function(a) {
			if (a.success) {
				// 加入打折的校验
				ajaxSubmit(serverName + "/discountConfigurationController/checkDiscountConfiguration.do", {
					creditapplicationId : $("#creditapplicationId").val()
				}, function(r) {
					if (r.success || r.success == "true") {
						// 调用没有出问题
						if (r.alert == "alert") {
							$.messager.confirm("提示", r.msg, "info");
							$("#addButton").linkbutton('enable');
						} else {
							//打印合同方法  begin
							$("#bankRank").val($("#bankNum").combobox("getText"));
								var data1 = $("#form1").serialize();
								var data2 = $("#form2").serialize();
								var data3 = $("#form3").serialize();
								var creditapplicationId=$("#creditapplicationId").val();
								//调用信托 结构资产的撮合方法
								ajaxSubmit(serverName + "/contractAndLoanController/trustprintContract.do",data1 + "&" + data2+"&" + data3, function(data) {
									// 提示
									if (data && data.success) {
										//弹出  撮合情况框
										$("#contractMatchmaking").dialog({
											title : "撮合情况",
											modal : false,
											maximizable : true,
											closed : true,
											width : 340,
											height : 250,
											buttons : [ {
												id : "rdButton1",
												text : "打印合同",
												iconCls : "icon-ok",
												handler : function() {
														// 禁用保存按钮
													$("#rdButton1").linkbutton("disable");
													//启用打印合同按钮
													$.post(serverName + "/contractAndLoanController/updatePrintContractState.do?creditapplicationId="+creditapplicationId,function(o) {
									        			if (o.success) {
										                	window.location.href = o.msg;
									        			}else {
									        				$("#printContractBut").linkbutton('enable');
									        				//操作失败提示
							                				parent.messageFail();
									        			}
													});
													
													$.messager.alert("", "请耐心等待合同下载完成后再关闭此窗口！", "ok", function(){
														//提示操作成功的方法
														parent.messageSuccess();
								        				//重新加载放款列表页面
														parent.flushContractDateGrid();
								        				// 关闭 合同页面
								        				parent.closeTab();
													});
												}
									}]
								});
										$('#contractMatchmaking').dialog('open');
										
										$("#contractMatchmakingMsg").val(data.msg);
									} else {
										$("#printContractBut").linkbutton('enable');
										//操作失败提示
		                				parent.messageFail();
									}

								});
								//end
						}
					} else {
						// 调用出问题了
						$("#addButton").linkbutton('enable');
						$.messager.alert("消息", r.msg, "info");
					}

				});
			} else {
				$("#addButton").linkbutton('enable');
				$.messager.alert("消息", a.msg, "info");
			}

		});
		} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		//启用打印合同按钮
		$("#printContractBut").linkbutton('enable');
	}
}