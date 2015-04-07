package com.creditease.rc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.vo.DepartmentEntityVo;

public class DepartmentUtil {

	private DepartmentUtil() {
		System.out.println("DepartmentUtil实例化");
	}

	public static Map<String, DepartmentEntityVo> departmentMap = new HashMap<String, DepartmentEntityVo>();

	public static Map<String, Object> retMapByCategory = new HashMap<String, Object>();

	private static DepartmentUtil instance = null;

	public static DepartmentUtil getInstance() {
		if (null == instance) {
			synchronized (DepartmentUtil.class) {
				if (null == instance) {
					instance = new DepartmentUtil();
				}
			}
		}
		return instance;
	}

	public static Map<String, DepartmentEntityVo> getDepartmentMapByAuthority() {
		// TODO Auto-generated method stub
		Integer getAreaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		Map<String, DepartmentEntityVo> departmentMap = new HashMap<String, DepartmentEntityVo>();
		if (getAreaDepartmentId == null) {
			// 如果没有getAreaDepartmentId说明是总部人员，或者大区经理，或者风险经理
			Integer getCityDepartmentId = SpringSecurityUtils.getCurrentUser().getCityDepartmentId();
			if (getCityDepartmentId == null) {
				// 如果没有getCityDepartmentId说明是总部人员
				departmentMap = constructMap("0");
			} else {
				// 如果有getCityDepartmentId说明是区域级别的经理或者风险经理
				departmentMap = constructMap(getCityDepartmentId.toString());
			}
		} else {
			// 如果有getAreaDepartmentId说明是 营业部经理或者信贷员
			departmentMap = constructMap(getAreaDepartmentId.toString());
		}
		return departmentMap;
	}

	private static Map<String, DepartmentEntityVo> constructMap(String departmentId) {
		Map<String, DepartmentEntityVo> map = new HashMap<String, DepartmentEntityVo>();
		DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(departmentId);
		map.put(departmentId, departmentEntityVo);
		List<String> getDepartmentIds = departmentEntityVo.getDepartmentIds();
		if (CommonUtil.isNotEmpty(getDepartmentIds)) {
			for (String getDepartmentId : getDepartmentIds) {
				Map<String, DepartmentEntityVo> temap = constructMap(getDepartmentId);
				map.putAll(temap);
			}

		}
		return map;
	}

}
