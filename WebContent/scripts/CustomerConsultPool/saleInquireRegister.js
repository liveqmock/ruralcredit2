$(function () {
    var dic = new DataDictionary();
    dic.addDic("conSex_Y", "gender");
    dic.addDic("consultWay_Y", "inquirePoolConsultWay");
    dic.addDic("borrowing_Y", "marketConsultBorrowing");
    dic.addDic("channel_Y", "marketConsultInfoSource");
    dic.dicAjax();

    showPCACombo('province', 'city', 'area');
});

// 提交登记咨询
function registerInquire() {
    var flag = true;
    //验证咨询日期
    var oDate1 = new Date($("#registDate").datebox('getValue'));
    var merwdata = new Date()
    if (!$("#registDate").datebox('getValue')) {
        $.messager.alert('提示', '请选择咨询日期', 'info');
        flag = false;
    } else if (Date.parse(oDate1) - Date.parse(merwdata) > 0) {
        $.messager.alert('提示', '咨询日期必须早于系统日期', 'info');
        flag = false;
    } else if (!$('#customerName').val()) {
        $.messager.alert('提示', '请填写客户姓名', 'info');
        flag = false;
    } else if ($('#customerName').val() && valiName($('#customerName').val()) == false) {
        //验证性别必填 字母或汉字组成 10个汉字以内
        $.messager.alert('提示', "姓名输入错误！请输入中文或者英文", 'info');
        flag = false;
    } else if (!$('#conSex').combobox('getValue')) {
        //验证性别必填
        $.messager.alert('提示', '请选择性别', 'info');
        flag = false;
    } else if (!$('#mobilePhone').val()) {
        //验证手机号码
        $.messager.alert('提示', '请填写手机号码', 'info');
        flag = false;
    } else if (valiPhone($('#mobilePhone').val()) == false) {
        $.messager.alert('提示', '请填写正确的手机号码', 'info');
        flag = false;
    } else if (!$('#consultWay').combobox('getValue')) {
        //验证咨询方式
        $.messager.alert('提示', '请选择咨询方式', 'info');
        flag = false;
    } else if (!$('#borrowing').combobox('getValue')) {
        //验证借款用途
        $.messager.alert('提示', '请选择借款用途', 'info');
        flag = false;
    } else if (!$('#consultAmount').val()) {
        //验证咨询金额
        $.messager.alert('提示', '请填写咨询金额', 'info');
        flag = false;
    } else if (!$('#channel').combobox('getValue')) {
        //验证信息来源
        $.messager.alert('提示', '请选择信息来源', 'info');
        flag = false;
    } else if (!$('#province').combobox('getValue') || !$('#city').combobox('getValue') || !$('#area').combobox('getValue')) {
        //验证户籍地址
        $.messager.alert('提示', '请选择户籍地址', 'info');
        flag = false;
    }
    if (flag) {
        window.parent.controlBtn('disable');
        if ($("#inquireForm").form("validate")) {
            $.post(serverName + "/saleInquireController/registerInquire.do", $("#inquireForm").serialize(), function (result) {
                if (result.success) {
                    parent.$.messager.show({
                        title: '消息',
                        msg: '操作成功',
                        timeout: 5000,
                        showType: 'slide'
                    });
                    parent.undoSubmitInquire();
                    parent.clearAll();
                    parent.searchSaleInquireInfo();

                } else {
                    parent.$.messager.show({
                        title: '消息',
                        msg: result.msg,
                        timeout: 5000,
                        showType: 'slide'
                    });
                    parent.undoSubmitInquire();
                }
                window.parent.controlBtn('enable');
            }, "json");
        } else {
            alert("请检查！必输项未填写或填写格式错误！");
            return;
        }
    } else {
        return;
    }
}

