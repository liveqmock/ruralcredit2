<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	
	<script type="text/javascript" src="<%=basePath %>/scripts/protocol/protocolMappingList.js">
	</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		
		<div region="center">
			<div class="easyui-tabs" id="accountTab" style="padding: 10px;">
				<div  title="高级搜索" style="padding: 10px;">
					<table id="queryAllConditionTB">
						<tr>
							<td align="right">分公司名称：</td>
							<td><input style="width: 150px;" name="branchofficeName" id="branchofficeName" class="easyui-combobox"/></td>
							<td align="right">合同编号：</td>
							<td>
								<input style="width: 150px;" name="protocolNumber" id="protocolNumber" />
							</td>
							<td align="right">业务编号：</td>
							<td><input style="width: 150px;" name="businessNumber" id="businessNumber" /></td>
                            <td>产品类型：</td>
                            <td><input id="productTypeName" name="productTypeName" style="width: 150px;"/>
                            </td>
							<td>
								<a class="easyui-linkbutton"   href="javascript:queryAllCondition();">搜索</a>
								<a class="easyui-linkbutton"    href="javascript:clearAllCondition();">清空</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div style="padding: 10px;">
				<table id="protocolMappingList"></table>
			</div>
		</div>
  </body>
</html>
