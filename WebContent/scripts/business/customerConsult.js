var editrow = undefined;
window.onresize = function(){
    setTimeout(function(){
    	var tabHeight2 = $("#customerConsultTab").height();
    	var heightMa2 = 50+tabHeight2;
  	   $('#customerComsult').resizeDataGrid(heightMa2, 20, 0, 0);
    }, 500);
};
var condistion = 0;
if(conditons == null || conditons == ""){
	condistion = "?history='0'";
}
var consultDateEndStart = "";
$(function() {
	var dic = new DataDictionary();
	dic.addDic("consultWayForm_consultWay_Y","consultWay");
//	dic.addDic("industryCategory_industryCategory_Y","industryCategory");
	dic.addDic("consultWay","consultWay");
	dic.addDic("borrowUseForm_borrowUse_Y","borroerUserConsult");
//	dic.addDic("infomationSourceForm_infomationSource_Y","infomationSource");
	dic.addDic("processResultForm_processResult_Y","processResult");
	dic.addDic("gender_gender_Y","gender");
	//	dic.addDic("processCauses","processCauses");
	
	dic.addDic("processCausesSearch","processCauses");
	dic.addDic("borrowUseSearch","borroerUserConsult");
	dic.addDic("infomationSourceSearch","infomationSource");
	dic.addDic("processResultSearch","processResult");
	
//	dic.addDic("consultWayFormEdit_consultWay_Y","consultWay");
	dic.addDic("borrowUseFormEdit_borrowUse_Y","borroerUserConsult");
	dic.addDic("processResultFormEdit_processResult_Y","processResult");
//	dic.addDic("genderEdit_gender_Y","gender");
	
	
    dic.dicAjax();
    $("#consultDateForm").datetimebox(
			{
				required:true,
				editable:false,
				onChange : function(newValue, oldValue) {
					var loanTime = $("#consultDateForm").datetimebox("getValue");
					if (newValue!="") {/*
						if (new Date(Date.parse(newValue.replace(/-/g,"/"))) > new Date(Date.parse(sysDate.replace(/-/g,"/")))) {
							$.messager.alert("消息", "咨询时间不能大于当前时间,当前系统时间为-"
									+ sysDate + "-");
						}*/
						ajaxSubmit(serverName+"/CustomerConsult/validConsultTime.do",{consultDate:loanTime},function(result){
							if(!result.success){
								$.messager.show({
									title:"消息",
									msg:result.msg
								});
							}
						});
					}
				}
			});
	$("#consultDateBegin").datetimebox({
		onSelect : function() {
			var consultDateBegin = $("#consultDateBegin").datetimebox("getValue");
			var consultDateEnd = $("#consultDateEnd").datetimebox("getValue");
			if (consultDateEnd != null && consultDateEnd != "") {
				if (consultDateBegin > consultDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#consultDateBegin").datetimebox("setValue", "");
					return;
				}
			}

		}
	});
	
	$("#consultDateEnd").datetimebox({
		onSelect : function() {
			var consultDateBegin = $("#consultDateBegin").datetimebox("getValue");
			var consultDateEnd = $("#consultDateEnd").datetimebox("getValue");
			if (consultDateEnd != null && consultDateEnd != "") {
				if (consultDateBegin > consultDateEnd) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#consultDateEnd").datetimebox("setValue", "");
					return;
				}else{
					//consultDateEndStart = $("#consultDateEnd").datebox("getValue").;
					var consultDateEnd = new Date($("#consultDateEnd").datebox("getValue"));
					//var date1 = new Date(consultDateEnd.getTime()+1000*3600*24);
					// consultDateEndStart  = date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();
				}
			}

		}
	});
    
//    $("#departmentName").combobox({
//    	url:serverName+"/CustomerConsult/departmentList.do",
//    	valueField:"departmentId",
//    	textField:"departmentName",
//    	mote:"remote",
//    	panelHeight:'auto',
//    	editable:false,
//		multiple:false,
//		onLoadSuccess:function(){
//			$("#departmentName").combobox('select','');
//		}
//    });
    
    departmentComboboxTree("departmentName", false);
    
    
    
	$("#customerComsult").datagrid({
		url : serverName + "/CustomerConsult/searchPagnation.do"+conditons,
		pagination : true,
		rownumbers : true,
		idfield : "customerConsultId",
		queryParams:{},
		height:370,
		singleSelect : true,
		loadMsg : '正在加载....',
		frozenColumns:[[
		                {title:"操作", field:"optionFlag", width: 155, formatter:optionFormatter
	       }]],
		columns : [ [  {field : "status",title : "状态",width: 70,sortable:true,formatter:function(value){
				if($.trim(value) == "1"){
					return "已申请";
				}
				if($.trim(value) == "0"){
					return "未申请";
				}
			}
		}, { field : "consultDate", title : "咨询时间", width: 125, sortable:true
		},
            { field: "createDate", title: "首次创建时间", width: 125, sortable: true
            },
            {field : "operateTime",title : "最后操作时间",width: 125,sortable:true
		}, {field : "consultWay",title : "咨询方式",width: 80,hidden:true,sortable:true
		},{field : "consultWayDetail",title : "咨询方式",width: 80,sortable:true
		}, {field : "customerName",title : "客户姓名",width: 90,editor:{type:"validatebox",options:{required:true}}
		}, {field : "telphone",title : "联系电话",width: 100,hidden:true
		}, { field : "genderDetail", title : "性别", width: 100 
		}, {field : "gender",title : "性别",width: 70,hidden:true,formatter:function(value){
				if(value == '0'){
					return "男";
				}
				if(value == '1'){
					return "女";
				}
			}  
		}, {field : "address",title : "客户住址",width: 100
		}, {field : "borrowAmount",title : "咨询金额(元)",width: 90
		}, {field : "borrowUse",title : "借款用途",width: 100,hidden:true
		}, {field : "borrowUseDetail",title : "借款用途",width: 100,sortable:true
		}, {field : "infomationSource",title : "信息来源",width: 100,hidden:true
		}, {field : "infomationSourceDetail",title : "信息来源",width: 90,sortable:true,
                formatter: function (value, row, index) {
                    return value == null ? row.subInfSrcValue : value;
                }
		},{field : "processCauses",title : "处理原因",width: 100,hidden:true,editor : {
				type : 'validatebox'
			} 
		}, {field : "processResult",title : "客户分类",width: 100,hidden:true	
		},{field : "processResultDetail",title : "客户分类",width: 100
		},{field : "processCausesDetail",title : "客户标签",width: 100,editor : {
				type : 'validatebox'
			}
		}, {field : "industryCategory",title : "借款用途行业",width: 100,hidden :true
		}, {field : "industryCategoryDetail",title : "借款用途行业",width: 100
		}, {field : "remark",title : "备注",width: 100
		},{field : "departmentName",width: 100,title : "分公司名称",sortable:true	
		},{field : "departmentId",width: 100,title : "分公司ID",hidden :true
		}, {field : "receptionist",title : "接待员工",width: 100,sortable:true	
		}, {field : "receptionistId",title : "接待员工ID",width: 100,hidden:true	
		}, {field : "count",title : "咨询次数",width: 80
		}, {field : "telphoneMd5",title : "咨询标识",width: 220 ,hidden:true
		}, {field : "history",title : "历史标记",width: 90,formatter:function(value){
				if(value == "1"){
					return "历史";
				}else if(value == "0"){
					return "非历史";
				}else{
					return "NULL";
				}
			}
		},{title:"业务编号", field:"businessNumber", width: 120
		},{title:"创建客户经理", field:"createManager", width: 120,sortable:true}
		,{title:"创建客户经理ID", field:"createManagerId", width: 120,hidden:true}
		] ]
	});
    // 第一次加载时自动变化大小  
	var tabHeight = $("#customerConsultTab").height();
	$("#customerConsultTab").tabs({
		onSelect :function(data){
			var tabHeight1 = $("#customerConsultTab").height();
			var heightMa1 = 50+tabHeight1;
			 $('#customerComsult').resizeDataGrid(heightMa1, 20, 0, 0);  
		}
	});
	var heightMa = 50+tabHeight;
    $('#customerComsult').resizeDataGrid(heightMa, 20, 0, 0);  
});

