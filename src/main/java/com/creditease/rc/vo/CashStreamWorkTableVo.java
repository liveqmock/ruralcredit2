package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.AvgIncRate;
import com.creditease.rc.domain.OperAverageCsIn;
import com.creditease.rc.domain.PurchaceCost;
/**
 * 
 * cs
 * Title:CashStreamWorkTableVo.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:27:30
 * @author wyf
 * @version v2.0
 */
public class CashStreamWorkTableVo {
	
	List<OperAverageCsIn> everyDayOperCsIn;
	List<OperAverageCsIn> everyWeekOperCsIn;
	List<OperAverageCsIn> everyMonthOperCsIn;
	List<OperAverageCsIn> everyCostOperCsIn;
	List<AvgIncRate> avgIncRates;
	List<PurchaceCost> purchaceCosts;

	public List<OperAverageCsIn> getEveryDayOperCsIn() {
		return everyDayOperCsIn;
	}

	public void setEveryDayOperCsIn(List<OperAverageCsIn> everyDayOperCsIn) {
		this.everyDayOperCsIn = everyDayOperCsIn;
	}

	public List<OperAverageCsIn> getEveryWeekOperCsIn() {
		return everyWeekOperCsIn;
	}

	public void setEveryWeekOperCsIn(List<OperAverageCsIn> everyWeekOperCsIn) {
		this.everyWeekOperCsIn = everyWeekOperCsIn;
	}

	public List<OperAverageCsIn> getEveryMonthOperCsIn() {
		return everyMonthOperCsIn;
	}

	public void setEveryMonthOperCsIn(List<OperAverageCsIn> everyMonthOperCsIn) {
		this.everyMonthOperCsIn = everyMonthOperCsIn;
	}

	public List<OperAverageCsIn> getEveryCostOperCsIn() {
		return everyCostOperCsIn;
	}

	public void setEveryCostOperCsIn(List<OperAverageCsIn> everyCostOperCsIn) {
		this.everyCostOperCsIn = everyCostOperCsIn;
	}

	public List<AvgIncRate> getAvgIncRates() {
		return avgIncRates;
	}

	public void setAvgIncRates(List<AvgIncRate> avgIncRates) {
		this.avgIncRates = avgIncRates;
	}

	public List<PurchaceCost> getPurchaceCosts() {
		return purchaceCosts;
	}

	public void setPurchaceCosts(List<PurchaceCost> purchaceCosts) {
		this.purchaceCosts = purchaceCosts;
	}
	
}
