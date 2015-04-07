var bsc_dataGrid;
var centerPanelWidth;
var centerPanelHeight;
var tabTitle;

$(function () {
    bsc_dataGrid = $('#bsc').datagrid({
        url: serverName + '/businessStatusChangeController/searchBusinessStatusChange.do',
        fitColumns: true,
        loadMsg: '正在加载....',
        height: 370,
        rownumbers: true,
        scrollbarSize:0,
        frozenColumns: [
            [
                {
                    title: '操作', field: 'CREDITAPPLICATION_ID', width: 200, align: 'left', formatter: function (value, row, index) {
                    if(tabTitle == '不良贷款'){
                        if(row.BUSINESS_STATUS == '还款中' || row.BUSINESS_STATUS == '不良贷款'){
                            return "<a href='#' onclick='changeBusinessStatus(\"" + tabTitle + "\",\"" + row.CREDITAPPLICATION_ID + "\"," +
                                "\"" + row.CHANGE_ID + "\",\"" + row.BUSINESS_STATUS + "\",\"" + row.ATTATCHMENT_ID + "\")'>变更</a>";
                        }
                    }else if(tabTitle == '法律诉讼'){
                        if(row.BUSINESS_STATUS == '还款中' || row.BUSINESS_STATUS == '法律诉讼'){
                            return "<a href='#' onclick='changeBusinessStatus(\"" + tabTitle + "\",\"" + row.CREDITAPPLICATION_ID + "\"," +
                                "\"" + row.CHANGE_ID + "\",\"" + row.BUSINESS_STATUS + "\",\"" + row.ATTATCHMENT_ID + "\")'>变更</a>";
                        }
                    }
                    /*if(row.BUSINESS_STATUS == '还款中' || row.BUSINESS_STATUS == '法律诉讼' || row.BUSINESS_STATUS == '不良贷款'){
                        return "<a href='#' onclick='changeBusinessStatus(\"" + tabTitle + "\",\"" + row.CREDITAPPLICATION_ID + "\"," +
                            "\"" + row.CHANGE_ID + "\",\"" + row.BUSINESS_STATUS + "\",\"" + row.ATTATCHMENT_ID + "\")'>变更</a>";
                    }*/
                }
                }
            ]
        ],
        columns: [
            [
                {
                    title: '业务编码', field: 'BUSINESS_NUMBER', width: 200, align: 'left'
                },
                {
                    title: '操作人', field: 'OPERATOR', width: 200, align: 'left'
                },
                {
                    title: '操作时间', field: 'OPERATE_DATE', width: 200, align: 'left'
                },
                {
                    title: '业务状态', field: 'BUSINESS_STATUS', width: 200, align: 'left'
                },
                {
                    title: '变更原因', field: 'CHANGE_REASON', width: 200, align: 'left'
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
            var tabDiv2 = $('#tabDiv2')[0].clientHeight;
            if (tabDiv1 != 0) {
                bsc_dataGrid.datagrid('resize', {
                    height: centerPanelHeight - tabDiv1 - 90
//                    width: centerPanelWidth - 10
                });
            } else if (tabDiv2 != 0) {
                bsc_dataGrid.datagrid('resize', {
                    height: centerPanelHeight - tabDiv2 - 90
//                    width: centerPanelWidth - 10
                });
            }
            tabTitle = title;
            bsc_dataGrid.datagrid('load',{groupNumber:''});

            /*法律诉讼去除变更原因*/
            if(tabTitle == '法律诉讼'){
                $('#bsc').datagrid('hideColumn','CHANGE_REASON');
            }else if(tabTitle == '不良贷款'){
                $('#bsc').datagrid('showColumn','CHANGE_REASON');
            }
        }
    });
});

//search
function searchBusStaChange(type) {
    if (type == 'nonPerformingLoans') {
        bsc_dataGrid.datagrid('load', {groupNumber: $("#businessNum_first").val(), type: type});
    } else {
        type = 'legalProceedings';
        bsc_dataGrid.datagrid('load', {groupNumber: $("#businessNum_second").val(), type: type});
    }
}

//clear
function clearBusinessNum(type) {
    if (type == 'nonPerformingLoans') {
        $("#businessNum_first").val("");
    } else {
        $("#businessNum_second").val("");
    }
}

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

//保存变更
function saveChange() {
    changeFrame.window.saveChangeReason();
}

//取消变更
function cancelChange() {
    $('#changeDiv').dialog("close");
}
