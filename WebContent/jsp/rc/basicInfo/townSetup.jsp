<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
	
    <title>My JSP 'manageTown.jsp' starting page</title>
    <jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
	<script type="text/javascript">
	var serverName="<%=path%>";
	 $(document).ready(function() {
			//$("td").attr('style', 'font-size: 12; color: navy;font: bolder;');
		});
	 
	 $(function(){
		 var editrow = undefined;
			//easyui-validatebox拓展验证
				$.extend($.fn.validatebox.defaults.rules, {
					//验证数字
					number : {
						validator : function(value, param) {
							if (value) {
								return /^[1-9][0-9]*(\.[0-9]+)?$/.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入非0开头的数字.'
					},
					date : {
						validator : function(value, param) {
							if (value) {
								return /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/
										.test(value);
							} else {
								return true;
							}
						},
						message : '请选择日期.'
					},
					wordAndNumber : {
						validator : function(value, param) {
							if (value) {
								return /^(\w)+$/.test(value);
							} else {
								return true;
							}
						},
						message : '请输入数字、字母、“_”的组合！'
					}
				});
			 
		 
		 
		var townName = $("#townName").combobox({
			
			onChange:function(){
				if($("#prefecture").combobox('getValue') == null){
					$.messager.show({
						title:'消息',
						msg:'请选择省 、市、 区！'
					});
					return false;
				}else{
					var val1 = $('#townName').combobox('getValue');
					$("#townName1").val(val1);
					var val = $('#townName').combobox('getData');
					for(var i = 0 ; i < val.length;i++){
						if(val[i].townshipinfName == val1){
						$("#townshipinfoid1").attr('value',val[i].townshipinfoid);
						$("#townid1").attr('value',val[i].townshipinfoid);
						$("#parentId1").val(val[i].key);
						break;
						} 
						
					}
					
					if(val1 == undefined|val1 == null|val1 == ""){
						$("#townshipinfoid1").attr('value',null);
						$("#townid1").attr('value',null);
						$("#parentId1").val(null);
					}
				}
			}
		});
		
		
		var province = $("#province").combobox({
			//required : true,
			url : serverName+'/NSC/list.do',
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			value : '',
			onSelect:function(value){
				$("#city").combobox({
					url : serverName+'/NSC/listCity.do?data='+value.cityCode,
					textField : 'cityName',
					valueField : 'cityCode',
					mode : 'remote',
					panelHeight : 'auto',
					delay : 500,
					value : '',
					onSelect:function(value){
						
						$("#prefecture").combobox({
							url : serverName+'/NSC/listCity.do?data='+value.cityCode,
							textField : 'cityName',
							valueField : 'cityCode',
							mode : 'remote',
							panelHeight : 'auto',
							delay : 500,
							value : '',
							onSelect:function(value){
								
								$("#townName").combobox({
									url : serverName+'/town/list.do?parentId='+value.cityCode,
									textField : 'townshipinfName',
									valueField : 'townshipinfName',
									mode : 'remote',
									panelHeight : 'auto',
									delay : 500,
									value : ''
								});
							}
						});
					}
				});
			}
		});
	 });
	 
	 function submit(){
		 if ($("#townInfo").form('validate')) {
				ajaxSubmit('town/addTown.do', $("#townInfo").serializeObject(), saveRetFunc);
			} else {
				$.message.alert("消息","请将数据填写完整！");
			}
	 };
	 
	 function saveRetFunc(result){
		 if (result.success) {
			 	$("#townInfo").form("clear");
				$.messager.show({
					msg : '数据保存成功！',
					title : '消息'
				});
			} else {
				$.message.alert('错误', result.message);
			}
	 }
	 
	 function showONline(){
		 $("#townON").attr('value',$("#online").val());
	 }
	 
	//上传文件事件
	 function uploadFile(){
		 var uploadFile = $("#filebutton").value;
		 if(uploadFile  == null || uploadFile  == ""){
			 message.show("请选择要上传的文件!");
			 return false;
		 }else{
			 
		 }
	 }
	function reset(){
		$("#townInfo").form("clear");
	}
    </script>
  </head>
  
  <body id="cc" class="easyui-layout">
  	<div region="center"   style="padding: 0px;border:0px;">
  		<form  id="townInfo" >
	  		<div id="p1" class="easyui-panel" style="width: 100% px; height: 100px; padding: 10px;"
						title="省市区信息设置" align="center">
	   			<table align="center">
	   				<tr align="center">
		   				<td align="right"> 省 :</td>
		   				<td align="left"><select id="province" class="easyui-combobox"  required="true" editable="false"></select> </td>
		   				<td align="right"> 市:</td>
		   				<td align="left"><select id="city" class="easyui-combobox" required="true"  editable="false"></select></td>
		   				<td align="right"> 区:</td>
		   				<td align="left"><select id="prefecture" name="listTown[0].parentId" required="true"  class="easyui-combobox"  editable="false"></select></td>
	   				</tr>
	   			</table>
	  		</div>
		   	<div id="p2" class="easyui-panel" style="width: 100% px; height: 200px; padding: 10px;" 
		   			 align="center" title="乡镇基础信息设置">
		   		<table align="center">
		   		<tr align="center">
		   			<td><input id="townshipinfoid1" name="listTown[1].parentId" style="visibility: hidden;"/>
		   			<td><input id="townid1" name="listTown[0].townshipinfoid" style="visibility: hidden;"/></td> 
		   			<td><input name="listTown[0].townshipinfName" id="townName1" style="visibility: hidden;"/></td>
		   			<td><input type="text" id="" name="listTown[1].townshipinfoid" style="visibility: hidden;"/><td>
		   			<td><input id="townON" name="listTown[0].onLine" style="visibility: hidden;"></td>
		   		</tr>
		   			<tr>
		   				<td align="right">镇: </td>
		   				<td align="left">
		   				<input  id="townName" class="easyui-comboBox" onchange="hiddenNumber();" required="true"/></td>
		   				<td align="right">村:</td>
		   				<td align="left"><input  name="listTown[1].townshipinfName" class="easyui-validatebox" required="true"/>
		   								</td>
		   			</tr>
		   			<tr>
		   				<td align="right">镇编码:</td>
		   				<td align="left"><input name="listTown[0].key" id="parentId1"  class="easyui-validatebox"
		   										 required="true" validType="wordAndNumber"/></td>
		   				<td align="right">村编码:</td>
		   				<td align="left"><input  name="listTown[1].key" class="easyui-validatebox" 
		   										 required="true" validType="wordAndNumber"/></td>
		   			</tr>
		   			<tr>
		   				<td align="right">是否上线:</td>
		   				<td align="left"><select id="online" name="listTown[1].onLine" onchange="showONline();" editable="false">
		   						<option value=""> 请选择</option>
								<option value="1">上线</option>
								<option value="0">离线</option>
		   				</select> 
		   				</td>
		   		</table>
		   		<table align="center">
		   			</tr>
		   			<tr align="right">
		   			<td  align="right"><a class="easyui-linkbutton" onclick="submit();"> 确定</a></td>
		   			<td  align="right"><a class="easyui-linkbutton" onclick="reset();"> 重置</a></td>
		   			</tr>
		   		</table>
		   </div>
		   <div id="p3" class="easyui-panel" style="width: 100% px; padding: 10px;" 
		   			 align="center" title="乡镇基础信息批量上传设置">
		   		<table align="center">
		   			<tr>
		   				<td width="100px">文件上传:</td>
		   				<td  align="left"><input type="file" id="filebutton" name="uploadFile" value=""></td>
		   				<td width="100px" align="right"><a class="easyui-linkbutton" onclick="uploadFile();">确定</a></td>
		   				<td width="200px"> <a class="easyui-linkbutton"> 取消</a></td>
		   			</tr>
		   		</table>
		   </div>
	   </form>
	  </div> 
  </body>
</html>
