$(function() {
	$("#showInfo").menubutton({
		menu : '#groupInfo',
		zindex : 0
	});
	/*
	 * $(".uname").bind('mouseover',function(){ $(".uname").parent().css('background-color','#FFF');
	 * $(this).parent().css('background-color','#A4BED4'); var name=$(this).text(); var offset=$(this).offset(); var left=offset.left; var
	 * top=offset.top; var height=$(this).height(); $("#groupInfo").show(); $("#groupInfo").offset({top:top+height-18,left:left+70}); var
	 * value=$(this).prev().prev().val(); $("#borrowserId").val(value); });
	 */

// $(".uname").hover(function() {
// $(".uname").parent().css('background-color', '#FFF');
// $(this).parent().css('background-color', '#A4BED4');
// var name = $(this).text();
// var offset = $(this).offset();
// var left = offset.left;
// var top = offset.top;
// var height = $(this).height();
// $("#groupInfo").show();
// $("#groupInfo").offset({
// top : top + height - 18,
// left : left + 70
// });
// var value = $(this).prev().prev().val();
// $("#borrowserId").val(value);
// }, function() {
// // $(".uname").parent().css('background-color','#FFF');
// $("#groupInfo").hide();
// });
// $("#groupInfo").hover(function() {
// $(this).parent().css('background-color', '#A4BED4');
// $("#groupInfo").show();
// }, function() {
// $(".uname").parent().css('background-color', '#FFF');
// $("#groupInfo").hide();
// });
	$("#accountInfoId").combobox({
		editable : false,
		width : 400
	});
	/*
	 * $("#groupInfo").click(function(){ $(".uname").parent().css('background-color','#FFF'); $("#groupInfo").hide(); });
	 */
});
// 业务申请表查看
function ruhu1(event) {
	var borrowerServiceAppId = $("#appBorrowerAppId").val();
	var groupNumber = $("#groupNumber1").val();
	var offset = $("#auditPage").offset();
	var left = offset.left;
	var top = offset.top;
	$("#ruhu1").dialog({
		title : "业务申请表",
		closed : true,
		modal : true,
		dosize : true,
		left : left - 15,
		top : top,
		href : server + "/application/showCreditApp.do?borrowerServiceAppId=" + borrowerServiceAppId
	});
	$("#ruhu1").dialog("open");
	stopEvent(event);
}
// 担保人申请表查看
function ruhu2(event, guaRanorId) {
	var offset = $("#auditPage").offset();
	var left = offset.left;
	var top = offset.top;
	$("#ruhu2").dialog({
		title : "担保人申请表",
		closed : true,
		modal : true,
		dosize : true,
		left : left - 15,
		top : top,
		href : server + "/guaranorProfile/guaranorProfileView.do?param=" + guaRanorId
	});
	$("#ruhu2").dialog("open");
	stopEvent(event);
}
// 信用背景及调查报告查看
function contact(event) {
	var creditapplicationId = $("#creditapplicationId").val();
	var groupNumber = $("#groupNumber1").val();
	var creditInvestigationId = $("#creditInvestigatioId").val();
	var offset = $("#auditPage").offset();
	var left = offset.left;
	var top = offset.top;
// $('#contactIFrame')[0].src = server + "creditInvestigation/toAddAndEditPageCreditInvestigationBak.do?creditapplicationId="+ creditapplicationId +
// "&groupNumber="+ groupNumber + "&creditInvestigatioId="+ creditInvestigatioId;
// $('#contactIFrame')[0].src = server + "creditInvestigation/toViewCreditInvestigation.do?creditInvestigationId="+ creditInvestigationId;
	$("#contact").dialog({
		href : server + "creditInvestigation/toViewCreditInvestigation.do?creditInvestigationId=" + creditInvestigationId,
		title : "信用背景调查报告",
		closed : true,
		modal : true,
		dosize : true,
		left : left - 15,
		top : top
	});
	$("#contact").dialog("open");
	stopEvent(event);
}
// 业务资料 接内容管理平台
function others(event, borrowServiceID) {
	var clientid = $("#caDESId").val();
	var titleSTR = "借款人资料";
	if (borrowServiceID != null && borrowServiceID != "") {
		clientid = borrowServiceID;
		titleSTR = "担保人资料";
	}
	// var url =
	// "http://10.100.30.54:8080/CreditCMClient/jsp/creditease/operation/operationControl.jsp?clientId="+clientid+"&systemID=00006&bussTableName=1845ddad2acd637eb7dd8a6ad691208c&userID=chuishou&type=3";
	var url1 = server + "creditapplication2Approval/getDate.do";
	var DESNow;
	var DESIp;
	ajaxSubmit(url1, function(result) {
		DESNow = result[0];
		DESIp = result[1];
	});
	var url = cmUrl + "/operation/transferParameter.action?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow + "&signIp="
			+ DESIp + "";
	$('#openCM')[0].src = url;
// var offset = $("#auditPage").offset();
// var left = offset.left;
// var top = offset.top;
	$("#others").dialog({
		title : titleSTR,
		closed : true,
		modal : true,
		width : 1200,
		height : 400,
		onclose : function() {
			$('#openCM')[0].src = "";
		}
	});
	stopEvent(event);
	$("#others").dialog("open");
}
// 前期审批效果实现所需函数。作用是阻止事件冒泡
function stopEvent(event) {
	if (window.event) {
		event.cancelBubble = true;
	} else {
		event.stopPropagation();
	}
}
// 消息提示框
function showMsg(operate) {
	$.messager.show({
		title : '消息',
		msg : operate + '成功',
		showType : 'slide',
		timeout : 5
	});
}
// 审批通过方法
function aduit() {
    $("#doButton").linkbutton("disable");
    $("#unDoButton").linkbutton("disable");
    var role = $("#role").val();
    if (role == "approve") {
        var url = server + "/creditgroup/getImgAmount.do";
        var creditAppId = $("#cmexam").val();
        ajaxSubmit(url, "post", {
            creditAppId: creditAppId
        }, function (imgamount) {

            if (imgamount == 0) {
                $("#doButton").linkbutton("enable");
                $("#unDoButton").linkbutton("enable");
                $.messager.alert("消息", "请先上传材料清单与审批表");
                return;
            } else if (imgamount < 0) {
                $("#doButton").linkbutton("enable");
                $("#unDoButton").linkbutton("enable");
                $.messager.alert("消息", "上传服务异常,请稍后重试");
                return;
            } else {
                var data = $("#aduit").serialize();
                var url = server + "creditapplication2Approval/updateRlcreditapplication.do";
                var url1 = server + "creditapplication2Approval/isAduited.do";// 后台验证是否可做审批
                ajaxSubmit(url1, data, function (result) {
                    if (!result.success) {
                        $("#doButton").linkbutton("enable");
                        $("#unDoButton").linkbutton("enable");
                        $.messager.alert('提示', result.msg, 'warning');
                        return;
                    } else {
                        if (remarkRequired()) {
                            ajaxSubmit(url, data, function (result) {
                                if (result.success) {
                                    $("#doButton").linkbutton("enable");
                                    $("#unDoButton").linkbutton("enable");
                                    $.messager.show({
                                        showType: "show",
                                        timeout: 1000,
                                        title: "消息",
                                        msg: result.msg
                                    });
                                    closeDialog();
                                } else {
                                    $("#doButton").linkbutton("enable");
                                    $("#unDoButton").linkbutton("enable");
                                    $.messager.show({
                                        showType: "show",
                                        timeout: 1000,
                                        title: "消息",
                                        msg: result.msg
                                    });
                                }
                            });
                        } else {
                            $("#doButton").linkbutton("enable");
                            $("#unDoButton").linkbutton("enable");
                        }
                    }
                });
            }
        }, false);
    } else {
        var data = $("#aduit").serialize();
        var url = server + "creditapplication2Approval/updateRlcreditapplication.do";
        var url1 = server + "creditapplication2Approval/isAduited.do";// 后台验证是否可做审批
        ajaxSubmit(url1, data, function (result) {
            if (!result.success) {
                $.messager.alert('提示', result.msg, 'warning');
                $("#doButton").linkbutton("enable");
                $("#unDoButton").linkbutton("enable");
                return;
            } else {
                if (remarkRequired()) {
                    ajaxSubmit(url, data, function (result) {
                        if (result.success) {
                            $("#doButton").linkbutton("enable");
                            $("#unDoButton").linkbutton("enable");
                            $.messager.show({
                                showType: "show",
                                timeout: 1000,
                                title: "消息",
                                msg: result.msg
                            });
                            closeDialog();
                        } else {
                            $("#doButton").linkbutton("enable");
                            $("#unDoButton").linkbutton("enable");
                            $.messager.show({
                                showType: "show",
                                timeout: 1000,
                                title: "消息",
                                msg: result.msg
                            });
                        }
                    });
                } else {
                    $("#doButton").linkbutton("enable");
                    $("#unDoButton").linkbutton("enable");
                }
            }
        });
    }
}
// 审批拒绝方法
function unaduit() {
    $("#doButton").linkbutton("disable");
    $("#unDoButton").linkbutton("disable");
    var url = server + "/creditgroup/getImgAmount.do";
    var creditAppId = $("#cmexam").val();
    var data = $("#aduit").serialize();
    var url1 = server + "creditapplication2Approval/isAduited.do";// 后台验证是否可做审批
    ajaxSubmit(url1, data, function (result) {
        if (!result.success) {
            $("#doButton").linkbutton("enable");
            $("#unDoButton").linkbutton("enable");
            $.messager.alert('提示', result.msg, 'warning');
            return;
        } else {
            ajaxSubmit(url, "post", {
                creditAppId: creditAppId
            }, function (imgamount) {
                if (imgamount == 0) {
                    $("#doButton").linkbutton("enable");
                    $("#unDoButton").linkbutton("enable");
                    $.messager.alert("消息", "请先上传材料清单与审批表");
                    return;
                } else if (imgamount < 0) {
                    $("#doButton").linkbutton("enable");
                    $("#unDoButton").linkbutton("enable");
                    $.messager.alert("消息", "上传服务异常,请稍后重试");
                    return;
                } else {
                    if (remarkRequired()) {
                        /*2014-07-24 新增：审批拒绝时，选择拒贷原因。*/
                        $("#sub_RefuseReason").val("");
                        $("#sub_showRefuseReason").val("");
                        $("#sub_refuseReasonDialog").dialog({
                            closed: false,
                            width: 500,
                            closable: false,
                            modal: true,
                            height: 200,
                            title: "拒贷原因",
                            buttons: [
                                {
                                    id: "okButton",
                                    text: "确定",
                                    handler: function () {
                                        var refuseReasons = $("#sub_RefuseReason").val();
                                        if (refuseReasons == "" || refuseReasons == null) {
                                            $.messager.alert("消息", "请输入拒贷原因");
                                        } else {
                                            $("#okButton").linkbutton("disable");
                                            $("#noButton").linkbutton("disable");
                                            var creditapplicationId = $("#sub_RefuseReasonCreditapplicationId").val();
                                            $.ajax({
                                                url: serverName + "/RefuseReasonController/refuse.do",
                                                datatype: "json",
                                                data: {creditapplicationId: creditapplicationId, section: 'refuseReason', refuseReasons: refuseReasons, auditStatus: "AR"},
                                                success: function (message) {
                                                    if (message.success) {
                                                        /*$.messager.alert("消息", "操作成功");
                                                         $("#okButton").linkbutton("enable");
                                                         $("#noButton").linkbutton("enable");
                                                         $("#sub_refuseReasonDialog").dialog("close");*/
                                                        $("#okButton").linkbutton("enable");
                                                        $("#noButton").linkbutton("enable");
                                                        $("#sub_refuseReasonDialog").dialog("close");
                                                        var url = server + "creditapplication2Approval/unUpdateRlcreditapplication.do";
                                                        ajaxSubmit(url, data, function (result) {
                                                            if (result.success) {
                                                                $("#doButton").linkbutton("enable");
                                                                $("#unDoButton").linkbutton("enable");
                                                                $.messager.show({
                                                                    showType: "show",
                                                                    timeout: 1000,
                                                                    title: "消息",
                                                                    msg: result.msg
                                                                });
                                                                closeDialog();
                                                            } else {
                                                                $("#doButton").linkbutton("enable");
                                                                $("#unDoButton").linkbutton("enable");
                                                                $.messager.show({
                                                                    showType: "show",
                                                                    timeout: 1000,
                                                                    title: "消息",
                                                                    msg: result.msg
                                                                });
                                                            }
                                                        });
                                                    } else {
                                                        $("#okButton").linkbutton("enable");
                                                        $("#noButton").linkbutton("enable");
                                                        $.messager.alert("消息", "操作失败");
                                                    }
                                                }
                                            });
                                        }

                                    }
                                },
                                {
                                    id: "noButton",
                                    text: "取消",
                                    handler: function () {
                                        $("#sub_RefuseReason").val("");
                                        $("#sub_showRefuseReason").val("");
                                        $("#sub_refuseReasonDialog").dialog("close");
                                        $("#doButton").linkbutton("enable");
                                        $("#unDoButton").linkbutton("enable");
                                    }
                                }
                            ]
                        });
                    } else {
                        $("#doButton").linkbutton("enable");
                        $("#unDoButton").linkbutton("enable");
                    }
                }
            }, false);
        }
    });
}
// 更改金额时验证数据是否合法。规则：产品上限>变更额度>产品下限>0
function validateAmount(obj) {
	var amount = $("#amount").val();
	var chgamount = $("#chgamount");
	var capitalLowerLimit = $("#capitalLowerLimit").val();
	if (!(eval(obj.value) > eval(0))) {
		$.messager.alert('提示', '请输入大于0的数', 'warning');
		$("#doButton").linkbutton('disable');
		$("#unDoButton").linkbutton('disable');
		return;
	}
	if ((amount - obj.value < 0) || (capitalLowerLimit - obj.value > 0)) {
		$.messager.alert('提示', '变更金额不能高于申请金额，不能低于产品额度下限！' + '申请金额：' + amount + ';变更金额：' + obj.value + ';产品额度下限：' + capitalLowerLimit, 'warning');
		$("#doButton").linkbutton('disable');
		$("#unDoButton").linkbutton('disable');
		return;
	}
	$("#doButton").linkbutton('enable');
	$("#unDoButton").linkbutton('enable');
}
// 接现金流
function showCashFlowStreamInput(event) {
	var creditapplicationId = $("#creditapplicationId").val();
	var dd1 = $("#cashFlow").dialog({
		title : "现金流入流出表",
		modal : true,
		closed : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		draggable : true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#cashFlow").dialog('close');
			}
		} ],
		href : server + "/jsp/rc/business/cashStreamInputBak.jsp",
		onLoad : function() {
			$("#saveAllCashStreams").attr("disabled", true);
			ajaxSubmit(server + "cashStream/getCashStreamVoByCreditAppId.do", {
				creditapplicationId : creditapplicationId
			}, function(result) {
				if (undefined != result) {
					$("#cashStreamForm").form("load", result);
					$("#creatDate").val(result.csOperInTotal.createDate);
					$("#borrowerName").val(result.csOperInTotal.borrowerName);
					$("#saveAllCashStreams").attr("disabled", false);
					$(".datagrid-mask").remove();
					$(".datagrid-mask-msg").remove();
				}
			});
			ajaxSubmit(serverName + "/cashStream/getProductInfo.do", {
				creditapplicationId : creditapplicationId
			}, function(a) {
				$("#repaymentPlanNamehq").val(a.repaymentPlanName);
			});
		}
	});
	stopEvent(event);
	dd1.dialog('open');
}
// 接现金流工作表
function showCashFlowWorkTable(event) {
	var creditapplicationId = $("#creditapplicationId").val();
	var dd1 = $("#cashStream").dialog({
		title : "现金流工作表",
		modal : true,
		closed : true,
		inline : false,
		width : 1000,
		height : 470,
		border : false,
		draggable : true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#cashStream").dialog('close');
			}
		} ],
		href : server + "/jsp/rc/business/cashStreamWorkTableInputBak.jsp",
		onLoad : function() {
			$("#everyDayOperCsInFormBtn").attr("disabled", true);
			$("#everyWeekOperCsInFormBtn").attr("disabled", true);
			$("#everyMonthOperCsInFormBtn").attr("disabled", true);
			$("#everyCostOperCsInFormBtn").attr("disabled", true);
			$("#avgIncRateFormBtn").attr("disabled", true);
			$("#purchaceCostsFormBtn").attr("disabled", true);
			ajaxSubmit(server + "cashStream/getCashStreamWorkTableVo.do", {
				creditapplicationId : creditapplicationId
			}, function(result) {
				$("#everyDayOperCsInForm").form("load", result);
				$("#everyWeekOperCsInForm").form("load", result);
				$("#everyMonthOperCsInForm").form("load", result);
				$("#everyCostOperCsInForm").form("load", result);
				$("#avgIncRateForm").form("load", result);
				$("#purchaceCostsForm").form("load", result);
				// 启用保存按钮
				$("#everyDayOperCsInFormBtn").attr("disabled", false);
				$("#everyWeekOperCsInFormBtn").attr("disabled", false);
				$("#everyMonthOperCsInFormBtn").attr("disabled", false);
				$("#everyCostOperCsInFormBtn").attr("disabled", false);
				$("#avgIncRateFormBtn").attr("disabled", false);
				$("#purchaceCostsFormBtn").attr("disabled", false);
				// 删除加载效果
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();

			});
		}
	});
	stopEvent(event);
	dd1.dialog('open');
}

