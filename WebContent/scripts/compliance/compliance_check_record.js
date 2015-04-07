/**
 * Created by Administrator on 2014-8-5.
 */
$(function () {
    var ca_datagrid = $('#ca_list').datagrid(
        {
            url: serverName + '/ComplianceController/searchComplianceCheck.do?checkType=99',
            pagination: true,
            idfield: 'creditapplicationId',
            striped: true,
            singleSelect: true,
            sortName: 'signagreementDate',
            sortOrder: 'desc',
            rownumbers: true,
            loadMsg: '正在加载...',
            frozenColumns: [
                [
                    {
                        title: '操作',
                        field: 'operateFlag',
                        width: '120',
                        align: 'left',
                        formatter: someOperation
                    }
                ]
            ],
            columns: [
                [
                    {
                        title: '业务单号',
                        field: 'BUSINESS_NUMBER',
                        width: '170'
                    },
                    {
                        title: '借款人',
                        field: 'BORROWER_NAME',
                        width: '80'
                    },
                    {
                        title: '借款金额（元）',
                        field: 'APPLY_LIMIT',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '放款日期',
                        field: 'SIGNAGREEMENT_DATE',
                        width: '90',
                        align: 'center'
                    },
                    {
                        title: '客户经理',
                        field: 'LOAN_OFFICER_NAME',
                        width: '110'
                    },
                    {
                        title: '资料提交人',
                        field: 'CREATE_LOAN_OFFICER_NAME',
                        width: '110'
                    },
                    {
                        title: '分公司名称',
                        field: 'COMPANY_NAME',
                        width: '150'
                    },
                    {
                        title: '业务状态',
                        field: 'AUDIT_STATUS',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '营业部自查扣分',
                        field: 'SCORE_SELF',
                        width: '90',
                        align: 'center',
                        formatter: function (v) {
                            if (v) {
                                return v.toFixed(1);
                            }
                        }
                    },
                    {
                        title: '客服检查扣分',
                        field: 'SCORE_CUSTOMER',
                        width: '80',
                        align: 'center',
                        formatter: function (v) {
                            if (v) {
                                return v.toFixed(1);
                            }
                        }
                    },
                    {
                        title: '内控合规检查扣分',
                        field: 'SCORE_OTHER',
                        width: '100',
                        align: 'center',
                        formatter: function (v) {
                            if (v) {
                                return v.toFixed(1);
                            }
                        }
                    },
                    {
                        title: '风险经理ID',
                        field: 'CREDIT_INVESTIGATIO_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '信贷申请ID',
                        field: 'CREDITAPPLICATION_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '合规检查ID',
                        field: 'COMPLIANCE_CHECK_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '申诉状态',
                        field: 'COMPLAINT_STATUS',
                        width: '115',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.CUSTOMER_COMMISSIONER) {
                                if (!value) {
                                    return '未申诉';
                                }
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        title: '申诉ID',
                        field: 'COMPLAINT_ID',
                        width: '3',
                        hidden: true
                    }
                ]
            ]
        }
    );

    /*调整 datagrid 高度*/
    var centerPanelHeight = $('#center_panel')[0].clientHeight;
    $('#compliance_check_record_tab').tabs({
        onSelect: function (title, index) {
            var tab_div1 = $('#tab_div1')[0].clientHeight;
            var tab_div2 = $('#tab_div2')[0].clientHeight;
            if (tab_div1 != 0) {
                ca_datagrid.datagrid('resize', {
                    height: centerPanelHeight - tab_div1 - 110
                });
            } else if (tab_div2 != 0) {
                ca_datagrid.datagrid('resize', {
                    height: centerPanelHeight - tab_div2 - 110
                });
            }
        }
    });
    $('.datagrid-header-inner .datagrid-cell').css("text-align", "center");
    /*分公司树形菜单*/
    departmentComboboxTreeWithPanelWidth("branch_name", false, 312);
    /*申诉状态*/
    var dic = new DataDictionary();
    dic.addDic("complaintStatus", "compliance_complaint_status");
    dic.dicAjax();
});

