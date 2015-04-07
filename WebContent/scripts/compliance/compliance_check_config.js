/**
 * Created by Administrator on 2014-8-19.
 */
var datagrid_customer, datagrid_commissioner;
var checkpoint_customer = [], checkpoint_commissioner = [], object_tip = {};
var div_dialog, commonDialog_checkpoint, commonCheckpoint_type;
var checkPoint_customer, checkPoint_commissioner;
var div_dialog_edit, editForm;
$(function () {
    /*检查点初始化：客服、合规*/
    var checkpoint_url = serverName + "/dicRequest/getSpecifiedDic.do?";
    ajaxSubmit(checkpoint_url + "section=checkpoint_loan", {}, function (ret) {
        if (ret && ret.length > 0) {
            checkpoint_customer = ret;
            object_tip.codeVlue = '请选择';
            object_tip.codeKey = '';
            checkpoint_customer.unshift(object_tip);
            $("#combobox_checkpoint_customer").combobox({
                textField: 'codeVlue',
                valueField: 'codeKey',
                data: ret,
                editable: false,
                onSelect: function (data) {
                    checkPoint_customer = data.codeKey;
                }
            });
            ajaxSubmit(checkpoint_url + "section=checkpoint_compliance_v1", {}, function (ret) {
                if (ret && ret.length > 0) {
                    checkpoint_commissioner = ret;
                    checkpoint_commissioner.unshift(object_tip);
                    $("#combobox_checkpoint_commissioner").combobox({
                        textField: 'codeVlue',
                        valueField: 'codeKey',
                        data: ret,
                        editable: false,
                        onSelect: function (data) {
                            checkPoint_commissioner = data.codeKey;
                        }
                    });
                } else {
                    console.info('合规检查点初始化发生错误！');
                }
            });
        } else {
            console.info('客服检查点初始化发生错误！');
        }
    });
    var datagrid_url = serverName + '/ComplianceController/selectComplianceCheckPointConfigByType.do?';
    datagrid_customer = $('#datagrid_customer').datagrid({
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        url: datagrid_url + 'complianceCheckPointType=0',
        columns: [
            [
                {title: '配置表主键ID', field: 'complianceCheckPointConfigId', width: 350, align: 'center', hidden: true},
                {title: '检查点', field: 'checkPoint', width: 350, align: 'center'},
                {title: '扣分', field: 'complianceCheckPointScore', width: 300, align: 'center', formatter: function (v) {
                    if (v) {
                        return v.toFixed(1);
                    }
                }},
                {title: '检查点数据字典key值', field: 'checkPointKey', width: 350, align: 'center', hidden: true}
            ]
        ]
    });
    datagrid_commissioner = $('#datagrid_commissioner').datagrid({
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        url: datagrid_url + 'complianceCheckPointType=1',
        columns: [
            [
                {title: '配置表主键ID', field: 'complianceCheckPointConfigId', width: 350, align: 'center', hidden: true},
                {title: '检查点', field: 'checkPoint', width: 350, align: 'center'},
                {title: '扣分', field: 'complianceCheckPointScore', width: 300, align: 'center', formatter: function (v) {
                    if (v) {
                        return v.toFixed(1);
                    }
                }},
                {title: '检查点数据字典key值', field: 'checkPointKey', width: 350, align: 'center', hidden: true}
            ]
        ]
    });
    $('#div_dialog').dialog('close');
    $('#div_dialog_edit').dialog('close');
});
function compliance_search(type) {
    var data_param = {};
    if (type == 'customer' || type == '0') {
        if (checkPoint_customer) {
            data_param.checkPoint = checkPoint_customer;
        }
        datagrid_customer.datagrid('load', data_param);
    } else if (type == 'commissioner' || type == '1') {
        if (checkPoint_commissioner) {
            data_param.checkPoint = checkPoint_commissioner;
        }
        datagrid_commissioner.datagrid('load', data_param);
    }
}
function compliance_add(type) {
    var title;
    var dialog_add_data = [];
    if (type == 'customer') {
        title = '客服合规检查配置';
        dialog_add_data = checkpoint_customer;
        commonCheckpoint_type = '0';
    } else if (type == 'commissioner') {
        title = '合规检查配置';
        dialog_add_data = checkpoint_commissioner;
        commonCheckpoint_type = '1';
    }
    commonDialog_checkpoint = $("#commonDialog_checkpoint").combobox({
        textField: 'codeVlue',
        valueField: 'codeKey',
        data: dialog_add_data
    });
    div_dialog = $('#div_dialog').dialog({
        title: title,
        cache: false,
        modal: true
    });
}
function compliance_edit(type) {
    var rowData;
    var title;
    var dialog_add_data = [];
    if (type == 'customer') {
        title = '客服合规检查配置';
        dialog_add_data = checkpoint_customer;
        commonCheckpoint_type = '0';
        rowData = datagrid_customer.datagrid('getSelected');
        if (!rowData) {
            $.messager.alert("提示", '请选择一条数据进行操作！');
            return;
        }
    } else if (type == 'commissioner') {
        title = '合规检查配置';
        dialog_add_data = checkpoint_commissioner;
        commonCheckpoint_type = '1';
        rowData = datagrid_commissioner.datagrid('getSelected');
        if (!rowData) {
            $.messager.alert("提示", '请选择一条数据进行操作！');
            return;
        }
    }
    $("#commonDialog_checkpoint_edit").combobox({
        textField: 'codeVlue',
        valueField: 'codeKey',
        /*data: dialog_add_data,*/
        editable: false
    });
    $("#commonDialog_checkpoint_edit").combobox('setValue', rowData.checkPointKey);
    $("#commonDialog_checkpoint_edit").combobox('setText', rowData.checkPoint);
    editForm = $('#editForm').form('load', {
        complianceCheckPointConfigId: rowData.complianceCheckPointConfigId,
        commonDialog_score_edit: rowData.complianceCheckPointScore
    });
    div_dialog_edit = $('#div_dialog_edit').dialog({
        title: title,
        cache: false,
        modal: true
    });
}
function compliance_delete(type) {
    var rowData;
    if (type == 'customer') {
        commonCheckpoint_type = '0';
        rowData = datagrid_customer.datagrid('getSelected');
        if (!rowData) {
            $.messager.alert("提示", '请选择一条数据进行操作！');
            return;
        }
    } else if (type == 'commissioner') {
        commonCheckpoint_type = '1';
        rowData = datagrid_commissioner.datagrid('getSelected');
        if (!rowData) {
            $.messager.alert("提示", '请选择一条数据进行操作！');
            return;
        }
    }
    $.messager.confirm('确认', '确定删除此条记录吗？', function (ret) {
        if (ret) {
            ajaxSubmit(serverName + '/ComplianceController/deleteComplianceCheckConfigById.do?', {complianceCheckPointConfigId: rowData.complianceCheckPointConfigId, complianceCheckPointType: commonCheckpoint_type}, function (ret) {
                if (ret && ret.success) {
                    compliance_search(commonCheckpoint_type);
                }
                $.messager.alert("提示", ret.msg);
            });
        }
    });
}
function config_cancel() {
    div_dialog.dialog('close');
    $('#commonDialog_score').numberbox('clear');
}
function config_cancel_edit() {
    div_dialog_edit.dialog('close');
    $('#commonDialog_score_edit').numberbox('clear');
}
function config_save() {
    var cp = commonDialog_checkpoint.combobox('getValue');
    var cs = $('#commonDialog_score').numberbox('getValue');
    if(cs == 0.0){
        $.messager.alert('提示','扣分不能为零！');
        return;
    }
    if(cs > 99){
        $.messager.alert('提示','扣分不能超过99分！');
        return;
    }
    if (cp && cs) {
        ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheckConfig.do?', {checkPoint: cp, complianceCheckPointScore: cs, complianceCheckPointType: commonCheckpoint_type}, function (ret) {
            if (ret) {
                if (ret.success) {
                    $('#commonDialog_score').numberbox('clear');
                    div_dialog.dialog('close');
                    compliance_search(commonCheckpoint_type);
                }
                $.messager.alert("提示", ret.msg);
            } else {
                $.messager.alert("提示", '保存失败');
            }
        });
    } else {
        if (!cp) {
            $.messager.alert("提示", '请选择检查点');
        } else {
            $.messager.alert("提示", '请填写分数');
        }
    }
}
function config_save_edit() {
    var cp = $('#commonDialog_checkpoint_edit').combobox('getValue');
    var cs = $('#commonDialog_score_edit').numberbox('getValue');
    if(cs == 0.0){
        $.messager.alert('提示','扣分不能为零！');
        return;
    }
    if(cs > 99){
        $.messager.alert('提示','扣分不能超过99分！');
        return;
    }
    var cid = $('#complianceCheckPointConfigId').val();
    if (cp && cs) {
        ajaxSubmit(serverName + '/ComplianceController/updateComplianceCheckConfig.do?', {checkPoint: cp, complianceCheckPointScore: cs, complianceCheckPointConfigId: cid, complianceCheckPointType: commonCheckpoint_type}, function (ret) {
            if (ret) {
                if (ret.success) {
                    $('#commonDialog_score_edit').numberbox('clear');
                    div_dialog_edit.dialog('close');
                    compliance_search(commonCheckpoint_type);
                }
                $.messager.alert("提示", ret.msg);
            } else {
                $.messager.alert("提示", '保存失败！');
            }
        });
    } else {
        if (!cp) {
            $.messager.alert("提示", '请选择检查点');
        } else {
            $.messager.alert("提示", '请填写分数');
        }
    }

}

