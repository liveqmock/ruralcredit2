<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
var serverName="<%=path%>";
	$(function(){
		
		$("#tabs").tabs({
			tools:[{
				iconCls:'icon-reload',
				text:"清空当前页",
				handler:function(){
					clearPage();
				}
			},{
				iconCls:'icon-save',
				text:"保存&下一步",
				handler:function(){
					openjiekuan();
				}
			}],
			buttons:"#toolbar"
		});
	});
	//清空
	function clearPage(){
		var tabs = $("#tabs").tabs("getSelected");
		tabs.children("form").form("clear");
	}
	//保存当前页表单
	function openjiekuan(){
		//$("#tabs").tabs("select","个人资料");
		var tab = $("#tabs").tabs("getSelected");
		if(tab.children("form").form("validate")){
			if(tab.children("form").attr("id") == "borrowerServiceAppForm"){
					if($("[name='borrowerServiceApp.livingCommercial']")[0].checked == false &&
							$("[name='borrowerServiceApp.livingSelf']")[0].checked == false&&
							$("[name='borrowerServiceApp.livingRent']")[0].checked ==false &&
							$("[name='borrowerServiceApp.livingRelative']")[0].checked ==false &&
							$("[name='borrowerServiceApp.livingOther']")[0].checked ==false)
					 {
							 $.messager.alert("消息","请至少选择一项 居住状况！");
							 return false;
					 }else{
						tab.children(":button").get(0).click();
						var index = $("#tabs").tabs("getTabIndex",tab);
						//如果到了最后一页
						$("#tabs").tabs("select",index+1);
					 }
			}else{
				tab.children(":button").get(0).click();
				//如果是最后一个标签
				var index = $("#tabs").tabs("getTabIndex",tab);
				if(index == 3){
					//验证前边的数据
					saveAll();
				}
				$("#tabs").tabs("select",index+1);
			}
				
		}else{
			$.messager.alert("消息","请填写完整数据！");
		}		
	}
	
