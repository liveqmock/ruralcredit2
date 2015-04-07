/*0	待分配 1 已退回 2 无营业网点 3 已分配 4 拒绝 5 无效名单 6 未联系上客户*/
var inquire_pool_dataGrid;
var centerPanelWidth;
var centerPanelHeight;
$(function () {

    var dic = new DataDictionary();
    dic.addDic("channel", "inquirePoolConsultWay");
    dic.addDic("marketConsultState", "marketConsultStatus");
    /*dic.addDic("history", "inquirePoolHistory");*/
    dic.dicAjax();

    //省、市、县 初始化
    showPCACombo('province', 'city', 'area');

    //营业部 初始化
    initDepartmentComboTree("distributeDepartment");

    inquire_pool_dataGrid = $('#inquire_pool').datagrid({
        url: serverName + '/saleInquireController/searchSaleInquireInfo.do',
        fitColumns: false,
        idfield: "CONSULT_POOL_ID",
        loadMsg: '正在加载....',
        height: 370,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        rownumbers: true,
        singleSelect: false,
        sortName: 'REGIST_DATE',
        sortOrder: 'DESC',
        striped: true,
        frozenColumns: [
            [
{
     field: 'any', checkbox:true
},
                {
                    title: '操作', field: 'CONSULT_POOL_ID', width: 90, align: 'left', formatter: inquirePoolOperate
                }
            ]
        ],
        columns: [
            [
                {
                    title: '咨询日期', field: 'REGIST_DATE', width: 100, align: 'left', sortable: true
                },
                {
                    title: '客户姓名', field: 'CUSTOMER_NAME', width: 100, align: 'left'
                },
                {
                    title: '户籍地址', field: 'RESIDENCEADDRESS', width: 170, align: 'left', sortable: true
                },
                {
                    title: '借款用途行业', field: 'BORROWING', width: 150, align: 'left', sortable: true
                },
                {
                    title: '咨询金额（万元）', field: 'CONSULTMONEY', width: 100, align: 'left', sortable: true
                },
                {
                    title: '信息来源', field: 'CHANNEL', width: 100, align: 'left', sortable: true
                },
                {
                    title: '咨询方式', field: 'CONSULTWAY', width: 110, align: 'left', sortable: true
                },
                {
                    title: '状态', field: 'MARKET_CONSULT_STATE', width: 100, align: 'left', sortable: true
                },
                {
                    title: '操作人', field: 'CREATER', width: 100, align: 'left'
                },
                {
                    title: '操作时间', field: 'CREATE_DATE', width: 150, align: 'left', sortable: true
                },
                {
                    title: '分配日期', field: 'DISTRIBUTEDATE', width: 80, align: 'left', sortable: true
                },
                {
                    title: '分配营业部', field: 'DISTRIBUTEDEPARTMENTNAME', width: 150, align: 'left', sortable: true
                },
                {
                    title: '咨询次数', field: 'CONSULTTIMES', width: 60, align: 'left', sortable: true
                },
                {
                    title: '联系方式', field: 'MOBILE_PHONE', hidden: true
                },
                {
                    title: '状态-KEY', field: 'STATECODE', width: 100, align: 'left', sortable: true, hidden:true
                }
            ]
        ],
        onLoadSuccess: function (data) {
            var obj;
            for (var i = 0; i < data.rows.length; i++) {
                obj = data.rows[i];
                //分配规则：已分配 、拒绝 、无效名单 、未联系上客户
                if (obj.MARKET_CONSULT_STATE == '已分配' || obj.MARKET_CONSULT_STATE == '拒绝' ||
                    obj.MARKET_CONSULT_STATE == '无效客户' || obj.MARKET_CONSULT_STATE == '未联系上客户') {
                    $("input[type='checkbox']")[i + 1].disabled = true;
                }
            }

        },
        onClickRow : function(rowIndex, rowData) {// 加载完毕后获取所有的checkbox遍历
            $("input[type='checkbox']").each(function(index, el) {
                if (el.disabled == true) {
                    $('#inquire_pool').datagrid('unselectRow', index - 1);
                }
            });
            rowNum = rowIndex;
        },
        onSelectAll : function(rowIndex, rowData) {
            $("input[type='checkbox']").each(function(index, el) {
                if (el.disabled == true) {
                    $('#inquire_pool').datagrid('unselectRow', index - 1);
                }
            });
        }
    });

    //调整 datagrid 高度
    centerPanelWidth = $('#centerPanel')[0].clientWidth;
    centerPanelHeight = $('#centerPanel')[0].clientHeight;
    $('#inquirePoolTab').tabs({
        onSelect: function (title, index) {
            var tabDiv1 = $('#tabDiv1')[0].clientHeight;
            var tabDiv2 = $('#tabDiv2')[0].clientHeight;
            if (tabDiv1 != 0) {
                inquire_pool_dataGrid.datagrid('resize', {
                    height: centerPanelHeight - tabDiv1 - 90,
                    width: centerPanelWidth - 10
                });
            } else if (tabDiv2 != 0) {
                inquire_pool_dataGrid.datagrid('resize', {
                    height: centerPanelHeight - tabDiv2 - 90,
                    width: centerPanelWidth - 10
                });
            }
        }
    });
});

