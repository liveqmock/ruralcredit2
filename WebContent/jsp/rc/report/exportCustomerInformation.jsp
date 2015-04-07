<%@ page language="java" import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'exportCustomerInformation.jsp' starting page</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
	<script type="text/javascript">
	var param = "";
	window.onresize = function(){
	    setTimeout(function(){
	  	  $('#exportCustomerInformationtable').resizeDataGrid(50, 0, 0, 0);
	    }, 500);
	};
		$(function(){
			$("#exportCustomerInformationtable").datagrid({
				url:"<%=basePath%>borrowerServiceApp/exportCustomerInformationtList.do",
				rownumbers: true,
				pagination : true,
				title: "客户基本信息",
				singleSelect: true,
				toolbar : "#toobar",
				columns : [[{field : "客户姓名",title:"客户姓名",width:100},
				            {field : "城市",title:"城市",width:100},
				            {field : "性别",title:"性别",width:100},
				            {field : "身份证号",title:"身份证号",width:100},
				            {field : "年龄",title:"年龄",width:100},
				            {field : "联系方式",title:"联系方式",width:100},
				            {field : "借款金额",title:"借款金额",width:100},
				            {field : "财务付款额",title:"财务付款额",width:100},
				            {field : "放款时间",title:"放款时间",width:100},
				            {field : "还款时间",title:"还款时间",width:100},
				            {field : "家庭住址",title:"家庭住址",width:100},
				            {field : "借款用途",title:"借款用途",width:100},
				            {field : "营业部",title:"营业部",width:100},
				            {field : "产品名称",title:"产品名称",width:100},
				            {field : "客户经理",title:"客户经理",width:100}]]
			}); 
			 $('#exportCustomerInformationtable').resizeDataGrid(50, 0, 0, 0);
			 
			 $("#beginLoanDate").datebox({
					onSelect : function() {
						var beginLoanDate = $("#beginLoanDate").datebox("getValue");
						var endLoanDate = $("#endLoanDate").datebox("getValue");
						if (endLoanDate != null && endLoanDate != "") {
							if (beginLoanDate > endLoanDate) {
								$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
								$("#beginLoanDate").datebox("setValue", "");
								return;
							}
						}

					}
				});
				$("#endLoanDate").datebox({
					onSelect : function() {
						var beginLoanDate = $("#beginLoanDate").datebox("getValue");
						var endLoanDate = $("#endLoanDate").datebox("getValue");
						if (beginLoanDate != null && beginLoanDate != "") {
							if (beginLoanDate > endLoanDate) {
								$.messager.alert("消息", "时间选择有误！结束时间应该大于等于开始时间。");
								$("#endLoanDate").datebox("setValue", "");
								return;
							}
						}

					}
				});

		});
		function clearCondotion(){
			 $("#beginLoanDate").datebox("setValue","");
			 $("#endLoanDate").datebox("setValue","");
		}
	
		function toDate(param){
			var returnDate = new Date(param);
			var year = returnDate.getFullYear();
			var month = returnDate.getMonth()+1;
			var day = returnDate.getDate();
			return year +"-"+month+"-"+day ;
		}
		function searchCustomerInformation(){
			$("#exportCustomerInformationtable").datagrid("load",{
				beginLoanDate : $("#beginLoanDate").datebox("getValue"),
				endLoanDate : $("#endLoanDate").datebox("getValue")
			});
			
		    param = "beginLoanDate="+$("#beginLoanDate").datebox("getValue")+"&endLoanDate="+$("#endLoanDate").datebox("getValue");
		}
		
		function exportCustomerInformation(){
			$.messager.confirm('提示信息','确认要导出报表吗?',function(r){
				if (r){
					window.location.href = "<%=basePath%>borrowerServiceApp/exportCustomerInformationt.do?"+param+"";
				}
			});
		}
	</script>
  </head>
  <body class="easyui-layout" id="cc">
    <div region="center" >
    <div>
    	 <table id="exportCustomerInformationtable">		
    		</table>
    		</div>
    </div>
     <div id="toobar">
    	放款时间 ：<input id="beginLoanDate" class="easyui-datebox"/> 至 <input id="endLoanDate" class="easyui-datebox"/>
    	<a class="easyui-linkbutton" onclick="searchCustomerInformation();">查询</a>
    	<sec:authorize ifAnyGranted="${control.exportCustomerInformation_exportCustomerInformation}">
           		<a class="easyui-linkbutton" onclick="exportCustomerInformation();">导出报表</a>
		 </sec:authorize> 
    	<a class="easyui-linkbutton" onclick="clearCondotion();">清空条件</a>
    </div>
  </body>
  
  
</html>
