<%@ page language="java"
	import="java.util.*,com.creditease.core.security.SpringSecurityUtils,com.creditease.core.security.User,
	java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user=SpringSecurityUtils.getCurrentUser();
	String userid=user.getUserId();
	//罗红杰添加
	String consultPoolId = request.getParameter("consultPoolId");
	StringBuffer conditionsConsult=new StringBuffer("");
	String separator="?";
	if(CommonUtil.isNotEmpty(consultPoolId)){
		conditionsConsult.append("?consultPoolId=").append(consultPoolId);
		separator="&";
	}
%>

<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<head >
	<title>受理咨询列表</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script  type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/inquire_list.js"></script>
	<script  type="text/javascript" src="<%=basePath%>scripts/CustomerConsultPool/back_pool_reason.js"></script>
	<script type="text/javascript">
	 var serverName = "<%=path%>";
	 var conditons = "";
	 <%if(CommonUtil.isNotEmpty(conditionsConsult.toString())){%>
		conditons = "<%=conditionsConsult.toString()%>";
		<%} %>
	 /*受理咨询列表咨询-查看详情：查看当前联系电话关联的所有咨询记录*/
     function registerInquireView(poolId, phoneNumber) {
         var param;
         $("#registerInquireViewDiv").dialog({
             title: "查看咨询记录",
             buttons: [
                 {id: "registerInquireViewButton", text: "关闭", handler: function () {
                     $("#registerInquireViewDiv").dialog("close");
                 }}
             ],
             closed: true,
             draggable: true,
             width: $('#centerPanel')[0].clientWidth - 100,
             height: 300
         });
         <%--$("#registerInquireViewIframe")[0].src = "<%=basePath%>saleInquireController/registerInquireView.do?" + param--%>
         $("#registerInquireViewIframe")[0].src = "<%=basePath%>jsp/rc/CustomerConsultPool/acceptInquireView.jsp?poolId=" + poolId + "&phoneNumber=" + phoneNumber;
         $("#registerInquireViewDiv").dialog("open");
     }
	
		//受理客户咨询
		function acceptInquire(){
			$('#acceptInquire').dialog({
				href:"accept_inquire.html",
				title:"受理咨询",
				iconCls:"icon-edit",
				buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						$('#acceptInquire').dialog('close');
					}
				},{
					text:'取消',
					iconCls:'icon-undo',
					handler:function(){
						$('#acceptInquire').dialog('close');
					}
				}]
			});
		}
		
		function operatePool(date,rows,index){
			var consultPoolId = rows.consultPoolId;
	         var marketConsultState = rows.marketConsultState;
	         var mobilePhone = rows.mobilePhone;
	         var links=null;
				
			
			<sec:authorize ifAnyGranted="${control.acceptInquireList_view}">
				links = " <a href='#' onclick='registerInquireView(\"" + consultPoolId + "\",\"" + mobilePhone + "\");'>查看详情 </a>";
			</sec:authorize>
	    		
			
			<sec:authorize ifAnyGranted="${control.acceptInquireList_accept}">
				if( rows.acceptConsultState == '2'||rows.acceptConsultState=='4'&&rows.componentOperationFlag=='0'){
    				links =links+ "|<a href='#' onclick='acceptAdvice(" + rows.consultPoolId+",\""+rows.acceptConsultState+ "\")'>受理咨询</a>";
    			}
			
			</sec:authorize>
		
			<sec:authorize ifAnyGranted="${control.acceptInquireList_back}">
				if(rows.acceptConsultState == '0'||rows.acceptConsultState == '1'&&rows.distributeOperationFlag=='0'){
    				links =links+ "|<a href='#' onclick='acceptConsultRollback(\"" + rows.consultPoolId
						+ "\")'>申请退回</a>";
    		}
	    	</sec:authorize>
	    	
			<sec:authorize ifAnyGranted="${control.acceptInquireList_backConfirm}">
				if(rows.acceptConsultState == '3'&&rows.distributeOperationFlag=='0'){
    				links =links+ "|<a href='#' onclick='acceptConsultRollbackConfirm(\"" + rows.consultPoolId
				+ "\")'>退回确认</a>";
    		}
    	    </sec:authorize>
	    		
	    	<sec:authorize ifAnyGranted="${control.acceptInquireList_apply}">
	    	if(rows.acceptConsultState == '5'&&rows.componentOperationFlag=='0'){
    			links =links+ "|<a href='#' onclick='addNewClient(\"" + rows.consultPoolId
			+ "\")'>申请借款</a>";
    		}
	    	</sec:authorize>
    		
    		<sec:authorize ifAnyGranted="${control.acceptInquireList_acceptView}">
    		if(rows.acceptConsultState == '6'||rows.acceptConsultState=='7'){
				links =links+ "|<a href='#' onclick='showApplication(" + rows.consultPoolId+",\""+rows.acceptConsultState
    			+ "\")'>查看申请</a>";
    		}
    		</sec:authorize>
    		return links; 
    	}
		

		//导出数据
		function downloadData(){
			var customerName = $.trim($("#customerName").val());
			var connectionWay = $.trim($("#telphone").val());
			var channel = $("#channel").combobox("getValue");
			var acceptConsultState = $("#acceptConsultState").combobox("getValue");
			//var businessStatus = $("#businessStatus").combobox("getValue");   +'&businessStatus='+businessStatus
			var beginAddresseeDate = $("#beginAddresseeDate").datebox("getValue");
			var endAddresseeDate = $("#endAddresseeDate").datebox("getValue");
			var beginDistributeDate = $("#beginDistributeDate").datebox("getValue");
			var endDistributeDate = $("#endDistributeDate").datebox("getValue");
			var teamdepartmentId = $("#teamdepartmentId").combotree("getValue");
			window.location.href=serverName+'/inquirePoolOfficeController/downloadExcel.do?customerName='
				+customerName+'&connectionWay='+connectionWay+'&channel='+channel+'&acceptConsultState='
				+acceptConsultState+'&beginAddresseeDate='+beginAddresseeDate+'&endAddresseeDate='+endAddresseeDate+'&beginDistributeDate='+
				beginDistributeDate+'&endDistributeDate='+endDistributeDate+'&teamdepartmentId='+teamdepartmentId;
		}
		//加载信贷员
		function showGrantProceedName(record) {
			$("#grantProceedName").combobox("setValue", "");
			ajaxSubmit(serverName + "/collectionListController/getXindaiyuanListByRole.do", {id : record.id }, function(r) {
				$("#grantProceedName").combobox({
					valueField : 'code',
					textField : 'value',
					data : r
				});
			});
		}
		/*********************************分件与分件确认begin*********************************/
		//分件
		function distributeOrders(){
			//$("#grantServiceOrdersButton").linkbutton("disable");
			$("#distributeOrdersButton").linkbutton("disable");
			$("#distributeConfirmButton").linkbutton("disable");
			if($("#grantProceedName").combobox("getValue") == undefined ||
			   $("#grantProceedName").combobox("getValue") == null ||
			   $("#grantProceedName").combobox("getValue") == ""){
				$("#distributeOrdersButton").linkbutton("enable");
				$("#distributeConfirmButton").linkbutton("enable");
				$.messager.alert('操作提示','没有选择贷员!','error');  
				return;
			}
			var consultPoolIds = new Array();
			var data = $("#inquire_pool_office").datagrid("getSelections");
			//选中的数据个数
			var length = data.length;
			for(var i = 0 ; i < length;i++){
				//只有 “待分件”、“待处理”状态的咨询客户才可以选择，其他状态均不允许被选中；
				if(data[i].acceptConsultState!=0 && data[i].acceptConsultState!=1) {
					$.messager.alert('操作提示','只有咨询状态为“待分件”、“待确认”的客户可以被分件,其他状态不允许被分件!','error');
					$("#distributeOrdersButton").linkbutton("enable");
					$("#distributeConfirmButton").linkbutton("enable");
					return;
				}
				//if((data[i].acceptConsultState==0 || data[i].acceptConsultState==1) && data[i].loanOfficerName != null && data[i].loanOfficerName != "") {
					//$.messager.alert('操作提示','当咨询状态为“跟进中”、“申请进件”的客户,已有受理客户经理的不允许被再分件!','error');  
					//return;
				//}
				//可以批量分件，每个咨询客户只能分配给其所属营业部下的某一个信贷员
				var  distributeDepartmentId = data[i].distributeDepartmentId;
				var grantTeamDepartmentId = $("#grantTeamDepartmentId").combotree("getValue");
				if(distributeDepartmentId != grantTeamDepartmentId) {
					$.messager.alert('操作提示','每个咨询客户只能分配给其所属营业部下的信贷员!','error');  
					$("#distributeOrdersButton").linkbutton("enable");
					$("#distributeConfirmButton").linkbutton("enable");
					return;
				}
				consultPoolIds.push(data[i].consultPoolId);
			}
			if(length == 0){
				$.messager.alert('操作提示','没有选中数据!','error');  
				$("#distributeOrdersButton").linkbutton("enable");
				$("#distributeConfirmButton").linkbutton("enable");
				return;
			}else{
				$.messager.confirm("消息","是否分件？已经选中"+length+"条赠与数据",function(r){
					$("#distributeOrdersButton").linkbutton("disable");
					if(r){
						  $.ajax({url:serverName+"/saleInquireController/distributeOrders.do",
								  data:{
									  	/* teamdepartmentId:$("#grantTeamDepartmentId").combotree("getValue"),
									  	teamdepartmentName:$("#grantTeamDepartmentId").combobox("getText"), */
									  	loanOfficerId:$("#grantProceedName").combobox("getValue"),
									  	loanOfficerName:$("#grantProceedName").combobox("getText"),
									    consultPoolIds:JSON.stringify(consultPoolIds)
								  },
								  dataType:"json",
								  success:function(message){
									  if(message.success){
										  $.messager.alert('操作提示','分件成功！','info');  
										  $("#inquire_pool_office").datagrid('reload');
									  }else{
										 $.messager.alert('操作提示', message.msg,'info',function(){$("#inquire_pool_office").datagrid('reload');});  
										  //去掉窗体右上角的x按钮
										  $('.panel-tool-close').hide();
									  }
									  
									  $("#distributeOrdersButton").linkbutton("enable");
									  $("#distributeConfirmButton").linkbutton("enable");
								  }
						 });
					 } else {
						 $("#distributeOrdersButton").linkbutton("enable");
						 $("#distributeConfirmButton").linkbutton("enable");
					 }
				});
			}
		}
		// 分件确认
		function distributeConfirm(){
			//$("#grantServiceOrdersButton").linkbutton("disable");
			$("#distributeOrdersButton").linkbutton("disable");
			$("#distributeConfirmButton").linkbutton("disable");
			var consultPoolIds = new Array();
			var data = $("#inquire_pool_office").datagrid("getSelections");
			//选中的数据个数
			var length = data.length;
			for(var i = 0 ; i < length;i++){
				//只有 “待分件”、“待处理”状态的咨询客户才可以选择，其他状态均不允许被选中；
				if(data[i].acceptConsultState!=1) {
					$.messager.alert('操作提示','只有咨询状态为“待确认”的客户可以被分件,其他状态不允许被分件!','error');
					$("#distributeOrdersButton").linkbutton("enable");
					$("#distributeConfirmButton").linkbutton("enable");
					return;
				}
				//if((data[i].acceptConsultState==0 || data[i].acceptConsultState==1) && data[i].loanOfficerName != null && data[i].loanOfficerName != "") {
					//$.messager.alert('操作提示','当咨询状态为“跟进中”、“申请进件”的客户,已有受理客户经理的不允许被再分件!','error');  
					//return;
				//}
				//可以批量分件，每个咨询客户只能分配给其所属营业部下的某一个信贷员
				var  distributeDepartmentId = data[i].distributeDepartmentId;
				var grantTeamDepartmentId = $("#grantTeamDepartmentId").combotree("getValue");
				consultPoolIds.push(data[i].consultPoolId);
			}
			if(length == 0){
				$.messager.alert('操作提示','没有选中数据!','error');  
				$("#distributeOrdersButton").linkbutton("enable");
				$("#distributeConfirmButton").linkbutton("enable");
				return;
			}else{
				$.messager.confirm("消息","是否确认分件？已经选中"+length+"条赠与数据",function(r){
					$("#distributeOrdersButton").linkbutton("disable");
					if(r){
						  $.ajax({url:serverName+"/saleInquireController/distributeConfirm.do",
								  data:{
									  	/* teamdepartmentId:$("#grantTeamDepartmentId").combotree("getValue"),
									  	teamdepartmentName:$("#grantTeamDepartmentId").combobox("getText"), */
									  	loanOfficerId:$("#grantProceedName").combobox("getValue"),
									  	loanOfficerName:$("#grantProceedName").combobox("getText"),
									    consultPoolIds:JSON.stringify(consultPoolIds)
								  },
								  dataType:"json",
								  success:function(message){
									  if(message.success){
										  $.messager.alert('操作提示','分件确认成功！','info');  
										  $("#inquire_pool_office").datagrid('reload');
									  }else{
										  $.messager.alert('操作提示', message.msg,'info',function(){$("#inquire_pool_office").datagrid('reload');});   
										  //去掉窗体右上角的x按钮
										  $('.panel-tool-close').hide();
									  }
									  
									  $("#distributeOrdersButton").linkbutton("enable");
									  $("#distributeConfirmButton").linkbutton("enable");
								  }
						 });
					 } else {
						 $("#distributeOrdersButton").linkbutton("enable");
						 $("#distributeConfirmButton").linkbutton("enable");
					 }
				});
			}
		}
		/*********************************分件与分件确认end*********************************/
		
		/*********************************退回确认 begin**************************************************/
		function acceptConsultRollbackConfirm(consultPoolId){
			//
			//$("#rollbackframeConfirm").attr("src",serverName+ "/acceptAdviceController/backInquirePools.do?consultPoolId="+ consultPoolId);
			$('#rollbackDivConfirm').dialog('open');
    		$("#rollbackframeConfirm").attr("src",serverName+ "/acceptAdviceController/backInquirePools.do?consultPoolId="+ consultPoolId);
		}
		
		function saveRollbackConfirm(){
    		rollbackframeConfirm.window.updateAcceptConsultRollBackConfirm();
    	}
		function cancelRollbackConfirm(){
    		//window.frames["rollbackframeConfirm"].$("#acceptConsultRollbackConfirmForm").form("clear");
    		rollbackframeConfirm.window.updateAcceptConsultRollBackCancel();
    		//$("#rollbackDivConfirm").dialog('close');
    	}
		/*********************************退回确认end********************************************/
		
		/*********************************退回原因start********************************************/
		//申请退回
    	function acceptConsultRollback(consultPoolId){
    		$('#rollbackDiv').dialog('open');
    		$("#rollbackframe").attr("src",serverName+ "/acceptAdviceController/applyBackInquirePools.do?consultPoolId="+ consultPoolId+"&pool="+1);
    	}
    	
    	function saveRollback(){
    		rollbackframe.window.acceptConsultRollbackForm();
    	}
    	function cancelRollback(){
    		window.frames["rollbackframe"].$("#acceptConsultRollbackForm").form("clear");
    		$("#rollbackDiv").dialog('close');
    	}
    	
    	/********************************退回原因end*************************************************/
    	
    	/*********************************受理咨询start********************************************/
    	function acceptAdvice(consultPoolId,acceptConsultState){
    		$.ajax({url:serverName+"/acceptAdviceController/getConsultPoolInfo.do",
				  data:{
					  consultPoolId:consultPoolId
				  },
				  dataType:"json",
				  success:function(result){
					 
					 if(result.acceptConsultState!=acceptConsultState){
						  $.messager.alert('操作提示','咨询状态与当前状态不一致','error');  
						  searchgrid();
					  }else{
			    		$('#acceptAdviceDiv').dialog('open');
			    		$("#acceptAdviceframe").attr("src",serverName+ "/acceptAdviceController/acceptAdvice.do?consultPoolId="+ consultPoolId);
					  }
					
				  }
		 });
    	}
    	
    	function updateAcceptAdvice(){
    		acceptAdviceframe.window.updateAcceptAdvice();
    	}
    	function cancelAcceptAdvice(){
    		//window.frames["acceptAdviceframe"].$("#acceptInquireForm").form("clear");
    		$("#acceptAdviceDiv").dialog('close');
    	}
    	
    	/********************************受理咨询end*************************************************/
    	
    	/*********************************查看受理咨询start********************************************/
    	function showAcceptAdvice(consultPoolId){
    		$("#showAcceptAdviceDiv").dialog("open");
    		$("#showAcceptAdviceframe").attr("src",serverName+ "/acceptAdviceController/getAcceptAdviceInfo.do?consultPoolId="+ consultPoolId);
    	}
    	
    	/*********************************查看受理咨询end**********************************************/
    	
    	/*********************************申请借款start********************************************/
    //	function addNewClient(consultPoolId){
    		//$("#addNewClientDiv").dialog("open");
    	//	$("#addNewClientframe").attr("src",serverName+ "/acceptAdviceController/showAddNewClint.do?consultPoolId="+ consultPoolId);
    	//}
    	function submitNewClient(){
    		addNewClientframe.window.saveApplyPersonInfo();
    	}
    	/*********************************申请借款end********************************************/
    	
    	function showMessage(a) {
    		$.messager.show({
    					title : '消息',
    					msg : a,
    					timeout : 5000,
    					showType : 'slide'
    				});
    	}
    	
    	//显示借款额度 manzhang
    	function showAmount(datas,row,index){
    		if(row.borrowAmount != undefined && row.borrowAmount != null &&row.borrowAmount != ""){
    		return row.borrowAmount+"万";
    		}
    	}
	</script>
