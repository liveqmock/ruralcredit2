<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils,com.creditease.rc.util.PropertiesUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
String cmUrl = properties.getProperty("cm.url");
String userId = SpringSecurityUtils.getCurrentUser().getUserId();

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
java.util.Date currentTime = new java.util.Date();
String str_dataFormatter = formatter.format(currentTime);
String str_dataString = currentTime.toString();
String auditStatus=request.getParameter("auditStatus");
String customerConsultId = request.getParameter("customerConsultId");//客户咨询id
String consultPoolId=request.getParameter("consultPoolId");	//咨询池id
String validState = request.getParameter("validState");//可查询删除的标记
String role=request.getParameter("role");
//该属性是用来判断是否是从首页的“风险单提醒” 点进来的  不为空时就是首页 begin
String fxB=request.getParameter("fxB");
//end
StringBuffer conditions=new StringBuffer("");
String separator="?";

if(CommonUtil.isNotEmpty(fxB)){
	conditions.append("?fxB=").append(fxB);
	separator="&";
}
if(CommonUtil.isNotEmpty(auditStatus)){
	conditions.append("?auditStatus=").append(auditStatus);
	separator="&";
}
if(CommonUtil.isNotEmpty(role)){
	conditions.append(separator).append("role=").append(role);
	separator="&";
}
//客户咨询Id
if(CommonUtil.isNotEmpty(customerConsultId)){
	conditions.append(separator).append("customerConsultId=").append(customerConsultId);
	separator="&";
}

//咨询池Id
if(CommonUtil.isNotEmpty(consultPoolId)){
	conditions.append(separator).append("consultPoolId=").append(consultPoolId);
	separator="&";
}

