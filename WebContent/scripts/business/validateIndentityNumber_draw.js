//var identityNumber = $.trim($("#identity").val());
var identityNumberForBorrower= $.trim($("#identityForBorrower").val());
var identityNumberForCoBorrower = $.trim($("#identityForCoBorrower").val());
//console.info("-------identityNumber------------"+identityNumber);
$(function(){
    //初始化借款人验证结果 datagrid
	initBorrowerValidateRSDG();
	//初始化借款人配偶验证结果	datagrid
	initMateValidateRSDG();
});
//初始化借款人验证结果 datagrid
function initBorrowerValidateRSDG(){
	$("#borrowerList").datagrid({
	 	panelWidth : 645,
	 	panelHeight:52,
    	mode: "remote",
    	url :serverName+"/customer/getIdentityListInfo.do",
        queryParams:{identityNumber: identityNumberForBorrower},
        idField: "credentialsNumber",
    	textField: "credentialsNumber",
    	columns : [[{field :"name" ,title: "姓名" , width:100},
    	            {field :"credentialsNumber" ,title: "身份证号" , width:150},
    	            {field :"auditStatusShow" ,title: "状态" , width:100,formatter:function(value){
    	            	if(value == null ||value == ""){
    	            		return "未申请";
    	            	}else{
    	            		return value;
    	            	}
    	              }
    	            },
    	            {field:"customerType",title:"客户类型", width:80,formatter:function(value,param){
    	            	if(value == "5"){
    	            		return "一期未完成";
    	            	}
    	            	if(value == "4"){
    	            		return "本组担保人";
    	            	}
    	            	if(value == "3"){
    	            		return "借款人共借人";
    	            	}
    	            	if(value == "1" || value == "9"){
    	            		return "已存借款人";
    	            	}
                        if(value == "6"){
                              if(param.credentialsType =='1'){
                                  return "已存借款人";
                              }   else{
                                  return "已存担保人";
                              }
                        }
    	            	if(value == "0"){
    	            		return "已存担保人";
    	            	}else{
                            return "没有角色";
                        }
    	            }},
                      {field:"blackFlag",title:"是否加入黑名单", width:90,formatter:function(value){
                          if(value == 'Y'){
                              return '是';
                          }else {
                              return '否';
                          }
                      }},
                     {field:"flag",title:"是否可用", width:90,formatter:function(value,data,index){
                        // console.info("-----------value----start----"+value);
                         //step0检查一期未完成用户
                         //step1检查借款人黑名单信息
                         //step2检查借款人信息
                         //step3检查借款人配偶信息
                         //step4检查借款人担保信息
                         if(data.customerType == "5"){
                             $("#borrowerValidateMsgFlag").val("false");
                             $("#borrowerValidateFalseMsg").val("此人存在借贷关系且状态为一期未完成，不能使用.....");
                             return "不可用";
                         }
                         //step1检查借款人黑名单信息
                         if(data.customerType == "9"){
                             $("#borrowerValidateMsgFlag").val("false");
                             $("#borrowerValidateFalseMsg").val("此人在黑名单列表中，不能使用.....");
                             return "不可用";
                         }
                         //step2检查借款人信息
                         if(data.customerType == "4"){
                             $("#borrowerValidateMsgFlag").val("false");
                             $("#borrowerValidateFalseMsg").val("此人为同组担保人，不能使用.....");
                             return "不可用";
                         }
                        // console.info("-----------value----1----"+value);
                       //  console.info("-----------data.customerType----1----"+data.customerType);
                          //检查借款人及配偶信息
                         if(data.customerType == "1" || data.customerType == "3" ){
                             if($.trim(value) == "18"
                                 ||$.trim(value) == "16"
                                 ||$.trim(value) == "20"
                                 ||$.trim(value) == "26"
                                 ||($.trim(value) == "27" && data && data.specialRefuseFlag != "sayYes")
                                 ||$.trim(value) == "28"){
                                 //正常的

                                 return "可以使用";
                             }else{
                               //  console.info("-------value----"+value);
                                 $("#borrowerValidateMsgFlag").val("false");
                                 if(data.customerType == "1" ){
                                     $("#borrowerValidateFalseMsg").val("此人已借款且状态为'"+data.auditStatusShow+"'，不能使用.....");
                                 } else{
                                     $("#borrowerValidateFalseMsg").val("此人为其他借款人共借人，且借贷状态为'"+data.auditStatusShow+"'，不能使用.....");
                                 }

                                 return "不可用";
                             }
                         }
                         //step4检查借款人担保信息
                         if(data.customerType == "0"){
                             if( $.trim(value) == "16"
                                 ||$.trim(value) == "20"
                                 ||$.trim(value) == "18"
                                 ||$.trim(value) == "26"
                                 ||($.trim(value) == "27")
                                 ||$.trim(value) == "28"){
                                 //正常的
                                 return "可以使用";
                             }else{
                                 $("#borrowerValidateMsgFlag").val("false");
                                 $("#borrowerValidateFalseMsg").val("此人正在为其他借款人做担保，且状态为"+data.auditStatusShow+"，不能使用.....");
                                 return "不可用";
                             }
                         }
                         //step5 检查此人直系亲属是否在做借款和担保不符合标准
                         if(data.customerType == "6"){
                             $("#borrowerValidateMsgFlag").val("false");
                             $("#borrowerValidateFalseMsg").val("此人直系亲属在借款期或者担保期，不能使用.....");
                             return "不可用";
                         }
                         return "可以使用";
    	               }
    	            }]],
			    	onLoadSuccess:function(data){
                        $("#validateBorrowerIndentity").linkbutton("enable");
                        $("#validateMateIndentity").linkbutton("enable");
                        //判断是否是首次进入及未输入身份证号
                        var identityNumber = $.trim($("#identityForBorrower").val());
                      //  console.info("identityNumber--------"+identityNumber);
                        if(identityNumber == null || ""==identityNumber ) {
                             return;  //首次进入直接返回及未输入身份证号
                        }
                        if(data.total == 0 && $.trim($("#identityForBorrower").val()).length == 18){
                            //console.info(data+"------1-----");
                                //未在系统中做过贷款信息
                                $("#borrowerIdNumber").val($.trim($("#identityForBorrower").val()));
                                $("#borrowerFlag").val("true");
                              //  $("#borrowerValidateRSinfo").val("借款人可以借款.....");
                                $("#borrowerValidateFalseMsg").val("该人在系统中未存在借贷信息，可以使用...");
                                $("#borrowerValidateMsgFlag").val("true");
                                $("#borrowerValidateRSinfo").val("该人在系统中未存在借贷信息，可以使用...");
                                showGoToCreditApplicationButton();
                                return;
                       }
//                        console.info(data+"------2-----");
                        setCustomerValidateInfo();
                        showGoToCreditApplicationButton();
			    	}

    });
}

