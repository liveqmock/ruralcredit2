/**
 * Created by Administrator on 2014-8-11.
 */
var self_datagrid;
/*合规检查：获取数据字典：对象、系统资料、检查点*/
var sections = 'selfCheck';
var object_compliance = [], systemMaterial_compliance = [], checkpoint_compliance = [];
/*操作人*/
var operator;
/*原始备注*/
var checkRemark = '';
var dynamicSystemMaterial;
$(function () {
    ajaxSubmit(serverName + '/ComplianceController/selectDataDictionaryBySections.do?sections=' + sections, {}, function (ret) {
        if (ret) {
            if (ret.object_compliance.length > 0) {
                object_compliance = JSON.parse(ret.object_compliance);
            }
            if (ret.systemMaterial_compliance.length > 0) {
                systemMaterial_compliance = JSON.parse(ret.systemMaterial_compliance);
            }
            if (ret.checkpoint_compliance.length > 0) {
                checkpoint_compliance = JSON.parse(ret.checkpoint_compliance);
            }
            if (ret.operator) {
                operator = ret.operator;
            }
        }

        ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
            self_datagrid = $('#check_self').datagrid({
                fitColumns: true,
                singleSelect: true,
                columns: [
                    [
                        {title: '对象', field: 'complianceObject', width: 80, align: 'center', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeKey',
                                textField: 'codeVlue',
                                data: object_compliance,
                                required: true,
                                panelHeight: 'auto',
                                editable: false,
                                onSelect: function (rowData) {
                                    /*0-借款人&共借人 1-担保人 2-系统 3-其他*/
                                    if (rowData.codeKey == '0') {
                                        dynamicSystemMaterial = 'borrower_systemMaterial_v1';
                                    } else if (rowData.codeKey == '1') {
                                        dynamicSystemMaterial = 'guarantor_systemMaterial_v1';
                                    } else if (rowData.codeKey == '2') {
                                        dynamicSystemMaterial = 'system_systemMaterial_v1';
                                    } else if (rowData.codeKey == '3') {
                                        dynamicSystemMaterial = 'other_systemMaterial_v1';
                                    } else if (rowData.codeKey == '4') {
                                        dynamicSystemMaterial = 'business_fo_systemMaterial_v1';
                                    }
                                    var target = self_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'systemMaterial' }).target;
                                    target.combobox('clear');
                                    var url = serverName + '/ComplianceController/getDataDictionaryBySectionAndParentId.do?section=' + dynamicSystemMaterial + '&parentId=' + rowData.codaTableId;
                                    target.combobox('reload', url);
                                }
                            }
                        }, formatter: function (value) {
                            for (var i = 0; i < object_compliance.length; i++) {
                                if (object_compliance[i].codeKey == value) {
                                    return object_compliance[i].codeVlue;
                                }
                            }
                            return value;
                        }
                        },
                        {title: '系统资料', field: 'systemMaterial', width: 200, align: 'center', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeKey',
                                textField: 'codeVlue',
//                                data: systemMaterial_compliance,
                                required: true,
                                panelHeight: 'auto',
                                editable: false,
                                onSelect: function (rowData) {
                                    /*0-身份证明S 1-婚姻证明H 2-资产证明Z 3-收入证明I 4-居住证明J 5-工作/经营证明G 6-业务表格B */
                                    var dynamic_checkpoint;
                                    if (dynamicSystemMaterial == 'borrower_systemMaterial_v1') {
                                        if (rowData.codeKey == '0') {
                                            dynamic_checkpoint = 'borrower_cp_shenfen_v1';
                                        } else if (rowData.codeKey == '1') {
                                            dynamic_checkpoint = 'borrower_cp_hunyin_v1';
                                        } else if (rowData.codeKey == '2') {
                                            dynamic_checkpoint = 'borrower_cp_zichan_v1';
                                        } else if (rowData.codeKey == '3') {
                                            dynamic_checkpoint = 'borrower_cp_juzhu_v1';
                                        } else if (rowData.codeKey == '4') {
                                            dynamic_checkpoint = 'borrower_cp_gongjing_v1';
                                        } else if (rowData.codeKey == '5') {
                                            dynamic_checkpoint = 'borrower_cp_shouru_v1';
                                        }
                                    } else if (dynamicSystemMaterial == 'guarantor_systemMaterial_v1') {
                                        if (rowData.codeKey == '0') {
                                            dynamic_checkpoint = 'guarantor_cp_shenfen_v1';
                                        } else if (rowData.codeKey == '1') {
                                            dynamic_checkpoint = 'guarantor_cp_hunyin_v1';
                                        } else if (rowData.codeKey == '2') {
                                            dynamic_checkpoint = 'guarantor_cp_zichan_v1';
                                        } else if (rowData.codeKey == '3') {
                                            dynamic_checkpoint = 'guarantor_cp_juzhu_v1';
                                        } else if (rowData.codeKey == '4') {
                                            dynamic_checkpoint = 'guarantor_cp_gongjing_v1';
                                        } else if (rowData.codeKey == '5') {
                                            dynamic_checkpoint = 'guarantor_cp_shouru_v1';
                                        }
                                    } else if (dynamicSystemMaterial == 'system_systemMaterial_v1') {
                                        if (rowData.codeKey == '11') {
                                            dynamic_checkpoint = 'system_cp_input_v1';
                                        }
                                    } else if (dynamicSystemMaterial == 'other_systemMaterial_v1') {
                                        if (rowData.codeKey == '12') {
                                            dynamic_checkpoint = 'other_cp_credit_v1';
                                        } else if (rowData.codeKey == '13') {
                                            dynamic_checkpoint = 'other_cp_using_v1';
                                        }
                                    } else if (dynamicSystemMaterial == 'business_fo_systemMaterial_v1') {
                                        if (rowData.codeKey == '6') {
                                            dynamic_checkpoint = 'business_fo_shenqingbiao_v1';
                                        } else if (rowData.codeKey == '7') {
                                            dynamic_checkpoint = 'business_fo_dbrxinxibiao_v1';
                                        } else if (rowData.codeKey == '8') {
                                            dynamic_checkpoint = 'business_fo_diaochabiao_v1';
                                        } else if (rowData.codeKey == '9') {
                                            dynamic_checkpoint = 'business_fo_cash_v1';
                                        } else if (rowData.codeKey == '10') {
                                            dynamic_checkpoint = 'business_fo_approve_v1';
                                        }
                                    }
                                    var target = self_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'checkPoint' }).target;
                                    target.combobox('clear');
                                    var url = serverName + '/ComplianceController/getDataDictionaryBySectionAndParentId.do?section=' + dynamic_checkpoint + '&parentId=' + rowData.codaTableId;
                                    target.combobox('reload', url);
                                }
                            }
                        }, formatter: function (value) {
                            for (var i = 0; i < systemMaterial_compliance.length; i++) {
                                if (systemMaterial_compliance[i].codeKey == value) {
                                    return systemMaterial_compliance[i].codeVlue;
                                }
                            }
                            return value;
                        }},
                        {title: '检查点', field: 'checkPoint', width: 300, align: 'center', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeKey',
                                textField: 'codeVlue',
//                                data: checkpoint_compliance,
                                required: true,
                                panelHeight: 'auto',
                                editable: false,
                                panelHeight: 'auto'
                            }
                        }, formatter: function (value) {
                            for (var i = 0; i < checkpoint_compliance.length; i++) {
                                if (checkpoint_compliance[i].codeKey == value) {
                                    return checkpoint_compliance[i].codeVlue;
                                }
                            }
                            return value;
                        }},
                        {title: '操作人', field: 'operatorName', width: 80, align: 'center', formatter: function (value) {
                            if (!value) {
                                return operator;
                            }
                            return value;
                        }},
                        {title: '操作', field: 'auditStatus', width: 100, align: 'center', formatter: function (value, row, index) {
                            return '<a href="#" style="color: red" onclick="deleteRow(this)">删除</a>';
                        }},
                        {title: '检查项主键ID', field: 'complianceCheckItemId', hidden: true}
                    ]
                ]
            });
            self_datagrid.datagrid('doCellTipSpecial',{cls:{'background-color':'#fafafa'},delay:100,showfield:"checkPoint",showContant:"checkPoint"});
            if (ret) {
                if (ret.self && ret.self.length > 0) {
                    self_datagrid.datagrid('loadData', ret.self);
                }
                /*自查备注*/
                if (ret.remark_self && ret.remark_self.length > 0) {
                    var maxIndex = ret.remark_self.length - 1;
                    $('#complianceCheckId').val(ret.remark_self[maxIndex].complianceCheckId);
                    if (ret.remark_self[maxIndex].checkRemark) {
                        checkRemark = ret.remark_self[maxIndex].checkRemark;
                        $('#remark_self').text(ret.remark_self[maxIndex].checkRemark);
                        $('#counter').html('还可以输入' + (200 - (ret.remark_self.length)) + '个字');
                    }
                }
            }
        });
    });
});

