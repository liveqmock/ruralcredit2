var industryCategoryAll;
var cashIncomeFamilyTotal;
var highDangerCustomer;
var sourceIncomeChangedContentTotal;
var flag1 = false;
var flag2 = false;
var flag3 = false;

/*-001 借款用途*/
//显示借款用途对话框
function showborrowUse(valueField,textField){
	//console.info("----ss---");
	if(flag1 && isDisplayRadio('loanUse')){
		var table = document.getElementById("tableUse");
		var pchildren = table.childNodes;
		//清空表中的行和列
		for(var a=pchildren.length-1; a>=0; a--){
			table.removeChild(pchildren[a]);
		}
		//判断颜色的
		var m = 0;
		for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
			var  index = industryCategoryAll.industryCategoryTotal[k].codaTableId;
			var  name = "industryCategoryTotal"+index;
			var  objectName = "industryCategoryAll."+name;
			var object = (eval("("+objectName+")"));
			var n = 0;
			var length = 0;
			if(object.length%3 > 0){
				length = parseInt(object.length/3)+1;
			}else{
				length = parseInt(object.length/3);
			}
			for(var i = 0 ; i < length;i++){
				m = m+1;
				var tr=document.createElement("tr");
				if(k % 2 == 0){
					//tr.setAttribute('bgcolor','#DDE4EE');
				}
				if(i == 0){
					var cell1=document.createElement("td");
					cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ industryCategoryAll.industryCategoryTotal[k].codeVlue+"</font>";
					cell1.setAttribute("rowspan",length);
					cell1.setAttribute("align","center");
					cell1.setAttribute("width","150");
					cell1.setAttribute("bgcolor","#DDE4EE");
					tr.appendChild (cell1);
				}


				for(var j = 0 ; j < 3 ;j++){
					var cell=document.createElement("td");
					cell.setAttribute("width","160");
					n = n+1;
					if(m % 2 == 0){
						cell.setAttribute("bgcolor","#DDE4EE");
					}
					if(object[n-1] != undefined){
						var key = object[n-1].codeKey;
						var value = object[n-1].codeVlue;
						var arrayObj = new Array(key,value);
						var obj = new Object();
						obj["codeKey"]=object[n-1].codeKey;
						obj["codeValue"]=object[n-1].codeVlue;
						cell.innerHTML =
							"<a href='javaScript:addBorrow(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+valueField+"\",\""+textField+"\");'>"+object[n-1].codeVlue+"</a>";
					}
					tr.appendChild (cell);
				}
				table.appendChild (tr);
			}
		}
		$("#windowBorrowUse").window({
			width:650,
			height:30*(Math.ceil(industryCategoryAll.length/3)),
			title:"借款行业用途",
			minimizable:false,
			maximizable:false,
			collapsible:false,
			modal:true
		});
	}
}


//借款用途行业
function addBorrow(){
	$("#"+arguments[3]).val(arguments[1]);
	$("#"+arguments[3]).validatebox("validate");
	$("#"+arguments[2]).val(arguments[0]);
	$("#"+arguments[2]).focus();
	$("#windowBorrowUse").window("close");
}



