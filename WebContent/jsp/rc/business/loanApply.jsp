<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

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
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		 var serverName="<%=path%>";
		 var cmUrl = "<%=cmUrl%>";
		 var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;
		 var userId = "<%=userId%>";
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/loanApply.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/guaranorList.js"></script>
		</head>
	<body class="easyui-layout">
		<div region="center">
			<div id="tt" class="easyui-tabs" width="100%" height="500">
				<div title="申请单信息">
					<fieldset>
						<legend>
							申请单
						</legend>
						<div id="groupList">
							<form id="group" novalidate>
								<table width="700"  style="padding: 10px;"  align="center">
									<tr height="30">
										<td width="100">
											*分公司:
										</td>
										<td align="left" width="200">
											<!-- 公司名称 -->
											<input name="companyName" readonly="readonly" value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentName()%>"/>
											<!-- id -->
											<input name="creditapplicationId" id="creditapplicationId" type="hidden" 
											value="${creditApplicationReturn.creditapplicationId}" />
											<!-- 付款账户id -->
											<input name="accountInfoId" id="accountInfoIdCredit" 
											  type="hidden"
											 value="${creditApplicationReturn.accountInfoId}"/>
											<!-- 还款账户id -->
											<input name="returnAccountInfoId" id="returnAccountInfoIdCredit" 
											  type="hidden"
											 value="${creditApplicationReturn.returnAccountInfoId}"/>
											<!-- 业务单号 -->
											<input name="groupNumber" type="hidden" id="groupNumber" value="${creditApplicationReturn.groupNumber}" />
										</td>
										<td></td>
									</tr>
									<tr height="30">
										<td>
											*业务单号:
										</td>
										<td align="left">
											<input type="text" id="editGroupNum"
											value="${creditApplicationReturn.groupNumber}" 
											class="easyui-validatebox"
											readonly="readonly" />
										</td>
									</tr>
									<tr height="30">
										<td>
											*产品类型：
										</td>
										<td align="left">
											<div id="repaymentPlanIdHidden" width="200">
											<table>
                                                <tr><td>
                                                    <input name="repaymentPlanId"
                                                           editable="false" class="easyui-combobox"
                                                           id="productType" required="true"
                                                           value="${creditApplicationReturn.repaymentPlanId}" />
                                                </td>
                                                    <td>
                                                     <div id="subProductTypeDiv"> <input name="subProductType"
                                                               editable="false" class="easyui-combobox"
                                                               id="subProductType" required="true"
                                                               value="${creditApplicationReturn.instalments}" /> </div>
                                                    </td>
                                                </tr>
											</table>
												<input name="repaymentPlanName"
												id="repaymentPlanName" type="hidden"
												value="${creditApplicationReturn.repaymentPlanName}" />
												
												<input name="instalments" 
												id="instalments"   type="hidden"
												value="${creditApplicationReturn.instalments}" />
												
												<input name="repaymentType" 
												id="repaymentType"  type="hidden"
												value="${creditApplicationReturn.repaymentType}" />
												
												<input name=capitalUpperLimit 
												id="capitalUpperLimit"  type="hidden"
												value="${creditApplicationReturn.capitalUpperLimit}" />
												
												<input name="capitalLowerLimit" 
												id="capitalLowerLimit"  type="hidden"
												value="${creditApplicationReturn.capitalLowerLimit}" />
												
												<input name="producttypeid" 
												id="producttypeid"  type="hidden"
												value="${creditApplicationReturn.producttypeid}" />
												
											</div>
										</td>
										<td></td>
									</tr>
									
									<tr height="30">
										<td>
											*信 贷 员：
										</td>
										<td align="left">
											<!-- 信贷员姓名 -->
											<input name="loanOfficerName" id="loanOfficerName" 
											class="easyui-validatebox" required="true" 
											value="<%=SpringSecurityUtils.getCurrentUser().getName_zh()%>" readonly="readonly" />
											<!-- 信贷员id -->
											<input name="loanOfficerId" class="easyui-validatebox" type="hidden" 
											value="<%=SpringSecurityUtils.getCurrentUser().getUserId()%>" />
											<!-- 分公司id -->
											<input name="companyId" type="hidden" 
											value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentId()%>"/>
											<!-- 部门id -->
											<input name="departmentId"  type="hidden" 
											value="<%=SpringSecurityUtils.getCurrentUser().getDepartmentId()%>"/>
											<!-- 咨询id -->	
											 <input name="customerConsultId" id="customerConsultId" type="hidden" value="${creditApplicationReturn.customerConsultId }"/>
											 <!-- 咨询池id -->	
											 <input name="consultPoolId" id="consultPoolId" type="hidden" value="${creditApplicationReturn.consultPoolId }"/>


                                        </td>
										<td></td>
									</tr>
                                    <tr height="30">
                                        <td>
                                            借款人身份证号：
                                        </td>
                                        <td align="left" >
                                            <!--   借款人身份证号码   -->
                                            <input name="credentialsNumber" id="credentialsNumber" readonly="true" value="${creditApplicationReturn.credentialsNumber }"/>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr height="30">
                                        <td>
                                            共借人身份证号：
                                        </td>
                                        <td align="left" >
                                            <!-- 配偶身份证号-->
                                            <input name="mateIdNumber" id="mateIdNumber" readonly="true" value="${creditApplicationReturn.mateIdNumber }"/>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
									<tr height="30">
										<td>
										</td>
										<td align="left" >
										</td>
										<td>
											
										</td>
									</tr>
									<tr height="30">
										<td>
										
										</td>
										<td align="center">
										<a id="submitGroupButton" class="easyui-linkbutton" iconCls="icon-save" onclick="submitGroup()">保存</a>
										</td>
										<td></td>
									</tr>
									
								</table>
							</form>
								<table>
									<tr>
										<td>
										</td>
										<td width="50">
											
										</td>
										<td>
										</td>
									</tr>
								</table>
							
						</div>
						
						<!-- 不可编辑 显示 -->
						<div id="editableFalse" style="display: none;">
							<form id="groupShow">
								<table width="700" style="padding: 10px;"  align="center">
									<tr height="30">
										<td width="100">
											*分公司:
										</td>
										<td width="500" align="left">
											<input name="companyName" id="companyName1"  readonly="readonly" value="${creditApplicationReturn.companyName}" />
											<input name="creditapplicationId" id="creditapplicationId1" 
											type="hidden"   value="${creditApplicationReturn.creditapplicationId}" />
											<input name="creditapplicationDESId" id="caDESId" type="hidden"
												value="${creditApplicationReturn.creditapplicationDESId}" />
										</td>
									</tr>
									<tr height="30">
										<td>
											*业务单号:
										</td>
										<td align="left">
											<input name="groupNumber" type="text" id="groupNumber1" 
											value="${creditApplicationReturn.groupNumber}" 
											class="easyui-validatebox"
											readonly="readonly" />
										</td>
									</tr>
									<tr height="30">
										<td>
											*产品类型：
										</td>
										<td width="200" align="left"><%--
											<input name="repaymentPlanId" id="productTypeLook"
											 readonly="readonly" />
											 --%><input name="repaymentPlanName" 
												id="repaymentPlanNameLook" readonly="readonly"
												value="${creditApplicationReturn.repaymentPlanName}" />
										</td>
									</tr>
									<tr height="30">
										<td>
											*信 贷 员：
										</td>
										<td align="left">
											<input name="loanOfficerName" id="loanOfficerName1" value="${creditApplicationReturn.loanOfficerName}" class="easyui-validatebox" readonly="readonly" />
											<input name="loanOfficerId" style="visibility: hidden;" value="${creditApplicationReturn.loanOfficerId}" />
											<input name="companyId"  type="hidden" 
											value="${creditApplicationReturn.companyId}">
											<input name="companyName" type="hidden" 
											value="${creditApplicationReturn.companyName}">
										</td>
									</tr>
									<tr height="30">
									<td>
										</td>
										<td align="left">
										
										</td>
									</tr>
									<tr height="30">
										<td>
										</td>
										<td width="500" align="left">
											<a class="easyui-linkbutton" onclick="showUploadDig();">上传业务资料</a>
											<a class="easyui-linkbutton" onclick="showUpdateGroup();">修改</a>
											<a class="easyui-linkbutton" iconCls="icon-save" href="javascript:submitAuditing();" >提交申请</a>
										</td>
										<td align="left">
											
										</td>
										<td align="center" style="width: 100px;">
										</td>
									</tr>
								</table>
							</form>
						</div>
						<table>
							<tr>
								<td>
								</td>
								<td width="50">
								
								</td>
								<td>
								</td>
							</tr>
						</table>
					</fieldset>
					

				</div>
				<div title="组员资料添加"   onmouseover="validateUse();">
					<%--<fieldset>--%>
						<%--<legend>--%>
							<%--新增借款人--%>
						<%--</legend>--%>
						<%--请输入借款人身份证 ：--%>
                        <div style="display: none">
                        <input style="width: 180px;" id="identity"      type="hidden"/>
						<span id="buttonAdd"><font color='red'>请输入正确的身份证号码，方可添加！</font> </span>
						<input id="credentialsNumberAdd" type="hidden" />
                        </div>
					<%--</fieldset>--%>

					<br />
					<fieldset>
						<legend>
							借款人列表
						</legend>
						<table id="borrowSerivceList"></table>
						<div id="cashStream"></div>
					</fieldset>
					<fieldset>
						<legend>
							新增担保人
						</legend>
						请输入担保人身份证 ：
						<input style="width: 180px;" id="identityGuaranor"     />
						<span id="buttonAddGuaranor"><font color='red'>请输入正确的身份证号码，方可添加！</font> </span>
						<input id="credentialsNumberAddGuaranor" type="hidden" />
					</fieldset>
					<fieldset>
						<legend>
							担保人列表
						</legend>
						<table id="guaranorList"></table>
					</fieldset>
				</div>
				<table align="center" width="600px">
					<tr>
						<td width="100%" align="center"><%--
							<a id="buttonBackList" iconCls="icon-back" 
							 class="easyui-linkbutton" href="jsp/rc/business/creditApplicationList.jsp">信贷列表</a>
							 	<a id="buttonZixun" iconCls="icon-back" 
							 class="easyui-linkbutton" href="jsp/rc/business/customerConsult.jsp">客户咨询</a>--%>
						</td>
					</tr>
				</table>
			</div>

			<div id="cm">
				 
			</div>
			<div id="guaranorCM">
				 
			</div>
			<div id="borrowSerivce" class="easyui-dialog" modal="true" maximizable="true" 
				closed="true" title="业务申请单" style="width:1000px;height:470px;overflow: hidden;">
							 <iframe scrolling="no" id='borrowSerivceFram' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
						</div>
			<div id="guaranorDialog" class="easyui-dialog" modal="true" maximizable="true" 
				closed="true" title="担保人信息表" style="width:1000px;height:470px;overflow: hidden;">
				 <iframe scrolling="no" id='openGuaranor' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>

			</div>
			</div>
	</body>
    <script type="text/javascript">
        //处理键盘事件
        //        function doKey(e){
        //            console.info("---------------------key------------");
        //            var ev = e || window.event;//获取event对象
        //            var obj = ev.target || ev.srcElement;//获取事件源
        //            var t = obj.type || obj.getAttribute('type');//获取事件源类型
        //            if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){
        //                ev.preventDefault();
        //                return false;
        //            }
        //        }
        //        //禁止后退键 作用于Firefox、Opera
        //        document.onkeypress=doKey;
        //        //禁止后退键  作用于IE、Chrome
        //        document.onkeydown=doKey;

        $(document).on("keydown", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });
        $(document).on("keypress", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });

    </script>
</html>
