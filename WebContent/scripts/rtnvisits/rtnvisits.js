$(function(){
	window.onresize = function(){
	    setTimeout(function(){
	  	  $('#rtnlist').resizeDataGrid(130, 20, 0, 0);
	    }, 500);
	};
				$("#rtnlist").datagrid({
					nowrap:true,
					striped:true,
					title:"回访列表",
	    	        pagination:true,
	    	        singleSelect:true,
					url:serverName+"/ReturnVisit/selectReturnVisitList.do",
					rownumbers: true,
					columns:[[        
					      	    {title:'客户回访情况',colspan:5},
					      	    {title:'借款人近况',colspan:7},
					      	    {title:'担保人近况',colspan:4},
					      	    {title:'结论',colspan:1}
				      	    ],[
					      	 	{field:'returnvisitId',					title:'',width:80,hidden:true},
							    {field:'customManager', 				title:'客户经理',width:80 },
							    {field:'borrowerName', 					title:'客户姓名',width:80 },
							    {field:'groupNumber',					title:'业务单号',width:110 },
							    {field:'contactTime',					title:'回访日期',width:80 },
							    {field:'clienteleAttitude' , 			title:'客户态度',width:80 },
							    {field:'contactChangeDetails',			title:'联系方式变更',width:110 },
							    {field:'addressChangeDetails',			title:'家庭住址变更',width:110 },
							    {field:'workingChangeDetails',			title:'工作情况变更',width:110 },
							    {field:'monthIncomeDetails'	,			title:'当月收入情况',width:110 },
							    {field:'vastexpenseReasonDetails',		title:'大笔开支情况',width:110 },
							    {field:'paypressureReasonDetails',		title:'家庭成员情况',width:110 },
							    {field:'familymembersChangeDetails'	,	title:'是否有还款压力',width:110 },
							    {field:'otherFamilystatusDetails',		title:'家庭状况是否稳定',width:120 },
							    {field:'otherWorkingDetails',			title:'工作/生意有无重大波动',width:140 },
							    {field:'otherVastexpenseDetails',		title:'有无大笔开支',width:110 },
							    {field:'otherPaypressureDetails',		title:'是否有还款压力',width:110 },
							    {field:'keytoFollowupDetails',			title:'是否重点跟进',width:110 }
		    	            ]]
				});		
				 $('#rtnlist').resizeDataGrid(130, 20, 0, 0);
			});

function searchReturnVisit(){
	$("#rtnlist").datagrid("load",
			{contactTime:$("#returnTime").datebox("getValue"),
			groupNumber:$("#groupNumber").val(),
			customManager:$("#managerMan").val()}
	);
}


function clearReturnVisit(){
		 $("#returnTime").datebox("clear");
		 $("#groupNumber").val("");
		 $("#managerMan").val(""); 
}

