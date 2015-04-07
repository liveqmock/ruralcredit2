<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="../include/easyui.jsp"></jsp:include>
	<head>
		<%--<script type="text/javascript">var serverName="<%=path%>";</script>--%>
		<script type="text/javascript">var serverName="<%=path%>";</script>
		<script type="text/javascript" src="<%=basePath%>scripts/business/creditBackgroundInvestigation.js"></script>
		<script type="text/javascript">
		
		var contactsSurveySize = 0;
		var sizeA = 0
	$(function() {
		var dicReport = new DataDictionary();
		dicReport.addDic("creditHistoryList0LoanOrganization_creditHistoryList[0].loanOrganization", "depositOrganization");
		dicReport.addDic("creditHistoryList1LoanOrganization_creditHistoryList[1].loanOrganization", "depositOrganization");
		dicReport.addDic("creditHistoryList2LoanOrganization_creditHistoryList[2].loanOrganization", "depositOrganization");
		dicReport.addDic("creditHistoryList3LoanOrganization_creditHistoryList[3].loanOrganization", "depositOrganization");
		dicReport.addDic("creditHistoryList4LoanOrganization_creditHistoryList[4].loanOrganization", "depositOrganization");
		dicReport.addDic("creditHistoryList5LoanOrganization_creditHistoryList[5].loanOrganization", "depositOrganization");

		dicReport.addDic("creditHistoryList0AccountStatus_creditHistoryList[0].accountStatus", "accountStatus");
		dicReport.addDic("creditHistoryList1AccountStatus_creditHistoryList[1].accountStatus", "accountStatus");
		dicReport.addDic("creditHistoryList2AccountStatus_creditHistoryList[2].accountStatus", "accountStatus");
		dicReport.addDic("creditHistoryList3AccountStatus_creditHistoryList[3].accountStatus", "accountStatus");
		dicReport.addDic("creditHistoryList4AccountStatus_creditHistoryList[4].accountStatus", "accountStatus");
		dicReport.addDic("creditHistoryList5AccountStatus_creditHistoryList[5].accountStatus", "accountStatus");

		dicReport.addDic("creditHistoryList0RepaymentType_creditHistoryList[0].repaymentType", "repaymentType");
		dicReport.addDic("creditHistoryList1RepaymentType_creditHistoryList[1].repaymentType", "repaymentType");
		dicReport.addDic("creditHistoryList2RepaymentType_creditHistoryList[2].repaymentType", "repaymentType");
		dicReport.addDic("creditHistoryList3RepaymentType_creditHistoryList[3].repaymentType", "repaymentType");
		dicReport.addDic("creditHistoryList4RepaymentType_creditHistoryList[4].repaymentType", "repaymentType");
		dicReport.addDic("creditHistoryList5RepaymentType_creditHistoryList[5].repaymentType", "repaymentType");

		//dicReport.addDic("linkmanInvestigationList0Relation_linkmanInvestigationList[0].relation_Y", "borrowerreRationship");
		//修改与联系人关系 的数据字典   如下：
		dicReport.addDic("linkmanInvestigationList0Relation_linkmanInvestigationList[0].relation_Y", "borrowerreRationB");
		//dicReport.addDic("linkmanInvestigationList1Relation_linkmanInvestigationList[1].relation_Y", "borrowerRelation");

		dicReport.addDic("linkmanInvestigationList0HowLong_linkmanInvestigationList[0].howLong_Y", "howLong");
		//dicReport.addDic("linkmanInvestigationList1HowLong_linkmanInvestigationList[1].howLong_Y", "howLong");

		dicReport.addDic("linkmanInvestigationList0Discontentment_Y", "answer");
		//dicReport.addDic("linkmanInvestigationList1Discontentment_linkmanInvestigationList[1].discontentment_Y", "answer");

		dicReport.addDic("linkmanInvestigationList0Gambling_linkmanInvestigationList[0].gambling_Y", "answer");
		//dicReport.addDic("linkmanInvestigationList1Gambling_linkmanInvestigationList[1].gambling_Y", "answer");

		dicReport.addDic("appelleeRecordIsAppelleeRecord_appelleeRecord.isAppelleeRecord_Y", "answer");

		dicReport.addDic("appelleeRecordAccusalStatusOne", "accusalStatus");
		dicReport.addDic("appelleeRecordAccusalStatusTwo", "accusalStatus");

		dicReport.addDic("result_result_Y", "result");
		
		
		//获取当前div中的表格数量luohongjie
		var contactsSurvey =document.getElementById("contactsSurvey");
		var objectsContactsSurvey = $("#contactsSurvey table");
		contactsSurveySize= objectsContactsSurvey.length;
		for(var i=1;i<contactsSurveySize;i++){
			
			dicReport.addDic("linkmanInvestigationList"+i+"Relation_Y", "borrowerreRationB");

			dicReport.addDic("linkmanInvestigationList"+i+"HowLong_Y", "howLong");

			dicReport.addDic("linkmanInvestigationList"+i+"Discontentment_Y", "answer");
			dicReport.addDic("linkmanInvestigationList"+i+"Gambling_Y", "answer");

		
		
			//选下拉框的时候判断所选
			var j=1;
			$("#linkmanInvestigationList"+i+"Discontentment").combobox({onSelect:function(record){
						//新加的
						if ("0" == record.codeKey) {
						sizeA=this.id;
						j=sizeA.substr(24,1);
						
							$("#areaOne"+j+"").show();
							$("#areaOne"+j+"S").show();
							$("#linkmanInvestigationList"+j+"DiscontentmentDetail").validatebox({
								required : true,
								validType : "length[0,100]"
							});
						} else {
							$("#linkmanInvestigationList"+j+"DiscontentmentDetail").validatebox({
								required : false,
								validType : "length[0,100]"
							});
							$("#linkmanInvestigationList"+j+"DiscontentmentDetail").val("");
							$("#areaOne"+j+"").hide();
							$("#areaOne"+j+"").hide();
							
						}
			}});
			
			
			//选下拉框的时候判断所选的值     是否有赌博习惯
			var m=1;
			$("#linkmanInvestigationList"+i+"Gambling").combobox({onSelect:function(record){
				if ("0" == record.codeKey) {
					sizeA=this.id;
					m=sizeA.substr(24,1);
					$("#areaTwo"+m+"").show();
					$("#areaTwo"+m+"S").show();
					$("#linkmanInvestigationList"+m+"GamblingDetail").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#linkmanInvestigationList"+m+"GamblingDetail").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#linkmanInvestigationList"+m+"GamblingDetail").val("");
					$("#areaTwo"+m+"").hide();
					$("#areaTwo"+m+"S").hide();
				}
				}
			});
		
		
		
		}
		dicReport.dicAjax();
		//是否有任何不满
		$("#linkmanInvestigationList0Discontentment").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					$("#areaOne0").show();
					$("#areaOne0S").show();
					$("#linkmanInvestigationList0DiscontentmentDetail").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#linkmanInvestigationList0DiscontentmentDetail").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#linkmanInvestigationList0DiscontentmentDetail").val("");
					$("#areaOne0").hide();
					$("#areaOne0S").hide();
				}
			}
		});
		//是否有赌博习惯
		$("#linkmanInvestigationList0Gambling").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					$("#areaTwo0").show();
					$("#areaTwo0S").show();
					$("#linkmanInvestigationList0GamblingDetail").validatebox({
						required : true,
						validType : "length[0,100]"
					});
				} else {
					$("#linkmanInvestigationList0GamblingDetail").validatebox({
						required : false,
						validType : "length[0,100]"
					});
					$("#linkmanInvestigationList0GamblingDetail").val("");
					$("#areaTwo0").hide();
					$("#areaTwo0S").hide();
				}
			}
		});
		
		//罗红杰修改
		
				
		//$("#linkmanInvestigationList"'+contactsSurveySize+'"Gambling").combobox({
			//onSelect : function(record) {
			//	console.info(record);
				//if ("0" == record.codeKey) {
					//$("#areaTwo"'+contactsSurveySize+').show();
					//$("#areaTwo"'+contactsSurveySize+'"S").show();
					//$("#linkmanInvestigationList"'+contactsSurveySize+'"GamblingDetail").validatebox({
						//required : true,
						//validType : "length[0,100]"
					//});
				//} else {
					//$("#linkmanInvestigationList"'+contactsSurveySize+'"GamblingDetail").validatebox({
						//required : false,
						//validType : "length[0,100]"
				//	});
					//$("#linkmanInvestigationList"'+contactsSurveySize+'"GamblingDetail").val("");
					//$("#areaTwo"'+contactsSurveySize+').hide();
					//$("#areaTwo"'+contactsSurveySize+'"S").hide();
				//}
			//}
		//});
		// $("#appelleeRecordAccusalStatusOne").combobox({
		// disabled : true
		// });
		// $("#appelleeRecordAccusalStatusTwo").combobox({
		// disabled : true
		// });
		$("#appelleeRecordIsAppelleeRecord").combobox({
			onSelect : function(record) {
				if ("0" == record.codeKey) {
					// $("#appelleeRecordAccusalOne").attr('readonly', false);
					// $("#appelleeRecordAccusalStatusOne").combobox('enable');
					// $("#appelleeRecordAccusalTwo").attr('readonly', false);
					// $("#appelleeRecordAccusalStatusTwo").combobox('enable');
					// <textarea id='appelleeRecordAccusalOne' name="appelleeRecord.accusalOne" cols="100" rows='2'
				// validType='length[0,100]' readonly="readonly" style='width:
				// 600px;'>${creditInvestigation.appelleeRecord.accusalOne}</textarea>
					// <input id="appelleeRecordAccusalStatusOne" name="appelleeRecord.accusalStatusOne" type="text" style="width: 125px;"
				// />
					$("#appelleeRecordOne0").show();
					$("#appelleeRecordOne1").show();
					$("#appelleeRecordOne2").show();
					$("#appelleeRecordTwo0").show();
					$("#appelleeRecordTwo1").show();
					$("#appelleeRecordTwo2").show();

				} else {

					$("#appelleeRecordOne0").hide();
					$("#appelleeRecordOne1").hide();
					$("#appelleeRecordOne2").hide();
					$("#appelleeRecordTwo0").hide();
					$("#appelleeRecordTwo1").hide();
					$("#appelleeRecordTwo2").hide();
				}
			}
		});
		$("#creditInvForm").form('validate');
		$("#creditInvestigationName").focus();
	});
	
		function comfirm(one){
			$.messager.confirm('提示','确定删除本条新增的联系人调查吗?',function(r){   
				if(r){
					contactsSurveySize=contactsSurveySize-1;
					$(one).parent().parent().parent().remove();
				}
			  }); 
		}
	
	//添加联系人调查一块
	function addContactsSurvey(){
		var contactsSurvey=document.getElementById("contactsSurvey");
		var table=document.createElement("table");
		table.width="100%";
		var size=contactsSurveySize;
     
		//第一行
		var tr1=document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		td1.height="20px";
		td1.width="130px";
		td2.width="130px";
		td3.width="130px";
		td4.width="130px";
		td5.width="130px";
		td6.width="130px";
		
		tr1.appendChild(td1);
		tr1.appendChild(td2);
		tr1.appendChild(td3);
		tr1.appendChild(td4);
		tr1.appendChild(td5);
	    tr1.appendChild(td6);
		table.appendChild(tr1);
		
		//第二行的列
		var tr2=document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		td1.innerHTML ='联系人姓名：';
		td2.innerHTML='<input type="hidden" name="linkmanInvestigationList['+size+'].linkmanInvestigationId"  />'
		+'<input type="hidden" name="linkmanInvestigationList['+size+'].creditInvestigatioId" />'
		+'<input type="text" id="linkmanInvestigationList'+size+'name"  name="linkmanInvestigationList['+size+'].name"  class="easyui-validatebox" required="true" maxlength="10" />'
		+'<input name="linkmanInvestigationList['+size+'].seq" type="hidden"  value="'+(size)+'"/>';
		td3.innerHTML='和申请人关系：';
		td4.innerHTML='<input id="linkmanInvestigationList'+size+'Relation" name="linkmanInvestigationList['+size+'].newrelation" />';
		td5.innerHTML='联系电话：';
		td6.innerHTML='<input type="text" id="linkmanInvestigationList'+size+'mobilephone" name="linkmanInvestigationList['+size+'].mobilephone"  class="easyui-validatebox" validType="phoneNumber" maxlength="11" />';
		td1.height="20px";
		td1.align="right";
		td2.align="left";
		td3.align="right";
		td4.align="left";
		td5.align="right";
		td6.align="left";

		tr2.appendChild(td1);
		tr2.appendChild(td2);
		tr2.appendChild(td3);
		tr2.appendChild(td4);
		tr2.appendChild(td5);
		tr2.appendChild(td6);
		table.appendChild(tr2);
		

		//第三行的列
		var tr3 = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		td1.innerHTML ='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？';
		td1.setAttribute("colspan","5");
		td2.innerHTML ='<input id="linkmanInvestigationList'+size+'HowLong" name="linkmanInvestigationList['+size+'].howLong"  style="width: 125px;" />';
		td1.height="20px";
		td1.align="left";
		td2.align="left";
		tr3.appendChild(td1);
		tr3.appendChild(td2);
		table.appendChild(tr3);
		

		//第四行的列
		var tr4 = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		td1.innerHTML ='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你知道有人对（申请人夫妻姓名）有任何不满吗？';
		td1.setAttribute("colspan","5");
		td2.innerHTML ='<input id="linkmanInvestigationList'+size+'Discontentment" name="linkmanInvestigationList['+size+'].discontentment" style="width: 125px;" />';
		td1.height = "20px";
		td1.align="left";
		td2.align="left";
		tr4.appendChild(td1);
		tr4.appendChild(td2);
		table.appendChild(tr4);
		
		//第五行的列
		var tr5 = document.createElement("tr");
		var td1 = document.createElement("td");
		td1.setAttribute("colspan","5");
		td1.height = "20px";
		td1.align="left";
		
		td1.innerHTML='<span id="areaOne'+size+'" style="display:none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList'+size+'DiscontentmentDetail" name="linkmanInvestigationList['+size+'].discontentmentDetail"'
	    +'rows="3" style="width: 500px;" onclick="textCount(\'linkmanInvestigationList'+size+'DiscontentmentDetail\', \'areaOne'+size+'S\', 100);"   maxlength="100" class="easyui-validatebox" validType="length[0,100]"></textarea> </span>'
	    +'&nbsp;&nbsp;<span id="areaOne'+size+'S"></span>';
	    
		tr5.appendChild(td1);
		table.appendChild(tr5);
		
		//第六行的列
		var tr6 = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		td1.innerHTML ='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？';
		td1.setAttribute("colspan","5");
		td2.innerHTML ='<input id="linkmanInvestigationList'+size+'Gambling" name="linkmanInvestigationList['+size+'].gambling"  style="width: 125px;" />';
		td1.height = "20px";
		td1.align="left";
		td2.align="left";
		tr6.appendChild(td1);
		tr6.appendChild(td2);
		table.appendChild(tr6);
	
		
		//第七行的列
		var tr7 = document.createElement("tr");
		var td1 = document.createElement("td");
		td1.setAttribute("colspan","6");
		td1.height = "20px";
		td1.align="left";
		td1.innerHTML='<span id="areaTwo'+size+'" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList'+size+'GamblingDetail" name="linkmanInvestigationList['+size+'].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount(\'linkmanInvestigationList'+size+'GamblingDetail\', \'areaTwo'+size+'S\', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">'
			+'</textarea></span>&nbsp;&nbsp;<span id="areaTwo'+size+'S"></span>';
		tr7.appendChild(td1);
		table.appendChild(tr7);
		
		//第八行的列
		var tr8= document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		td1.innerHTML ='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点评';
		td1.setAttribute("colspan","6");
		td1.height = "20px";
		td1.align="left";
		tr8.appendChild(td1);
		table.appendChild(tr8);
		
		//第九行的列 点评
		var tr9 = document.createElement("tr");
		var td1 = document.createElement("td");
		td1.setAttribute("colspan","6");
			td1.innerHTML=' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="linkmanInvestigationList'+size+'Remark" name="linkmanInvestigationList['+size+'].remark" rows="3" cols="150"'
			+'style="width: 700px;" onclick="textCount(\'linkmanInvestigationList'+size+'Remark\', \'remarkTwo'+size+'\', 100);" class="easyui-validatebox" required="true" validType="length[0,100]"'
			+'maxlength="100"></textarea>&nbsp;&nbsp;<span id="remarkTwo'+size+'"></span>';
		td1.height = "20px";
		td1.align="left";
		tr9.appendChild(td1);
		table.appendChild(tr9);				
		
		//第十行
		var tr10=document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		td1.height="20px";
		td1.width="130px";
		td2.width="130px";
		td3.width="130px";
		td4.width="130px";
		td5.width="130px";
		td6.width="130px";
		
		tr10.appendChild(td1);
		tr10.appendChild(td2);
		tr10.appendChild(td3);
		tr10.appendChild(td4);
		tr10.appendChild(td5);
	    tr10.appendChild(td6);
		table.appendChild(tr10);
		
		
		 //第十一行
		var tr11= document.createElement("tr");
		var td1 = document.createElement("td");
		td1.setAttribute("colspan","6");
		td1.height = "20px";
		td1.align="right";
		td1.style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;";
		td1.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;<a  iconCls="icon-remove" id="linkmanInvestigationList'+size+'but"  class="easyui-linkbutton" onclick="comfirm(this)"></a>';
		tr11.appendChild(td1);
		table.appendChild(tr11);
		//给div添加一个表格
		contactsSurvey.appendChild(table);
		//调用同步加载数据方法
		var dicReport1 = new DataDictionaryBySynchronize();
		dicReport1.addDic("linkmanInvestigationList"+size+"Relation_Y","borrowerreRationB");
		dicReport1.addDic("linkmanInvestigationList"+size+"HowLong_Y","howLong");
		dicReport1.addDic("linkmanInvestigationList"+size+"Discontentment_Y", "answer");
		dicReport1.addDic("linkmanInvestigationList"+size+"Gambling_Y", "answer");
		dicReport1.dicAjax();
		
		//选下拉框的时候判断所选
		$("#linkmanInvestigationList"+size+"Discontentment").combobox({onSelect:function(record){
					if ("0" == record.codeKey) {
						$("#areaOne"+size+"").show();
						$("#areaOne"+size+"S").show();
						$("#linkmanInvestigationList"+size+"DiscontentmentDetail").validatebox({
							required : true,
							validType : "length[0,100]"
						});
					} else {
						$("#linkmanInvestigationList"+size+"DiscontentmentDetail").validatebox({
							required : false,
							validType : "length[0,100]"
						});
						$("#linkmanInvestigationList"+size+"DiscontentmentDetail").val("");
						$("#areaOne"+size+"").hide();
						$("#areaOne"+size+"").hide();
					}
				
		}});
		$("#linkmanInvestigationList"+size+"Discontentment").combobox("validate");
		//$("#linkmanInvestigationList"+size+"DiscontentmentDetail").validatebox({required:true});
		//$("#linkmanInvestigationList"+size+"DiscontentmentDetail").validatebox("validate");
		
		//选下拉框的时候判断所选的值     是否有赌博习惯
		
		$("#linkmanInvestigationList"+size+"Gambling").combobox({onSelect:function(record){
			if ("0" == record.codeKey) {
				$("#areaTwo"+size+"").show();
				$("#areaTwo"+size+"S").show();
				$("#linkmanInvestigationList"+size+"GamblingDetail").validatebox({
					required : true,
					validType : "length[0,100]"
				});
			} else {
				$("#linkmanInvestigationList"+size+"GamblingDetail").validatebox({
					required : false,
					validType : "length[0,100]"
				});
				$("#linkmanInvestigationList"+size+"GamblingDetail").val("");
				$("#areaTwo"+size+"").hide();
				$("#areaTwo"+size+"S").hide();
			}
		}
	});
		$("#linkmanInvestigationList"+size+"Gambling").combobox("validate");
		
		
		$("#linkmanInvestigationListAdd"+size+"but").linkbutton({iconCls:"icon-addOne"});		
		$("#linkmanInvestigationList"+size+"but").linkbutton({iconCls:"icon-remove"});
		
		$("#linkmanInvestigationList"+size+"Remark").validatebox({required:true});
		$("#linkmanInvestigationList"+size+"Remark").validatebox("validate");
		
	    $("#linkmanInvestigationList"+size+"name").validatebox({required:true});
		$("#linkmanInvestigationList"+size+"name").validatebox("validate");
		
		 //$("#linkmanInvestigationList"+size+"mobilephone").validatebox({required:true});
		 $("#linkmanInvestigationList"+size+"mobilephone").validatebox();
		
		contactsSurveySize=contactsSurveySize+1;
	}
