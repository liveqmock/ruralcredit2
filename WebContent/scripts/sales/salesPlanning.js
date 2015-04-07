$(function () {
    $("#year").combobox({
        valueField: "code",
        textField: "value",
        url: serverName + "/salesController/_getComboYear.do"
    });

    if (!from) {
        localStorage.removeItem('department');
        localStorage.removeItem('index_when_box');
    }

    if (sessionStorage.getItem('edit')) {
        sessionStorage.removeItem('edit');
    }
    if (localStorage.getItem('year')) {
        $('#year').combobox('setValue', localStorage.getItem('year'));
        $('#_year').val(localStorage.getItem('year'));
        localStorage.removeItem('year');
    } else {
        $('#year').combobox('setValue', new Date().getFullYear());
        $('#_year').val(new Date().getFullYear());
    }

    /*确保当前已编辑标签页数据已保存*/
    //$.fn.window.defaults.closable = false;
    //$.messager.defaults = {ok: '是', cancel: '否'};
    $('#sales_tab').tabs({
        'onSelect': function (title) {
            var index = $('#sales_tab').tabs('getTabIndex', $('#sales_tab').tabs('getTab', title));

            var box_name = role_to_search[me_js + index][0];
            if (box_name) {
                $('#box_name').html(box_name);
            }
            departmentTreeByType("areaDepartmentIds", role_to_search[me_js + index][1], role_to_search[me_js + index][2], localStorage.getItem('department'));

            var arr_index = getIndexsofSelections();
            if (arr_index[0] == index) {
                $('#' + role_to_tab[me_js + index]).tabs({
                    'onSelect': function (title) {
                        var _index = $('#' + role_to_tab[me_js + index]).tabs('getTab', title);
                        if (arr_index[1] != _index && sessionStorage.getItem('edit')) {
                            $.messager.confirm('提示', '<p style="width: 85%" align="center">是否保存当前修改？</p>', function (ret) {
                                if (ret) {
                                    //save
                                    sessionStorage.removeItem('edit');
                                    saveAll();
                                } else {
                                    //cancel save
                                    cancelEdit();
                                }
                            });
                        }
                    }
                });
            } else {
                if (sessionStorage.getItem('edit')) {
                    saveAll();
                }
            }
        }
    });

    /*取消后重新显示当前标签页*/
    var pIndex = sessionStorage.getItem('pIndex');
    var cIndex = sessionStorage.getItem('cIndex');
    if (pIndex && cIndex) {
        actions['select']([pIndex, cIndex]);
    }
    sessionStorage.removeItem('pIndex');
    sessionStorage.removeItem('cIndex');
});


/*
 *   操作：保存 编辑 取消 重填 选中
 */
var actions = {
    '00reset': resetFunc(['sales_tab_region_loan', 'sales_tab_subs_loan_counterparts', 'sales_tab_depart_loan_counterparts']),
    '01reset': resetFunc(['sales_tab_region_contract', 'sales_tab_subs_contract_counterparts', 'sales_tab_depart_contract_counterparts']),
    '10reset': resetFunc(['sales_tab_subs_loan']),
    '11reset': resetFunc(['sales_tab_subs_contract']),
    '20reset': resetFunc(['sales_tab_depart_loan']),
    '21reset': resetFunc(['sales_tab_depart_contract']),
    'select': selectFunc(),
    'edit': editFunc()
};

/*
 *   角色对应子标签页ID
 */
var role_to_tab = {
    'admin': 'sales_tab_region',
    'regionMgr': 'sales_tab_subs',
    'cityMgr': 'sales_tab_depart',
    'admin0': 'sales_tab_region',
    'admin00': 'sales_tab_region_loan',
    'admin01': 'sales_tab_region_contract',
    'regionMgr0': 'sales_tab_subs',
    'regionMgr00': 'sales_tab_subs_loan_counterparts',
    'regionMgr01': 'sales_tab_subs_contract_counterparts',
    'cityMgr0': 'sales_tab_depart',
    'cityMgr00': 'sales_tab_depart_loan_counterparts',
    'cityMgr01': 'sales_tab_depart_contract_counterparts',
    'admin1': 'sales_tab_subs',
    'admin10': 'sales_tab_subs_loan',
    'admin11': 'sales_tab_subs_contract',
    'admin2': 'sales_tab_depart',
    'admin20': 'sales_tab_depart_loan',
    'admin21': 'sales_tab_depart_contract'
};

/*
 *   角色对应标识，用于保存
 */
var role_to_saving = {
    'admin00': 'region_loan',
    'admin01': 'region_contract',
    'admin10': 'subs_loan',
    'admin11': 'subs_contract',
    'admin20': 'depart_loan',
    'admin21': 'depart_contract',
    'regionMgr00': 'subs_loan',
    'regionMgr01': 'subs_contract',
    'cityMgr00': 'depart_loan',
    'cityMgr01': 'depart_contract'
};

/*
 *   角色对应搜索框
 */
var role_to_search = {
    'admin0': ['请选择大区', 1, 'admin'],
    'admin1': ['请选择大区', 1, 'admin'],
    'admin2': ['请选择大区', 1, 'admin'],
    'regionMgr0': ['请选择分中心', 2, 'regionMgr'],
    'cityMgr0': ['请选择营业部', 3, 'cityMgr']
};

/*
 *   保存
 */
