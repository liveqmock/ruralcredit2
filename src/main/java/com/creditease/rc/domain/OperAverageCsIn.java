package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
/**
 * 
 *  平均每天经营现金流入表
 *  2012-04-22
 *  liuli
 *
 */
public class OperAverageCsIn implements Serializable {
	
	private Integer operAverageCsInId;
	private String item1;		//项目类型(如果该条记录item_sort大于0就是记录数据)
	private String item2;		//项目类型(如果该条记录item_sort大于0就是记录数据)
	private String item3;		//项目类型(如果该条记录item_sort大于0就是记录数据)
	private Integer itemSort;	//排序字段，从0开始
	private Integer belongId;	//所属的ID
	private String timeCycle;	//记录是按什么周期算，day-天，week-每周，month-每月
	
	public Integer getOperAverageCsInId() {
		return operAverageCsInId;
	}
	public void setOperAverageCsInId(Integer operAverageCsInId) {
		this.operAverageCsInId = operAverageCsInId;
	}
	public String getItem1() {
		return item1;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	public String getItem3() {
		return item3;
	}
	public void setItem3(String item3) {
		this.item3 = item3;
	}
	public Integer getItemSort() {
		return itemSort;
	}
	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public String getTimeCycle() {
		return timeCycle;
	}
	public void setTimeCycle(String timeCycle) {
		this.timeCycle = timeCycle;
	}
	
}
