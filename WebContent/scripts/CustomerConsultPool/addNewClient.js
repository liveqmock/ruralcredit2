// 删除申请人列表中的一行
function delApplyInfo() {
	$.messager.confirm('提示', '确认删除申请人信息?', function(r) {
		if (r) {
			// 删除人法网结果
			var rowIndex = $('#commonCourtSearch').datagrid('getRows');
			if (rowIndex) {
				for ( var i = rowIndex.length - 1; i >= 0; i--) {
					var index = $('#commonCourtSearch').datagrid('getRowIndex', rowIndex[i]);
					$('#commonCourtSearch').datagrid('deleteRow', index);
				}
			}
			$('#commonCourtSearch').datagrid('loadData', {
				total : 0,
				rows : []
			});

			var index = $('#commonApply').datagrid('getRowIndex');
			$('#commonApply').datagrid('deleteRow', index);
			isQueryCourt = false;
			queryResultFlag = "";
		}
	});
}

// 保存申请人和人法网信息
function saveApplyPersonInfo() {
	$.messager.confirm('提示信息', '是否需要继续执行?', function(r) {
		if (r) {
			if (!isQueryCourt) {
				$.messager.alert('操作提示', '请先进行人法网查询！', 'error');
				return false;
			}
			disableOptBtn();
			var appLen = $('#commonApply').datagrid('getRows').length;
			var courtLen = $('#commonCourtSearch').datagrid('getRows').length;
			var appJson;
			var courtJson;
			var params = '';
			if (appLen > 0) {
				var appRow = $('#commonApply').datagrid('getRows');
				var obj = new Object();
				obj.appInfoName = appRow[0].appInfoName;
				obj.identityCard = appRow[0].identityCard;
				obj.appInfoAge = appRow[0].appInfoAge;
				obj.appInfoSex = appRow[0].appInfoSex;
				obj.courtQueryResultFlag = queryResultFlag;
				// js obj 转化为js str
				appJson = JSON.stringify(obj);
				if (courtLen > 0) {
					var courtRow = $('#commonCourtSearch').datagrid('getRows');
					courtJson = JSON.stringify(courtRow);
				}
				/*************************************************************************************************************************************
				 * if(appJson!=undefined){ params +='strinfo='+appJson; if(courtJson!=undefined){ params +='&strArrested='+courtJson; } }
				 ************************************************************************************************************************************/

				// 防止重复提交禁用保存按钮
				// $('#btnSave').linkbutton('disable');
				$.ajax({
					url : serverName + '/acceptAdviceController/saveApplyPerson.do',
					data : {
						customerConsultId : $("#customerConsultId").val(),
						consultPoolId : $("#consultPoolId").val(),
						strInfo : appJson,
						strArrested : courtJson
					},
					type : 'POST',
					success : function(o) {
						// $('#btnSave').linkbutton('enable');
						var h = "a";
						parent.$.messager.show({
							title : "操作提示",
							msg : "保存成功",
							showType : 'slide',
							timeout : 5000
						});

						parent.$("#inquire_pool_office").datagrid("reload");
						parent.$("#inquire_pool_office").datagrid({
							onLoadSuccess : function() {
								if (null != h && h == "a") {
// setTimeout(function(){
									window.parent.parent.addTabFunRefresh({
										id : '',
										title : '编辑客户申请' + o,
										src : serverName + '/creditApplyController/getAllCreditApplyInfo.do?customerConsultid=' + $("#customerConsultId").val() + '&applicationId='
									});
// },1000);
								}
								h = "b";
							}
						});
						parent.$('#addNewClientDiv').dialog('close');

						/*
						 * window.parent.parent.addTabFunRefresh({ id : '客户咨询列表', title : '客户咨询列表', src :
						 * getRootPath()+'/jsp/customerConsult/inquire_list.jsp' });
						 */

						// 跳转到编辑客户申请第一页

					}
				});
			} else {
				$.messager.alert('操作提示', '请添加申请人', 'error');
			}
			enableOptBtn;
		}
	});
}

function ddd() {
	window.parent.parent.addTabFunRefresh({
		id : '',
		title : '编辑客户申请' + o,
		src : serverName + '/creditApplyController/getAllCreditApplyInfo.do?customerConsultid=5003294&applicationId='
	});
}
// 添加人法网查询结果
var isQueryCourt = false;
var queryResultFlag = "";
function addCourtSearchInfo(name, codeNum) {
	$.ajax({
		type : 'POST',
		url : serverName + '/validateController/queryPersonArrested.do?name=&code=' + codeNum,
		success : function(msg) {
			isQueryCourt = true;
			if (msg.success != undefined) {
				$.messager.alert('操作提示', '调用人法网接口服务异常，请稍后再试！', 'error');
				queryResultFlag = "2";
				return;
			}
			if (msg.length > 0) {
				for ( var i = 0; i < msg.length; i++) {
					$('#commonCourtSearch').datagrid('appendRow', {
						name : msg[i].name,
						code : msg[i].code,
						court : msg[i].court,
						time : msg[i].time,
						caseNumber : msg[i].caseNumber,
						money : msg[i].money,
						state : msg[i].state
					});
				}
				queryResultFlag = "1";
			} else {
				$.messager.alert('操作提示', '无此人查询信息', 'error');
				queryResultFlag = "0";
			}
		}
	});
}

