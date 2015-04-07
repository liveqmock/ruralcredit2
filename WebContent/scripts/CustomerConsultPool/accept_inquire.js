$(function() {
	var dic = new DataDictionary();
	dic.addDic("conSex_Y", "gender");
	dic.addDic("borrowing_Y", "marketConsultBorrowing");
	dic.addDic("traceStatus_Y", "traceStatus");
	dic.addDic("consultWay_Y", "inquirePoolConsultWay");
	dic.addDic("channel_Y", "marketConsultInfoSource");
	dic.addDic("borrowUse", "borroerUserConsult");
	dic.dicAjax();

	
	showCityCombopublicHight('province', 'city', 'area');
	
	/*$("#acceptConsultState").combobox({
		onSelect : function() {
			acceptConsultState = "acceptConsultStatus_" + $("#acceptConsultState").combobox("getValue");
			ajaxSubmit(serverName + "/acceptAdviceController/getDataDictionaryList.do", {
				section : acceptConsultState
			}, function(r) {
				$("#feedbackResult").combobox({
					valueField : "codeKey",
					textField : "codeValue",
					data : r
				});
				$("#feedbackResult").combobox("setValue", "");
			});
		}

	});
	
	
*/
	//选择'跟进中'，'申请进件'时，借款用途弹出
	$("#traceStatus").combobox({
		onSelect : function(record) {
            if(record.codeKey == '0' || record.codeKey == '1'){
                $('#borrowing_tr').show();
            }else{
            	//$('#borrowUse').combobox('setValue','');
                $('#borrowing_tr').hide();
            }
		}
	});
	
	/*$("#feedbackResult").combobox({
		valueField : "codeKey",
		textField : "codeValue",
		data : [ {
			codeKey : "",
			codeValue : "请选择"
		} ]
	});*/

	//cityComboboxPublicOtherCounty("province", "city", "area", $('#province').combobox('getValue'), $('#city').combobox('getValue'), $('#area').combobox('getValue'));

	countChar("communicationRecord", "counter");
	
	
});

/*字数验证*/
function textCount(textId, htmlId, max) {
    $("#" + textId).keyup(function () {
        var maxl = max;
        var tishi =  maxl ;
        $("#" + htmlId).html(tishi);
        var xianyou = $(this).val().length;
        var keyi = maxl - xianyou;
        var tishi =  keyi  ;
        if (xianyou > (max - 1)) {
            var tishi = "0";
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

//信息来源div赋值
function addinfomationSource() {
    console.info(arguments);
    $("#channel").val(arguments[0]);
    $("#channelText").val(arguments[1]);
    $("#windowinfomationSource").window("close");
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
     panelHeight:120,
     onSelect: function (value) {
         $("#" + city).combobox({
             url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
             textField: 'cityName',
             valueField: 'cityCode',
             mode: 'remote',
             width: 120,
             delay: 500,
             value: '',
             panelHeight:120,
             onSelect: function (value) {
                 $("#" + area).combobox({
                     url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                     textField: 'cityName',
                     valueField: 'cityCode',
                     mode: 'remote',
                     width: 125,
                     delay: 500,
                     value: '',
                     panelHeight:120
                 });
             }
         });
     }
 });
}

function countChar(textareaName, spanName) {
	if (document.getElementById(textareaName).value.length > 100) {
		$("#" + textareaName).val(document.getElementById(textareaName).value.substr(0, 200));
	}
	document.getElementById(spanName).innerHTML = 200 - document.getElementById(textareaName).value.length;
}


function updateAcceptAdvice() {
	$.messager.confirm('提示信息', '是否需要提交?', function(r) {
		if (r) {
			// 通过表单验证，保存表单信息进入下一步
			//跟踪状态
			var traceStatus = $("#traceStatus").combobox('getValue');
			var consultMoney = $("#consultMoney").val();
			var frontTraceStatus=$("#frontTraceStatus").val();
			var borrowUse =$("#borrowUse").combobox('getValue');
			var customerName=$('#customerName').val();
			var acceptConsultState=$("#acceptConsultState").val();
			
			// 跟踪状态为“申请进件”，咨询金额必填
			if (traceStatus == '1') {
				if (consultMoney == "" || consultMoney == null || consultMoney == undefined) {
					$.messager.alert("操作提示", "跟踪状态为'申请进件'时，借款额度必填！", "error");
					return;
				}
			}
			
			if(traceStatus == '0'||traceStatus == '1'){
				
				if(borrowUse == "" || borrowUse == null || borrowUse == undefined){
					$.messager.alert("操作提示", "跟踪状态为'申请进件或者跟进中'时， 借款用途必填 ！", "error");
					return;
				}
				
			}
			
			if(customerName == "" || customerName == null || customerName == undefined){
				$.messager.alert('操作提示', "客户姓名不能为空！", 'info');
		        return;
			}
			
			if ( valiName(customerName) == false) {
			        //验证性别必填 字母或汉字组成 10个汉字以内
			        $.messager.alert('操作提示', "姓名输入错误！请输入中文或者英文", 'info');
			        return;
			       
			}
			
			var data = "";
			if(frontTraceStatus != undefined && frontTraceStatus != ""){
				data = "frontTraceStatus="+frontTraceStatus;
			}else{
				frontTraceStatus="";
				data = frontTraceStatus;
			}
			console.info($("#acceptInquireForm").serialize());
			if($("#acceptInquireForm").serialize() != null && $("#acceptInquireForm").serialize() != ""){
				if(data != ""){
					data = data+"&" +$("#acceptInquireForm").serialize();
				}else{
					data = data+"&" +$("#acceptInquireForm").serialize();
				}
				
			}
			
			if ($("#acceptInquireForm").form("validate")) {
				$.ajax({
					url : serverName + "/acceptAdviceController/updateAcceptAdvice.do",
					type : "POST",
					data : data,
					dataType : "json",
					success : function(result) {
						if (result.success) {
							window.parent.showMessage("操作成功");
							parent.$('#acceptAdviceDiv').dialog('close');
							parent.$("#inquire_pool_office").datagrid("reload");
							parent.$("#acceptAdviceframe").attr("src", "");
						} else if (result.msg != null && result.msg != "" && result.msg != undefined) {
							$.messager.alert('操作提示', result.msg, 'error', function() {
								parent.$('#acceptAdviceDiv').dialog('close');
								parent.$("#inquire_pool_office").datagrid("reload");
								parent.$("#acceptAdviceframe").attr("src", "");
							});
						} else {
							window.parent.showMessage("操作失败");
							parent.$('#acceptAdviceDiv').dialog('close');
							parent.$("#acceptAdviceframe").attr("src", "");
						}
					}
				});
			} else {
				$.messager.alert('操作提示', '必填项不能为空！', 'error');
			}

		}
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