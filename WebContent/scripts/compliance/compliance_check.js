/**
 * Created by Administrator on 2014-8-5.
 */ 

$(function () {
	//初始化datagrid请求url 获取datagrid数据
	var urlStr = initDataGridUrl();
	//console.info(urlStr);
    var ca_datagrid = $('#ca_list').datagrid(
        {
           // url: serverName + '/ComplianceController/searchComplianceCheck.do?checkType=1&businessStatus=15,29,30', /*1-合规检查*/
            url:urlStr,
        	pagination: true,
            idfield: 'creditapplicationId',
            striped: true,
            singleSelect: true,
            sortName: 'signagreementDate',
            sortOrder: 'desc',
            rownumbers: true,
            loadMsg: '正在加载...',
            onLoadSuccess:function(){
            	//数据加载成功后初始化 分公司名称comboTree信息
            	initBranchComboTree();
            	//根据传入的tab_index的值 显示特定的tab页签
            	if(!($.trim(tab_index) == null || $.trim(tab_index) == "null" || $.trim(tab_index) == "" )){
            		   $('#compliance_check_tab').tabs('select',parseInt(tab_index));            	         		
            	}else{
            		   $('#compliance_check_tab').tabs('select',0);
            	}
            },
            frozenColumns: [
                [
                    {
                        title: '操作',
                        field: 'operateFlag',
                        width: '90',
                        align: 'center',
                        formatter: function (value, row, index) {
                        	 var hrefUrl = 'compliance_check_edit.jsp?creditApplicationId=' + row.CREDITAPPLICATION_ID + '&creditInvestigatioId=' + row.CREDIT_INVESTIGATIO_ID + '&type=commissioner';
                        	 var creditApplicationId = row.CREDITAPPLICATION_ID;
                        	 var creditInvestigatioId = row.CREDIT_INVESTIGATIO_ID;
                        	 if (row.OPERATOR_NAME || row.COMPLIANCE_CHECK_ID) {
                        		 return "<a href='#' onclick='updateComplianceCheckInfo("+creditApplicationId+","+creditInvestigatioId+")'>编辑合规检查</a>";
                            } else {
                                 return "<a href='#' onclick='updateComplianceCheckInfo("+creditApplicationId+","+creditInvestigatioId+")'>合规检查</a>"; 
                            }
                        }
                    }
                ]
            ],
            columns: [
                [
                    {
                        title: '业务单号',
                        field: 'BUSINESS_NUMBER',
                        width: '150'
                    },
                    {
                        title: '借款人',
                        field: 'BORROWER_NAME',
                        width: '90'
                    },
                    {
                        title: '借款金额（元）',
                        field: 'APPLY_LIMIT',
                        width: '90',
                        align: 'right'
                    },
                    {
                        title: '放款日期',
                        field: 'SIGNAGREEMENT_DATE',
                        width: '90',
                        align: 'center'
                    },
                    {
                        title: '客户经理',
                        field: 'LOAN_OFFICER_NAME',
                        width: '110'
                    },
                    {
                        title: '资料提交人',
                        field: 'CREATE_LOAN_OFFICER_NAME',
                        width: '110'
                    },
                    {
                        title: '分公司名称',
                        field: 'COMPANY_NAME',
                        width: '150'
                    },
                    {
                        title: '业务状态',
                        field: 'AUDIT_STATUS',
                        width: '65'
                    },
                    {
                        title: '营业部自查扣分',
                        field: 'SCORE_SELF',
                        width: '115',
                        align: 'center',
                        formatter: function (v) {
                            if (v) {
                                return v.toFixed(1);
                            }
                        }
                    },
                    {
                        title: '内控合规检查扣分',
                        field: 'SCORE_OTHER',
                        width: '115',
                        align: 'center',
                        formatter: function (v) {
                            if (v) {
                                return v.toFixed(1);
                            }
                        }
                    },
                    {
                        title: '风险经理ID',
                        field: 'CREDIT_INVESTIGATIO_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '信贷申请ID',
                        field: 'CREDITAPPLICATION_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '合规检查ID',
                        field: 'COMPLIANCE_CHECK_ID',
                        width: '100',
                        hidden: true
                    },
                    {
                        title: '最新检查人',
                        field: 'OPERATOR_NAME',
                        width: '100',
                        hidden: true
                    }
                ]
            ]
        }
    );

    /*调整 datagrid 高度*/
    var centerPanelHeight = $('#center_panel')[0].clientHeight;
    $('#compliance_check_tab').tabs({
        onSelect: function (title, index) {
            var tab_div1 = $('#tab_div1')[0].clientHeight;
            var tab_div2 = $('#tab_div2')[0].clientHeight;
            if (tab_div1 != 0) {
                ca_datagrid.datagrid('resize', {
                    height: centerPanelHeight - tab_div1 - 110
                });
            } else if (tab_div2 != 0) {
                ca_datagrid.datagrid('resize', {
                    height: centerPanelHeight - tab_div2 - 110
                });
            }
        }
    });
    $('.datagrid-header-inner .datagrid-cell').css("text-align","center");
    /*分公司树形菜单  */
});
//初始化datagrid请求的URL 根据查询条件拼接url 初始化和编辑后回显公用同一个URL
function  initDataGridUrl(){
  var urlStr = serverName + '/ComplianceController/searchComplianceCheck.do?checkType=1&businessStatus=15,29,30';
  if(!($.trim(tab_index) == null || $.trim(tab_index) == "null" || $.trim(tab_index) == "" )){
  	urlStr =urlStr+ "&tab_index="+$.trim(tab_index);
  	if(parseInt($.trim(tab_index)) == 0){
  		 //The  First Tab
  		 if(!($.trim(fuzzy) == null || $.trim(fuzzy) == "null" || $.trim(fuzzy) == "" )){
	    	$("#fuzzy").val($.trim(fuzzy));
	    	urlStr =urlStr+ "&fuzzyValue="+$.trim(fuzzy);
	     }
  	}else{
  		//The Second tab
  		if(!($.trim(branch_name) == null || $.trim(branch_name) == "null" || $.trim(branch_name) == "")){
  		   
  		   urlStr =urlStr+ "&branchName="+$.trim(branch_name);
  	    }
  		if(!($.trim(business_number) == null || $.trim(business_number) == "null" || $.trim(business_number) == "" )){
  		   $("#business_number").val($.trim(business_number));
  	  	   urlStr =urlStr+ "&businessNumber="+$.trim($('#business_number').val());

  	    }
  	    if(!($.trim(material_man) == null || $.trim(material_man) == "null" || $.trim(material_man) =="")){
  	    	$("#material_man").val($.trim(material_man));
  	 	  urlStr =urlStr+ "&materialMan="+$.trim($('#material_man').val());

  	    }
  	    if(!($.trim(loan_begin) == null || $.trim(loan_begin) == "null" || $.trim(loan_begin) == "" )){
  	    	$("#loan_begin").datebox("setValue",$.trim(loan_begin));
  	 	    urlStr =urlStr+ "&loanBegin="+$('#loan_begin').datebox('getValue');
  	    }
//  	    if(!($.trim(loan_end) == null || $.trim(loan_end) == "null")){
//  	    	$("#loan_end").datebox("setValue",$.trim(loan_end));
//  	    	 flag = 'true';
//  	    }
  	  if(!($.trim(loan_end) == null || $.trim(loan_end) == "null" || $.trim(loan_end) == "" )){
	    	$("#loan_end").datebox("setValue",$.trim(loan_end));
	 	    urlStr =urlStr+ "&loanEnd="+$('#loan_end').datebox('getValue');
	    }
  	    if(!($.trim(borrower_man) == null || $.trim(borrower_man) == "null" || $.trim(borrower_man) == "")){
  	    	$("#borrower_man").val($.trim(borrower_man));
  	    	urlStr =urlStr+ "&borrowerMan="+$.trim($('#borrower_man').val());
  	    }
  	}
  }else{
	 return urlStr;
  }
	return urlStr;
};
function initBranchComboTree(){
	var branchs = null;

	if(!($.trim(branch_name) == null || $.trim(branch_name) == "null" || $.trim(branch_name) == "")){
		branchs = $.trim(branch_name).split(','); 
	}
	departmentComboboxTreeWithPanelWidth("branch_name", false, 312,branchs);
}
//模糊查询  获取模糊查询的查询条件 更改dataGrid的查询url(由于涉及到初始化URL参数的问题 所以需要重置URL而不是reload) 获取数据
function fuzzySearch() {
    var v = $('#fuzzy').val();
    //console.info("----------------------------------"+v);
    $('#ca_list').datagrid({url:serverName + '/ComplianceController/searchComplianceCheck.do?checkType=1&businessStatus=15,29,30',
    	queryParams:{fuzzyValue: $.trim(v)},
    	pageNumber:1}); 
}
 