</head>

<body class="easyui-layout">
	<div region="center" id="centerPanel">
		<div id="tabs" class="easyui-tabs" data-options="plain:true"
			style="padding: 5px; width: auto; height: auto; margin-top: 5px; margin-bottom: 4px;">

			<div title="搜索条件" style="padding: 10px;" id="tabDiv1">
				<table>
					<tbody>
						<tr>
							<td>
								客户姓名：
							</td>
							<td>
								<input style="width: 110px;" id="customerName" maxlength="10">
							</td>
							<td>
								咨询方式：
							</td>
							<td>
								<select id="consultWay" name="consultWay" class="easyui-combobox"
									style="width: 150px;" data-options="required:false">
								</select>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>

							<td>
								分配日期：
							</td>
							<td>
								<input class="easyui-datebox" type="text" id="beginDistributeDate"
									name="beginDistributeDate" editable="false"
									data-options="required:false" style="width: 150px;" />
								-
								<input class="easyui-datebox" type="text" editable="false"
									id="endDistributeDate" name="endDistributeDate"
									data-options="required:false" style="width: 150px;" />
							</td>
						</tr>
						<tr>
							<td>
								联系方式：
							</td>
							<td>
								<input type="text" style="width: 150px;" id="mobilePhone" 
									name="mobilePhone">
							</td>
							<td>
								状态：
							</td>
							<td>
								<select id="acceptConsultState" class="easyui-combobox"
									name="acceptConsultState" style="width: 150px;"
									data-options="required:false">
								</select>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>
								分件日期：
							</td>
							<td>
								<input class="easyui-datebox" type="text" id="beginSentDate"
									name="beginSentDate" data-options="required:false"
									style="width: 150px;" editable="false" />
								-
								<input class="easyui-datebox" type="text" id="endSentDate"
									name="endSentDate" data-options="required:false"
									style="width: 150px;" editable="false" />
							</td>
						</tr>
						<tr>
							<td>
								户籍地址：
							</td>
							<td colspan="3">
								<select id="province" class="easyui-combobox" name="province"
									style="width: 120px;">
								</select>
								<select id="city" class="easyui-combobox" name="city"
									style="width: 120px;">
								</select>
								<select id="area" class="easyui-combobox" name="area"
									style="width: 125px;">
								</select>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>
								历史：
							</td>
							 <td>
                      		  <select id="history" class="easyui-combobox" name="history" style="width:152px;">
                        	</select>
                    	</td>
							<td colspan="2">
								<a class="easyui-linkbutton" href="javascript:searchgrid();">搜索</a>
								<a  class="easyui-linkbutton" href="javascript:cleargrid();">清空</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<sec:authorize ifAnyGranted="${control.acceptInquireList_distributeTab}">
			<div title="分件" style="padding: 10px;" id="tabDiv1">
				<table cellpadding="2px">
					<tbody>
						<tr>
							<td>
								分中心营业部：
							</td>

							<td>
								<input id="grantTeamDepartmentId" name="grantTeamDepartmentId"
									 style="width: 260px;" type="text"/>
							</td>
							<td>
								信贷员：
								<input type="text" id="grantProceedName"
									name="grantProceedName"
									style="width: 220px;" />
							</td>
							<td>
							
							<sec:authorize ifAnyGranted="${control.acceptInquireList_distributeOrders}">
									<a href="#" id="distributeOrdersButton"
									class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
									onclick="distributeOrders();">分件</a>
							</sec:authorize>
									&nbsp;
							</td>
							<td>
							<sec:authorize ifAnyGranted="${control.acceptInquireList_distributeConfirm}">
									<a href="#" id="distributeConfirmButton"
									class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
									onclick="distributeConfirm();">确认分件</a>
							</sec:authorize>
							&nbsp;
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
</sec:authorize>
		</div>
