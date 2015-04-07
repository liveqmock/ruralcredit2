package com.creditease.rc.service;

import java.util.Map;

import com.creditease.rc.vo.DepartmentEntityVo;

public interface IEasyUIService {

	public void constructDepartmentMap();

	public Map<String, DepartmentEntityVo> getDepartmentMapByAuthority();

}
