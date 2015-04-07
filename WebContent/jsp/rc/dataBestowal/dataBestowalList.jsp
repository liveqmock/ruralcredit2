<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
       <base href="<%=basePath%>"></base>
    <title>My JSP 'bankSetup.jsp' starting page</title>
     <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	
	</script>
	
	<script type="text/javascript" src="<%=basePath %>/scripts/dataBestowal/dataBestowalList.js">
	</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		
		<div region="center">
					<div class="easyui-tabs" id="accountTab" style="padding: 10px;">
						<div  title="操作" style="padding: 10px;">
							<table id="queryAllConditionTB">
								<tr>
									<td><font style="size: landscape;">检索条件：</font></td>
									<td align="right">分公司名称：</td>
									<td><input style="width: 150px;" name="companyName" id="companyName" class="easyui-combobox"/></td>
									<td align="right">原客户经理：</td>
									<td><input style="width: 150px;" name="old_loanOfficerName" id="old_loanOfficerName" class="easyui-combobox"/></td>
									<td align="right">业务编号：</td>
									<td><input style="width: 148px;" id="businessNumber"/></td>
									<td>
									</td>
									
									
								</tr>
								<tr>
									<td><font style="size: landscape;">赠与信息：</font></td>
									<td align="right">新客户经理：</td>
									<td><input style="width: 150px;" name="new_loanOfficerName" id="new_loanOfficerName" class="easyui-combobox"/></td>
									<td align="right">数据赠与备注：</td>
									<td colspan="4"><input id="note" size="80" maxlength="32"/></td>
								</tr>
								<tr>
									<td><font style="size: landscape;">执行操作：</font></td>
									<td align="center"><a class="easyui-linkbutton" href="javascript:queryAllCondition();">搜索申请</a></td>
									<td align="center"><a class="easyui-linkbutton" href="javascript:queryAllCondition('1');">搜索咨询</a></td>
									<td align="center"><a class="easyui-linkbutton" href="javascript:clearAllCondition();">清空</a></td>
									<td align="center"> <a class="easyui-linkbutton" id="submitDataBestowal" href="javascript:submitDataBestowal();">申请数据赠与</a></td>
									<td align="center"> <a class="easyui-linkbutton" id="dataBestowalConsultButton" href="javascript:submitDataBestowal('1');">咨询数据赠与</a></td>
								</tr>
							</table>
						</div>
					</div>
					<div id="listTab" class="easyui-tabs" style="padding: 10px;">	
						<div style="padding: 10px;" title="申请列表">
							<table id="dataBestowallist"></table>
						</div>
						<div style="padding: 10px;" title="咨询列表">
							<table id="customerConsultTable"></table>
						</div>
					</div>	
				<div id="newLoanOfferNameDialog" style="display:none">
					<div id="newLoanOfferNameDataGrid">
					</div>
				</div>
		</div>
  </body>
</html>
