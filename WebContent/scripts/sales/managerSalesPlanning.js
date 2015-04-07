var lengthAll=0;
var jiaose;
var year;
$(function() {
	$("#year").combobox({
		valueField : "code",
		textField : "value",
		url : serverName + "/salesController/getComboYear.do"
	});
	departmentComboboxTree("areaDepartmentId", true);
	//页面加载时//获取动态的toolbar 默认为：当前年份
	var myDate = new Date();
	//获取完整的年份(4位,1970-????)
	year=myDate.getFullYear();
	$("#loanYear").val(year);
	// loanNumSalesPlanning放款量 列表
	$("#loanNumSalesPlanning").datagrid({
		url : serverName + "/managerSalesPlanningController/selectManagerSalesPlanningLoanSum.do?year="+year,
		pagination : true,
		idfield : 'employeeId',
		striped : true,
		fitColumns : false,
		singleSelect : true,
		rownumbers : true,
		onLoadSuccess : function(data) {// 加载完毕后获取最后一行
            jiaose=data.rows[data.rows.length-1].me;
            var index=data.rows.length-1;
            $('#loanNumSalesPlanning').datagrid('deleteRow',index); 
		},
		toolbar : [{
		        text:'编辑',
		        iconCls:'icon-edit',
		        handler:function(){
		        	//启用保存按钮
		    		$("#iconSave1").linkbutton({disabled:false});
		        	//启用取消编辑按钮
		        	$("#iconRedo1").linkbutton({disabled:false});
		        	//var rows = $('#loanNumSalesPlanning').datagrid('selectAll');
		        	//禁用选择年份框
		        	$("#year").combobox({
		        		disabled:"disabled",
		        		url:""
		        	});
		        	//获取
		        	var loan = $("#loanYear").val();
		        	year=loan;
		        	var rows = $('#loanNumSalesPlanning').datagrid('getRows');
		        	var length = rows.length;
		        	for ( var i = 0; i < length; i++) {
		        		var rowsIndexs =  $('#loanNumSalesPlanning').datagrid('getRowIndex',rows[i]);
		        		if(rows[i].jan==null&&rows[i].feb==null
		        			&&rows[i].mar==null
		        			&&rows[i].apr==null
		        			&&rows[i].may==null
		        			&&rows[i].jun==null
		        			&&rows[i].jul==null
		        			&&rows[i].aug==null
		        			&&rows[i].sep==null
		        			&&rows[i].oct==null
		        			&&rows[i].nov==null
		        			&&rows[i].dec==null||jiaose=="admin"){
		        			$('#loanNumSalesPlanning').datagrid('beginEdit',rowsIndexs);
		        		}else{
		        		}
					}
		        }},{
		        text:'保存',
		        iconCls:'icon-save',
		        id:'iconSave1',
		        disabled:true,
		        handler:function(){
		        	//提示是否要保存
		        	$.messager.confirm('消息','确认要保存吗？',function(b){
		    		if(b){
		    		//禁用保存按钮
		    		$("#iconSave1").linkbutton({disabled:true});
		    		//禁用取消编辑按钮
		    		$("#iconRedo1").linkbutton({disabled:true});
		        	var rows = $('#loanNumSalesPlanning').datagrid('getRows');
		        	var length = rows.length;
		        	if(length==lengthAll){
		        		lengthAll=0;
		        	}
		        	for ( var i = 0; i < length; i++) {
		        		var rowsIndexs =  $('#loanNumSalesPlanning').datagrid('getRowIndex',rows[i]);
		        		if((i+1)==length){
		        			lengthAll=length;
		        		}
		        		$('#loanNumSalesPlanning').datagrid('endEdit',rowsIndexs);
					}
		    			}
		        	});
		        }
		           },{
		        	   text:'取消编辑',
				        iconCls:'icon-redo',
				        id:"iconRedo1",
				        disabled:true,
				        handler:function(){
				        	//禁用保存按钮
				    		$("#iconSave1").linkbutton({disabled:true});
				        	//启用取消编辑按钮
				        	$("#iconRedo1").linkbutton({disabled:true});
				        	//启用选择年份框
				        	$("#year").combobox({
				        		disabled:false
				        	});
				        	var rows = $('#loanNumSalesPlanning').datagrid('getRows');
				        	var length = rows.length;
				        	for ( var i = 0; i < length; i++) {
				        		var rowsIndexs =  $('#loanNumSalesPlanning').datagrid('getRowIndex',rows[i]);
				        		$('#loanNumSalesPlanning').datagrid('cancelEdit',rowsIndexs);
							}
				        } 
		           },{
				        text:'',
				        id: "LoanNumYear"
				        }],
		   	    onAfterEdit:function(rowIndex,rowData,changers){
		   	    	var rows = $('#loanNumSalesPlanning').datagrid('getRows');
		        	var length = rows.length;
		   	    	if(lengthAll==length){
		   	    		var year1 = $("#year").combobox("getValue");
		   	    		if(year1==""){
		   	    			year1=year;
		   	    		}
		   	    		var areaDepartmentId=$("#areaDepartmentId").combotree("getValues");
		   	    		var rowDataAll =  $('#loanNumSalesPlanning').datagrid('getChanges');
			   	    		ajaxSubmit(serverName+"/managerSalesPlanningController/managerSalesPlanningSave.do?year="+year1+"&areaDepartmentId="+areaDepartmentId,
			   	    				{'data': JSON.stringify(rowDataAll)},
			   	    				function(result){
			   	    					if (result.success) {
			   	    					//启用选择年份框
			   	 			        	$("#year").combobox({
			   	 			        		disabled:false
			   	 			        	});
			   	    						$.messager.show({
			   	    							title : "消息",
			   	    							msg : "保存成功！"
			   	    						});
			   	    						var data = new Object();
			   	    						//获取动态的toolbar 
			   	    						//var year = $("#year").combobox("getValue");
			   	    						var year = $("#loanYear").val();
			   	    						var departmentNameValues = $("#areaDepartmentId").combotree("getValues");
			   	    						var departmentNamestr = departmentNameValues.join(",");
			   	    						$("#LoanNumYear" ).linkbutton({text:year+"放款量"});
			   	    						$("#ContractMoneyYear" ).linkbutton({text:year+"放款金额"});
			   	    						var map = {
			   	    								year : year,
			   	    								areaDepartmentId : departmentNamestr
			   	    							};
			   	    							data["paramJsonMap"] = JSON.stringify(map);
			   	    							$('#loanNumSalesPlanning').datagrid('load', data);
			   	    					} else {
			   	    						$.messager.show({
			   	    							title : "消息",
			   	    							msg : "保存失败！"
			   	    						});
			   	    					}
			   	    				});
		   	    	}
		   	    },
		columns :[ [ 
		            {
			title : '客户经理姓名',
			field : 'name',
			width : '100'
		}
		,{
			title : '客户经理(信贷员)ID',
			field : 'comEmpId',
			width : '100',
			hidden:true
		},{
			title : '营业部ID',
			field : 'areaDepartmentId',
			width : '100',
			hidden:true
		},{
			title : '营业部名称',
			field : 'areaDepartmentName',
			width : '100',
			hidden:true
		},{
			title : '主键ID',
			field : 'managerSalesPlanningId',
			width : '100',
			hidden:true
		},{
			title : '1月',
			field : 'jan',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '2月',
			field : 'feb',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '3月',
			field : 'mar',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '4月',
			field : 'apr',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '5月',
			field : 'may',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '6月',
			field : 'jun',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '7月',
			field : 'jul',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '8月',
			field : 'aug',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '9月',
			field : 'sep',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '10月',
			field : 'oct',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		}, {
			title : '11月',
			field : 'nov',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		} , {
			title : '12月',
			field : 'dec',
			width : '100',
			editor:{
				type:'numberbox',
				options:{
					required:false
				}}
		} ] ]
	});
//放款量  end	
	
	
	
	// contractMoneySalesPlanning合同金额  列表 begin
	$("#contractMoneySalesPlanning").datagrid({
		url : serverName + "/managerSalesPlanningController/selectManagerSalesPlanningContractMoney.do?year="+year,
		pagination : true,
		idfield : 'employeeId',
		striped : true,
		fitColumns : false,
		singleSelect : true,
		rownumbers : true,
		onLoadSuccess : function(data) {// 加载完毕后获取最后一行
            jiaose=data.rows[data.rows.length-1].me;
            var index=data.rows.length-1;
            $('#contractMoneySalesPlanning').datagrid('deleteRow',index); 
		},
		toolbar : [{
	        text:'编辑',
	        iconCls:'icon-edit',
	        handler:function(){
	        	//启用保存按钮
	    		$("#iconSave2").linkbutton({disabled:false});
	        	//启用取消编辑按钮
	        	$("#iconRedo2").linkbutton({disabled:false});
	        	//var rows = $('#contractMoneySalesPlanning').datagrid('selectAll');
	        	//禁用选择年份框
	        	$("#year").combobox({
	        		disabled:"disabled",
	        		url:""
	        	});
	        	//获取
	        	var loan = $("#loanYear").val();
	        	year=loan;
	        	var rows = $('#contractMoneySalesPlanning').datagrid('getRows');
	        	var length = rows.length;
	        	for ( var i = 0; i < length; i++) {
	        		var rowsIndexs =  $('#contractMoneySalesPlanning').datagrid('getRowIndex',rows[i]);
	        		if(rows[i].jan==null&&rows[i].feb==null
	        			&&rows[i].mar==null
	        			&&rows[i].apr==null
	        			&&rows[i].may==null
	        			&&rows[i].jun==null
	        			&&rows[i].jul==null
	        			&&rows[i].aug==null
	        			&&rows[i].sep==null
	        			&&rows[i].oct==null
	        			&&rows[i].nov==null
	        			&&rows[i].dec==null||jiaose=="admin"){
	        			$('#contractMoneySalesPlanning').datagrid('beginEdit',rowsIndexs);
	        		}else{
	        		}
				}
	        }},{
		        text:'保存',
		        iconCls:'icon-save',
		        id:'iconSave2',
		        disabled:true,
		        handler:function(){
		        	//提示是否要保存
		        	$.messager.confirm('消息','确认要保存吗？',function(b){
		    		if(b){
		    		//禁用保存按钮
			    	$("#iconSave2").linkbutton({disabled:true});
			    	//禁用取消编辑按钮
			    	$("#iconRedo2").linkbutton({disabled:true});
		        	var rows = $('#contractMoneySalesPlanning').datagrid('getRows');
		        	var length = rows.length;
		        	if(length==lengthAll){
		        		lengthAll=0;
		        	}
		        	for ( var i = 0; i < length; i++) {
		        		var rowsIndexs =  $('#contractMoneySalesPlanning').datagrid('getRowIndex',rows[i]);
		        		if((i+1)==length){
		        			lengthAll=length;
		        		}
		        		$('#contractMoneySalesPlanning').datagrid('endEdit',rowsIndexs);
					}
		    		}
		        	});
		        }
		           },{
		        	   text:'取消编辑',
				        iconCls:'icon-redo',
				        id:"iconRedo2",
				        disabled:true,
				        handler:function(){
				        	//禁用保存按钮
				    		$("#iconSave2").linkbutton({disabled:true});
				        	//启用取消编辑按钮
				        	$("#iconRedo2").linkbutton({disabled:true});
				        	//启用选择年份框
				        	$("#year").combobox({
				        		disabled:false
				        	});
				        	var rows = $('#contractMoneySalesPlanning').datagrid('getRows');
				        	var length = rows.length;
				        	for ( var i = 0; i < length; i++) {
				        		var rowsIndexs =  $('#contractMoneySalesPlanning').datagrid('getRowIndex',rows[i]);
				        		$('#contractMoneySalesPlanning').datagrid('cancelEdit',rowsIndexs);
							}
				        } 
		           },{
				        text:'',
				        id: "ContractMoneyYear"
				        } ],
		   	    onAfterEdit:function(rowIndex,rowData,changers){
		   	    	var rows = $('#contractMoneySalesPlanning').datagrid('getRows');
		        	var length = rows.length;
		   	    	if(lengthAll==length){
		   	    		//获取当前选择的年份
		   	    		var year1 = $("#year").combobox("getValue");
		   	    		if(year1==""){
		   	    			year1=year;
		   	    		}
		   	    		var areaDepartmentId=$("#areaDepartmentId").combotree("getValues");
		   	    		var rowDataAll =  $('#contractMoneySalesPlanning').datagrid('getChanges');
			   	    		ajaxSubmit(serverName+"/managerSalesPlanningController/managerSalesPlanningSaveContractMoney.do?year="+year1+"&areaDepartmentId="+areaDepartmentId,
			   	    				{'data': JSON.stringify(rowDataAll)},
			   	    				function(result){
			   	    					if (result.success) {
			   	    					//启用选择年份框
				   	 			        	$("#year").combobox({
				   	 			        		disabled:false
				   	 			        	});
			   	    						$.messager.show({
			   	    							title : "消息",
			   	    							msg : "保存成功！"
			   	    						});
			   	    						var data = new Object();
			   	    						//获取动态的toolbar 
			   	    						//var year = $("#year").combobox("getValue");
			   	    						var year = $("#loanYear").val();
			   	    						var departmentNameValues = $("#areaDepartmentId").combotree("getValues");
			   	    						var departmentNamestr = departmentNameValues.join(",");
			   	    						$("#LoanNumYear" ).linkbutton({text:year+"放款量"});
			   	    						$("#ContractMoneyYear" ).linkbutton({text:year+"放款金额"});
			   	    						var map = {
			   	    								year : year,
			   	    								areaDepartmentId : departmentNamestr
			   	    							};
			   	    							data["paramJsonMap"] = JSON.stringify(map);
			   	    							$('#contractMoneySalesPlanning').datagrid('load', data);
			   	    					} else {
			   	    						$.messager.show({
			   	    							title : "消息",
			   	    							msg : "保存失败！"
			   	    						});
			   	    					}
			   	    				});
		   	    	}
		   	    	
	        		//判断
	        		/*if(lengthAll!=0){
		   	    		$.ajax({
		   	    			type: 'POST',
		   	    			url: serverName + "/managerSalesPlanningController/managerSalesPlanningSave.do?managerSalesPlanningVo="+rowDataAll,
		   	    			data:rowData,
		   	    			success: function (r) {
		   	    				if (r.success) {
		   	    					$.messager.alert("消息", "第"+rowIndex+"成功！");
		   	    				} else {
		   	    					$.messager.alert("消息", "第"+rowIndex+"保存失败！");
		   	    				}
		   	    			},
		   	    			async: false
		   	    		});
	        		}*/
		   	    	
		   	    },
		columns :[  [ 
			            {
			    			title : '客户经理姓名',
			    			field : 'name',
			    			width : '100'
			    		}
			    		,{
			    			title : '客户经理(信贷员)ID',
			    			field : 'comEmpId',
			    			width : '100',
			    			hidden:true
			    		},{
			    			title : '营业部ID',
			    			field : 'areaDepartmentId',
			    			width : '100',
			    			hidden:true
			    		},{
			    			title : '营业部名称',
			    			field : 'areaDepartmentName',
			    			width : '100',
			    			hidden:true
			    		},{
			    			title : '主键ID',
			    			field : 'managerSalesPlanningId',
			    			width : '100',
			    			hidden:true
			    		},{
			    			title : '1月',
			    			field : 'jan',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '2月',
			    			field : 'feb',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '3月',
			    			field : 'mar',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '4月',
			    			field : 'apr',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '5月',
			    			field : 'may',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '6月',
			    			field : 'jun',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '7月',
			    			field : 'jul',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '8月',
			    			field : 'aug',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '9月',
			    			field : 'sep',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '10月',
			    			field : 'oct',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		}, {
			    			title : '11月',
			    			field : 'nov',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		} , {
			    			title : '12月',
			    			field : 'dec',
			    			width : '100',
			    			editor:{
			    				type:'numberbox',
			    				options:{
			    					required:false
			    				}}
			    		} ]  ]
	});
	//赋值
	$("#LoanNumYear").linkbutton({text:myDate.getFullYear()+"放款量"});
	$("#ContractMoneyYear").linkbutton({text:myDate.getFullYear()+"放款金额"});
});
//搜索放款量功能
function constructSalesPlanningLoanNumTable(){
	if ($("#searchForm").form("validate")) {
		//$.messager.alert("温馨提示", '请切换到对应【放款量(单位/笔)】的列表哦！', "info");
	var data = new Object();
	//获取动态的toolbar 
	var year = $("#year").combobox("getValue");
	var loanYear=$("#loanYear").val();
	if(year==""){
		year=loanYear;
	}
	var departmentNameValues = $("#areaDepartmentId").combotree("getValues");
	var departmentNamestr = departmentNameValues.join(",");
	$("#LoanNumYear" ).linkbutton({text:year+"放款量"});
	$("#loanYear").val(year);
	$("#ContractMoneyYear" ).linkbutton({text:year+"放款金额"});
	var map = {
			year : year,
			areaDepartmentId : departmentNamestr
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#loanNumSalesPlanning').datagrid('load', data);
	}else{
		$.messager.alert("提示", '请选择营业部', "warning");
	}
}
//搜索合同金额功能
function constructSalesPlanningContractTable(){
	if ($("#searchForm").form("validate")) {
		//$.messager.alert("温馨提示", '请切换到对应【合同金额(单位/万元)】的列表哦！', "info");
	var data = new Object();
	//获取动态的toolbar 
	var year = $("#year").combobox("getValue");
	var loanYear=$("#loanYear").val();
	if(year==""){
		year=loanYear;
	}
	var departmentNameValues = $("#areaDepartmentId").combotree("getValues");
	var departmentNamestr = departmentNameValues.join(",");
	$("#LoanNumYear" ).linkbutton({text:year+"放款量"});
	$("#ContractMoneyYear" ).linkbutton({text:year+"放款金额"});
	var map = {
			year : year,
			areaDepartmentId : departmentNamestr
		};
		data["paramJsonMap"] = JSON.stringify(map);
		$('#contractMoneySalesPlanning').datagrid('load', data);
	}else{
		$.messager.alert("提示", '请选择营业部', "warning");
	}
}
//传参数
/*function onClickNum(num){
 * 在放款量（单位/笔数）
	console.info(num);
	alert(num);
}*/

