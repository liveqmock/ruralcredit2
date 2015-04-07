$(function() {
	$("#identityGuaranor").combogrid({
		panelWidth : 556,
		panelHeight : 52,
       // required:true,
        validType:"idnumberAll",
        length:[18,18] ,
        //missingMessage:"请输入正确的18位身份证号码... "  ,
		mode : "remote",
		url : serverName + "/customer/getGuarnorIdentityListInfo.do",
		idField : "credentialsNumber",
		textField : "credentialsNumber",
		columns : [ [ {
			field : "name",
			title : "姓名",
			width : 100
		}, {
			field : "credentialsNumber",
			title : "身份证号",
			width : 150
		}, {field :"auditStatusShow" ,title: "状态" , width:100,formatter:function(value){
            if(value == null ||value == ""){
                return "未申请";
            }else{
                return value;
            }
        }
		}, {
			field : "flag",
			title : "操作",
			width : 100,
			formatter : function(value,data,index) {
                // console.info("-----------value----start----"+value);
                //step0检查一期未完成用户
                //step1检查借款人黑名单信息
                //step2检查借款人信息
                //step3检查借款人配偶信息
                //step4检查借款人担保信息
                if(data.customerType == "5"){
                    $("#buttonAddGuaranor").html("<font color='red'>此人已借款且为一期未完成，不能添加！</font>");
                    return "不可用";
                }
                //step1检查借款人黑名单信息
                if(data.blackFlag != null && data.blackFlag != ""){
                    $("#buttonAddGuaranor").html("<font color='red'>此人在黑名单中，不能做担保！</font>");
                    return "不可用";
                }
                //step2检查借款人信息
                if(data.customerType == "4"){
                    $("#buttonAddGuaranor").html("<font color='red'>此人在同组担保人中，不能做担保！</font>");
                    return "不可用";
                }
                //检查借款人及配偶信息
                if(data.customerType == "1" || data.customerType == "3" ){
                    if($.trim(value) == "18"
                        ||$.trim(value) == "16"
                        ||$.trim(value) == "20"
                        ||$.trim(value) == "26"
                        ||($.trim(value) == "27" && data && data.specialRefuseFlag != "sayYes")
                        ||$.trim(value) == "28"){
                        //正常的
                        return "<a href='javascript:addNewGuaranor();'><font>选中添加</font></a>";
                    }else{
                        if(data.customerType == "1" ){
                            $("#buttonAddGuaranor").html("<font color='red'>此人已借款且借款状态为"+data.auditStatusShow+"，不能做担保！</font>");
                        } else{
                            $("#buttonAddGuaranor").html("<font color='red'>此人为其他借款人共用借款人，且借款状态为"+data.auditStatusShow+"，不能做担保！</font>");
                        }
                        return "不可用";
                    }
                }
                //step4检查借款人担保信息
                if(data.customerType == "0"){
                    if( $.trim(value) == "16"
                        ||$.trim(value) == "20"
                        ||$.trim(value) == "18"
                        ||$.trim(value) == "26"
                        ||($.trim(value) == "27")
                        ||$.trim(value) == "28"){
                        //正常的
                        return "<a href='javascript:addNewGuaranor();'><font>选中添加</font></a>";
                    }else{
                        $("#buttonAddGuaranor").html("<font color='red'>此人已为其他借款人做了担保，且状态为"+data.auditStatusShow+"，不能做担保！</font>");
                        return "不可用";
                    }
                }
                //step5 检查此人直系亲属是否在做借款和担保不符合标准
                if(data.customerType == "6"){
                    $("#buttonAddGuaranor").html("<font color='red'>此人直系亲属在借款期或者担保期，且状态为"+data.auditStatusShow+"，不能做担保！</font>");
                    return "不可用";
                }
                return "<a href='javascript:addNewGuaranor();'><font>选中添加</font></a>";
			}
		} ] ],
		hasDownArrow : true,
        onChange:function(){
            var rows = $("#identityGuaranor").combogrid("grid").datagrid("getRows");
         //   console.info("---------rows-----"+rows.length);
            for(var i=0;i<rows.length;i++){
                $("#identityGuaranor").combogrid("grid").datagrid("deleteRow",i);
            }
           // console.info("---------change--end---");
        }   ,
        onBeforeLoad:function(){

            $("#buttonAddGuaranor").html("");
            if($.trim($("#identityGuaranor").combogrid("getValue"))==null || $.trim($("#identityGuaranor").combogrid("getValue")) ==""){
                $("#buttonAddGuaranor").html("<font color='red'> 请输入正确的18位身份证号码...</font>");
                return false;
            }
            if($("#identityGuaranor").combogrid("isValid")){
                return true;
            }   else{
                $("#buttonAddGuaranor").html(" <font color='red'>请输入正确的18位身份证号码...</font>");
                return false;
            }
        },
		onLoadSuccess : function(data) {
            //把身份证号加到隐藏域中
            $("#credentialsNumberAddGuaranor").val($("#identityGuaranor").combogrid("getValue"));
            //判断是否是首次进入及未输入身份证号
            var identityNumber = $.trim($("#identityGuaranor").combogrid("getValue"));
            if(identityNumber == null || ""==identityNumber ) {
                return;  //首次进入直接返回及未输入身份证号
            }
            if(data.total == 0 && $.trim(identityNumber).length == 18){
                //console.info(data+"------1-----");
                //未在系统中做过贷款信息
                $("#buttonAddGuaranor").html(" <a href='javascript:addNewGuaranor(1);' id='addbuttonG'>该客户可用，可以点此添加！</a>");
                return;
            }
		}
	});
//	$("#tt").tabs({
//		onSelect : function(title) {
//			if (title = "组员资料添加") {
//				$("#identityGuaranor").combogrid({
//					url : serverName + "/guaranorProfile/guaranorComboGrid.do?creditapplicationId=" + $("#creditapplicationId1").val()
//				});
//			}
//		}
//	});
	$("#guaranorList").datagrid({
		url : serverName + '/borrowerServiceApp/guaranorList.do',
		nowrap : false,
		idfield : "borrowerServiceAppId",
		queryParams : {
			creditapplicationId : $("#creditapplicationId1").val()
		},
		singleSelect : true,
		sortOrder : "desc",
		sortName : "",
		columns : [ [ {
			title : "担保人姓名",
			field : "name",
			width : "150"
		}, {
			title : "身份证号码",
			field : "credentialsNumber",
			width : "150"
		}, {
			title : "担保人信息表",
			field : "firstFlag",
			width : "150",
			formatter : function(value, rowData, rowIndex) {
				if (value == "1") {
					return '<a href="javaScript:openGuaranorDialog(' + rowData.borrowerServiceAppId + ',1' + ');"><font color="green">点击修改</font></a>';
				} else {
					return '<a href="javaScript:openGuaranorDialog(' + rowData.borrowerServiceAppId + ',0' + ');"><font color="red">点击添加</font></a>';
				}
			}
		}, {
			title : "担保人资料上传",
			field : "borrowerServiceAppDESId",
			width : "150",
			formatter : function(value, rowData, rowIndex) {
				return "<a href='javaScript:showGuaranorUploadDig(\"" + rowData.borrowerServiceAppDESId + "\");'>上传文件</a>";
			}
		}, {
			title : "操作",
			field : "borrowerServiceAppId",
			width : "150",
			formatter : function(value) {
				return '<a href="javaScript:deleteBorrowerInfoG(' + value + ');"><font color="green">删除</font></a>';
				// return "<font color='red'>暂无操作</font>";
			}
		} ] ]
	});

});
function disableButton(buttonId) {
	$("#" + buttonId + "").linkbutton('disable');
}
function enableButton(buttonId) {
	$("#" + buttonId + "").linkbutton('enable');
}
function closeGuarantor(dialogId,msg) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : msg
	});
	$("#" + dialogId + "").dialog('close');
	$('#openGuaranor')[0].src = "";
}
function showMessage() {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : "保存失败！"
	});
}
function openGuaranorDialog(borrowerServiceAppId, param) {
	$('#openGuaranor')[0].src = serverName + "/guaranorProfile/guaranorProfileJSP.do?borrowerServiceAppId=" + borrowerServiceAppId + "&param=" + param + "&businessNumber=" + $("#groupNumber").val();
	$("#guaranorDialog").dialog({
		buttons : [ {
			id : "guaranorButton0",
			text : "暂存",
			handler : function() {
				var guarantor = window.frames["openGuaranor"];
				if (guarantor.window) {
					guarantor.window.tempDoSaveShowUpdate(param);
				} else {
					guarantor.contentWindow.tempDoSaveShowUpdate(param);
				}
			}
		},{
			id : "guaranorButton1",
			text : "保存",
			handler : function() {
				var guarantor = window.frames["openGuaranor"];
				if (guarantor.window) {
					guarantor.window.doSaveShowUpdate(param);
				} else {
					guarantor.contentWindow.doSaveShowUpdate(param);
				}
			}
		}, {
			id : "guaranorButton2",
			text : "取消",
			handler : function() {
				$("#guaranorDialog").dialog('close');
				$('#openGuaranor')[0].src = "";
			}
		} ],
// href : serverName + "/jsp/rc/business/guaranorProfile.jsp",
		onLoad : function() {
// showGuaranorDialog(borrowerServiceAppId, param, false);
		},
		onClose : function() {
			isShowoOrUpdate = false;
			$("#guaranorList").datagrid('reload', {
				creditapplicationId : $("#creditapplicationId1").val()
			});
			$('#openGuaranor')[0].src = "";
		}

	});
	$("#guaranorDialog").dialog('open');
}

