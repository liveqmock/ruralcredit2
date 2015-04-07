<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <script type="text/javascript">
var serverName="<%=path%>";
paramValidate=false;
$(function(){
		  var dicVisit = new DataDictionary();
	 		dicVisit.addDic("borrowerName2_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName4_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName5_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName6_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName7_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName8_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName9_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName16_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName18_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName20_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName22_d_Y", "yesOrNo");
	 		dicVisit.addDic("borrowerName24_d_Y", "yesOrNo");
	 		dicVisit.dicAjax();
	 		/* 
	 		$("#borrowerName2").combobox({
	 			onSelect:function(value){
	 				if(value.codeKey == "0"){
	 					$("#borrowerName3").validatebox({
	 						required:true
	 					});
	 					$("#borrowerName3").validatebox("validate");
	 				}else{
	 					$("#borrowerName3").validatebox({
	 						required:false
	 					});
	 					$("#borrowerName3").validatebox("validate");
	 				}
	 			}
	 		});
	 		
			$("#borrowerName4").combobox({
	 			
	 		});
$("#borrowerName5").combobox({
		
	});
$("#borrowerName6").combobox({
		
	});
$("#borrowerName7").combobox({
		
	});
$("#borrowerName8").combobox({
		
	});
$("#borrowerName9").combobox({
		
	});
$("#borrowerName16").combobox({
		
	});
$("#borrowerName18").combobox({
		
	});
$("#borrowerName20").combobox({
		
	});
$("#borrowerName22").combobox({
		
	});
$("#borrowerName24").combobox({
	
});
	 		*/
	 		
 	});
	function addReturnVisit(){
		$("#submitButton").linkbutton("disable");
		if($("#visitForm").form("validate")){
			ajaxSubmit(serverName+"/ReturnVisit/addReturnVisit.do",$("#visitForm").serialize(),function(result){
				if(result.success){
					$("#submitButton").linkbutton("enable");
					$("#returnVisitDiv").dialog("close");
					$("#visitForm").form("clear");
					$.messager.show({title:"消息",msg:"保存成功"});
				}else{
					$("#submitButton").linkbutton("enable");
					$.messager.alert("消息",result.msg);
				}
				
			});
		}else{
			$("#submitButton").linkbutton("enable");
			$.messager.alert("消息","请把表单数据填写完整并正确！");
		}
	}
	function closeReturnDialog(){
		 $("#returnVisitDiv").dialog("close");
	}
 	</script>
 <div>
    <h2 class="centerTitle" align="center">客户回访调查表</h2>
	<form id="visitForm" novalidate>
	  <table width="754" align="center" cellpadding="3" cellspacing="0">
	    <tr>
	      <td style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;"colspan="4" align="center" class="fontTitle"> </td>
        </tr>
	    <tr>
	      <td width="120" align="right">借款人：</td>
	      <td width="150" align="left"><input   class="easyui-validatebox" value="张某" readonly="readonly" name="borrowerName" id="borrowerName" /></td>
	      <td width="173" align="right">客户经理：</td>
	      <td width="210"><input type="text" name="customManager" class="easyui-validatebox" value="崇信信贷经理" readonly="readonly" id="customManager" /></td>
        </tr>
	    <tr>
	      <td align="right">调查时间：</td>
	      <td align="left"><input class="easyui-datebox" required="true" editable="false"  name="contactTime" id="contactTime" /></td>
	      <td align="right">本次回访方式：</td>
	      <td><input class="easyui-validatebox"  type="text" name="contactWay" required="true"  id="contactTime2" maxlength="10"/>
	      </td>
        </tr>
	    <tr>
	      <td align="right">客户态度：</td>
	      <td align="left"><input type="text" class="easyui-validatebox" required="true" maxlength="10"  name="clienteleAttitude" id="contactTime3" /></td>
	      <td align="right"><input name="borrowerId" id="borrowerId" type="hidden"/>
	      					<input name="groupNumber" id="groupNumber" type="hidden">
	      </td>
	      <td>&nbsp;</td>
        </tr>
      </table>
	  <p>&nbsp;</p>
      <table width="753"   align="center" cellpadding="3" cellspacing="0">
  <tr>
    <th style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;" colspan="3" class="fontTitle" scope="col">客户近况</th>
    </tr>
  <tr>
    <td width="237"  align="left">联系方式变更是否变更?</td>
    <td width="200"><input type="text" name="contactChange"   id="borrowerName2" /></td>
    <td width="316" align="right"><input type="text" name="contactChangeDetail" id="borrowerName3" maxlength="60" class="easyui-validatebox"  size="40"/></td>
    </tr>
  <tr>
    <td align="left">家庭住址是否变更?</td>
    <td><input type="text" name="addressChange" id="borrowerName4"  /></td>
    <td align="right"><input type="text" name="addressChangeDetail" id="borrowerName10" maxlength="60" size="40"/></td>
    </tr>
  <tr>
    <td align="left">工作情况是否变动?</td>
    <td><input type="text" name="workingChange" id="borrowerName5"  /></td>
    <td align="right"><input type="text" name="workingChangeDetail" id="borrowerName11" maxlength="60" size="40"/></td>
    </tr>
  <tr>
    <td align="left">当月收入情况是否变化?</td>
    <td><input type="text" name="monthIncome" id="borrowerName6"  /></td>
    <td align="right"><input type="text" name="monthIncomeDetail" id="borrowerName12" maxlength="60" size="40"/></td>
    </tr>
  <tr>
    <td align="left">是否有大笔开支?</td>
    <td><input type="text" name="vastexpenseReason" id="borrowerName7"  /></td>
    <td align="right"><input type="text" name="vastexpenseReasonDetail" id="borrowerName13" maxlength="60" size="40"/></td>
    </tr>
  <tr>
    <td align="left">是否有还款压力?</td>
    <td><input type="text" name="paypressureReason" id="borrowerName8"  /></td>
    <td align="right"><input type="text" name="paypressureReasonDetail" id="borrowerName14" maxlength="60" size="40"/></td>
    </tr>
  <tr>
    <td align="left">家庭成员是否有变化?</td>
    <td><input type="text" name="familymembersChange" id="borrowerName9"  /></td>
    <td align="right"><input type="text" name="familymembersChangeDetail" id="borrowerName15" maxlength="60" size="40"/></td>
    </tr>
