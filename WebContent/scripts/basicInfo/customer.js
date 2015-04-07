	$(function() {
		window.onresize = function(){
			setTimeout(function(){
				$('#list').resizeDataGrid(130, 20, 0, 0);
			}, 500);
		};
		var dic = new DataDictionary();
		dic.addDic("gender","gender");
		dic.addDic("highestEducation","highestEducation");
		dic.addDic("maritalStatus","maritalStatus");
		dic.addDic("national","national");
		dic.addDic("customerType","customerType");
		dic.dicAjax();

		 var editrow = undefined;
		 
		 $("#dialog").dialog({
			 height:370,
			 closed:true,
			 modal:true,
			 draggable:false,
			 title:"客户信息",
			 buttons:"#buttons"
		 });
		$("#list").datagrid({
			title:"客户基本信息",
			url : serverName+'/customer/list.do',
			idfield : 'customerBasicId',
		// nowrap:false,
			singleSelect : true,
			striped : true,
			pagination: true,
			frozenColumns : [ [ { field : 'operation', width : 150, title:"操作", formatter:operation
							} ] ],
			columns : [ [   { title : '借款人姓名', field : 'name', width : 130  } ,
			     { title : '证件类型', field : 'credentialsType', hidden:true, width : 150,
				   formatter:function(value){
				        if(value==1){
				                return '身份证';
						     }  
						        if(value==2){
					                return '驾照';
					        }  
						        if(value==3){
					                return '护照';
					        }  
						        if(value==4){
					                return '户口本';
					        }  
						        if(value==5){
					                return '临时身份证';
					        }  
				        }
			}, { title : '身份证号', field : 'credentialsNumber', width : 200 
			}, { title : '性别', field : 'gender', width : 80, formatter : function(value){
					if(value == "0"){
						return "男";
					}
					if(value == "1"){
						return "女";
					}
				}
			} , { title : '电话', field : 'mobilephone', width : 150 
			} , { title : '操作人姓名', field : 'operateName', width : 170 
			} , { title : '操作人id', field : 'operateId', width : 100, hidden : true 
			} ,{ title : '固话', field : 'telephone', width : 150, hidden:true 
			},{ title : '住址', field : 'presentAddress', width : 300 
			},{ title : '借款人状态', field : 'auditStatus', width : 140,hidden:true 
			},{ title : '是否加入黑名单', field : 'blackFlag', width : 100,formatter:function(value){
				if(value != "" && value != null){
					return "是";
				}else{
					return "不是";
				}
			}
			},{ title : '借款人状态', field : 'auditStatusShow', width : 100
			},{ title : '民族', field : 'national', width : 100, hidden: true 
			},{ title : '民族', field : 'nationalDetail', width : 100 
			},{ title : '户籍地址', field : 'residenceAddress', width : 100, hidden: true 
			},{ title : '个人收入', field : 'personIncome', width : 100, hidden: true 
			},{ title : '最高学历', field : 'highestEducation', width : 100, hidden: true 
			},{ title : '是借款人', field : 'customerType', width : 100,
				formatter:function(value){
					if(value == '0'){
						return "是";
					}else{
						return "不是";
					}
				}
			},{ title : '是担保人', field : 'guaranor', width : 100,
				formatter:function(value){
					if(value == '0'){
						return "是";
					}else{
						return "不是";
					}
				}
			},{ title : '最高学历',  field : 'highestEducationDetail', width : 100 
			},{ title : '邮寄地址', field : 'residenceAddress', width : 100, hidden: true 
			},{ title : '备注', field : 'remark', width : 100, hidden: true 
			},{ title : '婚姻状态', field : 'maritalStatus', width : 100, hidden: true 
			},{ title : '婚姻状态', field : 'maritalStatusDetail', width : 100 
			},{ field : 'vallageId', width : 100, title:"村id", hidden:true
			}] ],
			
			rownumbers : true,
			loadMsg : '请稍后,数据查询中......',

			toolbar:[{
				id:'customeradd',
				text:'添加',
				iconCls:'icon-addOne',
				handler:function(){
					newCustomer();
				}
			} 
			],
			onLoadSuccess:function(data){}

			/*
			 * , onDblClickRow : function(rowIndex, rowData) { if( editrow != undefined){ $("#list").datagrid('endEdit',editrow);
			 * $("#list").datagrid('unselectAll'); editrow =undefined; $("#list").datagrid('beginEdit',rowIndex); editrow = rowIndex; }else{
			 * 
			 * $("#list").datagrid('beginEdit',rowIndex); editrow = rowIndex; } },
			 * 
			 * //开始 onAfterEdit : function(rowIndex, rowData, changes) { var inserted = $("#list").datagrid('getChanges','inserted'); var updata =
			 * $("#list").datagrid('getChanges','updated'); var realurl = null; if(inserted[0] == rowData){ realurl = "/ruralcredit2/customer/add.do"; }
			 * if(updata[0] == rowData){ realurl = "/ruralcredit2/customer/update.do"; }
			 * 
			 * $.ajax({ url:realurl, data : rowData, cache : false, dataType : "json", type: "post", success : function(r) { if (r.success) { editrow =
			 * undefined; $("#list").datagrid('reload',rowIndex); } else { $("#list").datagrid('beginEdit',rowIndex); $.messager.show({ msg : '保存失败!',
			 * title : '提示' }); } } }); }
			 */
				
				// 结束
				
		});
		

		 // 第一次加载时自动变化大小
		 $('#list').resizeDataGrid(130, 20, 0, 0);
		// 村镇联动下拉列表
		// showComboboxTown("countyId","townId","villageId");
		 
		 /** 初始化黑名单对话框 郝强上传**/
		 $("#blacklistDialog").dialog({
				modal : true,
				title : "黑名单",
				maximizable : false,
				cache : false,
				closed : true,
				buttons : [ {
					id : "rdButton1",
					text : "保存",
					iconCls : "icon-ok",
					handler : function() {
						saveBlacklist();
					}

				}, {
					id : "rdButton2",
					text : "取消",
					iconCls : "icon-cancel",
					handler : function() {
						$("#blacklistDialog").dialog('close');
					}
				} ],
				onClose : function() {
					$("#blackForm").form('clear');
					$("#list").datagrid("reload");
				},
				onOpen:function(){
					$("#blackForm").form('validate');
				}

			});
	});
	var url;
	
	function returnVisit(){
		var row = $('#list').datagrid('getSelected');
		 $("#returnVisitDiv").dialog({
			  height:450,
			  width:900,
			  closed:true,
			  title:"回访调查表",
			  draggable:false,
			  modal : true,
			  href: serverName+"/jsp/rc/basicInfo/returnVisit.jsp",
			  onLoad:function(){$('#visitForm').form('validate');}
		  });
		  $("#returnVisitDiv").dialog("open");
		  setTimeout(function(){
			  $("#borrowerName").val(row.name);
			  $("#customManager").val(row.operateName);
			  $("#borrowerId").val(row.customerBasicId);
		  }, 500);
		
		  
	}
	
	function deleteCustomer(){
		 var row = $('#list').datagrid('getSelections');
		 var customerIds = null;
		 for(var i= 0 ;i<row.length;i++ ){
			 customerIds = customerIds+row[i].customerBasicId+",";
		 }
		 customerIds = customerIds.substring(0,customerIds.length-1);
		  if(row){
		  		$.messager.confirm('消息','确定要删除吗？',function(b){
		  			if(b){
						url=serverName+'/customer/delete.do';
						ajaxSubmit(url,{customerIds:customerIds},deleteRetFunc);
		  			}
		  		});
				$("#list").datagrid('unselectAll');
			}else{
				$.messager.show({
					msg:'请选择一条记录！',
					title:'消息',
					icon:'warning'
				});
			}	
	}
	function newCustomer(){
		
		$("#dialog").dialog('open');
		$("#credentialsNumber1").removeAttr("readonly");
		$("#fm").form('clear');
		$("#credentialsType").val("1");
		$('#fm').form('validate');
		// 村镇联动下拉列表
		cityComboboxPublic("sheng","shi","prefecture1","town1","village1","","addressOther","presentAddress","residenceAddress");
		url=serverName+'/customer/add.do';			
	}
	function updataCustomer(){
		  var row = $('#list').datagrid('getSelected');
					$("#dialog").dialog('open');
//					// 村镇联动下拉列表
					var villageId = row.vallageId; 
					var address  =  row.presentAddress;
					if(address != null){
						var addressArray = address.split("-");
						$("#addressOther").val(addressArray[addressArray.length-1]);
					}
					url=serverName+'/customer/update.do';
					$("#credentialsNumber1").attr("readonly","readonly");
					$("#fm").form('load',row);

		var province = "sheng";
		var city = "shi";
		var county = "prefecture1";
		var town = "town1";
		var village = "village1";
		var address = "presentAddress";
		var detail = "addressOther";
		var address2 = "residenceAddress";
		
		var homeAddress = $("#"+address).val();
		if(homeAddress != undefined){
			var homeAddressList = homeAddress.split("-");
			$("#"+detail ).val(homeAddressList[homeAddressList.length-1]);
		}
		var villageHome = $("#"+village).combobox("getValue");
		var townHome;
		var countyHome;
		var cityHome;
		var provinceHome;
		if(villageHome != null && villageHome != ""){
			ajaxSubmit(serverName+"/NSC/selectByVallageId.do",{VallageId:villageId},function(resultMap){
							$("#" + province).combobox(
									{
										editable: false,
									//	url : serverName + '/NSC/list.do',
										data: resultMap.nationalStandardCodeSheng,
										textField : 'cityName',
										valueField : 'cityCode',
										mode :'remote',
										delay :500,
										width :'150',
										value:resultMap.nationalStandardCodeShi.parentId,
										onSelect: function(value) {
											$("#" + city).combobox("clear");
											$("#" + county).combobox("setValue","");
											$("#"+town).combobox("setValue","");
											$("#"+village).combobox("setValue","");
											addAddressPublic(true,province, city, county,town,village,detail,address,address2);
											$("#" + city).combobox(
											{
											editable : false,
											url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
											textField : 'cityName',
											valueField : 'cityCode',
											mode : 'remote',
											delay : 500,
											width : '150',
											value:""
											});
										}
									});
							$("#" + county).combobox({
								//url : serverName + '/NSC/listCity.do?parentId=' + cityHome,
								data : resultMap.nationalStandardCodeQuList,
								textField : 'cityName',
								valueField : 'cityCode',
								mode : 'remote',
								delay : 500,
								width : '150',
								//value : countyHome,
								value : resultMap.nationalStandardCodeQu.cityCode,
								onSelect:function(countryNSC){
									$("#"+village).combobox("setValue","");
									$("#"+town).combobox("setValue","");
									addAddressPublic(true,province, city, county,town,village,detail,address,address2);
									$("#"+town).combobox({
										url : serverName+ '/town/list.do?parentId='+countryNSC.cityCode,
										textField : 'townshipinfName',
										valueField : 'townshipinfoid',
										mode : 'remote',
										delay : 500,
										width : '150',
										value:""
									});
								}
							});
							$("#" + city).combobox({
								editable : false,
								//url : serverName + '/NSC/listCity.do?parentId=' + provinceHome,
								data :  resultMap.nationalStandardCodeShiList,
								textField : 'cityName',
								valueField : 'cityCode',
								mode : 'remote',
								delay : 500,
								width : '150',
								//value : cityHome,
								value : resultMap.nationalStandardCodeShi.cityCode,
								onSelect:function(cityNSC){
									$("#" + county).combobox("setValue","");
									$("#"+town).combobox("setValue","");
									$("#"+village).combobox("setValue","");
									addAddressPublic(true,province, city, county,town,village,detail,address,address2);
									$("#" + county).combobox({
										editable : false,
										url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										delay : 500,
										width : '150',
										value:""
									});
								}
								
							});
							
							$("#"+town).combobox({
								//url : serverName+ '/town/list.do?parentId='+countyHome,
								data: resultMap.townTownList,
								textField : 'townshipinfName',
								valueField : 'townshipinfoid',
								mode : 'remote',
								delay : 500,
								width : '150',
								//value : townHome,
								value : resultMap.townTown.townshipinfoid,
								onSelect:function(townTown){
									$("#"+village).combobox("setValue","");
									addAddressPublic(true,province, city, county,town,village,detail,address,address2);
									$("#"+village).combobox({
										url : serverName+ '/town/list.do?parentId='+townTown.townshipinfoid,
										textField : 'townshipinfName',
										valueField : 'townshipinfoid',
										mode : 'remote',
										delay : 500,
										width : '150',
										value:""
									});
								}
							});
							$("#"+village).combobox({
								//url : serverName+ '/town/list.do?parentId='+townHome,
								data : resultMap.townVallageList,
								textField : 'townshipinfName',
								valueField : 'townshipinfoid',
								mode : 'remote',
								delay : 500,
								width : '150',
								value : villageHome,
								onSelect:function(){
									addAddressPublic(true,province, city, county,town,village,detail,address,address2);
								}
							});
			});
		}
	}
	
	function addAddress(paramValidate){
		if(paramValidate){
		var provinceHome=$("#sheng").combobox("getText");
		var cityHome=$("#shi").combobox("getText");
		var countyHome=$("#prefecture1").combobox("getText");
		var townHome=$("#town1").combobox("getText");
		var villageHome=$("#village1").combobox("getText");
		var detailHome=$("#addressOther").val();
		
		var homeAddress = provinceHome+"-"+cityHome+"-"+countyHome+"-"+townHome+"-"+villageHome+"-"+detailHome;
		$("#presentAddress").val(homeAddress);
		$("#presentAddress").validatebox("validate");
		$("#residenceAddress").val(homeAddress);
		$("#residenceAddress").validatebox("validate");
		}
	}

	function savaCustomer(){
					$("#savabutton").linkbutton("disable");
				  if($('#fm').form('validate')){
		            	ajaxSubmit(url,$('#fm').serializeObject(),saveRetFunc);
		            } else {
		            	$("#savabutton").linkbutton("enable");
		                $.messager.alert("消息","请将数据填写完整！");
		            }
	}
	
	function saveRetFunc(result){
		if(result.success){
			$("#fm").form('clear');
			$("#dialog").dialog('close');
			$("#savabutton").linkbutton("enable");
			$("#list").datagrid('load');
			$.messager.show({
				msg:'数据保存成功！',
				title:'消息'
			});
		}else{
			$("#savabutton").linkbutton("enable");
			$.message.alert('错误',result.message);
		}
	}
	function deleteRetFunc(result){
		if(result.success){
			$("#list").datagrid('reload');
			$.messager.show({
				msg:'删除成功！',
				title:'消息'
			});
		}else{
			$.messager.alert('错误',result.msg);
		}
	}
	
	function search12(){
		$("#list").datagrid("load",{
			name:$("#name").val(),
			credentialsNumber:$("#credentialsNumber").val(),
			telephone:$("#telephone").val(),
			customerType:$("#customerType").combobox("getValue")
// presentAddress:address
		});
	}
	
	function clear1(){
		 $("#name").val("");
		 $("#credentialsNumber").val("");
		 $("#telephone").val("");
		 $("#customerType").combobox("setValue","");
	}
	

