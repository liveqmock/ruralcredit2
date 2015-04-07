var temp = "0";
var inquire_pool_officeDataGrid;
$(function() {
	
	// 添加搜索框中分公司下拉框
	//departmentComboboxTree("grantTeamDepartmentId", false);
	//添加数据字典值
	var dic=new DataDictionary();
    dic.addDic("consultWay", "inquirePoolConsultWay");
    dic.addDic("acceptConsultState", "acceptConsultState");
    dic.addDic("history", "inquirePoolHistory");
    dic.dicAjax();

	showPCACombo('province', 'city', 'area');
	
	$("#grantProceedName").combobox({
		valueField:'code',   
		textField:'value',
		editable : false
	});
	$("#grantTeamDepartmentId").combotree({
		url:serverName + "/easyUIController/getDepartmentComboboxTree.do",
		editable : false,
		onSelect : function(note) {
			var children = $("#grantTeamDepartmentId").tree("getChildren", note.target);
			if (children == null || children == "") {
				ajaxSubmit(serverName + "/easyUIController/getcustomerManagerBydepartmentId.do",{
					departmentId:note.id
				},function(r){
					$("#grantProceedName").combobox("clear");
					$("#grantProceedName").combobox("loadData",r);
				});
				
			} else {
				$.messager.alert("消息", "请选择营业部。","info");
			}
		}
	});
	$('#inquire_pool_office').datagrid({
	 	url: serverName + '/inquirePoolOfficeController/inquirePoolOfficeList.do'+conditons,
	 	method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : false,
		nowrap : true,
		striped : true,
		singleSelect : false,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
        frozenColumns: [
            [
                {
                    field: 'ANY',
        			checkbox : true
                    
                },
                {
                    title: '操作', field: 'consultPoolId', width: 120, align: 'left', formatter: operatePool
                }
            ]
        ],
        columns: [
            [
                {
                    title: '咨询日期', field: 'registDate', width: 100, align: 'left', sortable: true
                },
                {
                    title: '客户姓名', field: 'customerName', width: 100, align: 'left',sortable: true
                },
                {
                    title: '户籍地址', field: 'addRess', width: 150, align: 'left', sortable: true
                },
                {
                    title: '借款用途', field: 'borrowingShow', width: 100, align: 'left', sortable: true
                },
                {
                    title: '信息来源', field: 'channelShows', width: 100, align: 'left', sortable: true
                },
                {
                    title: '咨询方式', field: 'consultWayShow', width: 100, align: 'left',sortable: true
                },
                {
                    title: '状态', field: 'acceptConsultStateShow', width: 100, align: 'left',sortable: true
                },
                {
                    title: '电话号码', field: 'mobilePhone', width: 100, align: 'left',hidden:true,sortable: true
                },
                {
                    title: '营业部名称', field: 'distributeDepartmentName', width: 150, align: 'left', sortable: true
                },
                {
                    title: '受理客户经理', field: 'loanOfficerName', width: 100, align: 'left',sortable: true
                },
                {
                    title: '分配日期', field: 'distributionDate', width: 100, align: 'left', sortable: true
                },
                {
                    title: '分件日期', field: 'sentDate', width: 100, align: 'left', sortable: true
                },
                {
                    title: '受理日期', field: 'receiveDate', width: 100, align: 'left', sortable: true
                },
                {
                    title: 'distributeOperationFlag', field: 'distributeOperationFlag', width: 100,hidden:true, align: 'left', sortable: true
                },
                {
                    title: 'componentOperationFlag', field: 'componentOperationFlag', width: 100,hidden:true, align: 'left', sortable: true
                },
                {
                    title: 'acceptConsultState', field: 'acceptConsultState', width: 100,hidden:true, align: 'left', sortable: true
                },
                {
                    title: '咨询次数', field: 'consultTimes', width: 100, align: 'left', sortable: true
                }
            ]
        ],
		onLoadSuccess : function(data) {// 加载完毕后获取所有的checkbox遍历
			for ( var i = 0; i < data.rows.length; i++) {
				var oprtSts = data.rows[i].acceptConsultState;
				if (($.trim(oprtSts) != "0" && $.trim(oprtSts) != "1")) {
					$("input[type='checkbox']")[i + 1].disabled = true;
				}
			}
		},
		onClickRow : function(rowIndex, rowData) {// 加载完毕后获取所有的checkbox遍历
			$("input[type='checkbox']").each(function(index, el) {
				if (el.disabled == true) {
					$('#inquire_pool_office').datagrid('unselectRow', index - 1);
				}
			});
			rowNum = rowIndex;
		},
		onSelectAll : function(rowIndex, rowData) {
			$("input[type='checkbox']").each(function(index, el) {
				if (el.disabled == true) {
					$('#inquire_pool_office').datagrid('unselectRow', index - 1);
				}
			});
		}
    });


	
	//加载户籍地址
	function showPCACombo(province, city, area) {
     var province = $("#" + province).combobox({
         //required : true,
         url: serverName + '/NSC/list.do',
         textField: 'cityName',
         valueField: 'cityCode',
         mode: 'remote',
         width: 120,
         delay: 500,
         value: '',
         onSelect: function (value) {
             $("#" + city).combobox({
                 url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                 textField: 'cityName',
                 valueField: 'cityCode',
                 mode: 'remote',
                 width: 120,
                 delay: 500,
                 value: '',
                 onSelect: function (value) {
                     $("#" + area).combobox({
                         url: serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
                         textField: 'cityName',
                         valueField: 'cityCode',
                         mode: 'remote',
                         width: 125,
                         delay: 500,
                         value: ''
                     });
                 }
             });
         }
     });
	}
	

	//分配开始日期
	$("#beginDistributeDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginDistributeDate").datebox("getValue");
			var endSubmitDate = $("#endDistributeDate").datebox("getValue");
			if (endSubmitDate != null && endSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginDistributeDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	//分配结束日期
	$("#endDistributeDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginDistributeDate").datebox("getValue");
			var endSubmitDate = $("#endDistributeDate").datebox("getValue");
			if (beginSubmitDate != null && beginSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endDistributeDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	
	//分件开始日期
	$("#beginSentDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginSentDate").datebox("getValue");
			var endSubmitDate = $("#endSentDate").datebox("getValue");
			if (endSubmitDate != null && endSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#beginSentDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	//分件结束日期
	$("#endSentDate").datebox({
		onSelect : function() {
			var beginSubmitDate = $("#beginSentDate").datebox("getValue");
			var endSubmitDate = $("#endSentDate").datebox("getValue");
			if (beginSubmitDate != null && beginSubmitDate != "") {
				if (beginSubmitDate > endSubmitDate) {
					$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
					$("#endSentDate").datebox("setValue", "");
					return;
				}
			}

		}
	});
	
	$('#rollbackDiv').dialog({
		modal : true,
		title : "退回原因",
       onClose:function(){
             $("#rollbackframe").attr("src","");
        }
	});
	$('#rollbackDiv').dialog('close');
	
	
	$('#acceptAdviceDiv').dialog({
		modal : true,
		title : "受理咨询",
       onClose:function(){
             $("#acceptAdviceframe").attr("src","");
        }
	});
	$('#acceptAdviceDiv').dialog('close');
	
	$('#showAcceptAdviceDiv').dialog({
		modal : true,
		title : "查看客户咨询",
       onClose:function(){
             $("#showAcceptAdviceframe").attr("src","");
        }
	});
	$('#showAcceptAdviceDiv').dialog('close');
	
	$('#addNewClientDiv').dialog({
		modal : true,
		title : "新增申请人",
       onClose:function(){
             $("#addNewClientframe").attr("src","");
        }
	});
	$('#addNewClientDiv').dialog('close');
   
   
});
//搜索按钮
function searchgrid(){
	var data = new Object();
	var customerName = $.trim($("#customerName").val());
	var mobilePhone = $.trim($("#mobilePhone").val());
	var consultWay = $("#consultWay").combobox("getValue");
	var acceptConsultState = $("#acceptConsultState").combobox("getValue");
	/*分配日期*/
    var beginDistributeDate = $('#beginDistributeDate').datebox('getValue');
    var endDistributeDate = $('#endDistributeDate').datebox('getValue');
    /*分件日期*/
    var beginSentDate = $('#beginSentDate').datebox('getValue');
    var endSentDate = $('#endSentDate').datebox('getValue');

    var province=$("#province").combobox("getValue");
    var city=$("#city").combobox("getValue");
    var area=$("#area").combobox("getValue");
    var history=$("#history").combobox("getValue");
	var map = {
			customerName:customerName,
			mobilePhone:mobilePhone,
			consultWay:consultWay,
		acceptConsultState:acceptConsultState,
		//businessStatus:$("#businessStatus").combobox("getValue"),
		beginDistributeDate: beginDistributeDate,
        endDistributeDate: endDistributeDate,
        beginSentDate:beginSentDate,
        endSentDate:endSentDate,
        province:province,
        city:city,
        area:area,
        history:history
	};
	data["paramJsonMap"] = JSON.stringify(map);
	$("#inquire_pool_office").datagrid('load', data);
}
//清空搜索条件
function cleargrid(){
	 $("#customerName").val("");
	 $("#mobilePhone").val("");
	 $("#consultWay").combobox("setValue","");
	$("#acceptConsultState").combobox("setValue","");
	//$("#businessStatus").combobox("setValue","");
	$("#beginDistributeDate").datebox("setValue","");
	$("#endDistributeDate").datebox("setValue","");
	$("#beginSentDate").datebox("setValue","");
	$("#endSentDate").datebox("setValue","");
	$("#province").combobox("setValue","");
	$("#city").combobox("setValue","");
	$("#area").combobox("setValue","");
	$("#history").combobox("setValue","");
}

//查看申请 luohongjie
function showApplication(consultPoolId,acceptConsultState){
	ajaxSubmit(serverName+"/creditgroup/selectCreditValidByconsultPool.do",{consultPoolId:consultPoolId},function(message){
		if(message.success){
			var objTabs = window.parent;
			objTabs.addTabFun({
				src : serverName + "/jsp/rc/business/creditApplicationList.jsp?consultPoolId="+consultPoolId+"&validState=0",
				title : "信贷申请"
			});
		}else{
			$.messager.alert("消息",message.msg);
		}
	});
	
}

//申请借款  hongjieluo
function addNewClient(consultPoolId) {
	ajaxSubmit(serverName+"/inquirePoolOfficeController/selectCustomerConsultPool.do",{consultPoolId:consultPoolId},function(result){
		if(result.acceptConsultState== "6"){
				$.messager.alert("消息","该用户已申请借款");
				$("#inquire_pool_office").datagrid("reload");
		}else{
//			var centerj = window.parent;
//			centerj.addTabFun({
//				src : serverName+"/CustomerConsult/poolToAddApplication.do?consultPoolId="+consultPoolId,
//				title : "新增申请"
//			});
            //新增身份证验证功能end
            $('#validateIndentityNumberFrame').attr('src', serverName + '/customer/validateIndentityNumber.do?consultPoolId='+consultPoolId);
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
            $('#validateIndentityNumberDialog').dialog('open');
            //新增身份证验证功能end
		}
	});
	
}



// 编辑客户咨询
function editInquireDialog(customerConsultId) {
	$("#editframe").attr("src",serverName+ "/CustomerConsultController/editFrame.do?customerConsultId="+ customerConsultId);
}

function showCustomer(customerConsultId) {
  
    $("#showframe").attr("src",serverName+ "/CustomerConsultController/showFrame.do?customerConsultId="+ customerConsultId);

}

function closeAddUserDialog() {
	$('#addConsult').dialog('close');
}

// 查看客户咨询历史

function selHis(telephone,customerConsultId) {
	$("#inquire_history").dialog("open");

	$("#inquire_his").datagrid("load", {
				phone : telephone,
                customerConsultId:customerConsultId,
				tit : "2"
			});
}
function addClient() {
	$('#add_client').dialog({
				modal : true,
				href : "add_client.jsp",
				title : "新增申请人",
				iconCls : "icon-add",
				buttons : [{
							text : '取消',
							iconCls : 'icon-undo',
							handler : function() {
								$('#add_client').dialog('close');
							}
						}]
			});
}



function addApply(customerConsultId) {
	$('#apply').dialog("open");
	temp = "1";
	$("#applyframe").attr("src",
			"../add_client.jsp?customerConsultId=" + customerConsultId);

}
//搜索条件
function selHigh() {
	
//  var phone= $("#mobilePhone").val();
    /*客户姓名*/
    var customerName = $.trim($("#customerName").val());
    /*咨询方式*/
    var channel = $("#channel").combobox("getValue");
    /*分配日期*/
    var beginDistributeDate = $('#beginDistributeDate').datebox('getValue');
    var endDistributeDate = $('#endDistributeDate').datebox('getValue');
   // var _startTime = new Date(startTime);
    //var _endTime = new Date(endTime);
    /*联系方式*/
    var mobilePhone = $.trim($("#mobilePhone").val());
    /*受理状态*/
    var acceptConsultState = $("#acceptConsultState").combobox("getValue");
    //console.info(acceptConsultState);
    /*分件日期*/
    var beginSentDate = $('#beginSentDate').datebox('getValue');
    var endSentDate = $('#endSentDate').datebox('getValue');
    //var distributionstartTime = new Date(distributionstartTime);
    //var distributionendTime = new Date(distributionendTime);
    /*户籍地址*/
    var province = $("#province").combobox("getValue");
    var city = $("#city").combobox("getValue");
    var area = $("#area").combobox("getValue");
    /*历史*/
    var history = $("#history").combobox("getValue");
        inquire_pool_dataGrid.datagrid("load", {
            customerName: customerName,
            channel: channel,
            mobilePhone: mobilePhone,
            acceptConsultState: acceptConsultState,
            province: province,
            city: city,
            area: area,
            beginDistributeDate: beginDistributeDate,
            endDistributeDate: endDistributeDate,
            distributionBeginTime: $.trim($("#distributionBeginTime").datebox('getValue')),
            distributionEndTime: $.trim($("#distributionEndTime").datebox('getValue')),
            history: history,
            viewType: 'buttonSearch'
        });
    };
    
 // 禁用确定按钮
    function buttonBackPooldisDisablRollbacke() {
    	$("#saveRollback").linkbutton("disable");
    	$("#cancelRollback").linkbutton("disable");
    }

    function buttonBackPooldisEnableRollback() {
    	$("#saveRollback").linkbutton("enable");
    	$("#cancelRollback").linkbutton("enable");
    }
    // 禁用退回确定按钮
    function buttonBackPooldisDisableRollbackConfirm() {
    	$("#saveRollbackConfirm").linkbutton("disable");
    	$("#cancelRollbackConfirm").linkbutton("disable");
    }

    function buttonBackPooldisEnableRollbackConfirm() {
    	$("#saveRollbackConfirm").linkbutton("enable");
    	$("#cancelRollbackConfirm").linkbutton("enable");
    }
    

function clearAll() {
	$("#teamDepartmentName").combotree("setValue", "");
	$("#consultType").combobox("select", "");
	$("#customerName").val("");
	$("#conAddress").val("");
	$("#conTelephone").val("");
	$("#customerIndustry1").combobox("select", "");
	$("#customerIndustry2").combobox("select", "");
	$("#firstNextDate").datebox("setValue", "");
	$("#secondNextDate").datebox("setValue", "");
	$("#receptionistName").combobox("setValue","");
	$("#consultWayForm").combobox("setValue","");

}
function selCustomer() {
	$("#consultList").datagrid("load", {
				fuzzy : $.trim($("#fuzzy").val()),
				tit : "0"
			});
}

function apps(a, b, c) {
	var str1 = "";
	var str2 = "";
	var str3 = "";
if(null != b.customerIndustry1 && b.customerIndustry1 != "")
{
	if (null != b.customerIndustry2 && b.customerIndustry2 != "")
	{	str1 = b.customerIndustry1 + ",";
	    if (null != b.customerIndustry3 && b.customerIndustry3 != "") {
	        str2 = b.customerIndustry2 + ",";
            if(b.customerIndustryDetail!=""&&null!=b.customerIndustryDetail)
            
	        str3 = b.customerIndustry3+","+b.customerIndustryDetail;
            else
            str3 = b.customerIndustry3
            
            
            
	    }
	    else 
	    {
	        str2 = b.customerIndustry2; 
	    }
    }
     else
       str1 = b.customerIndustry1;
}
       
        
	
if(str1 + str2 + str3==0)
	return "";
    else
    {
            return str1 + str2 + str3;
    }

}

function showMessage(a) {
	$.messager.show({
				title : '消息',
				msg : a,
				timeout : 5000,
				showType : 'slide'
			});
}
function adds(a, b, c) {
	var str1 = "";
	var str2 = "";


    
      
if(null != b.borrowUse1 && b.borrowUse1 != "")
{
    if (null != b.borrowUse2 && b.borrowUse2 != "")
    {   str1 =b.borrowUse1 + ",";
        str2 =b.borrowUse2;
       
    }
     else
       str1 = b.borrowUse1;
}

	if(str1 + str2==0)
    return "";
    else
    {
            return str1 + str2 ;
    }

}