<%@ page language="java" import="java.util.*,com.creditease.core.security.SpringSecurityUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../include/easyui.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<script type="text/javascript" src="<%=basePath%>scripts/acceptadvice/addNewClient.js"></script>
</head>

<body class="easyui-layout">	
	<div region="center" id="" style="background:#fafafa;width:1100px;height:400px;">
	    <form novalidate="" action="" id="applyPersonForm">
	    	<input type="hidden" id="consultPoolId" name="consultPoolId" value="${consultPoolId }"/>
	    	<input type="hidden" id="customerConsultId" name="customerConsultId" value="${customerConsultId }"/>
            <fieldset>
				<legend>新增申请人</legend>
				<table width="100%" class="tabfrom">
	    			<tbody>
					<tr height="30">
						<td align="right">请输入申请人姓名：</td>
	    				<td>
							<input id="applyname" class="easyui-validatebox" type="text" name="applyname" 
							maxlength ="16" validType="nameLength['姓名至少为2位，最长为16位，非数字',2,16]"
							data-options="required:true"style="width:100px;" />
						</td>
	    				<td align="right">请输入申请人身份证：</td>
	    				<td>
	    				 
							<input id="codenum1"   class="easyui-validatebox" type="text" name="codenum1" onselectstart="return false" onpaste="return false" data-options="required:true" style="width:200px;" />
						</td>
						<td align="right">请再次输入申请人身份证：</td>
	    				<td>
							<input id="codenum2"   class="easyui-validatebox" type="text" name="codenum2" onselectstart="return false" onpaste="return false" data-options="required:true" style="width:200px;"/>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton" iconCls="icon-add"  onclick="checkCodeNum();">添加</a>
						</td>
	    			</tr>
	    		</tbody>
				</table>
			</fieldset>
            
            <fieldset>
				<legend>申请人列表</legend>
				<table id="commonApply" style="width:auto;height:auto;" class="easyui-datagrid">
                <thead>
                    <tr>
                        <th field="appInfoName" width="80">申请人姓名</th>
                        <th field="identityCard" width="150">身份证号码</th>
                         <th field="appInfoAge" width="30">年龄</th>
                          <th field="appInfoSex" width="30">性别</th>
                        <th field="info" width="200">操作</th>
                        <th field="operate" width="100"></th>
                    </tr>
				</thead>
                <!--  <tbody>
                    <tr>
                        <td>艾米</td>
                        <td>123456789098765432</td>
                        <td>
							<a href="#" onclick="$('#add_new_client').show();$(this).hide();">人法网查询</a>
							<span style="display:none;" id="add_new_client"><a href="#" onclick="Open('编辑客户申请','add_apply.html');$('#add_client').dialog('close');">点击添加</a></span>
						</td>
						</td>
                        <td>删除</td>
                    </tr>
                </tbody>-->
            </table>
			</fieldset>
			
			<fieldset>
				<legend>人法网查询结果</legend>
				<table id="commonCourtSearch" style="width:auto;height:auto;" class="easyui-datagrid">
                <thead>
                    <tr>
                        <th field="name" width="100">被执行人姓名</th>
                        <th field="code" width="150">身份证号码/组织机构代码</th>
                        <th field="court" width="150">执行法院</th>
                        <th field="time" width="80">立案时间</th>
						<th field="caseNumber" width="150">案号</th>
						<th field="money" width="80">执行标的</th>
						<th field="state" width="80">案件状态</th>
                    </tr>
                </thead>
            </table>
			</fieldset>
    	</form>
	</div>
	</body>
	</html>
	