<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
		<script type="text/javascript" >
			var serverName="<%=path%>";
		</script>
		<script type="text/javascript" >
			function loadSuc(){
				var dic = new DataDictionary();
				dic.addDic("auditStatusForm_auditStatus_Y","auditStatus");
				dic.addDic("auditStatusM_Y","auditStatusM");
				dic.dicAjax();
				//日期控件显示十分秒
				
				//显示tab内容
				$("#allTabs").tabs({
					fit:true,
					onSelect:function(title){
						if(title=="申请单状态修改"){
							//showDanbao();
						}else if(title=="审批历史修改"){
							//showExamineHistory();
						}else if(title=="额度确认修改"){
							//showDanbao();
						}else if(title=="放款登记修改"){
							//showDanbao();
						}
					}
				});
			}
			//根据业务单号更新rl_credit_application表中的AUDIT_STATUS字段
			function updateAuditStatus(){
				
				var formName = "modifyCreditAppAuditStatusFrom";
				if(document.getElementById('auditBusinessNumber').value==""){
					alert("业务单号不能为空!");
					return ;
				}
				if(!($("#modifyCreditAppAuditStatusFrom").form('validate'))){
					alert("请选择一个有效的业务状态!");
					return ;
				}
				$.messager.confirm("消息", "确定保存数据？", function(result) {
					if(result){
						var formId = "#"+formName;
						ajaxSubmit(serverName+"/systemInfo/updateAuditStatus.do",$(formId).serialize(),function(result){
							if(result>0){
								alert("更新成功");
							}else if(result==0){
								alert("更新失败");
							}
						});
					}
				});
			}
			
			//根据业务单号查询审批历史
			function showApproveHistoryList(){
				
				var businessNum = document.getElementById('approveBusinessNumber').value;
				$("#approveHistory_list").datagrid({
					url : serverName + "/systemInfo/searchApproveHistoryList.do",
					rownumbers : true,
					idfield : "applyaudittableId",
					queryParams:{businessNum:businessNum},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{
							field : "applyaudittableId",
							title : "额度确认ID",
							width: 100
						},{
							field : "creditapplicationId",
							title : "申请单ID",
							width: 100
						},{
							title : "操作",
							field : "no",
							width: 125,
							formatter:function(value){
								return "<a href='javascript:setToHistory();'>置为历史</a>";
							}
						}
				   ] ]
				});
			}
			
			//将审批置为历史数据
			function setToHistory(){
				var rows = $("#approveHistory_list").datagrid("getSelected");
				var applyaudittableId = rows.applyaudittableId;
				alert(applyaudittableId);
				$.messager.confirm("消息", "确定将审批ID为:"+applyaudittableId+"的数据置为历史?", function(result) {
					if(result){
						ajaxSubmit(serverName+"/systemInfo/setApproveToHistoryData.do",{applyaudittableId:applyaudittableId},function(result){
							if(result.success){
								alert("更新成功!");
								showApproveHistoryList();
							}else{
								alert("更新失败!");
								showApproveHistoryList();
							}
						});
					}
				});
			}
			
			//根据业务单号查询额度确认
			function showAmountConfirmList(){
				
				var businessNum = document.getElementById('amountConfirmBusinessNumber').value;
				$("#amountConfirm_list").datagrid({
					url : serverName + "/systemInfo/searchAmountConfirm.do",
					rownumbers : true,
					idfield : "amountConfirmId",
					queryParams:{businessNum:businessNum},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{
							field : "amountConfirmId",
							title : "额度确认ID",
							width: 100
						},{
							field : "creditapplicationId",
							title : "申请单ID",
							width: 100
						},{
							field : "historyFlag",
							title : "状态",
							width: 100
						},{
							field : "amount",
							title : "确认金额",
							width: 100
						},{
							field : "loanTime",
							title : "确认放款时间",
							width: 100
						},{
							field : "createTime",
							title : "创建时间",
							width: 100
						},{
							title : "操作",
							field : "no",
							width: 125,
							formatter:function(value){
								return "<a href='javascript:setAmountConfirmToHis();'>置为历史</a>";
							}
						}
				   ] ]
				});
			}
			
			//将制定的额度确认条目置为历史数据
			function setAmountConfirmToHis(){
				var rows = $("#amountConfirm_list").datagrid("getSelected");
				var amountConfirmId = rows.amountConfirmId;
				if(rows.historyFlag=="1"){
					alert("该条数据已经是历史数据,不需要更新!");
					return ;
				}
				$.messager.confirm("消息", "确定将额度确认ID为:"+amountConfirmId+"的数据置为历史?", function(result) {
					if(result){
						ajaxSubmit(serverName+"/systemInfo/setAmountConfirmToHis.do",{amountConfirmId:amountConfirmId},function(result){
							if(result.success){
								alert("更新成功!");
								showAmountConfirmList();
							}else{
								alert("更新失败!");
								showAmountConfirmList();
							}
						});
					}
				});
			}
			//显示放款登记列表
			function showReleaseMoneyList(){
				var businessNum = document.getElementById('releaseMoneyBusinessNumber').value;
				$("#releaseMoney_list").datagrid({
					url : serverName + "/systemInfo/searchReleaseMoney.do",
					rownumbers : true,
					idfield : "groupLoanRegistId",
					queryParams:{businessNum:businessNum},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{
							field : "groupLoanRegistId",
							title : "放款登记ID",
							width: 100
						},{
							field : "creditapplicationId",
							title : "申请单ID",
							width: 100
						},{
							field : "registStatus",
							title : "状态",
							width: 100
						},{
							field : "loanTime",
							title : "确认放款时间",
							width: 100
						},{
							title : "操作",
							field : "no",
							width: 125,
							formatter:function(value){
								return "<a href='javascript:showModifyReleaseMoneyDilog();'>修改状态</a>";
							}
						}
				   ] ]
				});
			}
			//弹出修改放款登记状态对话框
			function showModifyReleaseMoneyDilog(){
				var rows = $("#releaseMoney_list").datagrid("getSelected");
				var groupLoanRegistId = rows.groupLoanRegistId;
				 $("#modifyRegisterStatusDialog").dialog({
		            title:"修改放款登记状态",
		            close:true,
		            top:120,
		            height:100,
		            width:400,
		            modal:true,
		            draggable:true
		       	 });
				 $("#modifyGroupLoanRegistId").val(groupLoanRegistId);
				 $("#modifyRegisterStatusDialog").dialog("open");
			}
			//修改放款登记状态
			function modifyRegisterStatus(){
				var formId = "#registerStatusForm"; 
				$.messager.confirm("消息", "确定修改选定的条目的状态?", function(result) {
					if(result){
						ajaxSubmit(serverName+"/systemInfo/modifyGroupLoanRegistStatus.do",$(formId).serialize(),function(result){
							if(result.success){
								$("#modifyRegisterStatusDialog").dialog("close");
								alert("更新成功!");
								showReleaseMoneyList();
							}else{
								$("#modifyRegisterStatusDialog").dialog("close");
								alert("更新失败!");
								showReleaseMoneyList();
							}
						});
					}
				});
			}
			
			//查询付款预约
			function showFinanceMoney(){
				var bizId = $("#bizId").val();
				$("#FinanceMoney_list").datagrid({
					url : serverName + "/financeMoneyController/selectPaymentFinanceMoneyList.do",
					rownumbers : true,
					idfield : "financeMoneyId",
					queryParams:{bizId:bizId},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{ 	field : "financeMoneyId", title : "财务预约记录表ID", width: 100
						},{ field : "type", title : " 财务类型(F:付款;S:收款;U:撤销)", width: 100
						},{ field : "financeStatus", title : " 财务状态(0:未预约;1:已预约;2:成功;3:失败;4:撤销;5:作废)", width: 100
						},{ field : "operatorName", title : "操作人姓名", width: 100
						},{ field : "operateDate", title : "操作日期", width: 100
						},{ field : "historyFlag", title : "历史记录标志(T:历史记录;F:当前记录)", width: 100
						},{ field : "remark", title : "remark", width: 100
						},{ field : "bizId", title : "订单号", width: 100
						},{ field : "returnMsg", title : "返回信息", width: 100
						}
				   ] ]
				});
			}
			
			//修改财务预约
			function updateFinanceMoney(){
				ajaxSubmit(serverName + "/financeMoneyController/updateFinanceMoneyByBizId.do",$("#FinanceMoney_form").serialize(),function(message){
					$.messager.alert("消息",message.msg);
				});
			}
			//借款申请列表
			 function showborrowerList(){
				var borrowerServiceAppId = document.getElementById('borrowerServiceAppId').value;
				var customerBasicId = document.getElementById('customerBasicId').value;
				var credentialsNumber = document.getElementById('credentialsNumber').value;
				$("#borrower_list").datagrid({
					url : serverName + "/systemInfo/selectBorrowerServiceApp.do",
					rownumbers : true,
					idfield : "BORROWER_SERVICE_APP_ID",
					queryParams:{customerBasicId:customerBasicId,credentialsNumber:credentialsNumber,borrowerServiceAppId:borrowerServiceAppId},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{ field : "BORROWER_SERVICE_APP_ID", title : "ID", width: 100
						},{ field : "CUSTOMER_BASIC_ID", title : "客户基本信息id", width: 100
						},{ field : "NAME", title : "姓名", width: 100
						},{ field : "CREDENTIALS_NUMBER", title : "身份证号", width: 150
						},{ field : "LEADER", title : "借款人标记（1-借款人）", width: 100
						},{ field : "VALID_STATE", title : "有效标记（1-有效）", width: 100
						}
				   ] ]
				});
			}
			//客户基本信息
			function showCustomerList(){
				var customerBasicId = document.getElementById('customerBasicIdC').value;
				var credentialsNumber = document.getElementById('credentialsNumberC').value;
				$("#customer_list").datagrid({
					url : serverName + "/customer/list.do",
					rownumbers : true,
					idfield : "customerBasicId",
					pagination : true,
					queryParams:{customerBasicId:customerBasicId,credentialsNumber:credentialsNumber},
					height:300,
					width:750,
					singleSelect : true,
					columns : 
					[ [  
						{ field : "customerBasicId", title : "客户基本信息id", width: 100
						}, { title : "借款人姓名", field : "name", width : 130 
						}, { title : "身份证号", field : "credentialsNumber", width : 200 
						},{ title : "是借款人(0是)", field : "customerType", width : 100 
						},{ title : "是担保人 (0是)", field : "guaranor", width : 100 
						},{ title : "操作", field : "no", width: 125, formatter:function(value,rowData){
								return "<a href='javascript:deleteCustomer();'>删除</a>";
							}
						}
				   ] ]
				});
			}
			
			function deleteCustomer(){
				var rowData = $("#customer_list").datagrid("getSelected");
				ajaxSubmit(serverName + "/systemInfo/deleteCustomerBasicInfo.do",{customerBasicInfoId:rowData.customerBasicId},function(message){
					$.messager.alert("消息",message.msg);
					if(message.success){
						 $("#customer_list").datagrid("reload");
					}
				});
			}
			
			function updateborrowerServiceApp(){
				ajaxSubmit(serverName + "/systemInfo/updateborrowerServiceApp.do",$("#borrowerServiceAppForm").serialize(),function(message){
					$.messager.alert("消息",message.msg);
				});
			}
			
			function updateCustomerList(){
				ajaxSubmit(serverName + "/systemInfo/updateCustomerList.do",$("#CustomerForm").serialize(),function(message){
					$.messager.alert("消息",message.msg);
				});
			}
			function updateStatusByReceiveCreditMsg(){
			//将提交按钮置灰
			$("#updateStatusByReceiveCreditMsgSumbit").linkbutton("disable");
			if(document.getElementById('businessNumberByApplyId').value==""){
					alert("业务单号不能为空!");
					//放开按钮
					$("#updateStatusByReceiveCreditMsgSumbit").linkbutton("enable");
					return ;
				}
				if(!($("#receiveCreditObjForm").form('validate'))){
					alert("请选择一个有效的业务状态!");
					//放开按钮
					$("#updateStatusByReceiveCreditMsgSumbit").linkbutton("enable");
					return ;
				}
				$.messager.confirm("消息", "确定提交数据？", function(result){
				if(result){
					ajaxSubmit(serverName + "/systemInfo/updateStatusByReceiveCreditMsg.do",$("#receiveCreditObjForm").serialize(),function(message){
					$.messager.alert("消息",message.msg);
					//放开按钮
					$("#updateStatusByReceiveCreditMsgSumbit").linkbutton("enable");
				});
				}
				});
				$("#updateStatusByReceiveCreditMsgSumbit").linkbutton("enable");
			}
            // 根据业务编号查询借款人身份证号 担保人身份证号 共借人身份证号 信息
            function getIdInfoByBusinessNumber(){
                //将提交按钮置灰
               var businessNumber =    document.getElementById('businessNumberForIDChange').value;
                if(businessNumber==""){
                    alert("业务单号不能为空!");
                    return ;
                }
                ajaxSubmit(serverName+"/application/existThisBussinessNumber.do",{businessNumber:businessNumber},function(res){
                     console.info("-res---------"+res);
                      if("false" == $.trim(res)){
                          $.messager.alert("消息","请输入正确的业务单号，系统中无此比业务！");
                          return;
                      }else{
                          $('#idChangeFrame')[0].src =  serverName+"/application/queryIDInfoVo.do?businessNumber="+businessNumber;
                          $("#idChangeDiv").dialog({
                              buttons : [ {
                                  id: "addSave",
                                  text : "保存",
                                  iconCls : "icon-save",
                                  handler : function() {
                                      var idChangeFrame = window.frames["idChangeFrame"];
                                      if (idChangeFrame.window) {
                                          idChangeFrame.window.submitFun();
                                      } else {
                                          idChangeFrame.contentWindow.submitFun();
                                      }
                                  }
                              } , {
                                  text : "关闭",
                                  iconCls : "icon-cancel",
                                  handler : function() {
                                      $("#idChangeDiv").dialog('close');
                                  }
                              }
                              ]  ,
                              onLoad : function() {
                                  console.info("------init-------------");
                              }
                          });
                          $("#idChangeDiv").dialog('open');
                      }}
                );
            }
            // 根据业务编号查询借款人身份证号 担保人身份证号 共借人身份证号 信息
            function getIdInfoByBusinessNumber(){
                //将提交按钮置灰
               var businessNumber =    document.getElementById('businessNumberForIDChange').value;
                if(businessNumber==""){
                    alert("业务单号不能为空!");
                    return ;
                }
                ajaxSubmit(serverName+"/application/existThisBussinessNumber.do",{businessNumber:businessNumber},function(res){
                     console.info("-res---------"+res);
                      if("false" == $.trim(res)){
                          $.messager.alert("消息","请输入正确的业务单号，系统中无此比业务！");
                          return;
                      }else{
                          $('#idChangeFrame')[0].src =  serverName+"/application/queryIDInfoVo.do?businessNumber="+businessNumber;
                          $("#idChangeDiv").dialog({
                              buttons : [ {
                                  id: "addSave",
                                  text : "保存",
                                  iconCls : "icon-save",
                                  handler : function() {
                                      var idChangeFrame = window.frames["idChangeFrame"];
                                      if (idChangeFrame.window) {
                                          idChangeFrame.window.submitFun();
                                      } else {
                                          idChangeFrame.contentWindow.submitFun();
                                      }
                                  }
                              } , {
                                  text : "关闭",
                                  iconCls : "icon-cancel",
                                  handler : function() {
                                      $("#idChangeDiv").dialog('close');
                                  }
                              }
                              ]  ,
                              onLoad : function() {
                                  console.info("------init-------------");
                              }
                          });
                          $("#idChangeDiv").dialog('open');
                      }}
                );
            }
        </script>
        <style type="text/css">
        	.modifySystemDataDiv {
				letter-spacing: 1px;
			}
			
			.modifySystemDataDiv table {
				border-collapse: collapse;
			}
			
			.modifySystemDataDiv td {
				border: 1px solid #CCC;
			}
        </style>
  </head>
  
  <body id="cc" class="easyui-layout" fit="true" onload="loadSuc();">
		<div region="center">
			<div id="allTabs" style="padding: 10px;" plain="true">
				<div title="申请单状态修改" style="padding: 10px; margin-left:250px;">
  					<form id="modifyCreditAppAuditStatusFrom" >
  						<table>
  							<tr>
  								<td colspan="4" style="text-align:center;font-size:19px;">
  									申请单状态修改<br />
  								</td>
  							</tr>
  							<tr>
  								<td colspan="4" style="height:20px;">
  								</td>
  							</tr>
  							<tr>
  								<td>
  									业务单号：
  								</td>
  								<td>
  									<input type="text" id="auditBusinessNumber" name="businessNumber" style="width:150px;" />
  								</td>
  								<td>
  									&nbsp;&nbsp;业务状态：
  								</td>
  								<td>
  									<input name="auditStatus" style="width: 150px;" id="auditStatusForm" required="true" />
  								</td>
  								<td>
  									&nbsp;&nbsp;<input type="button" onclick="updateAuditStatus();" value="提 交" />
  								</td>
  							</tr>
  						</table>
  					</form>
  				</div>
  				<div title="审批历史修改" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="4" style="text-align:center;font-size:19px;">
								审批历史修改<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td>
								业务单号：
							</td>
							<td>
								<input type="text" id="approveBusinessNumber" name="businessNumber" style="width:150px;" />
							</td>
							<td>
								&nbsp;&nbsp;<input type="button" onclick="showApproveHistoryList();" value="查询" />
							</td>
						</tr>
					</table>
					<table id="approveHistory_list" ></table>
  				</div>
  				<div title="额度确认修改" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="4" style="text-align:center;font-size:19px;">
								额度确认修改<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td>
								业务单号：
							</td>
							<td>
								<input type="text" id="amountConfirmBusinessNumber" name="businessNumber" style="width:150px;" />
							</td>
							<td>
								&nbsp;&nbsp;<input type="button" onclick="showAmountConfirmList();" value="查询" />
							</td>
						</tr>
					</table>
					<table id="amountConfirm_list" ></table>
  				</div>
  				<div title="放款登记修改" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="4" style="text-align:center;font-size:19px;">
								放款登记修改<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td>
								业务单号：
							</td>
							<td>
								<input type="text" id="releaseMoneyBusinessNumber" name="businessNumber" style="width:150px;" />
							</td>
							<td>
								&nbsp;&nbsp;<input type="button" onclick="showReleaseMoneyList();" value="查询" />
							</td>
						</tr>
					</table>
					<table id="releaseMoney_list" ></table>
  				</div>
  				<div title="修改付款预约" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="2" style="text-align:center;font-size:19px;">
								修改付款预约<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<form id = "FinanceMoney_form">
									<table>
										<TR><td>预约订单号：</td><td><input type="text" id="bizId" name="bizId" style="width:150px;" /></td></TR>
										<TR><td>历史记录:</td><td> <select   id="historyFlag" name="historyFlag" style="width:150px;" >
																<option value=""></option>
																<option value="F">F:当前记录</option>
																<option value="T">T:历史记录</option>
															</select></td>
										</TR>
										<TR><td>财务状态：</td><td><select type="text" id="financeStatus" name="financeStatus" style="width:150px;" >
										<option value=""></option>
										<option value="0">0:未预约</option>
										<option value="1">1:已预约</option>
										<option value="2">2:成功</option>
										<option value="3">3:失败</option>
										<option value="4">4:撤销</option>
										<option value="5">5:作废</option>
									</select></td></TR>
									<TR><td>财务返回消息：</td> <td><textarea type="text" id="returnMsg" name="returnMsg" style="width:150px;" ></textarea></td></TR>
									</table>
								</form>
							</td>
							
						</tr>
						<tr>
						<td>
								&nbsp;&nbsp;<input type="button" onclick="showFinanceMoney();" value="查询" />
								（按照订单号查询）
							</td>
							<td>
								&nbsp;&nbsp;<input type="button" onclick="updateFinanceMoney();" value="修改" />
							</td>
						</tr>
					</table>
					
					<table id="FinanceMoney_list" ></table>
  				</div>
  				<div title="借款人申请单信息" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="4" style="text-align:center;font-size:19px;">
								借款人申请单信息修改<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<form id="borrowerServiceAppForm">
									<table>
									<tr>
										<td>
											借款申请自身id：
										</td>
										<td>
											<input type="text" id="borrowerServiceAppId" name="borrowerServiceAppId" style="width:150px;" />
										</td>
									</tr>
									<tr>
										<td>
											客户基本信息id：
										</td>
										<td>
											<input type="text" id="customerBasicId" name="customerBasicId" style="width:150px;" />
										</td>
									</tr>
									<tr>
										<td>
											身份证号：
										</td>
										<td>
											<input type="text" id="credentialsNumber" name="credentialsNumber" style="width:150px;" />
										</td>
									</tr>
									</table>
								</form>
								
							</td>
						</tr>
						<tr>
							<td>
							&nbsp;&nbsp;<input type="button" onclick="showborrowerList();" value="查询" />（按照以上条件借款申请）
							</td>
							<td>
								<input type="button" onclick="updateborrowerServiceApp();" value="修改" />
							</td>
						</tr>
					</table>
					<table id="borrower_list" ></table>
  				</div>
  				
  				<div title="客户基本信息" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="2" style="text-align:center;font-size:19px;">
								客户基本信息<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td>
								<form id="CustomerForm">
									<table>
										<tr>
											<td>
												客户基本信息id：
											</td>
											<td>
												<input type="text" id="customerBasicIdC" name="customerBasicId" style="width:150px;" />
											</td>
										</tr>
										<tr>
											<td>
												身份证号：
											</td>
											<td>
												<input type="text" id="credentialsNumberC" name="credentialsNumber" style="width:150px;" />
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
						<tr>
							<td>
							&nbsp;&nbsp;<input type="button" onclick="showCustomerList();" value="查询" />（按照以上条件查询客户基本信息）
							</td>
							<td>
							&nbsp;&nbsp;<input type="button" onclick="updateCustomerList();" value="修改" />
							</td>
						</tr>
					</table>
					<table id="customer_list" ></table>
  				</div>
  				<!--模拟贷后放款通知 begin -->
  				<div title="模拟贷后放款通知" style="padding: 10px; margin-left:250px;">
  					<table>
						<tr>
							<td colspan="2" style="text-align:center;font-size:19px;">
								模拟贷后放款通知<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:20px;">
							</td>
						</tr>
						<tr>
							<td>
								<form id="receiveCreditObjForm">
									<table border='0'>
										<tr>
											<td>
												业务单号：
											</td>
											<td>
												<input type="text" id="businessNumberByApplyId" name="applyId" style="width:150px;" />
											</td>
										</tr>
										<tr>
											<td>
												实际放款/结清时间：
											</td>
											<td>
												<input type="text" id="loanOrSettleDdate" class="easyui-datetimebox" name="time" style="width:152px;" />
											</td>
										</tr>
										<tr>
											<td>
												业务状态：
											</td>
											<td>
												<input type="text" id="auditStatusM" name="type" style="width:150px;" />
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input type="button" onclick="updateStatusByReceiveCreditMsg();" class="easyui-linkbutton" id="updateStatusByReceiveCreditMsgSumbit" value="     提   交     " />
							</td>
						</tr>
					</table>
  				</div>
  				<!-- 模拟贷后放款通知   end -->
                <!--身份证号变更 begin -->
                <div title="身份证号变更" style="padding: 10px; margin-left:250px;">

                    <table>
                        <tr>
                            <td colspan="4" style="text-align:center;font-size:19px;">
                                身份证号变更<br />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height:20px;">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                业务单号：
                            </td>
                            <td>
                                <input type="text" id="businessNumberForIDChange" name="businessNumber" style="width:150px;"  required="true" class="easyui-validatebox"   />
                            </td>
                            <td>
                                &nbsp;&nbsp;<input type="button" onclick="getIdInfoByBusinessNumber();" value="查询" />
                            </td>
                        </tr>
                    </table>
                    <div id="idChangeDiv" class="easyui-dialog" modal="true" maximizable="true"
                         closed="true" title="更改身份证信息"  style="width:500px;height:370px;overflow: hidden;">
                        <iframe scrolling="no" id='idChangeFrame' frameborder="0"  src="" style="width:100%;height:100%;" align="center"></iframe>
                    </div>

                </div>
                <!-- 身份证号变更   end -->
            </div>
                <!--身份证号变更 begin -->
                <div title="身份证号变更" style="padding: 10px; margin-left:250px;">

                    <table>
                        <tr>
                            <td colspan="4" style="text-align:center;font-size:19px;">
                                身份证号变更<br />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="height:20px;">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                业务单号：
                            </td>
                            <td>
                                <input type="text" id="businessNumberForIDChange" name="businessNumber" style="width:150px;"  required="true" class="easyui-validatebox"   />
                            </td>
                            <td>
                                &nbsp;&nbsp;<input type="button" onclick="getIdInfoByBusinessNumber();" value="查询" />
                            </td>
                        </tr>
                    </table>
                    <div id="idChangeDiv" class="easyui-dialog" modal="true" maximizable="true"
                         closed="true" title="更改身份证信息"  style="width:500px;height:370px;overflow: hidden;">
                        <iframe scrolling="no" id='idChangeFrame' frameborder="0"  src="" style="width:100%;height:100%;" align="center"></iframe>
                    </div>

                </div>
                <!-- 身份证号变更   end -->
            </div>
   		</div>	
   		<div id="modifyRegisterStatusDialog" class="easyui-dialog" closed="true" style="width: 800px;padding: 10px;" >
    		<form id="registerStatusForm" >
    			<input type="hidden" id="modifyGroupLoanRegistId" name="groupLoanRegistId"/>
    			修改状态为：
    			<select name="status" style="width:150px;">
    				<option value="0">0-放款登记</option>
    				<option value="1">1-放款确认</option>
    				<option value="2">2-放款确认回退</option>
    			</select>
    			<input type="button" value="提交" onclick="modifyRegisterStatus();" />
    		</form>
    	</div>
  </body>
</html>
