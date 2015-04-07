
$(function(){
	var dic = new DataDictionary();
	/*dic.addDic("visitWay_Y","visit_way");*/
	dic.addDic("newDebt_Y","new_debt");

/*	dic.addDic("loanUse_Y","yesOrNo");*/
	//dic.addDic("familyIncome_Y","yesOrNo");
	/*dic.addDic("income_Y","yesOrNo");*/
	/*dic.addDic("spending_Y","yesOrNo");*/
	/*dic.addDic("isPurposeConsistency_Y","yesOrNo");*/
/*	dic.addDic("contactWay_Y","yesOrNo");*/
	dic.addDic("attitudeForRepayment_Y","att_for_repayment");
	dic.addDic("attitudeForCutomermanager_Y","att_for_cutomermanager");
	dic.addDic("sourceIncomeChangedType_Y","sourceIncomeChangedType");
	/*dic.addDic("highDangered_Y","yesOrNo");*/
	dic.dicAjax();
	$("#higDangerCustomer").hide();
	flag1 = false;
	flag2 = false;
	flag3 = false;
	$("#customerReturnVisitForm").form("validate");
	flag1 = true;
	flag2 = true;
	flag3 = true;
	var creditapplicationid = "${customerReturnVisitVo.businessNumber }";
	if(creditapplicationid ==undefined ||creditapplicationid == null || creditapplicationid == ""){
		$("#commitButton").linkbutton("disable");
		alert("还没有生成回访计划");
	};

	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"industryCategoryTotal",q:""},function(result){
		industryCategoryAll = result;
	});
	/*
	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"cashIncomeFamilyTotal",q:""},function(result){
		cashIncomeFamilyTotal = result;
	});*/
	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"highDangerCustomer",q:""},function(result){
		highDangerCustomer = result;
	});
	ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"sourceIncomeChangedContentTotal",q:""},function(result){
		sourceIncomeChangedContentTotal = result;
	});

	////新家庭收入来源：
	//$("#familyIncome").combobox({width:"119",
	//	onSelect:function(date){
	//		if(date.codeKey =='0'){
	//			flag2 = true;
	//			$("#newFamilyIncome").validatebox({required:true});
	//			$("#newFamilyIncome").validatebox("validate");
	//		}else{
	//			flag2 = false;
	//			$("#newFamilyIncome").val("");
	//			$("#newFamilyIncomeReal").val("");
	//			$("#newFamilyIncome").validatebox({required:false});
	//			$("#newFamilyIncome").validatebox("validate");
	//		}
	//	}});
	////新经营收入来源：
	//$("#income").combobox({width:"119",
	//	onSelect:function(date){
	//		if(date.codeKey =='0'){
	//			flag3 = true;
	//			$("#newIncome").validatebox({required:true});
	//			$("#newIncome").validatebox("validate");
	//		}else{
	//			flag3 = false;
	//			$("#newIncome").val("");
	//			$("#newIncomeReal").val("");
	//			$("#newIncome").validatebox({required:false});
	//			$("#newIncome").validatebox("validate");
	//		}
	//	}});



	//债权债务金额：
	$("#newDebt").combobox({width:"119",
		onSelect:function(date){
			if(date.codeVlue =='请选择' || date.codeVlue =='无' ){
				$("#newDebtMoney").numberbox("setValue","");
				$("#newDebtMoney").attr("readonly","readonly");
				$("#newDebtMoney").numberbox({required:false,disabled:true});
				$("#newDebtMoney").numberbox("validate");
			}else{
				$("#newDebtMoney").attr("readonly",false);
				$("#newDebtMoney").numberbox({required:true,disabled:false});
				$("#newDebtMoney").numberbox("validate");
			}
		}});




	$("#minutes").combobox({data:[{id:"00",text:"00"},{id:"15",text:"15"},{id:"30",text:"30"},{id:"45",text:"45"}],
		valueField:"id",
		textField:"text",
		height:"auto",
		editable:false,
		width:50});

	console.info("serverName:"+serverName+",creditapplicationId:"+creditapplicationId);

	$.ajax({
		url:serverName+'/PresentDateController/returnDateListSelectChange.do?creditapplicationId='+creditapplicationId,
		datatype:"json",
		success:function(data){
			$("#visitDate").combobox({
				data:data.list,
				width:119,
				required:true,
				editable:false,
				panelHeight:"auto",
				value:data.todayNow,
				valueField:"dateString",
				textField:"dateString",
				onSelect:function(value){
					if(data.flag){   //如果今天是还款日
						if(value.dateString == data.lastRepayDate){
							$("#repaymentDate").val($("#repaymentDateHide").val()); //给还款日期赋值
							$("#customerReturnVisitId").val($("#customerReturnVisitIdPresent").val());		//给回访id 赋值
						}else{
							$("#repaymentDate").val(data.lastRepayDate);
							$("#customerReturnVisitId").val(data.lastcustomerReturnVisitId);		//给回访id 赋值
						}
					}
				}
			});
		},
		error:function(){

		}
	});

	//上传文件
	var url1 = serverName + "/creditapplication2Approval/getDate.do";
	ajaxSubmit(url1, function (result) {
		if (result) {
			var signTime = result[0];
			var signIp = result[1];
			var href = cmUrl + "/jsp/creditease/operation/operationControl.jsp?clientId=" + clientid + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&userID=" + userId + "&type=3&signTime=" + signTime + "&signIp=" + signIp + "";
			$('#openCM')[0].src = href;
		} else {
			$.messager.alert("提示信息", "服务异常，请稍后重试");
		}
	})

	/*//回访方式初始化
	$("input[name='visitWay']").on("change",
		function(e){
			console.info($(e.target).val());
		}
	);
*/

	//新借款用途初始化
	$("input[name='loanUse']").on("change",
		function(e){

			if($(e.target).val() == '0'){//是
				flag1 = true;
				//$("#newLoanUse").attr("readonly","");*/

				$("#newLoanUse").bind("focus",function(){
					console.info("new focus");
					showborrowUse('industryCategory','newLoanUse');
				});
				$("#newLoanUse").validatebox({required:true});
				$("#newLoanUse").validatebox("validate");
			}else{
				flag1 = false;
				$("#newLoanUse").val("");
				$("#industryCategory").val("");
				$("#newLoanUse").validatebox({
					required:false
				});
				$("#newLoanUse").validatebox("validate");
			}
		}
	);
	//大项开支：
	$("input[name='spending']").on("change",
		function(e){

			if($(e.target).val() == '0'){
				$("#newSpending").attr("readonly",false);
				$("#newSpending").validatebox({required:true});
				$("#newSpending").validatebox("validate");
			}else{
				$("#newSpending").val("");
				$("#newSpending").attr("readonly","readonly");
				$("#newSpending").validatebox({required:false});
				$("#newSpending").validatebox("validate");
			}
		}
	);
	//是否高危标记
	$("input[name='highDangered']").on("change",
		function(e){
			if($(e.target).val() == '0'){
				console.info("---是高危标记客户--");
				selectHighDangerCustomer("higDangerCustomer","highDangerReason");
			}else{
				console.info("--不是高危标记客户---");
				$("#higDangerCustomer").val("");
				$("#highDangerReason").val("");
				$("#higDangerCustomer").hide();
			}
		}
	);
	function doIncomChangedPro(newValue){
		//2	减少
		//1	不变
		//0	新增
		if((newValue == 0) ||(newValue == 2)){
			console.info("---是新增或者减少--");
			$("#sourceIncomeChangedContent").val("");
			$("#sourceIncomeChangedContent_ipt").val("");
			selectSourceIncomeChangedContent('sourceIncomeChangedContent_ipt','sourceIncomeChangedContent');
			$("#sourceIncomeChangedContent_div").show();
			console.info("变更后的值为：sourceIncomeChangedContent"+$("#sourceIncomeChangedContent").val()+
			",sourceIncomeChangedContent_ipt:"+$("#sourceIncomeChangedContent_ipt").val());
		}else{
			console.info("--不是新增或者减少----");
			$("#sourceIncomeChangedContent").val("");
			$("#sourceIncomeChangedContent_ipt").val("");
			$("#sourceIncomeChangedContent_div").hide();
			console.info("值为：sourceIncomeChangedContent"+$("#sourceIncomeChangedContent").val()+
			",sourceIncomeChangedContent_ipt:"+$("#sourceIncomeChangedContent_ipt").val());

		}
	}
	$("#sourceIncomeChangedType").combobox({width:"119",
		onSelect:function(record){
			var newValue = record.codeKey;
			console.info("recorrd--newValue-"+newValue);
			doIncomChangedPro(newValue);
		},
		onChange:function(newValue,oldValue){
			console.info("-----------newValue:"+newValue);
			//2	减少
			//1	不变
			//0	新增
			doIncomChangedPro(newValue);

		}});


	//借款用途是否一致：
	$("input[name='isPurposeConsistency']").on("change",
		function(e){
			if($(e.target).val() == '0'){
				$("#reasonNotConsistencyDiv").hide();
				$("#reasonNotConsistency").val("");
				$("#reasonNotConsistency").attr("readonly","readonly");
				$("#reasonNotConsistency").validatebox({required:false});
				$("#reasonNotConsistency").validatebox("validate");
			}else{
				$("#reasonNotConsistencyDiv").show();
				$("#reasonNotConsistency").attr("readonly",false);
				$("#reasonNotConsistency").validatebox({required:true});
				$("#reasonNotConsistency").validatebox("validate");
			}
		}
	);

	//回访照片是否齐全：
	$("input[name='isComplete']").on("change",
		function(e){
			if($(e.target).val() == '0'){
				$("#reasonForIncompleteDiv").hide();
				$("#reasonForIncomplete").val("");
				$("#reasonForIncomplete").attr("readonly","readonly");
				$("#reasonForIncomplete").validatebox({required:false});
				$("#reasonForIncomplete").validatebox("validate");
			}else{
				$("#reasonForIncompleteDiv").show();
				$("#reasonForIncomplete").attr("readonly",false);
				$("#reasonForIncomplete").validatebox({required:true});
				$("#reasonForIncomplete").validatebox("validate");
			}
		}
	);



	//新联系方式：
	$("#contractChangeType").combotree({width:"119",
		url:serverName+'/customer/getCustomerInfoListByCreditApplicationID.do?creditapplicationId='+creditapplicationId,
		editable: false,
		multiple:true,
		separator:",",
		/*onCheck:function(){
			var phoneValid = $("#phoneValid").val();
			console.info("-----------phoneValid:"+phoneValid);
			if(phoneValid && phoneValid == 'false'){
				console.info("phoneValid:"+phoneValid);
				event.stopPropagation();
				return false;
			}
		},*/
		onChange:function(newValue, oldValue){
			var changeBorrowerPhone_tmp = $("#changeBorrowerPhone_tmp").val();
			var changeCoborrowerPhone_tmp = $("#changeCoborrowerPhone_tmp").val();
			var changeGuaranteeFirstPhone_tmp = $("#changeGuaranteeFirstPhone_tmp").val();
			var changeGuaranteeSecondPhone_tmp = $("#changeGuaranteeSecondPhone_tmp").val();
/*			console.info("-----------------------changeBorrowerPhone_tmp:"+changeBorrowerPhone_tmp);
			console.info("-----------------------changeCoborrowerPhone_tmp:"+changeCoborrowerPhone_tmp);
			console.info("-----------------------changeGuaranteeFirstPhone_tmp:"+changeGuaranteeFirstPhone_tmp);
			console.info("-----------------------changeGuaranteeSecondPhone_tmp:"+changeGuaranteeSecondPhone_tmp);*/
			var nodes  = $("#contractChangeType").combotree("tree").tree('getChecked');
			var contractChangeType_div = document.getElementById("contractChangeType_div");
			if(newValue == null || "" == newValue){
				$("#contractChangeType_div").html("");
				$("#contractChangeType_div").hide();
			}else{
				var divStr='';
				var j=0;
				var arrs = [];
				for(var i=0;i<nodes.length;i++){
					var node = nodes[i];
					j++;
					if(j == 3){
						divStr+="<br>";
					}
					if(node.id == '借款人'){
						divStr+='&nbsp; &nbsp;'+node.id+'('+node.text+'):&nbsp;<input maxlength="11" id="changeBorrowerPhone" name="changeBorrowerPhone" class="easyui-validatebox" required="true" value="'+changeBorrowerPhone_tmp+'"/> &nbsp; &nbsp;';
						arrs.push("changeBorrowerPhone");
					}
					if(node.id == '共借人'){
						divStr+='&nbsp; &nbsp;'+node.id+'('+node.text+'):&nbsp;<input maxlength="11" id="changeCoborrowerPhone" name="changeCoborrowerPhone" class="easyui-validatebox"  required="true" value="'+changeCoborrowerPhone_tmp+'"/> &nbsp; &nbsp;';
						arrs.push("changeCoborrowerPhone");
					}
					if(node.id == '担保人1'){
						divStr+='&nbsp; &nbsp;担保人'+'('+node.text+'):&nbsp;<input maxlength="11" id="changeGuaranteeFirstPhone" name="changeGuaranteeFirstPhone" class="easyui-validatebox"  required="true" value="'+changeGuaranteeFirstPhone_tmp+'"/> &nbsp; &nbsp;';
						arrs.push("changeGuaranteeFirstPhone");
					}
					if(node.id == '担保人2'){
						divStr+='&nbsp; &nbsp;担保人'+'('+node.text+'):&nbsp;<input maxlength="11" id="changeGuaranteeSecondPhone" name="changeGuaranteeSecondPhone" class="easyui-validatebox"  required="true" value="'+changeGuaranteeSecondPhone_tmp+'"/> &nbsp; &nbsp;';
						arrs.push("changeGuaranteeSecondPhone");
					}
				}

				$("#contractChangeType_div").html(divStr);
				$("#contractChangeType_div").show();
				console.info("validate count11"+$("#contractChangeType_div input").size());
				console.info("arrs------------"+arrs);
				for(var j=0;j<arrs.length;j++){
					$("#"+arrs[j]).attr("readonly",false);
					$("#"+arrs[j]).validatebox({required:true});

					$("#"+arrs[j]).bind("blur",function(event){
						console.info("blur------------"+event);
						var phoneNumber = $(this).val();
						var tmp_id = '#'+this.id+'_tmp';
						if(phoneNumber && validPhone(phoneNumber)){
							console.info("this.id:-----------------"+this.id);
							$(tmp_id).val(phoneNumber);
						}else{

							$(this).focus();
							$("#phoneValid").val("false");
							$(tmp_id).val(phoneNumber);
							return false;
						}
					});
					$("#"+arrs[j]).validatebox("validate");
				}
			}

		},
		onSelect:function(date){
			/*var auditStatus =$("#auditStatus").combobox("getValues");
			if(date.codeKey =='0'){
				$("#newContactWay").attr("readonly",false);
				$("#newContactWay").validatebox({required:true});
				$("#newContactWay").validatebox("validate");
			}else{
				$("#newContactWay").val("");
				$("#newContactWay").attr("readonly","readonly");
				$("#newContactWay").validatebox({required:false});
				$("#newContactWay").validatebox("validate");
			}*/
		}});
	$("#sourceIncomeChangedContent_div").hide();
	//$("#newLoanUse_div").attr("disabled","disabled");
});