if(CommonUtil.isNotEmpty(customerConsultId)){
	conditions.append(separator).append("validState=").append(validState);
	separator="&";
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
		<script type="text/javascript" >
			var serverName="<%=path%>";
			var flag = false;
			var sysDate="<%=str_dataFormatter%>";
			var conditions="";
			<%if(CommonUtil.isNotEmpty(conditions.toString())){%>
				conditions="<%=conditions.toString()%>";
			<%}%>
			 var cmUrl = "<%=cmUrl%>";
			 var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;
			 var userId = "<%=userId%>";
			 
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/creditApplication.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/creditBackgroundInvestigation.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/specialApplyApplication.js"></script>
		
		<script type="text/javascript" >
		     function updateAItem() {
                var rows = $("#creditApplicationList").datagrid("getSelections");
                if (rows.length != 1 && rows.length != 0) {
                    var names = [];
                    for ( var i = 0; i < rows.length; i++) {
                        names.push(rows[i].groupNumber);
                    }
                    $.messager.show({
                        msg : '只能选择一条记录进行编辑！您已经选择了【' + names.join(',') + '】' + rows.length + '条记录',
                        title : '提示'
                    });
                } else if (rows.length == 0) {
                    $.messager.show({
                        msg : '请选择一条记录进行编辑！',
                        title : '提示'
                    });
                } else {
                    window.location = serverName + "/creditgroup/selectCreditApplication.do?creditapplicationId=" + rows[0].creditapplicationId;
                }
            }
            
		     function exportExcelBorrower(){
		    		window.location.href=serverName+"/customer/exportExcelBorrower.do";
		     }
		     
            function optionFormatter(value,param) {
            	
            	if(param.validState == "0"){
					return "";
				}
            	 var links = "<a href='javascript:searchLook();'>查看</a>";
            	 links = links + "|<a href='javascript:showCustomerConsult();'>查看咨询</a>";
            	
            	 
            	 if( 
                  		$.trim(value) == "01" ||//待审批
                  		$.trim(value) == "24" ||//审批中
                  		$.trim(value) == "25" ||//审批中
                  		$.trim(value) == "04" ||//审批通过
  	               		$.trim(value) == "21" ||//额度确认
  	               		$.trim(value) == "11" ||//已放款登记
  	               		$.trim(value) == "13"|| //放款确认 对私
  	               		$.trim(value) == "34" ||//等待重新打印合同||
  	               		$.trim(value) == "40"//等待ICP合同签订
  	               		
  	            ){
                  	    <sec:authorize ifAnyGranted="${control.loanList_rollback}">
              	 	 		links = links + "|<a href='javascript:rollback();'>回退</a>";
               	        </sec:authorize>
                 }
            	 
            	 if(	(	
	            			($.trim(value) == "00" ||//未提交
	            			$.trim(value) == "01" ||//待审批
	                  		$.trim(value) == "24" ||//审批中
	                  		$.trim(value) == "25" ||//审批中
	                  		$.trim(value) == "04" ||//审批通过
	                  		$.trim(value) == "07" ||//撤回
	                  		$.trim(value) == "21" ||//额度确认
	  	               		$.trim(value) == "18" //审批拒绝
	  	               		)||(
	  	               			(
	  	                  		$.trim(value) == "11" //已放款登记
	  	               			)&&(
	  	               			param.businessType == '1' //对私业务
	  	               			)
	  	               		)
  	               		)&&(
  	               			param.familyName != "" && param.familyName != null
  	        	           &&param.familyIdnumber != "" && param.familyIdnumber != null
  	        	           &&param.credentialsNumber != "" && param.credentialsNumber != null
  	        	           &&param.groupName != "" && param.groupName != null
  	               		)
  	               	)
            	 {
            		<sec:authorize ifAnyGranted="${control.loanList_refuse}">
	            		 links = links + "|<a href='javascript:refuse();'>拒贷</a>";
        	        </sec:authorize>
        	        <sec:authorize ifAnyGranted="${control.loanList_customerQuit}">
	             	 	links = links + "|<a href='javascript:giveUp();'>客户放弃</a>";
   	        		</sec:authorize>
            	 }
       	  		<sec:authorize ifAnyGranted="${control.loanList_updateCancel}">
                        links = links +"|<a href='javascript:updateCancel();'>作废</a>";
                </sec:authorize>
                
                if(($.trim(value) == "00" ||    //没有提交
                		$.trim(value) == "01" ||//待审批
                		$.trim(value) == "24" ||//审批中
                		$.trim(value) == "25" ||//审批中
                		$.trim(value) == "07" ||//回退
                		$.trim(value) == "18" ||//审批拒绝
                		$.trim(value) == "04") ||//审批通过
	               		(($.trim(value) == "21" ||//额度确认 （对私）
	               		$.trim(value) == "14") && param.businessType == '1')//放款回退（对私）
	               	 	){
	                	 <sec:authorize ifAnyGranted="${control.loanList_updateClose}">
		                     links = links +"|<a href='javascript:updateClose();'>关单</a>";
		                 </sec:authorize>
               			 }

                /*增加参审--start*/
                if ($.trim(value) == '00' || $.trim(value) == '01') {
                    <%--
                    2014.12.02 参审添加为统一入口 start
                    <sec:authorize ifAnyGranted="${control.loanList_qyapprove}">
                    links = links + "|<a href='javascript:p_approve(\"qyapprove\","+param.creditapplicationId+","+param.isCityParticipate+");'>参审</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_qyfxapprove}">
                    links = links + "|<a href='javascript:p_approve(\"qyfxapprove\","+param.creditapplicationId+","+param.isCityParticipate+");'>参审</a>";
                    </sec:authorize>
                    --%>
					<sec:authorize ifAnyGranted="${control.loanList_participate}">
					if (param.isCityParticipate == 0) {
						links = links + "|<a href='javascript:participate(" + param.creditapplicationId + "," + param.isCityParticipate + ");'>参审</a>";
					}
					</sec:authorize>
					<%-- 2014.12.02 参审添加为统一入口 end --%>
                }
                /*增加参审--end*/
              	if (($.trim(value) == "00" || $.trim(value) == "07" ||$.trim(value) == "18") && (param.fxid == "" || param.fxid == null)) {
                    <sec:authorize ifAnyGranted="${control.loanList_edit}">
                        links = links + "|<a href='javascript:update();'>编辑</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_addFX}">
                    links = links + "|<a href='javascript:addfx();'>新增背景调查</a>";
               		</sec:authorize>
               		<%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                }else if (($.trim(value) == "00" || $.trim(value) == "07" ||$.trim(value) == "18") && (param.fxid != "" && param.fxid != null)) {
                    <sec:authorize ifAnyGranted="${control.loanList_edit}">
                        links = links + "|<a href='javascript:update();'>编辑</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_addFX}">
                    links = links + "|<a href='javascript:addfx();'>编辑背景调查</a>";
               		</sec:authorize>
               		<%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "01") {
                    /*<sec:authorize ifAnyGranted="${control.loanList_withdraw}">
                        links = links + "|<a href='javascript:chehui();'>撤回</a>";
                    </sec:authorize>*/
                    <sec:authorize ifAnyGranted="${control.loanList_approve}">
                        links = links + "|<a href='javascript:approval(\"approve\");'><font color='#4455dd'>审批</font></a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
                    links = links + "|<a href='javascript:approval(\"fxapprove\");'><font color='#4455dd'>审批</font></a>";
               		</sec:authorize>
               		<%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "22") {
                    <sec:authorize ifAnyGranted="${control.loanList_approve}">
                    links = links + "|<a href='javascript:approval(\"approve\");'><font color='#4455dd'>审批</font></a>";
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
	                links = links + "|<a href='javascript:approval(\"fxapprove\");'><font color='#4455dd'>审批</font></a>";
	           		</sec:authorize>
	           		<%--特殊情况申请添加权限   luohongjie --%> 
	              	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
	              	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
	   	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "23") {
                    <sec:authorize ifAnyGranted="${control.loanList_approve}">
                    links = links + "|<a href='javascript:approval(\"approve\");'><font color='#4455dd'>审批</font></a>";
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
	                links = links + "|<a href='javascript:approval(\"fxapprove\");'><font color='#4455dd'>审批</font></a>";
	           		</sec:authorize>
	           		<%--特殊情况申请添加权限   luohongjie --%> 
	              	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
	              	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
	   	        	</sec:authorize>
                    return links;
                }  else if ($.trim(value) == "24") {
                    <sec:authorize ifAnyGranted="${control.loanList_approve}">
                    links = links + "|<a href='javascript:approval(\"approve\");'><font color='#4455dd'>审批</font></a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
	                links = links + "|<a href='javascript:approval(\"fxapprove\");'><font color='#4455dd'>审批</font></a>";
	           		</sec:authorize>
                    /*当状态为审批中时，已参审人员（区域经理和区域风险经理任一或全部，谁参审，谁审批）可进行审批操作*//*
                    if(param.isCityParticipate){
                        <sec:authorize ifAnyGranted="${control.loanList_qyapprove}">
                        links = links + "|<a href='javascript:approval(\"qyapprove\");'><font color='#4455dd'>审批1</font></a>";
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="${control.loanList_qyfxapprove}">
                        links = links + "|<a href='javascript:approval(\"qyfxapprove\");'><font color='#4455dd'>审批</font></a>";
                        </sec:authorize>
                    }*/
	           		<%--特殊情况申请添加权限   luohongjie --%>
	              	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
	              	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
	   	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "25") {
                    <sec:authorize ifAnyGranted="${control.loanList_approve}">
                    links = links + "|<a href='javascript:approval(\"approve\");'><font color='#4455dd'>审批</font></a>";
	                </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
                    links = links + "|<a href='javascript:approval(\"fxapprove\");'><font color='#4455dd'>审批</font></a>";
                    </sec:authorize>
                    /*当状态为审批中时，已参审人员（区域经理和区域风险经理任一或全部，谁参审，谁审批）可进行审批操作*//*
                    if(param.isCityParticipate){
                        <sec:authorize ifAnyGranted="${control.loanList_qyapprove}">
                        links = links + "|<a href='javascript:approval(\"qyapprove\");'><font color='#4455dd'>审批2</font></a>";
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="${control.loanList_qyfxapprove}">
                        links = links + "|<a href='javascript:approval(\"qyfxapprove\");'><font color='#4455dd'>审批</font></a>";
                        </sec:authorize>
                    }*/
	                <%--特殊情况申请添加权限   luohongjie --%> 
	              	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
	              	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
	   	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "04" ) {
                	<%--因为业务需求   把最终额度确认   按钮给注释掉了   luohongjie
                   <sec:authorize ifAnyGranted="${control.loanList_confirm}">
                        links = links + "|<a href='javascript:confirmLimit();'><font color='#4455dd'>最终额度确认</font></a>";
                    </sec:authorize>--%>
                    <%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "15") {
                	<%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                } else if ($.trim(value) == "-1") {
                    <sec:authorize ifAnyGranted="${control.loanList_edit}">
                        links = links + "|<a href='javascript:update();'>编辑</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_delete}">
                        links = links + "|<a href='javascript:deleteById();'>删除</a>";
                    </sec:authorize>
                    <%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                }  else if ($.trim(value) == "31" || $.trim(value) == "32") {
                    /*待城市审批、城市审批中*/
                    var _links = "<a href='javascript:searchLook();'>查看</a>|<a href='javascript:showCustomerConsult();'>查看咨询</a>";
                    <sec:authorize ifAnyGranted="${control.loanList_updateCancel}">
                    _links = _links + "|<a href='javascript:updateCancel();'>作废</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                    _links = _links + "|<a href='javascript:openDialog(" + param.creditapplicationId + "," + 0 + ");'>特殊情况申请</a>"
                    </sec:authorize>
                    /*此两种状态下，已参审人员（区域经理和区域风险经理任一或全部，谁参审，谁审批）可进行审批操作*/
					if(param.isCityParticipate && param.isCityParticipate > 0){
						<%--
                        2014.12.02 参审添加为统一入口 start
                        <sec:authorize ifAnyGranted="${control.loanList_qyapprove}">
                        _links = _links + "|<a href='javascript:approval(\"qyapprove\");'><font color='#4455dd'>审批</font></a>";
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="${control.loanList_qyfxapprove}">
                        _links = _links + "|<a href='javascript:approval(\"qyfxapprove\");'><font color='#4455dd'>审批</font></a>";
                        </sec:authorize>
                        --%>
						<sec:authorize ifAnyGranted="${control.loanList_participate}">
						_links = _links + "|<a href='javascript:approval(\"participateApprove\");'><font color='#4455dd'>审批</font></a>";
						</sec:authorize>
						<%-- 2014.12.02 参审添加为统一入口 end --%>
					}
                    <sec:authorize ifAnyGranted="${control.loanList_refuse}">
                    _links = _links + "|<a href='javascript:refuse();'>拒贷</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_customerQuit}">
                    _links = _links + "|<a href='javascript:giveUp();'>客户放弃</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_rollback}">
                    _links = _links + "|<a href='javascript:rollback();'>回退</a>";
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="${control.loanList_updateClose}">
                    _links = _links +"|<a href='javascript:updateClose();'>关单</a>";
                    </sec:authorize>
                    return _links;
                } else{
                	<%--特殊情况申请添加权限   luohongjie --%> 
                 	 <sec:authorize ifAnyGranted="${control.loanList_specialApply}">
                 	 links=links+"|<a href='javascript:openDialog("+param.creditapplicationId+","+0+");'>特殊情况申请</a>"
      	        	</sec:authorize>
                    return links;
                }  
            }
            function refuse(){
            	//清空数据
            	$("#RefuseReason").val("");
            	$("#showRefuseReason").val("");
            	var rowdata = $("#creditApplicationList").datagrid("getSelected");
            	$("#RefuseReasonCreditapplicationId").val(rowdata.creditapplicationId);
            	$("#refuseReasonDialog").dialog({
            		closed:false,
            		width:500,
            		closable:false,
            		modal:true,
            		height:200,
            		title:"拒贷原因",
            		buttons:[
            		        {
            		        	id:"okButton",
            		        	text:"确定",
            		        	handler:function(){
            		        		var refuseReasons = $("#RefuseReason").val();
            		        		if(refuseReasons =="" || refuseReasons ==null){
            		        			$.messager.alert("消息", "请输入拒贷原因");
            		        		}else{
	            		        		$("#okButton").linkbutton("disable");
	            		        		$("#noButton").linkbutton("disable");
	        		    				var creditapplicationId =$("#RefuseReasonCreditapplicationId").val();
	        		    				var section = $("#RefuseReasonSection").val();
	            		        		$.ajax({
	            		        			url:"<%=basePath%>RefuseReasonController/refuse.do",
	            		        			datatype:"json",
											data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:refuseReasons,auditStatus:"27"},
											success:function(message){
												if(message.success){
													$.messager.alert("消息", "操作成功");
													$("#okButton").linkbutton("enable");
				            		        		$("#noButton").linkbutton("enable");
													$("#refuseReasonDialog").dialog("close");
													$("#creditApplicationList").datagrid("reload", "");
												}else{
													$("#okButton").linkbutton("enable");
				            		        		$("#noButton").linkbutton("enable");
													$.messager.alert("消息", "操作失败");
												}
											}
	            		        		});
            		        		}
            		        			
            		        	}
            		        },{
            		        	id:"noButton",
            		        	text:"取消",
            		        	handler:function(){
            		        		$("#refuseReasonDialog").dialog("close");
            		        	}
            		        }
            		     ]
            	});
            }
            function showRefuseReasonDiv(valueField,textField){
            		//原因
            		var reasons = $("#RefuseReason").val();
            		var reasonsArray = reasons.split(",");
    				ajaxSubmit(serverName+"/CustomerConsult/selectDictionary.do",{section:"refuseReasonTotal",q:""},function(result){
    						 var table = document.getElementById("refuseReasonTable");
    						 var pchildren = table.childNodes;;
    						 //清空表中的行和列
    						 for(var a=pchildren.length-1; a>=0; a--){
    							 table.removeChild(pchildren[a]);
    						 }
    						 //判断颜色的 
    						 var m = 0;
    					 	for(var k = 0 ; k < result.refuseReasonTotal.length;k++){
    							 	var  index = result.refuseReasonTotal[k].codaTableId;	
    							 	var  name = "refuseReasonTotal"+index;
    							 	var  objectName = "result."+name;
    							 	var object = (eval("("+objectName+")"));
    							 	var n = 0;
    						     	var length = 0;
    						     	if(object.length%3 > 0){
    						     		length = parseInt(object.length/3)+1;
    						     	}else{
    						     		length = parseInt(object.length/3);
    						     	}
    								for(var i = 0 ; i < length;i++){
    									m = m+1;
    									var tr=document.createElement("tr");
    									if(k % 2 == 0){
    										//tr.setAttribute('bgcolor','#DDE4EE');
    									}
    										if(i == 0){
    											var cell1=document.createElement("td"); 
    											cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ result.refuseReasonTotal[k].codeVlue+"</font>";
    											cell1.setAttribute("rowspan",length);
    											cell1.setAttribute("align","center");
    											cell1.setAttribute("width","150");
    											cell1.setAttribute("bgcolor","#DDE4EE");
    											tr.appendChild (cell1);
    										}
    										
    									
    									for(var j = 0 ; j < 3 ;j++){
    										var cell=document.createElement("td"); 
    										cell.setAttribute("width","360");
    										n = n+1;
    										if(m % 2 == 0){
    											cell.setAttribute("bgcolor","#DDE4EE");
    										}
    										if(object[n-1] != undefined){
    											$("#RefuseReasonSection").val(object[n-1].section);
    											var key = object[n-1].codeKey;
    											var value = object[n-1].codeVlue;
    											var arrayObj = new Array(key,value);
    											var obj = new Object();
    											obj["codeKey"]=object[n-1].codeKey;
    											obj["codeValue"]=object[n-1].codeVlue;
    												for(var l =0;l <reasonsArray.length;l++){
    													if(obj.codeKey == reasonsArray[l]){
    														cell.innerHTML = 
    		    												"<input type='checkbox' checked='checked' name='refuseReason' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
    		    											break;
    													}else{
    														cell.innerHTML = 
    		    												"<input type='checkbox' name='refuseReason' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
    													}
    												}
    												
    												//"<a href='javaScript:addRefuseReason(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
    											}
    										tr.appendChild (cell); 
    									}
    								　	table.appendChild (tr); 
    								}
    					 	}
    					$("#refuseReasonDiv").dialog({   
    					    width:950,  
    					    height:30*(Math.ceil(result.length/3)), 
    					    title:"拒贷原因",
    					    minimizable:false,
    					    maximizable:false,
    					    collapsible:false,
    	   					modal:true,
    	   					buttons:[{
	    	   						id:"refuseButton",
	    	   						text:'确定',
	    	   						iconCls:'icon-ok',
	    	   						handler:function(){
	    	   							addRefuseReason();
	    	   						}
	    	   					}
    	   					   ]
       					});   
    				});
            }
            
            function addRefuseReason(){
            	var datas = $("[name='refuseReason']");
            	var refuseResionKey= "";
            	var refuseResionValue= "";
            	for(var i =0;i<datas.length;i++){
            		if(datas[i].checked == true){
            		refuseResionKey = refuseResionKey +","+datas[i].value;
            		refuseResionValue = refuseResionValue+ "," + datas[i].nextSibling.nodeValue;
            		}
            	}
            	if(refuseResionKey !=""){
            		refuseResionKey = refuseResionKey.substr(1,refuseResionKey.length);
            		refuseResionValue = refuseResionValue.substr(1,refuseResionValue.length);
            	}
    			$("#showRefuseReason").val(refuseResionValue);
    			$("#RefuseReason").val(refuseResionKey);
    			$("#RefuseReason").focus();
    			$("#refuseReasonDiv").dialog("close");
    		}
            
            function giveUp(){
            	var rowdata = $("#creditApplicationList").datagrid("getSelected");
            	$("#customerQiutCreditapplicationId").val(rowdata.creditapplicationId);
            	$("#customerQiut").combobox({
            		width:130,
            		onSelect:function(data){
            			$("#customerQiutSection").val(data.section);
            		}
            	});
            	$("#customerQiutDiv").dialog({
            		closed:false,
            		width:400,
            		closable:false,
            		modal:true,
            		height:150,
            		title:"客户放弃原因",
            		buttons:[
	            		        {
	            		        	id:"customerQiutButton",
	            		        	text:"确定",
	            		        	handler:function(){
	            		        		var  customerQiutReason= $("#customerQiut").combobox("getValue");
	            		        		if(customerQiutReason == "" || customerQiutReason == null){
	            		        			$.messager.alert("消息", "请选择客户放弃原因");
	            		        		}else{
		            		        		$("#customerQiutButton").linkbutton("disable");
		            		        		$("#customerQiutCancelButton").linkbutton("disable");
		        		    				var creditapplicationId =$("#customerQiutCreditapplicationId").val();
		        		    				var section = $("#customerQiutSection").val();
		            		        		$.ajax({
		            		        			url:"<%=basePath%>RefuseReasonController/refuse.do",
		            		        			datatype:"json",
												data:{creditapplicationId:creditapplicationId,section:section,refuseReasons:customerQiutReason,auditStatus:"28"},
												success:function(message){
													if(message.success){
														$.messager.alert("消息", "操作成功");
														$("#customerQiutButton").linkbutton("enable");
					            		        		$("#customerQiutCancelButton").linkbutton("enable");
														$("#customerQiutDiv").dialog("close");
														$("#creditApplicationList").datagrid("reload", "");
													}else{
														$("#customerQiutButton").linkbutton("enable");
					            		        		$("#customerQiutCancelButton").linkbutton("enable");
														$.messager.alert("消息", "操作失败");
													}
												}
		            		        		});
	            		        		}
	            		        	}
	            		        },
	            		        {
	            		        	id:"customerQiutCancelButton",
	            		        	text:"取消",
	            		        	handler:function(){
	            		        		$("#customerQiutDiv").dialog("close");
	            		        	}
	            		        }
            		       
            		        ]
            	});
            	var dic1 = new DataDictionary();
            	dic1.addDic("customerQiut", "customerQiutReason");
            	dic1.dicAjax();
            	
            }
        </script>
  </head>
  
  <body id="cc" class="easyui-layout" fit="true" >
  			
		<div region="center" >
		<div id="creditApplicationListTab" class="easyui-tabs" style="padding: 10px;">
  				<div title="模糊查询" style="padding: 10px;">
					<table>
						<tr>
		     				<td>模糊查询条件：</td>
		     				<td><input type="text" id="fuzzy"/></td>
			     			<td>
                                <a class="easyui-linkbutton" href="javascript:fuzzySearch();">搜索</a>
			     			</td>
			     			<td><a class="easyui-linkbutton" href="javascript:clearFuzzyBox();">清空</a></td>
			     			<td><font color="red">（可输入业务单号</font></td>
			     			<td><font color="red">、或借款人姓名、或客户经理、分公司名称 不完整片段进行搜索）</font></td>
			     		</tr>
	     			</table>
  				</div>
  				<div title="高级查询" style="padding: 10px;">
  				 <table>
								<tr>
				     				<td>业务单号:</td>
					     			<td><input type="text" id="groupNumber" style="width: 147px;" /> </td>
					     				<td>分公司名称:</td>
					     			<td><input  style="width: 150px;" id="companyName"
					     			type="text" /></td>
					     			
					     			<td>提交日期：</td>
					     			<td><input  id="beginSubmitDate" class="easyui-datebox" editable="false" style="width: 150px;" /></td>
					     			<td>至</td>
					     			<td><input  id="endSubmitDate" class="easyui-datebox" editable="false" style="width: 150px;"  onSelect="sssddd()"></td>
                                    <td>产品类型：</td>
                                    <td><input id="productTypeName" name="productTypeName" style="width: 150px;"/>
                                    </td>
					     		</tr>
					     		<tr>
					     			
					     		<td>借款人姓名:</td>
					     			<td><input type="text" id="groupName" style="width: 147px;" /></td>
					     			<td>申请状态:</td>
					     			<td><input class="easyui-combobox" style="width: 150px;" 
					     			 id="auditStatus" multiple="true" separator=","/></td>
					     			<td>放款日期：</td>
					     			<td><input  id="beginLoanDate" class="easyui-datebox" editable="false" 	style="width: 150px;" > </td>
					     			<td>至</td>
					     			<td><input  id="endLoanDate" class="easyui-datebox" editable="false" style="width: 150px;" ></td>
					     			<td></td>
					     			<td align="right">
                                        <a class="easyui-linkbutton" href="javascript:searchAll();">搜索</a>
                                        <a class="easyui-linkbutton" href="javascript:clearSearchBox();">清空</a>
                                    </td>
					     		</tr>
							</table>
  				</div>
  			</div>
  				<div style="padding: 10px;">
					<table id="creditApplicationList" toolbar="#toolbar1"></table>
				</div>
		        <div id="toolbar1">
		            <sec:authorize ifAnyGranted="${control.loanList_editSpecial}">
		                  <a href="javascript:void(0)" id="updateCreditApplication" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateAItem()">修改</a> 
		            </sec:authorize> 
		             <sec:authorize ifAnyGranted="${control.loanList_exportExcelBorrower}">
               			<a href='javascript:exportExcelBorrower();' class="easyui-linkbutton" iconCls="icon-edit" plain="true">导出借款人信息</a>
		            </sec:authorize> 
		        </div>
   		</div>	
   		<div class="easyui-dialog" id="auditPage" 
   			style="width: 1000px; height: 400px;" closed="true">
   		</div>
		<div id="riskMgrDialog" class="easyui-dialog" modal="true" maximizable="true" 
			closed="true" title="信用及背景调查报告" style="width:1000px;height:470px;overflow: hidden;"> 
   			<iframe scrolling="no" id='openRiskMgr' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
   		</div>
   		<div class="easyui-dialog" id="confirmAmount"  style="padding: 10px;" closed="true" closable="false">
			<form id="confirmAmountForm">
   			<table align="center">
   				<tr>
   					<td>
   						业务编号：
   					</td>
   					<td>
   						<input name="groupNumber" 
   						style="width: 150px;" readonly="readonly"/> 
			   			
   					</td>
   					<td width="40"></td>
   					<td>
   						借款人：
   					</td>
   					<td><input name="groupName" 
   					style="width: 150px;" readonly="readonly"/></td>
   					
   				</tr>
   				<tr>
   					<td>
   					申请金额：
   					</td>
   					
   					<td>
   					<input name="groupAppTotal"
   					style="width: 150px;" readonly="readonly">(元)
   					</td>
   					<td width="40"></td>
   					<td>
   						
			   			审批金额：
			   			
   					</td>
   					<td><input name="loanAmount" id="loanAmount"
   					style="width: 150px;" readonly="readonly">(元)</td>
   					
   				</tr>
   				<tr>
   					<td>
   						放款金额：
   					</td>
   					<td>
   						<input name="amount" id="amountLoan"
   						class="easyui-validatebox"
   						validType="number"
   						style="width: 150px;"
   						 onblur="confirmAmount(this);">(元)
   					</td>
   					<td width="40"></td>
   					 <td>期望放款时间</td>
   					 <td>
   					 <input name="loanTime" class="easyui-datebox" style="width: 150px;" id="loanTime" editable="false" required="true"/>
   					 <input name="creditapplicationId" id="creditapplicationIdComfirm" type="hidden"/>
   					 </td>
   				</tr>
   				<tr>
   					<td>收放款方式：</td>
   					<td><input name="businessType" id="businessType" style="width: 153px;"/></td>
   					<td   width="150">
	   					<span id="accountButtonSpan">
	   						<a class="easyui-linkbutton" style="width:90px;" onclick="change();">选择公司账户</a>
	   					</span>
   					</td>
   					<td></td>
   					<td></td>
   				</tr>
   				</table>
   			</form>
   				<table align="center">
   				<tr>
   					<td colspan="5">
										<div id="showForm" >
												<form id="accountInfoForm" novalidate>
												    <table align="center" style="padding: 10px;">
												    		<tr>
												    			<td align="center" colspan="6">
												    				<h3>账户</h3>
												    			</td>
												    		</tr>
												    		<tr align="center">
												    			<td>所在省:</td>
												    			<td>所在市:</td>
												    			<td>所在区县:</td>
												    			
												    		</tr>
												    		<tr>
												    			<td align="left">
												    				<input name="accountInfoId" id="accountInfoId" type="hidden" />
												    				<input name="provinceId" class="easyui-combobox" style="width: 150px;" id="provinceId1" required="true" editable="false"/></td>
												    			<td align="left">
												    				<input name="cityId" class="easyui-combobox" style="width: 150px;" id="cityId1" required="true" editable="false"/></td>
												    			<td align="left">
												    				<input name="districtId" class="easyui-combobox" style="width: 150px;" id="districtId1" required="true" editable="false"/></td>
												    		</tr>
											    	</table>
											    	<table  align="center"  style="padding: 10px;border-top-color:olive; border-top-style: solid;">
											    		<tr align="center">
											    			<td align="right"">手机号码：</td>
											    			<td align="left">
											    				<input style="width:150px;" name="mobilephone"  
																		id="mobilephone"
																	  	class="easyui-validatebox" validType="phoneNumber" required="true" invalidMessage
	    				="输入11位手机号或加区号的固话号码" maxlength="11"/> </td>
											    			<td align="right"">分公司名称：</td>
											    			<td align="left"><input style="width:150px;" name="branchName" 
											    									class="easyui-validatebox"  
											    									id="branchName"
											    									required="true"
											    									readonly="readonly"
											    									value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentName()%>"/>
											    						<input name="departmentId" class="easyui-validatebox" type="hidden" 
																		value="<%=SpringSecurityUtils.getCurrentUser().getAreaDepartmentId()%>"/>
											    			</td>
											    		</tr>
											    		<tr align="center">
											    			<td align="right"">所在行地区号：</td>
											    			<td align="left">
											    					<input style="width:150px;"
													    			id="bankPrefectureNum"
													    			 name="bankPrefectureNum" class="easyui-validatebox"
													    			  validType="numberOnly" required="true" maxlength="20"/></td>
											    			<td align="right"">所在行别：</td>
											    			<td align="left"><input style="width:153px;"  required="true"
											    							name="bankNum" id="bankNum"/>
											    							<input style="width:150px;" name="bankRank" id="bankRank" 
											    							type="hidden" width="130px"/>
											    			</td>
											    		</tr>
											    		<tr align="center">
											    			<td align="right"">开户行：</td>
											    			<td align="left"><input style="width:150px;" 
											    							id="bankName" name="bankName" 
											    							class="easyui-validatebox"  required="true" maxlength="30"/></td>
											    					<td align="right"">卡折类型：</td>
											    			<td align="left">
											    						<input style="width:153px;" name="cardFlag"   
											    						id="cardFlag" required="true" />
											    			</td>
											    		</tr>
											    		<tr align="center">
											    			<td align="right"">账&nbsp;&nbsp;号：</td>
											    			<td align="left"><input style="width:150px;" name="account"
											    							 class="easyui-validatebox" 
											    							 validType="numberOnly"
											    							 id="account" required="true" maxlength="20"/></td>
											    	
											    			<td align="right"">账户名：</td>
											    			<td align="left"><input style="width:150px;" 
											    							name="accountName" 
											    							id="accountName" class="easyui-combobox"
											    							required="true" maxlength="10"/></td>
											    		</tr>
											    		<tr align="center">
											    			<td align="right"">支付行号：</td>
											    			<td align="left"><input style="width:150px;" name="payBranchno"
											    						 class="easyui-validatebox" 
											    						 validType="numberOnly" 
											    						 id="payBranchno" required="true" maxlength="20"/>
											    			</td>
											    			<td align="right"">账户人身份证号：</td>
											    			<td align="left">
											    			<input  name="credentialsNumber"  
											    				 style="width:150px;"
											    				 readonly="readonly"
											    			 	validType="idnumberAll"  
											    			 	id="credentialsNumber"/>
											    			</td>
											    			<td align="left">
											    			<input name="useType" id="useType" type="hidden"  value="2"/>		<!-- 付款账户 --></td>
											    		</tr>
											    		<tr align="center">
											    			<td align="left">
											    			<input  name="onUsed" type="hidden" id="onUsed" value="1"/>
											    			<input  name="accountType"  id="accountType" type="hidden" value="1"/>
											    			</td>
											    		</tr>
											    	</table>
											    </form>	
											</div>
					</td>
   				</tr>
   			</table>
   		
   		</div>
   		<div class="easyui-dialog" id="chehui"  style="padding: 10px;" closed="true">
   			<form id="chehuiForm">
   				<table align="center">
	   				<tr>
	   					<td>
	   						业务编号：
	   					</td>
	   					<td>
	   						<input name="groupNumber" 
	   						style="width: 200px;" readonly="readonly"/>
	   						<input name="creditapplicationId" type="hidden"
	   						style="width: 150px;" readonly="readonly"/>  
	   					</td>
	   				</tr>
	   				<tr>
	   					<td>
	   						处理结果：
	   					</td>
	   					<td><input   value="撤回"
	   					style="width: 200px;" readonly="readonly"/>
	   					<input name="auditStatus"  value="07" type="hidden"
	   					style="width: 200px;" readonly="readonly"/></td>
	   					
	   				</tr>
	   				<tr>
	   					<td>
	   						撤回原因类型：
	   					</td>
	   					<td><input name="reasonType"   id="reasonType"
	   					style="width: 205px;" required="true"/></td>
	   					
	   				</tr>
	   				<tr>
	   					<td>
	   						撤回原因：
	   					</td>
	   					<td>
	   					<input name="revokeReason"  class="easyui-validatebox"
	   					style="width: 200px;" required="true" maxlength="100" validType="length[0,100]"/></td>
	   					
	   				</tr>
   				</table>
   			</form>
   		</div>
  		<div id="cashStream"></div>
  			<div id="cardDialog" title="请单击账户 选择一张公司账户与该申请单 的付款业务绑定 " style="width: 650px; height: 300px;" closed="true" modal="true" closable="false">
				<table id="cardDataGrid">
				</table>
		</div>
		<div id="refuseReasonDialog" class="easyui-dialog"  style="width:500;height:150;" closed="true">
			<fieldset>
				<legend>拒贷原因</legend>
				<input id="RefuseReason" type="hidden"/>
				<input id="RefuseReasonSection" type="hidden"/>
				<input id="RefuseReasonCreditapplicationId" type="hidden"/>
				请输入拒贷原因：<input name="" id="showRefuseReason" size="60" readonly="readonly"
				onfocus="showRefuseReasonDiv('RefuseReason','showRefuseReason');"/>
			</fieldset>
		</div>
		<input id="section"/>
		 <div id="refuseReasonDiv">
    		<table id="refuseReasonTable" style="width: 870px;border:1px;  border-color: black;" align="center" fit="true" >
    		</table>
   		 </div>
   		 <div id="customerQiutDiv">
	   		 <fieldset>
					<legend>客户放弃原因</legend>
					<input id="customerQiutSection" type="hidden"/>
					<input id="customerQiutCreditapplicationId" type="hidden"/>
	   		 		请输入客户放弃原因:
	   		 		<input id="customerQiut" editable="false"/>
	   		 </fieldset>
   		 </div>
   		 <!-- 添加特殊情况申请信息 -->
   		<div id="specialApplyDialog" style="width: 850px; height: 400px;">
				<iframe scrolling="no" id="iframeTest" name="myIframe"
					frameborder="0" src="" style="width: 100%; height: 100%;"></iframe>
			</div>
  </body>
</html>