// 取消按钮
	function cencleDialog(){
		$("#dialog").dialog('close');
		$("#fm").form('clear');
	}

	/**
	 * 通过身份证判断是男是女
	 * 
	 * @param idCard
	 *            15/18位身份证号码
	 * @return '0'-男 '1'-女、
	 */ 
	function maleOrFemalByIdCard(IdCard){ 
	    if(IdCard.length==15){  
	        if(IdCard.substr(14,1)%2==0){  
	            return '1';  
	        }else{  
	            return '0';  
	        }  
	    }else if(IdCard.length ==18){ 
	        if(IdCard.substr(16,1)%2==0){  
	            return '1';  
	        }else{  
	            return '0';  
	        }  
	    }
	}
	
	function validIDNumber(){
		var credentialsNumber = $("#credentialsNumber1").val();
		var customerBasicId = $("#customerBasicId").val();
			ajaxSubmit(serverName+"/customer/validateCredentialsNumber.do",{credentialsNumber:credentialsNumber,customerBasicId:customerBasicId},function(result){
				if(result != null && result != "" ){
					$.messager.show({
						msg:"该客户已经存在！",
						title:"消息"
					});
					$("#credentialsNumber1").val("");
					$("#credentialsNumber1").focus();
					return;
				}
				var sex=maleOrFemalByIdCard(credentialsNumber);
				$("#gender").combobox("setValue",sex);
    		});
	}
	function searchFuzzy(){
		$("#list").datagrid("load",{
			fuzzyValue:$("#fuzzyValue").val()
		});
	}

	function clearFuzzy(){
		$("#fuzzyValue").val("");
	}
	
	
	/** 郝强添加 加入黑名单功能* */
	function blacklisted(name,credentialsNumber){
		if(name=="null"||name==null){
			name="";
		}
		$("#blackName").val(name);
		$("#blackCredentialsNumber").val(credentialsNumber);
		$("#blacklistDialog").dialog("open");
	}
	function textCount(textId, htmlId, max) {
		$("#" + textId).attr("readonly", false);
		if ($("#" + textId).val() == "点击添加备注……") {
			$("#" + textId).val("");
		}
		$("#" + textId).keyup(function() {
			var maxl = max;
			var tishi = "还可以输入" + maxl + "个字";
			$("#" + htmlId).html(tishi);
			var xianyou = $(this).val().length;
			var keyi = maxl - xianyou;
			var tishi = "还可以输入" + keyi + "个字";
			if (xianyou > (max - 1)) {
				var tishi = "还可以输0个字";
				$("#" + htmlId).css({
					"color" : "red"
				});
				var s = $("#" + textId).val().substr(0, 100);
				$("#" + textId).val();
			} else {
				$("#" + htmlId).css({
					"color" : "#000"
				});
			}
			$("#" + htmlId).html(tishi);
		});
	}
	function checkTextArea(textId, htmlId) {
		if ($("#" + textId).val() == "" || $("#" + textId).val() == null) {
			$("#" + textId).val("请添加加入黑名单原因……");
			$("#" + textId).attr("style", "width: 100%; font-style: italic; color: gray;");
			$("#" + textId).attr("readonly", "readonly");
			$("#" + htmlId).html("");
		}
	}
	//保存黑名单
	function saveBlacklist(){
		if($("#blackForm").form('validate')){
			$("#rdButton1").linkbutton("disable");
			$("#rdButton2").linkbutton("disable");
			ajaxSubmit(serverName+"/blacklistController/blacklisted.do",$("#blackForm").serialize(),function(r){
				$("#rdButton1").linkbutton("enable");
				$("#rdButton2").linkbutton("enable");
				if(r.success){
					$("#blacklistDialog").dialog('close');
						$.messager.show({
							showType : "show",
							timeout : 5000,
							title : "消息",
							msg : "保存黑名单成功！"
						});
				}else{
					$.messager.alert("消息",r.msg,"error",function(){
						$.messager.show({
							showType : "show",
							timeout : 5000,
							title : "消息",
							msg : "保存黑名单失败！"
						});
					});
				}
			});
		}else {
			$.messager.alert("消息","请将数据填写完整！","warning");
		}
	}