function saveAll() {
    $.messager.confirm('提示', '<p style="width: 85%" align="center">确定保存吗？</p>', function (ret) {
        if (ret) {
            //save
            $("#save").linkbutton("disable");
            $("#redo").linkbutton("disable");
            $("#reload").linkbutton("disable");
            $(".easyui-numberbox").addClass("myInputBorder");
            $(".easyui-numberbox").attr("readonly", "readonly");
            var arr_index = getIndexsofSelections();
            var form_parent = role_to_tab[me_js + arr_index[0] + arr_index[1]];
            var types = role_to_saving[me_js + arr_index[0] + arr_index[1]];
            ajaxSubmit(serverName + "/salesController/saveSalesPlanning.do", {
                'd': JSON.stringify($('#' + form_parent + ' form').serialize()),
                'types': types,
                'year': $('#_year').val()
            }, function (r) {
                memMe();
                $("#edit").linkbutton("enable");
                if (r.success) {
                    $.messager.show({
                        showType: "show",
                        timeout: 5000,
                        title: "消息",
                        msg: r.msg
                    });

                }
            });
        }
    });
}

/*
 *   编辑
 */
function edit() {
    var arr_index = getIndexsofSelections();
    actions['edit']([arr_index[0], arr_index[1]]);
    sessionStorage.setItem('edit', 'any');
}

/*
 *   取消
 */
function cancelEdit(type) {
    var cancelAction = function () {
        memMe();
        searchSalesPlan('', '', '');
    }
    if (type == 'cancelEdit') {
        $.messager.confirm('提示', '<p style="width: 85%" align="center">确定取消吗？</p>', function (ret) {
            if (ret) {
                cancelAction();
            }
        });
    } else {
        cancelAction();
    }

}

/*
 *   重填
 */
function clearSelected() {
    $.messager.confirm('提示', '您确定重填当前所有销售计划数据吗？', function (ret) {
        if (ret) {
            var arr_index = getIndexsofSelections();
            actions['' + arr_index[0] + arr_index[1] + 'reset']();
        }
    });
}
function resetFunc(tabArr) {
    function nest() {
        for (var i = 0; i < tabArr.length; i++) {
            if (tabArr[i]) {
                $('#' + tabArr[i] + ' form').trigger('reset');
            }
        }
    }

    return nest;
}
function selectFunc() {
    function nest(tabIndexArr) {
        $('#sales_tab').tabs('select', Number.parseInt(tabIndexArr[0]));
        $('#' + role_to_tab[[me_js] + tabIndexArr[0]]).tabs('select', Number.parseInt(tabIndexArr[1]));
    }

    return nest;
}
function editFunc() {
    function nest(tabIndexArr) {
        $("#save").linkbutton("enable");
        $("#redo").linkbutton("enable");
        $("#reload").linkbutton("enable");
        $("#edit").linkbutton("disable");
        $('#' + role_to_tab[[me_js] + tabIndexArr[0] + tabIndexArr[1]] + ' .myInputBorder').removeClass("myInputBorder").removeAttr("readonly");
    }

    return nest;
}

/*
 *   获取已选中父子标签ID
 */
function getIndexsofSelections() {
    //parent
    var tab = $('#sales_tab').tabs('getSelected');
    var index = $('#sales_tab').tabs('getTabIndex', tab);
    //child
    var _tab, _index;
    if (index == 0) {
        _tab = $('#' + role_to_tab[me_js]).tabs('getSelected');
        _index = $('#' + role_to_tab[me_js]).tabs('getTabIndex', _tab);
    } else if (index == 1) {
        _tab = $('#sales_tab_subs').tabs('getSelected');
        _index = $('#sales_tab_subs').tabs('getTabIndex', _tab);
    } else if (index == 2) {
        _tab = $('#sales_tab_depart').tabs('getSelected');
        _index = $('#sales_tab_depart').tabs('getTabIndex', _tab);
    }
    return [index, _index]
}
function memMe() {
    sessionStorage.clear();
    var arr_index = getIndexsofSelections();
    sessionStorage.setItem('pIndex', arr_index[0]);
    sessionStorage.setItem('cIndex', arr_index[1]);
}
function memYearAndDepartment() {
    localStorage.setItem('year', $('#year').combobox('getValue'));
    localStorage.setItem('department', $('#areaDepartmentIds').combobox('getValues'));
}

function searchSalesPlan(year, departmentIds, from) {
    var alreadyYear = $('#year').combobox('getValue');
    if (!year) {
        year = alreadyYear;
    }
    var alreadyIds = $('#areaDepartmentIds').combotree('getValues').join(',');
    if (!departmentIds) {
        if (alreadyIds) {
            departmentIds = alreadyIds;
        } else if (from) {
            $.messager.alert("提示", '请选择营业部', "warning");
            return;
        }
    }
    memMe();
    memYearAndDepartment();
    window.location.href = serverName + '/salesController/_salesPlanningTable.do?year=' + year + '&departmentIds=' + departmentIds;
}

/*
 *   分别获取（操作人-对应）大区、分中心、营业部
 */
function departmentTreeByType(id, type, role, selected) {
    ajaxSubmit(serverName + "/easyUIController/departmentTreeByType.do", {wantType: type, role: role}, function (r) {
        $("#" + id + "").combotree({
            data: r,
            multiple: true,
            editable: false,
            onlyLeafCheck: true,
            required: true,
            onLoadSuccess: function () {
                if (selected) {
                    $('#' + id).combotree('setValues', selected.split(','));
                }
            }
        });
    });
}
