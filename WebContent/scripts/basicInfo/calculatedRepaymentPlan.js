var rateResult;
$(function(){
	var calRate = new DataDictionary();
	calRate.addDic("paymentTypeCode","paymentTypeCode");
	calRate.dicAjax();
	
	rateResult = $('#rateResult').datagrid({
        //url:'../../../borrowingProducts/rateCalculate.do',
        toolbar:'#toolbar',
        fitColumns:true,
        pagination:true,
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
			{field:'allheadMoney',title:'全部提前还款金额',width:100},
			{field:'accumulativeMoney',title:'累计总还款额',width:100},
			{field:'periodChargeList',title:'分期服务费列表信息',width:400,
				formatter:function(value1,row,rowIndex){
					var n = value1.chargeInfo;
					var text = "编号:"+n[0].chargeType+",公式描述:"+n[0].chargeFormula+",金额:"+n[0].charge;
					return text;
				}
			}
		]]
        
    });
    //设置分页控件 
    var p = rateResult.datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });  
});
/** 计算 **/
function calculatedRate(){
	var rateData = $('#rateOptions').serializeObject();
	$.post("../../../borrowingProducts/repaymentCalculate.do",rateData,function(returnData){
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
	$('#toolbar input').val('');
	rateResult.datagrid('load', {});
}
