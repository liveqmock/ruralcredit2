// 加载产品类型
 var creditIdt=$("#creditapplicationId1").val();
 var familyIdFlag = true;
//验证申请单信息是否添加  add y ygx2014-09-22
//如果未添加 先添加申请单 然后才能看组成员tab
function validateUse(){
   var tab = $('#tt').tabs('getSelected');
   var idx = $('#tt').tabs('getTabIndex',tab);
   if($("#groupList").attr("style") == null || ""== $("#groupList").attr("style")){
    $.messager.alert("消息","请先保存申请单信息！");
    $('#tt').tabs('select',0);
  }
}

$(function(){
	$("#accountInfo").form("validate");
	$("#returnAccountInfo").form("validate");
   var creditapplicationId = $.trim($("#creditapplicationId").val());
    if(creditapplicationId != null && !"" == creditapplicationId) {
        console.info("-f----creditapplicationId--"+creditapplicationId);
	ajaxSubmit(serverName+"/creditgroup/select.do?creditapplicationId="+$("#creditapplicationId").val(),{},function(result){
		$("#group").form("load",result);
		creditIdt=$("#creditapplicationId").val();
		// 验证 身份证
	   //validateIndentityNumber();
	});
    }
	
	// 客户咨询按钮
	if($("#customerConsultId").val() != "" && $("#customerConsultId").val() != null &&($("#creditapplicationId1").val() == "" || 
			$("#creditapplicationId1").val() == null ||
			$("#creditapplicationId1").val() ==  undefined)) {
// $("#buttonZixun").show();
	}else{
// $("#buttonZixun").hide();
	}
	
	if($("#creditapplicationId1").val() == "" || 
			$("#creditapplicationId1").val() == null ||
			$("#creditapplicationId1").val() ==  undefined){
// $("#buttonBackList").hide();
			$("#editableFalse").attr("style", "display: none;");
			$("#groupList").attr("style", "");
		
	}else{
		
		
		$("#editableFalse").attr("style", "");
		$("#groupList").attr("style", "display: none;");
		
		
		
	}
	// 还款计划
    /**
     * 产品类型上有点特殊
     Server端返回的都是产品大类
     但是需求让选择完产品大类后选择产品小类 小类用分期来区分
     所以在server端会把所以产品小类的分期和还款方式都放到产品大类上
     到客户端再拆分存储显示
     真正保存到数据库里面的是产品大类及分期数 大类和小类一块保存了
     *
     * @type {*|jQuery}
     */
    initProductType();
	// 显示借款人列表
    showBorrowerServiceApp();
});
function initProductType(productTypeValue,subProductTypeValue){
    var repaymentPlanId = $("#productType").combobox("getValue");
    $("#productType").combobox({
        url:serverName+"/creditgroup/selectProInfo.do",
        textField : 'productName',
        valueField : 'productInfoId',
        mode : 'remote',
        width:"122",
        value: repaymentPlanId,
        onSelect:function(data){
            //分期数
            var instalments = $.trim(data.instalments);
            //eg: 3,6,12 or 3
            if( instalments && instalments.length > 1 && instalments.lastIndexOf(",")>0){
                var    instalmentList = instalments.split(',');
                var    repaymentTypeList =$.trim(data.repaymentType).split(',');
                var arrs = new Array();
                for(i=0;i<instalmentList.length;i++){
                    var obj = new Object();
                    obj.instalments =  data.productName+"["+instalmentList[i]+"]期";
                    obj.repaymentType =  repaymentTypeList[i];
                    obj.idx = i;
                    arrs.push(obj);
                }

                //把小类放入到二级combox上
                $("#subProductType").combobox({
                    width:"122",
                    valueField: 'idx',
                    textField: 'instalments',
                    disabled:false,
                    data: arrs,
                     //data:[{"instalments1":1, "instalments":"text1"  },{"instalments1":2 ,"instalments":"text2"  }],
                    multiple:false,
                    hasDownArrow:true,
                  formatter:function(row){
                        var s = '<span  style="height: 50px;margin-bottom: 15px;">' + row.instalments + '</span>';
                        return s;
                    },
                    onSelect:function(data){
                        var instalments = data.instalments;
                        $("#repaymentPlanName").val(instalments);
                        instalments = instalments.slice(instalments.indexOf('[')+1,instalments.indexOf(']'));
                        instalments = $.trim(instalments);
                        // console.info("-------- 'instalments-----"+ instalments);
                        //保存分期和还款方式
                        $("#instalments").val(instalments);
                        $("#repaymentType").val(data.repaymentType);
                        //  console.info("-------- 'instalments-----"+ $("#instalments").val()+"----------repaymentType---------"+$("#repaymentType").val());
                    }
                });
               // console.info("-------------subValue---------"+$("#subProductType").combobox("getValue"));
                $("#subProductType").combobox("clear");
              //  $("#subProductType").combobox("panel").panel("options").style = "";
               // console.info("-------------subValue---------"+$("#subProductType").combobox("getValue"));
            }else{
                //   只有一个分期
                // 保存分期和还款方式
                $("#instalments").val(data.instalments);
                $("#repaymentType").val(data.repaymentType);
                //  console.info("-------- 'instalments-----"+  data.productName+"----------repaymentType---------"+data.productInfoId);
                var arrs = new Array();
                var obj = new Object();
                obj.productName =  data.productName;
                obj.productInfoId = data.productInfoId;
                arrs.push(obj);
                $("#subProductType").combobox({
                    textField : 'productName',
                    valueField : 'productInfoId',
                    width:"122",
                    disabled:true,
                    data:  arrs,
                    hasDownArrow:false
                });
                $("#subProductType").combobox("setValue",data.productInfoId) ;
                $("#repaymentPlanName").val(data.productName);
                //console.info("--------------repaymentPlanName-----------------"+$("#repaymentPlanName").val());
                //  console.info("-------- 'instalments-----"+ $("#instalments").val()+"----------repaymentType---------"+$("#repaymentType").val());
            }
            $("#capitalUpperLimit").val(data.capitalUpperLimit);
            $("#capitalLowerLimit").val(data.capitalLowerLimit);
            $("#producttypeid").val(data.producttypeid);
            $("#subProductTypeDiv").show();
        },
        onLoadSuccess:function(data){
            console.info("------------repaymentPlanId-------"+repaymentPlanId);
            if(""==repaymentPlanId || repaymentPlanId ==null || $.trim(repaymentPlanId) ==null || "" ==  $.trim(repaymentPlanId)){
                //console.info("------------repaymentPlanId----333---"+repaymentPlanId);
                $("#subProductTypeDiv").hide();
            }
        }
    });
}
function addNewCustomer(){
	var datagrids = $("#borrowSerivceList").datagrid("getRows");
	if(datagrids.length > 0){
		$.messager.alert("消息","借款人已经存在！");
		$("#identity").combogrid("clear");
		$("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
		return;
	}
	var credentialsNumber = $.trim($("#identity").combogrid("getValue"));
	var url= serverName+"/customer/selectByNumberNew.do";
	var creditapplicationId = $("#creditapplicationId1").val();
	var customerConsultId = $("#customerConsultId").val();
	var	consultPoolId=$("#consultPoolId").val();
	ajaxSubmit(url,{creditapplicationId:creditapplicationId,consultPoolId:consultPoolId,credentialsNumber:credentialsNumber,customerConsultId:customerConsultId},function(result){
			if(result.success){
				showBorrowerServiceApp();
				
			}else{
				$.messager.alert("消息",result.msg);
				showBorrowerServiceApp();
				return;
			}
	});
	$("#identity").combogrid("setValue","0");
	$("#identity").combogrid("clear");
	$("#identityGuaranor").combogrid("setValue","0");
	$("#identityGuaranor").combogrid("clear");
	$("#buttonAdd").html("");
	$("#buttonAddGuaranor").html("");
	$("#identityGuaranor").combogrid("hidePanel");
	$("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
}
function validateIndentityNumber(){
	if(creditIdt==undefined){creditIdt="";}
	 $("#identity").combogrid({
		 	panelWidth : 645,
		 	panelHeight:52,
	    	mode: "remote",
	    	url :serverName+"/customer/listForCombo.do?creditapplicationId="+creditIdt,
	    	idField: "credentialsNumber",
	    	textField: "credentialsNumber",
	    	columns : [[{field :"name" ,title: "姓名" , width:100},
	    	            {field :"borrowerServiceAppId" ,title: "borrowerServiceAppId" , width:100,hidden:true},
	    	            {field :"credentialsNumber" ,title: "身份证号" , width:150},
	    	            {field :"creditApplicationId" ,title: "身份证号" , width:90,hidden:true},
	    	            {field :"auditStatus" ,title: "状态" , width:100,hidden:true,
	    	            	formatter: function(value){
								if($.trim(value) == "00"){
									return "已经申请借款";
								}else if($.trim(value)== "01"){
									return "<font>待审批</font>";
								}else if($.trim(value) == "02"){
									return "审查通过";
								}else if($.trim(value)== "03"){
									return "<font>审查拒绝</font>";
								}else if($.trim(value) == "04"){
									return "审批通过";
								}else if($.trim(value)== "05"){
									return "<font>审批补充资料</font>";
								}else if($.trim(value) == "06"){
									return "放款失败登记";
								}else if($.trim(value)== "07"){
									return "<font>撤回</font>";
								}else if($.trim(value) == "08"){
									return "推迟放款";
								}else if($.trim(value)== "09"){
									return "<font>已付款</font>";
								}else if($.trim(value) == "10"){
									return "款项到位";
								}else if($.trim(value)== "11"){
									return "<font>已放款登记</font>";
								}else if($.trim(value) == "12"){
									return "放款失败-推迟";
								}else if($.trim(value)== "13"){
									return "<font>放款确认</font>";
								}else if($.trim(value) == "14"){
									return "放款确认退回";
								}else if($.trim(value)== "15"){
									return "<font>还款中</font>";
								}else if($.trim(value) == "16"){
									return "还款完成";
								}else if($.trim(value)== "17"){
									return "<font>审批变更额度</font>";
								}else if($.trim(value)== "18"){
									return "<font>审批拒绝</font>";
								}else if($.trim(value) == "19"){
									return "提前还款登记";
								}else if($.trim(value)== "20"){
									return "<font>提前还款完成</font>";
								}else if($.trim(value)== "24" ||$.trim(value)== "25"  ){
									return "<font>审批中</font>";
								}else if($.trim(value)== "21"){
									return "<font>额度确认</font>";
								}else if($.trim(value)== "26"){
									return "<font>注销</font>";
								}else{
									return "<font>未申请</font>";
								}
							}
	    	            }, {field :"auditStatusShow" ,title: "状态" , width:100,formatter:function(value){
	    	            	if(value == null ||value == ""){
	    	            		return "未申请";
	    	            	}else{
	    	            		return value;
	    	            	}
	    	            }
	    	            },{field:"guaranor",title:"担保人类型", width:80,hidden:true },
	    	            {field:"customerType",title:"客户类型", width:80,formatter:function(value,param){
	    	            	if(value == "5"){
	    	            		return "一期未完成";
	    	            	}
	    	            	if(value == "4"){
	    	            		return "本组担保人";
	    	            	}
	    	            	if(value == "3"){
	    	            		return "借款人配偶";
	    	            	}
	    	            	if(value == "1" || param.leader == "0"){
	    	            		return "其他担保人";
	    	            	}
	    	            	if(value == "0"){
	    	            		return "借款人";
	    	            	}
	    	            	if((value == "" || value == null)&& param.guaranor == "0"){
	    	            		return "其他担保人";
	    	            	}else{
	    	            		return "没有角色";
	    	            	}
	    	            	
	    	            }},{field:"blackFlag",title:"是否加入黑名单", width:90,hidden:true,formatter:function(value){
	    	            	if(value == null || value == ""){
	    	            		return "否";
	    	            	}else{
	    	            		return "是";
	    	            	}
	    	            }
	    	            },
	    	            {field:"blackFlagShow",title:"是否加入黑名单", width:90},
	    	            {field :"flag" ,title: "操作" , width:100,formatter:function(value,data){
	    	            	//如果是加入黑名单 ，就直接不可用
	    	            	if(data.blackFlag != null && data.blackFlag != ""){
	    	            		return "不可用";
	    	            	}
	    	            	if(data.customerType == "5"){
	    	            		return "不可用";
	    	            	}
	    	            	if(data.customerType == "4"){
	    	            		return "不可用";
	    	            	}
	    	            	if(data.leader == "0" ||data.customerType == "1" || (data.customerType == "" || data.customerType == null)&& data.guaranor == "0"){
	    	            		return "<a href='javascript:newBorrower();'><font>选中添加</font></a>";
	    	            	}
	    	            	if(value == ""
	    	            		|| value == null
	    	            		||$.trim(value) == "16"
	    	            		||$.trim(value) == "20"
	    	            		||$.trim(value) == "06"
	    	            		||$.trim(value) == "26"
	    	            		||($.trim(value) == "27" && data && data.specialRefuseFlag != "sayYes")
	    	    	            ||$.trim(value) == "28"){
	    	            		return "<a href='javascript:newBorrower();'><font>选中添加</font></a>";
	    	            	}
	    	            	if(data.customerType == "3" 
	    	            		|| data.customerType == "0" 
	    	            		||data.customerType == ""
	    	            		||data.customerType == null){
	    	            		return "不可用";
	    	            	}
	    	            	if(value == ""
	    	            		|| value == null
	    	            		||$.trim(value) == "16"
	    	            		||$.trim(value) == "20"
	    	            		||$.trim(value) == "06"
	    	            		||$.trim(value) == "26"
	    	            		||($.trim(value) == "27" && data && data.specialRefuseFlag != "sayYes")
	    	            		||$.trim(value) == "28"){
	    	            		return "<a href='javascript:newBorrower();'><font>选中添加</font></a>";
	    	            	}else {
	    	            		return "不可用";
	    	            	}
	    	            }}]],
	    	            onShowPanel:function(){
	    	            	if($("#groupList").attr("style") == null || ""== $("#groupList").attr("style")){
	    	            		$("#identity").combogrid("clear");
	       	            	 	$("#identity").combogrid("hidePanel");
	       	            	 	$.messager.alert("消息","请先保存申请单信息！");
	    	            	}else{
//	    	            		if(!validNumberAll($("#identity").combogrid("getValue"))){
//	    	            			$("#identity").combogrid("hidePanel");
//					    			 $("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
//					    			 return;
//					    		}
//									 if($("#identity").combogrid("grid").datagrid("getRows").length == 0 && $.trim($("#identity").combogrid("getValue")).length == 18){
//									 $("#identity").combogrid("hidePanel");
//									 $("#buttonAdd").html(" <a href='javascript:addNewCustomer();' id='addbutton'>该客户可用，可以点此添加！</a>");
//									 $("#addbutton").linkbutton({});
//									 }else if($("#identity").combogrid("grid").datagrid("getRows").length == 0 && $.trim($("#identity").combogrid("getValue")).length < 18){
//									 $("#identity").combogrid("hidePanel");
//									 $("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
//									 }else{
//									 $("#buttonAdd").html("");
//									 }
	    	            	}	
				    	},
				    	onLoadSuccess:function(data){
				    		$("#credentialsNumberAdd").val($("#identity").combogrid("getValue"));
				    		if(!validNumberAll($("#credentialsNumberAdd").val())){
				    			 $("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
				    			 return;
				    		}
		    				if(data.total == 0 && $.trim($("#identity").combogrid("getValue")).length == 18){
				    			$("#identity").combogrid("hidePanel");
    	            			$("#buttonAdd").html(" <a href='javascript:addNewCustomer();' id='addbutton'>该客户可用，可以点此添加！</a>");
    	            			$("#addbutton").linkbutton({});
    	            		}else if(data.total== 0 && $.trim($("#identity").combogrid("getValue")).length < 18){
    	            			$("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
    	            		}else{
    	            			$("#buttonAdd").html("");
    	            		}
				    		
				    	}
	 
	    });
	 $("#identity").combogrid('grid').datagrid('doCellTip',{cls:{'background-color':'#fafafa'},delay:100,"notShowfield":"flag"});  

}

function witeFun(){
	if($("#identity").combogrid("grid").datagrid("getRows").length  == 0 && $.trim($("#identity").combogrid("getValue")).length == 18){
		$("#identity").combogrid("hidePanel");
		$("#buttonAdd").html(" <a href='javascript:addNewCustomer();' id='addbutton'>该客户可用，可以点此添加！</a>");
		$("#addbutton").linkbutton({});
	}else if($("#identity").combogrid("grid").datagrid("getRows").length  == 0 && $.trim($("#identity").combogrid("getValue")).length < 18){
		$("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
	}else{
		$("#buttonAdd").html("");
	}
}

// 验证身份证号 与 姓名的 方法回调
function retFun(data){
	if(data.success){
		return;
	}else{
		$("#name").val("");
		$.messager.alert("消息","省份证号与姓名不匹配！请填写正确姓名，或修改客户基本信息！");
	}
}


// 显示可编辑的小组信息
function showUpdateGroup(){
	// liuli 2013-05-02 修改在编辑时不显示信贷列表按钮，保存成功后显示
// $("#buttonBackList").hide();
	$("#submitGroupButton").removeAttr("disabled");
	$("#editGroupNum").val($("#groupNumber1").val());
	$("#groupList").attr("style", "");
  //  $("#subProductType").combobox("setValue","") ;
    var productType  = $("#productType").combobox("getValue");
    var productTypeText  = $("#productType").combobox("getText");
    var subProductTypeValue  = $("#subProductType").combobox("getValue");
    var instalments = $("#instalments").val();
    //console.info("---------------productType-------------------"+productType+"    -----------------------subProductTypeValue-----------"+subProductTypeValue);
    initProductType(productType,subProductTypeValue);
    $("#productType").combobox("select",productType);
    var    hasDownArrow = $.trim($("#subProductType").combobox("options").hasDownArrow);
    //console.info("---------hasDownArrow-----"+hasDownArrow);
    //console.info("---------subProductTypeValue-----"+subProductTypeValue);
    if(hasDownArrow && hasDownArrow =='true'){
        var subValue =   productTypeText + "[" +  instalments + "]期" ;
        $("#subProductType").combobox("setText",subValue);
    }
	$("#editableFalse").attr("style", "display: none;");
}

// 提交小组信息
function submitGroup() {
	var repaymentPlanName = $("#productType").combobox("getText");
    if (parseInt(repaymentPlanName) > 0) {
        $.messager.alert("消息", "产品类型获取有误，请重新编辑此业务申请！");
        return;
    }
	//$("#repaymentPlanName").val(repaymentPlanName);
	if ($("#group").form('validate'))
		{
          var  credentialsNumber  = $.trim($("#credentialsNumber").val());
          var  mateIdNumber       = $.trim($("#mateIdNumber").val());
          var  customerConsultId  = $.trim($("#customerConsultId").val());
          var  consultPoolId      = $.trim($("#consultPoolId").val());
            /*consultPoolId =  null;
            console.info("-------------credentialsNumber--------------"+credentialsNumber);
            console.info("-------------mateIdNumber--------------"+mateIdNumber);
            console.info("-------------customerConsultId--------------"+customerConsultId);
            console.info("-------------consultPoolId--------------"+consultPoolId);*/
          if(credentialsNumber==null || credentialsNumber =="" || mateIdNumber==null || mateIdNumber =="" || ((customerConsultId =="" || customerConsultId ==null) && (consultPoolId =="" || consultPoolId ==null))){
              $.messager.alert("消息", "您在页面停留的时间过长，请重新发起申请...");
              return ;
          }

				// 数据填写正确。。开始调用方法前。。按钮变灰
				$("#submitGroupButton").attr("disabled","disabled");
				var url = serverName + '/creditgroup/addOrUpdate.do?';
				var  obj = new Object();
				obj["returnAccountInfo"] =  JSON.stringify($("#returnAccountInfo").serializeObject());
				obj["accountInfo"] =  JSON.stringify($("#accountInfo").serializeObject());
				obj["group"] =  JSON.stringify($("#group").serializeObject());
				// var dateObject =$("#returnAccountInfo").serialize()+"&"+
				// $("#accountInfo").serialize() +"&"+$("#group").serialize();
				ajaxSubmit(url,"post",obj, resFunc,false);
				$("#submitGroupButton").removeAttr("disabled");
				// liuli 2013-05-02 修改在编辑时不显示信贷列表按钮，保存成功后显示
// $("#buttonBackList").show();
// }
			return true;
	} else {
		$.messager.alert("消息","请填写完整数据！");
		return false;
	}
}

// 提交小组信息后回调 页面回显
function resFunc(result){
	 if(result != null && result != undefined){
		 var exp = result.groupNumber;
		 var companyId = result.companyId;
		 if (!companyId && typeof(companyId)!="undefined"){
			 $.messager.alert("提示信息","分公司获取异常,请联系管理员");
			 return;
		 }
		 if (!exp && typeof(exp)!="undefined"){
			 $.messager.alert("提示信息","业务编码生成错误，请稍后重试！");
			 return;
		 }else if(exp=="departmentsn_no"){
			 $.messager.alert("提示信息","业务编码生成错误，请先设置营业部编码！");
			 return;
		 }else if(exp=="productsn_no"){
			 $.messager.alert("提示信息","业务编码生成错误，请先设置产品编码！");
			 return;
		 }
		 if(result.success == false){
			 	$.messager.alert("错误",result.msg);
			 	$("#submitGroupButton").removeAttr("disabled");
			 	return;
		 }
		$.messager.show({
			msg:"数据保存成功！",
			title:"消息"
		});
		// 隐藏 能编辑状态的 账户表单
		$("#showForm").hide();
		// 隐藏 能编辑状态的 账户表单
		$("#showReturnAccount").hide();
		$("#editableFalse").attr("style", "");
		$("#groupList").attr("style", "display: none;");
		// $("#defaultReturnmentWayLook").val(result.defaultReturnmentWay);
		$("#companyName1").val(result.companyName);
		$("#groupNumber1").val(result.groupNumber);
		$("#groupNumber").val(result.groupNumber);
		$("#creditapplicationId1").val(result.creditapplicationId);
		creditIdt = result.creditapplicationId;
		//validateIndentityNumber();
		$("#caDESId").val(result.creditapplicationDESId);
		$("#creditapplicationId").val(result.creditapplicationId);
		$("#loanOfficerName1").val(result.loanOfficerName);
		$("#repaymentPlanNameLook").val(result.repaymentPlanName);
		$("#accountInfoId").val(result.accountInfoId);
		$("#accountInfoIdRETURN").val(result.returnAccountInfoId);
         showBorrowerServiceApp();
	 }else{
		 $.messager.alert("错误","保存失败！系统错误，请与管理员联系！");
	 }
}
		
	 
		// 动态加载产品类型
		function showProductCombo(){
			$("#productType").combobox({
			});
		}
		// 删除回调
		function deleteRetFunc(result){
			if(result.success){
				$.messager.show({
					msg:'删除成功！',
					title:'消息'
				});
				$("#borrowSerivceList").datagrid('reload');
				$("#identity").combogrid("setValue","0");
				$("#identity").combogrid("clear");
			}else{
				$.messager.show({
					msg:result.msg,
					title:'消息'
				});
			}
		}

		// 页面加载时 显示借款人信息列表
		function showBorrowerServiceApp(){
			var creditapplicationId = $("#creditapplicationId1").val();
			$("#borrowSerivceList").datagrid({
				url:serverName+'/borrowerServiceApp/borrowServiceAppList.do?creditapplicationId='+creditapplicationId,
				nowrap:false, 
				idfield:"borrowerServiceAppId",
				singleSelect:true,
				sortOrder: "desc",
				sortName:"",
				columns:[[ 
							 {
								title:"借款人姓名",
								field:"name",
								width:"150"
							},{
								title:"身份证号码",
								field:"credentialsNumber",
								width:"150"
							},{
								title:"联系电话",
								field:"mobilephone",
								width:"150",
								hidden:true
							},{
								title : "业务申请单",
								field : "firstFlag",
								width : "150",
								formatter : function(value) {
									if (value == "1") {
										return '<a href="javaScript:ruhu1();"><font color="green">点击修改</font></a>';
									} else {
										return '<a href="javaScript:ruhu1();"><font color="red">点击添加</font></a>';
									}
								}
							}, {
								title : "现金流入和流出表",
								field:"borrowerServiceAppId1",
								width : "150",
								formatter : function(value) {
									return '<a id="aCashOutAndIn" href="javaScript:showCashStreamInput();"><font color="red">现金流入和流出表</font></a>';
								}
							}, {
								title : "现金流工作表",
								field:"borrowerServiceAppId2",
								width : "150",
								formatter : function(value) {
									return '<a href="javaScript:showCashStreamWorkTableInput();"><font color="red">现金流工作表</font></a>';
								}
							},
//                            {
//								title:"操作",
//								field:"borrowerServiceAppId",
//								width:"150",
//								formatter:function(value){
//										return '<a href="javaScript:deleteBorrowerInfo('+value+');"><font color="green">删除</font></a>';
//								 }
//							}
				]]
			});
		}
		
		// 显示借款服务申请地址
		function showBorrowerServiceAddress(){
					addressString = $("#prefecture1").combobox("getText")
							+ "-"+ $("#town1").combobox("getText")+ "-"
							+ $("#village1").combobox("getText")+ "-"
							+ $("#groupNum").val();
					// 设置所在村镇的值
					$("#residenceAddress").val(addressString);
		}
		
	
		// 新曾借款人
		function newBorrower(){
			var datagrids = $("#borrowSerivceList").datagrid("getRows");
			var rowdata =  $("#identity").combogrid("grid").datagrid("getSelected");
			if(datagrids.length > 0){
				$.messager.alert("消息","借款人已经存在！");
				return;
			}
			var credentialsNumber =  rowdata.credentialsNumber;
			var urlAdd = "";
			if(rowdata.borrowerServiceAppId =="" || rowdata.borrowerServiceAppId == null ||rowdata.borrowerServiceAppId== undefined){
				urlAdd = serverName+"/customer/selectByNumberNew.do";
			}else{
				urlAdd = serverName+"/customer/selectByNumberNew.do?borrowerServiceAppId="+rowdata.borrowerServiceAppId;
			}
			
			var creditapplicationId = $("#creditapplicationId1").val();
			var customerConsultId = $("#customerConsultId").val();
			
			ajaxSubmit(urlAdd,{creditapplicationId:creditapplicationId,credentialsNumber:credentialsNumber,customerConsultId:customerConsultId},function(result){
				if(result.success){
					showBorrowerServiceApp();
					
				}else{
					$.messager.alert("消息",result.msg);
					showBorrowerServiceApp();
					return;
				}
			});
			$("#identity").combogrid("grid").datagrid("deleteRow",0);
			$("#identity").combogrid("setValue","0");
			$("#identity").combogrid("clear");
			$("#buttonAdd").html("");
			$("#buttonAdd").html("<font color='red'>请输入正确的身份证号码，方可添加！</font>");
			$("#buttonAddGuaranor").html("");
			$("#identityGuaranor").combogrid("hidePanel");
			$("#identityGuaranor").combogrid("setValue","0");
			$("#identityGuaranor").combogrid("clear");
			if($("#identityGuaranor").combogrid("grid").datagrid("getRows").length != 0){
				$("#identityGuaranor").combogrid("grid").datagrid("deleteRow",0);
			}
		}
		// 删除借款人
		function deleteBorrowerInfo(borrowerServiceAppId){
				$.messager.confirm('消息','确定要删除吗？',function(b){
		  			if(b){
						var url=serverName+'/borrowerServiceApp/deleteById.do';
						ajaxSubmit(url,{borrowerServiceAppId:borrowerServiceAppId},deleteRetFunc);
		  			}
		  		});
				$("#borrowSerivceList").datagrid('unselectAll');
		}
		

/** wyf内容管理平台页面 * */
function showUploadDig(){
	var clientid = $("#caDESId").val();
	// var href =
	// cmUrl+"/jsp/creditease/operation/operationControl.jsp?clientId="+clientid+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=yinongdai&type=3&signTime="+DESNow+"&signIp="+DESIp+"";
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1,function(result) {
		if(result){
			var signTime = result[0];	
			var signIp = result[1];
			var href = cmUrl+"/jsp/creditease/operation/operationControl.jsp?clientId="+clientid+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID="+userId+"&type=3&signTime="+signTime+"&signIp="+signIp+"";
			var content = '<iframe scrolling="auto" id="openCM" frameborder="0"  src="'+href+'" style="width:100%;height:100%;">';
			$("#cm").dialog({
				content:content,
				title:"业务资料",
				modal:false,
				maximizable:true,
				closed:true, 
				width:1000,
				height:350
			});
			// $('#openCM')[0].src = href;
			$('#cm').dialog('open');
		}else{
			$.messager.alert("提示信息","服务异常，请稍后重试");
		}
	});
}
function submitAuditing(){
	$("#borrowSerivceList").datagrid("load");
	if(($("#creditapplicationId1").val() == null || ""== $("#creditapplicationId1").val())||
			($("#groupList").attr("style") == null || ""== $("#groupList").attr("style"))){
		 //$("#identity").combogrid("clear");
		 //$("#identity").combogrid("hidePanel");
		 $.messager.alert("消息","请先保存申请单信息！");
		 return;
	}
	var datagrid = $("#borrowSerivceList").datagrid("getRows");
	if(datagrid == undefined || datagrid  == "" ||datagrid == null){
		$.messager.alert("消息","请添加借款人");
		return;
	}
	if(datagrid[0].name == null){
		$.messager.alert("消息","请填写借款人姓名");
		return;
	}
	for(i = 0 ; i < datagrid.length;i++){
		if(datagrid[i].firstFlag != "1"){
			$.messager.alert("消息","借款人的业务申请单未填写完整，请填写完整后提交！");
			return;
		}
	}
	var guarantee = $("#guaranorList").datagrid("getRows");
	var guaranteeLength =guarantee.length;
	if(guaranteeLength == 0){
		$.messager.alert("消息","请添加担保人");
		return;
	}
	for(var i = 0; i <guaranteeLength;i++ ){
		if(guarantee[i].firstFlag != "1"){
			$.messager.alert("消息","第"+(i+1)+"位担保人信息没有保存，请填写完整后提交！");
			return;
		}
	}
	ajaxSubmit(serverName+"/creditgroup/selectCoBorrower.do",{borrowerServiceAppId:datagrid[0].borrowerServiceAppId,borrowerreRationship:'2'},function(result){
		for(var i = 0; i <guaranteeLength;i++ ){
			if(guarantee[i].credentialsNumber == result.idNumber){
				$.messager.alert("消息","第"+(i+1)+"位担保人与借款人的配偶的身份证号码相同，请修改后提交");
				return;
			}else{
			}
		}
		var url=serverName+"/creditgroup/getImgAmountByGuaranor.do";
		ajaxSubmit(url,"post",{creditAppId:$("#creditapplicationId1").val()},submitAuditingReturn,false);
// $.post(url,{creditAppId:$("#creditapplicationId1").val()},submitAuditingReturn);
	});
}

function submitAuditingReturn(imgamount){
	if(imgamount){
		if(imgamount.creditAppImg<=0){
			$.messager.alert("消息","请先上传业务资料");
			return;
		}
		if(!imgamount.guaranorImg){
			$.messager.alert("消息","请先上传担保人资料");
			return;
		}
	}else{
		$.messager.alert("消息","上传服务异常,请稍后重试");
		return;
	}
	$.messager.confirm("提示","确认提交吗？",function(r){
		if(r){
		var url=serverName+"/creditgroup/changgeAuditing.do";
		var creditapplicationId = $("#creditapplicationId").val();
		ajaxSubmit(url,{creditapplicationId:creditapplicationId,auditStatus:"01"},function(result){
			if(result.success){
				window.location.href = serverName+"/jsp/rc/business/creditApplicationList.jsp";
				parent.$.messager.show({
					msg:"保存成功",
					title: "消息"
				});
			}else{
				$.messager.show({
					msg:result.msg,
					title: "消息"
				});
			}
		});
		}
	});
}
/** 弹出入户一对话框 * */
function ruhu1(){
	var tempAddFlag = "1";
	var caId = $("#creditapplicationId").val();
	var businessNumber = $("#groupNumber1").val();
	var row= $("#borrowSerivceList").datagrid("getSelected");
	var industryCategoryAll = "";
	//$('#borrowSerivceFram')[0].src =  serverName+"/application/selectHouseServeyVo.do?borrowerServiceAppId="+row.borrowerServiceAppId+"&businessNumber="+$("#groupNumber1").val();
    $('#borrowSerivceFram')[0].src =  serverName+"/application/queryHouseServeyVo.do?borrowerServiceAppId="+row.borrowerServiceAppId+"&businessNumber="+$("#groupNumber1").val();

    $("#borrowSerivce").dialog({
		buttons : [
		           /*{id:"judaiButton",text : "拒贷",handler:function(){
			$.messager.confirm('提示信息','确认要拒贷吗?',function(r){
				if (r){
					var borrowSerivceFram = window.frames["borrowSerivceFram"];
					if (borrowSerivceFram.window) {
						borrowSerivceFram.window.judaiFun(caId);
					} else {
						borrowSerivceFram.contentWindow.judaiFun(caId);
					}
				}
			});
		}},
		            {id:"fangqiButton",text : "客户放弃",handler:function(){
		            	$.messager.confirm('提示信息','确认要放弃吗?',function(r){
		            		if (r){
		            			ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:caId,flag:"28"},function(result){
		    						if(result){
		    							$.messager.alert("消息",result.msg,"info",function(){
		    								if(result.success){
		    									document.location='jsp/rc/business/creditApplicationList.jsp';
		    								}
		    							});
		    						}else{
		    							$.messager.alert("消息","系统错误,请重试或联系管理员！");
		    						}
		    					});
		            		}
		            	});
		            }},*/
		            {
		    			id: "tempAddSave",
		    			text : "暂存",
		    			iconCls : "icon-save",
		    			handler : function() {
		    				var paramSerialize;
		    				var borrowSerivceFram = window.frames["borrowSerivceFram"];
		    				if (borrowSerivceFram.window) {
		    					paramSerialize =borrowSerivceFram.window.$("#formOther").serialize()+"&"+borrowSerivceFram.window.$("#formBorrrower").serialize()
		    				} else {
		    					paramSerialize =borrowSerivceFram.contentWindow.$("#formOther").serialize()+"&"+borrowSerivceFram.contentWindow.$("#formBorrrower").serialize()
		    				}
		    				ajaxSubmit(serverName+"/application/tempAdd.do",paramSerialize,function(result){
		    					if(result){
		    						$.messager.show({
		    							showType : "show",
		    							timeout : 5000,
		    							title : "消息",
		    							msg : "操作成功,填完表单后请全部保存！"
		    						});
		    						$("#borrowSerivce").dialog("close");
		    						showBorrowerServiceApp();
		    					}else{
		    						$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
		    					}
		    				});
		    			}
		    		}, {
			id: "addSave",
			text : "全部保存",
			iconCls : "icon-save",
			handler : function() {
				var borrowSerivceFram = window.frames["borrowSerivceFram"];
				if (borrowSerivceFram.window) {
					borrowSerivceFram.window.saveBorrowerService();
				} else {
					borrowSerivceFram.contentWindow.saveBorrowerService();
				}
			}
		} , {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#borrowSerivce").dialog('close');
			}
		} ],
		onLoad : function() {
		},
		onClose : function() {
			$("#borrowSerivceList").datagrid('reload', {
				creditapplicationId : $("#creditapplicationId1").val()
			});
			$('#borrowSerivceFram')[0].src = "";
		}

	});
	$("#borrowSerivce").dialog('open');
	
	
//	var dd1 = $("#borrowSerivce").dialog({
////		 href :serverName+"/application/selectHouseServeyVo.do?borrowerServiceAppId="+row.borrowerServiceAppId+"&businessNumber="+$("#groupNumber1").val(),
//		href : serverName+"/jsp/rc/business/borrowerServiceApp.jsp",
//		title : "业务申请表",
//		modal : true,
//		width: 1000,
//		height: 470,
//		buttons : [ {text : "拒贷",handler:function(){
//			if( $("#jobInfoList0company").val() == "" &&
//					$("#jobInfoList1company").val() == "" &&
//					$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
//					$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
//					$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
//				 {
//					 $.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
//					 return;
//				 }
//				if( $("#livingCommercialForm").attr("checked") == undefined &&
//					$("#livingSelfForm").attr("checked") == undefined &&
//					$("#livingRentForm").attr("checked") == undefined &&
//					$("#livingRelativeForm").attr("checked") == undefined &&
//					$("#livingOtherForm").attr("checked") == undefined)
//				 {
//					 $.messager.alert("消息","请至少选择一项 居住状况！");
//					 return;
//				 }
//			
//				familyIdFlag = false;
//				if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
//					$("#addSave").linkbutton('disable');
//				}else{
//					$.messager.alert("消息","请将数据填写正确完整！");
//					return;
//				}
//				familyIdFlag = true;
//				ajaxSubmit(serverName+"/application/add.do","post",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//					if(result){
//						$("#borrowSerivce").dialog("close");
//						showBorrowerServiceApp();
//						$("#addSave").linkbutton('enable');
//					}else{
//						$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//						$("#addSave").linkbutton('enable');
//					}
//				},false);
//			$.messager.confirm('提示信息','确认要拒贷吗?',function(r){
//				if (r){
//					ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:caId,flag:"27"},function(result){
//						if(result){
//							$.messager.alert("消息",result.msg,"info",function(){
//								if(result.success){
//									document.location='jsp/rc/business/creditApplicationList.jsp';
//								}
//							});
//						}else{
//							$.messager.alert("消息","系统错误,请重试或联系管理员！");
//						}
//					});
//				}
//			});
//		}},
//		            {text : "客户放弃",handler:function(){
//		            	$.messager.confirm('提示信息','确认要放弃吗?',function(r){
//		            		if (r){
//		            			ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:caId,flag:"28"},function(result){
//		    						if(result){
//		    							$.messager.alert("消息",result.msg,"info",function(){
//		    								if(result.success){
//		    									document.location='jsp/rc/business/creditApplicationList.jsp';
//		    								}
//		    							});
//		    						}else{
//		    							$.messager.alert("消息","系统错误,请重试或联系管理员！");
//		    						}
//		    					});
//		            		}
//		            	});
//		            }},
//		            /*{
//		    			id: "tempAddSave",
//		    			text : "暂存",
//		    			iconCls : "icon-save",
//		    			handler : function() {
//		    				ajaxSubmit(serverName+"/application/tempAdd.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//		    					if(result){
//		    						$.messager.show({
//		    							showType : "show",
//		    							timeout : 5000,
//		    							title : "消息",
//		    							msg : "操作成功,填完表单后请全部保存！"
//		    						});
//		    						$("#borrowSerivce").dialog("close");
//		    						showBorrowerServiceApp();
//		    					}else{
//		    						$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//		    					}
//		    				});
//		    			}
//		    		},*/
//		            {
//			id: "addSave",
//			text : "全部保存",
//			iconCls : "icon-save",
//			handler : function() {
//					if( $("#jobInfoList0company").val() == "" &&
//						$("#jobInfoList1company").val() == "" &&
//						$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
//						$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
//						$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
//					 {
//						 $.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
//						 return;
//					 }
//					if( $("#livingCommercialForm").attr("checked") == undefined &&
//						$("#livingSelfForm").attr("checked") == undefined &&
//						$("#livingRentForm").attr("checked") == undefined &&
//						$("#livingRelativeForm").attr("checked") == undefined &&
//						$("#livingOtherForm").attr("checked") == undefined)
//					 {
//						 $.messager.alert("消息","请至少选择一项 居住状况！");
//						 return;
//					 }
//				
//					familyIdFlag = false;
//					if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
//						$("#addSave").linkbutton('disable');
//					}else{
//						$.messager.alert("消息","请将数据填写正确完整！");
//						return;
//					}
//					familyIdFlag = true;
//					ajaxSubmit(serverName+"/application/add.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//						if(result){
//							$("#borrowSerivce").dialog("close");
//							showBorrowerServiceApp();
//							$("#addSave").linkbutton('enable');
//						}else{
//							$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//							$("#addSave").linkbutton('enable');
//						}
//					});
//			}
//		}, {
//			text : "关闭",
//			iconCls : "icon-cancel",
//			handler : function() {
//				$("#borrowSerivce").dialog('close');
//			}
//		} ]
//	});
////	$("#borrowSerivce").dialog("refresh", serverName+"/application/selectHouseServeyVo.do?borrowerServiceAppId="+row.borrowerServiceAppId+"&businessNumber="+$("#groupNumber1").val());	
//	dd1.dialog('open');
//			
//    ajaxSubmit(serverName+"/application/select.do",{borrowerServiceAppId:row.borrowerServiceAppId},function(result){
//		if(result == null){
//			alert("查询失败");
//		}else{
//			$("#businessNumberBorrower").val($("#groupNumber1").val());
//			$("#formBorrrower").form('validate');
//			familyIdFlag = false;
//			$("#formOther").form('validate');
//			familyIdFlag = true;
//			$("#formBorrrower").form("load",result);
//			$("#formOther").form("load",result);
//			// 地址
//			provinceShow("provinceHome", "cityHome", "countyHome","townHome","villageHome","homeAddress","detailHome","hourseholdAddress");
//			provinceShow("provinceBusiness", "cityBusiness", "countyBusiness","townBusiness","villageBusiness","businessAddress","detailBusiness","");
//			var sex = maleOrFemalByIdCard(result.borrowerService.credentialsNumber);
//			$("#genderBorrower").val(sex);
//			$("#showGender").val((sex == 0)?"男":"女");
//			$("#businessNumberBorrower").focus();
//		}
//	});
}


// 动态显示 省 、市、区县 下拉列表 ，需要传入 省、市、区下拉框的 id
function showCityCombo(province, city, district) {
	var provinceid = $("#" + province).combobox("getValue");

	var province = $("#" + province).combobox(
					{
						// required : true,
						editable : false,
						url : serverName + '/NSC/list.do',
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						width : '150',
						value : provinceid,
						onSelect : function(value) {
							$("#" + city).combobox(
											{
												editable : false,
												url : serverName
														+ '/NSC/listCity.do?parentId='
														+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value : '',
												onSelect : function(value) {
													$("#" + district).combobox(
																	{
																		editable : false,
																		url : serverName
																				+ '/NSC/listCity.do?parentId='
																				+ value.cityCode,
																		textField : 'cityName',
																		valueField : 'cityCode',
																		mode : 'remote',
																		width : '150',
																		delay : 500,
																		value : ''
																	});
												}
											});
						}
					});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId =$("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : ciryId
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : districtId
		});
	}

}


//function showBackApplication(){
//	var caId = $("#creditapplicationId").val();
//	var row= $("#borrowSerivceList").datagrid("getSelected");
//	var dd1 = $("#borrowSerivce").dialog({
//		title : "业务申请表",
//		modal : true,
//		closed : true,
//		inline : false,
//		width: 1000,
//		height: 470,
//		border : false,
//		draggable:false,
//		dosize : true,
//		buttons : [ {text : "拒贷",handler:function(){
//			if( $("#jobInfoList0company").val() == "" &&
//					$("#jobInfoList1company").val() == "" &&
//					$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
//					$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
//					$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
//				 {
//					 $.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
//					 return;
//				 }
//				if( $("#livingCommercialForm").attr("checked") == undefined &&
//					$("#livingSelfForm").attr("checked") == undefined &&
//					$("#livingRentForm").attr("checked") == undefined &&
//					$("#livingRelativeForm").attr("checked") == undefined &&
//					$("#livingOtherForm").attr("checked") == undefined)
//				 {
//					 $.messager.alert("消息","请至少选择一项 居住状况！");
//					 return;
//				 }
//			
//				
//				if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
//					$("#addSave").linkbutton('disable');
//				}else{
//					$.messager.alert("消息","请将数据填写正确完整！");
//					return;
//				}
//				
//				ajaxSubmit(serverName+"/application/add.do","post",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//					if(result){
//						$("#borrowSerivce").dialog("close");
//						showBorrowerServiceApp();
//						$("#addSave").linkbutton('enable');
//					}else{
//						$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//						$("#addSave").linkbutton('enable');
//					}
//				},false);
//			$.messager.confirm('提示信息','确认要拒贷吗?',function(r){
//				if (r){
//					ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:caId,flag:"27"},function(result){
//						if(result){
//							$.messager.alert("消息",result.msg,"info",function(){
//								if(result.success){
//									document.location='jsp/rc/business/creditApplicationList.jsp';
//								}
//							});
//						}else{
//							$.messager.alert("消息","系统错误,请重试或联系管理员！");
//						}
//					});
//				}
//			});
//		}},
//		            {text : "客户放弃",handler:function(){
//		            	$.messager.confirm('提示信息','确认要放弃吗?',function(r){
//		            		if (r){
//		            			ajaxSubmit(serverName + "/creditgroup/updateRefuse.do",{caId:caId,flag:"28"},function(result){
//		    						if(result){
//		    							$.messager.alert("消息",result.msg,"info",function(){
//		    								if(result.success){
//		    									document.location='jsp/rc/business/creditApplicationList.jsp';
//		    								}
//		    							});
//		    						}else{
//		    							$.messager.alert("消息","系统错误,请重试或联系管理员！");
//		    						}
//		    					});
//		            		}
//		            	});
//		            }},
//		            /*{
//		    			id: "tempAddSave",
//		    			text : "暂存",
//		    			iconCls : "icon-save",
//		    			handler : function() {
//		    					ajaxSubmit(serverName+"/application/tempUpdate.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//		    						if(result){
//		    							$.messager.show({
//			    							showType : "show",
//			    							timeout : 5000,
//			    							title : "消息",
//			    							msg : "操作成功,填完表单后请全部保存！"
//			    						});
//			    						$("#borrowSerivce").dialog("close");
//			    						showBorrowerServiceApp();
//		    						}else{
//		    							$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//		    						}
//		    					});
//		    			}
//		    		},*/
//		            {
//			text : "全部保存",
//			iconCls : "icon-save",
//			handler : function() {
//					if( $("#jobInfoList0company").val() == "" &&
//						$("#jobInfoList1company").val() == "" &&
//						$("#surveybusinessinfoList0operatingItemsDetail").val() == ""&&
//						$("#surveybusinessinfoList1operatingItemsDetail").val() == ""&&
//						$("#surveybusinessinfoList2operatingItemsDetail").val() == "")
//					 {
//						 $.messager.alert("消息","请在“工作情况”或“经营情况”中至少添加一条记录！”");
//						 return;
//					 }
//					if( $("#livingCommercialForm").attr("checked") == undefined &&
//						$("#livingSelfForm").attr("checked") == undefined &&
//						$("#livingRentForm").attr("checked") == undefined &&
//						$("#livingRelativeForm").attr("checked") == undefined &&
//						$("#livingOtherForm").attr("checked") == undefined)
//					 {
//						 $.messager.alert("消息","请至少选择一项 居住状况！");
//						 return;
//					 }
//					
//					familyIdFlag = false;
//					if($("#formOther").form("validate") && $("#formBorrrower").form("validate")){
//					}else{
//						$.messager.alert("消息","请将将数据填写正确完整！");
//						return;
//					}
//					familyIdFlag = true;
//					ajaxSubmit(serverName+"/application/add.do",$("#formOther").serialize()+"&"+$("#formBorrrower").serialize(),function(result){
//						if(result){
//							$("#borrowSerivce").dialog("close");
//							showBorrowerServiceApp();
//						}else{
//							$.messager.alert("消息","操作失败！系统错误，请与管理员联系！");
//						}
//					});
//				
//			}
//		}, {
//			text : "关闭",
//			iconCls : "icon-cancel",
//			handler : function() {
//				$("#borrowSerivce").dialog('close');
//			}
//		} ],
////		 href : serverName+"/application/selectHouseServeyVo.do?borrowerServiceAppId="+row.borrowerServiceAppId+"&businessNumber="+$("#groupNumber1").val()
//		href : serverName+"/jsp/rc/business/borrowerServiceApp.jsp"
//
//	});
//			
//	dd1.dialog('open');
//			
//			ajaxSubmit(serverName+"/application/select.do",{borrowerServiceAppId:row.borrowerServiceAppId},function(result){
//				if(result == null){
//					alert("查询失败");
//				}else{
//					$("#businessNumberBorrower").val($("#groupNumber1").val());
//					if(result.borrowerService.livingDate == "" ||result.borrowerService.livingDate == null){
//					 	$("#livingdateDiv").html('');
//// $("#livingDate").datebox({required : false,width :130,disabled: true});
//					}else{
//						$("#livingdateDiv").html('<input type="text" name="borrowerService.livingDate" id="livingDate" style="width: 130px;" />');
//						$("#livingDate").datebox({width :130,editable:false});
//					}
//					if(result.borrowerService.businessDate  == "" ||result.borrowerService.businessDate  == null){
//						$("#businessDateDiv").html('');
//// $("#businessDate").datebox({required : false,width :130,disabled: true});
//					}else{
//						$("#businessDateDiv").html('<input type="text" name="borrowerService.businessDate"  id="businessDate" style="width: 130px;" />');
//						$("#businessDate").datebox({width :130,editable:false});
//					}
//					
//					$("#formBorrrower").form('validate');
//					familyIdFlag = false;
//					$("#formOther").form('validate');
//					$("#formBorrrower").form("load",result);
//					$("#formOther").form("load",result);
//					familyIdFlag = true;
//					
//					// 地址
//					provinceShow("provinceHome", "cityHome", "countyHome","townHome","villageHome","homeAddress","detailHome","hourseholdAddress");
//					provinceShow("provinceBusiness", "cityBusiness", "countyBusiness","townBusiness","villageBusiness","businessAddress","detailBusiness","");
//					// 设置性别
//					var sex = maleOrFemalByIdCard(result.borrowerService.credentialsNumber);
//					$("#genderBorrower").val(sex);
//					$("#showGender").val((sex == 0)?"男":"女");
//					$("#businessNumberBorrower").focus();
//				}
//			});
//	
//}
 
//
//// 计算添加里的余额合计
//function addBlance(){
//	var totalBlance;
//	var balance2= $("#balance2").val();
//	var balance1= $("#balance1").val();
//	var balance0= $("#balance0").val();
//	totalBlance = accAdd(accAdd(balance2,balance1),balance0);
//	$("#totalBlance").val(totalBlance);
//	
//}
//
//
//// 计算总额
//function addAmount(){
//	var TTAmount;
//	
//	var totalAmount2;
//	var totalAmount1;
//	var totalAmount0;
//	
//	var unitPrice2=$("#unitPrice2").val();
//	var unitPrice1=$("#unitPrice1").val();
//	var unitPrice0=$("#unitPrice0").val();
//	
//	var quantity2 = $("#quantity2").val();
//	var quantity1 = $("#quantity1").val();
//	var quantity0 = $("#quantity0").val();
//	
//	totalAmount2 = accMul(unitPrice2,quantity2).toFixed(2);
//	totalAmount1 = accMul(unitPrice1,quantity1).toFixed(2);
//	totalAmount0 = accMul(unitPrice0,quantity0).toFixed(2);
//	
//	if(totalAmount2 != 0){
//		$("#totalAmount2").val(totalAmount2);
//	}else{
//		$("#totalAmount2").val("");
//	}
//	if(totalAmount1 != 0){
//		$("#totalAmount1").val(totalAmount1);
//	}else{
//		$("#totalAmount1").val("");
//	}
//	if(totalAmount0 != 0){
//		$("#totalAmount0").val(totalAmount0);
//	}else{
//		$("#totalAmount0").val("");
//	}
//	TTAmount= accAdd(accAdd(totalAmount2,totalAmount1),totalAmount0).toFixed(2);
//	if(TTAmount != 0){
//		$("#TTAmount").val(TTAmount);
//	}else{
//		$("#TTAmount").val("");
//	}
//	
//}
//
//function livingRentDate(){
//	if($("#livingRentForm").attr("checked") == "checked"){
//		
//		$("#livingdateDiv").html('<input type="text" name="borrowerService.livingDate" id="livingDate" style="width: 130px;" />');
//		$("#livingDate").datebox({required : true,width :130,editable:false});
//	}else{
//		$("#livingdateDiv").html('');
//	}
//}
//
//function businessRentDate(){
//	if($("#businessRentForm").attr("checked") == "checked"){
//		$("#businessDateDiv").html('<input type="text" name="borrowerService.businessDate"  id="businessDate" style="width: 130px;" />');
//		$("#businessDate").datebox({required : true,width :130,editable:false});
//	}else{
//		$("#businessDateDiv").html('');
//	}
//}
//
//
//function changsuo(){
//	if( $("[name='borrowerService.businessCommercial']")[0].checked == false &&
//			$("[name='borrowerService.businessSelf']")[0].checked == false&&
//			$("[name='borrowerService.businessRent']")[0].checked ==false &&
//			$("[name='borrowerService.businessRelative']")[0].checked ==false &&
//			$("[name='borrowerService.businessOther']")[0].checked ==false)
//		 {
//			
//			$("#businessArea").val("");
//			$("#businessArea").validatebox({
//				 required:false,
//				 validType:"number" 
//			 });
//			$("#businessArea").attr("readonly","readonly");
//			$("#businessYear").val("");
//			 $("#businessYear").validatebox({
//				 required:false,
//				 validType:"number" 
//			 });
//			 $("#businessYear").attr("readonly","readonly");
//			 $("#officePhone").val("");
//			 $("#officePhone").validatebox({
//				 required:false,
//				 validType:"number" 
//			 });
//			 $("#officePhone").attr("readonly","readonly");
//			
//		 }else{
//			 $("#businessArea").validatebox({
//				 required:true,
//				 validType:"number"
//			 });
//			 $("#businessArea").attr("readonly",false);
//			 $("#businessYear").validatebox({
//				 required:true,
//				 validType:"number"
//			 });
//			 $("#businessYear").attr("readonly",false);
//			 $("#officePhone").validatebox({
//				 required:true,
//				 validType:"phoneNumber"
//			 });
//			 $("#officePhone").attr("readonly",false);
//		 }
//}
//
//function changsuobusinessRentDate(){
//	changsuo();
//	businessRentDate();
//}

///**
// * 通过身份证判断是男是女
// * 
// * @param idCard
// *            15/18位身份证号码
// * @return '0'-男 '1'-女、
// */ 
//function maleOrFemalByIdCard(IdCard){
//    if(IdCard.length==15){  
//        if(IdCard.substr(14,1)%2==0){  
//            return '1';  
//        }else{  
//            return '0';  
//        }  
//    }else if(IdCard.length ==18){ 
//        if(IdCard.substr(16,1)%2==0){  
//            return '1';  
//        }else{  
//            return '0';  
//        }  
//    }
//}


//function provinceShow(province, city, county,town,village,address,detail,address2){
//	var homeAddress = $("#"+address).val();
//	if(homeAddress != undefined){
//		var homeAddressList = homeAddress.split("-");
//		$("#"+detail ).val(homeAddressList[5]);
//	}
//	var villageHome = $("#"+village).combobox("getValue");
//	var townHome;
//	var countyHome;
//	var cityHome;
//	var provinceHome;
//// $("#detailHome").validatebox();
//	if(villageHome != null && villageHome != ""){
//		ajaxSubmit(serverName+"/NSC/selectByVallageId.do",{VallageId:villageHome},function(resultMap){
////		var urlTown = serverName+"/town/searchParentId.do";
////		ajaxSubmit(urlTown,{townshipinfoid:villageHome},function(parentId){
////			townHome = parentId.parentId;
////			ajaxSubmit(urlTown,{townshipinfoid:townHome},function(parentId){
////				countyHome = parentId.parentId;
////				var urlCity = serverName+"/NSC/selectByCode.do";
////				ajaxSubmit(urlCity,{cityCode:countyHome},function(parentId){
////					cityHome = parentId.parentId;
////					ajaxSubmit(urlCity,{cityCode:cityHome},function(parentId){
////						provinceHome = parentId.parentId;
//						$("#" + province).combobox(
//								{
//									editable: false,
//								//	url : serverName + '/NSC/list.do',
//									data: resultMap.nationalStandardCodeSheng,
//									textField : 'cityName',
//									valueField : 'cityCode',
//									mode :'remote',
//									delay :500,
//									width :'150',
//									value:resultMap.nationalStandardCodeShi.parentId,
//									onSelect: function(value) {
//										$("#" + city).combobox("clear");
//										$("#" + county).combobox("setValue","");
//										$("#"+town).combobox("setValue","");
//										$("#"+village).combobox("setValue","");
//										if(province == "provinceHome"){
//										addAddress(true);
//										}else{addBusinessAddress();
//										}$("#" + city).combobox(
//										{
//										editable : false,
//										url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										delay : 500,
//										width : '150',
//										value:""
//										});
//									}
//								});
//						$("#" + county).combobox({
//							//url : serverName + '/NSC/listCity.do?parentId=' + cityHome,
//							data : resultMap.nationalStandardCodeQuList,
//							textField : 'cityName',
//							valueField : 'cityCode',
//							mode : 'remote',
//							delay : 500,
//							width : '150',
//							//value : countyHome,
//							value : resultMap.nationalStandardCodeQu.cityCode,
//							onSelect:function(countryNSC){
//								$("#"+village).combobox("setValue","");
//								$("#"+town).combobox("setValue","");
//								if(province == "provinceHome"){
//									addAddress(true);
//								}else{
//									addBusinessAddress();
//								}
//								$("#"+town).combobox({
//									url : serverName+ '/town/list.do?parentId='+countryNSC.cityCode,
//									textField : 'townshipinfName',
//									valueField : 'townshipinfoid',
//									mode : 'remote',
//									delay : 500,
//									width : '150',
//									value:""
//								});
//							}
//						});
//						$("#" + city).combobox({
//							editable : false,
//							//url : serverName + '/NSC/listCity.do?parentId=' + provinceHome,
//							data :  resultMap.nationalStandardCodeShiList,
//							textField : 'cityName',
//							valueField : 'cityCode',
//							mode : 'remote',
//							delay : 500,
//							width : '150',
//							//value : cityHome,
//							value : resultMap.nationalStandardCodeShi.cityCode,
//							onSelect:function(cityNSC){
//								$("#" + county).combobox("setValue","");
//								$("#"+town).combobox("setValue","");
//								$("#"+village).combobox("setValue","");
//								if(province == "provinceHome"){
//									addAddress(true);
//								}else{
//									addBusinessAddress();
//								}
//								$("#" + county).combobox({
//									editable : false,
//									url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
//									textField : 'cityName',
//									valueField : 'cityCode',
//									mode : 'remote',
//									delay : 500,
//									width : '150',
//									value:""
//								});
//							}
//							
//						});
//						
//						$("#"+town).combobox({
//							//url : serverName+ '/town/list.do?parentId='+countyHome,
//							data: resultMap.townTownList,
//							textField : 'townshipinfName',
//							valueField : 'townshipinfoid',
//							mode : 'remote',
//							delay : 500,
//							width : '150',
//							//value : townHome,
//							value : resultMap.townTown.townshipinfoid,
//							onSelect:function(townTown){
//								$("#"+village).combobox("setValue","");
//								if(province == "provinceHome"){
//									addAddress(true);
//								}else{
//									addBusinessAddress();
//								}
//								$("#"+village).combobox({
//									url : serverName+ '/town/list.do?parentId='+townTown.townshipinfoid,
//									textField : 'townshipinfName',
//									valueField : 'townshipinfoid',
//									mode : 'remote',
//									delay : 500,
//									width : '150',
//									value:""
//								});
//							}
//						});
//						$("#"+village).combobox({
//							//url : serverName+ '/town/list.do?parentId='+townHome,
//							data : resultMap.townVallageList,
//							textField : 'townshipinfName',
//							valueField : 'townshipinfoid',
//							mode : 'remote',
//							delay : 500,
//							width : '150',
//							value : villageHome,
//							onSelect:function(){
//								if(province == "provinceHome"){
//									addAddress(true);
//								}else{
//									addBusinessAddress();
//								}
//							}
//						});
//						// cityCombobox(province, city, county,town,village,provinceHome);
////					});
////				});
////			});
////		});
//		});
//		$("#detailHome").validatebox('validate');
//	}else{
//		cityComboboxPublic(province, city, county,town,village,provinceHome,detail,address,address2);
//	}
//	 
//}



//function addAddress(paramValidate){
//	if(paramValidate){
//	var provinceHome=$("#provinceHome").combobox("getText");
//	var cityHome=$("#cityHome").combobox("getText");
//	var countyHome=$("#countyHome").combobox("getText");
//	var townHome=$("#townHome").combobox("getText");
//	var villageHome=$("#villageHome").combobox("getText");
//	var detailHome=$("#detailHome").val();
//	
//	var homeAddress = provinceHome+"-"+cityHome+"-"+countyHome+"-"+townHome+"-"+villageHome+"-"+detailHome;
//	$("#homeAddress").val(homeAddress);
//	$("#hourseholdAddress").val(homeAddress);
//	$("#hourseholdAddress").validatebox("validate");
//	}
//}
// 
//function addBusinessAddress(){
//	
//	var provinceBusiness=$("#provinceBusiness").combobox("getText");
//	var cityBusiness=$("#cityBusiness").combobox("getText");
//	var countyBusiness=$("#countyBusiness").combobox("getText");
//	var townBusiness=$("#townBusiness").combobox("getText");
//	var villageBusiness=$("#villageBusiness").combobox("getText");
//	var detailBusiness=$("#detailBusiness").val();
//	var BusinessAddress = provinceBusiness+"-"+cityBusiness+"-"+countyBusiness+"-"+townBusiness+"-"+villageBusiness+"-"+detailBusiness;
//	$("#businessAddress").val(BusinessAddress);
//}

// 现金流入和流出表 liuli
//2014.5.7郝强为了循环贷判断加入了申请单是否提交的判断！
function showCashStreamInput() {
	document.getElementById('aCashOutAndIn').setAttribute('href','javascript:void(0)');
	// var rows = $("#creditApplicationList").datagrid("getSelected");
	var row = $("#borrowSerivceList").datagrid("getSelected");
	var creditId = $("#creditapplicationId1").val();
	
	ajaxSubmit(serverName+"/creditgroup/checkBorrowerSave.do",{creditapplicationId:creditId},function(r){
		if(r.success){
			document.getElementById('aCashOutAndIn').setAttribute('href','javascript:showCashStreamInput()');
			var name = row.name;
			// alert(serverName);
				var dd1 = $("#cashStream").dialog({
					title : "现金流入流出表",
					modal : true,
					closed : true,
					inline : false,
					width: 1000,
					height: 470,
					border : false,
					draggable:true,
					dosize : true,
					buttons : [ {
						id : "saveAllCashStreams",
						text : "全部保存",
						iconCls : "icon-save",
						handler : function() {
							var maxLoanSumValue = document.getElementById('maxLoanSum').value;
							if(maxLoanSumValue==""){
								$.messager.alert("提示","最大借款额度值没有被计算出来,不能提交。");
								return ;
							}
							if(maxLoanSumValue==0){
								$.messager.alert("提示","最大借款额度值不能为0.00,不能提交。");
								return ;
							}
							$.messager.confirm("消息", "确定保存数据?", function(result) {
								if(result){
									$("#saveAllCashStreams").attr("disabled",true);
									// 加载中提示
									$("<div class='datagrid-mask'></div>").css({ cursor:"wait", display: "block", width: "100%", height: $(window).height() }).appendTo("load");  
									$("<div class='datagrid-mask-msg'></div>").html("数据保存中,请稍后...").appendTo("load").css({ cursor:"wait", display: "block", left: ($(document.body).outerWidth(true) - 350) / 2, top: ($(window).height()) / 2 - 90 }); 
									
									ajaxSubmit(serverName+"/cashStream/update.do?creditapplicationId="+creditId,$("#cashStreamForm").serialize(),function(result){
										if(result){
											// 删除加载中提示
											$(".datagrid-mask").remove();  
											$(".datagrid-mask-msg").remove();  
											$.messager.show({
												title : "消息",
												msg : result.msg
											});
											//当返回的code值为1时，保存成功，关闭dialog
											if(result.code==1){
												$("#cashStream").dialog("close");
											}
												//alert("保存成功");
										}else{
											// 删除加载中提示
											$(".datagrid-mask").remove();  
											$(".datagrid-mask-msg").remove();  
											$.messager.show({
												title : "消息",
												msg : result.msg
											});
											//$.messager.alert("操作失败,请检查是否有空值或联系管理员");
											$("#saveAllCashStreams").attr("disabled",false);
										}
									});
								}
							});
						}
					}, {
						text : "关闭",
						iconCls : "icon-cancel",
						handler : function() {
							$.messager.confirm("消息", "确定要关闭窗口么?未保存的数据在关闭窗口后丢失。", function(result) {
								if(result){
									$("#cashStream").dialog('close');
								}
							});
						}
					} ],
					href : serverName+"/jsp/rc/business/cashStreamInput.jsp",
					onLoad:function(){
						$("#saveAllCashStreams").attr("disabled",true);
						var paraValue = creditId +"_" +name;
						ajaxSubmit(serverName+"/cashStream/getCashStream.do",{param:paraValue},function(result){
							$("#cashStreamForm").form("load",result);
							//这里用到了张嫚那个页面中的产品名称 显示在这里-----保险起见 先不用！
							//$("#repaymentPlanNamehq").val($("#repaymentPlanName").val());
							$("#creatDate").val(result.csOperInTotal.createDate);
							$("#borrowerName").val(result.csOperInTotal.borrowerName);
							$("#saveAllCashStreams").attr("disabled",false);
							calculateEveryEq();
							// 删除加载中提示
							$(".datagrid-mask").remove();  
							$(".datagrid-mask-msg").remove(); 
							// 加载完成后计算一遍 liuli 2013-05-30 因为从excel导入的数据是没有进行计算的
							//var maxLoanSumValue = document.getElementById('maxLoanSum').value;
							// 当最大借款额度值为零时才进行计算
							//if(maxLoanSumValue=="0"){
								//calculateAllDown();
							//}
							

//							设置家庭流入项目内容
							ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"cashIncomeFamilyTotal",q:""},function(result){
								industryCategoryAll = result;
								for(var k = 0 ; k < industryCategoryAll.cashIncomeFamilyTotal.length;k++){
								 	var  index = industryCategoryAll.cashIncomeFamilyTotal[k].codaTableId;	
								 	var  name = "cashIncomeFamilyTotal"+index;
								 	var  objectName = "industryCategoryAll."+name;
								 	var object = (eval("("+objectName+")"));
									for(var i = 0 ; i < object.length;i++){
											if(object[i] != undefined){
												if(object[i].codeKey == $("#csFamilyInList0projectCodeKey").val()){
													$("#csFamilyInList0projectCodeKeyShow").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csFamilyInList1projectCodeKey").val()){
													$("#csFamilyInList1projectCodeKeyShow").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csFamilyInList2projectCodeKey").val()){
													$("#csFamilyInList2projectName").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csFamilyInList3projectCodeKey").val()){
													$("#csFamilyInList3projectName").val(object[i].codeVlue);
												};
											}
									}
								}
							});
							
//							设置经营项目内容
							
							//高艳红提交现金流项目的名称取经营项目详细字段   
							var pro_detailName=$("#csOperInList0projectName").val();
							$("#csOperInList0projectName").val(pro_detailName);
							
							
							/*ajaxSubmit(serverName+"/dicRequest/getSpecifiedDic.do",{section:"industryCategory",q:""},function(result){
								var object = result;
									for(var i = 0 ; i < object.length;i++){
											if(object[i] != undefined){
												
												if(object[i].codeKey == $("#csOperInList0projectCodeKey").val()){
													$("#csOperInList0projectName").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csOperInList1projectCodeKey").val()){
													$("#csOperInList1projectName").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csOperInList2projectCodeKey").val()){
													$("#csOperInList2projectName").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csOperOutListChild0projectCodeKey").val()){
													$("#csOperOutListChildprojectName0").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csOperOutListChild1projectCodeKey").val()){
													$("#csOperOutListChildprojectName1").val(object[i].codeVlue);
												};
												if(object[i].codeKey == $("#csOperOutListChild2projectCodeKey").val()){
													$("#csOperOutListChildprojectName2").val(object[i].codeVlue);
												};
											}
									}
							});*/
						});
						ajaxSubmit(serverName+"/cashStream/getProductInfo.do",{
							creditapplicationId:$("#creditapplicationId1").val()
							},function(a){
								$("#repaymentPlanNamehq1").val(a.repaymentPlanName);
						});
					}
				});
				dd1.dialog('open');
		}else{
			$.messager.alert("消息",r.msg,"info");
		}
	});
	
}
// 现金流工作表 liuli
function showCashStreamWorkTableInput() {
// var rows = $("#creditApplicationList").datagrid("getSelected");
	var row = $("#borrowSerivceList").datagrid("getSelected");
	var creditId = $("#creditapplicationId1").val();
	var dd1 = $("#cashStream").dialog({
		title : "现金流工作表",
		modal : true,
		closed : true,
		inline : false,
		width: 1000,
		height: 470,
		border : false,
		draggable:true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$.messager.confirm("消息", "确定要关闭窗口么?未保存的数据在关闭窗口后丢失。", function(result) {
					if(result){
						$("#cashStream").dialog('close');
					}
				});
			}
		} ],
		href : serverName+"/jsp/rc/business/cashStreamWorkTableInput.jsp",
		onLoad:function(){
// $("#saveAllCashStreams").attr("disabled",true);
			// 禁用保存按钮
			$("#everyDayOperCsInFormBtn").attr("disabled",true);
			$("#everyWeekOperCsInFormBtn").attr("disabled",true);
			$("#everyMonthOperCsInFormBtn").attr("disabled",true);
			$("#everyCostOperCsInFormBtn").attr("disabled",true);
			$("#avgIncRateFormBtn").attr("disabled",true);
			$("#purchaceCostsFormBtn").attr("disabled",true);
			var paraValue = creditId;
			ajaxSubmit(serverName+"/cashStream/getCashStreamWorkTable.do",{param:paraValue},function(result){
// $("<div class='datagrid-mask'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
// $("<div class='datagrid-mask-msg'></div>").html("加载中。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190)
// / 2, top: ($(window).height() - 45) / 2 });
				// 填充表单
				$("#everyDayOperCsInForm").form("load",result);
				$("#everyWeekOperCsInForm").form("load",result);
				$("#everyMonthOperCsInForm").form("load",result);
				$("#everyCostOperCsInForm").form("load",result);
				$("#avgIncRateForm").form("load",result);
				$("#purchaceCostsForm").form("load",result);
				// 启用保存按钮
				$("#everyDayOperCsInFormBtn").attr("disabled",false);
				$("#everyWeekOperCsInFormBtn").attr("disabled",false);
				$("#everyMonthOperCsInFormBtn").attr("disabled",false);
				$("#everyCostOperCsInFormBtn").attr("disabled",false);
				$("#avgIncRateFormBtn").attr("disabled",false);
				$("#purchaceCostsFormBtn").attr("disabled",false);
				// 设置采购或进货成本名称
				setProjNameAndAddRate();
				// 删除加载效果
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove();  

			});
		}
	});
	dd1.dialog('open');
}

