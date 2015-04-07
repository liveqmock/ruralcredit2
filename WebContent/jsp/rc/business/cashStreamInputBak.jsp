<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
	var serverName="<%=path%>";
	
	function setInitValue(obj){
		if(obj.getAttribute("initValue")==null){
			obj.setAttribute("initValue",obj.value);
		}
	}
	//计算函数
	function calculate(obj,type){
		/*var objValue = obj.value;
		//if(objValue == ""){
			//obj.value = parseFloat("0.00");
		//}
		calculateRow(obj);
		calculateDown(obj);
		//经营现金流合计(流入流出)
		calculateTotalRow(type);
		var types = type.substring(0,4);
		if(types=="oper"){
			//经营净现金流计算
			calculateOperNet("oper");
		}else if(types=="fami"){
			//家庭净现金流计算
			calculateOperNet("family");
		}
		//计算净现金流
		calculateNet();
		//计算各项等效
		calculateEveryEq();*/
	}
	//计算所有
	function calculateAll(){
		
	}
	
	//统计所有列及等效值
	function calculateAllDown(){
		/*//经营现金流入合计
		calculateAllDownByName("operIn");
		//经营现金流出合计
		calculateAllDownByName("operOut");
		//家庭现金流入合计
		calculateAllDownByName("familyIn");
		//家庭现金流出合计
		calculateAllDownByName("familyOut");
		//现金流计算
		calculateOperNet("oper");
		calculateOperNet("family");
		//计算净现金流
		calculateNet();
		//计算各项等效
		calculateEveryEq();
	}
	//统计经营现金流入项目所有列
	function calculateAllDownByName(begin){
		calculateDownById(begin+"_item1_day");
		calculateDownById(begin+"_item1_week");
		calculateDownById(begin+"_item1_month");
		calculateDownById(begin+"_item1_quarter");
		calculateDownById(begin+"_item1_halfYear");
		calculateDownById(begin+"_item1_nineMonth");
		calculateDownById(begin+"_item1_year");*/
	}
	
	function setZero(str){
		if(str==""){
			return parseFloat(0);
		}else{
			return str;
		}
	}
	
	function checkEmpty(obj){
		var valueT = obj.value;
		var id = obj.id;
		if(valueT == ""){
			//$("#"+id).numberbox('setValue',0);
			//calculate(obj,inputTypeS);
		}
	}
	
	//统计行,根据id区分，格式为:operIn_item1_day 总数以:eqMonth结尾 operIn_item1_eqMonth
	function calculateRow(obj){
		var objId = obj.id;
		calculateRowById(objId);
	}
	function calculateRowById(id){
		/*var strs = id.split("_");
		var day = "day";
		var week = "week";
		var month = "month";
		var quarter = "quarter";
		var halfYear = "halfYear";
		var nineMonth = "nineMonth";
		var year = "year";
		var add = 0;
		var begin = strs[0]+"_"+strs[1]+"_";
		var dayValue = setZero(document.getElementById(begin+day).value);
		var weekValue = setZero(document.getElementById(begin+week).value);
		var monthValue = setZero(document.getElementById(begin+month).value);
		var quarterValue = setZero(document.getElementById(begin+quarter).value);
		var halfYearValue = setZero(document.getElementById(begin+halfYear).value);
		var nineMonthValue = setZero(document.getElementById(begin+nineMonth).value);
		var yearValue = setZero(document.getElementById(begin+year).value);
		
		
		add = parseFloat(dayValue)*30 + parseFloat(weekValue)/7*30 
				+ parseFloat(monthValue) + parseFloat(quarterValue)/3 
				+ parseFloat(halfYearValue)/6 + parseFloat(nineMonthValue)/9
				+ parseFloat(yearValue)/12;
		//document.getElementById('display').innerHTML = parseFloat(dayValue);
		var num = cutDecimal(add.toString(),2);
		document.getElementById(begin+"eqMonth").value = num;*/
	}
	
	//统计列
	function calculateDown(obj){
		/*//获取所有的input对象
		var objId = obj.id;
		calculateDownById(objId);*/
	}
	function calculateDownById(objId){
		/*var controls = document.getElementsByTagName('input');
		var objStrs = objId.split("_");
		var downTotal = 0;
		var begin = objStrs[0]+"_"+objStrs[2]+"_";
		//遍历所有的input对象
		for(var i=0; i<controls.length; i++){
			var id = controls[i].id;
			var strs = id.split("_");
			if(strs[0]==objStrs[0] && strs[2]==objStrs[2]){
				downTotal = parseFloat(downTotal) + parseFloat(setZero(controls[i].value));
			}
		}
		var num = cutDecimal(downTotal.toString(),2);
		document.getElementById(begin+"total").value = num;*/
	}
	//经营现金流合计(type控制是计算流入还是流出)
	function calculateTotalRow(type){
		/*var day = "day";
		var week = "week";
		var month = "month";
		var quarter = "quarter";
		var halfYear = "halfYear";
		var nineMonth = "nineMonth";
		var year = "year";
		var add = 0;
		var begin = type+"_";
		var end = "_total";
		var dayValue = setZero(document.getElementById(begin+day+end).value);
		var weekValue = setZero(document.getElementById(begin+week+end).value);
		var monthValue = setZero(document.getElementById(begin+month+end).value);
		var quarterValue = setZero(document.getElementById(begin+quarter+end).value);
		var halfYearValue = setZero(document.getElementById(begin+halfYear+end).value);
		var nineMonthValue = setZero(document.getElementById(begin+nineMonth+end).value);
		var yearValue = setZero(document.getElementById(begin+year+end).value);
		
		add = parseFloat(dayValue)*30 + parseFloat(weekValue)/7*30 
				+ parseFloat(monthValue) + parseFloat(quarterValue)/3 
				+ parseFloat(halfYearValue)/6 + parseFloat(nineMonthValue)/9
				+ parseFloat(yearValue)/12;
		
		var num = cutDecimal(add.toString(),2);
		document.getElementById(begin+"eqMonth"+end).value = num;*/
	}
	//经营/家庭-净现金流计算
	function calculateOperNet(type){
		/*var day = "day";
		var week = "week";
		var month = "month";
		var quarter = "quarter";
		var halfYear = "halfYear";
		var nineMonth = "nineMonth";
		var year = "year";
		var add = 0;
		var operIn = type+"In_";
		var operOut = type+"Out_";
		var operNet = type+"Net_";
		var total = "_total";
		//alert(document.getElementById(operNet+year+total));
		var dayValue = setZero(document.getElementById(operIn+day+total).value-document.getElementById(operOut+day+total).value);
		var weekValue = setZero(document.getElementById(operIn+week+total).value-document.getElementById(operOut+week+total).value);
		var monthValue = setZero(document.getElementById(operIn+month+total).value-document.getElementById(operOut+month+total).value);
		var quarterValue = setZero(document.getElementById(operIn+quarter+total).value-document.getElementById(operOut+quarter+total).value);
		var halfYearValue = setZero(document.getElementById(operIn+halfYear+total).value-document.getElementById(operOut+halfYear+total).value);
		var nineMonthValue = setZero(document.getElementById(operIn+nineMonth+total).value-document.getElementById(operOut+nineMonth+total).value);
		var yearValue = setZero(document.getElementById(operIn+year+total).value-document.getElementById(operOut+year+total).value);
		
		document.getElementById(type+"Net_day_total").value = cutDecimal(dayValue.toString(),2);
		document.getElementById(type+"Net_week_total").value = cutDecimal(weekValue.toString(),2);
		document.getElementById(type+"Net_month_total").value = cutDecimal(monthValue.toString(),2);
		document.getElementById(type+"Net_quarter_total").value = cutDecimal(quarterValue.toString(),2);
		document.getElementById(type+"Net_halfYear_total").value = cutDecimal(halfYearValue.toString(),2);
		document.getElementById(type+"Net_nineMonth_total").value = cutDecimal(nineMonthValue.toString(),2);
		document.getElementById(type+"Net_year_total").value = cutDecimal(yearValue.toString(),2);
		
		add = parseFloat(dayValue)*30 + parseFloat(weekValue)/7*30 
				+ parseFloat(monthValue) + parseFloat(quarterValue)/3 
				+ parseFloat(halfYearValue)/6 + parseFloat(nineMonthValue)/9
				+ parseFloat(yearValue)/12;
		//alert("add---"+document.getElementById("operNet_day_total"));
		var num = cutDecimal(add.toString(),2);
		
		document.getElementById(operNet+"eqMonth"+total).value = num;*/
		
	}
	//净现金流计算
	function calculateNet(){
		
		/*var add = 0;
		
		var dayValue = parseFloat(setZero(document.getElementById("operNet_day_total").value)) + parseFloat(setZero(document.getElementById("familyNet_day_total").value));
		var weekValue = parseFloat(setZero(document.getElementById("operNet_week_total").value)) + parseFloat(setZero(document.getElementById("familyNet_week_total").value));
		var monthValue = parseFloat(setZero(document.getElementById("operNet_month_total").value)) + parseFloat(setZero(document.getElementById("familyNet_month_total").value));
		var quarterValue = parseFloat(setZero(document.getElementById("operNet_quarter_total").value)) + parseFloat(setZero(document.getElementById("familyNet_quarter_total").value));
		var halfYearValue = parseFloat(setZero(document.getElementById("operNet_halfYear_total").value)) + parseFloat(setZero(document.getElementById("familyNet_halfYear_total").value));
		var nineMonthValue = parseFloat(setZero(document.getElementById("operNet_nineMonth_total").value)) + parseFloat(setZero(document.getElementById("familyNet_nineMonth_total").value));
		var yearValue = parseFloat(setZero(document.getElementById("operNet_year_total").value)) + parseFloat(setZero(document.getElementById("familyNet_year_total").value));
	
		document.getElementById("net_day_total").value = cutDecimal(dayValue.toString(),2);
		document.getElementById("net_week_total").value = cutDecimal(weekValue.toString(),2);
		document.getElementById("net_month_total").value = cutDecimal(monthValue.toString(),2);;
		document.getElementById("net_quarter_total").value = cutDecimal(quarterValue.toString(),2);;
		document.getElementById("net_halfYear_total").value = cutDecimal(halfYearValue.toString(),2);;
		document.getElementById("net_nineMonth_total").value = cutDecimal(nineMonthValue.toString(),2);;
		document.getElementById("net_year_total").value = cutDecimal(yearValue.toString(),2);;
		
		add = parseFloat(dayValue)*30 + parseFloat(weekValue)/7*30 
				+ parseFloat(monthValue) + parseFloat(quarterValue)/3 
				+ parseFloat(halfYearValue)/6 + parseFloat(nineMonthValue)/9
				+ parseFloat(yearValue)/12;
		//alert("add---"+document.getElementById("operNet_day_total"));
		var num = cutDecimal(add.toString(),2);
		
		document.getElementById("net_eqMonth_total").value = num;*/
			
	}
	//计算页底的各项等效
	function calculateEveryEq(){
		
		/*var netDay = setZero(document.getElementById("net_day_total").value);
		var netWeek = setZero(document.getElementById("net_week_total").value);
		var netMonth = setZero(document.getElementById("net_month_total").value);
		var netQuarter = setZero(document.getElementById("net_quarter_total").value);
		var nethalfYear = setZero(document.getElementById("net_halfYear_total").value);
		var netNineMonth = setZero(document.getElementById("net_nineMonth_total").value);
		var netYear = setZero(document.getElementById("net_year_total").value);
		
		//每日等效
		var dayEq = cutDecimal((parseFloat(netDay)*30).toString(),2);
		//每周等效
		var weekEq = cutDecimal((parseFloat(netWeek)/7*30).toString(),2);
		//每月等效
		var monthEq = cutDecimal(parseFloat(netMonth).toString(),2);
		//每季度等效
		var quarterEq = cutDecimal((parseFloat(netQuarter)/3).toString(),2);
		//每半年等效
		var halfYearEq = cutDecimal((parseFloat(nethalfYear)/6).toString(),2);
		//每九个月等效
		var nineMonthEq = cutDecimal((parseFloat(netNineMonth)/9).toString(),2);
		//每年等效
		var yearEq = cutDecimal((parseFloat(netYear)/12).toString(),2);
		
		//每日等效
		document.getElementById("everyDayEq").value = dayEq;
		//每周等效
		document.getElementById("everyWeekEq").value = weekEq;
		//每月等效
		document.getElementById("everyMonthEq").value = monthEq;
		//每季度等效
		document.getElementById("everyQuarterEq").value = quarterEq;
		//每半年等效
		document.getElementById("everyHalfYearEq").value = halfYearEq;
		//每九个月等效
		document.getElementById("everyNineMonthEq").value = nineMonthEq;
		//每年等效
		document.getElementById("everyYearEq").value = yearEq;
		
		//可支配现金
		var canDominateMoneyN = parseFloat(dayEq) + parseFloat(weekEq) + parseFloat(monthEq) 
			+ parseFloat(quarterEq) + parseFloat(halfYearEq) + parseFloat(nineMonthEq) + parseFloat(yearEq);
		
		document.getElementById("canDominateMoney").value = cutDecimal(canDominateMoneyN,2);
		
		//现金流调整系数
		var adjNum = parseFloat(document.getElementById("csAdjCoefficient").value)/100;
		//年化利率
		var yir = parseFloat(setZero(document.getElementById("yearInterestRate").value))/100;
		
		var loanDeadline = parseFloat(document.getElementById("loanDeadline").value);
		var maxLoanSumN = (canDominateMoneyN*adjNum*loanDeadline)/(1+(yir/(12*12)));
		
		//最大借款额度
		document.getElementById("maxLoanSum").value = cutDecimal(maxLoanSumN.toString(),2);*/
		
	}
	
	//截取两位小数
	function cutDecimal(str,median){
		return Math.round(parseFloat(str)*100)/100;
	}
	
