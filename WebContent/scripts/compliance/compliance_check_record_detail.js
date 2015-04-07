/**
 * Created by Administrator on 2014-8-11.
 */
var self_datagrid;
var customer_datagrid;
var commissioner_datagrid;

$(function () {
    /*初始化数据：查询三种类型检查记录*/
    ajaxSubmit(serverName + '/ComplianceController/searchComplianceCheckRecord.do?creditApplicationId=' + creditApplicationId, {}, function (ret) {
        self_datagrid = $('#check_self').datagrid({
            scrollbarSize: 0,
            fitColumns: true,
            singleSelect: true,
            columns: [
                [
                    {title: '对象', field: 'complianceObject', width: 80, align: 'center'},
                    {title: '系统资料', field: 'systemMaterial', width: 200, align: 'center'},
                    {title: '检查点', field: 'checkPoint', width: 300, align: 'center'},
                    {title: '操作人', field: 'operatorName', width: 80, align: 'center'},
                    {title: '操作时间', field: 'operateDate', width: 100, align: 'center'}
                ]
            ]
        });
        self_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
        customer_datagrid = $('#check_customer').datagrid({
            scrollbarSize: 0,
            fitColumns: true,
            singleSelect: true,
            columns: [
                [
                    {title: '错误文件', field: 'errorFile', width: 100, align: 'center'},
                    {title: '检查点', field: 'checkPointLoan', width: 200, align: 'center'},
                    {title: '操作人', field: 'operatorName', width: 100, align: 'center'},
                    {title: '操作时间', field: 'operateDate', width: 150, align: 'center'}
                ]
            ]
        });
        customer_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPointLoan", showContant: "checkPointLoan"});
        commissioner_datagrid = $('#check_commissioner').datagrid({
                scrollbarSize: 0,
                fitColumns: true,
                singleSelect: true,
                columns: [
                    [
                        {title: '对象', field: 'complianceObject', width: 80, align: 'center' },
                        {title: '系统资料', field: 'systemMaterial', width: 100, align: 'center'},
                        {title: '检查点', field: 'checkPoint', width: 200, align: 'center'},
                        {title: '操作人', field: 'operatorName', width: 80, align: 'center'},
                        {title: '操作时间', field: 'operateDate', width: 100, align: 'center'}
                    ]
                ]
            }
        );
        commissioner_datagrid.datagrid('doCellTipSpecial', {cls: {'background-color': '#fafafa'}, delay: 100, showfield: "checkPoint", showContant: "checkPoint"});
        if (ret) {
            if (ret.self && ret.self.length > 0) {
                self_datagrid.datagrid('loadData', ret.self);
            } else {
//                $('#div_self_outer').hide();
            }
            var maxIndex;
            /*自查备注*/
            maxIndex = ret.remark_self.length - 1;
            if (ret.remark_self && ret.remark_self.length > 0 && ret.remark_self[maxIndex].checkRemark) {
                $('#remark_self').text(ret.remark_self[maxIndex].checkRemark);
            }
            if (ret.customer && ret.customer.length > 0) {
                customer_datagrid.datagrid('loadData', ret.customer);
            } else {
//                $('#div_customer_outer').hide();
            }
            /*客服备注*/
            if (ret.remark_customer && ret.remark_customer.length > 0) {
                for (var i = 0; i < ret.remark_customer.length; i++) {
                    if (ret.remark_customer[i].checkRemark) {
                        $('#remark_customer').html($('#remark_customer').html() + (i + 1) + '.' + ret.remark_customer[i].checkRemark + '<br/>');
                    }
                }
            }
            if (ret.commissioner && ret.commissioner.length > 0) {
                commissioner_datagrid.datagrid('loadData', ret.commissioner);
            } else {
//                $('#div_commissioner_outer').hide();
            }
            /*合规备注*/
            maxIndex = ret.remark_commissioner.length - 1;
            if (ret.remark_commissioner && ret.remark_commissioner.length > 0 && ret.remark_commissioner[maxIndex].checkRemark) {
                $('#remark_commissioner').text(ret.remark_commissioner[maxIndex].checkRemark);
            }
        }
    });
    /*申诉记录*/
    if (complaintStatus == '待申诉审批') {
        $('#div_complaint').show();
        $('#complaint_content').html(complaintContent);
    } else if (complaintStatus == '申诉通过' || complaintStatus == '申诉驳回') {
        $('#div_complaint_approve').show();
        $('#_complaint_content').html(complaintContent);
        $('#approve_result').html(complaintStatus.substring(2));
        $('#approve_content').html(approveContent);
    }
});