// 查看现金流入和流出表 liuli2013-05-31该查看调用另一个接口，只是查看，不在更新借款人姓名和项目名称
function viewCashStreamInput() {
	
	// var rows = $("#creditApplicationList").datagrid("getSelected");
	var row = $("#borrowSerivceList").datagrid("getSelected");
	var creditId = $("#creditapplicationId1").val();
	var name = row.name;
// alert(serverName);
	var dd1 = $("#cashStream").dialog({
		title : "现金流入流出表",
		modal : true,
		closed : true,
		inline : false,
		width: 1000,
		height: 470,
		border : false,
		draggable:true,
		dosize : true,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$("#cashStream").dialog('close');
			}
		} ],
		href : serverName+"/jsp/rc/business/cashStreamInputView.jsp",
		onLoad:function(){
			$("#saveAllCashStreams").attr("disabled",true);
			var paraValue = creditId;
			ajaxSubmit(serverName+"/cashStream/toViewCashStream.do",{param:paraValue},function(result){
				$("#cashStreamForm").form("load",result);
				$("#creatDate").val(result.csOperInTotal.createDate);
				$("#borrowerName").val(result.csOperInTotal.borrowerName);
				$("#saveAllCashStreams").attr("disabled",false);
				calculateEveryEq();
				// 删除加载中提示
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove(); 
				// 加载完成后计算一遍 liuli 2013-05-30 因为从excel导入的数据是没有进行计算的
				var maxLoanSumValue = document.getElementById('maxLoanSum').value;
				// 当最大借款额度值为零时才进行计算
				if(maxLoanSumValue=="0"){
					calculateAllDown();
				}
			});
			ajaxSubmit(serverName+"/cashStream/getProductInfo.do",{
				creditapplicationId:$("#creditapplicationId1").val()
				},function(a){
					$("#repaymentPlanNamehq2").val(a.repaymentPlanName);
			});
		}
	});
	dd1.dialog('open');
}


//function hhAddress(){
//	if($("#hourseholdAddress").val() == null || $("#hourseholdAddress").val() == "" ||$("#hourseholdAddress").val() == undefined){
//		$("#hourseholdAddress").val($("#homeAddress").val());
//		$("#hourseholdAddress").validatebox("validate");
//	}
//}