function clearFuzzyBox() {
    $('#fuzzy').val('');
    fuzzySearch();
}
//高级查询  获取高级查询的查询条件 更改dataGrid的查询url(由于涉及到初始化URL参数的问题 所以需要重置URL而不是reload) 获取数据
function search_advanced() {
    var loan_begin = $('#loan_begin').datebox('getValue');
    var loan_end = $('#loan_end').datebox('getValue');
    var tab = $('#compliance_check_tab').tabs('getSelected');
    var tab_index = $('#compliance_check_tab').tabs("getTabIndex",tab);
    if (loan_begin && loan_end && loan_begin > loan_end) {
        $.messager.alert('提示', '开始日期大于结束日期,请重新选择日期');
        return;
    }
   // console.info("loanEnd---------"+$('#loan_end').datebox('getValue'));
    $('#ca_list').datagrid({url:serverName + '/ComplianceController/searchComplianceCheck.do?checkType=1&businessStatus=15,29,30',
    	queryParams:{
            branchName: $.trim($('#branch_name').combotree('getValues')),
            businessNumber: $.trim($('#business_number').val()),
            materialMan: $.trim($('#material_man').val()),
            loanBegin: $('#loan_begin').datebox('getValue'),
            loanEnd: $('#loan_end').datebox('getValue'),
            borrowerMan: $.trim($('#borrower_man').val())
        },
        onLoadSuccess:function(){
        	$('#compliance_check_tab').tabs('select',tab_index);
        },
        pageNumber:1
    });
}

