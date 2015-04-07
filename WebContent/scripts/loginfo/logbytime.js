$(function(){
				$('#groupinfo').datagrid({
					nowrap:true,
					striped:true,
					url:'<%=basePath%>jsp/rc/loginfo/datagrid_logbytime.json',
					columns:[[
			      	    {field:'number',title:'小组编号',width:100,align:'center'},
			      	    {field:'repaymenplan',title:'还款方案',width:100,align:'center'},
			      	    {field:'leadername',title:'组长姓名',width:100,align:'center'},
			      	    {field:'custommanager',title:'客户经理',width:100,align:'center'},
			      	    {field:'status',title:'审批状态',width:100,align:'center'},
			      	    {field:'operattime',title:'创建时间',width:200,align:'center'}
	    	        ]],
	    	       
	    	        rownumbers:false,
	    	        singleSelect:true
				});
				
				$('#memberinfo').datagrid({
					nowrap:true,
					striped:true,
					url:'<%=basePath%>jsp/rc/loginfo/datagrid_logbytime.json',
					columns:[[
			      	    {field:'acceptname',title:'申请人姓名',width:100,align:'center'},
			      	    {field:'identifyno',title:'身份证号',width:100,align:'center'}
	    	        ]],
	    	       
	    	        rownumbers:false,
	    	        singleSelect:true
				});
				
				$('#loglist').datagrid({
					nowrap:true,
					striped:true,
					url:'<%=basePath%>jsp/rc/loginfo/datagrid_logbytime.json',
			columns : [ [ {
				field : 'logtime',
				title : '日志记录时间',
				width : 100,
				align : 'center'
			}, {
				field : 'operatefunction',
				title : '操作业务功能',
				width : 100,
				align : 'center'
			}, {
				field : 'operator',
				title : '操作人',
				width : 100,
				align : 'center'
			}, {
				field : 'remark',
				title : '备注',
				width : 100,
				align : 'center'
			} ] ],
			rownumbers : false,
			singleSelect : true
		});
	});

	function timeortype() {
		$("#logtab").tabs("add",{
			title:"按类型显示",
			href:"<%=basePath%>jsp/rc/loginfo/logbytype.jsp",
			closable:true
		});
	}