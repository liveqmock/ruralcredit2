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
      <%--<script type="text/javascript" >
          function updateIdInfoSumbit(){
              console.info("--------eeeeeeeeee---------");
              var url = serverName + '/application/updateIdInfo.do?';
              var  obj = new Object();
              obj["group"] =  JSON.stringify($("#group").serializeObject());
              ajaxSubmit(url,"post",obj, resFunc,false);
          }
          // 提交小组信息后回调 页面回显
          function resFunc(result){
              if(result != null && result != undefined){
                  $.messager.alert("提示信息","更改身份证信息成功！");
                  return;
              }else{
                  $.messager.alert("错误","保存失败！系统错误，请与管理员联系！");
              }
          }
      </script>--%>
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
  
  <body id="cc" class="easyui-layout" fit="true" >
		<div region="center">
            <form id="group" novalidate>
                <p>&nbsp;</p>
                <table>
                    <tr><td>
                        <table align="left">
                            <tbody id="tbody">
                            <tr >
                                <td colspan="6" align="center"   style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
                                    <span style="font-size: 15px; font-weight: 700;"> 借款人信息</span>
                                </td>
                            </tr>
                            <tr width="100%">
                                <td >借款人姓名：</td>
                                <td >借款人身份证号:</td>
                                <td >借款人年龄:</td>
                            </tr>
                            <tr width="100%">
                                <td><input  value="${idInfoVo.borrowerInfo.name }"   readonly="readonly"   name="borrowerInfo.name" type="text" /></td>
                                <td><input  value="${idInfoVo.borrowerInfo.credentialsNumber }"    name="borrowerInfo.credentialsNumber" type="text"  class="easyui-validatebox" validType="idnumberAll"   onblur="generateAge(this,'borrowerServiceId');"/></td>
                                <td><input  value="${idInfoVo.borrowerInfo.age }"   readonly="readonly"   name="borrowerInfo.age" id="borrowerServiceId" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3" size="15"/></td>
                                <input  value="${idInfoVo.borrowerInfo.customerBasicId }"    name="borrowerInfo.customerBasicId" type="hidden" />
                                <input  value="${idInfoVo.borrowerInfo.borrowerServiceAppId }"   name="borrowerInfo.borrowerServiceAppId" type="hidden" />
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </td></tr>
                    <tr><td>
                        <table    align="left" >
                            <tr width="100%">
                                <td colspan="6"  align="center"   style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
                                    <span style="font-size: 15px; font-weight: 700;">共借人信息</span>
                                </td>
                            </tr>
                            <tr width="100%">
                                <td >共借人姓名:</td>
                                <td >共借人身份证号:</td>
                                <td >共借人年龄:</td>
                            </tr>

                            <tr width="100%">
                                <td><input  value="${idInfoVo.cb.name }" readonly="readonly"    name="cb.name" maxlength="10" /></td>
                                <td><input  value="${idInfoVo.cb.idNumber }"  name="cb.idNumber"  type="text"  maxlength="18"  class="easyui-validatebox" validType="idnumberAll"  onblur="generateAge(this,'creditCoBorrowerId');" /></td>
                                <td><input  value="${idInfoVo.cb.age }"   readonly="readonly" name="cb.age"    id="creditCoBorrowerId" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3" size="15"/></td>
                                <input  value="${idInfoVo.cb.borrowerServiceAppId }"   name="cb.borrowerServiceAppId" type="hidden" />
                                <input  value="${idInfoVo.cb.coBorrowerId }"   name="cb.coBorrowerId" type="hidden" />
                                </td>
                            </tr>
                        </table>


                    </td></tr>

                    <c:if test="${fn:length(idInfoVo.guarantListInfo)>0}">
                    <tr><td>
                        <table align="left" >
                            <tr>
                                <td colspan="6"  align="center"  style="border-bottom-color:navy;border-bottom-style: solid;border-bottom-width: thin;">
                                    <span style="font-size: 15px; font-weight: 700;">担保人信息</span>
                                </td>
                            </tr>
                            <tr width="100%">
                                <td >姓名</td>
                                <td >身份证号</td>
                                <td >年龄</td>
                            </tr>

                            <c:forEach items="${idInfoVo.guarantListInfo}" var="borrowerInfoVar" varStatus="borrowerC" begin="0">
                                <tr width="100%">
                                    <td ><input  value="${borrowerInfoVar.name }" readonly="readonly"   type="text" name="guarantListInfo[${borrowerC.count-1}].name"  /></td>
                                    <td><input  value="${borrowerInfoVar.credentialsNumber }" type="text"  name="guarantListInfo[${borrowerC.count-1}].credentialsNumber" class="easyui-validatebox" validType="idnumberAll"  onblur="generateAge(this,'guarantListInfoId${borrowerC.count-1}');" />        </td>
                                    <td ><input  value="${borrowerInfoVar.age }" readonly="readonly"    name="guarantListInfo[${borrowerC.count-1}].age"  id="guarantListInfoId${borrowerC.count-1}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" validType="number" maxlength="3" size="15" /></td>
                                    <input  value="${borrowerInfoVar.customerBasicId}" name="guarantListInfo[${borrowerC.count-1}].customerBasicId" type="hidden"  />
                                    <input  value="${borrowerInfoVar.borrowerServiceAppId}" name="guarantListInfo[${borrowerC.count-1}].borrowerServiceAppId" type="hidden"  />
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                    </td></tr>
                        </c:if>
                   <%-- <tr><td>
                        <table align="center">
                            <tr>
                                <td  align="center">
                                    <input type="button" onclick="submitFun();"  class="easyui-linkbutton"  value="提交" />
                                </td>
                            </tr>
                        </table>
                    </td></tr>--%>
                </table>
            </form>
        </div>
  </body>
  <script type="text/javascript">
      function submitFun(){
          var url = serverName + '/application/updateIdInfo.do?';
          var  obj = new Object();
          console.info("----serizlize-----"+JSON.stringify($("#group").serializeObject()));
           $('#group').form('submit', {
             url:url,
             success: function(data){
                 var result = eval('(' + data + ')');  // change the JSON string to javascript object
                 console.info("------result1-------"+result);
                 if(result != null && result != undefined && "true" == $.trim(result)){
                    // $.messager.alert("提示信息","更改身份证信息成功！");
                     parent.$("#idChangeDiv").dialog("close");
                 }else{
                     $.messager.alert("错误","保存失败！系统错误，请与管理员联系！");
                 }
             }
          });

      }
      // 提交小组信息后回调 页面回显
      function resFunc(result){
          if(result != null && result != undefined){
              $.messager.alert("提示信息","更改身份证信息成功！");
              return;
          }else{
              $.messager.alert("错误","保存失败！系统错误，请与管理员联系！");
          }
      }
     function generateAge(idNumberObj,ageId){
         var IdNumber = idNumberObj.value;
        console.info("IdNumber:"+IdNumber+"---------ageId:"+ageId+"-----setAge()"+setAge(IdNumber));
         $("#"+ageId).numberbox("setValue",setAge(IdNumber));
         console.info( "value-------"+ $("#"+ageId).numberbox("getValue"));
     }
      // 按照身份证 生成年龄
      function setAge(sid) {
          var ageValue = "";
          var len = sid.length;
          if(len == 18){
              var birthYearMonth = sid.substr(6, 6);
          } else if (len == 15){
              var birthYearMonth = 19 + sid.substr(6, 4);
          }
          if (birthYearMonth == null) {
              ageValue = "";
          } else {
              var today = new Date();
              ageValue = today.getFullYear()*100+today.getMonth()+1 - birthYearMonth;
          }
          if(Math.floor(ageValue/100) == 0){
              return "";
          }else{
              return Math.floor(ageValue/100);
          }
      }
  </script>
</html>
