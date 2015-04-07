<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'dataBestowal.jsp' starting page</title>
	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
		var serverName = "<%=path%>";
		var basePath = "<%=basePath%>";
		$(function(){
			window.onresize = function() {
				setTimeout(function() {
					$('#dataBestowal').resizeDataGrid(150, 20, 0, 0);
				}, 500);
			};
			$("#dataBestowal").datagrid({
				url:"<%=basePath%>/dataBestowalController/selectDataBestowalPaigantion.do",
				pagination : true,
				striped : true,
				singleSelect : true,
				rownumbers : true,
				loadMsg : '正在加载....',
				columns:[[

					{title:"原客户经理id",field:"oldLoanOfficerId",width:150,hidden:true},
					{title:"原客户经理姓名",field:"oldLoanOfficerName",width:160},
					{title:"新客户经理id",field:"newLoanOfficerId",width:150,hidden:true},
					{title:"新客户经理姓名",field:"newLoanOfficerName",width:160},
						{title:"操作人id",field:"operatingId",width:150,hidden:true},
					{title:"操作人姓名",field:"operatingName",width:160},
					{title:"操作时间",field:"operatingTime",width:160},
					{title:"备注",field:"note",width:250},
					{title:"操作",field:"dataBestowalId",width:160,formatter:function(value){
						return "<a href='javascript:dataBestowalDetail("+value+")'>查看详情</a>";
					}}
				]]
			});
			$('#dataBestowal').resizeDataGrid(150, 20, 0, 0);
		});
		function dataBestowalDetail(dataBestowalId){
			var data = $('#dataBestowal').datagrid("getSelected");
			var note = data.note;
			if(note != undefined && note !=null && note.indexOf("咨询赠与") == 0){
				$("#dataBestowalDetail").datagrid({
					url:"<%=basePath%>/dataBestowalController/selectCredit.do?customerConsult=1",
					queryParams:{dataBestowalId:dataBestowalId},
					fitColumns:true,
					striped : true,
					singleSelect : true,
					rownumbers : true,
					loadMsg : '正在加载....',
					columns:[[
						{title:"id",field:"DATA_BESTOWAL_DETAIL_ID",width:170,hidden:true},
						{title:"主表id",field:"DATA_BESTOWAL_ID",width:170,hidden:true},
						{title:"申请id",field:"CREDIT_APPLICATION_ID",width:170,hidden:true},
						{title:"电话号码",field:"TELPHONE",width:170},
						{title:"赠与时的状态",field:"AUDIT_STATUS",width:170,formatter:function(value){
							if(value =='1'){
								return "已经申请";
							}else{
								return "未申请";
							}
						}}
					]]
				});
			}else{
				$("#dataBestowalDetail").datagrid({
					url:"<%=basePath%>/dataBestowalController/selectCredit.do",
					queryParams:{dataBestowalId:dataBestowalId},
					fitColumns:true,
					striped : true,
					singleSelect : true,
					rownumbers : true,
					loadMsg : '正在加载....',
					columns:[[
						{title:"id",field:"DATA_BESTOWAL_DETAIL_ID",width:170,hidden:true},
						{title:"主表id",field:"DATA_BESTOWAL_ID",width:170,hidden:true},
						{title:"申请id",field:"CREDIT_APPLICATION_ID",width:170,hidden:true},
						{title:"业务编号",field:"BUSINESS_NUMBER",width:170},
						{title:"转赠时的状态",field:"AUDIT_STATUS_SHOW",width:170},
						{title:"状态",field:"auditStatus",width:150,hidden:true}, {
							field : 'SIGNAGREEMENT_DATE',
							title : '放款日期',
							width : 150,
							formatter : function(value) {
								if ("" == value || value == null) {
									return "";
								} else {
									var date = new Date(value);
									var year = date.getFullYear();
									var month = date.getMonth() + 1;
									var day = date.getDate();

									return year + "年" + month + "月" + day + "日";
								}
							}
						},
						{title:"是否逾期",field:"IS_OVERDUE",width:150,hidden:false,formatter:function(value){
							if("1"==value){
								return "是";
							}else{
								return "否";
							}
						}}
					]]
				});
			}
			$("#dataBestowalDetailDialog").dialog({
				title:"详情",
				width : 600,
				height:400,
				closed:true,
				draggable:false,
				modal:true
			});
			$("#dataBestowalDetailDialog").dialog("open");
		}


		function searchDataBestowal(){
			$("#dataBestowal").datagrid("load",{
				oldLoanOfficerName:$.trim($("#oldLoanOfficerName").val()),
				newLoanOfficerName:$.trim($("#newLoanOfficerName").val()),
				operatingName:$.trim($("#operatingName").val())
			});
		}

		function clearDataBestowal(){
			$("#oldLoanOfficerName").val("");
			$("#newLoanOfficerName").val("");
			$("#operatingName").val("");
		}
		//按业务编号查询
		function searchbusiness(){
			var businessNumber = $("#businessNumber").val();
			if(businessNumber == null||
					businessNumber == ""||
					businessNumber == undefined){
				alert("条件为空");
				return;
			}
			$("#dataBestowal").datagrid("load",{
				businessNumber:businessNumber
			});
		}

        /*按查询条件导出数据*/
        function exportExcel() {
            var tab = $('#tt').tabs('getSelected');
            var index = $('#tt').tabs('getTabIndex', tab);
            var url = serverName + '/dataBestowalController/exportDataBestowal.do';
            var req = '';
            if (index == 0) {
                req = 'oldLoanOfficerName=' + $.trim($("#oldLoanOfficerName").val()) +
                '&newLoanOfficerName=' + $.trim($("#newLoanOfficerName").val()) +
                '&operatingName=' + $.trim($("#operatingName").val());
            } else if (index == 1) {
                req = 'businessNumber=' + $.trim($("#businessNumber").val());
            }
            window.location.href = url + '?' + req;
        }
	</script>
  </head>

  <body class="easyui-layout">
  	<div region="center">
  		<div id="tt" class="easyui-tabs" style="padding: 10px;">
  			<div title="查询" style="padding: 10px;">
  				原客户经理姓名：<input id="oldLoanOfficerName"/>
  				新客户经理姓名：<input id="newLoanOfficerName"/>
  				操作人姓名：<input id="operatingName"/>
  				<a class="easyui-linkbutton" href="javascript:searchDataBestowal()">搜索</a>
  				<a class="easyui-linkbutton" href="javascript:clearDataBestowal()">清空</a>
  			</div>
  			<div title="按业务编号查询" style="padding: 10px;">
  				业务编号：<input id="businessNumber"/>
  				<a class="easyui-linkbutton" href="javascript:searchbusiness()">搜索</a>
  			</div>
  		</div>
  		<div style="padding: 10px;">
  			<table id="dataBestowal" toolbar="#toolbar1"></table>
  		</div>
  		<div  id="dataBestowalDetailDialog" closed="true">
			<table id="dataBestowalDetail"></table>
  		</div>
		<sec:authorize ifAnyGranted="${control.datagiftret_exp}">
			<div id="toolbar1">
				<table>
					<tr>
						<td align="left">
							<a href="javascript:void(0)" class="easyui-linkbutton"
							   iconCls="icon-save" plain="true" onclick="exportExcel()">导出Excel表格</a>
						</td>
					</tr>
				</table>
			</div>
		</sec:authorize>
  	</div>
  </body>
</html>
