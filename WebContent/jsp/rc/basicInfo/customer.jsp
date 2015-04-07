<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		 var serverName="<%=path%>";
		 paramValidate=false;
		 var countryAreaCode="<%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>";
		 function operation(value,data){
			 var name = data.name;
			 var credentialsNumber = data.credentialsNumber;
			 var links = "";
			 <sec:authorize ifAnyGranted="${control.customerList_editCustomer}">
					if(links != ""){
						links = links +"|";
					}
					links = links+"<a href='javascript:updataCustomer();'   iconCls='icon-edit' class='easyui-linkbutton' plain='true'>编辑<a>";
	         </sec:authorize>
			  
				if(data.blackFlag == null || data.blackFlag == "" ){
					<sec:authorize ifAnyGranted="${control.customerList_returnVisit}">
						if(links != ""){
							links = links +"|";
						}
						links = links+"<a href='javascript:returnVisit();'  class='easyui-linkbutton' plain='true'>回访<a>";
		            </sec:authorize>
		            <sec:authorize ifAnyGranted="${control.customerList_blacklisted}">
			            if(links != ""){
							links = links +"|";
						}
						links = links+"<a href='javascript:void(0)'   onclick='blacklisted(\""+name+"\",\""+credentialsNumber+"\");'>加入黑名单<a>";
		            </sec:authorize>
				} 
				return links;
			}	
		 </script>
		<script type="text/javascript" src="<%=basePath%>scripts/basicInfo/customer.js">
	</script>
	</head>
	<body id="cc" class="easyui-layout" fit="true" style="border: 0px;">

		<div region="center" style="padding: 0px; border: 0px;">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="模糊搜索" style="padding: 10px;">
					搜索条件：
					<input name="fuzzyValue" id="fuzzyValue" style="width: 150px;" />
					<a class="easyui-linkbutton" href="javascript:searchFuzzy();">搜索</a>
					<a class="easyui-linkbutton" href="javascript:clearFuzzy();">清空</a>
					<font color="red">(可以按照借款人姓名，身份证号，联系电话 进行搜索)</font>
				</div>
				<div title="高级搜索" style="padding: 10px;">
					借款人姓名:
					<input type="text" id="name" />
					身份证号:
					<input size="25" type="text" id="credentialsNumber" onblur="validateNumber();" />
					电话:
					<input type="text" id="telephone" />
					客户类型:
					<input type="text" id="customerType" />
					<%--<td>
						所属乡镇:
					</td>
						<td><input  id="countyId" class="easyui-combobox" editable="false" style="width: 150px;"/> </td>
				     	<td><input  id="townId" class="easyui-combobox" editable="false" style="width: 150px;"/></td>
				     	<td><input  id="villageId" class="easyui-combobox" editable="false" style="width: 150px;"/></td>
				</tr>
				<tr>	
					
				</tr>
				<tr>
					--%>
					<a class="easyui-linkbutton" href="javascript:search12();">搜索</a>
					<a class="easyui-linkbutton" href="javascript:clear1();">清空</a>

				</div>
			</div>
			<div style="padding: 10px;">
				<table id="list">
				</table>
			</div>
		</div>
		<div id="dialog" style="padding: 10px; width: 800px;">
			<form id="fm" novalidate>
				<table id="tttt" title="借款人基本信息">
					<tr>
						<td align="right" width="115">
							姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
						</td>
						<td align="left" width="150">
							<input name="name" class="easyui-validatebox" required="true" style="width: 150px;" maxlength="10" />
						</td>
						<td width="150" align="right">
							&nbsp;
						</td>
						<td width="150" align="right">
							个人年收入(元)：
						</td>
						<td width="150" align="left">
							<input name="personIncome" style="width: 147px;" class="easyui-numberbox" validType="numberNonNegative" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号码：
						</td>
						<td align="left">
							<input name="mobilephone" class="easyui-numberbox" invalidMessage="请输入11位电话号码" required="true" style="width: 150px;" validType="length[11,11]" maxlength="11" />
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="right">
							婚姻状况：
						</td>
						<td align="left">
							<input name="maritalStatus" id="maritalStatus" style="width: 150px;" />
						</td>

					</tr>
					<tr>
						<td align="right">
							身份证号：
						</td>
						<td align="left" width="150">
							<input name="credentialsNumber" class="easyui-validatebox" id="credentialsNumber1" required="true" style="width: 150px;" validType="idnumberAll" onblur="validIDNumber();" maxlength="18" />
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="right">
							最高学历：
						</td>
						<td align="left">
							<input name="highestEducation" id="highestEducation" style="width: 150px;" />
						</td>
					</tr>
					<tr>
						<td align="right">
							性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
						</td>
						<td align="left">
							<input name="gender" id="gender" editable="false" style="width: 153px;" hasDownArrow="false" />
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="right">
							民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：
						</td>
						<td align="left">
							<input name="national" id="national" style="width: 150px;" />
						</td>
					</tr>

					<tr>
						<td align="right">
							选择居住地址：
						</td>
						<td>
							<input id="address" type="hidden">
								</select>
								<input id="sheng" class="easyui-combobox" id="prefecture1" style="width: 153px;" required="true"/>
						</td>
						<td>
							<input id="shi" class="easyui-combobox" id="town1" style="width: 150px;" editable="false" required="true"/>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td align="right">
						</td>
						<td>
							<input id="address" type="hidden">
								</select>
								<select name="countyId" class="easyui-combobox" id="prefecture1" style="width: 153px;" required="true"></select>
						</td>
						<td>
							<select name="townId" class="easyui-combobox" id="town1" style="width: 150px;" editable="false" required="true"></select>
						</td>
						<td>
							<select name="vallageId" class="easyui-combobox" id="village1" style="width: 150px;" editable="false" required="true">
							</select>
						</td>
						<td>
							<input style="width: 147px;" name="addressOther" id="addressOther" onkeydown="paramValidate=true;" onblur="addAddress(paramValidate);"   maxlength="10" />
						</td>
					</tr>

					<tr>
						<td align="right">
							家庭住址：
						</td>
						<td align="left" colspan="4">
							<input fit="true" style="width: 100%;" class="easyui-validatebox" name="presentAddress" id="presentAddress" required="true" maxlength="32" />
						</td>
					</tr>
					<tr>
						<td align="right">
							户籍地址：
						</td>
						<td align="left" colspan="4">
							<input fit="true" style="width: 100%;" class="easyui-validatebox" name="residenceAddress" id="residenceAddress" required="true" maxlength="32" />
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">
							备注：
						</td>
						<td align="left" colspan="4">
							<textarea name="remark" rows="2" fit="true" style="width: 100%;" maxlength="32"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
						</td>
						<td align="left">
							<input name="credentialsType" id="credentialsType" type="hidden" value="1" />
						</td>
						<td>
							<input name="operateName" type="hidden" />
						</td>
						<td>
							<input name="customerBasicId" id="customerBasicId" type="hidden" />
						</td>
						<td>
							<input name="operateId" type="hidden" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="buttons">
			<a id="savabutton" href="javascript:savaCustomer()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
			<a href="javascript:cencleDialog();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		</div>
		<div id="returnVisitDiv"></div>
		<div id="blacklistDialog" style="width: 700px; height: 250px;">
			<form id="blackForm" novalidate>
				<table width="100%">
					<tr>
						<td align="right">
							客户姓名：
						</td>
						<td>
							<input id="blackName" name="name" class="easyui-validatebox" required="true" size="30"/>
						</td>
						<td align="right">
							身份证号：
						</td>
						<td>
							<input id="blackCredentialsNumber" name="credentialsNumber" readonly="readonly" size="30"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td>
							&nbsp;
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4" align="center">
							<textarea id="blacklistedReason" name="blacklistedReason" class="easyui-validatebox" validType="length[0,250]" maxlength="250"  rows="3" cols="45" onclick="textCount('blacklistedReason', 'blacklistedReasonSpan', 250);" required="true" style="width: 98%; font-style: italic; color: black;"></textarea>
						</td>
					</tr>
					<tr>
						<td width="100px;" colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;<span id="blacklistedReasonSpan"></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>