// 上传材料清单与审批表
function showUploadDig2Approval() {
	var clientid = $("#caDESId1").val();
	// var href =
	// cmUrl+"/jsp/creditease/operation/operationControl.jsp?clientId="+clientid+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=yinongdai&type=3&signTime="+DESNow+"&signIp="+DESIp+"";
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1, function(result) {
		if (result) {
			var signTime = result[0];
			var signIp = result[1];
			var href = cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID="
					+ userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
			var content = '<iframe scrolling="auto" id="openCM1" frameborder="0"  src="' + href + '" style="width:100%;height:100%;">';
			$("#cm1").dialog({
				content : content,
				title : "材料清单与审批表",
				modal : false,
				maximizable : true,
				closed : true,
				width : 1000,
				height : 350
			});
			// $('#openCM')[0].src = href;
			$('#cm1').dialog('open');
		} else {
			$.messager.alert("提示信息", "服务异常，请稍后重试");
		}
	});
}

function submitAuditingReturn1(imgamount) {
	if (imgamount == 0) {
		$.messager.alert("消息", "请先上传材料清单与审批表");
		return false;
	} else if (imgamount < 0) {
		$.messager.alert("消息", "上传服务异常,请稍后重试");
		return false;
	}
}

// 补充资料 已去掉这个业务
function addinfo() {
	var url = "creditapplication2Approval/addinfo.do";
	var data = $("#aduit").serialize();
	ajaxSubmit(url, data, function() {
		showMsg("操作");
		closeDialog();
	});
}

