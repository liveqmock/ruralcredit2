<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils,com.creditease.core.security.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user=SpringSecurityUtils.getCurrentUser();
	String userid=user.getUserId();
%>

<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<html>
<head>
<title>客户咨询</title>
<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
<script src="<%=basePath%>scripts/CustomerConsultPool/accept_inquire.js"></script>	
<script>
	var currentUserId = "<%=userid%>";
	var serverName = "<%=path%>";
	
	//客户标签
	function showpprocessCauses(valueField, textField) {
		//加/*------------------------begin-----------------*/
		var reasons = $("#customerTag").val();
		var reasonsArray = reasons.split(",");
		/*-------------------------end-----------------*/
	    ajaxSubmit(serverName + "/CustomerConsult/selectDictionary.do", {section: "processCausesTotalPool", q: ""}, function (result) {
	        var opanel = document.getElementById("tableprocessCauses");
	        var pchildren = opanel.childNodes;
	        //清空表中的行和列
	        for (var a = pchildren.length - 1; a >= 0; a--) {
	            opanel.removeChild(pchildren[a]);
	        }
	        var m = 0;
	        for (var k = 0; k < result.processCausesTotalPool.length; k++) {
	            var index = result.processCausesTotalPool[k].codaTableId;
	            var name = "processCausesTotalPool" + index;
	            var objectName = "result." + name;
	            var object = (eval("(" + objectName + ")"));
	            var n = 0;
	            var length = 0;
	            if (object.length % 3 > 0) {
	                length = parseInt(object.length / 3) + 1;
	            } else {
	                length = parseInt(object.length / 3);
	            }
	            for (var i = 0; i < length; i++) {
	                m = m + 1;
	                var tr = document.createElement("tr");
	                if (i == 0) {

	                    var cell1 = document.createElement("td");
	                    cell1.innerHTML = "<font style='width: 150px;font-size: 13;'>" + result.processCausesTotalPool[k].codeVlue + "</font>";
	                    cell1.setAttribute("rowspan", length);
	                    cell1.setAttribute("align", "center");
	                    cell1.setAttribute("width", "130");
	                    cell1.setAttribute('bgcolor', '#DDE4EE');
	                    tr.appendChild(cell1);
	                }
	                for (var j = 0; j < 3; j++) {
	                    n = n + 1;
	                    var cell = document.createElement("td");
	                    cell.setAttribute("width", "130");
	                    if (m % 2 == 0) {
	                        cell.setAttribute('bgcolor', '#DDE4EE');
	                    }
	                    
	                   
	                    
	                    //加/*-------------------------begin-----------------*/
	                    if(object[n-1] != undefined){
							$("#processCausesSection").val(object[n-1].section);
							var key = object[n-1].codeKey;
							var value = object[n-1].codeVlue;
							var arrayObj = new Array(key,value);
							var obj = new Object();
							obj["codeKey"]=object[n-1].codeKey;
							obj["codeValue"]=object[n-1].codeVlue;
								for(var l =0;l <reasonsArray.length;l++){
									if(obj.codeKey == reasonsArray[l]){
										cell.innerHTML = 
											"<input type='checkbox' checked='checked' id='customerTag' name='customerTag' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
										break;
									}else{
										cell.innerHTML = 
											"<input type='checkbox' id='customerTag' name='customerTag' value='"+obj.codeKey+"' />"+object[n-1].codeVlue;
									}
								}
								
							}
	                    
	                    /*-------------------------end-----------------*/
	                    tr.appendChild(cell);
	                }
	                document.getElementById("tableprocessCauses").appendChild(tr);
	            }
	        }
	        $("#windowprocessCauses").dialog({
	            width: 600,
	            height: 30 * (Math.ceil(result.length / 3)),
	            title: "客户标签",
	            minimizable: false,
	            maximizable: false,
	            collapsible: false,
	            modal: true,
	            buttons:[{
						id:"CustomerTagButton",
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
							addCustomerTag();
						}
					}
				   ]
	        });
	    });
	}
		
		//客户标签div赋值
		
		function addprocessCauses() {
		    $("#" + arguments[2]).val(arguments[0]);
		    $("#" + arguments[3]).val(arguments[1]);
		    $("#windowprocessCauses").window("close");
		}
		
	 	function addCustomerTag(){
	 		var datas = $("[name='customerTag']");
        	var customerTagKey= "";
        	var customerTagValue= "";
        	for(var i =0;i<datas.length;i++){
        		if(datas[i].checked == true){
        		customerTagKey = customerTagKey +","+datas[i].value;
        		customerTagValue = customerTagValue+ "," + datas[i].nextSibling.nodeValue;
        		}
        	}
        	if(customerTagKey !=""){
        		customerTagKey = customerTagKey.substr(1,customerTagKey.length);
        		customerTagValue = customerTagValue.substr(1,customerTagValue.length);
        	}
			$("#customerTagName").val(customerTagValue);
			$("#customerTag").val(customerTagKey);
			$("#customerTag").focus();
			$("#windowprocessCauses").window("close");
	 	}

        function showBorrowing(){
            var traceStatus_key = '${customerConsultPool.traceStatus}';
            if(traceStatus_key == '0' || traceStatus_key == '1'){
                $('#borrowing_tr').show();
            }
        }
	 	</script>
