<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <script type="text/javascript">
var serverName="<%=basePath%>";
$(function(){
	var dicBorrower = new DataDictionary();
	dicBorrower.addDic("maritalStatusBorrower_borrowerService.maritalStatus_Y", "maritalStatus");
	dicBorrower.addDic("jobStatusBorrower_borrowerService.jobStatus_Y", "jobStatus");
	dicBorrower.addDic("nationality_borrowerService.nationality_Y", "national");
	dicBorrower.addDic("genderBorrower", "gender");
	dicBorrower.addDic("borrowerreRationship0_familymemberList[0].borrowerreRationship_Y", "borrowerreRationship");
	dicBorrower.addDic("borrowerreRationship1", "borrowerreRationship");
	dicBorrower.addDic("borrowerreRationship2", "borrowerreRation");
	dicBorrower.addDic("borrowerreRationship3", "borrowerreRation");
	dicBorrower.addDic("borrowerreRationship4", "borrowerreRation");
	dicBorrower.addDic("profession0_familymemberList[0].profession_Y", "profession");
	dicBorrower.addDic("profession1", "profession");
	dicBorrower.addDic("profession2", "profession");
	dicBorrower.addDic("profession3", "profession");
	dicBorrower.addDic("profession4", "profession");
	dicBorrower.addDic("businessLicense0", "yesno");
	dicBorrower.addDic("businessLicense1", "yesno");
	dicBorrower.addDic("businessLicense2", "yesno");
	dicBorrower.addDic("taxRegistrationCertificate0", "yesno");
	dicBorrower.addDic("taxRegistrationCertificate1", "yesno");
	dicBorrower.addDic("taxRegistrationCertificate2", "yesno");
	dicBorrower.addDic("proveDocument0", "yesno");
	dicBorrower.addDic("proveDocument1", "yesno");
	dicBorrower.addDic("proveDocument2", "yesno");
	dicBorrower.addDic("proveDocumentApplication0_applicationList[0].proveDocument_Y", "yesno");
	dicBorrower.addDic("proveDocumentApplication1", "yesno");
	dicBorrower.addDic("proveDocumentApplication2", "yesno");
	
	//dicBorrower.addDic("operatingItems0", "operatingItems");
	//dicBorrower.addDic("operatingItems1", "operatingItems");
	//dicBorrower.addDic("operatingItems2", "operatingItems");
	dicBorrower.addDic("stateSurveybusinessinfoList0", "stateSurveybusinessinfoList");
	dicBorrower.addDic("stateSurveybusinessinfoList1", "stateSurveybusinessinfoList");
	dicBorrower.addDic("stateSurveybusinessinfoList2", "stateSurveybusinessinfoList");
	dicBorrower.addDic("depositOrganization0", "depositOrganization");
	dicBorrower.addDic("depositOrganization1", "depositOrganization");
	dicBorrower.addDic("depositOrganization2", "depositOrganization");
	dicBorrower.addDic("borrowUse0_applicationList[0].borrowUse_Y", "borroerUserConsult");
	dicBorrower.addDic("borrowUse1", "borroerUserConsult");
	dicBorrower.addDic("borrowUse2", "borroerUserConsult");
	dicBorrower.dicAjax();
});
/**
 * 失去焦点时判断申请金额和产品是否匹配
 */
