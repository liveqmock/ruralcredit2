<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
	var serverName="<%=path%>";
	var returnValue = "";
	var weekLoad = "0";
	var monthLoad = "0";
	
	function setZero(str){
		if(str==""){
			return parseFloat(0);
		}else{
			return str;
		}
	}
	
	function calculate(obj,type){
		/*var id = obj.id;
		var ss = id.split("_");
		var total = 0;
		var row = 0;
		if(type=="edOperCsIn" || type=="ewOperCsIn"){
			row = 8;
		}else if(type=="emOperCsIn"){
			row = 13;
		}
		for(var i=1;i<row;i++){
			var id2 = ss[0]+"_"+i+"_"+ss[2];
			var valueT = document.getElementById(id2).value;
			if(valueT!=""){
				total = parseFloat(valueT) + parseFloat(total);
			}
		}
		var totalNum = total/(row-1);
		document.getElementById(ss[0]+"_average_"+ss[2]).value = cutDecimal(totalNum.toString(),2);*/
	}
	
	function calculateRate(obj,type){
		
		/*var id = obj.id;
		var ss = id.split("_");
		var total = 0;
		var row = 10;
		if(ss[2]=="item4"){
			total = 0;
			//计算单件销售汇总
			for(var i=0;i<row;i++){
				var id2 = ss[0]+"_"+i+"_item4";
				var valueT = document.getElementById(id2).value;
				//alert("---"+id2+"--"+valueT);
				if(valueT!=""){
					total = parseFloat(valueT) + parseFloat(total);
				}
			}
			document.getElementById("avgRate_total_item4").value = cutDecimal(total.toString(),2);
		}else{
			//第一列
			var item1Id = ss[0]+"_"+ss[1]+"_item1";
			var item2Id = ss[0]+"_"+ss[1]+"_item2";
			var item1Value = document.getElementById(item1Id).value;
			var item2Value = document.getElementById(item2Id).value;
			
			if(item1Value==""){
				item1Value = 0;
			}
			if(item2Value==""){
				item2Value = 1;
			}
			//单件成本 同一行的第四列
			var perOneCostId = ss[0]+"_"+ss[1]+"_item3";
			var perOneCost = parseFloat(item1Value)/parseFloat(item2Value);
			document.getElementById(perOneCostId).value = cutDecimal(perOneCost.toString(),2);
			//计算单件成本汇总
			for(var i=0;i<row;i++){
				var id2 = ss[0]+"_"+i+"_item3";
				var valueT = document.getElementById(id2).value;
				//alert("---"+id2+"--"+valueT);
				if(valueT!=""){
					total = parseFloat(valueT) + parseFloat(total);
				}
			}
			document.getElementById("avgRate_total_item3").value = cutDecimal(total.toString(),2);
		}
		//计算平均加价率
		var totalC = document.getElementById("avgRate_total_item3").value;
		var totalS = document.getElementById("avgRate_total_item4").value;
		if(totalC==""){
			totalC = 1;
		}
		if(totalS==""){
			totalS = 1;
		}
		var calculateValue = (parseFloat(totalS)-parseFloat(totalC))/parseFloat(totalC)
		calculateValue = cutDecimal(calculateValue.toString(),2);
		document.getElementById("avgRate_avg_item4").value = calculateValue;
		for(var i=1;i<4;i++){
			var rateId = "avgCost_2_item"+i;
			document.getElementById(rateId).value = calculateValue;
		}
		//重新计算 采购或进货成本 表中的值
		calculateCost2();*/
		
	}

	//采购或进货成本
	function calculateCost(obj,type){
		
		/*var id = obj.id;
		var ss = id.split("_");
		
		var sellId = "avgSell_"+1+"_"+ss[2];
		var rateId = "avgCost_"+2+"_"+ss[2];
		var totalId = "avgCost_average_"+ss[2];
		var sell = document.getElementById(sellId).value;
		if(sell==""){
			sell = 0;
		}
		var rate = document.getElementById(rateId).value;
		if(rate==""){
			rate = 0;
		}
		var total = document.getElementById(totalId);
		var num1 = parseFloat(sell); 
		var num2 = parseFloat(1)+parseFloat(rate);
		var totalNum = parseFloat(num1)/parseFloat(num2);
		
		total.value = cutDecimal(totalNum.toString(),2);*/
	}
	
	//设置采购或进货成本项目名称和加价率
	//2013-10-08 业务要求去掉所有的计算公式
	function setProjNameAndAddRate(){
		/*//设置采购或进货成本的项目名称和平均加价率 项目取第一个 liuli 
		document.getElementById('avgProjName_0_item1').value = document.getElementById('avgRate_0_projName').value;
		document.getElementById('avgProjName_0_item2').value = document.getElementById('avgRate2_0_projName').value;
		//设置加价率
		document.getElementById("avgCost_2_item1").value = document.getElementById("avgRate_avg_item4").value;
		document.getElementById("avgCost_2_item2").value = document.getElementById("avgRate2_avg_item4").value;*/
	}
	//采购或进货成本(不需要参数，硬编码计算)
	function calculateCost2(){
		
		/*var avgItem1 = setZero(document.getElementById("avgSell_1_item1").value);
		var avgItem2 = setZero(document.getElementById("avgSell_1_item2").value);
		var avgItem3 = setZero(document.getElementById("avgSell_1_item3").value);
		var avgRate = setZero(document.getElementById("avgCost_2_item1").value);
		
		//document.getElementById("display").innerHTML = "avgItem1="+avgItem1+"--avgItem2="+avgItem2+"--avgRate="+avgRate;
		
		document.getElementById("avgCost_average_item1").value = cutDecimal(parseFloat(avgItem1)/(parseFloat(1)+parseFloat(avgRate)),2);
		document.getElementById("avgCost_average_item2").value = cutDecimal(parseFloat(avgItem2)/(parseFloat(1)+parseFloat(avgRate)),2);
		document.getElementById("avgCost_average_item3").value = cutDecimal(parseFloat(avgItem3)/(parseFloat(1)+parseFloat(avgRate)),2);*/
		
	}
	
	//表 4:  采购或进货成本 汇总
	function calculatePuCost(obj,type){
		
		/*var id = obj.id;
		var ss = id.split("_");
		var total = 0;
		var row = 9;
		
		for(var i=0;i<row;i++){
			var id2 = ss[0]+"_"+i+"_"+ss[2];
			var valueT = document.getElementById(id2).value;
			//alert("---"+id2+"--"+valueT);
			if(valueT!=""){
				total = parseFloat(valueT) + parseFloat(total);
			}
		}
		var totalNum = total;
		if(ss[2]=="item2"){
			document.getElementById(ss[0]+"_avg_"+ss[2]).value = totalNum;
		}else{
			document.getElementById(ss[0]+"_avg_"+ss[2]).value = cutDecimal(totalNum.toString(),2);
		}*/
		
	}
	
	//截取两位小数
	function cutDecimal(str,median){
		return Math.round(parseFloat(str)*100)/100;
	}
	//显示加载条
	function displayLoadingProc(str){
		$("<div class='datagrid-mask'></div>").css({ cursor:"wait", display: "block", width: "100%", height: $(window).height() }).appendTo("load");  
		$("<div class='datagrid-mask-msg'></div>").html(str).appendTo("load").css({ cursor:"wait", display: "block", left: ($(document.body).outerWidth(true) - 350) / 2, top: ($(window).height()) / 2 - 90 }); 
	}
	//关闭加载条
	function closeLoadingProc(){
		var maskObj = $(".datagrid-mask");
		var maskMsgObj = $(".datagrid-mask-msg");
		if(maskObj!=null){
			maskObj.remove();  
			maskMsgObj.remove();  
		}
	}
