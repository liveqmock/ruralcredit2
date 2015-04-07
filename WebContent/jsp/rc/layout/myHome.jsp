<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.creditease.rc.domain.SystemAnnouncement"%>
<%@ page import="com.creditease.rc.util.PropertiesUtil"%>
<%@ page import="com.creditease.rc.util.DESPlus"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
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
	List<SystemAnnouncement> sAnnouncements = (List<SystemAnnouncement>) request.getAttribute("sAnnouncements");
	int sASize = sAnnouncements.size();
	String cmIp = properties.getProperty("cm.hostip");
	DESPlus desPlus = new DESPlus();
	String DESNow = desPlus.encrypt(new Date().getTime() + "");
	String DESIp = desPlus.encrypt(cmIp);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>My JSP 'myHome.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<jsp:include page="../include/easyui-portal.jsp"></jsp:include>
		<style type="text/css">
.toPanel {
	padding-left: 5px;
}
</style>
		<script type="text/javascript">
	var serverName="<%=path%>";
	
	var cmUrl = "<%=cmUrl%>";
	var DESNow =  "<%=DESNow%>";
	var DESIp =  "<%=DESIp%>";
	var sAnnoucementSize = <%=sASize%>;
	
	
	function changeHeight(){
		if(sAnnoucementSize<10){
			var height = sAnnoucementSize*8;
			document.getElementById('demo').style.height = height+"px";
		}
	}
	//查看更多公告信息
	function showMoreSystemAnnouncement(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName + "/jsp/rc/system/systemAnnouncementList.jsp",
			title : "公告"
		});
	}
	//查看销售计划
	function showSalesPlanning(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName + "/jsp/rc/sales/salesPlanning.jsp",
			title : "销售计划"
		});
	}
	//查看滚动预测
	function showRollingForecast(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName + "/salesController/rollingForecastJSP.do",
			title : "滚动预测"
		});
	}
	//查看预测对比
	function showComparison(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName + "/salesController/showComparisonJSP.do",
			title : "预测对比"
		});
	}
	//查看公告
	function toViewSystemAnnouncement(id){
		var dd1 = $("#systemAnnouncementDailog").dialog({
			title : "查看",
			modal : true,
			closed : true,
			inline : false,
			width: 1000,
			height: 470,
			border : false,
			draggable:true,
			dosize : true,
			buttons : [ {
				text : "关闭",
				iconCls : "icon-cancel",
				handler : function() {
					$("#systemAnnouncementDailog").dialog('close');
				}
			} ],
			href : serverName+"/jsp/rc/system/systemAnnouncementView.jsp",
			onLoad:function(){
				var paraValue = id;
				ajaxSubmit(serverName+"/systemInfo/viewSystemAnnouncement.do",{param:paraValue},function(result){
					$("#saViewForm").form("load",result);
					var attachmentId = $("#saViewAttachmentId").val();
					var imageNum = $("#saViewImageNum").val();
					if(imageNum>0){
						var href = cmUrl+"/operation/transferParameter.action?clientId="+attachmentId+"&systemID=00003&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529&signTime="+DESNow+"&signIp="+DESIp+"";
						$('#saViewOpenCM')[0].src = href;
					}
				});
			}
		});
		dd1.dialog('open');
	}
	
	
	var centerj = window.parent;
	function toAduitRemindFX(){
		centerj.addTabFun({
			src : "business/creditApplicationList.jsp?role=25",
			title : "信贷申请"
		});
	}
	function toAduitRemindBS(){
		centerj.addTabFun({
			src : "business/creditApplicationList.jsp?role=24",
			title : "信贷申请"
		});
	}
	function toAmountConfirm(){
		centerj.addTabFun({
			src : "business/creditApplicationList.jsp?auditStatus=04",
			title : "信贷申请"
		});
	}
	function toInvestigation(){
		centerj.addTabFun({
			src : "business/creditApplicationList.jsp?role=addFX&fxB=2728",
			title : "信贷申请"
		});
	}
	/*function toLoanRegister(){
		centerj.addTabFun({
			src : "business/loanList.jsp?role=loanRegister",
			title : "放款列表"
		});
	}*/
	function  toUploadContract(){
		centerj.addTabFun({
			src : serverName + "/contractAndLoanController/reutrnNewLoanListSession.do",
			title : "放款列表"
		});
	}
	function toLoanConfirmBack(){
		centerj.addTabFun({
			src : "business/loanList.jsp?role=loanConfirmBack",
			title : "放款列表"
		});
	}
	function toFinanceReceiveWill(){
		centerj.addTabFun({
			src : "receivables/receiveList.jsp?role=rw",
			title : "财务回款列表"
		});
	}
	function toFinanceReceiveFail(){
		centerj.addTabFun({
			src : "receivables/receiveList.jsp?role=rf",
			title : "财务回款列表"
		});
	}
	function toFinancePayment(){
		centerj.addTabFun({
			src : "payment/financeList.jsp?role=p",
			title : "财务放款列表"
		});
	}
	
	function toReceiptRegister(param){
		var ids=$("#receiptRegister"+param).val();
		centerj.addTabFun({
			src : "receivables/receivablesList.jsp?ids="+ids,
			title : "收款列表"
		});
	}
	function toUrgeRemind(urgeNum){
		centerj.addTabFun({
			src:serverName + "/urgeController/returnCreditUrgeListJSP.do?urgeNum="+urgeNum,
			title:"催收列表"
		});
	}
	//点击催收提醒显示催收详细信息笔数
	function toUrge(){
		document.getElementById("urgeId").style.display="block";
		ajaxSubmit(serverName+"/indexremindcontroller/returnMyHomeJSP.do",function(r){
			$("#overDay").val(r.overDay);
			$("#toDay0").val(r.toDay);
			$("#threeDay3").val(r.threeDay);
			$("#sevenDay7").val(r.sevenDay);
		});
	}
	function toVisteRemind(param){
		var ids=$("#viste"+param).val();
		centerj.addTabFun({
			src:"receivables/newReceivablesList.jsp?ids="+ids,
			title:"收款列表"
		});
	}
	//点击回访提醒显示催收详细信息笔数
	function toViste(){
		//console.info(document.getElementById("visteId").style.border);
		document.getElementById("visteId").style.display="block";
		//document.getElementById("visteId").style.border="1";
		
		ajaxSubmit(serverName+"/indexremindcontroller/returnVisteMyHomeJSP.do",function(r){
			$("#viste1").val(r.id1s);
			$("#viste3").val(r.id3s);
			$("#viste7").val(r.id7s);
			$("#viste8").val(r.idDontCallBackIdStr);
			$("#toDayCustomerList").val(r.toDayCustomerList);
			$("#threeDayCustomerList").val(r.threeDayCustomerList);
			$("#sevenDayCustomerList").val(r.sevenDayCustomerList);
			$("#forePeriodDontCallBackList").val(r.forePeriodDontCallBackList);
		});
	}
	
	function decodeValue(param){
		if('countInfo'==param){
			return '统计信息';
		}else if('countInfoNew'==param){
			return '新统计信息';
		}else if('auditRemindBS'==param){
			return '审批提醒';
		}else if('auditRemindFX'==param){
			return '审批提醒';
		}else if('investigation'==param){
			return '风险单提醒';
		}else if('upLoadContract'==param){
			return '上传合同提醒';
		}else if('urgeRemind'==param){
			return '催收提醒';
		}else if('visteRemind'==param){
			return'回访提醒';
		}else if('receiptRegister'==param){
			return '收款登记提醒';
		}else if('financePayment'==param){
			return '财务提醒';
		}else if("announcement"==param){
			return "系统公告";
		}else if("departmentCountInfo"==param){
			return "营业部年度业绩统计";
		}else if("overdueInfo"==param){
			return "逾期提醒";
		}else if("consult"==param){
			return "咨询统计";
		}else if("salesInfo"==param){
			return "销售计划";
		}
	}
	function showLoanPortfolioList(){
		centerj.addTabFun({
			src : "payment/LoanPortfolioList.jsp",
			title : "放款详情"
		});
	}
	function showOverdueInfoList(){
		var centerj = window.parent;
		centerj.addTabFun({
			src : serverName+"/receivablesList/returnOverdueListJSP.do?type=formIndexByOverdue",
			title : "逾期列表"
		});
	}
	function refresh()   
	{   
		 url = location.href; //把当前页面的地址赋给变量 url   
		    var times = url.split("?"); //分切变量 url 分隔符号为 "?"    
		    if(times[1] != 1){ //如果?后的值不等于1表示没有刷新   
		        url += "?1"; //把变量 url 的值加入 ?1  
		        self.location.replace(url); //刷新页面     
		    }     
	}   
	$(function(){
		//解决火狐浏览器下panel加权限的不能完整显示的问题
		if($.browser.mozilla){
			refresh();
		}
		$("#shouye").portal({
			border:true,
			fit:true
		});
		$(".toPanel").each(function(i){
			var id=this.id;
			var name=decodeValue(id);
			var p=$("#"+id).panel({
				title:name,
				height:200,
				width:300
			});
			$("#shouye").portal('add', {  
			    panel: p,  
			    columnIndex: i%3
			});
		});
		
		/*$("#departmentCountInfoDataGrid").datagrid({
			url : serverName + "/indexremindcontroller/selectDepartmentCountInfo.do",
			pagination : false,
			columns : [[ {
				title : '月份',
				field : 'monthName',
				width : '100',
				align:'right'
			}, {
				title : '放款业务量',
				field : 'paymentBusinessCount',
				width : '100',
				align:'right'
			}, {
				title : '放款金额',
				field : 'paymentBusinessSum',
				width : '100',
				align:'right'
			}]]
		});*/
	});	
		