// function openRiskMgrDialog(creditapplicationId, groupNumber, creditInvestigatioId) {
// // true代表新增false代表修改
// var addOrUpdate = false;
//
// if (creditInvestigatioId == null) {
// creditInvestigatioId = "";
// addOrUpdate = true;
// }
// $("#riskMgrDialog").dialog(
// {
// title : "信用与背景调查",
// closed : true,
// modal : true,
// inline : false,
// width : 1000,
// height : 500,
// border : false,
// dosize : false,
// draggable : false,
// buttons : [ {
// id : "saveButton",
// text : "保存",
// handler : function() {
// saveRiskMgr(0);
// }
// }, {
// id : "saveAndSubmitButton",
// text : "保存&提交",
// handler : function() {
// saveRiskMgr(1);
// }
// }, {
// text : "取消",
// handler : function() {
// alert("取消");
// }
// } ],
// href : serverName + "/creditInvestigation/toAddAndEditPageCreditInvestigation.do?creditapplicationId=" + creditapplicationId + "&groupNumber=" +
// groupNumber + "&creditInvestigatioId="
// + creditInvestigatioId,
// onLoad : function() {
// if (addOrUpdate == false) {
// // 回显操作
// showRiskMgrDialog(creditapplicationId, groupNumber, creditInvestigatioId);
// }
// },
// onClose : function() {
// $("#riskMgr").datagrid('reload');
// }
// });
// $("#riskMgrDialog").dialog('open');
// }

