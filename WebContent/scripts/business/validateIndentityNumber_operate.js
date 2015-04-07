//验证借款人身份
function validateBorrowerIndentityInfo(){
    $("#invalIdCoBorrowerIdNumberDiv").html("");
    //console.info("-----------div--------");
    //清空并隐藏身份证无效div
    $("#invalIdBorrowerIdNumberDiv").html("").hide();
    //获取身份证号
    var identityNumber =  $.trim($("#identityForBorrower").val());
    if($("#identityForBorrower").validatebox("validate")){
        if(!validNumberAll(identityNumber)){    //无效的身份证号
            $("#goToCreditApplication").hide();
            $("#borrowerFlag").val("false");
            $("#invalIdBorrowerIdNumberDiv").html("<font color='red'>您输入的身份证不存在，请输入正确的身份证。</font>").show();
            return;
        }
        //获取配偶身份证验证信息   及身份证号码
        var   mateFlag =     $.trim($("#mateFlag").val());
        var   mateIdNumber = $.trim($("#mateIdNumber").val());
        //对于配偶验证成功         判断输入的身份证是否为同一个
        if(mateFlag =="true" && mateIdNumber ==identityNumber ) {
            $("#goToCreditApplication").hide();
            $("#borrowerFlag").val("false");
            $("#invalIdBorrowerIdNumberDiv").html("<font color='red'>你输入的身份证号码不能和共借人的一样！请重新输入您的身份证！</font>").show();
            return;
        }
        //清空身份证验证结果div
        $("#borrowerValidateRSinfo").val("");
        //设置借款人验证通过标致为初始值         未通过
        $("#borrowerFlag").val("false");
        //设置添加按钮为隐藏
        $("#goToCreditApplication").hide();
        //设置借款人循环验证失败标识为初始值         验证通过
        //验证失败信息为fasle
        $("#borrowerValidateFalseMsg").val("false");
        $("#borrowerValidateMsgFlag").val("true");
      //console.info("-----------div1--------"+identityNumber);
        $("#validateBorrowerIndentity").linkbutton("disable");
        $("#validateMateIndentity").linkbutton("disable");
       // console.info("identityNumber-------------"+identityNumber);
        $('#borrowerList').datagrid('load',{identityNumber:identityNumber});
    }

}

//验证共借人身份
 function validateMateIndentityInfo(){
     $("#invalIdBorrowerIdNumberDiv").html("");
    // console.info("-------comein-----------");
     //清空并隐藏身份证无效div
     $("#invalIdCoBorrowerIdNumberDiv").html("").hide();
     //获取身份证号
     var identityNumber =  $.trim($("#identityForCoBorrower").val());
     if($("#identityForCoBorrower").validatebox("validate")){
         if(!validNumberAll(identityNumber)){    //无效的身份证号
             $("#goToCreditApplication").hide();
             $("#mateFlag").val("false");
             $("#invalIdCoBorrowerIdNumberDiv").html("<font color='red'>您输入的身份证不存在，请输入正确的身份证。</font>").show();
             return;
         }
         //获取借款人身份证验证信息   及身份证号码
         var   borrowerFlag =     $.trim($("#borrowerFlag").val());
         var   borrowerIdNumber = $.trim($("#borrowerIdNumber").val());
       //  console.info("borrowerFlag------"+borrowerFlag);
       //  console.info("borrowerIdNumber--------"+borrowerIdNumber) ;

         //对配偶验证成功         判断输入的身份证是否为同一个
         if(borrowerFlag =="true" && borrowerIdNumber ==identityNumber ) {
             $("#goToCreditApplication").hide();
             $("#mateFlag").val("false");
             $("#invalIdCoBorrowerIdNumberDiv").html("<font color='red'>你输入的身份证号码不能和借款人的一样！请重新输入您的身份证！</font>").show();
             return;
         }
         //清空身份证验证结果div
         $("#mateValidateRSinfo").val("");
         //设置借款人验证通过标致为初始值         未通过
         $("#mateFlag").val("false");
         //设置添加按钮为隐藏
         $("#goToCreditApplication").hide();
         //设置借款人循环验证失败标识为初始值         验证通过
         //验证失败信息为fasle
         $("#mateValidateFalseMsg").val("false");
         $("#mateValidateMsgFlag").val("true");
         $("#validateBorrowerIndentity").linkbutton("disable");
         $("#validateMateIndentity").linkbutton("disable");
         $('#mateList').datagrid('load',{identityNumber:identityNumber});
     }
 }

 //跳转到信贷申请
function goToCreditApplicationFun(){
    var   borrowerIdNumber = $.trim($("#borrowerIdNumber").val());
    var   mateIdNumber = $.trim($("#mateIdNumber").val());
    console.info("borrowerIdNumber---------"+borrowerIdNumber+" mateIdNumber----------"+mateIdNumber);
    var urlStr = serverName+"/customer/goToCreditApplication.do?customerConsultId="+customerConsultId +"&consultPoolId="+consultPoolId+"&borrowerIdNumber="+borrowerIdNumber+"&mateIdNumber="+mateIdNumber;
   // console.info("----------url---------"+urlStr);
	//此处需要把前面传过来的
//	var centerj = window.parent;
//	centerj.addTabFun({
//		src : urlStr,
//		title : "新增申请"
//	});
    var grd =  window.parent.parent;
    var prt =  window.parent;
    prt.$("#validateIndentityNumberDialog").dialog("close");
    grd.addTabFun({
        src: urlStr,
        title : "新增申请"
    });
   // console.info("跳转到另外的页面");
}

