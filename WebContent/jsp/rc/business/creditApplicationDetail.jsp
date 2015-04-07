<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    	<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>  
		<script type="text/javascript">
			var serverName = "<%=path%>";
		</script>
		<script type="text/javascript"
				 src="<%=basePath%>scripts/business/creditApplicationDetail.js">
		</script>
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/datagrid-detailview.js"></script>
  </head>
  
  <body class="easyui-layout">
  
  	<div region="center" style="padding:10px">
    <div>
    	<input id="creditapplicationId" type="hidden" value="${creditApplicationId }"/>
	    <a class="easyui-linkbutton" iconCls="icon-back" 
			    	href="jsp/rc/business/creditApplicationList.jsp">信贷列表</a>
		</div>
			<div id="yewu" ></div>
			<br/>
		  	<div id="shenpi" class="easyui-panel">
		  		
		  	</div>
		  	<br/>
		  	<div  id="allPeopel" class="easyui-tabs">
		  		<div title="借款人">
				    <table id="borrower" class="easyui-datagrid">
				    </table>
			    </div>
			   	<div title="担保人">
				    <table id="danbao" class="easyui-datagrid">
				    </table>
			    </div>
			</div>
		    <br/>
		    <div id="allDatas" class="easyui-tabs">
		    	<div title="还款计划">
		    		<div id="returnPlanList" class="easyui-datagrid"></div>
		    	</div>
		    	<div title="入户资料" style="padding:10px">
		    		
		    		<div id="houseDatas" class="easyui-tabs">
		    			<div title="入户一" id="ruhuyi"
		    				href="<%=basePath%>jsp/rc/business/householdSurveyFirstShow.jsp" 
		    				style="padding:10px">
		    			</div>
		    			<div title="入户二"></div>
		    			<div title="调查人"></div>
		    			
		    		</div>
		    	</div>
		    	<div title="业务跟踪">
		    		<div id="businesStrack"></div>
		    	</div>
		    	<div title="附 &nbsp;&nbsp;件"></div>
		    </div>
		    <div><a class="easyui-linkbutton" iconCls="icon-back" 
		    	href="jsp/rc/business/creditApplicationList.jsp">信贷列表</a></div>
	</div>
  </body>
</html>
