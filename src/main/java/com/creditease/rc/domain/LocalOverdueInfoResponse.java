package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author haoqiang
 * 
 */
public class LocalOverdueInfoResponse implements Serializable {

	private int sOverdueCount;// 逾期数
	private int aOverdueCountTotle;// 逾期期数总计
	private List<Long> creditApplicationIdList;// 信贷申请Id集合
	private Map<Long, OverDueObj> overDueObjMap;

	public int getsOverdueCount() {
		return sOverdueCount;
	}

	public void setsOverdueCount(int sOverdueCount) {
		this.sOverdueCount = sOverdueCount;
	}

	public int getaOverdueCountTotle() {
		return aOverdueCountTotle;
	}

	public void setaOverdueCountTotle(int aOverdueCountTotle) {
		this.aOverdueCountTotle = aOverdueCountTotle;
	}

	public List<Long> getCreditApplicationIdList() {
		return creditApplicationIdList;
	}

	public void setCreditApplicationIdList(List<Long> creditApplicationIdList) {
		this.creditApplicationIdList = creditApplicationIdList;
	}

	public Map<Long, OverDueObj> getOverDueObjMap() {
		return overDueObjMap;
	}

	public void setOverDueObjMap(Map<Long, OverDueObj> overDueObjMap) {
		this.overDueObjMap = overDueObjMap;
	}

}
