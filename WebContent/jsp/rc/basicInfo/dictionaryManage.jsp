<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>数据字典配置父子关系</title>
  <jsp:include page="../include/easyui.jsp"></jsp:include>
  <base href="<%=basePath%>">
  <script type="text/javascript">
  	$(function(){
  		$("#sectionCondition").combobox({
  			url:"<%=basePath%>dicRequest/selectSection.do",
  			textField:"SECTION",
  			valueField:"SECTION"
  		});
  		
  		 $('#parentCode').datagrid({
  	        url:'<%=basePath%>/dicRequest/forManage.do',
  	        title:"父节点列表",
  	        toolbar:'#toolbar',
  	        pagination:true,
  	        rownumbers:true,
  	        fitColumns:true,
  	      	queryParams:{querayFlag:"T"},
  	        singleSelect:true,
  	        idField:'codaTableId',
  	        columns:[[   
				{checkbox:true},  
				{field:'codaTableId',title:"id",width:100},
  				{field:'section',title:'字典分类',width:100},   
  				{field:'codeKey',title:'字典代码',width:100},   
  				{field:'codeVlue',title:'字典名称',width:100},
  				{field:'codeNote',title:'说明',width:100},
  				{field:'sequence',title:'序列',width:100},
  				{field:'parentId',title:'父节点',width:100},   
   				{field:'available',title:'是否启用',width:100,formatter:function(value,rowData,rowIndex){
   					if(value == -1){
   						return '<font color="green">停&nbsp;用</font>';
   					}else{
   						return '<font color="red">启&nbsp;用</font>';
   					}
   				}}
  			]],
  			onLoadSuccess:function(data){
  				$.parser.parse();
  			}
  	    });
  		 
  		 $('#subCode').datagrid({
   	        url:'<%=basePath%>/dicRequest/forManage.do',
   	     	title:"子节点列表",
   	        toolbar:'#toolbar',
   	        pagination:true,
   	        rownumbers:true,
   	        fitColumns:true,
   	      	queryParams:{querayFlag:"T"},
   	        idField:'codaTableId',
   	        columns:[[   
 				{checkbox:true},  
 				{field:'codaTableId',title:"id",width:100},
   				{field:'section',title:'字典分类',width:100},   
   				{field:'codeKey',title:'字典代码',width:100},   
   				{field:'codeVlue',title:'字典名称',width:100},
   				{field:'codeNote',title:'说明',width:100},
   				{field:'sequence',title:'序列',width:100},
   				{field:'parentId',title:'父节点',width:100},   
   				
   				{field:'available',title:'是否启用',width:100,formatter:function(value,rowData,rowIndex){
   					if(value == -1){
   						return '<font color="green">停&nbsp;用</font>';
   					}else{
   						return '<font color="red">启&nbsp;用</font>';
   					}
   				}}
   			]],
   			onLoadSuccess:function(data){
   				$.parser.parse();
   			}
   	    });
  	})
  	
  	function showParentList(){
  		if($.trim($("#sectionCondition").combobox("getValue")) == null ||$.trim($("#sectionCondition").combobox("getValue")) == ""){
  			alert("请选择查询条件");
  		}else{
	  		$('#parentCode').datagrid("load",{
	  			section:$("#sectionCondition").combobox("getValue"),
	  			codeValue:$("#codevalue").val(),
	  			querayFlag: ""
	  		});
  		}
  	}
  	
  	function showSubList(){
  		if($.trim($("#sectionCondition").combobox("getValue")) == null ||$.trim($("#sectionCondition").combobox("getValue")) == ""){
  			alert("请选择查询条件");
  		}else{
	  		$('#subCode').datagrid("load",{
	  			section:$("#sectionCondition").combobox("getValue"),
	  			codeValue:$("#codevalue").val(),
	  			querayFlag: ""
	  		});
  		}
  	}
  	
  	function contact(){
  		var data = $("#subCode").datagrid("getSelections");
  		//选中的数据个数 (子节点)
  		var length = data.length;
  		var codeIds = new Array();
  		for(var i = 0 ; i < length;i++){
  			codeIds.push(data[i].codaTableId);
  		}
  		var dataP = $("#parentCode").datagrid("getSelections");
  		//选中的数据个数  （父节点）
  		var lengthP = dataP.length;
  		if(lengthP == 0){
  			alert ("请选择一个父节点");
  			return;
  		}
  		if(length == 0){
  			alert ("请选择至少一个子节点");
  			return;
  		}
  		var parentId = dataP[0].codaTableId;
  		$.messager.confirm("消息","确定关联父子关系吗?",function(r){
  			if(r){
  				$.ajax({url:"<%=basePath%>dicRequest/updateParentId.do",
					  data:{parentId:parentId,codeIds:JSON.stringify(codeIds)},
					  dataType:"json",
					  success:function(message){
						  if(message.success){
							  $.messager.alert("消息","操作成功");
							  $("#contactButton").linkbutton("enable");
							  $("#subCode").datagrid("clearSelections");
							  $("#parentCode").datagrid("clearSelections");
							  $("#subCode").datagrid("reload");
						  }else{
							  $("#contactButton").linkbutton("enable");
							  $.messager.show({title:"消息",msg:message.msg});
						  }
					  }
				});
  			}
  		}); 
  	}
  	
  	function deleteContact(){
  		var data = $("#subCode").datagrid("getSelections");
  		//选中的数据个数 (子节点)
  		var length = data.length;
  		var codeIds = new Array();
  		for(var i = 0 ; i < length;i++){
  			codeIds.push(data[i].codaTableId);
  		}
  		if(length == 0){
  			alert ("请选择至少一个子节点");
  			return;
  		}
  		$.messager.confirm("消息","确定删除父子关系吗?",function(r){
  			if(r){
  				$.ajax({url:"<%=basePath%>dicRequest/deleteParentId.do",
					  data:{codeIds:JSON.stringify(codeIds)},
					  dataType:"json",
					  success:function(message){
						  if(message.success){
							  $.messager.alert("消息","操作成功");
							  $("#contactButton").linkbutton("enable");
							  $("#subCode").datagrid("clearSelections");
							  $("#parentCode").datagrid("clearSelections");
							  $("#subCode").datagrid("reload");
						  }else{
							  $("#contactButton").linkbutton("enable");
							  $.messager.show({title:"消息",msg:message.msg});
						  }
					  }
				});
  			}
  		}); 
  	}
  </script>
  </head>
  
  <body class="easyui-layout">
    	<div region="center">
    		筛选条件:<input id="sectionCondition"/>
    		字典名称：<input id="codevalue">
    		 <a class='easyui-linkbutton' onclick="showParentList();">搜索父节点列表</a>
    		 <a class="easyui-linkbutton" onclick="showSubList();">搜索子节点列表</a>
    		 <a id="contactButton" class="easyui-linkbutton" onclick="contact();">关联父子节点</a>
    		 <a id="deleteContactButton" class="easyui-linkbutton" onclick="deleteContact();">去除关联关系</a>
    		<table id="parentCode"></table>
    		<br/>
    		<table id="subCode"></table>
    	</div>
  </body>
</html>
