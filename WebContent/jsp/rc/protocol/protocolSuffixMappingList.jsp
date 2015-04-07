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
	
	<script type="text/javascript" src="<%=basePath %>/scripts/protocol/protocolSuffixMappingList.js">
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
							<td align="right">年度：</td>
							<td>
								<select  id="simpleYear" name="simpleYear" class="easyui-combobox" style="width:150px" editable="false">
                                    <option value=""></option>
                                    <option value="13">2013</option>
									<option value="14">2014</option>
									<option value="15">2015</option>
								</select>
							</td>
							<td align="right">年度起始编号：</td>
							<td>
								 <input style="width: 150px;" name="suffixNumber" id="suffixNumber" />
								
							</td>
							<td>
								 <a class="easyui-linkbutton"  href="javascript:queryAllCondition();">搜索</a>
			   					 <a class="easyui-linkbutton"   href="javascript:clearAllCondition();">清空</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="toolbar_div">
				<a class="easyui-linkbutton" iconCls="icon-addOne" plain="true" href="javascript:addDialog();">新增</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" href="javascript:editDialog();">修改</a>
			</div>
			<div style="padding: 10px;">
				<table id="protocolSuffixMappingList"></table>
			</div>
		</div>
		<div id="newDialog" style="dispaly:none">
			<form id="new_form">
				<table>
					<tr>
						<td align="right">分公司名称：</td>
						<td><input type="text" id="new_branchofficeName" name="new_branchofficeName" required="true" style="width: 150px;"/></td>
						<td align="right">年度起始编号：</td>
						<td><input type="text" id="new_suffixNumber" class="easyui-validatebox" name="new_suffixNumber" required="true" validType="numberZeroOver" maxlength="4" /></td>
					</tr>
					<tr>
						<td align="right">年度：</td>
						<td> 	
							<select  id="newsimpleYear" name="new_simpleYear" class="easyui-combobox" required="true" style="width: 150px;" 	>
                                <option value="13">2013</option>
								<option value="14">2014</option>
								<option value="15">2015</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="editDialog" style="dispaly:none">
			<form id="edit_form">
				<table>
					<tr>
						<td align="right">分公司名称：</td>
						<td><input type="text" id="edit_branchofficeName" class="easyui-combobox" name="edit_branchofficeName" required="true" style="width: 150px;"/></td>
						<td align="right">年度起始编号：</td>
						<td><input type="text" id="edit_suffixNumber" class="easyui-validatebox" name="edit_suffixNumber" required="true" validType="numberZeroOver" maxlength="4"/></td>
					</tr>
					<tr>
						<td align="right">年度：</td>
						<td> 	
							<select  id="editsimpleYear" name="edit_simpleYear" class="easyui-combobox" required="true" style="width: 150px;" 	>
							</select>
							<td><input type="hidden" id="edit_suffixId" name="suffixId" /></td>
							<td><input type="hidden" id="old_suffixNumber" name="suffixId" /></td>
						</td>
					</tr>
				</table>
			</form>
		</div>
  </body>
</html>