//加载户籍地址
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
        panelHeight:200,
        onSelect: function (value) {
            $('#provinceName').attr('value', $('#province').combobox('getText'));
            $("#" + city).combobox({
                url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                textField: 'cityName',
                valueField: 'cityCode',
                mode: 'remote',
                width: 120,
                delay: 500,
                value: '',
                panelHeight:200,
                onSelect: function (value) {
                    $('#cityName').attr('value', $('#city').combobox('getText'));
                    $("#" + area).combobox({
                        url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                        textField: 'cityName',
                        valueField: 'cityCode',
                        mode: 'remote',
                        width: 125,
                        delay: 500,
                        value: '',
                        panelHeight:200,
                        onSelect: function () {
                            $('#areaName').attr('value', $('#area').combobox('getText'));
                        }
                    });
                }
            });
        }
    });
}

//信息来源
function showinfomationSource() {
    ajaxSubmit(serverName + "/dicRequest/getSpecifiedDic.do", {section: "infomationSource", q: ""}, function (result) {
        var opanel = document.getElementById("tableinfomationSource");
        var pchildren = opanel.childNodes;
        //清空表中的行和列
        for (var a = pchildren.length - 1; a >= 0; a--) {
            opanel.removeChild(pchildren[a]);
        }
        var n = 0;
        for (var i = 0; i < parseInt(result.length / 3); i++) {
            var tr = document.createElement("tr");
            if (i % 2 == 0) {
                tr.setAttribute('bgcolor', '#DDE4EE');
            }
            for (var j = 0; j < 3; j++) {
                n = n + 1;
                var cell = document.createElement("td");
                cell.innerHTML =
                    "<a href='javaScript:addinfomationSource(\"" + result[n - 1].codeKey + "\",\"" + result[n - 1].codeVlue + "\");'>" + result[n - 1].codeVlue + "</a>";
                tr.appendChild(cell);
            }
            document.getElementById("tableinfomationSource").appendChild(tr);
        }
        if (result.length % 3 > 0) {
            var tr = document.createElement("tr");
            if ((parseInt(result.length / 3)) % 2 == 0) {
                tr.setAttribute('bgcolor', '#DDE4EE');
            }
            for (var j = 0; j < result.length % 3; j++) {
                n = n + 1;
                var obj = new Object();
                var cell = document.createElement("td");
                var tag = document.createElement("tag");

                obj["codeKey"] = result[n - 1].codeKey;
                obj["codeValue"] = result[n - 1].codeVlue;
                tag.innerHTML = "<a href='javascript:;' onclick='javascript:addinfomationSource(\"" + obj.codeKey + "\",\"" + obj.codeValue + "\")'>" + result[n - 1].codeVlue + "</a>";
                cell.appendChild(tag);
                tr.appendChild(cell);
            }
            document.getElementById("tableinfomationSource").appendChild(tr);
        }
        $("#windowinfomationSource").window({
            width: 500,
            height: 30 * (Math.ceil(result.length / 3)),
            title: "信息来源",
            minimizable: false,
            maximizable: false,
            collapsible: false,
            modal: true
        });
    });

}

/*信息来源div赋值*/
function addinfomationSource() {
    console.info('ss');
    console.info(arguments);
    $("#channel").val(arguments[0]);
    $("#channelText").val(arguments[1]);
    $("#windowinfomationSource").window("close");
}

/*字数验证*/
function textCount(textId, htmlId, max) {
    $("#" + textId).keyup(function () {
        var maxl = max;
        var tishi = "还可以输入" + maxl + "个字";
        $("#" + htmlId).html(tishi);
        var xianyou = $(this).val().length;
        var keyi = maxl - xianyou;
        var tishi = "还可以输入" + keyi + "个字";
        if (xianyou > (max - 1)) {
            var tishi = "还可以输0个字";
            $("#" + htmlId).css({
                "color": "red"
            });
            var s = $("#" + textId).val().substr(0, 100);
            $("#" + textId).val();
        } else {
            $("#" + htmlId).css({
                "color": "#000"
            });
        }
        $("#" + htmlId).html(tishi);
    });
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

function valiPhone(value) {
    var a = /^[1][3-8]\d{9}$|^((\d{4}|\d{3})-(\d{7,8}))$/;
    if (a.test(value) || value == "") {

        return true;
    }
    else {

        return false;
    }
}