function validateCapitaLimit(){
	var applyLimitBorrower=$("#applyLimitBorrower");
	var capitalUpperLimit=$("#capitalUpperLimit").val();
	var capitalLowerLimit=$("#capitalLowerLimit").val();
	if(applyLimitBorrower.val()==""){
		return;
	}
	if(capitalUpperLimit==""){
		capitalUpperLimit=0;
	}
	if(capitalLowerLimit==""){
		capitalLowerLimit=0;
	}
	if(parseFloat(applyLimitBorrower.val())<=parseFloat(capitalUpperLimit) && parseFloat(applyLimitBorrower.val()) >=parseFloat(capitalLowerLimit) ){
 		
 	}else{
 		$.messager.alert("消息","申请额度不符合该产品额度,产品中额度上限="+capitalUpperLimit+";额度下限="+capitalLowerLimit);
 		applyLimitBorrower.val("");
 		return ;
 	}
	
}
</script>
<div>
	<form id="formBorrrower" name="form1" method="post" >
		<table width="892"   align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="5" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
					个人申请
					<input readonly id="borrowerServiceAppId" name="borrowerService.borrowerServiceAppId" type="hidden"> 
					<input readonly id="creditapplicationIdBorrower" name="borrowerService.creditapplicationId" type="hidden">
					<!-- 产品上限 -->
					<input readonly id="capitalUpperLimit" name="creditApplication.capitalUpperLimit" type="hidden">
					<!-- 产品下限 -->
					<input readonly id="capitalLowerLimit" name="creditApplication.capitalLowerLimit" type="hidden">
				</td>
			</tr>
		  <tr>
		    <td width="149" align="right">业务编号：</td>
		    <td width="193"><input readonly type="text"   id="businessNumberBorrower"    style="width: 150px;"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">民族：</td>
		    <td width="207"><input readonly type="text" name="borrowerService.nationality"   id="nationality"   style="width: 150px;"/></td>
		  </tr>
		   <tr>
		    <td width="149" align="right">申请金额(元)：</td>
		    <td width="193"><input readonly type="text" name="borrowerService.applyLimit" id="applyLimitBorrower"  class="easyui-validatebox" validType="number" onblur="validateCapitaLimit()"  style="width: 150px;"/></td>
		    <td width="132">&nbsp;</td>
		    <td width="169" align="right">身份证：</td>
		    <td width="207"><input readonly type="text" name="borrowerService.credentialsNumber" id="credentialsNumberBorrrower"   style="width: 150px;"/></td>
		  </tr>
		  <tr>
		    <td align="right">姓名：</td>
		    <td><input readonly type="text" name="borrowerService.name" id="nameBorrow" class="easyui-validatebox"   style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">曾用名：</td>
		    <td><input readonly type="text" name="borrowerService.former" id="businessNumber5"  style="width: 150px;"/></td>
		  </tr>
		  <tr>
		    <td align="right">出生日期：</td>
		    <td><input readonly type="text" name="borrowerService.birthday" id="businessNumber3"   style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">性别：</td>
		    <td><input hasDownArrow="false" name="borrowerService.gender" id="genderBorrower"  style="width: 150px;"/></td>
		  </tr>
		   <tr>
		    <td align="right">婚姻状况：</td>
		    <td> 
		      <input hasDownArrow="false" name="borrowerService.maritalStatus"  id="maritalStatusBorrower"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">就业状况：</td>
		    <td> 
		      <input hasDownArrow="false" name="borrowerService.jobStatus"  id="jobStatusBorrower"  style="width: 150px;"/></td>
		  </tr>
		  <tr>
		  	  <td align="right" valign="top">家庭住址：</td>
		  	  <td>
		  	    <textarea style="resize:none;" name="borrowerService.homeAddress" rows="3" cols="6"  id="homeAddress" style="width: 347px;"readonly="readonly">
        
        </textarea>
		  	  </td>
		  	  <td></td>
		  	    <td align="right" valign="top">现经营场所地址：</td>
		  	  <td>
		  	    <textarea style="resize:none;" name="borrowerService.residenceAddress" rows="3" cols="6"  id="businessAddress" style="width: 347px;"readonly="readonly">
        
        </textarea>
		  	  </td>
		  </tr>
		  <tr>
		    <td align="right" valign="top">家庭住址：</td>
		    <td><p>
		      <label>
		        <input readonly type="checkbox" value="1" name="borrowerService.livingCommercial" />
		        自有商品房</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" value="1" name="borrowerService.livingSelf"   id="CheckboxGroup1_1" />
		        自有宅基地</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" value="1" name="borrowerService.livingRent" onclick="livingRentDate();"  id="livingRent" />
		        租住</label>
		      （到期日<span id="livingdateDiv" >
		      </span>
		      ）<br />
		      <label>
		        <input readonly type="checkbox" name="borrowerService.livingRelative" value="1" id="CheckboxGroup1_3" />
		        亲戚住房</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" name="borrowerService.livingOther" value="1" />
		        其他</label>
		      <br />
		    </p></td>
		    <td>&nbsp;</td>
		    <td align="right" valign="top">现经营场所地址：</td>
		    <td><label>
		      <input readonly type="checkbox" onclick="changsuo();" name="borrowerService.businessCommercial" value="1"  />
		      自有商品房</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" onclick="changsuo();" name="borrowerService.businessSelf" value="1" id="CheckboxGroup1_6" />
		        自有宅基地</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" onclick="changsuobusinessRentDate();" name="borrowerService.businessRent"   id="businessRent" value="1" />
		        租住</label>
		      （到期日<span id="businessDateDiv" >
			</span>
		）<br />
		       <label>
		        <input readonly type="checkbox" onclick="changsuo();" name="borrowerService.businessRelative" value="1" id="CheckboxGroup1_8" />
		        亲戚住房</label>
		      <br />
		      <label>
		        <input readonly type="checkbox" onclick="changsuo();" name="borrowerService.businessOther" value="1" id="CheckboxGroup1_9" />
		        其他</label></td>
		    </tr>
		    <tr>
		    <td align="right">家庭住址面积(㎡)：</td>
		    <td><input disabled type="text" name="borrowerService.livingArea" id="businessNumber8"  class="easyui-validatebox" validType="number"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所面积(㎡)：</td>
		    <td><input disabled type="text" name="borrowerService.businessArea" id="businessArea"   style="width: 150px;"/></td>
		    </tr>
		  <tr>
		    <td align="right">家庭住址居住年限(年)：</td>
		    <td><input disabled type="text" name="borrowerService.livingYear" id="businessNumber6"  class="easyui-validatebox" validType="number"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">经营场所已使用年限(年)：</td>
		    <td><input disabled type="text" name="borrowerService.businessYear" id="businessYear"    style="width: 150px;"/></td>
		  </tr>
		    <tr>
		    <td align="right">手机号码：</td>
		    <td><input disabled type="text" name="borrowerService.mobilephone" id="mobilephoneBorrow"  class="easyui-validatebox" validType="phoneNumber"  style="width: 150px;"/></td>
		    <td>&nbsp;</td>
		    <td align="right">办公电话：</td>
		    <td><input disabled type="text" name="borrowerService.officePhone" id="officePhone"  style="width: 150px;"/></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>										
	</form>