/*添加行*/
function addRow() {
    var rows = self_datagrid.datagrid('getRows');
    self_datagrid.datagrid('appendRow', {});
    self_datagrid.datagrid('beginEdit', rows.length - 1);
    self_datagrid.datagrid('selectRow', rows.length - 1);
}
/*删除行*/
function deleteRow(target) {
    self_datagrid.datagrid('deleteRow', getRowIndex(target));
}
/*保存营业部自查*/
function selfSave() {
    var rows = self_datagrid.datagrid('getRows');
    if ((rows.length <= 0) && (!$('#remark_self').val())) {
        $.messager.alert("提示", "请添加检查项或填写备注信息！");
        return;
    }
    for (var i = 0; i < rows.length; i++) {
        if (!self_datagrid.datagrid('validateRow', i)) {
            $.messager.alert("提示", "请将检查信息填写完整！", "info");
            return;
        } else {
            self_datagrid.datagrid('endEdit', i);
        }
    }
    var inserted = self_datagrid.datagrid('getChanges', "inserted");
    var deleted = self_datagrid.datagrid('getChanges', "deleted");
    var effectRow = new Object();
    /*var arrResult = {};
    for (var i = 0, n = inserted.length; i < n; i++) {
        var item = inserted[i];
        arrResult[ item.complianceObject + " - " + item.systemMaterial + " - " + item.checkPoint ] = item;
    }
    var i = 0, inserted_final = [];
    for (var item in arrResult) {
        inserted_final[i++] = arrResult[item];
    }
    if (inserted_final.length) {
        effectRow["inserted"] = JSON.stringify(inserted_final);
    }*/
    /*客户需求变更：同一次可添加重复检查点记录*/
    if (inserted.length) {
        effectRow["inserted"] = JSON.stringify(inserted);
    }
    if (deleted.length) {
        effectRow["deleted"] = JSON.stringify(deleted);
    }
    if (inserted.length == 0 && deleted.length == 0 && ($.trim($('#remark_self').val()) == checkRemark)) {
        $.messager.alert("提示", "未进行检查，无需保存！", "info");
        return;
    }
    $.messager.confirm('消息', '确定保存吗？', function (ret) {
        if (ret) {
            ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $.trim($('#remark_self').val()) + '&checkType=0&complianceCheckId=' + $('#complianceCheckId').val(), effectRow, function (ret) {
                if (ret.success) {
                    $.messager.alert("提示", "保存成功！");
                    //                        self_datagrid.datagrid('acceptChanges');
                    window.location.href = serverName + '/jsp/rc/compliance/compliance_self.jsp';
                } else {
                    $.messager.alert("提示", "保存失败！");
                    //                        self_datagrid.datagrid('rejectChanges');
                }
            });
        } else {
            /*self_datagrid.datagrid('rejectChanges');
             $('#remark_self').val(checkRemark);*/
        }
    });

}

function selfCancel() {
    window.location.href = serverName + '/jsp/rc/compliance/compliance_self.jsp';
}
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
/*获取行索引*/
function getRowIndex(target) {
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