</script>
<style>
<!--
.cashStreamDiv {
	letter-spacing: 1px;
}

.cashStreamDiv table {
	border-collapse: collapse;
}

.cashStreamDiv td {
	border: 1px solid #CCC;
}
.cashStreamDiv .savaBtn{
	font-size:13px;
}
/*表头标题*/
.cashStreamDiv .title {
	text-align: center;
	font-weight: bold;
}

.cashStream_item_tr {
	text-align: center;
	letter-spacing: 1px;
}

.cashStream_item_tr td input {
	width: 80px;
	text-align: left;
}
-->
</style>
<script type="text/javascript">
<!--
	$("<div class='datagrid-mask'></div>").css({ cursor:"wait", display: "block", width: "100%", height: $(window).height() }).appendTo("load");  
	$("<div class='datagrid-mask-msg'></div>").html("数据加载中,请稍后...").appendTo("load").css({ cursor:"wait", display: "block", left: ($(document.body).outerWidth(true) - 350) / 2, top: ($(window).height()) / 2 - 90 });  
//-->
</script>
<load style="display:false;" id="loading">
</load>
<div>
	<font color="red" id="display"></font>
</div>
<div id="tt" class="easyui-tabs" width="100%" height="500">
	<div title="平均每天经营现金流入表">
		<div class="cashStreamDiv">
			<form id="everyDayOperCsInForm" name="form1" method="post">
				<table width="500" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center"
							style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
							<br />
							<font style="font-size: 16px; letter-spacing: 3px;">平均每天经营现金流入表</font>
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td rowspan="2"> 
							星期
						</td>
						<td colspan="3">
							经营或商品类别
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td width="125">
							<input type="hidden" name="everyDayOperCsIn[0].operAverageCsInId" />
							<input type="text" maxlength="11" name="everyDayOperCsIn[0].item1" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyDayOperCsIn[0].item2" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyDayOperCsIn[0].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr"> 
						<td>
							星期一
							<input type="hidden" name="everyDayOperCsIn[1].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" id="edOperCsIn_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyDayOperCsIn[1].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_1_item2" name="everyDayOperCsIn[1].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_1_item3" name="everyDayOperCsIn[1].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期二
							<input type="hidden" name="everyDayOperCsIn[2].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_2_item1" name="everyDayOperCsIn[2].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_2_item2" name="everyDayOperCsIn[2].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_2_item3" name="everyDayOperCsIn[2].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期三
							<input type="hidden" name="everyDayOperCsIn[3].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_3_item1" name="everyDayOperCsIn[3].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_3_item2" name="everyDayOperCsIn[3].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_3_item3" name="everyDayOperCsIn[3].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期四
							<input type="hidden" name="everyDayOperCsIn[4].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_4_item1" name="everyDayOperCsIn[4].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_4_item2" name="everyDayOperCsIn[4].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_4_item3" name="everyDayOperCsIn[4].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期五
							<input type="hidden" name="everyDayOperCsIn[5].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_5_item1" name="everyDayOperCsIn[5].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_5_item2" name="everyDayOperCsIn[5].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_5_item3" name="everyDayOperCsIn[5].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期六
							<input type="hidden" name="everyDayOperCsIn[6].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_6_item1" name="everyDayOperCsIn[6].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_6_item2" name="everyDayOperCsIn[6].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_6_item3" name="everyDayOperCsIn[6].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							星期日
							<input type="hidden" name="everyDayOperCsIn[7].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_7_item1" name="everyDayOperCsIn[7].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_7_item2" name="everyDayOperCsIn[7].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'edOperCsIn');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" id="edOperCsIn_7_item3" name="everyDayOperCsIn[7].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							平均
							<input type="hidden" name="everyDayOperCsIn[8].operAverageCsInId" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" id="edOperCsIn_average_item1"  name="everyDayOperCsIn[8].item1" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" id="edOperCsIn_average_item2" name="everyDayOperCsIn[8].item2" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" id="edOperCsIn_average_item3" name="everyDayOperCsIn[8].item3" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div title="经营平均每周现金流入表" onSelect="test();">
		<div class="cashStreamDiv">
			<form id="everyWeekOperCsInForm" method="post">
				<table width="500" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center"
							style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
							<br />
							<font style="font-size: 16px; letter-spacing: 3px;">经营平均每周现金流入表</font>
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td rowspan="2"> 
							第_周
						</td>
						<td colspan="3">
							经营或商品类别
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td width="125">
							<input type="hidden" name="everyWeekOperCsIn[0].operAverageCsInId" />
							<input type="text" maxlength="11" name="everyWeekOperCsIn[0].item1" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyWeekOperCsIn[0].item2" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyWeekOperCsIn[0].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							1
							<input type="hidden" name="everyWeekOperCsIn[1].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[1].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_1_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[1].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_1_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[1].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							2
							<input type="hidden" name="everyWeekOperCsIn[2].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_2_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[2].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_2_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[2].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_2_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[2].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							3
							<input type="hidden" name="everyWeekOperCsIn[3].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_3_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[3].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_3_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[3].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_3_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[3].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							4
							<input type="hidden" name="everyWeekOperCsIn[4].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_4_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[4].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_4_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[4].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_4_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[4].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							5
							<input type="hidden" name="everyWeekOperCsIn[5].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_5_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[5].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_5_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[5].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_5_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[5].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							6
							<input type="hidden" name="everyWeekOperCsIn[6].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_6_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[6].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_6_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[6].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_6_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[6].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							7
							<input type="hidden" name="everyWeekOperCsIn[7].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_7_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[7].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_7_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[7].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'ewOperCsIn');" id="ewOperCsIn_7_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyWeekOperCsIn[7].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							平均
							<input type="hidden" name="everyWeekOperCsIn[8].operAverageCsInId" />
						</td>
						<td>
							<input type="text" id="ewOperCsIn_average_item1" readonly style="border:none;text-align:center;" name="everyWeekOperCsIn[8].item1" />
						</td>
						<td>
							<input type="text" id="ewOperCsIn_average_item2" readonly style="border:none;text-align:center;" name="everyWeekOperCsIn[8].item2" />
						</td>
						<td>
							<input type="text" id="ewOperCsIn_average_item3" readonly style="border:none;text-align:center;" name="everyWeekOperCsIn[8].item3" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div title="经营平均每月现金流入表">
		<div class="cashStreamDiv">
			<form id="everyMonthOperCsInForm" method="post">
				<table width="500" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="4" align="center"
							style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
							<br />
							<font style="font-size: 16px; letter-spacing: 3px;">经营平均每月现金流入表</font>
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td rowspan="2"> 
							月份
						</td>
						<td colspan="3">
							经营或商品类别
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td width="125">
							<input type="hidden" name="everyMonthOperCsIn[0].operAverageCsInId" />
							<input type="text" maxlength="11" name="everyMonthOperCsIn[0].item1" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyMonthOperCsIn[0].item2" />
						</td>
						<td  width="125">
							<input type="text" maxlength="11" name="everyMonthOperCsIn[0].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							1月
							<input type="hidden" name="everyMonthOperCsIn[1].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[1].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_1_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[1].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_1_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[1].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							2月
							<input type="hidden" name="everyMonthOperCsIn[2].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_2_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[2].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_2_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[2].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_2_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[2].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							3月
							<input type="hidden" name="everyMonthOperCsIn[3].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_3_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[3].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_3_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[3].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_3_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[3].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							4月
							<input type="hidden" name="everyMonthOperCsIn[4].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_4_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[4].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_4_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[4].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_4_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[4].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							5月
							<input type="hidden" name="everyMonthOperCsIn[5].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_5_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[5].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_5_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[5].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_5_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[5].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							6月
							<input type="hidden" name="everyMonthOperCsIn[6].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_6_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[6].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_6_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[6].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_6_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[6].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							7月
							<input type="hidden" name="everyMonthOperCsIn[7].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_7_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[7].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_7_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[7].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_7_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[7].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							8月
							<input type="hidden" name="everyMonthOperCsIn[8].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_8_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[8].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_8_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[8].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_8_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[8].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							9月
							<input type="hidden" name="everyMonthOperCsIn[9].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_9_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[9].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_9_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[9].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_9_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[9].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							10月
							<input type="hidden" name="everyMonthOperCsIn[10].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_10_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[10].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_10_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[10].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_10_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[10].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							11月
							<input type="hidden" name="everyMonthOperCsIn[11].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_11_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[11].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_11_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[11].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_11_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[11].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							12月
							<input type="hidden" name="everyMonthOperCsIn[12].operAverageCsInId" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_12_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[12].item1" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_12_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[12].item2" />
						</td>
						<td>
							<input type="text" onkeyup="calculate(this,'emOperCsIn');" id="emOperCsIn_12_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyMonthOperCsIn[12].item3" />
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td>
							平均
							<input type="hidden" name="everyMonthOperCsIn[13].operAverageCsInId" />
						</td>
						<td>
							<input type="text" id="emOperCsIn_average_item1" readonly style="border:none;text-align:center;" name="everyMonthOperCsIn[13].item1" />
						</td>
						<td>
							<input type="text" id="emOperCsIn_average_item2" readonly style="border:none;text-align:center;" name="everyMonthOperCsIn[13].item2" />
						</td>
						<td>
							<input type="text" id="emOperCsIn_average_item3" readonly style="border:none;text-align:center;" name="everyMonthOperCsIn[13].item3" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div title="平均加价率">
		<div class="cashStreamDiv">
			<form id="avgIncRateForm" method="post">
				<table width="500" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="5" align="center"
							style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
							<br />
							<font style="font-size: 16px; letter-spacing: 3px;">平均加价率</font>
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td> 
							销售项目
						</td>
						<td>
							批发采购成本
						</td>
						<td>
							批发采购数量
						</td>
						<td>
							单件成本
						</td>
						<td>
							单件销售价格
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[0].avgIncRateId" />
							<input type="text" onkeyup="setProjNameAndAddRate();" id="avgRate_0_projName" maxlength="11" name="avgIncRates[0].sellProject" />
						</td>
						<td>
							<input type="text" maxlength="11" onkeyup="calculateRate(this,'avgRate');" id="avgRate_0_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[0].purchaceCost" />
						</td>
						<td>
							<input type="text" maxlength="9" onkeyup="calculateRate(this,'avgRate');" id="avgRate_0_item2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[0].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_0_item3" readonly style="border:none;text-align:center;" name="avgIncRates[0].onePieceCost" />
						</td>
						<td>
							<input type="text" maxlength="11" onkeyup="calculateRate(this,'avgRate');" id="avgRate_0_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[0].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[1].avgIncRateId" />
							<input type="text" name="avgIncRates[1].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[1].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_1_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[1].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_1_item3" readonly style="border:none;text-align:center;" name="avgIncRates[1].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_1_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[1].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td>
							<input type="hidden" name="avgIncRates[2].avgIncRateId" />
							<input type="text" name="avgIncRates[2].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_2_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[2].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_2_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[2].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_2_item3" readonly style="border:none;text-align:center;" name="avgIncRates[2].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_2_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[2].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[3].avgIncRateId" />
							<input type="text" name="avgIncRates[3].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_3_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[3].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_3_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[3].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_3_item3" readonly style="border:none;text-align:center;" name="avgIncRates[3].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_3_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[3].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[4].avgIncRateId" />
							<input type="text" name="avgIncRates[4].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_4_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[4].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_4_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[4].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_4_item3" readonly style="border:none;text-align:center;" name="avgIncRates[4].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_4_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[4].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[5].avgIncRateId" />
							<input type="text" name="avgIncRates[5].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_5_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[5].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_5_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[5].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_5_item3" readonly style="border:none;text-align:center;" name="avgIncRates[5].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_5_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[5].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[6].avgIncRateId" />
							<input type="text" name="avgIncRates[6].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_6_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[6].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_6_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[6].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_6_item3" readonly style="border:none;text-align:center;" name="avgIncRates[6].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_6_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[6].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[7].avgIncRateId" />
							<input type="text" name="avgIncRates[7].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_7_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[7].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_7_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[7].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_7_item3" readonly style="border:none;text-align:center;" name="avgIncRates[7].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_7_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[7].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[8].avgIncRateId" />
							<input type="text" name="avgIncRates[8].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_8_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[8].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_8_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[8].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_8_item3" readonly style="border:none;text-align:center;" name="avgIncRates[8].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_8_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[8].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[9].avgIncRateId" />
							<input type="text" name="avgIncRates[9].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_9_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[9].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_9_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[9].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_9_item3" readonly style="border:none;text-align:center;" name="avgIncRates[9].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate_9_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[9].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td style="text-align:left;padding-left:8px;"> 
							<input type="hidden" name="avgIncRates[10].avgIncRateId" />
							汇总
						</td>
						<td>
							<input type="text" id="avgRate_total_item1" readonly style="border:none;text-align:center;" name="avgIncRates[10].purchaceCost" />
						</td>
						<td>
							<input type="text" id="avgRate_total_item2" readonly style="border:none;text-align:center;" name="avgIncRates[10].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate_total_item3" readonly style="border:none;text-align:center;" name="avgIncRates[10].onePieceCost" />
						</td>
						<td>
							<input type="text" id="avgRate_total_item4" readonly style="border:none;text-align:center;" name="avgIncRates[10].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td style="text-align:left;padding-left:8px;"> 
							<input type="hidden" name="avgIncRates[11].avgIncRateId" />
							平均加价率
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[11].purchaceCost" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[11].purchaceQuantity" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[11].onePieceCost" />
						</td>
						<td>
							<input type="text" id="avgRate_avg_item4" readonly style="border:none;text-align:center;" name="avgIncRates[11].onePieceSalePrice" />
						</td>
					</tr>
					
					<!-- 增加一个 表 2013-05-08 liuli -->
					<table width="500" align="center" cellpadding="3" cellspacing="0">
					<tr>
						<td colspan="5" align="center"
							style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
							<br />
							<font style="font-size: 16px; letter-spacing: 3px;">平均加价率</font>
						</td>
					</tr>
					<tr class="cashStream_item_tr">
						<td> 
							销售项目
						</td>
						<td>
							批发采购成本
						</td>
						<td>
							批发采购数量
						</td>
						<td>
							单件成本
						</td>
						<td>
							单件销售价格
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[12].avgIncRateId" />
							<input type="text" onkeyup="setProjNameAndAddRate();" id="avgRate2_0_projName" maxlength="11" name="avgIncRates[12].sellProject" />
						</td>
						<td>
							<input type="text" maxlength="11" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_0_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[12].purchaceCost" />
						</td>
						<td>
							<input type="text" maxlength="9" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_0_item2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[12].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_0_item3" readonly style="border:none;text-align:center;" name="avgIncRates[12].onePieceCost" />
						</td>
						<td>
							<input type="text" maxlength="11" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_0_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[12].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[13].avgIncRateId" />
							<input type="text" name="avgIncRates[13].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[13].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_1_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[13].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_1_item3" readonly style="border:none;text-align:center;" name="avgIncRates[13].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_1_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[13].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td>
							<input type="hidden" name="avgIncRates[14].avgIncRateId" />
							<input type="text" name="avgIncRates[14].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_2_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[14].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_2_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[14].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_2_item3" readonly style="border:none;text-align:center;" name="avgIncRates[14].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_2_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[14].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[15].avgIncRateId" />
							<input type="text" name="avgIncRates[15].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_3_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[15].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_3_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[15].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_3_item3" readonly style="border:none;text-align:center;" name="avgIncRates[15].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_3_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[15].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[16].avgIncRateId" />
							<input type="text" name="avgIncRates[16].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_4_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[16].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_4_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[16].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_4_item3" readonly style="border:none;text-align:center;" name="avgIncRates[16].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_4_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[16].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[17].avgIncRateId" />
							<input type="text" name="avgIncRates[17].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_5_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[17].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_5_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[17].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_5_item3" readonly style="border:none;text-align:center;" name="avgIncRates[17].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_5_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[17].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[18].avgIncRateId" />
							<input type="text" name="avgIncRates[18].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_6_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[18].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_6_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[18].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_6_item3" readonly style="border:none;text-align:center;" name="avgIncRates[18].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_6_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[18].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[19].avgIncRateId" />
							<input type="text" name="avgIncRates[19].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_7_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[19].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_7_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[19].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_7_item3" readonly style="border:none;text-align:center;" name="avgIncRates[19].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_7_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[19].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[20].avgIncRateId" />
							<input type="text" name="avgIncRates[20].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_8_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[20].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_8_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[20].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_8_item3" readonly style="border:none;text-align:center;" name="avgIncRates[20].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_8_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[20].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td> 
							<input type="hidden" name="avgIncRates[21].avgIncRateId" />
							<input type="text" name="avgIncRates[21].sellProject" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_9_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[21].purchaceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_9_item2" maxlength="9" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[21].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_9_item3" readonly style="border:none;text-align:center;" name="avgIncRates[21].onePieceCost" />
						</td>
						<td>
							<input type="text" onkeyup="calculateRate(this,'avgRate');" id="avgRate2_9_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="avgIncRates[21].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td style="text-align:left;padding-left:8px;"> 
							<input type="hidden" name="avgIncRates[22].avgIncRateId" />
							汇总
						</td>
						<td>
							<input type="text" id="avgRate2_total_item1" readonly style="border:none;text-align:center;" name="avgIncRates[22].purchaceCost" />
						</td>
						<td>
							<input type="text" id="avgRate2_total_item2" readonly style="border:none;text-align:center;" name="avgIncRates[22].purchaceQuantity" />
						</td>
						<td>
							<input type="text" id="avgRate2_total_item3" readonly style="border:none;text-align:center;" name="avgIncRates[22].onePieceCost" />
						</td>
						<td>
							<input type="text" id="avgRate2_total_item4" readonly style="border:none;text-align:center;" name="avgIncRates[22].onePieceSalePrice" />
						</td>
					</tr>
					
					<tr class="cashStream_item_tr">
						<td style="text-align:left;padding-left:8px;"> 
							<input type="hidden" name="avgIncRates[23].avgIncRateId" />
							平均加价率
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[23].purchaceCost" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[23].purchaceQuantity" />
						</td>
						<td>
							<input type="text" readonly style="border:none;text-align:center;" name="avgIncRates[23].onePieceCost" />
						</td>
						<td>
							<input type="text" id="avgRate2_avg_item4" readonly style="border:none;text-align:center;" name="avgIncRates[23].onePieceSalePrice" />
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	
	<div title="采购或进货成本">
		<div class="cashStreamDiv">
				<form id="everyCostOperCsInForm" method="post">
					<table width="500" align="center" cellpadding="3" cellspacing="0">
						<tr>
							<td colspan="4" align="center"
								style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
								<br />
								<font style="font-size: 16px; letter-spacing: 3px;">采购或进货成本</font>
							</td>
						</tr>
						<tr class="cashStream_item_tr">
							<td rowspan="2"> 
								
							</td>
							<td colspan="3">
								经营或商品类别
							</td>
						</tr>
						<tr class="cashStream_item_tr">
							<td width="125">
								<input type="hidden" name="everyCostOperCsIn[0].operAverageCsInId" />
								<input type="text" readonly style="border:none;text-align:center;" id="avgProjName_0_item1" maxlength="11" name="everyCostOperCsIn[0].item1" />
							</td>
							<td  width="125">
								<input type="text" readonly style="border:none;text-align:center;" id="avgProjName_0_item2" maxlength="11" name="everyCostOperCsIn[0].item2" />
							</td>
							<td  width="125">
								<input type="text" maxlength="11" name="everyCostOperCsIn[0].item3" />
							</td>
						</tr>
						<tr class="cashStream_item_tr">
							<td>
								平均销售额
								<input type="hidden" name="everyCostOperCsIn[1].operAverageCsInId" />
							</td>
							<td>
								<input type="text" onkeyup="calculateCost(this,'avgSell');" id="avgSell_1_item1" onkeyup="calculate(this,'avg');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyCostOperCsIn[1].item1" />
							</td>
							<td>
								<input type="text" onkeyup="calculateCost(this,'avgSell');" id="avgSell_1_item2" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyCostOperCsIn[1].item2" />
							</td>
							<td>
								<input type="text" onkeyup="calculateCost(this,'avgSell');" id="avgSell_1_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="everyCostOperCsIn[1].item3" />
							</td>
						</tr>
						<tr class="cashStream_item_tr">
							<td>
								平均加价率
								<input type="hidden" name="everyCostOperCsIn[2].operAverageCsInId" />
							</td>
							<td>
								<input type="text" id="avgCost_2_item1" readonly style="border:none;text-align:center;" name="everyCostOperCsIn[2].item1" />
							</td>
							<td>
								<input type="text" id="avgCost_2_item2" readonly style="border:none;text-align:center;" name="everyCostOperCsIn[2].item2" />
							</td>
							<td>
								<input type="text" id="avgCost_2_item3" onkeyup="calculateCost2();" name="everyCostOperCsIn[2].item3" />
							</td>
						</tr>
						<tr class="cashStream_item_tr">
							<td>
								采购或进货成本
								<input type="hidden" name="everyCostOperCsIn[3].operAverageCsInId" />
							</td>
							<td>
								<input type="text" id="avgCost_average_item1" readonly style="border:none;text-align:center;" name="everyCostOperCsIn[3].item1" />
							</td>
							<td>
								<input type="text" id="avgCost_average_item2" readonly style="border:none;text-align:center;" name="everyCostOperCsIn[3].item2" />
							</td>
							<td>
								<input type="text" id="avgCost_average_item3" readonly style="border:none;text-align:center;" name="everyCostOperCsIn[3].item3" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	<div title="采购或进货成本2">
		<div class="cashStreamDiv">
				<form id="purchaceCostsForm" method="post">
					<table width="910" align="center" cellpadding="3" cellspacing="0">
						<tr>
							<td colspan="10" align="center"
								style="border-left: none; border-top: none; border-right: none;padding-bottom: 10px;">
								<br />
								<font style="font-size: 16px; letter-spacing: 3px;">经营或商品类别</font>
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td width="130px" rowspan="2">
								原材料或其他消耗品
							</td>
							<td width="60px" rowspan="2">
								单位
							</td>
							<td width="60px" rowspan="2">
								单价
							</td>
							<td width="60px" rowspan="2">
								采购数量
							</td>
							<td colspan="6">
								采购或进货成本
								<tr class="cashStream_item_tr">
									<td width="100px">
										每天
									</td>
									<td width="100px">
										每周
									</td>
									<td width="100px">
										每月
									</td>
									<td width="100px">
										每季度
									</td>
									<td width="100px">
										每半年
									</td>
									<td width="100px">
										每年
									</td>
								</tr>
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[0].purchaceCostId" />
								<input type="text" maxlength="11" name="purchaceCosts[0].consumables" />
							</td>
							<td>
								<input style="width:40px;" maxlength="11" type="text" name="purchaceCosts[0].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_0_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_0_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item3" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item4" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item5" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item6" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item7" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" onkeyup="calculatePuCost(this,'puCost');" id="puCost_0_item8" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[0].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[1].purchaceCostId" />
								<input type="text" name="purchaceCosts[1].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[1].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_1_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_1_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_1_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[1].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[2].purchaceCostId" />
								<input type="text" name="purchaceCosts[2].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[2].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_2_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_2_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_2_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[2].everyYear" />
							</td>
						</tr> 
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[3].purchaceCostId" />
								<input type="text" name="purchaceCosts[3].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[3].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_3_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_3_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_3_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[3].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[4].purchaceCostId" />
								<input type="text" name="purchaceCosts[4].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[4].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_4_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_4_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_4_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[4].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[5].purchaceCostId" />
								<input type="text" name="purchaceCosts[5].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[5].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_5_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_5_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_5_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[5].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[6].purchaceCostId" />
								<input type="text" name="purchaceCosts[6].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[6].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_6_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_6_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_6_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[6].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[7].purchaceCostId" />
								<input type="text" name="purchaceCosts[7].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[7].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_7_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_7_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_7_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[7].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								<input type="hidden" name="purchaceCosts[8].purchaceCostId" />
								<input type="text" name="purchaceCosts[8].consumables" />
							</td>
							<td>
								<input style="width:40px;" type="text" name="purchaceCosts[8].unit" />
							</td>
							<td>
								<input style="width:40px;" type="text" id="puCost_8_item1" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].unitPrice" />
							</td>
							<td>
								<input style="width:50px;" type="text" id="puCost_8_item2" maxlength="11" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].purchaceNum" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item3" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyDay" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item4" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyWeek" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item5" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyMonth" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item6" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyQuarter" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item7" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyHalfYear" />
							</td>
							<td>
								<input style="width:80px;" type="text" id="puCost_8_item8" onkeyup="calculatePuCost(this,'puCost');" maxlength="11" precision="2" class="easyui-numberbox" onkeypress="if(event.which==45){return false;}" name="purchaceCosts[8].everyYear" />
							</td>
						</tr>
						
						<tr class="cashStream_item_tr">
							<td>
								汇总
								<input type="hidden" name="purchaceCosts[11].purchaceCostId" />
							</td>
							<td>
								<input style="width:40px;border:none;text-align:center;" type="text" name="purchaceCosts[11].unit" />
							</td>
							<td>
								<input id="puCost_avg_item1" style="width:40px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].unitPrice" />
							</td>
							<td>
								<input id="puCost_avg_item2" style="width:50px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].purchaceNum" />
							</td>
							<td>
								<input id="puCost_avg_item3" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyDay" />
							</td>
							<td>
								<input id="puCost_avg_item4" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyWeek" />
							</td>
							<td>
								<input id="puCost_avg_item5" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyMonth" />
							</td>
							<td>
								<input id="puCost_avg_item6" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyQuarter" />
							</td>
							<td>
								<input id="puCost_avg_item7" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyHalfYear" />
							</td>
							<td>
								<input id="puCost_avg_item8" style="width:80px;border:none;text-align:center;" type="text" readonly name="purchaceCosts[11].everyYear" />
							</td>
						</tr>
						
						<tr style="text-align:right;"> 
						<td> 
						</td>
						<td colspan="9">
						</td>
					</tr>
						
					</table>
				</form>
		</div>
	</div>
		
</div>
