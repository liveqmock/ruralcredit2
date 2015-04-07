var datagrid;
window.onresize = function(){
    setTimeout(function(){
    	datagrid.resizeDataGrid(0, 0, 0, 0);
    }, 500);
};
$(function(){
    datagrid = $('#codetables').datagrid({
        url:'../../../dicRequest/listPage.do',
        toolbar:'#toolbar',
        pagination:true,
        rownumbers:true,
        fitColumns:true,
        singleSelect:true,
        idField:'codaTableId',
        columns:[[   
			{field:'section',title:'字典分类',width:100,editor:{type:'validatebox',options:{required : true}}},   
			{field:'codeKey',title:'字典代码',width:100},   
			{field:'codeVlue',title:'字典名称',width:100,editor:{type:'validatebox',options:{required : true}}},
			{field:'codeNote',title:'说明',width:100,editor:'text'},
			{field:'sequence',title:'序列',
				width:100,
				editor:{
					type:'validatebox',
					options:{required : true}
				},
				sortable:true
			},   
			{field:'codaTableId',hidden:true},
			{field:'available',title:'启用操作',width:100,formatter:function(value,rowData,rowIndex){
				if(value == -1){
					return '<a href="#" id="btn" onclick="saveEdit('+rowIndex+')" plain="true" class="easyui-linkbutton"><font color="green">启&nbsp;用</font></a>';
				}else{
					return '<a href="#" id="btn" onclick="saveEdit('+rowIndex+')" plain="true" class="easyui-linkbutton"><font color="red">停&nbsp;用</font></a>';
				}
			}}
		]],
		onDblClickRow:function(rowIndex, rowData){
			editRow(rowData);
		},
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
/** 按条件查询 **/
function searchByParam(){
	var sectionVal = $.trim($("#section").val());
	$('#codetables').datagrid('load',{section:sectionVal});
}
/** 清空查询条件 **/
function clearFun() {
	$('#toolbar input').val('');
	datagrid.datagrid('load', {});
}

/** 编辑一行数据 **/
function editRow(rowData){
	$('#dlg1').dialog('open').dialog('setTitle', '编辑字典信息');
	$('#insertForm1').form('load',rowData);
}
/** 编辑一行数据ByButton **/
function editRowByButton(){
    var row = datagrid.datagrid('getSelected');
    if (row){
    	editRow(row);
    }else{
		$.messager.alert("提示信息","请选择一行数据进行删除！");
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
/**新增**/
function showInsertView() {
	$('#dlg').dialog('open').dialog('setTitle', '添加字典信息');
	$('#insertForm').form('clear');
}
/** 保存增加的数据字典 **/
function saveInsert(){
    if($('#insertForm').form('validate')){
    	ajaxSubmit("../../../dicRequest/saveInsert.do","post",$('#insertForm').serializeObject(),retFun,false);
    }
}
/** 删除一行数据 
//回调函数
function saveRetFunc(result){
	 if(result.success){
		 $.messager.alert("提示信息", "保存成功！","info",function(){
			 $('#dlg').dialog('close');    
			 datagrid.datagrid('reload'); 
		 });
     }else{
         $.messager.alert('错误',result.msg,"error");  
     }     
}
**/
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

//重新加载数字字典
function reloadInitGetAllDic(){
	ajaxSubmit(serverName+"/dicRequest/reloadInitGetAllDic.do",function (data){
		if (data.success == true) {
			$.messager.alert("消息", "加载成功");
		} else {
			$.messager.alert("消息", "加载失败，原因："+data.mgs);
		}
	}
	);
}