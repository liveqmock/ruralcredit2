$(function () {
    //上传文件
    var openCM = $('#openCM');
    if (openCM.length > 0) {
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
    }
});

//保存变更
function saveChangeReason() {
    var confirmMsg;
    var type = $('#type').val();
    var beforeStatus = $('#beforeStatus').val();
    if (type == '不良贷款') {
        type = 'nonPerformingLoans';
        if (beforeStatus == '还款中' || beforeStatus == '法律诉讼') {
            confirmMsg = '确认将此业务变更为不良贷款吗？';
        } else {
            confirmMsg = '确认将此业务变更为还款中吗？';
        }
    } else if (type == '法律诉讼') {
        type = 'legalProceedings';
        if (beforeStatus == '还款中' || beforeStatus == '不良贷款') {
            confirmMsg = '确认将此业务变更为法律诉讼吗？';
        } else {
            confirmMsg = '确认将此业务变更为还款中吗？';
        }
    }
    if ($('#changeReasonForm').form("validate")) {
        $.messager.confirm('提示', confirmMsg, function (r) {
            if (r) {
                //检查是否上传附件
                var clientId = clientid;
                var url = serverName + "/businessStatusChangeController/checkAttachment.do";
                ajaxSubmit(url, "post", {clientId: clientId}, function (attachmentCount) {
                    if(!attachmentCount.count > 0){
                        clientId = '';
                    }
                }, false);
                var dataParam = 'clientId=' + clientId + '&' + $('#changeReasonForm').serialize();
                $.post(serverName + "/businessStatusChangeController/saveChange.do", dataParam, function (result) {
                    if (result.success) {
                        parent.$.messager.show({
                            title: '消息',
                            msg: '操作成功',
                            timeout: 5000,
                            showType: 'slide'
                        });
                        window.parent.cancelChange();
                        window.parent.searchBusStaChange(type);
                    } else {
                        parent.$.messager.show({
                            title: '消息',
                            msg: result.msg,
                            timeout: 5000,
                            showType: 'slide'
                        });
                    }
                }, "json");
            }
        });
    } else {
        $.messager.alert("提示", "请选择变更原因",'info');
        return;
    }
}