////选择家庭收入
//function selectProject(projectName,projectCodeKey){
//	if(flag2 && isDisplay($('#familyIncome'))){
//		var table = document.getElementById("tableProject");
//		var pchildren = table.childNodes;
//		//清空表中的行和列
//		for(var a=pchildren.length-1; a>=0; a--){
//			table.removeChild(pchildren[a]);
//		}
//		var n = 0;
//		//判断颜色的
//		var m = 0;
//		for(var k = 0 ; k < cashIncomeFamilyTotal.cashIncomeFamilyTotal.length;k++){
//			var  index = cashIncomeFamilyTotal.cashIncomeFamilyTotal[k].codaTableId;
//			var  name = "cashIncomeFamilyTotal"+index;
//			var  objectName = "cashIncomeFamilyTotal."+name;
//			var object = (eval("("+objectName+")"));
//			var n = 0;
//			var length = 0;
//			if(object.length%3 > 0){
//				length = parseInt(object.length/3)+1;
//			}else{
//				length = parseInt(object.length/3);
//			}
//			for(var i = 0 ; i < length;i++){
//				m = m+1;
//				var tr=document.createElement("tr");
//				if(k % 2 == 0){
//					//tr.setAttribute('bgcolor','#DDE4EE');
//				}
//				if(i == 0){
//					var cell1=document.createElement("td");
//					cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ cashIncomeFamilyTotal.cashIncomeFamilyTotal[k].codeVlue+"</font>";
//					cell1.setAttribute("rowspan",length);
//					cell1.setAttribute("align","center");
//					cell1.setAttribute("width","150");
//					cell1.setAttribute("bgcolor","#DDE4EE");
//					tr.appendChild (cell1);
//				}
//
//
//				for(var j = 0 ; j < 3 ;j++){
//					var cell=document.createElement("td");
//					cell.setAttribute("width","160");
//					n = n+1;
//					if(m % 2 == 0){
//						cell.setAttribute("bgcolor","#DDE4EE");
//					}
//					if(object[n-1] != undefined){
//						var key = object[n-1].codeKey;
//						var value = object[n-1].codeVlue;
//						var arrayObj = new Array(key,value);
//						var obj = new Object();
//						obj["codeKey"]=object[n-1].codeKey;
//						obj["codeValue"]=object[n-1].codeVlue;
//						cell.innerHTML =
//							"<a href='javascript:;' onclick='javascript:addProject(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+projectName+"\",\""+projectCodeKey+"\")'>"+obj.codeValue+"</a>";
//					}
//					tr.appendChild (cell);
//				}
//				table.appendChild (tr);
//			}
//		}
//		$("#windowProject").window({
//			width:550,
//			height:25*(Math.ceil(cashIncomeFamilyTotal.length/3)),
//			title:"新家庭收入来源",
//			minimizable:false,
//			maximizable:false,
//			collapsible:false,
//			closed:false,
//			modal:true
//		});
//	}
//
//}
//
//function addProject(){
//	$("#"+arguments[2]).val(arguments[1]);
//	$("#"+arguments[2]).validatebox("validate");
//	$("#"+arguments[3]).val(arguments[0]);
//	$("#"+arguments[3]).focus();
//	$("#windowProject").window("close");
//}



/*
//选择收入
function selectNewIncome(projectName,projectCodeKey){
	if(flag3 && isDisplay($('#income'))){

		var table = document.getElementById("tableNewIncome");
		var pchildren = table.childNodes;
		//清空表中的行和列
		for(var a=pchildren.length-1; a>=0; a--){
			table.removeChild(pchildren[a]);
		}
		var n = 0;
		//判断颜色的
		var m = 0;
		for(var k = 0 ; k < industryCategoryAll.industryCategoryTotal.length;k++){
//								不显示 家庭消费
			if(industryCategoryAll.industryCategoryTotal[k].codeVlue == "家庭消费"){
			}else{
				var  index = industryCategoryAll.industryCategoryTotal[k].codaTableId;
				var  name = "industryCategoryTotal"+index;
				var  objectName = "industryCategoryAll."+name;
				var object = (eval("("+objectName+")"));
				var n = 0;
				var length = 0;
				if(object.length%3 > 0){
					length = parseInt(object.length/3)+1;
				}else{
					length = parseInt(object.length/3);
				}
				for(var i = 0 ; i < length;i++){
					m = m+1;
					var tr=document.createElement("tr");
					if(k % 2 == 0){
						//tr.setAttribute('bgcolor','#DDE4EE');
					}
					if(i == 0){
						var cell1=document.createElement("td");
						cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ industryCategoryAll.industryCategoryTotal[k].codeVlue+"</font>";
						cell1.setAttribute("rowspan",length);
						cell1.setAttribute("align","center");
						cell1.setAttribute("width","150");
						cell1.setAttribute("bgcolor","#DDE4EE");
						tr.appendChild (cell1);
					}


					for(var j = 0 ; j < 3 ;j++){
						var cell=document.createElement("td");
						cell.setAttribute("width","160");
						n = n+1;
						if(m % 2 == 0){
							cell.setAttribute("bgcolor","#DDE4EE");
						}
						if(object[n-1] != undefined){
							var key = object[n-1].codeKey;
							var value = object[n-1].codeVlue;
							var arrayObj = new Array(key,value);
							var obj = new Object();
							obj["codeKey"]=object[n-1].codeKey;
							obj["codeValue"]=object[n-1].codeVlue;
							cell.innerHTML =
								"<a href='javascript:;' onclick='javascript:addNewIncome(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+projectName+"\",\""+projectCodeKey+"\")'>"+obj.codeValue+"</a>";
						}
						tr.appendChild (cell);
					}
					table.appendChild (tr);
				}
			}
		}
		$("#windowNewIncome").window({
			width:550,
			height:25*(Math.ceil(industryCategoryAll.length/3)),
			title:"新经营收入来源",
			minimizable:false,
			maximizable:false,
			collapsible:false,
			closed:false,
			modal:true
		});
	}
}

function addNewIncome(){
	$("#"+arguments[2]).val(arguments[1]);
	$("#"+arguments[2]).validatebox("validate");
	$("#"+arguments[3]).val(arguments[0]);
	$("#"+arguments[3]).focus();
	$("#windowNewIncome").window("close");
}*/

