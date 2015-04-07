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
	
	<script type="text/javascript" src="<%=basePath %>/scripts/protocol/protocolPrefixMappingList.js">
	</script>
  </head>
  
  <body id="cc" class="easyui-layout">
		
		<div region="center">
			<div class="easyui-tabs" id="accountTab" style="padding: 10px;">
				<div  title="高级搜索" style="padding: 10px;">
					<table id="queryAllConditionTB">
						<tr>
							<td align="right">分公司名称：</td>
							<td><input style="width: 150px;" name="companyName" id="companyName"/></td>
							<td align="right">合同编号前缀：</td>
							<td>
								<input style="width: 150px;" name="prefixNumber" id="prefixNumber" />
							</td>
							<td align="right">状态：</td>
							<td><input style="width: 150px;" name="onOff" id="onOff" class="easyui-combobox"/></td>
							<td>
								<a class="easyui-linkbutton"   href="javascript:queryAllCondition();">搜索</a>
								<a class="easyui-linkbutton"    href="javascript:clearAllCondition();">清空</a>
							
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="toolbar_div">
				
				<a class="easyui-linkbutton" iconCls="icon-addOne" plain="true" href="javascript:addDialog();">新增</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" href="javascript:editDialog();">修改</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" href="javascript:deleteProtocolPrefixMapping();">删除</a>
			</div>
			<div style="padding: 10px;">
				<table id="protocolPrefixMappingList"></table>
			</div>
		</div>
		<div id="newDialog" sytle="display:none">
			<form action="new_form">
                <table style="width: 100%">
                    <tr style="width: 100%">
                        <td align="right">分公司：</td>
                        <td>
                            <input type="text" id="new_companyName" name="new_companyName" style="width: 160px;"
                                   required="true"/>
                            合同编号前缀：<input type="text" id="new_prefixNumber"
                                          name="new_prefixNumber" style="width: 100px;"
                                          class="easyui-validatebox"
                                          required="true"/>
                            状态：<input type="text" id="newonOff"
                                      name="new_onOff"
                                      class="easyui-combobox"
                                      style="width: 110px;"
                                      required="true"/>
                        </td>
                    </tr>
                    <tr style="width: 100%">
                        <td align="right">
                            户籍地址：
                        </td>
                        <td>
                            <input id="address" type="hidden">
                            </select>
                            <input type="text" id="provinceHome" name="provinceHome" class="easyui-combobox"
                                   editable="false" required="true" style="width: 150px;"/>

                            <input type="text" id="cityHome" name="cityHome" class="easyui-combobox"
                                   editable="false" required="true" style="width: 150px;"/>

                            <input type="text" id="countyHome" name="countyHome" class="easyui-combobox"
                                   editable="false" required="true" style="width: 150px;"/>
                        </td>

                    </tr>
                </table>
			</form>
		</div>
		<div id="editDialog" sytle="display:none">
			<form id="editForm" >
				<table>
					<tr>
						<td align="right">分公司名称：</td>
						<td><input type="text" id="edit_companyName" class="easyui-combobox" name="edit_companyName" style="width: 150px;"/></td>
						<td align="right">合同编号前缀：</td>
						<td><input type="text" id="edit_prefixNumber" name="edit_prefixNumber"/></td>
					</tr>
					<tr>
						<td align="right">状态：</td>
						<td><input type="text" id="editonOff" name="edit_onOff" class="easyui-combobox" style="width: 150px;"/></td>
						<td><input type="hidden" id="edit_prefix_id" name="prefix_id" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">
							选择居住地址：
						</td>
						<td>
							<input id="address" type="hidden">
								</select>
								省：<input type="text"  id="edit_provinceHome"  name="provinceHome" class="easyui-combobox" editable="false" style="width: 150px;"/>
						</td>
						<td>
							市：<input type="text"  id="edit_cityHome" name="cityHome" class="easyui-combobox" editable="false"  style="width: 150px;"/>
						</td>
						<td>
							区：<input type="text"  id="edit_countyHome" name="countyHome" class="easyui-combobox" editable="false"  style="width: 150px;"/>
						</td>

					</tr>
				</table>
			</form>
		</div>
  </body>
</html>
