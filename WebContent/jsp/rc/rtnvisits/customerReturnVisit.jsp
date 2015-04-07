<%@ page language="java"  import="java.util.*,com.creditease.rc.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
    String cmUrl = properties.getProperty("cm.url");
    String isURL = request.getRequestURL().toString();
    if (isURL.indexOf(".cn") > 0) {
        if (cmUrl.indexOf(".corp") > 0) {
            cmUrl = cmUrl.replaceAll(".corp", ".cn");
        }
    } else if (isURL.indexOf(".corp") > 0) {
        if (cmUrl.indexOf(".cn") > 0) {
            cmUrl = cmUrl.replaceAll(".cn", ".corp");
        }
    }
    String cmIp = properties.getProperty("cm.hostip");
    DESPlus desPlus = new DESPlus();
    String DESNow = desPlus.encrypt(new Date().getTime() + "");
    String DESIp = desPlus.encrypt(cmIp);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户回访列表页</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/rtnvisits/customerReturnVisit.js"></script>
	<script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        var cmUrl = "<%=cmUrl%>";
        var DESNow = "<%=DESNow%>";
        var DESIp = "<%=DESIp%>";

		//datagrid 高度
		window.onresize = function() {
			setTimeout(function() {
				var tabHeight2 = $("#tabs").height();
		    	var heightMa2 = 50+tabHeight2;
				$('#creditApplicationSearchTable').resizeDataGrid(heightMa2, 15, 0, 0);
			}, 500);
		};

		//datagrid 高度
		var tabHeight = $("#tabs").height();
		$("#tabs").tabs({
			onSelect :function(data){
				var tabHeight1 = $("#tabs").height();
				var heightMa1 = 50+tabHeight1;
				 $('#creditApplicationSearchTable').resizeDataGrid(heightMa1, 15, 0, 0);  
			}
		});
		var heightMa = 50+tabHeight;
		$('#creditApplicationSearchTable').resizeDataGrid(heightMa, 15, 0, 0);
		//datagrid 高度




        //查看附件
        function viewAttachment(attatchmentId) {
            var href = cmUrl + "/operation/transferParameter.action?clientId=" + attatchmentId + "&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime=" + DESNow
                    + "&signIp=" + DESIp + "";
            window.open(href);
        }
	</script>
  </head>
  
 <body class="easyui-layout">
    	<div region="center">
    		<div id="tabs" class="easyui-tabs" style="padding: 5px;">
    			<div id="" title="条件查询">
    				<div style="margin: 5px;">
						<table>
							<tr>
								<td  >业务单号：<input id="businessBumber"/>
									<span style="margin-left: 50px">
										回访日期：<input id="beginReturnVisitDate" class="easyui-datebox">&nbsp;&nbsp; 至&nbsp;&nbsp; <input id="endReturnVisitDate" class="easyui-datebox">
									</span>
								</td>


							</tr>
							<tr>
								<td >借款人 ：<input id="borrowerName">
								<span style="margin-left: 150px">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="easyui-linkbutton" onclick="searchPagination(0);">搜索</a>
									<a class="easyui-linkbutton" onclick="cleanInput();">清空</a>
									<sec:authorize ifAnyGranted="${control.customerReturnVisit_exportExcel}">
										<a class="easyui-linkbutton" onclick="exportExcel(0);">导出报表</a>
									</sec:authorize>
									</span>
								</td>


							</tr>
						</table>
		 			</div>
    			</div>
				<div title="高级查询">
					<form id="searchform" method="post">
						<br />
						<table width="100%">
							<tr>
								<td >
									分公司名称：<input id="companyId" name="companyId" type="text"  />
						        <span style="margin-left: 50px">
									产品类型：<input id="productTypeName" name="productTypeName" style="width: 122px;"/>
								</span>
								<span style="margin-left: 50px">
									客户经理：<input id="loanOfficerName">
								</span>
								</td>

								</tr>
							<tr>
								<td colspan="2">还款日期：<input id="beginRepaymentDate" class="easyui-datebox">&nbsp;&nbsp; 至&nbsp;&nbsp; <input id="endRepaymentDate" class="easyui-datebox">
								<span style="margin-left: 150px">
									<a class="easyui-linkbutton" onclick="searchPagination(1);">搜索</a>

									<a class="easyui-linkbutton" onclick="cleanInput();">清空</a>

									<sec:authorize ifAnyGranted="${control.customerReturnVisit_exportExcel}">
										<a class="easyui-linkbutton" onclick="exportExcel(1);">导出报表</a>
									</sec:authorize>
									</span>
								</td>
							</tr>
						</table>
						<br />
					</form>
				</div>

			</div>
    		<div style="padding: 5px;" >
    			<table id="creditApplicationSearchTable"></table>
    		</div>

		</div>
  </body>
</html>