// 新增担保人
function addNewGuaranor(value) {
    if(value == 1){
        $("#buttonAddGuaranor").html("");
    }
	var length = $("#guaranorList").datagrid('getRows').length;
	if (length < 2) {
		var credentialsNumber = $.trim($("#identityGuaranor").combogrid("getValue"));
		var creditapplicationId = $("#creditapplicationId1").val();
		// var customerConsultId = $("#customerConsultId").val();
		ajaxSubmit(serverName + "/customer/addNewGuaranor.do", {
			creditapplicationId : creditapplicationId,
			credentialsNumber : credentialsNumber
		}, function(r) {
			if (r == true) {
				$("#guaranorList").datagrid("reload", {
					creditapplicationId : $("#creditapplicationId1").val()
				});
			} else {
				$.messager.alert("添加失败！");
			}
		});
	} else {
		$.messager.alert("消息", "最多只能添加两位担保人！", "info");
	}
//	if ($("#identity").combogrid("grid").datagrid("getRows").length != 0) {
//		$("#identity").combogrid("grid").datagrid("deleteRow", 0);
//	}
//	if ($("#identityGuaranor").combogrid("grid").datagrid("getRows").length != 0) {
//		$("#identityGuaranor").combogrid("grid").datagrid("deleteRow", 0);
//	}
	$("#identityGuaranor").combogrid("setValue", "0");
	$("#identityGuaranor").combogrid("clear");
//	$("#identity").combogrid("setValue", "0");
//	$("#identity").combogrid("clear");
	$("#buttonAddGuaranor").html("");
//	$("#buttonAdd").html("");
//	$("#identity").combogrid("hidePanel");
	$("#buttonAddGuaranor").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
}

function deleteBorrowerInfoG(borrowerServiceAppId) {
	$.messager.confirm('消息', '确定要删除吗？', function(b) {
		if (b) {
			var url = serverName + '/borrowerServiceApp/deleteById.do';
			ajaxSubmit(url, {
				borrowerServiceAppId : borrowerServiceAppId
			}, function(result) {
				if (result.success) {
					$.messager.show({
						msg : '删除成功！',
						title : '消息'
					});
					$("#guaranorList").datagrid('load');
					$("#identityGuaranor").combogrid("setValue", "0");
					$("#identityGuaranor").combogrid("clear");
				} else {
					$.messager.show({
						msg : result,
						title : '消息'
					});
				}
			});
		}
	});
	$("#guaranorList").datagrid('unselectAll');
}
/** wyf担保人内容管理平台页面 * */
function showGuaranorUploadDig(borrowerServiceAppDESId) {
// var clientid = rowData.borrowerServiceAppDESId;
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1, function(result) {

		if (result) {
			var signTime = result[0];
			var signIp = result[1];
			var href = cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + borrowerServiceAppDESId
					+ "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=" + userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
			var content = '<iframe scrolling="auto" id="openGuaranorCM" frameborder="0"  src="' + href + '" style="width:100%;height:100%;">';
			$("#guaranorCM").dialog({
				content : content,
				title : "担保人资料",
				modal : false,
				maximizable : true,
				closed : true,
				width : 1000,
				height : 350
			});
			// $('#openGuaranorCM')[0].src = href;
			$('#guaranorCM').dialog('open');
		} else {
			$.messager.alert("提示信息", "服务异常，请稍后重试");
		}
	});
}