//选择高危客户标记原因
function selectHighDangerCustomer(higDangerCustomer,highDangerReason){
	console.info(higDangerCustomer+"-----"+highDangerReason);

	if(isDisplayRadio('highDangered')){
		console.info("ss");
		var table = document.getElementById("tableHighDangerCustomer");
		var pchildren = table.childNodes;
		//清空表中的行和列
		for(var a=pchildren.length-1; a>=0; a--){
			table.removeChild(pchildren[a]);
		}
		var n = 0;
		//判断颜色的
		var m = 0;
		for(var k = 0 ; k < highDangerCustomer.highDangerCustomer.length;k++){
			var  index = highDangerCustomer.highDangerCustomer[k].codaTableId;
			var  name = "highDangerCustomer"+index;
			var  objectName = "highDangerCustomer."+name;
			var object = (eval("("+objectName+")"));
			var n = 0;
			var length = 0;
			if(object.length%3 > 0){
				length = parseInt(object.length/3)+1;
			}else{
				length = parseInt(object.length/3);
			}
			for(var i = 0 ; i < length;i++){
				m = m+1;
				var tr=document.createElement("tr");
				if(k % 2 == 0){
					//tr.setAttribute('bgcolor','#DDE4EE');
				}
				if(i == 0){
					var cell1=document.createElement("td");
					cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ highDangerCustomer.highDangerCustomer[k].codeVlue+"</font>";
					cell1.setAttribute("rowspan",length);
					cell1.setAttribute("align","center");
					cell1.setAttribute("width","150");
					cell1.setAttribute("bgcolor","#DDE4EE");
					tr.appendChild (cell1);
				}


				for(var j = 0 ; j < 3 ;j++){
					var cell=document.createElement("td");
					cell.setAttribute("width","160");
					n = n+1;
					if(m % 2 == 0){
						cell.setAttribute("bgcolor","#DDE4EE");
					}
					if(object[n-1] != undefined){
						var key = object[n-1].codeKey;
						var value = object[n-1].codeVlue;
						var arrayObj = new Array(key,value);
						var obj = new Object();
						obj["codeKey"]=object[n-1].codeKey;
						obj["codeValue"]=object[n-1].codeVlue;
						cell.innerHTML =
							"<a href='javascript:;' onclick='javascript:addHighDangerCustomer(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+higDangerCustomer+"\",\""+highDangerReason+"\")'>"+obj.codeValue+"</a>";
					}
					tr.appendChild (cell);
				}
				table.appendChild (tr);
			}
		}
		$("#windowHighDangerCustomer").window({
			width:550,
			height:25*(Math.ceil(highDangerCustomer.length/3)),
			title:"高危标记客户原因选择",
			minimizable:false,
			maximizable:false,
			collapsible:false,
			closed:false,
			modal:true,
			closable: false
		});
	}

}

function addHighDangerCustomer(){
	console.info("args[2]----"+arguments[2]+"----arg[3]---"+arguments[3]);
	$("#"+arguments[2]).val(arguments[1]);
	//$("#"+arguments[2]).bind("click",selectHighDangerCustomer('higDangerCustomer','highDangerReason'));
	//$("#"+arguments[2]).validatebox("validate");
	$("#"+arguments[2]).show();
	$("#"+arguments[3]).val(arguments[0]);
	$("#"+arguments[3]).focus();
	$("#windowHighDangerCustomer").window("close");
}




function containedKey(arrs,item){
	for(var i=0;i<arrs.length;i++){
		if(arrs[i] == item){
			return true;
		}
	}
	return false;
}
//选择收入来源变更内容

