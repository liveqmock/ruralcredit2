$(function(){
    /*更改：分公司名称下拉选择框为两级树形菜单
	$("#branchofficeName").combobox({
		url : serverName + "/protocolMappingController/getDepartmentList.do",
		valueField : 'departmentId',
		 textField : 'departmentName'
	});*/
    departmentComboboxTreeWithPanelWidth("branchofficeName",false,250);
	/*var dic = new DataDictionary();
		dic.addDic("onOff","onOff");
		dic.addDic("newonOff","onOff");
	    dic.dicAjax();*/
	$("#protocolMappingList").datagrid({
			title:" 信息",
			url : serverName+'/protocolMappingController/queryProtocolMappingList.do',
			idfield : 'cod',
			singleSelect : true,
			striped : true,
			pagination: true,
			frozenColumns:[[
				      	    {field:'DOWN_LOAD_URL',title:'下载地址',width:170,
				      	    	formatter : function(value,data){
				      	    	return "<a name='searchHrtongButton' href='javascript:searchHrtong();'>查看</a>";
				      	    }}]],
			columns : [ 
						[
				      	    {field:'BRANCHOFFICE_NAME',title:'分公司',width:170},
				      	    {field:'BUSINESS_NUMBER',title:'业务编号',width:170},
				      	    {field:'PROTOCOL_NUMBER',title:'合同编号',width:170},
				      	    {field:'NAME',title:'借款人',width:170},
				      	    {field:'OPERATOR',title:'操作人',width:170},
				      	    {field:'OPERATE_DATE',title:'操作时间',width:180},
				      	    {field:'REPAYMENT_PLAN_NAME',title:'产品类型',width:180}

		      	  		 ]
      	   ]
	});

    /*增加产品类型搜索条件*/
    $("#productTypeName").combobox({
        url: serverName + "/creditgroup/selectProInfoAll.do",
        textField: 'productName',
        valueField:'productName',
        mode: 'remote',
        editable: false,
        onSelect: function (data) {
            $("#productTypeName").val(data.productName);
        }
    });
});

function disableButton(){
	$("[name='searchHrtongButton']").each(function(){
		$(this)[0].removeAttribute('href');  
	});
}

function enableButton(){
	$("[name='searchHrtongButton']").each(function(){
		$(this).attr('href','javascript:searchHrtong();');  
	});
}
function searchHrtong(){
	disableButton();
	var datarow = $("#protocolMappingList").datagrid("getSelected");
	ajaxSubmit(serverName + "/protocolMappingController/searchAndDownProtocol.do",{creditapplicationId:datarow.CREDITAPPLICATION_ID},function(r){
		if(r.success){
			enableButton();
			window.location.href = r.msg;
		}else{
			enableButton()
			alert("下载失败");
		}
	});
}

/**
 * 精确查询
 */
function queryAllCondition(){
    var branchofficeIds = $("#branchofficeName").combotree("getValues").join(",");
	$("#protocolMappingList").datagrid("reload",
	{
		branchofficeId : branchofficeIds,
		businessNumber 	 : $.trim($("#businessNumber").val()),
		protocolNumber 	 : $.trim($("#protocolNumber").val()),
        productTypeName : $("#productTypeName").combobox("getValue")
		
	});
}
/**
 * 清空
 */
function clearAllCondition(){
//	$("#branchofficeName").combobox("clear");
    $("#branchofficeName").combotree("setValues","");
	$("#businessNumber").val("");
	$("#protocolNumber").val("");
    $("#productTypeName").combobox("clear");
    queryAllCondition();
}


