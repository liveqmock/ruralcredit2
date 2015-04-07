var bsc_dataGrid;
var centerPanelWidth;
var centerPanelHeight;
var tabTitle;

$(function () {
    bsc_dataGrid = $('#bsc').datagrid({
        url: serverName + '/businessStatusChangeController/selectChangeLog.do',
        fitColumns: true,
        loadMsg: '正在加载....',
        height: 370,
        rownumbers: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        striped: true,
        singleSelect: true,
        scrollbarSize:0,
        columns: [
            [
                {
                    title: '业务编码', field: 'BUSINESS_NUMBER', width: 200, align: 'left', formatter: function (value, row, index) {
                    return "<a href='#' onclick='viewDetailByNumber(\"" + tabTitle + "\",\"" + value + "\")'>" + value + "</a>";
                }
                },
                {
                    title: '操作人', field: 'OPERATOR', width: 200, align: 'left'
                },
                {
                    title: '操作时间', field: 'OPERATE_DATE', width: 200, align: 'left'
                },
                {
                    title: '操作前状态', field: 'BEFORE_STATUS', width: 200, align: 'left'
                },
                {
                    title: '操作后状态', field: 'AFTER_STATUS', width: 200, align: 'left'
                },
                {
                    title: '变更原因', field: 'CHANGE_REASON', width: 200, align: 'left'
                },
                {
                    title: '备注', field: 'CHANGE_REMARK', width: 200, align: 'left'
                },
                {
                    title: '上传文件', field: 'ATTATCHMENT_ID', width: 200, align: 'left',formatter:function(value,row,index){
                    if(!value){
                        return '';
                    }
                    return "<a href='#' style='color: blue' onclick='viewAttachment(\"" + value + "\")'>查看</a>";
                }
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

            /*法律诉讼去除变更原因*/
            if (title == '不良贷款') {
                tabTitle = 'nonPerformingLoans';
                $('#bsc').datagrid('showColumn','CHANGE_REASON');
            } else if (title == '法律诉讼') {
                tabTitle = 'legalProceedings';
                $('#bsc').datagrid('hideColumn','CHANGE_REASON');
            }
            bsc_dataGrid.datagrid('load', {groupNumber: '', type: tabTitle});
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

//查看附件
function viewAttachment(attatchmentId) {
    var href = cmUrl + "/operation/transferParameter.action?clientId=" + attatchmentId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
        + "&signIp=" + DESIp + "";
    window.open(href);
}

//查看详情
function viewDetailByNumber(tabTitle,number) {
    if(tabTitle == '不良贷款'){
        tabTitle = 'nonPerformingLoans';
    } else if(tabTitle == '法律诉讼'){
        tabTitle = 'legalProceedings';
    }
    $("#registerInquireViewIframe")[0].src = basePath + "jsp/rc/businessStatusChange/viewStatusChangeDetail.jsp?number=" + number + "&tabTitle=" + tabTitle;
    $("#registerInquireViewDiv").dialog({
        title: "查看变更记录",
        buttons: [
            {id: "registerInquireViewButton", text: "关闭", handler: function () {
                $("#registerInquireViewDiv").dialog("close");
            }}
        ],
        closed: true,
        draggable: true,
        width: $('#centerPanel')[0].clientWidth - 100,
        height: 300,
        onClose: function () {
            $("#registerInquireViewIframe").attr('src', "");
        }
    });
    $("#registerInquireViewDiv").dialog("open");
}