// 自动分配营销咨询单
function autoDistribute() {
    $.messager.confirm("消息", "是否确认分配？", function (r) {
        $("#piliangfenpei").linkbutton("disable");
        if (r) {
            $.ajax({url: serverName + "/saleInquireController/autoDistributeDepartment.do",
                dataType: "json",
                success: function (message) {
                    if (message.success) {
                        $("#piliangfenpei").linkbutton("enable");
                        $.messager.alert('操作提示', message.msg, 'info');
                        searchSaleInquireInfo();
                    } else {
                        $("#piliangfenpei").linkbutton("enable");
                        $.messager.show({title: "消息", msg: message.msg});
                    }
                }
            });
        } else {
            // 操作按钮
            $("#piliangfenpei").linkbutton("enable");
        }
    });
}

// 分配营销咨询单
function distribute() {
    //判断营业部必选
    if ($("#distributeDepartment").combotree("getValue") == undefined ||
        $("#distributeDepartment").combotree("getValue") == null ||
        $("#distributeDepartment").combotree("getValue") == "") {
        $.messager.alert('提示', '请选择营业部', 'info');
        return;
    }

    var finalData =  $('#inquire_pool').datagrid("getSelections")

    // 选中的数据个数
    if (finalData.length == 0) {

        $.messager.alert('提示', '请选择预分配咨询记录', 'info');

        return;
    } else {
        var consultPoolIds = [];
        var consultStates = [];
        var consultDates = [];
        for (var i = 0; i < finalData.length; i++) {
            //手工分配规则 1：如果状态为“已分配”、“拒绝”、“无效名单”或“未联系上客户”，则不允许选中；
            consultPoolIds.push(finalData[i].CONSULT_POOL_ID);
            consultStates.push(finalData[i].STATECODE);
            consultDates.push(finalData[i].REGIST_DATE);
        }
        $.messager.confirm("消息", "是否确认分配？已经选中" + finalData.length + "条数据", function (r) {
            $("#fenpei").linkbutton("disable");
            if (r) {
                ajaxSubmit(serverName + "/saleInquireController/distributeDepartment.do", {
                    distributeDepartmentId: $("#distributeDepartment").combotree("getValue"),
                    distributeDepartmentName: $("#distributeDepartment").combotree("getText"),
                    consultPoolIds: JSON.stringify(consultPoolIds),
                    consultStates: consultStates.join(","),
                    consultDates: consultDates.join(",")
                }, function (ret) {
                    if (ret) {
                        $("#fenpei").linkbutton("enable");
                        if(ret.ownSale){
                            $.messager.alert('提示',ret.ownSale,'info');
                        }else if(ret.noAssistant){
                            $.messager.alert('提示',ret.noAssistant,'info');
                        }else if(ret.assigned){
                            $.messager.alert('提示',ret.assigned,'info');
                        }else{
                            $.messager.alert('错误',ret.error,'error');
                        }
                        searchSaleInquireInfo();
                    }
                    /*if (ret.success) {
                        $.messager.alert('提示', '分配成功!', 'info');
                        $("#fenpei").linkbutton("enable");
                        searchSaleInquireInfo();
                    } else {
                        $("#fenpei").linkbutton("enable");
                        $.messager.show({title: "消息", msg: ret.msg});
                    }*/
                });
            } else {
                // 操作按钮
                $("#fenpei").linkbutton("enable");
            }
        });
    }
}