</script>

<script type="text/javascript">
<!--
	$("<div class='datagrid-mask'></div>").css({ cursor:"wait", display: "block", width: "100%", height: $(window).height() }).appendTo("load");  
	$("<div class='datagrid-mask-msg'></div>").html("数据加载中,请稍后...").appendTo("load").css({ cursor:"wait",display: "block", left: ($(document.body).outerWidth(true) - 350) / 2, top: ($(window).height()) / 2 - 90 });  
//-->
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

/*表头标题*/
.cashStreamDiv .title {
	text-align: center;
	font-weight: bold;
}

.cashStream_item_tr {
	text-align: center;
}

.cashStream_item_tr td input {
	width: 70px;
	text-align: left;
}
-->
</style>
<load>
</load>
<div class="cashStreamDiv">
	<form id="cashStreamForm" name="form1" method="post">
		<table width="892" align="center" cellpadding="3" cellspacing="0">
			<!-- 表名 -->
			<tr>
				<td colspan="8" align="center"
					style="border-left: none; border-top: none; border-right: none;">
					<br />
					<font style="font-size: 17px; letter-spacing: 3px;">现金流入和流出表</font>
				</td>
			</tr>
			<tr>
				<td width="150" align="center">
					客户姓名:
				</td>
				<td colspan="5">
					<input readonly style="border:none;text-align:right;" type="text" id="borrowerName"  style="border:none;text-align:left;" />
					<font id='display' color="red"></font>
				</td>
				<td align="center">
					日期：
				</td>
				<td><input readonly style="border:none;text-align:right;" type="text" id="creatDate"  style="border:none;text-align:center;" /></td>
			</tr>
			<!-- 表头  -->
			<tr class="title">
				<td>
					项目
				</td>
				<td>
					每日
				</td>
				<td>
					每周
				</td>
				<td>
					每月
				</td>
				<td>
					每季度
				</td>
				<td>
					每半年
				</td>
				<!--<td>
					9个月
				</td>
				--><td>
					每年
				</td>
				<td>
					等效月度
				</td>
			</tr>
			<tr>
				<td align="left">
					经营现金流入项目:
				</td>
				<td colspan="7"></td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目1:<input readonly style="border:none;text-align:right;" type="text" name="csOperInList[0].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 2px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperInList[0].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn')" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[0].everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn')" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[0].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item1_eqMonth" name="csOperInList[0].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目2:<input readonly style="border:none;text-align:right;" type="text" name="csOperInList[1].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 2px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperInList[1].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  name="csOperInList[1].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item2_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" name="csOperInList[1].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目3:<input readonly style="border:none;text-align:right;" type="text" name="csOperInList[2].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 2px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperInList[2].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyWeek"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperInList[2].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_item3_eqMonth" name="csOperInList[2].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					经营现金流入合计
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperInTotal.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_day_total" name="csOperInTotal.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_week_total" name="csOperInTotal.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_month_total" name="csOperInTotal.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_quarter_total" name="csOperInTotal.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_halfYear_total" name="csOperInTotal.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_nineMonth_total" name="csOperInTotal.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_year_total" name="csOperInTotal.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operIn_eqMonth_total" name="csOperInTotal.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr>
				<td align="left">
					经营现金流出项目:
				</td>
				<td colspan="7"></td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[0].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 2px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[0].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}"  />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[0].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item0_eqMonth" name="csOperOutList[0].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目1:<input readonly style="border:none;text-align:right;" type="text" name="csOperOutListChild[0].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 2px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutListChild[0].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperInList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutListChild[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[0].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item1_eqMonth" name="csOperOutListChild[0].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目2:<input readonly style="border:none;text-align:right;" type="text" name="csOperOutListChild[1].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutListChild[1].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutListChild[1].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[1].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item2_eqMonth" name="csOperOutListChild[1].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					项目3:<input readonly style="border:none;text-align:right;" type="text" name="csOperOutListChild[2].projectName"  style="width:100px;border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutListChild[2].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutListChild[2].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutListChild[2].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item3_eqMonth" name="csOperOutListChild[2].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[1].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[1].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[1].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[1].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item4_eqMonth" name="csOperOutList[1].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[2].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[2].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[2].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[2].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item5_eqMonth" name="csOperOutList[2].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[3].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[3].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[3].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[3].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item6_eqMonth" name="csOperOutList[3].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[4].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[4].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[4].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[4].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item7_eqMonth" name="csOperOutList[4].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[5].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[5].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[5].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[5].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item8_eqMonth" name="csOperOutList[5].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[6].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[6].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[6].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[6].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item9_eqMonth" name="csOperOutList[6].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[7].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[7].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[7].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[7].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item10_eqMonth" name="csOperOutList[7].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[8].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[8].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[8].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[8].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item11_eqMonth" name="csOperOutList[8].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csOperOutList[9].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[9].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[9].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[9].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item12_eqMonth" name="csOperOutList[9].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csOperOutList[10].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[10].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[10].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[10].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item13_eqMonth" name="csOperOutList[10].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csOperOutList[11].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[11].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[11].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[11].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item14_eqMonth" name="csOperOutList[11].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csOperOutList[12].projectName"  />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[12].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[12].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[12].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item15_eqMonth" name="csOperOutList[12].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csOperOutList[13].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutList[13].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" name="csOperOutList[13].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'operOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csOperOutList[13].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_item16_eqMonth" name="csOperOutList[13].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td align="left">
					经营现金流出合计
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperOutTotal.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_day_total" name="csOperOutTotal.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_week_total" name="csOperOutTotal.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_month_total" name="csOperOutTotal.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_quarter_total" name="csOperOutTotal.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_halfYear_total" name="csOperOutTotal.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_nineMonth_total" name="csOperOutTotal.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_year_total" name="csOperOutTotal.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operOut_eqMonth_total" name="csOperOutTotal.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					经营净现金流
					<input readonly style="border:none;text-align:right;" type="hidden" name="csOperNet.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_day_total" name="csOperNet.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_week_total" name="csOperNet.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_month_total" name="csOperNet.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_quarter_total" name="csOperNet.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_halfYear_total" name="csOperNet.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_nineMonth_total" name="csOperNet.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_year_total" name="csOperNet.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="operNet_eqMonth_total" name="csOperNet.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr>
				<td align="left">
					家庭现金流入项目:
				</td>
				<td colspan="7"></td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyInList[0].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyInList[0].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" name="csFamilyInList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[0].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item0_eqMonth" name="csFamilyInList[0].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyInList[1].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyInList[1].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" name="csFamilyInList[1].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[1].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item1_eqMonth" name="csFamilyInList[1].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyInList[2].projectName"  />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyInList[2].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" name="csFamilyInList[2].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[2].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item2_eqMonth" name="csFamilyInList[2].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyInList[3].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyInList[3].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" name="csFamilyInList[3].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyIn');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyInList[3].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_item3_eqMonth" name="csFamilyInList[3].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>

			<%--2014-12-22取消显示
			<tr class="cashStream_item_tr">
				<td align="left">
					家庭现金流入合计
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyInTotal.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_day_total" name="csFamilyInTotal.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_week_total" name="csFamilyInTotal.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_month_total" name="csFamilyInTotal.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_quarter_total" name="csFamilyInTotal.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_halfYear_total" name="csFamilyInTotal.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_nineMonth_total" name="csFamilyInTotal.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_year_total" name="csFamilyInTotal.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyIn_eqMonth_total" name="csFamilyInTotal.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>--%>
			
			<tr>
				<td align="left">
					家庭现金流出项目:
				</td>
				<td colspan="7"></td>
			</tr>
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[0].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[0].cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[0].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item0_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[0].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[1].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[1].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[1].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[1].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item1_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[1].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[2].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[2].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[2].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[2].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item2_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[2].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[3].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[3].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[3].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[3].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item3_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[3].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[4].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[4].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[4].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[4].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item4_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[4].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[5].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[5].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[5].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[5].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item5_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[5].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[6].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[6].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[6].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[6].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item6_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[6].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[7].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[7].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[7].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[7].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item7_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[7].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[8].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[8].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[8].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[8].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item8_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[8].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[9].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[9].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[9].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[9].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item9_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[9].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[10].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[10].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[10].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[10].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item10_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[10].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[11].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[11].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[11].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[11].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item11_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[11].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[12].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[12].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[12].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[12].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item12_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[12].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="csFamilyOutList[13].projectName"  style="border:none;text-align:center;letter-spacing: 1px;" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[13].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[13].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[13].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item13_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[13].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyOutList[14].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[14].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[14].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[14].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item14_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[14].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyOutList[15].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[15].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[15].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[15].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item15_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[15].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyOutList[16].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[16].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[16].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[16].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item16_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[16].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td>
					其他:<input readonly style="border:none;text-align:right;" type="text" maxlength="11" name="csFamilyOutList[17].projectName" />
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutList[17].cashStreamId" />
				</td>
				<td>
					<!-- <input readonly style="border:none;text-align:right;" type="text" initValue="123" name="csOperOutList[0].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="return checkNum(event,this);"  onblur="checkNum2(this);" /> -->
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_day" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[17].everyDay" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_week" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].everyWeek" onkeypress="if(event.which==45){return false;}" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_month" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].everyMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_quarter" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_halfYear" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_nineMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_year" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" maxlength="11" precision="2" onkeypress="if(event.which==45){return false;}" name="csFamilyOutList[17].everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_item17_eqMonth" onblur="checkEmpty(this);" onkeyup="calculate(this,'familyOut');" name="csFamilyOutList[17].calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>

			<%--2014-12-22取消显示
			<tr class="cashStream_item_tr">
				<td align="left">
					家庭现金流出合计
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyOutTotal.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_day_total" name="csFamilyOutTotal.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_week_total" name="csFamilyOutTotal.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_month_total" name="csFamilyOutTotal.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_quarter_total" name="csFamilyOutTotal.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_halfYear_total" name="csFamilyOutTotal.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_nineMonth_total" name="csFamilyOutTotal.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_year_total" name="csFamilyOutTotal.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyOut_eqMonth_total" name="csFamilyOutTotal.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>--%>
			<tr class="cashStream_item_tr">
				<td align="left">
					家庭净现金流
					<input readonly style="border:none;text-align:right;" type="hidden" name="csFamilyNet.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_day_total" name="csFamilyNet.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_week_total" name="csFamilyNet.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_month_total" name="csFamilyNet.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_quarter_total" name="csFamilyNet.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_halfYear_total" name="csFamilyNet.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_nineMonth_total" name="csFamilyNet.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_year_total" name="csFamilyNet.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="familyNet_eqMonth_total" name="csFamilyNet.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			
			<tr class="cashStream_item_tr">
				<td align="left">
					净现金流合计
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetTotal.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_day_total" name="csNetTotal.everyDay"  style="border:none;text-align:left;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_week_total" name="csNetTotal.everyWeek"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_month_total" name="csNetTotal.everyMonth"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_quarter_total" name="csNetTotal.everyQuarter"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_halfYear_total" name="csNetTotal.everyHalfYear"  style="border:none;text-align:left;"/>
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_nineMonth_total" name="csNetTotal.nineMonth"  style="border:none;text-align:left;"/>
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_year_total" name="csNetTotal.everyYear"  style="border:none;text-align:left;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="net_eqMonth_total" name="csNetTotal.calculateValue"  style="border:none;text-align:center;"/>
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					每日等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyDayEq" name="csNetEveryDayEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryDayEq.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					每周等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyWeekEq" name="csNetEveryWeekEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryWeekEq.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					每月等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyMonthEq" name="csNetEveryMonthEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryMonthEq.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					每季度等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyQuarterEq" name="csNetEveryQuarterEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryQuarterEq.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					每半年等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyHalfYearEq" name="csNetEveryHalfYearEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryHalfYearEq.everyMonth" />
				</td>
			</tr>
			<!--<tr class="cashStream_item_tr">
				<td align="left">
					九个月等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyNineMonthEq" name="csNetEverynineMonthEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyHalfYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.nineMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEverynineMonthEq.everyMonth" />
				</td>
			</tr>
			--><tr class="cashStream_item_tr">
				<td align="left">
					年度等效
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="everyYearEq" name="csNetEveryYearEq.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csNetEveryYearEq.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					可支配现金
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="canDominateMoney" name="canDominateMoney.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="canDominateMoney.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					现金流调整系数
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="csAdjCoefficient" name="csAdjCoefficient.calculateValue"  style="border:none;text-align:right;"/>%
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="csAdjCoefficient.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					年化利率
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="yearInterestRate" name="yearInterestRate.calculateValue"  style="border:none;text-align:right;" />%
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="yearInterestRate.everyMonth" />
				</td>
			</tr>
			<%--<tr class="cashStream_item_tr">
				<td align="left">
					借款期限
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="loanDeadline" name="loanDeadline.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyHalfYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.nineMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="loanDeadline.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					等额还款期限
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="eqRepaymentPeriod" name="eqRepaymentPeriod.calculateValue"  style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyHalfYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.nineMonth" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="eqRepaymentPeriod.everyMonth" />
				</td>
			</tr>
			--%><tr class="cashStream_item_tr">
				<td align="left">
					产品名称
				</td>
				<td colspan="7">
					<input id="repaymentPlanNamehq2" type="text" readonly="readonly" style="border: none;text-align:left;width:162px;" />
					<!-- 借款期限相关 -->
					<input type="hidden" name="loanDeadline.cashStreamId" />
					<input type="hidden" name="loanDeadline.everyDay" />
					<input type="hidden" name="loanDeadline.everyWeek" />
					<input type="hidden" id="loanDeadline" name="loanDeadline.calculateValue" readonly style="border:none;text-align:right;"/>
					<input type="hidden" name="loanDeadline.everyQuarter" />
					<input type="hidden" name="loanDeadline.everyHalfYear" />
					<!--<input type="hidden" name="loanDeadline.nineMonth" />
					--><input type="hidden" name="loanDeadline.everyYear" />
					<input type="hidden" name="loanDeadline.everyMonth" />
					<!-- 等额还款期限相关 -->
					<input type="hidden" name="eqRepaymentPeriod.cashStreamId" />
					<input type="hidden" name="eqRepaymentPeriod.everyDay" />
					<input type="hidden" name="eqRepaymentPeriod.everyWeek" />
					<input type="hidden" id="eqRepaymentPeriod" name="eqRepaymentPeriod.calculateValue" readonly style="border:none;text-align:right;"/>
					<input type="hidden" name="eqRepaymentPeriod.everyQuarter" />
					<input type="hidden" name="eqRepaymentPeriod.everyHalfYear" />
					<!--<input type="hidden" name="eqRepaymentPeriod.nineMonth" />
					--><input type="hidden" name="eqRepaymentPeriod.everyYear" />
					<input type="hidden" name="eqRepaymentPeriod.everyMonth" />
				</td>
			</tr>
			<%--2014-12-22增加显示--%>
			<tr class="cashStream_item_tr">
				<td align="left"> 等额还款期数</td>
				<td></td>
				<td></td>
				<td>
					<input type="text" name="eqRepaymentPeriod.calculateValue" class="easyui-numberbox" readonly="readonly" style="border:none;text-align:right;"/>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					申请借款金额
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" name="applyLoanSum.calculateValue" maxlength="11" onkeypress="if(event.which==45){return false;}" style="text-align:right;" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="applyLoanSum.everyMonth" />
				</td>
			</tr>
			<tr class="cashStream_item_tr">
				<td align="left">
					最大借款额度
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.cashStreamId" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyDay" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyWeek" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="text" id="maxLoanSum" name="maxLoanSum.calculateValue"   style="border:none;text-align:right;"/>
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyQuarter" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyHalfYear" />
				</td>
				<!--<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.nineMonth" />
				</td>
				--><td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyYear" />
				</td>
				<td>
					<input readonly style="border:none;text-align:right;" type="hidden" name="maxLoanSum.everyMonth" />
				</td>
			</tr>
		</table>
	</form>
</div>