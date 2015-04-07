/**
 * Created by Administrator on 2014-9-16.
 */
var customer_datagrid;
var commissioner_datagrid;

$(function () {
    /*Backspace 页面误返回*/
    $(document).on("keydown", function (e) {
        if (e.which === 8 && !$(e.target).is("input, textarea")) {
            e.preventDefault();
        }
    });
    /*Enter 提交*/
    $(document).on('keypress', function (e) {
        if (e.keyCode == 13) {
            if (opType == 'approve') {
                $('#submit_approve').click();
            } else {
                $('#submit_complaining').click();
            }
            return false;
        }
    });
    /*初始化数据：查询两种类型检查记录*/
    ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
        customer_datagrid = $('#check_customer').datagrid({
            scrollbarSize: 0,
            fitColumns: true,
            singleSelect: true,
            columns: [
                [
                    {title: '错误文件', field: 'errorFile', width: 100, align: 'center'},
                    {title: '检查点', field: 'checkPointLoan', width: 200, align: 'center'},
                    {title: '操作人', field: 'operatorName', width: 100, align: 'center'},
                    {title: '操作时间', field: 'operateDate', width: 150, align: 'center'}
                ]
            ]
        });
        customer_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPointLoan", showContant: "checkPointLoan"});
        commissioner_datagrid = $('#check_commissioner').datagrid({
                scrollbarSize: 0,
                fitColumns: true,
                singleSelect: true,
                columns: [
                    [
                        {title: '对象', field: 'complianceObject', width: 80, align: 'center' },
                        {title: '系统资料', field: 'systemMaterial', width: 100, align: 'center'},
                        {title: '检查点', field: 'checkPoint', width: 200, align: 'center'},
                        {title: '操作人', field: 'operatorName', width: 80, align: 'center'},
                        {title: '操作时间', field: 'operateDate', width: 100, align: 'center'}
                    ]
                ]
            }
        );
        commissioner_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
        if (ret) {
            if (ret.customer && ret.customer.length > 0) {
                customer_datagrid.datagrid('loadData', ret.customer);
            } else {
//                $('#div_customer_outer').hide();
            }
            /*客服备注*/
            if (ret.remark_customer && ret.remark_customer.length > 0) {
                for (var i = 0; i < ret.remark_customer.length; i++) {
                    if (ret.remark_customer[i].checkRemark) {
                        $('#remark_customer').html($('#remark_customer').html() + (i + 1) + '.' + ret.remark_customer[i].checkRemark + '<br/>');
                    }
                }
            }
            if (ret.commissioner && ret.commissioner.length > 0) {
                commissioner_datagrid.datagrid('loadData', ret.commissioner);
            } else {
//                $('#div_commissioner_outer').hide();
            }
            /*合规备注*/
            if (ret.remark_commissioner && ret.remark_commissioner.length > 0 && ret.remark_commissioner[0].checkRemark) {
                $('#remark_commissioner').text(ret.remark_commissioner[0].checkRemark);
            }
        }
    });
    /*适用于申诉审批*/
    if (opType == 'approve') {
        $('#complaint_content_approve').html(complaintContent);
        $('#approve_result_select').combobox({
            valueField: 'id',
            textField: 'text',
            data: [
                { id: '', text: '请选择' },
                { id: '0', text: '通过' },
                { id: '1', text: '驳回' }
            ],
            panelHeight: 'auto',
            editable: false,
            width: 150
        });
//        document.getElementById('remark_self_approve').scrollIntoView();
    } else {
//        document.getElementById('remark_self_complaining').scrollIntoView();
    }
});
/*字数验证*/
function textCount(textId, htmlId, max) {
    $("#" + textId).keyup(function () {
        var maxl = max;
        var tishi = "还可以输入" + maxl + "个字";
        $("#" + htmlId).html(tishi);
        var xianyou = $(this).val().length;
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
/*提交-申诉*/
function submit_complaining() {
    $("#submit_complaining").linkbutton("disable");
    if (!$.trim($('#remark_self_complaining').val())) {
        $("#submit_complaining").linkbutton("enable");
        $.messager.alert('提示', '请填写申诉内容！', 'info');
    } else {
        $.messager.confirm('提示', '确定提交吗？', function (ret) {
            if (ret) {
                ajaxSubmit(serverName + '/ComplianceController/saveComplianceComplaint.do?complainingType=' + opType + '&creditApplicationId=' + creditApplicationId + '&complaintContent=' + $.trim($('#remark_self_complaining').val()), {}, function (ret) {
                    if (ret) {
                        $("#submit_complaining").linkbutton("enable");
                        $.messager.alert('提示', '保存成功！', 'info');
                        return_masterList_common();
                    } else {
                        $("#submit_complaining").linkbutton("enable");
                        $.messager.alert('提示', '保存失败！', 'info');
                        return;
                    }
                });
            } else {
                $("#submit_complaining").linkbutton("enable");
            }
        });
    }
}
/*取消-申诉*/
function return_masterList_common() {
    window.location.href = serverName + '/jsp/rc/compliance/compliance_record.jsp';
}
/*保存-申诉审批*/
function submit_approve() {
    $("#submit_approve").linkbutton("disable");
    if (!$('#approve_result_select').combobox('getValue')) {
        $("#submit_approve").linkbutton("enable");
        $.messager.alert('消息', '请选择审批结果！', 'info');
        return;
    }
    if (!$.trim($('#remark_self_approve').val())) {
        $("#submit_approve").linkbutton("enable");
        $.messager.alert('消息', '请填写审批内容！', 'info');
        return;
    }
    $.messager.confirm('提示', '确定保存吗？', function (ret) {
        if (ret) {
            ajaxSubmit(serverName + '/ComplianceController/saveComplianceComplaint.do?complainingType=' + opType + '&creditApplicationId=' + creditApplicationId + '&complaintId=' + complaintId + '&approveResult=' + $('#approve_result_select').combobox('getValue') + '&approveContent=' + $.trim($('#remark_self_approve').val()), {}, function (ret) {
                if (ret) {
                    $("#submit_approve").linkbutton("enable");
                    $.messager.alert('提示', '保存成功！', 'info');
                    return_masterList_common();
                } else {
                    $("#submit_approve").linkbutton("enable");
                    $.messager.alert('提示', '保存失败！', 'info');
                    return;
                }
            });
        } else {
            $("#submit_approve").linkbutton("enable");
        }
    });
}
