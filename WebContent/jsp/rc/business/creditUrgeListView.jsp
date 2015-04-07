<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="../include/easyui.jsp"></jsp:include>
<script type="text/javascript">

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <!-- 新增催收   luohongjie -->
		<div id="UrgeDialog2" style="width: 800px;height:400px;">
		  <form id="subUrgeForm" action="">
    		   <table align="left" cellpadding="5" style="width:100%;" cellspacing="0" border="1" id="table">
	    		   <tr>
					  <td colspan="6" style="font-size:14px;">&nbsp;催收概括</td>
					</tr>
					<tr>
					  <td>&nbsp;&nbsp;&nbsp;催收概括：</td>
					  <td><input style="width:135px"  name="URGESUMMARIZE" value="" type="text" readonly="readonly"/></td>
					  <td>催收方式：</td>
					  <td><input name="URGEWAY" type="text" style="width:135px" readonly="readonly"/></td>
					   <td>催收状态：</td>
					  <td><input   name="URGESTATE" type="text" style="width:135px" readonly="readonly"/></td>
					</tr>
					<tr>
					  <td>&nbsp;&nbsp;&nbsp;收回金额：</td>
					  <!-- 必填，大于等于0 -->
					  <td><input type="text"   style="width:135px" readonly="readonly" /></td>
					  <td>金额来源：</td>
					  <!-- 如收回金额不为0，则必填，下拉：同与借款人关系 -->
					  <td><input style="width:135px" name="MONEYSOURCE" type="text" readonly="readonly"/></td>
					  <td>催收时长：</td>
					  <td><input type="text" size="2" readonly="readonly"/>时<input type="text" size="2" readonly="readonly"/>分</td>
					</tr>
					<tr>
					 
					  <td>&nbsp;&nbsp;&nbsp;催收日期：</td>
					  <!-- 格式：YYYY-MM-DD -->
					  <td><input type="text" style="width:135px" readonly="readonly"/></td>
					  <td>违约开始日期：</td>
					  <td><input type="text" style="width:135px" readonly="readonly"/></td>
					  <td>催收沟通评价：</td>
					  <td><inputname="URGEREMARK" type="text" style="width:135px" readonly="readonly"/></td>
					</tr>
					<tr>
					</tr>
					<tr>
					  <td>&nbsp;&nbsp;&nbsp;重大风险预警：</td>
					  <td><input name="BIGWARNING" type="text" style="width:135px" readonly="readonly"/></td>
					  <td >&nbsp;&nbsp;&nbsp;催收描述：</td>
					  <td colspan="3"><input type="text" style="width:135px" readonly="readonly"/></td>
					</tr>
					<tr>
					  <td colspan="6" style="font-size:14px;" readonly="readonly">&nbsp;承诺情况</td>
					</tr>
					<tr>
					  <td>上次承诺还款金额(元):</td>
					  <td><input type="text" style="width:135px" readonly="readonly"/></td>
					  <td>上次承诺还款时间</td>
					  <td><input type="text" style="width:135px" readonly="readonly"/></td>
					  <td>是否履诺：</td>
					  <td ><input name="YNPROMISE" type="text" style="width:135px" readonly="readonly"/></td>
					</tr>
					<tr>
					  <td>本次承诺还款金额(元):</td>
					  <td><input type="text"  style="width:135px" readonly="readonly"/></td>
					  <td>本次承诺还款时间：</td>
					  <td colspan="3"><input type="text" style="width:135px" readonly="readonly"/></td>
					</tr>
					<tr>
					  <td colspan="6" style="font-size:14px;">&nbsp;催收联系人</td>
					</tr>
					<tr>
					  <td >催收联系人姓名：</td>
					  <td><input type="text"   style="width:135px"/></td>
					  <td>与借款人关系：</td>
					  <td><input name="RELATION"  type="text" style="width:135px" /></td>
					  <td>联系电话：</td>
					  <td><input type="text" style="width:135px"/></td>
					</tr>
				</table>
    		   </form>
		</div>
</body>
</html>