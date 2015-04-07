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

//计算现金流旧数据的方法
function caclMoney() {
	$.messager.confirm("消息","确定开始计算吗?",function(rs){
		if(rs){
	document.getElementById('aOldDataCalc').setAttribute('href','javascript:void(0)');
	ajaxSubmit(serverName+"/cashStream/oldCashStreamCalcMaxMoney.do",function(r){
		if(r.success){
			document.getElementById('aOldDataCalc').setAttribute('href','javascript:caclMoney()');
			alert("计算成功！");
		}else{
			alert("计算失败！");
		}
		});
		}
	});
}

//计算现金流旧数据2014-05-09做的单子，在05-09计算的现金流的方法
function caclMoney0509Before() {
	$.messager.confirm("消息","确定开始计算吗?",function(rs){
		if(rs){
	document.getElementById('aOldDataCalc0509Before').setAttribute('href','javascript:void(0)');
	ajaxSubmit(serverName+"/cashStream/oldCashStreamCalcMaxMoney0509Before.do",function(r){
		if(r.success){
			document.getElementById('aOldDataCalc0509Before').setAttribute('href','javascript:caclMoney0509Before()');
			alert("计算成功！");
		}else{
			alert("计算失败！");
		}
		});
		}
	});
}