function ddd(){
	var rows =  $("#customerComsult").datagrid("getRows");
//	for(var i = 0;i < rows.length;i++){
//		var roe =$("#customerComsult").datagrid("selectRecord",rows[i].customerConsultId);
//	}
$("#customerComsult").datagrid("selectRecord","34573");
//	alert($("#customerCo?msult").datagrid("getPager"));
	
}
//编辑咨询
function editConsult(){
	var row = $("#customerComsult").datagrid("getSelected");
	ajaxSubmit(serverName+"/CustomerConsult/selectCustomerConsult.do",{customerConsultId:row.customerConsultId},function(result){
		
		if(result.status == "1"){
				$.messager.alert("消息","该用户已申请借款");
				$("#customerComsult").datagrid("reload");
		}else{
			var row = $("#customerComsult").datagrid("getSelected");
//			var index = $("#customerComsult").datagrid("getRowIndex",row);
//			alert(index);
//			$("#customerComsult").datagrid("beginEdit",index);
			$("#customerComsultDialogEdit").dialog({
		        title:"编辑客户咨询",
		        close:true,
		        modal:true,
		        draggable:false
		    });
			flag = false;
			$('#processCausesEdit').val(row.processCauses);
			$('#processCausesDetail').val(row.processCausesDetail);
			$('#_infomationSourceForm').val(row.subInfSrcKey == null ? row.infomationSource : row.subInfSrcKey);
			$('#_infomationSourceDetail').val(row.subInfSrcValue == null ? row.infomationSourceDetail : row.subInfSrcValue);
		    $("#customerComsultFormEdit").form("load",row);
		    $("#customerComsultFormEdit").form("validate");
		    flag = true;
		    $("#customerComsultDialogEdit").dialog("open"); 
			$("#counterEdit").html("100");
		}
	});
 	
}
//查看申请
function showApplication(){
	var rowdata = $("#customerComsult").datagrid("getSelected");
	ajaxSubmit(serverName+"/creditgroup/selectCreditValidByconsult.do",{customerConsultId:rowdata.customerConsultId},function(message){
		if(message.success){
			var objTabs = window.parent;
			objTabs.addTabFun({
				src : serverName + "/jsp/rc/business/creditApplicationList.jsp?customerConsultId="+rowdata.customerConsultId+"&validState=0",
				title : "信贷申请"
			});
		}else{
			$.messager.alert("消息",message.msg);
		}
	});
	
}
//显示历史
function showHistory(){
	 $('#customerComsult').datagrid("load",{history:""});  
	 condistion = 1;
}

