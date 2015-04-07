// 声明公共变量
var urgeContactsSize = 1;
$(function() {
	var urgeAdd = $("#urgeAdd").val();
	var dic = new DataDictionary();
	if (urgeAdd == 1) {
		// 渲染所有下拉框的值的方法
		dic.addDic("urgeSummarize_Y", "urgesummarize");
		dic.addDic("urgeWay_Y", "urgeway");
		dic.addDic("urgeState_Y", "urgestate");
		dic.addDic("moneySource", "moneysource");
		dic.addDic("urgeRemark_Y", "urgeremark");
		dic.addDic("bigWarning", "bigwarning");
		dic.addDic("ynPromise_Y", "ynpromise");
		dic.addDic("relation0_Y", "moneysource"); // 金额来源和联系人关系是一样值
	} else {
		dic.addDic("urgeSummarize", "urgesummarize");
		dic.addDic("urgeWay", "urgeway");
		dic.addDic("urgeState", "urgestate");
		dic.addDic("moneySource", "moneysource");
		dic.addDic("urgeRemark", "urgeremark");
		dic.addDic("bigWarning", "bigwarning");
		dic.addDic("ynPromise", "ynpromise");
		dic.addDic("relation0", "moneysource"); // 金额来源和联系人关系是一样值
	}
	dic.dicAjax();
	if (urgeAdd == 1) {
		$("#hours").numberbox({
			required : true
		});
		$("#currentPromiseMoney").numberbox({
			required : true
		});
	}
	$("#urgeSummarize").combobox({
		panelHeight : 200
	});

	$("#minutes").combobox({
		data : [ {
			id : "00",
			text : "00"
		}, {
			id : "15",
			text : "15"
		}, {
			id : "30",
			text : "30"
		}, {
			id : "45",
			text : "45"
		} ],
		valueField : "id",
		textField : "text",
		height : "auto",
		editable : false,
		width : 50
	});
	// var selectFrom=$("#SelectsubUrgeForm input[id^='relation']");
	$("#urgeSummarize").combobox({
		onSelect : function() {
			var urgeSummarize = $("#urgeSummarize").combobox("getValue");
			if (urgeSummarize == 6) {
				$("#urgeWay").combobox({
					required : false,
					validType : ""
				});
				$("#urgeWay").combobox("validate");

				$("#hours").numberbox({
					required : false,
					validType : ""
				});
				$("#hours").numberbox("validate");

				$("#currentPromiseMoney").numberbox({
					required : false
				});
				$("#currentPromiseMoney").numberbox("validate");

				$("#currentPromiseTime").datebox({
					required : false
				});
				$("#currentPromiseTime").datebox("validate");

				// $("#urgeListContact0Name").validatebox({required:false});
				// $("#urgeListContact0Name").validatebox("validate");

				/*
				 * $("#urgeDate").combobox({ required : false, validType : "" }); $("#urgeDate").combobox("validate");
				 */

				$("#moneySource").combobox({
					required : false,
					validType : ""
				});
				$("#moneySource").combobox("validate");

				$("#urgeState").combobox({
					required : false,
					validType : ""
				});
				$("#urgeState").combobox("validate");

				$("#minutes").combobox({
					required : false,
					validType : ""
				});
				$("#minutes").combobox("validate");

				$("#urgeRemark").combobox({
					required : false,
					validType : ""
				});
				$("#urgeRemark").combobox("validate");

				$("#ynPromise").combobox({
					required : false,
					validType : ""
				});
				$("#ynPromise").combobox("validate");

				$("input[name$='borrowerRelation']").removeAttr("required");

				var relation = $("#relationOne input[id^='relation']");
				$.each(relation, function(i, val) {
					$("#" + val.id + "").combobox({
						required : false,
						validType : ""
					});
				});
				var urgeLinkName = $("input[name$='urgeLinkName']");
				$.each(urgeLinkName, function(i, val) {
					$("#" + val.id + "").validatebox({
						required : false
					});
					$("#" + val.id + "").validatebox("validate");
				});
			}
			if (urgeSummarize != 6) {
				$("#urgeWay").combobox({
					required : true,
					validType : "selectValueRequired['urgeWay','请选择']"
				});
				$("#urgeWay").combobox("validate");

				if ($("#reginMoney").val() != 0.0 && $("#reginMoney").val() != null) {
					$("#moneySource").combobox({
						required : true,
						validType : "selectValueRequired['moneySource','请选择']"
					});
					$("#moneySource").combobox("validate");
				}

				$("#urgeState").combobox({
					required : true,
					validType : "selectValueRequired['urgeState','请选择']"
				});
				$("#urgeState").combobox("validate");

				$("#minutes").combobox({
					required : true,
					validType : "selectValueRequired['minutes','请选择']"
				});
				$("#minutes").combobox("validate");

				$("#urgeRemark").combobox({
					required : true,
					validType : "selectValueRequired['urgeRemark','请选择']"
				});
				$("#urgeRemark").combobox("validate");

				$("#ynPromise").combobox({
					required : true,
					validType : "selectValueRequired['ynPromise','请选择']"
				});
				$("#ynPromise").combobox("validate");

				var relation = $("#relationOne input[id^='relation']");

				$.each(relation, function(i, val) {
					$("#" + val.id + "").combobox({
						required : true,
						validType : "selectValueRequired['ynPromise','请选择']"
					});
				});

				var urgeLinkName = $("input[name$='urgeLinkName']");
				$.each(urgeLinkName, function(i, val) {
					$("#" + val.id + "").validatebox({
						required : true
					});
					$("#" + val.id + "").validatebox("validate");
				});

				$("#hours").numberbox({
					required : true
				});
				$("#hours").numberbox("validate");

				$("#currentPromiseMoney").numberbox({
					required : true
				});
				$("#currentPromiseMoney").numberbox("validate");

				$("#currentPromiseTime").datebox({
					required : true
				});
				$("#currentPromiseTime").datebox("validate");

				/*
				 * $("#urgeDate").combobox({ required : true }); $("#urgeDate").combobox("validate");
				 */
			}
		}
	});

	// 获取催收日期
	$("#urgeDate").combobox({
		url : serverName + "/urgeController/dateCombo.do",
		valueField : "code",
		textField : "value",
		editable : false,
		validType : 'selectValueRequired["urgeDate","请选择"]',
		onSelect : function(record) {
			if ("" != record.code && record.code != null) {
				ajaxSubmit(serverName + "/urgeController/getBreakbegindateSave.do", {
					urgeDateString : record.code,
					creditapplicationId : $("#urgeCreditapplicationId").val()
				}, function(r) {
					if (r.success == "true") {
						$("#breakbegindate").val(r.breakbegindateSave);
						$("#repaymentCycle").val(r.repaymentCycle);
					} else {
						$("#urgeDate").combobox("setValue", "");
						$("#breakbegindate").val("");
						$("#repaymentCycle").val("");
						$.messager.alert("消息", "违约开始日期为空！");
					}
				});
			} else {
				$("#breakbegindate").val("");
				$("#repaymentCycle").val("");
			}
			var urgeDate = $("#urgeDate").combobox("getValue");
			var previousPromiseTime = $("#currentPromiseTime").datebox("getValue");
			if (urgeDate != null && previousPromiseTime != "") {
				if (previousPromiseTime < urgeDate) {
					$.messager.alert("消息", "时间选择有误！承诺时间应该大于等于催收时间。请先修改承诺时间！");
					$("#urgeDate").combobox("setValue", "");
					return;
				}
			}
		}
	});
	// 判断本次承诺还款时间是否小于催收时间
	$("#currentPromiseTime").datebox({
		onSelect : function() {
			var urgeDate = $("#urgeDate").combobox("getValue");
			var previousPromiseTime = $("#currentPromiseTime").datebox("getValue");
			if (urgeDate != null && previousPromiseTime != "") {
				if (previousPromiseTime < urgeDate) {
					$.messager.alert("消息", "时间选择有误！承诺时间应该大于等于催收时间。");
					$("#currentPromiseTime").datebox("setValue", "");
					return;
				}
			}
		}
	});

	// 判断本次承诺还款时间是否小于催收时间
	// $("#urgeDate").combobox({
	//
	// });
	$("#subUrgeForm").form("validate");
	$("#relationOne").form("validate");

});