function clear_advanced() {
    $("#branch_name").combotree("setValues", "");
    $("#loan_begin").datebox("clear");
    $("#loan_end").datebox("clear");
    $('#searchForm').form('clear');
    
   search_advanced();
}
function departmentComboboxTreeWithPanelWidth(id, required, panelWidth,branchs) {
    ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function (r) {
        $("#" + id + "").combotree({
            data: r,
            multiple: true,
            editable: false,
            required: required,
            panelWidth: panelWidth,
            onCheck: function (record) {
            },
            onLoadSuccess:function(node, data){
            	 if(branchs != null && branchs != 'null'){
            	 for(i=0;i<branchs.length;i++){
            		 node = $('#branch_name').combotree('tree').tree('find',branchs[i]);
        			 $('#branch_name').combotree('tree').tree('check',node.target);
            	 }
             }
            			
            }
        });
    });
}
//编辑合规检查及 合规检查时 初始化URL参数信息 并触发重定向操作
function updateComplianceCheckInfo(creditApplicationId,creditInvestigatioId){
   	    var hrefUrl = 'compliance_check_edit.jsp?creditApplicationId=' + creditApplicationId + '&creditInvestigatioId=' + creditInvestigatioId + '&type=commissioner';
    	var fuzzy = $.trim($("#fuzzy").val());
    	var branch_name = $.trim($("#branch_name").combotree('getValues'));
    	var business_number = $.trim($("#business_number").val());
    	var material_man = $.trim($("#material_man").val());
    	var loan_begin = $.trim($("#loan_begin").datebox("getValue"));
    	var loan_end = $.trim($("#loan_end").datebox("getValue"));
    	var borrower_man = $.trim($("#borrower_man").val());
    	var tab = $('#compliance_check_tab').tabs('getSelected');
    	var tab_index = $('#compliance_check_tab').tabs("getTabIndex",tab);
    	if(tab_index==0){
    		hrefUrl += '&tab_index='+tab_index+'&fuzzy='+fuzzy;
    	}else{
    		hrefUrl += '&tab_index='+tab_index+'&branch_name='+branch_name+'&business_number='+business_number
    		 +'&material_man='+material_man+'&loan_begin='+loan_begin+'&loan_end='+loan_end+'&borrower_man='+borrower_man; 
    	} 
    	window.location = hrefUrl;
 return;
}