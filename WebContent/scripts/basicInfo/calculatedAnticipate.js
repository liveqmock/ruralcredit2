$(function(){  
	$("#allAheadSchedule").datagrid({  
        url: ''
    });
	$("#departmentId1").combobox({
    	url:serverName+"/CustomerConsult/departmentList.do",
    	valueField:"departmentId",
    	textField:"departmentName",
    	mote:"remote",
    	panelHeight:'auto',
    	editable:false,
		multiple:false,
		required:true,
		width:124,
		validType:"selectValueRequired['departmentId1','请选择营业部']"
	});
	initProductType();
}); 
/** 产品列表请求 **/
function initProductType(){
	var rateData = "departmentId="+deptid;
	$.post("../../../borrowingProducts/queryProByDepart.do",{departmentId:deptid},function(returnData){
		if(returnData.paymentType){
			$.each(returnData, function(key, value){
				$('#paymentTypeCode').combobox({
					data:value,
					valueField:'paymentTypeCode',   
					textField:'productName',
					mode:'remote',
					panelHeight:'',
					editable:false,
					multiple:false,
					width:100,
					required:true,
					onSelect:function(record){
						$("#productInfoId").val(record.productCategoryId);
						$("#periodCount").val(record.instalments);
					},
					validType:"selectValueRequired['paymentTypeCode','请选择产品']"
				});
			});
		}
	},"json");
}
/** 查询申请还款情况 **/
function queryReturnPlan(){
	var groupNum = $("#groupNum").val();
	if(groupNum){
		$("#dg").datagrid({
			url: serverName+'/borrowingProducts/queryReturnPlanByGroupNum.do',
			queryParams:{groupNum:groupNum},
			onSelect:function(rowIndex, rowData){
				endEdit();
			},
			onDblClickCell:function(rowIndex, rowData, changes){
				var row = $("#dg").datagrid("getSelected");
				if(row){
					var rowIndex = $("#dg").datagrid('getRowIndex',row);
					$("#dg").datagrid('beginEdit', rowIndex);
				}
			}
		});
	}else{
		$.messager.alert("提示信息", "请输入业务编号！");
	}
}
/** 格式化显示 **/
function formatOverdueFlag(val,row){
	if (val == 1){
		return '<span style="color:red;">逾期</span>';
	}else{
		return '正常';
	}  
}

/** 新增 **/
function addLine(){
	var allRow = $('#dg').datagrid('getRows');
	
	var havaValidate = $('#dg').datagrid('validateRow',allRow.length-1);
	if(!havaValidate){
		$.messager.alert("提示信息", "请输入正确的实际还款记录！");
		return;
	}
	endEdit();
	$("#dg").datagrid('appendRow',{});
	var rows = $("#dg").datagrid('getRows');
	$("#dg").datagrid('beginEdit', rows.length - 1);
}
function destroyLine(){
	var rows = $("#dg").datagrid('getRows');
	if (rows.length>0){
		$("#dg").datagrid('deleteRow', rows.length-1);
	}else{
		$.messager.alert("提示信息","请选择一行数据进行删除！");
	}
}
/** 计算 **/
function calculatedRate(){
	endEdit();
	//1、form
	if(!$('#rateOptions').form('validate')){
		$.messager.alert("提示信息", "请输入正确的提前还款选项！");
		return;
	}
	var formObject = $('#rateOptions').serializeObject();
	var gridObj = $('#dg').datagrid('getChanges', "inserted");
	var allRow = $('#dg').datagrid('getRows');
	if(allRow.length<=0){
		$.messager.alert("提示信息", "请添加各分期实际还款记录！");
		return;
	}
	var havaValidate = true;
	for(var i=0;i<=allRow.length;i++){
		havaValidate = $('#dg').datagrid('validateRow',i);
		if(!havaValidate){
			$.messager.alert("提示信息", "请输入正确的实际还款记录！");
			return;
		}
	} 
	var effectRow = new Object();
	if(allRow.length){
		effectRow["apsDtos"] = JSON.stringify(allRow);
	}
	effectRow["paymentAmountReq"] = JSON.stringify(formObject);
	
	$.post(serverName+"/borrowingProducts/paymentAmountCalculate2.do",effectRow,function(returnData){
		if(returnData){
			if(returnData.resultCode=="0"){
				//加载表格数据
//				if(returnData.apsListInfoList){
//					$("#apsList").datagrid("loadData",returnData.apsListInfoList);
//				}
				if(returnData.aheadSchedule){
					var obj = {'total':1,'rows':[returnData.aheadSchedule]};
					$("#allAheadSchedule").datagrid("loadData",obj);
				}
			}
			$.messager.alert('提示信息',returnData.resultMsg);
		}else{
			$.messager.alert('提示信息','服务异常,稍后重试');
		}
	},"json");
}
/** 清空 **/
function clearFun(){
//	$('#rateOptions').form('clear');
	$("#dg").datagrid('load', {});
	$("#allAheadSchedule").datagrid('load', {});
}
/** 结束编辑 **/
function endEdit(){
	var rows = $("#dg").datagrid('getRows');
	for( var i = 0; i < rows.length; i++) {
		$("#dg").datagrid('endEdit', i);
	}
}