// 清空新增催收内容
function clearText() {
	
	$("#urgeWay").combobox({
		required : true,
		validType : "selectValueRequired['urgeWay','请选择']"
	});
	$("#urgeWay").combobox("validate");

	if ($("#reginMoney").val() != 0.0 && $("#reginMoney").val() != null) {
		$("#moneySource").combobox({
			required : true,
			validType : "selectValueRequired['moneySource','请选择']"
		});
		$("#moneySource").combobox("validate");
	}

	$("#urgeState").combobox({
		required : true,
		validType : "selectValueRequired['urgeState','请选择']"
	});
	$("#urgeState").combobox("validate");

	$("#minutes").combobox({
		required : true,
		validType : "selectValueRequired['minutes','请选择']"
	});
	$("#minutes").combobox("validate");

	$("#urgeRemark").combobox({
		required : true,
		validType : "selectValueRequired['urgeRemark','请选择']"
	});
	$("#urgeRemark").combobox("validate");

	$("#ynPromise").combobox({
		required : true,
		validType : "selectValueRequired['ynPromise','请选择']"
	});
	$("#ynPromise").combobox("validate");

	var relation = $("#relationOne input[id^='relation']");

	$.each(relation, function(i, val) {
		$("#" + val.id + "").combobox({
			required : true,
			validType : "selectValueRequired['ynPromise','请选择']"
		});
	});

	var urgeLinkName = $("input[name$='urgeLinkName']");
	$.each(urgeLinkName, function(i, val) {
		$("#" + val.id + "").validatebox({
			required : true
		});
		$("#" + val.id + "").validatebox("validate");
	});

	$("#hours").numberbox({
		required : true
	});
	$("#hours").numberbox("validate");

	$("#currentPromiseMoney").numberbox({
		required : true
	});
	$("#currentPromiseMoney").numberbox("validate");

	$("#currentPromiseTime").datebox({
		required : true
	});
	$("#currentPromiseTime").datebox("validate");

	/*
	 * $("#urgeDate").combobox({ required : true }); $("#urgeDate").combobox("validate");
	 */
	
	
	
	
	
	$("#urgeSummarize").combobox("setValue", "");
	$("#urgeWay").combobox("setValue", "");
	$("#urgeState").combobox("setValue", "");
	$("#moneySource").combobox("setValue", "");
	$("#urgeRemark").combobox("setValue", "");
	$("#bigWarning").combobox("setValue", "");
	$("#ynPromise").combobox("setValue", "");

	$("#reginMoney").val("");
	$("#hours").val("");
	$("#minutes").combobox("setValue", "");
	$("#urgeDate").combobox("setValue", "");
	$("#urgeDescribe").val("");
	$("#currentPromiseMoney").val("");
	$("#currentPromiseTime").datebox("setValue", "");

	for ( var i = 0; i < urgeContactsSize; i++) {
		$("#urgeListContact" + i + "Name").val("");
		$("#relation" + i).combobox("setValue", "");
		$("#urgeList" + i + "telephone").val("");
	}
	$("#subUrgeForm").form("validate");
	$("#relationOne").form("validate");
}