function valiPhone(value) {
    var a = /^[1][3-8]\d{9}$|^((\d{4}|\d{3})-(\d{7,8}))$/;
    if (a.test(value) || value == "") {

        return true;
    }
    else {

        return false;
    }
}

function valiName(value) {
    var a = /^[\\Α-\￥]+$/i;
    var b = /^[A-Za-z]+$/i;
    var c = /^[a-zA-Z\u4e00-\u9fa5]+$/;

    if (a.test(value) || b.test(value) || value == "" || c.test(value)) {

        return true;
    }
    else {

        return false;
    }
}

function searchSaleInquireInfo() {
//   var phone= $("#mobilePhone").val();
    /*客户姓名*/
    var customerName = $.trim($("#customerName").val());
    /*咨询方式*/
    var channel = $("#channel").combobox("getValue");
    /*咨询日期*/
    var startTime = $('#registDate1').datebox('getValue');
    var endTime = $('#registDate2').datebox('getValue');
    var _startTime = new Date(startTime);
    var _endTime = new Date(endTime);
    /*联系方式*/
    var mobilePhone = $.trim($("#mobilePhone").val());
    /*状态*/
    var marketConsultState = $("#marketConsultState").combobox("getValue");
    /*分配日期*/
    var distributionstartTime = $('#distributionDate1').datebox('getValue');
    var distributionendTime = $('#distributionDate2').datebox('getValue');
    var distributionstartTime = new Date(distributionstartTime);
    var distributionendTime = new Date(distributionendTime);
    /*户籍地址*/
    var province = $("#province").combobox("getValue");
    var city = $("#city").combobox("getValue");
    var area = $("#area").combobox("getValue");
    /*历史*/
    /*
     var history = $("#history").combobox("getValue");*/

    if (Date.parse(_startTime) - Date.parse(_endTime) > 0) {
        $.messager.alert('提示', "咨询日期结束时间不能早于咨询日期开始时间", 'info');
        return;
    }
    else if (Date.parse(distributionstartTime) - Date.parse(distributionendTime) > 0) {
        $.messager.alert('提示', "分配日期结束时间不能早于分配日期开始时间", 'info');
        return;
    }
    /*else if (valiPhone(mobilePhone) == false) {
     $.messager.alert('提示', "联系方式输入错误", 'info');
     return;
     }*/
    /*else if (valiName(customerName) == false) {
     $.messager.alert('提示', "姓名输入错误！请输入中文或者英文", 'info');
     return;
     }*/
    else {
        inquire_pool_dataGrid.datagrid("load", {
            customerName: customerName,
            channel: channel,
            mobilePhone: mobilePhone,
            marketConsultState: marketConsultState,
            province: province,
            city: city,
            area: area,
            registDate1: startTime,
            registDate2: endTime,
            distributionDate1: $.trim($("#distributionDate1").datebox('getValue')),
            distributionDate2: $.trim($("#distributionDate2").datebox('getValue')),
            /*history: history,*/
            viewType: 'buttonSearch'
        });
    }
}
;
// 提交客户咨询,调用iframe页里面的方法
function submitInquire() {
    registerInquireFrame.window.registerInquire();
}

// 提交编辑客户咨询,调用编辑页里面的方法
function submitInquireEdit() {
    registerInquireEditFrame.window.registerInquireEdit();
}


