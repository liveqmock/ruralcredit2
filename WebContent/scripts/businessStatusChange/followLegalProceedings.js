var poolViewDataGrid;
$(function () {
    poolViewDataGrid = $('#inquire_pool_view').datagrid({
        url: serverName + '/businessStatusChangeController/viewLegalProceedingsDetail.do',
        fitColumns: true,
        rownumbers: true,
        scrollbarSize: 0,
        queryParams: {creditapplication_id: creditapplication_id},
        singleSelect:true,
        height: $('#centerPanel')[0].clientHeight - $('#followText_history')[0].clientHeight - $('#followText')[0].clientHeight - 70,
        pagination: true,
        columns: [
            [
                {
                    title: '操作人', field: 'OPERATOR', width: 50, align: 'left'
                },
                {
                    title: '操作时间', field: 'OPERATE_DATE', width: 50, align: 'left'
                },
                {
                    title: '跟进记录', field: 'FOLLOW_LOG', width: 100, align: 'left'
                }
            ]
        ]
    });
    poolViewDataGrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"FOLLOW_LOG",showContant:"FOLLOW_LOG"});
});

function _saveFollow(){
    var data = 'creditapplication_id=' + creditapplication_id + '&' + $("#followForm").serialize();
    $.post(serverName + '/businessStatusChangeController/saveLegalProceedings.do', data, function (result) {
        if (result.success) {
            parent.$.messager.show({
                title: '消息',
                msg: '操作成功',
                timeout: 5000,
                showType: 'slide'
            });
            //刷新列表
            window.parent.reloadLegalProceedings();
            //置灰按钮-防止多次提交
            window.parent.controlBtn('disable');
        } else {
            parent.$.messager.show({
                title: '消息',
                msg: result.msg,
                timeout: 5000,
                showType: 'slide'
            });
        }
        //关闭弹窗
        window.parent.cancelFollow();
    }, "json");
}

/*字数验证*/
function textCount(textId, htmlId, max) {
    $("#" + textId).keyup(function () {
        window.parent.controlBtn('disable');
        var maxl = max;
        var tishi = "还可以输入" + maxl + "个字";
        $("#" + htmlId).html(tishi);
        var xianyou = $(this).val().trim().length;
        if (xianyou > 0) {
            xianyou = $(this).val().length;
        }
        if(xianyou > 0){
            window.parent.controlBtn('enable');
        }
        var keyi = maxl - xianyou;
        var tishi = "还可以输入" + keyi + "个字";
        if (xianyou > (max - 1)) {
            var tishi = "还可以输0个字";
            $("#" + htmlId).css({
                "color": "red"
            });
            var s = $("#" + textId).val().substr(0, 100);
            $("#" + textId).val();
        } else {
            $("#" + htmlId).css({
                "color": "#000"
            });
        }
        $("#" + htmlId).html(tishi);
    });
}