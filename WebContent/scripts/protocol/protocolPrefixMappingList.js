$(function(){
    /*更改：分公司名称下拉选择框为两级树形菜单
	$("#companyName").combobox({
		url : serverName + "/protocolPrefixMappingController/getDepartmentList.do",
		valueField : 'departmentId',
		 textField : 'departmentName'
	});*/
    departmentComboboxTreeWithPanelWidth("companyName",false,250);
	var dic = new DataDictionary();
		dic.addDic("onOff","onOff");
		dic.addDic("newonOff","onOff");
		dic.addDic("editonOff","onOff");
	    dic.dicAjax();
	$("#protocolPrefixMappingList").datagrid({
			title:" 信息",
			url : serverName+'/protocolPrefixMappingController/queryProtocolPrefixMappingList.do',
			idfield : 'cod',
			singleSelect : true,
			striped : true,
			pagination: true,
			columns : [ 
						[
				      	    {field:'BRANCHOFFICE_NAME',title:'分公司',width:100},
				      	    {field:'PREFIX_NUMBER',title:'合同编号前缀',width:100},
				      	    {field:'OPERATOR',title:'操作人',width:100},
				      	    {field:'OPERATE_DATE',title:'操作日期',width:200 } ,
				      	    {field:'ON_OFF',title:'状态',width:100,
				      	    	formatter:function(value){
				      	    		if("Y"==value){
				      	    			return "开启";
				      	    		}else if("N"==value){
				      	    			return "关闭";
				      	    		}else{
				      	    			return value;
				      	    		}
				      	    	}
				      	    
				      	    },
				      	  {field:'ADDRESS',title:'户籍地址',width:300},
				      	{field:'PROVINCEHOME',title:'省',width:300,hidden:true},
				      	{field:'CITYHOME',title:'市',width:300,hidden:true},
				      	{field:'COUNTYHOME',title:'区',width:300,hidden:true},
				      	    {field:'BRANCHOFFICE_ID',title:'分公司id',width:100,hidden:true},
				      	    {field:'PREFIX_ID',title:'前缀id',width:100,hidden:true}
		      	  		 ]
      	   ],
      	   toolbar: '#toolbar_div'
	});
	$("#newDialog").show().dialog({
		title:"新增",
		//closed : true,防止两次渲染
		closed : true,
		modal : true,
		inline : false,
		width : 650,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "addButton",
			text : "保存",
			handler : function() {				
				//保存方法
				//$("#newDialog").dialog('close');
				insertProtocolPrefixMapping();
					}
			}, {
				id : "cencleButton",
				text : "取消",
				handler : function() {
					
					$("#newDialog").dialog('close');
				}
			} ]

	});
	$("#editDialog").show().dialog({
		title:"修改",
		//closed : true,防止两次渲染
		closed : true,
		modal : true,
		inline : false,
		width : 650,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "addButton",
			text : "保存",
			handler : function() {
				//保存方法
				
				updateProtocolPrefixMapping();
					}
			}, {
				id : "cencleButton",
				text : "取消",
				handler : function() {
					$("#editDialog").dialog('close');
				}
			} ]

	});
});




/**
 * 精确查询
 */
function queryAllCondition(){
    var branchofficeIds = $("#companyName").combotree("getValues").join(",");
	$("#protocolPrefixMappingList").datagrid("reload",
	{
        branchofficeId 	: branchofficeIds,
		onOff 			: $("#onOff").combobox("getValue"),
		prefixNumber 	: $("#prefixNumber").val()
		
	});
}
/**
 * 清空
 */
function clearAllCondition(){
//	$("#companyName").combobox("clear");
    $("#companyName").combotree("setValues","");
	$("#onOff").combobox("clear");
	$("#prefixNumber").val("");
	queryAllCondition();
	
}
// 弹出新增dialog
function addDialog(){
	
	/*$("#new_companyName").combobox({
		url : serverName + "/protocolPrefixMappingController/getDepartmentList.do",
		valueField : 'departmentId',
		textField : 'departmentName'
	});*/
    /*2014-07-30 新增操作时，分公司名称修改为树形选择菜单*/
    $("#new_companyName").combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        panelWidth: 250,
        width:124,
        onSelect: function (note) {
            var children = $("#new_companyName").tree("getChildren", note.target);
            if (children.length) {
                $.messager.alert('提示', '请选择营业部', 'info');
                $("#new_companyName").combotree('clear');
            }
        }
    });
	//初始化省、市、区下拉框
	showCityCombopublic("provinceHome", "cityHome", "countyHome");
	
	$("#newDialog").dialog('open');
	//$("#new_companyName").combobox("clear");
	$("#newonOff").combobox("clear");
	$("#new_prefixNumber").val("");
	
}
// 弹出编辑dialog
function editDialog(){
	var row =$("#protocolPrefixMappingList").datagrid("getSelected");
	if(null==row){
		$.messager.alert("提示信息","请选中修改数据所在行");
		return ;
	}
	
	//console.info(row);
	var BRANCHOFFICE_ID=row.BRANCHOFFICE_ID;
	var edit_companyName=row.BRANCHOFFICE_NAME;
	var edit_onOff=row.ON_OFF;
	var edit_prefixNumber=row.PREFIX_NUMBER;
	var edit_prefix_id=row.PREFIX_ID;
	var edit_provinceHome=row.PROVINCEHOME;
	var edit_cityHome=row.CITYHOME;
	var edit_countyHome=row.COUNTYHOME;
	/*console.info(BRANCHOFFICE_ID);
	console.info(edit_companyName);
	console.info(edit_onOff);
	console.info(edit_prefixNumber);
	console.info(edit_provinceHome);
	console.info(edit_cityHome);
	console.info(edit_countyHome);*/
	/*$("#edit_companyName").combobox("setValue",BRANCHOFFICE_ID);
	$("#edit_companyName").combobox("setText",edit_companyName);*/
	$("#editonOff").combobox("select",edit_onOff);
	$("#edit_prefixNumber").val(edit_prefixNumber);
	$("#edit_prefix_id").val(edit_prefix_id);
	$("#edit_provinceHome").combobox("setValue",edit_provinceHome);
	$("#edit_cityHome").combobox("setValue",edit_cityHome);
	$("#edit_countyHome").combobox("setValue",edit_countyHome);
	
	
	//回显出 对应的省  市 区
	showCityCombopublic("edit_provinceHome", "edit_cityHome", "edit_countyHome");
    /*2014-07-30 修改操作时，分公司名称修改为树形选择菜单*/
    $("#edit_companyName").combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        panelWidth: 250,
        width: 150,
        value: BRANCHOFFICE_ID,
        onSelect: function (note) {
            var children = $("#edit_companyName").tree("getChildren", note.target);
            if (children.length) {
                $.messager.alert('提示', '请选择营业部', 'info');
                $("#edit_companyName").combotree('clear');
            }
        }
    });
	$("#editDialog").dialog('open');
}	
	
