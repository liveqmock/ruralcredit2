$(function () {
    var dic = new DataDictionary();
    dic.addDic("channel_Y", "creditchannel");
    dic.dicAjax();

    $("#departmentTree").tree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        onSelect: function (node) {
            var children = $("#departmentTree").tree("getChildren", node.target);
            if (children.length) {

                $("#areaDepartmentId").val('');
                $("#areaDepartmentName").val('');

                for (var i in children) {
                    node.id += ',' + children[i].id;
                }
            } else {
                $("#areaDepartmentId").val(node.id);
                $("#areaDepartmentName").val(node.text);
            }
            var ids = node.id;
            $("#creditChannelList").datagrid('load', {ids: ids});

            $('#who_am_i').html(node.text);
        }
    });

    $("#creditChannelList").datagrid({
        url: serverName + "/discountConfigurationController/creditChannelConfig.do?",
        loadMsg: "数据装载中....",
        fitColumns: true,
        nowrap: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        columns: [[{
            field: 'departmentId',
            title: '营业部ID',
            width: 100
        }, {
            field: 'departmentName',
            title: '营业部名称',
            width: 100
        }, {
            field: 'channelText',
            title: '渠道',
            width: 100
        }]]
    });
});
function addOne() {
    var areaDepartmentId = $("#areaDepartmentId").val();
    var areaDepartmentName = $("#areaDepartmentName").val();
    if (areaDepartmentId == 0 || areaDepartmentId == null || areaDepartmentId == "") {
        $.messager.alert("消息", "请在树形菜单选择营业部！", "info");
    } else {
        /*tip:也可通过$("#creditChannelList").datagrid('getRows') 获取当前页面条数判断，后台判断更灵活一些！*/
        ajaxSubmit(serverName + '/discountConfigurationController/checkExistOfConfig.do', {departmentId: areaDepartmentId}, function (ret) {
            if (ret) {
                $.messager.alert("消息", "此营业部已配置渠道！", "info");
            } else {
                $("#departmentId").val(areaDepartmentId);
                $("#departmentName").val(areaDepartmentName);
                $("#configForm").form("validate");
                $("#configDialog").dialog("open");
            }
        });
    }
}
function save_credit_channel_config() {
    if ($("#configForm").form("validate")) {
        $("#saveButton").linkbutton("disable");
        $("#cancelButton").linkbutton("disable");
        ajaxSubmit(serverName + "/discountConfigurationController/saveCreditChannelConfig.do", $("#configForm").serialize(), function (r) {
            $("#saveButton").linkbutton("enable");
            $("#cancelButton").linkbutton("enable");
            if (r.success) {
                $("#configForm").form("clear");
                $("#configDialog").dialog("close");
                $("#creditChannelList").datagrid("reload");
            }
            $.messager.show({
                showType: "show",
                timeout: 5000,
                title: "消息",
                msg: r.msg
            });
        });
    } else {
        $.messager.alert("消息", "请填写完整信息！", "info");
    }
}
function cancel_credit_channel_config() {
    $("#configForm").form("clear");
    $("#configDialog").dialog("close");
}
function edit() {
    var areaDepartmentId = $("#areaDepartmentId").val();
    var areaDepartmentName = $("#areaDepartmentName").val();
    if (areaDepartmentId == 0 || areaDepartmentId == null || areaDepartmentId == "") {
        $.messager.alert("消息", "请在树形菜单选择营业部！", "info");
    } else {
        var getSelected = $("#creditChannelList").datagrid("getSelected");
        if (getSelected == "" || getSelected == null) {
            $.messager.alert("消息", "请先选择一条数据！", "info");
        } else {
            getSelected.channel = getSelected.channel;
            $("#configForm").form("load", getSelected);
            $("#configForm").form("validate");
            $("#configDialog").dialog("open");
        }

    }
}
function removeOne() {
    var getSelected = $("#creditChannelList").datagrid("getSelected");
    if (getSelected == "" || getSelected == null) {
        $.messager.alert("消息", "请先选择一条数据！", "info");
    } else {
        $.messager.confirm('提示', '确定删除此营业部渠道吗', function (r) {
            if (r) {
                $("#addOneButton").linkbutton("disable");
                $("#editButton").linkbutton("disable");
                $("#removeButton").linkbutton("disable");
                ajaxSubmit(serverName + "/discountConfigurationController/removeCreditChannelConfigById.do", {
                    configId: getSelected.configId
                }, function (r) {
                    $("#addOneButton").linkbutton("enable");
                    $("#editButton").linkbutton("enable");
                    $("#removeButton").linkbutton("enable");
                    $("#creditChannelList").datagrid("reload");
                    $.messager.show({
                        showType: "show",
                        timeout: 5000,
                        title: "消息",
                        msg: r.msg
                    });
                });
            }
        });
    }
}