function selectSourceIncomeChangedContent(sourceIncomeChangedContent_ipt,sourceIncomeChangedContent,click){
	/*console.info(sourceIncomeChangedContent_ipt+"-----"+sourceIncomeChangedContent);
	console.info("sourceIncomeChangedContentTotal-------------"+sourceIncomeChangedContentTotal);*/
	console.info("click------"+click);
	var chooseValueArray;
	if(click && "yes" == click){
		var choosedValue = $("#sourceIncomeChangedContent").val();
		 chooseValueArray = choosedValue.split(";");
		console.info("chooseValueArray--------------"+chooseValueArray);
	}

	if(isDisplaySourceIncomeChangedContentWindow($('#sourceIncomeChangedType'))){
		/*console.info("------------------xxxx");*/
		var table = document.getElementById("tableSourceIncomeChangedContent");
		var pchildren = table.childNodes;
		//清空表中的行和列
		for(var a=pchildren.length-1; a>=0; a--){
			table.removeChild(pchildren[a]);
		}
		var n = 0;
		//判断颜色的
		var m = 0;
		for(var k = 0 ; k < sourceIncomeChangedContentTotal.sourceIncomeChangedContentTotal.length;k++){
			var  index = sourceIncomeChangedContentTotal.sourceIncomeChangedContentTotal[k].codaTableId;
			var  name = "sourceIncomeChangedContentTotal"+index;
			var  objectName = "sourceIncomeChangedContentTotal."+name;
			var object = (eval("("+objectName+")"));
			var n = 0;
			var length = 0;
			if(object.length%3 > 0){
				length = parseInt(object.length/3)+1;
			}else{
				length = parseInt(object.length/3);
			}
			for(var i = 0 ; i < length;i++){
				m = m+1;
				var tr=document.createElement("tr");
				if(k % 2 == 0){
					//tr.setAttribute('bgcolor','#DDE4EE');
				}
				if(i == 0){
					var cell1=document.createElement("td");
					cell1.innerHTML = "<font style='width: 200px;font-size: 13;'>"+ sourceIncomeChangedContentTotal.sourceIncomeChangedContentTotal[k].codeVlue+"</font>";
					cell1.setAttribute("rowspan",length);
					cell1.setAttribute("align","center");
					cell1.setAttribute("width","150");
					cell1.setAttribute("bgcolor","#DDE4EE");
					tr.appendChild (cell1);
				}

				for(var j = 0 ; j < 3 ;j++){
					var cell=document.createElement("td");
					cell.setAttribute("width","160");
					n = n+1;
					if(m % 2 == 0){
						cell.setAttribute("bgcolor","#DDE4EE");
					}
					if(object[n-1] != undefined){
						var key = object[n-1].codeKey;
						var value = object[n-1].codeVlue;
						var arrayObj = new Array(key,value);
						var obj = new Object();
						obj["codeKey"]=object[n-1].codeKey;
						obj["codeValue"]=object[n-1].codeVlue;
						if("yes"==click){
							console.info("chooseValueArray.length--------"+chooseValueArray.length);
							console.info("key---"+key+"---value--"+value);
							console.info("containedKey(chooseValueArray,key)----------"+containedKey(chooseValueArray,key));
						}

						if(chooseValueArray && chooseValueArray.length>0 && containedKey(chooseValueArray,key)){
							cell.innerHTML =
								//	"<a href='javascript:;' onclick='javascript:addSourceIncomeChangedContent(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+sourceIncomeChangedContent_ipt+"\",\""+sourceIncomeChangedContent+"\")'>"+obj.codeValue+"</a>";
								"<input type='checkbox' checked='checked' name='ckSourceIncomeChangedContent' id='ckSourceIncomeChangedContent' text='"+obj.codeValue+"' value='"+obj.codeKey+"'/>"+obj.codeValue;

						}else{
							cell.innerHTML =
								//	"<a href='javascript:;' onclick='javascript:addSourceIncomeChangedContent(\""+obj.codeKey+"\",\""+obj.codeValue+"\",\""+sourceIncomeChangedContent_ipt+"\",\""+sourceIncomeChangedContent+"\")'>"+obj.codeValue+"</a>";
								"<input type='checkbox'  name='ckSourceIncomeChangedContent' id='ckSourceIncomeChangedContent' text='"+obj.codeValue+"' value='"+obj.codeKey+"'/>"+obj.codeValue;

						}
					}
					tr.appendChild (cell);
				}
				table.appendChild (tr);
			}
		}
		$("#windowSourceIncomeChangedContent").dialog({
			width:550,
			height:25*(Math.ceil(sourceIncomeChangedContentTotal.length/3)),
			title:"收入来源变更内容选择",
			minimizable:false,
			maximizable:false,
			collapsible:false,
			closed:false,
			modal:true,
			closable: false,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					console.info("-save----------");
					var sourceIncomeChangedContentText = '';
					var sourceIncomeChangedContentVale = '';
					var checkedNodes = $("input[name='ckSourceIncomeChangedContent']:checked");
					if(checkedNodes.length==0){
						$.messager.alert("注意","请选择变更的收入来源...");
						return;
					}else{
						for(var i=0;i<checkedNodes.length;i++){
							var node = checkedNodes[i];
							sourceIncomeChangedContentText  = sourceIncomeChangedContentText + $(node).attr("text")+";";
							sourceIncomeChangedContentVale  = sourceIncomeChangedContentVale + $(node).attr("value")+";";
						}
						//console.info("sourceIncomeChangedContentText:"+sourceIncomeChangedContentText+",sourceIncomeChangedContentVale:"+sourceIncomeChangedContentVale);
						$("#sourceIncomeChangedContent").val(sourceIncomeChangedContentVale);
						$("#sourceIncomeChangedContent_ipt").val(sourceIncomeChangedContentText);
						$("#sourceIncomeChangedContentStr").val(sourceIncomeChangedContentText);
						console.info("sourceIncomeChangedContentText1:"+$("#sourceIncomeChangedContent_ipt").val()+
						",sourceIncomeChangedContentVale:"+$("#sourceIncomeChangedContent").val()+",sourceIncomeChangedContentStr:"+sourceIncomeChangedContentStr);


						//console.info("sourceIncomeChangedContent_div--------sourceIncomeChangedContent_div");
						$("#sourceIncomeChangedContent_div").show();
						//console.info("sourceIncomeChangedContent_div----sssss----sourceIncomeChangedContent_div");
						$("#windowSourceIncomeChangedContent").dialog("close");

					}

				}
			}]
		});
	}

}

