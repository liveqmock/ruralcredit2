var datagrid;
window.onresize = function(){
    setTimeout(function(){
    	datagrid.resizeDataGrid(0, 0, 0, 0);
    }, 500);
};
$(function(){
    datagrid = $('#codetables').datagrid({
        url:'../../../dicRequest/listPageByserialNum.do',
        toolbar:'#toolbar',
        pagination:true,
        rownumbers:true,
        fitColumns:true,
        singleSelect:true,
        idField:'codaTableId',
        columns:[[
			{field:'section',hidden:true},   
			{field:'codeVlue',title:'名称',width:100,editor:{type:'validatebox',options:{required : true}}},
			{field:'codeKey',title:'编码',width:100},   
			{field:'codeNote',title:'说明',width:100,editor:'text'},
			{field:'codaTableId',hidden:true},
			{field:'available',title:'启用操作',width:100,formatter:function(value,rowData,rowIndex){
				if(value == -1){
					return '<a href="#" id="btn" onclick="saveEdit('+rowIndex+')" class="easyui-linkbutton" plain="true"><font color="green">启用</font></a>';
				}else{
					return '<a href="#" id="btn" onclick="saveEdit('+rowIndex+')" class="easyui-linkbutton" plain="true"><font color="red">停用</font></a>';
				}
			}}
		]],
		onLoadSuccess:function(data){
			$.parser.parse();
		}
    });
    //设置分页控件 
    var p = datagrid.datagrid('getPager');
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
});

/** 编辑一行数据 **/
function editRow(rowData){
	$('#dlg1').dialog('open').dialog('setTitle', '添加字典信息');
	$('#insertForm1').form('load',rowData);
}
/** 编辑一行数据ByButton **/
function editRowByButton(){
    var row = datagrid.datagrid('getSelected');
    if (row){
    	editRow(row);
    }else{
		$.messager.alert("提示信息","请选择一行数据进行编辑！");
	}
}
/** 修改启用状态 **/
function saveEdit(rowIndex){
	datagrid.datagrid("selectRow",rowIndex);
	var row = datagrid.datagrid("getSelected");
	if(row){
		if(row.available==-1){
			row.available = 0;
		}else{
			row.available = -1;
		}
		var effectRow = new Object();
		effectRow["updated"] = JSON.stringify(row);
		ajaxSubmit("../../../dicRequest/updateData.do","post",effectRow,retFun,false);
	}
}
/** 修改一行数据 **/
function updateRow(){
	var effectRow = new Object();
	effectRow["updated"] = JSON.stringify($('#insertForm1').serializeObject());
	ajaxSubmit("../../../dicRequest/updateData.do","post",effectRow,retFun,false);
}
function retFun(response){
	datagrid.datagrid('reload'); 
	if(response.success){
		$.messager.alert("提示信息", response.msg);
	}else{
		$.messager.alert("提示信息", response.msg);
	}
}
/**新增营业部**/
function showDepartmentSN() {
	$('#insertForm').form('clear');
	$('#mysection').val("departmentSN");
	/*$("#departmentName").combobox({
    	url:serverName+"/CustomerConsult/departmentList.do",
    	valueField:"departmentName",
    	textField:"departmentName",
    	mote:"remote",
    	panelHeight:'auto',
    	editable:false,
		multiple:false,
		required:true,
		width:124,
		value:"请选择",
		validType:"remoteValidate['mysection','请先填写字典分类','codeKey','已存在,请重新选择','../../../dicRequest/validateCodeValue.do']"
	});*/
    /*2014-07-28 添加营业部编码时，字典名称修改为树形选择菜单*/
    $("#departmentName").combotree({
        url: serverName + "/easyUIController/getDepartmentComboboxTree.do",
        multiple: false,
        editable: false,
        panelWidth: 250,
        width:124,
        value:"请选择",
        validType:"remoteValidate['mysection','请先填写字典分类','codeKey','已存在,请重新选择','../../../dicRequest/validateCodeValue.do']",
        onSelect: function (note) {
            var children = $("#departmentName").tree("getChildren", note.target);
            if (children.length) {
                $.messager.alert('提示', '请选择营业部', 'info');
                $("#departmentName").combotree('clear');
            }
        }
    });
	$('#dlg').dialog('open').dialog('setTitle', '添加营业部编码');
}
/**新增产品**/
function showProductSN() {
	$('#insertForm1').form('clear');
	$('#mysection1').val("productSN");
	$("#productName").combobox({
    	url:serverName+"/borrowingProducts/queryProByDepartByList.do?departmentId="+departmentId,
    	valueField:"productName",
    	textField:"productName",
    	mote:"remote",
    	panelHeight:'auto',
    	editable:false,
		multiple:false,
		required:true,
		width:124,
		value:"请选择",
		validType:"remoteValidate['mysection1','请先填写字典分类','codeKey','已存在,请重新选择','../../../dicRequest/validateCodeValue.do']"
	});
	$('#dlg1').dialog('open').dialog('setTitle', '添加产品编码');
}
/** 保存增加的数据字典 **/
function saveInsert(){
    /*2014-07-28 添加营业部编码时，字典名称修改为树形选择菜单*/
    $('#departmentName').combotree('setValue',$('#departmentName').combotree('getText'));
    if($('#insertForm').form('validate')){
    	ajaxSubmit("../../../dicRequest/saveInsert.do","post",$('#insertForm').serializeObject(),retFun,false);
//    	ajaxSubmit('../../../dicRequest/saveInsert.do',$('#insertForm').serializeObject(),saveRetFunc);
    }
}
/** 保存增加的数据字典 **/
function saveInsert1(){
    if($('#insertForm1').form('validate')){
    	ajaxSubmit("../../../dicRequest/saveInsert.do","post",$('#insertForm1').serializeObject(),retFun,false);
    }
}
/** 删除一行数据 **/
function removeRow(){
	var row = datagrid.datagrid('getSelected');
	if (row){
		$.messager.confirm('删除数据字典','你确定要删除该数据字典吗?',function(r){
			if (r){
				var rowIndex = datagrid.datagrid('getRowIndex',row);
				datagrid.datagrid('deleteRow',rowIndex);
				var effectRow = new Object();
				effectRow["deleted"] = JSON.stringify(row);
				ajaxSubmit("../../../dicRequest/deleteData.do","post",effectRow,retFun,false);
			}
		});
	}else{
		$.messager.alert("提示信息","请选择一行数据进行删除！");
	}
}
