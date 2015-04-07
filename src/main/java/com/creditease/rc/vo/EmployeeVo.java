package com.creditease.rc.vo;

import java.util.Date;

import com.creditease.rc.domain.ManagerSalesPlanning;

public class EmployeeVo extends ManagerSalesPlanning{
	private String name;
	private Date openauthoritydate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getOpenauthoritydate() {
		return openauthoritydate;
	}
	public void setOpenauthoritydate(Date openauthoritydate) {
		this.openauthoritydate = openauthoritydate;
	}
	

}