function addSourceIncomeChangedContent(){
	console.info("args[2]----"+arguments[2]+"----arg[3]---"+arguments[3]);
	$("#"+arguments[2]).val(arguments[1]);
	//$("#"+arguments[2]).bind("click",selectHighDangerCustomer('higDangerCustomer','highDangerReason'));
	//$("#"+arguments[2]).validatebox("validate");
	$("#"+arguments[2]).show();
	$("#"+arguments[3]).val(arguments[0]);
	$("#"+arguments[3]).focus();
	$("#windowSourceIncomeChangedContent").window("close");
}


function editUpdate(){
	$("#visitDurationTimes").val($("#hours").val()+"-"+$("#minutes").combobox("getValue"));
}

/*验证当选择为否时，控制是否可显*/
function isDisplay($obj) {
	return $obj.combobox('getText') == '否' ? false : true;
}
/*验证当选择为否时，控制是否可显*/
function isDisplayRadio($obj) {
	return $("input[name='"+$obj+"']:checked").val() == '否' ? false : true;
}

/*验证当选择为否时，控制是否可显*/
function isDisplaySourceIncomeChangedContentWindow($obj) {
	var text = $obj.combobox('getText');
	return (text == "新增" )|| (text = "减少") ? true : false;
}
function validateContract() {
	if ($("#contractChangeType_div input").size() > 0) {
		$("#contractChangeType_div input").each(function (index, input) {
			var phoneNumber = $(input).val();
			var isValid = $(input).validatebox("isValid");
			var phoneValid= validPhone(phoneNumber);
			/*console.info("验证：---"+isValid);
			console.info("电话验证："+ phoneValid);*/
			if (isValid && $.trim(isValid) =="true" && phoneValid && $.trim(phoneValid)=="true") {
				//console.info("验证通过");
			} else {
				$(input).focus();
				return ;
			}
		});
	}
}
function editUpdateSubmit() {
	flag1 = false;
	flag2 = false;
	flag3 = false;
	 validateContract();
	if ($("#customerReturnVisitForm").form("validate")) {
		//检查是否上传附件
		var clientId = clientid;
		var url = serverName + "/businessStatusChangeController/checkAttachment.do";
		ajaxSubmit(url, "post", {clientId: clientId}, function (attachmentCount) {
			console.info("attachmentCount:"+attachmentCount.count);
			if (!attachmentCount.count > 0) {
				clientId = '';
				console.info("visway"+$("input[name='visitWay']:checked").val());
				if ($("input[name='visitWay']:checked").val() == '1002') {
					$.messager.alert("提示", "实地回访请上传附件！");
					return;
				}
			}
			$.messager.confirm('提示', '确定保存吗？', function (r) {
				if (r) {
					var dataParam = 'attachmentId=' + clientId + '&' + $('#customerReturnVisitForm').serialize();
					$("#commitButton").linkbutton("disable");
					editUpdate();
					ajaxSubmit(serverName + "/CustomerReturnVisitController/update.do", dataParam, function (result) {
						if (result.success) {
							$("#commitButton").linkbutton("enable");
							parent.$.messager.show({
								title: "消息",
								msg: "保存成功"
							});
							parent.closeTab("编辑回访");
						}
						$("#commitButton").linkbutton("enable");
					});
				}
			});
		}, false);
	} else {
		alert("请将红色提示框填写正确");
	}
	flag1 = true;
	flag2 = true;
	flag3 = true;
}
