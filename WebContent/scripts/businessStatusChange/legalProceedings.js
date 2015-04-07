var bsc_dataGrid;
var centerPanelWidth;
var centerPanelHeight;

$(function () {

    //营业部 初始化
    departmentComboboxTreeWithPanelWidth("branchName",false,250);

    bsc_dataGrid = $('#bsc').datagrid({
        url: serverName + '/businessStatusChangeController/viewLegalProceedings.do',
        fitColumns: false,
        loadMsg: '正在加载....',
        height: 370,
        rownumbers: true,
        pagination:true,
        singleSelect:true,
        frozenColumns: [
            [
                {
                    title: '操作', field: 'CREDITAPPLICATION_ID', width: 155, align: 'left', formatter: operationsFunc
                }
            ]
        ],
        columns: [
            [
                {
                    title: '业务单号', field: 'BUSINESS_NUMBER', width: 200, align: 'left'
                },
                {
                    title: '借款人姓名', field: 'NAME', width: 200, align: 'left'
                },
                {
                    title: '分公司名称', field: 'COMPANY_NAME', width: 200, align: 'left'
                },
                {
                    title: '客户经理', field: 'LOAN_OFFICER_NAME', width: 200, align: 'left'
                },
                {
                    title: '放款日期', field: 'SIGNAGREEMENT_DATE', width: 200, align: 'left'
                },
                {
                    title: '放款金额（元）', field: 'AMOUNT', width: 200, align: 'left'
                },
                {
                    title: '产品类型', field: 'REPAYMENT_PLAN_NAME', width: 200, align: 'left'
                },
                {
                    title: '操作人', field: 'OPERATOR', width: 200, align: 'left'
                },
                {
                    title: '操作时间', field: 'OPERATE_DATE', width: 200, align: 'left'
                },
                {
                    title: '风险经理', field: 'CREDIT_INVESTIGATIO_ID', width: 200, align: 'left', hidden:true
                }
            ]
        ]
    });

    //调整 datagrid 高度
    centerPanelWidth = $('#centerPanel')[0].clientWidth;
    centerPanelHeight = $('#centerPanel')[0].clientHeight;
    $('#bscTab').tabs({
        onSelect: function (title, index) {
            var tabDiv1 = $('#tabDiv1')[0].clientHeight;
            if (tabDiv1 != 0) {
                bsc_dataGrid.datagrid('resize', {
                    height: centerPanelHeight - tabDiv1 - 90
                });
            }
        }
    });
});

//变更业务状态
function changeBusinessStatus(tab,creditApplicationId,changeId,businessStatus,attachmentId) {
    if(!changeId){
        changeId = 0;
    }
    if(attachmentId == 'null'){
        attachmentId = 0;
    }
    $('#changeFrame')[0].src = serverName + '/businessStatusChangeController/initChange.do?' +
        'creditapplicationId='+creditApplicationId+
        '&type='+tab+
        '&changeId='+changeId+
        '&beforeStatus='+businessStatus+
        '&attachmentId='+attachmentId;
    //调整弹窗高度高度以适应页面
    var w = 900,h = 530;
    if ((tab == '不良贷款' && businessStatus == '不良贷款') || (tab == '法律诉讼' && businessStatus == '法律诉讼')) {
        //不良贷款/法律诉讼变更回操作前状态，仅显示备注
        w = 666;
        h = 300;
    }
    $("#changeDiv").dialog({
        title: "变更原因",
        close: true,
        modal: true,
        draggable: true,
        cache: false,
        width: w,
        height: h,
        onClose: function () {
            $("#changeFrame").attr('src', "");
        }
    });
    $('#changeDiv').dialog("open");
}
/*更改：分公司名称下拉选择框为两级树形菜单
//营业部初始化
function initDepartmentComboTree(id) {
    var $id = $("#" + id);
    $id.combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        onSelect: function (note) {
            var children = $id.tree("getChildren", note.target);
            if (children && children.length > 0) {
                $.messager.alert('提示', '请选择分公司', 'info');
                $id.combotree('clear');
                return;
            }
        }
    });
}*/

//取消按钮-关闭弹窗
function cancelFollow(){
    $('#registerInquireViewDiv_follow').dialog('close');
}

//保存跟进记录
function saveFollow() {
    window.frames['registerInquireViewIframe_follow'].contentWindow._saveFollow();
}

//诉讼列表-刷新
function reloadLegalProceedings(){
    bsc_dataGrid.datagrid('reload');
}

//按钮控制-防止网络延迟造成多次数据提交
function controlBtn(enableOrDisable){
    $('#saveBtn').linkbutton(enableOrDisable);
}

//搜索-按条件
function searchLegalProceedings() {
    var branchNames = $("#branchName").combotree("getValues").join(",");
    bsc_dataGrid.datagrid('load', {businessNumber: $('#businessNumber').val(),
        borrowerName: $("#borrowerName").val(), branchName: branchNames});
}

//搜索-清空
function clearLegalProceedings(){
    $('#businessNumber').val('');
    $("#borrowerName").val('');
//    $('#branchName').combobox('setText','');
    $("#branchName").combotree("setValues","");
    searchLegalProceedings();
}