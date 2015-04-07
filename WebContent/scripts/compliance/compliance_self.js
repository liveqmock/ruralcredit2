/**
 * Created by Administrator on 2014-8-5.
 */
$(function () {
    var ca_datagrid = $('#ca_list').datagrid(
        {
            url: serverName + '/ComplianceController/searchComplianceCheck.do?checkType=0&businessStatus=00,01',/*0-合规自查*/
            pagination: true,
            idfield: 'creditapplicationId',
            striped: true,
            singleSelect: true,
            sortName: 'businessNumber',
            sortOrder: 'asc',
            rownumbers: true,
            loadMsg: '正在加载...',
//            fitColumns: true,
            scrollbarSize:0,
            frozenColumns: [
                [
                    {
                        title: '操作',
                        field: 'operateFlag',
                        width: '150',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var hrefUrl = 'compliance_check_edit.jsp?creditApplicationId=' + row.CREDITAPPLICATION_ID + '&creditInvestigatioId=' + row.CREDIT_INVESTIGATIO_ID + '&type=self';
                            if (row.OPERATOR_NAME || row.COMPLIANCE_CHECK_ID) {
                                return "<a href='" + hrefUrl + "'>编辑合规自查</a>";
                            } else {
                                return "<a href='" + hrefUrl + "'>合规自查</a>";
                            }
                        }
                    }
                ]
            ],
            columns: [
                [
                    {
                        title: '业务单号',
                        field: 'BUSINESS_NUMBER',
                        width: '150'
                    },
                    {
                        title: '借款人',
                        field: 'BORROWER_NAME',
                        width: '150'
                    },
                    {
                        title: '借款金额（元）',
                        field: 'APPLY_LIMIT',
                        width: '150'
                    },
                    {
                        title: '客户经理',
                        field: 'LOAN_OFFICER_NAME',
                        width: '150'
                    },
                    {
                        title: '放款日期',
                        field: 'SIGNAGREEMENT_DATE',
                        width: '100',
                        hidden: 'true'
                    },
                    {
                        title: '资料提交人',
                        field: 'CREATE_LOAN_OFFICER_NAME',
                        width: '150',
                        hidden: 'true'
                    },
                    {
                        title: '分公司名称',
                        field: 'COMPANY_NAME',
                        width: '150'
                    },
                    {
                        title: '申请状态',
                        field: 'AUDIT_STATUS',
                        width: '120',
                        align: 'center'
                    },
                    {
                        title: '营业部自查扣分',
                        field: 'SCORE_SELF',
                        width: '155',
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
                        hidden: true
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
                        title: '最新检查人',
                        field: 'OPERATOR_NAME',
                        width: '100',
                        hidden: true
                    }
                ]
            ]
        }
    );

    /*调整 datagrid 高度*/
    var centerPanelHeight = $('#center_panel')[0].clientHeight;
    $('#compliance_check_tab').tabs({
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
    $('.datagrid-header-inner .datagrid-cell').css("text-align","center");
    /*分公司树形菜单*/
    departmentComboboxTreeWithPanelWidth("branch_name", false, 250);
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
    $('#ca_list').datagrid('load',
        {
            branchName: $.trim($('#branch_name').combotree('getValues')),
            businessNumber: $.trim($('#business_number').val()),
            accountManager: $.trim($('#account_manager').val()),
            borrowerMan: $.trim($('#borrower_man').val())
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