</script>
	</head>
	<body class="easyui-layout">
		<div region="center" style="overflow: hidden;" border="false">
			<div id="shouye" style="width: 800px; height: 500px;">
				<div style="width: 40%; height: 800px;">
				</div>
				<div style="width: 30%; height: 800px;">
				</div>
				<div style="width: 30%; height: 800px;">
				</div>
			</div>

			<div id="announcement" class="toPanel" tools="#announcementTools">
				<!-- 公告滚动显示 -->
				<%
					//超过一定条目的数据才滚动
					if (sASize > 4) {
				%>
				<div id="demo" style="overflow: hidden; height: 100%; width: 98%;">
					<div id="demo1">
						<%
							for (int i = 0; i < sASize; i++) {
									SystemAnnouncement s = sAnnouncements.get(i);
									String id = s.getSystemAnnouncementId().toString();
									String title = s.getTitle();
									String num = s.getNumber();
									String titleFlag = s.getTitleFlag();
									String createTime = s.getCreateTime().toLocaleString();
									String content = s.getContent();
									if (title.length() > 18) {
										title = title.substring(0, 18);
									}
									if (content.length() > 18) {
										content = content.substring(0, 18) + "...";
									}
									if ("1".equals(titleFlag)) {
						%>
						<div style="margin-top: 10px;">
							<%--<a href="javascript:toViewSystemAnnouncement(<%=id %>)" style="text-decoration: none;"> <span style="display: -moz-inline-box; display: inline-block; width: 60px; font-weight: bold; color: #9932cc;"><%= title %></span> &nbsp;|&nbsp; <span style="display: -moz-inline-box; display: inline-block; width: 250px;"><%= content %></span> &nbsp;|&nbsp; <%= createTime %> </a>
							--%>
							<a href="javascript:toViewSystemAnnouncement(<%=id%>)" style="text-decoration: none;"> （<%=num%>）&nbsp;&nbsp; <span style="display: -moz-inline-box; display: inline-block; width: 200px; font-weight: bold; color: red;"><%=title%></span> &nbsp;&nbsp;<span style="display: -moz-inline-box; display: inline-block; width: 300px; font-weight: bold; color: #696969;"><%=content%></span> <font style="color: #696969;"><%=createTime%></font> </a>
						</div>
						<%
							} else {
						%>
						<div style="margin-top: 10px;">
							<%--<a href="javascript:toViewSystemAnnouncement(<%=id %>)" style="text-decoration: none;"> <span style="display: -moz-inline-box; display: inline-block; width: 60px; font-weight: bold; color: #9932cc;"><%= title %></span> &nbsp;|&nbsp; <span style="display: -moz-inline-box; display: inline-block; width: 250px;"><%= content %></span> &nbsp;|&nbsp; <%= createTime %> </a>
							--%>
							<a href="javascript:toViewSystemAnnouncement(<%=id%>)" style="text-decoration: none;"> （<%=num%>）&nbsp;&nbsp; <span style="display: -moz-inline-box; display: inline-block; width: 200px; font-weight: bold; color: black;"><%=title%></span>&nbsp;&nbsp;<span style="display: -moz-inline-box; display: inline-block; width: 300px; font-weight: bold; color: #696969;"><%=content%></span><font style="color: #696969;"><%=createTime%></font> </a>
						</div>
						<%
							}
								}
						%>

					</div>
					<div id=demo2></div>
				</div>
				<script>
	var speed = 80;
	var demo = document.getElementById("demo");
	var demo2 = document.getElementById("demo2");
	var demo1 = document.getElementById("demo1");
	demo2.innerHTML = demo1.innerHTML;
	function Marquee(){
		if(demo2.offsetTop-demo.scrollTop<=0){
			demo.scrollTop-=demo1.offsetHeight;
		}else{
			demo.scrollTop++;
		}
	}
	var MyMar = setInterval(Marquee,speed);
	demo.onmouseover=function() {clearInterval(MyMar);};
	demo.onmouseout=function() {MyMar=setInterval(Marquee,speed);};
</script>
				<%
					} else {
				%>
				<div style="overflow: hidden; height: 100%; width: 98%;">
					<%
						for (int i = 0; i < sASize; i++) {
								SystemAnnouncement s = sAnnouncements.get(i);
								String id = s.getSystemAnnouncementId().toString();
								String title = s.getTitle();
								String createTime = s.getCreateTime().toLocaleString();
								String content = s.getContent();
								if (title.length() > 5) {
									title = title.substring(0, 5);
								}
								if (content.length() > 18) {
									content = content.substring(0, 18) + "...";
								}
					%>
					<div style="margin-top: 10px;">
						<a href="javascript:toViewSystemAnnouncement(<%=id%>)" style="text-decoration: none;"> <span style="display: -moz-inline-box; display: inline-block; width: 200px; font-weight: bold; color: black;"><%=title%></span>&nbsp;&nbsp;<span style="display: -moz-inline-box; display: inline-block; width: 300px; font-weight: bold; color: #696969;"><%=content%></span><font style="color: #696969;"><%=createTime%></font> </a>
					</div>
					<%
						}
					%>
				</div>
				<%
					}
				%>
				<!-- 公告滚动显示 -->
			</div>
			<sec:authorize ifAnyGranted="${control.loanList_confirm}">
				<div id="consult" class="toPanel">
					<table>
						<tr>
							<td>
								客户咨询总数：
							</td>
							<td>
								${conSultCount}&nbsp;次
							</td>
						</tr>
						<tr>
							<td>
								转为申请的总数：
							</td>
							<td>
								${indexRemind.consultToApply.remindCount}&nbsp;笔
							</td>
						</tr>
						<tr>
							<td>
								转化率：
							</td>
							<td>
								${indexRemind.consultToApply.remindSum }&nbsp;%
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>

					</table>
				</div>

				<!-- 逾期提醒 -->
				<%--<div id="overdueInfo" class="toPanel" tools="#overdueInfoTools">
					<table>
						<tr>
							<td>
								逾期笔数总计：
							</td>
							<td>
								${localOverdueInfoResponse.sOverdueCount }&nbsp;笔
							</td>
						</tr>
						<tr>
							<td>
								逾期期数总计：
							</td>
							<td>
								${localOverdueInfoResponse.aOverdueCountTotle}&nbsp;期
							</td>
						</tr>
					</table>
				</div>
			
			--%>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.loanList_approve}">
				<div id="auditRemindBS" class="toPanel">
					<a href="javascript:void(0)" class="linkContent" onclick="toAduitRemindBS()">需做审批提醒：${indexRemind.auditRemindBS.auditCount }&nbsp;笔</a>
				</div>
				<div id="departmentCountInfo" class="toPanel">
					<table class="easyui-datagrid" style="width: 310px;" >
						<thead>
							<tr>
								<th width="100" field="indexRemind.departmentCountInfoList.monthName">
									月份
								</th>
								<th width="100" field="indexRemind.departmentCountInfoList.paymentBusinessSum">
									金额
								</th>
								<th width="100" field="indexRemind.departmentCountInfoList.paymentBusinessCount">
									笔数
								</th>
							</tr>
						</thead>
						<tbody align="right">
							<c:forEach items="${indexRemind.departmentCountInfoList}" var="i">
								<tr align="right">
									<td align="right">
										${i.monthName}
									</td>
									<td align="right">
										${i.paymentBusinessSum}
									</td>
									<td align="right">
										${i.paymentBusinessCount}笔
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.myHome_countInfo}">
				<div id="countInfo" class="toPanel" tools="#countInfoTools">
					<table>
						<tr>
							<td>
								本月放款金额统计：
							</td>
							<td>
								${indexRemind.currentLoanSumAmount.remindSum }&nbsp;元
							</td>
						</tr>
						<tr>
							<td>
								本月放款笔数统计：
							</td>
							<td>
								${indexRemind.currentLoanSumAmount.remindCount}&nbsp;笔
							</td>
						</tr>
						<tr>
							<td>
								放款金额统计：
							</td>
							<td>
								${indexRemind.loanSumAmount.remindSum }&nbsp;元
							</td>
						</tr>
						<tr>
							<td>
								放款笔数统计：
							</td>
							<td>
								${indexRemind.loanSumAmount.remindCount }&nbsp;笔
							</td>
						</tr>

					</table>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.myHome_countInfoNew}">
				<div id="countInfoNew" class="toPanel" tools="#countInfoNewTools">
					<table>
						<tr>
							<td>
								本月放款金额统计：
							</td>
							<td>
								${currentAmount}&nbsp;元
							</td>
						</tr>
						<tr>
							<td>
								本月放款笔数统计：
							</td>
							<td>
								${currentCount}&nbsp;笔
							</td>
						</tr>
						<tr>
							<td>
								放款金额统计：
							</td>
							<td>
								${totalAmount}&nbsp;元
							</td>
						</tr>
						<tr>
							<td>
								放款笔数统计：
							</td>
							<td>
								${totalCount }&nbsp;笔
							</td>
						</tr>

					</table>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
				<div id="auditRemindFX" class="toPanel">
					<a href="javascript:void(0)" class="linkContent" onclick="toAduitRemindFX()">需做审批提醒：${indexRemind.auditRemindFX.auditCount }&nbsp;笔</a>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.loanList_fxapprove}">
				<div id="investigation" class="toPanel">
					<a href="javascript:void(0)" class="linkContent" onclick="toInvestigation()">需新增风险单:${indexRemind.investigation.addFxCount }&nbsp;</a>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="${control.loanList_confirm}">
				<div id="upLoadContract" class="toPanel">
					<a href="javascript:void(0)" class="linkContent" onclick="toUploadContract()">需做上传合同：${indexRemind.loanRegister.auditCount }&nbsp;笔</a>
					<br />
					<%--<a href="javascript:void(0)" class="linkContent" onclick="toLoanConfirmBack()">需做放款确认退回：${indexRemind.loanConfirmBack.auditCount }&nbsp;笔</a>
				--%>
				</div>
				<%--<div id="receiptRegister" class="toPanel">
					<a href="javascript:void(0)" class="linkContent" onclick="toReceiptRegister(1)">今天：${indexRemind.receiptRegister1.auditCount }&nbsp;笔</a>
					<br />
					<input type="hidden" id="receiptRegister1" value="${indexRemind.receiptRegister1.creditapplicationIds }" />
					<a href="javascript:void(0)" class="linkContent" onclick="toReceiptRegister(3)">3个工作日内：${indexRemind.receiptRegister3.auditCount }&nbsp;笔</a>
					<br />
					<input type="hidden" id="receiptRegister3" value="${indexRemind.receiptRegister3.creditapplicationIds }" />
					<a href="javascript:void(0)" class="linkContent" onclick="toReceiptRegister(7)">7个工作日内：${indexRemind.receiptRegister7.auditCount }&nbsp;笔</a>
					<br />
					<input type="hidden" id="receiptRegister7" value="${indexRemind.receiptRegister7.creditapplicationIds }" />
				</div>
			--%>
			</sec:authorize>
			
			<!-- 催收提醒 -->
			 <sec:authorize ifAnyGranted="${control.loanList_urgePanel}">
			<div id="urgeRemind" class="toPanel" tools="#urgeTools">
				<div id="urgeId" style="display:none">
				           催收业务提醒：
				    <br/>
					<a href="javascript:void(0)" class="linkContent" onclick="showOverdueInfoList()">今天：<input id="overDay" style="border:0"  size="2"/>笔</a>
					<br />
					<br/>
					催收承诺还款日提醒：
					<br/>
					<a href="javascript:void(0)" class="linkContent" onclick="toUrgeRemind(0)">今天：<input id="toDay0" style="border:0"  size="2"/>笔</a>
					<br />
					<input type="hidden" id="receiptRegister1" value="${indexRemind.receiptRegister1.creditapplicationIds }" />
					<a href="javascript:void(0)" class="linkContent" onclick="toUrgeRemind(2)">3个工作日内：<input id="threeDay3" style="border:0"  size="2"/>笔</a>
					<br />
					<input type="hidden" id="receiptRegister3" value="${indexRemind.receiptRegister3.creditapplicationIds }" />
					<a href="javascript:void(0)" class="linkContent" onclick="toUrgeRemind(6)">7个工作日内：<input id="sevenDay7" style="border:0"  size="2"/>笔</a>
					<br />
					<%--luohongjie  begin<a href="javascript:void(0)" class="linkContent" onclick="toLoanConfirmBack()">需做放款确认退回：${indexRemind.loanConfirmBack.auditCount }&nbsp;笔</a>
				end--%>
				</div>
				</div>
			</sec:authorize>
			<!--回访提醒 -->
			<sec:authorize ifAnyGranted="${control.loanList_vistePanel}"> 
				<div id="visteRemind" class="toPanel" tools="#visteTools">
				<div id="visteId" style="display:none" >
					<a href="javascript:void(0)" class="linkContent" onclick="toVisteRemind(1)">今天：<input id="toDayCustomerList" style="border:0"  size="2"/>&nbsp;笔</a>
					<br />
					<input type="hidden" id="viste1" />
					<a href="javascript:void(0)" class="linkContent" onclick="toVisteRemind(3)">3个工作日内：<input id="threeDayCustomerList" style="border:0"  size="2"/>&nbsp;笔</a>
					<br />
					<input type="hidden" id="viste3"  />
					<a href="javascript:void(0)" class="linkContent" onclick="toVisteRemind(7)">7个工作日内：<input id="sevenDayCustomerList" style="border:0"  size="2"/>&nbsp;笔</a>
					<br />
					<input type="hidden" id="viste7"  />
					<a href="javascript:void(0)" class="linkContent" onclick="toVisteRemind(8)">上期未做回访：<input id="forePeriodDontCallBackList" style="border:0"  size="2"/>&nbsp;笔</a>
					<br />
					<input type="hidden" id="viste8"  />
				</div>
				</div>
			</sec:authorize>
			
			
			<!-- 暂且去掉了统计信息 -->
			<%--<sec:authorize ifAnyGranted="${control.loanList_approve}">
				<div id="salesInfo" class="toPanel" tools="#salesTools">
					<table class="easyui-datagrid" fit="true">
						<thead>
							<tr>
								<th width="100" field="loanRealAndPlannings.dateTime">
									日期
								</th>
								<th width="100" field="loanRealAndPlannings.rCount">
									实际放款量
								</th>
								<th width="100" field="loanRealAndPlannings.rAmount">
									实际合同金额
								</th>
								<th width="100" field="loanRealAndPlannings.pCount">
									计划放款量
								</th>
								<th width="100" field="loanRealAndPlannings.pAmount">
									计划合同金额
								</th>
								<th width="100" field="loanRealAndPlannings.countRatio">
									放款量比例
								</th>
								<th width="100" field="loanRealAndPlannings.amountRatio">
									合同金额比例
								</th>
								<th width="100" field="loanRealAndPlannings.countRatioTotal">
									累计放款量比例
								</th>
								<th width="100" field="loanRealAndPlannings.amountRatioTotal">
									累计合同金额
								</th>
							</tr>
						</thead>
						<tbody align="right">
							<c:forEach items="${loanRealAndPlannings}" var="lrp">
								<tr>
									<td>

										<fmt:formatDate value="${lrp.dateTime}" pattern="yyyy年MM月" />
									</td>
									<td>
										${lrp.rCount}
									</td>
									<td>
										<fmt:formatNumber type="number" value="${lrp.rAmount}" pattern="#,#00.00#" />
									</td>
									<td>
										${lrp.pCount}
									</td>
									<td>
										<fmt:formatNumber type="number" value="${lrp.pAmount}" pattern="#,#00.00#" />
									</td>
									<td>
										<fmt:formatNumber type="number" value="${lrp.countRatio*100}" pattern="#0.00#" />%
									</td>
									<td>
										
										<fmt:formatNumber type="number" value="${lrp.amountRatio*100}" pattern="#0.00#" />%
									</td>
									<td>
										
										<fmt:formatNumber type="number" value="${lrp.countRatioTotal*100}" pattern="#0.00#" />%
									</td>
									<td>
										<fmt:formatNumber type="number" value="${lrp.amountRatioTotal*100}" pattern="#0.00#" />%
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</sec:authorize>
			--%><!-- 暂且去掉了财务提醒 -->
			<%--<sec:authorize ifAnyGranted="${control.financeMoney_financePayment},${control.financeMoney_financeReceive}">
				<div id="financePayment" class="toPanel">
					<sec:authorize ifAnyGranted="${control.financeMoney_financePayment}">
						<a href="javascript:void(0)" class="linkContent" onclick="toFinancePayment()">财务付款失败提醒：${indexRemind.financePaymentRemind.auditCount }&nbsp;笔</a>
						<br />
					</sec:authorize>
					<sec:authorize ifAnyGranted="${control.financeMoney_financeReceive}">
						<a href="javascript:void(0)" class="linkContent" onclick="toFinanceReceiveWill()">财务收款未预约提醒：${indexRemind.financeReceiveRemindWill.auditCount }&nbsp;笔</a>
						<br />
						<a href="javascript:void(0)" class="linkContent" onclick="toFinanceReceiveFail()">财务收款失败提醒：${indexRemind.financeReceiveRemindFail.auditCount }&nbsp;笔</a>
					</sec:authorize>
				</div>
			</sec:authorize>
			--%>
			<!-- 查看公告信息 -->
			<div id="systemAnnouncementDailog"></div>
			<div id="announcementTools" style="width: 100px;">
				<input type="button" value="更多" onclick="showMoreSystemAnnouncement();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			<div id="countInfoTools" style="width: 100px;">
				<input type="button" value="详细" onclick="showLoanPortfolioList();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			<div id="countInfoNewTools" style="width: 100px;">
				<input type="button" value="详细" onclick="showLoanPortfolioList();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			
			<div id="urgeToolsurgeTools" style="width: 100px;">
				<input type="button" value="详细" onclick="showLoanPortfolioList();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			
			<div id="urgeTools" style="width: 100px;">
				<input type="button" value="刷新" onclick="toUrge();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			<div id="visteTools" style="width: 100px;">
				<input type="button" value="刷新" onclick="toViste();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			<div id="salesTools" style="width: 100px;">
				<input type="button" value="滚动预测" onclick="showRollingForecast();" style="border: 0px; background-color: transparent; cursor: pointer;" />
				<input type="button" value="销售计划" onclick="showSalesPlanning();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
			<%--<div id="comparisonTools" style="width: 100px;">
				<input type="button" value="查看预测对比" onclick="showComparison();" style="border: 0px; background-color: transparent; cursor: pointer;" />
			</div>
		--%></div>

	</body>
</html>