function fuzzySearch() {
    var v = $('#fuzzy').val();
    $('#ca_list').datagrid('load', {fuzzyValue: $.trim(v)});
}

function clearFuzzyBox() {
    $('#fuzzy').val('');
    fuzzySearch();
}

function search_advanced() {
    var loan_begin = $('#loan_begin').datebox('getValue');
    var loan_end = $('#loan_end').datebox('getValue');
    if (loan_begin && loan_end && loan_begin > loan_end) {
        $.messager.alert('提示', '开始日期大于结束日期,请重新选择日期');
        return;
    }
    $('#ca_list').datagrid('load',
        {
            branchName: $.trim($('#branch_name').combotree('getValues')),
            businessNumber: $.trim($('#business_number').val()),
            materialMan: $.trim($('#material_man').val()),
            loanBegin: $('#loan_begin').datebox('getValue'),
            loanEnd: $('#loan_end').datebox('getValue'),
            complaintStatus: $('#complaintStatus').combobox('getValue')
        });
}

function clear_advanced() {
    $("#branch_name").combotree("setValues", "");
    $("#loan_begin").datebox("clear");
    $("#loan_end").datebox("clear");
    $('#searchForm').form('clear');
    search_advanced();
}
function departmentComboboxTreeWithPanelWidth(id, required, panelWidth) {
    ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function (r) {
        $("#" + id + "").combotree({
            data: r,
            multiple: true,
            editable: false,
            required: required,
            panelWidth: panelWidth,
            onCheck: function (record) {
            }
        });
    });
}
function exportData(type) {
    var filterData = '&branchName=' + $.trim($('#branch_name').combotree('getValues')) + '&businessNumber=' + $.trim($('#business_number').val()) + '&materialMan=' + $.trim($('#material_man').val()) + '&loanBegin=' + $('#loan_begin').datebox('getValue') + '&loanEnd=' + $('#loan_end').datebox('getValue');
    if (type == 'commissioner') {
        window.location.href = serverName + '/ComplianceController/exportCheckDetail.do?checkType=1' + filterData;
    } else if (type == 'customer') {
        window.location.href = serverName + '/ComplianceController/exportCheckDetail.do?checkType=2' + filterData;
    } else if (type == 'statistics') {
        window.location.href = serverName + '/ComplianceController/exportStatistics.do?checkType=1&businessStatus=15,29,30' + filterData;
    }
}
/*查看明细*/
function viewDetail() {
    var rowData = $("#ca_list").datagrid("getSelected");
    window.parent.addTabFun({
        src: serverName + '/jsp/rc/compliance/compliance_check_record_detail.jsp?creditApplicationId=' + rowData.CREDITAPPLICATION_ID + '&complaintStatus=' + rowData.COMPLAINT_STATUS + '&complaintContent=' + rowData.COMPLAINT_CONTENT + '&approveContent=' + rowData.APPROVE_CONTENT,
        title: "查看明细：" + rowData.BUSINESS_NUMBER
    });
}
/*申诉、申诉审批*/
function complianceComplaint(opType, complaintContent) {
    var rowData = $("#ca_list").datagrid("getSelected");
    /*var title = (opType == 'complaining' ? '申诉' : '申诉审批');
     window.parent.addTabFun({
     src: serverName + '/jsp/rc/compliance/compliance_check_complaint.jsp?creditApplicationId=' + rowData.CREDITAPPLICATION_ID + '&opType=' + opType + '&complaintId=' + rowData.COMPLAINT_ID,
     title: title
     });*/
    var complaintContent = '';
    if (rowData.COMPLAINT_CONTENT) {
        complaintContent = rowData.COMPLAINT_CONTENT;
    }
    window.location = serverName + '/jsp/rc/compliance/compliance_check_complaint.jsp?creditApplicationId=' + rowData.CREDITAPPLICATION_ID + '&opType=' + opType + '&complaintId=' + rowData.COMPLAINT_ID + '&complaintContent=' + complaintContent;
}

