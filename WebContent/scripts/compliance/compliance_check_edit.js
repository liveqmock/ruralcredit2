/**
 * Created by Administrator on 2014-8-7.
 */
var centerTabs;
$(function () {
    /*初始化标签页*/
    centerTabs = $("#allPeopel").tabs({
        fit: true,
        plain: true
    });

    /*初始化标签页数据*/
    initData();

    /*绑定选中事件*/
    $("#allPeopel").tabs({
        onSelect: function (title) {
            var guarantorIdsArr = $('#guarantorIds').val().split(',');
            for (var j = 1; j <= guarantorIdsArr.length; j++) {
                if (title == '担保人' + j) {
                    showDanbao(title, guarantorIdsArr[j - 1], j);
                }
            }
        }
    });
});

/*初始化数据*/
function initData() {

    /*申请单信息*/
    ajaxSubmit(serverName + '/ComplianceController/initData.do?creditApplicationId=' + creditApplicationId, {}, 'post', function (ret) {
            if (ret) {
                $('#borrowerServiceAppId').val(ret.borrowerServiceAppId);
                $('#creditapplicationId').val(ret.creditapplicationId);
                showCreditApp();
            } else {
                console.info('获取借款信息时，发生错误。');
            }
        }, false
    );

    /*担保人信息*/
    ajaxSubmit(serverName + '/borrowerServiceApp/selectByBorrowerInfoVo.do?leader=0&creditapplicationId=' + creditApplicationId, {}, 'post', function (ret) {
            if (ret) {
                var guarantorIdsArr = [], guarantorAttachesArr = [];
                for (var i = 1; i <= ret.length; i++) {
                    $('#allPeopel').tabs('add', {
                        title: '担保人' + i
                    });
                    guarantorIdsArr.push(ret[i - 1].borrowerServiceAppId);
                    guarantorAttachesArr.push(ret[i - 1].borrowerServiceAppDESId);
                }
                $('#guarantorIds').val(guarantorIdsArr.join(','));
                $('#borrowerServiceAppDESIds').val(guarantorAttachesArr.join(','));

                /*信用及背景调查报告*/
                $('#allPeopel').tabs('add', {
                    title: '信用及背景调查报告'
                });
                showFxInfo();
            } else {
                console.info('获取担保人信息时，发生错误。');
            }
        }, false
    );
}

/*显示申请单*/
function showCreditApp() {
    $("#allPeopel").tabs("getTab", "申请单信息").panel({
        cache: true,
        href: serverName + "/application/showCreditApp.do?borrowerServiceAppId=" + $("#borrowerServiceAppId").val() + "&showAttach=yes"
    });
}

/*显示担保人*/
function showDanbao(tabTitle, borrowerServiceAppId, guarantorOrderNum) {
    $("#allPeopel").tabs("getTab", tabTitle).panel({
        cache: true,
        href: serverName + "/guaranorProfile/guaranorProfileView.do?param=" + borrowerServiceAppId + "&showAttach=yes" + "&orderNum=" + guarantorOrderNum
    });
}

/*显示信用及背景调查报告*/
function showFxInfo() {
    $("#allPeopel").tabs("getTab", "信用及背景调查报告").panel({
        cache: true,
        href: serverName + "/creditInvestigation/toViewCreditInvestigation.do?creditInvestigationId=" + creditInvestigatioId + "&showAttach="+checkType
    });
}

/*查看审批单附件*/
function viewApplyBillAttach() {
    window.addTabFun({
        src: cmUrl + "/operation/transferParameter.action?clientId=" + creditApplicationDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529",
        title: "查看申请单附件"
    });
}

/*查看担保人附件*/
function viewguarantorAttach(guarantorOrderNum) {
    var guarantorAttachesArr = $('#borrowerServiceAppDESIds').val().split(',');
    window.addTabFun({
        src: cmUrl + "/operation/transferParameter.action?clientId=" + guarantorAttachesArr[guarantorOrderNum - 1] + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529",
        title: "查看担保人" + guarantorOrderNum + "附件"
    });
}

/*查看信用及背景调查报告附件*/
function viewApprovalBillAttach() {
    window.addTabFun({
        src: cmUrl + "/operation/transferParameter.action?clientId=" + approvalDESId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
            + "&signIp=" + DESIp + "",
        title: "查看审批单附件"
    });
}

function addTabFun(opts) {
    var options = $.extend({
        title: '',
        content: '<iframe marginwidth="0" id="' + opts.id + '" marginheight="0" src="' + opts.src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
        closable: true,
        iconCls: '',
        fit: true
    }, opts);
    if (!centerTabs.tabs('exists', options.title)) {
        centerTabs.tabs('add', options);
    } else {
        centerTabs.tabs('close', options.title);
        centerTabs.tabs('add', options);
    }
};