<%--受理咨询池列表--%>
		<div class="easyui-tabs" style="padding: 10px;" tools="#tol">
				<div title="受理咨询列表">
					<table id="inquire_pool_office">
					</table>
				</div>
			</div>
<%--查看详情--%>
<div id="registerInquireViewDiv" class="easyui-dialog" closed="true" style="padding: 0px;">
    <iframe id="registerInquireViewIframe" src="" scrolling="no" frameborder="0"
            style="width:100%;height:100%;"></iframe>
</div>
		<div id="rollbackDiv" title="退回原因" style="width:600px;height:300px;" buttons="#edittools1">
			<iframe id="rollbackframe" name="rollbackframe" src="" scrolling="no"
				frameborder="0" style="width: 100%;height: 100%"></iframe>
				
		</div>
		<div id="edittools1">
					<a id="saveRollback" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRollback()">确定</a>
					<a id="cancelRollback" class="easyui-linkbutton" iconCls="icon-undo"  onclick="cancelRollback()">取消</a>
				</div>
				
				
		<div id="rollbackDivConfirm" class='easyui-dialog' closed='true'  title="退回确认" style="width:600px;height:300px;" buttons="#edittools2">
			<iframe id="rollbackframeConfirm" name="rollbackframeConfirm" src="" scrolling="no"
				frameborder="0" style="width: 100%;height: 100%"></iframe>
				
		</div>
		<div id="edittools2">
					<a id="saveRollbackConfirm" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRollbackConfirm()">确定</a>
					<a id="cancelRollbackConfirm" class="easyui-linkbutton" iconCls="icon-undo"  onclick="cancelRollbackConfirm()">取消退回</a>
				</div>
				
				
				
		<div id="acceptAdviceDiv" title="受理客户" iconCls="icon-edit" style="width:700px;height:450px;" buttons="#edittools">
			<iframe id="acceptAdviceframe" name="acceptAdviceframe" src="" scrolling="no"
				frameborder="0" style="width: 100%;height: 100%"></iframe>
				
		</div>
		<div id="edittools">
					<a id="editCus" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateAcceptAdvice()">提交</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"  onclick="cancelAcceptAdvice()">取消</a>
				</div>
     </div>

    <!-- 2014-09-16 add by ygx 身份证验证 -->
    <div id="validateIndentityNumberDialog"
         style="width: 800px; height: 500px; padding: 10px;"
         buttons="#opt_btn">
        <iframe id="validateIndentityNumberFrame" name="validateIndentityNumberFrame" src=""
                scrolling="yes" frameborder="0" style="width: 100%; height: 100%">
        </iframe>
    </div>
  </body>
</html>
