<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
			var serverName="<%=path%>";
			$(function(){
				$("#gaoji").hide();
				$("#loanList").datagrid({
					url: serverName+"/scripts/data/loanList.json",
					pagination : true,
					idfield:'creditapplicationId',
					striped:true,
					singleSelect:true,
					sortName : 'contactNumber',
					sortOrder : 'desc',
					columns:[[{
						title:'乡村镇',
						field:'address', 
						width:'200'
					},{
						title:'产品类型',
						field:'repaymentPlanId', 
						width:'100',
						formatter: function(value){
							if(value == "1"){
								return "宜农贷";
							}else{
								return "付息通";
							}
						}
					},{
						title:'借款金额',
						field:'loanOfficerName', 
						width:'100'
					},{
						title:'放款费',
						field:'submitDate', 
						width:'100'
					},{
						title:'实发金额',
						field:'expectLoanDate', 
						width:'100'
					},{
						title:'放款日期',
						field:'signagreementDate', 
						width:'100'
					},{
						title:'客户经理',
						field:'loanOfficerName', 
						width:'100'
					},{
						title:'欠款总额',
						field:'auditStatus', 
						width:'100',
						formatter: function(value){
						}
					}
			]],
					frozenColumns:[[
					                {
										title:'操作',
										field:'operateFlag', 
										width:'230',
										formatter : function(value){
												return 	"<a href='javascript:showletout();'>违约还款登记</a>" ;
										}
									},{
										title:'小组编号',
										field:'groupNumber', 
										width:'90'
									},{
										title:'借款人姓名',
										field:'groupName', 
										width:'90' 
									}
					]]
				});
			});
			function showgaoji(){
				$("#sousuo").hide();
				$("#gaoji").show();
			}
			function showsousuo(){
				$("#sousuo").show();
				$("#gaoji").hide();
			}
			function showletout(){
				window.location ="/ruralcredit2/jsp/rc/business/letoffApply.jsp";
			}
		</script>
	</head>

	<body class="easyui-layout" fit="true">
		<div region="center">
			<div class="easyui-panel" title="搜索">
				<div id="sousuo" >
				条件:<input  style="width: 120px;"/>状态:<input style="width: 120px;"/><input type="button" value="搜索"/>
				 <input type="button" value="高级搜索" onclick="showgaoji();"/>
				 </div>
				 <div id="gaoji"   title="高级搜索">
				小组编号：<input style="width: 120px;"/>
				借款人姓名：<input  style="width: 120px;"/>	客户经理:<input  style="width: 120px;"/><br/>
				逾期天数:<input style="width: 120px;"/>违约开始日期：<input class="easyui-datebox" style="width: 120px;"/> 至 <input class="easyui-datebox" style="width: 120px;"/> <input type="button" value="搜索"/>
				 <input type="button" value="基础搜索" onclick="showsousuo();"/> 
			</div>
			</div>
			
			<div>
				<table id="loanList">
				</table>
			</div>
		</div>
	</body>
</html>