// 新增申请人身份证验证
function checkCodeNum() {
	/** *******身份证验证开始***************************** */
	var applyname = $('#applyname').val().trim();
	var codenum1 = $('#codenum1').val();
	var codenum2 = $('#codenum2').val();
	if (applyname == '') {
		$.messager.alert('操作提示', '请输入申请人姓名', 'error');
		$('#applyname').val('');
		document.getElementById('applyname').focus();
		return;
	}
	if (applyname.length < 2 || applyname.length > 16) {
		$.messager.alert('操作提示', '共同借款人姓名必须在2-16位', 'error');
		$('#applyname').val('');
		document.getElementById('applyname').focus();
		return;
	}

	if (!(/^[\u4E00-\u9FA5\uF900-\uFA2Da-zA-Z]+$/).test(applyname)) {
		$.messager.alert('操作提示', '共同借款人姓名输入不正确，请输入正确姓名', 'error');
		$('#applyname').val('');
		document.getElementById('applyname').focus();
		return;
	}
	if (codenum1 == '') {
		$.messager.alert('操作提示', '请输入身份证号', 'error');
		$('#codenum2').val('');
		document.getElementById('codenum1').focus();
		return;
	}
	if (codenum2 == '') {
		$.messager.alert('操作提示', '请再次输入身份证号', 'error');
		$('#codenum2').focus();
		return;
	}

	if (codenum1 != codenum2) {
		$.messager.alert('操作提示', '两次输入的身份证号不一致，请重新输入', 'error');
		$('#codenum2').val('');
		return;
	}

	if (codenum2.length != 18) {
		$.messager.alert('操作提示', '请输入有效18位身份证', 'error');
		return;
	}

	if (!validNumberAll(codenum2)) {
		$.messager.alert('操作提示', '无效身份证', 'error');
		return;
	}

	if (!isValidityBrithBy18IdCard(codenum2)) {
		$.messager.alert('操作提示', '无效身份证', 'error');
		return;
	}

	if (codenum1[17] == 'x') {
		$.messager.alert('操作提示', '校验位字母应该为大写', 'error');
		return;
	}
	if (codenum2[17] == 'x') {
		$.messager.alert('操作提示', '校验位字母应该为大写', 'error');
		return;
	}

	var flag = true;
	var result = $.ajax({
		url : serverName + '/validateController/identityValidate.do?identity=' + codenum2,
		async : false,
		type : "post",
		success : function retFun(response) {
			if (!response.success) {
				$.messager.alert("操作提示", response.msg, 'error');
				flag = false;
			}
		}
	});

	// 业务规则校验通过就添加数据
	if (flag) {

		/** ***************身份证验证结束******************* */

		// 根据身份证号得到年龄
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - codenum2.substring(6, 10) - 1;
		if (codenum2.substring(10, 12) < month || codenum2.substring(10, 12) == month && codenum2.substring(12, 14) <= day) {
			age++;
		}

		// 根据身份证号得到性别
		var gender = '';
		if (parseInt(codenum2.substr(16, 1)) % 2 == 1) {
			gender = '男';
		} else {
			gender = '女';
		}

		// 只允许添加一条申请人数据
		var rows = $('#commonApply').datagrid('getRows');
		if (rows.length == 1) {
			$.messager.alert('操作提示', '请先删除申请人列表数据，再添加', 'error');
			return;
		}

		// 查询输入姓名是否和历史申请中的姓名一致
		$.ajax({
			url : serverName + '/applyPersonInfo/getCustomerName.do?code=' + codenum2,
			type : "POST",
			success : function(result) {
				if (result != null && result != '' && result.appInfoName != undefined) {
					if (result.appInfoName != applyname) {
						$.messager.alert("操作提示", '系统检测到该身份证对应的客户姓名是' + result.appInfoName + '，请再次核对！', 'error');
						return;
					}
				}
			}
		});

		// 添加数据到申请人列表
		$('#commonApply').datagrid(
				'appendRow',
				{
					appInfoName : applyname,
					identityCard : codenum2,
					appInfoAge : age,
					appInfoSex : gender,
					info : // "<a href=\"#\" onclick=\"$('#add_new_client').show();$(this).hide();\">人法网查询</a>"
					"<a href=\"#\" onclick=\"addCourtSearchInfo('" + applyname + "','" + codenum2 + "');$(this).hide();\" ><font color=\"red\">人法网查询</font></a>"
							+ " <span style=\"display:none;\" id=\"add_new_client\"><a href=\"#\" "
							+ " onclick=\"window.parent.parent.addTabFun({title:'编辑客户申请',src:'jsp/add_apply.jsp'});parent.$('#apply').dialog('close');\">点击添加</a></span>",// "<a
																																											// href=\"#\"
																																											// onclick=\"addCourtSearchInfo("+codenum2+");$(this).hide();\"
																																											// >人法网查询</a>",
					operate : "<a href=\"#\" onclick=\"delApplyInfo();\" >删除</a>"
				});
	}
}

// 操作按钮不可用
function disableOptBtn() {
	var t = $("#opt_btn a");
	console.info(t);
	t.linkbutton('disable');
}
// 按钮可用
function enableOptBtn() {
	var t = $("#opt_btn a");
	t.linkbutton('enable');
}

/*
 * 去掉申请人姓名中的空格
 */
function trimName(obj) {
	var applyname = $("#" + obj.id).val();
	if (applyname != '') {
		$("#" + obj.id).val(applyname.NoSpace());
	}
}

/*
 * 去掉字符串中所有空格
 */
String.prototype.NoSpace = function() {
	return this.replace(/\s+/g, "");
}
