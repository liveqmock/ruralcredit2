$(function(){
	var creditapplicationId = $("#creditapplicationId").val();
	$("#yewu").datagrid({
		url:serverName+"/creditgroup/selectCreditApplicationList.do?creditapplicationId="+creditapplicationId,
		columns:[[
		 {title:"业务编号",
			width:"150",
		 	field:"groupNumber"
		 },
		 	 {title:"当前状态",
		 		width:"150",
			 	field:"auditStatus",
		 		formatter:function(value){
		 			if ($.trim(value) == "00") {
						return "未提交";
					}
					if ($.trim(value) == "01") {
						return "<font>审查中</font>";
					}
					if ($.trim(value) == "02") {
						return "审查通过";
					}
					if ($.trim(value) == "03") {
						return "<font>审查拒绝</font>";
					}
					if ($.trim(value) == "04") {
						return "审批通过";
					}
					if ($.trim(value) == "05") {
						return "<font>审批补充资料</font>";
					}
					if ($.trim(value) == "06") {
						return "撤销";
					}
					if ($.trim(value) == "07") {
						return "<font>撤回</font>";
					}

					if ($.trim(value) == "08") {
						return "推迟放款";
					}
					if ($.trim(value) == "09") {
						return "<font>已付款</font>";
					}
					if ($.trim(value) == "10") {
						return "款项到位";
					}
					if ($.trim(value) == "11") {
						return "<font>已放款登记</font>";
					}
					if ($.trim(value) == "12") {
						return "放款失败-推迟";
					}
					if ($.trim(value) == "13") {
						return "<font>放款确认</font>";
					}
					if ($.trim(value) == "14") {
						return "放款确认退回";
					}
					if ($.trim(value) == "15") {
						return "<font>还款中</font>";
					}
					if ($.trim(value) == "16") {
						return "还款完成";
					}
					if ($.trim(value) == "17") {
						return "<font>审批变更额度</font>";
					}
					if ($.trim(value) == "18") {
						return "<font>审批拒绝</font>";
					}
					if ($.trim(value) == "19") {
						return "提前还款登记";
					}
					if ($.trim(value) == "20") {
						return "<font>提前还款完成</font>";
					}
					if ($.trim(value) == "21") {
						return "<font>放款额度确认</font>";
					}
		 		}},
			 	 {title:"业务类型",
			 		width:"150",
			 		field:"repaymentPlanName",
			 		formatter:function(){
			 			return "农商贷";
			 		}},
			 	 {title:"借款人",
				 	width:"150",
					field:"groupName"},
				 {title:"放款金额",
					width:"150",
					field:"loanAmount"},
				{title:"操作",
					width:"150",
					field:"operateFlag",
					formatter:function(value){
						if ($.trim(value) == "00") {
							return  "";
						} else if ($.trim(value) == "01") {
							return "<a href='javascript:chehui();'>撤回</a>|" +
									"<a href='javascript:audit();'><font color='#4455dd'>审核</font></a>" ;
						} else if ($.trim(value) == "02") {
							return "<a href='javascript:approval();'><font color='#4455dd'>审批</font></a>" ;
						} else if ($.trim(value) == "17") {
							return "<a href='javascript:changeLimit();'><font color='#4455dd'>变更额度</font></a>" ;
						} else if ($.trim(value) == "13") {
							return"<a href='javascript:shoukuan();'>收款登记</a>" ;
						} else if ($.trim(value) == "-1") {
							return "" ;
						} else if ($.trim(value) == "06") {
							return "";
						} else {
							return "" ;
						}
				}}
		 ]]
	});
	$("#shenpi").datagrid({
		url:"scripts/data/grid.json",
		columns:[[
		 {title:"审批结果",
			 width:"150",
			 formatter:function(){
		 			return "审批通过";
		 		},
		 	field:"title"},
		 
		 	{title:"审批金额",
				 width:"150",
				 formatter:function(){
			 			return "5000";
			 		},
			 	field:"description"},
		 	
			 	 {title:"审批意见",
			 		width:"150",
				 	field:"title"},
					 {title:"放款日期",
				 		width:"150",
					 	field:"link",
				 		 formatter:function(){
					 			return "2012-09-05";
					 		}
			 		},
			 	 {title:"支付方式",
				 		width:"150",
					 	field:"title"},
					 	 {title:"支付账号",
					 		width:"150",
						 	field:"title"}
		 ]]
	});
	$("#borrower").datagrid({
		url:"scripts/data/grid.json",
		columns:[[
		 {title:"姓名",
			 width:"150",
		 	field:"title"},
		 	 {title:"性别",
		 		width:"150",
			 	field:"title"},
			 	 {title:"身份证",
			 		width:"150",
				 	field:"title"},
			 	 {title:"联系电话",
				 		width:"150",
					 	field:"title"},
					 	 {title:"借款用途",
					 		width:"150",
						 	field:"title"},
						 	 {title:"紧急联系人",
						 		width:"150",
							 	field:"title"},
							 	 {title:"紧急联系人电话",
							 		width:"150",
								 	field:"title"},
								 	 {title:"申请金额",
								 		width:"150",
									 	field:"title"}
		 ]]
	});
	$("#danbao").datagrid({
		url:"scripts/data/defaultList.json",
		 view: detailview,
		 detailFormatter:function(index,row){  
             return '<div style="padding:2px"><div id="ddv-' + index + '">'+
             		'</div></div>';  
         },
         onExpandRow: function(index,row){  
        	 
         },
		columns:[[
		          {title:"姓名",
		        	  width:"150",
		  		 	field:"address"},
		  		 	 {title:"性别",
		  		 		width:"150",
		  			 	field:"address"},
		  			 	 {title:"身份证",
		  			 		width:"150",
		  				 	field:"address"},
		  			 	 {title:"联系电话",
		  				 		width:"150",
		  					 	field:"address"}
		 ]]
	});
	$("#returnPlanList").datagrid({
		url:"scripts/data/loanList.json",
		columns:[[
		          {title:"日期",
		        	width:"150",
		  		 	field:"creditapplicationId"},
		  		 	 {title:"应还本金",
		  		 		width:"150",
		  			 	field:"creditapplicationId"},
		  			 	 {title:"应还利息",
		  			 		width:"150",
		  				 	field:"creditapplicationId"},
		  			 	 {title:"应还管理费",
		  				 		width:"150",
		  					 	field:"creditapplicationId"},
		  				 	{title:"应还违约金",
		  				 		width:"150",
		  					 	field:"creditapplicationId"},
		  				 	{title:"应还滞纳金",
		  				 		width:"150",
		  					 	field:"creditapplicationId"},
		  				 	{title:"已还本金",
		  				 		width:"150",
		  					 	field:"creditapplicationId"},
		  				 	{title:"已还利息",
		  				 		width:"150",
		  					 	field:"creditapplicationId"},
		  					 	{title:"已还管理费",
			  				 		width:"150",
			  					 	field:"creditapplicationId"},
			  					 	{title:"已还违约金",
				  				 		width:"150",
				  					 	field:"creditapplicationId"},
				  					 	{title:"已还滞纳金",
					  				 		width:"150",
					  					 	field:"creditapplicationId"},
					  					 	{title:"状态",
						  				 		width:"150",
						  					 	field:"creditapplicationId"},
		  				 	
		  				 	{title:"操作",
		  				 		width:"150",
		  					 	field:"creditapplicationId"}
		 ]]
	});
	$("#businesStrack").datagrid({
		url:"scripts/data/loanList.json",
		columns:[[
		          {title:"时间",
		        	  width:"150",
		  		 	field:"address"},
		  		 	 {title:"操作人",
		  		 		width:"150",
		  			 	field:"address"},
		  			 	 {title:"结果",
		  			 		width:"150",
		  				 	field:"address"}
		 ]]
	});
});