</table>
      <p>&nbsp;</p>
      <table width="752"  align="center" cellpadding="3" cellspacing="0">
        <tr>
          <th style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;" colspan="3" class="fontTitle" scope="col">担保人近况</th>
        </tr>
        <tr>
          <td width="237"   align="left">家庭状况是否稳定?</td>
          <td width="200"><input type="text" name="otherFamilystatusReason" id="borrowerName16"  /></td>
          <td width="315" align="right"><input type="text" name="otherFamilystatusDetail" id="borrowerName17" maxlength="60" size="40"/></td>
        </tr>
        <tr>
          <td align="left">工作/生意有无重大波动?</td>
          <td><input type="text" name="otherWorkingChange" id="borrowerName18"  /></td>
          <td align="right"><input type="text" name="otherWorkingDetail" id="borrowerName19" maxlength="60" size="40"/></td>
        </tr>
        <tr>
          <td align="left">有无大笔开支?</td>
          <td><input type="text" name="otherVastexpenseReason" id="borrowerName20"  /></td>
          <td align="right"> <input type="text" name="otherVastexpenseDetail" id="borrowerName21" maxlength="60" size="40"/></td>
        </tr>
        <tr>
          <td align="left">是否有还款压力?</td>
          <td><input type="text" name="otherPaypressureReason" id="borrowerName22"  /></td>
          <td align="right"><input type="text" name="otherPaypressureDetail" id="borrowerName23" maxlength="60" size="40"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="752"  cellpadding="3" cellspacing="0" align="center">
        <tr>
          <th style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;" colspan="3" class="fontTitle" scope="col">总结</th>
        </tr>
        <tr>
          <td width="237"   align="left"><p>是否需重点跟进?</p></td>
          <td width="200"><input type="text" name="keytoFollowup" id="borrowerName24"  /></td>
          <td width="315" align="right"><input type="text" name="keytoFollowupDetail" id="borrowerName25" maxlength="60" size="40"/></td>
        </tr>
        <tr>
          <td width="237"   align="left"></td>
          <td width="200" align="right"> <p><a id="submitButton" class="easyui-linkbutton" onclick="jacascript:addReturnVisit();">保存</a></p></td>
          <td width="264" align="left"><a class="easyui-linkbutton" onclick="jacascript:closeReturnDialog();">取消</a> </td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </form>
</div> 
