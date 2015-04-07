package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.common.DepartmentEntity;

public class DepartmentEntityVo extends DepartmentEntity {

	private List<String> departmentIds;

	public List<String> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<String> departmentIds) {
		this.departmentIds = departmentIds;
	}

}