function clearAll() {
    $("#customerName").val("");
    $("#channel").combobox('select', "");
    $("#registDate1").datebox('setValue', "");
    $("#registDate2").datebox('setValue', "");
    $("#mobilePhone").val("");
    $("#marketConsultState").combobox('select', "");
    $("#province").combobox('select', "1");
    $("#city").combobox('select', "1");
    $("#distributionDate1").datebox('setValue', "");
    $("#distributionDate2").datebox('setValue', "");
    $("#province").combobox('select', "");
    $("#city").combobox('select', "");
    $("#area").combobox('select', "");
    $("#history").combobox('select', "");

    /*$("#conAddress").val("");
     $("#conTelephone").val("");
     $("#customerIndustry1").combobox("select", "");
     $("#customerIndustry2").combobox("select", "");
     $("#firstNextDate").datebox("setValue", "");
     $("#secondNextDate").datebox("setValue", "");*/
}

function undoSubmitInquire() {
    $('#registerInquireFrame').attr("src", "");
    $('#registerInquire').dialog('close');
}

function undoEditSubmitInquire() {
    // $('#registerInquireEditFrame').attr("src","");
    $('#registerInquireEdit').dialog('close');
}

// 打开登记客户咨询弹窗
function registerInquire() {
    $('#registerInquireFrame').attr('src', serverName + '/saleInquireController/addFrame.do');
    $("#registerInquire").dialog({
        title: "新增咨询记录",
        close: true,
        modal: true,
        draggable: true,
        cache: false,
        onClose: function () {
            $("#registerInquireFrame").attr('src', "");
        }
    });
    $('#registerInquire').dialog('open');
}

// 废弃
function dropregisterInquire(consultPoolId) {
    $.messager.confirm('提示信息', '是否需要继续执行?', function (r) {
        if (r) {
            $.ajax({url: serverName + "/saleInquireController/dropregisterInquire.do",
                data: {
                    consultPoolId: consultPoolId
                },
                dataType: "json",
                success: function (message) {
                    if (message.success) {
                        $.messager.alert('操作提示', '操作成功', 'info');
                        searchSaleInquireInfo();
                    } else {
                        $.messager.alert('操作提示', '操作失败', 'info');
                    }
                }
            });
        }
    });
}

// 加载户籍地址
function showPCACombo(province, city, area) {
    var province = $("#" + province).combobox({
        //required : true,
        url: serverName + '/NSC/list.do',
        textField: 'cityName',
        valueField: 'cityCode',
        mode: 'remote',
        width: 120,
        delay: 500,
        value: '',
        onSelect: function (value) {
            $("#" + city).combobox({
                url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                textField: 'cityName',
                valueField: 'cityCode',
                mode: 'remote',
                width: 120,
                delay: 500,
                value: '',
                onSelect: function (value) {
                    $("#" + area).combobox({
                        url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                        textField: 'cityName',
                        valueField: 'cityCode',
                        mode: 'remote',
                        width: 125,
                        delay: 500,
                        value: ''
                    });
                }
            });
        }
    });
}

// 导入 Excel
function importExcelDialog() {
    $("#importExcelDiv").dialog({
        title: "导入 Excel ",
        closed: true,
        modal: true,
        draggable: true
    });
    $('#importExcelDiv').dialog('open');
}

//营业部初始化
function initDepartmentComboTree(id) {
    var $id = $("#" + id);
    $id.combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        onSelect: function (note) {
            var children = $id.tree("getChildren", note.target);
            if (children == null || children == "") {
                //营业部节点：如果咨询客户所在城市下有营业部，则不允许手工分配给其它城市的营业部

            } else {
                //分配规则：不能分配给分中心
                $.messager.alert('提示', '请选择营业部', 'info');
                $id.combotree('clear');
                return;
            }
        }
    });
}

//菜单查询：查询特定状态
function menuSearch() {
    inquire_pool_dataGrid.datagrid("load");
}

//
function controlBtn(type){
    if(type == 'disable'){
        $('#registSave').linkbutton('disable');
    }else{
        $('#registSave').linkbutton('enable');
    }
}