function hideHistory(){
	 $('#customerComsult').datagrid("load",{history:"0"});
	 condistion = 0;
}
function reloadCustomerComsult(){
	clearFuzzy();
	searchFuzzy();
}
function clearFuzzy(){
	$("#fuzzyValue").val("");
}

function searchFuzzy(){
	clearGaoji();
	if(condistion == 0){
		history = "0";
	}
	if(condistion == 1){
		history = "";
	}
	$("#customerComsult").datagrid("load",{
		fuzzyValue:$.trim($("#fuzzyValue").val())
	});
}

function searchGaoji(){
	$("#fuzzyValue").val("");
	if(condistion == 0){
		history = "0";
	}
	if(condistion == 1){
		history = "";
	}
	
	var departmentNameValues=$("#departmentName").combotree("getValues");
	if(departmentNameValues!=null&&departmentNameValues!=""){
		for(var i in departmentNameValues){
			departmentNameValues[i]="'"+departmentNameValues[i]+"'";
		}
	}
	var 	departmentNamestr=departmentNameValues.join(",");
	
	
		
	$("#customerComsult").datagrid("load",{
		departmentId:departmentNamestr,
		consultWay:$("#consultWay").combobox("getValue"),
		customerName:$.trim($("#customerName").val()),
		address:$.trim($("#address").val()),
		telphone:$.trim($("#telphone").val()),
		history:$("#history").combobox("getValue"),
		borrowUse:$("#borrowUseSearch").combobox("getValue"),
		processCauses:$("#processCausesSearch").combobox("getValue"),
		consultDateBegin:$("#consultDateBegin").datebox("getValue"),
		consultDateEnd:$("#consultDateEnd").datebox("getValue"),
		infomationSource:$("#infomationSourceSearch").combobox("getValue"),
		processResult:$("#processResultSearch").combobox("getValue"),
        operateDateBegin:$("#operateDateBegin").datebox("getValue"),
        operateDateEnd:$("#operateDateEnd").datebox("getValue")
	});
}

