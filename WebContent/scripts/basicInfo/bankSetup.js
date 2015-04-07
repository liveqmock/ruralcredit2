

	$(function(){
	//easyui-validatebox拓展验证
	$.extend($.fn.validatebox.defaults.rules, {
		//验证数字
		number : {
			validator : function(value, param) {
				if (value) {
					return /^[1-9][0-9]*(\.[0-9]+)?$/.test(value);
				} else {
					return true;
				}
			},
			message : '只能输入非0开头的数字.'
		},
		date : {
			validator : function(value, param) {
				if (value) {
					return /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/
							.test(value);
				} else {
					return true;
				}
			},
			message : '请选择日期.'
		},
		select : {
			validator : function(value, param) {
				if (value == "请选择") {
					return false;
				} else {
					return true;
				}
			},
			message : '请选择日期.'
		}
	});
	
});

	$(function(){
		var province = $("#provinceId").combobox({
			//required : true,
			editable:false,
			url : serverName+'/NSC/list.do',
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			delay : 500,
			width:'150',
			value : '',
			onSelect:function(value){
				$("#cityId").combobox({
					editable:false,
					url : serverName+'/NSC/listCity.do?data='+value.cityCode,
					textField : 'cityName',
					valueField : 'cityCode',
					mode : 'remote',
					delay : 500,
					width:'150',
					value : '',
					onSelect:function(value){
						
						$("#districtId").combobox({
							editable:false,
							url : serverName+'/NSC/listCity.do?data='+value.cityCode,
							textField : 'cityName',
							valueField : 'cityCode',
							mode : 'remote',
							width:'150',
							delay : 500,
							value : ''
						});
					}
				});
			}
		});
		
		$("#bankNum").combobox({
			url:serverName+"/accountInfo/selectBank.do",
			textField:"codeVlue",
			valueField:"codeKey",
			width:130,
			panelHeight : "auto",
			delay : 500,
			editable:false,
			mode: "remote",
			value:""
		});
	});

	function addAccountInfo(){
		url=serverName+"/accountInfo/addAccountInfo.do";
		$("#bankRank").val($("#bankNum").combobox("getText"));
		if($("#accountInfo").form("validate")){
			ajaxSubmit(url,$("#accountInfo").serializeObject(),recfunc);
		}else{
			$.messager.show({
				msg:"请将数据填写完整",
				title:"消息"
			});
		}
	}

	function recfunc(result){
		
		if(result.success){
			$("#accountInfo").form("clear");
			$.messager.show({
				msg:"保存成功!",
				title:"消息"
			});
		}else{
			$.messager.show({
				msg:"保存失败!",
				title:"消息"
			});
		}
	}

	function cancel(){
		$("#accountInfo").form("clear");
	}
	