//设置借款人验证结果
function  setCustomerValidateInfo(){
    var  borrowerValidateMsgFlag   =    $.trim( $("#borrowerValidateMsgFlag").val());
    var  borrowerValidateFalseMsg =     $.trim($("#borrowerValidateFalseMsg").val());
    //判断此记录是不是不符合的借款人
    if(borrowerValidateMsgFlag=="false"){  //是不符合标准的借款人
        $("#borrowerValidateRSinfo").val(borrowerValidateFalseMsg);
    }else{
        $("#borrowerIdNumber").val($.trim($("#identityForBorrower").val()));
        $("#borrowerFlag").val("true");
        $("#borrowerValidateRSinfo").val("此人符合标准，可以使用");
    }
}

//初始化借款人配偶验证结果

function initMateValidateRSDG(){
    $("#mateList").datagrid({
        panelWidth : 645,
        panelHeight:52,
        mode: "remote",
        url :serverName+"/customer/getIdentityListInfo.do",
        queryParams:{identityNumber: identityNumberForCoBorrower},
        idField: "credentialsNumber",
        textField: "credentialsNumber",
        columns : [[{field :"name" ,title: "姓名" , width:100},
            {field :"credentialsNumber" ,title: "身份证号" , width:150},
            {field :"auditStatusShow" ,title: "状态" , width:100,formatter:function(value){
                if(value == null ||value == ""){
                    return "未申请";
                }else{
                    return value;
                }
            }
            },
            {field:"customerType",title:"客户类型", width:80,formatter:function(value,param){
                if(value == "5"){
                    return "一期未完成";
                }
                if(value == "4"){
                    return "本组担保人";
                }
                if(value == "3" ){
                    return "借款人共借人";
                }
                if(value == "1" ||value == "9" ){
                    return "已存借款人";
                }
                if(value == "6"){
                    if(param.credentialsType =='1'){
                        return "已存借款人";
                    }   else{
                        return "已存担保人";
                    }
                }
                if(value == "0"){
                    return "已存担保人";
                }else{
                    return "没有角色";
                }
            }},
            {field:"blackFlag",title:"是否加入黑名单", width:90,formatter:function(value){
                if(value == 'Y'){
                    return '是';
                }else {
                    return '否';
                }
            }},
            {field:"flag",title:"是否可用", width:90,formatter:function(value,data,index){
                //step0检查一期未完成用户
                //step1检查借款人黑名单信息
                //step2检查借款人信息
                //step3检查借款人配偶信息
                //step4检查借款人担保信息
                if(data.customerType == "5"){
                    $("#mateValidateMsgFlag").val("false");
                    $("#mateValidateFalseMsg").val("此人存在借贷关系且状态为一期未完成，不能使用.....");
                    return "不可用";
                }
                //step1检查借款人黑名单信息
                if(data.customerType == "9"){
                    $("#mateValidateMsgFlag").val("false");
                    $("#mateValidateFalseMsg").val("此人在黑名单列表中，不能使用.....");
                    return "不可用";
                }
                //step2检查借款人信息
                if(data.customerType == "4"){
                    $("#mateValidateMsgFlag").val("false");
                    $("#mateValidateFalseMsg").val("此人为同组担保人，不能使用.....");
                    return "不可用";
                }
                //检查借款人及配偶信息
                if(data.customerType == "1" || data.customerType == "3" ){
                    if($.trim(value) == "18"
                            ||$.trim(value) == "16"
                            ||$.trim(value) == "20"
                            ||$.trim(value) == "26"
                            ||($.trim(value) == "27" && data && data.specialRefuseFlag != "sayYes")
                            ||$.trim(value) == "28"){
                        //正常的
                        return "可以使用";
                    }else{
                        $("#mateValidateMsgFlag").val("false");
                        if(data.customerType == "1" ){
                            $("#mateValidateFalseMsg").val("此人已借款且状态为'"+data.auditStatusShow+"'，不能使用.....");
                        } else{
                            $("#mateValidateFalseMsg").val("此人为其他借款人共借人，且借贷状态为'"+data.auditStatusShow+"'，不能使用.....");
                        }
                        return "不可用";
                    }
                }
                //step4检查借款人担保信息
                if(data.customerType == "0"){
                    if( $.trim(value) == "16"
                        ||$.trim(value) == "20"
                        ||$.trim(value) == "18"
                        ||$.trim(value) == "26"
                        ||($.trim(value) == "27")
                        ||$.trim(value) == "28"){
                        //正常的
                        return "可以使用";
                    }else{
                        $("#mateValidateMsgFlag").val("false");
                        $("#mateValidateFalseMsg").val("此人正在为其他借款人做担保，且状态为"+data.auditStatusShow+"，不能使用.....");
                        return "不可用";
                    }
                }
                //step5 检查此人直系亲属是否在做借款和担保不符合标准
                if(data.customerType == "6"){
                    $("#mateValidateMsgFlag").val("false");
                    $("#mateValidateFalseMsg").val("此人直系亲属在借款期或者担保期，不能使用.....");
                    return "不可用";
                }
                //step5 检查此人直系亲属是否在做借款和担保不符合标准
                if(data.customerType == "6"){
                    $("#borrowerValidateMsgFlag").val("false");
                    $("#borrowerValidateFalseMsg").val("此人直系亲属在借款期或者担保期，不能使用.....");
                    return "不可用";
                }
                return "可以使用";
            }
            }
        ]],
        onLoadSuccess:function(data){
            $("#validateBorrowerIndentity").linkbutton("enable");
            $("#validateMateIndentity").linkbutton("enable");
            //判断是否是首次进入及未输入身份证号
            var identityNumber = $.trim($("#identityForCoBorrower").val());
            if(identityNumber == null || ""==identityNumber ) {
                return;  //首次进入直接返回及未输入身份证号
            }
            if(data.total == 0 && $.trim($("#identityForCoBorrower").val()).length == 18){
                //console.info(data+"------1-----");
                //未在系统中做过贷款信息
                $("#mateIdNumber").val($.trim($("#identityForCoBorrower").val()));
                $("#mateFlag").val("true");
                //  $("#borrowerValidateRSinfo").val("借款人可以借款.....");
                $("#mateValidateFalseMsg").val("该人在系统中未存在借贷信息，可以使用...");
                $("#mateValidateMsgFlag").val("true");
                $("#mateValidateRSinfo").val("该人在系统中未存在借贷信息，可以使用...");
                showGoToCreditApplicationButton();
                return;
            }
//                        console.info(data+"------2-----");
            setMateValidateInfo();
            showGoToCreditApplicationButton();
        }

    });
}
//设置借款人配偶验证结果  共借人
function setMateValidateInfo(){
    var  mateValidateMsgFlag   =    $.trim( $("#mateValidateMsgFlag").val());
    var  mateValidateFalseMsg =     $.trim($("#mateValidateFalseMsg").val());
    //判断此记录是不是不符合的借款人
    if(mateValidateMsgFlag=="false"){  //是不符合标准的借款人
        $("#mateValidateRSinfo").val(mateValidateFalseMsg);
    }else{
        $("#mateIdNumber").val($.trim($("#identityForCoBorrower").val()));
        $("#mateFlag").val("true");
        $("#mateValidateRSinfo").val("此人符合标准，可以使用");
    }
}
//根据借款人及配偶验证结果
//显示添加按钮

function showGoToCreditApplicationButton(){
    //借款人及借款人配偶同为通过时显示添加按钮
    var borrowerFlag =   $.trim($("#borrowerFlag").val());
    var mateFlag = $.trim($("#mateFlag").val());
    if(borrowerFlag=="true" && mateFlag == "true"){
           $("#goToCreditApplication").show();
    }
}