function clearGaoji() {
    //$("#departmentName").combobox("setValue","");
    $("#departmentName").combotree("setValues", "");
    $("#consultWay").combobox("setValue", "");
    $("#customerName").val("");
    $("#address").val("");
    $("#telphone").val("");
    $("#borrowUseSearch").combobox("setValue", "");
    $("#processCausesSearch").combobox("setValue", "");
    $("#consultDateBegin").datebox("setValue", "");
    $("#consultDateEnd").datebox("setValue", "");
    $("#operateDateBegin").datebox("setValue", "");
    $("#operateDateEnd").datebox("setValue", "");
    $("#infomationSourceSearch").combobox("setValue", "");
    $("#processResultSearch").combobox("setValue", "");
}

function addNewApplication(){
    var row = $("#customerComsult").datagrid("getSelected");
    ajaxSubmit(serverName+"/CustomerConsult/selectCustomerConsult.do",{customerConsultId:row.customerConsultId},function(result){
        if(result.status == "1"){
            $.messager.alert("消息","该用户已申请借款");
            $("#customerComsult").datagrid("reload");
        }else{
            //新增身份证验证功能begin
            /*   	var centerj = window.parent;
             centerj.addTabFun({
             src : serverName+"/CustomerConsult/toAddApplication.do?customerConsultId="+row.customerConsultId,
             title : "新增申请"
             });
               */
            $('#validateIndentityNumberFrame').attr('src', serverName + '/customer/validateIndentityNumber.do?customerConsultId='+row.customerConsultId);
            $("#validateIndentityNumberDialog").dialog({
                title: "身份验证",
                close: true,
                modal: true,
                draggable: true,
                cache: false,
                onClose: function () {
                    $("#validateIndentityNumberFrame").attr('src', "");
                    //may be refresh the list
                    $("#customerComsult").datagrid("reload");
                }
            });
            //新增身份证验证功能end
           $('#validateIndentityNumberDialog').dialog('open');
        }
    });
}
//提交表单
function submitForm(){
			flag = false;
			$("#submitButtonCustomer").linkbutton('disable');
			if(!($("#customerComsultForm").form('validate'))){
				$("#submitButtonCustomer").linkbutton('enable');
				$.messager.alert("消息","请把表单数据填写正确！");
				flag = true;
				return;
			}else{
//
//				$("#consultWayDetail").val($("#consultWayForm").combobox("getText"));
//				$("#genderDetail").val($("#gender").combobox("getText"));
//				//$("#infomationSourceDetail").val($("#infomationSourceForm").combobox("getText"));
//				$("#borrowUseDetail").val($("#borrowUseForm").combobox("getText"));
//				//$("#industryCategoryDetail").val($("#industryCategory").combobox("getText"));
//				$("#processResultDetail").val($("#processResultForm").combobox("getText"));
//				//$("#processCausesDetail").val($("#processCauses").combobox("getText"));

				/*客户咨询-发送短信*/
				$('#send_sms_dialog').dialog({
					title: '确认',
					width: 400,
					height: 160,
					closable: false,
					cache: false,
					modal: true,
					buttons: [{
						text: '是',
						handler: function () {
							var manager_name = $.trim($('#manager_name').val());
							var manager_phone = $.trim($('#manager_phone').val());
							if (!manager_name || manager_name.length > 30) {
								$('#manager_name').attr('placeholder', '请填写您的姓名').focus();
								$('#tip_name').show();
								return;
							}
							if (!manager_phone || !validPhone(manager_phone)) {
								$('#manager_phone').attr('placeholder', '请填写您的电话').focus();
								$('#tip_phone').show();
								return;
							}
							$('#send_sms_dialog').dialog('close');
							var sms_inf = {
								customer_phone: $('#customer_phone').val(),
								manager_name: $('#manager_name').val(),
								manager_phone: $('#manager_phone').val()
							};
							ajaxSubmit(serverName + "/CustomerConsult/sendSMS.do?", sms_inf, function (result) {
								console.info(result.msg);
							});
							save_after_sms();
						}
					}, {
						text: '否',
						handler: function () {
							save_after_sms();
						}
					}]
				});
			}
}

