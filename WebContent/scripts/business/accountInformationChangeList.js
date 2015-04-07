$(function() {
	$("#receivablesList").datagrid({
		url : serverName + "/accountInfo/queryAccountInformationChangeList.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
		scrollbarSize: 0,
		columns : [ [ {
			field : 'modifyCatdAppId',
			title : '变更申请主键',
			width : 100,
			hidden : true
		}, {
			field : 'accountInfoId',
			title : '公司财务账号表ID',
			width : 100,
			hidden : true
		}, {
			field : 'creditapplicationId',
			title : '信贷申请ID',
			width : 100,
			hidden : true
		}, {
			field : 'businessNumber',
			title : '业务单号',
			width : 100,
			hidden : false
		}, {
			field : 'name',
			title : '借款人姓名',
			width : 150,
			hidden : false
		}, {
			field : 'status',
			title : '审批状态',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "待审批";
				} else if ("1" == value) {
					return "审批通过";
				} else if ("2" == value) {
					return "审批拒绝";
				} else {
					return "NULL";
				}
			}
		}, {
			field : 'companyName',
			title : '分公司名称',
			width : 100,
			hidden : false
		}, {
			field : 'proposerName',
			title : '申请人姓名',
			width : 200,
			hidden : false
		}, {
			field : 'applicationTime',
			title : '申请时间',
			width : 100,
			hidden : false
		}, {
			field : 'signagreementDate',
			title : '放款时间',
			width : 100,
			hidden : false
		}, {
			field : 'refusecause',
			title : '拒绝原因',
			width : 100
		} ] ],
		frozenColumns : [ [ {
			field : 'operation',
			title : '操作',
			width : 100,
			hidden : false,
			formatter : returnPalnView
		} ] ],
		onLoadSuccess : function(data) {
		}
	});
	$("#receivablesList").datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"refusecause",showContant:"refusecause"});
});

function openRegistrationDialog(modifyCatdAppId, creditapplicationId, type) {
	if (type == "0") {
		$("#registrationDialog").dialog({
			modal : true,
			title : "信行卡变更审批",
			maximizable : false,
			cache : false,
			closed : true,
			content : "<iframe scrolling='no' id='iframeTest' name='iframeRegistration' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
			buttons : [ {
				id : "rdButton1",
				text : "通过",
				iconCls : "icon-ok",
				handler : function() {
					iframeRegistration.window.approval(1);
				}

			}, {
				id : "rdButton2",
				text : "拒绝",
				iconCls : "icon-cancel",
				handler : function() {
					iframeRegistration.window.approval(2);
				}
			} ],
			onClose : function() {
				$("#iframeTest").attr('src', "");
			}
		});
		$("#iframeTest").attr('src', serverName + "/accountInfo/returnBankCardJSPforApproval.do?modifyCatdAppId=" + modifyCatdAppId + "&creditapplicationId=" + creditapplicationId + "&type=" + 1);

		$("#registrationDialog").dialog('open');
	} else {
		$("#registrationDialog2").dialog({
			modal : true,
			title : "信行卡变更查看",
			maximizable : false,
			cache : false,
			closed : true,
			content : "<iframe scrolling='no' id='iframeTest2' name='iframeRegistration2' frameborder='0'  src='' style='width:100%;height:100%;'></iframe>",
			onClose : function() {
				$("#iframeTest2").attr('src', "");
			}
		});
		$("#iframeTest2").attr('src', serverName + "/accountInfo/returnBankCardJSPforApproval.do?modifyCatdAppId=" + modifyCatdAppId + "&creditapplicationId=" + creditapplicationId + "&type=" + 1);

		$("#registrationDialog2").dialog('open');
	}

}
function closeRegistrationDialog() {
	$("#registrationDialog").dialog('close');
}

function buttonDisable() {
	$("#rdButton1").linkbutton('disable');
	$("#rdButton2").linkbutton('disable');
}

function buttonEnable() {
	$("#rdButton1").linkbutton('enable');
	$("#rdButton2").linkbutton('enable');
}

function messagerShow(message) {
	$.messager.show({
		showType : "show",
		timeout : 5000,
		title : "消息",
		msg : message
	});
}
function mySearch() {
	var data = new Object();
	var businessNumber = $("#businessNumber").val();
	var name = $("#name").val();
	var map = {
		businessNumber : businessNumber,
		name : name
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$('#receivablesList').datagrid('reload', data);
}

function myClear() {
	$("#businessNumber").val("");
	$("#name").val("");
}