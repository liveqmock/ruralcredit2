$(function() {

});
function uploadQuestionExcel() {
	$("#uploadQuestionExcelButton").linkbutton("disable");
	$('#uploadFileForm').form('submit', {
		url : serverName + '/subjectManagementController/uploadQuestionExcel.do',
		type : "post",
		dataType : "json",
		onSubmit : function() {
			var fileName = $('#file').val();
			if (fileName == '') {
				$.messager.alert('提示', '请选择需要上传的文件', 'info');
				return false;
			}
			if (fileName != '') {
				if (fileName.indexOf('.xlsx') == -1 && fileName.indexOf('.xls') == -1) {
					$.messager.alert('提示', '文件格式不正确，请选择正确的Excel文件', 'info');
					return false;
				}
			}
			return true;
		},
		success : function(r) {
			var data = $.parseJSON(r);
			if (data.success) {
				$.messager.show({
					showType : "show",
					timeout : 2000,
					title : '消息',
					height : 150,
					width : 300,
					msg : data.msg
				});
				// 清空上传文件信息显示
				$("#file").val('');
				$("#uploadQuestionExcelButton").linkbutton("enable");
			} else {
				$.messager.show({
					showType : "show",
					timeout : 50000,
					title : '消息',
					height : 150,
					width : 300,
					msg : data.msg
				});
				// 清空上传文件信息显示
				$("#file").val('');
				$("#uploadQuestionExcelButton").linkbutton("enable");
			}
		}
	});
}

function prize() {
	$("#prizeButton").linkbutton("disable");
	ajaxSubmit(serverName + '/prizeManagementController/constructPrizeTemp.do', {}, function(r) {
		$.messager.alert('提示', r.msg, 'info');
		$("#prizeButton").linkbutton("enable");
	});
}