/**
 * 新增方法
 */
function insertProtocolPrefixMapping(){
	if($("#new_form").form("validate")){
		$("#newDialog").dialog('close');
		var url=serverName + "/protocolPrefixMappingController/insertOrUpdateProtocolPrefixMapping.do";
	/*	$("#companyName").combobox("clear");
		$("#onOff").combobox("clear");
		$("#prefixNumber").val("");*/
		var data="branchofficeName="+$("#new_companyName").combotree("getText")+"&branchofficeId="+$("#new_companyName").combotree("getValue")+"&onOff="+$("#newonOff").combobox("getValue")+"&prefixNumber="+$("#new_prefixNumber").val()
		+"&provinceHome="+$("#provinceHome").combobox("getValue")+"&cityHome="+$("#cityHome").combobox("getValue")+"&countyHome="+$("#countyHome").combobox("getValue");
		ajaxSubmit(url,data, addReturnFunc);
	}else{
			$("#newDialog").dialog('open');
	}
	
}
/**
 * 新增的回调方法
 * @param {} data
 */
function addReturnFunc(data){
	if(data&&data.success){
		console.info("成功");
		$("#provinceHome").combobox("clear");
		$("#cityHome").combobox("clear");
		$("#countyHome").combobox("clear");
		$("#newDialog").dialog('close');
		queryAllCondition();
		$.messager.show({
			title:"提示信息",
			msg:data.msg
		});
	}else{
		//console.info(data.msg)；
		$("#newDialog").dialog('open');
		$.messager.alert("提示信息",data.msg);
	}
}
/**
 * 编辑
 */
function updateProtocolPrefixMapping(){

	if($("#editForm").form("validate")){
		$("#editDialog").dialog('close');
		var url=serverName + "/protocolPrefixMappingController/insertOrUpdateProtocolPrefixMapping.do";
		var data="branchofficeName="+$("#edit_companyName").combobox("getText")+"&branchofficeId="+$("#edit_companyName").combobox("getValue")+"&onOff="+$("#editonOff").combobox("getValue")+"&prefixNumber="+$("#edit_prefixNumber").val()+"&prefixId="+$("#edit_prefix_id").val()
		+"&provinceHome="+$("#edit_provinceHome").combobox("getValue")+"&cityHome="+$("#edit_cityHome").combobox("getValue")+"&countyHome="+$("#edit_countyHome").combobox("getValue");
		
		ajaxSubmit(url,data, editReturnFunc);
	}else{
		$("#editDialog").dialog('open');
	}
	
}
/**
 * 编辑回调方法
 * @param {} data
 */
function editReturnFunc(data){
	if(data&&data.success){
		console.info("成功");
		$("#provinceHome").combobox("clear");
		$("#cityHome").combobox("clear");
		$("#countyHome").combobox("clear");
		$("#editDialog").dialog('close');
		queryAllCondition();
		$.messager.show({
			title:"提示信息",
			msg:data.msg
		});
	}else{
		//console.info(data.msg)；
		$("#editDialog").dialog('open');
		$.messager.alert("提示信息",data.msg);
	}
}
/**
 * 删除合同规则
 */
function deleteProtocolPrefixMapping(){
	var row =$("#protocolPrefixMappingList").datagrid("getSelected");
	if(null==row){
		$.messager.alert("提示信息","请选中要操作的行");
		return ;
	}
	$.messager.confirm('Confirm','确定要进行删除吗?',function(r){   
		if(r){
			var prefix_id=row.PREFIX_ID;
			var url=serverName + "/protocolPrefixMappingController/deleteProtocolPrefixMapping.do";
			var data="prefixId="+prefix_id;
			ajaxSubmit(url,data, deleteReturnFunc);
		}
	});
	
	
}

function deleteReturnFunc(data){
	if(data&&data.success){
		console.info("成功");
		queryAllCondition();
		$.messager.show({
			title:"提示信息",
			msg:data.msg
		});
	}else{
		$.messager.alert("提示信息",data.msg);
	}
	
}