//点击数量显示详细
function numFrom(){
	flag=false;
	ajaxSubmit(serverName+"/CustomerConsult/selectCustomerConsultByTel.do","telphone="+$("#customerComsultForm input[name=telphone]").val(),function(result){
		$("#submitButtonCustomer").linkbutton('enable');
					
});
}


//关闭对话框
function closeDialog(){
	flag = false;
	  $("#customerComsultForm").form("clear");
	  $("#customerComsultDialog").dialog("close");
    $('#isMsg').attr('checked', false);
    $('#myInfo').hide();
}
//关闭提交后弹出对话框
function closeSubmitDialog(){
	flag=false;
	$("#subCustomerForm").form("clear");
	$("#submitDialog").dialog("close");
}



function history(telNumber,telNumberName){
	var row =  $("#customerComsult").datagrid("getSelected");
	$("#detail").dialog({
		title:"咨询历史",
		width : 1000,
		height:400,
		closed:true,
		draggable:false,
		modal:true
	});
	if(row){
		$("#customerConsultName").html(row.customerName);
		$("#customerConsultTepephone").html(row.telphone);
		var telphone = row.telphone;
	}
	$("#detail").dialog("open");
	if(telNumber){
		$("#customerConsultName").html(telNumberName);
		$("#customerConsultTepephone").html(telNumber);
		var telphone = telNumber;
	}
	$("#detailList").datagrid({
		url:serverName+"/CustomerConsult/history.do?telphone="+telphone,
		rownumbers : true,
		singleSelect : true,
		height:345,
		loadMsg : '正在加载....',
		columns : [ [   {
			field : "processResult", title : "处理结果", width: 100, hidden:true
		},  {field : "status", title : "状态", width: 100,
			formatter:function(value){
				if($.trim(value) == "1"){
					return "已申请";
				}
				if($.trim(value) == "0"){
					return "未申请";
				}
			}
		},
            {field: "consultDate", title: "咨询时间", width: 125 },
            {field: "createDate", title: "首次创建时间", width: 125 },
            {field: "operateTime", title: "最后操作时间", width: 125
		}, { field : "consultWay", title : "咨询方式", width: 100, hidden:true
		},{ field : "consultWayDetail", title : "咨询方式", width: 100
		}, { field : "customerName", title : "客户姓名", width: 100
		}, {field : "telphone",title : "联系电话",width: 100,hidden:true
		}, {field : "gender",title : "性别",width: 70,formatter:function(value){
			if(value == '0'){
				return "男";
			}
			if(value == '1'){
				return "女";
			}
		}
		}, {field : "address",title : "客户住址",width: 100
		}, {field : "borrowAmount",title : "咨询金额(元)",width: 100
		}, {field : "borrowUse",title : "借款用途",width: 100,hidden:true
		}, {field : "borrowUseDetail",title : "借款用途",width: 100
		}, { field : "infomationSource", title : "信息来源", width: 100, hidden:true
		}, {field : "industryCategoryDetail",title : "借款用途行业",width: 100
		}, { field : "infomationSourceDetail", title : "信息来源", width: 100,
                formatter: function (value, row, index) {
                    return value == null ? row.subInfSrcValue : value;
                }
		}, {field : "processResult",title : "客户分类",width: 100,hidden:true
		},{field : "processResultDetail",title : "客户分类",width: 100
		},{ field : "processCauses", title : "客户标签key", width: 100, hidden:true, editor : {
				type : 'validatebox'
			}
		},{ field : "processCausesDetail", title : "客户标签", width: 100, editor : {
				type : 'validatebox'
			}
		}, { field : "remark",
			title : "备注", width: 100
		},{ field : "departmentName", width: 100, title : "分公司名称"
		},{ field : "departmentId", width: 100, title : "分公司ID", hidden :true
		}, { field : "receptionist", title : "接待员工", width: 100
		}, { field : "receptionistId", title : "接待员工ID", width: 100, hidden:true
		}, { field : "count", title : "咨询次数", width: 100
		}, {field : "telphoneMd5",title : "咨询标识",width: 220 ,hidden:true
		},{title:"创建客户经理", field:"createManager", width: 120,sortable:true}
		,{title:"创建客户经理ID", field:"createManagerId", width: 120,hidden:true} ]]
	});
}