</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center">
			<form id="creditInvForm" method="post" novalidate>
				<table fit="true" style="width: 100%;" align="center">
					<tr align="center">
						<td align="center" width="800">
							<span style="font-size: 25px; font-weight: 700; color: #4B0082;"> 信用及背景调查报告 </span>
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							<input type="hidden" name="creditapplicationId" value="${creditInvestigation.creditapplicationId}" />
							<input type="hidden" name="creditInvestigatioId" value="${creditInvestigation.creditInvestigatioId}" />
							<input type="hidden" name="isSubmit" value="${creditInvestigation.isSubmit}" />
							<span style="font-size: 15px; font-weight: 700;">业务编号：${creditInvestigation.groupNumber}</span>
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							借款人姓名：
							<input type="text" id="creditInvestigationName" name="name" value="${creditInvestigation.name}" class="easyui-validatebox" required="true" maxlength="10" />
							&nbsp;&nbsp;&nbsp;&nbsp; 共借人姓名：
							<input id="spouseName" type="text" name="spouseName" value="${creditInvestigation.spouseName}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
					</tr>
					<tr align="center">
						<td align="center" width="800">
							&nbsp;
						</td>
					</tr>

				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" colspan="9">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="9">
							<span style="font-size: 15px; font-weight: 700;">信用历史</span>
						</td>
					</tr>
					<tr>
						<td colspan="9" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>

					<tr>
						<td align="center" width="100">
							贷款机构
						</td>
						<td align="center" width="10">
							发放日期
						</td>
						<td align="center" width="100">
							到期日期
						</td>
						<td align="center" width="100">
							授信额度
						</td>
						<td align="center" width="100">
							使用额度
						</td>
						<td align="center" width="100">
							账户状态
						</td>
						<td align="center" width="100">
							还款方式
						</td>
						<td align="center" width="100">
							贷款余额
						</td>
						<td align="center" width="100">
							每次还款金额
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[0].creditHistoryId" value="${creditInvestigation.creditHistoryList[0].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[0].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[0].creditInvestigatioId}" />
							<input id="creditHistoryList0LoanOrganization" type="text" name="creditHistoryList[0].loanOrganization" value="${creditInvestigation.creditHistoryList[0].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList0LoanDate" type="text" name="creditHistoryList[0].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList0TermDate" type="text" name="creditHistoryList[0].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[0].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].creditAccount" value="${creditInvestigation.creditHistoryList[0].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].usedAccount" value="${creditInvestigation.creditHistoryList[0].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList0AccountStatus" name="creditHistoryList[0].accountStatus" value="${creditInvestigation.creditHistoryList[0].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList0RepaymentType" name="creditHistoryList[0].repaymentType" value="${creditInvestigation.creditHistoryList[0].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList0LoanAmount" type="text" name="creditHistoryList[0].loanAmount" value="${creditInvestigation.creditHistoryList[0].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[0].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[0].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[1].creditHistoryId" value="${creditInvestigation.creditHistoryList[1].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[1].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[1].creditInvestigatioId}" />
							<input id="creditHistoryList1LoanOrganization" type="text" name="creditHistoryList[1].loanOrganization" value="${creditInvestigation.creditHistoryList[1].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList1LoanDate" type="text" name="creditHistoryList[1].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList1TermDate" type="text" name="creditHistoryList[1].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[1].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].creditAccount" value="${creditInvestigation.creditHistoryList[1].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].usedAccount" value="${creditInvestigation.creditHistoryList[1].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList1AccountStatus" name="creditHistoryList[1].accountStatus" value="${creditInvestigation.creditHistoryList[1].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList1RepaymentType" name="creditHistoryList[1].repaymentType" value="${creditInvestigation.creditHistoryList[1].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList1LoanAmount" type="text" name="creditHistoryList[1].loanAmount" value="${creditInvestigation.creditHistoryList[1].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" onblur="computLoanAmount()" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[1].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[1].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[2].creditHistoryId" value="${creditInvestigation.creditHistoryList[2].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[2].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[2].creditInvestigatioId}" />
							<input id="creditHistoryList2LoanOrganization" type="text" name="creditHistoryList[2].loanOrganization" value="${creditInvestigation.creditHistoryList[2].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList2LoanDate" type="text" name="creditHistoryList[2].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList2TermDate" type="text" name="creditHistoryList[2].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[2].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].creditAccount" value="${creditInvestigation.creditHistoryList[2].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].usedAccount" value="${creditInvestigation.creditHistoryList[2].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList2AccountStatus" name="creditHistoryList[2].accountStatus" value="${creditInvestigation.creditHistoryList[2].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList2RepaymentType" name="creditHistoryList[2].repaymentType" value="${creditInvestigation.creditHistoryList[2].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList2LoanAmount" type="text" name="creditHistoryList[2].loanAmount" value="${creditInvestigation.creditHistoryList[2].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[2].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[2].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[3].creditHistoryId" value="${creditInvestigation.creditHistoryList[3].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[3].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[3].creditInvestigatioId}" />
							<input id="creditHistoryList3LoanOrganization" type="text" name="creditHistoryList[3].loanOrganization" value="${creditInvestigation.creditHistoryList[3].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList3LoanDate" type="text" name="creditHistoryList[3].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList3TermDate" type="text" name="creditHistoryList[3].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[3].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].creditAccount" value="${creditInvestigation.creditHistoryList[3].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].usedAccount" value="${creditInvestigation.creditHistoryList[3].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList3AccountStatus" name="creditHistoryList[3].accountStatus" value="${creditInvestigation.creditHistoryList[3].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList3RepaymentType" name="creditHistoryList[3].repaymentType" value="${creditInvestigation.creditHistoryList[3].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList3LoanAmount" type="text" name="creditHistoryList[3].loanAmount" value="${creditInvestigation.creditHistoryList[3].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[3].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[3].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[4].creditHistoryId" value="${creditInvestigation.creditHistoryList[4].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[4].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[4].creditInvestigatioId}" />
							<input id="creditHistoryList4LoanOrganization" type="text" name="creditHistoryList[4].loanOrganization" value="${creditInvestigation.creditHistoryList[4].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList4LoanDate" type="text" name="creditHistoryList[4].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList4TermDate" type="text" name="creditHistoryList[4].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[4].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].creditAccount" value="${creditInvestigation.creditHistoryList[4].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].usedAccount" value="${creditInvestigation.creditHistoryList[4].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList4AccountStatus" name="creditHistoryList[4].accountStatus" value="${creditInvestigation.creditHistoryList[4].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList4RepaymentType" name="creditHistoryList[4].repaymentType" value="${creditInvestigation.creditHistoryList[4].repaymentType}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList4LoanAmount" type="text" name="creditHistoryList[4].loanAmount" value="${creditInvestigation.creditHistoryList[4].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[4].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[4].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="creditHistoryList[5].creditHistoryId" value="${creditInvestigation.creditHistoryList[5].creditHistoryId}" />
							<input type="hidden" name="creditHistoryList[5].creditInvestigatioId" value="${creditInvestigation.creditHistoryList[5].creditInvestigatioId}" />
							<input id="creditHistoryList5LoanOrganization" type="text" name="creditHistoryList[5].loanOrganization" value="${creditInvestigation.creditHistoryList[5].loanOrganization}" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList5LoanDate" type="text" name="creditHistoryList[5].loanDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].loanDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input id="creditHistoryList5TermDate" type="text" name="creditHistoryList[5].termDate" class="easyui-datebox" value="<fmt:formatDate value='${creditInvestigation.creditHistoryList[5].termDate}' pattern='yyyy-MM-dd' />" style="width: 100px;" editable="false" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].creditAccount" value="${creditInvestigation.creditHistoryList[5].creditAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].usedAccount" value="${creditInvestigation.creditHistoryList[5].usedAccount}" size="15" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" maxlength="10" />
						</td>
						<td>
							<input id="creditHistoryList5AccountStatus" name="creditHistoryList[5].accountStatus" value="${creditInvestigation.creditHistoryList[5].accountStatus}" style="width: 90px;" />

						</td>
						<td>
							<input id="creditHistoryList5RepaymentType" name="creditHistoryList[5].repaymentType" value="${creditInvestigation.creditHistoryList[5].repaymentType}" style="width: 90px;" />
						</td>
						<td>
							<input id="creditHistoryList5LoanAmount" type="text" name="creditHistoryList[5].loanAmount" value="${creditInvestigation.creditHistoryList[5].loanAmount}" onblur="computLoanAmount();" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
						<td>
							<input type="text" name="creditHistoryList[5].repaymentAmountPer" value="${creditInvestigation.creditHistoryList[5].repaymentAmountPer}" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" maxlength="10" />
						</td>
					</tr>

					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td align="right">
							总余额:
						</td>
						<td>
							<input id="total" type="text" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" precision="2" size="15" onblur="computLoanAmount();" maxlength="10" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="9">
							&nbsp;
						</td>
					</tr>
				</table>
				<div id="contactsSurvey">
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<span style="font-size: 15px; font-weight: 700;">联系人调查</span>
						</td>
						<td align="right">&nbsp;<a iconCls="icon-addOne" class="easyui-linkbutton" onclick="javascript:addContactsSurvey()"></a> </td>
					</tr>
					<tr>
						<td colspan="6" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="right">
							联系人姓名：
						</td>
						<td align="left">
							<input type="hidden" name="linkmanInvestigationList[0].linkmanInvestigationId" value="${creditInvestigation.linkmanInvestigationList[0].linkmanInvestigationId}" />
							<input type="hidden" name="linkmanInvestigationList[0].creditInvestigatioId" value="${creditInvestigation.linkmanInvestigationList[0].creditInvestigatioId}" />
							<input type="text" name="linkmanInvestigationList[0].name" value="${creditInvestigation.linkmanInvestigationList[0].name}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
						<td align="right">
							和申请人关系：
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Relation" name="linkmanInvestigationList[0].newrelation" value="${creditInvestigation.linkmanInvestigationList[0].newrelation}" required="true" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<input type="text" name="linkmanInvestigationList[0].mobilephone" value="${creditInvestigation.linkmanInvestigationList[0].mobilephone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0HowLong" name="linkmanInvestigationList[0].howLong" value="${creditInvestigation.linkmanInvestigationList[0].howLong}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你知道有人对（申请人夫妻姓名）有任何不满吗？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Discontentment" name="linkmanInvestigationList[0].discontentment" value="${creditInvestigation.linkmanInvestigationList[0].discontentment}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[0].discontentment==0}">
									<span id="areaOne0"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0DiscontentmentDetail" name="linkmanInvestigationList[0].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList0DiscontentmentDetail', 'areaOne0S', 100);" maxlength="100" class="easyui-validatebox" required="true" validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].discontentmentDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne0" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0DiscontentmentDetail" name="linkmanInvestigationList[0].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList0DiscontentmentDetail', 'areaOne0S', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].discontentmentDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
						</td>
						<td align="left">
							<span id="areaOne0S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList0Gambling" name="linkmanInvestigationList[0].gambling" value="${creditInvestigation.linkmanInvestigationList[0].gambling}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							<c:choose>
								<c:when test="${creditInvestigation.linkmanInvestigationList[0].gambling==0}">
									<span id="areaTwo0"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0GamblingDetail" name="linkmanInvestigationList[0].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList0GamblingDetail', 'areaTwo0S', 100);" maxlength="100" class="easyui-validatebox" required="true" validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].gamblingDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo0" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList0GamblingDetail" name="linkmanInvestigationList[0].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList0GamblingDetail', 'areaTwo0S', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.linkmanInvestigationList[0].gamblingDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaTwo0S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点评
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea id="linkmanInvestigationList0Remark" name="linkmanInvestigationList[0].remark" rows="3" cols="150" style="width: 700px;" class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('linkmanInvestigationList0Remark', 'remarkOne', 100);" maxlength="100">${creditInvestigation.linkmanInvestigationList[0].remark}</textarea>
							&nbsp;&nbsp;
							<span id="remarkOne"></span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
						&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
				</table>
				
				 <c:forEach items="${creditInvestigation.linkmanInvestigationList}" var="linkmans" varStatus="contacts" begin="1">
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0" >
					<tr>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
						<td align="center" width="130">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							联系人姓名：
						</td>
						<td align="left">
							<input type="hidden" name="linkmanInvestigationList[${contacts.count}].linkmanInvestigationId" value="${linkmans.linkmanInvestigationId}" />
							<input id="creditInvestigatioId${contacts.count}" type="hidden" name="linkmanInvestigationList[${contacts.count}].creditInvestigatioId" value="${linkmans.creditInvestigatioId}" />
							<input name="linkmanInvestigationList[${contacts.count}].seq" type="hidden"  value="${contacts.count}"/>
							<input type="text" name="linkmanInvestigationList[${contacts.count}].name" value="${linkmans.name}" class="easyui-validatebox" required="true" maxlength="10" />
						</td>
						<td align="right">
							和申请人关系：
						</td>
						<td align="left">
							 <input id="linkmanInvestigationList${contacts.count}Relation" name="linkmanInvestigationList[${contacts.count}].newrelation" value="${linkmans.newrelation}" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<input type="text" name="linkmanInvestigationList[${contacts.count}].mobilephone" value="${linkmans.mobilephone}" class="easyui-validatebox" validType="phoneNumber" maxlength="11" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你认识（申请人姓名）多久了？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList${contacts.count}HowLong" name="linkmanInvestigationList[${contacts.count}].howLong" value="${linkmans.howLong}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你知道有人对（申请人夫妻姓名）有任何不满吗？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList${contacts.count}Discontentment" name="linkmanInvestigationList[${contacts.count}].discontentment" value="${linkmans.discontentment}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							<c:choose>
								<c:when test="${linkmans.discontentment==0}">
									<span id="areaOne${contacts.count}"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList${contacts.count}DiscontentmentDetail" name="linkmanInvestigationList[${contacts.count}].discontentmentDetail" rows="3"  style="width: 500px;"   onclick="textCount('linkmanInvestigationList${contacts.count}DiscontentmentDetail', 'areaOne${contacts.count}S', 100);" maxlength="100" class="easyui-validatebox" required="true" validType="length[0,100]">${linkmans.discontentmentDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaOne${contacts.count}" style="display: none;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList${contacts.count}DiscontentmentDetail" name="linkmanInvestigationList[${contacts.count}].discontentmentDetail" rows="3" style="width: 500px;" onclick="textCount('linkmanInvestigationList${contacts.count}DiscontentmentDetail', 'areaOne${contacts.count}S', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${linkmans.discontentmentDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaOne${contacts.count}S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（申请人夫妇姓名）是否有长期赌博的习惯呢？
						</td>
						<td align="left">
							<input id="linkmanInvestigationList${contacts.count}Gambling" name="linkmanInvestigationList[${contacts.count}].gambling" value="${linkmans.gambling}" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							<c:choose>
								<c:when test="${linkmans.gambling==0}">
									<span id="areaTwo${contacts.count}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList${contacts.count}GamblingDetail" name="linkmanInvestigationList[${contacts.count}].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList${contacts.count}GamblingDetail', 'areaTwo${contacts.count}S', 100);" maxlength="100" class="easyui-validatebox" required="true" validType="length[0,100]">${linkmans.gamblingDetail}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="areaTwo${contacts.count}" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea id="linkmanInvestigationList${contacts.count}GamblingDetail" name="linkmanInvestigationList[${contacts.count}].gamblingDetail" rows="2" style="width: 500px;" onclick="textCount('linkmanInvestigationList${contacts.count}GamblingDetail', 'areaTwo${contacts.count}S', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${linkmans.gamblingDetail}</textarea> </span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<span id="areaTwo${contacts.count}S"></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点评
						</td>
					</tr>
					<tr>
						<td align="left" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea id="linkmanInvestigationList${contacts.count}Remark" name="linkmanInvestigationList[${contacts.count}].remark" rows="3" cols="150" style="width: 700px;" class="easyui-validatebox" required="true" validType="length[0,100]" onclick="textCount('linkmanInvestigationList${contacts.count}Remark', 'remarkTwo${contacts.count}', 100);" maxlength="100">${linkmans.remark}</textarea>
							&nbsp;&nbsp;
							<span id="remarkTwo${contacts.count}"></span>
						</td>
					</tr>
					 <tr>
				        <td colspan="6" align="right"><a  iconCls="icon-remove"  class="easyui-linkbutton" onclick="comfirm(this)"></a></td>
				        </tr>
						<tr>
						<td colspan="6" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
				</table>
				</c:forEach>
				</div>
				 
				
				
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<span style="font-size: 15px; font-weight: 700;">被执行记录</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="left" width="600">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人夫妇是否有过任何犯罪记录？如果有，请详细说明。
						</td>
						<td align="left" width="200">
							<input type="hidden" name="appelleeRecord.appelleeRecordId" value="${creditInvestigation.appelleeRecord.appelleeRecordId}" />
							<input type="hidden" name="appelleeRecord.creditInvestigatioId" value="${creditInvestigation.appelleeRecord.creditInvestigatioId}" />
							<input id="appelleeRecordIsAppelleeRecord" name="appelleeRecord.isAppelleeRecord" value="${creditInvestigation.appelleeRecord.isAppelleeRecord}" required="true" style="width: 125px;" />
						</td>
					</tr>
					<tr>
						<td align="left">
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordOne0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordOne0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalOne" name="appelleeRecord.accusalOne" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalOne', 'appelleeRecordOne1', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalOne}</textarea> </span>
								</c:otherwise>
							</c:choose>

							<span id="appelleeRecordOne1"></span>
						</td>
						<td>
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordOne2"><input id="appelleeRecordAccusalStatusOne" name="appelleeRecord.accusalStatusOne" type="text" value="${creditInvestigation.appelleeRecord.accusalStatusOne}" style="width: 125px;" /> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordOne2" style="display: none;"><input id="appelleeRecordAccusalStatusOne" name="appelleeRecord.accusalStatusOne" type="text" value="${creditInvestigation.appelleeRecord.accusalStatusOne}" style="width: 125px;" /> </span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="left">
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordTwo0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordTwo0" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="appelleeRecordAccusalTwo" name="appelleeRecord.accusalTwo" rows="3" style="width: 500px;" onclick="textCount('appelleeRecordAccusalTwo', 'appelleeRecordTwo1', 100);" maxlength="100" class="easyui-validatebox" validType="length[0,100]">${creditInvestigation.appelleeRecord.accusalTwo}</textarea> </span>
								</c:otherwise>
							</c:choose>
							<span id="appelleeRecordTwo1"></span>
						</td>
						<td>
							<c:choose>
								<c:when test="${creditInvestigation.appelleeRecord.isAppelleeRecord==0}">
									<span id="appelleeRecordTwo2"><input id="appelleeRecordAccusalStatusTwo" name="appelleeRecord.accusalStatusTwo" type="text" value="${creditInvestigation.appelleeRecord.accusalStatusTwo}" style="width: 125px;" /> </span>
								</c:when>
								<c:otherwise>
									<span id="appelleeRecordTwo2" style="display: none;"><input id="appelleeRecordAccusalStatusTwo" name="appelleeRecord.accusalStatusTwo" type="text" value="${creditInvestigation.appelleeRecord.accusalStatusTwo}" style="width: 125px;" /> </span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							&nbsp;
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center">
							<span style="font-size: 15px; font-weight: 700;">总结</span>
						</td>
					</tr>
					<tr>
						<td align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							<input id="result" name="result" type="text" required="true" value="${creditInvestigation.result}" style="width: 200px;" />
						</td>
					</tr>
				</table>
				<table fit="true" width="100%" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td align="center" style="border-bottom-color: navy; border-bottom-style: solid; border-bottom-width: thin;"></td>
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="riskManagerId" value="${creditInvestigation.riskManagerId}" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查人姓名：&nbsp;&nbsp;
							<input id="riskManagerName" name="riskManagerName" class="easyui-validatebox" required="true" value="${creditInvestigation.riskManagerName}" readonly="readonly" />
							<c:choose>
								<c:when test="${creditInvestigation.investigationDate!=null||creditInvestigation.investigationDate!=''}">
									
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp;	<input id="investigationDate" name="investigationDate" value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />" type="text" class="easyui-datebox" required="true" editable="false" />
								</c:when>
								<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查日期&nbsp;&nbsp;
							<input id="investigationDate" name="investigationDate" value="<fmt:formatDate value='${creditInvestigation.investigationDate}' pattern='yyyy-MM-dd' />" type="text" class="easyui-datebox" required="true" editable="false" />
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>