</script>
 	<div id="tabs" class="easyui-tabs" fit="true">
        <div title="个人资料" style="padding:10px;" >
        <input id="submitButtom" value="提交" type="button" style="visibility: hidden;"
        hidden="true" onclick="borrowerServiceAppFormSubmit();">  
        	<form id="borrowerServiceAppForm">
         		<table align="center">
						<tr>
							<td align="right">
							申&nbsp; 请 &nbsp;日&nbsp;期：
							</td>
					<td align="left">
						<input class="easyui-datebox" style="width: 150px;"
							required="true" id="applyDate" name="borrowerServiceApp.applyDate" 
							editable="fasle"/>
					</td>
					<td width="40"></td>
							<td align="right" width="110">
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 名：
                                <input id="leader" name="borrowerServiceApp.leader"
									type="hidden"  value="${borrowerServiceApp.leader }"/>
								<input id="firstFlag" name="borrowerServiceApp.firstFlag" 
									type="hidden"  value="${borrowerServiceApp.firstFlag }"/>
								<input id="borrowerServiceAppId" name="borrowerServiceApp.borrowerServiceAppId"
									type="hidden"  value="1574"/>
                                <input id="cuntomerBasicId" name = "customerBasicId" 
                                	type="hidden" value="${borrowerServiceApp.customerBasicId}"/>
                                <input  id="creditapplicationIdForeign"   name="borrowerServiceApp.creditapplicationId" 
									type="hidden" value="${creditApplication.creditapplicationId}"/>
							</td>
							<td width="168"  align="left">
								<input name="borrowerServiceApp.name" 
								id="name12" 
								type="text" 
								onBlur="validIDNumber();" 
								value="${borrowerServiceApp.name}"
								style="width: 150px;"  
								class="easyui-validatebox" 
								required="true" />
							</td>
							<td>
							</td>
							 
						<td width="150" align="right">
								身份证号码：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"  validType="idnumber" value="${borrowerServiceApp.credentialsNumber}"
									 name="borrowerServiceApp.credentialsNumber" id="credentialsNumber1" style="width: 150px;" onblur="validIDNumber();" />
							</td>
						 
						</tr>
                        	
                        <tr>
                      	  <td align="right">
								还&nbsp; 款&nbsp; 来&nbsp; 源：
							</td>
							<td align="left">
								<input name="borrowerServiceApp.paymentSource" 
								class="easyui-validatebox"
								required="true" style="width: 150px;"/>
							</td>
							<td></td>
                        	<td align="right">
								曾&nbsp;用&nbsp;名：
							</td>
							<td align="left">
								<input name="borrowerServiceApp.former" validType="length[0,32]" value="${borrowerServiceApp.former}"
									class="easyui-validatebox" type="text" style="width: 150px;" />
							</td>
							<td>
							</td>
                            <td align="right">
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：
							</td>
							<td width="168" align="left">
								<input name="borrowerServiceApp.age" id="age" 	validType="number"  value="${borrowerServiceApp.age }" style="width: 150px;"
								  type="text" readonly="readonly"/>
							</td>
                        </tr>
                        <tr>
                        	<td align="right">
								申请借款额度：
							</td>
							<td align="left">
							<input id="applyLimit" required="true" name="borrowerServiceApp.applyLimit" style="width: 150px;"  class="easyui-numberbox" />
							</td> 
							<td></td>
                        	<td  align="right" width="80">
								联系电话：
							</td>
							<td  align="left">
								<input name="borrowerServiceApp.mobilephone" style="width: 150px;" value="${borrowerServiceApp.mobilephone}"
										class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage
	    				="输入11位手机号或加区号的固话号码"/>
							</td>
							<td>
							</td>
                            	<td align="right" >
								性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
							</td>
							<td align="left">
								<input name="borrowerServiceApp.gender" id="gender"  value="${borrowerServiceApp.gender}" style="width: 150px;"/>
							</td>
                        </tr>
                        <tr>
                       		 <td align="right">
								借&nbsp; 款&nbsp; 用&nbsp; 途：
							</td>
							<td align="left">
								<input id="detailsLoansuseType" required="true" style="width: 150px;" name="borrowerServiceApp.detailsLoansuseType" />
							</td>
							<td></td>
                        	<td align="right">选择地址：</td>
							<td><input name="borrowerServiceApp.countyId"  class="easyui-combobox" id="countyId1" style="width: 150px;" required="true" editable="false"/>
							</td>
							<td>
							</td>
                            <td align="right">
								居住面积(M2)：
							</td>
							<td  align="left">
								<input name="borrowerServiceApp.livingArea" type="text"
									class="easyui-validatebox" validType="number" 
									style="width: 150px" required="true"/>
							</td>
                        </tr>
                        
						<tr>
							<td align="right">
								详细借款用途：
							</td>
							<td align="left">
							<input id="detailUse" style="width: 150px;" name="borrowerServiceApp.detailsLoansUse"/>
							</td> 
							<td></td>
							<td></td>
					<td>
						<input name="borrowerServiceApp.townId" class="easyui-combobox" id="town1"
							style="width: 150px;" required="true" editable="false" />
					</td>
					<td></td>
							<td align="right" valign="top">
								居住状况：
							</td>
							<td colspan="5" align="left">
								<input name="borrowerServiceApp.livingCommercial"
									id="livingCommercial" type="checkbox" value="0" />
								自有商品房
							</td>
						</tr>
						<tr>
							<td align="right">
								咨&nbsp; 询&nbsp; 来&nbsp; 源：
							</td>
							<td align="left">
								<input name="borrowerServiceApp.consulteSource" required="true" style="width: 150px;"  id="consulteSource"/>
							</td>
							<td></td>
							<td></td>
					<td>
						<input name="borrowerServiceApp.villageId" class="easyui-combobox" id="village1"
							style="width: 150px;" required="true" editable="false" />
					</td>
					<td></td>
							<td></td>
							<td><input name="borrowerServiceApp.livingSelf" id="livingSelf"
									type="checkbox" value="1" />
								
								自有宅基地</td>
						</tr>
						<tr>
							<td align="right">
								详细咨询来源：
							</td>
							<td align="left">
								<input name="borrowerServiceApp.detailsConsulteSource" style="width: 150px;" id="detailsConsulteSource"/>
							</td>
							<td></td>
							<td></td>
							<td>
							<input type="text" id="groupNum" style="width: 150px;" onblur="showBorrowerServiceAddressHouse();"/> 
							</td>
							<td></td>
							<td></td>
							<td><input name="borrowerServiceApp.livingRent" id="livingRent"
									type="checkbox" value="2" onclick="showLivigDate(this);" />
								租住（到期日：
								<span id="rentD">
								</span>）</td>
						</tr>
						<tr>
							
							<td align="right" >
								家庭年收入（元）：
							</td>
							<td align="left">
							<input 	name="borrowerServiceApp.familyIncome" 
							id="income"
									type="text"
									class="easyui-numberbox" 
									validType="number"
									required="true"
									precision="2",
									decimalSeparator=".",
									style="width: 150px;"
									/>
							</td>
							<td></td>
							<td align="right">编辑地址：</td>
							<td rowspan="2">
								<textarea  name="borrowerServiceApp.residenceAddress" style="resize:none;width: 150px;"
									 id="residenceAddress" value="${borrowerServiceApp.residenceAddress}" rows="2" /></textarea>
							</td>
							<td></td>
							<td></td>
							<td>
								<input name="borrowerServiceApp.livingRelative" 
									id="livingRelative" type="checkbox" value="3" />
								亲属住房
							</td>
						
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><input name="borrowerServiceApp.livingOther" id="livingOther"
									type="checkbox" value="4" />
								其他</td>
						</tr>
					 
					</table>
				</form>		
        </div>  
        <div title="借款情况" style="padding:10px" align="center"> 
        <input type="button" hidden="true" value="提交"  style="visibility: hidden;"  onclick="borrowInfosFormSubmit();" > 
        <form id="borrowInfosForm">
					<table style="border-bottom-style: dashed;border-bottom-width: 0.5px;border-bottom-color: green;" >
						<tr>
							<td></td>
							<td width="120px">
								贷款机构
							</td>
							<td width="120px">
								发放日期
							</td>
							<td width="120px">
								到期日期
							</td>
							<td width="120px">
								授信额度
							</td>
							<td width="120px">
								使用额度
							</td>
					</tr>
					<tr>
							<td>1、</td>
							<td>
								<input name="borrowInfos[0].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[0].releaseDate" type="text" type="text"
									class="easyui-datebox" style="width: 100%" editable="false" />
							</td>
							<td>
								<input name="borrowInfos[0].expirationDate" type="text"
									type="text" class="easyui-datebox" style="width: 100%"
									editable="false" />
							</td>
							<td>
								<input name="borrowInfos[0].creditLine" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[0].useCredit" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
						</tr>
						<tr>
							<td>2、</td>
							<td>
								<input name="borrowInfos[1].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[1].releaseDate" type="text"
									class="easyui-datebox" style="width: 100%" editable="false" />
							</td>
							<td>
								<input name="borrowInfos[1].expirationDate" type="text"
									class="easyui-datebox" style="width: 100%" editable="false" />
							</td>
							<td>
								<input name="borrowInfos[1].creditLine" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[1].useCredit" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
					</tr>
					<tr>
							<td>3、</td>
							<td>

								<input name="borrowInfos[2].lender" type="text"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[2].releaseDate" type="text" type="text"
									editable="false" class="easyui-datebox" style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[2].expirationDate" type="text"
									type="text" editable="false" class="easyui-datebox"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[2].creditLine" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[2].useCredit" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" onblur="sumLoanChargeTotal();" />
							</td>
					</tr>
					</table>
					<table>
					<tr>
							<td width="5%"></td>
							<td width="180px">
								账户状态
							</td>
							<td width="230px">
								还款方式
							</td>
							<td width="100px">
								年利率%
							</td>
							<td width="90px">
								每次还款金额
							</td>
							<td width="20px">

							</td>
						</tr>
						
						<tr>	
							<td width="5%">1、</td>
							<td>
								<input name="borrowInfos[0].amountStatus" type="radio" value="0" />
								正常
								<input name="borrowInfos[0].amountStatus" type="radio" value="1" />
								结清
								<input name="borrowInfos[0].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input name="borrowInfos[0].repayWay" type="radio" value="0" />
								按季还息
								<input name="borrowInfos[0].repayWay" type="radio" value="1" />
								利随本清
								<input name="borrowInfos[0].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input name="borrowInfos[0].interestRate" type="text"
									class="easyui-numberbox" precision="2" max=100
									invalidMessage="请输入0~100之间的数值" validType="numberNonNegative"
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[0].eachRepayAmount" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td width="20px">
								<input name="borrowInfos[0].borrowInfoId" type="hidden" />
								<input id="borrowerServiceAppId0"
									name="borrowInfos[0].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						
						<tr>	
							<td>2、</td>	
							<td>
								<input name="borrowInfos[1].amountStatus" type="radio" value="0" />
								正常
								<input name="borrowInfos[1].amountStatus" type="radio" value="1" />
								结清
								<input name="borrowInfos[1].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input name="borrowInfos[1].repayWay" type="radio" value="0" />
								按季还息
								<input name="borrowInfos[1].repayWay" type="radio" value="1" />
								利随本清
								<input name="borrowInfos[1].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input name="borrowInfos[1].interestRate" type="text"
									class="easyui-numberbox" precision="2" max=100
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[1].eachRepayAmount" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td width="20px">
								<input name="borrowInfos[1].borrowInfoId" type="hidden" />
								<input id="borrowerServiceAppId1"
									name="borrowInfos[1].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						
						<tr>	
							<td width="5%">3、</td>	
							<td>
								<input name="borrowInfos[2].amountStatus" type="radio" value="0" />
								正常
								<input name="borrowInfos[2].amountStatus" type="radio" value="1" />
								结清
								<input name="borrowInfos[2].amountStatus" type="radio" value="2" />
								逾期
							</td>
							<td>
								<input name="borrowInfos[2].repayWay" type="radio" value="0" />
								按季还息
								<input name="borrowInfos[2].repayWay" type="radio" value="1" />
								利随本清
								<input name="borrowInfos[2].repayWay" type="radio" value="2" />
								其他
							</td>
							<td>
								<input name="borrowInfos[2].interestRate" type="text"
									class="easyui-numberbox" precision="2" max=100
									style="width: 100%" />
							</td>
							<td>
								<input name="borrowInfos[2].eachRepayAmount" type="text"
									class="easyui-numberbox" precision="2" validType="number"
									style="width: 100%" />
							</td>
							<td width="20px">
								<input name="borrowInfos[2].borrowInfoId" type="hidden" />
								<input id="borrowerServiceAppId2"
									name="borrowInfos[2].borrowerServiceAppId"
									value="${borrowerServiceApp.borrowerServiceAppId }"
									type="hidden" />
							</td>
						</tr>
						<tr style="height: 20px;">
							<td>
							</td>
						</tr>
					</table>
				</form>
        </div>  
        <div title="信用卡" style="padding:10px" align="center">
          <input type="button" hidden="true" value="提交"  style="visibility: hidden;" onclick="creditCardInfoFormSubmit();">
      	  <form id="creditCardInfoForm">
        	<table >
									<tr>
										<td align="left">
											<font color="black" style="font-weight: bold;">信用卡使用情况:	
											</font> 
											<input name="creditCardInfo.creditCardInfoId" type="hidden"/>
										</td>
										<td>
											<table>
												<tr>
													<td width="171">
														<input type="radio" name="creditCardInfo.creditCondition"
														value="0" onclick="hiddengovarment();"/>
															无 
													</td>
													<td width="176">
														<input type="radio" name="creditCardInfo.creditCondition"
														value="1" onclick="showgovarment();"/>
														有，未透支 
													</td>
													<td width="179">
																<input type="radio" name="creditCardInfo.creditCondition"
																value="2" onclick="showgovarment();"/>
															有，透支
													</td>
													<td width="225">
													</td>
												</tr>
											</table>
										</td>
											 
									</tr>
									<tr>
										<td></td>
										<td>
											<table id="govarment"  style="display: none;" >
												<tr>
													<td width="181">
														发卡机构名称
													</td>
													<td width="176">
														开户日期
													</td>
													<td width="179">
														信用额度（元）
													</td>
													<td width="225">
														已使用额度（元）
													</td>
			
			
												</tr>
												<tr>
													<td>
														<input name="creditOrganization[0].creditCardIssue"
															type="text" style="width: 150px;" class="eastui-validatebox" validType="length[0,32]"/>
														<input name="creditOrganization[0].creditOrgId" type="hidden"/>
													</td>
													<td>
														<input name="creditOrganization[0].openingDate" type="text"
															 editable="false" class="easyui-datebox" style="width: 150px;" />
													</td>
													<td>
														<input name="creditOrganization[0].creditLimit" 
																 class="easyui-numberbox"  precision="2" min="0"
															style="width: 150px;" />
													</td>
			
													<td>
														<input name="creditOrganization[0].creditUsedAmount"  onblur="sumCreditCardChargeTotal();"
															 class="easyui-numberbox"  precision="2" min="0"
															style="width: 150px;"/>
													</td>
													 
			
												</tr>
												<tr>
													<td>
			                                        <input name="creditOrganization[1].creditOrgId"  type="hidden"/>
														<input name="creditOrganization[1].creditCardIssue"
															type="text" style="width: 150px;"  class="eastui-validatebox" validType="length[0,32]"/>
													</td>
													<td>
														<input name="creditOrganization[1].openingDate" type="text"
															 editable="false" class="easyui-datebox" style="width: 150px;" />
													</td>
													<td>
														<input name="creditOrganization[1].creditLimit"  
														 class="easyui-numberbox"  precision="2" min="0"
															style="width: 150px;" />
													</td>
													<td>
														<input name="creditOrganization[1].creditUsedAmount" onblur="sumCreditCardChargeTotal();" 
															 class="easyui-numberbox"  precision="2" min="0"
															style="width: 150px;"  />
													</td>
													 
												</tr>
										
											</table>
											<table id="govarment1"  >
												<tr>
													<td width="181">
														发卡机构名称
													</td>
													<td width="176">
														开户日期
													</td>
													<td width="179">
														信用额度（元）
													</td>
													<td width="225">
														已使用额度（元）
													</td>
			
			
												</tr>
												<tr>
													<td>
														<input  readonly="readonly"  
															type="text" style="width: 150px;background-color: 
															#eeeeee;" class="eastui-validatebox" validType="length[0,32]"/>
													</td>
													<td>
														<input  readonly="readonly" type="text"
															 editable="false" class="easyui-datebox" style="width: 150px;background-color: #eeeeee;" />
													</td>
													<td>
														<input  readonly="readonly" type="text"
															class="easyui-validatebox" validType="number"
															style="width: 150px;background-color: #eeeeee;" />
													</td>
			
													<td>
														<input  readonly="readonly"   onblur="sumCreditCardChargeTotal();"
															type="text" class="easyui-validatebox" validType="number"
															style="width: 150px;background-color: #eeeeee;"/>
													</td>
													 
			
												</tr>
												<tr>
													<td>
														<input  readonly="readonly"  
															type="text" style="width: 150px;background-color: #eeeeee;"  class="eastui-validatebox" validType="length[0,32]"/>
													</td>
													<td>
														<input  readonly="readonly" type="text"
															 editable="false" class="easyui-datebox" style="width: 150px;background-color: #eeeeee;" />
													</td>
													<td>
														<input  readonly="readonly"  type="text"
															class="easyui-validatebox" validType="number"
															style="width: 150px;background-color: #eeeeee;" />
													</td>
													<td>
														<input  readonly="readonly" onblur="sumCreditCardChargeTotal();" 
															type="text" class="easyui-validatebox" validType="number"
															style="width: 150px;background-color: #eeeeee;"  />
													</td>
													 
												</tr>
										
											</table>
										</td>
									</tr>
									
								
						<tr>
							<td align="left">
								<font color="black" style="font-weight: bold;">宜信借款情况：</font> 
							</td>
							<td>
								<table>
									<tr>
										<td width="181">	
								<input type="radio" onclick="validateTime();" value="0"
									name="creditCardInfo.creditBorrowCondition"/>
								从未在宜信申请借款 
							</td>
							<td width="176">	
								<input type="radio" onclick="validateTime();" value="1"
									name="creditCardInfo.creditBorrowCondition"/>
								申请过未通过审核 
							</td>
							<td width="179">	
								<input type="radio" onclick="validateTime();" value="2"
									name="creditCardInfo.creditBorrowCondition"/>
								已在宜信借款
								<span id="timesBorrow"> </span> 
								<input id="creditCardInfoborrowerServiceAppId"  name="creditCardInfo.borrowerServiceAppId" 
									  value="${borrowerServiceApp.borrowerServiceAppId }"  type="hidden" />	
							</td>
							<td width="225">
							</td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
									<td valign="top">
										<font color="black" style="font-weight: bold;">信贷情况负债情况合计：</font> 
										</td>
								 		<td>
								 			<table>
								 				<tr>
								 					
													<td width="181" align="left">
														贷款负债合计（元）：
													</td>
			                                        	<td align="left" width="176">
														信用卡负债合计（元）：
													</td>
			                                        	<td align="left"  width="179">
														为他人贷款担保合计（元）：
													</td>
								 				</tr>
								 				<tr>
								 					<td>
													<input type="text"   id="loanChargeTotal"
														style="width: 150px;" readonly="readonly"/>
														 
													</td>
													<td>
														<input id="creditCardChargeTotal" type="text"  
															style="width: 150px;" readonly="readonly"/>
														
													</td>
													<td>
														<input name="creditCardInfo.loanGuaranteesOther"  
															 class="easyui-numberbox"  precision="2" min="0"
															style="width: 150px;" />
													
													</td>
													<td width="225">
													</td>
								 				</tr>
								 			</table>
								 		</td>
                                  </tr>
							
					</table>
			</form>
        </div><%--
        <div title="工作" style="padding: 10px;"> 
        	<table   align="center" style="padding: 20px;">
						<tr>
							<td>
								<input name="jobInfo.jobInfoId" type="hidden" />
							</td>
							<td width="100"></td>
							<td>
								<input name="jobInfo.borrowerServiceAppId" id="jobInfoborrowerServiceAppId" type="hidden"/>
							</td>
						</tr>
						<tr>
							<td>
							工作单位及地址：
							</td>
							<td width="100"></td>
								<td >
								<input name="jobInfo.workunitAddress" type="text"
									style="width:150px;"  class="eastui-validatebox" validType="length[0,32]"/>
							</td>
						</tr>
						<tr>	
							<td >
								担任职务：
							</td>
							<td width="100"></td>
							<td >
								<input name="jobInfo.post" type="text" style="width: 150px;" 
										 class="eastui-validatebox" validType="length[0,32]"/>
							</td>
						</tr>	
						<tr>
						
							<td >
								单位电话：
							</td>
							<td width="100"></td>
							<td >
								<input name="jobInfo.workunitTelephone" type="text"
									style="width: 150px;" class="easyui-validatebox" validType="numberZero"/>
							</td>
						</tr>
						<tr>	
							<td >
								进入单位时间：
							</td>
							<td width="100"></td>
							<td >
								<input name="jobInfo.enterTime" class="easyui-datebox"
									id="jobInfo.enterTime" style="width: 150px;" editable="false"/>
							</td>
						</tr>
						<tr>		
							<td >
								所在部门：
							</td>
							<td width="100"></td>
							<td>
								<input name="jobInfo.department" type="text" style="width: 150px;" 
										 class="eastui-validatebox" validType="length[0,32]"/>
							</td>
						</tr>
					</table>
						
        </div>
        --%><div title="工作&联系人" style="padding: 10px;">
        <input type="button" hidden="true" value="提交 "  style="visibility: hidden;" onclick="jobContactSubmit();">
        <form id="jobContact">
        	 <table width="98%" style="border-bottom-style: dashed;border-bottom-width: 0.5px;border-bottom-color: green;" >
        	 <tr>
        	 	<td valign="top">
        	 		<font color="black" style="font-weight: bold;">工作:</font> 
        	 	</td>
          		<td>
          			<table>
          				<tr>
          					<td width="100">
							工作单位及地址：
							</td>
							
							<td width="100">
								单位电话：
							</td>
							
							<td width="100">
								担任职务：
							</td>
							<td width="100">
								进入单位时间：
							</td>
							<td width="100">
								所在部门：
							</td>
          				</tr>
          				<tr>
							<td >
								<input name="jobInfo.jobInfoId" type="hidden" />
								<input name="jobInfo.borrowerServiceAppId" id="jobInfoborrowerServiceAppId" type="hidden"/>
								<input name="jobInfo.workunitAddress" type="text"
									style="width:130px;"  class="eastui-validatebox" validType="length[0,32]"/>
							</td>
							<td >
								<input name="jobInfo.workunitTelephone" type="text"
									style="width: 130px;"class="easyui-numberbox" invalidMessage="请输入11位电话号码"
								  				 validType="length[11,11]"/>
							</td>
						 
							<td >
								<input name="jobInfo.post" type="text" style="width: 130px;" 
										 class="eastui-validatebox" validType="length[0,32]"/>
							</td>
							<td >
								<input name="jobInfo.enterTime" class="easyui-datebox"
									id="jobInfo.enterTime" style="width: 130px;" editable="false"/>
							</td>
					 	
							<td>
								<input name="jobInfo.department" type="text" style="width: 130px;" 
										 class="eastui-validatebox" validType="length[0,32]"/>
							</td>
						</tr>
          			</table>
          		</td>	
        	 </tr>
		</table>
        <table width="98%">
        				<tr>
        					<td valign="top">
        					<font color="black" style="font-weight: bold;">联系人:</font>
        					</td>
        					<td>
        						<table>
        							<tr >
										<td width="100px">
											姓名
										</td>
										<td >
											与本人关系
										</td>
										<td>
											工作单位
										</td>
										<td>
											家庭或工作单位详细地址
										</td>
										<td>
											职  务
										</td>
										<td >
											联系电话
										</td>
										<td></td>
									</tr>
									<tr>
										<td colspan="6" align="left">
											<font color="black" style="font-weight: bold;"> 工作证明人：</font> 
											<input id="contactType0" name="contacts[0].contactType" type="hidden" value="1">
										</td>
									</tr>
			
									<tr>
										<td>
											<input name="contacts[0].name" type="text" style="width: 130px;" 
													 class="easyui-valibox" validType="length[0,32]"/>
										</td>
										<td >
											<input name="contacts[0].borrowerreRationship" type="text"
												style="width: 130px;"  id="contacts0BorrowerreRationship"
												editable="false"/>
										</td>
										<td>
											<input name="contacts[0].workUnit" type="text"
												style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[0].addressfamilyOrWorkunit" type="text"
												style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[0].post" type="text" style="width: 130px;" />
										</td>
										<td >
											<input name="contacts[0].telphone" type="text"
												style="width: 130px;" class="easyui-numberbox" invalidMessage="请输入11位电话号码"
								  				 validType="length[11,11]"/>
										</td>
										<td>
											<input name="contacts[0].contactId" type="hidden"/>
											<input name="contacts[0].borrowerServiceAppId" id="contacts0borrowerServiceAppId" type="hidden"/>
										</td>
									</tr>
									<tr>
										<td colspan="6" align="left">
											<font color="black" style="font-weight: bold;">家庭联系人:</font> 
											（配偶）
											<input id="contactType1" name="contacts[1].contactType" type="hidden" value="2">
										</td>
									</tr>
									<tr>
										<td>
											<input name="contacts[1].name" type="text" style="width: 130px;" 
												class="easyui-validatebox" required="true" validType="length[0,32]"/>
										</td>
										<td >
											<input name="contacts[1].borrowerreRationship" type="text"
												style="width: 130px;" id="contacts1BorrowerreRationship"
												editable="false"/>
										</td>
										<td >
											<input name="contacts[1].workUnit" type="text"
												style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[1].addressfamilyOrWorkunit" type="text"
												style="width: 130px;"/>
										</td>
										<td>
											<input name="contacts[1].post" type="text" style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[1].telphone" type="text" style="width: 130px;"
											class="easyui-numberbox" invalidMessage="请输入11位电话号码"
											  required="true" validType="length[11,11]"/>
										</td>
										
										<td>
											<input name="contacts[1].contactId" type="hidden"/>
											<input name="contacts[1].borrowerServiceAppId" id="contacts1borrowerServiceAppId" type="hidden"/>
										</td>
										
									</tr>
									<tr>
										<td colspan="6" align="left">
											<font color="black" style="font-weight: bold;">紧急联系人:</font> 
											（邻居、非配偶亲属、或其他有效联系人均可）
											<input id="contactType2" name="contacts[2].contactType" type="hidden" value="3">
										</td>
									</tr>
									<tr>
										<td>
											<input name="contacts[2].name" type="text" style="width: 130px;"
													 class="easyui-validatebox" validType="length[0,32]"/>
										</td>
										<td >
											<input name="contacts[2].borrowerreRationship" type="text"
												style="width: 130px;"  id="contacts2BorrowerreRationship"
												editable="false"/>
										</td>
										<td >
											<input name="contacts[2].workUnit" type="text"
												style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[2].addressfamilyOrWorkunit" type="text"
												style="width: 130px;" />
										</td>
										<td>
											<input name="contacts[2].post" type="text" style="width: 130px;" />
										</td>
										<td >
											<input name="contacts[2].telphone" type="text"
												style="width: 130px;" class="easyui-numberbox" 
												invalidMessage="请输入11位电话号码"
								 				validType="length[11,11]"/>
										</td>
										<td>
											<input name="contacts[2].contactId" type="hidden"/>
											<input name="contacts[2].borrowerServiceAppId" id="contacts2borrowerServiceAppId" type="hidden" />
										</td>
									</tr>
        						</table>
        					</td>
        				</tr>
			</table>
			</form>
        </div>
    </div>
