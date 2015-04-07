//加载产品类型
function newPageHourse(borrowerServiceAppId){
		borrowerServiceAppFormSelect(borrowerServiceAppId); 
		borrowInfosFormSelect(borrowerServiceAppId);
		creditCardInfoFormSelect(borrowerServiceAppId);
		jobContactSelect(borrowerServiceAppId);
		
	var dic1 = new DataDictionary();
	dic1.addDic("consulteSource","consulteSource");
	dic1.addDic("detailsLoansuseType","detailsLoansuseType");
	dic1.addDic("gender","gender");
    dic1.dicAjax();
}


		// 入户点查表一 表单提交
		// 保存返回 回调
		function saveOrUpdateBack(result){
			if(result>0 ){
				window.location = serverName+"/creditgroup/selectCreditApplication.do?creditapplicationId="+$("#creditapplicationIdForeign").val();
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
				$("#borrowSerivceList").datagrid('reload');
				$.messager.show({
					msg:'删除成功！',
					title:'消息'
				});
			}else{
			}
		}

		// 入户点查表一 表单提交
		// 保存下一步 回调
		function saveOrNextBack(result){
			if(result>0 ){
				$("#borrowerServiceAppId").val(result);
				var borrowerServiceAppId = $("#borrowerServiceAppId").val();
				var creditApplicationId= $("#creditapplicationIdForeign").val();
				window.location = serverName
				+ "/borrowerServiceApp/showApplicationUpdate2.do?borrowerServiceAppId="
				+ borrowerServiceAppId+"&creditApplicationId="+creditApplicationId;
			} 
		}
		
		// 显示小组地址
		function showGroupAddressHouse(){
			 
					addressString = $("#prefecture").combobox("getText")
							+ "-"+ $("#town").combobox("getText")+ "-"
							+ $("#village").combobox("getText");
					// 设置所在村镇的值
					if($("#groupNum").val() != null && $("#groupNum").val() != undefined && $("#groupNum").val()!= ""){
						showBorrowerServiceAddressHouse();
					}else{
						$("#residenceAddress").val(addressString);
					}
					
		}
		
		// 显示借款服务申请地址
		function showBorrowerServiceAddressHouse(){
					addressString = $("#countyId1").combobox("getText")
							+ "-"+ $("#town1").combobox("getText")+ "-"
							+ $("#village1").combobox("getText")+ "-"
							+ $("#groupNum").val();
					// 设置所在村镇的值
					$("#residenceAddress").val(addressString);
		}
		
		// 计算贷款额度合计
		function sumLoanChargeTotal(){
			var useCredit1= $("[name='borrowInfos[0].useCredit']").val();
			var useCredit2= $("[name='borrowInfos[1].useCredit']").val();
			var useCredit3= $("[name='borrowInfos[2].useCredit']").val();
			var sum = 0;
			sum = accAdd(accAdd(useCredit1,useCredit2),useCredit3);
				$("#loanChargeTotal").val(sum);
		}
		// 计算信用卡负债合计
		function sumCreditCardChargeTotal(){
			var creditUsedAmount1 = $("[name='creditOrganization[0].creditUsedAmount']").val();
			var creditUsedAmount2 = $("[name='creditOrganization[1].creditUsedAmount']").val();
			var sum = 0 ;
			sum = accAdd(creditUsedAmount1,creditUsedAmount2);
				$("#creditCardChargeTotal").val(sum);
		}
		
		// 保存借款申请（日期）
		function borrowerServiceAppFormSubmit(){
					 url=serverName+"/borrowerServiceApp/addOrUpdate.do";
					ajaxSubmit(url,$("#borrowerServiceAppForm").serializeObject(),borrowerServiceAppFormSubmitBack);
		}
		
		// 保存借款申请 回调
		function borrowerServiceAppFormSubmitBack(result){
		}
		
		// 借款服务申请 查询
		function borrowerServiceAppFormSelect(borrowerServiceAppId){
			url=serverName+"/borrowerServiceApp/select.do";
			ajaxSubmit(url,{borrowerServiceAppId:borrowerServiceAppId},borrowerServiceAppFormSelectBack);
		}
		
		
		// 借款服务申请 显示
		function borrowerServiceAppFormSelectBack(result){
			if(result != null ){
				$("#borrowerServiceAppForm").form("load",result);
			}	
		}
		
		// 保存借款情况 查询
		function borrowInfosFormSelect(borrowerServiceAppId){
			url=serverName+"/borrowInfo/select.do";
			ajaxSubmit(url,{borrowerServiceAppId:borrowerServiceAppId},borrowInfosFormSubmitBack);
		}
		
		// 保存借款情况 表单提交 回调
		function borrowInfosFormSubmitBack(result){
			if(result != null){
				$("#borrowInfosForm").form("clear");
				$("#borrowInfosForm").form("load",result);
			}
		}
	
		// 保存信用卡 机构信息 查询
		function creditCardInfoFormSelect(borrowerServiceAppId){
			url = serverName+"/creditCardInfo/select.do";
			ajaxSubmit(url,{borrowerServiceAppId:borrowerServiceAppId},creditCardInfoFormSubmitBack);
		}
		// 保存信用卡 机构信息 回调
		function creditCardInfoFormSubmitBack(result){
			if(result != null){
				$("#creditCardInfoForm").form("clear");
				if(result.creditCardInfo != null && result.creditCardInfo != ""){
					if(result.creditCardInfo.creditBorrowConditionTime != null && ""!= result.creditCardInfo.creditBorrowConditionTime ){
						};
						$("#creditCardInfoForm").form("load",result);
				}
				
				sumLoanChargeTotal();
				sumCreditCardChargeTotal();
			}
		}
		
		
		//回调函数
		
		function  jobContactCallBack(result){
			($("#jobContact").form("clear"));
			($("#jobContact").form("load",result));
		};

		function jobContactSelect(borrowerServiceAppId){
			url = serverName+"/surveyFirst/jobContentSelect.do";
			ajaxSubmit(url,{borrowerServiceAppId:borrowerServiceAppId},jobContactCallBack);
		}
		function showgovarment(){
			$("#govarment").attr("style","");
			$("#govarment1").attr("style","display: none;");
		}
		
		function hiddengovarment(){
			$("#govarment1").attr("style","");
			$("#govarment").attr("style","display: none;");
		}
