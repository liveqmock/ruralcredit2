$(function(){
    /*更改：分公司名称下拉选择框为两级树形菜单
	$("#branchofficeName").combobox({
		url : serverName + "/protocolSuffixMappingController/getDepartmentList.do",
		valueField : 'departmentId',
		 textField : 'departmentName'
	});*/
    departmentComboboxTreeWithPanelWidth("branchofficeName",false,250);
	$("#protocolSuffixMappingList").datagrid({
			title:" 信息",
			url : serverName+'/protocolSuffixMappingController/queryProtocolSuffixMappingList.do',
			idfield : 'cod',
			singleSelect : true,
			striped : true,
			pagination: true,
            fitColumns : true,
            scrollbarSize: 0,
			columns : [ 
						[
				      	    {field:'branchofficeName',title:'分公司',width:100},
				      	    {field:'suffixNumber',title:'年度起始编号',width:100},
				      	    {field:'simpleYear',title:'年度',width:100,
				      	   		 formatter: function(value){
										return "20"+value;
								} 
							},
				      	    {field:'suffixId',title:'编号id',width:100,hidden:true},
				      	    {field:'branchofficeId',title:'分公司id',width:100,hidden:true}
		      	  		 ]
      	   ],
      	    toolbar: '#toolbar_div'
	}); 
	$("#newDialog").show().dialog({
		title:"新增",
		closed : true,
		modal : true,
		inline : false,
		width : 500,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "addButton",
			text : "保存",
			handler : function() {
				//保存方法
				
				insertProtocolSuffixMapping();
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
		title:"编辑",
		closed : true,
		modal : true,
		inline : false,
		width : 500,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "addButton",
			text : "保存",
			handler : function() {
				//保存方法
				
				editProtocolSuffixMapping();
					}
			}, {
				id : "cencleButton",
				text : "取消",
				handler : function() {
					$("#editDialog").dialog('close');
				}
			} ]

	});
	$("#new_suffixNumber").on("blur", function(){
		completionZero($(this));
	});
	$("#edit_suffixNumber").on("blur", function(){
		completionZero($(this));
	});
});



/**
 * 精确查询
 */
function queryAllCondition(){
    var branchofficeIds = $("#branchofficeName").combotree("getValues").join(",");
	$("#protocolSuffixMappingList").datagrid("reload",
	{
		branchofficeId 	    : branchofficeIds,
		simpleYear 			: $("#simpleYear").combobox("getValue"),
		suffixNumber 	    : $("#suffixNumber").val()
		
	});
}
/**
 * 清空
 */
function clearAllCondition(){
//	$("#branchofficeName").combobox("clear");
    $("#branchofficeName").combotree("setValues","");
	$("#simpleYear").combobox("clear");
	$("#suffixNumber").val("");
    queryAllCondition();
	
	
}
// 弹出新增dialog
function addDialog(){
	/*$("#new_branchofficeName").combobox({
		url : serverName + "/protocolSuffixMappingController/getDepartmentList.do",
		valueField : 'departmentId',
		textField  : 'departmentName'
	});*/
    /*2014-07-30 新增操作时，分公司名称修改为树形选择菜单*/
    $("#new_branchofficeName").combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        panelWidth: 250,
        width:150,
        onSelect: function (note) {
            var children = $("#new_branchofficeName").tree("getChildren", note.target);
            if (children.length) {
                $.messager.alert('提示', '请选择营业部', 'info');
                $("#new_branchofficeName").combotree('clear');
            }
        }
    });
	
	//closed : true,防止两次渲染
	$("#newDialog").dialog('open');
	$("#new_branchofficeName").combobox("clear");
	$("#newsimpleYear").combobox("clear");
	$("#new_suffixNumber").val("");
	
}
/**
 * 新增方法
 */
function insertProtocolSuffixMapping(){
	
	if($("#new_form").form("validate")){
		$("#newDialog").dialog('close');
		var url=serverName + "/protocolSuffixMappingController/insertOrUpdateProtocolSuffixMapping.do";
		var data="branchofficeName="+$("#new_branchofficeName").combotree("getText")+"&branchofficeId="+$("#new_branchofficeName").combotree("getValue")+"&simpleYear="+$("#newsimpleYear").combobox("getValue")+"&suffixNumber="+$("#new_suffixNumber").val();
		ajaxSubmit(url,data, addReturnFunc);
	}else{
		$("#newDialog").dialog('open');
	}
	
}

function addReturnFunc(data){
	console.info(data);
	if(data&&data.success){
		queryAllCondition();
		console.info("成功");
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

// 弹出编辑dialog
function editDialog(){
	var row =$("#protocolSuffixMappingList").datagrid("getSelected");
	if(null==row){
		$.messager.alert("提示信息","请选中修改数据所在行");
		return ;
	}
	$("#editDialog").dialog('open');
	var edit_suffixId=row.suffixId;
	var branchofficeId=row.branchofficeId;
	var edit_branchofficeName=row.branchofficeName;
	var edit_simpleYear=row.simpleYear;
	var edit_suffixNumber=row.suffixNumber;
	$("#edit_branchofficeName").combobox("setValue",branchofficeId);
	$("#edit_branchofficeName").combobox("setText",edit_branchofficeName);
	$("#editsimpleYear").combobox("clear");
	$("#editsimpleYear").combobox("setValue",edit_simpleYear);
	$("#editsimpleYear").combobox("setText","20"+edit_simpleYear);
	$("#edit_suffixNumber").val(edit_suffixNumber);
	$("#old_suffixNumber").val(edit_suffixNumber);
	$("#edit_suffixId").val(edit_suffixId);
}	
/**
 * 编辑
 */
function editProtocolSuffixMapping(){
	var old_suffixNumber=$("#old_suffixNumber").val();
	var new_suffixNumber=$("#edit_suffixNumber").val();
	
	if($("#edit_form").form("validate")){
		if(parseFloat(new_suffixNumber)<parseFloat(old_suffixNumber)){
			$.messager.alert("提示信息","输入的新值小于旧值,请重新输入");
			return ;
		}
		$("#editDialog").dialog('close');
		var url=serverName + "/protocolSuffixMappingController/insertOrUpdateProtocolSuffixMapping.do";
		var data="branchofficeName="+$("#edit_branchofficeName").combobox("getText")+"&branchofficeId="+$("#edit_branchofficeName").combobox("getValue")+"&simpleYear="+$("#editsimpleYear").combobox("getValue")+"&suffixNumber="+$("#edit_suffixNumber").val()+"&suffixId="+$("#edit_suffixId").val();
		ajaxSubmit(url,data, editReturnFunc);
	}else{
		$("#newDialog").dialog('open');
	}
}

function editReturnFunc(data){
	if(data&&data.success){
		console.info("成功");
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
 * 补全5位0
 * @param {} obj
 */
function completionZero(obj){
	var value=obj.val();
	var len=value.length;
	if(len==1){
		obj.val("000"+value);
	}else if(len==2){
		obj.val("00"+value);
	}else if(len==3){
		obj.val("0"+value);
	}
}


	