function validPhone(value) {
    var a = /^[1][3-8]\d{9}$|^((\d{4}|\d{3})-(\d{7,8}))$/;
    if (a.test(value) || value == "") {

        return true;
    }
    else {

        return false;
    }
}

function save_after_sms() {
	$('#send_sms_dialog').dialog('close');
	$('#tip_name').hide();
	$('#tip_phone').hide();
	ajaxSubmit(serverName + "/CustomerConsult/addUpdate.do", $("#customerComsultForm").serialize(), function (result) {
		$("#submitButtonCustomer").linkbutton('enable');
		if (result.success) {

			$.messager.show({
				title: "消息",
				msg: result.msg
			});
			$("#telNumber").val($("#customerComsultForm input[name=telphone]").val());
			$("#telNumberName").val($("#customerComsultForm input[name='customerName']").val());
			//点击提交后弹窗（显示咨询数量）  luohongjie
			ajaxSubmit(serverName + "/CustomerConsult/selectCustomerConsultByTel.do", "telphone=" + $("#customerComsultForm input[name=telphone]").val(), function (result) {
				$("#submitButtonCustomer").linkbutton('enable');
				if (!(typeof (result) == "undefined")) {
					if (result.customerNumber != "0") {
						$("#num_a").html(result.customerNumber + "条咨询记录");
					} else {
						$("#numtd_a").html(result.customerNumber + "条咨询记录");
					}
					if (result.borrowerNum != "0") {
						$("#num_b").html(result.borrowerNum + "次借款记录");
						$("#numtd_b").html("");
						$("#numborrower").val(result.borrowerNum);
					} else {
						$("#num_b").html("");
						$("#numtd_b").html(result.borrowerNum + "次借款记录");
					}
					if (result.guantorNum != "0") {
						$("#num_c").html(result.guantorNum + "次担保记录");
						$("#numtd_c").html("");
						$("#numguantor").val(result.guantorNum);
					} else {
						$("#num_c").html("");
						$("#numtd_c").html(result.guantorNum + "次担保记录");
					}


				}
			});
			//点击提交后弹窗（显示借款人的借款结清数量）
//						ajaxSubmit(serverName+"/borrowerServiceApp/selectBorrowerAuditListByTel.do","telphone="+$("#customerComsultForm input[name=telphone]").val(),function(result){
//							$("#submitButtonCustomer").linkbutton('enable');
//							if(!(typeof(result)=="undefined")){
//								var sizeb=result.length;
//								$("#num_b").html(sizeb);
//							}
//						});
//
//						//点击提交后弹窗（显示担保人的借款结清数量）
//						ajaxSubmit(serverName+"/borrowerServiceApp/selectBorrowerListByTel.do","telphone="+$("#customerComsultForm input[name=telphone]").val(),function(result){
//							$("#submitButtonCustomer").linkbutton('enable');
//							if(!(typeof(result)=="undefined")){
//								var sizec=result.length;
//								$("#num_c").html(sizec);
//							}
//						});
			//点击提交后弹窗
			$("#submitDialog").dialog("open");
			//$("#subCustomerForm").form("clear");

			$("#customerComsultDialog").dialog("close");
			$("#customerComsultForm").form("clear");
			$("#customerComsult").datagrid({sortName: '', sortOrder: ''});
			$("#customerComsult").datagrid("load", {});
		} else {
			$("#submitButtonCustomer").linkbutton('enable');
			$.messager.show({
				title: "消息",
				msg: result.msg
			});
		}
	});
}