// 催款列表信息 luohongjie
// 声明一个变量接收信贷申请ID
var creditApplicationID1;
// 动态添加催收联系人信息一行
function addUrgeContacts() {
	var urgeContactsTable = document.getElementById("urgeContacts");
	var tr = document.createElement("tr");
	tr.width = "100%";
	var size = urgeContactsSize;
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	var td5 = document.createElement("td");
	var td6 = document.createElement("td");
	var td7 = document.createElement("td");
	// var td8 = document.createElement("td");
	td1.innerHTML = '催收联系人姓名：';
	td2.innerHTML = '<input type="text" id="urgeListContact' + size + 'Name" name="urgeLinkmans[' + size + '].urgeLinkName" style="width:135px" />';
	td3.innerHTML = '和申请人关系：';
	td4.innerHTML = '<input id="relation' + size + '"  name="urgeLinkmans[' + size + '].borrowerRelation" style="width:135px"/>';
	td5.innerHTML = '联系电话：';
	td6.innerHTML = '<input type="text" id="urgeList' + size + 'telephone" name="urgeLinkmans[' + size
			+ '].linkTelephone"  class="easyui-validatebox" validType="phoneNumber" maxlength="11"  style="width:123px" />';
	td7.innerHTML = '<a  iconCls="icon-remove" id="urgeList' + size + 'but"  class="easyui-linkbutton" onclick="$(this).parent().parent().remove()"></a>';
	// td8.innerHTML='<input value="'+(size+1)+'"
	// name="familymemberList['+size+'].seq" type="hidden"/>';
	td1.width = "115px";

	td7.width = "70px;";
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	tr.appendChild(td6);
	tr.appendChild(td7);
	// tr.appendChild(td8);
	urgeContactsTable.appendChild(tr);
	var urgeSummarize = $("#urgeSummarize").combobox("getValue");
	if (urgeSummarize != 6) {

		var dicRelation = new DataDictionary();
		dicRelation.addDic("relation" + size + "_Y", "moneysource");
		dicRelation.dicAjax();
		$("#urgeListContact" + size + "Name").validatebox({
			required : true
		});
		$("#urgeListContact" + size + "Name").validatebox("validate");
		$("#urgeList" + size + "telephone").validatebox();
		$("#urgeList" + size + "but").linkbutton({
			iconCls : "icon-remove"
		});
	} else {
		var dicRelation = new DataDictionary();
		dicRelation.addDic("relation" + size, "moneysource");
		dicRelation.dicAjax();
		$("#urgeListContact" + size + "Name").validatebox({
			required : false
		});
		$("#urgeListContact" + size + "Name").validatebox("validate");
		$("#urgeList" + size + "telephone").validatebox();
		$("#urgeList" + size + "but").linkbutton({
			iconCls : "icon-remove"
		});
	}
	urgeContactsSize = urgeContactsSize + 1;
}
// 保存
function save() {
	// 验证
	if ($("#urgeDialog").form('validate')) {
		// 禁用保存按钮
		window.parent.buttonUrgedisDisable();
		// ajax
		var data1 = $("#subUrgeForm").serialize();
		var data2 = $("#relationOne").serialize();

		ajaxSubmit(serverName + "/urgeController/saveUrgeAndLinkmanVo.do", $("#subUrgeForm").serialize() + "&" + $("#relationOne").serialize(), function(r) {
			// 关闭dialog
			// 提示
			if (r.success) {
				window.parent.buttonUrgedisEnable();
				parent.messageSuccess("保存成功！");
				parent.closeMyDialog("urgeDialog", "urgeDialog");
				// 调用清空from表单方法
				$("#subUrgeForm").dialog("clearSelections");
			} else {
				window.parent.buttonUrgedisEnable();
				parent.messageFail("保存失败！");
				parent.closeMyDialog("urgeDialog", "urgeDialog");
				// 调用清空from表单方法
				$("#subUrgeForm").dialog("clearSelections");
			}

		});
	} else {
		$.messager.alert("消息", "请填写完整信息！", "warning");
		window.parent.buttonUrgedisEnable();
	}
}

// 当收回金额不为0的时候，金额来源的下拉框为必填
function moneyIsNot0() {
	if ($("#reginMoney").val() != 0.0 && $("#reginMoney").val() != null && $("#urgeSummarize").combobox("getValue") != 6) {
		var dic = new DataDictionary(); 
		dic.addDic("moneySource_Y", "moneysource");
		dic.dicAjax();

	} else {
		$("#moneySource").combobox({
			required : false,
			validType : ""
		});
		$("#moneySource").combobox("validate");
	}
}

/*
 * function moneyIs0(){ dic.addDic("moneySource", "moneysource"); dic.dicAjax(); }
 */
// 数字框的验证
function reginMoneyNum() {
	$("#reginMoney").numberbox({
		precision : 1
	});
}
// function HHNum() {
// $("#hours").numberbox({
// min : 0,
// max : 99
// });
// }
/*
 * //上次承诺还款金额 function previousPromiseMoneyNum() { $("#previousPromiseMoney").numberbox({ min : 0, precision : 1 }); }
 */
// function currentPromiseMoneyNum() {
// $("#currentPromiseMoney").numberbox({
// min : 0,
// precision : 1
// });
// }
