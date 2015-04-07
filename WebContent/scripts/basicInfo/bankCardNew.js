$(function() {

	wpd = window.parent.document;
	wp = window.parent;
	creditapplicationId = $(wpd).find("input[id='creditapplicationId']").val();
	/** 以下为修改账户信息新增方法 */
	var dic = new DataDictionary();
	dic.addDic("cardFlag_cardFlag_Y", "cardFlag");
	dic.addDic("onUsed_onUsed_Y", "onUsed");
	dic.addDic("bankNum_bankNum_Y", "bankNum");
	dic.addDic("useType_useType_Y", "useType");
	dic.dicAjax();
	// easyui-validatebox拓展验证
	$.extend($.fn.validatebox.defaults.rules, {
		// 验证数字
		number : {
			validator : function(value, param) {
				if (value) {
					return /^[1-9][0-9]*(\.[0-9]+)?$/.test(value);
				} else {
					return true;
				}
			},
			message : '只能输入非0开头的数字.'
		},
		date : {
			validator : function(value, param) {
				if (value) {
					return /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/.test(value);
				} else {
					return true;
				}
			},
			message : '请选择日期.'
		},
		select : {
			validator : function(value, param) {
				if (value == "请选择") {
					return false;
				} else {
					return true;
				}
			},
			message : '请选择'
		}
	});

	$("#departmentId").combobox({
		url : serverName + "/accountInfo/getDepartmentList.do",
		textField : 'departmentName',
		valueField : 'departmentId'
	});

	ajaxSubmit(serverName + "/accountInfo/selectByCrediApplicationId.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function(row) {
		//$("#accountInfo").form("load", row);
		showCityCombo("provinceId1", "cityId1", "districtId1");
		showCodeTable();
		//$("#departmentId").combobox("setValue", $("#departmentId").combobox("getValue"));
		url = serverName + "/accountInfo/addOrUpdate.do";
		var defultAcount = $("#accountName").combobox("getValue");
		$("#accountName").combobox({
			width : 153,
			url : serverName + "/creditgroup/selectBorrowerFamilyByID.do?credentialsNumber=" + $("#borrowerCredentialsNumberLook").val(),
			mode : 'remote',
			editable : false,
			required : true,
			valueField : "name",
			textField : "name",
			value : defultAcount,
			onSelect : function(data) {
				$("#credentialsNumber").val(data.credentialsNumber);
			}
		});
		$("#accountInfo").form("validate");
	});

	/** 以上为修改账户信息新增方法 */
});

/** 以下为修改账户信息新增方法 */
// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
function showCityCombo(province, city, district) {
	var provinceid = $("#provinceId1").combobox("getValue");

	var province = $("#" + province).combobox({
		// required : true,
		editable : false,
		url : serverName + '/NSC/list.do',
		textField : 'cityName',
		valueField : 'cityCode',
		mode : 'remote',
		delay : 500,
		width : '150',
		value : provinceid,
		onSelect : function(value) {
			$("#" + city).combobox({
				editable : false,
				url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
				textField : 'cityName',
				valueField : 'cityCode',
				mode : 'remote',
				delay : 500,
				width : '150',
				value : '',
				onSelect : function(value) {
					$("#" + district).combobox({
						editable : false,
						url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						width : '150',
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
			width : '150',
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
			width : '150',
			value : districtId
		});
	}

}
function showCodeTable() {
	var bankNum = $("#bankNum").combobox("getValue");
}
function showUpdateAcountInfo() {
	$("#showForm").dialog('open').dialog('setTitle', '修改账号信息');
// ajax请求得到卡信息
	ajaxSubmit(serverName + "/accountInfo/selectByCrediApplicationId.do", {
		creditapplicationId : $("#creditapplicationId").val()
	}, function(row) {
		//$("#accountInfo").form("load", row);
		showCityCombo("provinceId1", "cityId1", "districtId1");
		showCodeTable();
		//$("#departmentId").combobox("setValue", $("#departmentId").combobox("getValue"));
		url = serverName + "/accountInfo/addOrUpdate.do";
		var defultAcount = $("#accountName").combobox("getValue");
		$("#accountName").combobox({
			width : 153,
			url : serverName + "/creditgroup/selectBorrowerFamilyByID.do?credentialsNumber=" + $("#borrowerCredentialsNumberLook").val(),
			mode : 'remote',
			editable : false,
			required : true,
			valueField : "name",
			textField : "name",
			value : defultAcount,
			onSelect : function(data) {
				$("#credentialsNumber").val(data.credentialsNumber);
			}
		});
		$("#accountInfo").form("validate");
	});

}
/** 以上为修改账户信息新增方法 */
/** 判断有没有上传附件 */

function save() {
	$("#bankRank").val($("#bankNum").combobox("getText"));
	if ($("#accountInfo").form("validate")) {
		ajaxSubmit(serverName + "/accountInfo/getImgAmountByCreditAccountCard.do", {
			creditAppId : $("#creditapplicationId").val()
		}, function(r) {
			if (r) {
				if (r.imgAmount <= 0) {
					$.messager.alert("消息", "请先上传资料");
				} else {
					wp.buttonDisable();
					ajaxSubmit(serverName + "/accountInfo/saveModifyCatdApp.do", $("#accountInfo").serialize(), function(b) {
						wp.buttonEnable();
						wp.messagerShow(b.msg);
						wp.closeRegistrationDialog();
					});
				}
			} else {
				$.messager.alert("消息", "上传服务异常,请稍后重试");
			}
		});
	} else {
		$.messager.alert("消息", "请将数据填完整！");
	}
}

function textCount(textId, htmlId, max) {
	$("#" + textId).keyup(function() {
		var maxl = max;
		var tishi = "还可以输入" + maxl + "个字";
		$("#" + htmlId).html(tishi);
		var xianyou = $(this).val().length;
		var keyi = maxl - xianyou;
		var tishi = "还可以输入" + keyi + "个字";
		if (xianyou > (max - 1)) {
			var tishi = "还可以输0个字";
			$("#" + htmlId).css({
				"color" : "red"
			});
			var s = $("#" + textId).val().substr(0, 100);
			$("#" + textId).val();
		} else {
			$("#" + htmlId).css({
				"color" : "#000"
			});
		}
		$("#" + htmlId).html(tishi);
	});
}