<form id="formOther" novalidate>
 <p>&nbsp;	</p>
  <table width="898"   align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="7" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">配偶信息</td>
    </tr>
    <tr>
      <td width="149">姓名</td>
      <td width="199">与申请人关系</td>
      <td width="155">身份证号</td>
      <td width="90">年龄</td>
      <td width="155">职业</td>
      <td width="185">职业明细</td>
      <td width="155">联系电话</td>
    </tr>
   
	<tr>
      <td><input readonly class="easyui-validatebox"   name="familymemberList[0].name" maxlength="10" /></td>
      <td><input hasDownArrow="false"  name="familymemberList[0].borrowerreRationship" id="borrowerreRationship0" value="2"   size="15"/></td>
      <td><input readonly  name="familymemberList[0].idNumber" id="idNumber0" class="easyui-validatebox" validType="idnumberAll" size="25"/></td>
     <td><input readonly class="easyui-numberbox"   name="familymemberList[0].age" onkeypress="if(event.which==45){return false;}"  validType="number" maxlength="3" size="15"/></td>
       <td><input hasDownArrow="false"    name="familymemberList[0].profession" id="profession0" size="15"/></td>
      <td><input readonly class="easyui-validatebox"   name="familymemberList[0].professionDetail" maxlength="20"/></td>
      <td><input readonly class="easyui-validatebox"   name="familymemberList[0].telphone" validType="phoneNumber" maxlength="11"/></td>
    </tr>
  </table>
  <p>&nbsp;	</p>
  <table width="898"   align="center" cellpadding="3" cellspacing="0">
     <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">其他家庭成员情况</td>
    </tr>
    <tr>
     <td width="149">姓名</td>
      <td width="199">与申请人关系</td>
      <td width="168">年龄</td>
      <td width="185">职业</td>
      <td width="185">职业明细</td>
      <td width="155">联系电话</td>
     </tr>
   	<tr>
      <td><input readonly type="text" name="familymemberList[1].name" id="businessNumber16" maxlength="10"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[1].borrowerreRationship" id="borrowerreRationship1" /></td>
      <td><input readonly type="text" name="familymemberList[1].age" id="businessNumber18" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[1].profession" id="profession1"/></td>
      <td><input readonly name="familymemberList[1].professionDetail" maxlength="20"/></td>
      <td><input readonly type="text" name="familymemberList[1].telphone" id="businessNumber20" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
    </tr>
    <tr>
      <td><input readonly type="text" name="familymemberList[2].name" id="businessNumber21" maxlength="10"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[2].borrowerreRationship" id="borrowerreRationship2" /></td>
      <td><input readonly type="text" name="familymemberList[2].age" id="businessNumber23" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[2].profession" id="profession2"/></td>
      <td><input readonly name="familymemberList[2].professionDetail" maxlength="20"/></td>
      <td><input readonly type="text" name="familymemberList[2].telphone" id="businessNumber25" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
    </tr>
    <tr>
      <td><input readonly type="text" name="familymemberList[3].name" id="businessNumber26" maxlength="10"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[3].borrowerreRationship" id="borrowerreRationship3" /></td>
      <td><input readonly type="text" name="familymemberList[3].age" id="businessNumber28" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td><input hasDownArrow="false" type="text" name="familymemberList[3].profession" id="profession3"/></td>
      <td><input readonly name="familymemberList[3].professionDetail" maxlength="20"/></td>
      <td><input readonly type="text" name="familymemberList[3].telphone" id="businessNumber30" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
    </tr>
    <tr>
      <td width="168"><input readonly type="text" name="familymemberList[4].name" id="businessNumber43" maxlength="10"/></td>
      <td width="177"><input hasDownArrow="false" type="text" name="familymemberList[4].borrowerreRationship" id="borrowerreRationship4" /></td>
      <td width="168"><input readonly type="text" name="familymemberList[4].age" id="businessNumber45" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3"/></td>
      <td width="175"><input hasDownArrow="false" type="text" name="familymemberList[4].profession" id="profession4"/></td>
      <td><input readonly name="familymemberList[4].professionDetail" maxlength="20"/></td>
      <td width="168"><input readonly type="text" name="familymemberList[4].telphone" id="businessNumber47" class="easyui-validatebox" validType="phoneNumber" maxlength="11"/></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="898"  align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">相关工作情况</td>
    </tr>
    <tr>
      <td width="185">就职公司</td>
      <td width="153">地址</td>
      <td width="139">工作年限</td>
      <td width="131">职位</td>
      <td width="126">每月净收入(元)</td>
    </tr>
    <tr>
      <td><input readonly  class="easyui-validatebox"  name="jobInfoList[0].company" type="text" id="businessNumber31" size="18" /></td>
      <td><input readonly  class="easyui-validatebox"  name="jobInfoList[0].workunitAddress" type="text" id="businessNumber32" size="18" /></td>
      <td><input readonly  class="easyui-validatebox"  name="jobInfoList[0].years" type="text" id="businessNumber33" size="18" validType="number"/></td>
      <td><input readonly  class="easyui-validatebox"  name="jobInfoList[0].post" type="text" id="businessNumber34" size="18" /></td>
      <td><input readonly  class="easyui-numberbox"  name="jobInfoList[0].income" type="text" min="0" precision="2" size="18" /></td>
    </tr>
    <tr>
      <td><input readonly name="jobInfoList[1].company" type="text" id="businessNumber37" size="18" /></td>
      <td><input readonly name="jobInfoList[1].workunitAddress" type="text" id="businessNumber38" size="18" /></td>
      <td><input readonly name="jobInfoList[1].years" type="text" id="businessNumber39" size="18" class="easyui-validatebox" validType="number"/></td>
      <td><input readonly name="jobInfoList[1].post" type="text" id="businessNumber40" size="18" /></td>
      <td><input readonly name="jobInfoList[1].income" class="easyui-numberbox"  type="text" min="0" precision="2" size="18" /></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="898"  align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">经营情况（包括当前和历史的经营情况）</td>
    </tr>
    <tr>
      <td width="193">经营项目详细</td>
      <td width="154">起始年份</td>
      <td width="146">状态</td>
      <td width="94">营业执照</td>
      <td width="85">税务登记证</td>
    </tr>
    <tr>
      <td><input readonly   class="easyui-validatebox"  name="surveybusinessinfoList[0].operatingItemsDetail" type="text" id="businessNumber48" size="20" /></td>
      <td><input readonly   class="easyui-validatebox" validType="yearValidate"  name="surveybusinessinfoList[0].startingDate" type="text" id="businessNumber53" size="20" /></td>
      <td><input hasDownArrow="false"   name="surveybusinessinfoList[0].state" id="stateSurveybusinessinfoList0"></input></td>
      <td><input hasDownArrow="false"   name="surveybusinessinfoList[0].businessLicense" id="businessLicense0"> </input></td>
      <td><input hasDownArrow="false"   name="surveybusinessinfoList[0].taxRegistrationCertificate" id="taxRegistrationCertificate0">
      </input></td>
    </tr>
    <tr>
      <td><input readonly name="surveybusinessinfoList[1].operatingItemsDetail" type="text" id="businessNumber49" size="20" /></td>
      <td><input readonly name="surveybusinessinfoList[1].startingDate" type="text" id="businessNumber52" size="20" class="easyui-validatebox" validType="yearValidate"/></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[1].state" id="stateSurveybusinessinfoList1">
      </input></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[1].businessLicense" id="businessLicense1">
      </input></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[1].taxRegistrationCertificate" id="taxRegistrationCertificate1">
      </input></td>
    </tr>
    <tr>
      <td><input readonly name="surveybusinessinfoList[2].operatingItemsDetail" type="text" id="businessNumber50" size="20" /></td>
      <td><input readonly name="surveybusinessinfoList[2].startingDate" type="text" id="businessNumber51" size="20" class="easyui-validatebox" validType="yearValidate"/></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[2].state" id="stateSurveybusinessinfoList2">
      </input></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[2].businessLicense" id="businessLicense2">
      </input></td>
      <td><input hasDownArrow="false" name="surveybusinessinfoList[2].taxRegistrationCertificate" id="taxRegistrationCertificate2">
      </input></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="898" align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">存款和应收现金</td>
    </tr>
    <tr>
      <td width="192">存款机构或应支付现金方</td>
      <td width="196">存款机构或应支付现金方</td>
      <td width="140">地址</td>
      <td width="152">电话</td>
      <td width="112">余额(元)</td>
      <td width="68">证明文档</td>
    </tr>
    <tr>
      <td><input hasDownArrow="false"   name="depositList[0].depositOrganization" id="depositOrganization0">
      </input></td>
      <td><input readonly  class="easyui-validatebox"  name="depositList[0].depositOrganizationDetail" type="text" id="businessNumber66" size="20" /></td>
      <td><input readonly  class="easyui-validatebox"  name="depositList[0].address" type="text" id="businessNumber67" size="20" /></td>
      <td><input readonly  class="easyui-validatebox"  name="depositList[0].telephone" type="text" id="businessNumber68" size="20"  validType="phoneNumber"/> </td>
      <td><input readonly   class="easyui-numberbox"   name="depositList[0].balance" type="text" id="balance0"  size="16" min="0" precision="2"/></td>
      <td><input hasDownArrow="false"   name="depositList[0].proveDocument" id="proveDocument0">
      </input></td>
    </tr>
    <tr>
      <td><input hasDownArrow="false" name="depositList[1].depositOrganization" id="depositOrganization1">
      </input></td>
      <td><input readonly name="depositList[1].depositOrganizationDetail" type="text" id="businessNumber70" size="20" /></td>
      <td><input readonly name="depositList[1].address" type="text" id="businessNumber71" size="20" /></td>
      <td><input readonly name="depositList[1].telephone" type="text" id="businessNumber72" size="20" class="easyui-validatebox" validType="phoneNumber"/></td>
      <td><input readonly name="depositList[1].balance" type="text" id="balance1"  size="16"  class="easyui-numberbox" min="0" precision="2"/></td>
      <td><input hasDownArrow="false" name="depositList[1].proveDocument" id="proveDocument1">
      </input></td>
    </tr>
    <tr>
      <td><input hasDownArrow="false" name="depositList[2].depositOrganization" id="depositOrganization2">
      </input></td>
      <td><input readonly name="depositList[2].depositOrganizationDetail" type="text" id="businessNumber87" size="20" /></td>
      <td><input readonly name="depositList[2].address" type="text" id="businessNumber88" size="20" /></td>
      <td><input readonly name="depositList[2].telephone" type="text" id="businessNumber89" size="20" class="easyui-validatebox" validType="phoneNumber"/></td>
      <td><input readonly name="depositList[2].balance" type="text" id="balance2"  size="16"  class="easyui-numberbox" min="0" precision="2"/></td>
      <td><input hasDownArrow="false" name="depositList[2].proveDocument" id="proveDocument2">
      </input></td>
    </tr>
    <tr>
      <td colspan="4" align="right">汇总(元)：</td>
      <td><input readonly name="businessNumber66" type="text" id="totalBlance" size="16" /></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  
  <table width="899"  align="center" cellpadding="3" cellspacing="0">
    <tr>
      <td colspan="6" align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">借款申请</td>
    </tr>
    <tr>
      <td width="197">借款使用计划</td>
      <td width="163">借款使用计划详细</td>
      <td width="140">数量</td>
      <td width="140">单价(元)</td>
      <td width="145">总额(元)</td>
      <td width="76">证明文档</td>
    </tr>
    <tr>
      <td><input hasDownArrow="false"   name="applicationList[0].borrowUse" id="borrowUse0"></input></td>
      <td><input readonly  class="easyui-validatebox"   name="applicationList[0].borrowUseDetail" type="text" id="businessNumber891" size="20" /></td>
      <td><input readonly  class="easyui-validatebox"   name="applicationList[0].quantity" type="text" id="quantity0"  size="20"  validType="number"/></td>
      <td><input readonly  class="easyui-numberbox"     name="applicationList[0].unitPrice" type="text" id="unitPrice0"  size="20"  min="0" precision="2"/></td>
      <td><input readonly   name="applicationList[0].totalAmount" type="text" id="totalAmount0" size="20"  /></td>
      <td><input hasDownArrow="false"    name="applicationList[0].proveDocument" id="proveDocumentApplication0">
      </input></td>
    </tr>
    <tr>
      <td><input hasDownArrow="false" name="applicationList[1].borrowUse" id="borrowUse1">
      </input></td>
      <td><input readonly name="applicationList[1].borrowUseDetail" type="text" id="businessNumber89rr" size="20" /></td>
      <td><input readonly name="applicationList[1].quantity" type="text" id="quantity1"  size="20" class="easyui-validatebox" validType="number"/></td>
      <td> 
        <input readonly name="applicationList[1].unitPrice" type="text" id="unitPrice1"  size="20" class="easyui-numberbox" min="0" precision="2"/>
       </td>
      <td><input readonly name="applicationList[1].totalAmount" type="text" id="totalAmount1" size="20" /></td>
      <td><input hasDownArrow="false" name="applicationList[1].proveDocument" id="proveDocumentApplication1">
      </input></td>
    </tr> 
    <tr>
      <td><input hasDownArrow="false" name="applicationList[2].borrowUse" id="borrowUse2">
      </input></td>
      <td><input readonly name="applicationList[2].borrowUseDetail" type="text" id="busqNumber89sdgw34" size="20" /></td>
      <td><input readonly name="applicationList[2].quantity" type="text" id="quantity2"  size="20" class="easyui-validatebox" validType="number"/></td>
      <td> 
        <input readonly name="applicationList[2].unitPrice" type="text" id="unitPrice2"  size="20" class="easyui-numberbox" min="0" precision="2"/>
       </td>
      <td><input readonly name="applicationList[2].totalAmount" type="text" id="totalAmount2" size="20"  /></td>
      <td><input hasDownArrow="false" name="applicationList[2].proveDocument" id="proveDocumentApplication2">
      </input></td>
    </tr>
     <tr>
      <td colspan="4" align="right">汇总(元)：  <td><input readonly name="businessNumber65" type="text" id="TTAmount" size="20"  /></td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
 			<input readonly name="familymemberList[0].familyMemberId" type="hidden" />
			<input readonly name="familymemberList[1].familyMemberId" type="hidden" />
			<input readonly name="familymemberList[2].familyMemberId" type="hidden" />
			<input readonly name="familymemberList[3].familyMemberId" type="hidden" />
			<input readonly name="familymemberList[4].familyMemberId" type="hidden" />
			<input readonly name="jobInfoList[0].jobInfoId" type="hidden" />
			<input readonly name="jobInfoList[1].jobInfoId" type="hidden" />
			<input readonly name="surveybusinessinfoList[0].surveyBusinessInfoId"
				type="hidden" />
			<input readonly name="surveybusinessinfoList[1].surveyBusinessInfoId"
				type="hidden" />
			<input readonly name="surveybusinessinfoList[2].surveyBusinessInfoId"
				type="hidden" />
			<input readonly name="depositList[0].depositid" type="hidden" />
			<input readonly name="depositList[1].depositid" type="hidden" />
			<input readonly name="depositList[2].depositid" type="hidden" />
			<input readonly name="applicationList[0].applicationid" type="hidden" />
			<input readonly name="applicationList[1].applicationid" type="hidden" />
			<input readonly name="applicationList[2].applicationid" type="hidden" />
</form>
</div>