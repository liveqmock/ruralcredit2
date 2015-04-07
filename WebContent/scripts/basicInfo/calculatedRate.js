var rateResult;
$(function(){
	initProductType();
	rateResult = $('#rateResult').datagrid({
        //url:'../../../borrowingProducts/rateCalculate.do',
        toolbar:'#toolbar',
        fitColumns:true,
        rownumbers:true,
        singleSelect:true,
        striped: true,
        showFooter: true,
        columns:[[   
			{field:'phase',title:'阶段',width:100},   
			{field:'period',title:'分期',width:100},   
			{field:'repayDate',title:'还款日',width:100},
			{field:'irr',title:'内部收益率',width:100},   
			{field:'amortizedPrincipal',title:'当期应还本金',width:100},
			{field:'amortizedInterest',title:'当期应还利息',width:100},   
			{field:'periodCharge',title:'分期服务费',width:100},
			{field:'periodMoney',title:'当期还款额',width:100},
			{field:'periodChargeList',title:'分期服务费列表信息',width:400,
				formatter:function(value1,row,rowIndex){
					var n = value1.chargeInfo;
					var text = "编号:"+n[0].chargeType+",公式描述:"+n[0].chargeFormula+",金额:"+n[0].charge;
					return text;
				}
			}
		]]
    });
    rateResult.datagrid('doCellTip',{delay:500});
});
/** 计算 **/
function calculatedRate(){
	var rateData = $('#rateOptions').serializeObject();
	$.post("../../../borrowingProducts/rateCalculate2.do",rateData,function(returnData){
		if(returnData){
			if(returnData.resultCode=="0"){
				//加载表格数据
				rateResult.datagrid("loadData",returnData.periodSchedules);
			}else{
				$.messager.alert('提示信息',returnData.resultMsg);   
			}
		}else{
			$.messager.alert('提示信息','服务异常,稍后重试');
		}
	},"json");
}
/** 清空 **/
function clearFun() {
	$("#productInfo").combobox("setValue","");
	$("#contractMoney").val("");
	$("#calcDate").datebox("setValue","");
}
/** 产品列表请求 **/
function initProductType(){
	var rateData = "departmentId=11009485";
	$.post("../../../borrowingProducts/queryProByDepart.do",rateData,function(returnData){
		$.each(returnData, function(key, value){
			$('#paymentTypeCode').combobox({
				data:value,
				valueField:'productCategoryId',   
				textField:'productName',
				mode:'remote',
				panelHeight:'',
				editable:false,
				multiple:false,
				width:150,
				onSelect:function(record){
					$("#catagoryId").val(record.productCategoryId);
					$("#periodCount").val(record.instalments);
					$("#paymentTypeCode1").val(record.paymentTypeCode);
				}
			});
		});
	},"json");
}