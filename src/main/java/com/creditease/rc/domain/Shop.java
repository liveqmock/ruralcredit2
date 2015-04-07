package com.creditease.rc.domain;
/**
 * 
 * Shop.java
 * @author yifengwang
 * 2012-12-24 下午03:45:19
 * @version V2.0
 */
public class Shop {

	String name;
	
	String staffName[];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getStaffName() {
		return staffName;
	}
	public void setStaffName(String[] staffName) {
		this.staffName = staffName;
	}
	/**
	 * 
	 */
	public Shop() {
	} 
	
}