</head>
<body class="easyui-layout" onload="showBorrowing();">
<div region="center">
	<div style="background:#fafafa;padding:1px;width:auto;height:auto;">
	    <form novalidate="" id="acceptInquireForm">
	    	<input type="hidden" id="consultPoolId" name="consultPoolId" value="${customerConsultPool.consultPoolId }"/>
	    	<input type="hidden"  name="frontTraceStatus" value="${customerConsultPool.traceStatus}"/>
	    	<input type="hidden"  id="acceptConsultState" name="acceptConsultState" value="${customerConsultPool.acceptConsultState}"/>
	    		<table class="tabfrom" align="center" width="100%">
	    			<tbody>
					<tr height="30">
	    				<td align="right" width="15%"><font color="red">*</font>咨询日期：</td>
	    				<td width="35%">
							<input class="easyui-validatebox" type="text" name="registDate" value="<fmt:formatDate value='${customerConsultPool.registDate }' pattern='yyyy-MM-dd' />" data-options="required:true" style="width:147px;" readonly />
	    				<td align="right" width="15%"><font color="red">*</font>客户姓名：</td>
	    				<td width="35%">
							<input class="easyui-validatebox" type="text" id="customerName" name="customerName" value="${customerConsultPool.customerName}" required="true"  data-options="required:true" style="width:147px;" validType="length[0,10]" maxlength="10"/>
						</td>
	    			</tr>
	    			<tr height="30">
						<td align="right"><font color="red">*</font>性别：</td>
	    				<td>
							<input class="easyui-combobox" id="conSex" name="conSex" style="width: 147px;" value="${customerConsultPool.conSex }"  >
	    				</td>
						<td align="right">年龄：</td>
	    				<td>
							<input class="easyui-numberbox" type="text" name="conAge" value="${customerConsultPool.conAge}"  min="20" max="100" precision="0" style="width:147px;" maxlength="3"  />
						</td>
	    			</tr>
					
	    			<tr height="30">
	    				<td align="right"><font color="red">*</font>手机号码：</td>
	    				<td>
							<input class="easyui-validatebox" type="text" name="mobilePhone" value="${customerConsultPool.mobilePhone }" required="true" data-options="required:true" validType="phoneNumber" style="width: 147px;" readonly="readonly"/>
						</td>
	    				<td align="right"><font color="red">*</font>咨询方式：</td>
	    				<td>
							<input class="easyui-combobox" type="text" id="consultWay" name="consultWay" style="width:147px;" required="true" value="${customerConsultPool.consultWay}" disabled="disabled"/>
						</td>
					</tr>
					<tr height="30">
						<td align="right"><font color="red">*</font>借款用途行业：</td>
	    				<td>
							<input id="borrowing" class="easyui-combobox" name="borrowing" value="${customerConsultPool.borrowing }" style="width:147px;" />
	    				</td>
						<td align="right"><font color="red">*</font>咨询金额：</td>
	    				<td>
							<input id="consultMoney" name="consultMoney" class="easyui-numberbox" value="${customerConsultPool.consultMoney }"  style="width: 147px;" readonly/>万元
							</input>
						</td>
	    			</tr>
	    			<tr height="30">
						<td align="right"><font color="red">*</font>信息来源：</td>
	    				<td>
                           	<%--<input type="hidden" id="channel" name="channel" value="${customerConsultPool.channel}"/>
                   			<input class="easyui-validatebox" type="text" id="channelText" name="channelText"  style="width: 147px;" required="true" editable="false" onclick="showinfomationSource();" value="${customerConsultPool.channelText}"  />
						--%>
							<input class="easyui-validatebox" type="text" id="channel" name="channel" value="${customerConsultPool.channel}" style="width: 147px;" required="true" editable="false">
						<tr height="30">
						<td align="right"><font color="red">*</font>户籍地址：</td>
	    				<td colspan="3">
                  			<input id="province" class="easyui-combobox" name="province" style="width:147px;" required="true"   value="${customerConsultPool.province}" />
                    		<input id="city" class="easyui-combobox" name="city" style="width:147px;height:10px;" required="true"  value="${customerConsultPool.city}"  />
                    		<input id="area" class="easyui-combobox" name="area" style="width:147px;height:10px;"  value="${customerConsultPool.area}" required="true"  />
	    				</td>
	    			</tr>
					<tr height="30">
	    				<td align="right">备注：</td>
	    				<td colspan="3">
	    				<textarea id="marketConsultRemark" name="marketConsultRemark" maxlength="200" validType="maxLength[200]" style="height:50px;width:527px;"  readonly="readonly">${customerConsultPool.marketConsultRemark }</textarea>
	    			</tr>
					<tr height="30">
	    				<td align="right"><font color="red">*</font>跟踪状态：</td>
	    				<td >
							<input id="traceStatus" name="traceStatus"  style="width:147px;" value="${customerConsultPool.traceStatus}" >
	    				</td>
	    				<td align="right">客户标签：</td>
	    				<td>
	    					<input id="customerTag" type="hidden" name="customerTag" value="${customerConsultPool.customerTag}"/>
							<input id="processCausesSection" type="hidden"/>
							<input type="text" id="customerTagName" name="customerTagName" style="width: 185px;" onclick="showpprocessCauses('customerTag','customerTagName');" value="${customerConsultPool.customerTagName}"/>
						</td>
	    			</tr>
                    <tr height="30" id="borrowing_tr" style="display: none">
                        <td align="right"><font color="red">*</font>借款用途： </td>
                        <td align="left">
                            <input id="borrowUse" type="text" name="borrowUse"
                                   value="${customerConsultPool.borrowUse}" style="width:147px;"/>
                            &nbsp;&nbsp;
                        </td>
                    </tr>
					<tr height="30">
	    				<td align="right">沟通记录：</td>
	    				<td colspan="3">
							<textarea id="communicationRecord" name="communicationRecord" 
                              style="height:50px;width:527px;" 
                              maxlength="200" validType="maxLength[200]"
                              onkeydown="textCount('communicationRecord','counter',200);$('#counter').show()"
                              onkeyup="textCount('communicationRecord','counter',200);$('#counter').show()">${customerConsultPool.communicationRecord}</textarea>
							
							<br>还可以输入<span id="counter">200</span>个字<br>
	    				</td>
	    			</tr>
	    		</tbody>
			</table>
    		</form>
	</div>
	<%--弹出信息来源 DIV--%>
	<div id="windowinfomationSource">
    <table id="tableinfomationSource" style="width: 400px;border:1px;  border-color: black;" align="center" fit="true">
    </table>
	</div>
	<div id="windowprocessCauses">
    	<table id="tableprocessCauses" style="width: 500px;border:1px;  border-color: black;" align="center" fit="true" >
    	</table>
    </div>
	</div>
</body>
</html>