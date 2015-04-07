/**
 * Title:DepartmentEntity.java  
 * Description:  
 */
package com.creditease.rc.common;

/**
 * Title:DepartmentEntity.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
public class DepartmentEntity {

	/**
	 * @Description 默认构造器 
	 */
	public DepartmentEntity() {
		// TODO Auto-generated constructor stub
	}
	//部门id
	private String departmentId;
	//部门名称
	private String departmentName;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
