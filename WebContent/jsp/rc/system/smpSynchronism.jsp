<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'smpSynchronism.jsp' starting page</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	 <style>
  html, body {
 height: 100%;
 width: 100%;
}
.white_content {
 display: none;
 position: absolute;
 top: 25%;
 left: 25%;
 width: 50%;

 border: 12px solid #D6E9F1;
 z-index:1002;
}
.black_overlay {
 display: none;
 position: absolute;
 top: 0%;
 left: 0%;
 width: 100%;
 height: 100%;
 background-color:#f5f5f5;
 z-index:1001;
 -moz-opacity: 0.8;
 opacity:.80;
 vertical-align:middle;
 filter: alpha(opacity=80);
}
.close {
 float:right;
 clear:both;
 width:100%;
 text-align:right;
 margin:0 0 6px 0
}
.close a {
 color:#333;
 text-decoration:none;
 font-size:14px;
 font-weight:700
}
.con {
 text-indent:1.5pc;
 line-height:21px
}
</style>
	<script type="text/javascript">
	var serverName="<%=path%>";
		$(function(){
			window.onresize = function() {
				setTimeout(function() {
					$('#creditApplication').resizeDataGrid(140, 20, 0, 0);
					$('#accountInfo').resizeDataGrid(140, 20, 0, 0);
					$('#customerConsult').resizeDataGrid(140, 20, 0, 0);
				}, 500);
			};
			$("#creditApplication").datagrid({
				url : serverName+ "/creditgroup/selectCreditApplicationList.do",
				pagination : true,
				idfield : 'creditapplicationId',
				striped : true,
				singleSelect : false,
				sortName : 'groupNumber',
				sortOrder : 'desc',
				rownumbers : true,
				loadMsg : '正在加载....',
				frozenColumns : [ [ {
						title : 'id',field : 'creditapplicationId',width : '130',checkbox:true
						},{title : '业务单号',field : 'groupNumber',width : '130'
						}, {title : '借款人',field : 'groupName',width : '90' 
				} ] ],
				columns : [ [
						{title : "申请金额(元)",field : 'groupAppTotal',width : '90',hidden : true
						},{title : "审批金额(元)",field : 'amount',width : '90',hidden : true
						},{title : "最终放款金额(元)",field : 'loanAmount',width : '100',hidden : true
						},{title : '产品类型',field : 'repaymentPlanId',width : '100',hidden : true
						},{title : '产品类型',field : 'repaymentPlanName',width : '100',hidden : true
						},{title : '产品额度下限',field : 'capitalLowerLimit',	width : '100',hidden : true
						},{title : '提交日期',field : 'submitDate',width : '100'
						},{title : '放款日期',field : 'signagreementDate',width : '100'
						},{title : '分公司名称',field : 'companyName',width : '100'
						},{title : '分公司名称id(companyId)',field : 'companyId',width : '100'
						},{title : '部门id（departmentId）',field : 'departmentId',width : '100'
						},{title : '客户经理',field : 'loanOfficerName',width : '100'
						},{title : '客户经理id',field : 'loanOfficerId',width : '100'
						},{title : '风险经理id',field : 'fxid',width : '100',hidden : true
						},{title : '申请状态',field : 'auditStatus',width : '100',hidden:true
						},{title : '申请状态',field : 'auditStatusShow',width : '100' 
						},{title : '操作',field : 'operateFlag',width : '150',formatter:function(value,param){
							return "<a href='javascript:updateThisAmp();'>同步smp</a>";
						}
						} ] ],
				toolbar:[{id:"",text:"同步所有SMP",handler:function(){
					$.messager.confirm("消息","批量处理全部数据需要时间较长，确定批量处理吗？",function(r){
						if(r){
							show("creditApplication");
							setInterval("progress()", 5000);
							 ajaxSubmit(serverName+"/creditgroup/updateSmpAll.do",{},function(result){
								if(result.success){
									$('#progressbar').progressbar('setValue', 0);  
									hide("creditApplication");
									$("#creditApplication").datagrid("reload");
									$.messager.show({title:"消息",msg:"成功"});
								}else{
									hide("creditApplication");
									$('#progressbar').progressbar('setValue', 0);  
									$.messager.show({title:"消息",msg:"失败"});
									$("#creditApplication").datagrid("reload");
								}
							}); 
						}
					});
				}}]
			});
			$('#creditApplication').resizeDataGrid(140, 20, 0, 0);
			$("#customerConsult").datagrid({
				url : serverName + "/CustomerConsult/searchPagnation.do",
				pagination : true,
				rownumbers : true,
				idfield : "customerConsultId",
				queryParams:{history:""},
				height:370,
				singleSelect : false,
				loadMsg : '正在加载....',
				columns : [ [  
				{field:"customerConsultId",checkbox:true},
				   {field : "consultDate",title : "咨询时间",width: 125,sortable:true
				},{field : "operateTime",title : "最后操作时间",width: 125,sortable:true 
				},{field : "consultWay",title : "咨询方式",width: 100,hidden:true,sortable:true
				},{field : "consultWayDetail",title : "咨询方式",width: 100,sortable:true
				}, {field : "customerName",title : "客户姓名",	width: 100 
				}, {field : "telphone",title : "联系电话",width: 100 
				}, {field : "address",title : "客户住址",width: 100
				}, {field : "borrowAmount",title : "咨询金额(元)",width: 100
				}, {field : "borrowUse",title : "借款用途",width: 100,hidden:true
				}, {field : "borrowUseDetail",title : "借款用途",width: 100,sortable:true
				}, {field : "infomationSource",title : "信息来源",width: 100,hidden:true
				}, {field : "infomationSourceDetail",title : "信息来源",width: 100,sortable:true
				},{field : "processCauses",title : "处理原因",width: 100,hidden:true,editor : {
						type : 'validatebox'
					}
				},{field : "processResult",title : "客户分类",width: 100,hidden:true
				},{field : "processResultDetail",title : "客户分类",width: 100
				},{field : "processCausesDetail",title : "客户标签",width: 100,editor : {
						type : 'validatebox'
					}
					
				}, {field : "remark",title : "备注",width: 100
				},{field : "departmentName",width: 100,title : "分公司名称"
				},{field : "departmentId",width: 100,title : "分公司ID(areaDepartmentId)"
				}, {field : "receptionist",title : "接待员工",width: 100,	sortable:true
				}, {field : "receptionistId",title : "接待员工ID",width: 100
				}, {field : "count",title : "咨询次数",width: 100 
				}, {field : "status",title : "状态",width: 100,sortable:true,formatter:function(value){
						if($.trim(value) == "1"){
							return "借款中";
						}
						if($.trim(value) == "0"){
							return "未借款";
						}
					}
				},{title:"操作", field:"optionFlag", width: 170,formatter:function(value ){
					return "<a href='javascript:updateSmpcustomerConsult()' >同步smp</a>";}
		        }
				] ],
				toolbar : [ {
					id : "updateSmpAll",
					text : "同步所有SMP",
					handler : function() {
						$.messager.confirm("消息","批量处理全部数据需要时间较长，确定批量处理吗？",function(r){
							if(r){
								show("customerConsult");
								setInterval("progress()", 5000);
								 ajaxSubmit(serverName+"/CustomerConsult/updateSmpAll.do",{},function(result){
									if(result.success){
										$('#progressbar').progressbar('setValue', 0);  
										hide("customerConsult");
										$("#customerConsult").datagrid("reload");
										$.messager.show({title:"消息",msg:"成功"});
									}else{
										hide("customerConsult");
										$('#progressbar').progressbar('setValue', 0);  
										$.messager.show({title:"消息",msg:"失败"});
										$("#customerConsult").datagrid("reload");
									}
								}); 
							}
						});
					}
					}
				]
			});
			$('#customerConsult').resizeDataGrid(140, 20, 0, 0);
			
			
			//初始化分公司
			/*$("#companyName").combobox({
				url : serverName + "/dataBestowalController/getDepartmentList.do",
				valueField : 'departmentId',
				textField : 'departmentName',
				onSelect:function(data){
					$("#new_loanOfficerName").combobox("setValue","");
					$("#old_loanOfficerName").combobox({
						url : serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId="+data.departmentId,
						valueField : 'LOAN_OFFICER_ID',
						textField : 'LOAN_OFFICER_NAME'
					});
				}
			});*/
            /*2014-07-30 新增操作时，分公司名称修改为树形选择菜单*/
            ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function (r) {
                $("#companyName").combotree({
                    data: r,
                    multiple: false,
                    editable: false,
                    required: false,
                    panelWidth: 250,
                    onSelect: function (data) {
                        var children = $("#companyName").tree("getChildren", data.target);
                        if (children.length) {
                            $.messager.alert('提示', '请选择营业部', 'info');
                            $("#companyName").combotree('clear');
                        } else {
                            $("#new_loanOfficerName").combobox("setValue", "");
                            $("#old_loanOfficerName").combobox({
                                url: serverName + "/dataBestowalController/querySysloanOfficerList.do?companyId=" + data.id,
                                valueField: 'LOAN_OFFICER_ID',
                                textField: 'LOAN_OFFICER_NAME'
                            });
                        }
                    }
                });
            });
		});
		function updateSmpcustomerConsult(){
			var dataGrid = $("#customerConsult").datagrid("getSelected");
			ajaxSubmit(serverName+"/CustomerConsult/updateSmp.do",{receptionistId:dataGrid.receptionistId,customerConsultId:dataGrid.customerConsultId},function(result){
				if(result.success){
					$.messager.show({title:"消息",msg:"成功"});
					$("#customerConsult").datagrid("reload");
				}else{
					$.messager.show({title:"消息",msg:"失败"});
					$("#customerConsult").datagrid("reload");
				}
			})
		}
		function updateThisAmp(){
			var dataGrids = $("#creditApplication").datagrid("getSelections");
			if(dataGrids.length != 1){
				alert("请选择一条数据");
				return;
			}else{
				var dataGrid = dataGrids[0];
				ajaxSubmit(serverName+"/creditgroup/updateSmp.do",{loanOfficerId:dataGrid.loanOfficerId,creditapplicationId:dataGrid.creditapplicationId},function(result){
					if(result.success){
						$.messager.show({title:"消息",msg:"成功"});
						$("#creditApplication").datagrid("reload");
					}else{
						$.messager.show({title:"消息",msg:"失败"});
						$("#creditApplication").datagrid("reload");
					}
				});
			}
		}
		
		function progress(){
			 var val = $("#progressbar").progressbar('getValue');  
			 if (val < 100){  
	                val += Math.floor(Math.random() * 10);  
	                $('#progressbar').progressbar('setValue', val);  
	               // setTimeout(arguments.callee, 200);  
	            }  
		}
		function show(tag){
			 var light=document.getElementById(tag);
			 var fade=document.getElementById('fade');
			 light.style.display='block';
			 fade.style.display='block';
			 }
		function hide(tag){
		 var light=document.getElementById(tag);
		 var fade=document.getElementById('fade');
		 light.style.display='none';
		 fade.style.display='none';
		}
		
		//查询咨询列表
		function customerConsultList(){
			$("#customerConsult").datagrid("load",{
				receptionistId:$("#receptionistId").val(),
				receptionist:$("#receptionist").val(),
				history:""
			});
		}
		
		//查询申请列表
		function searchCreditList(){
			$("#creditApplication").datagrid("load",{
				loanOfficerName:$("#loanOfficerName").val(),
				loanOfficerId:$("#loanOfficerId").val(),
				groupNumber:$("#businessNumber").val()
			});
		}
		
		
		/**
		 * 精确查询
		 */
		function queryAllCondition(customerConsult){
            console.info($("#companyName").combotree("getValue"));
			if($("#companyName").combotree("getValue") == undefined ||
					   $("#companyName").combotree("getValue") == null ||
					   $("#companyName").combotree("getValue") == ""){
						alert("没有选择分公司");
						return;
					}
					if($("#old_loanOfficerName").combobox("getValue") == undefined ||
					   $("#old_loanOfficerName").combobox("getValue") == null ||
					   $("#old_loanOfficerName").combobox("getValue") == ""){
						alert("没有选择原客户经理");
						return;
					}
			if(customerConsult == '1'){
				var	loanOfficerId=$("#old_loanOfficerName").combobox("getValue");
				$("#listTab").tabs("select","客户咨询");
				$("#customerConsult").datagrid("load",
						{
					receptionistId:loanOfficerId
						});
			}else{
				
				$("#listTab").tabs("select","信贷申请");
				$("#creditApplication").datagrid("load",
						{
					loanOfficerId:$("#old_loanOfficerName").combobox("getValue")
						});
			}
		}
		
		
		//同步申请的权限
		function synchroAurhority(){
					if($("#companyName").combotree("getValue") == undefined ||
					   $("#companyName").combotree("getValue") == null ||
					   $("#companyName").combotree("getValue") == ""){
						$("#submitDataBestowal").linkbutton("enable");
						alert("没有选择分公司");
						return;
					}
					if($("#old_loanOfficerName").combobox("getValue") == undefined ||
					   $("#old_loanOfficerName").combobox("getValue") == null ||
					   $("#old_loanOfficerName").combobox("getValue") == ""){
						$("#submitDataBestowal").linkbutton("enable");
						alert("没有选择原客户经理");
						return;
					}
					var loanOfficerId =$("#old_loanOfficerName").combobox("getValue");
					var data = $("#creditApplication").datagrid("getSelections");
					//选中的数据个数
					var length = data.length;
					if(length == 0){
						$.messager.confirm("提示","在没有选择数据的情况下将会同步该客户经理下所有数据权限，确定执行吗？",function(r){
							if(r){
								$("#synchroAurhorityButton").linkbutton("disable");
								ajaxSubmit(serverName+"/dataBestowalController/updateCreditApplicationAuthorization.do",{loanOfficerId:loanOfficerId},function(result){
									if(result.success){
										$("#synchroAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
								});
							}
						});
					}else{
						$.messager.confirm("提示","确定同步该"+length+"条数据权限吗？",function(r){
							if(r){
								$("#synchroAurhorityButton").linkbutton("disable");
									var creditApplicationIds = new Array();
									for(var i = 0 ; i < length;i++){
										creditApplicationIds.push(data[i].creditapplicationId);
									}
									creditApplicationIds = JSON.stringify(creditApplicationIds);
								ajaxSubmit(serverName+"/dataBestowalController/updateCreditApplicationAuthorization.do",{loanOfficerId:loanOfficerId,objectIds:creditApplicationIds},function(result){
									if(result.success){
										$("#synchroAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
										$("#creditApplication").datagrid("unselectAll");
									}else{
										$("#synchroAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
								});
							}
						});
					}
		}
		
		//同步咨询的权限
		function synchroConsultAurhority(){
					if($("#companyName").combotree("getValue") == undefined ||
					   $("#companyName").combotree("getValue") == null ||
					   $("#companyName").combotree("getValue") == ""){
						$("#submitDataBestowal").linkbutton("enable");
						alert("没有选择分公司");
						return;
					}
					if($("#old_loanOfficerName").combobox("getValue") == undefined ||
					   $("#old_loanOfficerName").combobox("getValue") == null ||
					   $("#old_loanOfficerName").combobox("getValue") == ""){
						$("#submitDataBestowal").linkbutton("enable");
						alert("没有选择原客户经理");
						return;
					}
					var loanOfficerId =$("#old_loanOfficerName").combobox("getValue");
					var data = $("#customerConsult").datagrid("getSelections");
					//选中的数据个数
					var length = data.length;
					if(length == 0){
						$.messager.confirm("提示","在没有选择数据的情况下将会同步该客户经理下所有咨询数据权限，确定执行吗？",function(r){
							if(r){
								$("#synchroConsultAurhorityButton").linkbutton("disable");
								ajaxSubmit(serverName+"/dataBestowalController/updateCustomerConsultAuthorization.do",{loanOfficerId:loanOfficerId},function(result){
									if(result.success){
										$("#synchroConsultAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroConsultAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
								});
							}
						});
					}else{
						$.messager.confirm("提示","确定同步该"+length+"条数据权限吗？",function(r){
							if(r){
								$("#synchroConsultAurhorityButton").linkbutton("disable");
									var objectIds = new Array();
									for(var i = 0 ; i < length;i++){
										objectIds.push(data[i].customerConsultId);
									}
									objectIds = JSON.stringify(objectIds);
								ajaxSubmit(serverName+"/dataBestowalController/updateCustomerConsultAuthorization.do",{loanOfficerId:loanOfficerId,objectIds:objectIds},function(result){
									if(result.success){
										$("#synchroConsultAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
										$("#customerConsult").datagrid("unselectAll");
									}else{
										$("#synchroConsultAurhorityButton").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
								});
							}
						});
					}
		}
		
		//按营业部更新权限
		function synchroAurhorityByDepartment(){
			if($("#companyName").combotree("getValue") == undefined ||
					   $("#companyName").combotree("getValue") == null ||
					   $("#companyName").combotree("getValue") == ""){
						$("#submitDataBestowal").linkbutton("enable");
						alert("没有选择分公司");
						return;
					}
			//获得营业部id
			var departmentId = $("#companyName").combotree("getValue");
			//客户咨询数据
			var dataCustomer = $("#customerConsult").datagrid("getSelections");
			//信贷申请数据
			var dataApplication = $("#creditApplication").datagrid("getSelections");
			var dataCustomerLength = dataCustomer.length;
			var dataApplicationLength = dataApplication.length;
			if((dataCustomerLength == 0 && dataApplicationLength ==0) 
			|| (dataCustomerLength == 0 && dataApplicationLength == undefined) ||
			(dataCustomerLength == undefined && dataApplicationLength == 0) ||
			(dataCustomerLength == undefined && dataApplicationLength == undefined)){
				$.messager.confirm("提示","在没有选择数据的情况下将会同步该营业部下所有客户经理的所有咨询和申请的数据权限，确定执行吗？",function(r){
					if(r){
						$("#synchroAurhorityByDepartment").linkbutton("disable");
						ajaxSubmit(serverName+"/dataBestowalController/updatesynchroAurhorityByDepartment.do",{departmentId:departmentId},function(result){
									if(result.success){
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
								});
					}
				});
			}else if((dataCustomerLength == 0 && dataApplicationLength >0) || 
					  (dataCustomerLength == undefined && dataApplicationLength >0)){//更新申请数据权限
				$.messager.confirm("提示","同步该营业部下所有客户经理的"+dataApplicationLength+"条申请的数据权限，确定执行吗？",function(r){
					if(r){
						$("#synchroAurhorityByDepartment").linkbutton("disable");
						//得到申请数据的id
						var creditApplicationIds = new Array();
						for(var i = 0 ; i < dataApplicationLength;i++){
							creditApplicationIds.push(dataApplication[i].creditapplicationId);
						}
						creditApplicationIds = JSON.stringify(creditApplicationIds);
						ajaxSubmit(serverName+"/dataBestowalController/updatesynchroAurhorityByDepartment.do",{departmentId:departmentId,creditApplicationIds:creditApplicationIds},function(result){
									if(result.success){
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
						});
					}
				});
			}else if((dataCustomerLength > 0 && dataApplicationLength ==0) ||
					  (dataCustomerLength > 0 && dataApplicationLength ==undefined)){//更新咨询数据权限
				$.messager.confirm("提示","同步该营业部下所有客户经理的"+dataCustomerLength+"条咨询的数据权限，确定执行吗？",function(r){
					if(r){
						$("#synchroAurhorityByDepartment").linkbutton("disable");
						//咨询数据id
						var customerIds = new Array();
						for(var i = 0 ; i < dataCustomerLength;i++){
							customerIds.push(dataCustomer[i].customerConsultId);
						}
						customerIds = JSON.stringify(customerIds);
						ajaxSubmit(serverName+"/dataBestowalController/updatesynchroAurhorityByDepartment.do",{departmentId:departmentId,customerIds:customerIds},function(result){
									if(result.success){
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
						});
					}
				});
			}else if(dataCustomerLength > 0 && dataApplicationLength >0){//更新咨询和申请数据权限
				$.messager.confirm("提示","同步该营业部下所有客户经理的"+dataCustomerLength+"条咨询,"+dataApplicationLength+"条申请的数据权限，确定执行吗？",function(r){
					if(r){
						$("#synchroAurhorityByDepartment").linkbutton("disable");
						//得到申请数据的id
						var creditApplicationIds = new Array();
						for(var i = 0 ; i < dataApplicationLength;i++){
							creditApplicationIds.push(dataApplication[i].creditapplicationId);
						}
						creditApplicationIds = JSON.stringify(creditApplicationIds);
						//咨询数据id
						var customerIds = new Array();
						for(var i = 0 ; i < dataCustomerLength;i++){
							customerIds.push(dataCustomer[i].customerConsultId);
						}
						customerIds = JSON.stringify(customerIds);
						ajaxSubmit(serverName+"/dataBestowalController/updatesynchroAurhorityByDepartment.do",{departmentId:departmentId,customerIds:customerIds,creditApplicationIds:creditApplicationIds},function(result){
									if(result.success){
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}else{
										$("#synchroAurhorityByDepartment").linkbutton("enable");
										$.messager.alert("消息",result.msg);
									}
						});
					}
				});
			}
			
		}
		
		function syncDepartmentMap(){
			$("#synchroDepartmentMapButton").linkbutton("disable");
			ajaxSubmit(serverName+"/easyUIController/syncDepartmentMap.do",{},function(r){
				$.messager.alert("消息",r.msg,"info");
				$("#synchroDepartmentMapButton").linkbutton("enable");
			});
		} 
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="center">
  		<div>
  				<table>
  							<tr>
									<td><font style="size: landscape;">检索条件：</font></td>
									<td align="right">分公司名称：</td>
									<td><input style="width: 150px;" name="companyName" id="companyName" class="easyui-combobox"/></td>
									<td align="right">客户经理：</td>
									<td><input style="width: 150px;" name="old_loanOfficerName" id="old_loanOfficerName" class="easyui-combobox"/></td>
									<td align="center">
									
									</td>
							</tr>
							<tr>
								<td><font style="size: landscape;">执行操作：</font>
								</td>
								<td align="center"><a class="easyui-linkbutton" href="javascript:queryAllCondition();">搜索申请</a></td>
								<td align="center"><a class="easyui-linkbutton" href="javascript:queryAllCondition('1');">搜索咨询</a></td>
								<td align="center" colspan="2"> 
									 <sec:authorize ifAnyGranted="${control.dataBestowalList_synchroAurhority}">
									 <a class="easyui-linkbutton" id="synchroAurhorityButton" href="javascript:synchroAurhority();">申请权限同步</a>
								</sec:authorize>
								&nbsp;&nbsp;
								 <sec:authorize ifAnyGranted="${control.dataBestowalList_synchroConsultAurhority}">
									 <a class="easyui-linkbutton" id="synchroConsultAurhorityButton" href="javascript:synchroConsultAurhority();">咨询权限同步</a>
									 </sec:authorize>
									 &nbsp;&nbsp;
									 <sec:authorize ifAnyGranted="${control.dataBestowalList_syncDepartmentMap}">
									 <a class="easyui-linkbutton" id="synchroDepartmentMapButton" href="javascript:syncDepartmentMap();">营业部MAP同步</a>
									 </sec:authorize><!--
									 &nbsp;&nbsp;
									 <sec:authorize ifAnyGranted="${control.dataBestowalList_synchroAurhorityByDepartment}">
									 <a class="easyui-linkbutton" id="synchroAurhorityByDepartment" href="javascript:synchroAurhorityByDepartment();">营业部权限同步</a>
									 </sec:authorize>
								--></td>
							</tr>
				</table>
  		<div>
	    <div class="easyui-tabs" id="listTab">
	    	<div title="信贷申请">
	    		<div style="padding: 10px;">
		    		客户经理id：<input id="loanOfficerId"/>
		    		客户经理姓名：<input id="loanOfficerName"/>
		    		业务编号：<input style="width: 148px;" id="businessNumber"/>
		    		<a class="easyui-linkbutton" onclick="javascript:searchCreditList();">查询</a>
	    		</div>
	    		<table id="creditApplication" >
	    		</table>
	    	</div>
	    	<%--<div title="账户信息">
	    		<table id="accountInfo"></table>
	    	</div>
	    	--%><div title="客户咨询">
	    		<div style="padding: 10px;">
		    		接待员工ID：<input id="receptionistId"/>
		    		接待员工姓名：<input id="receptionist"/>
		    		<a class="easyui-linkbutton" onclick="javascript:customerConsultList();">查询</a>
		    	</div>
	    		<table id="customerConsult"></table>
	    	</div>
	    </div>
	    <div id="fade" class="black_overlay">
   		    <div id="progressbar" class="easyui-progressbar" style="width:400px;position: absolute;
 top: 50%;
 left: 30%;"></div>
   		</div>
	   </div>
	  
  </body>
</html>
