$(function() {

	appendFlag = true;
	$("#loanPortfolioList").datagrid({
		url : serverName + "/loanPortfolioListController/LoanPortfolioDateGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
		columns : [ [ {
			field : 'CREDITAPPLICATIONID',
			title : '信贷申请单ID',
			width : 100,
			hidden : true
		}, {
			field : 'BUSINESSNUMBER',
			title : '业务单号',
			width : 100,
			hidden : false
		}, {
			field : 'BORROWERNAME',
			title : '借款人姓名',
			width : 100,
			hidden : false
		}, {
			field : 'LOANOFFICERNAME',
			title : '信贷员姓名',
			width : 100,
			hidden : false
		}, {
			field : 'COMPANYNAME',
			title : '分公司名称',
			width : 150,
			hidden : false
		}, {
			field : 'BUSINESSTYPE',
			title : '业务类型',
			width : 100,
			hidden : false
		}, {
			field : 'REPAYMENTPLANNAME',
			title : '产品名称',
			width : 200,
			hidden : false
		}, {
			field : 'AMOUNT',
			title : '合同金额',
			width : 100,
			hidden : false
		}, {
			field : 'SIGNAGREEMENTDATE',
			//title : '协议签订时间',
			title : '财务转账时间',
			width : 100,
			hidden : false
		} ] ],
		onLoadSuccess : function(data) {
// if (appendFlag) {
// getAmongMonth(data);
// appendFlag = false;
// }
		}
	});
	ajaxSubmit(serverName + "/loanPortfolioListController/queryListForLoanPort.do", {}, function(r) {
		getAmongMonth(r,0);
	});
	$("#loanCurrentList").datagrid({
		url :"",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
		columns : [ [ {
			field : 'date',
			title : '日期（月份）',
			width : 100,
			hidden : false
		}, {
			field : 'amount',
			title : '月放款总额',
			width : 100,
			hidden : false
		}, {
			field : 'count',
			title : '笔数',
			width : 100,
			hidden : false
		} ] ],
		onLoadSuccess : function(data) {

			getAmongMonth(data,0);
		}
	});
	//营业部搜索
	 departmentComboboxTreeWithPanelWidth("companyId",false,250);
});

function doSearch(param) {
	var data = new Object();
	if (param == 0) {
		var map = {
			fuzzyValue : $("#fuzzy").val()
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#loanPortfolioList').datagrid('load', data);
	} else if (param == 1) {
		var start = $("#startDate").datebox('getValue');
		var end = $("#endDate").datebox('getValue');
		var map = {
			businessNumber : $("#businessNumber").val(),
			borrowerName : $("#borrowerName").val(),
			companyId : $("#companyId").combotree("getValues").join(","),
			startDate : start,
			endDate : end
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#loanPortfolioList').datagrid('load', data);
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
function doSearchMonth() {
		var map = {
			companyId : $("#companyId").combotree("getValues").join(",")
		};
		var jsonMap = JSON.stringify(map);
		ajaxSubmit(serverName + "/loanPortfolioListController/queryListForLoanPort.do?paramJsonMap="+jsonMap, {}, function(r) {
			getAmongMonth(r,1);
		});

}
function doClear(param) {
	if (param == 0) {
		$('#fuzzy').val("");
	} else if (param == 1) {
		$("#searchform").form('clear');
		$("#companyId").combotree("setValues", "");
	} else {
		$.messager.alert('提示', "doClearRepayment的JS出错了！", 'error');
	}
}
function getAmongMonth(data,status) {
	// 获取当前时间
	var date = new Date();
	// 构造系统上线时间
	var online = new Date("2013/03/01");
	var between_Year = date.getFullYear() - online.getFullYear();
	// 得到月份差 + 1
	var between_Month = date.getMonth() - online.getMonth();
	// 得到从系统上线到现在一共经历了多少个月
	var among = between_Year * 12 + between_Month;
	for ( var i = 0; i <among; i++) {
		if(i==0){
			date.setMonth(date.getMonth());
		}else{
			date.setMonth(date.getMonth()-1);
		}
		var temMonth = date.getMonth() + 1;
		temMonth = temMonth < 10 ? "0" + temMonth : temMonth;
		var dateString = date.getFullYear() + "年" + temMonth + "月";
		var paramString = date.getFullYear() + "-" + temMonth + "-";
		var result = comput(paramString, data);
		var ind = result.indexOf(",");
		var a = result.substring(0, ind);
		var k = parseFloat(a);
		var c = result.substring(ind + 1, result.length);
		if(status == 0){
			$("#loanCurrentList").datagrid('appendRow', {
				date : dateString,
				amount : k.toFixed(2),
				count : c + "笔"
			});
		}else{
			var a = $("#loanCurrentList").datagrid('getRows');
			$("#loanCurrentList").datagrid('updateRow',				{
				index:i,
				row:{date : dateString,
				amount : k.toFixed(2),
				count : c + "笔"}
			});
		}
		
	}
	
}
// 计算每个月一共多少笔一共多少钱
function comput(paramString, data) {
	var list = data;
	// console.info(list);
	var newList = new Array();
	for ( var i = 0; i < list.length; i++) {
		var d = list[i].SIGNAGREEMENTDATE;
		if (d != null && d != "") {
			d = d.substring(0, 8);
		} else {
		}
		if (paramString == d) {
			newList.push(list[i]);
		}
	}
	// console.info(newList);
	// alert(newList);
	var sumAmount = 0;
	var sumCount = newList.length;
	var result = "";
	for ( var j = 0; j < newList.length; j++) {
		sumAmount = accAdd(sumAmount, newList[j].AMOUNT);
	}
	result = sumAmount + "," + sumCount;
	return result;
}