//拒贷
function sub_showRefuseReasonDiv(valueField, textField) {
    //原因
    var reasons = $("#sub_RefuseReason").val();
    var reasonsArray = reasons.split(",");
    ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {section: "refuseReasonTotal", q: ""}, function (result) {
        var table = document.getElementById("sub_refuseReasonTable");
        var pchildren = table.childNodes;
        ;
        //清空表中的行和列
        for (var a = pchildren.length - 1; a >= 0; a--) {
            table.removeChild(pchildren[a]);
        }
        //判断颜色的
        var m = 0;
        for (var k = 0; k < result.refuseReasonTotal.length; k++) {
            var index = result.refuseReasonTotal[k].codaTableId;
            var name = "refuseReasonTotal" + index;
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
                    cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>" + result.refuseReasonTotal[k].codeVlue + "</font>";
                    cell1.setAttribute("rowspan", length);
                    cell1.setAttribute("align", "center");
                    cell1.setAttribute("width", "150");
                    cell1.setAttribute("bgcolor", "#DDE4EE");
                    tr.appendChild(cell1);
                }


                for (var j = 0; j < 3; j++) {
                    var cell = document.createElement("td");
                    cell.setAttribute("width", "360");
                    n = n + 1;
                    if (m % 2 == 0) {
                        cell.setAttribute("bgcolor", "#DDE4EE");
                    }
                    if (object[n - 1] != undefined) {
                        $("#sub_RefuseReasonSection").val(object[n - 1].sub_section);
                        var key = object[n - 1].codeKey;
                        var value = object[n - 1].codeVlue;
                        var arrayObj = new Array(key, value);
                        var obj = new Object();
                        obj["codeKey"] = object[n - 1].codeKey;
                        obj["codeValue"] = object[n - 1].codeVlue;
                        for (var l = 0; l < reasonsArray.length; l++) {
                            if (obj.codeKey == reasonsArray[l]) {
                                cell.innerHTML =
                                    "<input type='checkbox' checked='checked' name='refuseReason' value='" + obj.codeKey + "' />" + object[n - 1].codeVlue;
                                break;
                            } else {
                                cell.innerHTML =
                                    "<input type='checkbox' name='refuseReason' value='" + obj.codeKey + "' />" + object[n - 1].codeVlue;
                            }
                        }

                        //"<a href='javaScript:addRefuseReason(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
                    }
                    tr.appendChild(cell);
                }
                table.appendChild(tr);
            }
        }
        $("#sub_refuseReasonDiv").dialog({
            width: 950,
            height: 30 * (Math.ceil(result.length / 3)),
            title: "拒贷原因",
            minimizable: false,
            maximizable: false,
            collapsible: false,
            modal: true,
            buttons: [
                {
                    id: "refuseButton",
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                        addRefuseReason();
                    }
                }
            ]
        });
    });
}

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
    $("#sub_showRefuseReason").val(refuseResionValue);
    $("#sub_RefuseReason").val(refuseResionKey);
    $("#sub_RefuseReason").focus();
    $("#sub_refuseReasonDiv").dialog("close");
}

//验证审批意见（备注）必填
function remarkRequired() {
    var validFunc = function (content, proposalOrRemark) {
        if (!content) {
            $.messager.alert("消息", "请填写" + proposalOrRemark);
            return false;
        } else if (content.length > 200) {
            $.messager.alert("消息", proposalOrRemark + "最多可输入200字");
            return false;
        } else {
            return true;
        }
    }
    var ret = true;
    var r = $.trim($('#borrRemark').val());
    var role = $("#role").val();
    if (role == 'approve' || role == 'fxapprove') {
        ret = validFunc(r, "审批意见");
    } else {
        ret = validFunc(r, "备注");
    }
    return ret;
}
