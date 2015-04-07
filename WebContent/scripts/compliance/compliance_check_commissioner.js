/**
 * Created by Administrator on 2014-8-11.
 */
var self_datagrid;
var customer_datagrid;
var commissioner_datagrid;

/*合规检查：获取数据字典：对象、系统资料、检查点*/
var sections = 'commissionerCheck';
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

        /*初始化数据：查询三种类型检查记录*/
        ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
            self_datagrid = $('#check_self').datagrid({
                fitColumns: true,
                singleSelect: true,
                scrollbarSize: 0,
                columns: [
                    [
                        {title: '对象', field: 'complianceObject', width: 80, align: 'center'},
                        {title: '系统资料', field: 'systemMaterial', width: 200, align: 'center'},
                        {title: '检查点', field: 'checkPoint', width: 300, align: 'center'},
                        {title: '操作人', field: 'operatorName', width: 80, align: 'center'},
                        {title: '操作时间', field: 'operateDate', width: 100, align: 'center'},
                        {title: '主键ID', field: 'complianceCheckItemId', hidden: true}
                    ]
                ]
            });
            self_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
            if (ret) {
                if (ret.self && ret.self.length > 0) {
                    self_datagrid.datagrid('loadData', ret.self);
                } else {
//                    $('#div_self_outer').hide();
                }
                /*自查备注*/
                if (ret.remark_self && ret.remark_self.length > 0 && ret.remark_self[0].checkRemark) {
                    $('#remark_self').text(ret.remark_self[0].checkRemark);
                }
                customer_datagrid = $('#check_customer').datagrid({
                    fitColumns: true,
                    singleSelect: true,
                    scrollbarSize: 0,
                    columns: [
                        [
                            {title: '错误文件', field: 'complianceObject', width: 100, align: 'center'},
                            {title: '检查点', field: 'checkPointLoan', width: 200, align: 'center'},
                            {title: '操作人', field: 'operatorName', width: 100, align: 'center'},
                            {title: '操作时间', field: 'operateDate', width: 150, align: 'center'},
                            {title: '主键ID', field: 'complianceCheckItemId', hidden: true}
                        ]
                    ]
                });
                customer_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
                if (ret.customer && ret.customer.length > 0) {
                    customer_datagrid.datagrid('loadData', ret.customer);
                } else {
//                    $('#div_customer_outer').hide();
                }
                /*客服备注*/
                if (ret.remark_customer && ret.remark_customer.length > 0) {
                    for (var i = 0; i < ret.remark_customer.length; i++) {
                        $('#remark_customer').html($('#remark_customer').html() + (i + 1) + '.' + ret.remark_customer[i].checkRemark + '<br/>');
                    }
                }
                commissioner_datagrid = $('#check_commissioner').datagrid({
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
                                            /*0-申请人&共同借款人 1-担保人 2-系统 3-其他 4-业务表格*/
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
                                            var target = commissioner_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'systemMaterial' }).target;
                                            target.combobox('clear');
                                            var url = serverName + '/ComplianceController/getDataDictionaryBySectionAndParentId.do?section=' + dynamicSystemMaterial + '&parentId=' + rowData.codaTableId;
                                            target.combobox('reload', url);
                                            var _target = commissioner_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'checkPoint' }).target;
                                            _target.combobox('clear');
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
                                {title: '系统资料', field: 'systemMaterial', width: 100, align: 'center', editor: {
                                    type: 'combobox',
                                    options: {
                                        valueField: 'codeKey',
                                        textField: 'codeVlue',
                                        data: '',
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
                                            var target = commissioner_datagrid.datagrid('getEditor', { 'index': getRowIndex(this), 'field': 'checkPoint' }).target;
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
                                }
                                },
                                {title: '检查点', field: 'checkPoint', width: 200, align: 'center', editor: {
                                    type: 'combobox',
                                    options: {
                                        valueField: 'codeKey',
                                        textField: 'codeVlue',
                                        data: '',
                                        required: true,
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
                                }
                                },
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
                    }
                );
                commissioner_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
                if (ret.commissioner && ret.commissioner.length > 0) {
                    commissioner_datagrid.datagrid('loadData', ret.commissioner);
                }
                /*合规备注*/
                if (ret.remark_commissioner && ret.remark_commissioner.length > 0) {
                    var maxIndex = ret.remark_commissioner.length - 1;
                    $('#complianceCheckId').val(ret.remark_commissioner[maxIndex].complianceCheckId);
                    if (ret.remark_commissioner[maxIndex].checkRemark) {
                        checkRemark = ret.remark_commissioner[maxIndex].checkRemark;
                        $('#remark_commissioner').text(ret.remark_commissioner[maxIndex].checkRemark);
                        $('#counter').html('还可以输入' + (200 - (ret.remark_commissioner.length)) + '个字');
                    }
                }
            }
        });
    });
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
/*添加行*/
function addRow() {
    var rows = commissioner_datagrid.datagrid('getRows');
    commissioner_datagrid.datagrid('appendRow', {});
    commissioner_datagrid.datagrid('beginEdit', rows.length - 1);
    commissioner_datagrid.datagrid('selectRow', rows.length - 1);
}
/*删除行*/
function deleteRow(target) {
    commissioner_datagrid.datagrid('deleteRow', getRowIndex(target));
}
/*保存合规检查*/
function commissionSave() {
    var rows = commissioner_datagrid.datagrid('getRows');
    if ((rows.length <= 0) && (!$('#remark_commissioner').val())) {
        $.messager.alert("提示", "请添加检查项或填写备注信息！");
        return;
    }
    for (var i = 0; i < rows.length; i++) {
        if (!commissioner_datagrid.datagrid('validateRow', i)) {
            $.messager.alert("提示", "请将检查信息填写完整！", "info");
            return;
        } else {
            commissioner_datagrid.datagrid('endEdit', i);
        }
    }
    var inserted = commissioner_datagrid.datagrid('getChanges', "inserted");
    var deleted = commissioner_datagrid.datagrid('getChanges', "deleted");
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
    if (inserted.length == 0 && deleted.length == 0 && ($.trim($('#remark_commissioner').val()) == checkRemark)) {
        $.messager.alert("提示", "未进行检查，无需保存！", "info");
        return;
    }
    $.messager.confirm('消息', '确定保存吗？', function (ret) {
        if (ret) {
            ajaxSubmit(serverName + '/ComplianceController/saveComplianceCheck.do?creditApplicationId=' + creditApplicationId + '&checkRemark=' + $.trim($('#remark_commissioner').val()) + '&checkType=1&complianceCheckId=' + $('#complianceCheckId').val(), effectRow, function (ret) {
                if (ret.success) {
                    $.messager.alert("提示", "保存成功！");
                    //commissioner_datagrid.datagrid('acceptChanges');
                    //根据主页面获取的请求参数 拼接页面关闭返回回显的URL并重定向
                    var hrefUrl = serverName + '/jsp/rc/compliance/compliance_check.jsp?'+'&pageNumber='+pageNumber+'&tab_index='+tab_index+'&fuzzy='+fuzzy+'&branch_name='+branch_name+'&business_number='+business_number
                    +'&material_man='+material_man+'&loan_begin='+loan_begin+'&loan_end='+loan_end+'&borrower_man='+borrower_man; 
                    //console.info(hrefUrl);
                    window.location.href = hrefUrl;
                } else {
                    $.messager.alert("提示", "保存失败！");
                    // commissioner_datagrid.datagrid('rejectChanges');
                }
            });
        } else {
            // commissioner_datagrid.datagrid('rejectChanges');
        }
    });
}
function commissionCancel() {
   // window.location.href = serverName + '/jsp/rc/compliance/compliance_check.jsp';
	//根据主页面获取的请求参数 拼接页面关闭返回回显的URL并重定向
    var hrefUrl = serverName + '/jsp/rc/compliance/compliance_check.jsp?'+'&pageNumber='+pageNumber+'&tab_index='+tab_index+'&fuzzy='+fuzzy+'&branch_name='+branch_name+'&business_number='+business_number
    +'&material_man='+material_man+'&loan_begin='+loan_begin+'&loan_end='+loan_end+'&borrower_man='+borrower_man; 
    //console.info(hrefUrl);
    window.location.href = hrefUrl;
}
/*获取行索引*/
function getRowIndex(target) {
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}

