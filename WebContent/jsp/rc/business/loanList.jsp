<%@page import="com.creditease.rc.util.CommonUtil"%>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
String cmUrl = properties.getProperty("cm.url");
String isURL = request.getRequestURL().toString();
if(isURL.indexOf(".cn")>0){
	if(cmUrl.indexOf(".corp")>0){
		cmUrl=cmUrl.replaceAll(".corp",".cn");
	}
}else if(isURL.indexOf(".corp")>0){
	if(cmUrl.indexOf(".cn")>0){
		cmUrl=cmUrl.replaceAll(".cn",".corp");
	}
}
String cmIp = properties.getProperty("cm.hostip");
DESPlus desPlus = new DESPlus();
String DESNow = desPlus.encrypt(new Date().getTime()+"");
String DESIp = desPlus.encrypt(cmIp);
String role=request.getParameter("role");
StringBuffer conditions=new StringBuffer("");
String separator="?";
if(CommonUtil.isNotEmpty(role)){
	conditions.append(separator).append("role=").append(role);
	separator="&";
}
String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName="<%=path%>";
			var cmUrl = "<%=cmUrl%>";
			var DESIp = "<%=DESIp%>";
			var DESNow = "<%=DESNow%>";
			var userId = "<%=userId%>";
			var conditions="";
			<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
				conditions="<%=conditions.toString()%>";
			<%}%>
			function  loanListOperater(value,data,index){
				var links = "";
				<sec:authorize ifAnyGranted="${control.giveMoney_searchLook}">
			 		links = links + "<a href='javascript:searchGroupRegist();'>查看</a>";
				</sec:authorize>
				if(($.trim(value) == "21" || $.trim(value) == "14")&& $.trim(data.businessType) == "1"){
					
					<sec:authorize ifAnyGranted="${control.giveMoney_loanRegist}">
					 	links = links + "|<a href='javascript:dengji();'>放款登记</a>";
					</sec:authorize>
					<sec:authorize ifAnyGranted="${control.giveMoney_loanFailtureRegist}">
						links = links + "|<a href='javascript:LoanRegistrationFail();'>放款失败登记</a>";	
					</sec:authorize>
					<sec:authorize ifAnyGranted="${control.giveMoney_contract}">
				 		links = links + "|<a href='javascript:contract();'>打印合同</a>";
					</sec:authorize>
					return links;
				}else if(($.trim(value) == "10" || $.trim(value) == "14")&& $.trim(data.businessType) == "0"){
					<sec:authorize ifAnyGranted="${control.giveMoney_loanRegist}">
					 	links = links + "|<a href='javascript:dengji();'>放款登记</a>";
					</sec:authorize>
					<sec:authorize ifAnyGranted="${control.giveMoney_loanFailtureRegist}">
				 		links = links + "|<a href='javascript:LoanRegistrationFail();'>客户放弃</a>";
					</sec:authorize>
					<sec:authorize ifAnyGranted="${control.giveMoney_contract}">
				 	links = links + "|<a href='javascript:contract();'>打印合同</a>";
					</sec:authorize>
					return links;
				}else if($.trim(value) == "11"){
					<sec:authorize ifAnyGranted="${control.giveMoney_loanConfirm}">
					 	links = links + "|<a href='javascript:LoanConfirm();'>放款确认</a>";
					</sec:authorize>
					return links;
				}else{
					return links;
				}
			}
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/loanList.js"></script>
		<style type="text/css">
			.viewInput{
				border-left: 0;
	   			border-right: 0;
	   			border-top: 0;
	   			border-bottom: 1px;
	   			background:white;
	   			text-align: inherit;
   			}
   			.tabTitle {
				background-color: #E9E5E1;
				text-align:center;
			}			
			.advisetable {
				border:thin;
				border-color:#558DD5;
				border-collapse:collapse;
				width:100%;
				align:center;
			}
			.advisetable td{
				border: 1px solid #558DD5;
				text-align:left;
			}
		</style>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center">
		 		
			<div id="loanListTab" class="easyui-tabs" style="padding: 10px;">
				<div id="sousuo" title="模糊搜索" style="padding: 10px;">
					模糊搜索条件：<input id="fuzzyValue" style="width: 120px;"/>
					<a class="easyui-linkbutton" href="javascript:searchFussy();">搜索</a>
					<a class="easyui-linkbutton" href="javascript:searchCancel();">清除</a><font color="red">(可以按照 借款人姓名、客户经理查询)</font>
				 </div>
				 <div  title="高级搜索" style="padding: 10px;">
				 	<%--<table>
				 		<tr>
				 			<td>业务类型:</td><td><input id="businessType" class="easyui-combobox"  style="width: 100px;"/></td>
				 			<td>分公司名称:</td><td><input  style="width: 120px;" id="companyName" type="text"/></td>
				 			<td>状态:</td><td>	<select id="auditStatus" class="easyui-combobox"  style="width: 100px;">
								<option value="">请选择</option>
								<option value="10">款项到位</option>
								<option value="11">已放款登记</option>
								<option value="21">放款额度确认</option>
								<option value="13">放款确认</option>
								<option value="15">还款中</option>
							</select></td>
								<td>业务单号：</td><td><input id="groupNumber" style="width: 110px;"/></td>
								<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
				 		</tr>
				 		<tr>
				 		
				 			<td>借款人姓名：</td><td><input id="groupName" style="width: 100px;"/></td>
				 			<td>放款日期：</td><td><input id="beginLoanDate" class="easyui-datebox" style="width: 100px;" editable="false"/> 至</td>
				 			<td colspan="2"><input id="endLoanDate" class="easyui-datebox" style="width: 100px;"editable="false"/></td>
				 			<td>放款确认日期：</td>
				 			<td><input id="loanConfirmDateBegin" class="easyui-datebox" style="width: 100px;" editable="false"/> 至</td>
				 			<td colspan="2"><input id="loanConfirmDateEnd" class="easyui-datebox" style="width: 100px;"editable="false"/></td>
				 			<td><a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a></td>
				 			<td><a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a></td>
				 		</tr>
				 	</table>--%>
                        <table>
                            <tr>
                                <td nowrap="nowrap">业务单号：<input id="groupNumber" style="width: 115px;"/></td>
                                <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借款人：<input id="groupName"
                                                                                                   style="width: 100px;"/>
                                    状态:
                                    <select id="auditStatus" class="easyui-combobox" style="width: 95px;">
                                        <option value="">请选择</option>
                                        <option value="10">款项到位</option>
                                        <option value="11">已放款登记</option>
                                        <option value="21">放款额度确认</option>
                                        <option value="13">放款确认</option>
                                        <option value="15">还款中</option>
                                    </select>
                                </td>
                                <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;放款日期：
                                    <input id="beginLoanDate" class="easyui-datebox" style="width: 150px;"
                                           editable="false"/>
                                    -
                                    <input id="endLoanDate" class="easyui-datebox" style="width: 150px;"
                                           editable="false"/>
                                </td>
                                <td nowrap="nowrap">
                                    <a class="easyui-linkbutton" href="javascript:searchAdvanced();">搜索</a>
                                    <a class="easyui-linkbutton" href="javascript:searchCancel1();">清除</a>
                                </td>
                            </tr>
                            <tr>
                                <td nowrap="nowrap">业务类型：<input id="businessType" class="easyui-combobox"
                                                                style="width: 120px;"/></td>
                                <td nowrap="nowrap">分公司名称：<input style="width: 235px;" id="companyName" type="text"/>
                                </td>
                                <td nowrap="nowrap" colspan="2">放款确认日期：
                                    <input id="loanConfirmDateBegin" class="easyui-datebox" style="width: 150px;"
                                           editable="false"/>
                                    -
                                    <input id="loanConfirmDateEnd" class="easyui-datebox" style="width: 150px;"
                                           editable="false"/>
                                </td>
                            </tr>
                        </table>
				</div>
			</div>
			
			<div style="padding: 10px;">
				<table id="loanList">
				</table>
			</div>
		</div>
		<!-- 放款登记 -->
		<div id="loanRegistration" 
		title="放款登记"
		class='easyui-dialog' 
		closed="true"
		draggable="false"
		style="width: 900px;height:440px;padding: 10px;"  maximizable="true">
			  			<form id="loanRefistration" novalidate>
				 			 <table >
								<tr height="40">
									<td align="right" width = "90">放款日期：
									<input name="creditapplicationId" id="creditapplicationIdDengji" type="hidden"/> <!-- 信贷申请id -->
									<input name="loanOfficerId" type="hidden"/>		<!-- 信贷员id -->
									<input name="loanAmount"  type="hidden"/>		<!-- 放款金额 -->
									<input name="realAmount"  type="hidden"/>		<!-- 实发金额 -->
									</td>
									<td align="left" width = "300">
										<input id="loanTime" name="loanTime" class="easyui-datebox" editable="false"
										 required="true"/>
									</td>
								 
									<td align="right" width = "90">协议编号：</td>
									<td align="left" width = "300">
										<input name="protocolID" class="easyui-validatebox" required="true"/>
									</td>
									<%--<td align="right" width = "90">还款方式：
									</td>
									<td align="left" width = "150">
										<input name="defaultReturnmentWay" id="defaultReturnmentWay" class="easyui-combobox" style="width:150px;" required="true"/>
									</td>
								--%></tr>
								<tr height="40">
								<td align="right" width="90">业务单号：</td>
								<td align="left" width="300">
									<input  name="groupNumber" id="groupNumberRegistration" readonly="readonly" 
									  />
								</td>
								<td align="right" width="90">客户经理：</td>
								<td align="left" width="300">
									<input name="loanOfficerName" readonly="readonly" 
										id="loanOfficerName"/> <!-- 信贷员姓名 -->
									<input name="businessType"
										type="hidden"
									 	id="businessTypeRegristration">
								</td>
								</tr>
								<tr>
								<td align="right" valign="top" width="90">
								放款确认历史：
								</td>
								<td colspan="3">
										<table id="historyLoan">
										</table>
								</td>
								</tr>
							</table>
					   </form>	
							<table>	
								<tr height="40">
									<td align="right" width="90">付款方式：</td>
									<td width="700">
											<input id="paywayRegristraion" readonly="readonly"/>
											<a id="buttonEdit" class="easyui-linkbutton" onclick="editAccount();">修改</a>
								 			<a id="buttonCongirm" class="easyui-linkbutton"  onclick="showAcount();">确定</a>
								 			<input id="zhanghu" type="hidden" value="1"/><!-- "1": 账户未修改 ，"0":账户修改 -->
								  	</td>	
							  	</tr>
							  	<tr>
								  	<td>
								  	</td>
								  	<td width="700">
								  		<div id="accountShow" style="width: 100%;">	
								  			<form id="showAccountForm">
												<table style="width: 100%;">
													<tr>
														<td>省：</td><td align="left">
														<input name="provinceId" id="provinceIdEnable"  readonly="readonly"  /> </td>
														<td>市：</td><td align="left">
														<input name="cityId" id="cityIdEnable" readonly="readonly"  /></td>
														<td>区：</td><td align="left">
														<input name="districtId" id="districtIdEnable" readonly="readonly"  /></td>
													</tr>
													<tr>
														<td>所在行地区号：</td><td align="left">
														<input name="bankPrefectureNum" readonly="readonly"   /> </td>
														
														<td>账户名：</td>
														<td align="left">
														<input name="accountName" readonly="readonly"  />
														<input   name="credentialsNumber" type="hidden"/>
														<input   name="accountType" type="hidden" value="1"/>	<!-- 账户类型 -->	
														</td>
														<td>支付行号：</td><td align="left">
														<input name="payBranchno"  readonly="readonly"  /></td>
													</tr>
													
													<tr>
														<td>开户行：</td><td align="left">
														<input name="bankName" readonly="readonly"   /></td>
														<td>账号：</td><td align="left">
														<input name="account" readonly="readonly"   /></td>
														<td>卡折类型：</td><td align="left">
														<input name="cardFlag" id="cardFlagEnable" readonly="readonly"  /></td>
													</tr>
												</table>
											</form>
										</div>
								  			<div id="accountEdit">
								  			<form id="accountEditForm">
												<table style="width: 100%;">
													<tr>
														<td>省：</td><td align="left">
														<input name="accountInfoId" type="hidden">
														<input name="provinceId" class="easyui-combobox"
															required="true" id="provinceId"  /> </td>
														<td>市：</td><td align="left">
														<input name="cityId" class="easyui-combobox"
															required="true" id="cityId"  /></td>
														<td>区：</td><td align="left">
														<input name="districtId" class="easyui-combobox" 
															required="true" id="districtId"  /></td>
													</tr>
													<tr>
														<td>所在行地区号：</td><td align="left">
														<input name="bankPrefectureNum" id="bankPrefectureNum" 
															class="easyui-validatebox"
															validType="numberOnly"
															required="true"/> </td>
														<td>账户名：</td><td align="left">
														<input name="accountName" id="accountName"  
															class="easyui-combobox"
															required="true"/>
														<input   name="credentialsNumber" id="credentialsNumber" type="hidden"/>
														<input   name="accountType" type="hidden" value="1"/>	<!-- 账户类型 -->		
														</td>
														<td>支付行号：</td><td align="left">
															<input  name="payBranchno" class="easyui-validatebox"
															validType="numberOnly"/></td>
													</tr>
													<tr>
														<td>开户行：</td><td align="left">
														<input name="bankName" id="bankName" 
															class="easyui-validatebox"
															required="true"/></td>
														<td>账号：</td><td align="left">
														<input name="account" id="account" 
															class="easyui-validatebox"
															validType="numberOnly"
															required="true"/></td>
														<td>卡折类型：</td><td align="left">
														<input name="cardFlag" id="cardFlag" style="width: 123px;" class="easyui-combobox"  
															required="true"/></td>
													</tr>
												</table>
											</form>
										</div>
									</td>
								</tr>
								<tr height="40">
									<td valign="top"  align="right"  width="90">放款明细：</td>
									<td width="700">
										<table id="detailRegist">
										</table>
									</td>
								</tr>
								<tr height="40">
									<td  align="right" valign="top" width="90">
										文件上传：
									</td>
									<td width="700">
											<iframe scrolling="auto" id='openCM' frameborder="0" src="" style="width: 100%; height: 200px;"></iframe>
									</td>
								</tr>
							</table>
		</div>
		
		<div id="LoanConfirm" >
		
					<div id="layoutZ" class="easyui-layout" style="width:100%;height:100%;">	
						<div region="west" split="true" title="合同基本信息" style="width:710px;height:500px;padding-top:10px;">
							<form id="loanConfirmForm">
								<input name="groupLoanRegistId" id="groupLoanRegistId" type="hidden"/>		<!-- 放款登记编号 -->
								<input name="loanAmount"  type="hidden"/>		 
								<input name="realAmount"  type="hidden"/>	
								<input name="loanPerson"  id="loanPerson" type="hidden"/>			<!-- 放款确认人 -->
								<input name="remarkComment" id="remarkComment" type="hidden"/>		<!-- 放款备注 -->	
								<input name="creditapplicationId" type="hidden"/> 								<!-- 信贷申请id -->
								
					 			<table width="100%">
									<tr height="30">
										<td align="right" width="10%">放款日期：</td>
										<td align="left" width = "150"><input size="25" name="loanTime" readonly="readonly"/></td>
										<td align="right" width = "120">协议编号：</td>
										<td align="left" width = "150"><input name="protocolID" readonly="readonly"/></td>
										<td align="right" width="120">业务单号：</td>
										<td align="left" width="150"><input  name="groupNumber" readonly="readonly"/></td>
									</tr>
										<tr height="30">
										<td align="right" width = "120">身份证号：</td>
										<td align="left" width = "150"><input size="25" name="credentialsNumber" readonly="readonly"/></td>
										<td align="right" width="120">银行账号:</td>
										<td align="left" width="150"><input name="bankAccountNumber"  readonly="readonly"/></td>
										<td align="right" width="120">客户经理：</td>
										<td align="left" width="150"><input  name="loanOfficerName"  readonly="readonly"/>
									</tr>
								</table>
							</form>
                            <%--客服检查--%>
                            <%--<table width="100%">
                                <tr>
                                    <td valign="top" width="15%" align="right">放款明细：</td>
                                    <td width="85%">
                                        <table id="detailConfirm">
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top" >
                                        放款确认历史：
                                    </td>
                                    <td>
                                            <table id="historyRemark">
                                            </table>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td align="right">确认人：
                                    </td>
                                    <td align="left">
                                        <input readonly="readonly"
                                        id="loanPerson1"
                                        value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>"  />
                                    </td>
                                    </tr>
                                    <tr>
                                        <td align="right">备注和意见：
                                    </td>
                                    <td align="left">
                                        <textarea id="remarkComment1"
                                        class="easyui-validatebox"
                                        required="true"
                                        rows="2" cols="50"></textarea>
                                    </td>
                                    </tr>
                                </table>--%>
                            <input id="complianceCheckId" type="hidden"/>
                            <div class="easyui-panel" title="合规检查">
                                <table id="check_customer" style="border: solid 1px #9ABBE8;"></table>
                                <table width="100%">
                                    <tr>
                                        <td align="right" colspan="2">
                                            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-addOne"
                                               plain="true"
                                               onclick="addRow();">添加</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="easyui-panel" title="放款确认历史" style="padding-bottom: 10px;">
                                <table width="100%">
                                    <tr>
                                        <td>
                                            <table id="historyRemark">
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="easyui-panel" title="确认信息">
                                <table width="100%">
                                    <tr>
                                        <td align="right">确认人：
                                        </td>
                                        <td align="left">
                                            <input readonly="readonly"
                                                   id="loanPerson1"
                                                   value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">备注和意见：
                                        </td>
                                        <td align="left">
                                            <textarea id="remarkComment1"
                                                      class="easyui-validatebox"
                                                      required="true"
                                                      rows="2" cols="50"></textarea>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <%--客服检查--%>
						</div>
						<div region="center" title="上传的图片" style="background:#fafafa;width:710px;">
							<div id="editButton" class="easyui-tabs">
								<div title="放款登记附件">
												<iframe height="450px" width="100%" scrolling="auto" id='openCMView' frameborder="0" src=""></iframe>
								</div>
								<div title="申请单附件">
												<iframe height="450px" width="100%" scrolling="auto" id='applicationCMView' frameborder="0" src=""></iframe>
								</div>
							</div>
						</div>
					</div>
			</div>
		
		<div id="LoanRegistrationFail" 
		title="放款失败登记"
		class='easyui-dialog' 
		closed="true"
		style="width: 500px;height:300px;padding: 20px;"
		buttons="#testbutton">
			<form id="loanFail">
				<table>
					<tr height="30"><td>
						业务单号：<input name="groupNumber" 
								readonly="true" id="groupNumberShow"/><br/>
					</td></tr>
					<tr height="30"><td>
						客户经理：<input name="loanOfficerName" 
								readonly="true" id="customerManager"/><br/>
					</td></tr><%--
					<tr height="30"><td>
						处理方式：<select name="auditStatus" id="auditStatus1" class="easyui-combobox" editable="false">
								<option value="06" checked="checked">撤销</option>
								</select><br/><br/>
					</td></tr>
					--%><tr><td>
						失败原因：<textarea name="revokeReason" 
								class = "easyui-validatebox"
								id="failReason" required="true" 
								rows="1" cols="30">
							</textarea><br/><br/>
						<input name="creditapplicationId" type="hidden"/>	
					</td></tr>
				</table>
			</form>
		</div>
		
		<div id="contract" 
		title="合同打印"
		class='easyui-dialog' 
		closed="true"
		style="width: 900px;height:500px;padding: 10px;"
		buttons="#testbutton">
				<div class="easyui-panel" title="基础信息"
						style="border: 0px; margin: 0px; width: 100% px; padding: 10px;">
					<table width="80%">
			 		<tr><td width="70" align="right">业务单号:</td>
			 		<td width="100" align="left">we333</td>
			 		<td width="70" align="right">放款日期:</td>
			 		<td width="100" align="left"><input class="easyui-datebox"/></td>
			 		<td width="200"><a href="#" class="easyui-linkbutton">合同预览</a></td>
			 	</table>
			 </div>
			 
			<table id="detailsContract" title="明细">
					
			</table>
			<br />
			<br />
			<table id="history" title="历史">
			</table>
		</div>
		<div id="LoanLook" 
		title="查看放款登记"
		class='easyui-dialog' 
		closed="true"
		draggable="false"
		style="width: 900px;height:400px;padding: 10px;" maximizable="true">
					<form id="registLook">
			 			 <table id="registDetail">
								<tr  >
								<td align="right" width="89">业务单号：</td>
								<td align="left" width="148">
									<input name="groupNumber" readonly="readonly"  /></td>
								<td width="200"></td>
								<td align="right" width="88">客户经理：</td>
								<td align="left" width="149">
									<input name="loanOfficerName"  readonly="readonly"  /></td>
								</tr>
								<span id="addtr"></span>
								<%--<tr>
									<td align="right" width = "90">备注和意见：
									</td>
									<td align="left" colspan="4">
										<textarea name="remarkComment" id="remarkComment" 
										readonly="readonly" hidden="true"
										rows="2" cols="70"></textarea>
									</td>
								</tr>
							--%></table>
						</form>	
					<table>	
						<tr height="40">
							<td valign="top" width="50" align="right">放款明细：</td>
							<td width="750">
								<table id="detailLook">
								</table>
							</td>
						</tr>
                        <tr height="40">
                            <td valign="top" width="50" align="right">合规检查：</td>
                            <td>
                                <table id="check_customer_view"></table>
                            </td>
                        </tr>
						<tr height="40">
							<td align="right" valign="top">
								放款确认历史：
							</td>
							<td>
									<table id="historyRemarkLook">
									</table>
							</td>
							</tr>
							<tr height="40">
										<td align="right" width="90">付款方式：</td>
										<td colspan="3">
												<input id="paywayLook" readonly="readonly"/>
									  	</td>	
								 </tr>
							<tr height="40">
							<td width="800" align="center" colspan="2">
								<form id="showAccountFormLook">
												<table align="center" style="width: 80%;">
													<tr>
														<td align="right">省：</td><td align="left">
														<input name="provinceId" id="provinceIdLook"  readonly="readonly" /> </td>
														<td align="right">市：</td><td align="left">
														<input name="cityId" id="cityIdLook" readonly="readonly"  /></td>
														<td align="right">区：</td><td align="left">
														<input name="districtId" id="districtIdLook" readonly="readonly"  /></td>
													</tr>
													<tr>
														<td align="right">所在行地区号：</td><td align="left">
														<input name="bankPrefectureNum" readonly="readonly"   /> </td>
														
														<td align="right">账户名：</td>
														<td align="left">
														<input name="accountName" readonly="readonly"  />
														</td>
														<td align="right">支付行号：</td><td align="left">
														<input name="payBranchno"  readonly="readonly"  /></td>
													</tr>
													
													<tr>
														<td align="right">开户行：</td><td align="left">
														<input name="bankName" readonly="readonly"   /></td>
														<td align="right">账号：</td><td align="left">
														<input name="account" readonly="readonly"   /></td>
														<td align="right">卡折类型：</td><td align="left">
														<input name="cardFlag" id="cardFlagLook" readonly="readonly"  /></td>
													</tr>
												</table>
									</form>	
							</td>
						</tr>
							<table style="width: 100%;height: 500px;">
								<tr>
									<td align="right" width="10%" valign="top">协议文件：</td>
									<td width="90%">
										<iframe height="500px" width="100%" scrolling="auto" id='openCMDJView' frameborder="0" src=""></iframe>
									</td>
								</tr>
							</table>
						</table>
		</div>
		<div id="testbutton"> 
			<a class="easyui-linkbutton" onclick="saveRegistration();">确定</a>
			<a class="easyui-linkbutton" onclick="concel();">取消</a>
		</div>
		
		
		<div id="accInfoDialog" class="easyui-dialog" title="添加卡信息" modal="true" align="center" buttons="#abutn" style="padding: 10px; border: 0px; margin: 0px; width: 700px; height: 350px" closed="true" resizable="true" inline="false" closable="false">
			<div id="print_Dialog" style="display:none">
			<form id="print_form">
				<table>
						<tr>
							<td align="right">期望放款日期：</td>
							<td>
								<input type="text" id="print_loan_date" class="easyui-datebox" name="print_loan_date" required="true" editable="false" style="width: 150px;"/>
								<input type="hidden" name="" id="print_creditapplicationId"/>
								<a id="btn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="print_contract()">下载</a>
                                <font color="red"><span id="timer"></span></font>
                                <button id="start" style="display: none">start</button>
                                <button id="reset" style="display: none" onclick="">reset</button>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</body>
</html>
